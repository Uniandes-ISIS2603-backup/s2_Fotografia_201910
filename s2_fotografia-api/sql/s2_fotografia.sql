
delete from CLIENTEENTITY_FORMADEPAGOENTITY;
delete from FacturaEntity;
delete from FormaDePagoEntity;
delete from JURADOENTITY;
delete from ClienteEntity;
delete from CalificacionEntity;
delete from CONCURSOENTITY_FOTOGRAFOENTITY;
DELETE FROM CONCURSOENTITY_PHOTOENTITY;
delete from ConcursoEntity;
delete from RondaEntity;
delete from OrganizadorEntity;
delete from PHOTOENTITY;
delete from CONCURSOENTITY_PHOTOENTITY;
delete from INTERESFOTOGRAFICOENTITY_FOTOGRAFOENTITY;
delete from interesfotograficoentity ;
delete from fotografoentity;



insert into OrganizadorEntity(id, FOTO, nombre, apellido, edad, correo, telefono, pais) values (200,'https://pickaface.net/gallery/avatar/JuanesRozas5209a6689e87e.png', 'Jose', 'Martinez', 20, 'j.martinez@gmail.com', 31578965, 'Colombia');
insert into OrganizadorEntity(id, foto,nombre, apellido, edad, correo, telefono, pais) values (201,'https://pickaface.net/gallery/avatar/uyyii155a670caadbab.png', 'Juan', 'Martinez', 21, 'j.martinez1@gmail.com', 31578966, 'Colombia');
insert into OrganizadorEntity(id, foto,nombre, apellido, edad, correo, telefono, pais) values (202, 'https://pickaface.net/gallery/avatar/20130128_124302_3642_Lisa.png','Juana', 'Martinez', 22, 'j.martinez2@gmail.com', 31578967, 'Colombia');
insert into OrganizadorEntity(id, foto,nombre, apellido, edad, correo, telefono, pais) values (203,'https://pickaface.net/gallery/avatar/VirtualesM55584681cf2c1.png' ,'Julian', 'Martinez', 10, 'j.martinez3@gmail.com', 31578968, 'Colombia');
insert into OrganizadorEntity(id, foto,nombre, apellido, edad, correo, telefono, pais) values (204, 'https://pickaface.net/gallery/avatar/roverking543bcb6e6df62.png', 'Jaime', 'Martinez', 22, 'j.martinez4@gmail.com', 31578969, 'Colombia');
insert into OrganizadorEntity(id,FOTO,nombre, apellido, edad, correo, telefono, pais) values (205,'https://pickaface.net/gallery/avatar/unr_zerefdregneel_170905_2001_9rjz9l.png' ,'Jacobe', 'Martinez', 23, 'j.martinez5@gmail.com', 31578960, 'Colombia');
insert into OrganizadorEntity(id, foto,nombre, apellido, edad, correo, telefono, pais) values (206,'https://pickaface.net/gallery/avatar/20140629_122757_2166_Artid512.png' ,'John', 'Martinez', 18, 'j.martinez6@gmail.com', 31578961, 'Colombia');

insert into RondaEntity(id, numRonda) values (300, 1);
insert into RondaEntity(id, numRonda) values (301, 1);
insert into RondaEntity(id, numRonda) values (302, 1);
insert into RondaEntity(id, numRonda) values (303, 1);
insert into RondaEntity(id, numRonda) values (304, 1);

insert into ClienteEntity (id, login, nombre, correo, contrasena, imagen) values (100, 'Andrea', 'Andrea Lopez','andreLopez@hotmail.com','andrea12211' ,'http://es.web.img3.acsta.net/pictures/15/05/15/16/30/134942.jpg');
insert into ClienteEntity (id, login, nombre, correo, contrasena,imagen) values (1, 'JuanD', 'Juan David Rosas','jdrosas@hotmail.com','juandr11009','https://los40es00.epimg.net/los40/imagenes/2017/01/20/musica/1484908180_629395_1484909260_noticia_normal.jpg' );
insert into ClienteEntity (id, login, nombre, correo, contrasena,imagen) values (2, 'mariana0911', 'Mariana Lozano','ml@yahoo.com','marianalozano12', 'https://k62.kn3.net/taringa/7/4/D/E/6/8/adriano034/94C.jpg' );
insert into ClienteEntity (id, login, nombre, correo, contrasena,imagen) values (3, 'camilozuluaga', 'Camilo Zuluaga','czulu@gmail.com','cZulu095678', 'https://pixel.nymag.com/imgs/fashion/daily/2016/01/21/21-chris-martin.w700.h700.jpg' );



insert into JuradoEntity(id, nombre, apellido, correo, cedula, pais, ciudad) values (400, 'Pedro', 'Ramirez', 'pedroramirez@yahoo.com', 12345678, 'Colombia', 'Bogota');
insert into JuradoEntity(id, nombre, apellido, correo, cedula, pais, ciudad, imagen) values (500, 'Luisa', 'Sanchez', 'luisasanchez@hotmail.com', 67348812, 'Argentina', 'Buenos Aires', 'https://static.iris.net.co/semana/upload/images/2018/9/21/583976_1.jpg');
insert into JuradoEntity(id, nombre, apellido, correo, cedula, pais, ciudad,IMAGEN) values (600, 'IvÃ¡n', 'Camacho', 'ivan.camacho@yahoo.com', 0987656, 'Colombia', 'MedellÃ­n', 'https://lgfstatic.com/2015/conversions/virtudes-de-una-persona-wide.jpg');


insert into CONCURSOENTITY(ID, CANTIDADPREMIO, EDADDELAFOTO, FECHA, MAXFOTOS, TEMA, CLIENTE_ID, ORGANIZADOR_ID,RONDA_ID) 
values (100, 10000, 36,  '8/20/2015 ', 3, 'Blanco y negro', null, 200, 300 );
insert into CONCURSOENTITY(ID, CANTIDADPREMIO, EDADDELAFOTO, FECHA, MAXFOTOS, TEMA, CLIENTE_ID, ORGANIZADOR_ID,RONDA_ID) 
values (101, 12000, 3,  '8/20/2015 ', 4, 'Monica', null, 201, 301 );
insert into CONCURSOENTITY(ID, CANTIDADPREMIO, EDADDELAFOTO, FECHA, MAXFOTOS, TEMA, CLIENTE_ID, ORGANIZADOR_ID,RONDA_ID) 
values (102, 13000, 36,  '8/20/2015 ', 5, 'Beahc', null, 202, 302 );
insert into CONCURSOENTITY(ID, CANTIDADPREMIO, EDADDELAFOTO, FECHA, MAXFOTOS, TEMA, CLIENTE_ID, ORGANIZADOR_ID,RONDA_ID) 
values (103, 15000, 6,  '8/20/2015 ', 6, 'Conciertos', null, 203, 303 );

insert into FOTOGRAFOENTITY (id, apellido, correo, edad, fechanacimiento, nombre, pais, telefono, login, password,foto,hobbies,descrip) values (101,'Acosta','s.acosta@correo.com',19,'23/12/12','Sara','Colombia',319,'s.acostav','s_','https://www.dzoom.org.es/wp-content/uploads/2007/02/canon-mejorar-fotografo-consejos-principiantes-novatos-810x540.jpg', 'fotografiar paisajes, leer novelas de terror y practicar deportes extremos', 'Soy una fotografa apasionada me encanta captar los mejores momentos de la vida');
insert into FOTOGRAFOENTITY (id, apellido, correo, edad, fechanacimiento, nombre, pais, telefono, login, password,foto,hobbies,descrip) values (102,'Jimmenez','luis@correo.com',30,'21/10/12','Luis','Colombia',32459,'luisjim','s_','https://abcblogs.abc.es/proxima-estacion/wp-content/uploads/sites/86/2013/07/fotografo-lava-516x315.jpg', 'Fotografiar personas, ir al teatro y jugar videojuegos', 'Soy un fotografo  que le gusta plasmar lo mejor de la vida en fotografias');
insert into FOTOGRAFOENTITY (id, apellido, correo, edad, fechanacimiento, nombre, pais, telefono, login, password,foto,hobbies,descrip) values (103,'LÃ³pez','dianalo@correo.com',22,'23/12/12','Diana','Colombia',319,'dianalop','s_','http://www.mecenas20.com/wp-content/uploads/2017/11/maria-hesse8-570x460.jpg', 'Pintar paisajes y leer novelas romanticas.', 'Soy una fotografa  que le gusta plasmar lo mejor de la vida en fotografias');
insert into FOTOGRAFOENTITY (id, apellido, correo, edad, fechanacimiento, nombre, pais, telefono, login, password,foto,hobbies,descrip) values (104,'Rojas','juan@correo.com',21,'23/12/12','Juan','Peru',372455,'rojasj','s_','https://scontent-bog1-1.xx.fbcdn.net/v/t1.0-9/13103328_1610716002580982_1923281472404379076_n.jpg?_nc_cat=110&_nc_ht=scontent-bog1-1.xx&oh=1a44089358bdf062c2578eaf7097100f&oe=5D6A7DE9', 'Viajar por el mundo y plasmar en fotografias lo mejor de cada lugar', 'Soy una persona que le gusta conocer nuevos lugares y disfrutar lo mejor de ellos');

insert into INTERESFOTOGRAFICOENTITY(id,interes,foto)values(100,'Paisajes','https://culturafotografica.es/wp-content/uploads/2016/06/Egol-Skye-Island-Scotland-2013_Web.jpg');
insert into INTERESFOTOGRAFICOENTITY(id,interes,foto) values(300,'Animales','http://www.animalesextincion.es/images/noticias/1710080234_guepardo.jpg');
insert into INTERESFOTOGRAFICOENTITY(id,INTERES,foto) values(200,'Retratos','https://www.dzoom.org.es/wp-content/uploads/2013/04/retrato-foto-734x489.jpg');

insert into INTERESFOTOGRAFICOENTITY(id,interes,foto) values(4,'Edificios','https://cadenaser00.epimg.net/ser/imagenes/2019/03/09/internacional/1552122127_693173_1552122232_noticia_normal_recorte1.jpg');

UPDATE JURADOENTITY SET CONCURSOJURADO_ID = 100 WHERE ID = 400;
UPDATE JURADOENTITY SET CONCURSOJURADO_ID = 100 WHERE ID = 500;
UPDATE JURADOENTITY SET CONCURSOJURADO_ID = 102 WHERE ID = 600;    


insert into FacturaEntity(id, numero, precio, fechaCompra, cliente_id) values (1, 1, 32.0, '8/22/2018', 100);
insert into FacturaEntity(id, numero, precio, fechaCompra, cliente_id) values (2, 2, 38.0, '9/24/2018', 100);
insert into FacturaEntity(id, numero, precio, fechaCompra, cliente_id) values (3, 3, 92.0, '11/05/2017', 1);
insert into FacturaEntity(id, numero, precio, fechaCompra, cliente_id) values (4, 4, 53.0, '8/25/2018', 1);
insert into FacturaEntity(id, numero, precio, fechaCompra, cliente_id) values (5, 5, 12.0, '5/13/2016', 2);
insert into FacturaEntity(id, numero, precio, fechaCompra, cliente_id) values (6, 6, 76.0, '8/28/2018', 3);
insert into FacturaEntity(id, numero, precio, fechaCompra, cliente_id) values (7, 7, 81.0, '2/02/2018', 3);

insert into FormaDePagoEntity(id, numeroTarjeta, fechaVencimiento, numeroVerificacion, tipoDeTarjeta, tipoTarjetaDeCredito,cliente_id,nombre) values (22,123453268432156, '8/22/2020',456,'Tarjeta Credito', 'VISA',1, 'Juan Rosas');
insert into FormaDePagoEntity(id, numeroTarjeta, fechaVencimiento, numeroVerificacion, tipoDeTarjeta, tipoTarjetaDeCredito,cliente_id,nombre) values (1,100326968432156, '10/22/2020',496,'Tarjeta Credito', 'VISA',1,'Juan Rosas' );
insert into FormaDePagoEntity(id, numeroTarjeta, fechaVencimiento, numeroVerificacion, tipoDeTarjeta, tipoTarjetaDeCredito,cliente_id,nombre) values (2,109984268432222, '11/22/2028',123,'Tarjeta Credito', 'MASTERCARD',100, 'Andrea Lopez');
insert into FormaDePagoEntity(id, numeroTarjeta, fechaVencimiento, numeroVerificacion, tipoDeTarjeta, tipoTarjetaDeCredito,cliente_id,nombre) values (3,1001832155432, '2/22/2024',431,'Tarjeta Credito', 'VISA',2, 'Andres Lozano');
insert into FormaDePagoEntity(id, numeroTarjeta, fechaVencimiento, numeroVerificacion, tipoDeTarjeta, tipoTarjetaDeCredito,cliente_id,nombre) values (4,1990985122340, '6/22/2022',994,'Tarjeta Credito', 'MASTERCARD',2, 'Mariana Lozano');
insert into FormaDePagoEntity(id, numeroTarjeta, fechaVencimiento, numeroVerificacion, tipoDeTarjeta, tipoTarjetaDeCredito,cliente_id,nombre) values (5,101987651989211, '1/22/2027',502,'Tarjeta Credito', 'VISA',3, 'Camilo Zuluaga');


insert into ClienteEntity_FormaDePagoEntity (clienteentity_id, formasDePago_id) values (1,22);
insert into ClienteEntity_FormaDePagoEntity (clienteentity_id, formasDePago_id) values (1,1);


insert into RondaEntity(id, NUMRONDA) values (200, 1);


insert into PHOTOENTITY (nombre, date, description, price, winner, published, rutaFoto, FACTURA_ID) values ('El presi', '1970-01-01 12:25:06', 'Este es uribe Â¿, vale mucho porque es bien famoso', 5000, 8, 8, 'https://www.elheraldo.co/sites/default/files/styles/clavelistamovile/public/articulo/2017/05/09/alvaro-uribe-le-responde-al-contralor-sobre-sus-predios_0.jpg?itok=efvMFyQL', 12);
insert into PHOTOENTITY (nombre, date, description, price, winner, published, rutaFoto, FACTURA_ID) values ('Atardecer en cartagena', '1970-01-01 12:25:06', 'Un atardecer bien tarde', 9000, 8, 8, 'https://www.turismodeobservacion.com/media/fotografias/atardecer-en-cortegana-3123-xl.jpg', 12);
insert into PHOTOENTITY (nombre, date, description, price, winner, published, rutaFoto) values ('Baile ensordecido', '1970-01-01 12:25:06', 'La ciudad en blanco y negro', 7400, 8, 8, 'http://estag.fimagenes.com/img/4/1/9/d/M/19dM_900.jpg');
insert into PHOTOENTITY (nombre, date, description, price, winner, published, rutaFoto, FACTURA_ID) values ('India verde', '1970-01-01 12:25:06', 'Ojos verdes en India', 790, 8, 8, 'https://mott.pe/noticias/wp-content/uploads/2017/10/Conoce-cu%C3%A1les-son-las-mejores-im%C3%A4genes-art%C3%ADsticas-capturadas-a-trav%C3%A9s-de-fotograf%C3%ADas-famosas.jpg', 1);
insert into PHOTOENTITY (nombre, date, description, price, winner, published, rutaFoto) values ('NiÃ±a y su gato', '1970-01-01 12:25:06', 'Gato en la silla', 1500, 8, 8, 'https://bigotesdegato.com/wp-content/uploads/2013/10/La-bella-historia-de-amistad-entre-una-ni%C3%B1a-y-su-gato-05.jpg');
insert into PHOTOENTITY (nombre, date, description, price, winner, published, rutaFoto) values ('Colores para colorear', '1970-01-01 12:25:06', 'Coloreando ando', 900, 8, 8, 'https://comunidad.iebschool.com/iebs/files/2012/02/Cubilete-l%C3%A1pices.jpg');



insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID) values (801, 'El presi', '1970-01-01 12:25:06', 'Este es uribe Â¿, vale mucho porque es bien famoso', 79, 8, 8, 'https://www.elheraldo.co/sites/default/files/styles/clavelistamovile/public/articulo/2017/05/09/alvaro-uribe-le-responde-al-contralor-sobre-sus-predios_0.jpg?itok=efvMFyQL', 5);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (802, 'Atardecer en cartagena', '1970-01-01 12:25:06', 'Un atardecer bien tarde', 79, 8, 8, 'https://www.turismodeobservacion.com/media/fotografias/atardecer-en-cortegana-3123-xl.jpg', 2,1);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID) values (803, 'Baile ensordecido', '1970-01-01 12:25:06', 'La ciudad en blanco y negro', 79, 8, 8, 'http://estag.fimagenes.com/img/4/1/9/d/M/19dM_900.jpg', 5);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (804, 'India verde', '1970-01-01 12:25:06', 'Ojos verdes en India', 79, 8, 8, 'https://mott.pe/noticias/wp-content/uploads/2017/10/Conoce-cu%C3%A1les-son-las-mejores-im%C3%A4genes-art%C3%ADsticas-capturadas-a-trav%C3%A9s-de-fotograf%C3%ADas-famosas.jpg', 3,2);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID) values (805, 'NiÃ±a y su gato', '1970-01-01 12:25:06', 'Gato en la silla', 79, 8, 8, 'https://bigotesdegato.com/wp-content/uploads/2013/10/La-bella-historia-de-amistad-entre-una-ni%C3%B1a-y-su-gato-05.jpg', 2);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, FACTURA_ID) values (806, 'Colores para colorear', '1970-01-01 12:25:06', 'Coloreando ando', 79, 8, 8, 'https://comunidad.iebschool.com/iebs/files/2012/02/Cubilete-l%C3%A1pices.jpg', 1, 2);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID, FACTURA_ID) values (807, 'CaÃ±o cristales', '1970-01-01 12:25:06', 'El rio de los siete colores',85,8,8,'https://www.colombiatudestino.com/viajes/wp-content/uploads/2018/09/cristales3.jpg',3, 1, 2);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (808,'Machu Picchu','1970-01-01 12:25:06', 'La ciudad perdida de los Andes peruanos ',90,8,8,'https://www.eluniversal.com.mx/sites/default/files/styles/f03-651x400/public/2018/06/20/machu-picchu-peru-cuanto-cuesta.jpeg?itok=58DUQMkV&c=2170bd234c1760f19509e7d512cfb0b9',5,1);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (809, 'Amanecer en la nieve','1970-01-01 12:25:06', 'Un lugar maravilloso para sumergirse en la tranquilidad', 80,8,8,'http://cursodefoto-madrid.com/wp-content/uploads/2016/04/fotografia-en-la-nieve.jpg',2,1);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (810, 'Desierto de la tatacoa','1970-01-01 12:25:06', 'Silencioso bosque seco tropical', 80,8,8,'https://1.bp.blogspot.com/-tysBURztce0/WmH8kxdUzkI/AAAAAAAALoc/P6RGtVkJz6stCYhErrBBsmfhIK8AHkYwQCLcBGAs/s1600/desierto-tatacoa.jpg',3,1);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (811, 'Atardecer en el mar','1970-01-01 12:25:06', 'El cielo y el mar', 87,8,8,'https://cdnuploads.aa.com.tr/uploads/Contents/2018/09/24/thumbs_b_c_0970f11a6d81d650d9c47ea21f10a751.jpg?v=231325',5,1);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (888, 'Hombre mayor','1970-01-01 12:25:06', 'Hombre mayor', 87,8,8,'https://s03.s3c.es/imag/_v0/770x420/9/9/4/foto.jpg',5,2);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (812, 'Mirada dice mas que mil palabras','1970-01-01 12:25:06', 'Ojos expresivos', 87,8,8,'https://www.dzoom.org.es/wp-content/uploads/2010/09/mirada-ojos-encuadre-primer-plano-sexy-810x540.jpg',2,2);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (813, 'Africa','1970-01-01 12:25:06', 'Africana ', 87,8,8,'https://i.pinimg.com/originals/d2/71/37/d271376bde9a07209941cf1208e9c77a.jpg',3,2);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (814, 'Zorro en la nieve','1970-01-01 12:25:06', 'Zorro en la nieve', 87,8,8,'https://k16.kn3.net/taringa/6/0/0/4/5/1/9/megustaelpolen/368.jpg',1,3);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (815, 'Felino','1970-01-01 12:25:06', 'felino', 87,8,8,'https://wallpapercave.com/wp/wp3171578.jpg',2,3);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (817, 'Ranas azules','1970-01-01 12:25:06', 'ranas', 87,8,8,'https://wallpapercave.com/wp/wp3171598.jpg',3,3);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (818, 'Pandas','1970-01-01 12:25:06', 'pandas', 87,8,8,'https://www.gratistodo.com/wp-content/uploads/2017/01/osos-panda-1.jpg',5,3);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID) values (837, 'Lazare', '1970-01-01 12:25:06', 'Long-tailed skua', 79, 8, 8, 'https://www.apertura.com/__export/1520955322778/sites/revistaap/img/2018/03/13/lobo-wall-street.jpg_1913337537.jpg', 2);

insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (816, 'Pollitos','1970-01-01 12:25:06', 'pollitos', 87,8,8,'https://wallpapercave.com/wp/wp3171587.jpg',1,3);

insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (819, 'Arquitectua antigua','1970-01-01 12:25:06', 'Zorro en la nieve', 87,8,8,'https://images8.alphacoders.com/691/691609.jpg',1,4);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (820, 'Rascacielos China','1970-01-01 12:25:06', 'felino', 87,8,8,'http://www.hdfondos.org/file/26964/728x410/16:9/china-edificios-en-shanghai-calles_1482698950.jpg',2,4);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (821, 'Cielo','1970-01-01 12:25:06', 'ranas', 87,8,8,'https://images2.alphacoders.com/593/593292.jpg',3,4);
insert into PHOTOENTITY (id, nombre, date, description, price, winner, published, rutaFoto, FOTOGRAFO_ID, INTERES_ID) values (822, 'Paris','1970-01-01 12:25:06', 'pandas', 87,8,8,'http://s1.1zoom.me/big0/955/France_Houses_Sky_Paris_471651.jpg',5,4);



insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (100, 801);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (100, 802);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (100, 803);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (100, 804);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (100, 805);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (100, 806);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (100, 807);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (100, 808);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (100, 809);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (101, 810);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (101, 811);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (101, 812);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (101, 813);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (101, 814);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (101, 815);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (102, 816);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (102, 817);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (102, 818);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (102, 819);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (103, 820);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (103, 821);
insert into CONCURSOENTITY_PHOTOENTITY(CONCURSOS_ID,FOTOSENCONCURSO_ID) VAlUES (103, 888);



insert into CONCURSOENTITY_FOTOGRAFOENTITY(CONCURSOS_ID,FOTOGRAFOS_ID) values (100, 2);
insert into CONCURSOENTITY_FOTOGRAFOENTITY(CONCURSOS_ID,FOTOGRAFOS_ID) values (100, 5);


insert into CALIFICACIONENTITY(ID,COMENTARIO,PUNTAJE,FOTOCALIFICADA_ID,CLIENTECALIFICADOR_ID) values (101,'funciona',4,802,100);
