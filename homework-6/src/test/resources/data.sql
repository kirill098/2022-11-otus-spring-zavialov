insert into genre(title) values ('genre_title_1');
insert into genre(title) values ('genre_title_2');
insert into genre(title) values ('genre_title_3');
insert into genre(title) values ('genre_title_4');

insert into author(name) values ('author_name_1');
insert into author(name) values ('author_name_2');
insert into author(name) values ('author_name_3');
insert into author(name) values ('author_name_4');

insert into book(title, author_id, genre_id) values ('book_title_1', 1, 1);
insert into book(title, author_id, genre_id) values ('book_title_2', 1, 3);
insert into book(title, author_id, genre_id) values ('book_title_3', 2, 1);
insert into book(title, author_id, genre_id) values ('book_title_4', 2, 3);
insert into book(title, author_id, genre_id) values ('book_title_5', 3, 1);
insert into book(title, author_id, genre_id) values ('book_title_6', 3, 3);
insert into book(title, author_id, genre_id) values ('book_title_7', 4, 4);

insert into comment(description, book_id) values ('comment_1', 1);
insert into comment(description, book_id) values ('comment_2', 2);
insert into comment(description, book_id) values ('comment_3', 3);
insert into comment(description, book_id) values ('comment_4', 4);
insert into comment(description, book_id) values ('comment_5', 5);
insert into comment(description, book_id) values ('comment_6', 6);
insert into comment(description, book_id) values ('comment_7', 7);
insert into comment(description, book_id) values ('comment_8', 5);
insert into comment(description, book_id) values ('comment_9', 5);
insert into comment(description, book_id) values ('comment_10', 7);
insert into comment(description, book_id) values ('comment_11', 2);
insert into comment(description, book_id) values ('comment_12', 2);