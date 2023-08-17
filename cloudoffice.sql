/*
 Navicat Premium Data Transfer

 Source Server         : localhost.mysql_8.0.17_3306
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : cloudoffice

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 30/08/2021 11:14:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `enabled` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名-登录',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_face` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1001, 'admin', '13000000000', '15600000000', '江苏省无锡市梁溪区晶品公寓', '1', 'admin', '$2a$10$bpYMVmzOG96BT/5OxiA63.0ZcuWqjiw8gcfgSR0G.C7yVNTBefWWK', 'https://ae01.alicdn.com/kf/Ud4325f458c344b13a073e29095c69e39u.jpg', '管理员');
INSERT INTO `t_admin` VALUES (1002, 'hr', '13800000000', '13800000000', '江苏省无锡市梁溪区晶品公寓', '1', 'hr', '$2a$10$RUNJL76af9fDKJM12SITGu8tnrl6vggvK1nCHOj0.phrksn94oCby', 'https://ae01.alicdn.com/kf/U53f084ee63814e6695c33d498203b662L.jpg', '人事部员工');
INSERT INTO `t_admin` VALUES (1003, 'test', '13888888888', '13888888888', '江苏省无锡市梁溪区晶品公寓', '1', 'test', '$2a$10$kTKWMTgFmcLR7N60FJkhX.P0MTEZxFSX8A2G3tWupnz3QFJiBIgSi', 'https://ae01.alicdn.com/kf/Ucaa9d69887074c239c78550c7ef70bfbt.jpg', '人事部');
INSERT INTO `t_admin` VALUES (1004, 'test1', '13800000000', '13800000000', '江苏省无锡市梁溪区晶品公寓', '1', 'test1', '$2a$10$RUNJL76af9fDKJM12SITGu8tnrl6vggvK1nCHOj0.phrksn94oCby', 'https://ae01.alicdn.com/kf/U7dcf4edf012b4932a52126891f28f99fY.jpg', '人事部');
INSERT INTO `t_admin` VALUES (1005, 'test2', '13800000000', '13800000000', '江苏省无锡市梁溪区晶品公寓', '1', 'test2', '$2a$10$RUNJL76af9fDKJM12SITGu8tnrl6vggvK1nCHOj0.phrksn94oCby', 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3208455899,1963523311&fm=26&gp=0.jpg', '人事部');
INSERT INTO `t_admin` VALUES (1006, 'test3', '13800000000', '13800000000', '江苏省无锡市梁溪区晶品公寓', '0', 'test3', '$2a$10$RUNJL76af9fDKJM12SITGu8tnrl6vggvK1nCHOj0.phrksn94oCby', 'https://ae01.alicdn.com/kf/Ud188958b264f4f62897a0dafa5f060dcp.jpg', '人事部');
INSERT INTO `t_admin` VALUES (1007, 'test4', '13800000000', '13800000000', '江苏省无锡市梁溪区晶品公寓', '1', 'test4', '$2a$10$RUNJL76af9fDKJM12SITGu8tnrl6vggvK1nCHOj0.phrksn94oCby', 'https://ae01.alicdn.com/kf/Uf813349d87714b2e90925a7f0ba744f0h.jpg', '人事部');

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `admin_id` int(50) NULL DEFAULT NULL COMMENT '用户id',
  `rid` int(50) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES (1, 1001, 1);
INSERT INTO `t_admin_role` VALUES (2, 1002, 3);
INSERT INTO `t_admin_role` VALUES (50, 1003, 2);
INSERT INTO `t_admin_role` VALUES (51, 1003, 4);
INSERT INTO `t_admin_role` VALUES (52, 1006, 2);
INSERT INTO `t_admin_role` VALUES (53, 1004, 2);
INSERT INTO `t_admin_role` VALUES (54, 1005, 4);
INSERT INTO `t_admin_role` VALUES (55, 1005, 2);

-- ----------------------------
-- Table structure for t_appraise
-- ----------------------------
DROP TABLE IF EXISTS `t_appraise`;
CREATE TABLE `t_appraise`  (
  `id` int(11) NOT NULL,
  `eid` int(11) NULL DEFAULT NULL COMMENT '员工id',
  `app_date` date NULL DEFAULT NULL COMMENT '考评日期',
  `app_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考评结果',
  `app_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考评内容',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_appraise
-- ----------------------------

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父id',
  `dep_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `enabled` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否启用',
  `is_parent` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否上级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES (1, '股东会', -1, '.1', '1', '1');
INSERT INTO `t_department` VALUES (2, '董事会', 1, '.1.2', '1', '1');
INSERT INTO `t_department` VALUES (3, '总办', 2, '.1.2.3', '1', '1');
INSERT INTO `t_department` VALUES (4, '财务办', 3, '.1.2.3.4', '1', '0');
INSERT INTO `t_department` VALUES (5, '市场部', 3, '.1.2.3.5', '1', '1');
INSERT INTO `t_department` VALUES (6, '华东市场部', 5, '.1.2.3.5.6', '1', '1');
INSERT INTO `t_department` VALUES (7, '华南市场部', 5, '.1.2.3.5.7', '1', '0');
INSERT INTO `t_department` VALUES (8, '上海市场部', 6, '.1.2.3.5.6.8', '1', '0');
INSERT INTO `t_department` VALUES (9, '西北市场部', 5, '.1.2.3.5.9', '1', '1');
INSERT INTO `t_department` VALUES (10, '贵阳市场部', 9, '.1.2.3.5.9.10', '1', '1');
INSERT INTO `t_department` VALUES (11, '乌当区市场部', 10, '.1.2.3.5.9.10.11', '1', '0');
INSERT INTO `t_department` VALUES (12, '技术部', 3, '.1.2.3.12', '1', '0');
INSERT INTO `t_department` VALUES (13, '运维部', 3, '.1.2.3.13', '1', '0');
INSERT INTO `t_department` VALUES (15, '华中市场部', 5, '1.2.3.5.15', '1', '1');
INSERT INTO `t_department` VALUES (16, '测试部', 3, '.1.2.3.16', '1', '0');
INSERT INTO `t_department` VALUES (23, '江西市场部', 15, '1.2.3.5.15.23', '1', '0');
INSERT INTO `t_department` VALUES (24, '创始人联席会', 1, '.1.24', '1', '0');
INSERT INTO `t_department` VALUES (37, '南京市场部', 6, '.1.2.3.5.6.37', '1', '0');

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `gender` enum('男','女') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `wedlock` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '婚姻状况',
  `nation_id` int(11) NULL DEFAULT NULL COMMENT '民族',
  `native_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `politic_id` int(10) NULL DEFAULT NULL COMMENT '政治面貌',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `department_id` int(11) NULL DEFAULT NULL COMMENT '所属部门',
  `job_level_id` int(11) NULL DEFAULT NULL COMMENT '职称ID',
  `pos_id` int(11) NULL DEFAULT NULL COMMENT '职位ID',
  `engage_form` enum('劳务合同','劳动合同') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '聘用形式',
  `tiptop_degree` enum('中专','高中','大专','本科','研究生','博士') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最高学历',
  `specialty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属专业',
  `school` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毕业院校',
  `begin_date` date NULL DEFAULT NULL COMMENT '入职日期',
  `work_state` enum('在职','离职') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '在职状态',
  `work_id` int(11) NULL DEFAULT NULL COMMENT '工号',
  `contract_term` double(10, 0) NULL DEFAULT NULL COMMENT '合同期限',
  `conversion_time` date NULL DEFAULT NULL COMMENT '转正日期',
  `not_work_date` date NULL DEFAULT NULL COMMENT '离职日期',
  `begin_contract` date NULL DEFAULT NULL COMMENT '合同起始日期',
  `end_contract` date NULL DEFAULT NULL COMMENT '合同终止日期',
  `work_age` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工龄',
  `salary_id` int(11) NULL DEFAULT NULL COMMENT '工资账套ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 278 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES (1, '韦梅', '女', '1999-11-20', '341502198810194111', '已婚', 1, '关岭市1', 1, 'test@163.com', '15567487644', '贵州市关岭县朱北组13号', 8, 1, 1, '劳务合同', '中专', '1', '1', '2021-02-01', '在职', 33726, 0, '2021-02-16', '2021-02-17', '2021-02-01', '2021-02-25', '1', NULL);
INSERT INTO `t_employee` VALUES (2, '张三', '男', '1999-11-20', '341502198810194', '未婚', 1, '关岭市2', 1, 'test@163.com', '15567487644', '贵州市关岭县朱北组13号', 3, 1, 1, '劳务合同', '中专', '1', '1', '2021-02-01', '在职', 33727, 1, '2021-02-16', '2021-02-17', '2021-02-01', '2021-02-25', '1', 3);
INSERT INTO `t_employee` VALUES (3, '王五', '男', '1999-11-20', '341502198810194', '未婚', 1, '关岭市3', 1, 'test@163.com', '15567487644', '贵州市关岭县朱北组13号', 3, 1, 1, '劳务合同', '中专', '1', '1', '2021-02-01', '在职', 33728, 1, '2021-02-16', '2021-02-17', '2021-02-01', '2021-02-25', '1', 2);
INSERT INTO `t_employee` VALUES (98, '李四', '男', '2021-04-01', '362411199001011111', '已婚', 1, '上海', 1, '123@qq.com', '13888888888', '上海', 8, 4, 17, '劳动合同', '本科', '地质勘探', '华东交大', '2020-01-01', '在职', 33729, 3, '2020-01-01', NULL, '2020-01-01', '2023-01-01', '', 5);
INSERT INTO `t_employee` VALUES (99, '王二麻子', '女', '2020-01-01', '362411199001011111', '已婚', 1, '南京', 1, '123@qq.com', '13888888888', '南京', 8, 4, 17, '劳动合同', '研究生', '地质勘探', '华东政法', '2020-01-01', '在职', 33730, 3, '2020-01-01', NULL, '2020-01-01', '2023-01-01', '', 1);
INSERT INTO `t_employee` VALUES (100, '小燕子', '女', '2010-02-01', '362411199001011111', '已婚', 1, '南京', 1, '123@qq.com', '13888888888', '南京', 7, 3, 16, '劳动合同', '本科', '地理信息', '西南大学', '2020-01-01', '在职', 33730, 3, '2020-01-01', NULL, '2020-01-01', '2023-01-01', '', 3);
INSERT INTO `t_employee` VALUES (101, '金锁', '女', '2010-02-01', '362411199001011111', '已婚', 1, '南京', 1, '123@qq.com', '13888888888', '南京', 7, 3, 16, '劳动合同', '本科', '地理信息', '北京大学', '2020-01-01', '在职', 33731, 6, '2020-01-01', NULL, '2020-01-01', '2026-01-01', '', 2);
INSERT INTO `t_employee` VALUES (102, '金角大王', '男', '1980-01-01', '362411199001011111', '已婚', 1, '江西上饶', 1, '123@qq.com', '13888888888', '江西上饶', 12, 3, 1, '劳务合同', '本科', '软件工程', '南昌大学', '2018-01-01', '在职', 33732, 7, '2018-01-01', NULL, '2018-01-01', '2025-01-01', '', 9);
INSERT INTO `t_employee` VALUES (103, '银角大王', '男', '2021-04-01', '362411199001011111', '已婚', 2, '江西', 1, '123@qq.com', '13888888888', '江西', 8, 2, 16, '劳动合同', '本科', '11', '11', '2020-01-01', '在职', 33733, 1, '2020-01-01', NULL, '2020-01-01', '2021-04-05', '', 6);
INSERT INTO `t_employee` VALUES (104, '孙悟空', '男', '2021-04-01', '360101190001011111', '未婚', 1, '花果山', 2, '123@qq.com', '13888888888', '花果山', 6, 3, 2, '劳动合同', '研究生', '古代棍法实用性研究', '菩提门', '2021-04-01', '在职', 33734, 4, '2021-04-01', NULL, '2021-04-01', '2025-01-01', '', 5);
INSERT INTO `t_employee` VALUES (105, '猪悟能', '男', '2021-04-01', '360101190001011111', '已婚', 1, '高老庄', 2, '123@qq.com', '13888888888', '高老庄', 23, 3, 2, '劳动合同', '本科', '进食的艺术', '天庭', '2021-04-01', '在职', 33735, 4, '2021-04-01', NULL, '2021-04-01', '2025-01-01', '', NULL);
INSERT INTO `t_employee` VALUES (106, '沙悟净', '男', '2021-04-01', '360101190001011111', '未婚', 1, '流沙河', 2, '123@qq.com', '13888888888', '流沙河', 23, 3, 2, '劳动合同', '本科', '高等力学', '天庭', '2021-04-01', '在职', 33736, 4, '2021-04-01', NULL, '2021-04-01', '2025-01-01', '', 1);
INSERT INTO `t_employee` VALUES (107, '金蝉子', '男', '2021-04-01', '360101190001011111', '未婚', 1, '雷音寺', 2, '123@qq.com', '13888888888', '雷音寺', 4, 3, 2, '劳动合同', '博士', '人际关系与社会概论', '雷音寺', '2021-04-01', '在职', 33737, 4, '2021-04-01', NULL, '2021-04-01', '2025-01-01', '', 2);

-- ----------------------------
-- Table structure for t_employee_ec
-- ----------------------------
DROP TABLE IF EXISTS `t_employee_ec`;
CREATE TABLE `t_employee_ec`  (
  `id` int(11) NOT NULL,
  `eid` int(11) NULL DEFAULT NULL COMMENT '员工编号',
  `ec_date` datetime NULL DEFAULT NULL COMMENT '奖罚日期',
  `ec_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '奖罚原因',
  `ec_point` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '奖罚分',
  `ec_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '奖罚类别，0：奖，1：罚',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_employee_ec
-- ----------------------------

-- ----------------------------
-- Table structure for t_employee_remove
-- ----------------------------
DROP TABLE IF EXISTS `t_employee_remove`;
CREATE TABLE `t_employee_remove`  (
  `id` int(11) NOT NULL,
  `eid` int(11) NULL DEFAULT NULL COMMENT '员工id',
  `after_dep_id` int(11) NULL DEFAULT NULL COMMENT '调动后部门',
  `after_job_id` int(11) NULL DEFAULT NULL COMMENT '调动后职位',
  `remove_date` datetime NULL DEFAULT NULL COMMENT '调动日期',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调动原因',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_employee_remove
-- ----------------------------

-- ----------------------------
-- Table structure for t_employee_train
-- ----------------------------
DROP TABLE IF EXISTS `t_employee_train`;
CREATE TABLE `t_employee_train`  (
  `id` int(11) NOT NULL,
  `eid` int(11) NULL DEFAULT NULL COMMENT '员工编号',
  `train_date` datetime NULL DEFAULT NULL COMMENT '培训日期',
  `train_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '培训内容',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_employee_train
-- ----------------------------

-- ----------------------------
-- Table structure for t_joblevel
-- ----------------------------
DROP TABLE IF EXISTS `t_joblevel`;
CREATE TABLE `t_joblevel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称名称',
  `title_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '职称等级',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `enabled` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'true' COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_joblevel
-- ----------------------------
INSERT INTO `t_joblevel` VALUES (1, '技术员', '初级', '2021-02-17 00:11:14', 'true');
INSERT INTO `t_joblevel` VALUES (2, '实施', '初级', '2021-02-17 00:11:14', 'true');
INSERT INTO `t_joblevel` VALUES (3, '工程师', '中级', '2021-03-24 22:20:18', 'true');
INSERT INTO `t_joblevel` VALUES (4, '测试工程师', '高级', '2021-03-24 22:23:46', 'true');
INSERT INTO `t_joblevel` VALUES (10, '测试222', '高级', '2021-04-17 10:23:53', 'false');

-- ----------------------------
-- Table structure for t_mail_log
-- ----------------------------
DROP TABLE IF EXISTS `t_mail_log`;
CREATE TABLE `t_mail_log`  (
  `msgId` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息id',
  `eid` int(30) NULL DEFAULT NULL COMMENT '接收员工id',
  `status` bigint(1) NULL DEFAULT NULL COMMENT '状态（0:消息投递中 1:投递成功 2:投递失败）',
  `routeKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由键',
  `exchange` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交换机',
  `count` int(11) NULL DEFAULT NULL COMMENT '重试次数',
  `tryTime` datetime NULL DEFAULT NULL COMMENT '重试时间',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`msgId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_mail_log
-- ----------------------------
INSERT INTO `t_mail_log` VALUES ('02968cb0-20d8-418b-9952-0e60a7f2f7c2', 86, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-29 18:15:30', '2021-03-29 18:14:30', '2021-03-29 18:14:30');
INSERT INTO `t_mail_log` VALUES ('057cd61f-8873-467d-9220-b481c395f2ae', 106, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-04-21 15:50:09', '2021-04-21 15:49:09', '2021-04-21 15:49:09');
INSERT INTO `t_mail_log` VALUES ('0d729410-568f-4d3d-b8c7-a9c5123e19ef', 92, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 1, '2021-03-29 20:34:10', '2021-03-29 20:32:07', '2021-03-29 20:33:10');
INSERT INTO `t_mail_log` VALUES ('0da3d30d-3a73-4431-b229-00dfd578c1c4', 98, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 4, '2021-04-21 15:02:00', '2021-04-21 14:56:41', '2021-04-21 15:01:00');
INSERT INTO `t_mail_log` VALUES ('123456789', 97, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-29 21:28:05', '2021-03-29 21:27:05', '2021-03-29 21:27:05');
INSERT INTO `t_mail_log` VALUES ('12cfdad3-d6d2-465c-9525-6baf42d57a92', 85, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-29 18:13:29', '2021-03-29 18:12:29', '2021-03-29 18:12:29');
INSERT INTO `t_mail_log` VALUES ('2b84cd62-18ee-4657-a40b-deb1e432343c', 89, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-29 18:20:42', '2021-03-29 18:19:42', '2021-03-29 18:19:42');
INSERT INTO `t_mail_log` VALUES ('2fc1aa03-df7c-4360-bfee-7f352e78f7b2', 73, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-28 17:21:35', '2021-03-28 17:20:35', '2021-03-28 17:20:35');
INSERT INTO `t_mail_log` VALUES ('3d5b52c6-ad62-4491-9cd2-155ee68fc5fd', 88, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-29 18:18:30', '2021-03-29 18:17:30', '2021-03-29 18:17:30');
INSERT INTO `t_mail_log` VALUES ('5d0fbec5-c752-48fb-bb2f-d6bbba47e3fd', 65, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 1, '2021-03-28 15:32:50', '2021-03-28 15:30:50', '2021-03-28 15:31:50');
INSERT INTO `t_mail_log` VALUES ('5d4aba9b-f743-497b-9774-90ebffa537ac', 90, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-29 18:26:52', '2021-03-29 18:25:52', '2021-03-29 18:25:52');
INSERT INTO `t_mail_log` VALUES ('61e7895f-d580-48b7-b54f-2d2a323e8d80', 107, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-04-21 15:52:09', '2021-04-21 15:51:09', '2021-04-21 15:51:09');
INSERT INTO `t_mail_log` VALUES ('6333d000-e6d4-49dc-8a1b-600c977929e8', 105, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-04-21 15:49:07', '2021-04-21 15:48:07', '2021-04-21 15:48:07');
INSERT INTO `t_mail_log` VALUES ('697b610e-29ec-4f4a-8da1-f014daaf82be', 75, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-28 17:37:09', '2021-03-28 17:36:09', '2021-03-28 17:36:09');
INSERT INTO `t_mail_log` VALUES ('69e3b664-aa46-4b7c-87bc-facd9712dd3e', 108, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-04-21 15:52:39', '2021-04-21 15:51:39', '2021-04-21 15:51:39');
INSERT INTO `t_mail_log` VALUES ('6d08d44d-7397-4a88-b9c5-023a9ee88d5f', 74, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-28 17:31:06', '2021-03-28 17:30:06', '2021-03-28 17:30:06');
INSERT INTO `t_mail_log` VALUES ('71d83a6d-d689-4c3d-8ea8-f92ab67fab86', 104, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-04-21 15:47:06', '2021-04-21 15:46:06', '2021-04-21 15:46:06');
INSERT INTO `t_mail_log` VALUES ('7d3cf9ef-c2c4-4a71-8a2b-d36e448d1702', 102, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 1, '2021-04-21 15:29:30', '2021-04-21 15:27:10', '2021-04-21 15:28:30');
INSERT INTO `t_mail_log` VALUES ('862c5887-4a40-42ff-965b-24d8960db569', 93, 2, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 4, '2021-03-29 20:41:00', '2021-03-29 20:35:58', '2021-03-29 20:40:00');
INSERT INTO `t_mail_log` VALUES ('87e318e7-807d-4055-b400-0ba42499ba95', 101, 2, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 4, '2021-04-21 15:26:30', '2021-04-21 15:21:22', '2021-04-21 15:25:30');
INSERT INTO `t_mail_log` VALUES ('962dcd48-4a69-4218-a974-259c2c0dc619', 87, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-29 18:16:12', '2021-03-29 18:15:12', '2021-03-29 18:15:12');
INSERT INTO `t_mail_log` VALUES ('99840776-ea87-4b97-acc5-6a04705fd95d', 70, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-28 16:49:51', '2021-03-28 16:48:51', '2021-03-28 16:48:51');
INSERT INTO `t_mail_log` VALUES ('9a346b67-4052-4da4-9cdb-5dd3c31dfa56', 109, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-04-21 16:01:37', '2021-04-21 16:00:37', '2021-04-21 16:00:37');
INSERT INTO `t_mail_log` VALUES ('9bd49c3e-2efe-4e62-a30c-8b0c21b5d1ec', 100, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-04-21 15:07:49', '2021-04-21 15:06:49', '2021-04-21 15:06:49');
INSERT INTO `t_mail_log` VALUES ('a70bafde-e4eb-4ded-86b9-75cdfa4c4478', 71, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-28 16:50:43', '2021-03-27 15:49:43', '2021-03-28 16:49:43');
INSERT INTO `t_mail_log` VALUES ('af26749c-a624-4630-bd77-628ed62fbf6f', 103, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-04-21 15:34:53', '2021-04-21 15:33:53', '2021-04-21 15:33:53');
INSERT INTO `t_mail_log` VALUES ('beb0042f-49ff-4ce5-995e-77c00e454177', 76, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-28 17:40:03', '2021-03-28 17:39:03', '2021-03-28 17:39:03');
INSERT INTO `t_mail_log` VALUES ('c46452cc-7e64-45d7-a5dd-5bb91cfc4959', 66, 2, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 4, '2021-03-28 15:40:00', '2021-03-28 15:34:58', '2021-03-28 15:39:00');
INSERT INTO `t_mail_log` VALUES ('cbd7e76b-c639-4322-8b5e-fe51f14cf681', 99, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 1, '2021-04-21 15:01:30', '2021-04-21 14:59:17', '2021-04-21 15:00:30');
INSERT INTO `t_mail_log` VALUES ('d425c47d-bd8a-48af-9ed9-19918cd07dfa', 72, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-28 17:20:02', '2021-03-28 17:19:02', '2021-03-28 17:19:02');
INSERT INTO `t_mail_log` VALUES ('e1ed86b7-b748-406a-ad5f-cdb608c2e330', 91, 1, 'sunwul.mail.routing.key', 'sunwul.mail.exchange', 0, '2021-03-29 18:28:25', '2021-03-29 18:27:25', '2021-03-29 18:27:25');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(11) NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `iconCls` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `keep_alive` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否保持激活',
  `require_auth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否要求权限',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父id',
  `enabled` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (100, NULL, '', NULL, '所有', NULL, NULL, NULL, NULL, '1');
INSERT INTO `t_menu` VALUES (200, '/', '/home', 'Home', '员工管理', 'fa fa-user-circle-o', NULL, '1', 100, '1');
INSERT INTO `t_menu` VALUES (201, '/employee/basic/**', '/emp/basic', 'EmpBasic', '基本资料', '', NULL, '1', 200, '1');
INSERT INTO `t_menu` VALUES (202, '/employee/advanced/**', '/emp/adv', 'EmpAdv', '高级资料', '', NULL, '1', 200, '1');
INSERT INTO `t_menu` VALUES (300, '/', '/home', 'Home', '人事资料', 'fa fa-address-card-o', NULL, '1', 100, '1');
INSERT INTO `t_menu` VALUES (301, '/personal/emp/**', '/per/emp', 'PerEmp', '员工资料', NULL, NULL, '1', 300, '1');
INSERT INTO `t_menu` VALUES (302, '/personal/ec/**', '/per/ec', 'PerEc', '员工奖惩', NULL, NULL, '1', 300, '1');
INSERT INTO `t_menu` VALUES (303, '/personal/train/**', '/per/train', 'PerTrain', '员工培训', NULL, NULL, '1', 300, '1');
INSERT INTO `t_menu` VALUES (304, '/personal/salary/**', '/per/salary', 'PerSalary', '员工调薪', NULL, NULL, '1', 300, '1');
INSERT INTO `t_menu` VALUES (305, '/personal/remove/**', '/per/mv', 'PerMv', '员工调动', NULL, NULL, '1', 300, '1');
INSERT INTO `t_menu` VALUES (400, '/', '/home', 'Home', '薪资管理', 'fa fa-money', NULL, '1', 100, '1');
INSERT INTO `t_menu` VALUES (401, '/salary/sob/**', '/salary/sob', 'SalSob', '工资账套管理', NULL, NULL, '1', 400, '1');
INSERT INTO `t_menu` VALUES (402, '/salary/sobcfg/**', '/salary/sobcfg', 'SalSobCfg', '员工工资账套设置', NULL, NULL, '1', 400, '1');
INSERT INTO `t_menu` VALUES (403, '/salary/table/**', '/salary/table', 'SalTable', '工资表管理', NULL, NULL, '1', 400, '1');
INSERT INTO `t_menu` VALUES (404, '/salary/month/**', '/salary/month', 'SalMonth', '月末处理', NULL, NULL, '1', 400, '1');
INSERT INTO `t_menu` VALUES (405, '/salary/search/**', '/salary/search', 'SalSearch', '工资Search管理', NULL, NULL, '1', 400, '1');
INSERT INTO `t_menu` VALUES (500, '/', '/home', 'Home', '统计管理', 'fa fa-bar-chart', NULL, '1', 100, '1');
INSERT INTO `t_menu` VALUES (501, '/statistics/all/**', '/sta/all', 'StaAll', '综合信息统计', NULL, NULL, '1', 500, '1');
INSERT INTO `t_menu` VALUES (502, '/statistics/score/**', '/sta/score', 'StaScore', '员工积分统计', NULL, NULL, '1', 500, '1');
INSERT INTO `t_menu` VALUES (503, '/statistics/personal/**', '/sta/pers', 'StaPers', '人事信息统计', NULL, NULL, '1', 500, '1');
INSERT INTO `t_menu` VALUES (504, '/statistics/record/**', '/sta/record', 'StaRecord', '基础信息统计', NULL, NULL, '1', 500, '1');
INSERT INTO `t_menu` VALUES (600, '/', '/home', 'Home', '系统管理', 'fa fa-windows', NULL, '1', 100, '1');
INSERT INTO `t_menu` VALUES (601, '/system/basic/**', '/sys/basic', 'SysBasic', '基础信息设置', NULL, NULL, '1', 600, '1');
INSERT INTO `t_menu` VALUES (602, '/system/cfg/**', '/sys/cfg', 'SysCfg', '系统设置', NULL, NULL, '1', 600, '1');
INSERT INTO `t_menu` VALUES (603, '/system/log/**', '/sys/log', 'SysLog', '操作日志管理', NULL, NULL, '1', 600, '1');
INSERT INTO `t_menu` VALUES (604, '/system/admin/**', '/sys/admin', 'SysAdmin', '操作员管理', NULL, NULL, '1', 600, '1');
INSERT INTO `t_menu` VALUES (605, '/system/data/**', '/sys/backup', 'SysData', '备份恢复数据库', NULL, NULL, '1', 600, '1');
INSERT INTO `t_menu` VALUES (606, '/system/init/**', '/sys/init', 'SysInit', '初始化数据库', NULL, NULL, '1', 600, '1');

-- ----------------------------
-- Table structure for t_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_role`;
CREATE TABLE `t_menu_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NULL DEFAULT NULL COMMENT '菜单id',
  `rid` int(11) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 360 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_menu_role
-- ----------------------------
INSERT INTO `t_menu_role` VALUES (291, 201, 1);
INSERT INTO `t_menu_role` VALUES (292, 202, 1);
INSERT INTO `t_menu_role` VALUES (293, 301, 1);
INSERT INTO `t_menu_role` VALUES (294, 302, 1);
INSERT INTO `t_menu_role` VALUES (295, 303, 1);
INSERT INTO `t_menu_role` VALUES (296, 304, 1);
INSERT INTO `t_menu_role` VALUES (297, 305, 1);
INSERT INTO `t_menu_role` VALUES (298, 401, 1);
INSERT INTO `t_menu_role` VALUES (299, 402, 1);
INSERT INTO `t_menu_role` VALUES (300, 403, 1);
INSERT INTO `t_menu_role` VALUES (301, 404, 1);
INSERT INTO `t_menu_role` VALUES (302, 405, 1);
INSERT INTO `t_menu_role` VALUES (303, 501, 1);
INSERT INTO `t_menu_role` VALUES (304, 502, 1);
INSERT INTO `t_menu_role` VALUES (305, 503, 1);
INSERT INTO `t_menu_role` VALUES (306, 504, 1);
INSERT INTO `t_menu_role` VALUES (307, 601, 1);
INSERT INTO `t_menu_role` VALUES (308, 602, 1);
INSERT INTO `t_menu_role` VALUES (309, 603, 1);
INSERT INTO `t_menu_role` VALUES (310, 604, 1);
INSERT INTO `t_menu_role` VALUES (311, 605, 1);
INSERT INTO `t_menu_role` VALUES (312, 606, 1);
INSERT INTO `t_menu_role` VALUES (326, 201, 3);
INSERT INTO `t_menu_role` VALUES (327, 301, 3);
INSERT INTO `t_menu_role` VALUES (328, 302, 3);
INSERT INTO `t_menu_role` VALUES (329, 303, 3);
INSERT INTO `t_menu_role` VALUES (330, 304, 3);
INSERT INTO `t_menu_role` VALUES (331, 305, 3);
INSERT INTO `t_menu_role` VALUES (332, 401, 3);
INSERT INTO `t_menu_role` VALUES (333, 403, 3);
INSERT INTO `t_menu_role` VALUES (334, 404, 3);
INSERT INTO `t_menu_role` VALUES (335, 501, 3);
INSERT INTO `t_menu_role` VALUES (336, 502, 3);
INSERT INTO `t_menu_role` VALUES (337, 503, 3);
INSERT INTO `t_menu_role` VALUES (338, 504, 3);
INSERT INTO `t_menu_role` VALUES (339, 604, 3);
INSERT INTO `t_menu_role` VALUES (348, 201, 2);
INSERT INTO `t_menu_role` VALUES (349, 301, 2);
INSERT INTO `t_menu_role` VALUES (350, 305, 2);
INSERT INTO `t_menu_role` VALUES (351, 401, 2);
INSERT INTO `t_menu_role` VALUES (352, 404, 2);
INSERT INTO `t_menu_role` VALUES (353, 501, 2);
INSERT INTO `t_menu_role` VALUES (354, 601, 2);
INSERT INTO `t_menu_role` VALUES (356, 606, 7);
INSERT INTO `t_menu_role` VALUES (360, 501, 4);

-- ----------------------------
-- Table structure for t_nation
-- ----------------------------
DROP TABLE IF EXISTS `t_nation`;
CREATE TABLE `t_nation`  (
  `id` int(11) NOT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_nation
-- ----------------------------
INSERT INTO `t_nation` VALUES (1, '汉族');
INSERT INTO `t_nation` VALUES (2, '苗族');

-- ----------------------------
-- Table structure for t_oplog
-- ----------------------------
DROP TABLE IF EXISTS `t_oplog`;
CREATE TABLE `t_oplog`  (
  `id` int(11) NOT NULL,
  `add_date` datetime NULL DEFAULT NULL COMMENT '添加日期',
  `operate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作内容',
  `adminid` int(11) NULL DEFAULT NULL COMMENT '操作员ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_oplog
-- ----------------------------

-- ----------------------------
-- Table structure for t_politics_status
-- ----------------------------
DROP TABLE IF EXISTS `t_politics_status`;
CREATE TABLE `t_politics_status`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '政治面貌',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_politics_status
-- ----------------------------
INSERT INTO `t_politics_status` VALUES (1, '群众');
INSERT INTO `t_politics_status` VALUES (2, '党员');

-- ----------------------------
-- Table structure for t_position
-- ----------------------------
DROP TABLE IF EXISTS `t_position`;
CREATE TABLE `t_position`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `create_date` date NULL DEFAULT NULL COMMENT '创建时间',
  `enabled` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_position
-- ----------------------------
INSERT INTO `t_position` VALUES (1, '技术总监', '2021-02-14', 'false');
INSERT INTO `t_position` VALUES (2, '运营总监', '2021-02-14', 'true');
INSERT INTO `t_position` VALUES (14, '董事长', '2021-04-17', 'true');
INSERT INTO `t_position` VALUES (15, '总经理', '2021-04-17', 'true');
INSERT INTO `t_position` VALUES (16, '秘书', '2021-04-17', 'true');
INSERT INTO `t_position` VALUES (17, '测试1', '2021-04-17', 'true');
INSERT INTO `t_position` VALUES (18, '测试2', '2021-04-17', 'false');
INSERT INTO `t_position` VALUES (20, '测试4', '2021-04-17', 'true');
INSERT INTO `t_position` VALUES (25, 'test001', '2021-04-23', 'true');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `name_zh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'ROLE_admin', '系统管理员');
INSERT INTO `t_role` VALUES (2, 'ROLE_test', '测试');
INSERT INTO `t_role` VALUES (3, 'ROLE_person', '人力资源用户');
INSERT INTO `t_role` VALUES (4, 'ROLE_dev', '开发者');

-- ----------------------------
-- Table structure for t_salary
-- ----------------------------
DROP TABLE IF EXISTS `t_salary`;
CREATE TABLE `t_salary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `basicSalary` decimal(10, 2) NULL DEFAULT NULL COMMENT '基本工资',
  `bonus` decimal(10, 0) NULL DEFAULT NULL COMMENT '奖金',
  `lunchSalary` decimal(10, 2) NULL DEFAULT NULL COMMENT '午餐补助',
  `trafficSalary` decimal(10, 2) NULL DEFAULT NULL COMMENT '交通补助',
  `allSalary` decimal(10, 2) NULL DEFAULT NULL COMMENT '应发工资',
  `pensionBase` decimal(10, 0) NULL DEFAULT NULL COMMENT '养老金基数',
  `pensionPer` decimal(10, 0) NULL DEFAULT NULL COMMENT '养老金比率',
  `createDate` datetime NULL DEFAULT NULL COMMENT '启用时间',
  `medicalBase` decimal(10, 0) NULL DEFAULT NULL COMMENT '医疗基数',
  `medicalPer` decimal(10, 0) NULL DEFAULT NULL COMMENT '医疗保险比率',
  `accumulationFundBase` decimal(10, 0) NULL DEFAULT NULL COMMENT '公积金基数',
  `accumulationFundPer` decimal(10, 0) NULL DEFAULT NULL COMMENT '公积金比率',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_salary
-- ----------------------------
INSERT INTO `t_salary` VALUES (1, 4200.00, 700, 50.00, 50.00, 6000.00, 2, 5, '2021-03-28 18:14:43', 1, 5, 5, 5, '实施');
INSERT INTO `t_salary` VALUES (2, 4200.00, 700, 50.00, 50.00, 5000.00, 2, 5, '2021-03-28 18:15:56', 1, 5, 5, 5, '数据分析');
INSERT INTO `t_salary` VALUES (3, 4200.00, 700, 50.00, 50.00, 5000.00, 2, 5, '2021-03-28 18:16:08', 1, 5, 5, 5, '售前技术支持');
INSERT INTO `t_salary` VALUES (4, 4200.00, 700, 50.00, 50.00, 5000.00, 2, 5, '2021-03-28 18:16:11', 1, 5, 5, 5, '项目经理');
INSERT INTO `t_salary` VALUES (5, 3000.00, 2000, 200.00, 200.00, NULL, 3000, 0, '2021-04-22 10:51:50', 3000, 0, 3000, 0, '技术经理');
INSERT INTO `t_salary` VALUES (6, 4000.00, 600, 200.00, 200.00, NULL, 4000, 0, '2021-04-22 10:53:40', 4000, 0, 4000, 0, '销售');
INSERT INTO `t_salary` VALUES (8, 4200.00, 700, 50.00, 50.00, NULL, 2, 5, '2021-04-22 13:04:21', 1, 5, 5, 5, '售后技术支持');
INSERT INTO `t_salary` VALUES (9, 2000.00, 200, 200.00, 200.00, NULL, 200, 200, '2021-04-22 13:11:14', 200, 200, 200, 200, '硬件工程师');

-- ----------------------------
-- Table structure for t_sys_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_msg`;
CREATE TABLE `t_sys_msg`  (
  `id` int(11) NOT NULL,
  `msgid` int(11) NULL DEFAULT NULL COMMENT '消息id',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0表示群发消息',
  `sendid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '这条消息是给谁的',
  `state` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 未读 1 已读',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_sys_msg
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_msg_content
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_msg_content`;
CREATE TABLE `t_sys_msg_content`  (
  `id` int(11) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_sys_msg_content
-- ----------------------------

-- ----------------------------
-- Procedure structure for addDep
-- ----------------------------
DROP PROCEDURE IF EXISTS `addDep`;
delimiter ;;
CREATE PROCEDURE `addDep`(in depName VARCHAR(32),in parentId int,in enabled boolean,out result int,out result2 int)
BEGIN
	#定义变量
	DECLARE did int;
	DECLARE pDepPath VARCHAR(64);
	#插入部门记录
	INSERT INTO t_department set name = depName,parent_id = parentId,enabled = enabled;
	#查询插入受影响的行数 赋值给result
	SELECT ROW_COUNT() into result;
	#查询插入的最后一个主键id 赋值给did
	SELECT LAST_INSERT_ID() into did;
	#设置result2的值为did
	set result2 = did;
	#查询路径(t_department表的id等于传入的parentId) 赋值给pDepPath
	select dep_path into pDepPath FROM t_department where id=parentId;
	#拼接depPath
	UPDATE t_department set dep_path = CONCAT(pDepPath,'.',did) WHERE id=did;
	#当id为parentId时,将is_parent设置为1
	UPDATE t_department set is_parent = 1 WHERE id = parentId;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for deleteDep
-- ----------------------------
DROP PROCEDURE IF EXISTS `deleteDep`;
delimiter ;;
CREATE PROCEDURE `deleteDep`(in did int,out result int)
BEGIN
	DECLARE ecount int;
	DECLARE pid int;
	DECLARE pcount int;
	DECLARE a int;
	
	#查询当前的部门id是否是子部门
	SELECT count(1) INTO a from t_department where id = did and is_parent = 0;
	
	if a = 0 THEN 
		set result = -2;
	ELSE
		#查询该部门下是否有员工，如果有员工，则result返回-1
		SELECT count(1) into ecount from t_employee where department_id = did;
	
		if ecount > 0 then
			set result = -1;
		else
			select parent_id into pid from t_department WHERE id = did;
		
			DELETE from t_department WHERE id=did and is_parent = 0;
			
			select ROW_COUNT() into result;
			
			#执行完删除操作后，判断删除部门的上级部门是否还存在其他子部门，如果不存在，则将is_parent设置为false
			SELECT count(1) into pcount from t_department where parent_id = pid;
			
			if pcount=0 then
				update t_department set is_parent = 0 where id=pid;
			end if;
		END if;
	END if;
	
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for GreetWorld
-- ----------------------------
DROP PROCEDURE IF EXISTS `GreetWorld`;
delimiter ;;
CREATE PROCEDURE `GreetWorld`()
SELECT CONCAT(@gretting,'world')
;;
delimiter ;

-- ----------------------------
-- Procedure structure for p1
-- ----------------------------
DROP PROCEDURE IF EXISTS `p1`;
delimiter ;;
CREATE PROCEDURE `p1`()
SET @last_procedure ='p1'
;;
delimiter ;

-- ----------------------------
-- Procedure structure for p2
-- ----------------------------
DROP PROCEDURE IF EXISTS `p2`;
delimiter ;;
CREATE PROCEDURE `p2`()
SELECT CONCAT('last_procedure',@last_procedure)
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
