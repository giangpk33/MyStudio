-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: mystudio
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `object`
--

DROP TABLE IF EXISTS `object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `object` (
  `METHODTYPE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `OBJECTLOCATORS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PROJECT` int(11) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_PROJECT` (`ID_PROJECT`),
  CONSTRAINT `object_ibfk_1` FOREIGN KEY (`ID_PROJECT`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `object`
--

LOCK TABLES `object` WRITE;
/*!40000 ALTER TABLE `object` DISABLE KEYS */;
INSERT INTO `object` VALUES (NULL,NULL,1,NULL,NULL),('XPATH','//a[@title=\'Close\']',23,19,'button_close_popup'),('XPATH','//li[@class=\'login-signup\']//a[@class=\'button-login\']',24,19,'button_signup_header'),('ID','user-login',25,19,'input_email_login'),('ID','user-password',26,19,'input_password_login'),('ID','btn-submit-login',27,19,'button_submit_login'),('XPATH','//span[text()=\'Khóa học của tôi\']',28,19,'span_khoahoccuatoi'),('XPATH','//div[@class=\'error-summary alert alert-warning\']//li',32,19,'li_blankpass'),('XPATH','//div[@class=\'fb-login-pc\']//a[@title=\'Facebook\']',33,19,'button_loginwithFacebook'),('ID','email',34,19,'input_email_loginFacebook'),('ID','pass',35,19,'input_pass_loginFacebook'),('ID','loginbutton',36,19,'button_login_on_FacebookPage'),('XPATH','//div[@class=\'error-summary alert alert-warning\']',37,19,'div_error_alert'),('XPATH','//a[text()=\'Quên mật khẩu?\']',38,19,'a_quenmatkhau'),('ID','recovery-form-email',39,19,'input_recovery-form-email'),('ID','btn-submit-forgot',40,19,'button_submit-forgot'),('ID','w0-info-0',41,19,'input_alert_sendedmail_resetpass'),('XPATH','//div[@class=\'k-popup-account-mb-content\']//div[@class=\'inner\']//a[text()=\'Đăng ký\']',42,19,'button_register_popup'),('XPATH','//h4[@class=\'modal-title\' and text()=\'Đăng ký\']',43,19,'h4_title_form_signup');
/*!40000 ALTER TABLE `object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `project` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DESCRIPTION` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (19,'Kyna','Các chức năng chính của Kyna');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `result` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_TESTCASE` int(11) NOT NULL,
  `TIME` varchar(50) DEFAULT NULL,
  `RESULT` varchar(45) DEFAULT NULL,
  `LOG` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (1,15,'1','Fail','adh'),(2,15,'8','Fail','adh'),(3,15,'17743','Fail','adh'),(4,15,'25550976','Fail','null'),(5,15,'25550980','Fail','Timed out after 30 seconds waiting for visibility of element located by By.className: hjkk\nBuild info: version: \'2.53.1\', revision: \'a36b8b1\', time: \'2016-06-30 17:37:03\'\nSystem info: host: \'PC\', ip: \'192.168.137.125\', os.name: \'Windows 10\', os.arch: \'amd64\', os.version: \'10.0\', java.versi'),(6,17,'9','Fail','adh'),(7,17,'43','Fail','adh'),(8,17,'849','Fail','adh'),(9,17,'989','Fail','adh'),(10,17,'425848','Fail','adh'),(11,17,'25550936','Fail','adh'),(12,17,'25550938','Fail','adh'),(13,17,'25550944','Fail','adh'),(14,17,'25550947','Pass','adh'),(15,48,'4','Pass','LOG'),(17,16,'08/05/2018 15:54:10','Pass','null'),(18,16,'20180808_030640','Pass','null'),(19,18,'20180808_033911','Pass','null'),(20,16,'20180808_034846','Fail','Error communicating with the remote browser. It may have died.\nBuild info: version: \'2.53.1\', revision: \'a36b8b1\', time: \'2016-06-30 17:37:03\'\nSystem info: host: \'PC\', ip: \'192.168.1.2\', os.name: \'Win'),(21,18,'20180808_035446','Pass','null'),(22,16,'20180808_035745','Pass','null'),(23,18,'20180808_035903','Pass','null'),(24,16,'20180808_035934','Pass','null'),(25,18,'20180808_043247','Pass','null'),(26,18,'20180808_043407','Pass','null'),(27,16,'20180808_043438','Pass','null'),(28,18,'20180808_051719','Pass','null'),(29,16,'20180808_051747','Pass','null'),(30,17,'20180808_051819','Pass','null'),(31,15,'20180810_215248','Pass','null'),(32,15,'20180810_215958','Pass','null'),(33,17,'20180810_220957','Pass','Timed out after 30 seconds waiting for visibility of element located by By.xpath: //span[text()=\'Khóa học của tôi\']\nBuild info: version: \'2.53.1\', revision: \'a36b8b1\', time: \'2016-06-30 17:37:03\'\nSyst'),(34,17,'20180810_221452','Pass','Timed out after 30 seconds waiting for visibility of element located by By.xpath: //span[text()=\'Khóa học của tôi\']\nBuild info: version: \'2.53.1\', revision: \'a36b8b1\', time: \'2016-06-30 17:37:03\'\nSyst'),(35,17,'20180811_133823','Fail','Error communicating with the remote browser. It may have died.\nBuild info: version: \'2.53.1\', revision: \'a36b8b1\', time: \'2016-06-30 17:37:03\'\nSystem info: host: \'PC\', ip: \'192.168.1.248\', os.name: \'W'),(36,17,'20180811_135125','Fail','Error communicating with the remote browser. It may have died.\nBuild info: version: \'2.53.1\', revision: \'a36b8b1\', time: \'2016-06-30 17:37:03\'\nSystem info: host: \'PC\', ip: \'192.168.1.248\', os.name: \'W'),(37,17,'20180811_135552','Fail','Unable to locate element: { method : xpath , selector : //span[text()=\'Khóa học của tôi\'] }\nCommand duration or timeout: 30.37 seconds\nFor documentation on this error, please visit: http://seleniumhq.'),(38,17,'20180811_140156','Fail','Unable to locate element: { method : xpath , selector : //span[text()=\'Khóa học của tôi\'] }\nCommand duration or timeout: 30.10 seconds\nFor documentation on this error, please visit: http://seleniumhq.'),(39,17,'20180811_140833','Fail','Unable to locate element: { method : xpath , selector : //span[text()=\'Khóa học của tôi\'] }\nCommand duration or timeout: 30.06 seconds\nFor documentation on this error, please visit: http://seleniumhq.'),(40,18,'20180811_144720','Fail','Element is not clickable at point (1086.0333251953125, 75.38333129882812). Other element would receive the click: <div style= width: auto; height: auto; display: block; opacity: 0.0442984;  class= fan'),(41,18,'20180811_145041','Pass','null'),(42,19,'20180811_152008','Fail','Error communicating with the remote browser. It may have died.\nBuild info: version: \'2.53.1\', revision: \'a36b8b1\', time: \'2016-06-30 17:37:03\'\nSystem info: host: \'PC\', ip: \'192.168.1.248\', os.name: \'W'),(43,19,'20180811_153405','Fail','expected [Đăng nhập không được để trống.]but actual[Đăng nhập không được để trống.\nSai tên đăng nhập hoặc mật khẩu] expected [Đăng nhập không được để trống.\nSai tên đăng nhập hoặc mật khẩu] but found '),(44,19,'20180811_153659','Fail','Error communicating with the remote browser. It may have died.\nBuild info: version: \'2.53.1\', revision: \'a36b8b1\', time: \'2016-06-30 17:37:03\'\nSystem info: host: \'PC\', ip: \'192.168.1.248\', os.name: \'W'),(45,19,'20180811_153746','Fail','expected [Đăng nhập không được để trống.]but actual[Đăng nhập không được để trống.\nSai tên đăng nhập hoặc mật khẩu] expected [Đăng nhập không được để trống.\nSai tên đăng nhập hoặc mật khẩu] but found '),(46,19,'20180811_154159','Fail','expected [Đăng nhập không được để trống.\nSai tên đăng nhập hoặc mật khẩu] but found [Đăng nhập không'),(47,19,'20180811_154408','Fail','expected [Đăng nhập không được để trống.\nSai tên đăng nhập hoặc mật khẩu] but found [Đăng nhập không được để trống.]'),(48,20,'20180811_154930','Pass','null'),(49,21,'20180811_155730','Pass','null'),(50,22,'20180811_161503','Fail','Unable to locate element: { method : xpath , selector : w0-info-0 }\nCommand duration or timeout: 30.04 seconds\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_e'),(51,22,'20180811_161715','Fail','Unable to locate element: { method : xpath , selector : w0-info-0 }\nCommand duration or timeout: 30.09 seconds\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_e'),(52,22,'20180811_162607','Pass','null'),(53,23,'20180811_164612','Fail','expected [Vui lòng sửa các lỗi sau đây:\nEmail không tồn tại trong hệ thống] but found [Email khong ton tai]'),(54,23,'20180811_165026','Fail','expected [Vui lòng sửa các lỗi sau đây:  Email không tồn tại trong hệ thống] but found [Vui lòng sửa các lỗi sau đây:\nEmail không tồn tại trong hệ thống]'),(55,23,'20180811_165139','Fail','expected [Vui lòng sửa các lỗi sau đây: Email không tồn tại trong hệ thống] but found [Vui lòng sửa các lỗi sau đây:\nEmail không tồn tại trong hệ thống]'),(56,23,'20180811_165303','Fail','expected [Vui lòng sửa các lỗi sau đây:Email không tồn tại trong hệ thống] but found [Vui lòng sửa các lỗi sau đây:\nEmail không tồn tại trong hệ thống]'),(57,23,'20180811_165415','Pass','null'),(58,23,'20180811_165658','Fail','expected [Vui lòng sửa các lỗi sau đây:Email không tồn tại trong hệ thống] but found [Vui lòng sửa các lỗi sau đây:\nEmail không tồn tại trong hệ thống]'),(59,23,'20180811_165806','Pass','null'),(60,24,'20180811_171048','Pass','null'),(61,15,'20180811_171327','Fail','Element is not clickable at point (1086.0333251953125, 75.38333129882812). Other element would receive the click: <div style= width: auto; height: auto; display: block; opacity: 0.0509862;  class= fan'),(62,16,'20180811_171500','Pass','null'),(63,17,'20180811_171533','Fail','Unable to locate element: { method : xpath , selector : //span[text()=\'Khóa học của tôi\'] }\nCommand duration or timeout: 30.08 seconds\nFor documentation on this error, please visit: http://seleniumhq.'),(64,18,'20180811_171624','Pass','null'),(65,19,'20180811_171648','Fail','expected [Đăng nhập không được để trống.] but found [Đăng nhập không được để trống.\nSai tên đăng nhập hoặc mật khẩu]'),(66,20,'20180811_171713','Pass','null'),(67,21,'20180811_171736','Pass','null'),(68,22,'20180811_171800','Pass','null'),(69,23,'20180811_171829','Pass','null'),(70,24,'20180811_171853','Pass','null'),(71,15,'20180811_172611','Pass','null'),(72,15,'20180811_173222','Fail','Error communicating with the remote browser. It may have died.\nBuild info: version: \'2.53.1\', revision: \'a36b8b1\', time: \'2016-06-30 17:37:03\'\nSystem info: host: \'PC\', ip: \'192.168.1.248\', os.name: \'W'),(73,15,'20180811_173959','Fail','Unable to locate element: { method : xpath , selector : //div[@class=\'fb-login-pc\']//a[@title=\'Facebook\'] }\nCommand duration or timeout: 30.08 seconds\nFor documentation on this error, please visit: ht'),(74,15,'20180811_174655','Fail','Error communicating with the remote browser. It may have died.\nBuild info: version: \'2.53.1\', revision: \'a36b8b1\', time: \'2016-06-30 17:37:03\'\nSystem info: host: \'PC\', ip: \'192.168.1.248\', os.name: \'W'),(75,15,'20180811_175041','Pass','null'),(76,16,'20180811_175118','Pass','null'),(77,17,'20180811_175148','Fail','Unable to locate element: { method : xpath , selector : //span[text()=\'Khóa học của tôi\'] }\nCommand duration or timeout: 30.13 seconds\nFor documentation on this error, please visit: http://seleniumhq.'),(78,18,'20180811_175241','Pass','null'),(79,19,'20180811_175308','Fail','expected [Đăng nhập không được để trống.] but found [Đăng nhập không được để trống.\nSai tên đăng nhập hoặc mật khẩu]'),(80,20,'20180811_175333','Pass','null'),(81,21,'20180811_175357','Pass','null'),(82,22,'20180811_175422','Pass','null'),(83,23,'20180811_175447','Pass','null'),(84,24,'20180811_175510','Pass','null'),(85,15,'20180811_200453','Pass','null'),(86,16,'20180811_200805','Fail','Element is not clickable at point (1086.0333251953125, 75.38333129882812). Other element would receive the click: <div style= width: auto; height: auto; display: block; opacity: 0.294243;  class= fanc'),(87,27,'20180811_200824','Pass','null'),(88,18,'20180811_201550','Pass','null'),(89,16,'20180811_201613','Pass','null');
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suite_case`
--

DROP TABLE IF EXISTS `suite_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `suite_case` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_TESTCASE` int(11) DEFAULT NULL,
  `ID_TESTSUITE` int(11) DEFAULT NULL,
  `ID_RESULT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suite_case`
--

LOCK TABLES `suite_case` WRITE;
/*!40000 ALTER TABLE `suite_case` DISABLE KEYS */;
INSERT INTO `suite_case` VALUES (1,15,4,NULL),(2,16,4,NULL),(3,15,5,NULL),(4,17,5,NULL),(5,16,6,NULL),(6,18,6,NULL),(7,18,7,NULL),(8,16,8,NULL),(9,17,5,24),(10,17,5,24),(11,18,0,25),(12,18,10,NULL),(13,16,10,NULL),(14,18,10,26),(15,16,10,27),(16,18,11,NULL),(17,16,11,NULL),(18,17,11,NULL),(19,18,11,28),(20,16,11,29),(21,17,11,30),(22,15,0,31),(23,15,0,32),(24,17,0,33),(25,17,0,34),(26,17,0,NULL),(27,17,0,NULL),(28,17,0,NULL),(29,17,0,NULL),(30,17,0,35),(31,17,0,NULL),(32,17,0,NULL),(33,17,0,NULL),(34,17,0,36),(35,17,0,NULL),(36,17,0,37),(37,17,0,38),(38,17,0,39),(39,18,0,40),(40,18,0,41),(41,19,0,42),(42,19,0,43),(43,19,0,44),(44,19,0,45),(45,19,0,46),(46,19,0,47),(47,20,0,48),(48,21,0,49),(49,22,0,50),(50,22,0,51),(51,22,0,52),(52,23,0,53),(53,23,0,54),(54,23,0,55),(55,23,0,56),(56,23,0,57),(57,23,0,58),(58,23,0,59),(59,24,0,60),(60,15,0,61),(61,16,0,62),(62,17,0,63),(63,18,0,64),(64,19,0,65),(65,20,0,66),(66,21,0,67),(67,22,0,68),(68,23,0,69),(69,24,0,70),(70,15,12,NULL),(71,16,12,NULL),(72,17,12,NULL),(73,18,12,NULL),(74,19,12,NULL),(75,20,12,NULL),(76,21,12,NULL),(77,22,12,NULL),(78,23,12,NULL),(79,24,12,NULL),(80,15,0,71),(81,15,13,NULL),(82,16,13,NULL),(83,17,13,NULL),(84,18,13,NULL),(85,19,13,NULL),(86,20,13,NULL),(87,21,13,NULL),(88,22,13,NULL),(89,23,13,NULL),(90,24,13,NULL),(91,15,13,72),(92,15,0,73),(93,15,0,74),(94,15,14,NULL),(95,16,14,NULL),(96,17,14,NULL),(97,18,14,NULL),(98,19,14,NULL),(99,20,14,NULL),(100,21,14,NULL),(101,22,14,NULL),(102,23,14,NULL),(103,24,14,NULL),(104,15,14,75),(105,16,14,76),(106,17,14,77),(107,18,14,78),(108,19,14,79),(109,20,14,80),(110,21,14,81),(111,22,14,82),(112,23,14,83),(113,24,14,84),(114,15,14,NULL),(115,18,14,NULL),(116,27,14,NULL),(117,15,1,NULL),(118,28,1,NULL),(119,15,0,85),(120,16,2,NULL),(121,27,2,NULL),(122,16,0,86),(123,27,0,87),(124,18,0,88),(125,16,0,89);
/*!40000 ALTER TABLE `suite_case` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testcase`
--

DROP TABLE IF EXISTS `testcase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `testcase` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ID_PROJECT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testcase`
--

LOCK TABLES `testcase` WRITE;
/*!40000 ALTER TABLE `testcase` DISABLE KEYS */;
INSERT INTO `testcase` VALUES (15,'KYNA_LOGIN_01',19),(16,'KYNA_LOGIN_02',19),(17,'KYNA_LOGIN_03',19),(18,'KYNA_LOGIN_04',19),(19,'KYNA_LOGIN_05',19),(20,'KYNA_LOGIN_06',19),(21,'KYNA_LOGIN_07',19),(22,'KYNA_LOGIN_08',19),(23,'KYNA_LOGIN_09',19),(24,'KYNA_LOGIN_10',19),(27,'Test1',19),(28,'Test2',19);
/*!40000 ALTER TABLE `testcase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testscript`
--

DROP TABLE IF EXISTS `testscript`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `testscript` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `INPUT` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `OUTPUT` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DESCRIPTION` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ID_TESTCASE` int(11) DEFAULT NULL,
  `RESULT` varchar(45) DEFAULT NULL,
  `ID_OBJECT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_OBJECT` (`ID_OBJECT`),
  CONSTRAINT `testscript_ibfk_1` FOREIGN KEY (`ID_OBJECT`) REFERENCES `object` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testscript`
--

LOCK TABLES `testscript` WRITE;
/*!40000 ALTER TABLE `testscript` DISABLE KEYS */;
INSERT INTO `testscript` VALUES (28,'OPEN_BROWSER','https://staging.kyna.vn/','','',16,'true',23),(29,'CLICK','','','',16,'true',23),(30,'CLICK','','','',16,'true',24),(31,'SENDKEY','giang.phan@kyna.vn','','',16,'true',25),(32,'SENDKEY','123456','','',16,'true',26),(33,'CLICK','','','',16,'true',27),(36,'CHECK_VISIBLE','','','',16,'true',28),(37,'CLOSE_BROWSER','','','',16,'true',23),(41,'CLOSE_BROWSER','','','',15,'',NULL),(45,'OPEN_BROWSER','https://kyna.vn','','',17,'true',1),(46,'CLICK','','','',17,'true',23),(51,'OPEN_BROWSER','https://staging.kyna.vn/','','',18,'true',1),(59,'OPEN_BROWSER','https://staging.kyna.vn/','','',15,'true',1),(60,'CLICK','','','',15,'true',23),(61,'WAIT','2000','','',15,'true',1),(62,'CLICK','','','',15,'true',24),(63,'CLICK','','','',15,'true',33),(64,'PAGE_LOAD_TIMEOUT','','','',15,'true',1),(65,'SENDKEY','takagawa123@gmail.com','','',15,'true',34),(66,'SENDKEY','Taisaolailatoi123','','',15,'true',35),(67,'CLICK','','','',15,'true',36),(68,'PAGE_LOAD_TIMEOUT','','','',15,'true',1),(69,'CHECK_VISIBLE','','','',15,'true',28),(70,'MAXIMIZE_WINDOW','','','',17,'true',1),(71,'CLICK','','','',17,'true',24),(72,'SENDKEY','Phan Giang','','',17,'true',25),(73,'SENDKEY','123456','','',17,'true',26),(74,'CLICK','','','',17,'true',27),(75,'CHECK_VISIBLE','','','',17,'false',28),(76,'CLOSE_BROWSER','','','',17,'',1),(77,'OPEN_BROWSER','https://staging.kyna.vn/','','',19,'true',1),(79,'CLICK','','','',18,'true',23),(80,'MAXIMIZE_WINDOW','','','',18,'true',1),(81,'CLICK','','','',18,'true',24),(82,'SENDKEY','giang.phan@kyna.vn','','',18,'true',25),(83,'SENDKEY','    ','','',18,'true',26),(84,'CLICK','','','',18,'true',27),(85,'VERIFY_EQUAL','Mật khẩu không được để trống.','','',18,'true',32),(86,'CLOSE_BROWSER','','','',18,'true',1),(87,'CLICK','','','',19,'true',23),(88,'MAXIMIZE_WINDOW','','','',19,'true',1),(89,'CLICK','','','',19,'true',24),(90,'SENDKEY','      ','','',19,'true',25),(91,'SENDKEY','123456','','',19,'true',26),(92,'CLICK','','','',19,'true',27),(93,'VERIFY_EQUAL','Đăng nhập không được để trống.','','',19,'false',37),(94,'CLOSE_BROWSER','','','',19,'',1),(95,'OPEN_BROWSER','https://staging.kyna.vn/','','',20,'true',1),(96,'CLICK','','','',20,'true',23),(97,'MAXIMIZE_WINDOW','','','',20,'true',1),(98,'CLICK','','','',20,'true',24),(99,'SENDKEY','giang.phan@naky.vn','','',20,'true',25),(100,'SENDKEY','123456','','',20,'true',26),(101,'CLICK','','','',20,'true',27),(102,'VERIFY_EQUAL','Sai tên đăng nhập hoặc mật khẩu','','',20,'true',37),(103,'CLOSE_BROWSER','','','',20,'true',1),(104,'OPEN_BROWSER','https://staging.kyna.vn/','','',21,'true',1),(105,'CLICK','','','',21,'true',23),(106,'MAXIMIZE_WINDOW','','','',21,'true',1),(107,'CLICK','','','',21,'true',24),(108,'SENDKEY','giang.phan@kyna.vn','','',21,'true',25),(109,'SENDKEY','000000','','',21,'true',26),(110,'CLICK','','','',21,'true',27),(111,'VERIFY_EQUAL','Sai tên đăng nhập hoặc mật khẩu','','',21,'true',37),(112,'CLOSE_BROWSER','','','',21,'true',1),(113,'OPEN_BROWSER','https://staging.kyna.vn/','','',22,'true',1),(114,'CLICK','','','',22,'true',23),(115,'MAXIMIZE_WINDOW','','','',22,'true',1),(116,'CLICK','','','',22,'true',24),(117,'CLICK','','','',22,'true',38),(118,'SENDKEY','giangpk33@gmail.com','','',22,'true',39),(119,'CLICK','','','',22,'true',40),(120,'WAIT','2000','','',22,'true',1),(121,'CHECK_VISIBLE','','','',22,'true',41),(122,'CLOSE_BROWSER','','','',22,'true',1),(123,'OPEN_BROWSER','https://staging.kyna.vn/','','',23,'true',1),(124,'CLICK','','','',23,'true',23),(125,'MAXIMIZE_WINDOW','','','',23,'true',1),(126,'CLICK','','','',23,'true',24),(127,'CLICK','','','',23,'true',38),(128,'SENDKEY','thachvanthanh@hvtc.edu.vn','','',23,'true',39),(129,'CLICK','','','',23,'true',40),(130,'VERIFY_EQUAL','Vui lòng sửa các lỗi sau đây:\nEmail không tồn tại trong hệ thống','','',23,'true',37),(131,'CLOSE_BROWSER','','','',23,'true',1),(132,'OPEN_BROWSER','https://staging.kyna.vn/','','',24,'true',1),(133,'CLICK','','','',24,'true',23),(134,'MAXIMIZE_WINDOW','','','',24,'true',1),(135,'CLICK','','','',24,'true',24),(136,'CLICK','','','',24,'true',42),(137,'CHECK_VISIBLE','','','',24,'true',43),(138,'CLOSE_BROWSER','','','',24,'true',1),(139,'CLOSE_BROWSER','','','',15,'true',1),(140,'OPEN_BROWSER','https://kyna.vn','','',27,'true',1);
/*!40000 ALTER TABLE `testscript` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testsuite`
--

DROP TABLE IF EXISTS `testsuite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `testsuite` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(200) DEFAULT NULL,
  `ID_PROJECT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testsuite`
--

LOCK TABLES `testsuite` WRITE;
/*!40000 ALTER TABLE `testsuite` DISABLE KEYS */;
INSERT INTO `testsuite` VALUES (1,'TS1',19),(2,'TS2',19),(3,'TEST SUITE 03',19),(4,'TESTSUIT04',19),(5,'Testtttt',19),(6,'HiVong',19),(7,'HiVong2',19),(8,'Hivong3',19),(9,'TESTCASE CHAY RIENG',NULL),(10,'Hivong4',19),(11,'5hsang',19),(12,'TS3',19),(13,'TS01',19),(14,'TS04',19);
/*!40000 ALTER TABLE `testsuite` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-11 20:18:51
