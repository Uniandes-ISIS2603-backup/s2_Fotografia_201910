delete from CalificacionEntity;
delete from ClienteEntity;
delete from JURADOENTITY;
delete from fotografoentity;

insert into CalificacionEntity (id, puntaje, comentario) values (100, 4, 'Muy bonita');
insert into ClienteEntity (id, usuario, nombre, correo) values (100, 'Andrea', 'Andrea Lopez','andreLopez@hotmail.com');
insert into JuradoEntity(id, nombre, apellido, correo, cedula, pais, ciudad) values (400, 'Pedro', 'Ramirez', 'pedroramirez@yahoo.com', 12345678, 'Colombia', 'Bogota');

insert into FOTOGRAFOENTITY (id, apellido, correo, edad, fechanacimiento, nombre, pais, telefono, login, password) values (2,'Acosta','s.acosta@correo.com',19,'23/12/12','Sara','Colombia',319,'s.acostav','s_');
insert into FOTOGRAFOENTITY (id, apellido, correo, edad, fechanacimiento, nombre, pais, telefono, login, password) values (5,'Villegas','s@correo.com',30,'23/12/12','Sara','Colombia',319,'s.acostav','s_');

