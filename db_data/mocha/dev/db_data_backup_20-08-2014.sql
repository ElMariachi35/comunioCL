/*
SQLyog Community v8.32 
MySQL - 5.5.32-log : Database - elm355_dev-com-cl
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`elm355_dev-com-cl` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `elm355_dev-com-cl`;

/*Data for the table `comunio` */

LOCK TABLES `comunio` WRITE;

insert  into `comunio`(`comunioId`,`name`,`password`,`playoff_id`) values (20944,'Fantasy e-Champions League','c3c2257b1e378d3578e1b2ec3af3635e',217);

UNLOCK TABLES;

/*Data for the table `fixture` */

LOCK TABLES `fixture` WRITE;

insert  into `fixture`(`fixtureId`,`groupId`) values (199,207),(200,208),(201,209);

UNLOCK TABLES;

/*Data for the table `game` */

LOCK TABLES `game` WRITE;

insert  into `game`(`gameId`,`matchdayId`,`homeTeam`,`awayTeam`,`homeGoals`,`awayGoals`) values (3508,1746,832,835,0,0),(3509,1746,831,834,0,0),(3510,1747,835,831,0,0),(3511,1747,834,833,0,0),(3512,1748,833,831,0,0),(3513,1748,834,832,0,0),(3514,1749,835,833,0,0),(3515,1749,831,832,0,0),(3516,1750,834,835,0,0),(3517,1750,832,833,0,0),(3518,1751,835,832,0,0),(3519,1751,834,831,0,0),(3520,1752,831,835,0,0),(3521,1752,833,834,0,0),(3522,1753,831,833,0,0),(3523,1753,832,834,0,0),(3524,1754,833,835,0,0),(3525,1754,832,831,0,0),(3526,1755,835,834,0,0),(3527,1755,833,832,0,0),(3528,1756,837,840,0,0),(3529,1756,836,839,0,0),(3530,1757,840,836,0,0),(3531,1757,839,838,0,0),(3532,1758,838,836,0,0),(3533,1758,839,837,0,0),(3534,1759,840,838,0,0),(3535,1759,836,837,0,0),(3536,1760,839,840,0,0),(3537,1760,837,838,0,0),(3538,1761,840,837,0,0),(3539,1761,839,836,0,0),(3540,1762,836,840,0,0),(3541,1762,838,839,0,0),(3542,1763,836,838,0,0),(3543,1763,837,839,0,0),(3544,1764,838,840,0,0),(3545,1764,837,836,0,0),(3546,1765,840,839,0,0),(3547,1765,838,837,0,0),(3548,1766,842,844,0,0),(3549,1766,841,843,0,0),(3550,1767,844,841,0,0),(3551,1767,842,843,0,0),(3552,1768,843,844,0,0),(3553,1768,842,841,0,0),(3554,1769,844,842,0,0),(3555,1769,843,841,0,0),(3556,1770,841,844,0,0),(3557,1770,843,842,0,0),(3558,1771,844,843,0,0),(3559,1771,841,842,0,0),(3560,1772,842,844,0,0),(3561,1772,841,843,0,0),(3562,1773,844,841,0,0),(3563,1773,842,843,0,0),(3564,1774,843,844,0,0),(3565,1774,842,841,0,0);

UNLOCK TABLES;

/*Data for the table `groupe` */

LOCK TABLES `groupe` WRITE;

insert  into `groupe`(`groupId`,`groupName`,`comunioId`) values (207,'A',20944),(208,'B',20944),(209,'C',20944);

UNLOCK TABLES;

/*Data for the table `knockoutpairing` */

LOCK TABLES `knockoutpairing` WRITE;

UNLOCK TABLES;

/*Data for the table `matchday` */

LOCK TABLES `matchday` WRITE;

insert  into `matchday`(`matchdayId`,`fixtureId`,`comunioMatchdayNumber`,`leagueMatchdayNumber`,`byeTeam`) values (1746,199,1,0,833),(1747,199,2,0,832),(1748,199,3,0,835),(1749,199,4,0,834),(1750,199,5,0,831),(1751,199,6,0,833),(1752,199,7,0,832),(1753,199,8,0,835),(1754,199,9,0,834),(1755,199,10,0,831),(1756,200,1,0,838),(1757,200,2,0,837),(1758,200,3,0,840),(1759,200,4,0,839),(1760,200,5,0,836),(1761,200,6,0,838),(1762,200,7,0,837),(1763,200,8,0,840),(1764,200,9,0,839),(1765,200,10,0,836),(1766,201,1,0,NULL),(1767,201,2,0,NULL),(1768,201,3,0,NULL),(1769,201,4,0,NULL),(1770,201,5,0,NULL),(1771,201,6,0,NULL),(1772,201,7,0,NULL),(1773,201,8,0,NULL),(1774,201,9,0,NULL);

UNLOCK TABLES;

/*Data for the table `playoff` */

LOCK TABLES `playoff` WRITE;

insert  into `playoff`(`id`,`quaterFinal`,`semiFinal`,`playoffFinal`) values (217,NULL,NULL,NULL);

UNLOCK TABLES;

/*Data for the table `playofffinale` */

LOCK TABLES `playofffinale` WRITE;

UNLOCK TABLES;

/*Data for the table `playofffixture` */

LOCK TABLES `playofffixture` WRITE;

UNLOCK TABLES;

/*Data for the table `playoffgame` */

LOCK TABLES `playoffgame` WRITE;

UNLOCK TABLES;

/*Data for the table `result` */

LOCK TABLES `result` WRITE;

UNLOCK TABLES;

/*Data for the table `team` */

LOCK TABLES `team` WRITE;

insert  into `team`(`teamId`,`teamName`,`groupId`,`gamesPlayed`,`gamesWon`,`gamesDrawn`,`gamesLost`,`goalsFor`,`goalsAgainst`,`goalDifference`,`points`,`playoffFixture`) values (831,'Gierlos Galaktiker',207,0,0,0,0,0,0,0,0,NULL),(832,'c0rneliusse',207,0,0,0,0,0,0,0,0,NULL),(833,'SASK 1908',207,0,0,0,0,0,0,0,0,NULL),(834,'Feiprex',207,0,0,0,0,0,0,0,0,NULL),(835,'Stolpe',207,0,0,0,0,0,0,0,0,NULL),(836,'MAR10',208,0,0,0,0,0,0,0,0,NULL),(837,'FOX',208,0,0,0,0,0,0,0,0,NULL),(838,'Die coolen Schnegis',208,0,0,0,0,0,0,0,0,NULL),(839,'Fortuna Wild 1991',208,0,0,0,0,0,0,0,0,NULL),(840,'Berni Allstars',208,0,0,0,0,0,0,0,0,NULL),(841,'Balladassdaryn',209,0,0,0,0,0,0,0,0,NULL),(842,'FC Siewillja',209,0,0,0,0,0,0,0,0,NULL),(843,'Mensch ist der United',209,0,0,0,0,0,0,0,0,NULL),(844,'Tsubasas DreamTeam',209,0,0,0,0,0,0,0,0,NULL);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
