-- liquibase formatted.sql

-- changeset fjurken:create-table

create table main.price
(
    id      BIGINT                   not null,
    created timestamp with time zone not null,
    updated timestamp with time zone,
    status  varchar(20)              not null,

    car_id  BIGINT                   not null,
    price   double precision         not null,
    constraint price_pk primary key (id)
)