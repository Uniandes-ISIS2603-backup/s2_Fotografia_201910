/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.persistence;

import co.edu.uniandes.csw.fotografia.entities.OrganizadorEntity;
import co.edu.uniandes.csw.fotografia.persistence.OrganizadorPersistence;
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
 * @author nicolas melendez
 */
@RunWith(Arquillian.class)
public class OrganizadorPersistenceTest {
    
    @Inject
    private OrganizadorPersistence organizadorPersistence;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<OrganizadorEntity> data = new ArrayList<OrganizadorEntity>();
    @Deployment
    public static JavaArchive createDeployment(){
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrganizadorEntity.class.getPackage())
                .addPackage(OrganizadorPersistence.class.getPackage())
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
        em.createQuery("delete from OrganizadorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            OrganizadorEntity entity = factory.manufacturePojo(OrganizadorEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        OrganizadorEntity newEntity = factory.manufacturePojo(OrganizadorEntity.class);
        OrganizadorEntity result = organizadorPersistence.create(newEntity);

        Assert.assertNotNull(result);

        OrganizadorEntity entity = em.find(OrganizadorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
     @Test
    public void getOrganizadorsTest() {
        List<OrganizadorEntity> list = organizadorPersistence.findAll();
        org.junit.Assert.assertEquals(data.size(), list.size());
        for (OrganizadorEntity ent : list) {
            boolean found = false;
            for (OrganizadorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            org.junit.Assert.assertTrue(found);
        }
    }
    @Test
    public void getOrganizadorTest() {
        OrganizadorEntity entity = data.get(0);
        OrganizadorEntity newEntity = organizadorPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getApellido(), newEntity.getApellido());
        Assert.assertEquals(entity.getTelefono(), newEntity.getTelefono());
        Assert.assertEquals(entity.getCorreo(), newEntity.getCorreo());
    }
    
    @Test
    public void deleteOrganizadorTest() {
        OrganizadorEntity entity = data.get(0);
        organizadorPersistence.delete(entity.getId());
        OrganizadorEntity deleted = em.find(OrganizadorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    public void updateOrganizadorTest() {
        OrganizadorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        OrganizadorEntity newEntity = factory.manufacturePojo(OrganizadorEntity.class);

        newEntity.setId(entity.getId());

        organizadorPersistence.update(newEntity);

        OrganizadorEntity resp = em.find(OrganizadorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getApellido(), resp.getApellido());
        Assert.assertEquals(newEntity.getCorreo(), resp.getCorreo());
        Assert.assertEquals(newEntity.getTelefono(), resp.getTelefono());
    }
}
