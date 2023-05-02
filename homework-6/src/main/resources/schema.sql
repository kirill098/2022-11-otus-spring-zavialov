create table if not exists genre(
    id bigserial primary key,
    title varchar(255) not null
);

create table if not exists author(
    id bigserial primary key,
    name varchar(255) not null
);

create table if not exists book(
    id bigserial primary key,
    title varchar(255) not null,
    genre_id bigint not null,
    author_id bigint not null,
    foreign key(genre_id) references genre(id),
    foreign key(author_id) references author(id)
);

create table if not exists comment(
    id bigserial primary key,
    description varchar(255) not null,
    book_id bigint references book(id) on delete cascade
);

