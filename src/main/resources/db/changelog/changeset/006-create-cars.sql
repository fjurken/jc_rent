-- liquibase formatted.sql

-- changeset fjurken:create-table

create table cars (
    id BIGINT unique not null,
    created date not null,
    updated date not null,
    status varchar(20) not null,

    brand varchar(30) not null,
    model varchar(30) not null,
    car_type varchar(10) not null,
    color varchar(10) not null,
    engine_type varchar(10) not null,
    engine_capacity varchar(10),
    engine_power int not null,
    transmission varchar(10) not null,
    licence_plate varchar(10) not null,
    constraint cars_pk primary key (id)
)