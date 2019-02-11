/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.persistence;

import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.persistence.FotografoPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
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
 * @author s.acostav
 */
@RunWith(Arquillian.class)
public class FotografoPersistenceTest {
    
    @Inject
    private FotografoPersistence fotografoPersistence;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<FotografoEntity> data = new ArrayList<FotografoEntity>();
    @Deployment
    public static JavaArchive createDeployment(){
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FotografoEntity.class.getPackage())
                .addPackage(FotografoPersistence.class.getPackage())
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
        em.createQuery("delete from FotografoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FotografoEntity entity = factory.manufacturePojo(FotografoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        FotografoEntity newEntity = factory.manufacturePojo(FotografoEntity.class);
        FotografoEntity result = fotografoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        FotografoEntity entity = em.find(FotografoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
     @Test
    public void getFotografosTest() {
        List<FotografoEntity> list = fotografoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FotografoEntity ent : list) {
            boolean found = false;
            for (FotografoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void getFotografoTest() {
        FotografoEntity entity = data.get(0);
        FotografoEntity newEntity = fotografoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getApellido(), newEntity.getApellido());
        Assert.assertEquals(entity.getTelefono(), newEntity.getTelefono());
        Assert.assertEquals(entity.getCorreo(), newEntity.getCorreo());
    }
    
    @Test
    public void deleteFotografoTest() {
        FotografoEntity entity = data.get(0);
        fotografoPersistence.delete(entity.getId());
        FotografoEntity deleted = em.find(FotografoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    public void updateFotografoTest() {
        FotografoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FotografoEntity newEntity = factory.manufacturePojo(FotografoEntity.class);

        newEntity.setId(entity.getId());

        fotografoPersistence.update(newEntity);

        FotografoEntity resp = em.find(FotografoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getApellido(), resp.getApellido());
        Assert.assertEquals(newEntity.getCorreo(), resp.getCorreo());
        Assert.assertEquals(newEntity.getTelefono(), resp.getTelefono());
    }
}
