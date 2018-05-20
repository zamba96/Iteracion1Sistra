                 
Select Cliente.Cedula,Nombre,ROL,Tipo
From Cliente 
inner join Persona 
on Cliente.CEDULA = Persona.CEDULA
inner join Reserva
on Cliente.CEDULA = Reserva.CEDULA
inner join HOTELROOM
On Reserva.Cuarto = HotelRoom.ID
where Tipo = 'Suite';

Select Cliente.Cedula,Nombre,ROL,Precio
From Cliente 
inner join Persona 
on Cliente.CEDULA = Persona.CEDULA
inner join Reserva
on Cliente.cedula = Reserva.CEDULA
where PRECIO > 1500;


select r.cedula
from Reserva r
where r.fechainicio >= trunc(sysdate, 'YYYY') - interval '10' year and
      r.fechainicio < trunc(sysdate, 'YYYY')
group by r.cedula
having count(distinct trunc(r.fechainicio, 'MM')) = 12;
