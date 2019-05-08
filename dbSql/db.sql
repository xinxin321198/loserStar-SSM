CREATE TABLE `sys_users` (
	`id` VARCHAR(50) NOT NULL COMMENT '主键id' COLLATE 'utf8_bin',
	`user_name` VARCHAR(64) NULL DEFAULT NULL COMMENT '用户名' COLLATE 'utf8_bin',
	`password` VARCHAR(64) NULL DEFAULT NULL COMMENT '密码' COLLATE 'utf8_bin',
	PRIMARY KEY (`id`)
)
COMMENT='用户表'
COLLATE='utf8_bin'
ENGINE=InnoDB
;
