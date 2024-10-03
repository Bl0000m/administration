create table role
(
    id          bigserial primary key,
    code        varchar(255) not null unique,
    name        jsonb,
    description jsonb
);


create table users
(
    id           bigserial primary key,
    keycloak_id  varchar(255) unique      not null,
    name         varchar(255)             not null,
    email        varchar(255)             not null unique,
    phone_number varchar(15)              not null unique,
    created_date timestamp with time zone not null default now(),
    updated_date timestamp with time zone not null default now(),
    is_deleted   boolean                  not null default false
);

create table users_roles
(
    id           bigserial primary key,
    user_id bigint references users (id) not null,
    role_id bigint references role (id)  not null,
    constraint user_id_role_id_unique unique (user_id, role_id)
);


INSERT INTO role (id, code, name, description)
VALUES (default, 'CLIENT', '{
  "en": "Subscriber",
  "kk": "Жазылушы",
  "ru": "Подписчик"
}', '{
  "en": "A customer who creates flower delivery subscriptions",
  "kk": "Гүл жеткізу жазылымдарын жасайтын Клиент",
  "ru": "Клиент, который создает подписки на доставку цветов"
}');

INSERT INTO role (id, code, name, description)
VALUES (default, 'FLORIST', '{
  "en": "Florist",
  "kk": "Гүл өсіруші",
  "ru": "Флорист"
}', '{
  "en": "The contractor who is responsible for the assembly of flower arrangements and can order flowers from wholesale suppliers",
  "kk": "Гүл композицияларын жинауға жауапты және көтерме жеткізушілерден гүлдерге тапсырыс бере алатын Орындаушы",
  "ru": "Исполнитель, который отвечает за сборку цветочных композиций и может заказывать цветы у оптовых поставщиков"
}');

INSERT INTO role (id, code, name, description)
VALUES (default, 'COURIER', '{
  "en": "Courier",
  "kk": "Курьер",
  "ru": "Курьер"
}', '{
  "en": "The performer who is responsible for delivering compositions and flowers as resources",
  "kk": "Композициялар мен түстерді ресурстар ретінде жеткізуге жауапты орындаушы",
  "ru": "Исполнитель, который отвечает за доставку композиций и цветов, как ресурсов"
}');

INSERT INTO role (id, code, name, description)
VALUES (default, 'WHOLESALER', '{
  "en": "Wholesale Supplier",
  "kk": "Көтерме жеткізуші",
  "ru": "Оптовый поставщик"
}', '{
  "en": "Provides flowers for florists as resources for their compositions",
  "kk": "Флористерге олардың композициялары үшін ресурстар ретінде гүлдер ұсынады",
  "ru": "Предоставляет цветы для флористов в качестве ресурсов для их композиций"
}');