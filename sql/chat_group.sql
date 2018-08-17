DROP TABLE IF EXISTS `chat_group`;
CREATE TABLE `chat_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `two_dimension_code` mediumblob NOT NULL COMMENT '二维码图片base64编码数据',
  `tags` varchar(200) NOT NULL COMMENT '标签以'',''分割',
  `position` varchar(100) NOT NULL COMMENT '位置',
  `description` varchar(200) NOT NULL COMMENT '描述',
  `status` int(11) NOT NULL COMMENT '状态',
  `create_user_id` varchar(100) NOT NULL COMMENT '创建者用户openId',
  `create_user_name` varchar(100) NOT NULL COMMENT '创建者用户名称',
  `create_user_account_number` varchar(100) NOT NULL COMMENT '创建者用户账号',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
