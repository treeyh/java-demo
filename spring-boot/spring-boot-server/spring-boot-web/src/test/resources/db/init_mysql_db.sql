/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.170.82
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 192.168.170.82:3306
 Source Schema         : springboot_demo

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 19/01/2019 14:56:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for active_info
-- ----------------------------
DROP TABLE IF EXISTS `active_info`;
CREATE TABLE `active_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `size` int(11) NOT NULL,
  `score` double(20,2) NOT NULL,
  `type` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of active_info
-- ----------------------------
BEGIN;
INSERT INTO active_info VALUES (1, '1', 1, 1.23, 2, 1, '2018-05-05 04:12:12', '2018-05-05 04:12:12');
INSERT INTO active_info VALUES (2, '2', 2, 1.23, 2, 1, '2018-05-05 04:12:12', '2018-05-05 04:12:12');
INSERT INTO active_info VALUES (3, '3', 3, 1.23, 2, 1, '2018-05-05 04:12:12', '2018-05-05 04:12:12');
INSERT INTO active_info VALUES (4, '4', 4, 1.10, 1, 1, '2019-01-07 16:05:28', '2019-01-07 16:05:28');
INSERT INTO active_info VALUES (5, '5', 5, 1.10, 1, 1, '2019-01-07 16:06:16', '2019-01-07 16:06:16');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
