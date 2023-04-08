drop table if exists book;
drop table genre if exists;
drop table author if exists;


create table genre if not exists (
    id numeric(18) not null,
    title varchar(100) not null
)

create table author if not exists (
    id bigint primary key,
    name varchar(100) not null
)

create table book if not exists (
    id bigint primary key,
    title varchar(100) not null,
    genre_id bigint not null,
    author_id bigint not null
)

# TODO : связи с другими таблицами