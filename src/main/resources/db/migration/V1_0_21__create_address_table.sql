create table address
(
    id          bigserial primary key,
    street      varchar(200),
    building    varchar(200),
    apartment   varchar(200),
    entrance    varchar(200),
    intercom    varchar(200),
    floor       numeric,
    city        varchar(200),
    postal_code varchar(200),
    latitude    numeric,
    longitude   numeric
);

create table user_address
(
    id         bigserial primary key,
    address_id bigint references address (id),
    users_id   bigint references users (id),
    is_default boolean default false
)

create table order_address
(
    id              bigserial primary key,
    address_id      bigint references address (id),
    orders_id       bigint references orders (id),
    recipient_phone varchar(200),
    comment         varchar(200),
    is_default      boolean default false
)

alter table orders
drop
column address;