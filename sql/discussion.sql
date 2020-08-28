
DROP DATABASE IF EXISTS discussion;

CREATE DATABASE discussion;

USE discussion;

CREATE TABLE `user`(
	user_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户Id',
	username VARCHAR(42) NOT NULL UNIQUE COMMENT '用户名',
	PASSWORD VARCHAR(42) NOT NULL COMMENT '密码',
	email VARCHAR(255) NOT NULL UNIQUE COMMENT '邮箱',
	authority TINYINT NOT NULL DEFAULT 1 COMMENT '权限：1为普通用户0为管理员'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户基础信息表';

CREATE TABLE posts(
	posts_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '贴吧id',
	posts_title VARCHAR(255) NOT NULL COMMENT '贴吧名称',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建吧时间',
	posts_man INT NOT NULL COMMENT '外键，建吧人id',
	is_live TINYINT DEFAULT 1 COMMENT '0删，1在',
	FOREIGN KEY(posts_man) REFERENCES `user`(user_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='贴吧表';

CREATE TABLE post(
	post_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '帖子id',
	post_context BLOB(25500) NOT NULL COMMENT '帖子内容',
	post_man INT NOT NULL COMMENT '外键，发帖人id',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发帖时间',
	post_title VARCHAR(255) NOT NULL COMMENT '帖子标题',
	posts_id INT NOT NULL COMMENT '外键，贴吧id',
	is_live TINYINT DEFAULT 1 COMMENT '0删，1在',
	FOREIGN KEY(post_man) REFERENCES `user`(user_id),
	FOREIGN KEY(posts_id) REFERENCES posts(posts_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='帖子表';

CREATE TABLE `comment`(
	comment_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '评论id',
	comment_context BLOB(25500) NOT NULL COMMENT '评论内容',
	comment_man INT NOT NULL COMMENT '外键，评论人id',
	target_post INT NOT NULL COMMENT '外键，被评论的帖子id',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
	is_live TINYINT DEFAULT 1 COMMENT '0删，1在',
	FOREIGN KEY(comment_man) REFERENCES `user`(user_id),
	FOREIGN KEY(target_post) REFERENCES `post`(post_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='评论表';

CREATE TABLE reply(
	reply_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '回复id',
	reply_context BLOB(25500) NOT NULL COMMENT '回复内容',
	reply_man INT NOT NULL COMMENT '回复人id',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
	target_comment INT NOT NULL COMMENT '外键，被回复的评论id',
	target_reply INT COMMENT '回复的回复的id，在reply只针对comment时为空',
	is_live TINYINT DEFAULT 1 COMMENT '0删，1在',
	FOREIGN KEY(reply_man) REFERENCES `user`(user_id),
	FOREIGN KEY(target_comment) REFERENCES `comment`(comment_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='回复表';

CREATE TABLE user_to_posts(
	user_id INT NOT NULL,
	posts_id INT NOT NULL,
	star_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏时间',
	is_star TINYINT DEFAULT 1 COMMENT '是否收藏',
	PRIMARY KEY(user_id,posts_id),
	FOREIGN KEY(user_id) REFERENCES `user`(user_id),
	FOREIGN KEY(posts_id) REFERENCES posts(posts_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户收藏贴吧表';

CREATE TABLE user_to_post(
	user_id INT NOT NULL,
	post_id INT NOT NULL,
	star_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏时间',
	is_star TINYINT DEFAULT 1 COMMENT '是否收藏',
	PRIMARY KEY(user_id,post_id),
	FOREIGN KEY(user_id) REFERENCES `user`(user_id),
	FOREIGN KEY(post_id) REFERENCES post(post_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户收藏帖子表';

CREATE TABLE user_to_role(
	user_id INT NOT NULL,
	posts_id INT NOT NULL,
	`status` INT NOT NULL COMMENT '1管理员用户,2创建者用户',
	PRIMARY KEY(user_id,posts_id),
	FOREIGN KEY(user_id) REFERENCES `user`(user_id),
	FOREIGN KEY(posts_id) REFERENCES posts(posts_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户角色表';
