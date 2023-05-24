-- liquibase formatted.sql

-- changeset fjurken:create-table

create table rent (
    id BIGINT unique auto_increment not null,
    created date not null,
    updated date not null,
    status varchar(20) not null,

    car_id BIGINT not null,
    start_date datetime not null,
    end_date datetime not null,
    finished_date datetime,
    constraint rent_pk primary key (id)
)