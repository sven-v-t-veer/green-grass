CREATE SCHEMA IF NOT EXISTS GREENGRASS;
create table customer (
    customer_id uuid,
    name varchar(40) not null,
    surname varchar(40) not null,
    primary key (customer_id)
);


create table account (
    account_id uuid,
    customer_id uuid,
    balance numeric (6,2) not null,
    primary key (account_id)
);

create sequence transaction_id_seq start with 1 increment by 1;

create table transaction (
    transaction_id bigint,
    account_id uuid,
    type varchar(15),
    description varchar(40),
    amount numeric(6.2),
    ts timestamp,
    primary key (transaction_id)
)
