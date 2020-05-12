/*
 Navicat Premium Data Transfer

 Source Server         : zdefys
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 118.190.27.19:3306
 Source Schema         : epidermic

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 12/05/2020 22:14:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for illness
-- ----------------------------
DROP TABLE IF EXISTS `illness`;
CREATE TABLE `illness`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `now_confirm` int(11) NULL DEFAULT NULL,
  `confirm` int(11) NULL DEFAULT NULL,
  `dead` int(11) NULL DEFAULT NULL,
  `heal` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1259797475842072579 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of illness
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
