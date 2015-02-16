-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 27 May 2014, 02:21:56
-- Sunucu sürümü: 5.6.16
-- PHP Sürümü: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
SET NAMES utf8;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Veritabanı: `dosya`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `dosyalar`
--

CREATE TABLE IF NOT EXISTS `dosyalar` (
  `dosya_id` int(11) NOT NULL AUTO_INCREMENT,
  `dosya_ad` varchar(150) DEFAULT NULL,
  `kategori_id` int(11) DEFAULT NULL,
  `adres` varchar(150) DEFAULT NULL,
  `tur` varchar(50) DEFAULT NULL,
  `boyut` int(200) DEFAULT NULL,
  `kucukresim` varchar(150) DEFAULT NULL,
  `yukleyen` int(11) DEFAULT NULL,
  `yuklemetarihi` datetime DEFAULT NULL,
  `aciklama` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dosya_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Tablo döküm verisi `dosyalar`
--



-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kategoriler`
--

CREATE TABLE IF NOT EXISTS `kategoriler` (
  `kategori_id` int(11) NOT NULL AUTO_INCREMENT,
  `kategori_ad` varchar(75) NOT NULL,
  `ustkategori` int(11) DEFAULT '0',
  PRIMARY KEY (`kategori_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Tablo döküm verisi `kategoriler`
--

INSERT INTO `kategoriler` (`kategori_id`, `kategori_ad`, `ustkategori`) VALUES
(2, 'Bilgisayar', 0),
(3, 'Araba', 0),
(4, 'Asus', 2),
(5, 'Casper', 2),
(7, 'Nirvana Serisi', 5),
(8, 'BMW', 3),
(9, 'Toshiba', 2),
(10, 'Qosmio', 9),
(11, 'Satellite', 10);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kullanicilar`
--

CREATE TABLE IF NOT EXISTS `kullanicilar` (
  `kullanici_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad` varchar(150) CHARACTER SET utf8mb4 NOT NULL,
  `soyad` varchar(150) CHARACTER SET utf8mb4 NOT NULL,
  `kadi` varchar(50) DEFAULT NULL,
  `sifre` varchar(256) DEFAULT NULL,
  `eposta` varchar(150) DEFAULT NULL,
  `admin_status` int(1) NOT NULL DEFAULT '0',
  `is_banned` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`kullanici_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=18 ;

--
-- Tablo döküm verisi `kullanicilar`
--



-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `sikayetler`
--

CREATE TABLE IF NOT EXISTS `sikayetler` (
  `sikayet_id` int(11) NOT NULL AUTO_INCREMENT,
  `dosya_id` int(11) NOT NULL,
  `kullanici_id` int(11) NOT NULL,
  `neden` text NOT NULL,
  `tarih` datetime NOT NULL,
  PRIMARY KEY (`sikayet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
