BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS room_details;
DROP SEQUENCE IF EXISTS seq_room_id;
DROP TABLE IF EXISTS floor;
DROP SEQUENCE IF EXISTS seq_floor_id;
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
    number_of_floors int DEFAULT(1),
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
INSERT INTO house_details(house_id, foundation_size,region,house_name,user_id,number_of_floors,is_Private)
VALUES(993,20,'Portland', 'Public test house 1', 3, 2, false);
INSERT INTO house_details(house_id, foundation_size,region,house_name,user_id,number_of_floors,is_Private)
VALUES(994,50,'Brooklin', 'Absurd Public test house 2', 3, 7, false);



CREATE SEQUENCE seq_floor_id
  INCREMENT BY 1
  NO MAXVALUE
  START WITH 100
  CACHE 1;

CREATE TABLE floor (
    floor_id int DEFAULT nextval('seq_floor_id') NOT NULL,
    house_id int NOT NULL,
    floor_level int NOT NULL,
    CONSTRAINT PK_floor_id PRIMARY KEY (floor_id),
    CONSTRAINT FK_house_id FOREIGN KEY (house_id) REFERENCES house_details (house_id)
);

INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(90,990,1);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(91,990,2);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(92,990,3);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(93,991,1);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(94,992,1);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(95,992,2);
INSERT INTO floor(floor_id, house_id,floor_level)
VALUES(96,992,3);


CREATE SEQUENCE seq_room_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  START WITH 10
  CACHE 1;


CREATE TABLE room_details(
    room_id int DEFAULT nextval('seq_room_id') NOT NULL,
    room_name varchar(50) NOT NULL,
    room_size int NOT NULL,
    floor_id int,
    CONSTRAINT PK_room_id PRIMARY KEY(room_id),
    CONSTRAINT FK_floor_id FOREIGN KEY (floor_id) REFERENCES floor(floor_id)
);


INSERT INTO room_details(room_id, room_name, room_size, floor_id)
VALUES (1, 'Bedroom',10, 90);
INSERT INTO room_details(room_id, room_name, room_size, floor_id)
VALUES (2, 'Bedroom',7, 90);
INSERT INTO room_details(room_id, room_name, room_size, floor_id)
VALUES (3, 'Bedroom',10, 91);
INSERT INTO room_details(room_id, room_name, room_size, floor_id)
VALUES (4, 'Bedroom',10, 92);
INSERT INTO room_details(room_id, room_name, room_size, floor_id)
VALUES (5, 'Bedroom',10, 93);
INSERT INTO room_details(room_id, room_name, room_size, floor_id)
VALUES (6, 'Bedroom',10, 94);
INSERT INTO room_details(room_id, room_name, room_size, floor_id)
VALUES (7, 'Bedroom',10, 95);
INSERT INTO room_details(room_id, room_name, room_size, floor_id)
VALUES (8, 'Bedroom',10, 96);


COMMIT TRANSACTION;
