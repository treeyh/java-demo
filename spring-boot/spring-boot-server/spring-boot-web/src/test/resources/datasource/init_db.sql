SET MODE MYSQL;

DROP TABLE IF EXISTS active_info;
CREATE TABLE active_info (
  id bigint NOT NULL AUTO_INCREMENT,
  code varchar(64) NOT NULL,
  size int NOT NULL,
  score double NOT NULL,
  type int NOT NULL,
  status int NOT NULL,
  create_time timestamp NOT NULL,
  update_time timestamp NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO active_info VALUES (1, '1111', 1, 1.23, 2, 1, '2018-05-05 04:12:12', '2018-05-05 04:12:12');
INSERT INTO active_info VALUES (2, '2222', 1, 1.23, 2, 1, '2018-05-05 04:12:12', '2018-05-05 04:12:12');
INSERT INTO active_info VALUES (3, '3333', 1, 1.23, 2, 1, '2018-05-05 04:12:12', '2018-05-05 04:12:12');
INSERT INTO active_info VALUES (4, '4444', 10, 1.10, 1, 1, '2019-01-07 16:05:28', '2019-01-07 16:05:28');