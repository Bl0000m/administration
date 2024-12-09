alter table orders
drop column delivery_time;

alter table orders
    add column delivery_date DATE;

alter table orders
    add column delivery_start_time TIME;

alter table orders
    add column delivery_end_time TIME;