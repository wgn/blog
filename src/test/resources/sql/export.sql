CREATE DATABASE `blog` /*!40100 DEFAULT CHARACTER SET utf8 */

use blog;

-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: review
-- ------------------------------------------------------
-- Server version	5.6.24

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
  `type` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '����',
  `code` varchar(35) COLLATE utf8_bin NOT NULL COMMENT '����',
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '����',
  `parent_id` int(16) DEFAULT NULL COMMENT '�ϼ�����',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code`
--

LOCK TABLES `code` WRITE;
/*!40000 ALTER TABLE `code` DISABLE KEYS */;
INSERT INTO `code` VALUES (2,'sex','0','Ů',NULL),(3,'sex','1','��',NULL),(4,'base','base','������������',NULL),(5,'base','sex','�Ա�',NULL),(6,'base','jobType','��ҵ����',NULL),(7,'jobType','1','Ĭ����ҵ����',NULL),(9,'jobType','2','�����ҵ����',NULL);
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
  `job_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '��ҵ����',
  `job_cycle_type` varchar(35) COLLATE utf8_bin DEFAULT NULL COMMENT '��ҵ��������,���ڱ�����',
  `job_description` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '��ҵ����',
  `job_link` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '�ⲿ����',
  `job_status` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '��ҵ״̬�����ȣ�',
  `old_filename` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '�ļ�ԭ��',
  `filepath` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '��ַ���ϴ��ļ���������ҵ��',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'����','�ݶ�','����һ������','http://os.blog.163.com/common/ava.s?b=1&host=sw_wy_email',NULL,'note.zip','d173937e-aa42-4a61-a516-77cb17699778.zip');
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
  `user_id` int(16) NOT NULL COMMENT '�û�id',
  `username` varchar(24) COLLATE utf8_bin NOT NULL COMMENT '�û���',
  `password` varchar(24) COLLATE utf8_bin NOT NULL COMMENT '����',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '״̬��1-��Ч��0-��Ч�� Ĭ��ֵ1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_auth`
--

LOCK TABLES `login_auth` WRITE;
/*!40000 ALTER TABLE `login_auth` DISABLE KEYS */;
INSERT INTO `login_auth` VALUES (1,1,'admin','1234',1);
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
  `nickname` varchar(24) COLLATE utf8_bin DEFAULT NULL COMMENT '�ǳ�',
  `sex` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '�Ա�:0-Ů;1-��',
  `birthday` date DEFAULT NULL COMMENT '����',
  `address` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '��ַ',
  `email` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '��������',
  `mobile` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '�ֻ�����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'����Ա','1','1985-01-05','�����к��������ڳ�԰8�Źں�����14���������ͿƼ�','zhuani21@163.com','15810135202');
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

-- Dump completed on 2016-04-05  1:07:57
