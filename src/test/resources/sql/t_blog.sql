CREATE TABLE `blog` (
`id` int(16) NOT NULL AUTO_INCREMENT,
`user_id` int(16) NOT NULL COMMENT '用户id',
`content` text DEFAULT NULL COMMENT '内容',
`status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：1-有效；0-无效。 默认值1',
`create_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;
