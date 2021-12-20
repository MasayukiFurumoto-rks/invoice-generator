DROP TABLE IF EXISTS user_companies;

CREATE TABLE user_companies(
user_companies_id serial NOT NULL,
user_companies_name text NOT NULL,
deleted boolean NOT NULL default false
);

INSERT INTO user_companies(user_companies_name) VALUES ('������Ѓ��N�X�p�[�g�i�[�Y'); 

DROP TABLE IF EXISTS user_departments;
CREATE TABLE user_departments(
user_departments_id serial NOT NULL,
user_departments_name text NOT NULL,
user_company_id BIGINT UNSIGNED NOT NULL,
deleted boolean NOT NULL default false,
FOREIGN KEY fk_user_com_id(user_company_id) REFERENCES user_companies(user_companies_id),
CONSTRAINT  user_departments_pkey PRIMARY KEY (user_departments_id)
);


INSERT INTO user_departments (user_departments_name,user_company_id) VALUES ('�c�ƕ�',1);

DROP TABLE IF EXISTS users;

CREATE TABLE users(
id serial NOT NULL,
name text NOT NULL,
position varchar(30) NOT NULL default '',
email varchar(255) NOT NULL,
password text NOT NULL,
is_administrator boolean NOT NULL default false,
deleted boolean NOT NULL default false ,
user_department_id BIGINT UNSIGNED NOT NULL,
FOREIGN KEY fk_user_dep_id(user_department_id) REFERENCES user_departments(user_departments_id),
CONSTRAINT  users_pkey PRIMARY KEY (id),
UNIQUE (id,email)
);

INSERT INTO users(name,position,email,password,user_department_id) 
VALUES ('testUser','�e�X�g','test@test.co.jp','testtest',1);


DROP TABLE IF EXISTS items;
CREATE TABLE items(
items_id serial NOT NULL,
items_name text NOT NULL,
items_price int NOT NULL,
items_description text NOT NULL,
items_deleted boolean NOT NULL default false ,
items_owner_id BIGINT UNSIGNED NOT NULL,
FOREIGN KEY (items_owner_id) REFERENCES users(id),
CONSTRAINT  items_pkey PRIMARY KEY (items_id)
);

INSERT INTO items(items_name,items_price,items_description ,items_owner_id) VALUES ('�y�y���Z1�����v����',100000,'�y�y���Z1�����̗���',1);
INSERT INTO items(items_name,items_price,items_description ,items_owner_id) VALUES ('�y�y���Z1�N�v����',1000000,'�y�y���Z1�N�̗����B�P���v������肨���B',1);

DROP TABLE IF EXISTS clients;

CREATE TABLE clients (
clients_id serial NOT NULL,
clients_name varchar(50) NOT NULL,
clients_zipcode varchar(8) NOT NULL,
clients_address varchar(50) NOT NULL,
clients_building varchar(50) NOT NULL default '',
clients_telephone varchar(13) NOT NULL,
clients_credit_limit BIGINT NOT NULL,
clients_owner_id BIGINT UNSIGNED NOT NULL,
FOREIGN KEY fk_owner_id(clients_owner_id) REFERENCES users(id),
clients_remarks varchar(200) NOT NULL default '' ,
clients_deleted boolean NOT NULL default false 
);

INSERT INTO clients(clients_name,clients_zipcode,clients_address,clients_building,clients_telephone,clients_credit_limit,clients_owner_id,clients_remarks) 
VALUES('������Ѓ��N�X�p�[�g�i�[�Y','160-0022','�����s�V�h��V�h4-3-25','TOKYU REIT�V�h�r��8F','03-6675-3638',10000000,1,'���Ђł��B');

INSERT INTO clients(clients_name,clients_zipcode,clients_address,clients_building,clients_telephone,clients_credit_limit,clients_owner_id,clients_remarks) 
VALUES('������Ѓ��N�X','151-0051','�����s�a�J���ʃ��J5-27-5','�����N�X�N�G�A�V�h7�K','03-0000-0000',10000000,1,'�e��Ђł��B');

INSERT INTO clients(clients_name,clients_zipcode,clients_address,clients_building,clients_telephone,clients_credit_limit,clients_owner_id,clients_remarks) 
VALUES('�|�[�g�������','160-0023','�����s�V�h�搼�V�h8-17-1','�Z�F�s���Y�V�h�O�����h�^���[12F','03-0000-0000',999999,1,'�Ö{�̑O�E�ł��B');

DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts (
contacts_id serial NOT NULL,
contacts_name varchar(30) NOT NULL,
contacts_client_id BIGINT UNSIGNED NOT NULL,
FOREIGN KEY pk_client_id(contacts_client_id) REFERENCES clients(clients_id),
contacts_department varchar(30) NOT NULL,
contacts_position varchar(30) NOT NULL DEFAULT '',
contacts_telephone varchar(13) NOT NULL,
contacts_email varchar(255) NOT NULL,
contacts_owner_id BIGINT UNSIGNED NOT NULL,
FOREIGN KEY pk_owner_id(contacts_owner_id) REFERENCES users(id),
contacts_deleted boolean NOT NULL default false
);

INSERT INTO contacts (contacts_name,contacts_client_id,contacts_department,contacts_position,contacts_telephone,contacts_email,contacts_owner_id) 
VALUES ('�ԉ��E��',3,'�A�E�x�����ƕ�','','000-0000-0000','yuta.akashio@portport.co.jp',1);


DROP TABLE IF EXISTS trade_statuses;

CREATE TABLE trade_statuses (
trade_statuses_id serial NOT NULL,
trade_statuses_key BIGINT UNSIGNED NOT NULL,
trade_statuses_value varchar(15) NOT NULL,
UNIQUE (trade_statuses_key)
);

INSERT INTO trade_statuses (trade_statuses_key,trade_statuses_value)
VALUES (1,'�A�|�C���g�l��');
INSERT INTO trade_statuses (trade_statuses_key,trade_statuses_value)
VALUES (2,'��Ē�');
INSERT INTO trade_statuses (trade_statuses_key,trade_statuses_value)
VALUES (3,'����������E��ǂ�');
INSERT INTO trade_statuses (trade_statuses_key,trade_statuses_value)
VALUES (4,'������');
INSERT INTO trade_statuses (trade_statuses_key,trade_statuses_value)
VALUES (5,'�\�������t�ς݁A�_�������');
INSERT INTO trade_statuses (trade_statuses_key,trade_statuses_value)
VALUES (6,'��');
INSERT INTO trade_statuses (trade_statuses_key,trade_statuses_value)
VALUES (7,'����');





DROP TABLE IF EXISTS trades;

CREATE TABLE trades (
trades_id serial NOT NULL,
trades_name varchar(50) NOT NULL,
trades_trade_status_key BIGINT UNSIGNED NOT NULL,
FOREIGN KEY fk_status_key(trades_trade_status_key) REFERENCES trade_statuses(trade_statuses_key),
trades_date DATE NOT NULL,
trades_contact_id BIGINT UNSIGNED NOT NULL,
FOREIGN KEY fk_contact_id(trades_contact_id) REFERENCES contacts(contacts_id),
trades_owner_id BIGINT UNSIGNED NOT NULL,
FOREIGN KEY fk_owner_id(trades_owner_id) REFERENCES users(id),
trades_remarks varchar(200) NOT NULL default '' ,
trades_deleted boolean NOT NULL default false,
trades_invoice_id BIGINT UNSIGNED default 0
);

INSERT INTO trades(
trades_name,
trades_trade_status_key,
trades_date,
trades_contact_id,
trades_owner_id,
trades_remarks
)
VALUES('�|�[�g�������_20211216_�V�K���',1,'2021-12-16',1,1,'�����k�ł��I');


DROP TABLE IF EXISTS trading_items;

CREATE TABLE trading_items (
trading_items_id serial NOT NULL,
trading_items_item_id BIGINT UNSIGNED NOT NULL,
trading_items_quantity INT NOT NULL,
trading_items_trade_id BIGINT UNSIGNED NOT NULL,
trading_items_remarks varchar(200) NOT NULL default '' ,
trading_items_owner_id BIGINT UNSIGNED NOT NULL,
FOREIGN KEY fk_item_id(trading_items_item_id) REFERENCES items(items_id),
FOREIGN KEY fk_trade_id(trading_items_owner_id) REFERENCES trades(trades_id),
FOREIGN KEY fk_owner_id(trading_items_owner_id) REFERENCES users(id)
);

INSERT INTO trading_items(
trading_items_item_id,trading_items_quantity,trading_items_trade_id,trading_items_remarks,trading_items_owner_id)
VALUES(1,5,1,'11�����ŕς������炵���A���̌`�Ō_��ƂȂ�܂����B','1');


