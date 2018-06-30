/*
Navicat MySQL Data Transfer

Source Server         : 192.168.110.10
Source Server Version : 50722
Source Host           : 192.168.110.10:3306
Source Database       : remexs_user

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-30 16:54:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `re_resource`
-- ----------------------------
DROP TABLE IF EXISTS `re_resource`;
CREATE TABLE `re_resource` (
  `id` varchar(20) NOT NULL COMMENT '资源编码',
  `name` varchar(20) NOT NULL COMMENT '资源名称',
  `type` varchar(255) DEFAULT NULL COMMENT '资源类型',
  `code` varchar(50) DEFAULT NULL COMMENT '资源代码',
  `method` varchar(20) DEFAULT NULL COMMENT '请求方式',
  `path` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `create_id` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` bigint(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of re_resource
-- ----------------------------
INSERT INTO `re_resource` VALUES ('1012746485999104001', '新增', 'uri', 'resource:add', 'POST', '/resource', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:56');
INSERT INTO `re_resource` VALUES ('1012746486733107202', '删除', 'uri', 'resource:remove', 'DELETE', '/resource/{*}', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746486829576193', '查看', 'uri', 'resource:get', 'GET', '/resource/{*}', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746486871519234', '修改', 'uri', 'resource:update', 'PUT', '/resource/{*}', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746486909267970', '列表', 'uri', 'resource:list', 'PATCH', '/resource', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746486951211010', '分页', 'uri', 'resource:page', 'PATCH', '/resource/page', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746486993154049', '新增', 'uri', 'role:add', 'POST', '/role', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487026708482', '删除', 'uri', 'role:remove', 'DELETE', '/role/{*}', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487068651521', '查看', 'uri', 'role:get', 'GET', '/role/{*}', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487098011650', '修改', 'uri', 'role:update', 'PUT', '/role/{*}', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487127371777', '列表', 'uri', 'role:list', 'PATCH', '/role', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487165120514', '分页', 'uri', 'role:page', 'PATCH', '/role/page', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487198674946', '用户注册', 'uri', 'user:register', 'GET', '/user/register', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487236423681', '用户验证', 'uri', 'user:validate', 'GET', '/user/validate', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487269978114', '用户登陆', 'uri', 'user:login', 'GET', '/user/login', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487307726849', '新增', 'uri', 'user:add', 'POST', '/user', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487341281281', '删除', 'uri', 'user:remove', 'DELETE', '/user/{*}', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487370641409', '查看', 'uri', 'user:get', 'GET', '/user/{*}', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487412584449', '修改', 'uri', 'user:update', 'PUT', '/user/{*}', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487441944578', '列表', 'uri', 'user:list', 'PATCH', '/user', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012746487467110402', '分页', 'uri', 'user:page', 'PATCH', '/user/page', '0', '2018-06-30 01:15:53', '0', '2018-06-30 14:06:57');
INSERT INTO `re_resource` VALUES ('1012937024891629570', '新增', 'uri', 'company:add', 'POST', '/company', '0', '2018-06-30 13:53:01', '0', '2018-06-30 14:10:09');
INSERT INTO `re_resource` VALUES ('1012937025264922625', '删除', 'uri', 'company:remove', 'DELETE', '/company/{*}', '0', '2018-06-30 13:53:01', '0', '2018-06-30 13:53:01');
INSERT INTO `re_resource` VALUES ('1012937025327837186', '查看', 'uri', 'company:get', 'GET', '/company/{*}', '0', '2018-06-30 13:53:01', '0', '2018-06-30 13:53:01');
INSERT INTO `re_resource` VALUES ('1012937025386557441', '修改', 'uri', 'company:update', 'PUT', '/company/{*}', '0', '2018-06-30 13:53:01', '0', '2018-06-30 13:53:01');
INSERT INTO `re_resource` VALUES ('1012937025705324545', '列表', 'uri', 'company:list', 'PATCH', '/company', '0', '2018-06-30 13:53:01', '0', '2018-06-30 13:53:01');
INSERT INTO `re_resource` VALUES ('1012937025764044801', '分页', 'uri', 'company:page', 'PATCH', '/company/page', '0', '2018-06-30 13:53:01', '0', '2018-06-30 13:53:01');
INSERT INTO `re_resource` VALUES ('1012937026313498626', '新增', 'uri', 'employee:add', 'POST', '/employee', '0', '2018-06-30 13:53:01', null, null);
INSERT INTO `re_resource` VALUES ('1012937026384801794', '删除', 'uri', 'employee:remove', 'DELETE', '/employee/{*}', '0', '2018-06-30 13:53:01', null, null);
INSERT INTO `re_resource` VALUES ('1012937026464493570', '查看', 'uri', 'employee:get', 'GET', '/employee/{*}', '0', '2018-06-30 13:53:01', null, null);
INSERT INTO `re_resource` VALUES ('1012937026539991042', '修改', 'uri', 'employee:update', 'PUT', '/employee/{*}', '0', '2018-06-30 13:53:01', null, null);
INSERT INTO `re_resource` VALUES ('1012937026611294210', '列表', 'uri', 'employee:list', 'PATCH', '/employee', '0', '2018-06-30 13:53:01', null, null);
INSERT INTO `re_resource` VALUES ('1012937026682597378', '分页', 'uri', 'employee:page', 'PATCH', '/employee/page', '0', '2018-06-30 13:53:01', null, null);

-- ----------------------------
-- Table structure for `re_role`
-- ----------------------------
DROP TABLE IF EXISTS `re_role`;
CREATE TABLE `re_role` (
  `id` varchar(32) NOT NULL COMMENT '角色编码',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `create_id` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` bigint(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of re_role
-- ----------------------------

-- ----------------------------
-- Table structure for `re_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `re_role_resource`;
CREATE TABLE `re_role_resource` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL COMMENT '角色编码',
  `resource_id` varchar(32) NOT NULL COMMENT '资源编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源';

-- ----------------------------
-- Records of re_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `re_user`
-- ----------------------------
DROP TABLE IF EXISTS `re_user`;
CREATE TABLE `re_user` (
  `id` varchar(32) NOT NULL COMMENT '用户编码',
  `name` varchar(20) NOT NULL COMMENT '用户名称',
  `account` varchar(32) NOT NULL COMMENT '用户账号',
  `password` varchar(64) NOT NULL COMMENT '用户密码',
  `create_id` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` bigint(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of re_user
-- ----------------------------
INSERT INTO `re_user` VALUES ('996924922188062722', '刘其', 'liuqi', '$2a$12$pOKOFPGzhF7Tb7PGZkGS7ucHFGYldC66//j3jbjdDiaM0tvgsLOIa', '0', '2018-05-17 09:26:38', null, null);

-- ----------------------------
-- Table structure for `re_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `re_user_role`;
CREATE TABLE `re_user_role` (
  `id` varchar(32) DEFAULT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- ----------------------------
-- Records of re_user_role
-- ----------------------------
