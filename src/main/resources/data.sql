INSERT INTO GENRES(name, description) VALUES('Fantasy','Imaginary worlds with mysterious creatures');
INSERT INTO GENRES(name, description) VALUES('Adventure','Action and challenge for the protagonists');
INSERT INTO GENRES(name, description) VALUES('Comedy','Humor and funny situations');

INSERT INTO FLIXES(title, genre_id) VALUES('Willow',1);
INSERT INTO FLIXES(title, genre_id) VALUES('The Princess Bride',2);
INSERT INTO FLIXES(title, genre_id) VALUES('Ghostbusters',3);

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

INSERT INTO POSITIONS(name, description) VALUES('Acting','Real emotions in imaginary situations');
INSERT INTO POSITIONS(name, description) VALUES('Direction','Organize everyone, Orchestra Conductor');
INSERT INTO POSITIONS(name, description) VALUES('Scripting','Responsible for putting on paper or adapting the text');

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

