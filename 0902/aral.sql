-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.6.7-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- aral 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `aral` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `aral`;

-- 테이블 aral.admin 구조 내보내기
CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` varchar(50) NOT NULL DEFAULT '',
  `admin_pw` varchar(50) DEFAULT NULL,
  `admin_name` varchar(50) DEFAULT NULL,
  `admin_level` char(1) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 테이블 데이터 aral.admin:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- 테이블 aral.anoproduct 구조 내보내기
CREATE TABLE IF NOT EXISTS `anoproduct` (
  `anoNo` int(11) NOT NULL,
  `anoName` int(11) DEFAULT NULL,
  `anoOption1` int(11) DEFAULT NULL,
  `anoOption2` int(11) DEFAULT NULL,
  `anoCharge` int(11) DEFAULT NULL,
  `anoImage` int(11) NOT NULL,
  `resortNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`anoNo`) USING BTREE,
  KEY `FK2` (`resortNo`),
  CONSTRAINT `FK2` FOREIGN KEY (`resortNo`) REFERENCES `resort` (`resortNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 테이블 데이터 aral.anoproduct:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `anoproduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `anoproduct` ENABLE KEYS */;

-- 테이블 aral.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `boardNo` int(11) NOT NULL,
  `board_type` int(11) DEFAULT NULL,
  `board_title` int(11) DEFAULT NULL,
  `board_text` int(11) DEFAULT NULL,
  `board_date` int(11) DEFAULT NULL,
  `board_reply` int(11) DEFAULT NULL,
  `userNo` int(11) DEFAULT NULL,
  `no_num` int(11) DEFAULT NULL,
  `resortNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`boardNo`),
  KEY `FK3` (`userNo`),
  KEY `FK4` (`no_num`),
  KEY `FK5` (`resortNo`),
  CONSTRAINT `FK3` FOREIGN KEY (`userNo`) REFERENCES `user` (`userNo`),
  CONSTRAINT `FK4` FOREIGN KEY (`no_num`) REFERENCES `no_user` (`no_num`),
  CONSTRAINT `FK5` FOREIGN KEY (`resortNo`) REFERENCES `resort` (`resortNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 테이블 데이터 aral.board:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

-- 테이블 aral.calculation 구조 내보내기
CREATE TABLE IF NOT EXISTS `calculation` (
  `calNo` int(11) NOT NULL,
  `resortNo` int(11) DEFAULT NULL,
  `resortAccount` int(11) DEFAULT NULL,
  `calAmount` int(11) DEFAULT NULL,
  `cashStatus` int(11) DEFAULT NULL,
  `calDate` int(11) DEFAULT NULL,
  `calCost` int(11) DEFAULT NULL,
  PRIMARY KEY (`calNo`),
  KEY `FK12` (`resortNo`),
  CONSTRAINT `FK12` FOREIGN KEY (`resortNo`) REFERENCES `resort` (`resortNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 테이블 데이터 aral.calculation:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `calculation` DISABLE KEYS */;
/*!40000 ALTER TABLE `calculation` ENABLE KEYS */;

-- 테이블 aral.cart 구조 내보내기
CREATE TABLE IF NOT EXISTS `cart` (
  `cartNo` int(11) NOT NULL,
  `userNo` int(11) DEFAULT NULL,
  `resortNo` int(11) DEFAULT NULL,
  `no_num` int(11) DEFAULT NULL,
  `hoNo` int(11) DEFAULT NULL,
  `anoNo` int(11) DEFAULT NULL,
  `ho_qty` int(11) DEFAULT NULL,
  `ano_qty` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `add_price` int(11) DEFAULT NULL,
  PRIMARY KEY (`cartNo`),
  KEY `FK15` (`userNo`),
  KEY `FK16` (`resortNo`),
  KEY `FK17` (`no_num`),
  KEY `FK18` (`hoNo`),
  KEY `FK19` (`anoNo`),
  CONSTRAINT `FK15` FOREIGN KEY (`userNo`) REFERENCES `user` (`userNo`),
  CONSTRAINT `FK16` FOREIGN KEY (`resortNo`) REFERENCES `resort` (`resortNo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK17` FOREIGN KEY (`no_num`) REFERENCES `no_user` (`no_num`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK18` FOREIGN KEY (`hoNo`) REFERENCES `hoproduct` (`hoNo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK19` FOREIGN KEY (`anoNo`) REFERENCES `anoproduct` (`anoNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 테이블 데이터 aral.cart:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- 테이블 aral.hoproduct 구조 내보내기
CREATE TABLE IF NOT EXISTS `hoproduct` (
  `hoNo` int(11) NOT NULL,
  `roomName` varchar(50) NOT NULL DEFAULT '',
  `roomType` varchar(50) NOT NULL DEFAULT '',
  `roomNumber` varchar(50) NOT NULL DEFAULT '',
  `roomHow` varchar(50) NOT NULL DEFAULT '',
  `roomOption1` varchar(50) NOT NULL DEFAULT '',
  `roomOption2` varchar(50) NOT NULL DEFAULT '',
  `roomCharge` int(11) NOT NULL,
  `plusCharge` int(11) NOT NULL,
  `standardPeople` int(11) NOT NULL,
  `maxPeople` int(11) NOT NULL,
  `checkIn` datetime NOT NULL,
  `chekOut` datetime NOT NULL,
  `roomImage` varchar(50) NOT NULL DEFAULT '',
  `resortNo` int(11) NOT NULL,
  PRIMARY KEY (`hoNo`),
  KEY `FK_hoproduct_resort` (`resortNo`),
  CONSTRAINT `FK_hoproduct_resort` FOREIGN KEY (`resortNo`) REFERENCES `resort` (`resortNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 테이블 데이터 aral.hoproduct:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `hoproduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `hoproduct` ENABLE KEYS */;

-- 테이블 aral.notice 구조 내보내기
CREATE TABLE IF NOT EXISTS `notice` (
  `noticeNo` int(11) NOT NULL,
  `admin_id` varchar(50) NOT NULL DEFAULT '',
  `notice_title` int(11) DEFAULT NULL,
  `notice_text` int(11) DEFAULT NULL,
  `notice_date` int(11) DEFAULT NULL,
  PRIMARY KEY (`noticeNo`),
  KEY `FK6` (`admin_id`),
  CONSTRAINT `FK6` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 테이블 데이터 aral.notice:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;

-- 테이블 aral.no_user 구조 내보내기
CREATE TABLE IF NOT EXISTS `no_user` (
  `no_num` int(11) NOT NULL,
  `no_name` varchar(50) NOT NULL,
  `no_phone` varchar(50) NOT NULL,
  `no_pw` varchar(50) NOT NULL,
  `reserveNum` varchar(50) NOT NULL,
  `no_salt` varchar(50) NOT NULL,
  PRIMARY KEY (`no_num`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 테이블 데이터 aral.no_user:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `no_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `no_user` ENABLE KEYS */;

-- 테이블 aral.reservation 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservation` (
  `reserveNo` int(11) NOT NULL,
  `userNo` int(11) DEFAULT NULL,
  `no_num` int(11) DEFAULT NULL,
  `hoNo` int(11) DEFAULT NULL,
  `anoNo` int(11) DEFAULT NULL,
  `reserveName` int(11) DEFAULT NULL,
  `startDate` int(11) DEFAULT NULL,
  `endDate` int(11) DEFAULT NULL,
  `reservePhone` int(11) DEFAULT NULL,
  `reservePrice` int(11) DEFAULT NULL,
  `reservePoint` int(11) DEFAULT NULL,
  `reserveMethod` int(11) DEFAULT NULL,
  `cardName` int(11) DEFAULT NULL,
  `bankName` int(11) DEFAULT NULL,
  `bankEx` int(11) DEFAULT NULL,
  `reserveStatus` int(11) DEFAULT NULL,
  `reserveDate` int(11) DEFAULT NULL,
  `reserveImage` int(11) DEFAULT NULL,
  PRIMARY KEY (`reserveNo`),
  KEY `FK20` (`userNo`),
  KEY `FK21` (`no_num`),
  KEY `FK22` (`hoNo`),
  KEY `FK23` (`anoNo`),
  CONSTRAINT `FK20` FOREIGN KEY (`userNo`) REFERENCES `user` (`userNo`),
  CONSTRAINT `FK21` FOREIGN KEY (`no_num`) REFERENCES `no_user` (`no_num`),
  CONSTRAINT `FK22` FOREIGN KEY (`hoNo`) REFERENCES `hoproduct` (`hoNo`),
  CONSTRAINT `FK23` FOREIGN KEY (`anoNo`) REFERENCES `anoproduct` (`anoNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 테이블 데이터 aral.reservation:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;

-- 테이블 aral.resort 구조 내보내기
CREATE TABLE IF NOT EXISTS `resort` (
  `resortNo` int(11) NOT NULL,
  `resort_id` varchar(50) NOT NULL DEFAULT '',
  `resort_pw` varchar(50) NOT NULL DEFAULT '',
  `resort_name` varchar(50) NOT NULL DEFAULT '',
  `resort_birth1` varchar(50) DEFAULT '',
  `resort_birth2` varchar(50) DEFAULT '',
  `resort_birth3` varchar(50) DEFAULT '',
  `resort_personName` varchar(50) DEFAULT '',
  `companyNo` varchar(50) DEFAULT '',
  `companyImage` varchar(50) DEFAULT '',
  `resort_email` varchar(50) DEFAULT '',
  `resort_phone` varchar(50) DEFAULT '',
  `resort_code` int(11) DEFAULT NULL,
  `resort_joinDate` varchar(50) DEFAULT '',
  `resort_agree` char(1) DEFAULT '0',
  `resort_post` varchar(50) DEFAULT '',
  `resort_address` varchar(50) DEFAULT '',
  `resort_del` char(1) DEFAULT '',
  `resort_delDate` varchar(50) DEFAULT '',
  `resort_salt` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`resortNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 테이블 데이터 aral.resort:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `resort` DISABLE KEYS */;
/*!40000 ALTER TABLE `resort` ENABLE KEYS */;

-- 테이블 aral.review 구조 내보내기
CREATE TABLE IF NOT EXISTS `review` (
  `reviewNo` int(11) NOT NULL,
  `reviewImage` int(11) DEFAULT NULL,
  `reviewText` int(11) DEFAULT NULL,
  `reviewDate` int(11) DEFAULT NULL,
  `reviewReplyDate` int(11) DEFAULT NULL,
  `userNo` int(11) DEFAULT NULL,
  `no_num` int(11) DEFAULT NULL,
  `hoNo` int(11) DEFAULT NULL,
  `anoNo` int(11) DEFAULT NULL,
  `resortNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`reviewNo`),
  KEY `FK7` (`userNo`),
  KEY `FK8` (`no_num`),
  KEY `FK9` (`hoNo`),
  KEY `FK10` (`anoNo`),
  KEY `FK11` (`resortNo`),
  CONSTRAINT `FK10` FOREIGN KEY (`anoNo`) REFERENCES `anoproduct` (`anoNo`),
  CONSTRAINT `FK11` FOREIGN KEY (`resortNo`) REFERENCES `resort` (`resortNo`),
  CONSTRAINT `FK7` FOREIGN KEY (`userNo`) REFERENCES `user` (`userNo`),
  CONSTRAINT `FK8` FOREIGN KEY (`no_num`) REFERENCES `no_user` (`no_num`),
  CONSTRAINT `FK9` FOREIGN KEY (`hoNo`) REFERENCES `hoproduct` (`hoNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 테이블 데이터 aral.review:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;

-- 테이블 aral.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `userNo` int(11) NOT NULL,
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_pw` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_birth1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_birth2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_birth3` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_grade` char(1) DEFAULT NULL,
  `user_joinDate` timestamp NULL DEFAULT NULL,
  `user_point` int(11) DEFAULT NULL,
  `user_sum` int(11) DEFAULT NULL,
  `user_agree` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_post` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_del` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'N',
  `user_delDate` timestamp NULL DEFAULT current_timestamp(),
  `user_salt` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 테이블 데이터 aral.user:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
