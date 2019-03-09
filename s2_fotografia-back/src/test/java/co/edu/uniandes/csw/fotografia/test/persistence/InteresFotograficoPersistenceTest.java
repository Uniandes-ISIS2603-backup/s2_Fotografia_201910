/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.persistence;

import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import co.edu.uniandes.csw.fotografia.persistence.InteresFotograficoPersistence;
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
public class InteresFotograficoPersistenceTest {
    
    @Inject
    private InteresFotograficoPersistence interesFotograficoPersistence;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<InteresFotograficoEntity> data = new ArrayList<InteresFotograficoEntity>();
    @Deployment
    public static JavaArchive createDeployment(){
         return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InteresFotograficoEntity.class.getPackage())
                .addPackage(InteresFotograficoPersistence.class.getPackage())
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
        em.createQuery("delete from InteresFotograficoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            InteresFotograficoEntity entity = factory.manufacturePojo(InteresFotograficoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        InteresFotograficoEntity newEntity = factory.manufacturePojo(InteresFotograficoEntity.class);
        InteresFotograficoEntity result = interesFotograficoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        InteresFotograficoEntity entity = em.find(InteresFotograficoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getInteres(), entity.getInteres());
    }
    
     @Test
    public void getInteresFotograficosTest() {
        List<InteresFotograficoEntity> list = interesFotograficoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (InteresFotograficoEntity ent : list) {
            boolean found = false;
            for (InteresFotograficoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void find(){
        InteresFotograficoEntity entity = data.get(0);
        InteresFotograficoEntity esperado = interesFotograficoPersistence.find(entity.getId());
        Assert.assertEquals(entity.getId(), esperado.getId());
    }
    @Test
    public void getInteresFotograficoTest() {
        InteresFotograficoEntity entity = data.get(0);
        InteresFotograficoEntity newEntity = interesFotograficoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getInteres(), newEntity.getInteres());
    }
    
    @Test
    public void deleteInteresFotograficoTest() {
        InteresFotograficoEntity entity = data.get(0);
        interesFotograficoPersistence.delete(entity.getId());
        InteresFotograficoEntity deleted = em.find(InteresFotograficoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    public void updateInteresFotograficoTest() {
        InteresFotograficoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        InteresFotograficoEntity newEntity = factory.manufacturePojo(InteresFotograficoEntity.class);

        newEntity.setId(entity.getId());

        interesFotograficoPersistence.update(newEntity);

        InteresFotograficoEntity resp = em.find(InteresFotograficoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getInteres(), resp.getInteres());
    }
}
