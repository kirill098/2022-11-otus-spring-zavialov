insert into genre(id, title) values (1, 'genre_title_1');
insert into genre(id, title) values (2, 'genre_title_2');
insert into genre(id, title) values (3, 'genre_title_3');

insert into author(id, name) values (1, 'author_name_1');
insert into author(id, name) values (2, 'author_name_2');
insert into author(id, name) values (3, 'author_name_3');

insert into book(id, title, author_id, genre_id) values (1, 'book_title_1', 1, 1);
insert into book(id, title, author_id, genre_id) values (2, 'book_title_2', 1, 3);
insert into book(id, title, author_id, genre_id) values (3, 'book_title_3', 2, 1);
insert into book(id, title, author_id, genre_id) values (4, 'book_title_4', 2, 3);
insert into book(id, title, author_id, genre_id) values (5, 'book_title_5', 3, 1);
insert into book(id, title, author_id, genre_id) values (6, 'book_title_6', 3, 3);