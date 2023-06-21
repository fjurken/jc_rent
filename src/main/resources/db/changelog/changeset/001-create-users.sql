--liquibase formatted sql

--changeset fjurken:create-table

create table users (
    id BIGINT unique auto_increment not null,
    created timestamp not null,
    updated timestamp default current_timestamp(),
    status varchar(20) not null default 'NOT_ACTIVE',

    username varchar(100) unique not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    constraint user_pk primary key (id)
);

