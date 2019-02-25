/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.OrganizadorLogic;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.OrganizadorEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.OrganizadorPersistence;
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
 * Pruebas de logica de organizadores
 *
 * @author Nicolas Melendez
 */
@RunWith(Arquillian.class)
public class OrganizadorLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private OrganizadorLogic organizadorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<OrganizadorEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrganizadorEntity.class.getPackage())
                .addPackage(OrganizadorLogic.class.getPackage())
                .addPackage(OrganizadorPersistence.class.getPackage())
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
        em.createQuery("delete from OrganizadorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            OrganizadorEntity entity = factory.manufacturePojo(OrganizadorEntity.class);
            em.persist(entity);
            entity.setConcursos(new ArrayList<>());
            data.add(entity);
        }
        OrganizadorEntity organizador = data.get(2);
        ConcursoEntity entity = factory.manufacturePojo(ConcursoEntity.class);
        entity.setOrganizador(organizador);
        em.persist(entity);
        organizador.getConcursos().add(entity);
    }

    /**
     * Prueba para crear un Organizador.
     */
    @Test
    public void createOrganizadorTest() {
        OrganizadorEntity newEntity = factory.manufacturePojo(OrganizadorEntity.class);
        OrganizadorEntity result = organizadorLogic.createOrganizador(newEntity);
        Assert.assertNotNull(result);
        OrganizadorEntity entity = em.find(OrganizadorEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getEdad(), entity.getEdad());
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
    }

    /**
     * Prueba para consultar la lista de Organizadors.
     */
    @Test
    public void getOrganizadorsTest() {
        List<OrganizadorEntity> list = organizadorLogic.getOrganizadors();
        Assert.assertEquals(data.size(), list.size());
        for (OrganizadorEntity entity : list) {
            boolean found = false;
            for (OrganizadorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Organizador.
     */
    @Test
    public void getOrganizadorTest() {
        OrganizadorEntity entity = data.get(0);
        OrganizadorEntity resultEntity = organizadorLogic.getOrganizador(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getEdad(), resultEntity.getEdad());
        Assert.assertEquals(entity.getCorreo(), resultEntity.getCorreo());
    }

  

    /**
     * Prueba para eliminar un Organizador
     *
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @Test
    public void deleteOrganizadorTest() throws BusinessLogicException {
        OrganizadorEntity entity = data.get(0);
        organizadorLogic.deleteOrganizador(entity.getId());
        OrganizadorEntity deleted = em.find(OrganizadorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminar un Organizador asociado a un Concurso
     *
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteOrganizadorConConcursoTest() throws BusinessLogicException {
        organizadorLogic.deleteOrganizador(data.get(2).getId());
    }
    
}