-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 13, 2018 at 05:23 PM
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
-- Table structure for table `tbl_division`
--

CREATE TABLE IF NOT EXISTS `tbl_division` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `division_code` varchar(4) CHARACTER SET latin1 NOT NULL,
  `division_name_bng` varchar(15) CHARACTER SET utf8 NOT NULL,
  `division_name_eng` varchar(12) CHARACTER SET latin1 NOT NULL,
  `sort_by` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=13 ;

--
-- Dumping data for table `tbl_division`
--

INSERT INTO `tbl_division` (`id`, `division_code`, `division_name_bng`, `division_name_eng`, `sort_by`) VALUES
(0, '0', 'n\\a', 'n\\a', 0),
(1, '60', 'সিলেট', 'SYLHET', 1),
(2, '55', 'রংপুর', 'RANGPUR', 2),
(3, '50', 'রাজশাহী', 'RAJSHAHI', 3),
(4, '40', 'খুলনা', 'KHULNA', 4),
(5, '20', 'চট্টগ্রাম', 'CHITTAGONG', 5),
(6, '10', 'বরিশাল', 'BARISAL', 6),
(8, '30', 'ঢাকা', 'Dhaka', 7),
(9, 'DMA', 'ডিএমএ', 'DMA', 10),
(10, 'CMA', 'সিএমএ', 'CMA', 11),
(11, '25', 'কুমিল্লা', 'Comilla', 0),
(12, '12', 'ময়মনসিংহ', 'Mymensingh', 0);

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
