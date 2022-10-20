create sequence cars_seq
    as integer;

alter sequence cars_seq owner to admin;

create sequence countdown_seq
    as integer;

alter sequence countdown_seq owner to admin;

create table if not exists car
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

create index if not exists car_carregistration_index
    on car (carregistration);

create index if not exists car_model_index
    on car (model);

create table if not exists countdown
(
    id        integer default nextval('countdown_seq'::regclass) not null
        constraint countdown_pk
            primary key,
    carid     integer                                            not null
        constraint countdown_car_null_fk
            references car,
    startdate timestamp                                          not null,
    enddate   timestamp                                          not null,
    constraint countdown_id_carid_pk
        unique (id, carid)
);

alter table countdown
    owner to admin;

