-- MySQL dump 10.13  Distrib 8.3.0, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: codespace
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `algorithm`
--

DROP TABLE IF EXISTS `algorithm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `algorithm` (
  `algo_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '算法库id',
  `title` varchar(20) NOT NULL COMMENT '算法名',
  `content` mediumtext NOT NULL COMMENT '算法文件内容',
  `author` int unsigned NOT NULL COMMENT '作者用户id',
  `desc` varchar(500) NOT NULL COMMENT '算法简介',
  `line` int unsigned NOT NULL COMMENT '算法文件行数',
  `language` varchar(10) NOT NULL DEFAULT 'txt' COMMENT '算法文件使用的编程语言，统一为文件后缀名',
  PRIMARY KEY (`algo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='算法库表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `algorithm`
--

LOCK TABLES `algorithm` WRITE;
/*!40000 ALTER TABLE `algorithm` DISABLE KEYS */;
/*!40000 ALTER TABLE `algorithm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `algorithm_tag`
--

DROP TABLE IF EXISTS `algorithm_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `algorithm_tag` (
  `algo_id` int unsigned NOT NULL COMMENT '算法id',
  `tag` varchar(10) NOT NULL COMMENT '算法标签'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='算法库标签信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `algorithm_tag`
--

LOCK TABLES `algorithm_tag` WRITE;
/*!40000 ALTER TABLE `algorithm_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `algorithm_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(30) NOT NULL COMMENT '密码',
  `admin` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否为管理员 0为普通用户 1为管理员',
  `score` int unsigned NOT NULL DEFAULT '0' COMMENT '用户得分',
  `avatar` varchar(100) NOT NULL COMMENT '用户头像URL',
  `email` varchar(30) NOT NULL COMMENT '用户邮箱',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','123456',1,0,'https://profile-avatar.csdnimg.cn/040a5243873249ca8d978c23f7f9b883_weixin_44023153.jpg','123456@email.com'),(2,'Alice','111',0,0,'https://p9-passport.byteacctimg.com/img/mosaic-legacy/3796/2975850990~150x150.awebp','alice@email.com');
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

-- Dump completed on 2024-06-04 19:31:06
