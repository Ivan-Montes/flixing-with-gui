INSERT INTO GENRES(name, description) VALUES('Fantasia','Mundos imaginarios con criaturas misteriosas');
INSERT INTO GENRES(name, description) VALUES('Aventura','Acción y desafio para los protagonistas');
INSERT INTO GENRES(name, description) VALUES('Comedia','Humor y situaciones graciosas');

INSERT INTO FLIXES(title, genre_id) VALUES('Willow',1);
INSERT INTO FLIXES(title, genre_id) VALUES('La princesa prometida',2);
INSERT INTO FLIXES(title, genre_id) VALUES('Cazafantasmas',3);

INSERT INTO PEOPLE(name, surname) VALUES('Warwick','Davis');
INSERT INTO PEOPLE(name, surname) VALUES('Joanne','Whalley');
INSERT INTO PEOPLE(name, surname) VALUES('Val','Kilmer');
INSERT INTO PEOPLE(name, surname) VALUES('Ron','Howard');
INSERT INTO PEOPLE(name, surname) VALUES('Robin','Wright');
INSERT INTO PEOPLE(name, surname) VALUES('Cary','Elwes');
INSERT INTO PEOPLE(name, surname) VALUES('Mandy','Patinkin');
INSERT INTO PEOPLE(name, surname) VALUES('William','Goldman');
INSERT INTO PEOPLE(name, surname) VALUES('Dan','Aykroyd');
INSERT INTO PEOPLE(name, surname) VALUES('Harold','Ramis');
INSERT INTO PEOPLE(name, surname) VALUES('Bill','Murray');
INSERT INTO PEOPLE(name, surname) VALUES('Sigourney','Weaver');

INSERT INTO POSITIONS(name, description) VALUES('Actuar','Emociones reales en situaciones imaginarias');
INSERT INTO POSITIONS(name, description) VALUES('Dirigir','Organiza a todos, Director de Orquesta');
INSERT INTO POSITIONS(name, description) VALUES('Guionizar','Encargado de plasmar en papel o adaptar el texto');

INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(1,1,1);
INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(1,2,1);
INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(1,3,1);
INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(1,4,2);
INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(2,5,1);
INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(2,6,1);
INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(2,7,1);
INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(2,8,3);
INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(3,9,1);
INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(3,9,3);
INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(3,10,1);
INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(3,10,3);
INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(3,11,1);
INSERT INTO FLIX_PERSON_POSITION(fx_id, per_id, pos_id) VALUES(3,12,1);

