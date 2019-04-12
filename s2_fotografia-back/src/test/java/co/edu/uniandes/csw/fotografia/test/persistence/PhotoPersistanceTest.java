/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.persistence;

;import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.persistence.PhotoPersistance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.junit.Before;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author da.benavides
 */
@RunWith(Arquillian.class)
public class PhotoPersistanceTest {
    @Inject
    private PhotoPersistance photoPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<PhotoEntity> data = new ArrayList<PhotoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PhotoPersistance.class.getPackage())
                .addPackage(PhotoEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PhotoEntity entity = factory.manufacturePojo(PhotoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * 
     * Prueba la creación de una foto válida
     */
    @Test
    public void createPhotoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PhotoEntity newEntity = factory.manufacturePojo(PhotoEntity.class);
        PhotoEntity result = photoPersistence.create(newEntity);
        Assert.assertNotNull(result);
    }
    /**
     * Prueba para consultar la lista de fotos.
     */
    @Test
    public void getFotosTest() {
        List<PhotoEntity> list = photoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PhotoEntity ent : list) {
            boolean found = false;
            for (PhotoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /**
     * Prueba para consultar una foto.
     */
    @Test
    public void getFotoTest() {
        PhotoEntity entity = data.get(0);
        PhotoEntity newEntity = photoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
     
    }
    /**
     * Prueba para actualizar una foto.
     */
    @Test
    public void setClienteTest() {
        PhotoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PhotoEntity newEntity = factory.manufacturePojo(PhotoEntity.class);
        newEntity.setId(entity.getId());
        photoPersistence.update(newEntity);
        PhotoEntity resp = em.find(PhotoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    /**
     * Prueba para eliminar una foto.
     */
    @Test
    public void deleteClienteTest() {
        PhotoEntity entity = data.get(0);
        photoPersistence.delete(entity.getId());
        PhotoEntity deleted = em.find(PhotoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
