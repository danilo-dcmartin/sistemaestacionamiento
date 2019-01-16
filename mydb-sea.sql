-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-07-2018 a las 21:40:20
-- Versión del servidor: 10.1.33-MariaDB
-- Versión de PHP: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mydb-sea`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `automovil`
--

CREATE TABLE `automovil` (
  `id_automovil` int(11) NOT NULL,
  `patente` varchar(8) NOT NULL,
  `modelo` varchar(20) NOT NULL,
  `color` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `automovil`
--

INSERT INTO `automovil` (`id_automovil`, `patente`, `modelo`, `color`) VALUES
(1, '12*34*56', 'VMW', 'Negro'),
(2, 'FG*SW*12', 'Missan', 'Rojo'),
(3, 'DF*WE*12', 'VMW', 'Blanco'),
(4, 'FG*21*12', 'Mercedez', 'Rojo'),
(5, 'KO*KI*11', 'dsfsd', 'dffdssfs'),
(6, 'DF*WE*58', 'Missan', 'Verde');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datos_personales`
--

CREATE TABLE `datos_personales` (
  `id_barra` varchar(11) NOT NULL,
  `rut` varchar(9) NOT NULL,
  `nombres` varchar(40) NOT NULL,
  `ap_paterno` varchar(20) NOT NULL,
  `ap_materno` varchar(20) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `discapacidad` varchar(2) NOT NULL,
  `usuario_id_usuario` int(11) NOT NULL,
  `automovil_id_automovil` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `datos_personales`
--

INSERT INTO `datos_personales` (`id_barra`, `rut`, `nombres`, `ap_paterno`, `ap_materno`, `telefono`, `discapacidad`, `usuario_id_usuario`, `automovil_id_automovil`) VALUES
('1256332541', '158975584', 'Aldo', 'Vera', 'Zuñiga', '98856987', 'no', 6, 1),
('4452110369 ', '475896658', 'Alba', 'Soto', 'Soto', '95596821', 'no', 3, 2),
('5478547855', '15478784k', 'Pedro', 'Hernandez', 'Hernandez', '95874856', 'no', 3, 3),
('7785478844', '14785957k', 'Javier', 'Correa', 'Lopez', '985875100', 'no', 4, 6),
('8748774558', '198748541', 'Macarena', 'Melo', 'Melo', '985696521', 'no', 3, 4),
('9999999999', '145878965', 'sadsa', 'asdad', 'sadsa', '32313131', 'no', 3, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_plazas`
--

CREATE TABLE `historial_plazas` (
  `id_historial` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `plazas_id_plaza` int(11) NOT NULL,
  `datos_personales_id_barra` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `historial_plazas`
--

INSERT INTO `historial_plazas` (`id_historial`, `fecha`, `plazas_id_plaza`, `datos_personales_id_barra`) VALUES
(1, '2018-07-13', 1, '1256332541'),
(2, '2018-07-13', 55, '1256332541'),
(3, '2018-07-16', 67, '5478547855'),
(4, '2018-07-18', 194, '9999999999'),
(5, '2018-07-18', 193, '9999999999');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `login_app`
--

CREATE TABLE `login_app` (
  `rut` varchar(9) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nombres` varchar(40) NOT NULL,
  `ap_paterno` varchar(20) NOT NULL,
  `ap_materno` varchar(20) NOT NULL,
  `telefono` bigint(12) NOT NULL,
  `rol_app_id_rol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `login_app`
--

INSERT INTO `login_app` (`rut`, `password`, `nombres`, `ap_paterno`, `ap_materno`, `telefono`, `rol_app_id_rol`) VALUES
('11111111k', '1111', 'Uno Dos', 'Tres', 'Cuatro', 985695147, 2),
('123456789', '12345', 'Danilo Cristian', 'Martinez', 'Martinez', 965231478, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pass_android`
--

CREATE TABLE `pass_android` (
  `id_app` int(11) NOT NULL,
  `password` varchar(20) NOT NULL,
  `datos_personales_id_barra` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pass_android`
--

INSERT INTO `pass_android` (`id_app`, `password`, `datos_personales_id_barra`) VALUES
(1, '4444', '9999999999'),
(2, '1234', '8748774558'),
(3, '12345', '4452110369 ');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plazas`
--

CREATE TABLE `plazas` (
  `id_plaza` int(11) NOT NULL,
  `ocupado` int(11) NOT NULL,
  `datos_personales_id_barra` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `plazas`
--

INSERT INTO `plazas` (`id_plaza`, `ocupado`, `datos_personales_id_barra`) VALUES
(1, 1, '4452110369'),
(2, 1, '0'),
(3, 0, '0'),
(4, 0, '0'),
(5, 1, '0'),
(6, 0, '0'),
(7, 0, '0'),
(8, 0, '0'),
(9, 0, '0'),
(10, 0, '0'),
(11, 0, '0'),
(12, 0, '0'),
(13, 0, '0'),
(14, 0, '0'),
(15, 0, '0'),
(16, 0, '0'),
(17, 0, '0'),
(18, 0, '0'),
(19, 0, '0'),
(20, 0, '0'),
(21, 0, '0'),
(22, 0, '0'),
(23, 0, '0'),
(24, 0, '0'),
(25, 0, '0'),
(26, 0, '0'),
(27, 0, '0'),
(28, 0, '0'),
(29, 0, '0'),
(30, 0, '0'),
(31, 0, '0'),
(32, 0, '0'),
(33, 0, '0'),
(34, 0, '0'),
(35, 0, '0'),
(36, 0, '0'),
(37, 0, '0'),
(38, 0, '0'),
(39, 0, '0'),
(40, 0, '0'),
(41, 0, '0'),
(42, 0, '0'),
(43, 0, '0'),
(44, 0, '0'),
(45, 0, '0'),
(46, 0, '0'),
(47, 0, '0'),
(48, 0, '0'),
(49, 0, '0'),
(50, 0, '0'),
(51, 0, '0'),
(52, 0, '0'),
(53, 0, '0'),
(54, 0, '0'),
(55, 0, '0'),
(56, 0, '0'),
(57, 0, '0'),
(58, 0, '0'),
(59, 0, '0'),
(60, 0, '0'),
(61, 0, '0'),
(62, 0, '0'),
(63, 0, '0'),
(64, 0, '0'),
(65, 0, '0'),
(66, 0, '0'),
(67, 0, '0'),
(68, 0, '0'),
(69, 0, '0'),
(70, 0, '0'),
(71, 0, '0'),
(72, 0, '0'),
(73, 0, '0'),
(74, 0, '0'),
(75, 0, '0'),
(76, 0, '0'),
(77, 0, '0'),
(78, 0, '0'),
(79, 0, '0'),
(80, 0, '0'),
(81, 0, '0'),
(82, 0, '0'),
(83, 0, '0'),
(84, 0, '0'),
(85, 0, '0'),
(86, 0, '0'),
(87, 0, '0'),
(88, 0, '0'),
(89, 0, '0'),
(90, 0, '0'),
(91, 0, '0'),
(92, 0, '0'),
(93, 0, '0'),
(94, 0, '0'),
(95, 0, '0'),
(96, 0, '0'),
(97, 0, '0'),
(98, 0, '0'),
(99, 0, '0'),
(100, 0, '0'),
(101, 0, '0'),
(102, 0, '0'),
(103, 0, '0'),
(104, 0, '0'),
(105, 0, '0'),
(106, 0, '0'),
(107, 0, '0'),
(108, 0, '0'),
(109, 0, '0'),
(110, 0, '0'),
(111, 0, '0'),
(112, 0, '0'),
(113, 0, '0'),
(114, 0, '0'),
(115, 0, '0'),
(116, 0, '0'),
(117, 0, '0'),
(118, 0, '0'),
(119, 0, '0'),
(120, 0, '0'),
(121, 0, '0'),
(122, 0, '0'),
(123, 0, '0'),
(124, 0, '0'),
(125, 0, '0'),
(126, 0, '0'),
(127, 0, '0'),
(128, 0, '0'),
(129, 0, '0'),
(130, 0, '0'),
(131, 0, '0'),
(132, 0, '0'),
(133, 0, '0'),
(134, 0, '0'),
(135, 0, '0'),
(136, 0, '0'),
(137, 0, '0'),
(138, 0, '0'),
(139, 0, '0'),
(140, 0, '0'),
(141, 0, '0'),
(142, 0, '0'),
(143, 0, '0'),
(144, 0, '0'),
(145, 0, '0'),
(146, 0, '0'),
(147, 0, '0'),
(148, 0, '0'),
(149, 0, '0'),
(150, 0, '0'),
(151, 0, '0'),
(152, 0, '0'),
(153, 0, '0'),
(154, 0, '0'),
(155, 0, '0'),
(156, 0, '0'),
(157, 0, '0'),
(158, 0, '0'),
(159, 0, '0'),
(160, 0, '0'),
(161, 0, '0'),
(162, 0, '0'),
(163, 0, '0'),
(164, 0, '0'),
(165, 0, '0'),
(166, 0, '0'),
(167, 0, '0'),
(168, 0, '0'),
(169, 0, '0'),
(170, 0, '0'),
(171, 0, '0'),
(172, 0, '0'),
(173, 0, '0'),
(174, 0, '0'),
(175, 0, '0'),
(176, 0, '0'),
(177, 0, '0'),
(178, 0, '0'),
(179, 0, '0'),
(180, 0, '0'),
(181, 0, '0'),
(182, 0, '0'),
(183, 0, '0'),
(184, 0, '0'),
(185, 0, '0'),
(186, 0, '0'),
(187, 0, '0'),
(188, 0, '0'),
(189, 0, '0'),
(190, 0, '0'),
(191, 0, '0'),
(192, 0, '0'),
(193, 0, '0'),
(194, 0, '0'),
(195, 0, '0'),
(196, 0, '0'),
(197, 0, '0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol_app`
--

CREATE TABLE `rol_app` (
  `id_rol` int(11) NOT NULL,
  `rol` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `rol_app`
--

INSERT INTO `rol_app` (`id_rol`, `rol`) VALUES
(1, 'administrador'),
(2, 'guardia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `rol` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `rol`) VALUES
(1, 'administrativo'),
(2, 'docente'),
(3, 'alumno'),
(4, 'ejecutivo'),
(5, 'visita'),
(6, 'deshabilitado');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `automovil`
--
ALTER TABLE `automovil`
  ADD PRIMARY KEY (`id_automovil`);

--
-- Indices de la tabla `datos_personales`
--
ALTER TABLE `datos_personales`
  ADD PRIMARY KEY (`id_barra`),
  ADD KEY `fk_datos_personales_usuario1_idx` (`usuario_id_usuario`),
  ADD KEY `fk_datos_personales_automovil1_idx` (`automovil_id_automovil`);

--
-- Indices de la tabla `historial_plazas`
--
ALTER TABLE `historial_plazas`
  ADD PRIMARY KEY (`id_historial`),
  ADD KEY `fk_historial_plazas_datos_personales1_idx` (`datos_personales_id_barra`),
  ADD KEY `fk_historial_plazas_plazas1_idx` (`plazas_id_plaza`);

--
-- Indices de la tabla `login_app`
--
ALTER TABLE `login_app`
  ADD PRIMARY KEY (`rut`),
  ADD KEY `fk_login_app_rol_app1_idx` (`rol_app_id_rol`);

--
-- Indices de la tabla `pass_android`
--
ALTER TABLE `pass_android`
  ADD PRIMARY KEY (`id_app`,`datos_personales_id_barra`),
  ADD KEY `fk_pass_app_datos_personales1_idx` (`datos_personales_id_barra`);

--
-- Indices de la tabla `plazas`
--
ALTER TABLE `plazas`
  ADD PRIMARY KEY (`id_plaza`);

--
-- Indices de la tabla `rol_app`
--
ALTER TABLE `rol_app`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `automovil`
--
ALTER TABLE `automovil`
  MODIFY `id_automovil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `historial_plazas`
--
ALTER TABLE `historial_plazas`
  MODIFY `id_historial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `pass_android`
--
ALTER TABLE `pass_android`
  MODIFY `id_app` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `rol_app`
--
ALTER TABLE `rol_app`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `datos_personales`
--
ALTER TABLE `datos_personales`
  ADD CONSTRAINT `fk_datos_personales_automovil1` FOREIGN KEY (`automovil_id_automovil`) REFERENCES `automovil` (`id_automovil`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_datos_personales_usuario1` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `historial_plazas`
--
ALTER TABLE `historial_plazas`
  ADD CONSTRAINT `fk_historial_plazas_datos_personales1` FOREIGN KEY (`datos_personales_id_barra`) REFERENCES `datos_personales` (`id_barra`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_historial_plazas_plazas1` FOREIGN KEY (`plazas_id_plaza`) REFERENCES `plazas` (`id_plaza`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `login_app`
--
ALTER TABLE `login_app`
  ADD CONSTRAINT `fk_login_app_rol_app1` FOREIGN KEY (`rol_app_id_rol`) REFERENCES `rol_app` (`id_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pass_android`
--
ALTER TABLE `pass_android`
  ADD CONSTRAINT `fk_pass_app_datos_personales1` FOREIGN KEY (`datos_personales_id_barra`) REFERENCES `datos_personales` (`id_barra`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
