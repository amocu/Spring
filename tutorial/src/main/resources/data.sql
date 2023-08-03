INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO cliente(name) VALUES ('Abraham');
INSERT INTO cliente(name) VALUES ('José');
INSERT INTO cliente(name) VALUES ('Paco');

INSERT INTO prestamo(game_id, cliente_id, fechainicio, fechafin) VALUES (1,1,'2023-07-31','2023-08-04');
INSERT INTO prestamo(game_id, cliente_id, fechainicio, fechafin) VALUES (2,2,'2023-08-02','2023-08-05');
INSERT INTO prestamo(game_id, cliente_id, fechainicio, fechafin) VALUES (1,3,'2023-08-05','2023-08-06');
INSERT INTO prestamo(game_id, cliente_id, fechainicio, fechafin) VALUES (3,1,'2023-08-05','2023-08-10');