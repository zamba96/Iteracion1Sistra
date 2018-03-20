--Hostal
CREATE TABLE Hostal (
	Apertura number, 
	Cierre number, 
	Direccion varchar(50), 
	Nombre varchar(30)
	);

/*Hotel*/
CREATE TABLE Hotel (
	Desayuno number,
	Direccion varchar(50),
	Restaurante number,
	Piscina number,
	parqueadero number,
	horario number
	);

/*ViviendaU*/
CREATE TABLE ViviendaU (
	Direccion varchar(50),
	Nombre varchar(30)
	);

--HostalRoom
CREATE TABLE HostalRoom (
	Id number,
	Capacidad number,
	Precio float,
	Direccion varchar(50),
	NombreHostal varchar(30)
	);
	
--HotelRoom
CREATE TABLE HotelRoom (
	Cuarto number,
	Banera number,
	Jacuzzi number,
	Sala number,
	Cocina number,
	Cable number,
	Precio float,
	Capacidad number,
	Direccion varchar(50),
	NombreHotel varchar(30)
	);

--ViviendaURoom
CREATE TABLE ViviendaURoom (
	Cuarto number,
	Compartida float,
	Restaurante float,
	SalaDeEstudio float,
	SalaEsparcimiento float,
	Gym float,
	Precio float,
	Direccion varchar(50), --FK
	NombreVivienta varchar(30) --FK
	);
	
--Inmueble
CREATE TABLE Inmueble (
	Id number,
	Amoblado number,
	Servicios number,
	Cable number,
	Administracion number,
	Precio float,
	Direccion varchar(50),
	Dueno number --FK Usuario.ID
	);
	
--VecinoRoom
CREATE TABLE VecinoRoom (
	Habitaciones number,
	Banos number,
	Direccion varchar(50),
	Menaje varchar(30),
	Precio float,
	Dueno number --FK Vecino.ID
	);
	
--ReservaHostal
CREATE TABLE ReservaHostal (
	FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario number,
	Cuarto float --FK HostalRoom.Id
	);
	
--ReservaHotel
CREATE TABLE ReservaHotel (
	FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario number,
	Cuarto number --FK HotelRoom.Id
	);
	
--ContratoResidencia
CREATE TABLE ContratoResidencia (
	FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario number,
	Cuarto number --FK Residencia.Id
	);
	
--ContratoInmueble
CREATE TABLE ContratoInmueble (
	FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario number,
	Cuarto number --FK Inmueble.Id
	);
	
--Usuario
CREATE TABLE Usuario (
	Id number,
	Cedula varchar(30),
	Nombre varchar(30),
	FechaNacimiento varchar(30)
	);
	
--Relacionado
CREATE TABLE Relacionado (
	Id number --FK Usuario.Id
	);
	
--Estudiante
CREATE TABLE Estudiante (
	Id number, --FK Relacionado.Id
	Carnet varchar(30) --CK Carnet
	);

--Profesor
CREATE TABLE Profesor (
	Id number --FK Relacionado.Id
	);
	
--Empleado
CREATE TABLE Empleado (
	Id number --Relacionado.Id
	);

--Padre
CREATE TABLE Padre (
	Id number, --FK Usuario.id
	Hijo number --FK Estudiante.id
	);

--Egresado
CREATE TABLE Egresado (
	Id number --FK Usuario.Id
	);
	
--Vecino
CREATE TABLE Vecino (
	Id number,
	Cedula varchar(30),
	Nombre varchar(30),
	FechaNacimiento varchar(30)
	);
	
