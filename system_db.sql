CREATE DATABASE system_db;

CREATE TABLE `furn_inv` (
  `ID` int(10) UNSIGNED NOT NULL,
  `name` varchar(200) NOT NULL,
  `location` varchar(200) NOT NULL,
  `status` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `prefix` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `furn_inv` (`ID`, `name`, `location`, `status`, `type`, `remarks`, `prefix`) VALUES
(1, 'Sofa Desk', 'Valenzuela', 'Deployed', 'Desk', 'Red', 'F&F'),
(2, 'Cabinet', 'Antipolo', 'Not yet assembled', 'Cabinet', 'Stupid jrm', 'F&F');


CREATE TABLE `mat_inv` (
  `ID` int(10) UNSIGNED NOT NULL,
  `name` varchar(200) NOT NULL,
  `location` varchar(200) NOT NULL,
  `quantity` double NOT NULL,
  `unit` varchar(10) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `prefix` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `mat_inv` (`ID`, `name`, `location`, `quantity`, `unit`, `remarks`, `prefix`) VALUES
(1, 'Cement', 'Manila', 50, 'kilos', 'paid', 'MAT'),
(2, 'Hollow Blocks', 'Marikina', 1000, 'pcs', 'delivered using fuzo truck', 'MAT'),
(3, 'Screw Driver', 'Quezon', 5, 'pcs', 'Heaven', 'MAT');

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

INSERT INTO `tool_inv` (`ID`, `name`, `location`, `dop`, `amount`, `history`, `status`, `remarks`, `prefix`) VALUES
(1, 'Hammer', 'QC-Paloma', '2015-07-03', 350, 'Always Returned', 'In Use', 'Dents', 'T&E'),
(2, 'Cement Mixer', 'Zambales', '2018-07-21', 100000, 'Delivered to jerome', 'Ok', 'Rusty', 'T&E');

ALTER TABLE `furn_inv`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `mat_inv`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `tool_inv`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `furn_inv`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `mat_inv`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE `tool_inv`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;
