/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.ConcursoLogic;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class ConcursoLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ConcursoLogic concursoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ConcursoEntity> data = new ArrayList<>();

    
    
    
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConcursoEntity.class.getPackage())
                .addPackage(ConcursoLogic.class.getPackage())
                .addPackage(ConcursoPersistence.class.getPackage())
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
        em.createQuery("delete from ConcursoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ConcursoEntity entity = factory.manufacturePojo(ConcursoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
       
    }

    /**
     * Prueba para crear un Concurso.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException .
     */
    @Test
    public void createConcursoTest() throws BusinessLogicException {
        ConcursoEntity newEntity = factory.manufacturePojo(ConcursoEntity.class);
        ConcursoEntity result = concursoLogic.createConcurso(newEntity);
        Assert.assertNotNull(result);
        ConcursoEntity entity = em.find(ConcursoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTema(), entity.getTema());
        Assert.assertEquals(newEntity.getEdadFoto(), entity.getEdadFoto());
    }
    /**
     * Prueba para crear un concurso con numero maximo invalido
     */
    @Test(expected = BusinessLogicException.class)
    public void createConcursoInvalido1Test() throws BusinessLogicException{
        ConcursoEntity newEntity = factory.manufacturePojo(ConcursoEntity.class);
        newEntity.setMaximasFotos(0);
        concursoLogic.createConcurso(newEntity); 
    }

    /**
     * Prueba para consultar la lista de Concursos.
     */
    @Test
    public void getConcursosTest() {
        List<ConcursoEntity> list = concursoLogic.getConcursos();
        Assert.assertEquals(data.size(), list.size());
        for (ConcursoEntity entity : list) {
            boolean found = false;
            for (ConcursoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    
    /**
     * Prueba para consultar un Concurso.
     */
    @Test
    public void getConcursoTest() {
        ConcursoEntity entity = data.get(0);
        ConcursoEntity resultEntity = concursoLogic.getConcurso(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getTema(), resultEntity.getTema());
        Assert.assertEquals(entity.getMaximasFotos(), resultEntity.getMaximasFotos());
    }

    /**
     * Prueba para actualizar un Concurso.
     */
    @Test
    public void updateConcursoTest() {
        ConcursoEntity entity = data.get(0);
        ConcursoEntity pojoEntity = factory.manufacturePojo(ConcursoEntity.class);

        pojoEntity.setId(entity.getId());

        concursoLogic.updateConcurso(pojoEntity.getId(), pojoEntity);

        ConcursoEntity resp = em.find(ConcursoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getTema(), resp.getTema());
        Assert.assertEquals(pojoEntity.getCantidadPremio(), resp.getCantidadPremio());
    }

   

}
