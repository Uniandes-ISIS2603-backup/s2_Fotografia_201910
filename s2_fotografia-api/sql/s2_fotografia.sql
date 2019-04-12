
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
delete from PHOTOENTITY;

insert into CalificacionEntity (id, puntaje, comentario, nombre) values (100, 4, 'Muy bonita', 'Foto 1');
insert into CalificacionEntity (id, puntaje, comentario, nombre) values (200, 2.5, 'Esta desenfocada', 'Foto 2');
insert into CalificacionEntity (id, puntaje, comentario, nombre) values (300, 1.6, 'No me gusta', 'Foto 3');

insert into OrganizadorEntity(id, nombre, apellido, edad, correo, telefono, pais) values (200, 'Jose', 'Martinez', 20, 'j.martinez@gmail.com', 31578965, 'Colombia');
insert into OrganizadorEntity(id, nombre, apellido, edad, correo, telefono, pais) values (201, 'Juan', 'Martinez', 21, 'j.martinez1@gmail.com', 31578966, 'Colombia');
insert into OrganizadorEntity(id, nombre, apellido, edad, correo, telefono, pais) values (202, 'Juana', 'Martinez', 22, 'j.martinez2@gmail.com', 31578967, 'Colombia');
insert into OrganizadorEntity(id, nombre, apellido, edad, correo, telefono, pais) values (203, 'Julian', 'Martinez', 10, 'j.martinez3@gmail.com', 31578968, 'Colombia');
insert into OrganizadorEntity(id, nombre, apellido, edad, correo, telefono, pais) values (204, 'Jaime', 'Martinez', 22, 'j.martinez4@gmail.com', 31578969, 'Colombia');
insert into OrganizadorEntity(id, nombre, apellido, edad, correo, telefono, pais) values (205, 'Jacobe', 'Martinez', 23, 'j.martinez5@gmail.com', 31578960, 'Colombia');
insert into OrganizadorEntity(id, nombre, apellido, edad, correo, telefono, pais) values (206, 'John', 'Martinez', 18, 'j.martinez6@gmail.com', 31578961, 'Colombia');


insert into RondaEntity(id, numeroRonda) values (300, 1);
insert into RondaEntity(id, numeroRonda) values (301, 1);
insert into RondaEntity(id, numeroRonda) values (302, 1);
insert into RondaEntity(id, numeroRonda) values (303, 1);
insert into RondaEntity(id, numeroRonda) values (304, 1);

insert into ClienteEntity (id, login, nombre, correo, contrasena) values (100, 'Andrea', 'Andrea Lopez','andreLopez@hotmail.com','andrea' );
insert into ClienteEntity (id, login, nombre, correo, contrasena) values (1, 'JuanD', 'Juan David Rosas','jdrosas@hotmail.com','juandr' );



insert into JuradoEntity(id, nombre, apellido, correo, cedula, pais, ciudad) values (400, 'Pedro', 'Ramirez', 'pedroramirez@yahoo.com', 12345678, 'Colombia', 'Bogota');
insert into JuradoEntity(id, nombre, apellido, correo, cedula, pais, ciudad) values (500, 'Luisa', 'Sanchez', 'luisasanchez@hotmail.com', 67348812, 'Argentina', 'Buenos Aires');
insert into JuradoEntity(id, nombre, apellido, correo, cedula, pais, ciudad) values (600, 'Iván', 'Camacho', 'ivan.camacho@yahoo.com', 0987656, 'Colombia', 'Medellín');


insert into CONCURSOENTITY(ID, CANTIDADPREMIO, EDADDELAFOTO, FECHA, MAXFOTOS, TEMA, CLIENTE_ID, ORGANIZADOR_ID,RONDA_ID) 
values (100, 10000, 36,  '8/20/2015 ', 3, 'Blanco y negro', null, 200, 300 );
insert into CONCURSOENTITY(ID, CANTIDADPREMIO, EDADDELAFOTO, FECHA, MAXFOTOS, TEMA, CLIENTE_ID, ORGANIZADOR_ID,RONDA_ID) 
values (101, 12000, 3,  '8/20/2015 ', 4, 'Monica', null, 201, 301 );
insert into CONCURSOENTITY(ID, CANTIDADPREMIO, EDADDELAFOTO, FECHA, MAXFOTOS, TEMA, CLIENTE_ID, ORGANIZADOR_ID,RONDA_ID) 
values (102, 13000, 36,  '8/20/2015 ', 5, 'Beahc', null, 202, 302 );
insert into CONCURSOENTITY(ID, CANTIDADPREMIO, EDADDELAFOTO, FECHA, MAXFOTOS, TEMA, CLIENTE_ID, ORGANIZADOR_ID,RONDA_ID) 
values (103, 15000, 6,  '8/20/2015 ', 6, 'Conciertos', null, 203, 303 );


insert into FOTOGRAFOENTITY (id, apellido, correo, edad, fechanacimiento, nombre, pais, telefono, login, password,foto) values (2,'Acosta','s.acosta@correo.com',19,'23/12/12','Sara','Colombia',319,'s.acostav','s_','https://www.dzoom.org.es/wp-content/uploads/2007/02/canon-mejorar-fotografo-consejos-principiantes-novatos-810x540.jpg');
insert into FOTOGRAFOENTITY (id, apellido, correo, edad, fechanacimiento, nombre, pais, telefono, login, password,foto) values (5,'Villegas','s@correo.com',30,'23/12/12','Laura','Colombia',319,'s.acostav','s_','https://cadenaser00.epimg.net/ser/imagenes/2018/12/11/sociedad/1544508140_140111_1544508461_noticia_normal_recorte1.jpg');

UPDATE JURADOENTITY SET CONCURSOJURADO_ID = 100 WHERE ID = 400;
UPDATE JURADOENTITY SET CONCURSOJURADO_ID = 100 WHERE ID = 500;
UPDATE JURADOENTITY SET CONCURSOJURADO_ID = 102 WHERE ID = 600;    


insert into FacturaEntity(id, numero, precio, fechaCompra) values (1, 1, 32.0, '8/22/2018');
insert into FormaDePagoEntity(id, numeroTarjeta, fechaVencimiento, numeroVerificacion, tipoDeTarjeta, tipoTarjetaDeCredito) values (1,123453268432156, '8/22/2020',456,'Tarjeta Credito', 'VISA');

insert into ClienteEntity_FormaDePagoEntity (clienteentity_id, formasDePago_id) values (1,1);
insert into RondaEntity(id, NUMRONDA) values (200, 1);

insert into PHOTOENTITY (nombre, date, description, price, winner, published, rutaFoto) values ('Lazare', '1970-01-01 12:25:06', 'Long-tailed skua', 79, 8, 8, 'https://www.apertura.com/__export/1520955322778/sites/revistaap/img/2018/03/13/lobo-wall-street.jpg_1913337537.jpg');
