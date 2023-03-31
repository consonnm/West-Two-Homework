/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.27 : Database - order manage system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`order manage system` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `order manage system`;

/*Table structure for table `good` */

DROP TABLE IF EXISTS `good`;

CREATE TABLE `good` (
  `订单编号` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `商品编号` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `商品价格` double NOT NULL COMMENT '商品价格',
  `商品数量` int NOT NULL,
  `商品名称` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商品名称',
  `商品总价` double NOT NULL,
  `good_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`good_id`),
  KEY `good_id` (`good_id`),
  KEY `good_ibfk_1` (`订单编号`),
  CONSTRAINT `good_ibfk_1` FOREIGN KEY (`订单编号`) REFERENCES `order1` (`订单编号`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `good` */

insert  into `good`(`订单编号`,`商品编号`,`商品价格`,`商品数量`,`商品名称`,`商品总价`,`good_id`) values ('SFGGF','sfsefsf',232,32,'sfesf',7424,23),('adwad','sefse',2323,3,'sfe',6969,27),('adwad','asdad',212,12,'ada',2544,28),('adwad','sfesfe',2,213,'sedfcs',426,29);

/*Table structure for table `order1` */

DROP TABLE IF EXISTS `order1`;

CREATE TABLE `order1` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `订单编号` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `下单时间` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `订单金额` int NOT NULL,
  `下单人姓名` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`订单编号`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `order1` */

insert  into `order1`(`order_id`,`订单编号`,`下单时间`,`订单金额`,`下单人姓名`) values (18,'SFGGF','3432',2342,'sfsefs'),(20,'adsad','122',32342,'sfds'),(21,'adwad','2003.3.3',2323,'sedfse');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
