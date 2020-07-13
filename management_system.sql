-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- 主机： 127.0.0.1:3306
-- 生成日期： 2020-07-13 05:17:41
-- 服务器版本： 10.4.10-MariaDB
-- PHP 版本： 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `management_system`
--

-- --------------------------------------------------------

--
-- 表的结构 `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(20) NOT NULL,
  `apwd` varchar(40) NOT NULL,
  PRIMARY KEY (`aid`),
  UNIQUE KEY `aname` (`aname`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='高级管理员表';

--
-- 转存表中的数据 `admin`
--

INSERT INTO `admin` (`aid`, `aname`, `apwd`) VALUES
(1, 'root', 'root');

-- --------------------------------------------------------

--
-- 表的结构 `choose`
--

DROP TABLE IF EXISTS `choose`;
CREATE TABLE IF NOT EXISTS `choose` (
  `scid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `sno` varchar(20) NOT NULL COMMENT '学号',
  `cno` varchar(20) NOT NULL COMMENT '课程编号',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '申请状态',
  `grade` double DEFAULT NULL COMMENT '成绩 ',
  PRIMARY KEY (`scid`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='学生选课表';

--
-- 转存表中的数据 `choose`
--

INSERT INTO `choose` (`scid`, `sno`, `cno`, `status`, `grade`) VALUES
(7, '20110101020', 'X12001', 1, 0),
(2, '18110506084', 'L12353', 1, 90),
(3, '18110506083', 'L12353', 1, 81.5),
(4, '20110101002', 'L12353', 1, 82.5),
(5, '20110101003', 'L12353', 1, 83.5),
(6, '20110101022', 'N12171', 1, 0),
(8, '18110506084', 'E12271', 1, 0),
(9, '18110506083', 'E12271', 1, NULL),
(10, '20120101004', 'E12271', 1, NULL),
(11, '18110506084', 'E12266', 1, NULL),
(12, '18110506084', 'E12272', 0, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `class`
--

DROP TABLE IF EXISTS `class`;
CREATE TABLE IF NOT EXISTS `class` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级编号',
  `cno` varchar(20) NOT NULL COMMENT '班级号',
  `cname` varchar(20) NOT NULL COMMENT '班级名',
  `college` varchar(40) NOT NULL COMMENT '所属学院',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cno` (`cno`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='班级表';

--
-- 转存表中的数据 `class`
--

INSERT INTO `class` (`cid`, `cno`, `cname`, `college`) VALUES
(1, 'rj1803', '软件1803', '计算机科学与技术学院'),
(2, 'rj1801', '软件1801', '计算机科学与技术学院'),
(3, 'rj1802', '软件1802', '计算机科学与技术学院'),
(6, 'ck1801', '测控1801', '机械学院'),
(5, 'gm1801', '国贸1801', '经济学院'),
(7, 'cl1801', '车辆1801', '交通与车辆工程学院'),
(8, 'dx1802', '电信1802', '电气与电子工程学院'),
(9, 'hj1801', '化教1801', '化学化工学院'),
(15, 'jk2001', '计科2001', '计算机科学与技术学院');

-- --------------------------------------------------------

--
-- 表的结构 `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `cno` varchar(20) NOT NULL COMMENT '课程编号',
  `cname` varchar(20) NOT NULL COMMENT '课程名',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cno` (`cno`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

--
-- 转存表中的数据 `course`
--

INSERT INTO `course` (`cid`, `cno`, `cname`) VALUES
(1, 'E12266', 'Java Web程序设计'),
(2, 'E12197', '计算机网络(A)'),
(3, 'L12353', '大学物理(A)I'),
(4, 'E12025', '数据库系统原理(A)'),
(5, 'L12051', '概率论与数理统计(D)'),
(6, 'N12249', '学科英语'),
(7, 'P12228', '毛泽东思想和中国特色社会理论体系概论'),
(8, 'U12560', '手球IV'),
(9, 'N12171', '大学英语听说(A)I'),
(10, 'N12172', '大学英语听说(A)II'),
(11, 'X12001', '军事理论(A)'),
(12, 'E12272', 'C语言'),
(13, '400B18', '中国传统文化'),
(14, 'D12012', '数学电子技术(A)'),
(15, 'U12321', '运动解剖学'),
(16, 'E12271', '计算机应用基础'),
(17, 'P12227', '形势与政策');

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `sno` varchar(20) NOT NULL COMMENT '学号',
  `cno` varchar(20) NOT NULL COMMENT '班级编号',
  `sname` varchar(15) NOT NULL COMMENT '姓名',
  `spwd` varchar(40) NOT NULL COMMENT '密码',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `sage` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`sid`),
  UNIQUE KEY `sno` (`sno`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`sid`, `sno`, `cno`, `sname`, `spwd`, `sex`, `sage`, `email`, `phone`) VALUES
(1, '18110506084', 'rj1803', '李威', '123456', 1, 20, 'chall21@yeah.net', '17753062762'),
(2, '18110506083', 'rj1803', '姜富超', '123456', NULL, NULL, NULL, NULL),
(4, '20110101002', 'rj1801', '张三', '123456', 1, 18, '', ''),
(5, '20110101003', 'rj1802', '李四', '123456', 1, 18, '', ''),
(6, '20120101004', 'rj1803', '王五', '123456', 2, 18, '', ''),
(7, '20110101005', 'ck1801', '小花', '123456', 1, 18, '', ''),
(8, '20120101006', 'dx1802', '杜鹃', '123456', 2, 18, '', ''),
(10, '20110101020', 'gm1801', '小刘', '123456', 1, 18, '20110101020@sdut.com', ''),
(11, '20110101021', 'cl1801', '小刚', '123456', 1, 18, '20110101021@sdut.com', ''),
(12, '20110101022', 'hj1801', '小华', '123456', 1, 18, '20110101022@sdut.com', '');

-- --------------------------------------------------------

--
-- 表的结构 `tbuser`
--

DROP TABLE IF EXISTS `tbuser`;
CREATE TABLE IF NOT EXISTS `tbuser` (
  `tbid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主码',
  `tbuser` varchar(100) NOT NULL COMMENT '管理员登录名',
  `tbname` varchar(100) NOT NULL COMMENT '姓名',
  `tbpwd` varchar(100) NOT NULL COMMENT '密码',
  PRIMARY KEY (`tbid`),
  UNIQUE KEY `tbuser` (`tbuser`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COMMENT='管理员用户表';

--
-- 转存表中的数据 `tbuser`
--

INSERT INTO `tbuser` (`tbid`, `tbuser`, `tbname`, `tbpwd`) VALUES
(30, 'admin1', '管理员1', '123456'),
(29, 'admin', 'admin', 'admin'),
(28, 'root', 'root', 'root'),
(27, '888', '888', '888'),
(26, '777', '777', '777'),
(25, '666', '666', '666'),
(24, '555', '555', '555'),
(21, '222', '222', '222'),
(20, '111', '111', '111'),
(19, 'xiaohong', '小红', '123456'),
(18, 'xiaoming', '小明', '123456'),
(17, 'cxk', '蔡徐坤', '123456'),
(31, 'admin2', '管理员2', '123456'),
(32, 'admin3', '管理员3', '123456'),
(33, 'zhangsan', '张三', '123456'),
(34, 'lisi', '李四', '123456');

-- --------------------------------------------------------

--
-- 表的结构 `teacher`
--

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
  `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `tno` varchar(20) NOT NULL COMMENT '教师号',
  `tname` varchar(20) NOT NULL COMMENT '姓名',
  `tpwd` varchar(40) NOT NULL COMMENT '密码',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`tid`),
  UNIQUE KEY `tno` (`tno`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='教师表';

--
-- 转存表中的数据 `teacher`
--

INSERT INTO `teacher` (`tid`, `tno`, `tname`, `tpwd`, `email`, `phone`) VALUES
(1, 't1011', '王老师', '123456', 't1011@sdut.com', '17753062762'),
(14, 't1030', '苟老师', '123456', 't1030@sdut.com', '19812341030'),
(13, 't1023', '张老师', '123456', '', ''),
(4, 't1014', '赵老师', '123456', NULL, NULL),
(5, 't1015', '刘老师', '123456', NULL, NULL),
(6, 't1016', '孙教授', '123456', NULL, NULL),
(8, 't1018', '姜老师', '123456', NULL, NULL),
(9, 't1019', '周老师', '123456', NULL, NULL),
(10, 't1020', '吴老师', '123456', NULL, NULL),
(11, 't1021', '郑老师', '123456', NULL, NULL),
(12, 't1022', '江老师', '123456', NULL, NULL),
(15, 't1031', '朱老师', '123456', 't1031@sdut.com', '19812341031'),
(16, 't1032', '范老师', '123456', 't1032@sdut.com', '19812341032');

-- --------------------------------------------------------

--
-- 表的结构 `teaching`
--

DROP TABLE IF EXISTS `teaching`;
CREATE TABLE IF NOT EXISTS `teaching` (
  `tcid` int(11) NOT NULL AUTO_INCREMENT,
  `cno` varchar(20) NOT NULL COMMENT '课程编号',
  `tno` varchar(20) NOT NULL COMMENT '教师号',
  `book` varchar(20) DEFAULT NULL COMMENT '参考书',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '授课申请状态',
  PRIMARY KEY (`tcid`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='教师授课表';

--
-- 转存表中的数据 `teaching`
--

INSERT INTO `teaching` (`tcid`, `cno`, `tno`, `book`, `status`) VALUES
(1, 'E12266', 't1011', 'Java Web程序设计', 1),
(2, 'L12353', 't1011', '大学物理', 1),
(5, 'X12001', 't1022', NULL, 1),
(4, 'N12171', 't1023', NULL, 1),
(6, 'E12271', 't1019', NULL, 1),
(7, 'E12266', 't1015', NULL, 1),
(8, 'E12266', 't1016', NULL, 1),
(9, 'E12266', 't1018', NULL, 1),
(10, 'L12353', 't1030', NULL, 1),
(11, 'P12228', 't1011', '毛概', 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
