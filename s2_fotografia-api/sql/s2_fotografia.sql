
delete from FacturaEntity;
delete from FormaDePagoEntity;
delete from fotografoentity;
delete from JURADOENTITY;
delete from ClienteEntity;
delete from CalificacionEntity;
delete from CONCURSOENTITY_FOTOGRAFOENTITY;
DELETE FROM CONCURSOENTITY_PHOTOENTITY;
delete from ConcursoEntity;
delete from RondaEntity;
delete from OrganizadorEntity;

insert into CalificacionEntity (id, puntaje, comentario, nombre) values (100, 4, 'Muy bonita', 'Foto 1');
insert into CalificacionEntity (id, puntaje, comentario, nombre) values (200, 2.5, 'Esta desenfocada', 'Foto 2');
insert into CalificacionEntity (id, puntaje, comentario, nombre) values (300, 1.6, 'No me gusta', 'Foto 3');

insert into OrganizadorEntity(id, nombre, apellido, edad, correo, telefono, pais) values (200, 'Jose', 'Martinez', 20, 'j.martinez@gmail.com', 31578965, 'Colombia');


insert into RondaEntity(id, NUMRONDA) values (300, 1);

insert into ClienteEntity (id, login, nombre, correo, contrasena) values (100, 'Andrea', 'Andrea Lopez','andreLopez@hotmail.com','andrea' );
insert into ClienteEntity (id, login, nombre, correo, contrasena) values (1, 'JuanD', 'Juan David Rosas','jdrosas@hotmail.com','juandr' );

insert into JuradoEntity(id, nombre, apellido, correo, cedula, pais, ciudad) values (400, 'Pedro', 'Ramirez', 'pedroramirez@yahoo.com', 12345678, 'Colombia', 'Bogota');
insert into JuradoEntity(id, nombre, apellido, correo, cedula, pais, ciudad) values (500, 'Luisa', 'Sanchez', 'luisasanchez@hotmail.com', 67348812, 'Argentina', 'Buenos Aires');
insert into JuradoEntity(id, nombre, apellido, correo, cedula, pais, ciudad) values (600, 'Iván', 'Camacho', 'ivan.camacho@yahoo.com', 0987656, 'Colombia', 'Medellín');

insert into FOTOGRAFOENTITY (id, apellido, correo, edad, fechanacimiento, nombre, pais, telefono, login, password) values (5,'Villegas','s@correo.com',30,'23/12/12','Sara','Colombia',319,'s.acostav','s_');
insert into FOTOGRAFOENTITY (id, apellido, correo, edad, fechanacimiento, nombre, pais, telefono, login, password) values (2,'Acosta','s.acosta@correo.com',19,'23/12/12','Sara','Colombia',319,'s.acostav','s_');

insert into CONCURSOENTITY(ID, CANTIDADPREMIO, EDADDELAFOTO, FECHA, MAXFOTOS, TEMA, CLIENTE_ID, ORGANIZADOR_ID,RONDA_ID) 
values (1, 10000, 36,  '8/20/2015 ', 3, 'Blanco y negro', null, 200, 300 );

UPDATE JURADOENTITY SET CONCURSOJURADO_ID = 1 WHERE ID = 400;    

insert into FacturaEntity(id, numero, precio, fechaCompra) values (1, 1, 32.0, '8/22/2018');
insert into FormaDePagoEntity(id, numeroTarjeta, fechaVencimiento, numeroVerificacion, tipoDeTarjeta, tipoTarjetaDeCredito) values (1,123453268432156, '8/22/2020',456,'Tarjeta Credito', 'VISA');


insert into RondaEntity(id, NUMRONDA) values (200, 1);


insert into PHOTOENTITY (nombre, date, description, price, winner, published, rutaFoto) values ('Lazare', '1970-01-01 12:25:06', 'Long-tailed skua', 79, 8, 8, 'https://www.apertura.com/__export/1520955322778/sites/revistaap/img/2018/03/13/lobo-wall-street.jpg_1913337537.jpg');
