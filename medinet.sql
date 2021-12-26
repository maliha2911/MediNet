-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 26, 2021 at 10:12 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.3.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `medinet`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `email`, `password`) VALUES
(1001, 'admin@admin.com', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `email` text NOT NULL DEFAULT 'abc',
  `pid` int(11) NOT NULL DEFAULT 0,
  `quantity` text NOT NULL DEFAULT '10'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `email`, `pid`, `quantity`) VALUES
(1, 'username@gmail.com', 46, '2'),
(2, 'username@gmail.com', 46, '10'),
(3, 'temporary.getEmail()', 46, '20'),
(4, 'temporary.getEmail()', 46, '20'),
(5, 'username@gmail.com', 46, '20'),
(6, 'username@gmail.com', 46, '10'),
(7, 'username@gmail.com', 46, '30'),
(8, 'username@gmail.com', 46, '10'),
(9, 'username@gmail.com', 46, '10'),
(10, 'username@gmail.com', 46, '10'),
(11, 'username@gmail.com', 46, '10'),
(12, 'username@gmail.com', 46, '10'),
(13, 'username@gmail.com', 46, '20'),
(14, 'username@gmail.com', 46, '20'),
(15, 'username@gmail.com', 46, '10'),
(16, 'username@gmail.com', 46, '10'),
(17, 'username@gmail.com', 46, '10'),
(18, 'username@gmail.com', 46, '10'),
(19, 'username@gmail.com', 46, '10'),
(20, 'username@gmail.com', 46, '10'),
(21, 'username@gmail.com', 46, '10'),
(22, 'username@gmail.com', 46, '10'),
(23, 'username@gmail.com', 46, '10'),
(24, 'username@gmail.com', 46, '10'),
(25, 'username@gmail.com', 46, '10'),
(26, 'username@gmail.com', 46, '10'),
(27, 'username@gmail.com', 46, '10'),
(28, 'username@gmail.com', 46, '30'),
(29, 'username@gmail.com', 63, '10'),
(30, 'username@gmail.com', 64, '10'),
(31, 'username@gmail.com', 63, '10'),
(32, 'username@gmail.com', 63, '10'),
(33, 'username@gmail.com', 75, '10'),
(34, 'username@gmail.com', 64, '10'),
(35, 'username@gmail.com', 67, '10'),
(36, 'username@gmail.com', 64, '10'),
(37, 'username@gmail.com', 64, '10');

-- --------------------------------------------------------

--
-- Table structure for table `medicine`
--

CREATE TABLE `medicine` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `price` text NOT NULL,
  `exp` text DEFAULT NULL,
  `amount` text NOT NULL,
  `description` text DEFAULT NULL,
  `product_quantity` int(11) NOT NULL DEFAULT 0,
  `url` varchar(100) NOT NULL DEFAULT '1001.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medicine`
--

INSERT INTO `medicine` (`id`, `name`, `price`, `exp`, `amount`, `description`, `product_quantity`, `url`) VALUES
(63, 'Napa Extend 665mg', '2', '22-Dec', '11', 'Tablet  Generic: Paracetamol  Beximco Pharmaceuticals Ltd.', 0, 'Product1.jpg'),
(64, 'Seclo 20 20mg', '5', '22-Dec', '11', 'Capsule  Generic: Omeprazole  Square Pharmaceuticals Ltd.', 0, 'Product2.jpg'),
(67, 'Calbo D 500mg+200 IU', '189', '22-Dec', '11', 'Tablet  Generic: Calcium  Square Pharmaceuticals Ltd.', 0, 'Product3.jpg'),
(75, 'Vigogel Ointment', '158', '22-Dec', '11', 'Generic: Tila Jadeed Ibn Sina Pharmaceutical Ind. Ltd.', 0, 'Ointment.jpg'),
(80, 'Tasty Saline', '5', '22-Dec', '0', 'Powder Universal Food Ltd.', 0, 'Prod5.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `seller`
--

CREATE TABLE `seller` (
  `id` int(11) NOT NULL,
  `email` int(11) NOT NULL,
  `password` int(11) NOT NULL,
  `prod_name` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `confirm_password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `confirm_password`) VALUES
(1, 'username@gmail.com', '1234', '1234'),
(2, 'name@gmail.com', '1234', '1234'),
(3, 'admin@admin.com', '1234', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `seller`
--
ALTER TABLE `seller`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1002;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `medicine`
--
ALTER TABLE `medicine`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT for table `seller`
--
ALTER TABLE `seller`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
