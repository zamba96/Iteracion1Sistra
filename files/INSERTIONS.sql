
insert into Persona (CEDULA, NOMBRE,ROl, FECHANACIMIENTO) values (1, 'Dillon Loftus','Cliente', TO_DATE('14/02/2018', 'dd/mm/yyyy')); 
insert into Persona (CEDULA, NOMBRE,Rol, FECHANACIMIENTO) values (2, 'Vitia Greenfield','Cliente', TO_DATE('22/10/2017', 'dd/mm/yyyy')); 
insert into Cliente(CEDULA) Values(1);
insert into Cliente(CEDULA) Values(2);

insert into Alojamiento(ID,precio,capacidad,DType) values(1,10,2,'Hotel');
insert into Alojamiento(ID,precio,capacidad,DType) values(2,20,3,'Hotel');

insert into Reserva (ID,FECHAINICIO,FechaFin,Precio,Cedula,Cuarto) values (1,TO_DATE('01/09/2018', 'dd/mm/yyyy'),TO_DATE('04/09/2018', 'dd/mm/yyyy'),40,1,1);
insert into Reserva (ID,FECHAINICIO,FechaFin,Precio,Cedula,Cuarto) values (2,TO_DATE('01/09/2018', 'dd/mm/yyyy'),TO_DATE('04/09/2018', 'dd/mm/yyyy'),40,2,2);
insert into Reserva (ID,FECHAINICIO,FechaFin,Precio,Cedula,Cuarto) values (3,TO_DATE('05/10/2018', 'dd/mm/yyyy'),TO_DATE('10/10/2018', 'dd/mm/yyyy'),40,1,2);
insert into Reserva (ID,FECHAINICIO,FechaFin,Precio,Cedula,Cuarto) values (4,TO_DATE('11/12/2018', 'dd/mm/yyyy'),TO_DATE('12/12/2018', 'dd/mm/yyyy'),40,1,2);
insert into Reserva (ID,FECHAINICIO,FechaFin,Precio,Cedula,Cuarto) values (5,TO_DATE('05/10/2018', 'dd/mm/yyyy'),TO_DATE('09/10/2018', 'dd/mm/yyyy'),40,2,1);


Select TABLE_NAME, COLUMN_NAME, DATA_DEFAULT from USER_TAB_COLUMNS
where TABLE_NAME = 'ALOJAMIENTO';