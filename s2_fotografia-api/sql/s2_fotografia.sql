delete from CalificacionEntity;
delete from ClienteEntity;
delete from OrganizadorEntity;
delete from RondaEntity;
delete from JURADOENTITY;
delete from fotografoentity;
delete from FormaDePagoEntity;
delete from FacturaEntity;


insert into CalificacionEntity (id, puntaje, comentario) values (100, 4, 'Muy bonita');
insert into ClienteEntity (id, login, nombre, correo, contrasena) values (100, 'Andrea', 'Andrea Lopez','andreLopez@hotmail.com','andrea' );
insert into ClienteEntity (id, login, nombre, correo, contrasena) values (1, 'JuanD', 'Juan David Rosas','jdrosas@hotmail.com','juandr' );

insert into JuradoEntity(id, nombre, apellido, correo, cedula, pais, ciudad) values (400, 'Pedro', 'Ramirez', 'pedroramirez@yahoo.com', 12345678, 'Colombia', 'Bogota');

insert into FacturaEntity(id, numero, precio, fechaCompra) values (1, 1, 32.0, '8/22/2018');

insert into FormaDePagoEntity(id, numeroTarjeta, fechaVencimiento, numeroVerificacion, tipoDeTarjeta, tipoTarjetaDeCredito) values (1,123453268432156, '8/22/2020',456,'Tarjeta Credito', 'VISA');


insert into FOTOGRAFOENTITY (id, apellido, correo, edad, fechanacimiento, nombre, pais, telefono, login, password) values (2,'Acosta','s.acosta@correo.com',19,'23/12/12','Sara','Colombia',319,'s.acostav','s_');
insert into FOTOGRAFOENTITY (id, apellido, correo, edad, fechanacimiento, nombre, pais, telefono, login, password) values (5,'Villegas','s@correo.com',30,'23/12/12','Sara','Colombia',319,'s.acostav','s_');

insert into OrganizadorEntity(id, nombre, apellido, edad, correo, telefono, pais) values (200, 'Jose', 'Martinez', 18,'j.martinez@gmail.com', 31578965, 'Colombia');
insert into RondaEntity(id, numeroRonda) values (200, 1);
