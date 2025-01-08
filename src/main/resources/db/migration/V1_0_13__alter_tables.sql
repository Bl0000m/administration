create table branch_division
(
    id           bigserial primary key,
    company_id   bigint references companies (id) not null,
    address      varchar(255),
    phone_number varchar(15),
    email        varchar(255)
);

alter table bouquet
drop
column company_id;

alter table bouquet
drop
column price;

create table flower_variety_price
(
    id                 bigserial primary key,
    flower_variety_id  bigint references flower_variety (id)  not null,
    branch_division_id bigint references branch_division (id) not null,
    price              numeric,
    currency           varchar(20),
    valid_from         timestamp with time zone,
    valid_to           timestamp with time zone,
    created_date       timestamp with time zone               not null default now(),
    updated_date       timestamp with time zone               not null default now(),
    created_by         VARCHAR(500),
    updated_by         VARCHAR(500)
);

create table additional_element_price
(
    id                     bigserial primary key,
    additional_elements_id bigint references additional_elements (id) not null,
    branch_division_id     bigint references branch_division (id)     not null,
    price                  numeric,
    currency               varchar(20),
    valid_from             timestamp with time zone,
    valid_to               timestamp with time zone,
    created_date           timestamp with time zone                   not null default now(),
    updated_date           timestamp with time zone                   not null default now(),
    created_by             VARCHAR(500),
    updated_by             VARCHAR(500)
);

create table bouquet_branch_price
(
    id                 bigserial primary key,
    bouquet_id         bigint references bouquet (id)         not null,
    branch_division_id bigint references branch_division (id) not null,
    price              numeric
);
