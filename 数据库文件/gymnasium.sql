/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : gymnasium

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 15/11/2025 16:20:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` int NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `course_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '课程名称',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '封面图',
  `teacher_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '授课教师',
  `course_hour` int NULL DEFAULT NULL COMMENT '课时',
  `course_details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '课程详情',
  `course_price` decimal(18, 2) NULL DEFAULT NULL COMMENT '课程价格',
  `teacher_id` int NULL DEFAULT NULL COMMENT '教师',
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '篮球基础班', 'http://127.0.0.1:9000/gym/gym_1762433442603.jpg', '张明', 24, '<p>基础运球、投篮姿势与步伐训练。</p>', 399.00, 4);
INSERT INTO `course` VALUES (2, '篮球进阶班', 'http://127.0.0.1:9000/gym/gym_1762433470383.jpg', '张明', 36, '<p>战术演练与对抗训练，提升比赛能力。</p>', 699.00, 4);
INSERT INTO `course` VALUES (3, '足球技巧强化', 'http://127.0.0.1:9000/gym/gym_1762433479109.jpg', '王峰', 30, '<p>控球、传球与射门专项训练。</p>', 599.00, 6);
INSERT INTO `course` VALUES (4, '羽毛球班', 'http://127.0.0.1:9000/gym/gym_1762433490929.jpg', '赵柳', 24, '<p>步伐、发接发与杀球技术提升。</p>', 499.00, 7);
INSERT INTO `course` VALUES (5, '网球入门', 'http://127.0.0.1:9000/gym/gym_1762433498450.jpg', '张明', 18, '<p>正反手击球、发球基础与规则讲解。</p>', 459.00, 4);
INSERT INTO `course` VALUES (6, '力量训练基础', 'http://127.0.0.1:9000/gym/gym_1762433506814.jpg', '陈亮', 16, '<p>器械使用安全与基础增肌训练。</p>', 399.00, 8);
INSERT INTO `course` VALUES (7, '有氧燃脂', 'http://127.0.0.1:9000/gym/gym_1762433519425.jpg', '周洁', 12, '<p>间歇有氧与心肺耐力提升课程。</p>', 299.00, 9);
INSERT INTO `course` VALUES (8, '瑜伽舒展', 'http://127.0.0.1:9000/gym/gym_1762433530659.jpg', '林娜', 20, '<p>缓解压力与体态改善的基础序列。</p>', 359.00, 10);

-- ----------------------------
-- Table structure for course_copy1
-- ----------------------------
DROP TABLE IF EXISTS `course_copy1`;
CREATE TABLE `course_copy1`  (
  `course_id` int NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `course_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '课程名称',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '封面图',
  `teacher_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '授课教师',
  `course_hour` int NULL DEFAULT NULL COMMENT '课时',
  `course_details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '课程详情',
  `course_price` decimal(18, 2) NULL DEFAULT NULL COMMENT '课程价格',
  `teacher_id` int NULL DEFAULT NULL COMMENT '教师',
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_copy1
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名称',
  `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '价格',
  `store` int NULL DEFAULT NULL COMMENT '库存',
  `image` varchar(220) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  `details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '描述',
  `unit` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单位',
  `specs` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '规格',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '矿泉水', 3.00, 500, 'http://127.0.0.1:9000/gym/gym_1762433588889.jpg', '<p>纯净饮用水，运动后及时补水。</p>', '瓶', '550ml/瓶');
INSERT INTO `goods` VALUES (2, '运动毛巾', 15.00, 200, 'http://127.0.0.1:9000/gym/gym_1762433599114.jpg', '<p>吸汗速干材质，舒适耐用。</p>', '条', '95cm*25cm');
INSERT INTO `goods` VALUES (3, '蛋白棒', 12.00, 300, 'http://127.0.0.1:9000/gym/gym_1762433606681.jpg', '<p>高蛋白能量补给，低糖配方。</p>', '根', '60g/根');
INSERT INTO `goods` VALUES (4, '运动护腕', 25.00, 120, 'http://127.0.0.1:9000/gym/gym_1762433617726.jpg', '<p>加压护腕，保护关节，可调节。</p>', '对', '均码/可调');
INSERT INTO `goods` VALUES (5, '篮球', 120.00, 50, 'http://127.0.0.1:9000/gym/gym_1762433625446.jpg', '<p>室内外通用7号比赛用球。</p>', '个', '7号球');
INSERT INTO `goods` VALUES (6, '钢丝跳绳', 18.00, 180, 'http://127.0.0.1:9000/gym/gym_1762433633439.jpg', '<p>轴承手柄，旋转顺畅，长度可调。</p>', '条', '3m/钢丝绳');

-- ----------------------------
-- Table structure for goods_order
-- ----------------------------
DROP TABLE IF EXISTS `goods_order`;
CREATE TABLE `goods_order`  (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `goods_id` int NULL DEFAULT NULL COMMENT '商品id',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名称',
  `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '价格',
  `image` varchar(220) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  `details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '描述',
  `unit` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '单位',
  `specs` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '规格',
  `num` int NULL DEFAULT NULL COMMENT '数量',
  `total_price` decimal(18, 2) NULL DEFAULT NULL COMMENT '总价',
  `control_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '下单时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_order
-- ----------------------------
INSERT INTO `goods_order` VALUES (1, 1, '矿泉水', 3.00, 'http://127.0.0.1:9000/gym/gym_1762433588889.jpg', '<p>纯净饮用水。</p>', '瓶', '550ml/瓶', 4, 12.00, 'admin', '2025-07-12 10:20:00');
INSERT INTO `goods_order` VALUES (2, 2, '运动毛巾', 15.00, 'http://127.0.0.1:9000/gym/gym_1762433599114.jpg', '<p>吸汗速干。</p>', '条', '95cm*25cm', 2, 30.00, 'admin', '2025-07-12 10:25:00');
INSERT INTO `goods_order` VALUES (3, 3, '蛋白棒', 12.00, 'http://127.0.0.1:9000/gym/gym_1762433606681.jpg', '<p>高蛋白补给。</p>', '根', '60g/根', 5, 60.00, '张明', '2025-07-12 11:00:00');
INSERT INTO `goods_order` VALUES (4, 4, '运动护腕', 25.00, 'http://127.0.0.1:9000/gym/gym_1762433617726.jpg', '<p>加压保护。</p>', '对', '均码/可调', 1, 25.00, 'admin', '2025-07-12 11:15:00');
INSERT INTO `goods_order` VALUES (5, 5, '篮球', 120.00, 'http://127.0.0.1:9000/gym/gym_1762433625446.jpg', '<p>7号比赛用球。</p>', '个', '7号球', 1, 120.00, 'admin1', '2025-07-12 12:00:00');
INSERT INTO `goods_order` VALUES (6, 1, '矿泉水', 3.00, 'http://127.0.0.1:9000/gym/gym_1762433588889.jpg', '<p>纯净饮用水。</p>', '瓶', '550ml/瓶', 6, 18.00, '张明', '2025-07-13 09:30:00');
INSERT INTO `goods_order` VALUES (7, 6, '钢丝跳绳', 18.00, 'http://127.0.0.1:9000/gym/gym_1762433633439.jpg', '<p>旋转顺畅。</p>', '条', '3m/钢丝绳', 2, 36.00, 'admin', '2025-07-13 09:45:00');
INSERT INTO `goods_order` VALUES (8, 3, '蛋白棒', 12.00, 'http://127.0.0.1:9000/gym/gym_1762433606681.jpg', '<p>高蛋白补给。</p>', '根', '60g/根', 3, 36.00, 'admin', '2025-07-13 10:00:00');
INSERT INTO `goods_order` VALUES (9, 2, '运动毛巾', 15.00, 'http://127.0.0.1:9000/gym/gym_1762433599114.jpg', '<p>吸汗速干。</p>', '条', '95cm*25cm', 1, 15.00, 'admin1', '2025-07-13 10:10:00');
INSERT INTO `goods_order` VALUES (10, 1, '矿泉水', 3.00, 'http://127.0.0.1:9000/gym/gym_1762433588889.jpg', '<p>纯净饮用水。</p>', '瓶', '550ml/瓶', 10, 30.00, 'admin', '2025-07-13 10:20:00');
INSERT INTO `goods_order` VALUES (11, 2, '运动毛巾', 15.00, 'http://127.0.0.1:9000/gym/gym_1762433599114.jpg', '<p>吸汗速干。</p>', '条', '95cm*25cm', 3, 45.00, 'admin', '2025-07-14 09:15:00');
INSERT INTO `goods_order` VALUES (12, 4, '运动护腕', 25.00, 'http://127.0.0.1:9000/gym/gym_1762433617726.jpg', '<p>加压保护。</p>', '对', '均码/可调', 2, 50.00, '张明', '2025-07-14 10:02:00');
INSERT INTO `goods_order` VALUES (13, 5, '篮球', 120.00, 'http://127.0.0.1:9000/gym/gym_1762433625446.jpg', '<p>7号比赛用球。</p>', '个', '7号球', 1, 120.00, 'admin1', '2025-07-14 11:30:00');
INSERT INTO `goods_order` VALUES (14, 6, '钢丝跳绳', 18.00, 'http://127.0.0.1:9000/gym/gym_1762433633439.jpg', '<p>旋转顺畅。</p>', '条', '3m/钢丝绳', 5, 90.00, 'admin', '2025-07-15 08:55:00');
INSERT INTO `goods_order` VALUES (15, 3, '蛋白棒', 12.00, 'http://127.0.0.1:9000/gym/gym_1762433606681.jpg', '<p>高蛋白补给。</p>', '根', '60g/根', 8, 96.00, '张明', '2025-07-15 12:10:00');
INSERT INTO `goods_order` VALUES (16, 1, '矿泉水', 3.00, 'http://127.0.0.1:9000/gym/gym_1762433588889.jpg', '<p>纯净饮用水。</p>', '瓶', '550ml/瓶', 12, 36.00, 'admin', '2025-07-16 09:40:00');

-- ----------------------------
-- Table structure for lost
-- ----------------------------
DROP TABLE IF EXISTS `lost`;
CREATE TABLE `lost`  (
  `lost_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `lost_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名称',
  `found_time` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '捡到时间',
  `found_addres` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '捡到地址',
  `found_person` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '捡到人',
  `found_phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '捡到人电话',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT ' 认领状态 0：未认领 1：已认领',
  `lost_person` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '认领人',
  PRIMARY KEY (`lost_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of lost
-- ----------------------------
INSERT INTO `lost` VALUES (1, '钱包', '2025-06-28', '综合馆大厅', '李四', '18787171906', '0', NULL);
INSERT INTO `lost` VALUES (2, '学生证', '2025-06-30', '篮球馆看台', '张明', '18687110001', '1', '王五');
INSERT INTO `lost` VALUES (3, '雨伞', '2025-07-01', '游泳馆更衣室', '赵柳', '18687110002', '0', NULL);
INSERT INTO `lost` VALUES (4, '钥匙串', '2025-07-02', '室外跑道入口', '周洁', '18687110003', '1', '张三');
INSERT INTO `lost` VALUES (5, '水杯', '2025-07-03', '健身房器械区', '陈亮', '18687110004', '0', NULL);
INSERT INTO `lost` VALUES (6, '眼镜', '2025-07-04', '羽毛球馆2号场', '王峰', '18687110005', '1', '李丽');

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '器材id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '器材名称',
  `num_total` int NULL DEFAULT NULL COMMENT '数量',
  `details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES (1, '跑步机', 12, '电动跑步机，含心率监测');
INSERT INTO `material` VALUES (2, '杠铃套装', 8, '含杠铃杆与片，重量可调');
INSERT INTO `material` VALUES (3, '哑铃对', 20, '2kg-20kg自由重量');
INSERT INTO `material` VALUES (4, '瑜伽垫', 30, '防滑加厚瑜伽垫');
INSERT INTO `material` VALUES (5, '篮球', 40, '7号球，室内外通用');
INSERT INTO `material` VALUES (6, '动感单车', 6, '商用级动感单车');
INSERT INTO `material` VALUES (7, '龙门架', 2, '');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `member_id` int NOT NULL AUTO_INCREMENT COMMENT '会员id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `birthday` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生日',
  `height` int NULL DEFAULT NULL COMMENT '身高',
  `weight` int NULL DEFAULT NULL COMMENT '体重',
  `waist` int NULL DEFAULT NULL COMMENT '腰围',
  `join_time` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '加入时间',
  `end_time` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '到期时间',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '会员卡号',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '状态 1：启用  0：停用',
  `money` decimal(18, 2) NULL DEFAULT 0.00 COMMENT '充值金额',
  `card_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '会员类型',
  `card_day` int NULL DEFAULT NULL COMMENT '天数',
  `price` decimal(18, 2) NULL DEFAULT 0.00 COMMENT '会员价格',
  `is_account_non_expired` tinyint NULL DEFAULT NULL COMMENT '帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` tinyint NULL DEFAULT NULL COMMENT '帐户是否被锁定(1 未锁定，0已锁定)',
  `is_credentials_non_expired` tinyint NULL DEFAULT NULL COMMENT '密码是否过期(1 未过期，0已过期)',
  `is_enabled` tinyint NULL DEFAULT NULL COMMENT '帐户是否可用(1 可用，0 删除用户)',
  PRIMARY KEY (`member_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 256 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, '张雨薇', '1', '18687110001', 22, '2003-01-15', 165, 54, 64, '2024-10-05', '2024-11-04', 'MBR0001', '$2a$10$RuSq4KVFY64cG4au6v6viego5l694uJiNBpFEiaJ9pkhtHq73/LQ2', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (2, '王子豪', '0', '18687110002', 23, '2002-02-12', 178, 72, 80, '2024-10-06', '2024-10-13', 'MBR0002', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (3, '李佳怡', '1', '18687110003', 22, '2003-03-21', 162, 50, 61, '2024-10-07', '2024-11-06', 'MBR0003', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (4, '刘子墨', '0', '18687110004', 23, '2002-04-18', 175, 69, 79, '2024-10-08', '2025-10-08', 'MBR0004', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (5, '陈俊杰', '0', '18687110005', 22, '2003-05-11', 180, 75, 85, '2024-10-09', '2024-10-10', 'MBR0005', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (6, '杨诗涵', '1', '18687110006', 21, '2004-06-22', 166, 52, 63, '2024-10-10', '2024-11-09', 'MBR0006', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (7, '黄嘉怡', '1', '18687110007', 22, '2003-07-08', 164, 51, 61, '2024-10-11', '2024-10-18', 'MBR0007', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (8, '赵志强', '0', '18687110008', 23, '2002-08-02', 182, 80, 88, '2024-10-12', '2025-10-12', 'MBR0008', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (9, '周雪妍', '1', '18687110009', 21, '2004-09-09', 168, 55, 66, '2024-10-13', '2024-10-14', 'MBR0009', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (10, '吴思琪', '1', '18687110010', 20, '2005-10-20', 160, 49, 60, '2024-10-14', '2024-11-13', 'MBR0010', '$2a$10$mmd6JZxImeJ8tMx3lwQFBudG.fxwEVZZrLQzXRqca/EJR6AkucH8q', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (11, '徐家豪', '0', '18687110011', 21, '2004-11-18', 176, 73, 82, '2024-10-15', '2024-10-22', 'MBR0011', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (12, '孙志轩', '0', '18687110012', 23, '2002-12-28', 181, 78, 87, '2024-10-16', '2025-10-16', 'MBR0012', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (13, '马思远', '0', '18687110013', 22, '2003-01-08', 174, 68, 78, '2024-10-17', '2024-11-16', 'MBR0013', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (14, '朱俊逸', '0', '18687110014', 22, '2003-02-10', 179, 74, 84, '2024-10-18', '2024-10-19', 'MBR0014', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (15, '胡昕悦', '1', '18687110015', 21, '2004-03-13', 167, 54, 64, '2024-10-19', '2024-11-18', 'MBR0015', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (16, '郭晓彤', '1', '18687110016', 22, '2003-04-05', 163, 50, 61, '2024-10-20', '2024-10-27', 'MBR0016', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (17, '何语嫣', '1', '18687110017', 23, '2002-05-22', 170, 56, 66, '2024-10-21', '2025-10-21', 'MBR0017', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (18, '高怡然', '1', '18687110018', 20, '2005-06-26', 161, 49, 59, '2024-10-22', '2024-11-21', 'MBR0018', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (19, '林亦菲', '1', '18687110019', 22, '2003-07-14', 165, 52, 62, '2024-10-23', '2024-10-30', 'MBR0019', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (20, '罗婉婷', '1', '18687110020', 23, '2002-08-21', 168, 55, 66, '2024-10-24', '2025-10-24', 'MBR0020', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (21, '郑皓宇', '0', '18687110021', 23, '2002-09-02', 180, 76, 85, '2024-10-25', '2024-11-24', 'MBR0021', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (22, '梁皓轩', '0', '18687110022', 22, '2003-10-03', 177, 72, 82, '2024-10-26', '2024-11-02', 'MBR0022', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (23, '谢嘉琪', '1', '18687110023', 21, '2004-11-27', 164, 51, 61, '2024-10-27', '2025-10-27', 'MBR0023', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (24, '宋善琦', '1', '18687110024', 21, '2004-12-02', 163, 50, 61, '2024-10-28', '2024-11-27', 'MBR0024', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (25, '唐依然', '1', '18687110025', 22, '2003-01-06', 165, 52, 62, '2024-10-29', '2024-11-05', 'MBR0025', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (26, '许芊墨', '1', '18687110026', 23, '2002-02-22', 169, 55, 65, '2024-10-30', '2025-10-30', 'MBR0026', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (27, '韩梦瑶', '1', '18687110027', 22, '2003-03-10', 166, 52, 62, '2024-10-31', '2024-12-01', 'MBR0027', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (28, '冯晓曼', '1', '18687110028', 21, '2004-04-18', 162, 50, 60, '2024-11-01', '2024-11-08', 'MBR0028', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (29, '邓俊辰', '0', '18687110029', 23, '2002-05-29', 179, 74, 84, '2024-11-02', '2025-11-02', 'MBR0029', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (30, '曹雨婷', '1', '18687110030', 22, '2003-06-06', 165, 52, 62, '2024-11-03', '2024-12-03', 'MBR0030', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (31, '彭晓雪', '1', '18687110031', 21, '2004-07-07', 164, 51, 61, '2024-11-04', '2024-11-05', 'MBR0031', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (32, '曾宇航', '0', '18687110032', 21, '2004-08-25', 175, 69, 79, '2024-11-05', '2024-11-12', 'MBR0032', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (33, '苏俊凯', '0', '18687110033', 22, '2003-09-16', 178, 73, 82, '2024-11-06', '2024-12-06', 'MBR0033', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (34, '潘嘉欣', '1', '18687110034', 20, '2005-10-08', 160, 49, 60, '2024-11-07', '2024-11-14', 'MBR0034', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (35, '田文博', '0', '18687110035', 22, '2003-11-19', 180, 76, 85, '2024-11-08', '2025-11-08', 'MBR0035', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (36, '董子瑜', '1', '18687110036', 22, '2003-12-12', 166, 52, 62, '2024-11-09', '2024-12-09', 'MBR0036', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (37, '袁欣妍', '1', '18687110037', 21, '2004-01-17', 163, 50, 60, '2024-11-10', '2024-11-17', 'MBR0037', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (38, '于怡静', '1', '18687110038', 23, '2002-02-04', 169, 55, 66, '2024-11-11', '2025-11-11', 'MBR0038', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (39, '叶语汐', '1', '18687110039', 21, '2004-03-29', 165, 52, 62, '2024-11-12', '2024-12-12', 'MBR0039', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (40, '余语桐', '1', '18687110040', 22, '2003-04-14', 166, 52, 62, '2024-11-13', '2024-11-20', 'MBR0040', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (41, '沈可欣', '1', '18687110041', 23, '2002-05-02', 168, 55, 66, '2024-11-14', '2025-11-14', 'MBR0041', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (42, '夏可唯', '1', '18687110042', 21, '2004-06-06', 164, 51, 61, '2024-11-15', '2024-12-15', 'MBR0042', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (43, '钟芷晴', '1', '18687110043', 22, '2003-07-19', 165, 52, 62, '2024-11-16', '2024-11-23', 'MBR0043', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (44, '崔语彤', '1', '18687110044', 23, '2002-08-22', 170, 56, 67, '2024-11-17', '2025-11-17', 'MBR0044', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (45, '姚诗妍', '1', '18687110045', 21, '2004-09-01', 162, 50, 60, '2024-11-18', '2024-12-18', 'MBR0045', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (46, '陆婉茹', '1', '18687110046', 22, '2003-10-10', 165, 52, 62, '2024-11-19', '2024-11-26', 'MBR0046', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (47, '汪泽宇', '0', '18687110047', 23, '2002-11-11', 180, 76, 85, '2024-11-20', '2025-11-20', 'MBR0047', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (48, '石紫涵', '1', '18687110048', 22, '2003-12-02', 166, 52, 62, '2024-11-21', '2024-12-21', 'MBR0048', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (49, '贾一诺', '1', '18687110049', 21, '2004-01-28', 162, 50, 60, '2024-11-22', '2024-11-29', 'MBR0049', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (50, '熊心悦', '1', '18687110050', 23, '2002-02-16', 170, 56, 67, '2024-11-23', '2025-11-23', 'MBR0050', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (51, '白景程', '0', '18687110051', 23, '2002-03-03', 178, 73, 82, '2024-11-24', '2024-12-24', 'MBR0051', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (52, '邹景行', '0', '18687110052', 22, '2003-04-07', 176, 71, 81, '2024-11-25', '2024-12-02', 'MBR0052', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (53, '孟晓阳', '0', '18687110053', 21, '2004-05-12', 174, 68, 78, '2024-11-26', '2025-11-26', 'MBR0053', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (54, '秦思玥', '1', '18687110054', 21, '2004-06-15', 163, 50, 60, '2024-11-27', '2024-12-27', 'MBR0054', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (55, '薛楚涵', '1', '18687110055', 22, '2003-07-20', 165, 52, 62, '2024-11-28', '2024-12-05', 'MBR0055', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (56, '阎语馨', '1', '18687110056', 23, '2002-08-28', 169, 55, 66, '2024-11-29', '2025-11-29', 'MBR0056', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (57, '侯绮梦', '1', '18687110057', 21, '2004-09-22', 162, 50, 60, '2024-11-30', '2024-12-30', 'MBR0057', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (58, '江芷若', '1', '18687110058', 22, '2003-10-30', 165, 52, 62, '2024-12-01', '2024-12-08', 'MBR0058', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (59, '史沐晴', '1', '18687110059', 23, '2002-11-25', 170, 56, 67, '2024-12-02', '2025-12-02', 'MBR0059', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (60, '傅若溪', '1', '18687110060', 22, '2003-12-06', 166, 52, 62, '2024-12-03', '2025-01-02', 'MBR0060', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (61, '汤锦程', '0', '18687110061', 23, '2002-01-09', 179, 74, 84, '2024-12-04', '2024-12-05', 'MBR0061', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (62, '魏子豪', '0', '18687110062', 22, '2003-02-02', 176, 71, 81, '2024-12-05', '2024-12-12', 'MBR0062', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (63, '方雨薇', '1', '18687110063', 22, '2003-03-13', 165, 52, 62, '2024-12-06', '2024-12-13', 'MBR0063', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (64, '廖佳怡', '1', '18687110064', 22, '2003-04-24', 166, 52, 62, '2024-12-07', '2025-01-06', 'MBR0064', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (65, '赖子墨', '0', '18687110065', 23, '2002-05-05', 175, 69, 79, '2024-12-08', '2025-12-08', 'MBR0065', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (66, '邱俊杰', '0', '18687110066', 22, '2003-06-09', 178, 73, 82, '2024-12-09', '2024-12-10', 'MBR0066', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (67, '任诗涵', '1', '18687110067', 21, '2004-07-11', 166, 52, 62, '2024-12-10', '2025-01-09', 'MBR0067', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (68, '钟嘉怡', '1', '18687110068', 22, '2003-08-18', 164, 51, 61, '2024-12-11', '2024-12-18', 'MBR0068', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (69, '童志强', '0', '18687110069', 23, '2002-09-09', 182, 80, 88, '2024-12-12', '2025-12-12', 'MBR0069', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (70, '关雪妍', '1', '18687110070', 21, '2004-10-01', 168, 55, 66, '2024-12-13', '2025-01-12', 'MBR0070', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (71, '蒋思琪', '1', '18687110071', 20, '2005-01-22', 160, 49, 60, '2024-12-14', '2024-12-21', 'MBR0071', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (72, '毛家豪', '0', '18687110072', 21, '2004-02-13', 176, 71, 81, '2024-12-15', '2025-12-15', 'MBR0072', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (73, '谭志轩', '0', '18687110073', 23, '2002-03-05', 179, 74, 84, '2024-12-16', '2024-12-17', 'MBR0073', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (74, '姜思远', '0', '18687110074', 22, '2003-04-19', 174, 68, 78, '2024-12-17', '2025-01-16', 'MBR0074', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (75, '史俊逸', '0', '18687110075', 22, '2003-06-23', 178, 73, 82, '2024-12-18', '2024-12-25', 'MBR0075', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (76, '葛昕悦', '1', '18687110076', 21, '2004-07-08', 167, 54, 64, '2024-12-19', '2025-12-19', 'MBR0076', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (77, '倪晓彤', '1', '18687110077', 22, '2003-08-09', 163, 50, 60, '2024-12-20', '2025-01-19', 'MBR0077', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (78, '滕语嫣', '1', '18687110078', 23, '2002-09-12', 170, 56, 67, '2024-12-21', '2024-12-28', 'MBR0078', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (79, '仲怡然', '1', '18687110079', 20, '2005-01-05', 161, 49, 59, '2024-12-22', '2025-12-22', 'MBR0079', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (80, '管亦菲', '1', '18687110080', 22, '2003-02-28', 165, 52, 62, '2024-12-23', '2025-01-22', 'MBR0080', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (81, '程婉婷', '1', '18687110081', 21, '2004-03-06', 163, 50, 60, '2024-12-24', '2025-01-23', 'MBR0081', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (82, '尹皓宇', '0', '18687110082', 23, '2002-04-02', 180, 76, 85, '2024-12-25', '2025-12-25', 'MBR0082', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (83, '岳皓轩', '0', '18687110083', 22, '2003-05-05', 177, 72, 82, '2024-12-26', '2024-12-27', 'MBR0083', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (84, '蔡嘉琪', '1', '18687110084', 21, '2004-06-08', 164, 51, 61, '2024-12-27', '2025-01-26', 'MBR0084', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (85, '古善琦', '1', '18687110085', 22, '2003-07-12', 165, 52, 62, '2024-12-28', '2025-01-04', 'MBR0085', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (86, '戴依然', '1', '18687110086', 23, '2002-08-01', 170, 56, 67, '2024-12-29', '2025-12-29', 'MBR0086', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (87, '包芊墨', '1', '18687110087', 22, '2003-09-17', 165, 52, 62, '2024-12-30', '2025-01-29', 'MBR0087', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (88, '裴梦瑶', '1', '18687110088', 22, '2003-10-10', 166, 52, 62, '2024-12-31', '2025-01-07', 'MBR0088', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (89, '盛晓曼', '1', '18687110089', 21, '2004-11-11', 162, 50, 60, '2025-01-01', '2026-01-01', 'MBR0089', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (90, '冉俊辰', '0', '18687110090', 23, '2002-01-20', 179, 74, 84, '2025-01-02', '2025-02-01', 'MBR0090', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (91, '庄雨婷', '1', '18687110091', 22, '2003-02-15', 165, 52, 62, '2025-01-03', '2025-01-10', 'MBR0091', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (92, '吕晓雪', '1', '18687110092', 21, '2004-03-23', 164, 51, 61, '2025-01-04', '2026-01-04', 'MBR0092', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (93, '严宇航', '0', '18687110093', 21, '2004-04-04', 175, 69, 79, '2025-01-05', '2025-02-04', 'MBR0093', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (94, '牛俊凯', '0', '18687110094', 22, '2003-05-25', 178, 73, 82, '2025-01-06', '2025-01-13', 'MBR0094', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (95, '金嘉欣', '1', '18687110095', 20, '2005-06-26', 160, 49, 60, '2025-01-07', '2026-01-07', 'MBR0095', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (96, '钱文博', '0', '18687110096', 22, '2003-07-07', 180, 76, 85, '2025-01-08', '2025-02-07', 'MBR0096', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (97, '施子瑜', '1', '18687110097', 22, '2003-08-08', 166, 52, 62, '2025-01-09', '2025-01-16', 'MBR0097', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (98, '童欣妍', '1', '18687110098', 21, '2004-09-09', 163, 50, 60, '2025-01-10', '2026-01-10', 'MBR0098', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (99, '关怡静', '1', '18687110099', 23, '2002-01-30', 169, 55, 66, '2025-01-11', '2025-02-10', 'MBR0099', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (100, '蒋语汐', '1', '18687110100', 21, '2004-02-12', 165, 52, 62, '2025-01-12', '2025-01-19', 'MBR0100', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (101, '毛语桐', '1', '18687110101', 23, '2002-03-18', 170, 56, 67, '2025-01-13', '2026-01-13', 'MBR0101', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (102, '谭可欣', '1', '18687110102', 21, '2004-04-21', 162, 50, 60, '2025-01-14', '2025-02-13', 'MBR0102', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (103, '姜可唯', '1', '18687110103', 22, '2003-05-29', 164, 51, 61, '2025-01-15', '2025-01-22', 'MBR0103', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (104, '史芷晴', '1', '18687110104', 23, '2002-06-06', 170, 56, 67, '2025-01-16', '2026-01-16', 'MBR0104', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (105, '葛语彤', '1', '18687110105', 21, '2004-07-07', 162, 50, 60, '2025-01-17', '2025-02-16', 'MBR0105', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (106, '倪诗妍', '1', '18687110106', 22, '2003-08-08', 165, 52, 62, '2025-01-18', '2025-01-25', 'MBR0106', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (107, '滕婉茹', '1', '18687110107', 23, '2002-09-09', 169, 55, 66, '2025-01-19', '2026-01-19', 'MBR0107', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (108, '仲泽宇', '0', '18687110108', 23, '2002-10-10', 180, 76, 85, '2025-01-20', '2025-02-19', 'MBR0108', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (109, '管紫涵', '1', '18687110109', 22, '2003-11-11', 166, 52, 62, '2025-01-21', '2025-01-28', 'MBR0109', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (110, '程一诺', '1', '18687110110', 23, '2002-12-12', 170, 56, 67, '2025-01-22', '2026-01-22', 'MBR0110', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (111, '庄心悦', '1', '18687110111', 21, '2004-01-01', 163, 50, 60, '2025-01-23', '2025-02-22', 'MBR0111', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (112, '吕景程', '0', '18687110112', 23, '2002-02-02', 178, 73, 82, '2025-01-24', '2025-01-31', 'MBR0112', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (113, '严景行', '0', '18687110113', 22, '2003-03-03', 176, 71, 81, '2025-01-25', '2026-01-25', 'MBR0113', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (114, '牛晓阳', '0', '18687110114', 21, '2004-04-04', 174, 68, 78, '2025-01-26', '2025-02-25', 'MBR0114', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (115, '金思玥', '1', '18687110115', 21, '2004-05-05', 163, 50, 60, '2025-01-27', '2025-02-03', 'MBR0115', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (116, '钱楚涵', '1', '18687110116', 23, '2002-06-06', 170, 56, 67, '2025-01-28', '2026-01-28', 'MBR0116', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (117, '施语馨', '1', '18687110117', 22, '2003-07-07', 165, 52, 62, '2025-01-29', '2025-02-28', 'MBR0117', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (118, '童绮梦', '1', '18687110118', 22, '2003-08-08', 166, 52, 62, '2025-01-30', '2025-02-06', 'MBR0118', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (119, '关芷若', '1', '18687110119', 23, '2002-09-09', 170, 56, 67, '2025-01-31', '2026-01-31', 'MBR0119', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (120, '蒋沐晴', '1', '18687110120', 22, '2003-10-10', 166, 52, 62, '2025-02-01', '2025-03-03', 'MBR0120', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (121, '毛若溪', '1', '18687110121', 23, '2002-11-11', 169, 55, 66, '2025-02-02', '2025-02-09', 'MBR0121', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (122, '谭锦程', '0', '18687110122', 23, '2002-12-12', 179, 74, 84, '2025-02-03', '2026-02-03', 'MBR0122', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (123, '姜子豪', '0', '18687110123', 22, '2003-01-01', 176, 71, 81, '2025-02-04', '2025-02-05', 'MBR0123', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (124, '史雨薇', '1', '18687110124', 22, '2003-02-02', 165, 52, 62, '2025-02-05', '2025-03-07', 'MBR0124', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (125, '葛佳怡', '1', '18687110125', 22, '2003-03-03', 166, 52, 62, '2025-02-06', '2025-02-13', 'MBR0125', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (126, '倪子墨', '0', '18687110126', 23, '2002-03-03', 175, 69, 79, '2025-02-07', '2026-02-07', 'MBR0126', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (127, '滕俊杰', '0', '18687110127', 22, '2003-04-04', 178, 73, 82, '2025-02-08', '2025-03-10', 'MBR0127', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (128, '仲诗涵', '1', '18687110128', 21, '2004-05-05', 166, 52, 62, '2025-02-09', '2025-02-16', 'MBR0128', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (129, '管嘉怡', '1', '18687110129', 23, '2002-06-06', 169, 55, 66, '2025-02-10', '2026-02-10', 'MBR0129', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (130, '程志强', '0', '18687110130', 23, '2002-07-07', 182, 80, 88, '2025-02-11', '2025-03-13', 'MBR0130', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (131, '尹雪妍', '1', '18687110131', 21, '2004-08-08', 168, 55, 66, '2025-02-12', '2025-02-19', 'MBR0131', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (132, '岳思琪', '1', '18687110132', 20, '2005-09-09', 160, 49, 60, '2025-02-13', '2026-02-13', 'MBR0132', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (133, '蔡家豪', '0', '18687110133', 21, '2004-10-10', 176, 71, 81, '2025-02-14', '2025-03-16', 'MBR0133', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (134, '古志轩', '0', '18687110134', 23, '2002-11-11', 179, 74, 84, '2025-02-15', '2025-02-16', 'MBR0134', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (135, '戴思远', '0', '18687110135', 22, '2003-12-12', 174, 68, 78, '2025-02-16', '2025-03-18', 'MBR0135', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (136, '包俊逸', '0', '18687110136', 22, '2003-01-01', 178, 73, 82, '2025-02-17', '2025-02-24', 'MBR0136', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (137, '裴昕悦', '1', '18687110137', 21, '2004-02-02', 167, 54, 64, '2025-02-18', '2026-02-18', 'MBR0137', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (138, '盛晓彤', '1', '18687110138', 22, '2003-03-03', 162, 50, 60, '2025-02-19', '2025-03-21', 'MBR0138', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (139, '冉语嫣', '1', '18687110139', 23, '2002-04-04', 170, 56, 67, '2025-02-20', '2025-02-27', 'MBR0139', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (140, '庄怡然', '1', '18687110140', 20, '2005-05-05', 161, 49, 59, '2025-02-21', '2026-02-21', 'MBR0140', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (141, '吕亦菲', '1', '18687110141', 22, '2003-06-06', 165, 52, 62, '2025-02-22', '2025-03-24', 'MBR0141', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (142, '严婉婷', '1', '18687110142', 21, '2004-07-07', 163, 50, 60, '2025-02-23', '2025-03-02', 'MBR0142', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (143, '牛皓宇', '0', '18687110143', 23, '2002-08-08', 180, 76, 85, '2025-02-24', '2026-02-24', 'MBR0143', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (144, '金皓轩', '0', '18687110144', 22, '2003-09-09', 177, 72, 82, '2025-02-25', '2025-02-26', 'MBR0144', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (145, '钱嘉琪', '1', '18687110145', 21, '2004-10-10', 164, 51, 61, '2025-02-26', '2025-03-28', 'MBR0145', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (146, '施善琦', '1', '18687110146', 22, '2003-11-11', 165, 52, 62, '2025-02-27', '2025-03-06', 'MBR0146', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (147, '童依然', '1', '18687110147', 23, '2002-12-12', 170, 56, 67, '2025-02-28', '2026-02-28', 'MBR0147', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (148, '关芊墨', '1', '18687110148', 22, '2003-01-01', 165, 52, 62, '2025-03-01', '2025-04-01', 'MBR0148', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (149, '蒋梦瑶', '1', '18687110149', 22, '2003-02-02', 166, 52, 62, '2025-03-02', '2025-03-09', 'MBR0149', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (150, '毛晓曼', '1', '18687110150', 21, '2004-03-03', 162, 50, 60, '2025-03-03', '2026-03-03', 'MBR0150', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (151, '谭俊辰', '0', '18687110151', 23, '2002-04-04', 179, 74, 84, '2025-03-04', '2025-04-03', 'MBR0151', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (152, '姜雨婷', '1', '18687110152', 22, '2003-05-05', 165, 52, 62, '2025-03-05', '2025-03-12', 'MBR0152', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (153, '史晓雪', '1', '18687110153', 21, '2004-06-06', 164, 51, 61, '2025-03-06', '2026-03-06', 'MBR0153', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (154, '葛宇航', '0', '18687110154', 21, '2004-07-07', 175, 69, 79, '2025-03-07', '2025-04-06', 'MBR0154', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (155, '倪俊凯', '0', '18687110155', 22, '2003-08-08', 178, 73, 82, '2025-03-08', '2025-03-15', 'MBR0155', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (156, '滕嘉欣', '1', '18687110156', 20, '2005-09-09', 160, 49, 60, '2025-03-09', '2026-03-09', 'MBR0156', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (157, '仲文博', '0', '18687110157', 22, '2003-10-10', 180, 76, 85, '2025-03-10', '2025-04-09', 'MBR0157', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (158, '管子瑜', '1', '18687110158', 22, '2003-11-11', 166, 52, 62, '2025-03-11', '2025-03-18', 'MBR0158', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (159, '程欣妍', '1', '18687110159', 21, '2004-12-12', 163, 50, 60, '2025-03-12', '2026-03-12', 'MBR0159', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (160, '尹怡静', '1', '18687110160', 23, '2002-01-01', 169, 55, 66, '2025-03-13', '2025-04-12', 'MBR0160', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (161, '岳语汐', '1', '18687110161', 21, '2004-02-02', 165, 52, 62, '2025-03-14', '2025-03-21', 'MBR0161', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (162, '蔡语桐', '1', '18687110162', 23, '2002-03-03', 170, 56, 67, '2025-03-15', '2026-03-15', 'MBR0162', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (163, '古可欣', '1', '18687110163', 21, '2004-04-04', 162, 50, 60, '2025-03-16', '2025-04-15', 'MBR0163', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (164, '戴可唯', '1', '18687110164', 22, '2003-05-05', 164, 51, 61, '2025-03-17', '2025-03-24', 'MBR0164', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (165, '包芷晴', '1', '18687110165', 23, '2002-06-06', 170, 56, 67, '2025-03-18', '2026-03-18', 'MBR0165', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (166, '裴语彤', '1', '18687110166', 21, '2004-07-07', 162, 50, 60, '2025-03-19', '2025-04-18', 'MBR0166', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (167, '盛诗妍', '1', '18687110167', 22, '2003-08-08', 165, 52, 62, '2025-03-20', '2025-03-27', 'MBR0167', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (168, '冉婉茹', '1', '18687110168', 23, '2002-09-09', 169, 55, 66, '2025-03-21', '2026-03-21', 'MBR0168', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (169, '庄泽宇', '0', '18687110169', 23, '2002-10-10', 180, 76, 85, '2025-03-22', '2025-04-21', 'MBR0169', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (170, '吕紫涵', '1', '18687110170', 22, '2003-11-11', 166, 52, 62, '2025-03-23', '2025-03-30', 'MBR0170', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (171, '严一诺', '1', '18687110171', 23, '2002-12-12', 170, 56, 67, '2025-03-24', '2026-03-24', 'MBR0171', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (172, '牛心悦', '1', '18687110172', 21, '2004-01-01', 163, 50, 60, '2025-03-25', '2025-04-24', 'MBR0172', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (173, '金景程', '0', '18687110173', 23, '2002-02-02', 178, 73, 82, '2025-03-26', '2025-04-02', 'MBR0173', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (174, '钱景行', '0', '18687110174', 22, '2003-03-03', 176, 71, 81, '2025-03-27', '2026-03-27', 'MBR0174', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (175, '施晓阳', '0', '18687110175', 21, '2004-04-04', 174, 68, 78, '2025-03-28', '2025-04-27', 'MBR0175', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (176, '童思玥', '1', '18687110176', 21, '2004-05-05', 163, 50, 60, '2025-03-29', '2025-04-05', 'MBR0176', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (177, '关楚涵', '1', '18687110177', 23, '2002-06-06', 170, 56, 67, '2025-03-30', '2026-03-30', 'MBR0177', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (178, '蒋语馨', '1', '18687110178', 22, '2003-07-07', 165, 52, 62, '2025-03-31', '2025-04-30', 'MBR0178', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (179, '毛绮梦', '1', '18687110179', 22, '2003-08-08', 166, 52, 62, '2025-04-01', '2025-04-08', 'MBR0179', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (180, '谭芷若', '1', '18687110180', 23, '2002-09-09', 170, 56, 67, '2025-04-02', '2026-04-02', 'MBR0180', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (181, '姜若溪', '1', '18687110181', 22, '2003-10-10', 166, 52, 62, '2025-04-03', '2025-05-03', 'MBR0181', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (182, '史锦程', '0', '18687110182', 23, '2002-11-11', 179, 74, 84, '2025-04-04', '2025-04-05', 'MBR0182', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (183, '葛子豪', '0', '18687110183', 22, '2003-12-12', 176, 71, 81, '2025-04-05', '2025-05-05', 'MBR0183', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (184, '倪雨薇', '1', '18687110184', 22, '2003-01-01', 165, 52, 62, '2025-04-06', '2025-04-13', 'MBR0184', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (185, '滕佳怡', '1', '18687110185', 23, '2002-02-02', 169, 55, 66, '2025-04-07', '2026-04-07', 'MBR0185', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (186, '仲子墨', '0', '18687110186', 23, '2002-03-03', 175, 69, 79, '2025-04-08', '2025-05-08', 'MBR0186', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (187, '管俊杰', '0', '18687110187', 22, '2003-04-04', 178, 73, 82, '2025-04-09', '2025-04-16', 'MBR0187', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (188, '程诗涵', '1', '18687110188', 21, '2004-05-05', 166, 52, 62, '2025-04-10', '2026-04-10', 'MBR0188', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (189, '尹嘉怡', '1', '18687110189', 22, '2003-06-06', 164, 51, 61, '2025-04-11', '2025-05-11', 'MBR0189', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (190, '岳志强', '0', '18687110190', 23, '2002-07-07', 182, 80, 88, '2025-04-12', '2025-04-13', 'MBR0190', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (191, '蔡雪妍', '1', '18687110191', 21, '2004-08-08', 168, 55, 66, '2025-04-13', '2025-05-13', 'MBR0191', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (192, '古思琪', '1', '18687110192', 20, '2005-09-09', 160, 49, 60, '2025-04-14', '2026-04-14', 'MBR0192', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (193, '戴家豪', '0', '18687110193', 21, '2004-10-10', 176, 71, 81, '2025-04-15', '2025-05-15', 'MBR0193', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (194, '包志轩', '0', '18687110194', 23, '2002-11-11', 179, 74, 84, '2025-04-16', '2025-04-23', 'MBR0194', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (195, '裴思远', '0', '18687110195', 22, '2003-12-12', 174, 68, 78, '2025-04-17', '2026-04-17', 'MBR0195', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (196, '盛俊逸', '0', '18687110196', 22, '2003-01-01', 178, 73, 82, '2025-04-18', '2025-05-18', 'MBR0196', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (197, '冉昕悦', '1', '18687110197', 21, '2004-02-02', 167, 54, 64, '2025-04-19', '2025-04-26', 'MBR0197', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (198, '庄晓彤', '1', '18687110198', 22, '2003-03-03', 162, 50, 60, '2025-04-20', '2026-04-20', 'MBR0198', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (199, '吕语嫣', '1', '18687110199', 23, '2002-04-04', 170, 56, 67, '2025-04-21', '2025-05-21', 'MBR0199', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (200, '严怡然', '1', '18687110200', 20, '2005-05-05', 161, 49, 59, '2025-04-22', '2025-04-29', 'MBR0200', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (201, '牛亦菲', '1', '18687110201', 22, '2003-06-06', 165, 52, 62, '2025-04-23', '2026-04-23', 'MBR0201', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (202, '金婉婷', '1', '18687110202', 21, '2004-07-07', 163, 50, 60, '2025-04-24', '2025-05-24', 'MBR0202', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (203, '钱皓宇', '0', '18687110203', 23, '2002-08-08', 180, 76, 85, '2025-04-25', '2025-04-26', 'MBR0203', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (204, '施皓轩', '0', '18687110204', 22, '2003-09-09', 177, 72, 82, '2025-04-26', '2025-05-26', 'MBR0204', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (205, '童嘉琪', '1', '18687110205', 21, '2004-10-10', 164, 51, 61, '2025-04-27', '2025-05-04', 'MBR0205', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (206, '关善琦', '1', '18687110206', 22, '2003-11-11', 165, 52, 62, '2025-04-28', '2026-04-28', 'MBR0206', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (207, '蒋依然', '1', '18687110207', 23, '2002-12-12', 170, 56, 67, '2025-04-29', '2025-05-29', 'MBR0207', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (208, '毛芊墨', '1', '18687110208', 22, '2003-01-01', 165, 52, 62, '2025-04-30', '2025-05-07', 'MBR0208', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (209, '谭梦瑶', '1', '18687110209', 22, '2003-02-02', 166, 52, 62, '2025-05-01', '2026-05-01', 'MBR0209', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (210, '姜晓曼', '1', '18687110210', 21, '2004-03-03', 162, 50, 60, '2025-05-02', '2025-06-01', 'MBR0210', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (211, '史俊辰', '0', '18687110211', 23, '2002-04-04', 179, 74, 84, '2025-05-03', '2025-05-10', 'MBR0211', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (212, '葛雨婷', '1', '18687110212', 22, '2003-05-05', 165, 52, 62, '2025-05-04', '2026-05-04', 'MBR0212', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (213, '倪晓雪', '1', '18687110213', 21, '2004-06-06', 164, 51, 61, '2025-05-05', '2025-06-04', 'MBR0213', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (214, '滕宇航', '0', '18687110214', 21, '2004-07-07', 175, 69, 79, '2025-05-06', '2025-05-13', 'MBR0214', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (215, '仲俊凯', '0', '18687110215', 22, '2003-08-08', 178, 73, 82, '2025-05-07', '2026-05-07', 'MBR0215', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (216, '管嘉欣', '1', '18687110216', 20, '2005-09-09', 160, 49, 60, '2025-05-08', '2025-06-07', 'MBR0216', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (217, '程文博', '0', '18687110217', 22, '2003-10-10', 180, 76, 85, '2025-05-09', '2025-06-08', 'MBR0217', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (218, '尹子瑜', '1', '18687110218', 22, '2003-11-11', 166, 52, 62, '2025-05-10', '2025-05-17', 'MBR0218', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (219, '岳欣妍', '1', '18687110219', 21, '2004-12-12', 163, 50, 60, '2025-05-11', '2026-05-11', 'MBR0219', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (220, '蔡怡静', '1', '18687110220', 23, '2002-01-01', 169, 55, 66, '2025-05-12', '2025-06-11', 'MBR0220', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (221, '古语汐', '1', '18687110221', 21, '2004-02-02', 165, 52, 62, '2025-05-13', '2025-05-20', 'MBR0221', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (222, '戴语桐', '1', '18687110222', 23, '2002-03-03', 170, 56, 67, '2025-05-14', '2026-05-14', 'MBR0222', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (223, '包可欣', '1', '18687110223', 21, '2004-04-04', 162, 50, 60, '2025-05-15', '2025-06-14', 'MBR0223', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (224, '裴可唯', '1', '18687110224', 22, '2003-05-05', 164, 51, 61, '2025-05-16', '2025-05-23', 'MBR0224', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (225, '盛芷晴', '1', '18687110225', 23, '2002-06-06', 170, 56, 67, '2025-05-17', '2026-05-17', 'MBR0225', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (226, '冉语彤', '1', '18687110226', 21, '2004-07-07', 162, 50, 60, '2025-05-18', '2025-06-17', 'MBR0226', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (227, '庄诗妍', '1', '18687110227', 22, '2003-08-08', 165, 52, 62, '2025-05-19', '2025-05-26', 'MBR0227', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (228, '吕婉茹', '1', '18687110228', 23, '2002-09-09', 169, 55, 66, '2025-05-20', '2026-05-20', 'MBR0228', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (229, '严泽宇', '0', '18687110229', 23, '2002-10-10', 180, 76, 85, '2025-05-21', '2025-06-20', 'MBR0229', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (230, '牛紫涵', '1', '18687110230', 22, '2003-11-11', 166, 52, 62, '2025-05-22', '2025-05-29', 'MBR0230', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (231, '金一诺', '1', '18687110231', 23, '2002-12-12', 170, 56, 67, '2025-05-23', '2026-05-23', 'MBR0231', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (232, '施心悦', '1', '18687110232', 21, '2004-01-01', 163, 50, 60, '2025-05-24', '2025-06-23', 'MBR0232', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (233, '童景程', '0', '18687110233', 23, '2002-02-02', 178, 73, 82, '2025-05-25', '2025-06-01', 'MBR0233', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (234, '关景行', '0', '18687110234', 22, '2003-03-03', 176, 71, 81, '2025-05-26', '2026-05-26', 'MBR0234', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (235, '蒋晓阳', '0', '18687110235', 21, '2004-04-04', 174, 68, 78, '2025-05-27', '2025-06-26', 'MBR0235', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (236, '毛思玥', '1', '18687110236', 21, '2004-05-05', 163, 50, 60, '2025-05-28', '2025-06-04', 'MBR0236', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (237, '谭楚涵', '1', '18687110237', 23, '2002-06-06', 170, 56, 67, '2025-05-29', '2026-05-29', 'MBR0237', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (238, '姜语馨', '1', '18687110238', 22, '2003-07-07', 165, 52, 62, '2025-05-30', '2025-06-29', 'MBR0238', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (239, '史绮梦', '1', '18687110239', 22, '2003-08-08', 166, 52, 62, '2025-05-31', '2025-06-07', 'MBR0239', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (240, '葛芷若', '1', '18687110240', 23, '2002-09-09', 170, 56, 67, '2025-06-01', '2026-06-01', 'MBR0240', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (241, '倪婉婷', '1', '18687110241', 21, '2004-10-10', 163, 50, 60, '2025-06-02', '2025-07-02', 'MBR0241', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (242, '滕皓宇', '0', '18687110242', 23, '2002-08-08', 180, 76, 85, '2025-06-03', '2025-06-04', 'MBR0242', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 1.00, '1天体验卡', 1, 1.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (243, '仲皓轩', '0', '18687110243', 22, '2003-09-09', 177, 72, 82, '2025-06-04', '2025-07-04', 'MBR0243', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (244, '管嘉琪', '1', '18687110244', 21, '2004-10-10', 164, 51, 61, '2025-06-05', '2025-06-12', 'MBR0244', '$2a$10$ZtJ/GCvJgZp7R4qw8yKd..LQ1w2kv73bqSadDUIcfv9vFzF9YvEZW', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (245, '程善琦', '1', '18687110245', 22, '2003-11-11', 165, 52, 62, '2025-06-06', '2026-06-06', 'MBR0245', '$2a$10$VwxPPOZ1gWeoc5CqNhRAXu6gtwlVuQ05BtPgclYYzmBvdRf3zIK26', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (246, '尹依然', '1', '18687110246', 23, '2002-12-12', 170, 56, 67, '2025-06-07', '2025-07-07', 'MBR0246', '$2a$10$t85yDYkCYHO/1aK1JOhQkeBm7CSrYEmy54zNnDD/wxQgVH5IIAVZe', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (247, '岳芊墨', '1', '18687110247', 22, '2003-01-01', 165, 52, 62, '2025-06-08', '2025-06-15', 'MBR0247', '$2a$10$DeWn8iIjAJXNkVr6UPuB7.r6IB/6N9AHQyZnnkzX3oHLDNiJhVkDO', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (248, '蔡梦瑶', '1', '18687110248', 22, '2003-02-02', 166, 52, 62, '2025-06-09', '2026-06-09', 'MBR0248', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (249, '古晓曼', '1', '18687110249', 21, '2004-03-03', 162, 50, 60, '2025-06-10', '2025-07-10', 'MBR0249', '$2a$10$myAOP14vKQVBF5EoX2QJPOpO1fU6mLOLZHIABGNz9KdmFPaG1zNKG', '1', 999.00, '1月卡', 30, 999.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (250, '戴俊辰', '0', '18687110250', 23, '2002-04-04', 179, 74, 84, '2025-06-11', '2025-06-18', 'MBR0250', '$2a$10$0HVjw0UCs.eFOEti4TP4JurkdjO6pGaY65XNLDojEYX7DV/zQSVC.', '1', 500.00, '7天卡', 7, 500.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (251, '包雨婷', '1', '18687110251', 22, '2003-05-05', 165, 52, 62, '2025-06-12', '2026-06-12', 'MBR0251', '$2a$10$h84zWzUeB9xFtUjA7Wn7T.RoIUzivRfdSHrIFtHk0cI1nL9JsP11O', '1', 20000.00, '年卡', 365, 20000.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (252, '裴晓雪', '1', '18687110252', 21, '2004-06-06', 164, 51, 61, '2025-06-13', '2025-07-20', 'MBR0252', '$2a$10$KJZgpYFO/vgkZC0Syng5MuwckTE6xleJmCytFK4vjG7i303YG5GYe', '1', 1108.00, '7天卡', 7, 100.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (253, '1', '0', '1', 1, '2025-11-07', 112, 12, 1, '2025-11-08', '', '2003', '$2a$10$whvg8q3nBgmzZ4z0BFB2YujbMd9dmuIx6cRbbOCCtooSgyeZiH8H2', '1', 111.00, NULL, NULL, 0.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (254, 'hy', '0', '1', 18, '2025-11-10', 172, 121, 45, '2025-11-10', '', '1', '$2a$10$FGKGzOuUyDuWMiqrqeYq5es/yvN40nVVQNDkjTvcqozOawJg0XQXy', '1', 11657746.00, NULL, NULL, 0.00, 1, 1, 1, 1);
INSERT INTO `member` VALUES (255, '阿浅', '0', '17398208709', 22, '2003-10-11', 180, 175, NULL, '2025-11-10', '', '123456', '$2a$10$lT2XzXNX09RXwbBNSJj.9uaOfR/o3BVV.BLmeuto.ZWpc2WcfwJL2', '1', 1224124.00, NULL, NULL, 0.00, 1, 1, 1, 1);

-- ----------------------------
-- Table structure for member_apply
-- ----------------------------
DROP TABLE IF EXISTS `member_apply`;
CREATE TABLE `member_apply`  (
  `apply_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_id` int NULL DEFAULT NULL COMMENT '会员id',
  `card_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '会员类型',
  `card_day` int NULL DEFAULT NULL COMMENT '天数',
  `price` decimal(18, 2) NULL DEFAULT 0.00 COMMENT '价格',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`apply_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member_apply
-- ----------------------------
INSERT INTO `member_apply` VALUES (1, 1, '7天卡', 7, 500.00, '2025-06-16 16:11:49', NULL);
INSERT INTO `member_apply` VALUES (2, 2, '1天体验卡', 1, 1.00, '2025-06-16 16:12:13', NULL);
INSERT INTO `member_apply` VALUES (3, 2, '7天卡', 7, 500.00, '2025-06-16 16:12:28', NULL);
INSERT INTO `member_apply` VALUES (4, 1, '7天卡', 7, 500.00, '2025-07-03 18:04:57', NULL);
INSERT INTO `member_apply` VALUES (5, 1, '7天卡', 7, 500.00, '2025-07-03 18:05:08', NULL);
INSERT INTO `member_apply` VALUES (6, 1, '1月卡', 30, 999.00, '2025-07-03 18:05:24', NULL);
INSERT INTO `member_apply` VALUES (7, 3, '1月卡', 30, 999.00, '2025-07-07 16:46:29', NULL);
INSERT INTO `member_apply` VALUES (8, 4, '1月卡', 30, 999.00, '2025-07-08 11:04:29', NULL);
INSERT INTO `member_apply` VALUES (9, 5, '1天体验卡', 1, 1.00, '2025-07-10 22:03:27', NULL);
INSERT INTO `member_apply` VALUES (10, 3, '1月卡', 30, 999.00, '2025-07-11 09:56:29', NULL);
INSERT INTO `member_apply` VALUES (11, 6, '1月卡', 30, 999.00, '2025-07-11 10:08:17', NULL);
INSERT INTO `member_apply` VALUES (12, 7, '1月卡', 30, 999.00, '2023-11-11 22:03:33', NULL);
INSERT INTO `member_apply` VALUES (13, 8, '1月卡', 30, 999.00, '2023-11-13 10:17:24', NULL);
INSERT INTO `member_apply` VALUES (14, 9, '1月卡', 30, 999.00, '2024-03-10 23:47:16', NULL);
INSERT INTO `member_apply` VALUES (15, 9, '7天卡', 7, 500.00, '2024-03-10 23:54:05', NULL);
INSERT INTO `member_apply` VALUES (16, 9, '7天卡', 7, 500.00, '2024-03-10 23:54:43', NULL);
INSERT INTO `member_apply` VALUES (17, 252, '7天卡', 7, 100.00, '2025-11-07 09:59:52', NULL);

-- ----------------------------
-- Table structure for member_card
-- ----------------------------
DROP TABLE IF EXISTS `member_card`;
CREATE TABLE `member_card`  (
  `card_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `card_type` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '卡类型  1:天卡  2：周卡 3：月卡 4：年卡',
  `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '价格',
  `card_day` int NULL DEFAULT NULL COMMENT '天数',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '状态 1：启用 0：停用',
  PRIMARY KEY (`card_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member_card
-- ----------------------------
INSERT INTO `member_card` VALUES (1, '1天体验卡', '1', 19.90, 1, '1');
INSERT INTO `member_card` VALUES (2, '7天卡', '2', 100.00, 7, '1');
INSERT INTO `member_card` VALUES (3, '1月卡', '3', 288.00, 30, '1');
INSERT INTO `member_card` VALUES (4, '年卡', '4', 1299.00, 365, '1');

-- ----------------------------
-- Table structure for member_course
-- ----------------------------
DROP TABLE IF EXISTS `member_course`;
CREATE TABLE `member_course`  (
  `member_course_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` int NULL DEFAULT NULL COMMENT '课程id',
  `course_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '课程名称',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '封面图',
  `teacher_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '授课教师',
  `course_hour` int NULL DEFAULT NULL COMMENT '课时',
  `course_details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '课程详情',
  `course_price` decimal(18, 2) NULL DEFAULT NULL COMMENT '课程价格',
  `teacher_id` int NULL DEFAULT NULL COMMENT '教师id',
  `member_id` int NULL DEFAULT NULL COMMENT '会员id',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '0:报名成功 1：退款',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`member_course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member_course
-- ----------------------------
INSERT INTO `member_course` VALUES (5, 6, '网球入门', 'http://127.0.0.1:9000/gym/gym_1762433498450.jpg', '张明', 20, '<p>正反手击球与发球基础。</p>', 200.00, 4, 1, '1', NULL);
INSERT INTO `member_course` VALUES (9, 6, '网球入门', 'http://127.0.0.1:9000/gym/gym_1762433498450.jpg', '张明', 20, '<p>规范挥拍与步伐。</p>', 200.00, 4, 3, '0', NULL);
INSERT INTO `member_course` VALUES (11, 6, '网球入门', 'http://127.0.0.1:9000/gym/gym_1762433498450.jpg', '张明', 20, '<p>入门课程复训。</p>', 200.00, 4, 6, '0', NULL);
INSERT INTO `member_course` VALUES (12, 6, '网球入门', 'http://127.0.0.1:9000/gym/gym_1762433498450.jpg', '张明', 20, '<p>发球动作规范。</p>', 200.00, 4, 4, '0', NULL);
INSERT INTO `member_course` VALUES (16, 8, '有氧燃脂', 'http://127.0.0.1:9000/gym/gym_1762433519425.jpg', '李丽', 12, '<p>心肺耐力提升。</p>', 299.00, 5, 1, '0', '2024-03-10 10:48:32');
INSERT INTO `member_course` VALUES (18, 4, '足球技巧强化', 'http://127.0.0.1:9000/gym/gym_1762433479109.jpg', '张明', 30, '<p>控球与射门训练。</p>', 599.00, 4, 9, '0', '2024-03-10 23:56:26');
INSERT INTO `member_course` VALUES (21, 1, '篮球基础班', 'http://127.0.0.1:9000/gym/gym_1762433442603.jpg', '张明', 24, '<p>基础运球、投篮姿势与步伐训练。</p>', 399.00, NULL, 254, '0', NULL);

-- ----------------------------
-- Table structure for member_recharge
-- ----------------------------
DROP TABLE IF EXISTS `member_recharge`;
CREATE TABLE `member_recharge`  (
  `recharge_id` int NOT NULL AUTO_INCREMENT COMMENT '充值id',
  `member_id` int NULL DEFAULT NULL COMMENT '会员id',
  `money` decimal(18, 2) NULL DEFAULT 0.00 COMMENT '充值金额',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`recharge_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member_recharge
-- ----------------------------
INSERT INTO `member_recharge` VALUES (8, 1, 500.00, '2025-07-03 15:07:04', 'admin');
INSERT INTO `member_recharge` VALUES (9, 2, 1000.00, '2025-07-03 15:07:12', 'admin');
INSERT INTO `member_recharge` VALUES (10, 3, 2000.00, '2025-07-03 15:07:20', 'admin');
INSERT INTO `member_recharge` VALUES (11, 1, 4000.00, '2025-07-03 15:07:29', 'admin');
INSERT INTO `member_recharge` VALUES (12, 3, 1000.00, '2025-07-07 16:46:21', 'admin');
INSERT INTO `member_recharge` VALUES (13, 4, 600.00, '2025-07-08 11:04:36', 'admin');
INSERT INTO `member_recharge` VALUES (15, 6, 1000.00, '2025-07-11 10:08:27', 'admin');
INSERT INTO `member_recharge` VALUES (16, 4, 1000.00, '2023-11-11 21:57:12', 'admin1');
INSERT INTO `member_recharge` VALUES (17, 7, 5000.00, '2023-11-11 22:03:42', 'admin1');
INSERT INTO `member_recharge` VALUES (18, 8, 20000.00, '2023-11-13 10:17:46', 'admin1');
INSERT INTO `member_recharge` VALUES (19, 9, 100.00, '2024-03-11 00:44:07', 'admin1');
INSERT INTO `member_recharge` VALUES (20, 9, 100.00, '2024-03-11 00:44:30', 'admin1');
INSERT INTO `member_recharge` VALUES (21, 9, 2000.00, '2024-03-11 00:47:04', 'admin1');
INSERT INTO `member_recharge` VALUES (22, 252, 9.00, NULL, NULL);
INSERT INTO `member_recharge` VALUES (23, 252, 100.00, NULL, NULL);
INSERT INTO `member_recharge` VALUES (24, 255, 1224124.00, NULL, NULL);
INSERT INTO `member_recharge` VALUES (25, 254, 4212412.00, NULL, NULL);
INSERT INTO `member_recharge` VALUES (26, 254, 4212412.00, NULL, NULL);
INSERT INTO `member_recharge` VALUES (27, 253, 111.00, NULL, NULL);
INSERT INTO `member_recharge` VALUES (28, 254, 500.00, NULL, NULL);
INSERT INTO `member_recharge` VALUES (29, 254, 500.00, '2025-11-11 10:09:38', '阿浅');
INSERT INTO `member_recharge` VALUES (30, 254, 3232321.00, '2025-11-12 12:18:28', '阿浅');

-- ----------------------------
-- Table structure for member_role
-- ----------------------------
DROP TABLE IF EXISTS `member_role`;
CREATE TABLE `member_role`  (
  `member_role_id` int NOT NULL AUTO_INCREMENT COMMENT '会员角色id',
  `member_id` int NULL DEFAULT NULL COMMENT '会员id',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`member_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member_role
-- ----------------------------
INSERT INTO `member_role` VALUES (12, 2, 4);
INSERT INTO `member_role` VALUES (14, 3, 4);
INSERT INTO `member_role` VALUES (16, 1, 4);
INSERT INTO `member_role` VALUES (17, 4, 4);
INSERT INTO `member_role` VALUES (19, 6, 4);
INSERT INTO `member_role` VALUES (20, 7, 4);
INSERT INTO `member_role` VALUES (21, 8, 4);
INSERT INTO `member_role` VALUES (24, 9, 4);
INSERT INTO `member_role` VALUES (25, 253, 4);
INSERT INTO `member_role` VALUES (27, 254, 4);
INSERT INTO `member_role` VALUES (28, 255, 4);

-- ----------------------------
-- Table structure for suggest
-- ----------------------------
DROP TABLE IF EXISTS `suggest`;
CREATE TABLE `suggest`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '反馈内容',
  `date_time` datetime NULL DEFAULT NULL COMMENT '反馈时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of suggest
-- ----------------------------
INSERT INTO `suggest` VALUES (1, '跑步机老要排队', '晚高峰等二十来分钟，添几台或者上预约呗。', '2025-07-10 18:45:12');
INSERT INTO `suggest` VALUES (2, '淋浴水压有点小', '晚上洗完感觉水没劲，能不能把水压拧大点？', '2025-07-11 20:12:03');
INSERT INTO `suggest` VALUES (3, '更衣室地面湿', '地上老有水，打扫能不能勤一点，怕滑倒。', '2025-07-12 21:30:45');
INSERT INTO `suggest` VALUES (4, '动感单车吱吱响', '骑起来有点异响，能帮忙上个油不？看着心烦。', '2025-07-13 10:08:16');
INSERT INTO `suggest` VALUES (5, '空调有点热', '下午热得不行，能不能把温度再降一降？', '2025-07-13 16:22:57');
INSERT INTO `suggest` VALUES (6, '退课规则不清楚', '临时改期要不要收费说清楚点，前台和官网同步一下。', '2025-07-14 09:40:11');
INSERT INTO `suggest` VALUES (7, '饮水机有点怪味', '可能是滤芯该换了，能不能贴个更换记录让大家放心。', '2025-07-14 12:05:33');
INSERT INTO `suggest` VALUES (8, '自由力量区提示', '最好贴点安全提示，别一不小心砸到脚了。', '2025-07-15 19:55:02');
INSERT INTO `suggest` VALUES (9, '音乐太吵了', '晚上8点那会儿音乐有点炸，能不能小点声。', '2025-07-16 20:10:12');
INSERT INTO `suggest` VALUES (10, '镜子太少', '练动作找不着镜子位子，能不能多装两面镜子。', '2025-07-16 21:33:58');
INSERT INTO `suggest` VALUES (11, '教练太严肃', '上课表情太冷了，稍微活泼点更有氛围。', '2025-07-17 09:12:40');
INSERT INTO `suggest` VALUES (12, '毛巾有一股洗衣粉味', '有点冲，能不能换个温和点的清洁剂。', '2025-07-17 10:45:21');
INSERT INTO `suggest` VALUES (13, '跑道有人占道聊天', '跑步机旁边聊天太吵了，能不能提醒一下。', '2025-07-17 19:28:36');
INSERT INTO `suggest` VALUES (14, '器材预约太麻烦', '预约流程有点绕，建议简化下或者直接现场排队。', '2025-07-18 08:15:03');
INSERT INTO `suggest` VALUES (15, '课表老是改', '上午说下午改时间，下午又变，建议固定一点。', '2025-07-18 12:50:44');
INSERT INTO `suggest` VALUES (16, '更衣柜钥匙难插', '钥匙孔有点涩，插半天，能不能维护一下。', '2025-07-18 17:22:56');
INSERT INTO `suggest` VALUES (17, '瑜伽垫味道大', '新垫子味有点重，晒一晒或者通风下再用。', '2025-07-19 09:05:19');
INSERT INTO `suggest` VALUES (18, '前台回答太官方', '我就问个价格，被背诵了一堆话术，简单点吧。', '2025-07-19 10:41:27');
INSERT INTO `suggest` VALUES (19, '饮料太甜', '卖的饮料糖有点高，能不能上点无糖的。', '2025-07-19 18:33:52');
INSERT INTO `suggest` VALUES (20, 'Wi-Fi信号一般', '更衣室里老掉线，能不能加个路由器。', '2025-07-20 11:09:05');
INSERT INTO `suggest` VALUES (21, '厕所纸巾时常没补', '纸没了很尴尬，补货能不能及时点。', '2025-07-20 13:20:17');
INSERT INTO `suggest` VALUES (22, '有人穿拖鞋进器械区', '有点危险，建议加强提醒或者入口检查。', '2025-07-20 19:40:33');
INSERT INTO `suggest` VALUES (23, '跑步机屏幕太亮', '晚上跑的时候晃眼，能不能调暗一点。', '2025-07-21 20:41:12');
INSERT INTO `suggest` VALUES (25, '希望有躺着健身的项目', '不想动但又想瘦，有没有这种课程？', '2025-07-22 21:33:44');
INSERT INTO `suggest` VALUES (26, '健身房太安静', '没气氛，有人一起练就好了', '2025-07-23 18:26:51');
INSERT INTO `suggest` VALUES (27, '沙袋太硬', '打两下手疼，能不能软一点，或者配手套。', '2025-07-23 20:02:35');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` int NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` int NOT NULL COMMENT '父级id',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '权限字段',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '路由名称',
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '路由path',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组件路径',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类型(0 目录 1菜单，2按钮)',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图标',
  `parent_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '上级菜单名称',
  `order_num` int NULL DEFAULT NULL COMMENT '序号',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', 'sys:manage', 'system', '/system', '', '0', 'Setting', '顶级菜单', 1, '2025-06-13 11:20:17', NULL);
INSERT INTO `sys_menu` VALUES (2, 1, '员工管理', 'sys:user', 'userList', '/userList', '/system/user/UserList', '1', 'UserFilled', '系统管理', 2, '2025-06-13 11:24:39', '2025-06-13 14:21:37');
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', 'sys:role', 'roleList', '/roleList', '/system/role/RoleList', '1', 'Wallet', '系统管理', 3, '2025-06-13 11:26:25', NULL);
INSERT INTO `sys_menu` VALUES (4, 0, '会员管理', 'sys:memberRoot', 'memberRoot', '/memberRoot', '', '0', 'Setting', '顶级菜单', 2, '2025-06-13 13:30:37', NULL);
INSERT INTO `sys_menu` VALUES (5, 4, '会员卡类型', 'sys:cardType', 'cardType', '/cardType', '/member/type/CardType', '1', 'UserFilled', '会员管理', 4, '2025-06-13 13:31:42', NULL);
INSERT INTO `sys_menu` VALUES (6, 4, '会员管理', 'sys:memberList', 'memberList', '/memberList', '/member/list/MemberList', '1', 'Wallet', '会员管理', 5, '2025-06-13 13:32:38', '2025-06-13 14:21:51');
INSERT INTO `sys_menu` VALUES (7, 4, '充值记录', 'sys:myFee', 'myFee', '/myFee', '/member/fee/MyFee', '1', 'Menu', '会员管理', 6, '2025-06-13 13:33:24', '2025-11-11 05:46:12');
INSERT INTO `sys_menu` VALUES (8, 2, '新增', 'sys:user:add', '', '', '', '2', '', '员工管理', 1, '2025-06-13 13:33:56', NULL);
INSERT INTO `sys_menu` VALUES (9, 2, '编辑', 'sys:user:edit', '', '', '', '2', '', '员工管理', 2, '2025-06-13 13:34:16', NULL);
INSERT INTO `sys_menu` VALUES (10, 2, '删除', 'sys:user:delete', '', '', '', '2', '', '员工管理', 3, '2025-06-13 13:34:34', '2025-06-13 14:21:06');
INSERT INTO `sys_menu` VALUES (11, 3, '新增', 'sys:role:add', '', '', '', '2', '', '角色管理', 1, '2025-06-13 13:35:01', NULL);
INSERT INTO `sys_menu` VALUES (12, 3, '编辑', 'sys:role:edit', '', '', '', '2', '', '角色管理', 2, '2025-06-13 13:35:17', NULL);
INSERT INTO `sys_menu` VALUES (13, 3, '删除', 'sys:role:delete', '', '', '', '2', '', '角色管理', 3, '2025-06-13 13:35:33', NULL);
INSERT INTO `sys_menu` VALUES (14, 5, '新增', 'sys:memberRoot:add', '', '', '', '2', '', '会员卡类型', 1, '2025-06-13 13:35:59', NULL);
INSERT INTO `sys_menu` VALUES (15, 5, '编辑', 'sys:memberRoot:edit', '', '', '', '2', '', '会员卡类型', 2, '2025-06-13 13:36:21', NULL);
INSERT INTO `sys_menu` VALUES (16, 5, '删除', 'sys:memberRoot:delete', '', '', '', '2', '', '会员卡类型', 3, '2025-06-13 13:36:35', NULL);
INSERT INTO `sys_menu` VALUES (17, 1, '菜单管理', 'sys:menu', 'menuList', '/menuList', '/system/menu/MenuList', '1', 'Menu', '系统管理', 7, '2025-07-02 18:19:56', NULL);
INSERT INTO `sys_menu` VALUES (18, 17, '新增', 'sys:menu:add', '', '', '', '2', '', '菜单管理', 8, '2025-07-02 18:20:23', NULL);
INSERT INTO `sys_menu` VALUES (19, 17, '编辑', 'sys:menu:edit', '', '', '', '2', '', '菜单管理', 9, '2025-07-02 18:20:41', NULL);
INSERT INTO `sys_menu` VALUES (20, 17, '删除', 'sys:menu:delete', '', '', '', '2', '', '菜单管理', 11, '2025-07-02 18:20:58', NULL);
INSERT INTO `sys_menu` VALUES (21, 6, '新增', 'sys:memberList:add', '', '', '', '2', '', '会员管理', 12, '2025-07-02 18:22:06', NULL);
INSERT INTO `sys_menu` VALUES (22, 6, '编辑', 'sys:memberList:edit', '', '', '', '2', '', '会员管理', 13, '2025-07-02 18:22:32', NULL);
INSERT INTO `sys_menu` VALUES (23, 6, '删除', 'sys:memberList:delete', '', '', '', '2', '', '会员管理', 14, '2025-07-02 18:22:49', NULL);
INSERT INTO `sys_menu` VALUES (24, 6, '充值', 'sys:memberList:rechart', '', '', '', '2', '', '会员管理', 15, '2025-07-02 18:23:41', NULL);
INSERT INTO `sys_menu` VALUES (25, 6, '办卡', 'sys:memberList:setCard', '', '', '', '2', '', '会员管理', 16, '2025-07-02 18:24:18', NULL);
INSERT INTO `sys_menu` VALUES (26, 0, '课程管理', 'sys:courseRoot', 'courseRoot', '/courseRoot', '', '0', 'ScaleToOriginal', '顶级菜单', 18, '2025-07-02 18:25:24', NULL);
INSERT INTO `sys_menu` VALUES (27, 26, '课程列表', 'sys:courseList', 'courseList', '/courseList', '/course/CourseList', '1', 'UserFilled', '课程管理', 19, '2025-07-02 18:26:24', NULL);
INSERT INTO `sys_menu` VALUES (28, 27, '新增', 'sys:courseList:add', '', '', '', '2', '', '课程列表', 20, '2025-07-02 18:26:45', NULL);
INSERT INTO `sys_menu` VALUES (29, 27, '编辑', 'sys:courseList:edit', '', '', '', '2', '', '课程列表', 21, '2025-07-02 18:27:02', NULL);
INSERT INTO `sys_menu` VALUES (30, 27, '删除', 'sys:courseList:delete', '', '', '', '2', '', '课程列表', 22, '2025-07-02 18:27:19', NULL);
INSERT INTO `sys_menu` VALUES (31, 26, '我的课程', 'sys:mycourse', 'mycourse', '/mycourse', '/mycourse/mycourse', '1', 'Wallet', '课程管理', 23, '2025-07-02 18:28:21', NULL);
INSERT INTO `sys_menu` VALUES (32, 0, '器材管理', 'sys:materialList', 'materialList', '/materialList', '/material/MaterialList', '1', 'UserFilled', '顶级菜单', 24, '2025-07-02 18:29:36', NULL);
INSERT INTO `sys_menu` VALUES (33, 32, '新增', 'sys:materialList:add', '', '', '', '2', '', '器材管理', 25, '2025-07-02 18:29:57', NULL);
INSERT INTO `sys_menu` VALUES (34, 32, '编辑', 'sys:materialList:edit', '', '', '', '2', '', '器材管理', 26, '2025-07-02 18:30:15', NULL);
INSERT INTO `sys_menu` VALUES (35, 32, '删除', 'sys:materialList:delete', '', '', '', '2', '', '器材管理', 27, '2025-07-02 18:30:31', NULL);
INSERT INTO `sys_menu` VALUES (36, 0, '商品管理', 'sys:goodsRoot', 'goodsRoot', '/goodsRoot', '', '0', 'Document', '顶级菜单', 30, '2025-07-02 18:31:25', NULL);
INSERT INTO `sys_menu` VALUES (37, 36, '商品列表', 'sys:goodsList', 'goodsList', '/goodsList', '/goods/GoodsList', '1', 'UserFilled', '商品管理', 31, '2025-07-02 18:32:18', NULL);
INSERT INTO `sys_menu` VALUES (38, 37, '新增', 'sys:goodsList:add', '', '', '', '2', '', '商品列表', 32, '2025-07-02 18:32:44', NULL);
INSERT INTO `sys_menu` VALUES (39, 37, '编辑', 'sys:goodsList:edit', '', '', '', '2', '', '商品列表', 33, '2025-07-02 18:33:01', NULL);
INSERT INTO `sys_menu` VALUES (40, 37, '删除', 'sys:goodsList:delete', '', '', '', '2', '', '商品列表', 34, '2025-07-02 18:33:19', NULL);
INSERT INTO `sys_menu` VALUES (41, 36, '订单管理', 'sys:orderList', 'orderList', '/orderList', '/order/OrderList', '1', 'UserFilled', '商品管理', 35, '2025-07-02 18:34:12', NULL);
INSERT INTO `sys_menu` VALUES (42, 51, '失物列表', 'sys:lostList', 'lostList', '/lostList', '/lost/LostList', '1', 'UserFilled', '失物招领1', 36, '2025-07-02 18:35:06', '2025-07-02 20:12:07');
INSERT INTO `sys_menu` VALUES (43, 42, '新增', 'sys:lostList:add', '', '', '', '2', '', '失物招领', 37, '2025-07-02 18:35:28', NULL);
INSERT INTO `sys_menu` VALUES (44, 42, '编辑', 'sys:lostList:edit', '', '', '', '2', '', '失物招领', 38, '2025-07-02 18:35:47', NULL);
INSERT INTO `sys_menu` VALUES (45, 42, '删除', 'sys:lostList:delete', '', '', '', '2', '', '失物招领', 39, '2025-07-02 18:36:03', NULL);
INSERT INTO `sys_menu` VALUES (46, 52, '反馈列表', 'sys:suggestList', 'suggestList', '/suggestList', '/suggest/SuggestList', '1', 'UserFilled', '反馈管理', 40, '2025-07-02 18:37:12', '2025-07-02 20:13:51');
INSERT INTO `sys_menu` VALUES (47, 46, '新增', 'sys:suggestList:add', '', '', '', '2', '', '反馈管理', 42, '2025-07-02 18:37:39', NULL);
INSERT INTO `sys_menu` VALUES (48, 46, '编辑', 'sys:suggestList:edit', '', '', '', '2', '', '反馈管理', 43, '2025-07-02 18:37:55', NULL);
INSERT INTO `sys_menu` VALUES (49, 46, '删除', 'sys:suggestList:delete', '', '', '', '2', '', '反馈管理', 44, '2025-07-02 18:38:11', NULL);
INSERT INTO `sys_menu` VALUES (50, 42, '查看', 'sys:lostList:look', '', '', '', '2', '', '失物招领', 45, '2025-07-02 20:00:09', NULL);
INSERT INTO `sys_menu` VALUES (51, 0, '失物招领', 'sys:lostRoot', 'lostRoot', '/lostRoot', '', '0', 'Document', '顶级菜单', 36, '2025-07-02 20:10:57', '2025-07-02 20:12:14');
INSERT INTO `sys_menu` VALUES (52, 0, '反馈管理', 'sys:suggestRoot', 'suggestRoot', '/suggestRoot', '/suggest/SuggestList', '0', 'Document', '顶级菜单', 40, '2025-07-02 20:13:40', NULL);
INSERT INTO `sys_menu` VALUES (53, 27, '查看', 'sys:courseList:look', '', '', '', '2', '', '课程列表', 50, '2025-07-03 21:17:50', NULL);
INSERT INTO `sys_menu` VALUES (54, 27, '报名', 'sys:courseList:join', '', '', '', '2', '', '课程列表', 51, '2025-07-03 21:40:15', NULL);
INSERT INTO `sys_menu` VALUES (55, 42, '认领', 'sys:lostList:get', '', '', '', '2', '', '失物列表', 55, '2025-07-04 15:55:26', NULL);
INSERT INTO `sys_menu` VALUES (56, 3, '分配权限', 'sys:role:assign', '', '', '', '2', '', '角色管理', 56, '2025-07-04 16:04:26', NULL);
INSERT INTO `sys_menu` VALUES (57, 6, '查看', 'sys:memberList:look', '', '', '', '2', '', '会员管理', 57, '2025-07-04 16:06:44', NULL);
INSERT INTO `sys_menu` VALUES (58, 31, '导出学生', 'sys:mycourse:export', '', '', '', '2', '', '我的课程', 57, '2025-07-04 19:29:37', NULL);
INSERT INTO `sys_menu` VALUES (59, 2, '员工重置密码', 'sys:user:resetPassword', '', '', '', '2', '', '员工管理', 60, '2025-07-07 18:19:42', NULL);
INSERT INTO `sys_menu` VALUES (60, 6, '会员重置密码', 'sys:member:resetPassword', '', '', '', '2', '', '会员管理', 61, '2025-07-07 18:20:21', NULL);
INSERT INTO `sys_menu` VALUES (61, 41, '下单', 'sys:orderList:down', '', '', '', '2', '', '订单管理', 70, '2025-07-10 16:26:27', NULL);
INSERT INTO `sys_menu` VALUES (62, 31, '列表', 'sys:mycourse:list', '', '', '', '2', '', '我的课程', 33, '2023-11-11 21:52:37', NULL);
INSERT INTO `sys_menu` VALUES (63, 31, '退款', 'sys:mycourse:return', '', '', '', '2', '', '我的课程', 11, '2024-03-07 16:20:22', '2024-03-09 21:15:59');
INSERT INTO `sys_menu` VALUES (64, 26, '课程订单', 'sys:courseOrder:index', 'courseOrder', '/courseOrder', '/course/Order', '1', 'Plus', '课程管理', 41, '2024-03-10 10:21:22', '2024-03-10 10:26:39');
INSERT INTO `sys_menu` VALUES (65, 64, '退款', 'sys:courseOrder:return', '', '', '', '2', '', '课程订单', NULL, '2024-03-10 10:22:51', NULL);
INSERT INTO `sys_menu` VALUES (66, 4, '我的充值', 'sys:myRecharge', 'myRecharge', '/myRecharge', '/member/fee/MyRecharge', '1', 'Wallet', '会员管理', 7, '2025-11-11 05:15:08', '2025-11-11 05:56:10');
INSERT INTO `sys_menu` VALUES (67, 26, '我的授课', 'sys:myTeaching', 'myTeaching', '/myTeaching', '/course/TeachingList', '1', 'UserFilled', '课程管理', 42, '2025-11-12 12:56:27', '2025-11-12 12:58:30');
INSERT INTO `sys_menu` VALUES (68, 67, '列表', 'sys:myTeaching:list', '', '', '', '2', '', '我的授课', 1, '2025-11-12 12:58:40', NULL);
INSERT INTO `sys_menu` VALUES (69, 67, '导出学生', 'sys:myTeaching:export', '', '', '', '2', '', '我的授课', 2, '2025-11-12 12:58:40', NULL);
INSERT INTO `sys_menu` VALUES (70, 67, '退款', 'sys:myTeaching:return', '', '', '', '2', '', '我的授课', 3, '2025-11-12 12:58:40', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `types` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '1：员员类型 2：会员类型 ',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', '系统管理员', '2025-06-05 19:28:40', '2025-11-11 02:36:20', '1');
INSERT INTO `sys_role` VALUES (2, '员工', '员工', '2025-06-05 19:47:41', '2025-11-11 02:36:18', '1');
INSERT INTO `sys_role` VALUES (3, '教练', '教练', '2025-06-05 19:50:52', '2025-11-11 02:36:10', '1');
INSERT INTO `sys_role` VALUES (4, '会员', '会员', '2025-06-16 10:30:36', '2025-11-11 09:45:59', '2');
INSERT INTO `sys_role` VALUES (6, '最高权限', '拥有所有板块的权限，用于检查功能', '2025-11-11 02:07:56', '2025-11-14 08:59:00', '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_menu_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2775 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2688, 6, 2);
INSERT INTO `sys_role_menu` VALUES (2689, 6, 1);
INSERT INTO `sys_role_menu` VALUES (2690, 6, 3);
INSERT INTO `sys_role_menu` VALUES (2691, 6, 17);
INSERT INTO `sys_role_menu` VALUES (2692, 6, 5);
INSERT INTO `sys_role_menu` VALUES (2693, 6, 4);
INSERT INTO `sys_role_menu` VALUES (2694, 6, 6);
INSERT INTO `sys_role_menu` VALUES (2695, 6, 7);
INSERT INTO `sys_role_menu` VALUES (2696, 6, 66);
INSERT INTO `sys_role_menu` VALUES (2697, 6, 27);
INSERT INTO `sys_role_menu` VALUES (2698, 6, 26);
INSERT INTO `sys_role_menu` VALUES (2699, 6, 31);
INSERT INTO `sys_role_menu` VALUES (2700, 6, 64);
INSERT INTO `sys_role_menu` VALUES (2701, 6, 67);
INSERT INTO `sys_role_menu` VALUES (2702, 6, 32);
INSERT INTO `sys_role_menu` VALUES (2703, 6, 37);
INSERT INTO `sys_role_menu` VALUES (2704, 6, 36);
INSERT INTO `sys_role_menu` VALUES (2705, 6, 41);
INSERT INTO `sys_role_menu` VALUES (2706, 6, 42);
INSERT INTO `sys_role_menu` VALUES (2707, 6, 51);
INSERT INTO `sys_role_menu` VALUES (2708, 6, 46);
INSERT INTO `sys_role_menu` VALUES (2709, 6, 52);
INSERT INTO `sys_role_menu` VALUES (2726, 4, 66);
INSERT INTO `sys_role_menu` VALUES (2727, 4, 4);
INSERT INTO `sys_role_menu` VALUES (2728, 4, 31);
INSERT INTO `sys_role_menu` VALUES (2729, 4, 26);
INSERT INTO `sys_role_menu` VALUES (2730, 4, 41);
INSERT INTO `sys_role_menu` VALUES (2731, 4, 36);
INSERT INTO `sys_role_menu` VALUES (2732, 4, 42);
INSERT INTO `sys_role_menu` VALUES (2733, 4, 51);
INSERT INTO `sys_role_menu` VALUES (2734, 4, 46);
INSERT INTO `sys_role_menu` VALUES (2735, 4, 52);
INSERT INTO `sys_role_menu` VALUES (2736, 2, 5);
INSERT INTO `sys_role_menu` VALUES (2737, 2, 4);
INSERT INTO `sys_role_menu` VALUES (2738, 2, 6);
INSERT INTO `sys_role_menu` VALUES (2739, 2, 7);
INSERT INTO `sys_role_menu` VALUES (2740, 2, 27);
INSERT INTO `sys_role_menu` VALUES (2741, 2, 26);
INSERT INTO `sys_role_menu` VALUES (2742, 2, 64);
INSERT INTO `sys_role_menu` VALUES (2743, 2, 32);
INSERT INTO `sys_role_menu` VALUES (2744, 2, 37);
INSERT INTO `sys_role_menu` VALUES (2745, 2, 36);
INSERT INTO `sys_role_menu` VALUES (2746, 2, 41);
INSERT INTO `sys_role_menu` VALUES (2747, 2, 42);
INSERT INTO `sys_role_menu` VALUES (2748, 2, 51);
INSERT INTO `sys_role_menu` VALUES (2749, 2, 46);
INSERT INTO `sys_role_menu` VALUES (2750, 2, 52);
INSERT INTO `sys_role_menu` VALUES (2761, 3, 27);
INSERT INTO `sys_role_menu` VALUES (2762, 3, 26);
INSERT INTO `sys_role_menu` VALUES (2763, 3, 64);
INSERT INTO `sys_role_menu` VALUES (2764, 3, 67);
INSERT INTO `sys_role_menu` VALUES (2765, 3, 41);
INSERT INTO `sys_role_menu` VALUES (2766, 3, 36);
INSERT INTO `sys_role_menu` VALUES (2767, 3, 42);
INSERT INTO `sys_role_menu` VALUES (2768, 3, 51);
INSERT INTO `sys_role_menu` VALUES (2769, 3, 46);
INSERT INTO `sys_role_menu` VALUES (2770, 3, 52);
INSERT INTO `sys_role_menu` VALUES (2771, 1, 2);
INSERT INTO `sys_role_menu` VALUES (2772, 1, 1);
INSERT INTO `sys_role_menu` VALUES (2773, 1, 3);
INSERT INTO `sys_role_menu` VALUES (2774, 1, 17);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '账户(员工编号)',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT ' 性别  0：男 1：女',
  `salary` decimal(18, 2) NULL DEFAULT NULL COMMENT '工资',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '类型（1：员工 2：教练）',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '状态 0：停用 1：启用',
  `is_admin` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT ' 是否是管理员 （1：是 0 ：否）',
  `is_account_non_expired` tinyint NULL DEFAULT NULL COMMENT '帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` tinyint NULL DEFAULT NULL COMMENT '帐户是否被锁定(1 未锁定，0已锁定)',
  `is_credentials_non_expired` tinyint NULL DEFAULT NULL COMMENT '密码是否过期(1 未过期，0已过期)',
  `is_enabled` tinyint NULL DEFAULT NULL COMMENT '帐户是否可用(1 可用，0 删除用户)',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '姓名',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'zs001', '$2a$10$W1Qe8xRjjgomW81Pc.DOY.1s.lAJb/Ash0yOOF77s7o7Db6q3Fwme', '18687116223', '2383404558@qq.com', '0', 5000.00, '1', '1', '0', 1, 1, 1, 1, '张三', '2025-06-11 23:11:00', '2025-11-10 07:51:52');
INSERT INTO `sys_user` VALUES (3, 'root', '$2a$10$bI7MkRirh36ZZ9dgRLAIwu9eefXDNMdbORHMvMCVUqohIYa4H/yKG', '17398208709', '2383404558@qq.com', '0', 1000000.00, '1', '1', '0', 1, 1, 1, 1, 'admin', NULL, '2025-09-26 07:21:26');
INSERT INTO `sys_user` VALUES (6, 'admin', '$2a$10$fKInjm/5Cd/3/NGBiGggLeUG4RrE7oKGWt9qss658vGusO/m6ceGG', '18687116223', '', '0', 10000.00, '1', '1', '1', 1, 1, 1, 1, 'admin1', '2025-07-04 19:08:05', NULL);
INSERT INTO `sys_user` VALUES (21, '123456', '$2a$10$jjNPbapkILwwMqvntfnhLO0Sa2Kg71git23.HuDmX051FemvJUOI2', '17398208709', '123', '0', NULL, '2', '1', '0', 1, 1, 1, 1, '阿浅', '2025-11-10 13:20:26', NULL);
INSERT INTO `sys_user` VALUES (22, '1', '$2a$10$m.MUwvDVpPXVZpG3/3uCeOooQfw/.k.EeOd.0o/avMa.6DYKYypwa', '17398208709', '2803038543@qq,com', '0', 10000000.00, NULL, '1', '0', 1, 1, 1, 1, '阿浅', '2025-11-11 02:35:10', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_role_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NULL DEFAULT NULL COMMENT '员工id',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (2, 2, 2);
INSERT INTO `sys_user_role` VALUES (8, 1, 2);
INSERT INTO `sys_user_role` VALUES (9, 4, 3);
INSERT INTO `sys_user_role` VALUES (11, 6, 2);
INSERT INTO `sys_user_role` VALUES (12, 5, 2);
INSERT INTO `sys_user_role` VALUES (22, 12, 2);
INSERT INTO `sys_user_role` VALUES (24, 13, 2);
INSERT INTO `sys_user_role` VALUES (25, 14, 3);
INSERT INTO `sys_user_role` VALUES (30, 15, 2);
INSERT INTO `sys_user_role` VALUES (31, 17, 3);
INSERT INTO `sys_user_role` VALUES (32, 16, 3);
INSERT INTO `sys_user_role` VALUES (33, 18, 3);
INSERT INTO `sys_user_role` VALUES (34, 19, 2);
INSERT INTO `sys_user_role` VALUES (35, 20, 3);
INSERT INTO `sys_user_role` VALUES (36, 21, 3);
INSERT INTO `sys_user_role` VALUES (37, 22, 6);

SET FOREIGN_KEY_CHECKS = 1;

-- ==== 教练子类型与课程教师同步改造 ====
-- 为 sys_user 增加教练子类型字段
ALTER TABLE `sys_user`
  ADD COLUMN `coach_type` varchar(16) NULL COMMENT '教练子类型：课程教练/助教' AFTER `user_type`;
ALTER TABLE `sys_user` ADD INDEX `idx_user_type_coach_type` (`user_type`, `coach_type`);

-- 将课程列表中未在员工表出现的授课教师插入 sys_user（保持 user_id 与 course.teacher_id 一致），并标注为“课程教练”
INSERT INTO `sys_user` (
  `user_id`, `username`, `password`, `phone`, `email`, `sex`, `salary`,
  `user_type`, `status`, `is_admin`, `is_account_non_expired`, `is_account_non_locked`,
  `is_credentials_non_expired`, `is_enabled`, `nick_name`, `create_time`, `update_time`, `coach_type`
)
SELECT
  c.`teacher_id` AS `user_id`,
  CONCAT('coach_', c.`teacher_id`) AS `username`,
  '$2a$10$W1Qe8xRjjgomW81Pc.DOY.1s.lAJb/Ash0yOOF77s7o7Db6q3Fwme' AS `password`,
  '' AS `phone`, '' AS `email`, '0' AS `sex`, 0.00 AS `salary`,
  '2' AS `user_type`, '1' AS `status`, '0' AS `is_admin`,
  1 AS `is_account_non_expired`, 1 AS `is_account_non_locked`,
  1 AS `is_credentials_non_expired`, 1 AS `is_enabled`,
  c.`teacher_name` AS `nick_name`, NOW() AS `create_time`, NULL AS `update_time`,
  '课程教练' AS `coach_type`
FROM `course` c
LEFT JOIN `sys_user` u ON u.`user_id` = c.`teacher_id`
WHERE u.`user_id` IS NULL
GROUP BY c.`teacher_id`, c.`teacher_name`;

-- 为所有教练用户补齐“教练”角色绑定（role_id = 3）
INSERT INTO `sys_user_role` (`user_id`, `role_id`)
SELECT u.`user_id`, 3
FROM `sys_user` u
LEFT JOIN `sys_user_role` ur ON ur.`user_id` = u.`user_id` AND ur.`role_id` = 3
WHERE u.`user_type` = '2' AND ur.`user_id` IS NULL;
