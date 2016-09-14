#创建数据库blog默认编码utf8
CREATE DATABASE blog DEFAULT CHARACTER SET utf8;
###################################################################
#创建user表,COLLATE utf8_bin可以使表中列的值区分大小写。
CREATE TABLE `user` (
   `id` int(16) NOT NULL AUTO_INCREMENT,
   `nickname` varchar(24) DEFAULT NULL COMMENT '昵称',
   `sex` char(1) DEFAULT NULL COMMENT '性别:0-女;1-男',
   `birthday` date DEFAULT NULL COMMENT '生日',
   `address` varchar(256) DEFAULT NULL COMMENT '地址',
   `email` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
   `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;
 #用户表初始化数据
 insert into user values(1,'管理员','1','1985-01-05','北京市海淀区马甸冠城园8号冠海大厦14层视信世纪科技','zhuani21@163.com','15810135202');
 ###################################################################
 #权限表
 #登陆权限表，其中user_id外键,是user表的主键
 CREATE TABLE `login_auth` (
   `id` int(16) NOT NULL AUTO_INCREMENT,
   `user_id` int(16) NOT NULL COMMENT '用户id',
   `username` varchar(24) COLLATE utf8_bin NOT NULL COMMENT '用户名',
   `password` varchar(24) COLLATE utf8_bin NOT NULL COMMENT '密码',
   `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：1-有效；0-无效。 默认值1',
   PRIMARY KEY (`id`),
   UNIQUE KEY `login_user_id` (`user_id`),
   UNIQUE KEY `login_username` (`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
 #登陆权限表初始化数据
 insert into login_auth (user_id,username,password) values (1,'admin','1234');
 ###################################################################
 #创建code表,当type等于‘origin’的时候，他是原始类型
 CREATE TABLE `code` (
   `id` int(16) NOT NULL AUTO_INCREMENT,
   `type` varchar(50) NOT NULL COMMENT '类型',
   `code` varchar(35) NOT NULL COMMENT '编码',
   `name` varchar(200) DEFAULT NULL COMMENT '名称',
   `parent_id` int(16) DEFAULT NULL COMMENT '上级编码',
   PRIMARY KEY (`id`),
   UNIQUE KEY `type` (`type`,`code`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;
#初始化code表的数据
insert into code (type,code,name) values ('sex','-','');
insert into code (type,code,name) values ('sex','0','女');
insert into code (type,code,name) values ('sex','1','男');
###################################################################
#创建job表
CREATE TABLE `job` (
   `job_id` int(16) NOT NULL AUTO_INCREMENT,
   `job_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '作业名称',
   `job_cycle_type` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '作业周期类型,周期表的外键',
   `job_description` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '作业描述',
   `job_link` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '外部链接',
   `job_status` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '作业状态（进度）',
   `old_filename` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '文件原名',
   `filepath` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '地址（上传文件，下载作业）',
   `cycle_setting` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '周期设置（周期复习类型）',
   `reread_time` int(8) DEFAULT NULL COMMENT '多少天后重读（远期重读类型）',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   PRIMARY KEY (`job_id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin
###################################################################
CREATE TABLE `job_trace` (
   `id` int(16) NOT NULL AUTO_INCREMENT,
   `job_id` int(16) NOT NULL COMMENT '作业id',
   `job_cycle_type` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '作业周期类型,周期表的外键',
   `step` varchar(35) COLLATE utf8_bin DEFAULT NULL  COMMENT '进度',
   `step_value` float(6,1) COLLATE utf8_bin DEFAULT NULL  COMMENT '当前进度的跨度（天）',
   `status` varchar(8) COLLATE utf8_bin NOT NULL DEFAULT 'now' COMMENT 'now-当前；pass-已经完成',
   `plan_time`  datetime NOT NULL COMMENT '计划时间',
   `finish_time`  datetime DEFAULT null COMMENT '完成时间',
   `comment` varchar(256) DEFAULT NULL COMMENT'注释',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
###################################################################
#创建用户与作业关联表，将作业归属到用户。
#这里想到一个问题，就是如果有集体，比如班级，老师下发作业给全班学生，这时候作业的进度就很难表示了
#因此，这个表的设计其实是有问题的。也就是说，作业只是作业，进度只是进度，轨迹只是轨迹，都应该分开，这样同一个作业，对于不同人才可以有不同的进度。
#而现在的表结构显然不行，因为作业和进度是在一起的。这样如果有班级的结构的话，作业和进度就很难表示（折中的方式都很丑陋）。
create table user_job(
	`id` int(16) NOT NULL AUTO_INCREMENT,
	`user_id` int(16) NOT NULL COMMENT '用户id',
	`job_id` int(16) NOT NULL COMMENT '作业id',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
###################################################################
###################################################################
###################################################################
###################################################################
###################################################################
###################################################################
###################################################################
###################################################################
###################################################################
###################################################################