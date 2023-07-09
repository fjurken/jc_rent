--liquibase formatted sql

--changeset fjurken:create-table

create table roles(
    id BIGINT unique auto_increment not null,
    created timestamp not null,
    updated timestamp default current_timestamp(),
    status varchar(20) not null default 'ACTIVE',

    name varchar(100) unique not null,
    constraint roles_pk primary key (id)
);