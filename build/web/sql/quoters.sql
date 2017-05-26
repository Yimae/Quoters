CREATE TABOE IF NOT EXIST 'comments'(
'id' int(11) NOT NULL AUTO_INCREMENT,
'text' varchar(255) DEFAULT Null,
'date' datetime DEFAULT NULL,
'PersonId' int(11) NOT NULL,
'PostId' int(11) NOT NULL,
PRIMARY KEY('id'),
key 'commentsPostId' ('postId')
)ENGINE = MyISAM AUTO_INCREMENT = 2 DEFAULT CHARSET = UTF-8;

CREATE TABLE IF NOT EXIST 'frequest' (
'id' int(11) NOT NULL AUTO_INCREMENT,
'status' varchar(255) DEFAULT NULL,
'Date' datetime DEFAULT NULL,
'SourceId' int(11) NOT NULL,
'TargetId' int(11) NOT NULL,
PRIMARY KEY('id')
)ENGINE = MyISAM AUTO_INCREMENT = 2 DEFAULT CHARSET = UTF-8;

CREATE TABLE IF NTO EXIST 'link' (
'id' int(11) NOT NULL AUTO_INCREMENT;
'link' varchar(255) NOT NULL,
'PostId' int(11) NOT NULL,
PRIMARY KEY('id')
)ENGINE = MyISAM AUTO_INCREMENT = 2 DEFAULT CHARSET = utf-8;

CREATE TABLE IF NOT EXIST 'notif' (
'id' int(11) NOT NULL AUTO_INCREMENT,
'text''varchar(255) NOT NULL,
'url' varchar(255) DEFAULT NULL,
'date' datetime DEFAULT NULL,
'personId' int(11) NOT NULL,
PRIMARY KEY('id')
)ENGINE = MyISAM AUTO_INCREMENT = 2 DEFAULT CHARSET = utf-8;

CREATE TABLE 'quoter' (
'id' int(11) NOT NULL AUTO_INCREMENT,
'Qname' varchar(255) DEFAULT NULL,
'Qemail' varchar(255) NOT NULL,
'password' varchar(255) NOT NULL,
PRIMARY KEY('id'),
UNIQUE KEY('Index_quoter_on_email' ('Qemail')
)ENGINE = MyISAM AUTO_INCREMENT = 2 DEFAULT CHARSET = utf-8;

CREATE TABLE IF NOT EXIST 'quote' (
'id' int(11) NOT NULL AUTO_INCREMENT,
'title' varhcar(255) DEFAULT NULL,
'date' datetime DEFAULT NULL,
'postId' int(11) NOT NULL,
'ownerId' int(11) NOT NULL,
'popularity' int(11) DEFAULT '0';
PRIMARY KEY('id')
)ENGINE = MyISAM AUTO_INCREMENT = 2 DEFAULT CHARSET = utf-8;