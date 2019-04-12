package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.RondaLogic;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
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
 * Pruebas de logica de Authors
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class RondaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private RondaLogic rondaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<RondaEntity> data = new ArrayList<>();   
    private List<ConcursoEntity> conData = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RondaEntity.class.getPackage())
                .addPackage(RondaLogic.class.getPackage())
                .addPackage(RondaPersistence.class.getPackage())
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
        em.createQuery("delete from RondaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
           for (int i = 0; i < 3; i++) {
            RondaEntity entity = factory.manufacturePojo(RondaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Author.
     */
    @Test
    public void createRondaTest() {
        RondaEntity newEntity = factory.manufacturePojo(RondaEntity.class);
        RondaEntity result = rondaLogic.createRonda(newEntity);
        Assert.assertNotNull(result);
        RondaEntity entity = em.find(RondaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNumeroRonda(),entity.getNumeroRonda());
    }

    /**
     * Prueba para consultar la lista de Authors.
     */
    @Test
    public void getRondasTest() {
        List<RondaEntity> list = rondaLogic.getRondas();
        Assert.assertEquals(data.size(), list.size());
        for (RondaEntity entity : list) {
            boolean found = false;
            for (RondaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Author.
     */
    @Test
    public void getRondaTest() {
        RondaEntity entity = data.get(0);
        RondaEntity resultEntity = rondaLogic.getRonda(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNumeroRonda(), resultEntity.getNumeroRonda());
    }

    /**
     * Prueba para actualizar un Author.
     */
    @Test
    public void updateRondaTest() {
        RondaEntity entity = data.get(0);
        RondaEntity pojoEntity = factory.manufacturePojo(RondaEntity.class);

        pojoEntity.setId(entity.getId());

        rondaLogic.updateRonda(pojoEntity.getId(), pojoEntity);

        RondaEntity resp = em.find(RondaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNumeroRonda(), resp.getNumeroRonda());
    }
}