package co.edu.uniandes.csw.fotografia.test.persistence;

import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.persistence.ConcursoPersistence;
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
 * @author Nicolas Rincon
 */
@RunWith(Arquillian.class)
public class ConcursoPersistenceTest {
    @Inject
    private ConcursoPersistence fp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    private List<ConcursoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConcursoEntity.class.getPackage())
                .addPackage(ConcursoPersistence.class.getPackage())
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
        em.createQuery("delete from ConcursoEntity").executeUpdate();
    }
    
      /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ConcursoEntity entity = factory.manufacturePojo(ConcursoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    
    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ConcursoEntity newEntity = factory.manufacturePojo(ConcursoEntity.class);
        ConcursoEntity result = fp.create(newEntity);
        Assert.assertNotNull(result);
        
        ConcursoEntity entity = em.find(ConcursoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Prueba para crear una factura.
     */
    @Test
    public void createConcursoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ConcursoEntity newEntity = factory.manufacturePojo(ConcursoEntity.class);
        ConcursoEntity result = fp.create(newEntity);
        Assert.assertNotNull(result);
        ConcursoEntity entity = em.find(ConcursoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    /**
     * Prueba para consultar la lista de facturas
     */
    @Test
    public void getConcursosTest() {
        List<ConcursoEntity> list = fp.getAll();
        Assert.assertEquals(data.size(), list.size());
        for (ConcursoEntity ent : list) {
            boolean found = false;
            for (ConcursoEntity entity : data) {
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
    public void getConcursoTest() {
        ConcursoEntity entity = data.get(0);
        ConcursoEntity newEntity = fp.get(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getTema(), newEntity.getTema());
     
    }
    /**
     * Prueba para actualizar una factura.
     */
    @Test
    public void setConcursoTest() {
        ConcursoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ConcursoEntity newEntity = factory.manufacturePojo(ConcursoEntity.class);
        newEntity.setId(entity.getId());
        fp.set(newEntity);
        ConcursoEntity resp = em.find(ConcursoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    /**
     * Prueba para eliminar una forma de pago.
     */
    @Test
    public void deleteFormaDePagoTest() {
        ConcursoEntity entity = data.get(0);
        fp.delete(entity.getId());
        ConcursoEntity deleted = em.find(ConcursoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
