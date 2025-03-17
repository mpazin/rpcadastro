-- *********************************************************
-- DLL + Inserts
-- *********************************************************

CREATE DATABASE `rpbank` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */


-- rpbank.cadastro_pf definição
CREATE TABLE `cadastro_pf` (
  `IdPessoa` bigint NOT NULL AUTO_INCREMENT,
  `nmPessoa` varchar(100) NOT NULL,
  `cpfPessoa` varchar(14) NOT NULL,
  `rgPessoa` varchar(14) NOT NULL,
  PRIMARY KEY (`IdPessoa`)
) ENGIN


-- rpbank.carteira definição
CREATE TABLE `carteira` (
  `idCarteira` bigint NOT NULL AUTO_INCREMENT,
  `idConta` bigint NOT NULL,
  `nuConta` varchar(15) NOT NULL,
  `saldo` double DEFAULT NULL,
  `dtAlteracao` timestamp NOT NULL,
  `dtCadastro` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idCarteira`),
  KEY `carteira_conta_FK` (`idConta`),
  KEY `carteira_idCarteira_IDX` (`idCarteira`,`idConta`) USING BTREE,
  CONSTRAINT `carteira_conta_FK` FOREIGN KEY (`idConta`) REFERENCES `conta` (`idConta`)
) ENGINE=I


-- rpbank.conta definição
CREATE TABLE `conta` (
  `idConta` bigint NOT NULL AUTO_INCREMENT,
  `nuConta` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tpConta` int NOT NULL,
  `nuAgencia` int NOT NULL,
  `nuBanco` int NOT NULL,
  `flAtivo` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'N',
  `idPessoa` bigint NOT NULL,
  PRIMARY KEY (`idConta`),
  KEY `Conta_IdConta_IDX` (`idConta`,`idPessoa`) USING BTREE,
  KEY `Conta_CadastroPF_FK` (`idPessoa`),
  KEY `conta_tipoconta_FK` (`tpConta`),
  CONSTRAINT `Conta_CadastroPF_FK` FOREIGN KEY (`idPessoa`) REFERENCES `cadastro_pf` (`IdPessoa`),
  CONSTRAINT `conta_tipoconta_FK` FOREIGN KEY (`tpConta`) REFERENCES `tipo_conta` (`tpConta`)
) ENGINE=Inn


-- rpbank.historico_transacao definição
CREATE TABLE `historico_transacao` (
  `idHistoricoTransacao` bigint NOT NULL AUTO_INCREMENT,
  `cdTransacao` varchar(100) NOT NULL,
  `dataTransacao` varchar(100) NOT NULL,
  `cdEndToEnd` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `saldoPosTransacao` varchar(100) DEFAULT NULL,
  `saldoAnterior` varchar(100) DEFAULT NULL,
  `nmRemetente` varchar(100) DEFAULT NULL,
  `nuContaRemetente` varchar(100) DEFAULT NULL,
  `cpfCnpjRemetente` varchar(100) DEFAULT NULL,
  `nmDestinatario` varchar(100) DEFAULT NULL,
  `nuContaDestinatario` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `cpfCnpjDestinatario` varchar(100) DEFAULT NULL,
  `chavePixDestino` varchar(100) DEFAULT NULL,
  `chavePixRemetente` varchar(100) DEFAULT NULL,
  `valorTransacao` varchar(100) DEFAULT NULL,
  `nuAgenciaRementente` varchar(100) DEFAULT NULL,
  `nuBancoRemetente` varchar(100) DEFAULT NULL,
  `nuAgenciaDestinatario` varchar(100) DEFAULT NULL,
  `nuBancoDestinatario` varchar(100) DEFAULT NULL,
  `idConta` bigint NOT NULL,
  `descricao` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `tpTransacao` bigint DEFAULT NULL,
  `nuContaTitular` varchar(100) NOT NULL,
  PRIMARY KEY (`idHistoricoTransacao`),
  KEY `historico_transacao_conta_FK` (`idConta`),
  KEY `historico_transacao_tipo_transacaoo_FK` (`tpTransacao`),
  CONSTRAINT `historico_transacao_conta_FK` FOREIGN KEY (`idConta`) REFERENCES `conta` (`idConta`),
  CONSTRAINT `historico_transacao_tipo_transacaoo_FK` FOREIGN KEY (`tpTransacao`) REFERENCES `tipo_transacao` (`tpTransacao`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- rpbank.log_carteira definição
CREATE TABLE `log_carteira` (
  `idLogCarteira` bigint NOT NULL AUTO_INCREMENT,
  `dtLogCadastro` timestamp NOT NULL,
  `lgLogCadastro` varchar(100) NOT NULL,
  `idCarteira` bigint DEFAULT NULL,
  `idConta` bigint DEFAULT NULL,
  `nuConta` varchar(100) DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `dtCadastro` timestamp NULL DEFAULT NULL,
  `dtAlteracao` timestamp NULL DEFAULT NULL,
  `msgLogInfo` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`idLogCarteira`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- rpbank.tipo_conta definição
CREATE TABLE `tipo_conta` (
  `tpConta` int NOT NULL,
  `dsConta` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`tpConta`)
) ENGIN



-- rpbank.tipo_transacao definição
CREATE TABLE `tipo_transacao` (
  `tpTransacao` bigint NOT NULL,
  `dsTransacao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`tpTransacao`)
) ENGINE=


----  **************** INSERTS ****************** ----

INSERT INTO rpbank.cadastro_pf (nmPessoa,cpfPessoa,rgPessoa) VALUES
	 ('Tony Stark','18998004097','123456789'),
	 ('Daenerys Targaryen','36587941001','234567894'),
	 ('Velma Dinkley','42421750008','345678912'),
	 ('Anakin Skywalker','23155526050','456789321'),
	 ('Stephen King','18586452041','567893214'),
	 ('Daphne Blake','12345678912','4545454hhhhh'),
	 ('Fred Jones','85258988058','12345678');


INSERT INTO rpbank.carteira (idConta,nuConta,saldo,dtAlteracao,dtCadastro) VALUES
	 (11,'251203164359',3214.0,'2025-03-16 01:50:56','2025-03-12 00:00:00'),
	 (8,'251203010718',300.0,'2025-03-15 04:03:07','2025-03-12 00:00:00'),
	 (2,'100013',15000.0,'2025-03-15 04:03:07','2025-03-12 00:00:00'),
	 (1,'100012',4500.0,'2025-03-15 04:03:07','2025-03-15 04:03:07'),
	 (4,'100017',1250.0,'2025-03-15 04:03:07','2025-03-15 04:03:07'),
	 (3,'100015',100.0,'2025-03-15 04:03:07','2025-03-12 00:00:00'),
	 (5,'100020',0.0,'2025-03-15 04:03:07','2025-03-12 00:00:00');


INSERT INTO rpbank.conta (nuConta,tpConta,nuAgencia,nuBanco,flAtivo,idPessoa) VALUES
	 ('100012',1,10,888,'S',2),
	 ('100013',2,10,888,'S',1),
	 ('100015',2,12,888,'S',5),
	 ('100017',1,20,888,'S',3),
	 ('100020',1,25,888,'S',4),
	 ('251203010718',1,12,888,'S',8),
	 ('251203164359',1,12,888,'S',11);



INSERT INTO rpbank.historico_transacao (cdTransacao,dataTransacao,cdEndToEnd,saldoPosTransacao,saldoAnterior,nmRemetente,nuContaRemetente,cpfCnpjRemetente,nmDestinatario,nuContaDestinatario,cpfCnpjDestinatario,chavePixDestino,chavePixRemetente,valorTransacao,nuAgenciaRementente,nuBancoRemetente,nuAgenciaDestinatario,nuBancoDestinatario,idConta,descricao,tpTransacao,nuContaTitular) VALUES
	 ('RPPAY888251303170920-383','2025-03-13 17:09:23.477672','E2E888251303170920888','3212.0','2856.0','Fred Jones','251203164359','85258988058','Fred Jones','251203164359','85258988058',NULL,NULL,'356','12','888','12','888',11,'Auto Deposito',1,'251203164359'),
	 ('RPPAY888251403021745-822','2025-03-14 02:17:55.275247','E2E888251403021745888','3312.0','3212.0',NULL,'251203164359','85258988058','Fred Jones','251203164359','85258988058',NULL,NULL,'100','12','888','12','888',11,'Auto Deposito',1,'251203164359'),
	 ('RPPAY888251403025913-512','2025-03-14 02:59:14.236749','E2E888251403025913888','3462.50','3312.0',NULL,'251203164359','85258988058','Fred Jones','251203164359','85258988058',NULL,NULL,'150.50','12','888','12','888',11,'Auto Deposito',1,'251203164359'),
	 ('RPPAY888251403030144-556','2025-03-14 03:01:44.315579','E2E888251403030144888','3613.00','3462.5',NULL,'251203164359','85258988058','Fred Jones','251203164359','85258988058',NULL,NULL,'150.50','12','888','12','888',11,'Auto Deposito',1,'251203164359'),
	 ('RPPAY888251403030358-801','2025-03-14 03:03:58.174153','E2E888251403030358888','3763.50','3613.0','Fred Jones','251203164359','85258988058','Fred Jones','251203164359','85258988058',NULL,NULL,'150.50','12','888','12','888',11,'Auto Deposito',1,'251203164359'),
	 ('RPPAY888251403030420-770','2025-03-14 03:04:20.830126','E2E888251403030420888','3914.00','3763.5',NULL,NULL,NULL,'Fred Jones','251203164359','85258988058',NULL,NULL,'150.50',NULL,NULL,'12','888',11,'Auto Deposito',1,'251203164359'),
	 ('RPPAY888251403124716-563','2025-03-14 12:47:17.066842','E2E888251403124716888','3814.0','3914.0',NULL,NULL,NULL,NULL,'251203164359',NULL,NULL,NULL,'100',NULL,NULL,NULL,NULL,11,'Saque',2,'251203164359'),
	 ('RPPAY888251403125121-649','2025-03-14 12:51:44.140696','E2E888251403125120888','3714.0','3814.0',NULL,NULL,NULL,NULL,'251203164359',NULL,NULL,NULL,'100',NULL,NULL,NULL,NULL,11,'Saque',2,'251203164359'),
	 ('RPPAY888251503035254-601','2025-03-15 03:53:04.464342','E2E888251503035251888','3514.0','3714.0','Fred Jones','251203164359','85258988058','Daphne Blake','251203010718','12345678912',NULL,NULL,'200','12','888','12','888',11,'Transferencia de valor - Saida',3,'251203164359'),
	 ('RPPAY888251503035254-601','2025-03-15 03:53:10.833747','E2E888251503035251888','200.0','0.0','Fred Jones','251203164359','85258988058','Daphne Blake','251203010718','12345678912',NULL,NULL,'200','12','888','12','888',8,'Transferencia de valor - Crédito',6,'251203010718');
INSERT INTO rpbank.historico_transacao (cdTransacao,dataTransacao,cdEndToEnd,saldoPosTransacao,saldoAnterior,nmRemetente,nuContaRemetente,cpfCnpjRemetente,nmDestinatario,nuContaDestinatario,cpfCnpjDestinatario,chavePixDestino,chavePixRemetente,valorTransacao,nuAgenciaRementente,nuBancoRemetente,nuAgenciaDestinatario,nuBancoDestinatario,idConta,descricao,tpTransacao,nuContaTitular) VALUES
	 ('RPPAY888251503040307-870','2025-03-15 04:03:07.266718','E2E888251503040307888','3414.0','3714.0','Fred Jones','251203164359','85258988058','Daphne Blake','251203010718','12345678912',NULL,NULL,'300','12','888','12','888',11,'Transferencia - Saída',3,'251203164359'),
	 ('RPPAY888251503040307-870','2025-03-15 04:03:07.311692','E2E888251503040307888','300.0','0.0','Fred Jones','251203164359','85258988058','Daphne Blake','251203010718','12345678912',NULL,NULL,'300','12','888','12','888',8,'Transferencia - Crédito',6,'251203010718'),
	 ('RPPAY888251603013816-174','2025-03-16 01:38:17.118114','E2E888251603013816888','3314.0','3414.0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'100',NULL,NULL,NULL,NULL,11,'Saque',2,'251203164359'),
	 ('RPPAY888251603015055-992','2025-03-16 01:50:55.760188','E2E888251603015055888','3214.0','3314.0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'100',NULL,NULL,NULL,NULL,11,'Saque',2,'251203164359');



INSERT INTO rpbank.log_carteira (dtLogCadastro,lgLogCadastro,idCarteira,idConta,nuConta,saldo,dtCadastro,dtAlteracao,msgLogInfo) VALUES
	 ('2025-03-16 01:38:17','Sistema_RP',1,11,'251203164359',3314.0,'2025-03-12 00:00:00','2025-03-16 01:38:17','UPDATE SUCCESS'),
	 ('2025-03-16 01:50:56','Sistema_RP',1,11,'251203164359',3314.0,'2025-03-12 00:00:00','2025-03-16 01:38:17','UPDATE SUCCESS');



INSERT INTO rpbank.tipo_conta (tpConta,dsConta) VALUES
	 (1,'Conta Corrente'),
	 (2,'Conta Poupança');



INSERT INTO rpbank.tipo_transacao (tpTransacao,dsTransacao) VALUES
	 (1,'Deposito'),
	 (2,'Saque'),
	 (3,'Transferência Saída'),
	 (4,'Transferência pix'),
	 (5,'Pagamento Boleto'),
	 (6,'Tranferêcia Entrada');
