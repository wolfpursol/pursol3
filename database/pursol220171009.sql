/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : pursol2

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-10-09 17:17:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `cid` int(15) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `createtime` varchar(19) DEFAULT NULL,
  `text` longtext,
  `userid` int(15) DEFAULT NULL,
  `state` char(1) DEFAULT NULL,
  `classifyid` int(10) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for `classify`
-- ----------------------------
DROP TABLE IF EXISTS `classify`;
CREATE TABLE `classify` (
  `classifyid` int(10) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `createtime` varchar(19) DEFAULT NULL,
  `text` longtext,
  `state` char(1) DEFAULT NULL,
  PRIMARY KEY (`classifyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classify
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_log`;
CREATE TABLE `sys_user_log` (
  `logid` int(10) NOT NULL AUTO_INCREMENT,
  `userip` varchar(16) DEFAULT NULL,
  `createdate` varchar(19) DEFAULT NULL,
  `descript` varchar(2000) DEFAULT NULL,
  `userid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`logid`)
) ENGINE=MyISAM AUTO_INCREMENT=3013 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `age` int(5) DEFAULT NULL,
  `createtime` varchar(19) DEFAULT NULL,
  `state` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('15', '王五', '12', '2017-09-30 15:13:13', '1');
INSERT INTO `user` VALUES ('28', '558', '26', '2017-10-09 15:29:43', '1');
INSERT INTO `user` VALUES ('29', 'nice', '12', '2017-10-09 15:29:55', '1');
