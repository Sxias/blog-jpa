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
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목6', '내용6', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목7', '내용7', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목8', '내용8', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목9', '내용9', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목10', '내용10', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목11', '내용11', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목12', '내용12', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목13', '내용13', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목14', '내용14', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목15', '내용15', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목16', '내용16', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목17', '내용17', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목18', '내용18', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목19', '내용19', 1, true, now());
insert into board_tb(title, content, user_id, is_public, created_at)
values ('제목20', '내용20', 1, true, now());

insert into love_tb(board_id, user_id, created_at)
values (5, 1, now());
insert into love_tb(board_id, user_id, created_at)
values (4, 2, now());
insert into love_tb(board_id, user_id, created_at)
values (4, 1, now());

insert into reply_tb(board_id, user_id, content, created_at)
values (4, 1, '댓글1', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (4, 2, '댓글2', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (4, 1, '댓글3', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (3, 1, '댓글4', now());
insert into reply_tb(board_id, user_id, content, created_at)
values (2, 1, '댓글5', now());