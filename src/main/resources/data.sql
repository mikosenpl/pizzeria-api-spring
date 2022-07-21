INSERT INTO users (id, name, email, password) VALUES ('3ae2c889-4c3e-466a-84c0-4a3e15d1920a', 'Jan', 'admin@admin.com', '$2a$10$m/TI94zoM2PJQQuCuM8MSOBGG7Dsc3vuTRoOqGC1ehY95hfedBCl2');
INSERT INTO users (id, name, email, password) VALUES ('65a95de3-bc1f-4f28-829a-4dcdcaedd33e', 'Adam', 'adam_pracownik@pizza.com', '$2a$10$dScITNJs6mwgByQvl7DFGekZqhFuwy7VoKTcUouR5ztXL0vNstYly');
INSERT INTO users (id, name, email, password) VALUES ('d9a830fe-402a-4834-9c81-9b7580073c67', 'Tomasz', 'gosc@gmail.com', '$2a$10$l2cGmLjSgyNFwKSMc4iVU.NKzMw6InzdkrCFtWkhr8vaJS8Hex5Uu');


INSERT INTO role (id, name) VALUES ('1afa4286-46ad-4467-8daa-f77903500585', 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES ('402152bd-5084-432f-b74b-bb32315743dd', 'ROLE_STAFF');
INSERT INTO role (id, name) VALUES ('31600a3d-7f2d-4e36-a6c8-6981f8367732', 'ROLE_USER');

INSERT INTO users_roles (user_id, roles_id) VALUES ('3ae2c889-4c3e-466a-84c0-4a3e15d1920a','1afa4286-46ad-4467-8daa-f77903500585');
INSERT INTO users_roles (user_id, roles_id) VALUES ('65a95de3-bc1f-4f28-829a-4dcdcaedd33e','402152bd-5084-432f-b74b-bb32315743dd');
INSERT INTO users_roles (user_id, roles_id) VALUES ('d9a830fe-402a-4834-9c81-9b7580073c67','31600a3d-7f2d-4e36-a6c8-6981f8367732');