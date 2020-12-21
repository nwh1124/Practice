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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `article` */

insert  into `article`(`id`,`regDate`,`updateDate`,`title`,`body`,`hit`,`recommend`,`memberId`,`boardId`) values 
(1,'2020-12-17 13:12:03','2020-12-17 13:12:03','자유 게시판 첫 글','자유 게시판 첫 내용',0,0,1,1),
(2,'2020-12-17 13:12:03','2020-12-17 13:12:03','공지사항 게시판 첫 글','공지사항 게시판 첫 내용',0,0,1,2),
(3,'2020-12-17 13:12:03','2020-12-21 21:35:08','자바#1 변수#1 숫자','\r\n# 자바\r\n<br>\r\n## 변수#1 숫자\r\n- 자바에서 변수는 자료형(데이터형)을 갖는다\r\n- 자료형은 정수형, 실수형, 문자, 문자열, 불린 등있다\r\n- 자바에서 따옴표 없는 숫자는 그대로 숫자로 인식한다\r\n- +, -, *, / 등 키보드에 있는 기호로 사칙연산이 가능하다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nSystem.out.printf(\" 5 + 3 \");\r\nSystem.out.printf(\" 2.5 + 3.7 \");\r\nSystem.out.printf(\" 2 * 5 \");\r\n```\r\n## 결과 \r\n - 8\r\n - 6.2\r\n - 10',0,0,1,3),
(4,'2020-12-17 16:17:50','2020-12-21 21:36:32','자바#2 변수#2 문자','# 자바\r\n<br>\r\n## 변수#2 문자\r\n- 문자형 변수에는 문자(Character)와 문자열(String)이 있다\r\n- 문자는 한 글자, 스트링은 문자 여럿을 의미한다\r\n- 문자는 작은 따옴표, 문자열은 큰 따옴표로 감싸야 한다\r\n- 공백이 포함된다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nSystem.out.printf(\'쌀\');\r\nSystem.out.printf(\"흰 쌀 밥\");\r\nSystem.out.printf(\'치킨\');\r\nSystem.out.printf(\"맥\");\r\n```\r\n## 결과\r\n - 쌀\r\n - 흰 쌀 밥\r\n - 에러\r\n - 맥\r\n<br>\r\n## 부연\r\n - 문자열을 작은 따옴표로 감싸면 에러가 난다\r\n - 문자를 큰 따옴표로 감싸는 건 에러가 나지 않는다\r\n - 한 글자도 문자열이 될 수 있다',0,0,1,3),
(5,'2020-12-18 10:29:46','2020-12-21 21:42:49','자바#3 변수#3 그 외','# 자바\r\n<br>\r\n## 변수#3 그 외\r\n- 큰 따옴표를 문자열에 넣기 위해서는 역슬래시를 사용해야 한다\r\n- \\n을 쓰면 문자열에 줄바꿈을 삽입할 수 있다\r\n- 숫자를 더하는 것처럼 문자열끼리도 더하기가 가능하다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nSystem.out.printf(\"\"Waaaaaaaagh!!\"\");\r\nSystem.out.printf(\"그\\n님\\n티\\n\");\r\nSystem.out.printf(\"치킨\"+\"맥주\"+\"=치맥\");\r\n```\r\n## 결과\r\n - \"Waaaaaaaagh!!\"\r\n - 그\r\n - 님 \r\n - 티\r\n - 치킨맥주=치맥',0,0,1,3),
(6,'2020-12-18 10:31:49','2020-12-21 21:47:58','자바#4 변수#4 배경지식#1 정수','# 자바\r\n<br>\r\n## 변수#4 배경지식#1 정수\r\n- 변수에는 문자, 숫자 등 데이터를 담을 수 있다\r\n- 일반적으로 명령어가 끝났을 때는 세미콜론(;)을 사용한다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nint a;\r\na = 2;\r\nSystem.out,printf(a+1);\r\n```\r\n## 결과\r\n - 3\r\n<br>\r\n## 부연\r\n- int a는 a를 정수형 변수로 선언한 것이다\r\n- a = 2는 정수형 변쉐 a에 정수 2를 할당한 것이다\r\n- a(=2)에 + 1한 값을 출력하므로 결과가 3이 나온다\r\n- 정수형 변수는 주로 int가 쓰이지만 더 큰 값을 저장할 수 있는 long도 있다',0,0,1,3),
(7,'2020-12-18 11:18:42','2020-12-21 21:53:04','자바#5 변수#5 배경지식#2 실수','# 자바\r\n<br>\r\n## 변수#5 배경지식#2 실수\r\n- 실수는 float, double로 선언할 수 있다\r\n- 1은 정수이고 1.1은 실수이며 두 형식은 완전히 별개로 취급된다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nint a;\r\na = 2.2;\r\nSystem.out,printf(a+1.1);\r\n```\r\n## 결과\r\n - 에러\r\n<br>\r\n## 부연\r\n- 자료형에 맞지 않는 데이터를 할당할 경우 에러가 발생한다',0,0,1,3),
(9,'2020-12-21 22:21:33','2020-12-21 22:21:35','자바#6 변수#6 배경지식#3 문자열','# 자바\r\n<br>\r\n## 변수#6 배경지식#2 문자열\r\n- 자료형에는 문자를 담기 위한 String이 있다\r\n- String은 덧셈 기호로 문자열을 합칠 수 있다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nString str = \"맥주 한 캔 땡긴다\";\r\na = 2.2;\r\nSystem.out,printf(str + a);\r\n```\r\n## 결과\r\n - 맥주 한 캔 땡긴다2.2\r\n## 부연\r\n- 문자열형 변수에 숫자를 더할 경우 숫자가 그대로 문자열에 추가된다\r\n- 숫자 - > 문자열로는 치환이 가능하다\r\n- 반대는 성립하지 않는다',0,0,1,3);

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
