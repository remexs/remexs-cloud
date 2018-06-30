/*
Navicat MySQL Data Transfer

Source Server         : 192.168.110.10
Source Server Version : 50722
Source Host           : 192.168.110.10:3306
Source Database       : remexs_corp

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-30 16:54:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `re_company`
-- ----------------------------
DROP TABLE IF EXISTS `re_company`;
CREATE TABLE `re_company` (
  `id` varchar(32) NOT NULL COMMENT '公司编码',
  `name` varchar(50) NOT NULL COMMENT '公司名称',
  `secret` varchar(64) NOT NULL COMMENT '秘钥',
  `create_id` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司';

-- ----------------------------
-- Records of re_company
-- ----------------------------
INSERT INTO `re_company` VALUES ('1011525233896607746', '测试公司一', '123456', '0', '2018-06-26 16:23:04', null, null);
INSERT INTO `re_company` VALUES ('1011525280444993538', '测试公司二', '123456', '0', '2018-06-26 16:23:15', null, null);
INSERT INTO `re_company` VALUES ('994382966526324737', '测试', '123456', '0', '2018-05-10 09:05:48', null, null);

-- ----------------------------
-- Table structure for `re_department`
-- ----------------------------
DROP TABLE IF EXISTS `re_department`;
CREATE TABLE `re_department` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `parent_id` varchar(32) NOT NULL COMMENT '上级部门',
  `company_id` varchar(32) NOT NULL COMMENT '公司编码',
  `has_children` tinyint(1) NOT NULL COMMENT '是否有子部门',
  `manager_id` varchar(32) DEFAULT NULL COMMENT '部门管理者',
  `create_id` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司部门';

-- ----------------------------
-- Records of re_department
-- ----------------------------

-- ----------------------------
-- Table structure for `re_employee`
-- ----------------------------
DROP TABLE IF EXISTS `re_employee`;
CREATE TABLE `re_employee` (
  `id` varchar(32) NOT NULL COMMENT '编码',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `account` varchar(20) DEFAULT NULL COMMENT '账号',
  `create_id` varchar(32) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_id` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司用户';

-- ----------------------------
-- Records of re_employee
-- ----------------------------
INSERT INTO `re_employee` VALUES ('1011181342517424130', '刘其', 'liuqi', '0', '2018-06-25 17:36:34', null, null);
