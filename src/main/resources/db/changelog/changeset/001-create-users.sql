--liquibase formatted sql

--changeset fjurken:create-table

create table users (
    id BIGINT unique not null,
    created date not null,
    updated date not null,
    status varchar(20) not null,

    username varchar(100) unique not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    constraint user_pk primary key (id)
);

