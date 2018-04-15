-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 13, 2018 at 05:24 PM
-- Server version: 5.5.52-0ubuntu0.14.04.1-log
-- PHP Version: 5.5.9-1ubuntu4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_amis`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_unions`
--

CREATE TABLE IF NOT EXISTS `tbl_unions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `division_id` int(11) NOT NULL,
  `unit_id` int(11) NOT NULL,
  `thana_id` int(11) NOT NULL,
  `union_name_eng` varchar(255) DEFAULT NULL,
  `union_name_bng` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `action_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `kpi_withdraw_event` ON SCHEDULE EVERY 1 DAY STARTS '2016-01-19 00:00:00' ON COMPLETION PRESERVE DISABLE DO CALL withdraw_update()$$

CREATE DEFINER=`ansaru`@`localhost` EVENT `rest_ansar_paneling_event` ON SCHEDULE EVERY 5 MINUTE STARTS '2015-11-17 21:16:43' ON COMPLETION PRESERVE ENABLE DO CALL update_panel()$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
