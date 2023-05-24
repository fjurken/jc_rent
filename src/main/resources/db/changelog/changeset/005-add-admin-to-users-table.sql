--liquibase formatted sql

--changeset fjurken:add-data-to-tables

INSERT INTO users(
    id,
    created,
    updated,
    status,
    username,
    first_name,
    last_name,
    password
)
VALUES(
    1,
    current_date(),
    current_date(),
    'ACTIVE',
    'admin_jcrent@gmail.com',
    'admin',
    'admin',
    '$2a$12$RyhEBhwkgsjla1U5RHOSs.F/lX5tDiV2K/ROq6pwYNeShXX50LTqW'
);

INSERT INTO user_roles(id, created, updated, status, user_id, role_id)
VALUES(1, current_date(), current_date(), 'ACTIVE', 1, 1),
(2, current_date(), current_date(), 'ACTIVE', 1, 2);