--HOTEL
insert into HOTEL (NOMBRE, DESAYUNO, RESTAURANTE, DIRECCION, PISCINA, HORARIO, PARQUEADERO) values ('Browseblab', 0, 1, 'background', 1, 1, 1); 
insert into HOTEL (NOMBRE, DESAYUNO, RESTAURANTE, DIRECCION, PISCINA, HORARIO, PARQUEADERO) values ('Agivu', 0, 0, 'Team-oriented', 1, 0, 0);
insert into HOTEL (NOMBRE, DESAYUNO, RESTAURANTE, DIRECCION, PISCINA, HORARIO, PARQUEADERO) values ('Edgeblab', 0, 1, 'De-engineered', 1, 1, 1);
insert into HOTEL (NOMBRE, DESAYUNO, RESTAURANTE, DIRECCION, PISCINA, HORARIO, PARQUEADERO) values ('Chatterpoint', 1, 1, 'object-oriented', 1, 1, 1);
insert into HOTEL (NOMBRE, DESAYUNO, RESTAURANTE, DIRECCION, PISCINA, HORARIO, PARQUEADERO) values ('Flashpoint', 1, 1, 'maximized', 0, 0, 1);
insert into HOTEL (NOMBRE, DESAYUNO, RESTAURANTE, DIRECCION, PISCINA, HORARIO, PARQUEADERO) values ('Jabbersphere', 0, 0, 'fresh-thinking', 0, 0, 0);
insert into HOTEL (NOMBRE, DESAYUNO, RESTAURANTE, DIRECCION, PISCINA, HORARIO, PARQUEADERO) values ('Skyba', 0, 0, 'Virtual', 1, 0, 0);
insert into HOTEL (NOMBRE, DESAYUNO, RESTAURANTE, DIRECCION, PISCINA, HORARIO, PARQUEADERO) values ('Topiczoom', 1, 1, 'definition', 0, 0, 0);
insert into HOTEL (NOMBRE, DESAYUNO, RESTAURANTE, DIRECCION, PISCINA, HORARIO, PARQUEADERO) values ('Voolia', 0, 1, 'collaboration', 1, 1, 1);
insert into HOTEL (NOMBRE, DESAYUNO, RESTAURANTE, DIRECCION, PISCINA, HORARIO, PARQUEADERO) values ('Chatterbridge', 0, 0, 'Graphic Interface', 0, 1, 1);

--HOSTALES
insert into HOSTAL (APERTURA, CIERRE, DIRECCION, NOMBRE, DESAYUNO) values (6, 17, 'Landaulet', 'Hostal 1', 1);
insert into HOSTAL (APERTURA, CIERRE, DIRECCION, NOMBRE, DESAYUNO) values (16, 22, 'F250', 'Hostal 2', 1);
insert into HOSTAL (APERTURA, CIERRE, DIRECCION, NOMBRE, DESAYUNO) values (0, 2, 'Civic', 'Hostal 3', 0);
insert into HOSTAL (APERTURA, CIERRE, DIRECCION, NOMBRE, DESAYUNO) values (22, 13, 'Fiero', 'Hostal 4', 1);
insert into HOSTAL (APERTURA, CIERRE, DIRECCION, NOMBRE, DESAYUNO) values (10, 11, 'Charger', 'Hostal 5', 0);
insert into HOSTAL (APERTURA, CIERRE, DIRECCION, NOMBRE, DESAYUNO) values (24, 12, 'Tracer', 'Hostal 6', 1);
insert into HOSTAL (APERTURA, CIERRE, DIRECCION, NOMBRE, DESAYUNO) values (4, 24, 'Seville', 'Hostal 7', 0);
insert into HOSTAL (APERTURA, CIERRE, DIRECCION, NOMBRE, DESAYUNO) values (18, 13, '9-2X', 'Hostal 8', 0);
insert into HOSTAL (APERTURA, CIERRE, DIRECCION, NOMBRE, DESAYUNO) values (23, 10, 'Ram 1500 Club', 'Hostal 9', 1);
insert into HOSTAL (APERTURA, CIERRE, DIRECCION, NOMBRE, DESAYUNO) values (5, 5, 'Countach', 'Hostal 10', 0);

--VIVIENDAU
insert into VIVIENDAU (DIRECCION, NOMBRE) values ('665 Kropf Point', 'V1');
insert into VIVIENDAU (DIRECCION, NOMBRE) values ('02109 Mifflin Avenue', 'V2');
insert into VIVIENDAU (DIRECCION, NOMBRE) values ('7 Hauk Place', 'V3');
insert into VIVIENDAU (DIRECCION, NOMBRE) values ('803 Comanche Crossing', 'V4');
insert into VIVIENDAU (DIRECCION, NOMBRE) values ('05 Declaration Park', 'V5');
insert into VIVIENDAU (DIRECCION, NOMBRE) values ('82 Mcbride Circle', 'V6');
insert into VIVIENDAU (DIRECCION, NOMBRE) values ('5901 International Court', 'V7');
insert into VIVIENDAU (DIRECCION, NOMBRE) values ('7549 Hauk Park', 'V8');
insert into VIVIENDAU (DIRECCION, NOMBRE) values ('5644 Derek Avenue', 'V9');
insert into VIVIENDAU (DIRECCION, NOMBRE) values ('1937 Gina Pass', 'V10');

--VECINO
insert into VECINO (CEDULA, NOMBRE, FECHANACIMIENTO) values ('154.41.194.101', 'Veronike Jowitt', '11/12/2017');
insert into VECINO (CEDULA, NOMBRE, FECHANACIMIENTO) values ('168.106.18.153', 'Jamison Trodler', '15/03/2018');
insert into VECINO (CEDULA, NOMBRE, FECHANACIMIENTO) values ('28.122.32.72', 'Nettle Hickford', '07/02/2018');
insert into VECINO (CEDULA, NOMBRE, FECHANACIMIENTO) values ('232.50.111.156', 'Halimeda Calendar', '21/02/2018');
insert into VECINO (CEDULA, NOMBRE, FECHANACIMIENTO) values ('74.133.252.154', 'Ferne Kenealy', '08/09/2017');
insert into VECINO (CEDULA, NOMBRE, FECHANACIMIENTO) values ('252.94.60.87', 'Charlie Cleave', '17/12/2017');
insert into VECINO (CEDULA, NOMBRE, FECHANACIMIENTO) values ('86.232.78.7', 'Ora Selvester', '13/02/2018');
insert into VECINO (CEDULA, NOMBRE, FECHANACIMIENTO) values ('51.34.122.5', 'Omar Melwall', '26/09/2017'); 
insert into VECINO (CEDULA, NOMBRE, FECHANACIMIENTO) values ('182.245.167.74', 'Kimberlyn Dodsworth', '03/05/2017');
insert into VECINO (CEDULA, NOMBRE, FECHANACIMIENTO) values ('73.113.211.26', 'Lara Stratiff', '19/10/2017');

--HOTELROOM
insert into HOTELROOM (TIPO, CUARTO, BANERA, JACUZZI, SALA, COCINA, CABLE, PRECIO, CAPACIDAD, DIRECCION, NOMBREHOTEL) values ('normal', 1, 1, 0, 1, 1, 1, 1, 5, '9176 Reinke Park', 'Agivu');
insert into HOTELROOM (TIPO, CUARTO, BANERA, JACUZZI, SALA, COCINA, CABLE, PRECIO, CAPACIDAD, DIRECCION, NOMBREHOTEL) values ('normal', 2, 0, 1, 0, 0, 1, 1, 11, '4855 Banding Court', 'Agivu');
insert into HOTELROOM (TIPO, CUARTO, BANERA, JACUZZI, SALA, COCINA, CABLE, PRECIO, CAPACIDAD, DIRECCION, NOMBREHOTEL) values ('suite', 3, 1, 0, 0, 0, 0, 1, 11, '11773 Stephen Avenue', 'Voolia');
insert into HOTELROOM (TIPO, CUARTO, BANERA, JACUZZI, SALA, COCINA, CABLE, PRECIO, CAPACIDAD, DIRECCION, NOMBREHOTEL) values ('suite', 4, 0, 1, 0, 0, 1, 1, 8, '23 Sommers Point', 'Edgeblab');
insert into HOTELROOM (TIPO, CUARTO, BANERA, JACUZZI, SALA, COCINA, CABLE, PRECIO, CAPACIDAD, DIRECCION, NOMBREHOTEL) values ('suite', 5, 1, 1, 0, 1, 0, 0, 10, '669 Dahle Drive', 'Chatterbridge');
insert into HOTELROOM (TIPO, CUARTO, BANERA, JACUZZI, SALA, COCINA, CABLE, PRECIO, CAPACIDAD, DIRECCION, NOMBREHOTEL) values ('suite', 6, 1, 0, 1, 0, 1, 1, 4, '15145 Killdeer Crossing', 'Topiczoom');
insert into HOTELROOM (TIPO, CUARTO, BANERA, JACUZZI, SALA, COCINA, CABLE, PRECIO, CAPACIDAD, DIRECCION, NOMBREHOTEL) values ('normal', 7, 0, 0, 1, 0, 1, 1, 2, '4 Elka Pass', 'Voolia');
insert into HOTELROOM (TIPO, CUARTO, BANERA, JACUZZI, SALA, COCINA, CABLE, PRECIO, CAPACIDAD, DIRECCION, NOMBREHOTEL) values ('suite', 8, 0, 0, 1, 0, 0, 1, 2, '1386 Brickson Park Park', 'Jabbersphere');
insert into HOTELROOM (TIPO, CUARTO, BANERA, JACUZZI, SALA, COCINA, CABLE, PRECIO, CAPACIDAD, DIRECCION, NOMBREHOTEL) values ('normal', 9, 0, 1, 0, 1, 0, 1, 12, '9 Merrick Pass', 'Skyba'); 
insert into HOTELROOM (TIPO, CUARTO, BANERA, JACUZZI, SALA, COCINA, CABLE, PRECIO, CAPACIDAD, DIRECCION, NOMBREHOTEL) values ('normal', 10, 0, 0, 1, 0, 0, 0, 15, '80644 Sycamore Lane', 'Chatterbridge');

--HOSTALROOM
insert into HOSTALROOM (CUARTO, CAPACIDAD, PRECIO, NOMBREHOSTAL) values (1, 14, 736936, 'Hostal 4');
insert into HOSTALROOM (CUARTO, CAPACIDAD, PRECIO, NOMBREHOSTAL) values (2, 3, 384484, 'Hostal 9');
insert into HOSTALROOM (CUARTO, CAPACIDAD, PRECIO, NOMBREHOSTAL) values (3, 4, 546210, 'Hostal 9');
insert into HOSTALROOM (CUARTO, CAPACIDAD, PRECIO, NOMBREHOSTAL) values (4, 10, 117684, 'Hostal 3');
insert into HOSTALROOM (CUARTO, CAPACIDAD, PRECIO, NOMBREHOSTAL) values (5, 8, 901824, 'Hostal 7');
insert into HOSTALROOM (CUARTO, CAPACIDAD, PRECIO, NOMBREHOSTAL) values (6, 12, 803156, 'Hostal 9');
insert into HOSTALROOM (CUARTO, CAPACIDAD, PRECIO, NOMBREHOSTAL) values (7, 2, 960445, 'Hostal 8');
insert into HOSTALROOM (CUARTO, CAPACIDAD, PRECIO, NOMBREHOSTAL) values (8, 2, 643886, 'Hostal 8');
insert into HOSTALROOM (CUARTO, CAPACIDAD, PRECIO, NOMBREHOSTAL) values (9, 2, 869155, 'Hostal 7');
insert into HOSTALROOM (CUARTO, CAPACIDAD, PRECIO, NOMBREHOSTAL) values (10, 13, 176845, 'Hostal 9');
