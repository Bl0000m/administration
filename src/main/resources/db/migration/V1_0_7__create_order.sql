create table order_status
(
    id          bigserial primary key,
    code        varchar(255) not null unique,
    name        jsonb,
    description jsonb
);

INSERT INTO order_status (id, code, name, description)
VALUES (default, 'NEW', '{
  "en": "New",
  "kk": "Жаңа",
  "ru": "Новый"
}', '{
  "en": "New",
  "kk": "Жаңа",
  "ru": "Новый"
}');

INSERT INTO order_status (id, code, name, description)
VALUES (default, 'IN_PROCESS', '{
  "en": "In process",
  "kk": "Процесінде",
  "ru": "В процессе"
}', '{
  "en": "In process",
  "kk": "Процесінде",
  "ru": "В процессе"
}');

INSERT INTO order_status (id, code, name, description)
VALUES (default, 'DELIVERING', '{
  "en": "Delivered",
  "kk": "Жеткізілуде",
  "ru": "Доставляется"
}', '{
  "en": "Delivered",
  "kk": "Жеткізілуде",
  "ru": "Доставляется"
}');

INSERT INTO order_status (id, code, name, description)
VALUES (default, 'COMPLETED', '{
  "en": "Completed",
  "kk": "Аяқталды",
  "ru": "Завершенный"
}', '{
  "en": "Completed",
  "kk": "Аяқталды",
  "ru": "Завершенный"
}');

alter table subscription
    add column name varchar(255);


create table order_codes
(
    id           bigserial primary key,
    order_code   numeric                  not null,
    created_date timestamp with time zone not null default now(),
    updated_date timestamp with time zone not null default now()
);

create table bouquet
(
    id           bigserial primary key,
    name         varchar(255),
    description  varchar(255),
    company_id   bigint references companies (id) not null,
    price        numeric,
    addition     varchar(255),
    created_date timestamp with time zone         not null default now(),
    updated_date timestamp with time zone         not null default now()
);

create table order
(
    id              bigserial primary key,
    order_code      numeric                             not null,
    subscription_id bigint references subscription (id) not null,
    address         varchar(255),
    bouquet_id      bigint references bouquet (id)      not null,
    delivery_time   timestamp with time zone,
    order_status_id bigint references order_status (id) not null,
    created_date    timestamp with time zone            not null default now(),
    updated_date    timestamp with time zone            not null default now()
);


create table bouquet_photos
(
    id           bigserial primary key,
    title        varchar(255),
    directory    varchar(255),
    bouquet_id   bigint references bouquet (id) not null,
    uploaded_by  varchar(255),
    created_date timestamp with time zone       not null default now()
);

create table flower
(
    id           bigserial primary key,
    name         varchar(255),
    description  varchar(255),
    photo        varchar(255),
    created_date timestamp with time zone not null default now(),
    updated_date timestamp with time zone not null default now()
);

create table bouquet_flowers
(
    id         bigserial primary key,
    bouquet_id bigint references bouquet (id) not null,
    flower_id  bigint references flower (id)  not null,
    quantity   int
);























