-- 初始化数据库脚本
-- 系统表结构和数据begin
CREATE TABLE sys_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE sys_role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL -- 如 ROLE_ADMIN, ROLE_USER
);

CREATE TABLE sys_user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES sys_user(id),
    FOREIGN KEY (role_id) REFERENCES sys_role(id)
);


CREATE TABLE sys_menu (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    url VARCHAR(255), -- 对应接口路径，如 /api/user/*
    permission VARCHAR(100) -- 可选，用于方法级权限注解使用
);

CREATE TABLE sys_role_menu (
    role_id BIGINT NOT NULL,
    menu_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, menu_id),
    FOREIGN KEY (role_id) REFERENCES sys_role(id),
    FOREIGN KEY (menu_id) REFERENCES sys_menu(id)
);


--1.添加用户
INSERT INTO sys_user (id, username, password, enabled) VALUES(1, 'admin', '$2a$10$GZ7Upy8vRwWzYh45K3UjCeTmDf9kFtHcJiBnXqOeLQgVx5A0P5N6Z', true);
-- 2.添加角色
INSERT INTO sys_role (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');
-- 3.添加菜单
INSERT INTO sys_menu (id, name, url, permission) VALUES
(1, '用户列表', '/api/user/list', 'user:list'),
(2, '角色管理', '/api/role/manage', 'role:manage');
-- 4.添加角色与菜单的关联
INSERT INTO sys_role_menu (role_id, menu_id) VALUES
(1, 1), (1, 2),
(2, 1);
-- 5.添加用户与角色的关联
INSERT INTO sys_user_role (user_id, role_id) VALUES (1, 1);
-- 系统表结构和数据end

-- 业务表
CREATE TABLE `tb_words` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `user_id` BIGINT NOT NULL,
  `english` VARCHAR(255) NOT NULL,
  `chinese` TEXT NOT NULL,
  `example` TEXT,
  `added_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `next_review` DATE,
  `known` BOOLEAN DEFAULT FALSE,
  FOREIGN KEY (`user_id`) REFERENCES `sys_user`(`id`)
);