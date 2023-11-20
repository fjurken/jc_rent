-- liquibase formatted.sql

-- changeset fjurken:create-table

create table main.rent
(
    id            BIGINT                   not null,
    created       timestamp with time zone not null,
    updated       timestamp with time zone,
    status        varchar(20)              not null,

    car_id        BIGINT                   not null,
    user_id       varchar(100)             not null,
    start_date    timestamp                not null default current_timestamp,
    end_date      timestamp                not null default current_timestamp,
    finished_date timestamp                null,
    constraint rent_pk primary key (id)
)