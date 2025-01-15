alter table employee
    add column branch_division_id bigint references branch_division (id);

alter table employee
drop
column company_id;
