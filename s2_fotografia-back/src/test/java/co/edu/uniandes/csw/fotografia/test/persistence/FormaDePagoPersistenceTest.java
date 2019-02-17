/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.persistence;

import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.fotografia.persistence.FormaDePagoPersistence;
import java.util.ArrayList;
import java.util.List;
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
 * @author Valentina Duarte
 */
@RunWith(Arquillian.class)
public class FormaDePagoPersistenceTest 
{
    
    @Inject
    private FormaDePagoPersistence fdpp;
    
    @PersistenceContext
    private EntityManager em;
    
     @Inject
    UserTransaction utx;
    private List<FormaDePagoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FormaDePagoEntity.class.getPackage())
                .addPackage(FormaDePagoPersistence.class.getPackage())
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
        em.createQuery("delete from FormaDePagoEntity").executeUpdate();
    }
    
      /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FormaDePagoEntity entity = factory.manufacturePojo(FormaDePagoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    
    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        FormaDePagoEntity result = fdpp.create(newEntity);
        Assert.assertNotNull(result);
        
        FormaDePagoEntity entity = em.find(FormaDePagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Prueba para crear una forma de pago.
     */
    @Test
    public void createFormaDePagoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        FormaDePagoEntity result = fdpp.create(newEntity);
        Assert.assertNotNull(result);
        FormaDePagoEntity entity = em.find(FormaDePagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    /**
     * Prueba para consultar la lista de formas de pago.
     */
    @Test
    public void getFormasDePagoTest() {
        List<FormaDePagoEntity> list = fdpp.getAll();
        Assert.assertEquals(data.size(), list.size());
        for (FormaDePagoEntity ent : list) {
            boolean found = false;
            for (FormaDePagoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /**
     * Prueba para consultar una forma de pago.
     */
    @Test
    public void getFormaDePagoTest() {
        FormaDePagoEntity entity = data.get(0);
        FormaDePagoEntity newEntity = fdpp.get(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
     
    }
    /**
     * Prueba para actualizar una forma de pago.
     */
    @Test
    public void setFormaDePagoTest() {
        FormaDePagoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        newEntity.setId(entity.getId());
        fdpp.set(newEntity);
        FormaDePagoEntity resp = em.find(FormaDePagoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    /**
     * Prueba para eliminar una forma de pago.
     */
    @Test
    public void deleteFormaDePagoTest() {
        FormaDePagoEntity entity = data.get(0);
        fdpp.delete(entity.getId());
        FormaDePagoEntity deleted = em.find(FormaDePagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
