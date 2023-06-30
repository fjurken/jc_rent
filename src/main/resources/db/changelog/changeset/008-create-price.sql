-- liquibase formatted.sql

-- changeset fjurken:create-table

create table price (
    id BIGINT unique auto_increment not null,
    created timestamp not null,
    updated timestamp default current_timestamp(),
    status varchar(20) not null,

    car_id BIGINT not null,
    price double not null,
    constraint rent_pk primary key (id)
)