-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 24, 2018 at 10:42 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `system_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `furn_inv`
--

CREATE TABLE `furn_inv` (
  `ID` int(10) UNSIGNED NOT NULL,
  `name` varchar(200) NOT NULL,
  `location` varchar(200) NOT NULL,
  `status` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `prefix` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `furn_inv`
--

INSERT INTO `furn_inv` (`ID`, `name`, `location`, `status`, `type`, `remarks`, `prefix`) VALUES
(1, 'Sofa Desk', 'Valenzuela', 'Deployed', 'Desk', 'Red', 'F&F'),
(2, 'Cabinet', 'Antipolo', 'Not yet assembled', 'Cabinet', 'Stupid jrm', 'F&F');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `id` int(10) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `location` varchar(200) NOT NULL,
  `quantity` double NOT NULL,
  `unit` varchar(10) NOT NULL,
  `dop` varchar(20) NOT NULL,
  `amount` double NOT NULL,
  `history` varchar(255) NOT NULL,
  `status` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL,
  `remarks` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`id`, `name`, `location`, `quantity`, `unit`, `dop`, `amount`, `history`, `status`, `type`, `remarks`) VALUES
(7, 'Kiki', 'Manila', 5, 'pds', '', 0, '', '', '', 'do you love me'),
(8, 'Hammer', 'Qc', 4, 'pcs', '', 0, '', '', '', 'boysen'),
(9, 'Cement', 'qc', 4, 'kg', '', 0, '', '', '', 'heavy'),
(10, 'crane', 'manila', 0, '', '2018-07-21', 500000, 'repaired once', 'good', '', 'good'),
(11, 'bulldozer', 'manila', 0, '', '2018-07-03', 300000, 'too much', 'bad', '', 'for repair'),
(12, 'mixer', 'laguna', 0, '', '2018-07-22', 100000, 'bought', 'good', '', 'brandnew'),
(13, 'truck', 'laguna', 0, '', '2018-07-09', 2000000, 'change engine', 'working', '', 'repaired'),
(14, 'narra desk', 'laguna', 0, '', '', 0, '', 'delivered', 'desk', 'good'),
(15, 'monobloc', 'valenzuela', 0, '', '', 0, '', 'deployed', 'chair', '500pcs'),
(18, 'SCREW DRIVER', 'MANILA', 0, '', '2018-07-26', 500, 'HEHE', 'STAT', '', 'US');

-- --------------------------------------------------------

--
-- Table structure for table `mat_inv`
--

CREATE TABLE `mat_inv` (
  `ID` int(10) UNSIGNED NOT NULL,
  `name` varchar(200) NOT NULL,
  `location` varchar(200) NOT NULL,
  `quantity` double NOT NULL,
  `unit` varchar(10) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `prefix` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mat_inv`
--

INSERT INTO `mat_inv` (`ID`, `name`, `location`, `quantity`, `unit`, `remarks`, `prefix`) VALUES
(1, 'Cement', 'Manila', 50, 'kilos', 'paid', 'MAT'),
(2, 'Hollow Blocks', 'Marikina', 1000, 'pcs', 'delivered using fuzo truck', 'MAT'),
(3, 'Screw Driver', 'Quezon', 5, 'pcs', 'Heaven', 'MAT');

-- --------------------------------------------------------

--
-- Table structure for table `tool_inv`
--

CREATE TABLE `tool_inv` (
  `ID` int(10) UNSIGNED NOT NULL,
  `name` varchar(200) NOT NULL,
  `location` varchar(200) NOT NULL,
  `dop` varchar(20) NOT NULL,
  `amount` double NOT NULL,
  `history` varchar(255) NOT NULL,
  `status` varchar(50) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `prefix` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tool_inv`
--

INSERT INTO `tool_inv` (`ID`, `name`, `location`, `dop`, `amount`, `history`, `status`, `remarks`, `prefix`) VALUES
(1, 'Hammer', 'QC-Paloma', '2015-07-03', 350, 'Always Returned', 'In Use', 'Dents', 'T&E'),
(2, 'Cement Mixer', 'Zambales', '2018-07-21', 100000, 'Delivered to jerome', 'Ok', 'Rusty', 'T&E');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `furn_inv`
--
ALTER TABLE `furn_inv`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mat_inv`
--
ALTER TABLE `mat_inv`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tool_inv`
--
ALTER TABLE `tool_inv`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `furn_inv`
--
ALTER TABLE `furn_inv`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `mat_inv`
--
ALTER TABLE `mat_inv`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tool_inv`
--
ALTER TABLE `tool_inv`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
