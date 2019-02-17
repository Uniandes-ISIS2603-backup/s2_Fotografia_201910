/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.persistence;

import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
import co.edu.uniandes.csw.fotografia.persistence.FacturaPersistence;
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

@RunWith (Arquillian.class)
public class FacturaPersistenceTest 
{
    @Inject
    private FacturaPersistence fp; 
    
    @PersistenceContext
    private EntityManager em;
    
     @Inject
    UserTransaction utx;
    private List<FacturaEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
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
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }
    
      /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
        @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        FacturaEntity result = fp.create(newEntity);
        Assert.assertNotNull(result);
        
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
        
    }
    
     /**
     * Prueba para crear una factura.
     */
    @Test
    public void createFacturaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        FacturaEntity result = fp.create(newEntity);
        Assert.assertNotNull(result);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    /**
     * Prueba para consultar la lista de facturas.
     */
    @Test
    public void getFacturasTest() {
        List<FacturaEntity> list = fp.getAll();
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity ent : list) {
            boolean found = false;
            for (FacturaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /**
     * Prueba para consultar una factura.
     */
    @Test
    public void getFacturaTest() {
        FacturaEntity entity = data.get(0);
        FacturaEntity newEntity = fp.get(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNumero(), newEntity.getNumero());
        Assert.assertEquals(entity.getPrecio(), newEntity.getPrecio());
    }
    /**
     * Prueba para actualizar una Factura.
     */
    @Test
    public void setFacturaTest() {
        FacturaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setId(entity.getId());
        fp.set(newEntity);
        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    /**
     * Prueba para eliminar una factura.
     */
    @Test
    public void deleteFacturaTest() {
        FacturaEntity entity = data.get(0);
        fp.delete(entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
