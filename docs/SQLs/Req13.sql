Select Cliente.Cedula, Persona.Nombre, Persona.ROL
From Cliente
Inner join Persona
On Cliente.CEDULA = Persona.CEDULA
where Cliente.cedula in (Select Cedula
                 From Reserva
                 inner join HOTELROOM
                 On Reserva.Cuarto = HotelRoom.ID
                 Group by Tipo,Reserva.CEDULA
                 Having count(*)=1
                        and tipo = 'Suite');
         

Select Cliente.Cedula, Persona.Nombre, Persona.ROL
From Cliente
Inner join Persona
On Cliente.CEDULA = Persona.CEDULA
Where Cliente.Cedula in (Select Cedula
                         From Reserva
                         Group by CEDULA
                         Having AVG(Precio) > 80000);


select r.cedula
from Reserva r
where r.fechainicio >= trunc(sysdate, 'YYYY') - interval '10' year and
      r.fechainicio < trunc(sysdate, 'YYYY')
group by r.cedula
having count(distinct trunc(r.fechainicio, 'MM')) = 12;
