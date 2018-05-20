--crea todas las constraints de las tablas
--PERSONA
ALTER TABLE PERSONA
MODIFY (CEDULA CONSTRAINT PRS_ID_PK PRIMARY KEY,
        NOMBRE CONSTRAINT PRS_NMBR_NN NOT NULL,
        ROL CONSTRAINT PRS_RL_NN NOT NULL
        );

--VECINO
      
ALTER TABLE VECINO
ADD CONSTRAINT VEC_PK PRIMARY KEY(CEDULA);

ALTER TABLE VECINO
ADD CONSTRAINT VEC_FK FOREIGN KEY(CEDULA) REFERENCES PERSONA(CEDULA);

--CLIENTE
ALTER TABLE CLIENTE
ADD CONSTRAINT CLT_PK PRIMARY KEY(CEDULA);

ALTER TABLE CLIENTE
ADD CONSTRAINT CLT_FK FOREIGN KEY(CEDULA) REFERENCES PERSONA(CEDULA);


--Operador
ALTER TABLE OPERADOR
MODIFY (Id CONSTRAINT OP_ID_PK PRIMARY KEY, 
        NOMBRE CONSTRAINT OP_NM_NN NOT NULL,
        DTYPE CONSTRAINT OP_DTP_NN NOT NULL,
        DIRECCION CONSTRAINT OP_DRCN_NN NOT NULL
        );
--Hostal
ALTER TABLE HOSTAL
MODIFY (Apertura CONSTRAINT hostal_apertura_nn NOT NULL,
        Cierre CONSTRAINT hostal_cierre_nn NOT NULL,        
        Desayuno CONSTRAINT htstl_dsyno_ck CHECK (Desayuno BETWEEN 0 AND 1),
        Id constraint hstl_id_pk primary key
        );
ALTER TABLE HOSTAL
ADD CONSTRAINT HSTL_ID_FK FOREIGN KEY (ID) REFERENCES OPERADOR(ID) ON DELETE CASCADE;
        
--Hotel
ALTER TABLE HOTEL
MODIFY (Desayuno CONSTRAINT htl_dsyno_ck CHECK (Desayuno BETWEEN 0 AND 1),
        Restaurante CONSTRAINT htl_rst_ck CHECK (Restaurante BETWEEN 0 AND 1),
        Piscina CONSTRAINT htl_pscna_ck CHECK (Piscina  BETWEEN 0 AND 1),
        Parqueadero CONSTRAINT htl_prqdro_ck CHECK (Parqueadero BETWEEN 0 AND 1),
        ID CONSTRAINT htl_ID_PK PRIMARY KEY
        );
 
 ALTER TABLE HOTEL
ADD CONSTRAINT HOTL_ID_FK FOREIGN KEY (ID) REFERENCES OPERADOR(ID) ON DELETE CASCADE;       

--ViviendaU
ALTER TABLE VIVIENDAU
MODIFY (ID CONSTRAINT vndau_ID_PK PRIMARY KEY);

ALTER TABLE VIVIENDAU
ADD CONSTRAINT VNDAU_ID_FK FOREIGN KEY (ID) REFERENCES OPERADOR(ID) ON DELETE CASCADE;

--ALOJAMIENTO
ALTER TABLE ALOJAMIENTO
MODIFY (ID CONSTRAINT ALOJ_ID_PK PRIMARY KEY,
        PRECIO CONSTRAINT ALOJ_PRC_CK CHECK (PRECIO > 0),
        DTYPE CONSTRAINT ALOJ_DTP_NN NOT NULL,
        CAPACIDAD CONSTRAINT ALOJ_CPC_CK CHECK(CAPACIDAD > 0)
        );

--HOSTALROOM
ALTER TABLE HOSTALROOM
MODIFY (ID CONSTRAINT hstlrm_ID_PK PRIMARY KEY,
        CUARTO CONSTRAINT HSTLRM_CUARTO_NN NOT NULL
        );
        
ALTER TABLE HOSTALROOM
ADD CONSTRAINT HSTRM_ID_FK FOREIGN KEY (ID) REFERENCES ALOJAMIENTO(ID) ON DELETE CASCADE;

ALTER TABLE HOSTALROOM
ADD CONSTRAINT HSLRM_HSTLID_FK FOREIGN KEY (IDHOSTAL) REFERENCES HOSTAL(ID) ON DELETE CASCADE;

--HotelROom
ALTER TABLE HOTELROOM
MODIFY (Banera CONSTRAINT htlrm_bnr_ck CHECK (Banera BETWEEN 0 AND 1),
        Jacuzzi CONSTRAINT htlrm_jczz_ck CHECK (Jacuzzi BETWEEN 0 AND 1),
        Sala CONSTRAINT htlrm_sl_ck CHECK (Sala BETWEEN 0 AND 1),
        Cocina CONSTRAINT htlrm_ccn_ck CHECK (Cocina BETWEEN 0 AND 1),
        Cable CONSTRAINT htlrm_cbl_ck CHECK (Cable BETWEEN 0 AND 1),
        Tipo CONSTRAINT htlm_tp_NN NOT NULL,
        CUARTO CONSTRAINT htlrm_crt_nn NOT NULL,
        ID CONSTRAINT htlrm_id_pk PRIMARY KEY
        );
        
ALTER TABLE HOTELROOM
ADD CONSTRAINT HTLRM_ID_FK FOREIGN KEY (ID) REFERENCES ALOJAMIENTO(ID) ON DELETE CASCADE;

ALTER TABLE HOTELROOM
ADD CONSTRAINT HTLRM_HTLID_FK FOREIGN KEY (IDHOTEL) REFERENCES HOTEL(ID) ON DELETE CASCADE;

--ViviendaURoom
ALTER TABLE VIVIENDAROOM
MODIFY (COMPARTIDA CONSTRAINT vndurm_cmprt_ck CHECK (COMPARTIDA >= 0),
        RESTAURANTE CONSTRAINT vndurm_rstrt_ck CHECK (RESTAURANTE >= 0),
        SALADEESTUDIO CONSTRAINT vndurm_slsest_ck CHECK (SALADEESTUDIO >= 0),
        SALAESPARCIMIENTO CONSTRAINT vndurm_sesparc_ck CHECK (SALAESPARCIMIENTO >= 0),
        GYM CONSTRAINT vndurm_gym_ck CHECK (GYM >= 0),
        PRECIO CONSTRAINT vndurm_prc_ck CHECK (PRECIO > 0),
        ID CONSTRAINT vndurm_id_pk PRIMARY KEY,
        IDVIVIENDA CONSTRAINT vndurm_idviv_NN NOT NULL,
        CUARTO CONSTRAINT vndurm_crt_nn NOT NULL
        );
 

ALTER TABLE VIVIENDAROOM
ADD CONSTRAINT VNDAURM_ID_FK FOREIGN KEY (ID) REFERENCES ALOJAMIENTO(ID) ON DELETE CASCADE;

ALTER TABLE VIVIENDAROOM
ADD CONSTRAINT VNDAURM_IDVIVIENDA_FK FOREIGN KEY (IDVIVIENDA) REFERENCES VIVIENDAU(ID) ON DELETE CASCADE;

--INMUEBLE
ALTER TABLE INMUEBLE
MODIFY (AMOBLADO CONSTRAINT inmbl_ambld_ck CHECK (AMOBLADO BETWEEN 0 AND 1),
        SERVICIOS CONSTRAINT inmbl_srvs_ck CHECK (SERVICIOS BETWEEN 0 AND 1),
        CABLE CONSTRAINT inmbl_cbl_ck CHECK (CABLE BETWEEN 0 AND 1),
        ADMINISTRACION CONSTRAINT inmbl_admon_ck CHECK (ADMINISTRACION BETWEEN 0 AND 1),
        DIRECCION CONSTRAINT INMBL_DRC_ND UNIQUE NOT NULL,
        ID CONSTRAINT INMBL_ID_PK PRIMARY KEY,
        DUENO CONSTRAINT INBL_DNO_NN NOT NULL
        );

ALTER TABLE INMUEBLE
ADD CONSTRAINT INMBL_ID_FK FOREIGN KEY (ID) REFERENCES ALOJAMIENTO(ID);

ALTER TABLE INMUEBLE
ADD CONSTRAINT INBL_DUENO_FK FOREIGN KEY (DUENO) REFERENCES CLIENTE(CEDULA) ON DELETE CASCADE;


--VECINOROOM
ALTER TABLE VECINOROOM
MODIFY (HABITACIONES CONSTRAINT VCNRM_HAB_NN NOT NULL,
        BANOS CONSTRAINT VCNRM_BNS_NN_CK NOT NULL CHECK (BANOS > 0),
        DIRECCION CONSTRAINT VCNRM_DRC_ND UNIQUE,
        MENAJE CONSTRAINT VCNRM_MNJ_NN NOT NULL,
        ID CONSTRAINT VCNRM_ID_PK PRIMARY KEY,
        DUENO CONSTRAINT VCNRM_DNO_NN NOT NULL
        );
        

ALTER TABLE VECINOROOM
ADD CONSTRAINT VCNRM_DUENO_FK FOREIGN KEY (DUENO) REFERENCES VECINO(CEDULA) ON DELETE CASCADE;

--Reserva
Alter TABLE RESERVA
ADD CONSTRAINT RSRVA_FK FOREIGN KEY (Cedula) REFERENCES CLIENTE(CEDULA);

ALTER TABLE RESERVA
ADD CONSTRAINT CRT_FK FOREIGN KEY (Cuarto) REFERENCES ALOJAMIENTO(ID);

ALTER TABLE RESERVA
ADD CONSTRAINT RSVA_PK PRIMARY KEY (ID);