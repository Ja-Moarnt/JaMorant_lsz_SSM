/*
 Navicat Premium Data Transfer

 Source Server         : Beifen
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : glkt_user

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 06/05/2023 16:04:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'userid',
  `phone` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0,0' COMMENT '用户密码',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `nick_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` tinyint NULL DEFAULT NULL COMMENT '性别',
  `avatar` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '头像',
  `chengshi_id` bigint NULL DEFAULT NULL COMMENT '城市',
  `open_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '小程序open id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, '10086', '0,0', 'lyq', 'lyq', 1, 'data:image/jpeg;base64,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', 5, '', '2023-02-16 18:20:01', '2023-04-14 21:06:47', 0);
INSERT INTO `user_info` VALUES (12, '1001011', '', '', '迎峰来)登风造极', 0, 'data:image/jpeg;base64,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', 5, 'o1nVa5d4Gs3PmU5QgVmRQ74J4DO0', '2023-02-16 18:20:06', '2023-04-15 02:05:16', 0);
INSERT INTO `user_info` VALUES (34, '132564', '', '', 'cxz', 0, 'data:image/jpeg;base64,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', 5, 'o1nVa5ed2OYliWDE_DKZcIn0co1I', '2023-02-16 18:23:49', '2023-04-15 02:05:16', 0);

SET FOREIGN_KEY_CHECKS = 1;