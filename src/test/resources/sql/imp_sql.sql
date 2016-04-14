-- MySQL dump 10.13  Distrib 5.6.19, for Win64 (x86_64)
--
-- Host: localhost    Database: review
-- ------------------------------------------------------
-- Server version	5.6.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `code`
--

DROP TABLE IF EXISTS `code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '类型',
  `code` varchar(35) COLLATE utf8_bin NOT NULL COMMENT '编码',
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `parent_id` int(16) DEFAULT NULL COMMENT '上级编码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code`
--

LOCK TABLES `code` WRITE;
/*!40000 ALTER TABLE `code` DISABLE KEYS */;
INSERT INTO `code` VALUES (2,'sex','0','女',NULL),(3,'sex','1','男',NULL),(5,'base','base','基本编码',NULL),(6,'base','sex','性别',NULL),(7,'base','jobType','作业类型',NULL),(8,'jobType','1','默认作业类型',NULL);
/*!40000 ALTER TABLE `code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `job_id` int(16) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '作业名称',
  `job_cycle_type` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '作业周期类型,周期表的外键',
  `job_description` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '作业描述',
  `job_link` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '外部链接',
  `job_status` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '作业状态（进度）',
  `old_filename` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '文件原名',
  `filepath` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '地址（上传文件，下载作业）',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'测试','未定','这是一个测试','https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png',NULL,'《物种起源 》.txt','8488a365-d793-4950-a024-9365a6b28c7e.txt');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_auth`
--

DROP TABLE IF EXISTS `login_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_auth` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(16) NOT NULL COMMENT '用户id',
  `username` varchar(24) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(24) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：true-有效；false-无效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_user_id` (`user_id`),
   UNIQUE KEY `login_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_auth`
--

LOCK TABLES `login_auth` WRITE;
/*!40000 ALTER TABLE `login_auth` DISABLE KEYS */;
INSERT INTO `login_auth` VALUES (1,1,'admin','123456',1),(2,3,'zhuani21','1234',1);
/*!40000 ALTER TABLE `login_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(24) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `sex` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '性别:0-女;1-男',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `address` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `email` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮箱',
  `mobile` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'管理员','1','1985-01-05','北京市海淀区马甸冠城园8号冠海大厦14层视信世纪科技','zhuani21@163.com','15810135202'),(3,'zhuani21',NULL,NULL,NULL,NULL,'15810135201');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-30 18:24:55
