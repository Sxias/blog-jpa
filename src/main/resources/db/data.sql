insert into user_tb(username, password, email, created_at)
values ('ssar', '1234', 'ssar@nate.com', now());
insert into user_tb(username, password, email, created_at)
values ('cos', '1234', 'cos@nate.com', now());
insert into user_tb(username, password, email, created_at)
values ('love', '1234', 'love@nate.com', now());

insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목1', '내용1', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목2', '내용2', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목3', '내용3', 2, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목4', '내용4', 3, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목5', '내용5', 1, false, now());

insert into love_tb(board_id, user_id, created_at)
values (5, 1, now());
insert into love_tb(board_id, user_id, created_at)
values (4, 2, now());
insert into love_tb(board_id, user_id, created_at)
values (4, 1, now());