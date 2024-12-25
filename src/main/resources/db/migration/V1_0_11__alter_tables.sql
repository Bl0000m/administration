alter table flower
drop
column description;


alter table flower_variety
    add column color VARCHAR(30);

alter table bouquet
drop
column description;

alter table bouquet
drop
column addition;

create table additional_elements
(
    id                  bigserial primary key,
    name                varchar(255),
    description         varchar(255),
    example             varchar(255),
    unit_of_measurement varchar(255),
    created_date        timestamp with time zone not null default now(),
    created_by          VARCHAR(500)
);

create table bouquet_additional_elements
(
    id                     bigserial primary key,
    bouquet_id             bigint references bouquet (id)             not null,
    additional_elements_id bigint references additional_elements (id) not null,
    quantity               int,
    color                  varchar(30)
);

