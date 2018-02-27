CREATE TABLE `sys_user_log` (
  `logid` int(10) NOT NULL AUTO_INCREMENT,
  `userip` varchar(16) DEFAULT NULL,
  `createdate` varchar(19) DEFAULT NULL,
  `descript` varchar(2000) DEFAULT NULL,
  `userid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`logid`)
) ENGINE=MyISAM AUTO_INCREMENT=3013 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

