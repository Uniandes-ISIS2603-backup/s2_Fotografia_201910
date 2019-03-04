/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.RondaLogic;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.RondaEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.RondaPersistence;
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
 * Pruebas de logica de Ronda
 *
 * @author Nicolas Melendez
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

    private RondaEntity data = new RondaEntity();

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
        ConcursoEntity concurso = factory.manufacturePojo(ConcursoEntity.class);
         em.persist(concurso);
        concurso.setRonda(data);
       
        data.setConcurso(concurso);
    }

    /**
     * Prueba para crear un Ronda.
     *
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @Test
    public void createRondaTest() throws BusinessLogicException {
        RondaEntity newEntity = factory.manufacturePojo(RondaEntity.class);
       
        RondaEntity result = rondaLogic.createRonda(newEntity);
        Assert.assertNotNull(result);
        RondaEntity entity = em.find(RondaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

}

