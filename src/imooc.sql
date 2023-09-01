/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : imooc

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 24/02/2020 17:13:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `eno` int(11) NOT NULL,
  `ename` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `salary` float(10, 2) NOT NULL,
  `dname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `hiredate` date NULL DEFAULT NULL,
  PRIMARY KEY (`eno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1000, '员工1000', 5000.00, '市场部', '1992-03-04');
INSERT INTO `employee` VALUES (1001, '员工1001', 3500.00, '市场部', '1988-02-18');
INSERT INTO `employee` VALUES (1002, '员工1002', 4000.00, '市场部', '1996-02-01');
INSERT INTO `employee` VALUES (1003, '员工1003', 4000.00, '市场部', '2001-08-21');
INSERT INTO `employee` VALUES (1004, '员工1004', 4000.00, '市场部', '2003-01-02');
INSERT INTO `employee` VALUES (1005, '员工1005', 4000.00, '市场部', '1996-07-28');
INSERT INTO `employee` VALUES (1006, '员工1006', 4000.00, '市场部', '1999-12-30');
INSERT INTO `employee` VALUES (1007, '员工1007', 4000.00, '市场部', '2009-05-30');
INSERT INTO `employee` VALUES (1008, '员工1008', 4000.00, '市场部', '1984-05-30');
INSERT INTO `employee` VALUES (1009, '员工1009', 4000.00, '市场部', '2004-05-07');
INSERT INTO `employee` VALUES (3308, '张三', 6000.00, '研发部', '2011-05-08');
INSERT INTO `employee` VALUES (3420, '李四', 8700.00, '研发部', '2006-11-11');
INSERT INTO `employee` VALUES (3610, '王五', 4550.00, '市场部', '2009-10-01');

SET FOREIGN_KEY_CHECKS = 1;
