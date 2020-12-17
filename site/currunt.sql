/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.14-MariaDB : Database - ssgDb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssgDb` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `ssgDb`;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `article` */

insert  into `article`(`id`,`regDate`,`updateDate`,`title`,`body`,`hit`,`recommend`,`memberId`,`boardId`) values 
(1,'2020-12-17 13:12:03','2020-12-17 13:12:03','자유 게시판 첫 글','자유 게시판 첫 내용',0,0,1,1),
(2,'2020-12-17 13:12:03','2020-12-17 13:12:03','공지사항 게시판 첫 글','공지사항 게시판 첫 내용',0,0,1,2),
(3,'2020-12-17 13:12:03','2020-12-17 13:12:03','자바#1 변수','# 자바\r\n\r\n## 변수\r\n\r\n- 임의의 값을 넣어 활용할 수 있다\r\n\r\n- 정수, 실수, 문자열, 참거짓 등의 자료형이 있다\r\n\r\n- int, float, String, boolean 등으로 변수를 선언할 수 있다\r\n\r\n### 변수 선언 예시\r\n\r\n```java\r\nint A = 10; 		// 정수형 변수 선언\r\nString str = \"문자열\"	// 문자열 변수 선언\r\nboolean 참거짓 = false 	// 불린형(참거짓) 변수 선언\r\n```\r\n\r\n- 위와 같이 변수는 선언자에 의해 선언 가능하며 영문 소문자, 대문자, 한글 등을 가리지 않는다',0,0,1,3),
(4,'2020-12-17 16:17:50','2020-12-17 16:17:53','자바#2 조건문','# 자바\r\n\r\n## 조건문\r\n\r\n- 특정 조건 하에서만 코드가 실행된다\r\n\r\n- 분기를 설정하거나 최초, 최후 등 특정 타이밍에만 실행되는 코드 등에 쓴다\r\n\r\n### 조건문 예시\r\n\r\n```java\r\nScanner sc = new Scanner(System.in); 		// 입력을 받기 위한 Scanner 변수 선언 및 객체 생성\r\nint a = sc.nextInt(); 				// 정수형 변수 a에 정수형 입력을 받음\r\nif(a = >=20){ 					// 입력받은 정수가 20이상일 경우\r\n	System.out.printf(\"성인입니다.\");		// \"성인입니다.\" 출력\r\n}else{ 						// 정수가 20 미만일 경우\r\n	System.out.printf(\"미성년자입니다.\"); 	// \"미성년자입니다.\" 출력\r\n}\r\n```\r\n\r\n- 위와 같이 조건문이 참일 경우 코드가 실행된다\r\n\r\n- 뒤이어 else if(){} 을 사용하면 추가적으로 조건을 달 수 있다\r\n\r\n- else {} 를 사용할 경우 조건을 제외한 모든 경우에 코드가 실행된다',0,0,1,3);

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
(3,'2020-12-17 13:12:03','2020-12-17 13:12:03','IT','it');

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
