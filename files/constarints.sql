--crea todas las constraints de las tablas

--Hostal
ALTER TABLE HOSTAL
MODIFY (Apertura CONSTRAINT hostal_apertura_nn NOT NULL,
        Cierre CONSTRAINT hostal_cierre_nn NOT NULL,
        Direccion CONSTRAINT hostal_direccion_nd_nn UNIQUE NOT NULL,
        Nombre CONSTRAINT hostal_nombre_pk PRIMARY KEY,
        Desayuno CONSTRAINT htstl_dsyno_ck CHECK (Desayuno BETWEEN 0 AND 1)
        );
        
--Hotel
ALTER TABLE HOTEL
MODIFY (Desayuno CONSTRAINT htl_dsyno_ck CHECK (Desayuno BETWEEN 0 AND 1),
        Direccion CONSTRAINT htl_drcn_nn_nd UNIQUE NOT NULL,
        Restaurante CONSTRAINT htl_rst_ck CHECK (Restaurante BETWEEN 0 AND 1),
        Piscina CONSTRAINT htl_pscna_ck CHECK (Piscina  BETWEEN 0 AND 1),
        Parqueadero CONSTRAINT htl_prqdro_ck CHECK (Parqueadero BETWEEN 0 AND 1),
        Nombre CONSTRAINT htl_nmbr_pk PRIMARY KEY
        );
        
--ViviendaU
ALTER TABLE VIVIENDAU
MODIFY (Nombre CONSTRAINT vndau_nmbr_pk PRIMARY KEY,
        Direccion CONSTRAINT vndau_drccn_nn_nd UNIQUE NOT NULL
        );

--HOSTALROOM
ALTER TABLE HOSTALROOM
MODIFY (Capacidad CONSTRAINT hstlrm_cpcdd_NN NOT NULL,
        Precio CONSTRAINT hsltrm_prc_NN NOT NULL
        );

ALTER TABLE HOSTALROOM
ADD CONSTRAINT hstlrm_pk PRIMARY KEY(Cuarto,NOMBREHostal);

--HotelROom
ALTER TABLE HOTELROOM
MODIFY (Banera CONSTRAINT htlrm_bnr_ck CHECK (Banera BETWEEN 0 AND 1),
        Jacuzzi CONSTRAINT htlrm_jczz_ck CHECK (Jacuzzi BETWEEN 0 AND 1),
        Sala CONSTRAINT htlrm_sl_ck CHECK (Sala BETWEEN 0 AND 1),
        Cocina CONSTRAINT htlrm_ccn_ck CHECK (Cocina BETWEEN 0 AND 1),
        Cable CONSTRAINT htlrm_cbl_ck CHECK (Cable BETWEEN 0 AND 1),
        Precio CONSTRAINT htlrm_prc_NN NOT NULL,
        Capacidad CONSTRAINT htlrm_cpcdc_NN_CK NOT NULL CHECK(Capacidad > 0),
        Tipo CONSTRAINT htlm_tp_NN NOT NULL
        );

ALTER TABLE HOTELROOM
ADD CONSTRAINT htlrm_pk PRIMARY KEY (NOMBREHOTEL, CUARTO);

--ViviendaURoom
ALTER TABLE VIVIENDAUROOM
MODIFY (COMPARTIDA CONSTRAINT vndurm_cmprt_ck CHECK (COMPARTIDA >= 0),
        RESTAURANTE CONSTRAINT vndurm_rstrt_ck CHECK (RESTAURANTE >= 0),
        SALADEESTUDIO CONSTRAINT vndurm_slsest_ck CHECK (SALADEESTUDIO >= 0),
        SALAESPARCIMIENTO CONSTRAINT vndurm_sesparc_ck CHECK (SALAESPARCIMIENTO >= 0),
        GYM CONSTRAINT vndurm_gym_ck CHECK (GYM >= 0),
        PRECIO CONSTRAINT vndurm_prc_ck CHECK (PRECIO > 0)
        );

ALTER TABLE VIVIENDAUROOM
ADD CONSTRAINT vndurm_pk PRIMARY KEY(NOMBREVIVIENDA,CUARTO); 

ALTER TABLE INMUEBLE
MODIFY (AMOBLADO CONSTRAINT inmbl_ambld_ck CHECK (AMOBLADO BETWEEN 0 AND 1),
        SERVICIOS CONSTRAINT inmbl_srvs_ck CHECK (SERVICIOS BETWEEN 0 AND 1),
        CABLE CONSTRAINT inmbl_cbl_ck CHECK (CABLE BETWEEN 0 AND 1),
        ADMINISTRACION CONSTRAINT inmbl_admon_ck CHECK (ADMINISTRACION BETWEEN 0 AND 1),
        PRECIO CONSTRAINT INMBL_PRC_CK CHECK (PRECIO > 0),
        DIRECCION CONSTRAINT INMBL_DRC_ND UNIQUE
        );

ALTER TABLE INMUEBLE
ADD CONSTRAINT INMBL_PK PRIMARY KEY(DUENO, DIRECCION);

--VECINOROOM
ALTER TABLE VECINOROOM
MODIFY (HABITACIONES CONSTRAINT VCNRM_HAB_NN NOT NULL,
        BANOS CONSTRAINT VCNRM_BNS_NN_CK NOT NULL CHECK (BANOS > 0),
        DIRECCION CONSTRAINT VCNRM_DRC_ND UNIQUE,
        MENAJE CONSTRAINT VCNRM_MNJ_NN NOT NULL,
        PRECIO CONSTRAINT VCNRM_PRC_NN_CK NOT NULL CHECK (PRECIO > 0)
        );

ALTER TABLE VECINOROOM
ADD CONSTRAINT VCNRM_PK PRIMARY KEY (DUENO, DIRECCION);

--RESERVAHOSTAL
ALTER TABLE RESERVAHOSTAL
MODIFY (FECHAINICIO CONSTRAINT RESHSTL_FINIC_NN_CK NOT NULL CHECK(FECHAINICIO LIKE '__/__/____'),
        FECHAFIN CONSTRAINT RESHSTL_FFIN_NN_CK NOT NULL CHECK(FECHAFIN LIKE '__/__/____')
        );

ALTER TABLE RESERVAHOSTAL
ADD CONSTRAINT RESHSTL_PK PRIMARY KEY (FECHAINICIO, FECHAFIN, USUARIO);

--RESERVAHOTEL
ALTER TABLE RESERVAHOTEL
MODIFY (FECHAINICIO CONSTRAINT RESHTL_FINIC_NN_CK NOT NULL CHECK(FECHAINICIO LIKE '__/__/____'),
        FECHAFIN CONSTRAINT RESHTL_FFIN_NN_CK NOT NULL CHECK(FECHAFIN LIKE '__/__/____')
        );

ALTER TABLE RESERVAHOTEL
ADD CONSTRAINT RESHTL_PK PRIMARY KEY (FECHAINICIO, FECHAFIN, USUARIO);

--CONTRATORESIDENCIA
ALTER TABLE CONTRATORESIDENCIA
MODIFY (FECHAINICIO CONSTRAINT CONTRES_FINIC_NN_CK NOT NULL CHECK(FECHAINICIO LIKE '__/__/____'),
        FECHAFIN CONSTRAINT CONTRES_FFIN_NN_CK NOT NULL CHECK(FECHAFIN LIKE '__/__/____')
        );
        
ALTER TABLE CONTRATORESIDENCIA
ADD CONSTRAINT CONTRE_PK PRIMARY KEY (FECHAINICIO, FECHAFIN, USUARIO);

--CONTRATOINMUEBLE
ALTER TABLE CONTRATOINMUEBLE
MODIFY (FECHAINICIO CONSTRAINT CONTIMBL_FINIC_NN_CK NOT NULL CHECK(FECHAINICIO LIKE '__/__/____'),
        FECHAFIN CONSTRAINT CONTIMBL_FFIN_NN_CK NOT NULL CHECK(FECHAFIN LIKE '__/__/____')
        );

ALTER TABLE CONTRATOINMUEBLE
ADD CONSTRAINT CONTIMBL_PK PRIMARY KEY (FECHAINICIO, FECHAFIN, USUARIO);

--CONTRATOVECINO
ALTER TABLE CONTRATOVECINO
MODIFY (FECHAINICIO CONSTRAINT CONTVEC_FINIC_NN_CK NOT NULL CHECK(FECHAINICIO LIKE '__/__/____'),
        FECHAFIN CONSTRAINT CONTVEC_FFIN_NN_CK NOT NULL CHECK(FECHAFIN LIKE '__/__/____')
        );
        
ALTER TABLE CONTRATOVECINO
ADD CONSTRAINT CONTVEC_PK PRIMARY KEY (FECHAINICIO, FECHAFIN, USUARIO);

--USUARIO
ALTER TABLE USUARIO
MODIFY (NOMBRE CONSTRAINT USSR_NMB_NN NOT NULL,
        FECHANACIMIENTO CONSTRAINT USSR_FCHNCMTO_NN_CK NOT NULL CHECK(FECHANACIMIENTO LIKE '__/__/____')
        );

ALTER TABLE USUARIO
ADD CONSTRAINT USSR_PK PRIMARY KEY (CEDULA);

--RELACIONADO
ALTER TABLE RELACIONADO
ADD CONSTRAINT REL_PK PRIMARY KEY (CEDULA);

--ESTUDIANTE
ALTER TABLE ESTUDIANTE
MODIFY CARNET CONSTRAINT EST_CRNT_NN_ND NOT NULL UNIQUE;

ALTER TABLE ESTUDIANTE
ADD CONSTRAINT EST_PK PRIMARY KEY (CEDULA);

--PROFESOR
ALTER TABLE PROFESOR
ADD CONSTRAINT PRFSR_PK PRIMARY KEY (CEDULA);

--EMPLEADO
ALTER TABLE EMPLEADO
ADD CONSTRAINT EMPL_PK PRIMARY KEY (CEDULA);

--PADRE
ALTER TABLE PADRE
ADD CONSTRAINT PDR_PK PRIMARY KEY (CEDULA);

--PADRESEHIJOS
ALTER TABLE PADRESEHIJOS
ADD CONSTRAINT PEH_PK PRIMARY KEY(CEDULA_PADRE, CEDULA_HIJO);

--EGRESADO
ALTER TABLE EGRESADO
ADD CONSTRAINT EGR_PK PRIMARY KEY (CEDULA);

--VECINO
ALTER TABLE VECINO
MODIFY (NOMBRE CONSTRAINT VEC_NMBR_NN NOT NULL,
        FECHANACIMIENTO CONSTRAINT VEC_FCHNC_NN_CK NOT NULL CHECK(FECHANACIMIENTO LIKE '__/__/____')
        );
ALTER TABLE VECINO
ADD CONSTRAINT VEC_PK PRIMARY KEY(CEDULA);

--FOREIGN KEY
ALTER TABLE HOSTALROOM
ADD CONSTRAINT hstlrm_hstl_fk FOREIGN KEY (NOMBREHOSTAL) REFERENCES HOSTAL(NOMBRE);

ALTER TABLE HOTELROOM
ADD CONSTRAINT htlrm_nbrhtl_FK FOREIGN KEY(NOMBREHOTEL) REFERENCES HOTEL(NOMBRE);

ALTER TABLE VIVIENDAUROOM
ADD CONSTRAINT vndurm_vndu_fk FOREIGN KEY (NOMBREVIVIENDA) REFERENCES VIVIENDAU(NOMBRE);

ALTER TABLE INMUEBLE
ADD CONSTRAINT INMBL_DUENO_FK FOREIGN KEY (DUENO) REFERENCES USUARIO (CEDULA);

ALTER TABLE VECINOROOM
ADD CONSTRAINT VCNRM_DUENO_FK FOREIGN KEY (DUENO) REFERENCES VECINO (CEDULA);

ALTER TABLE RESERVAHOSTAL
ADD CONSTRAINT RESHSTL_USR_FK FOREIGN KEY(USUARIO) REFERENCES USUARIO (CEDULA);

ALTER TABLE RESERVAHOSTAL 
ADD CONSTRAINT RESHSTL_HTLRM_FK FOREIGN KEY (CUARTO, NOMBRE) REFERENCES HOSTALROOM(CUARTO, NOMBREHOSTAL);

ALTER TABLE RESERVAHOTEL
ADD CONSTRAINT RESHTL_USR_FK FOREIGN KEY(USUARIO) REFERENCES USUARIO (CEDULA);

ALTER TABLE RESERVAHOTEL 
ADD CONSTRAINT RESHTL_HTLRM_FK FOREIGN KEY (CUARTO, NOMBRE) REFERENCES HOTELROOM(CUARTO, NOMBREHOTEL);

ALTER TABLE CONTRATORESIDENCIA
ADD CONSTRAINT CONTRE_RES_FK FOREIGN KEY (CUARTO, NOMBRE) REFERENCES VIVIENDAUROOM (CUARTO, NOMBREVIVIENDA);

ALTER TABLE CONTRATORESIDENCIA 
ADD CONSTRAINT CONTRE_USSR_FK FOREIGN KEY (USUARIO) REFERENCES RELACIONADO(CEDULA);

ALTER TABLE CONTRATOINMUEBLE
ADD CONSTRAINT CONTIMBL_USSR_FK FOREIGN KEY(USUARIO) REFERENCES USUARIO(CEDULA);

ALTER TABLE CONTRATOINMUEBLE
ADD CONSTRAINT CONTIMBL_IMBL_FK FOREIGN KEY(DIRECCION, DUENO) REFERENCES INMUEBLE(DIRECCION, DUENO);

ALTER TABLE CONTRATOVECINO
ADD CONSTRAINT CONTVEC_USSR_FK FOREIGN KEY(USUARIO) REFERENCES USUARIO(CEDULA);

ALTER TABLE CONTRATOVECINO
ADD CONSTRAINT CONTVEC_VECROOM_FK FOREIGN KEY(DIRECCION, DUENO) REFERENCES VECINOROOM(DIRECCION, DUENO);

ALTER TABLE RELACIONADO
ADD CONSTRAINT REL_FK FOREIGN KEY (CEDULA) REFERENCES USUARIO(CEDULA);

ALTER TABLE ESTUDIANTE
ADD CONSTRAINT EST_FK FOREIGN KEY (CEDULA) REFERENCES RELACIONADO(CEDULA);

ALTER TABLE PROFESOR
ADD CONSTRAINT PRFSR_FK FOREIGN KEY (CEDULA) REFERENCES RELACIONADO(CEDULA);

ALTER TABLE EMPLEADO
ADD CONSTRAINT EMPL_FK FOREIGN KEY (CEDULA) REFERENCES RELACIONADO(CEDULA);

ALTER TABLE PADRE
ADD CONSTRAINT PDR_FK FOREIGN KEY (CEDULA) REFERENCES USUARIO(CEDULA);

ALTER TABLE PADRESEHIJOS
ADD CONSTRAINT PEH_PDR_FK FOREIGN KEY(CEDULA_PADRE) REFERENCES PADRE (CEDULA);

ALTER TABLE PADRESEHIJOS
ADD CONSTRAINT PEH_HJO_FK FOREIGN KEY(CEDULA_HIJO) REFERENCES ESTUDIANTE(CEDULA);

ALTER TABLE EGRESADO
ADD CONSTRAINT EGR_FK FOREIGN KEY (CEDULA) REFERENCES USUARIO(CEDULA);