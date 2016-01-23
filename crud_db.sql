-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 24, 2015 at 09:38 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `crud_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `student`
--
CREATE DATABASE crud_db;
use crud_db;
CREATE TABLE IF NOT EXISTS `student` (
  `idno` varchar(10) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `gen` varchar(10) DEFAULT NULL,
  `year` varchar(10) DEFAULT NULL,
  `branch` varchar(10) DEFAULT NULL,
  `ph` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`idno`, `name`, `gen`, `year`, `branch`, `ph`, `email`) VALUES
('N100976', 'AKHIL', 'Male', 'ENGG 4', 'CSE', '9675675675', 'akhil@gmail.com'),
('N100238', 'PAVAN', 'Male', 'ENGG 4', 'CSE', '6756576577', 'pavan@gmail.com'),
('N100106', 'manasa', 'Female', 'ENGG 4', 'CSE', '7868768767', 'manasa@gmail.com'),
('N100379', 'mounika', 'Female', 'ENGG 4', 'CSE', '8987987798', 'mounika@gmail.com'),
('N100211', 'seshu', 'Male', 'ENGG 4', 'CSE', '8978979878', 'seshu@gmail.com'),
('N100987', 'JOHN', '<select>', '<select>', '<select>', '9879779879', 'john@gmail.com'),
('N100123', 'kumar', 'Male', 'ENGG 2', 'MECH', '9876567876', 'kumar@gmail.com'),
('N100012', 'adi', 'Male', 'PUC 1', 'CSE', '9687656787', 'adi@gmail.com'),
('N100002', 'ganga', 'Female', 'ENGG 2', 'MECH', '9545456765', 'ganga@gmail.com'),
('N100234', 'BOB BHU', 'Male', 'ENGG 3', 'MECH', '9878987678', 'BIOB@GMAIL.COM');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
