SET MODE MYSQL;

DROP TABLE IF EXISTS active_info;
CREATE TABLE active_info (
  id bigint NOT NULL AUTO_INCREMENT,
  code varchar(64) NOT NULL,
  size int NOT NULL,
  score double NOT NULL,
  type int NOT NULL,
  status int NOT NULL,
  create_time datetime NOT NULL,
  update_time datetime NOT NULL,
  PRIMARY KEY (id)
);