CREATE TABLE Type (
    type_id NUMBER,
    type_name VARCHAR2(255)
);

CREATE TABLE Salon (
   salon_id NUMBER,
   salon_name VARCHAR2(255),
   location VARCHAR2(255),
   max_capacity NUMBER
);

CREATE TABLE Characteristic (
    characteristic_id NUMBER,
    characteristic_name VARCHAR2(255)
);

CREATE TABLE UserE (
    user_id NUMBER,
    user_name VARCHAR2(255),
    last_name VARCHAR2(255),
    phone NUMBER,
    email VARCHAR2(255),
    password VARCHAR2(255),
    isEmployee NUMBER
);

CREATE TABLE Pack (
    pack_id NUMBER,
    pack_name VARCHAR2(255),
    pack_description VARCHAR2(255),
    pack_price NUMBER
);

CREATE TABLE Service (
    service_id NUMBER,
    service_name VARCHAR2(255),
    service_description VARCHAR2(255),
    service_price NUMBER
);

CREATE TABLE Reservation (
    reservation_id NUMBER,
    salon_id NUMBER,
    type_id NUMBER,
    user_id NUMBER,
    pack_id NUMBER,
    reservation_state NUMBER,
    reservation_date DATE,
    reservation_price NUMBER,
    FOREIGN KEY (salon_id) REFERENCES Salon(salon_id),
    FOREIGN KEY (type_id) REFERENCES Type(type_id),
    FOREIGN KEY (user_id) REFERENCES UserE(user_id),
    FOREIGN KEY (pack_id) REFERENCES Pack(pack_id)
);

CREATE TABLE Salon_Characteristic (
    salon_id NUMBER,
    characteristic_id NUMBER,
    quantity NUMBER,
    PRIMARY KEY (salon_id, characteristic_id),
    FOREIGN KEY (salon_id) REFERENCES Salon(salon_id),
    FOREIGN KEY (characteristic_id) REFERENCES Characteristic(characteristic_id)
);

CREATE TABLE Image (
    image_id NUMBER,
    salon_id NUMBER,
    url VARCHAR2(255),
    FOREIGN KEY (salon_id) REFERENCES Salon(salon_id)
);

CREATE TABLE Pack_Service (
    pack_id NUMBER,
    service_id NUMBER,
    PRIMARY KEY (pack_id, service_id),
    FOREIGN KEY (pack_id) REFERENCES Pack(pack_id),
    FOREIGN KEY (service_id) REFERENCES Service(service_id)
);

CREATE TABLE Reservation_Service (
    reservation_id NUMBER,
    service_id NUMBER,
    PRIMARY KEY (reservation_id, service_id),
    FOREIGN KEY (reservation_id) REFERENCES Reservation(reservation_id),
    FOREIGN KEY (service_id) REFERENCES Service(service_id)
);

INSERT INTO Type(type_name) VALUES ('Aniversaio de boda');
INSERT INTO Type(type_name) VALUES ('Baby Shower');
INSERT INTO Type(type_name) VALUES ('Boda');
INSERT INTO Type(type_name) VALUES ('Cena');
INSERT INTO Type(type_name) VALUES ('Cincuentaños');
INSERT INTO Type(type_name) VALUES ('Cumpleaños');
INSERT INTO Type(type_name) VALUES ('Evento');
INSERT INTO Type(type_name) VALUES ('Fiesta');
INSERT INTO Type(type_name) VALUES ('Graduacion');
INSERT INTO Type(type_name) VALUES ('Quinceaños');

