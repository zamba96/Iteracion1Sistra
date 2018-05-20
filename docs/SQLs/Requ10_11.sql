Select Cliente.cedula, Persona.NOMBRE
From Cliente inner join Persona 
on Cliente.CEDULA = Persona.CEDULA 
Where Cliente.CEDULA in (Select Cedula 
                 From Reserva inner join Alojamiento
                 On Reserva.CUARTO = Alojamiento.ID
                 Where (FECHAINICIO Between '01/11/00' AND '31/12/00' 
                 Or FECHAFIN Between '01/12/00' AND '31/12/00')
                 AND Alojamiento.DTYPE = 'HOTELROOM');

Select Cliente.cedula, Persona.NOMBRE
From Cliente inner join Persona 
on Cliente.CEDULA = Persona.CEDULA 
Where Cliente.CEDULA not in (Select Cedula 
                 From Reserva inner join Alojamiento
                 On Reserva.CUARTO = Alojamiento.ID
                 Where (FECHAINICIO Between '01/01/95' AND '31/12/10' 
                 Or FECHAFIN Between '01/01/95' AND '31/12/10')
                 AND Alojamiento.DTYPE = 'HOTELROOM');
                 