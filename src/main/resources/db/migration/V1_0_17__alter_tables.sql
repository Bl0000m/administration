alter table branch_division
drop
column employee_id;

alter table bouquet
    add column employee_id bigint references employee (id);
