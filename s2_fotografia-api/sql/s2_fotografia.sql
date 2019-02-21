delete from CalificacionEntity;
delete from ClienteEntity;
delete from JURADOENTITY;

insert into CalificacionEntity (id, puntaje, comentario) values (100, 4, 'Muy bonita');
insert into ClienteEntity (id, usuario, nombre, correo) values (100, 'Andrea', 'Andrea Lopez','andreLopez@hotmail.com');
insert into JuradoEntity(id, nombre, apellido, correo, cedula, pais, ciudad) values (400, 'Pedro', 'Ramirez', 'pedroramirez@yahoo.com', 12345678, 'Colombia', 'Bogota');