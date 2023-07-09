--liquibase formatted sql

--changeset fjurken:create-table

create table user_roles(
    id BIGINT unique auto_increment not null,
    created timestamp not null,
    updated timestamp default current_timestamp(),
    status varchar(20) not null default 'ACTIVE',

    user_id BIGINT not null,
    role_id BIGINT not null,
    constraint user_roles_pk primary key (id)
);