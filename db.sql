create schema if not exists company;

create table if not exists company.products
(
    id             bigserial primary key,
    name           varchar(200) not null unique,
    description    text         not null,
    implement_cost numeric not null
);

create table if not exists company.articles
(
    id          bigserial primary key,
    product_id  bigint             not null
        references company.products,
    name        varchar(200)       not null unique,
    content     text               not null,
    create_date date default now() not null
);

create index if not exists product_id_index ON company.articles (product_id);