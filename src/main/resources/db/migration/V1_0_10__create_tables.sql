create table bouquet_styles
(
    id           bigserial primary key,
    name         varchar(255),
    description  varchar(255),
    created_date timestamp with time zone not null default now(),
    updated_date timestamp with time zone not null default now(),
    created_by   VARCHAR(500),
    updated_by   VARCHAR(500),
    is_verify    boolean                  not null default false
);

create table country
(
    id   bigserial primary key,
    name varchar(255)
);

create table stem_care
(
    id           bigserial primary key,
    name         varchar(255),
    description  varchar(255),
    created_date timestamp with time zone not null default now(),
    updated_date timestamp with time zone not null default now(),
    created_by   VARCHAR(500),
    updated_by   VARCHAR(500)
);

create table temperature_care
(
    id           bigserial primary key,
    name         varchar(255),
    description  varchar(255),
    created_date timestamp with time zone not null default now(),
    updated_date timestamp with time zone not null default now(),
    created_by   VARCHAR(500),
    updated_by   VARCHAR(500)
);

create table water_care
(
    id           bigserial primary key,
    name         varchar(255),
    description  varchar(255),
    created_date timestamp with time zone not null default now(),
    updated_date timestamp with time zone not null default now(),
    created_by   VARCHAR(500),
    updated_by   VARCHAR(500)
);


create table flower_variety
(
    id                   bigserial primary key,
    name                 varchar(255),
    flower_id            bigint references flower (id)           not null,
    shelf_life_days_min  numeric,
    shelf_life_days_max  numeric,
    fragrance            varchar(255),
    season               varchar(255),
    steam_type           varchar(255),
    bud_size_min         numeric,
    bud_size_max         numeric,
    stem_height_size_min numeric,
    stem_height_size_max numeric,
    image                varchar(1000),
    stem_care_id         bigint references stem_care (id)        not null,
    temperature_care_id  bigint references temperature_care (id) not null,
    water_care_id        bigint references water_care (id)       not null,
    country_id           bigint references country (id)          not null,
    created_date         timestamp with time zone                not null default now(),
    updated_date         timestamp with time zone                not null default now(),
    created_by           VARCHAR(500),
    updated_by           VARCHAR(500)
);


alter table bouquet
    add column bouquet_styles_id bigint references bouquet_styles (id) not null;

drop table bouquet_flowers;

create table bouquet_flower_variety
(
    id                bigserial primary key,
    bouquet_id        bigint references bouquet (id)        not null,
    flower_variety_id bigint references flower_variety (id) not null,
    quantity          int
);
