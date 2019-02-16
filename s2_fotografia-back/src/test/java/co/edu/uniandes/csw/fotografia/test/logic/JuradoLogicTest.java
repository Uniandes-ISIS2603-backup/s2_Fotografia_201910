/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.JuradoLogic;
import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
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
public class JuradoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private JuradoLogic juradoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

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
                .addPackage(JuradoLogic.class.getPackage())
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
        JuradoEntity newEntity = factory.manufacturePojo(JuradoEntity.class);
        JuradoEntity result = juradoLogic.createJurado(newEntity);
        Assert.assertNotNull(result);
        JuradoEntity entity = em.find(JuradoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());
    }

    /**
     * Prueba para consultar la lista de Jurados.
     */
    @Test
    public void getJuradosTest() {
        List<JuradoEntity> list = juradoLogic.getJurados();
        Assert.assertEquals(data.size(), list.size());
        for (JuradoEntity entity : list) {
            boolean found = false;
            for (JuradoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
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
        JuradoEntity resultEntity = juradoLogic.getJurado(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getApellido(), resultEntity.getApellido());
    }

    /**
     * Prueba para actualizar un Jurado.
     */
    @Test
    public void updateJuradoTest() {
        JuradoEntity entity = data.get(0);
        JuradoEntity pojoEntity = factory.manufacturePojo(JuradoEntity.class);

        pojoEntity.setId(entity.getId());

        juradoLogic.updateJurado(pojoEntity.getId(), pojoEntity);

        JuradoEntity resp = em.find(JuradoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getApellido(), resp.getApellido());
    }

    /**
     * Prueba para eliminar un Jurado
     *
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @Test
    public void deleteJuradoTest() throws BusinessLogicException {
        JuradoEntity entity = data.get(0);
        juradoLogic.deleteJurado(entity.getId());
        JuradoEntity deleted = em.find(JuradoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
