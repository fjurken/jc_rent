--liquibase formatted sql

--changeset fjurken:create-table

create schema if not exists main;

create table main.users
(
    id         BIGINT                   not null,
    created    timestamp with time zone not null,
    updated    timestamp with time zone,
    status     varchar(20)              not null default 'ACTIVE',

    username   varchar(100) unique      not null,
    first_name varchar(255)             not null,
    last_name  varchar(255)             not null,
    password   varchar(255)             not null,
    constraint user_pk primary key (id)
);

