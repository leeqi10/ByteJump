/*
 Navicat Premium Data Transfer

 Source Server         : admin
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : simplybyte

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 23/02/2023 18:20:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0',
  `create_data` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `video_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '447789814@qq.com', '好哇塞', 'Thu Feb 23 13:05:08 CST 2023', '31');
INSERT INTO `comment` VALUES (2, '447789814@qq.com', '好哇塞', 'Thu Feb 23 13:19:42 CST 2023', '31');
INSERT INTO `comment` VALUES (3, '447789814@qq.com', '好哇塞', 'Thu Feb 23 13:19:59 CST 2023', '31');
INSERT INTO `comment` VALUES (4, '447789814@qq.com', '好哇塞', 'Thu Feb 23 13:22:18 CST 2023', '31');
INSERT INTO `comment` VALUES (5, '447789814@qq.com', '好哇塞', 'Thu Feb 23 13:22:48 CST 2023', '31');
INSERT INTO `comment` VALUES (6, '447789814@qq.com', '好哇塞', 'Thu Feb 23 13:23:39 CST 2023', '31');
INSERT INTO `comment` VALUES (7, 'tqq', '你好啊', 'Thu Feb 23 13:24:47 CST 2023', '31');
INSERT INTO `comment` VALUES (8, 'tqq', 'hello', 'Thu Feb 23 13:24:53 CST 2023', '31');
INSERT INTO `comment` VALUES (12, 'tqq', '很强', 'Thu Feb 23 13:25:26 CST 2023', '32');
INSERT INTO `comment` VALUES (15, 'tqq', '你好', 'Thu Feb 23 13:59:34 CST 2023', '33');
INSERT INTO `comment` VALUES (16, '447789814@qq.com', '好哇塞', 'Thu Feb 23 14:04:37 CST 2023', '31');
INSERT INTO `comment` VALUES (17, 'tqq', '好好哇', 'Thu Feb 23 14:12:29 CST 2023', '33');
INSERT INTO `comment` VALUES (20, 'tqq', '😅😅😅😅😅', 'Thu Feb 23 14:17:50 CST 2023', '32');
INSERT INTO `comment` VALUES (21, 'tqq', '😅😅😅傻狗', 'Thu Feb 23 14:17:55 CST 2023', '32');
INSERT INTO `comment` VALUES (22, 'tqq', '渣狗', 'Thu Feb 23 14:18:09 CST 2023', '33');
INSERT INTO `comment` VALUES (23, 'hello', '哈哈哈哈', 'Thu Feb 23 17:28:19 CST 2023', '32');
INSERT INTO `comment` VALUES (24, 'xjzbjbz', '哈哈哈', 'Thu Feb 23 17:31:22 CST 2023', '34');
INSERT INTO `comment` VALUES (25, 'xjzbjbz', '嗯哼', 'Thu Feb 23 17:31:29 CST 2023', '35');
INSERT INTO `comment` VALUES (26, '1234567', '。。', 'Thu Feb 23 17:34:16 CST 2023', '33');
INSERT INTO `comment` VALUES (27, '1234567', '6666', 'Thu Feb 23 17:34:27 CST 2023', '34');
INSERT INTO `comment` VALUES (28, '1234567', '牛', 'Thu Feb 23 17:35:12 CST 2023', '37');
INSERT INTO `comment` VALUES (29, '1234567', '哈哈哈哈', 'Thu Feb 23 17:35:15 CST 2023', '37');
INSERT INTO `comment` VALUES (30, '1234567', '不错呀', 'Thu Feb 23 17:35:23 CST 2023', '38');
INSERT INTO `comment` VALUES (31, '447789814@qq.com', '哈哈哈', 'Thu Feb 23 18:12:59 CST 2023', '38');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `follow_count` int UNSIGNED NOT NULL DEFAULT 0,
  `follower_count` int UNSIGNED NOT NULL DEFAULT 0,
  `is_follow` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'false',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (25, '447789814@qq.com', 0, 0, 'false', '$2a$10$MoCdaOZE.toGij8JMvf/9.CkEJzSMqaE6dpenHernAtpF3Pqop3am');
INSERT INTO `user` VALUES (26, 'tt', 0, 0, 'false', '$2a$10$Jt0UV9iCSoUBipdIdRJZbOwDylMsu2rtyZ6FlxZ3zpsbNsUHzPZxq');
INSERT INTO `user` VALUES (27, 'tqq', 0, 0, 'false', '$2a$10$8XlQOfOBeha.7MkcIGZT6ug.accGZitA7u7c1LMyTdxogtskAf5Si');
INSERT INTO `user` VALUES (28, 'hello', 0, 0, 'false', '$2a$10$FrdT4A5v/G.NY2UTWK.XNOGBwRFtOkZZNnvBhEadWLGB2Hea9WuRu');
INSERT INTO `user` VALUES (29, 'xjzbjbz', 0, 0, 'false', '$2a$10$Zck4v77fVCmrHt/wVB/mge3nFJUztE1Sxlx1NWTrG7X8eNj2dp41a');
INSERT INTO `user` VALUES (30, '1234567', 0, 0, 'false', '$2a$10$IrCX5AxBdbZKpgvtADSOdOl4SLCtPW6uIA4NEtcRC2q75k7oUXDcK');

-- ----------------------------
-- Table structure for userlikevideo
-- ----------------------------
DROP TABLE IF EXISTS `userlikevideo`;
CREATE TABLE `userlikevideo`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `video_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_favorite` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'false',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userlikevideo
-- ----------------------------
INSERT INTO `userlikevideo` VALUES (1, '31', '25', 'true');
INSERT INTO `userlikevideo` VALUES (2, '32', '25', 'false');
INSERT INTO `userlikevideo` VALUES (3, '33', '25', 'true');
INSERT INTO `userlikevideo` VALUES (4, '32', '28', 'true');
INSERT INTO `userlikevideo` VALUES (5, '31', '28', 'true');
INSERT INTO `userlikevideo` VALUES (6, '33', '28', 'true');
INSERT INTO `userlikevideo` VALUES (7, '34', '28', 'true');
INSERT INTO `userlikevideo` VALUES (8, '35', '28', 'true');
INSERT INTO `userlikevideo` VALUES (9, '31', '29', 'true');
INSERT INTO `userlikevideo` VALUES (10, '35', '29', 'true');
INSERT INTO `userlikevideo` VALUES (11, '34', '30', 'true');
INSERT INTO `userlikevideo` VALUES (12, '35', '30', 'true');
INSERT INTO `userlikevideo` VALUES (13, '33', '30', 'true');
INSERT INTO `userlikevideo` VALUES (14, '36', '30', 'true');
INSERT INTO `userlikevideo` VALUES (15, '38', '30', 'true');
INSERT INTO `userlikevideo` VALUES (16, '37', '30', 'true');
INSERT INTO `userlikevideo` VALUES (17, '31', '30', 'true');
INSERT INTO `userlikevideo` VALUES (18, '32', '30', 'true');
INSERT INTO `userlikevideo` VALUES (19, '37', '25', 'true');
INSERT INTO `userlikevideo` VALUES (20, '38', '25', 'false');
INSERT INTO `userlikevideo` VALUES (21, '34', '25', 'true');
INSERT INTO `userlikevideo` VALUES (22, '35', '25', 'true');
INSERT INTO `userlikevideo` VALUES (23, '36', '25', 'true');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '视频唯一表示',
  `play_url` varchar(124) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频播放地址',
  `cover_url` varchar(124) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频封面地址',
  `favorite_count` int NOT NULL DEFAULT 0 COMMENT '视频点赞总数',
  `comment_count` int NOT NULL DEFAULT 0 COMMENT '视频评论总数',
  `is_favorite` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'false' COMMENT '点赞或者为点赞',
  `title` varchar(124) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频标题',
  `user_name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '视频作者的名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES (31, 'http://192.168.1.105:8084/video/25/share_2e1ced9e7830b8293c177ebe16c9d256.mp4', 'http://192.168.1.105:8084/Cover/25/share_2e1ced9e7830b8293c177ebe16c9d256.png', 4, 9, 'false', '你好', '447789814@qq.com');
INSERT INTO `video` VALUES (32, 'http://192.168.1.105:8084/video/26/share_9e6d04477343491aea4ecd22389ae56f.mp4', 'http://192.168.1.105:8084/Cover/26/share_9e6d04477343491aea4ecd22389ae56f.png', 2, 4, 'false', '测试3', 'tt');
INSERT INTO `video` VALUES (33, 'http://192.168.1.105:8084/video/26/1234.mp4', 'http://192.168.1.105:8084/Cover/26/1234.png', 3, 3, 'false', '流量', 'tt');
INSERT INTO `video` VALUES (34, 'http://192.168.1.105:8084/video/28/share_9d432f3e8cd64d2424eb7cb6852e0b8e.mp4', 'http://192.168.1.105:8084/Cover/28/share_9d432f3e8cd64d2424eb7cb6852e0b8e.png', 3, 1, 'false', '哈哈哈', 'hello');
INSERT INTO `video` VALUES (35, 'http://192.168.1.105:8084/video/28/share_0a8c6e0ece0ad443fd77508c8f21100b.mp4', 'http://192.168.1.105:8084/Cover/28/share_0a8c6e0ece0ad443fd77508c8f21100b.png', 4, 1, 'false', '中国', 'hello');
INSERT INTO `video` VALUES (36, 'http://192.168.1.105:8084/video/29/share_f273b0c8196a80401e4476034902fb5a.mp4', 'http://192.168.1.105:8084/Cover/29/share_f273b0c8196a80401e4476034902fb5a.png', 2, 0, 'false', '嘻嘻嘻哈哈哈', 'xjzbjbz');
INSERT INTO `video` VALUES (37, 'http://192.168.1.105:8084/video/29/share_86774aef1dba4c8ccfff374eb4f4d1f1.mp4', 'http://192.168.1.105:8084/Cover/29/share_86774aef1dba4c8ccfff374eb4f4d1f1.png', 2, 0, 'false', '嗯哼', 'xjzbjbz');
INSERT INTO `video` VALUES (38, 'http://192.168.1.105:8084/video/30/share_2e1ced9e7830b8293c177ebe16c9d256.mp4', 'http://192.168.1.105:8084/Cover/30/share_2e1ced9e7830b8293c177ebe16c9d256.png', 1, 2, 'false', '不错', '1234567');

SET FOREIGN_KEY_CHECKS = 1;
