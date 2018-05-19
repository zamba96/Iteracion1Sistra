load data 
infile 'C:\Users\juandavid\OneDrive - Universidad de Los Andes\SISTRANS\Workspaces\tutorial\MasiveDataMockerApplication\data\personas.csv' "str '\r\n'"
append
into table PERSONA
fields terminated by ','
OPTIONALLY ENCLOSED BY '"' AND '"'
trailing nullcols
           ( CEDULA CHAR(30),
             NOMBRE CHAR(50),
             ROL CHAR(10),
             FECHANACIMIENTO TIMESTAMP "DD/MM/YYYY"
           )
