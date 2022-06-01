BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS house_details;
DROP SEQUENCE IF EXISTS seq_house_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('bob','$2a$10$4oOJ/QruQg14j7.Bda6hau8PAyX1mtUIFpSUWmSQj/u9OIzpYzHNu','ROLE_HOME_BUYER');


CREATE SEQUENCE seq_house_id
  INCREMENT BY 1
  NO MAXVALUE
  START WITH 1000
  CACHE 1;

CREATE TABLE house_details (
    house_id int DEFAULT nextval('seq_house_id') NOT NULL,
    foundation_size int NOT NULL,
    region varchar(100) NOT NULL,
    house_name varchar(100) NOT NULL,
    user_id int NOT NULL,
    number_of_floors int DEFAULT(0),
    is_Private boolean NOT NULL,
    CONSTRAINT PK_house_id PRIMARY KEY (house_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)

);

INSERT INTO house_details(house_id, foundation_size,region,house_name,user_id,number_of_floors,is_Private)
VALUES(990,100,'America', 'Big House', 3, 4, true);
INSERT INTO house_details(house_id, foundation_size,region,house_name,user_id,number_of_floors,is_Private)
VALUES(991,25,'America', 'Small House', 2, 1, false);
INSERT INTO house_details(house_id, foundation_size,region,house_name,user_id,number_of_floors,is_Private)
VALUES(992,150,'America', 'Party House', 3, 2, true);


COMMIT TRANSACTION;
