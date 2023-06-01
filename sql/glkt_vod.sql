/*
 Navicat Premium Data Transfer

 Source Server         : Beifen
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : glkt_vod

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 06/05/2023 16:04:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '车牌号',
  `Gui_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '平台id号',
  `car_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '审核状态',
  `teacher_id` bigint NOT NULL DEFAULT 0 COMMENT '司机ID',
  `video_id` bigint NULL DEFAULT NULL COMMENT '广告ID',
  `order_id` bigint NULL DEFAULT NULL COMMENT '订单ID',
  `chengshi_id` bigint NULL DEFAULT NULL COMMENT '城市ID',
  `time_start` time NOT NULL COMMENT '工作起始时间',
  `time_end` time NOT NULL COMMENT '工作结束时间',
  `yingli` decimal(10, 2) NOT NULL COMMENT '盈利总额',
  `fengcheng` decimal(10, 2) NOT NULL COMMENT '分成额',
  `jifeng` decimal(10, 2) NOT NULL COMMENT '基础分成值',
  `longitude` decimal(10, 7) NULL DEFAULT NULL COMMENT '地理经度',
  `latitude` decimal(10, 7) NULL DEFAULT NULL COMMENT '地理纬度',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_teacher_id`(`teacher_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '车辆' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES (1, '赣B 66666', '20230210112022615', '1', 1, 50, NULL, 139, '08:00:00', '19:00:00', 3680.08, 5.00, 5.00, 114.7635968, 25.7933916, '2023-03-10 21:16:20', '2023-04-17 21:37:29', 0);
INSERT INTO `car` VALUES (2, '粤A 99999', '20230210112038060', '1', 2, 52, NULL, 139, '08:00:00', '19:00:00', 4158.18, 5.00, 5.00, 114.7635968, 25.7933916, '2023-03-10 21:16:20', '2023-04-18 21:36:08', 0);
INSERT INTO `car` VALUES (3, '京A 55555', '20230210112054650', '1', 22, 50, NULL, 200, '08:00:00', '19:00:00', 4448.71, 5.00, 5.00, 116.3909481, 39.7933916, '2023-03-10 21:16:20', '2023-04-12 15:27:17', 0);
INSERT INTO `car` VALUES (4, '黑S 66688', '20230210112147686', '1', 2, NULL, NULL, 139, '08:00:00', '19:00:00', 0.00, 0.00, 5.00, 112.7635968, 26.7933916, '2023-03-10 21:16:20', '2023-03-10 21:30:14', 0);
INSERT INTO `car` VALUES (20, '川A 33333', '20230210114307880', '1', 1, 54, NULL, 218, '08:00:00', '19:00:00', 1847.41, 5.00, 5.00, 114.7635968, 30.7933916, '2023-03-10 21:16:20', '2023-04-17 21:38:00', 0);
INSERT INTO `car` VALUES (21, '粤A 88888', '20230210211248713', '1', 20, NULL, NULL, 139, '08:00:00', '19:00:00', 3929.55, 0.00, 5.00, 114.7635968, 25.7933916, '2023-03-10 21:16:20', '2023-04-18 21:36:27', 0);
INSERT INTO `car` VALUES (22, '粤B 88888', '20230210211219678', '1', 1, 50, NULL, 128, '08:00:00', '19:00:00', 1156.86, 5.00, 5.00, 117.7635968, 25.7933916, '2023-03-10 21:16:20', '2023-04-17 21:36:43', 0);
INSERT INTO `car` VALUES (23, '蒙B 78956', '525626662225662', '1', 2, NULL, NULL, 71, '07:00:00', '22:00:00', 0.00, 0.00, 5.00, 114.7635968, 25.7933916, '2023-03-10 21:16:20', '2023-04-12 15:27:17', 0);
INSERT INTO `car` VALUES (24, '京 112233', '20230210211405932', '1', 2, NULL, NULL, 10, '09:30:00', '09:30:00', 0.00, 0.00, 0.00, 114.7635968, 25.7933916, '2023-03-10 21:16:20', '2023-04-12 15:27:17', 0);
INSERT INTO `car` VALUES (25, '京 112233', '20230210211405932', '1', 23, NULL, NULL, 2, '09:30:00', '09:30:00', 0.00, 0.00, 0.00, 117.7635968, 25.7933916, '2023-03-10 21:16:20', '2023-04-12 15:27:17', 0);
INSERT INTO `car` VALUES (26, '赣A 111111', '20230210211405932', '1', 1, NULL, NULL, 2, '09:30:00', '09:30:00', 0.00, 0.00, 0.00, 112.7635968, 37.7933916, '2023-03-10 21:16:39', '2023-04-15 00:16:58', 0);
INSERT INTO `car` VALUES (27, '赣B 77777', '20230210211348713', '1', 24, 52, NULL, 139, '06:00:00', '15:00:00', 0.00, 0.00, 0.00, 117.7635968, 25.7933916, '2023-03-17 21:41:56', '2023-04-12 15:27:17', 0);
INSERT INTO `car` VALUES (28, '粤A 59808', '20230210211208713', '1', 21, NULL, NULL, 60, '09:00:00', '21:00:00', 0.00, 0.00, 0.00, 112.7635968, 37.7933916, '2023-04-05 15:52:31', '2023-04-12 15:27:17', 0);
INSERT INTO `car` VALUES (29, '粤C 11111', '20230415000109630', '1', 1, NULL, NULL, 217, '08:00:00', '19:30:00', 0.00, 0.00, 0.00, NULL, NULL, '2023-04-15 00:07:01', '2023-04-15 03:40:13', 0);
INSERT INTO `car` VALUES (30, '粤A 22222', '20230415002717157', '0', 1, NULL, NULL, 215, '08:30:00', '19:30:00', 0.00, 0.00, 0.00, NULL, NULL, '2023-04-15 00:33:18', '2023-04-15 00:33:18', 0);
INSERT INTO `car` VALUES (31, '粤A 44444', '20230415003722339', '0', 1, NULL, NULL, 215, '09:00:00', '20:00:00', 0.00, 0.00, 0.00, NULL, NULL, '2023-04-15 00:43:20', '2023-04-15 00:43:20', 0);
INSERT INTO `car` VALUES (32, '粤A 66778', '20230415003722356', '0', 1, NULL, NULL, 215, '09:00:00', '19:00:00', 0.00, 0.00, 0.00, NULL, NULL, '2023-04-15 00:50:29', '2023-04-15 00:50:29', 0);
INSERT INTO `car` VALUES (33, '', '', '0', 1, NULL, NULL, 2, '08:00:00', '20:00:00', 0.00, 0.00, 0.00, NULL, NULL, '2023-04-15 03:26:32', '2023-04-15 03:26:32', 0);
INSERT INTO `car` VALUES (34, '粤C 12345', '20230415003722377', '0', 1, NULL, NULL, 217, '09:00:00', '21:00:00', 0.00, 0.00, 0.00, NULL, NULL, '2023-04-18 21:41:03', '2023-04-18 21:41:03', 0);

-- ----------------------------
-- Table structure for carshoping
-- ----------------------------
DROP TABLE IF EXISTS `carshoping`;
CREATE TABLE `carshoping`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` bigint NOT NULL DEFAULT 0 COMMENT '司机ID',
  `goods_id` bigint NOT NULL DEFAULT 0 COMMENT '商品ID',
  `sheng_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '剩余数量',
  `buy_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '历史销售数量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_subject_id`(`goods_id` ASC) USING BTREE,
  INDEX `idx_teacher_id`(`car_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '车辆货物' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of carshoping
-- ----------------------------
INSERT INTO `carshoping` VALUES (30, 1, 20, 48, 5, '2023-02-02 13:17:15', '2023-04-15 03:40:36', 0);
INSERT INTO `carshoping` VALUES (31, 1, 21, 29, 0, '2023-02-02 13:17:47', '2023-02-02 13:17:47', 0);
INSERT INTO `carshoping` VALUES (32, 1, 22, 19, 0, '2023-02-02 13:18:02', '2023-02-02 13:18:02', 0);
INSERT INTO `carshoping` VALUES (33, 1, 37, 30, 0, '2023-02-02 13:18:21', '2023-02-02 13:18:21', 0);
INSERT INTO `carshoping` VALUES (34, 1, 26, 10, 0, '2023-02-02 13:18:38', '2023-02-02 13:18:38', 0);
INSERT INTO `carshoping` VALUES (35, 1, 27, 30, 0, '2023-02-02 13:18:49', '2023-02-02 13:18:49', 0);
INSERT INTO `carshoping` VALUES (36, 1, 28, 25, 0, '2023-02-02 13:19:01', '2023-02-02 13:19:01', 0);
INSERT INTO `carshoping` VALUES (37, 1, 25, 30, 0, '2023-02-02 13:19:11', '2023-02-02 13:19:11', 0);
INSERT INTO `carshoping` VALUES (38, 1, 38, 25, 0, '2023-02-02 13:19:28', '2023-02-02 13:19:28', 0);
INSERT INTO `carshoping` VALUES (39, 1, 36, 10, 0, '2023-02-02 13:19:38', '2023-02-02 13:19:38', 0);
INSERT INTO `carshoping` VALUES (40, 1, 34, 50, 0, '2023-02-02 13:19:51', '2023-02-02 13:19:51', 0);
INSERT INTO `carshoping` VALUES (41, 1, 35, 30, 0, '2023-02-02 13:20:03', '2023-02-02 13:20:03', 0);
INSERT INTO `carshoping` VALUES (42, 2, 20, 34, 0, '2023-02-02 13:20:22', '2023-02-02 13:20:22', 0);
INSERT INTO `carshoping` VALUES (43, 2, 26, 36, 0, '2023-02-02 13:20:32', '2023-02-02 13:20:32', 0);
INSERT INTO `carshoping` VALUES (44, 20, 29, 36, 0, '2023-02-02 13:20:43', '2023-02-02 13:20:43', 0);
INSERT INTO `carshoping` VALUES (45, 20, 31, 36, 0, '2023-02-02 13:20:53', '2023-02-02 13:20:53', 0);
INSERT INTO `carshoping` VALUES (47, 21, 36, 3, 0, '2023-02-02 13:21:15', '2023-02-02 13:21:15', 0);
INSERT INTO `carshoping` VALUES (48, 21, 25, 10, 0, '2023-02-02 13:42:42', '2023-02-02 13:42:42', 0);
INSERT INTO `carshoping` VALUES (49, 21, 30, 10, 0, '2023-02-02 14:04:23', '2023-02-02 14:09:49', 0);
INSERT INTO `carshoping` VALUES (50, 21, 34, 10, 0, '2023-02-02 14:12:23', '2023-02-02 14:12:23', 0);
INSERT INTO `carshoping` VALUES (51, 21, 37, 11, 0, '2023-02-02 14:41:17', '2023-02-02 14:41:17', 0);
INSERT INTO `carshoping` VALUES (52, 21, 38, 10, 0, '2023-02-04 16:02:24', '2023-02-04 16:02:24', 0);
INSERT INTO `carshoping` VALUES (53, 4, 21, 1, 0, '2023-02-07 19:53:05', '2023-02-07 19:53:05', 0);
INSERT INTO `carshoping` VALUES (54, 2, 35, 88, 70, '2023-02-10 22:11:07', '2023-02-10 22:11:07', 0);
INSERT INTO `carshoping` VALUES (55, 2, 25, 6, 0, '2023-02-10 22:29:39', '2023-02-10 22:29:39', 0);
INSERT INTO `carshoping` VALUES (56, 0, 21, 5, 0, '2023-02-16 22:09:56', '2023-04-11 18:42:21', 1);
INSERT INTO `carshoping` VALUES (57, 0, 21, 5, 0, '2023-02-16 22:09:57', '2023-04-11 18:42:23', 1);
INSERT INTO `carshoping` VALUES (58, 2, 22, 6, 0, '2023-02-18 22:03:55', '2023-02-18 22:03:55', 0);
INSERT INTO `carshoping` VALUES (59, 2, 21, 4, 0, '2023-02-18 23:36:56', '2023-02-18 23:36:56', 0);
INSERT INTO `carshoping` VALUES (60, 27, 21, 3, 0, '2023-03-17 21:47:49', '2023-04-11 18:42:26', 1);
INSERT INTO `carshoping` VALUES (61, 27, 20, 1, 0, '2023-03-17 21:49:28', '2023-04-11 18:42:34', 1);
INSERT INTO `carshoping` VALUES (62, 27, 22, 2, 0, '2023-03-17 21:49:46', '2023-04-11 18:42:37', 1);
INSERT INTO `carshoping` VALUES (63, 1, 23, 4, 0, '2023-04-14 22:52:01', '2023-04-14 22:52:01', 0);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_id` bigint NOT NULL DEFAULT 0 COMMENT '商品分类ID',
  `subject_parent_id` bigint NOT NULL DEFAULT 0 COMMENT '商品分类父级ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '商品销售价格，设置为0则免费',
  `cover` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程封面图片路径',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '商品简介',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '商品状态 0未上架 1已上架',
  `publish_time` datetime NULL DEFAULT NULL COMMENT '上架时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_title`(`title` ASC) USING BTREE,
  INDEX `idx_subject_id`(`subject_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (20, 2, 1, '润田矿泉水', 0.90, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/d3dcc449d7bb4c239f1bb9dcb8a9b5d8润田矿泉水.jpg', '江西润田饮料股份有限公司是一家致力于生产经营天然饮料食品的中外合资企业，注册资本2.1亿元，总部位于历史名城赣江之滨----江西省南昌市。润田公司创建于1994年，从地方性品牌到全国性品牌，逐年稳步发展， 以产品质量求市场，倡导\"回归自然 ，关注健康\"的绿色理念，着力打造润田健康、安全的品牌形象，在消费者中有非常好的口碑。', 1, '2023-04-12 17:37:54', '2023-04-12 16:19:38', '2023-04-12 17:37:51', 0);
INSERT INTO `goods` VALUES (21, 2, 1, '冰露矿泉水', 1.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/ed06fb6823114432ab4c2fb2c91747b7冰露矿泉水.jpg', '冰露(Ice Dew)是可口可乐(Coca-Cola)公司出品的一款矿物质水饮料。市面上常见的有330毫升、550毫升装(一块一瓶)、1.5升装和5加仑桶装几种。为中国饮用水市场主流产品之一。', 1, '2023-04-12 17:38:09', '2023-04-12 16:22:44', '2023-04-12 17:38:06', 0);
INSERT INTO `goods` VALUES (22, 2, 1, '农夫山泉矿泉水', 2.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/c0ba749003184233b5551445d9fc24ec农夫山泉矿泉水.jpg', '农夫山泉（NONGFU SPRING），即农夫山泉股份有限公司，原名“浙江千岛湖养生堂饮用水有限公司”，成立于1996年9月26日，其公司总部位于浙江省杭州市，系养生堂旗下控股公司。2019年5月8日，农夫山泉宣布农夫山泉正式进军咖啡界。2019年9月1日，2019中国制造业企业500强榜单发布，农夫山泉股份有限公司名列第332位。2020年1月7日，入选2019年全国农产品加工业100强企业名单，综合排名第18。2020年9月8日，农夫山泉正式登陆港交所，开盘涨超85%。', 1, '2023-04-12 16:26:24', '2023-04-12 16:26:20', '2023-04-12 16:26:20', 0);
INSERT INTO `goods` VALUES (23, 2, 1, '小瓶冰红茶', 3.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/e96a92bfde2b4e94b76ea849274d72a3小瓶冰红茶.jpg', '冰红茶，即红茶加冰制作的饮料。冰红茶确切的发明时间已无从考证，但在1929年夏天时突然流行起来。茶商理查（Richard Blechynden） 参加中国西湖博览会时向人推销红茶，由于盛夏酷暑，理查自己都喝不下热腾腾的红茶。灰心之际，一堆冰块意外掉进泡好的热红茶，倒掉可惜，便盛一杯来解渴，没想到冰红茶清凉畅快。理查灵机一动，转卖冰红茶，随即销售一空。', 1, '2023-04-12 16:28:56', '2023-04-12 16:28:52', '2023-04-12 16:28:52', 0);
INSERT INTO `goods` VALUES (24, 2, 1, '大瓶冰红茶', 5.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/06add1d336fb419c879fda6a44bf4b20冰红茶.jpg', '冰红茶，即红茶加冰制作的饮料。冰红茶确切的发明时间已无从考证，但在1929年夏天时突然流行起来。茶商理查（Richard Blechynden） 参加中国西湖博览会时向人推销红茶，由于盛夏酷暑，理查自己都喝不下热腾腾的红茶。灰心之际，一堆冰块意外掉进泡好的热红茶，倒掉可惜，便盛一杯来解渴，没想到冰红茶清凉畅快。理查灵机一动，转卖冰红茶，随即销售一空。', 1, '2023-04-12 16:29:31', '2023-04-12 16:29:27', '2023-04-12 16:29:27', 0);
INSERT INTO `goods` VALUES (25, 2, 1, '罐装红牛', 5.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/13e3e0de794e45fdbc70c2c49d264b6e红牛.jpg', '\"红牛\"(Red Bull)是全球首先推出且被人熟知的能量饮品之一。20世纪70年代，红牛(Red Bull)饮料嘱桨笑创始人许书标的工厂研制出一款内含水、糖、咖啡因和维生素B等成分的\"滋补性饮料\"，取名为\"红牛\"。红牛通过帮助消费者在长时间的碑轿篮工作或者驾驶中保持清醒和精力充沛，一上市就受到泰国消费者的广泛喜爱。\n\n随着红牛在国际上取得成功，许书标先生决定将红牛引入中国以期回报家乡，1993年许书标先生在其祖籍所在地海南成立了海南红牛饮料有限公司，红牛由此进入中国。1995年，许书标先生和他的家人只项霸蜜与其他股东成立了红牛维他命饮料有限公司(合资公司)。2018年9月，合资公司的经营期限到期。2019年，天丝集团携手本地合作伙伴广州曜能量饮料公司以及普盛食品销售有限公司，在国内市场推出了红牛®维生素风味饮料和红牛®维生素牛磺酸饮料两款产品 。', 1, '2023-04-12 16:30:28', '2023-04-12 16:30:23', '2023-04-12 16:30:23', 0);
INSERT INTO `goods` VALUES (26, 2, 1, '小瓶百事可乐', 2.50, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/ae61c5fb19644461b310799010ef6ed4小瓶百事可乐.jpg', '百事可乐(英文名称Pepsi-Cola)，诞生于19世纪90年代，以碳酸水、糖、香草、生油、胃蛋白酶（pepsin）及可乐果制成，并于1903年6月16日将之注册为商标。后来逐渐发展为美国百事公司推出的一种碳酸饮料，也是美国可口可乐公司的主要竞争对手。2016年6月8日，《2016年BrandZ全球最具价值品牌百强榜》公布，百事可乐排第86名。2017年6月，《2017年BrandZ最具价值全球品牌100强》公布，百事可乐排名第84位。作为潮流文化引领者，百事可乐始终保持着年轻、潮流的姿态，并成为每代年轻人的选择。2019年10月，Interbrand发布的全球品牌百强榜排名24。', 1, '2023-04-12 16:32:56', '2023-04-12 16:32:53', '2023-04-12 16:32:53', 0);
INSERT INTO `goods` VALUES (27, 2, 1, '罐装可口可乐', 3.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/b1599be59250405f8cc29a2c066c420a罐装可口可乐.jpg', '1886年5月8日，可口可乐在美国乔治亚州亚特兰大市诞生，自此便与社会发展相互交融，激发创新灵感，也曾协办著名的埃默里大学。它每天为全球的人们带来怡神畅快的美妙感受。进入到21世纪后，全球每天有17亿人次的消费者在畅饮可口可乐公司的产品，大约每秒钟售出19,400瓶饮料，在2016年10月，可口可乐公司排2016年全球100大最有价值品牌第三名；可口可乐为中国消费者提供超过15个品牌50多种饮料选择，其系列产品在华的每天享用量达到1.5亿杯，可口可乐自1979年重返中国市场至2014年底，已累计投资超过90亿美元，截止到2020年底在华建有43家工厂，系统员工约45,000人，其中99%为本地员工，可口可乐及其装瓶厂在中国长期以来不遗余力地支持教育及公益事业，推广环境保护以及帮助当地社区的发展，捐资总额超过2.7亿元人民币。', 1, '2023-04-12 16:34:50', '2023-04-12 16:34:47', '2023-04-12 16:34:47', 0);
INSERT INTO `goods` VALUES (28, 2, 1, '茶π', 5.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/2bdaa49178f241afa05400a71f1f2105茶pai.jpg', '茶π是中国杭州养生堂旗下的农夫山泉公司出品的品牌茶饮料。主要分为西柚茉莉花茶，蜜桃乌龙茶，柚子绿茶，柠檬红茶。', 1, '2023-04-12 16:37:21', '2023-04-12 16:37:18', '2023-04-12 16:37:18', 0);
INSERT INTO `goods` VALUES (29, 2, 1, '雀巢咖啡', 6.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/2ee58bda5e5546d49c5237e305dd79ce雀巢咖啡.jpg', '速溶咖啡的创造者：雀巢咖啡，雀巢咖啡的起源是为了帮助解决过剩的咖啡库存问题，迎来了83年历史。现今，全球消费者每一秒钟就饮用5,800杯雀巢咖啡，各式各样的咖啡美味迎合了全球消费者不同的口感和偏好。“雀巢咖啡”是世界上第一个速溶咖啡品牌，保有独特的咖啡香醇，拥有悠久的历史与传承。过去的83年间，雀巢咖啡已经从一罐咖啡发展成为如今全面的产品种类和系统。雀巢咖啡在全球180多个国家都有销售，它引领着咖啡饮料品类的潮流。2019年10月，Interbrand发布的全球品牌百强榜排名38。', 1, '2023-04-12 16:38:04', '2023-04-12 16:38:01', '2023-04-12 16:38:01', 0);
INSERT INTO `goods` VALUES (30, 2, 1, '小瓶美年达', 2.50, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/09034f4e55464612bf4bc03fa2dcc845小瓶美年达.jpg', '美年达(Mirinda)是百事可乐公司推出的一种果味碳酸饮料，百事可乐公司的荣誉产品之一，主要于北美地区发行。', 1, '2023-04-12 16:40:14', '2023-04-12 16:40:11', '2023-04-12 16:40:11', 0);
INSERT INTO `goods` VALUES (31, 2, 1, '佳得乐', 5.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/74826ee5d6f5469f9ece146574a5f9ed佳得乐.jpg', '佳得乐(Gatorade)是一种全球领先的运动型饮料，拥有五十多年的运动科学研究背景。它于1965年由佛罗里达大学(University of Florida)的研究人员研制，其名称Gatorade正是由佛罗里达大学学生的别称\"Gators\"衍生。\n\n其在补充运动中身体所缺的水和电解质的同时还提供碳水化合物来增强运动耐力。\"解口渴更解体渴\"正是佳得乐(Gatorade)的独特之处。如今，\"佳得乐\"在美国占有运动饮料行业85%份额。佳得乐(Gatorade)全心打造中国的\"运动型饮料之最\"，给广大中国消费者带来全新的健康科学理念。', 1, '2023-04-12 16:41:57', '2023-04-12 16:41:54', '2023-04-12 16:41:54', 0);
INSERT INTO `goods` VALUES (32, 2, 1, '东鹏特饮', 5.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/8e2ec5a1b0db40c18c7cab067ccb9ccc东鹏特饮.jpg', '东鹏特饮，是国产的一款维生素功能饮料，国外也叫能量饮料，富含牛磺酸、赖氨酸及多种B族维生素等营养成份，能为消费者提供充分的营养，快速补充能量。', 1, '2023-04-12 16:43:33', '2023-04-12 16:43:30', '2023-04-12 16:43:30', 0);
INSERT INTO `goods` VALUES (33, 3, 1, '德芙巧克力', 2.50, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/16f616ae0a854ee5a2d509a8e0b4f3d5德芙巧克力.jpg', '德芙巧克力是世界最大宠物食品和休闲食品制造商美国跨国食品公司玛氏(Mars)公司在中国推出的系列产品之一，1989年进入中国，1995年成为中国巧克力领导品牌，\"牛奶香浓，丝般感受\"成为经典广告语。 然而，消费者食用德芙巧克力时发现异物不是一两次。2013年1月初，有消费者反映吃德芙巧克力时发现活蛆。1月6日，德芙巧克力官方声明道歉，表示将取走该样品调查，并给予10倍价格赔偿。', 1, '2023-04-12 16:46:15', '2023-04-12 16:46:12', '2023-04-12 16:46:12', 0);
INSERT INTO `goods` VALUES (34, 3, 1, '士力架', 4.50, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/61c8a036827c410cbf50d172f576b818士力架.jpg', '士力架是玛氏公司出品的巧克力产品，属于热量型巧克力品牌，口味甜中带咸，1930年在美国上市。士力架花生夹心巧克力是热量型巧克力的典型代表。包装分别为最小的20g装、稍大的35g装、51g装、70g两条装、175g多条装、240g劲享满足装和460g畅享全家桶。', 1, '2023-04-15 03:38:49', '2023-04-12 16:47:53', '2023-04-12 16:47:53', 0);
INSERT INTO `goods` VALUES (35, 3, 1, '溜溜梅', 4.50, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/daf6202d7fbf4b859dc5dcaf1e8ffaa4溜溜梅.jpg', '溜溜果园集团股份有限公司旗下 青梅零食品牌。总部位于安徽芜湖，2005年开始专注研制青梅食品。溜溜梅向消费者传递“中国青梅溜溜梅，含有多种天然有机酸 ] ”的品牌诉求  ，产品销售覆盖全国，并远销美国、澳大利亚、俄罗斯、日本、韩国、东南亚等几十个国家和地区。', 1, '2023-04-12 16:48:57', '2023-04-12 16:48:54', '2023-04-12 16:48:54', 0);
INSERT INTO `goods` VALUES (36, 3, 1, '可比克薯片', 3.50, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/46b939393b4a4bdd86bdd916ae636c5b可比克薯片.jpg', '可比克是福建达利集团推出的一种薯片品牌。食品名称∶可比克薯片（油炸型薯类复合膨化食品） 配料：马铃薯粉、精炼植物油、淀粉、变性淀粉、玉米粉、调味料（白砂糖 鸡肉粉、食用盐、谷氨酸钠、玉米淀粉、辣椒粉、酱油粉、复合香辛料、日落黄、柠檬黄） 食用盐、乳化剂、抗氧化剂、食用香料。执行标准 QB2353保质期10个月储藏方法 置于干燥阴凉处，避免阳光照射。', 1, '2023-04-12 16:50:03', '2023-04-12 16:50:01', '2023-04-12 16:50:01', 0);
INSERT INTO `goods` VALUES (37, 3, 1, '干脆面', 1.50, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/a776ecc596aa4f88870f21c3e51cb999干脆面.jpg', '干脆面，一种即食型休闲食品。5角钱一包的干脆面，是很好的零食。以前每袋干脆面里还会有一张卡片，有三国人物卡，也有水浒人物卡。市面上的主要品牌有魔法士、小浣熊、小当家等。', 1, '2023-04-12 16:52:14', '2023-04-12 16:52:11', '2023-04-12 16:52:11', 0);
INSERT INTO `goods` VALUES (38, 3, 1, '玉米火腿肠', 2.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/01aa9e6ef18e45ee93bf0f8db657817c玉米火腿肠.jpg', '玉米火腿肠，以水果玉米、火腿肠为主料的食品。', 1, '2023-04-12 16:55:18', '2023-04-12 16:55:15', '2023-04-12 16:55:15', 0);
INSERT INTO `goods` VALUES (39, 3, 1, '劲仔小鱼', 1.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/1a72d63aad6c400cb69d3ff911781eae劲仔小鱼干.jpg', '劲仔小鱼采用秘方卤制，出口26国，热销60亿包，   是全球销量领先的卤味零食。', 1, '2023-04-12 16:57:33', '2023-04-12 16:57:30', '2023-04-12 16:57:30', 0);
INSERT INTO `goods` VALUES (40, 4, 1, '紫山到饭点自热米饭', 24.90, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/e908c4d3765b4df2a34055f2c2992627到饭点自热米饭.jpg', '方便米饭是指由工业化大规模生产的，在食用前只须做简单烹调或者直接可食用，风味、口感、外形与普通米饭一致的主食食品。方便米饭食用方便、携带方便，有天然大米饭香味。 方便米饭主要有脱水干燥型、半干型、冷冻型、罐头型四种。\n方便米饭有多种类型，生产工艺不尽相同，均要求煮好的米饭米粒完整、轮廓分明、软而结实、不黏不连，并保持大米饭的正常香味。', 1, '2023-04-12 16:59:53', '2023-04-12 16:59:51', '2023-04-12 16:59:51', 0);
INSERT INTO `goods` VALUES (41, 4, 1, '自热煲仔饭', 23.90, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/46018be3afd74ad2be2eb07ba2cbdc4a自热煲仔饭.jpg', '方便米饭是指由工业化大规模生产的，在食用前只须做简单烹调或者直接可食用，风味、口感、外形与普通米饭一致的主食食品。方便米饭食用方便、携带方便，有天然大米饭香味。 方便米饭主要有脱水干燥型、半干型、冷冻型、罐头型四种。\n方便米饭有多种类型，生产工艺不尽相同，均要求煮好的米饭米粒完整、轮廓分明、软而结实、不黏不连，并保持大米饭的正常香味。', 1, '2023-04-12 17:02:19', '2023-04-12 17:02:16', '2023-04-12 17:02:16', 0);
INSERT INTO `goods` VALUES (42, 4, 1, '自热火锅', 25.90, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/27edfcb023354713a76a8b214348daa7自热火锅.jpg', '自热火锅是一种速食品。自热火锅不用火也不插电，它主要是由食材、食材锅、锅盖、外锅和发热包组成。其加热原理是利用发热包内的物质与水接触，释放出热量，从而使得上层食材锅内的食物吸收热量后温度升高。除“自热火锅”外，它也被称为“微火锅”“清煮火锅”“自煮火锅”等。 [1] \n自热方便火锅虽然美味、快捷，却存在一定安全隐患，自热食品的发热包属于易燃易爆品，消费者在使用时要非常小心，且坐火车和飞机都不允许携带。', 1, '2023-04-12 17:04:40', '2023-04-12 17:04:37', '2023-04-12 17:04:37', 0);
INSERT INTO `goods` VALUES (43, 4, 1, '白象红烧牛肉面', 5.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/951bdf4aecee413993a29d18afde5d3b白象红烧牛肉面.jpg', '方便面（Instant Noodles），又称快餐面、泡面、杯面、快熟面、速食面、即食面，南方一般称为碗面，香港则称之为公仔面。是一种可在短时间之内用热水泡熟食用的面制食品。广义上是指一种可在短时间之内用热水泡熟食用的面制食品，有相关的菜肴如归泡面、泡面沙拉等；狭义的方便面上通常指由面饼、调料包及油包组成的销售成品，市面上以袋装和杯或碗装居多。安藤百福在1958年发明了方便面，随着生活节奏加快及旅行需要，方便面成为现代生活不可或缺的简易食品之一。', 1, '2023-04-12 17:08:21', '2023-04-12 17:08:18', '2023-04-12 17:08:18', 0);
INSERT INTO `goods` VALUES (44, 4, 1, '葱油压缩饼干', 1.50, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/d6602f79fdaa48c2b4cebe6de41e0cbc葱油压缩饼干.jpg', '压缩饼干是以小麦粉、糖、油脂、乳制品为主要原料，经冷粉工艺调粉、辊英烘烤、冷却、粉碎、外拌，可夹入其他干果、肉松等辅料，再压缩而成的饼干。\n压缩饼干这一方便食品，可谓是一古老而有具有生命活力的产品，已从昔日用于充饥的方便食品不断朝口感风味宜人 营养均衡，并能适应在特殊的环境下食用的方向发展。', 1, '2023-04-12 17:09:42', '2023-04-12 17:09:39', '2023-04-12 17:09:39', 0);
INSERT INTO `goods` VALUES (45, 5, 1, '利群', 35.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/a2d585dff5484d7c821271e413260954利群.jpg', '利群是利群集团股份有限公司的简称，利群集团股份有限公司是一家有着80多年历史，跨地区、多业态、综合性的股份制商业企业集团。 经营范围涉及商业连锁、商业物流、酒店连锁、药品批发物流、进出口贸易、房地产等领域。 利群集团的前身--\"德源泰百货店\"始建于1933年，1956年由\"德源泰百货\"、\"永信绸布\"、\"大光明眼镜\"、\"福兴祥文具\"和\"福兴昌玻璃\"五家店铺公私合营为国营利群百货商店;1994年11月，由集团自行承建的集购物、娱乐、餐饮、住宿于一体的3.8万平米的利群商厦竣工营业，标志着利群集团\"重新创业\"的开始。', 1, '2023-04-12 17:12:38', '2023-04-12 17:12:35', '2023-04-12 17:12:35', 0);
INSERT INTO `goods` VALUES (46, 5, 1, '白沙', 15.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/36ebbd865bc2470a87e4b7606db65ef8白沙.jpg', '白沙烟是湖南的一种名烟，从低档到高档的颜色是从白色到深色，分软白沙、盒白沙、精品白沙一代、二代、软精品白沙。高档烟有红色和牌、紫色和牌、珍品白沙、和气生财、和天下等。', 1, '2023-04-12 17:14:14', '2023-04-12 17:14:12', '2023-04-12 17:14:12', 0);
INSERT INTO `goods` VALUES (47, 7, 6, '小包纸巾', 1.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/9a12b03b749c4142ba584b04fda0bb98纸巾.jpg', '纸巾（英文：Paper towel；Facial tissue），是日常生活用品，纸巾成分有氯、漂白粉、酒精、木浆及可再生资源。纸巾种类多种多样。', 1, '2023-04-12 17:15:37', '2023-04-12 17:15:35', '2023-04-12 17:15:35', 0);
INSERT INTO `goods` VALUES (48, 7, 6, '小包湿巾', 1.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/5ab4cfad021e4b7b8e4b18c9b34b7185清洁湿巾.jpg', '湿巾纸是用来擦拭皮肤的湿润的纸巾。市场上的湿巾纸大致可以分为两类：一类是本身已经被消毒，但不能消毒其他物品，里面含有护肤的成分，只能做皮肤湿润保养的。另一类是不仅本身被消毒，而且对别的物品也可起到消毒作用的消毒湿巾纸，可以用做皮肤擦伤、划伤等的消毒或杀菌。选购湿巾纸的时候一定要看清湿巾纸的功能定位。', 1, '2023-04-12 17:16:54', '2023-04-12 17:16:51', '2023-04-12 17:16:51', 0);
INSERT INTO `goods` VALUES (49, 7, 6, '雨伞', 25.90, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/a042fdc7666040bfbc1310a971945932雨伞.jpg', '伞是一种遮阳或遮蔽雨雪的工具，一般用油纸、油布或塑料布等做成。雨伞的英文Umbrella来自拉丁文的Umbra，有遮阳、阴影处的意思。\n伞的制作材料，通常包括了具延展性的布料和其他可用作骨架的材料与缠线。使用时以手将之举起，虽然伞在最初发明时的主要目的是用来阻挡阳光，但是最常被当作雨天挡雨的工具。雨伞的其它作用包括作为装饰物、拐杖甚至兵器，香港的老字号梁苏记雨伞就是可作兵器的雨伞之一。', 1, '2023-04-12 17:18:56', '2023-04-12 17:18:53', '2023-04-12 17:18:53', 0);
INSERT INTO `goods` VALUES (50, 8, 6, '口罩', 1.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/98aa723219054223b25e7ac8c3faaced口罩.jpg', '口罩是一种卫生防疫用品，一般指戴在口鼻部位用于过滤进入口鼻的空气，以达到阻挡有害的气体、气味、飞沫、病毒等物质的作用，以纱布或纸等材料做成。', 1, '2023-04-12 17:20:26', '2023-04-12 17:20:23', '2023-04-12 17:20:23', 0);
INSERT INTO `goods` VALUES (51, 8, 6, 'N95口罩', 2.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/844cae3fe2c24fe69e5e517c51129503N95口罩.jpg', '口罩是一种卫生防疫用品，一般指戴在口鼻部位用于过滤进入口鼻的空气，以达到阻挡有害的气体、气味、飞沫、病毒等物质的作用，以纱布或纸等材料做成。', 1, '2023-04-12 17:20:51', '2023-04-12 17:20:48', '2023-04-12 17:20:48', 0);
INSERT INTO `goods` VALUES (52, 8, 6, '医用酒精', 9.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/31937efb40a74bd896c0e22f7f8de869医用酒精.jpg', '医用酒精的主要成分是乙醇，并且它是混合物。医用酒精是用淀粉类植物经糖化再发酵经蒸馏制成，相当于制酒的过程，但蒸馏温度比酒低，蒸馏次数比酒多，酒精度高，制成品出量高，含酒精以外的醚、醛成分比酒多，不能饮用，但可接触人体医用。是植物原料产品。', 1, '2023-04-12 17:22:50', '2023-04-12 17:22:46', '2023-04-12 17:22:46', 0);
INSERT INTO `goods` VALUES (53, 8, 6, '创口贴', 1.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/53bda043f65c403fb8b8e86b95c90a25创口贴.jpg', '用于小创口、擦伤、切割伤等浅表性创面的护理。由医用胶带（按型号分别采用弹力布、聚乙烯膜、印刷卡通图案的聚乙烯膜和聚氨酯膜为基材，涂敷医用压敏胶制成）、吸水垫（由非织造无纺布和聚乙烯膜制成）、离型纸组成。所含成分不具有药理学作用。所含成分不可被人体吸收。产品经环氧乙烷灭菌，应无菌，一次性使用。', 1, '2023-04-12 17:25:22', '2023-04-12 17:25:19', '2023-04-12 17:25:19', 0);
INSERT INTO `goods` VALUES (54, 9, 6, '小米快充数据线', 19.90, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/e6b7d4503f8e4e6dafb670b7ccee41d0小米快充数据线.jpg', '数据线，是连接硬盘与主板必需的组件，通常会在购买主板时附送。每条数据线只能连接两个IDE装置(比如光驱、硬盘、刻录机等)', 1, '2023-04-12 17:26:56', '2023-04-12 17:26:53', '2023-04-12 17:26:53', 0);
INSERT INTO `goods` VALUES (55, 9, 6, '小米充电宝', 59.90, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/7ff6282791cd4ec5afdda47e015dda53小米充电宝.jpg', '行动电源、行动充电器（行电、行充、充电宝、尿袋、奶妈；英语：Power bank）是一种个人可随身携带，自身能储备电能，主要为手持式移动设备等消费电子产品（例如无线电话、笔记本电脑）充电的便携充电器，特别应用在没有外部电源供应的场合。其主要组成部分包括：用作电能存储的电池，稳定输出电压的电路（直流-直流转换器），绝大部分的行动电源带有充电器，用作为内置电池充电。', 1, '2023-04-12 17:29:50', '2023-04-12 17:29:47', '2023-04-12 17:29:47', 0);
INSERT INTO `goods` VALUES (56, 9, 6, 'Redmi无线蓝牙耳机', 199.90, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/6a832a3e7189489aacac698f9bd3864e红米耳机.jpg', '蓝牙耳机就是将蓝牙技术应用在免持耳机上，让使用者可以免除恼人电线的牵绊，自在地以各种方式轻松通话。自从蓝牙耳机问世以来，一直是行动商务族提升效率的好工具。', 1, '2023-04-12 17:32:49', '2023-04-12 17:32:46', '2023-04-12 17:32:46', 0);
INSERT INTO `goods` VALUES (57, 9, 6, '联想无线蓝牙耳机', 219.90, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/12/6da65065a460473cb5fdae1e4a773a4c联想蓝牙无线耳机.jpg', '蓝牙耳机就是将蓝牙技术应用在免持耳机上，让使用者可以免除恼人电线的牵绊，自在地以各种方式轻松通话。自从蓝牙耳机问世以来，一直是行动商务族提升效率的好工具。', 1, '2023-04-12 17:35:26', '2023-04-12 17:35:20', '2023-04-12 17:35:20', 0);
INSERT INTO `goods` VALUES (58, 3, 1, '脆升升薯条', 5.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/15/5b28e20d43f34f689a2dfb802ca2dbd8脆升升薯条.jpg', '脆升升 香脆马铃薯薯条，能吃出来土豆香味的薯条，采用原薯鲜切工艺，每一根薯条都保留着原汁原味的薯香，非膨化更健康，开袋即食,小包便利，四种口味多样选择。规格：100g。', 1, '2023-04-15 03:10:32', '2023-04-15 03:10:27', '2023-04-15 03:29:34', 1);
INSERT INTO `goods` VALUES (59, 3, 1, '脆升升薯条', 5.00, 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/15/401ead8199fc4e069a83bc202d01c52c脆升升薯条.jpg', '脆升升 香脆马铃薯薯条，能吃出来土豆香味的薯条，采用原薯鲜切工艺，每一根薯条都保留着原汁原味的薯香，非膨化更健康，开袋即食,小包便利，四种口味多样选择。规格：100g。', 1, '2023-04-15 03:36:26', '2023-04-15 03:36:23', '2023-04-15 03:36:23', 0);

-- ----------------------------
-- Table structure for goods_video
-- ----------------------------
DROP TABLE IF EXISTS `goods_video`;
CREATE TABLE `goods_video`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` bigint NOT NULL DEFAULT 0 COMMENT '商品ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `video_source_id` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原始文件名称',
  `duration` float NOT NULL DEFAULT 0 COMMENT '视频时长（秒）',
  `size` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '视频源文件大小（字节）',
  `version` bigint UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`goods_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品宣传视频' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of goods_video
-- ----------------------------
INSERT INTO `goods_video` VALUES (50, 25, '你真牛，红牛挺你', 'http://1309991848.vod2.myqcloud.com/4e0caab6vodcq1309991848/0138b29c243791578556292516/f0.mp4', '你真牛红牛挺你.mp4', 0, 0, 1, 0, '2023-02-02 11:41:21', '2023-04-12 17:39:53', 0);
INSERT INTO `goods_video` VALUES (51, 25, '红牛敢创敢为', 'http://1309991848.vod2.myqcloud.com/4e0caab6vodcq1309991848/a17ac059243791579147817045/f0.mp4', '红牛敢创敢为.mp4', 0, 0, 1, 0, '2023-02-02 11:44:54', '2023-04-12 17:39:53', 0);
INSERT INTO `goods_video` VALUES (52, 40, '紫山到饭点自热米饭——轻松恰好饭', 'http://1309991848.vod2.myqcloud.com/4e0caab6vodcq1309991848/036cf062243791578556378427/f0.mp4', '轻松恰好饭.mp4', 0, 0, 1, 0, '2023-02-02 11:50:18', '2023-04-12 17:39:53', 0);
INSERT INTO `goods_video` VALUES (54, 34, '士力架横扫饥饿', 'http://1309991848.vod2.myqcloud.com/4e0caab6vodcq1309991848/c0675b92243791581404321896/dFl5kX2H6FQA.mp4', '士力架横扫饥饿.mp4', 0, 0, 1, 0, '2023-04-15 03:38:38', '2023-04-15 03:38:38', 0);

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `id` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '主键',
  `title` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类别名称',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父ID',
  `sort` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序字段',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品类别' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES (1, '食品', 0, 1, '2023-04-12 16:13:56', '2023-04-12 16:13:56', 0);
INSERT INTO `subject` VALUES (2, '饮料类', 1, 1, '2023-04-12 16:13:56', '2023-04-12 16:13:56', 0);
INSERT INTO `subject` VALUES (3, '零食类', 1, 2, '2023-04-12 16:13:56', '2023-04-12 16:13:56', 0);
INSERT INTO `subject` VALUES (4, '便利食品', 1, 3, '2023-04-12 16:13:56', '2023-04-12 16:13:56', 0);
INSERT INTO `subject` VALUES (5, '香烟', 1, 4, '2023-04-12 16:13:56', '2023-04-12 16:13:56', 0);
INSERT INTO `subject` VALUES (6, '用品', 0, 2, '2023-04-12 16:13:56', '2023-04-12 16:13:56', 0);
INSERT INTO `subject` VALUES (7, '生活用品', 6, 1, '2023-04-12 16:13:56', '2023-04-12 16:13:56', 0);
INSERT INTO `subject` VALUES (8, '医用药品', 6, 2, '2023-04-12 16:13:56', '2023-04-12 16:13:56', 0);
INSERT INTO `subject` VALUES (9, '数码用品', 6, 3, '2023-04-12 16:13:56', '2023-04-12 16:13:56', 0);
INSERT INTO `subject` VALUES (10, '415测试', 6, 4, '2023-04-15 03:09:05', '2023-04-15 03:09:05', 0);
INSERT INTO `subject` VALUES (11, '415测试22', 6, 5, '2023-04-15 03:34:57', '2023-04-15 03:34:57', 0);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '司机姓名',
  `intro` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '无' COMMENT '司机简介',
  `avatar` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '司机头像',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '司机电话',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录密码',
  `open_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小程序open id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_phone`(`phone` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 299 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '讲师' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '高启强', '咖啡干嚼不加糖，京海建工高启强.', 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/03/22/8764aacde5884c1a8bcc1de5626b9421高启强1.jpg', '10086', '10086', 'o1nVa5ed2OYliWDE_DKZcIn0co1I', '2021-11-05 03:18:36', '2023-04-15 00:24:09', 0);
INSERT INTO `teacher` VALUES (2, '李有田', '弯道下山没刹车，莽村书记有田哥', 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/03/22/f01713855b8444cc91948cdb339b2dfb李有田.jpg', '18956473579', '10086', 'o1nVa5ed2OYliWDE_DKZcIn0co1I', '2021-11-05 03:18:36', '2023-01-31 15:03:17', 0);
INSERT INTO `teacher` VALUES (20, '高启盛', '手拿冻鱼追一路，我叫启盛你记住', 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/03/22/11a6fc282a3841c5ac1460c7d0b0c30f高启盛1.jpg', '15675234596', '10086', 'o1nVa5ed2OYliWDE_DKZcIn0co1I', '2023-02-02 12:17:30', '2023-02-02 12:17:30', 0);
INSERT INTO `teacher` VALUES (21, '陈书婷', '老公被埋不知情，我是大嫂陈书婷', 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/03/22/2340db42fd14485abf6001c26e58fc18陈书婷.jpg', '19845236512', '10086', 'o1nVa5ed2OYliWDE_DKZcIn0co1I', '2023-02-02 12:18:50', '2023-02-02 12:18:50', 0);
INSERT INTO `teacher` VALUES (22, '驴哥', '火眼金睛不怕死，道上叫我疯驴子', 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/03/22/1bb56fd313434304afe2fa76bde6bb5f疯驴子.jpg', '15987453621', '10086', 'o1nVa5ed2OYliWDE_DKZcIn0co1I', '2023-02-02 12:20:19', '2023-02-02 12:20:19', 0);
INSERT INTO `teacher` VALUES (23, '徐江', '头疼来自烟灰缸，我的名字叫徐江', 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/03/22/5952a41134294b27be06cfe87934d029徐江.jpg', '13798652543', '10086', 'o1nVa5ed2OYliWDE_DKZcIn0co1I', '2023-02-02 12:21:18', '2023-02-02 12:21:18', 0);
INSERT INTO `teacher` VALUES (24, '老默', '鱼摊卖鱼开箱货，我是杀手陈金默', 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/03/22/4f875676d5b843dda729a89c69438c2a老默.jpg', '15632458561', '10086', 'o1nVa5ed2OYliWDE_DKZcIn0co1I', '2023-02-02 13:05:59', '2023-02-02 13:05:59', 0);
INSERT INTO `teacher` VALUES (296, '411', '411', 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/11/57338c715b3043339ca792a5f3fa3a1a高启盛.jpg', '411', '411', 'o1nVa5ed2OYliWDE_DKZcIn0co1I', '2023-04-11 18:34:27', '2023-04-15 03:01:31', 1);
INSERT INTO `teacher` VALUES (297, '415测试222', '415测试', 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/15/e830f33509b2412585de3ba97ac071b5高启盛.jpg', '415', '415', '测试', '2023-04-15 03:05:43', '2023-04-15 03:06:02', 1);
INSERT INTO `teacher` VALUES (298, '415测试xxx', '415测试', 'https://xff-1309991848.cos.ap-guangzhou.myqcloud.com/2023/04/15/b07a78ba59964893a78b7c6456da7889高启盛.jpg', '415测试', '415测试', '415测试', '2023-04-15 03:32:10', '2023-04-15 03:32:28', 1);

-- ----------------------------
-- View structure for video_visitor_max_view
-- ----------------------------
DROP VIEW IF EXISTS `video_visitor_max_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `video_visitor_max_view` AS select max(`video_visitor`.`id`) AS `max_id`,`video_visitor`.`course_id` AS `course_id`,`video_visitor`.`video_id` AS `video_id`,`video_visitor`.`user_id` AS `user_id` from `video_visitor` group by `video_visitor`.`course_id`,`video_visitor`.`video_id`,`video_visitor`.`user_id`;

SET FOREIGN_KEY_CHECKS = 1;
