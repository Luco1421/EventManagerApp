# Event Manager App

Event-venue reservation system built with Spring Boot, for the *Bases de
Datos II* course. Lets customers browse event venues ("salones"), check
availability by date and event type, and build a reservation out of a venue,
an optional service pack, and individual add-on services; staff manage
venues, packs, services, and employee accounts from an admin view.

## Stack

- Java 21, Spring Boot 3.3.3
- Spring Data JPA, Spring Web, Thymeleaf (server-rendered views, no separate
  frontend or REST API — every route returns an HTML page)
- Oracle Autonomous Database, via `ojdbc11` + Oracle wallet (mTLS) connection
- Maven (with the Maven wrapper, `mvnw` / `mvnw.cmd`)

Authentication is a hand-rolled login/session flow (no Spring Security).
Pricing, availability lookups, and reporting are implemented largely in
Oracle: PL/SQL triggers keep reservation/pack prices in sync, and two of the
admin reporting pages call PL/SQL functions directly (most common salon
characteristic, most common salon + event type in a date range).

## Data model

Core JPA entities: `Salon`, `UserE` (customer or employee), `Reservation`,
`Type` (event type), `Pack`, `Service`, `Characteristic` /
`SalonCharacteristic` (venue amenities), `Image`. Schema DDL and PL/SQL
objects live in `src/main/java/com/example/dbii/entity/bda2.sql` and
`utils.sql` — there is no Flyway/Liquibase migration, the schema is applied
manually against the target Oracle instance.

## Running locally

Requires Java 21, Maven, and a reachable Oracle database (Autonomous DB with
wallet-based TLS, or any Oracle instance with `spring.datasource.*` in
`src/main/resources/application.properties` pointed at it). Apply the schema
first:

```bash
sqlplus <user>/<password>@<connect_string> @src/main/java/com/example/dbii/entity/bda2.sql
sqlplus <user>/<password>@<connect_string> @src/main/java/com/example/dbii/entity/utils.sql
```

Then build and run:

```bash
./mvnw clean package
./mvnw spring-boot:run
```

The app starts on the default port 8080.
