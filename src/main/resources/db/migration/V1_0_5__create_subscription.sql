create table subscription_type
(
    id          bigserial primary key,
    code        varchar(255) not null unique,
    name        jsonb,
    description jsonb
);

INSERT INTO subscription_type (id, code, name, description)
VALUES (default, 'SELECTED_FLOWERS_PERIODICALLY', '{
  "en": "The selected composition during the specified period",
  "kk": "Көрсетілген кезеңде таңдалған композиция",
  "ru": "Выбранная композиция в указанный период"
}', '{
  "en": "The selected composition during the specified period",
  "kk": "Көрсетілген кезеңде таңдалған композиция",
  "ru": "Выбранная композиция в указанный период"
}');

INSERT INTO subscription_type (id, code, name, description)
VALUES (default, 'ANY_FLOWERS_EVERY_TWO_DAYS', '{
  "en": "Any flowers every two days",
  "kk": "Екі күн сайын кез келген гүлдер",
  "ru": "Любые цветы каждые два дня"
}', '{
  "en": "Any flowers every two days",
  "kk": "Екі күн сайын кез келген гүлдер",
  "ru": "Любые цветы каждые два дня"
}');

create table subscription_status
(
    id          bigserial primary key,
    code        varchar(255) not null unique,
    name        jsonb,
    description jsonb
);

INSERT INTO subscription_status (id, code, name, description)
VALUES (default, 'ACTIVE', '{
  "en": "Active",
  "kk": "Белсенді",
  "ru": "Активный"
}', '{
  "en": "Active",
  "kk": "Белсенді",
  "ru": "Активный"
}');

INSERT INTO subscription_status (id, code, name, description)
VALUES (default, 'NOT_ACTIVE', '{
  "en": "Not active",
  "kk": "Белсенді емес",
  "ru": "Неактивный"
}', '{
  "en": "Not active",
  "kk": "Белсенді емес",
  "ru": "Неактивный"
}');

create table subscription
(
    id                     bigserial primary key,
    user_id                bigint references users (id)               not null,
    start_time             timestamp with time zone,
    end_time               timestamp with time zone,
    subscription_type_id   bigint references subscription_type (id)   not null,
    subscription_status_id bigint references subscription_status (id) not null,
    created_date       timestamp with time zone            not null default now(),
    updated_date       timestamp with time zone            not null default now()
);
