alter table orders
    alter column order_code DROP NOT NULL;

alter table orders
    alter column bouquet_id DROP NOT NULL;

alter table orders
    alter column order_status_id DROP NOT NULL;
