/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.PhotoLogic;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.PhotoPersistance;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author da.benavides
 */
@RunWith(Arquillian.class)
public class PhotoLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
   
   @Inject
    private PhotoLogic photoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PhotoEntity> data = new ArrayList<>();
    
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PhotoEntity.class.getPackage())
                .addPackage(PhotoLogic.class.getPackage())
                .addPackage(PhotoPersistance.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
        /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
        /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from PhotoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            PhotoEntity factura = factory.manufacturePojo(PhotoEntity.class);
            em.persist(factura);
            data.add(factura);
        }
    }
    
    /**
     * Prueba el metodo de createPhoto de la clase photLogic
     * @throws BusinessLogicException si no se puede crear la Photo
     */
     @Test
    public void createPhotoTest() throws BusinessLogicException 
    {
        
        PhotoEntity newEntity = factory.manufacturePojo(PhotoEntity.class);
        Random  rnd;
        Date    dt;
        long    ms;

        // Get a new random instance, seeded from the clock
        rnd = new Random();

        // Get an Epoch value roughly between 1940 and 2010
        // Add up to 70 years to it (using modulus on the next long)
        ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));

        // Construct a date
        dt = new Date(ms);
        newEntity.setDate(dt);
        newEntity.setDescription("Esta es una descripción de menos de 300 caracteres.");        
        PhotoEntity result = photoLogic.createPhoto(newEntity);
        Assert.assertNotNull(result);
        PhotoEntity entity = em.find(PhotoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
     /**
     * Prueba para consultar la lista de fotos.
     */
    @Test
    public void getFotosTest() {
        List<PhotoEntity> list = photoLogic.getFotos();
        Assert.assertEquals(data.size(), list.size());
        for (PhotoEntity entity : list) {
            boolean found = false;
            for (PhotoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /**
     * Prueba para actualizar una foto.
     */
    @Test
    public void setPhotoTest() throws BusinessLogicException {
        PhotoEntity entity = data.get(0);
        PhotoEntity pojoEntity = factory.manufacturePojo(PhotoEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setDescription("Lindo atardecer por la tarde");
        pojoEntity.setPrice(5000.0);
        photoLogic.updateFoto(pojoEntity.getId(), pojoEntity);
        PhotoEntity resp = em.find(PhotoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
        Assert.assertEquals(pojoEntity.getPrice(), resp.getPrice());

    }
    /**
     * Prueba para eliminar un cliente
     */
    @Test
    public void deletePhotoTest() {
        PhotoEntity entity = data.get(0);
        photoLogic.deletePhoto(entity.getId());
        PhotoEntity deleted = em.find(PhotoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    /**
     * Prueba para consultar un Foto.
     */
    @Test
    public void getClienteTest() {
        PhotoEntity entity = data.get(0);
        PhotoEntity resultEntity = photoLogic.getFoto(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }
    
    /**
     * Prueba crea una foto con una fecha en el futuro
     *
     * @throws BusinessLogicException si no se puede crear la foto
     */
    @Test(expected = BusinessLogicException.class)
    public void createFotoEnElFuturoTest() throws BusinessLogicException {
        PhotoEntity newEntity = factory.manufacturePojo(PhotoEntity.class);
        newEntity.setDate(new Date(2020, 12, 12));
        photoLogic.createPhoto(newEntity);
    }
    /**
     * Prueba crear una foto sin nombre
     *
     * @throws BusinessLogicException si no se puede crear la foto
     */
    @Test(expected = BusinessLogicException.class)
    public void createFotoSinNombreTest() throws BusinessLogicException {
        PhotoEntity newEntity = factory.manufacturePojo(PhotoEntity.class);
        newEntity.setName(null);
        photoLogic.createPhoto(newEntity);
    }
    /**
     * Prueba crear una foto con un nombre muy largo
     *
     * @throws BusinessLogicException si no se puede crear la foto
     */
    @Test(expected = BusinessLogicException.class)
    public void createFotoConNombreLargoTest() throws BusinessLogicException {
        PhotoEntity newEntity = factory.manufacturePojo(PhotoEntity.class);
        newEntity.setName("Dany Alejandro Benavides Pedroza esta es una foto con un nombre demasiado largo");
        photoLogic.createPhoto(newEntity);
    }
    /**
     * Prueba crear una foto con un precio negativo
     *
     * @throws BusinessLogicException si no se puede crear la foto
     */
    @Test(expected = BusinessLogicException.class)
    public void createFotoConPrecioNegativoTest() throws BusinessLogicException {
        PhotoEntity newEntity = factory.manufacturePojo(PhotoEntity.class);
        newEntity.setPrice(-50.2);
        photoLogic.createPhoto(newEntity);
    }
    /**
     * Prueba crear una foto con una descrpcion muy larga
     *
     * @throws BusinessLogicException si no se puede crear la foto
     */
    @Test(expected = BusinessLogicException.class)
    public void createFotoConDescripcionLargaTest() throws BusinessLogicException {
        PhotoEntity newEntity = factory.manufacturePojo(PhotoEntity.class);
        newEntity.setDescription("Dany Alejandro Benavides Pedroza esta es una foto con un nombre demasiado largoDany Alejandro Benavides Pedroza esta es una foto con un nombre demasiado largoDany Alejandro Benavides Pedroza esta es una foto con un nombre demasiado largoDany Alejandro Benavides Pedroza esta es una foto con un nombre demasiado largoDany Alejandro Benavides Pedroza esta es una foto con un nombre demasiado largoDany Alejandro Benavides Pedroza esta es una foto con un nombre demasiado largoDany Alejandro Benavides Pedroza esta es una foto con un nombre demasiado largoDany Alejandro Benavides Pedroza esta es una foto con un nombre demasiado largoDany Alejandro Benavides Pedroza esta es una foto con un nombre demasiado largoDany Alejandro Benavides Pedroza esta es una foto con un nombre demasiado largoDany Alejandro Benavides Pedroza esta es una foto con un nombre demasiado largo");
        photoLogic.createPhoto(newEntity);
    }
}
