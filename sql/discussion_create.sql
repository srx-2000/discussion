/*创建数据库以及表*/
DROP DATABASE IF EXISTS discussion;

CREATE DATABASE discussion;

USE discussion;

CREATE TABLE `user`
(
    user_id   INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户Id',
    username  VARCHAR(42)  NOT NULL UNIQUE COMMENT '用户名',
    PASSWORD  VARCHAR(42)  NOT NULL COMMENT '密码',
    email     VARCHAR(255) NOT NULL UNIQUE COMMENT '邮箱',
    authority TINYINT      NOT NULL DEFAULT 1 COMMENT '权限：1为普通用户0为管理员'
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='用户基础信息表';

CREATE TABLE posts
(
    posts_id    INT PRIMARY KEY AUTO_INCREMENT COMMENT '贴吧id',
    posts_title VARCHAR(255) NOT NULL COMMENT '贴吧名称',
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建吧时间',
    posts_man   INT          NOT NULL COMMENT '外键，建吧人id',
    is_live     TINYINT               DEFAULT 1 COMMENT '0删，1在',
    FOREIGN KEY (posts_man) REFERENCES `user` (user_id)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='贴吧表';

CREATE TABLE post
(
    post_id      INT PRIMARY KEY AUTO_INCREMENT COMMENT '帖子id',
    post_context BLOB(25500)  NOT NULL COMMENT '帖子内容',
    post_man     INT          NOT NULL COMMENT '外键，发帖人id',
    create_time  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发帖时间',
    post_title   VARCHAR(255) NOT NULL COMMENT '帖子标题',
    posts_id     INT          NOT NULL COMMENT '外键，贴吧id',
    is_live      TINYINT               DEFAULT 1 COMMENT '0删，1在',
    FOREIGN KEY (post_man) REFERENCES `user` (user_id),
    FOREIGN KEY (posts_id) REFERENCES posts (posts_id)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='帖子表';

CREATE TABLE `comment`
(
    comment_id      INT PRIMARY KEY AUTO_INCREMENT COMMENT '评论id',
    comment_context BLOB(25500) NOT NULL COMMENT '评论内容',
    comment_man     INT         NOT NULL COMMENT '外键，评论人id',
    target_post     INT         NOT NULL COMMENT '外键，被评论的帖子id',
    create_time     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
    is_live         TINYINT              DEFAULT 1 COMMENT '0删，1在',
    FOREIGN KEY (comment_man) REFERENCES `user` (user_id),
    FOREIGN KEY (target_post) REFERENCES `post` (post_id)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='评论表';

CREATE TABLE reply
(
    reply_id       INT PRIMARY KEY AUTO_INCREMENT COMMENT '回复id',
    reply_context  BLOB(25500) NOT NULL COMMENT '回复内容',
    reply_man      INT         NOT NULL COMMENT '回复人id',
    create_time    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
    target_comment INT         NOT NULL COMMENT '外键，被回复的评论id',
    target_reply   INT COMMENT '回复的回复的id，在reply只针对comment时为空',
    is_live        TINYINT              DEFAULT 1 COMMENT '0删，1在',
    FOREIGN KEY (reply_man) REFERENCES `user` (user_id),
    FOREIGN KEY (target_comment) REFERENCES `comment` (comment_id)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='回复表';

CREATE TABLE user_to_posts
(
    user_id   INT       NOT NULL,
    posts_id  INT       NOT NULL,
    star_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏时间',
    is_star   TINYINT            DEFAULT 1 COMMENT '是否收藏',
    PRIMARY KEY (user_id, posts_id),
    FOREIGN KEY (user_id) REFERENCES `user` (user_id),
    FOREIGN KEY (posts_id) REFERENCES posts (posts_id)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='用户收藏贴吧表';

CREATE TABLE user_to_post
(
    user_id   INT       NOT NULL,
    post_id   INT       NOT NULL,
    star_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏时间',
    is_star   TINYINT            DEFAULT 1 COMMENT '是否收藏',
    PRIMARY KEY (user_id, post_id),
    FOREIGN KEY (user_id) REFERENCES `user` (user_id),
    FOREIGN KEY (post_id) REFERENCES post (post_id)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='用户收藏帖子表';

CREATE TABLE user_to_role
(
    user_id  INT NOT NULL,
    posts_id INT NOT NULL,
    `status` INT NOT NULL COMMENT '1管理员用户,2创建者用户',
    PRIMARY KEY (user_id, posts_id),
    FOREIGN KEY (user_id) REFERENCES `user` (user_id),
    FOREIGN KEY (posts_id) REFERENCES posts (posts_id)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='用户角色表';

create table user_to_info
(
    user_id        int          not null,
    nickname       VARCHAR(255) NOT NULL COMMENT '用户昵称',
    address        varchar(255) default '无' comment '地址',
    self_signature varchar(255) default '这个人很懒还没有个性签名.....' comment '个性签名',
    age            int          default 0 comment '年龄',
    sex            varchar(9)   default '秘密' comment '性别',
    avatar         Blob(8192) comment '头像',
    foreign key (user_id) references `user` (user_id)
) engine = innodb
  default charset = utf8 comment ='用户信息表';

CREATE TABLE user_to_pyq
(
    user_id     INT           NOT NULL,
    pyq_context VARCHAR(2550) NOT NULL COMMENT '动态内容',
    create_time TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '动态发布时间',
    nickname    VARCHAR(255)  NOT NULL COMMENT '发布用户昵称',
    is_live     TINYINT                DEFAULT 1 COMMENT '动态是否存在',
    FOREIGN KEY (user_id) REFERENCES `user` (user_id)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8 COMMENT ='动态表';

/*插入用户数据*/

INSERT INTO USER(username, PASSWORD, email)
VALUES ('user1', 'user1', 'user1@qq.com');

INSERT INTO USER(username, PASSWORD, email)
VALUES ('user2', 'user2', 'user2@qq.com');

INSERT INTO USER(username, PASSWORD, email)
VALUES ('user3', 'user3', 'user13@qq.com');

INSERT INTO USER(username, PASSWORD, email)
VALUES ('user4', 'user4', 'user4@qq.com');

INSERT INTO USER(username, PASSWORD, email, authority)
VALUES ('admin1', 'admin1', 'admin1@qq.com', 0);

INSERT INTO USER(username, PASSWORD, email, authority)
VALUES ('admin2', 'admin2', 'admin2@qq.com', 0);

INSERT INTO USER(username, PASSWORD, email, authority)
VALUES ('admin3', 'admin3', 'admin3@qq.com', 0);

INSERT INTO USER(username, PASSWORD, email, authority)
VALUES ('admin4', 'admin4', 'admin4@qq.com', 0);

/*插入贴吧数据*/

INSERT INTO posts(posts_title, posts_man)
VALUES ('测试吧1', 1);

INSERT INTO posts(posts_title, posts_man)
VALUES ('测试吧2', 1);

INSERT INTO posts(posts_title, posts_man)
VALUES ('测试吧3', 5);

INSERT INTO posts(posts_title, posts_man)
VALUES ('测试吧4', 3);

INSERT INTO posts(posts_title, posts_man)
VALUES ('测试吧5', 5);


/*插入帖子数据*/

INSERT INTO post(post_context, post_man, post_title, posts_id)
VALUES ('测试帖子内容1', 2, '测试帖子1', 1);

INSERT INTO post(post_context, post_man, post_title, posts_id)
VALUES ('测试帖子内容2', 5, '测试帖子2', 2);

INSERT INTO post(post_context, post_man, post_title, posts_id)
VALUES ('测试帖子内容3', 3, '测试帖子3', 1);

INSERT INTO post(post_context, post_man, post_title, posts_id)
VALUES ('测试帖子内容4', 5, '测试帖子4', 2);

INSERT INTO post(post_context, post_man, post_title, posts_id)
VALUES ('测试帖子内容5', 1, '测试帖子5', 3);

/*插入评论数据*/

INSERT INTO COMMENT(comment_context, comment_man, target_post)
VALUES ('测试评论1', 1, 1);

INSERT INTO COMMENT(comment_context, comment_man, target_post)
VALUES ('测试评论2', 2, 1);

INSERT INTO COMMENT(comment_context, comment_man, target_post)
VALUES ('测试评论3', 3, 1);

INSERT INTO COMMENT(comment_context, comment_man, target_post)
VALUES ('测试评论4', 1, 5);

INSERT INTO COMMENT(comment_context, comment_man, target_post)
VALUES ('测试评论5', 2, 5);

INSERT INTO COMMENT(comment_context, comment_man, target_post)
VALUES ('测试评论6', 8, 3);

INSERT INTO COMMENT(comment_context, comment_man, target_post)
VALUES ('测试评论7', 1, 1);

INSERT INTO COMMENT(comment_context, comment_man, target_post)
VALUES ('测试评论8', 1, 1);

/*插入回复信息,这里有个bug：当用户回复一个评论或回复时可能将目标回复填成另一个评论下的回复
应该在插入时加上comment_id进一步约束target_reply的指向*/

INSERT INTO reply(reply_context, reply_man, target_comment, target_reply)
VALUES ('测试回复1', 1, 1, NULL);

INSERT INTO reply(reply_context, reply_man, target_comment, target_reply)
VALUES ('测试回复2', 1, 1, 1);

INSERT INTO reply(reply_context, reply_man, target_comment, target_reply)
VALUES ('测试回复3', 2, 1, 2);

INSERT INTO reply(reply_context, reply_man, target_comment, target_reply)
VALUES ('测试回复4', 3, 2, NULL);

INSERT INTO reply(reply_context, reply_man, target_comment, target_reply)
VALUES ('测试回复5', 3, 1, NULL);

INSERT INTO reply(reply_context, reply_man, target_comment, target_reply)
VALUES ('测试回复6', 8, 1, NULL);

INSERT INTO reply(reply_context, reply_man, target_comment, target_reply)
VALUES ('测试回复7', 8, 1, 2);

/*插入用户收藏贴子数据*/

INSERT INTO user_to_post(user_id, post_id)
VALUES (1, 1);

INSERT INTO user_to_post(user_id, post_id)
VALUES (2, 1);

INSERT INTO user_to_post(user_id, post_id)
VALUES (3, 2);

INSERT INTO user_to_post(user_id, post_id)
VALUES (8, 1);

INSERT INTO user_to_post(user_id, post_id)
VALUES (5, 5);

INSERT INTO user_to_post(user_id, post_id)
VALUES (5, 1);

/*插入用户收藏贴吧数据*/

INSERT INTO user_to_posts(user_id, posts_id)
VALUES (5, 5);

INSERT INTO user_to_posts(user_id, posts_id)
VALUES (1, 5);

INSERT INTO user_to_posts(user_id, posts_id)
VALUES (2, 5);

INSERT INTO user_to_posts(user_id, posts_id)
VALUES (1, 1);

INSERT INTO user_to_posts(user_id, posts_id)
VALUES (8, 4);

INSERT INTO user_to_posts(user_id, posts_id)
VALUES (2, 3);

INSERT INTO user_to_posts(user_id, posts_id)
VALUES (4, 2);

/*插入用户角色数据*/

INSERT INTO user_to_role
VALUES (1, 1, 2);

INSERT INTO user_to_role
VALUES (1, 2, 2);

INSERT INTO user_to_role
VALUES (5, 3, 2);

INSERT INTO user_to_role
VALUES (3, 4, 2);

INSERT INTO user_to_role
VALUES (5, 5, 2);

INSERT INTO user_to_role
VALUES (1, 5, 1);

INSERT INTO user_to_role
VALUES (2, 1, 1);

INSERT INTO user_to_role
VALUES (8, 1, 1);

/*插入用户信息数据*/
INSERT INTO user_to_info(user_id, nickname, address, age)
VALUES (1, '帅逼1号', '北京', 20);

INSERT INTO user_to_info(user_id, nickname, address, age, self_signature)
VALUES (2, '帅逼2号', '东北', 20, '我不做人啦，jojo');

INSERT INTO user_to_info(user_id, nickname)
VALUES (3, '帅逼3号');

INSERT INTO user_to_info(user_id, nickname, age, sex)
VALUES (4, '帅逼4号', 20, '男');

/*插入动态数据*/
insert into user_to_pyq(user_id, nickname, pyq_context)
values (1, '帅逼1号', '我承认我是一个大帅比了~');

INSERT INTO user_to_pyq(user_id, nickname, pyq_context)
VALUES (2, '帅逼2号', '我也承认我是一个大帅比了~');

INSERT INTO user_to_pyq(user_id, nickname, pyq_context)
VALUES (3, '帅逼3号', '我就不承认我是一个大帅比~');

INSERT INTO user_to_pyq(user_id, nickname, pyq_context)
VALUES (4, '帅逼4号', '得了吧上面三个丑逼');

