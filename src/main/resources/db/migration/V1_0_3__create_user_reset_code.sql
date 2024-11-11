create table user_reset_code
(
    id           bigserial primary key,
    email        varchar(255)             not null unique,
    reset_code   varchar(255),
    created_date timestamp with time zone not null default now(),
    updated_date timestamp with time zone not null default now()
);
