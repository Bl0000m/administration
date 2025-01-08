alter table orders
    add column branch_division_id bigint references branch_division (id);
