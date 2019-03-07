delete from CalificacionEntity;
delete from ClienteEntity;
delete from JuradoEntity;
delete from OrganizadorEntity;
delete from RondaEntity;

insert into CalificacionEntity (id, puntaje, comentario) values (100, 4, 'Muy bonita');
insert into ClienteEntity (id, usuario, nombre, correo) values (100, 'Andrea', 'Andrea Lopez','andreLopez@hotmail.com');
insert into JuradoEntity(id, nombre, apellido, correo, cedula, pais, ciudad) values (400, 'Pedro', 'Ramirez', 'pedroramirez@yahoo.com', 12345678, 'Colombia', 'Bogota');
insert into OrganizadorEntity(id, nombre, apellido, edad, correo, telefono, pais) values (200, 'Jose', 'Martinez', 'j.martinez@gmail.com', 31578965, 'Colombia');
insert into RondaEntity(id, numeroRonda) values (200, 1);