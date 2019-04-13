/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.ConcursoLogic;
import co.edu.uniandes.csw.fotografia.ejb.ConcursoClienteLogic;
import co.edu.uniandes.csw.fotografia.ejb.ClienteLogic;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.persistence.ConcursoPersistence;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.ConcursoPersistence;
import co.edu.uniandes.csw.fotografia.persistence.ClientePersistence;
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
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Pruebas de logica de la relacion Concurso - Clientes
 *
 * @author NicolasRinconD
 */
@RunWith(Arquillian.class)
public class ConcursoClienteLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ConcursoLogic concursoLogic;
    @Inject
    private ConcursoClienteLogic concursoClienteLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ClienteEntity> data = new ArrayList<ClienteEntity>();

    private List<ConcursoEntity> concursosData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ConcursoLogic.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
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
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ConcursoEntity concursos = factory.manufacturePojo(ConcursoEntity.class);
            em.persist(concursos);
            concursosData.add(concursos);
        }
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                concursosData.get(i).setCliente(entity);
            }
        }
    }

    /**
     * Prueba para remplazar las instancias de Concursos asociadas a una instancia
     * de Cliente.
     */
    @Test
    public void replaceClienteTest() {
        ConcursoEntity entity = concursosData.get(0);
        concursoClienteLogic.replaceCliente(entity.getId(), data.get(1).getId());
        entity = concursoLogic.getConcurso(entity.getId());
        Assert.assertEquals(entity.getCliente(), data.get(1));
    }

    /**
     * Prueba para desasociar un Concurso existente de un Cliente existente
     *
     * @throws co.edu.uniandes.csw.concursostore.exceptions.BusinessLogicException
     */
    @Test
    public void removeConcursosTest() throws BusinessLogicException {
        concursoClienteLogic.removeCliente(concursosData.get(0).getId());
        ConcursoEntity response = concursoLogic.getConcurso(concursosData.get(0).getId());
        Assert.assertNull(response.getCliente());
    }
}
