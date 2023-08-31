-- liquibase formatted.sql

-- changeset fjurken:create-table

create table rent (
    id BIGINT unique auto_increment not null,
    created timestamp not null,
    updated timestamp default current_timestamp(),
    status varchar(20) not null,

    car_id BIGINT not null,
    user_id varchar(100) not null,
    start_date timestamp not null default current_timestamp(),
    end_date timestamp not null default current_timestamp(),
    finished_date timestamp null,
    constraint rent_pk primary key (id)
)