/*
SQLyog Community v8.32 
MySQL - 5.5.32-cll-lve : Database - elm355_com-cl
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `Comunio` */

DROP TABLE IF EXISTS `Comunio`;

CREATE TABLE `Comunio` (
  `comunioId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `playoff_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`comunioId`),
  UNIQUE KEY `UNQ_name` (`name`),
  KEY `IDX_playoff` (`playoff_id`),
  CONSTRAINT `FK_comunio_playoff` FOREIGN KEY (`playoff_id`) REFERENCES `Playoff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=414353 DEFAULT CHARSET=utf8;

/*Table structure for table `Fixture` */

DROP TABLE IF EXISTS `Fixture`;

CREATE TABLE `Fixture` (
  `fixtureId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `groupId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`fixtureId`),
  KEY `IDX_fixture` (`fixtureId`),
  KEY `FK_fixture_group` (`groupId`),
  CONSTRAINT `FK_fixture_group` FOREIGN KEY (`groupId`) REFERENCES `Groupe` (`groupId`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8;

/*Table structure for table `Game` */

DROP TABLE IF EXISTS `Game`;

CREATE TABLE `Game` (
  `gameId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `matchdayId` bigint(20) unsigned NOT NULL,
  `homeTeam` bigint(20) unsigned NOT NULL,
  `awayTeam` bigint(20) unsigned NOT NULL,
  `homeGoals` int(11) DEFAULT NULL,
  `awayGoals` int(11) DEFAULT NULL,
  PRIMARY KEY (`gameId`),
  KEY `IDX_matchday` (`matchdayId`),
  KEY `IDX_homeTeam` (`homeTeam`),
  KEY `IDX_awayTeam` (`awayTeam`),
  CONSTRAINT `FK_game_awayTeam` FOREIGN KEY (`awayTeam`) REFERENCES `Team` (`teamId`),
  CONSTRAINT `FK_game_homeTeam` FOREIGN KEY (`homeTeam`) REFERENCES `Team` (`teamId`),
  CONSTRAINT `FK_game_matchday` FOREIGN KEY (`matchdayId`) REFERENCES `Matchday` (`matchdayId`)
) ENGINE=InnoDB AUTO_INCREMENT=3626 DEFAULT CHARSET=utf8;

/*Table structure for table `Groupe` */

DROP TABLE IF EXISTS `Groupe`;

CREATE TABLE `Groupe` (
  `groupId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `groupName` varchar(50) NOT NULL,
  `comunioId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`groupId`),
  KEY `IDX_comunio` (`comunioId`),
  CONSTRAINT `FK_groupe_comunio` FOREIGN KEY (`comunioId`) REFERENCES `Comunio` (`comunioId`)
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=utf8;

/*Table structure for table `KnockoutPairing` */

DROP TABLE IF EXISTS `KnockoutPairing`;

CREATE TABLE `KnockoutPairing` (
  `knockoutPairingId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `firstLeg` bigint(20) unsigned NOT NULL,
  `secondLeg` bigint(20) unsigned NOT NULL,
  `promotedTeam` bigint(20) unsigned DEFAULT NULL,
  `playoffFixtureId` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`knockoutPairingId`),
  KEY `IDX_promotedTeam` (`promotedTeam`),
  KEY `IDX_firstLeg` (`firstLeg`),
  KEY `IDX_secondLeg` (`secondLeg`),
  KEY `IDX_playoffFixtureId` (`playoffFixtureId`),
  CONSTRAINT `FK_knockoutpairing_firstLeg` FOREIGN KEY (`firstLeg`) REFERENCES `PlayoffGame` (`playoffGameId`),
  CONSTRAINT `FK_knockoutpairing_playoffFixture` FOREIGN KEY (`playoffFixtureId`) REFERENCES `PlayoffFixture` (`playoffFixtureId`),
  CONSTRAINT `FK_knockoutpairing_secondLeg` FOREIGN KEY (`secondLeg`) REFERENCES `PlayoffGame` (`playoffGameId`),
  CONSTRAINT `FK_knockoutpairing_team` FOREIGN KEY (`promotedTeam`) REFERENCES `Team` (`teamId`)
) ENGINE=InnoDB AUTO_INCREMENT=403 DEFAULT CHARSET=utf8;

/*Table structure for table `Matchday` */

DROP TABLE IF EXISTS `Matchday`;

CREATE TABLE `Matchday` (
  `matchdayId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `fixtureId` bigint(20) unsigned NOT NULL,
  `comunioMatchdayNumber` int(11) NOT NULL,
  `leagueMatchdayNumber` int(11) NOT NULL,
  `byeTeam` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`matchdayId`),
  KEY `IDX_fixture` (`fixtureId`),
  KEY `IDX_byeTeam` (`byeTeam`),
  CONSTRAINT `FK_matchday_fixture` FOREIGN KEY (`fixtureId`) REFERENCES `Fixture` (`fixtureId`),
  CONSTRAINT `FK_matchday_team` FOREIGN KEY (`byeTeam`) REFERENCES `Team` (`teamId`)
) ENGINE=InnoDB AUTO_INCREMENT=1805 DEFAULT CHARSET=utf8;

/*Table structure for table `Playoff` */

DROP TABLE IF EXISTS `Playoff`;

CREATE TABLE `Playoff` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `quaterFinal` bigint(20) unsigned DEFAULT NULL,
  `semiFinal` bigint(20) unsigned DEFAULT NULL,
  `playoffFinal` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_quaterFinal` (`quaterFinal`),
  KEY `IDX_semiFinal` (`semiFinal`),
  KEY `IDX_playoffFinal` (`playoffFinal`),
  CONSTRAINT `FK_playoff_quaterFinal` FOREIGN KEY (`quaterFinal`) REFERENCES `PlayoffFixture` (`playoffFixtureId`),
  CONSTRAINT `FK_playoff_semiFinal` FOREIGN KEY (`semiFinal`) REFERENCES `PlayoffFixture` (`playoffFixtureId`)
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8;

/*Table structure for table `PlayoffFinale` */

DROP TABLE IF EXISTS `PlayoffFinale`;

CREATE TABLE `PlayoffFinale` (
  `playoffFinalId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `teamOne` bigint(20) unsigned NOT NULL,
  `teamTwo` bigint(20) unsigned NOT NULL,
  `firstLeg` bigint(20) unsigned NOT NULL,
  `secondLeg` bigint(20) unsigned NOT NULL,
  `thirdLeg` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`playoffFinalId`),
  KEY `IDX_teamOne` (`teamOne`),
  KEY `IDX_teamTwo` (`teamTwo`),
  KEY `IDX_firstLeg` (`firstLeg`),
  KEY `IDX_secondLeg` (`secondLeg`),
  KEY `IDX_thirdLeg` (`thirdLeg`),
  CONSTRAINT `FK_playofffinale_thirdLeg` FOREIGN KEY (`thirdLeg`) REFERENCES `PlayoffGame` (`playoffGameId`),
  CONSTRAINT `FK_playofffinale_firstLeg` FOREIGN KEY (`firstLeg`) REFERENCES `PlayoffGame` (`playoffGameId`),
  CONSTRAINT `FK_playofffinale_secondLeg` FOREIGN KEY (`secondLeg`) REFERENCES `PlayoffGame` (`playoffGameId`),
  CONSTRAINT `FK_playofffinale_teamOne` FOREIGN KEY (`teamOne`) REFERENCES `Team` (`teamId`),
  CONSTRAINT `FK_playofffinale_teamTwo` FOREIGN KEY (`teamTwo`) REFERENCES `Team` (`teamId`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;

/*Table structure for table `PlayoffFixture` */

DROP TABLE IF EXISTS `PlayoffFixture`;

CREATE TABLE `PlayoffFixture` (
  `playoffFixtureId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`playoffFixtureId`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8;

/*Table structure for table `PlayoffGame` */

DROP TABLE IF EXISTS `PlayoffGame`;

CREATE TABLE `PlayoffGame` (
  `playoffGameId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `homeTeam` bigint(20) unsigned NOT NULL,
  `awayTeam` bigint(20) unsigned NOT NULL,
  `homeGoals` int(11) NOT NULL,
  `awayGoals` int(11) NOT NULL,
  PRIMARY KEY (`playoffGameId`)
) ENGINE=InnoDB AUTO_INCREMENT=1265 DEFAULT CHARSET=utf8;

/*Table structure for table `Result` */

DROP TABLE IF EXISTS `Result`;

CREATE TABLE `Result` (
  `resultId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `teamId` bigint(20) unsigned NOT NULL,
  `matchday` int(11) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `goals` int(11) DEFAULT NULL,
  PRIMARY KEY (`resultId`),
  KEY `IDX_team` (`teamId`),
  CONSTRAINT `FK_result_team` FOREIGN KEY (`teamId`) REFERENCES `Team` (`teamId`)
) ENGINE=InnoDB AUTO_INCREMENT=7111 DEFAULT CHARSET=utf8;

/*Table structure for table `Team` */

DROP TABLE IF EXISTS `Team`;

CREATE TABLE `Team` (
  `teamId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `teamName` varchar(50) NOT NULL,
  `groupId` bigint(20) unsigned NOT NULL,
  `gamesPlayed` int(11) DEFAULT NULL,
  `gamesWon` int(11) DEFAULT NULL,
  `gamesDrawn` int(11) DEFAULT NULL,
  `gamesLost` int(11) DEFAULT NULL,
  `goalsFor` int(11) DEFAULT NULL,
  `goalsAgainst` int(11) DEFAULT NULL,
  `goalDifference` int(11) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `playoffFixture` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`teamId`),
  KEY `IDX_groupId` (`groupId`),
  CONSTRAINT `FK_team_group` FOREIGN KEY (`groupId`) REFERENCES `Groupe` (`groupId`)
) ENGINE=InnoDB AUTO_INCREMENT=860 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
