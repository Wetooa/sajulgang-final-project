
-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2024 at 05:26 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbteknoblasterz`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblleaderboard`
--

CREATE TABLE `tblleaderboard` (
  `rankID` int(11) NOT NULL,
  `userID` int(20) NOT NULL,
  `points` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbluseraccount`
--

CREATE TABLE `tbluseraccount` (
  `userID` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbluseraccount`
--

INSERT INTO `tbluseraccount` (`userID`, `username`, `password`) VALUES
(1, 'test', 'test'),
(2, 'tepen', 'sales');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblleaderboard`
--
ALTER TABLE `tblleaderboard`
  ADD PRIMARY KEY (`rankID`);

--
-- Indexes for table `tbluseraccount`
--
ALTER TABLE `tbluseraccount`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblleaderboard`
--
ALTER TABLE `tblleaderboard`
  MODIFY `rankID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbluseraccount`
--
ALTER TABLE `tbluseraccount`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
