-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-05-2022 a las 13:42:28
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdfederacion`
--
CREATE DATABASE IF NOT EXISTS `bdfederacion` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bdfederacion`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `obtenerNombrePersona`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerNombrePersona` (IN `EMP_ID` INT, OUT `EMP_FIRST` VARCHAR(255))  BEGIN SELECT nombre INTO EMP_FIRST FROM personas WHERE id =
			  EMP_ID; END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `atletas`
--

DROP TABLE IF EXISTS `atletas`;
CREATE TABLE IF NOT EXISTS `atletas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `altura` float NOT NULL,
  `peso` float NOT NULL,
  `idpersona` int(11) NOT NULL,
  `idequipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKPERSONA` (`idpersona`),
  KEY `FKEQUIPO` (`idequipo`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `atletas`
--

INSERT INTO `atletas` (`id`, `altura`, `peso`, `idpersona`, `idequipo`) VALUES
(1, 1.7, 89.9, 1, NULL),
(2, 1.98, 72.8, 2, NULL),
(3, 1.69, 65.7, 3, NULL),
(4, 1.9, 89.4, 4, NULL),
(5, 1.87, 68, 5, NULL),
(6, 1.81, 67.5, 6, NULL),
(7, 1.79, 89.9, 7, NULL),
(8, 1.86, 65.8, 8, NULL),
(9, 1.9, 72, 9, NULL),
(10, 1.81, 84.8, 10, NULL),
(11, 1.76, 89.5, 11, NULL),
(12, 1.99, 67.7, 12, NULL),
(13, 1.6, 65.6, 13, NULL),
(14, 1.86, 79.2, 14, NULL),
(15, 1.79, 84.4, 15, NULL),
(16, 1.81, 68.9, 16, NULL),
(17, 1.99, 69.3, 17, NULL),
(18, 1.8, 65, 18, NULL),
(19, 1.69, 68.8, 19, NULL),
(20, 1.79, 84.6, 20, NULL),
(21, 1.86, 69.2, 21, NULL),
(22, 1.89, 69, 22, NULL),
(23, 1.99, 79.4, 23, NULL),
(24, 1.82, 69.7, 24, NULL),
(25, 1.79, 87.8, 25, NULL),
(26, 1.8, 69.3, 26, NULL),
(27, 1.89, 65, 27, NULL),
(28, 1.99, 68.2, 28, NULL),
(29, 1.85, 89.8, 29, NULL),
(30, 1.89, 79.8, 30, NULL),
(31, 1.76, 64.5, 31, NULL),
(32, 1.81, 69, 32, NULL),
(33, 1.99, 88.1, 33, NULL),
(34, 1.6, 69.5, 34, NULL),
(35, 1.86, 67.7, 35, NULL),
(36, 1.89, 64.9, 36, NULL),
(37, 1.71, 89.8, 37, NULL),
(38, 1.89, 68.6, 38, NULL),
(39, 1.89, 79.6, 39, NULL),
(40, 1.65, 69.4, 40, NULL),
(41, 1.89, 65, 41, NULL),
(42, 1.7, 89.5, 42, NULL),
(43, 1.79, 69.1, 43, NULL),
(44, 1.81, 78.8, 44, NULL),
(45, 1.99, 89, 45, NULL),
(46, 1.86, 69.7, 46, NULL),
(47, 1.69, 89.1, 47, NULL),
(48, 1.79, 69.5, 48, NULL),
(49, 1.7, 79.2, 49, NULL),
(50, 1.86, 69.9, 50, NULL),
(51, 1.81, 88, 51, NULL),
(52, 1.99, 64.1, 52, NULL),
(53, 1.69, 69.2, 53, NULL),
(54, 1.86, 85.5, 54, NULL),
(55, 1.8, 69.1, 55, NULL),
(56, 1.89, 89.5, 56, NULL),
(57, 1.71, 68, 57, NULL),
(58, 1.79, 65.8, 58, NULL),
(59, 1.66, 89.9, 59, NULL),
(60, 1.69, 69.4, 60, NULL),
(61, 1.8, 89, 61, NULL),
(62, 1.8, 69.3, 62, NULL),
(63, 1.89, 69.1, 63, NULL),
(64, 1.89, 68, 64, NULL),
(65, 1.79, 89, 65, NULL),
(66, 1.89, 79.1, 66, NULL),
(67, 1.89, 69, 67, NULL),
(68, 1.69, 69.7, 68, NULL),
(69, 1.8, 69.8, 69, NULL),
(70, 1.89, 88.4, 70, NULL),
(71, 1.79, 64.3, 71, NULL),
(72, 1.89, 67, 72, NULL),
(73, 1.9, 65.1, 73, NULL),
(74, 1.81, 69, 74, NULL),
(75, 1.8, 89, 75, NULL),
(76, 1.69, 68.9, 76, NULL),
(77, 1.89, 64.3, 77, NULL),
(78, 1.79, 69.2, 78, NULL),
(79, 1.79, 67.7, 79, NULL),
(80, 1.8, 87.8, 80, NULL),
(81, 1.81, 69.6, 81, NULL),
(82, 1.69, 79.4, 82, NULL),
(83, 1.86, 69.5, 83, NULL),
(84, 1.7, 88.9, 84, NULL),
(85, 1.89, 67.2, 85, NULL),
(86, 1.69, 65, 86, NULL),
(87, 1.79, 69, 87, NULL),
(88, 1.87, 85.5, 88, NULL),
(89, 1.89, 69.3, 89, NULL),
(90, 1.62, 67.2, 90, NULL),
(91, 1.89, 68.7, 91, NULL),
(92, 1.99, 65.8, 92, NULL),
(93, 1.69, 89.5, 93, NULL),
(94, 1.89, 69.8, 94, NULL),
(95, 1.89, 67, 95, NULL),
(96, 1.79, 64.7, 96, NULL),
(97, 1.62, 69.8, 97, NULL),
(98, 1.89, 79, 98, NULL),
(99, 1.86, 77.6, 99, NULL),
(100, 1.82, 69.8, 100, NULL),
(101, 1.88, 65.8, 2016, NULL),
(102, 1.87, 65.8, 2018, 1),
(103, 1.87, 76.8, 2019, 1),
(104, 1.87, 57.9, 2020, 3),
(105, 1.77, 77.7, 2021, 3),
(106, 1.98, 87.6, 2023, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE IF NOT EXISTS `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `abreviatura` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `abreviatura` (`abreviatura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `abreviatura`) VALUES
(1, 'Junior', 'J'),
(2, 'Senior', 'S'),
(3, 'Especial', 'E');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipos`
--

DROP TABLE IF EXISTS `equipos`;
CREATE TABLE IF NOT EXISTS `equipos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `anioinscripcion` year(4) NOT NULL,
  `idmanager` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKMANAGER` (`idmanager`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `equipos`
--

INSERT INTO `equipos` (`id`, `nombre`, `anioinscripcion`, `idmanager`) VALUES
(1, 'Equipo Masculino 2022', 2022, 1),
(2, 'Equipo Femenino 2022', 2022, 2),
(3, 'Equipo Mixto 2022', 2022, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lugares`
--

DROP TABLE IF EXISTS `lugares`;
CREATE TABLE IF NOT EXISTS `lugares` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `ubicacion` varchar(50) NOT NULL,
  `airelibre` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `lugares`
--

INSERT INTO `lugares` (`id`, `nombre`, `ubicacion`, `airelibre`) VALUES
(1, 'Las Mestas', 'Gijón', 1),
(2, 'Centro Ciudad', 'Gijón', 1),
(3, 'Parque San Francisco', 'Oviedo', 1),
(4, 'Puerto', 'Avilés', 1),
(5, 'Pabellon deportivo Aviles', 'Avilés', 0),
(6, 'Centro Ciudad', 'Oviedo', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `managers`
--

DROP TABLE IF EXISTS `managers`;
CREATE TABLE IF NOT EXISTS `managers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `direccion` varchar(150) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `idpersona` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PERSONA` (`idpersona`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `managers`
--

INSERT INTO `managers` (`id`, `direccion`, `telefono`, `idpersona`) VALUES
(1, 'Calle Las Mestas 3', '655342722', 55),
(2, 'Avda. Santander Nº 12 4º', '942331669', 57),
(3, 'Calle Rosas 33 SN', '650360140', 59);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metales`
--

DROP TABLE IF EXISTS `metales`;
CREATE TABLE IF NOT EXISTS `metales` (
  `id` int(11) NOT NULL,
  `pureza` float NOT NULL,
  `asignada` tinyint(1) NOT NULL DEFAULT 0,
  `idoro` int(11) DEFAULT NULL,
  `idplata` int(11) DEFAULT NULL,
  `idbronce` int(11) DEFAULT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `participantes`
--

DROP TABLE IF EXISTS `participantes`;
CREATE TABLE IF NOT EXISTS `participantes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dorsal` int(3) NOT NULL,
  `calle` char(1) NOT NULL,
  `tiempo` time DEFAULT NULL,
  `penalizacion` tinyint(1) NOT NULL DEFAULT 0,
  `otros` varchar(500) DEFAULT NULL,
  `idprueba` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_prueba` (`idprueba`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `patrocinadores`
--

DROP TABLE IF EXISTS `patrocinadores`;
CREATE TABLE IF NOT EXISTS `patrocinadores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idresponsable` int(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `web` varchar(150) DEFAULT NULL,
  `dotacion` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`),
  KEY `fk_responsables` (`idresponsable`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `patrocinadores`
--

INSERT INTO `patrocinadores` (`id`, `idresponsable`, `nombre`, `web`, `dotacion`) VALUES
(1, 1, 'ALSA', 'www.alsa.es', 500),
(2, 2, 'Ayto. Gijón', 'www.gijon.es', 250),
(3, 3, 'Universidad de Oviedo', 'www.uniovi.es', 350.5),
(4, 4, 'CIFP LaLaboral', 'www.cifplalaboral.es', 255.99);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

DROP TABLE IF EXISTS `personas`;
CREATE TABLE IF NOT EXISTS `personas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `fechanac` date NOT NULL,
  `nifnie` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nifnie` (`nifnie`)
) ENGINE=InnoDB AUTO_INCREMENT=2024 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id`, `nombre`, `telefono`, `fechanac`, `nifnie`) VALUES
(1, 'Jesus Perez Garcia', '697032897', '1993-07-31', '27184136EE'),
(2, 'Carlos Fernandez Avia', '685432819', '1980-11-14', 'Z2951204XX'),
(3, 'Luis Martinez Diaz', '695342871', '1998-01-08', '25031466ZZ'),
(4, 'Carmen Corrales Vega', '645532881', '1976-06-16', '35525353JJ'),
(5, 'Daniel Ruiz Blanco', '921342819', '2000-02-13', '08397958ZZ'),
(6, 'Pedro Gonzalez Puig', '690552712', '1977-07-20', '45070350XX'),
(7, 'Martin Ibarra Encina', '683542893', '1981-05-12', 'Y8645085CC'),
(8, 'Dinia Pino Novell', '685302577', '1978-04-17', '64104890AA'),
(9, 'Maria Frisuelos Ruiz', '605437884', '1979-03-01', '03424035WW'),
(10, 'Hector Garcia Perez', '607332815', '2001-12-11', '99185055QQ'),
(11, 'Carmen Perez Foz', '945707801', '1982-04-29', 'Y7323050WW'),
(12, 'Francisco Plaza Ayuso', '694432818', '1990-01-10', '32765678QQ'),
(13, 'Maria Jesus Perez Salinas', '635714801', '1983-12-09', '97424933SS'),
(14, 'Laura Castro Llorente', '695432805', '1989-05-21', 'X7592180HH'),
(15, 'Gabriela del Rio Severo', '681232816', '2001-02-28', '56743664LL'),
(16, 'Carla Gallo Pedroso', '687342207', '1984-05-30', 'Y4876028LL'),
(17, 'Alberto Perez Lago', '695382349', '1988-06-29', '39503961NN'),
(18, 'Aser Linares Nobel', '685337834', '1991-11-07', 'Z8316394JJ'),
(19, 'Daniel Suarez Melano', '680734341', '1985-06-30', '41102537LL'),
(20, 'Raquel Gonzalez Banda', '985334823', '2000-07-06', '13538622VV'),
(21, 'Maria Calvo Egea', '930332814', '1986-06-15', 'Y4649348GG'),
(22, 'Hugo Blasco Rato', '684035810', '1987-08-28', 'Y4822833TT'),
(23, 'Andrés Martinez Real', '671332800', '1988-07-05', '52305619PP'),
(24, 'Raul Velazquez Garcia', '690352881', '1987-06-09', '12229911YY'),
(25, 'Asuncion Garcia Perez', '603330818', '2001-09-28', '17472923FF'),
(26, 'Juan Jesus Ruiz Volantes', '620932819', '1986-10-08', 'X2713769EE'),
(27, 'Cecilio Gallo Garcia', '684012713', '1988-01-04', 'X5002764BB'),
(28, 'Sandra Vega Cuena', '605384817', '1985-02-22', 'Z6049056SS'),
(29, 'Sergio Poo Martinez', '608332113', '1989-07-03', 'Z6677201FF'),
(30, 'Pedro Toca Gutierrez', '985432713', '2002-10-02', '97983890AA'),
(31, 'Borja Perez Suarez', '680332801', '1982-12-27', '95555853FF'),
(32, 'Ander Santos Higuera', '682452807', '1990-10-01', '82783662SS'),
(33, 'Dominica Gonzalez Perez', '650237810', '1980-01-23', '79498609YY'),
(34, 'Laura Suarez Blasco', '680345912', '1990-02-01', 'X1689237WW'),
(35, 'Almudena Diaz Maltras', '685482881', '2002-06-25', '25340895RR'),
(36, 'Nicolasa Serrano Soto', '647002899', '1991-05-31', '64193092TT'),
(37, 'Maria Jesus Ibarra Montoya', '678305812', '1981-06-30', '91957994SS'),
(38, 'Yolanda Lopez Garcia', '684372315', '1992-11-15', '39232202KK'),
(39, 'Renata Prieto Fernandez', '605399813', '1975-07-14', '77727922CC'),
(40, 'Antonia Garcia Herrera', '675332884', '2000-12-24', '08950223AA'),
(41, 'Diego Diez Murcia', '645572813', '1993-04-08', '79448459LL'),
(42, 'Agatha Fernandez Marron', '644382856', '1994-04-08', 'Z6362552KK'),
(43, 'Luis Plaza Martinez', '607332436', '1977-05-29', 'X2387733BB'),
(44, 'Alberto Corrales Suarez', '629332834', '1978-06-13', '42105482AA'),
(45, 'Pedro del Val Almendro', '678433858', '2001-12-28', 'X6999745VV'),
(46, 'Teresa Gonzalez Garcia', '615432888', '1995-12-26', '19262790ZZ'),
(47, 'Luisa Pina Soto', '605332720', '1969-03-25', 'Y8550187CC'),
(48, 'Jana Blanco Garcia', '635337841', '1996-05-24', '31023630BB'),
(49, 'Cristina Pedrosa Garcia', '605372412', '1997-01-27', 'Z7910725HH'),
(50, 'Daniel Pares Mendez', '985357417', '2001-12-25', '35802654AA'),
(51, 'Martin Velazquez Melendez', '913232811', '1996-10-26', 'X5640435FF'),
(52, 'Candida Rato Linares', '605332872', '1990-04-23', '24474332DD'),
(53, 'Sebastian Castro Castro', '985372809', '1992-07-25', 'X8688631JJ'),
(54, 'Carlos del Pino Inda', '685332817', '1983-03-12', '38439209EE'),
(55, 'Lucia Santos Fresneda', '949332872', '2000-06-24', '52270684XX'),
(56, 'Andrés Diaz Lago', '609332927', '1969-12-08', '72980983MM'),
(57, 'Brigida Fernandez Fernandez', '675332819', '1987-12-27', '73727978YY'),
(58, 'Carlos Melano Largo', '685932817', '1998-10-11', '18350189FF'),
(59, 'Maria Rox Suarez', '985538470', '1995-01-23', '29475525JJ'),
(60, 'Francisco Calvo Real', '604832312', '2001-12-08', '50367980GG'),
(61, 'Diego Egea Gutierres', '987932838', '1991-05-10', '78208566XX'),
(62, 'Pablo Diez Vega', '67133591', '1978-01-28', 'Y1758825YY'),
(63, 'Hector Suarez Gonzalez', '670358827', '1992-08-21', 'Y1609578YY'),
(64, 'Andrés Gonzalez Res', '935332801', '1989-12-29', '43471148KK'),
(65, 'Juan Encina Panes', '609132923', '2001-11-20', '18269546WW'),
(66, 'Asier Mendez Blanco', '642782839', '1980-04-02', '28788465DD'),
(67, 'Luis Rajoy Garcia', '695282109', '1998-06-19', '66211725SS'),
(68, 'Rafael Pis Pedrosa', '685332913', '1981-05-09', '79204204RR'),
(69, 'Federico Ruiz Fresno', '685722391', '1997-06-30', '46671151BB'),
(70, 'Rosa Feroz Corbato', '645332823', '2002-12-31', '42909469WW'),
(71, 'Rai Jordan Riego Garcia', '918332819', '1980-07-21', '66975078KK'),
(72, 'Carmen Lago Par', '649332871', '1996-03-20', '97843368BB'),
(73, 'Asuncion Serrano Vega', '635839771', '1979-06-10', 'Z2707685SS'),
(74, 'Marina Castro Garcia', '659331801', '1995-01-18', 'Z8555951WW'),
(75, 'Maria Ana de la Riva Suarez', '685932890', '2000-02-10', '67172867XX'),
(76, 'Maria Salas Fernandez', '675331829', '1994-07-01', 'X2293232VV'),
(77, 'Amaya Fernandez Muela', '97539218', '1978-04-02', 'Y2544788JJ'),
(78, 'Sandra Peres Robles', '929352801', '1993-08-08', '76517927DD'),
(79, 'Ursula Suarez Diaz', '645702851', '1990-06-17', 'Y8861516KK'),
(80, 'Gabriel Julio Sol', '985372858', '2000-02-16', '38784755QQ'),
(81, 'Carlos Culto Medina', '6230328710', '1992-03-08', '34487252VV'),
(82, 'Natasha Puig Garcia', '625159802', '1998-12-15', '96241332VV'),
(83, 'Dario Linares Gutierrez', '988032859', '1977-04-03', '90852353FF'),
(84, 'Diego Bolo Garcia', '659373808', '1976-11-04', 'Z6538331BB'),
(85, 'Alberto Agujas Montoya', '930832821', '2001-12-07', '90514904ZZ'),
(86, 'Martin Garcia Garcia', '698501818', '1991-05-04', '14607818NN'),
(87, 'Jorge Ibarretxe Gallo', '680392814', '1975-04-08', '48868669VV'),
(88, 'Daniel Muela Velazquez', '682312841', '1974-02-05', '83097887ZZ'),
(89, 'Teresa Martinez Vivo', '680372845', '1990-07-16', '37441134DD'),
(90, 'Borja Lagos Perez', '699332873', '2002-01-15', '29908580RR'),
(91, 'Andrés Gonzalez Galvez', '989300959', '1989-12-08', 'Y6359761EE'),
(92, 'Maria Garcia de Vivar', '938332801', '1973-08-14', '24690168JJ'),
(93, 'Juan Robles Sello', '689337821', '1971-06-05', '20548833NN'),
(94, 'Pablo Leon Garcia', '685409720', '1989-01-06', '32778256JJ'),
(95, 'Amalia del Pino Gutierrez', '910832839', '2001-12-30', '90815175CC'),
(96, 'Raul Plaza Corbato', '694937805', '1988-09-14', '17039814BB'),
(97, 'Padro Santos Diaz', '637339880', '1972-09-13', '08345111KK'),
(98, 'Juaquin Dalmata Serrano', '929330831', '1987-09-08', '92724132TT'),
(99, 'Francisco Suarez Serrano', '970309833', '1986-04-26', '88821224PP'),
(100, 'Emma Fernandez Corral', '941272809', '2002-10-12', '85410682KK'),
(1001, 'Sonia Montoya Torna', '671250081', '1990-07-12', '29733812XX'),
(1002, 'Natalia Prado Aguja', '680235891', '1989-02-11', '49855958PP'),
(1003, 'Martin Puig Fernandez', '694382839', '1978-08-07', '94752983KK'),
(1004, 'Juan Alvarez Roig', '685732413', '1985-07-06', 'Z7500169NN'),
(1005, 'Carlos Diez Cobo', '675312945', '1989-09-08', 'Y6072270PP'),
(1006, 'Carmen Martinez Tazones', '958232831', '1990-10-21', '84577138CC'),
(1007, 'Hector Calvo Blanco', '688257823', '1984-02-11', 'X3034373YY'),
(1008, 'Pablo Linares Altamira', '697131831', '1990-01-08', '69650971WW'),
(1009, 'Hugo Fuero Ruiz', '951832859', '1978-10-10', 'Z1182828NN'),
(1010, 'Luis Garcia Gonzalez', '681952317', '1983-08-09', '54161963EE'),
(1011, 'Carmen Diaz Castro', '61734815', '1979-10-07', 'Y6521791VV'),
(1012, 'Borja Vega Martinez', '688215870', '1979-07-10', '57979381SS'),
(1013, 'Carlos Garcia Perez', '650732808', '1982-05-20', '30730072WW'),
(1014, 'Maria Ruiz Fernandez', '652732818', '1981-02-21', '77712913FF'),
(1015, 'Emma del Rio Flores', '650732111', '1980-01-02', '15580165BB'),
(2001, 'Rosario Prado Blanco', '627943650', '1979-09-18', '49586708LL'),
(2002, 'Laura Torices Fernandez', '685334056', '1980-02-08', '19932413SS'),
(2003, 'Manuel Santiago Diez', '638135956', '1983-10-31', 'Y6223253LL'),
(2004, 'Alfredo Garcia Gallo', '608364550', '1981-03-28', 'X2408426GG'),
(2005, 'Ramon Garcia Lazo', '694267193', '1976-08-13', '75564453TT'),
(2006, 'Rosa Diez Lucas', '628324853', '1981-04-07', '77483039HH'),
(2007, 'Ana Ruiz Arenas', '629304871', '1988-11-04', 'X7352499CC'),
(2008, 'Alvaro Osorno Soto', '654768870', '1998-03-23', '13486677YY'),
(2009, 'Bernabe Ruiz Garcia', '623804841', '1978-11-14', '20709459YY'),
(2010, 'Elisabeth Salamanca Blanco', '669359871', '1980-04-07', '56608421QQ'),
(2011, 'Ana Ruiz Jurado', '609304772', '1985-12-25', '22878897SS'),
(2012, 'Maria Cuena Velazquez', '672340851', '1983-09-14', '77131100WW'),
(2013, 'Juana de la Riva Gutierrez', '629904871', '1989-01-09', '69092756CC'),
(2014, 'Damian Ortiz Arenas', '681331773', '1987-07-24', '92223222FF'),
(2015, 'Cesar Gutierrez Fernandez', '690302861', '1995-08-10', '85283417SS'),
(2016, 'Luis de Blas', '566334411', '1985-01-01', '72140278L'),
(2018, 'Luis Cuevas', '63462422', '1986-01-01', '53775444H'),
(2019, 'Hugo Martinez', '3463246346', '1986-06-01', '58462854M'),
(2020, 'Ville Bajo', '634367324', '1990-01-01', '58427424H'),
(2021, 'Carlos Medrano', '4262462263', '1967-07-07', '53776141W'),
(2023, 'Juaquin', '2352362362', '1968-07-01', '71977564F');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pruebas`
--

DROP TABLE IF EXISTS `pruebas`;
CREATE TABLE IF NOT EXISTS `pruebas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `fecha` date NOT NULL,
  `idlugar` int(11) NOT NULL,
  `individual` tinyint(1) NOT NULL,
  `idpatrocinador` int(11) NOT NULL,
  `idresultado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lugares` (`idlugar`),
  KEY `fk_patrocinadores` (`idpatrocinador`),
  KEY `FK_resultados` (`idresultado`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pruebas`
--

INSERT INTO `pruebas` (`id`, `nombre`, `fecha`, `idlugar`, `individual`, `idpatrocinador`, `idresultado`) VALUES
(1, 'Prueba1', '2022-06-10', 1, 1, 3, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `responsables`
--

DROP TABLE IF EXISTS `responsables`;
CREATE TABLE IF NOT EXISTS `responsables` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idpersona` int(11) NOT NULL,
  `telefonoprof` varchar(10) NOT NULL,
  `horaini` time DEFAULT NULL,
  `horafin` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_personas` (`idpersona`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `responsables`
--

INSERT INTO `responsables` (`id`, `idpersona`, `telefonoprof`, `horaini`, `horafin`) VALUES
(1, 1011, '902422202', '00:00:00', '23:59:00'),
(2, 1012, '985181105', '09:00:00', '18:00:00'),
(3, 1013, '985103000', '08:30:00', '20:00:00'),
(4, 1014, '985185503', '08:30:00', '10:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resultados`
--

DROP TABLE IF EXISTS `resultados`;
CREATE TABLE IF NOT EXISTS `resultados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `definitivo` tinyint(1) NOT NULL DEFAULT 0,
  `fechahora` datetime DEFAULT NULL,
  `idoro` int(11) DEFAULT NULL,
  `idplata` int(11) DEFAULT NULL,
  `idbronce` int(11) DEFAULT NULL,
  `idpuesto1` int(11) DEFAULT NULL,
  `idpuesto2` int(11) DEFAULT NULL,
  `idpuesto3` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_puesto1` (`idpuesto1`),
  KEY `FK_puesto2` (`idpuesto2`),
  KEY `FK_puesto3` (`idpuesto3`),
  KEY `FK_oro` (`idoro`),
  KEY `FK_plata` (`idplata`),
  KEY `FK_bronce` (`idbronce`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `atletas`
--
ALTER TABLE `atletas`
  ADD CONSTRAINT `fkequipo` FOREIGN KEY (`idequipo`) REFERENCES `equipos` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fkpersona` FOREIGN KEY (`idpersona`) REFERENCES `personas` (`id`);

--
-- Filtros para la tabla `equipos`
--
ALTER TABLE `equipos`
  ADD CONSTRAINT `fk_manager` FOREIGN KEY (`idmanager`) REFERENCES `managers` (`id`);

--
-- Filtros para la tabla `managers`
--
ALTER TABLE `managers`
  ADD CONSTRAINT `FK_PERSONA` FOREIGN KEY (`idpersona`) REFERENCES `personas` (`id`);

--
-- Filtros para la tabla `participantes`
--
ALTER TABLE `participantes`
  ADD CONSTRAINT `FK_prueba` FOREIGN KEY (`idprueba`) REFERENCES `pruebas` (`id`);

--
-- Filtros para la tabla `patrocinadores`
--
ALTER TABLE `patrocinadores`
  ADD CONSTRAINT `fk_responsables` FOREIGN KEY (`idresponsable`) REFERENCES `responsables` (`id`);

--
-- Filtros para la tabla `pruebas`
--
ALTER TABLE `pruebas`
  ADD CONSTRAINT `FK_resultados` FOREIGN KEY (`idresultado`) REFERENCES `resultados` (`id`),
  ADD CONSTRAINT `fk_lugares` FOREIGN KEY (`idlugar`) REFERENCES `lugares` (`id`),
  ADD CONSTRAINT `fk_patrocinadores` FOREIGN KEY (`idpatrocinador`) REFERENCES `patrocinadores` (`id`);

--
-- Filtros para la tabla `responsables`
--
ALTER TABLE `responsables`
  ADD CONSTRAINT `FK_personas` FOREIGN KEY (`idPersona`) REFERENCES `personas` (`id`);

--
-- Filtros para la tabla `resultados`
--
ALTER TABLE `resultados`
  ADD CONSTRAINT `FK_bronce` FOREIGN KEY (`idbronce`) REFERENCES `metales` (`id`),
  ADD CONSTRAINT `FK_oro` FOREIGN KEY (`idoro`) REFERENCES `metales` (`id`),
  ADD CONSTRAINT `FK_plata` FOREIGN KEY (`idplata`) REFERENCES `metales` (`id`),
  ADD CONSTRAINT `FK_puesto1` FOREIGN KEY (`idpuesto1`) REFERENCES `participantes` (`id`),
  ADD CONSTRAINT `FK_puesto2` FOREIGN KEY (`idpuesto2`) REFERENCES `participantes` (`id`),
  ADD CONSTRAINT `FK_puesto3` FOREIGN KEY (`idpuesto3`) REFERENCES `participantes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
