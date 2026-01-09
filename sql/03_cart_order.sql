/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80012 (8.0.12)
 Source Host           : localhost:3306
 Source Schema         : shopping

 Target Server Type    : MySQL
 Target Server Version : 80012 (8.0.12)
 File Encoding         : 65001

 Date: 24/12/2025 21:23:06
*/

-- Create database if not exists
CREATE DATABASE IF NOT EXISTS `shopping` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Use the database
USE `shopping`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cartinfo
-- ----------------------------
DROP TABLE IF EXISTS `cartinfo`;
CREATE TABLE `cartinfo`  (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(10) NOT NULL,
  `goods_id` int(20) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 1,
  `allPrice` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`cart_id`) USING BTREE,
  INDEX `fk_customer_id`(`customer_id` ASC) USING BTREE,
  INDEX `fk_goods_id`(`goods_id` ASC) USING BTREE,
  CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goodsinfo` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3022 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cartinfo
-- ----------------------------
INSERT INTO `cartinfo` VALUES
(3005, 1, 1003, 3, 299.40),
(3006, 1, 1013, 1, 269.00),
(3007, 1, 1015, 2, 222.22),
(3008, 4, 1014, 2, 214.78),
(3009, 4, 1016, 4, 63.60),
(3010, 2, 1010, 2, 599.98),
(3011, 2, 1001, 1, 133.00),
(3012, 2, 1018, 2, 27.04),
(3013, 1, 1019, 1, 21.80),
(3014, 1, 1017, 3, 14.40),
(3016, 4, 1003, 9, 898.20);

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `customer_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE,
  UNIQUE INDEX `id_card`(`id_card` ASC) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `phone`(`phone` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES
(1, 'Zhang San', '411628200003213829', 'myidkadi', '123456', '18293839202'),
(2, 'Zzz', '411628200401292923', 'efrbgfvdcsd', 'efrbgrg3243teb', '17284938293'),
(4, 'Zhang Erniu', '422345844957687960', 'whrigvdsv7898', 'h6y7iuky34', '12323465789');


-- ----------------------------
-- Table structure for goodsinfo
-- ----------------------------
DROP TABLE IF EXISTS `goodsinfo`;
CREATE TABLE `goodsinfo`  (
  `goods_id` int(20) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `pic` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `manufacturer` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1029 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodsinfo
-- ----------------------------
INSERT INTO `goodsinfo` VALUES
(1001, 'Warm Winter Sweater', '001.png', 'Henan Cotton Factory', 133.00, 'Clothing',
 'A warm sweater that brings comfort and care in winter.'),

(1003, 'Men Winter Hooded Padded Jacket', '002.png', 'VUUG Official Store', 99.80, 'Clothing',
 'A high-quality winter jacket with thick padding and stylish design.'),

(1008, 'Winter Cotton Slippers', '006.png', 'Hongxiang Dachang', 23.20, 'Shoes',
 'Comfortable and warm slippers suitable for indoor winter use.'),

(1010, 'Warrior Winter Sports Shoes', '003.png', 'Warrior Official Store', 299.99, 'Shoes',
 'Warm and durable winter sports shoes for outdoor activities.'),

(1011, 'Waterproof Cotton Slippers', '005.png', 'Runrou Official Store', 24.60, 'Shoes',
 'Waterproof and anti-slip cotton slippers for winter.'),

(1013, 'FIIL Key Max Noise Cancelling Headphones', '009.png', 'FIIL Official Store', 269.00, 'Electronics',
 'High-quality wireless headphones with excellent noise cancellation and sound performance.'),

(1014, 'Qianhuan VR Headset 2025 Edition', '010.png', 'Qianhuan VR Store', 107.39, 'Electronics',
 'Cost-effective VR headset suitable for movies and games.'),

(1015, 'Smart 5G Kids Watch', '011.png', 'Suibian Store', 111.11, 'Electronics',
 'Smart watch with calling, internet access, and multimedia functions for students.'),

(1016, 'Sleep Aid Tablets', '012.png', 'Tongren Pharmacy', 15.90, 'Medicine',
 'Helps improve sleep quality and relieve insomnia.'),

(1017, 'Helicobacter Pylori Treatment Medicine', '013.png', 'Xiuhe Pharmacy', 4.80, 'Medicine',
 'Effective medicine for treating Helicobacter pylori infection.'),

(1018, 'Fresh Navel Oranges (10 pcs)', '015.png', 'Daily Deals Factory', 13.52, 'Fruit',
 'Fresh and juicy navel oranges directly harvested from the orchard.'),

(1019, 'Fresh Kiwi Fruit Box', '016.png', 'Daily Deals Factory', 21.80, 'Fruit',
 'Sweet and juicy green kiwi fruits, perfect for daily consumption.'),

(1025, 'Henan Agricultural University Xuchang Campus', '004.jpg', 'Group Two', 9.90, 'Clothing',
 'This is a great university!');


-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(10) NOT NULL,
  `goods_id` int(20) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 1,
  `total_amount` decimal(10, 2) NOT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Pending',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `fk_customer_id2`(`customer_id` ASC) USING BTREE,
  INDEX `fk_goods_id2`(`goods_id` ASC) USING BTREE,
  CONSTRAINT `fk1_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_customer_id2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_goods_id2` FOREIGN KEY (`goods_id`) REFERENCES `goodsinfo` (`goods_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1021 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
INSERT INTO `orderinfo` VALUES
(1010, 1, 1001, 1, 133.00, 'Completed', '2025-12-24 14:53:33'),
(1011, 1, 1008, 3, 69.60, 'Completed', '2025-12-24 15:03:20'),
(1012, 1, 1014, 1, 107.39, 'Shipped', '2025-12-24 15:06:50'),
(1013, 1, 1019, 3, 65.40, 'Processing', '2025-12-24 17:40:50'),
(1014, 1, 1003, 1, 99.80, 'Pending', '2025-12-24 17:41:59'),
(1015, 1, 1014, 1, 107.39, 'Pending', '2025-12-24 17:42:37'),
(1016, 4, 1008, 2, 46.40, 'Completed', '2025-12-24 18:10:46'),
(1017, 4, 1013, 1, 269.00, 'Shipped', '2025-12-24 18:10:57');


-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin (default: admin/admin123)
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin123', 'Administrator', '2025-12-24 00:00:00');

-- ----------------------------
-- View structure for v_order_detail (admin order view)
-- ----------------------------
DROP VIEW IF EXISTS `v_order_detail`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_order_detail` AS 
SELECT 
    a.order_id, 
    a.customer_id, 
    c.name AS customer_name,
    c.phone AS customer_phone,
    a.goods_id, 
    b.pic, 
    b.goods_name, 
    b.price, 
    a.quantity, 
    a.total_amount, 
    a.status,
    a.created_at 
FROM orderinfo a 
JOIN goodsinfo b ON a.goods_id = b.goods_id
JOIN customers c ON a.customer_id = c.customer_id
ORDER BY a.created_at DESC;

-- ----------------------------
-- View structure for goods_cart_view
-- ----------------------------
DROP VIEW IF EXISTS `goods_cart_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `goods_cart_view` AS select `a`.`cart_id` AS `cart_id`,`a`.`customer_id` AS `customer_id`,`b`.`goods_id` AS `goods_id`,`b`.`pic` AS `pic`,`b`.`goods_name` AS `goods_name`,`b`.`price` AS `price`,`a`.`quantity` AS `quantity`,`a`.`allPrice` AS `allPrice` from (`cartinfo` `a` join `goodsinfo` `b` on((`a`.`goods_id` = `b`.`goods_id`)));

-- ----------------------------
-- View structure for v_order_goods_customer
-- ----------------------------
DROP VIEW IF EXISTS `v_order_goods_customer`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_order_goods_customer` AS select `a`.`order_id` AS `order_id`,`a`.`customer_id` AS `customer_id`,`a`.`goods_id` AS `goods_id`,`b`.`pic` AS `pic`,`b`.`goods_name` AS `goods_name`,`b`.`price` AS `price`,`a`.`quantity` AS `quantity`,`a`.`total_amount` AS `total_amount`,`a`.`created_at` AS `created_at` from (`orderinfo` `a` join `goodsinfo` `b`) where (`a`.`goods_id` = `b`.`goods_id`) order by `a`.`created_at` desc;

SET FOREIGN_KEY_CHECKS = 1;
