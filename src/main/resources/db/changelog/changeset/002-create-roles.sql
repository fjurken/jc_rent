--liquibase formatted sql

--changeset fjurken:create-table

create table main.roles
(
    id      BIGINT                   not null,
    created timestamp with time zone not null,
    updated timestamp with time zone,
    status  varchar(20)              not null default 'ACTIVE',

    name    varchar(100) unique      not null,
    constraint roles_pk primary key (id)
);