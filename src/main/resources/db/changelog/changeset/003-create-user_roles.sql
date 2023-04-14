--liquibase formatted sql

--changeset fjurken:create-table

create table user_roles(
    id BIGINT unique not null,
    created date not null,
    updated date not null,
    status varchar(20) not null,

    user_id BIGINT not null,
    role_id BIGINT not null,
    constraint user_roles_pk primary key (id)
);