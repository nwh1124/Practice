DROP DATABASE IF EXISTS ssgDb;

CREATE DATABASE ssgDb;

USE ssgDb;

CREATE TABLE article (
  id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  regDate DATETIME NOT NULL,
  updateDate DATETIME NOT NULL,
  title CHAR(60) NOT NULL,
  `body` LONGTEXT NOT NULL,
  recommend INT(10) UNSIGNED NOT NULL,
  memberId INT(10) UNSIGNED NOT NULL,
  boardId INT(10) UNSIGNED NOT NULL,
  hitsCount INT(10) UNSIGNED NOT NULL,
  likesCount INT(10) UNSIGNED NOT NULL,
  commentsCount INT(10) UNSIGNED NOT NULL  
) ;

INSERT  INTO `article`(`regDate`,`updateDate`,`title`,`body`,`recommend`,`memberId`,`boardId`,`hitsCount`,`likesCount`,`commentsCount`) VALUES 
('2020-12-17 13:12:03','2021-01-14 20:59:41','자바#1 변수#1 숫자','\r\n# 자바\r\n<br>\r\n## 변수#1 숫자\r\n- 자바에서 변수는 자료형(데이터형)을 갖는다\r\n- 자료형은 정수형, 실수형, 문자, 문자열, 불린 등있다\r\n- 자바에서 따옴표 없는 숫자는 그대로 숫자로 인식한다\r\n- +, -, *, / 등 키보드에 있는 기호로 사칙연산이 가능하다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nSystem.out.printf(\" 5 + 3 \");\r\nSystem.out.printf(\" 2.5 + 3.7 \");\r\nSystem.out.printf(\" 2 * 5 \");\r\n```\r\n## 결과 \r\n - 8\r\n - 6.2\r\n - 10',0,1,3,4,0,1),
('2020-12-17 16:17:50','2021-01-14 20:59:42','자바#2 변수#2 문자','# 자바\r\n<br>\r\n## 변수#2 문자\r\n- 문자형 변수에는 문자(Character)와 문자열(String)이 있다\r\n- 문자는 한 글자, 스트링은 문자 여럿을 의미한다\r\n- 문자는 작은 따옴표, 문자열은 큰 따옴표로 감싸야 한다\r\n- 공백이 포함된다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nSystem.out.printf(\'쌀\');\r\nSystem.out.printf(\"흰 쌀 밥\");\r\nSystem.out.printf(\'치킨\');\r\nSystem.out.printf(\"맥\");\r\n```\r\n## 결과\r\n - 쌀\r\n - 흰 쌀 밥\r\n - 에러\r\n - 맥\r\n<br>\r\n## 부연\r\n - 문자열을 작은 따옴표로 감싸면 에러가 난다\r\n - 문자를 큰 따옴표로 감싸는 건 에러가 나지 않는다\r\n - 한 글자도 문자열이 될 수 있다',0,1,3,0,0,0),
('2020-12-18 10:29:46','2021-01-14 20:59:42','자바#3 변수#3 그 외','# 자바\r\n<br>\r\n## 변수#3 그 외\r\n- 큰 따옴표를 문자열에 넣기 위해서는 역슬래시를 사용해야 한다\r\n- \\n을 쓰면 문자열에 줄바꿈을 삽입할 수 있다\r\n- 숫자를 더하는 것처럼 문자열끼리도 더하기가 가능하다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nSystem.out.printf(\"\"Waaaaaaaagh!!\"\");\r\nSystem.out.printf(\"그\\n님\\n티\\n\");\r\nSystem.out.printf(\"치킨\"+\"맥주\"+\"=치맥\");\r\n```\r\n## 결과\r\n - \"Waaaaaaaagh!!\"\r\n - 그\r\n - 님 \r\n - 티\r\n - 치킨맥주=치맥',0,1,3,0,0,0),
('2020-12-18 10:31:49','2021-01-14 20:59:42','자바#4 변수#4 배경지식#1 정수','# 자바\r\n<br>\r\n## 변수#4 배경지식#1 정수\r\n- 변수에는 문자, 숫자 등 데이터를 담을 수 있다\r\n- 일반적으로 명령어가 끝났을 때는 세미콜론(;)을 사용한다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nint a;\r\na = 2;\r\nSystem.out,printf(a+1);\r\n```\r\n## 결과\r\n - 3\r\n<br>\r\n## 부연\r\n- int a는 a를 정수형 변수로 선언한 것이다\r\n- a = 2는 정수형 변쉐 a에 정수 2를 할당한 것이다\r\n- a(=2)에 + 1한 값을 출력하므로 결과가 3이 나온다\r\n- 정수형 변수는 주로 int가 쓰이지만 더 큰 값을 저장할 수 있는 long도 있다',0,1,3,0,0,0),
('2020-12-18 11:18:42','2021-01-14 20:59:43','자바#5 변수#5 배경지식#2 실수','# 자바\r\n<br>\r\n## 변수#5 배경지식#2 실수\r\n- 실수는 float, double로 선언할 수 있다\r\n- 1은 정수이고 1.1은 실수이며 두 형식은 완전히 별개로 취급된다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nint a;\r\na = 2.2;\r\nSystem.out,printf(a+1.1);\r\n```\r\n## 결과\r\n - 에러\r\n<br>\r\n## 부연\r\n- 자료형에 맞지 않는 데이터를 할당할 경우 에러가 발생한다',0,1,3,3,0,1),
('2020-12-21 22:21:33','2021-01-14 20:59:43','자바#6 변수#6 배경지식#3 문자열','# 자바\r\n<br>\r\n## 변수#6 배경지식#2 문자열\r\n- 자료형에는 문자를 담기 위한 String이 있다\r\n- String은 덧셈 기호로 문자열을 합칠 수 있다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nString str = \"맥주 한 캔 땡긴다\";\r\na = 2.2;\r\nSystem.out,printf(str + a);\r\n```\r\n## 결과\r\n - 맥주 한 캔 땡긴다2.2\r\n## 부연\r\n- 문자열형 변수에 숫자를 더할 경우 숫자가 그대로 문자열에 추가된다\r\n- 숫자 - > 문자열로는 치환이 가능하다\r\n- 반대는 성립하지 않는다',0,1,3,0,0,0),
('2020-12-22 09:41:24','2021-01-14 20:59:44','자바#7 주석','# 자바\r\n<br>\r\n## 주석\r\n- 주석은 // 코드, /* 코드 */ 식으로 사용된다\r\n- 주석에 포함된 코드는 활성화되지 않는다\r\n- 코딩 설명을 위해 쓰이며, 일시적으로 코드를 비활성화하기 위해서도 쓰인다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nString str = \"난 치킨\";\r\nString not = \"안\";\r\nString like = \"좋아해\";\r\n\r\n// str = str + not;\r\nstr = str + like;\r\n\r\nSystem.out,printf(str);\r\n```\r\n## 결과\r\n - 난 치킨 좋아해\r\n## 부연\r\n- ctl + / 단축키로 커서가 있는 줄의 코드를 주석 처리할 수 있다\r\n- ctl + shift + / 단축키로 드래그된 코드를 전부 주석처리할 수 있다',0,1,3,0,0,0),
('2020-12-22 09:51:05','2021-01-12 11:42:10','자바#8 데이터 타입#1','# 자바\r\n<br>\r\n## 데이터 타입#1 데이터 크기\r\n- 데이터 크기의 최소 단위는 0과 1로 이루어진 bit이다\r\n- byte는 bit*8의 크기를 가진다\r\n- byte부터는 각 1024배씩 커지면서 크기 단위가 정해진다\r\n- kilo, mega, giga, tera, peta, exa ... byte로 이어진다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\n8 bit = 1 byte\r\n8,192 bit = 1024 byte = 1 kilobyte \r\n8,388,608 bit = 1024kilobyte = 1megabyte\r\n...\r\n...\r\n```\r\n## 결과\r\n - ...\r\n## 부연\r\n- byte 단위부터는 1024 배 커질 때마다 용량의 단위가 바뀐다',0,1,3,0,0,0),
('2020-12-22 10:05:12','2021-01-12 11:42:10','자바#9 데이터 타입#2','# 자바\r\n<br>\r\n## 데이터 타입#2 정수형 데이터\r\n- 정수형 데이터는 byte(1 byte), short(2 byte), int(4 byte), long(8 byte) 등이 있다\r\n- 원칙적으로는 각 상황에 맞게 적절한 정수형 데이터를 사용하면 좋다\r\n- 실질적으로는 속도도 느리지 않고 큰 수까지 표현할 수 있는 int가 범용적으로 쓰인다\r\n\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\n...\r\n```\r\n## 결과\r\n ...\r\n## 부연\r\n- int는 -2,147,483,648~2,147,483,647, 약 21억까지 표현할 수 있다\r\n- long은 -9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807, 약 900경까지 표현할 수 있다\r\n- 실수형의 경우 float은 4 byte, double은 8 byte까지 표현 가능하다',0,1,3,0,0,0),
('2020-12-22 10:20:17','2021-01-12 11:42:10','자바#10 데이터 타입#3','# 자바\r\n<br>\r\n## 데이터 타입#3 상수와 데이터 타입\r\n- 상수는 변수의 반대 의미로, 변하지 않는 값을 의미한다\r\n- int a = 2; 에서의 2가 상수이다.\r\n- 변수의 byte, short, int, long 처럼 상수에도 데이터 타입이 있다.\r\n- 정수는 기본적으로 int형이며 실수는 double형이다\r\n- 각 상수 뒤에 .F, L을 붙이거나 앞에 (float), (long)을 붙여서 형식을 지정해줄 수 있다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nint a = 2;  // a는 변수, 2는 상수이다\r\n2 = 5; // 상수인 2에는 다른 값인 5를 할당할 수 없으므로 에러가 난다\r\nfloat = 2.2; // double을 float형으로 치환할 수 없다는 에러가 난다\r\n```\r\n## 결과\r\n ...\r\n## 부연\r\n- 데이터 타입에 따른 에러는 각 타입을 따로 지정해주는 방식으로 해결할 수도 있다',0,1,3,0,0,0),
('2020-12-23 09:17:53','2021-01-12 11:42:11','자바#11 자료형 변환','# 자바\r\n<br>\r\n## 자료형 변환\r\n- 같은 값이라도 자료형 별로 bit는 다르게 저장된다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nint a = 200;\r\nfloat b = 200.0;\r\n```\r\n## 결과 \r\na bit == 00000000 00000000 00000000 11001000\r\nb bit == 01000011 01001000 00000000 00000000\r\n\r\n## 부연\r\n- 데이터의 손실이 없다면 자동 형 변환이 일어난다\r\n- int a = 2; float b = a; .. b == 2;\r\n- 위의 경우는 정수형 -> 실수형으로 변환하는 과정에서 데이터 손실이 없기 때문에 자동적으로 자료형이 변한다\r\n- 반대로 float을 int에 대입할 경우 에러가 난다\r\n- 형 변환 규칙\r\n- byte -> shot, char -> int -> long -> float -> double\r\n- 자동 형 변환이 일어나지 않는 경우도 데이터 앞에 (int)를 붙이는 것과 같은 방식으로 명시적 형 변환도 가능하다',0,1,3,0,0,0),
('2020-12-23 09:28:35','2021-01-12 11:42:11','자바#12 연산자','# 자바\r\n<br>\r\n## 연산자\r\n- 연산자에는 대입, 산술, 비교, 논리 연산자 등이 있다\r\n- 산술 연산자는 + - * / % 등이 있다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nint a = 10;\r\n\r\na = a + 10;\r\nSystem.out.println(a);\r\n\r\na = a - 10;\r\nSystem.out.println(a);\r\n\r\na = a * 10;\r\nSystem.out.println(a);\r\n\r\na = a / 10;\r\nSystem.out.println(a);\r\n\r\na = a % 10;\r\nSystem.out.println(a);\r\n\r\nString b = \"와\"\r\nString c = \"우\"\r\n\r\nSystem.out.println(b + c);\r\n```\r\n## 결과 \r\n20\r\n10\r\n100\r\n10\r\n1\r\n와우\r\n\r\n## 부연\r\n- 더하기 연산자는 문자열과 문자열을 합칠 때도 사용할 수 있다\r\n- 정수 나누기 연산 시 실수 부분은 버려진다 (3 / 10 == 3.3333... ) -> 3\r\n- 정수를 실수로 나눌 경우 자동 형 변환이 일어나고 자료형이 실수가 된다 -> 3.333...',0,1,3,0,0,0),
('2020-12-23 09:36:20','2021-01-12 11:42:11','자바#13 단항 연산자','# 자바\r\n<br>\r\n## 단항 연산자\r\n- 단항 연산자는 항이 하나인 연산자이다\r\n- -3, +2 등 양수 및 음수 표현이 가능하다\r\n- a++, b-- 등 값을 1씩 더하거나 뺄 수 있다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nint a = 10;\r\n\r\nSystem.out.println(a++);\r\n\r\nSystem.out.println(++a);\r\n\r\nSystem.out.println(--a);\r\n\r\nSystem.out.println(a--);\r\n\r\nSystem.out.println(a);\r\n```\r\n## 결과 \r\n10\r\n12\r\n11\r\n11\r\n10\r\n\r\n## 부연\r\n- ++, --와 같은 단항 연산자는 항의 앞 뒤 모두 자리할 수 있다\r\n- 항의 앞에 자리할 경우 연산 후 println이 실행된다\r\n- 항의 뒤에 자리할 경우 println 실행 후 연산이 일어난다',0,1,3,4,0,0),
('2020-12-29 18:29:25','2021-01-12 11:42:11','자바#14 Boolean','# 자바\r\n<br>\r\n## Boolean\r\n- Boolean은 참과 거짓을 의미하는 데이터 타입이다\r\n- bool이라고도 부른다\r\n- true, false 두 가지의 값을 갖는다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nboolean a = true;\r\nboolean b = false;\r\nif( a ){\r\nSystem.out.println(\"a는 참이다\");\r\n}\r\nif( b ){\r\nSystem.out.println(\"b는 참이다\");\r\n}\r\n```\r\n## 결과 \r\n- a는 참이다\r\n\r\n## 부연\r\n- 없음',0,1,3,0,0,1),
('2020-12-29 18:32:44','2021-01-12 11:42:12','자바#15 비교 연산자','# 자바\r\n<br>\r\n## 비교 연산자\r\n- 주어진 값들이 같은지, 다른지, 큰지, 작은지 등을 구분하는 연산자\r\n- 참true, 거짓false 중 하나의 결과를 가진다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\n\r\nint a = 10;\r\nint b = 20;\r\n\r\nif( a == b ){\r\nSystem.out.println(\"a와 b는 같다\");\r\n}else if( a != b ){\r\nSystem.out.println(\"a와 b는 다르다\");\r\n}\r\n\r\nif( a >= b ){\r\nSystem.out.println(\"a는 b보다 크거나 같다\");\r\n}else if( a < b ){\r\nSystem.out.println(\"a는 b보다 작다\");\r\n}\r\n\r\n```\r\n## 결과 \r\n- a와 b는 다르다\r\n- a는 b보다 작다\r\n\r\n## 부연\r\n- 관계 연산자라고도 부른다',0,1,3,25,1,2),
('2021-01-08 12:13:21','2021-01-12 11:42:12','자바#16 조건문','# 자바\r\n<br>\r\n## 조건문\r\n- if( 조건 ) { 실행할 코드 }\r\n- 특정 조건 하에서만 코드가 실행되게 설계할 수 있다\r\n- else if, else를 뒤이어 붙일 수 있다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\n\r\nif ( true ) {\r\n	System.out.println(\"Ture\");\r\n}\r\n\r\nif ( false ) {\r\n	System.out.println(\"False\");\r\n}\r\n\r\n```\r\n## 결과 \r\n- Ture\r\n<br>\r\n\r\n### 예시 #2\r\n<br>\r\n```java\r\n\r\nint a = 5;\r\nint b = 10;\r\n\r\nif ( a > b ) {\r\n	System.out.println(\"a는 b보다 크다\");\r\n}\r\n\r\nelse {\r\n	System.out.println(\"a는 b보다 작다\");\r\n}\r\n\r\n```\r\n## 결과 \r\n- a는 b보다 작다\r\n\r\n## 부연\r\n- else if는 선행하는 if문의 조건이 만족되지 않을 때만 실행된다\r\n- else는 선행하는 if, else if의 조건이 전부 만족되지 않을 때 실행된다',0,1,3,0,0,0),
('2021-01-12 11:41:58','2021-01-12 11:42:12','자바#17 논리 연산자','# 자바\r\n<br>\r\n## 논리 연산자\r\n- 값의 참, 거짓에 대한 연산자\r\n- 두 논리 값을 연산해 결과를 도출해낸다\r\n- &&는 값 모두가 참일 때만 참이 된다\r\n- ||는 값 중 하나라도 참일 때만 참이 된다\r\n- !는 값이 반대가 된다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\n\r\nboolean A true;\r\nboolean B false;\r\nboolean C true;\r\nboolean D false;\r\n\r\nif ( A && B ) {\r\n	System.out.println(\"예문 1\");\r\n}\r\n\r\nif ( A && C ) {\r\n	System.out.println(\"예문 2\");\r\n}\r\n\r\nif ( B && C ) {\r\n	System.out.println(\"예문 3\");\r\n}\r\n\r\nif ( A || B ) {\r\n	System.out.println(\"예문 4\");\r\n}\r\n\r\nif ( A || C ) {\r\n	System.out.println(\"예문 5\");\r\n}\r\n\r\nif ( B || D ) {\r\n	System.out.println(\"예문 6\");\r\n}\r\n\r\n```\r\n## 결과 \r\n- 예문 2\r\n- 예문 4\r\n- 예문 5\r\n<br>\r\n\r\n## 부연\r\n- boolean 자료형 외에도 참 거짓을 구하는 연산에는 적용할 수 있다',0,1,3,0,0,0),
('2021-01-14 20:47:48','2021-01-14 20:47:50','자바#18 반복문','# 자바\r\n<br>\r\n## 반복문(Loop)\r\n- 특정 조건 동안 코드를 반복한다\r\n- while, for 문 등이 있다\r\n<br>\r\n### 예시\r\n<br>\r\n```java\r\nint a = 0;\r\n\r\na = a + 1;\r\na = a + 2;\na = a + 3;\na = a + 4;\na = a + 5;\na = a + 6;\na = a + 7;\na = a + 8;\na = a + 9;\na = a + 10;\r\n\r\nint b = 0;\r\n\r\nfor(int i = 1; i <= 10; i++){\r\nb = b + i;\r\n}\r\n\r\nSystem.out.printf(\"a = %d, b = %d\\n\", a, b)\r\n```\r\n## 결과 \r\n- a = 55, b = 55\r\n<br>\r\n\r\n## 부연\r\n- 조건문과 break 명령어를 통해 반복문을 중단시킬 수 있다\r\n- 조건문과 continue 명령어를 통해 특정 구간 코드를 넘길 수 있다',0,1,3,0,0,0);

CREATE TABLE board (
  id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  regDate DATETIME NOT NULL,
  updateDate DATETIME NOT NULL,
  `name` CHAR(50) NOT NULL,
  `code` CHAR(50) NOT NULL  
);

INSERT  INTO `board`(`regDate`,`updateDate`,`name`,`code`) VALUES 
('2020-12-17 13:12:03','2020-12-17 13:12:03','IT','it');

CREATE TABLE ga4DataPagePath (
  id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  regDate DATETIME NOT NULL,
  updateDate DATETIME NOT NULL,
  pagePath CHAR(100) NOT NULL UNIQUE,
  hitsCount INT(10) UNSIGNED NOT NULL
);

CREATE TABLE `member` (
  id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  regDate DATETIME NOT NULL,
  updateDate DATETIME NOT NULL,
  loginId CHAR(50) NOT NULL UNIQUE,
  loginPw CHAR(200) NOT NULL,
  `name` CHAR(20) NOT NULL,
  nickname CHAR(20) NOT NULL UNIQUE,
  email CHAR(30) NOT NULL,
  phoneNum CHAR(30) NOT NULL,
  authLevel INT(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '0: 탈퇴 1: 정지: 2: 미인증 3: 인증 4: 관리자'  
);

INSERT  INTO `member`(`id`,`regDate`,`updateDate`,`loginId`,`loginPw`,`name`,`nickname`,`email`,`phoneNum`,`authLevel`) VALUES 
(1,'2020-12-17 13:12:03','2020-12-17 13:12:03','admin','admin','관리자','','','',2);

CREATE TABLE recommend (
  id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  regDate DATETIME NOT NULL,
  updateDate DATETIME NOT NULL,
  relTypeCode CHAR(30) NOT NULL,
  relId INT(10) UNSIGNED NOT NULL,
  memberId INT(10) UNSIGNED NOT NULL,
  `point` INT(1) NOT NULL  
);

CREATE TABLE reply (
  id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  regDate DATETIME NOT NULL,
  updateDate DATETIME NOT NULL,
  `body` TEXT NOT NULL,
  relTypeCode CHAR(30) NOT NULL,
  relId INT(10) UNSIGNED NOT NULL,
  memberId INT(10) UNSIGNED NOT NULL,
  delDate DATETIME DEFAULT NULL,
  delStatus INT(1) NOT NULL  
);

CREATE TABLE tag (
  id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  regDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  updateDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  relTypeCode CHAR(20) NOT NULL,
  relId INT(10) UNSIGNED NOT NULL,
  `body` CHAR(50) NOT NULL  
);

INSERT  INTO `tag`(`id`,`regDate`,`updateDate`,`relTypeCode`,`relId`,`body`) VALUES 
(1,'2021-01-14 11:12:05','2021-01-14 11:12:05','article',2,'#SQL'),
(2,'2021-01-14 11:12:54','2021-01-14 11:12:54','article',2,'#INSERT'),
(3,'2021-01-14 11:17:24','2021-01-14 11:17:24','article',2,'#MySQL');

SELECT *
FROM article;