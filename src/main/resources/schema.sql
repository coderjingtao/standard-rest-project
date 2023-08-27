drop table course;
create table course(
    id bigint not null,
    name varchar(255) not null,
    author varchar(255) not null,
    primary key (id)
);

drop table item;
create table item
(
    id     bigint       not null,
    name   varchar(255) not null,
    url varchar(255) not null,
    primary key (id)
);

drop table item_detail;
create table item_detail
(
    id     bigint       not null,
    name   varchar(255) not null,
    detail varchar(255) not null,
    primary key (id)
);