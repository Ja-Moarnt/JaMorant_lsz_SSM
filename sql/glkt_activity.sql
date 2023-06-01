/*
 Navicat Premium Data Transfer

 Source Server         : Beifen
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : glkt_activity

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 06/05/2023 15:58:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupon_info
-- ----------------------------
DROP TABLE IF EXISTS `coupon_info`;
CREATE TABLE `coupon_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `coupon_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '优惠卷名字',
  `amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '金额',
  `condition_amount` int NOT NULL DEFAULT 0 COMMENT '使用门槛 0->没门槛,其他数字表示只限该商品使用',
  `start_time` datetime NULL DEFAULT NULL COMMENT '可以领取的开始日期',
  `end_time` datetime NULL DEFAULT NULL COMMENT '可以领取的结束日期',
  `rule_desc` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '规则描述',
  `publish_count` int NOT NULL DEFAULT 1 COMMENT '发行数量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '优惠券信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon_info
-- ----------------------------
INSERT INTO `coupon_info` VALUES (1, '新用户注册10元优惠券', 10.00, 12, '2023-02-03 00:00:00', '2023-03-03 00:00:00', '每个新用户注册就能使用的10元优惠券，全场通用，无门槛', 100, '2023-02-03 22:43:32', '2023-04-11 19:29:21', 0);
INSERT INTO `coupon_info` VALUES (2, '双11狂欢10元优惠券', 10.00, 0, '2023-02-03 00:00:00', '2023-03-03 23:59:59', '双11狂欢10元优惠券，全场通用，无门槛', 100, '2023-02-03 22:43:32', '2023-03-03 16:26:26', 0);
INSERT INTO `coupon_info` VALUES (3, '国庆长假8元优惠券', 8.00, 0, '2023-02-03 22:41:33', '2023-03-02 22:41:36', '国庆长假8元优惠券，全场通用，无门槛', 100, '2023-02-03 22:43:32', '2023-03-03 12:37:19', 0);
INSERT INTO `coupon_info` VALUES (4, '新年欢乐9.99元优惠券', 9.99, 0, '2023-02-03 22:41:33', '2023-03-03 22:41:36', '新年欢乐9.99元优惠券，全场通用，无门槛', 100, '2023-02-03 22:43:32', '2023-02-03 22:43:34', 0);
INSERT INTO `coupon_info` VALUES (5, '中秋团圆8.88元优惠券', 8.88, 0, '2023-02-03 22:41:33', '2023-03-03 22:41:36', '中秋团圆8.88元优惠券，全场通用，无门槛', 100, '2023-02-03 22:43:32', '2023-02-03 22:44:48', 0);
INSERT INTO `coupon_info` VALUES (6, '端午6.66元优惠券', 6.66, 0, '2023-02-03 22:41:33', '2023-03-03 22:41:36', '端午6.66元优惠券，全场通用，无门槛', 100, '2023-02-03 22:43:32', '2023-02-03 22:43:34', 0);
INSERT INTO `coupon_info` VALUES (7, '五一劳动5元优惠券', 5.00, 0, '2023-02-03 22:41:33', '2023-03-03 22:41:36', '五一劳动5元优惠券，全场通用，无门槛', 100, '2023-02-03 22:43:32', '2023-04-11 19:17:03', 0);
INSERT INTO `coupon_info` VALUES (8, '情人节9.99元优惠券', 9.99, 0, '2023-02-03 22:41:33', '2023-03-03 22:41:36', '情人节9.99元优惠券，全场通用，无门槛', 100, '2023-02-03 22:43:32', '2023-02-03 22:43:34', 0);
INSERT INTO `coupon_info` VALUES (10, '女神节5.50优惠券', 5.50, 0, '2023-03-03 00:00:00', '2023-03-09 00:00:00', '女神节5.50优惠券，全场通用', 10, '2023-03-03 12:30:58', '2023-03-03 12:30:58', 0);
INSERT INTO `coupon_info` VALUES (39, '5元神劵', 5.00, 0, '2023-03-07 00:00:00', '2023-04-01 23:59:59', '全场通用无规则', 50, '2023-03-07 22:38:13', '2023-03-07 22:38:13', 0);
INSERT INTO `coupon_info` VALUES (40, '清明节优惠券', 2.00, 0, '2023-04-05 00:00:00', '2023-05-05 23:59:59', '无门槛', 10, '2023-04-05 14:40:52', '2023-04-05 14:40:52', 0);
INSERT INTO `coupon_info` VALUES (42, '411测试', 5.00, 0, '2023-04-11 19:53:21', '2023-04-14 19:53:23', '411测试', 4, '2023-04-11 19:53:46', '2023-04-15 01:53:41', 1);
INSERT INTO `coupon_info` VALUES (43, '4112', 2.00, 3, '2023-04-11 20:34:44', '2023-04-11 20:34:46', '412测试', 3, '2023-04-11 20:34:47', '2023-04-15 01:53:41', 1);
INSERT INTO `coupon_info` VALUES (44, '413', 2.00, 0, '2023-04-13 21:08:54', '2023-04-13 21:08:56', '413测试', 3, '2023-04-13 21:08:57', '2023-04-15 01:53:41', 0);
INSERT INTO `coupon_info` VALUES (45, '414 优惠券', 5.00, 0, '2023-04-13 21:30:01', '2023-04-18 21:30:03', '414 无门槛优惠券', 10, '2023-04-14 21:30:10', '2023-04-14 21:40:41', 0);
INSERT INTO `coupon_info` VALUES (46, '414 演示使用优惠券', 2.00, 0, '2023-04-14 22:11:08', '2023-04-21 00:00:00', '414 无门槛优惠券', 10, '2023-04-14 22:11:29', '2023-04-14 22:11:51', 0);
INSERT INTO `coupon_info` VALUES (47, '414 随手买演示优惠券', 2.00, 0, '2023-04-14 23:02:15', '2023-04-22 00:00:00', '414 无门槛优惠券', 15, '2023-04-14 23:02:23', '2023-04-14 23:02:40', 0);

-- ----------------------------
-- Table structure for coupon_use
-- ----------------------------
DROP TABLE IF EXISTS `coupon_use`;
CREATE TABLE `coupon_use`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `coupon_id` bigint NULL DEFAULT NULL COMMENT '购物券ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `order_id` bigint NULL DEFAULT NULL COMMENT '订单ID',
  `coupon_status` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '购物券状态（1：未使用 2：已使用）',
  `get_time` datetime NULL DEFAULT NULL COMMENT '获取时间',
  `using_time` datetime NULL DEFAULT NULL COMMENT '使用时间',
  `used_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '优惠券领用表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon_use
-- ----------------------------
INSERT INTO `coupon_use` VALUES (1, 1, 1, 49, '1', '2021-11-10 09:02:23', '2023-02-04 19:32:43', '2023-02-04 19:32:43', '2021-07-02 00:00:00', '2021-11-10 09:02:23', '2023-02-04 19:32:43', 0);
INSERT INTO `coupon_use` VALUES (2, 1, 1, NULL, '0', '2021-11-10 09:02:23', NULL, NULL, '2022-07-02 00:00:00', '2021-11-10 09:02:28', '2021-11-22 07:59:02', 0);
INSERT INTO `coupon_use` VALUES (5, 4, 1, 4, '1', '2021-11-12 16:37:00', '2021-11-23 18:57:27', NULL, '2022-09-30 00:00:00', '2021-11-12 08:36:58', '2021-11-23 10:57:27', 0);
INSERT INTO `coupon_use` VALUES (9, 4, 1, 1, '1', '2021-11-22 14:50:05', '2021-11-22 21:38:48', '2021-11-22 21:39:12', '2022-09-30 00:00:00', '2021-11-22 14:50:05', '2021-11-22 13:39:12', 0);
INSERT INTO `coupon_use` VALUES (27, 4, 12, NULL, '0', '2021-11-23 18:14:01', NULL, NULL, '2022-09-30 00:00:00', '2021-11-23 10:14:01', '2023-04-11 20:33:46', 0);
INSERT INTO `coupon_use` VALUES (28, 4, 12, NULL, '0', '2021-11-23 18:49:01', NULL, NULL, '2022-09-30 00:00:00', '2021-11-23 10:49:00', '2023-04-11 20:33:46', 0);
INSERT INTO `coupon_use` VALUES (29, 4, 12, 9, '1', '2021-11-23 18:49:03', '2023-01-18 15:16:52', NULL, '2022-09-30 00:00:00', '2021-11-23 10:49:03', '2023-04-11 20:33:46', 0);
INSERT INTO `coupon_use` VALUES (30, 4, 12, 5, '1', '2021-11-23 18:50:03', '2021-11-23 18:57:52', NULL, '2022-09-30 00:00:00', '2021-11-23 10:50:02', '2023-04-11 20:33:46', 0);
INSERT INTO `coupon_use` VALUES (31, 4, 12, NULL, '0', '2021-11-23 18:52:49', NULL, NULL, '2022-09-30 00:00:00', '2021-11-23 10:52:49', '2023-04-11 20:33:46', 0);
INSERT INTO `coupon_use` VALUES (32, 4, 12, NULL, '0', '2021-11-26 08:57:40', NULL, NULL, '2022-09-30 00:00:00', '2021-11-26 00:57:39', '2023-04-11 20:33:46', 0);
INSERT INTO `coupon_use` VALUES (33, 4, 12, NULL, '0', '2021-11-26 18:33:04', NULL, NULL, '2022-09-30 00:00:00', '2021-11-26 10:33:03', '2023-04-11 20:33:46', 0);
INSERT INTO `coupon_use` VALUES (34, 4, 1, NULL, '0', '2021-11-26 18:34:12', NULL, NULL, '2022-09-30 00:00:00', '2021-11-26 10:34:11', '2023-04-11 20:33:46', 0);
INSERT INTO `coupon_use` VALUES (35, 4, 1, NULL, '0', '2021-11-28 16:46:53', NULL, NULL, '2022-09-30 00:00:00', '2021-11-28 08:46:53', '2023-04-11 20:33:46', 0);
INSERT INTO `coupon_use` VALUES (36, 4, 1, NULL, '0', '2021-12-28 12:29:18', NULL, NULL, '2022-09-30 00:00:00', '2021-12-28 12:29:17', '2023-04-11 20:33:46', 0);
INSERT INTO `coupon_use` VALUES (37, 4, 34, NULL, '0', '2022-01-05 14:35:16', NULL, NULL, '2022-09-30 00:00:00', '2022-01-05 14:35:15', '2023-04-11 20:33:46', 0);
INSERT INTO `coupon_use` VALUES (49, 1, 34, NULL, '0', '2023-02-03 23:11:53', NULL, NULL, '2023-02-10 23:11:53', '2023-02-03 23:11:53', '2023-02-03 23:31:50', 0);
INSERT INTO `coupon_use` VALUES (50, 2, 34, NULL, '0', '2023-02-04 15:59:50', NULL, NULL, '2023-02-11 15:59:50', '2023-02-04 15:59:50', '2023-02-04 15:59:50', 0);
INSERT INTO `coupon_use` VALUES (51, 1, 12, 11, '1', '2023-02-07 18:41:52', '2023-02-07 18:52:20', '2023-02-07 18:52:20', '2023-02-14 18:41:52', '2023-02-07 18:41:51', '2023-02-07 18:52:19', 0);
INSERT INTO `coupon_use` VALUES (52, 3, 34, NULL, '0', '2023-02-08 17:05:51', NULL, NULL, '2023-02-15 17:05:51', '2023-02-08 17:05:50', '2023-02-08 17:05:50', 0);
INSERT INTO `coupon_use` VALUES (53, 4, 34, NULL, '0', '2023-02-08 17:07:18', NULL, NULL, '2023-02-15 17:07:18', '2023-02-08 17:07:18', '2023-02-08 17:07:18', 0);
INSERT INTO `coupon_use` VALUES (54, 7, 34, NULL, '0', '2023-02-09 18:37:56', NULL, NULL, '2023-02-16 18:37:56', '2023-02-09 18:37:56', '2023-02-09 18:37:56', 0);
INSERT INTO `coupon_use` VALUES (55, 5, 34, NULL, '0', '2023-02-16 13:28:37', NULL, NULL, '2023-02-23 13:28:37', '2023-02-16 13:28:36', '2023-02-16 13:28:36', 0);
INSERT INTO `coupon_use` VALUES (56, 8, 34, NULL, '0', '2023-02-16 13:29:11', NULL, NULL, '2023-02-23 13:29:11', '2023-02-16 13:29:10', '2023-02-16 13:29:10', 0);
INSERT INTO `coupon_use` VALUES (57, 1, 1, NULL, '0', '2023-02-16 18:24:09', NULL, NULL, '2023-02-23 18:24:09', '2023-02-16 18:24:08', '2023-04-11 20:33:05', 0);
INSERT INTO `coupon_use` VALUES (58, 2, 12, NULL, '0', '2023-02-16 18:24:14', NULL, NULL, '2023-02-23 18:24:14', '2023-02-16 18:24:13', '2023-04-11 20:33:05', 0);
INSERT INTO `coupon_use` VALUES (59, 5, 12, NULL, '0', '2023-02-16 22:16:57', NULL, NULL, '2023-02-23 22:16:57', '2023-02-16 22:16:56', '2023-04-11 20:33:05', 0);
INSERT INTO `coupon_use` VALUES (60, 4, 34, NULL, '0', '2023-02-16 22:17:00', NULL, NULL, '2023-02-23 22:17:00', '2023-02-16 22:16:59', '2023-04-11 20:33:05', 0);
INSERT INTO `coupon_use` VALUES (61, 6, 34, NULL, '0', '2023-02-27 22:20:35', NULL, NULL, '2023-03-06 22:20:35', '2023-02-27 22:20:35', '2023-02-27 22:20:35', 0);
INSERT INTO `coupon_use` VALUES (62, 39, 34, NULL, '0', '2023-03-13 13:48:57', NULL, NULL, '2023-03-20 13:48:57', '2023-03-13 13:48:56', '2023-03-13 13:48:56', 0);
INSERT INTO `coupon_use` VALUES (63, 40, 34, NULL, '0', '2023-04-05 14:45:53', NULL, NULL, '2023-04-12 14:45:53', '2023-04-05 14:45:53', '2023-04-05 14:45:53', 0);
INSERT INTO `coupon_use` VALUES (64, 45, 34, 389, '1', '2023-04-14 21:42:27', '2023-04-14 21:45:23', '2023-04-14 21:45:23', '2023-04-21 21:42:27', '2023-04-14 21:42:27', '2023-04-14 21:45:22', 0);
INSERT INTO `coupon_use` VALUES (65, 45, 12, 391, '1', '2023-04-14 22:06:00', '2023-04-14 22:07:33', '2023-04-14 22:07:33', '2023-04-21 22:06:00', '2023-04-14 22:05:59', '2023-04-14 22:07:33', 0);
INSERT INTO `coupon_use` VALUES (66, 40, 12, NULL, '0', '2023-04-14 22:06:12', NULL, NULL, '2023-04-21 22:06:12', '2023-04-14 22:06:11', '2023-04-14 22:06:11', 0);
INSERT INTO `coupon_use` VALUES (67, 46, 12, 392, '1', '2023-04-14 22:37:51', '2023-04-14 22:39:58', '2023-04-14 22:39:58', '2023-04-21 22:37:51', '2023-04-14 22:37:50', '2023-04-14 22:39:58', 0);
INSERT INTO `coupon_use` VALUES (68, 46, 34, NULL, '0', '2023-04-14 23:01:05', NULL, NULL, '2023-04-21 23:01:05', '2023-04-14 23:01:04', '2023-04-14 23:01:04', 0);
INSERT INTO `coupon_use` VALUES (69, 47, 34, 401, '1', '2023-04-14 23:25:37', '2023-04-14 23:28:00', '2023-04-14 23:28:00', '2023-04-21 23:25:37', '2023-04-14 23:25:37', '2023-04-14 23:27:59', 0);

SET FOREIGN_KEY_CHECKS = 1;
