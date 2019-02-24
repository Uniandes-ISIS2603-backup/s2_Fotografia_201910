/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.persistence;

import co.edu.uniandes.csw.fotografia.entities.RondaEntity;
import co.edu.uniandes.csw.fotografia.persistence.RondaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author nicolas melendez
 */
@RunWith(Arquillian.class)
public class RondaPersistenceTest {
    
    @Inject
    private RondaPersistence rondaPersistence;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<RondaEntity> data = new ArrayList<RondaEntity>();
    @Deployment
    public static JavaArchive createDeployment(){
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RondaEntity.class.getPackage())
                .addPackage(RondaPersistence.class.getPackage())
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
        em.createQuery("delete from RondaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            RondaEntity entity = factory.manufacturePojo(RondaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        RondaEntity newEntity = factory.manufacturePojo(RondaEntity.class);
        RondaEntity result = rondaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        RondaEntity entity = em.find(RondaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNumeroRonda(), entity.getNumeroRonda());
    }
    
     @Test
    public void getRondasTest() {
        List<RondaEntity> list = rondaPersistence.findAll();
        org.junit.Assert.assertEquals(data.size(), list.size());
        for (RondaEntity ent : list) {
            boolean found = false;
            for (RondaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            org.junit.Assert.assertTrue(found);
        }
    }
    @Test
    public void getRondaTest() {
        RondaEntity entity = data.get(0);
        RondaEntity newEntity = rondaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNumeroRonda(), newEntity.getNumeroRonda());
    }
    
    @Test
    public void deleteRondaTest() {
        RondaEntity entity = data.get(0);
        rondaPersistence.delete(entity.getId());
        RondaEntity deleted = em.find(RondaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    public void updateRondaTest() {
        RondaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        RondaEntity newEntity = factory.manufacturePojo(RondaEntity.class);

        newEntity.setId(entity.getId());

        rondaPersistence.update(newEntity);

        RondaEntity resp = em.find(RondaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNumeroRonda(), resp.getNumeroRonda());
    
    }
}

