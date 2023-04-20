create table if not exists genre(
    id bigint primary key,
    title varchar(255) not null
);

create table if not exists author(
    id bigint primary key,
    name varchar(255) not null
);

create table if not exists book(
    id bigint primary key,
    title varchar(255) not null,
    genre_id bigint not null,
    author_id bigint not null,
    foreign key (genre_id) references genre(id),
    foreign key (author_id) references author(id)
);

