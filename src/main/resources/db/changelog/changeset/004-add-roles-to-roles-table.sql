--liquibase formatted sql

--changeset fjurken:add-data-to-tables

insert into roles (id, created, updated, status, name)
values
    (1, current_timestamp() , current_timestamp(), 'ACTIVE', 'ROLE_USER'),
    (2, current_timestamp() , current_timestamp(), 'ACTIVE', 'ROLE_ADMIN');