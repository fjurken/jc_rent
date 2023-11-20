--liquibase formatted sql

--changeset fjurken:create-table

create table main.user_roles
(
    id      BIGINT                   not null,
    created timestamp with time zone not null,
    updated timestamp with time zone,
    status  varchar(20)              not null default 'ACTIVE',

    user_id BIGINT                   not null,
    role_id BIGINT                   not null,
    constraint user_roles_pk primary key (id)
);