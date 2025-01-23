create table transactions_status
(
    id          bigserial primary key,
    code        varchar(255) not null unique,
    name        jsonb,
    description jsonb
);

INSERT INTO transactions_status (id, code, name, description)
VALUES (default, 'ACCEPTED', '{
  "en": "Accepted",
  "kk": "Қабылданды",
  "ru": "Успешно"
}', '{
  "en": "Accepted",
  "kk": "Қабылданды",
  "ru": "Успешно"
}');

INSERT INTO transactions_status (id, code, name, description)
VALUES (default, 'REJECTED', '{
  "en": "Rejected",
  "kk": "Қабылданған жоқ",
  "ru": "Ошибка"
}', '{
  "en": "Rejected",
  "kk": "Қабылданған жоқ",
  "ru": "Ошибка"
}');

create table transactions_type
(
    id          bigserial primary key,
    code        varchar(255) not null unique,
    name        jsonb,
    description jsonb
);

INSERT INTO transactions_type (id, code, name, description)
VALUES (default, 'REPLENISHMENT', '{
  "en": "Replenishment",
  "kk": "Пополнение",
  "ru": "Пополнение"
}', '{
  "en": "Replenishment",
  "kk": "Пополнение",
  "ru": "Пополнение"
}');

INSERT INTO transactions_type (id, code, name, description)
VALUES (default, 'DEBIT', '{
  "en": "Debit",
  "kk": "Списание",
  "ru": "Списание"
}', '{
  "en": "Debit",
  "kk": "Списание",
  "ru": "Списание"
}');

INSERT INTO transactions_type (id, code, name, description)
VALUES (default, 'TRANSFER', '{
  "en": "Transfer",
  "kk": "Перевод",
  "ru": "Перевод"
}', '{
  "en": "Transfer",
  "kk": "Перевод",
  "ru": "Перевод"
}');

INSERT INTO transactions_type (id, code, name, description)
VALUES (default, 'IN_THE_BLOCK', '{
  "en": "In the block",
  "kk": "В блоке",
  "ru": "В блоке"
}', '{
  "en": "In the block",
  "kk": "В блоке",
  "ru": "В блоке"
}');

create table transactions
(
    id                     bigserial primary key,
    users_id               bigint references users (id)               not null,
    amount                 numeric,
    currency               varchar(255),
    transactions_type_id   bigint references transactions_type (id)   not null,
    transactions_status_id bigint references transactions_status (id) not null,
    description            varchar(255),
    created_date           timestamp with time zone                   not null default now(),
    updated_date           timestamp with time zone                   not null default now()
);

create table balances
(
    id              bigserial primary key,
    users_id        bigint references users (id) not null,
    current_balance numeric,
    currency        varchar(255),
    updated_date    timestamp with time zone     not null default now()
);
