-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: localhost    Database: sys
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `db_users`
--

DROP TABLE IF EXISTS `db_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `db_users` (
  `idusers` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `isTeacher` tinyint DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `grade` varchar(45) DEFAULT NULL,
  `DoB` varchar(12) DEFAULT NULL,
  `tuitionFee` int DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `lessonCount` int DEFAULT NULL,
  `lessonDuration` int DEFAULT NULL,
  `emailAddress` varchar(45) DEFAULT NULL,
  `contactNumber` varchar(45) DEFAULT NULL,
  `hash` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `db_users`
--

LOCK TABLES `db_users` WRITE;
/*!40000 ALTER TABLE `db_users` DISABLE KEYS */;
INSERT INTO `db_users` VALUES (0,'teacher','main','teacher','p',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'148de9c5a7a44d19e56cd9ae1a554bf67847afb0c58f6e12fa29ac7ddfca9940'),(30,'Student','One','studentOne','pass',0,'j','p2','2003-04-03',200,100,0,120,'','19394013','d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1'),(31,'Student','Two','studenttwo','password',0,'j','p2','1333-04-02',250,325,0,90,'','03495954','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8'),(32,'Student','Three','student3','student3',0,'p','p4','2004-02-04',1000,0,0,60,'','17273949','373b29d2837e83b9ca5cec712a5985843df271cc7c06e64629472f4d03c6f83c');
/*!40000 ALTER TABLE `db_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lessons`
--

DROP TABLE IF EXISTS `lessons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lessons` (
  `idlessons` int NOT NULL AUTO_INCREMENT,
  `date` varchar(10) NOT NULL,
  `time` varchar(5) NOT NULL,
  `duration` int NOT NULL,
  `idusers2` int NOT NULL,
  `isApproved` int NOT NULL DEFAULT '0',
  `isCounted` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`idlessons`),
  KEY `fk_idusers2_idx` (`idusers2`),
  CONSTRAINT `fk_idusers2` FOREIGN KEY (`idusers2`) REFERENCES `db_users` (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lessons`
--

LOCK TABLES `lessons` WRITE;
/*!40000 ALTER TABLE `lessons` DISABLE KEYS */;
INSERT INTO `lessons` VALUES (117,'2023-05-22','11:00',600,0,2,0),(122,'2025-02-11','09:00',120,30,1,0),(123,'2025-02-11','11:01',60,31,1,0);
/*!40000 ALTER TABLE `lessons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_config` (
  `variable` varchar(128) NOT NULL,
  `value` varchar(128) DEFAULT NULL,
  `set_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `set_by` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`variable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES ('diagnostics.allow_i_s_tables','OFF','2022-09-30 01:07:08',NULL),('diagnostics.include_raw','OFF','2022-09-30 01:07:08',NULL),('ps_thread_trx_info.max_length','65535','2022-09-30 01:07:08',NULL),('statement_performance_analyzer.limit','100','2022-09-30 01:07:08',NULL),('statement_performance_analyzer.view',NULL,'2022-09-30 01:07:08',NULL),('statement_truncate_len','64','2022-09-30 01:07:08',NULL);
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-11 20:51:03
