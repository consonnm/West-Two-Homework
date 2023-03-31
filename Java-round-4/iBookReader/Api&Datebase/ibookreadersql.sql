/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.27 : Database - ibookreader
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ibookreader` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ibookreader`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `book_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_introduction` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_author` varchar(1000) CHARACTER SET utf8 NOT NULL,
  `book_photo` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`book_name`),
  KEY `book_id` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `book_context` */

DROP TABLE IF EXISTS `book_context`;

CREATE TABLE `book_context` (
  `book_chapter` varchar(100) CHARACTER SET utf8 NOT NULL,
  `book_name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `book_context` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`book_id`),
  KEY `book_name` (`book_name`),
  CONSTRAINT `book_context_ibfk_1` FOREIGN KEY (`book_name`) REFERENCES `book` (`book_name`)
) ENGINE=InnoDB AUTO_INCREMENT=125832 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_password` varchar(100) NOT NULL,
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_name`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;

/*Table structure for table `user_book` */

DROP TABLE IF EXISTS `user_book`;

CREATE TABLE `user_book` (
  `user_name` varchar(100) NOT NULL,
  `book_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`book_id`),
  KEY `user_name` (`user_name`),
  CONSTRAINT `user_book_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
