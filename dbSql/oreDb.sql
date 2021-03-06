CREATE TABLE `record` (
	`id` VARCHAR(100) NOT NULL,
	`no` VARCHAR(100) NOT NULL COMMENT '编号',
	`type` VARCHAR(10) NOT NULL COMMENT '类型，1已购2未购',
	`remarks` VARCHAR(5000) NULL DEFAULT NULL COMMENT '备注'
)
COMMENT='导入记录表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;




CREATE TABLE `sub` (
	`id` VARCHAR(100) NOT NULL COMMENT '主键',
	`p_id` VARCHAR(100) NOT NULL COMMENT '父id',
	`no` VARCHAR(100) NOT NULL COMMENT '编号',
	`date` TIMESTAMP NOT NULL DEFAULT '',
	`type` VARCHAR(50) NOT NULL COMMENT '1已购2未购',
	`remarks` VARCHAR(500) NULL DEFAULT NULL COMMENT '备注',
	`unit` VARCHAR(50) NULL DEFAULT NULL COMMENT '单位',
	`price` DECIMAL(10,3) NULL DEFAULT NULL COMMENT '单价',
	`wet_count` DECIMAL(10,3) NULL DEFAULT NULL COMMENT '湿量',
	`water_count` DECIMAL(10,3) NULL DEFAULT NULL COMMENT '水分',
	`fe` DECIMAL(10,3) NULL DEFAULT NULL,
	`s` DECIMAL(10,3) NULL DEFAULT NULL,
	`p` DECIMAL(10,3) NULL DEFAULT NULL,
	`pb` DECIMAL(10,3) NULL DEFAULT NULL,
	`as_` DECIMAL(10,3) NULL DEFAULT NULL,
	`sio2` DECIMAL(10,3) NULL DEFAULT NULL,
	`tio2` DECIMAL(10,3) NULL DEFAULT NULL,
	`zn` DECIMAL(10,3) NULL DEFAULT NULL,
	`cu` DECIMAL(10,3) NULL DEFAULT NULL,
	`sn` DECIMAL(10,3) NULL DEFAULT NULL,
	`k20` DECIMAL(10,3) NULL DEFAULT NULL,
	`na20` DECIMAL(10,3) NULL DEFAULT NULL
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
