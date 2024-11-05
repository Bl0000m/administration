create table company_type
(
    id          bigserial primary key,
    code        varchar(255) not null unique,
    name        jsonb,
    description jsonb
);

INSERT INTO company_type (id, code, name, description)
VALUES (default, 'COURIER_SERVICE', '{
  "en": "Courier service",
  "kk": "Курьерлік қызмет",
  "ru": "Курьерская служба"
}', '{
  "en": "Courier service",
  "kk": "Курьерлік қызмет",
  "ru": "Курьерская служба"
}');

INSERT INTO company_type (id, code, name, description)
VALUES (default, 'FLOWER_SHOP', '{
  "en": "Flower shot",
  "kk": "Гүл магазині",
  "ru": "Цветочный магазин"
}', '{
  "en": "Flower shot",
  "kk": "Гүл магазині",
  "ru": "Цветочный магазин"
}');

create table companies
(
    id                 bigserial primary key,
    bin                varchar(255)                        not null,
    name               varchar(255)                        not null,
    company_type_id    bigint references company_type (id) not null,
    address            varchar(255),
    additional_address text[],
    phone_number       varchar(15)                         not null unique,
    email              varchar(255)                        not null unique,
    website            varchar(255),
    social_media       varchar(255),
    contract_id        varchar(255),
    created_date       timestamp with time zone            not null default now(),
    updated_date       timestamp with time zone            not null default now(),
    is_deleted         boolean                             not null default false
);

drop table users_roles;

alter table users
    add column role_id bigint references role (id);

create table status
(
    id          bigserial primary key,
    code        varchar(255) not null unique,
    name        jsonb,
    description jsonb
);

INSERT INTO status (id, code, name, description)
VALUES (default, 'ACTIVE', '{
  "en": "Active",
  "kk": "Белсенді",
  "ru": "Активный"
}', '{
  "en": "Active",
  "kk": "Белсенді",
  "ru": "Активный"
}');

INSERT INTO status (id, code, name, description)
VALUES (default, 'NOT_ACTIVE', '{
  "en": "Not active",
  "kk": "Белсенді емес",
  "ru": "Не активный"
}', '{
  "en": "Not active",
  "kk": "Белсенді емес",
  "ru": "Не активный"
}');


create table employee
(
    id           bigserial primary key,
    keycloak_id  varchar(255) unique      not null,
    name         varchar(255)             not null,
    email        varchar(255)             not null unique,
    phone_number varchar(15)              not null unique,
    role_id      bigint references role (id),
    iin          varchar(12)              not null unique,
    position     varchar(255),
    company_id   bigint references companies (id),
    status_id    bigint references status (id),
    created_date timestamp with time zone not null default now(),
    updated_date timestamp with time zone not null default now(),
    is_deleted   boolean                  not null default false
);