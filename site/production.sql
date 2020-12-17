/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.17-MariaDB : Database - exam
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`exam` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `exam`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `title` char(100) NOT NULL,
  `body` text NOT NULL,
  `hit` int(10) unsigned NOT NULL,
  `recommend` int(10) unsigned NOT NULL,
  `memberId` int(10) unsigned NOT NULL,
  `boardId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `article` */

insert  into `article`(`id`,`regDate`,`updateDate`,`title`,`body`,`hit`,`recommend`,`memberId`,`boardId`) values 
(1,'2020-12-17 13:12:03','2020-12-17 13:12:03','자유 게시판 첫 글','자유 게시판 첫 내용',0,0,1,1),
(2,'2020-12-17 13:12:03','2020-12-17 13:12:03','공지사항 게시판 첫 글','공지사항 게시판 첫 내용',0,0,1,2),
(3,'2020-12-17 13:12:03','2020-12-17 13:12:03','자바#1 변수','# 자바\r\n\r\n## 변수\r\n\r\n- 임의의 값을 넣어 활용할 수 있다\r\n\r\n- 정수, 실수, 문자열, 참거짓 등의 자료형이 있다\r\n\r\n- int, float, String, boolean 등으로 변수를 선언할 수 있다\r\n\r\n### 변수 선언 예시\r\n\r\n```java\r\nint A = 10; // 정수형 변수 선언\r\nString str = \"문자열\" // 문자열 변수 선언\r\nboolean 참거짓 = false // 불린형(참거짓) 변수 선언\r\n```\r\n\r\n- 위와 같이 변수는 선언자에 의해 선언 가능하며 영문 소문자, 대문자, 한글 등을 가리지 않는다',0,0,1,3);

/*Table structure for table `board` */

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `name` char(100) NOT NULL,
  `code` char(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `board` */

insert  into `board`(`id`,`regDate`,`updateDate`,`name`,`code`) values 
(1,'2020-12-17 13:12:03','2020-12-17 13:12:03','자유','free'),
(2,'2020-12-17 13:12:03','2020-12-17 13:12:03','공지사항','notice'),
(3,'2020-12-17 13:12:03','2020-12-17 13:12:03','Java','java');

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `loginId` char(100) NOT NULL,
  `loginPw` char(100) NOT NULL,
  `name` char(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `member` */

insert  into `member`(`id`,`regDate`,`updateDate`,`loginId`,`loginPw`,`name`) values 
(1,'2020-12-17 13:12:03','2020-12-17 13:12:03','admin','admin','노원하');

/*Table structure for table `recommend` */

DROP TABLE IF EXISTS `recommend`;

CREATE TABLE `recommend` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `recommend` tinyint(1) NOT NULL,
  `articleId` int(10) unsigned NOT NULL,
  `memberId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `recommend` */

/*Table structure for table `reply` */

DROP TABLE IF EXISTS `reply`;

CREATE TABLE `reply` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `body` text NOT NULL,
  `articleId` int(10) unsigned NOT NULL,
  `memberId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `reply` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
