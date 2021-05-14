-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2021 at 08:29 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.2.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `fundings`
--

CREATE TABLE `fundings` (
  `fundingID` int(11) NOT NULL,
  `sponserCode` varchar(30) NOT NULL,
  `sponserName` varchar(30) NOT NULL,
  `projectCode` varchar(30) NOT NULL,
  `projectName` varchar(30) NOT NULL,
  `fundingAmount` varchar(30) NOT NULL,
  `fundingStatus` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fundings`
--

INSERT INTO `fundings` (`fundingID`, `sponserCode`, `sponserName`, `projectCode`, `projectName`, `fundingAmount`, `fundingStatus`) VALUES
(10, 's456', 'suddda ', 'pr0012', 'wrist watch', '2000.0', 'pending'),
(11, 'asc', 'sdv', 'sa', 'afv', '898.0', 'ghd'),
(13, 'dvd233', 'dfsf', 'bsd', 'wffq', '561.0', 'dvs'),
(14, 's220', 'oiehb', 'skvn15', 'aa', '100.0', 'gggggg'),
(15, 'sda5685', 'zcv', 'dsfb', 'sdb', '65460.0', 'saba'),
(16, '4u578', 'djdj', 'dyrj', 'rdjr', '587678.0', 'hii');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fundings`
--
ALTER TABLE `fundings`
  ADD PRIMARY KEY (`fundingID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fundings`
--
ALTER TABLE `fundings`
  MODIFY `fundingID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
