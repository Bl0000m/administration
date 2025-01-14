alter table additional_element_price
    add column employee_id bigint references employee (id);

alter table bouquet_branch_price
    add column employee_id bigint references employee (id);

alter table flower_variety_price
    add column employee_id bigint references employee (id);

alter table additional_element_price
    alter column branch_division_id drop not null;

alter table flower_variety_price
    alter column branch_division_id drop not null;

alter table bouquet_branch_price
    alter column branch_division_id drop not null;