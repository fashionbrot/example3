

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `request_desc` varchar(100) DEFAULT NULL COMMENT '请求描述',
  `request_url` varchar(100) DEFAULT NULL COMMENT '请求url',
  `request_method` varchar(8) DEFAULT NULL COMMENT '请求方法',
  `request_ip` varchar(32) DEFAULT NULL COMMENT '请求ip',
  `request_param` varchar(1000) DEFAULT NULL COMMENT '请求参数',
  `target_method` varchar(32) DEFAULT NULL COMMENT '接口方法',
  `log_type` tinyint(1) NOT NULL COMMENT '1 请求日志 2:异常日志',
  `interface_type` tinyint(1) NOT NULL COMMENT '接口类型 1:后台日志 2:机构后台日志 3:web 端日志',
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建者id',
  `create_name` varchar(20) DEFAULT NULL COMMENT '操作者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `exception` varchar(1000) DEFAULT NULL COMMENT '异常日志',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志位 1删除 0未删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系统日志';
ALTER TABLE sys_log ADD INDEX index_del_flagAndinterface_type (del_flag,interface_type);
ALTER TABLE sys_log ADD INDEX index_create_id (create_id);


DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `org_name` varchar(30) NOT NULL COMMENT '机构名称',
  `parent_id` bigint(11) default 0 comment '父机构id',
  `group_id` bigint(11) default 0 comment '组id',
  `leader` varchar(20) default null comment '负责人',
  `phone` varchar(11) default null comment '联系电话',
  `status` tinyint(2) NOT NULL COMMENT '机构状态 1开启 2关闭',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='机构表';




DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户名',
  `real_name` varchar(20) NOT NULL COMMENT '真实姓名',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `password` varchar(32) NOT NULL COMMENT '加密密码',
  `salt` varchar(32) NOT NULL COMMENT '密码加盐参数',
  `status` tinyint(2) NOT NULL COMMENT '用户状态',
  `super_admin` tinyint(1) DEFAULT '0' COMMENT '是否是超级管理员 1超级 0普通',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_id` bigint(11) NOT NULL COMMENT '创建者id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_id` bigint(11) DEFAULT NULL COMMENT '最近更新者id',
  `update_date` datetime DEFAULT NULL COMMENT '最近更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志位 1删除 0未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户信息表';
ALTER TABLE sys_user ADD INDEX index_del_flag (del_flag);


DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `menu_name` varchar(16) NOT NULL COMMENT '菜单名称',
  `menu_level` tinyint(3) unsigned NOT NULL COMMENT '菜单级别',
  `menu_url` varchar(100) DEFAULT NULL COMMENT '菜单url',
  `parent_menu_id` bigint(11) unsigned DEFAULT '0' COMMENT '父菜单id',
  `priority` int(5) unsigned NOT NULL COMMENT '显示优先级',
  `code` varchar(64) DEFAULT NULL COMMENT '权限code',
  `target` varchar(10) default 'menuItem' comment '打开方式（menuItem页签 menuBlank新窗口）',
  `visible` tinyint(1) default 0 comment '菜单状态（0显示 1隐藏）',
  `is_refresh` tinyint(1) default 1 comment '是否刷新（0刷新 1不刷新）',
  `icon` varchar(32) default '#' comment '菜单图标',
  `create_id` bigint(11) DEFAULT NULL COMMENT '创建者id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(11) DEFAULT NULL COMMENT '最近更新者id',
  `update_date` datetime DEFAULT NULL COMMENT '最近更新时间',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标志位 1删除 0未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='菜单表';
ALTER TABLE sys_menu ADD INDEX index_del_flag (del_flag);