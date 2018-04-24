/* SQL Que crea todas las tablas del proyecto*/

--Hostal
CREATE TABLE Hostal (
    Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
	Apertura number, 
	Cierre number, 
	Direccion varchar(50), 
	Nombre varchar(30),
    Desayuno number
	);

/*Hotel*/
CREATE TABLE Hotel (
    Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
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
    Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
	Direccion varchar(50),
	Nombre varchar(30)
	);

--HostalRoom
CREATE TABLE HostalRoom (
    Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
	Cuarto number,
	Capacidad number,
	Precio float,
	IDHOSTAL INTEGER
	);
	
--HotelRoom
CREATE TABLE HotelRoom (
    Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    Tipo varchar(10),
	Cuarto number,
	Banera number,
	Jacuzzi number,
	Sala number,
	Cocina number,
	Cable number,
	Precio float,
	Capacidad number,
	IDHOTEL INTEGER
	);

--ViviendaURoom
CREATE TABLE ViviendaURoom (
    Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
	Cuarto number,
	Compartida float,
	Restaurante float,
	SalaDeEstudio float,
	SalaEsparcimiento float,
	Gym float,
	Precio float,
	IDVIVIENDA INTEGER
	);
	
--Inmueble
CREATE TABLE Inmueble (
    Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
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
    Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
	Habitaciones number,
	Banos number,
	Direccion varchar(50),
	Menaje varchar(140),
	Precio float,
	Dueno VARCHAR(30) --FK Vecino.ID
	);
	
--ReservaHostal
CREATE TABLE ReservaHostal (
	Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario VARCHAR(30), --USUARIO.CEDULA
    IDHOSTALROOM INTEGER,
    PrecioTotal float
	);
	
--ReservaHotel
CREATE TABLE ReservaHotel (
	Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario VARCHAR(30),
    IDHOTELROOM INTEGER,
    PrecioTotal float
	);
	
--ContratoResidencia
CREATE TABLE ContratoResidencia (
	Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario VARCHAR(30),
    IDVIVIENDAUROOM INTEGER,
    PrecioTotal float
	);
	
--ContratoInmueble
CREATE TABLE ContratoInmueble (
	Id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario VARCHAR(30),
	IDINMUEBLE INTEGER,
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
    id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    PrecioTotal float,
    FechaInicio varchar(30),
	FechaFin varchar(30),
	Usuario VARCHAR(30),
	IDVECINOROOM INTEGER
    );
    
--OFERTA
CREATE TABLE RESERVASMASIVAS(
    ID INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    CANTIDAD INTEGER,
    DESCRIPCION VARCHAR (140),
    TIPO VARCHAR(20)
    );

CREATE TABLE RELMASIVAS(
    IDMASIVA INTEGER,
    IDRESERVAHOTEL INTEGER,
    IDRESERVAHOSTAL INTEGER,
    IDCONTRATOINMUEBLE INTEGER,
    IDCONTRATORESIDENCIA INTEGER,
    IDCONTRATOVECINO INTEGER
    );
	
