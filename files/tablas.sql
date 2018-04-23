/* SQL Que crea todas las tablas del proyecto*/

--Hostal
CREATE TABLE Hostal (
	Apertura number, 
	Cierre number, 
	Direccion varchar(50), 
	Nombre varchar(30),
    Desayuno number
	);

/*Hotel*/
CREATE TABLE Hotel (
	Desayuno number,
	Direccion varchar(50),
	Restaurante number,
	Piscina number,
	parqueadero number,
	horario number,
    nombre varchar(30)
	);

/*ViviendaU*/
CREATE TABLE ViviendaU (
	Direccion varchar(50),
	Nombre varchar(30)
	);

--HostalRoom
CREATE TABLE HostalRoom (
	Cuarto number,
	Capacidad number,
	Precio float,
	NombreHostal varchar(30)
	);
	
--HotelRoom
CREATE TABLE HotelRoom (
    Tipo varchar(10),
	Cuarto number,
	Banera number,
	Jacuzzi number,
	Sala number,
	Cocina number,
	Cable number,
	Precio float,
	Capacidad number,
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
	NombreVivienda varchar(30) --FK
	);
	
--Inmueble
CREATE TABLE Inmueble (
	Amoblado number,
	Servicios number,
	Cable number,
	Administracion number,  
	Precio float,
	Direccion varchar(50),
	Dueno VARCHAR(30)
	);
	
--VecinoRoom
CREATE TABLE VecinoRoom (
	Habitaciones number,
	Banos number,
	Direccion varchar(50),
	Menaje varchar(140),
	Precio float,
	Dueno VARCHAR(30) --FK Vecino.ID
	);
	
--ReservaHostal
CREATE TABLE ReservaHostal (
	Id float,
    FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario VARCHAR(30), --USUARIO.CEDULA
	Cuarto float, --FK HostalRoom.Id
    Nombre varchar(30),
    PrecioTotal float
	);
	
--ReservaHotel
CREATE TABLE ReservaHotel (
	Id float,
    FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario VARCHAR(30),
	Cuarto number, --FK HotelRoom.Id
    Nombre varchar(30),
    PrecioTotal float
	);
	
--ContratoResidencia
CREATE TABLE ContratoResidencia (
	Id float,
    FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario VARCHAR(30),
	Cuarto number, --FK Residencia.Id
    Nombre VARCHAR(30),
    PrecioTotal float
	);
	
--ContratoInmueble
CREATE TABLE ContratoInmueble (
	Id float,
    FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario VARCHAR(30),
	Dueno varchar(30),
    Direccion varchar(30),
    PrecioTotal float
	);
	
--Usuario
CREATE TABLE Usuario (
	Cedula varchar(30),
	Nombre varchar(30),
	FechaNacimiento varchar(30)
	);
	
--Relacionado
CREATE TABLE Relacionado (
	CEDULA VARCHAR(30) --FK USUARIO.CEDULA
	);
	
--Estudiante
CREATE TABLE Estudiante (
	CEDULA VARCHAR(30), --FK Relacionado.CEDULA
	Carnet varchar(30) --CK Carnet
	);

--Profesor
CREATE TABLE Profesor (
	CEDULA VARCHAR(30) --FK Relacionado.CEDULA
	);
	
--Empleado
CREATE TABLE Empleado (
	CEDULA VARCHAR(30) --Relacionado.CEDULA
	);

--Padre
CREATE TABLE Padre (
	CEDULA VARCHAR(30) --FK USUARIO.CEDULA
	);

--Egresado
CREATE TABLE Egresado (
	CEDULA VARCHAR(30) --FK USUARIO.CEDULA
	);
	
--Vecino
CREATE TABLE Vecino (
	Cedula varchar(30),
	Nombre varchar(30),
	FechaNacimiento varchar(30)
	);
    
--PARESEHIJOS
CREATE TABLE PADRESEHIJOS (
    CEDULA_PADRE VARCHAR(30), --FK PADRE.CEDULA
    CEDULA_HIJO VARCHAR(30) --FK ESTUDIANTE.CEDULA
    );
    
--CONTRATOVECINO
CREATE TABLE CONTRATOVECINO (
    id float,
    PrecioTotal float,
    FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario VARCHAR(30),
	Dueno varchar(30),
    Direccion varchar(30)
    );
	
