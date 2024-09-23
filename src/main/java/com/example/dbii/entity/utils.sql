
CREATE OR REPLACE TYPE Salon_Type AS OBJECT (
    salon_id NUMBER,
    salon_name VARCHAR2(255),
    location VARCHAR2(255),
    max_capacity NUMBER
);
/

CREATE OR REPLACE TYPE SALON_TABLE AS TABLE OF SALON_TYPE;
/

CREATE OR REPLACE FUNCTION avaiblesSalons(p_date IN DATE)
RETURN Salon_Table PIPELINED
IS
BEGIN
    FOR rec IN (
        SELECT *
        FROM Salon s
        WHERE s.salon_id NOT IN (
            SELECT r.salon_id
            FROM Reservation r
            WHERE r.reservation_date = p_date
        )
    ) LOOP
        PIPE ROW(Salon_Type(rec.salon_id, rec.salon_name, rec.location, rec.max_capacity));
    END LOOP;

    RETURN;
END;
/



CREATE OR REPLACE PROCEDURE CHECK_IF_EMPLOYEE (
    p_email IN VARCHAR2,
    p_is_employee OUT NUMBER
) AS
BEGIN
    SELECT CASE WHEN isEmployee = 1 THEN 1 ELSE 0 END
    INTO p_is_employee
    FROM userE
    WHERE email = p_email;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_is_employee := -1;
END;
/

CREATE OR REPLACE PROCEDURE CheckSalonReservations (
    p_salon_id IN NUMBER,
    p_exists OUT NUMBER
)
IS
BEGIN
    p_exists := 0;
    
    BEGIN
        DECLARE
            v_count NUMBER;
        BEGIN
            SELECT COUNT(*)
            INTO v_count
            FROM Reservation
            WHERE salon_id = p_salon_id;
            
            IF v_count > 0 THEN
                p_exists := 1;
            ELSE
                p_exists := 0;
            END IF;
        EXCEPTION
            WHEN OTHERS THEN
                p_exists := -1;
        END;
    EXCEPTION
        WHEN OTHERS THEN
            p_exists := -1;
    END;
END;
/

CREATE OR REPLACE PROCEDURE CheckUserReservations (
    p_user_id IN NUMBER,
    p_has_reservations OUT NUMBER
)
IS
BEGIN
    p_has_reservations := 0;

    DECLARE
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*)
        INTO v_count
        FROM Reservation
        WHERE user_id = p_user_id;

        IF v_count > 0 THEN
            p_has_reservations := 1;
        ELSE
            p_has_reservations := 0;
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            p_has_reservations := -1;
    END;
END;
/


CREATE OR REPLACE TRIGGER manage_pack_price
AFTER INSERT OR DELETE OR UPDATE ON PACK_SERVICE
FOR EACH ROW
DECLARE
    v_service_price NUMBER;
BEGIN
    IF INSERTING THEN
        SELECT service_price INTO v_service_price
        FROM SERVICE
        WHERE service_id = :NEW.service_id;

        UPDATE PACK
        SET pack_price = pack_price + (v_service_price * 0.9)
        WHERE pack_id = :NEW.pack_id;

    ELSIF DELETING THEN
        SELECT service_price INTO v_service_price
        FROM SERVICE
        WHERE service_id = :OLD.service_id;

        UPDATE PACK
        SET pack_price = pack_price - (v_service_price * 0.9)
        WHERE pack_id = :OLD.pack_id;

    ELSIF UPDATING THEN
        SELECT service_price INTO v_service_price
        FROM SERVICE
        WHERE service_id = :OLD.service_id;

        UPDATE PACK
        SET pack_price = pack_price - (v_service_price * 0.9)
        WHERE pack_id = :OLD.pack_id;

        SELECT service_price INTO v_service_price
        FROM SERVICE
        WHERE service_id = :NEW.service_id;

        UPDATE PACK
        SET pack_price = pack_price + (v_service_price * 0.9)
        WHERE pack_id = :NEW.pack_id;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER reservation_package_price_auto
AFTER INSERT OR DELETE ON RESERVATION
FOR EACH ROW
DECLARE
    v_package_price NUMBER := 0;
BEGIN
    IF INSERTING THEN
        SELECT pack_price INTO v_package_price
        FROM PACK
        WHERE pack_id = :NEW.pack_id;

        UPDATE RESERVATION
        SET reservation_price = reservation_price + v_package_price
        WHERE reservation_id = :NEW.reservation_id;

    ELSIF DELETING THEN
        SELECT pack_price INTO v_package_price
        FROM PACK
        WHERE pack_id = :OLD.pack_id;

        UPDATE RESERVATION
        SET reservation_price = reservation_price - v_package_price
        WHERE reservation_id = :OLD.reservation_id;
    END IF;
END;
/


CREATE OR REPLACE FUNCTION Obtener_Caracteristica_Recurrente
RETURN VARCHAR2 IS
    v_caracteristica VARCHAR2(255);
BEGIN
    SELECT c.characteristic_name
    INTO v_caracteristica
    FROM Characteristic c
    LEFT OUTER JOIN Salon_Characteristic sc ON c.characteristic_id = sc.characteristic_id
    INNER JOIN Salon s ON sc.salon_id = s.salon_id
    GROUP BY c.characteristic_name
    ORDER BY SUM(sc.quantity) DESC
    FETCH FIRST 1 ROWS ONLY;

    RETURN v_caracteristica;
END;
/

CREATE OR REPLACE PACKAGE verificaciones IS
    FUNCTION avaiblesSalons(p_date IN DATE) RETURN Salon_Table PIPELINED;
    PROCEDURE CHECK_IF_EMPLOYEE(p_email IN VARCHAR2, p_is_employee OUT NUMBER);
    PROCEDURE CheckSalonReservations(p_salon_id IN NUMBER, p_exists OUT NUMBER);
    PROCEDURE CheckUserReservations(p_user_id IN NUMBER, p_has_reservations OUT NUMBER);
    FUNCTION Obtener_Caracteristica_Recurrente RETURN VARCHAR2;
END verificaciones;
/

CREATE OR REPLACE PACKAGE BODY verificaciones IS

    FUNCTION avaiblesSalons(p_date IN DATE)
        RETURN Salon_Table PIPELINED IS
    BEGIN
        FOR rec IN (
            SELECT *
            FROM Salon s
            WHERE s.salon_id NOT IN (
                SELECT r.salon_id
                FROM Reservation r
                WHERE r.reservation_date = p_date
            )
            ) LOOP
                PIPE ROW(Salon_Type(rec.salon_id, rec.salon_name, rec.location, rec.max_capacity));
            END LOOP;

        RETURN;
    END avaiblesSalons;

    PROCEDURE CHECK_IF_EMPLOYEE(p_email IN VARCHAR2, p_is_employee OUT NUMBER) IS
    BEGIN
        SELECT CASE WHEN isEmployee = 1 THEN 1 ELSE 0 END
        INTO p_is_employee
        FROM userE
        WHERE email = p_email;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            p_is_employee := -1;
    END CHECK_IF_EMPLOYEE;

    PROCEDURE CheckSalonReservations(p_salon_id IN NUMBER, p_exists OUT NUMBER) IS
    BEGIN
        p_exists := 0;
        DECLARE
            v_count NUMBER;
        BEGIN
            SELECT COUNT(*)
            INTO v_count
            FROM Reservation
            WHERE salon_id = p_salon_id;

            IF v_count > 0 THEN
                p_exists := 1;
            ELSE
                p_exists := 0;
            END IF;
        EXCEPTION
            WHEN OTHERS THEN
                p_exists := -1;
        END;
    END CheckSalonReservations;

    PROCEDURE CheckUserReservations(p_user_id IN NUMBER, p_has_reservations OUT NUMBER) IS
    BEGIN
        p_has_reservations := 0;
        DECLARE
            v_count NUMBER;
        BEGIN
            SELECT COUNT(*)
            INTO v_count
            FROM Reservation
            WHERE user_id = p_user_id;

            IF v_count > 0 THEN
                p_has_reservations := 1;
            ELSE
                p_has_reservations := 0;
            END IF;
        EXCEPTION
            WHEN OTHERS THEN
                p_has_reservations := -1;
        END;
    END CheckUserReservations;

END verificaciones;
/

CREATE OR REPLACE FUNCTION Obtener_Salon_y_Evento_Mas_Comun(
    p_fecha_inicio IN DATE,
    p_fecha_fin IN DATE
) RETURN VARCHAR2 IS
    v_resultado VARCHAR2(255);
BEGIN
    SELECT s.salon_name  ' - '  t.type_name
    INTO v_resultado
    FROM Salon s
    INNER JOIN Reservation r ON s.salon_id = r.salon_id
    INNER JOIN Type t ON r.type_id = t.type_id
    WHERE r.reservation_date BETWEEN p_fecha_inicio AND p_fecha_fin
    GROUP BY s.salon_name, t.type_name
    ORDER BY COUNT(r.reservation_id) DESC
    FETCH FIRST 1 ROWS ONLY;

    RETURN v_resultado;
END;
/

CREATE OR REPLACE TRIGGER reservation_service_price_auto
AFTER INSERT OR DELETE ON RESERVATION_SERVICE
FOR EACH ROW
DECLARE
    v_service_price NUMBER := 0;
BEGIN
    IF INSERTING THEN
        SELECT service_price INTO v_service_price
        FROM SERVICE
        WHERE service_id = :NEW.service_id;

        UPDATE RESERVATION
        SET reservation_price = reservation_price + v_service_price
        WHERE reservation_id = :NEW.reservation_id;

    ELSIF DELETING THEN
        SELECT service_price INTO v_service_price
        FROM SERVICE
        WHERE service_id = :OLD.service_id;

        UPDATE RESERVATION
        SET reservation_price = reservation_price - v_service_price
        WHERE reservation_id = :OLD.reservation_id;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER reservation_price_auto
AFTER UPDATE OF pack_id ON RESERVATION
FOR EACH ROW
DECLARE
    v_pack_price NUMBER := 0;
BEGIN
    IF :OLD.pack_id IS NOT NULL THEN
        BEGIN
            SELECT pack_price INTO v_pack_price
            FROM PACK
            WHERE pack_id = :OLD.pack_id;

            UPDATE RESERVATION
            SET reservation_price = reservation_price - v_pack_price
            WHERE reservation_id = :OLD.reservation_id;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                NULL;
        END;
    END IF;

    IF :NEW.pack_id IS NOT NULL THEN
        BEGIN
            SELECT pack_price INTO v_pack_price
            FROM PACK
            WHERE pack_id = :NEW.pack_id;

            UPDATE RESERVATION
            SET reservation_price = reservation_price + v_pack_price
            WHERE reservation_id = :NEW.reservation_id;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                NULL;
        END;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER reservation_price_auto
BEFORE UPDATE OF pack_id ON RESERVATION
FOR EACH ROW
DECLARE
    v_old_pack_price NUMBER := 0;
    v_new_pack_price NUMBER := 0;
BEGIN
    -- Si había un pack asociado previamente, obtenemos su precio
    IF :OLD.pack_id IS NOT NULL THEN
        BEGIN
            SELECT pack_price INTO v_old_pack_price
            FROM PACK
            WHERE pack_id = :OLD.pack_id;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                v_old_pack_price := 0;
        END;
    END IF;

    -- Si hay un nuevo pack asociado, obtenemos su precio
    IF :NEW.pack_id IS NOT NULL THEN
        BEGIN
            SELECT pack_price INTO v_new_pack_price
            FROM PACK
            WHERE pack_id = :NEW.pack_id;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                v_new_pack_price := 0;
        END;
    END IF;

    -- Actualizamos el precio de la reserva
    :NEW.reservation_price := :OLD.reservation_price - v_old_pack_price + v_new_pack_price;
END;
/
