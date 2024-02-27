CREATE DATABASE socialnetwork;
CREATE SCHEMA sn;
-- Пользователи
CREATE TABLE sn.author (
	id serial4 NOT NULL,
	rating int8 NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	CONSTRAINT author_pkey PRIMARY KEY (id)
);
-- Подписки
CREATE TABLE sn."subscription" (
	id serial4 NOT NULL,
    author int4 NULL,	
	"subscription" int4 NULL,
	CONSTRAINT subscription_pkey PRIMARY KEY (id)
);
ALTER TABLE sn."subscription" ADD CONSTRAINT subscription_fk1 FOREIGN KEY ("subscription") REFERENCES sn.author(id);
ALTER TABLE sn."subscription" ADD CONSTRAINT subscription_fk2 FOREIGN KEY (author) REFERENCES sn.author(id);
-- Посты
CREATE TABLE sn.post (
	id serial4 NOT NULL,
    author int4 NULL,	
	caption varchar(255) NULL,
	"text" varchar(255) NULL,
	CONSTRAINT post_pkey PRIMARY KEY (id)
);
ALTER TABLE sn.post ADD CONSTRAINT post_fk1 FOREIGN KEY (author) REFERENCES sn.author(id);
-- Комментарии
CREATE TABLE sn."comment" (
	id serial4 NOT NULL,
    author int4 NULL,	
	creation_time timestamp(6) NULL,
	"text" varchar(255) NULL,
	CONSTRAINT comment_pkey PRIMARY KEY (id)
);
ALTER TABLE sn."comment" ADD CONSTRAINT comment_fk1 FOREIGN KEY (author) REFERENCES sn.author(id);
-- Связь комментариев и постов
CREATE TABLE sn.comments_to_post (
	"comment" int4 NULL,
	id serial4 NOT NULL,
	post int4 NULL,
	CONSTRAINT comments_to_post_pkey PRIMARY KEY (id)
);
ALTER TABLE sn.comments_to_post ADD CONSTRAINT comments_to_post_k1 FOREIGN KEY (post) REFERENCES sn.post(id);
ALTER TABLE sn.comments_to_post ADD CONSTRAINT comments_to_post_fk2 FOREIGN KEY ("comment") REFERENCES sn."comment"(id);
-- Лайки на постах
CREATE TABLE sn.likes_to_post (
	author int4 NULL,
	id serial4 NOT NULL,
	post int4 NULL,
	CONSTRAINT likes_to_post_pkey PRIMARY KEY (id)
);
ALTER TABLE sn.likes_to_post ADD CONSTRAINT likes_to_post_pkey_fk1 FOREIGN KEY (post) REFERENCES sn.post(id);
ALTER TABLE sn.likes_to_post ADD CONSTRAINT likes_to_post_pkey_fk2 FOREIGN KEY (author) REFERENCES sn.author(id);
-- Лайки на комментариях
CREATE TABLE sn.likes_to_comment (
	author int4 NULL,
	"comment" int4 NULL,
	id serial4 NOT NULL,
	CONSTRAINT likes_to_comment_pkey PRIMARY KEY (id)
);
ALTER TABLE sn.likes_to_comment ADD CONSTRAINT likes_to_post_pkey_fk1 FOREIGN KEY (author) REFERENCES sn.author(id);
ALTER TABLE sn.likes_to_comment ADD CONSTRAINT likes_to_post_pkey_fk2 FOREIGN KEY ("comment") REFERENCES sn."comment"(id);