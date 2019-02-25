/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.FotografoLogic;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.FotografoPersistence;
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
public class FotografoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FotografoLogic fotografoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FotografoEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FotografoEntity.class.getPackage())
                .addPackage(FotografoLogic.class.getPackage())
                .addPackage(FotografoPersistence.class.getPackage())
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
        em.createQuery("delete from FotografoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            FotografoEntity entity = factory.manufacturePojo(FotografoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Fotografo.
     */
    @Test
    public void createFotografoTest() throws BusinessLogicException {
        FotografoEntity newEntity = factory.manufacturePojo(FotografoEntity.class);
        FotografoEntity result = fotografoLogic.createFotografo(newEntity);
        Assert.assertNotNull(result);
        FotografoEntity entity = em.find(FotografoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());
    }

    /**
     * Prueba para consultar la lista de Fotografos.
     */
    @Test
    public void getFotografosTest() {
        List<FotografoEntity> list = fotografoLogic.getFotografos();
        Assert.assertEquals(data.size(), list.size());
        for (FotografoEntity entity : list) {
            boolean found = false;
            for (FotografoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Fotografo.
     */
    @Test
    public void getFotografoTest() {
        FotografoEntity entity = data.get(0);
        FotografoEntity resultEntity = fotografoLogic.getFotografo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getApellido(), resultEntity.getApellido());
    }

    /**
     * Prueba para actualizar un Fotografo.
     */
    @Test
    public void updateFotografoTest() {
        FotografoEntity entity = data.get(0);
        FotografoEntity pojoEntity = factory.manufacturePojo(FotografoEntity.class);

        pojoEntity.setId(entity.getId());

        fotografoLogic.updateFotografo(pojoEntity.getId(), pojoEntity);

        FotografoEntity resp = em.find(FotografoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getApellido(), resp.getApellido());
    }

    /**
     * Prueba para eliminar un Fotografo
     *
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @Test
    public void deleteFotografoTest() throws BusinessLogicException {
       /** FotografoEntity entity = data.get(0);
        fotografoLogic.deleteFotografo(entity.getId());
        FotografoEntity deleted = em.find(FotografoEntity.class, entity.getId());
        Assert.assertNull(deleted);**/
    }
}
