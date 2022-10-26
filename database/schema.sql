create sequence cars_seq
    as integer;

alter sequence cars_seq owner to admin;

create sequence countdown_seq
    as integer;

alter sequence countdown_seq owner to admin;

create table car
(
    id              integer default nextval('cars_seq'::regclass) not null
        constraint car_pk
            primary key,
    model           varchar                                       not null,
    carregistration varchar                                       not null,
    entry           timestamp                                     not null,
    exit            timestamp
);

alter table car
    owner to admin;

alter sequence cars_seq owned by car.id;

create index car_carregistration_index
    on car (carregistration);

create index car_model_index
    on car (model);

