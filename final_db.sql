# --------------------------------------------------------
# Host:                         127.0.0.1
# Database:                     secure_encrypted_data
# Server version:               5.1.73-community
# Server OS:                    Win32
# HeidiSQL version:             5.0.0.3272
# Date/time:                    2023-02-15 18:17:56
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
# Dumping database structure for secure_encrypted_data
CREATE DATABASE IF NOT EXISTS `secure_encrypted_data` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `secure_encrypted_data`;


# Dumping structure for table secure_encrypted_data.access
CREATE TABLE IF NOT EXISTS `access` (
  `fromuser` text,
  `filename` text,
  `touser` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table secure_encrypted_data.access: 1 rows
/*!40000 ALTER TABLE `access` DISABLE KEYS */;
INSERT INTO `access` (`fromuser`, `filename`, `touser`) VALUES ('admin@gmail.com', 'accident.txt', 'om@gmail.com');
/*!40000 ALTER TABLE `access` ENABLE KEYS */;


# Dumping structure for table secure_encrypted_data.fileinfo
CREATE TABLE IF NOT EXISTS `fileinfo` (
  `user` text,
  `filename` text,
  `data` longblob,
  `PrivateKey` text,
  `Size` int(50) DEFAULT NULL,
  `Time` int(50) DEFAULT NULL,
  `contenttype` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table secure_encrypted_data.fileinfo: 2 rows
/*!40000 ALTER TABLE `fileinfo` DISABLE KEYS */;
INSERT INTO `fileinfo` (`user`, `filename`, `data`, `PrivateKey`, `Size`, `Time`, `contenttype`) VALUES ('admin@gmail.com', 'accident.txt', _binary 0x57564C7133354A6C656B31426944586339594B486E666D516A675A716654516B622F33644132556E786243464A446F54445656717838556375564D3734332B787868696F333856534872486C0D0A497334614C794A6435717A366E6D42366D2B634B47784F366A6C4C6862694F74386F3641396157625A3346697362554C6D52494E747345734B45576D6D32485776746F762F4D5A6E4F62522B0D0A415562353950574338344F583837386962626371397873692B4251326136675A626E544C4656346C514B6F43464E5943456D54594E73797558324335576E3757664A342F61555730, 'EUSGG', 161, 571, 'text/plain'), ('om@gmail.com', 'demo.txt', _binary 0x784344434B596669314E6C2F7779634F6D41334C496E4178666141485544384C2B57527A4A51582F4E306E76696D496532733036486C734C746468754A305236465A7A7250677452777569330D0A2F43524F535A48326C7571514D764F3147584C654E636A5166655A6E5544315632347A51324D4F4565782F44417565716C317647734B6D3051494D776C5851666A2F36377334646B757662370D0A46345734575967336A695939624E5532626F794E6C527767496B3643576954326B44745647357042546D587A6647787254792F71734758453149724777544A415A6975624E673152, 'DIELI', 162, 12, 'text/plain');
/*!40000 ALTER TABLE `fileinfo` ENABLE KEYS */;


# Dumping structure for table secure_encrypted_data.register
CREATE TABLE IF NOT EXISTS `register` (
  `name` varchar(30) DEFAULT NULL,
  `addr` varchar(30) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `contactno` varchar(15) DEFAULT NULL,
  `pwd` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table secure_encrypted_data.register: 2 rows
/*!40000 ALTER TABLE `register` DISABLE KEYS */;
INSERT INTO `register` (`name`, `addr`, `gender`, `email`, `contactno`, `pwd`) VALUES ('admin', 'pune', 'Male', 'admin@gmail.com', '9874563210', '123'), ('om', 'pune', 'Male', 'om@gmail.com', '9874563210', '123');
/*!40000 ALTER TABLE `register` ENABLE KEYS */;


# Dumping structure for table secure_encrypted_data.requestdata
CREATE TABLE IF NOT EXISTS `requestdata` (
  `fromuser` text,
  `touser` text,
  `status` text,
  `datetime` text,
  `filename` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table secure_encrypted_data.requestdata: 1 rows
/*!40000 ALTER TABLE `requestdata` DISABLE KEYS */;
INSERT INTO `requestdata` (`fromuser`, `touser`, `status`, `datetime`, `filename`) VALUES ('om@gmail.com', 'admin@gmail.com', 'Accept', 'Wed Feb 15 17:55:58 IST 2023', 'accident.txt');
/*!40000 ALTER TABLE `requestdata` ENABLE KEYS */;


# Dumping structure for table secure_encrypted_data.tblsha
CREATE TABLE IF NOT EXISTS `tblsha` (
  `Email_ID` text,
  `File_Name` text,
  `Data_Sha` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dumping data for table secure_encrypted_data.tblsha: 2 rows
/*!40000 ALTER TABLE `tblsha` DISABLE KEYS */;
INSERT INTO `tblsha` (`Email_ID`, `File_Name`, `Data_Sha`) VALUES ('admin@gmail.com', 'accident.txt', '1a84d6f9db752a617784a96dd3466a6f0dd5e3a59779d5fd2390ee818db2b61b'), ('om@gmail.com', 'demo.txt', 'fac202aba30b5cd33a288dd66733b9d6ab5f49e8067d75ce553f23da38f51e1a');
/*!40000 ALTER TABLE `tblsha` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
