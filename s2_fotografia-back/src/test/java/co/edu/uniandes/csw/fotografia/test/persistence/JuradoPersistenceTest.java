/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.persistence;

import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import co.edu.uniandes.csw.fotografia.persistence.JuradoPersistence;
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
 * @author a.trujilloa1
 */
@RunWith(Arquillian.class)
public class JuradoPersistenceTest {
    
     @Inject
    private JuradoPersistence juradoPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<JuradoEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(JuradoEntity.class.getPackage())
                .addPackage(JuradoPersistence.class.getPackage())
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
        em.createQuery("delete from JuradoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            JuradoEntity entity = factory.manufacturePojo(JuradoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Jurado.
     */
    @Test
    public void createJuradoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        JuradoEntity newEntity = factory.manufacturePojo(JuradoEntity.class);
        JuradoEntity result = juradoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        JuradoEntity entity = em.find(JuradoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar la lista de Jurados.
     */
    @Test
    public void getJuradosTest() {
        List<JuradoEntity> list = juradoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (JuradoEntity ent : list) {
            boolean found = false;
            for (JuradoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Jurado.
     */
    @Test
    public void getJuradoTest() {
        JuradoEntity entity = data.get(0);
        JuradoEntity newEntity = juradoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getApellido(), newEntity.getApellido());
    }

    /**
     * Prueba para actualizar un Jurado.
     */
    @Test
    public void updateJuradoTest() {
        JuradoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        JuradoEntity newEntity = factory.manufacturePojo(JuradoEntity.class);

        newEntity.setId(entity.getId());

        juradoPersistence.update(newEntity);

        JuradoEntity resp = em.find(JuradoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para eliminar un Jurado.
     */
    @Test
    public void deleteJuradoTest() {
        JuradoEntity entity = data.get(0);
        juradoPersistence.delete(entity.getId());
        JuradoEntity deleted = em.find(JuradoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
