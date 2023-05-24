--liquibase formatted sql

--changeset fjurken:create-table

create table roles(
    id BIGINT unique auto_increment not null,
    created date not null,
    updated date not null,
    status varchar(20) not null,

    name varchar(100) unique not null,
    constraint roles_pk primary key (id)
);