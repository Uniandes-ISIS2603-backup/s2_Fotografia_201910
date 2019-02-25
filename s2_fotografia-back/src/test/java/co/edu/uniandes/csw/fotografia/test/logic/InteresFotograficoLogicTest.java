/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.InteresFotograficoLogic;
import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.InteresFotograficoPersistence;
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
 * @author s.acostav
 */
@RunWith(Arquillian.class)
public class InteresFotograficoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private InteresFotograficoLogic interesFotograficoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<InteresFotograficoEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InteresFotograficoEntity.class.getPackage())
                .addPackage(InteresFotograficoLogic.class.getPackage())
                .addPackage(InteresFotograficoPersistence.class.getPackage())
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
        em.createQuery("delete from InteresFotograficoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            InteresFotograficoEntity entity = factory.manufacturePojo(InteresFotograficoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un InteresFotografico.
     */
    @Test
    public void createInteresFotograficoTest() throws BusinessLogicException {
        InteresFotograficoEntity newEntity = factory.manufacturePojo(InteresFotograficoEntity.class);
        InteresFotograficoEntity result = interesFotograficoLogic.createInteresFotografico(newEntity);
        Assert.assertNotNull(result);
        InteresFotograficoEntity entity = em.find(InteresFotograficoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getInteres(), entity.getInteres());
    }

    /**
     * Prueba para consultar la lista de InteresesFotograficos.
     */
    @Test
    public void getInteresesFotograficosTest() {
        List<InteresFotograficoEntity> list = interesFotograficoLogic.getInteresesFotograficos();
        Assert.assertEquals(data.size(), list.size());
        for (InteresFotograficoEntity entity : list) {
            boolean found = false;
            for (InteresFotograficoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un InteresFotografico.
     */
    @Test
    public void getInteresFotograficoTest() {
        InteresFotograficoEntity entity = data.get(0);
        InteresFotograficoEntity resultEntity = interesFotograficoLogic.getInteresFotografico(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getInteres(), resultEntity.getInteres());
    }

    /**
     * Prueba para actualizar un InteresFotografico.
     */
    @Test
    public void updateInteresFotograficoTest() {
        InteresFotograficoEntity entity = data.get(0);
        InteresFotograficoEntity pojoEntity = factory.manufacturePojo(InteresFotograficoEntity.class);

        pojoEntity.setId(entity.getId());

        interesFotograficoLogic.updateInteresFotografico(pojoEntity.getId(), pojoEntity);

        InteresFotograficoEntity resp = em.find(InteresFotograficoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getInteres(), resp.getInteres());
    }

    /**
     * Prueba para eliminar un InteresFotografico
     *
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @Test
    public void deleteInteresFotograficoTest() throws BusinessLogicException {
       /** InteresFotograficoEntity entity = data.get(0);
        interesFotograficoLogic.deleteInteresFotografico(entity.getId());
        InteresFotograficoEntity deleted = em.find(InteresFotograficoEntity.class, entity.getId());
        Assert.assertNull(deleted);**/
    }
}
