CREATE DATABASE  IF NOT EXISTS `banco_pd` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `banco_pd`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: banco_pd
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `cargos`
--

DROP TABLE IF EXISTS `cargos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `salario` float NOT NULL,
  `permissao` int(11) NOT NULL COMMENT ' 0 - Acesso total\n 1 - Acesso de caxai\n 2 - restrito\n ',
  `descricao` varchar(45) DEFAULT NULL,
  `status` tinyint(1) NOT NULL COMMENT '0 = desligado\n1 = ligado',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargos`
--

LOCK TABLES `cargos` WRITE;
/*!40000 ALTER TABLE `cargos` DISABLE KEYS */;
INSERT INTO `cargos` VALUES (1,'1',0.01,1,'1',1),(2,'123',1.23,2,'123',1);
/*!40000 ALTER TABLE `cargos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `consulta_venda`
--

DROP TABLE IF EXISTS `consulta_venda`;
/*!50001 DROP VIEW IF EXISTS `consulta_venda`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `consulta_venda` AS SELECT 
 1 AS `resp`,
 1 AS `id`,
 1 AS `forma_pag`,
 1 AS `data_final`,
 1 AS `data_emissao`,
 1 AS `nome`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `faturamento`
--

DROP TABLE IF EXISTS `faturamento`;
/*!50001 DROP VIEW IF EXISTS `faturamento`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `faturamento` AS SELECT 
 1 AS `quantidade`,
 1 AS `data_final`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `funcionarios`
--

DROP TABLE IF EXISTS `funcionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionarios` (
  `cpf` bigint(11) NOT NULL,
  `idcargos` int(11) NOT NULL,
  `nome` varchar(55) NOT NULL,
  `telefone` bigint(11) NOT NULL,
  `data_nasc` date NOT NULL,
  `email` varchar(55) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`cpf`),
  KEY `fk_funcionarios_cargos1_idx` (`idcargos`),
  CONSTRAINT `fk_funcionarios_cargos1` FOREIGN KEY (`idcargos`) REFERENCES `cargos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionarios`
--

LOCK TABLES `funcionarios` WRITE;
/*!40000 ALTER TABLE `funcionarios` DISABLE KEYS */;
INSERT INTO `funcionarios` VALUES (11111111111,1,'1111111111111',11111111111,'2000-01-01','1111111@1.1',1),(11111111112,1,'111111111111111',11111111111,'2000-01-01','1111111@1.1',0),(12312123123,1,'Vinícios Alves de Oliveira',12312312312,'2000-01-19','1@1.1',1),(22222222255,1,'11111',22222222222,'2000-01-17','11111@11111.11111',1),(23423412312,1,'pedrin@pedrin.com',25254245343,'2000-01-09','pedrin@pedrin.com',0),(99999999999,2,'999999999',99999999999,'2000-01-03','9999@99.99',1);
/*!40000 ALTER TABLE `funcionarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `graficoproduto`
--

DROP TABLE IF EXISTS `graficoproduto`;
/*!50001 DROP VIEW IF EXISTS `graficoproduto`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `graficoproduto` AS SELECT 
 1 AS `status`,
 1 AS `data_final`,
 1 AS `nome`,
 1 AS `prec`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `listavenda`
--

DROP TABLE IF EXISTS `listavenda`;
/*!50001 DROP VIEW IF EXISTS `listavenda`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `listavenda` AS SELECT 
 1 AS `id`,
 1 AS `nome`,
 1 AS `unid_medida`,
 1 AS `preco`,
 1 AS `quantidade`,
 1 AS `idvendas`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(55) NOT NULL,
  `unid_medida` varchar(45) NOT NULL,
  `preco` float NOT NULL,
  `status` tinyint(1) NOT NULL COMMENT '0 = desligado\n1 = ligado',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (1,'1','0',313.21,1),(2,'2','1',312.31,1),(3,'3','0',123.12,1);
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `ultimavenda`
--

DROP TABLE IF EXISTS `ultimavenda`;
/*!50001 DROP VIEW IF EXISTS `ultimavenda`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `ultimavenda` AS SELECT 
 1 AS `id`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `funcionarios_cpf` bigint(11) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `senha` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuarios_funcionarios1_idx` (`funcionarios_cpf`),
  CONSTRAINT `fk_usuarios_funcionarios1` FOREIGN KEY (`funcionarios_cpf`) REFERENCES `funcionarios` (`cpf`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,12312123123,'123','123'),(4,99999999999,'99999','D3EB9A9233E52948740D7EB8C3062D14'),(5,22222222255,'11111','B0BAEE9D279D34FA1DFD71AADB908C3F');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendas`
--

DROP TABLE IF EXISTS `vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) NOT NULL,
  `forma_pag` tinyint(1) NOT NULL COMMENT '0 = dinheiro\n1 = débito\n2 = crédito',
  `data_emissao` date DEFAULT NULL,
  `data_final` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vendas_usuario1_idx` (`idusuario`),
  CONSTRAINT `fk_vendas_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas`
--

LOCK TABLES `vendas` WRITE;
/*!40000 ALTER TABLE `vendas` DISABLE KEYS */;
INSERT INTO `vendas` VALUES (1,1,0,'2018-11-21','2018-11-21'),(2,1,1,'2018-11-20','2018-11-20'),(3,1,1,'2018-11-11','2018-11-11'),(4,1,0,'2018-11-08','2018-11-08'),(5,1,0,'2018-10-30','2018-10-30'),(6,1,1,'2018-11-17','2018-11-17'),(7,1,0,'2018-11-16','2018-11-16'),(8,1,1,'2018-11-15','2018-11-15'),(9,1,0,'2018-11-14','2018-11-14'),(10,1,1,'2018-11-13','2018-11-13'),(11,1,2,'2018-11-12','2018-11-12'),(12,1,0,'2018-11-11','2018-11-11'),(13,1,2,'2018-11-10','2018-11-10'),(14,1,0,'2018-11-06','2018-11-06'),(15,1,0,'2018-11-19','2018-11-19'),(16,1,0,'2018-11-18','2018-11-18'),(17,1,1,'2018-11-09','2018-11-09'),(18,1,0,'2018-11-07','2018-11-07'),(19,1,0,'2018-11-05','2018-11-05'),(20,1,0,'2018-11-04','2018-11-04'),(21,1,0,'2018-11-03','2018-11-03'),(22,1,0,'2018-11-02','2018-11-02'),(23,1,0,'2018-11-01','2018-11-01'),(24,1,0,'2018-10-31','2018-10-31'),(25,1,1,'2018-10-29','2018-10-29'),(26,1,0,'2018-10-28','2018-10-28'),(27,1,0,'2018-10-27','2018-10-27'),(28,1,0,'2018-10-26','2018-10-26'),(29,1,0,'2018-10-25','2018-10-25'),(30,1,0,'2018-10-24','2018-10-24'),(31,1,0,'2018-10-23','2018-10-23'),(32,1,0,'2018-10-22','2018-10-22'),(33,1,0,'2018-10-21','2018-10-21'),(34,1,0,'2018-10-20','2018-10-20'),(35,1,2,'2018-11-26','2018-11-26'),(36,1,2,'2018-11-25','2018-11-25'),(37,1,2,'2018-11-24','2018-11-24'),(38,1,2,'2018-11-23','2018-11-23'),(39,1,2,'2018-11-22','2018-11-22'),(40,4,0,'2018-11-30','2018-11-30');
/*!40000 ALTER TABLE `vendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendas_has_produtos`
--

DROP TABLE IF EXISTS `vendas_has_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendas_has_produtos` (
  `idvendas` int(11) NOT NULL,
  `idprodutos` int(11) NOT NULL,
  `quantidade` float NOT NULL,
  KEY `fk_produtos_has_vendas_vendas1_idx` (`idvendas`),
  KEY `fk_produtos_has_vendas_produtos1_idx` (`idprodutos`),
  CONSTRAINT `fk_produtos_has_vendas_produtos1` FOREIGN KEY (`idprodutos`) REFERENCES `produtos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_produtos_has_vendas_vendas1` FOREIGN KEY (`idvendas`) REFERENCES `vendas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas_has_produtos`
--

LOCK TABLES `vendas_has_produtos` WRITE;
/*!40000 ALTER TABLE `vendas_has_produtos` DISABLE KEYS */;
INSERT INTO `vendas_has_produtos` VALUES (1,1,2),(2,2,2.22),(3,3,9),(4,2,3.33),(5,3,2),(6,1,1),(7,2,2.22),(8,3,3),(9,1,1),(10,2,2.22),(11,3,3),(12,1,1),(13,2,2.22),(14,3,6),(15,1,1),(16,1,1),(17,1,2),(18,1,1),(19,1,1),(20,2,2.22),(21,2,2.22),(22,3,3),(23,1,2),(24,1,1),(25,1,1),(26,1,22),(27,3,6),(28,1,11),(29,2,22.22),(30,1,11),(31,2,22.22),(32,3,11),(33,3,22),(34,1,13),(35,1,3),(36,2,6.66),(37,2,9.99),(38,2,8.88),(39,2,22.22),(40,1,3);
/*!40000 ALTER TABLE `vendas_has_produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `view_funcuser`
--

DROP TABLE IF EXISTS `view_funcuser`;
/*!50001 DROP VIEW IF EXISTS `view_funcuser`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_funcuser` AS SELECT 
 1 AS `usuario`,
 1 AS `id`,
 1 AS `cpf`,
 1 AS `idcargos`,
 1 AS `nome`,
 1 AS `telefone`,
 1 AS `data_nasc`,
 1 AS `email`,
 1 AS `status`,
 1 AS `nome_cargo`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'banco_pd'
--

--
-- Dumping routines for database 'banco_pd'
--

--
-- Final view structure for view `consulta_venda`
--

/*!50001 DROP VIEW IF EXISTS `consulta_venda`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `consulta_venda` AS select round((`p`.`preco` * `vp`.`quantidade`),2) AS `resp`,`v`.`id` AS `id`,`v`.`forma_pag` AS `forma_pag`,`v`.`data_final` AS `data_final`,`v`.`data_emissao` AS `data_emissao`,`f`.`nome` AS `nome` from ((((`vendas` `v` join `usuarios` `u` on((`v`.`idusuario` = `u`.`id`))) join `funcionarios` `f` on((`u`.`funcionarios_cpf` = `f`.`cpf`))) join `vendas_has_produtos` `vp` on((`vp`.`idvendas` = `v`.`id`))) join `produtos` `p` on((`p`.`id` = `vp`.`idprodutos`))) group by `vp`.`idvendas` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `faturamento`
--

/*!50001 DROP VIEW IF EXISTS `faturamento`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `faturamento` AS select round(sum((`p`.`preco` * `vp`.`quantidade`)),2) AS `quantidade`,`v`.`data_final` AS `data_final` from (`vendas` `v` left join (`vendas_has_produtos` `vp` join `produtos` `p` on((`vp`.`idprodutos` = `p`.`id`))) on((`v`.`id` = `vp`.`idvendas`))) group by `v`.`data_final` order by `v`.`data_final` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `graficoproduto`
--

/*!50001 DROP VIEW IF EXISTS `graficoproduto`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `graficoproduto` AS select `p`.`status` AS `status`,`v`.`data_final` AS `data_final`,`p`.`nome` AS `nome`,round((sum(`vp`.`quantidade`) * `p`.`preco`),2) AS `prec` from ((`vendas_has_produtos` `vp` join `produtos` `p` on((`vp`.`idprodutos` = `p`.`id`))) join `vendas` `v` on((`v`.`id` = `vp`.`idvendas`))) group by `vp`.`idprodutos` order by `prec` desc limit 8 */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `listavenda`
--

/*!50001 DROP VIEW IF EXISTS `listavenda`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `listavenda` AS select `p`.`id` AS `id`,`p`.`nome` AS `nome`,`p`.`unid_medida` AS `unid_medida`,`p`.`preco` AS `preco`,`vp`.`quantidade` AS `quantidade`,`vp`.`idvendas` AS `idvendas` from (`vendas_has_produtos` `vp` join `produtos` `p` on((`vp`.`idprodutos` = `p`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ultimavenda`
--

/*!50001 DROP VIEW IF EXISTS `ultimavenda`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ultimavenda` AS select max(`vendas`.`id`) AS `id` from `vendas` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_funcuser`
--

/*!50001 DROP VIEW IF EXISTS `view_funcuser`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_funcuser` AS select `usuarios`.`usuario` AS `usuario`,`usuarios`.`id` AS `id`,`funcionarios`.`cpf` AS `cpf`,`funcionarios`.`idcargos` AS `idcargos`,`funcionarios`.`nome` AS `nome`,`funcionarios`.`telefone` AS `telefone`,`funcionarios`.`data_nasc` AS `data_nasc`,`funcionarios`.`email` AS `email`,`funcionarios`.`status` AS `status`,`cargos`.`nome` AS `nome_cargo` from ((`funcionarios` join `cargos` on((`funcionarios`.`idcargos` = `cargos`.`id`))) join `usuarios` on((`usuarios`.`funcionarios_cpf` = `funcionarios`.`cpf`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-30 22:11:43
