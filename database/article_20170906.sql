 DROP TABLE IF EXISTS article;
CREATE TABLE article(
	cid INT(15) PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(100),
	author VARCHAR(20),
	createtime VARCHAR(19),
	text LONGTEXT,
	userid int(15),
	state char(1),
  classifyid  int(10)
);