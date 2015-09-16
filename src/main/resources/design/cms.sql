/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.116
Source Server Version : 50610
Source Host           : 192.168.1.116:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2015-09-11 11:58:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources` (
  `id` varchar(20) NOT NULL,
  `sys_resources_id` varchar(20) NOT NULL,
  `sys_resources_name` varchar(20) NOT NULL,
  `sys_resources_url` varchar(100) NOT NULL,
  `sys_resources_parent_id` varchar(20) NOT NULL,
  `sys_resources_permission` varchar(50) NOT NULL,
  `sys_resources_icon` varchar(50) DEFAULT NULL,
  `sys_resources_seq` varchar(20) NOT NULL,
  `sys_resources_create_by` varchar(20) DEFAULT NULL,
  `sys_resources_create_time` datetime DEFAULT NULL,
  `sys_resources_update_by` varchar(20) DEFAULT NULL,
  `sys_resources_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resources` VALUES ('1', '1', 'Dashboard', '1', '0', 'sys', 'glyphicon-home', '1', 'admin', '2015-09-09 17:26:49', 'admin', '2015-09-09 17:26:55');
INSERT INTO `sys_resources` VALUES ('10', '10', 'Child Menu 2', '10', '8', 'sys', '', '10', 'admin', '2015-09-09 17:26:49', 'admin', '2015-09-09 17:26:55');
INSERT INTO `sys_resources` VALUES ('2', '2', 'UI Features', '2', '0', 'sys', 'glyphicon-eye-open', '2', 'admin', '2015-09-09 17:26:49', 'admin', '2015-09-09 17:26:55');
INSERT INTO `sys_resources` VALUES ('3', '3', 'Forms', '3', '0', 'sys', 'glyphicon-edit', '3', 'admin', '2015-09-09 17:26:49', 'admin', '2015-09-09 17:26:55');
INSERT INTO `sys_resources` VALUES ('4', '4', 'Charts', '4', '0', 'sys', 'glyphicon-list-alt', '4', 'admin', '2015-09-09 17:26:49', 'admin', '2015-09-09 17:26:55');
INSERT INTO `sys_resources` VALUES ('5', '5', 'Typography', '5', '0', 'sys', 'glyphicon-font', '5', 'admin', '2015-09-09 17:26:49', 'admin', '2015-09-09 17:26:55');
INSERT INTO `sys_resources` VALUES ('6', '6', 'Gallery', '6', '0', 'sys', 'glyphicon-picture', '6', 'admin', '2015-09-09 17:26:49', 'admin', '2015-09-09 17:26:55');
INSERT INTO `sys_resources` VALUES ('7', '7', 'Tables', '7', '0', 'sys', 'glyphicon-align-justify', '7', 'admin', '2015-09-09 17:26:49', 'admin', '2015-09-09 17:26:55');
INSERT INTO `sys_resources` VALUES ('8', '8', 'Accordion Menu', '8', '0', 'sys', 'glyphicon-plus', '8', 'admin', '2015-09-09 17:26:49', 'admin', '2015-09-09 17:26:55');
INSERT INTO `sys_resources` VALUES ('9', '9', 'Child Menu 1', '9', '8', 'sys', '', '9', 'admin', '2015-09-09 17:26:49', 'admin', '2015-09-09 17:26:55');

-- ----------------------------
-- Table structure for sys_rich_text
-- ----------------------------
DROP TABLE IF EXISTS `sys_rich_text`;
CREATE TABLE `sys_rich_text` (
  `id` varchar(20) NOT NULL,
  `sys_rich_text_id` varchar(20) NOT NULL,
  `sys_rich_text_content` text NOT NULL,
  `sys_rich_text_create_by` varchar(20) DEFAULT NULL,
  `sys_rich_text_create_time` datetime DEFAULT NULL,
  `sys_rich_text_update_by` varchar(20) DEFAULT NULL,
  `sys_rich_text_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`sys_rich_text_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_rich_text
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` varchar(20) NOT NULL,
  `sys_roles_id` varchar(20) NOT NULL,
  `sys_roles_name` varchar(20) NOT NULL,
  `sys_roles_create_by` varchar(20) DEFAULT NULL,
  `sys_roles_create_time` datetime DEFAULT NULL,
  `sys_roles_update_by` varchar(20) DEFAULT NULL,
  `sys_roles_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
INSERT INTO `sys_roles` VALUES ('1', '1', '1', '1', '2015-09-09 13:32:45', '1', '2015-09-09 13:32:49');

-- ----------------------------
-- Table structure for sys_roles_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_resources`;
CREATE TABLE `sys_roles_resources` (
  `id` varchar(20) NOT NULL,
  `sys_roles_resources_id` varchar(20) NOT NULL,
  `sys_roles_id` varchar(20) NOT NULL,
  `sys_resources_id` varchar(20) NOT NULL,
  `sys_roles_resources_create_by` varchar(20) DEFAULT NULL,
  `sys_roles_resources_create_time` datetime DEFAULT NULL,
  `sys_roles_resources_update_by` varchar(20) DEFAULT NULL,
  `sys_roles_resources_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles_resources
-- ----------------------------
INSERT INTO `sys_roles_resources` VALUES ('1', '1', '1', '1', '1', '2015-09-28 13:33:23', '1', '2015-09-09 13:33:30');
INSERT INTO `sys_roles_resources` VALUES ('10', '10', '1', '10', '1', '2015-09-09 15:51:28', '1', '2015-09-09 15:51:30');
INSERT INTO `sys_roles_resources` VALUES ('2', '2', '1', '2', '1', '2015-09-09 15:51:28', '1', '2015-09-09 15:51:30');
INSERT INTO `sys_roles_resources` VALUES ('6', '6', '1', '6', '1', '2015-09-09 15:51:28', '1', '2015-09-09 15:51:30');
INSERT INTO `sys_roles_resources` VALUES ('7', '7', '1', '7', '1', '2015-09-09 15:51:28', '1', '2015-09-09 15:51:30');
INSERT INTO `sys_roles_resources` VALUES ('8', '8', '1', '8', '1', '2015-09-09 15:51:28', '1', '2015-09-09 15:51:30');
INSERT INTO `sys_roles_resources` VALUES ('9', '9', '1', '9', '1', '2015-09-09 15:51:28', '1', '2015-09-09 15:51:30');

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` varchar(20) NOT NULL,
  `sys_users_id` varchar(20) NOT NULL,
  `sys_users_name` varchar(20) NOT NULL,
  `sys_users_create_by` varchar(20) DEFAULT NULL,
  `sys_users_create_time` datetime DEFAULT NULL,
  `sys_users_update_by` varchar(20) DEFAULT NULL,
  `sys_users_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('1', '1', 'test', '1', '2015-09-07 14:03:46', '1', '2015-09-07 14:03:49');
INSERT INTO `sys_users` VALUES ('2', '2', 'test2', '2', '2015-09-07 14:04:01', '2', '2015-09-07 14:04:03');

-- ----------------------------
-- Table structure for sys_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `id` varchar(20) NOT NULL,
  `sys_users_roles_id` varchar(20) NOT NULL,
  `sys_roles_id` varchar(20) NOT NULL,
  `sys_users_id` varchar(20) NOT NULL,
  `sys_users_roles_create_by` varchar(20) DEFAULT NULL,
  `sys_users_roles_create_time` datetime DEFAULT NULL,
  `sys_users_roles_update_by` varchar(20) DEFAULT NULL,
  `sys_users_roles_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
INSERT INTO `sys_users_roles` VALUES ('1', '1', '1', '1', '1', '2015-09-09 13:33:40', '1', '2015-09-09 13:33:45');
