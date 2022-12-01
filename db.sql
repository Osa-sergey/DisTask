create schema company;

set schema 'company';

    create table company.products
    (
        id             serial primary key,
        name           varchar(200) not null unique,
        description    text         not null,
        implement_cost money        not null
    );

create table company.articles
(
    id          serial
        primary key,
    productid   integer            not null unique
        references company.products,
    name        varchar(200)       not null,
    content     text               not null,
    create_date date default now() not null
);
