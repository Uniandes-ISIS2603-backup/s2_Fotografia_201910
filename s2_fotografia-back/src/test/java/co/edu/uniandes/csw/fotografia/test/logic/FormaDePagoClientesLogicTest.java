/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.FormaDePagoClientesLogic;
import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.ClientePersistence;
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
 * @author Valentina Duarte
 */
@RunWith(Arquillian.class)
public class FormaDePagoClientesLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FormaDePagoClientesLogic formaDePagoClienteLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private ArrayList<ClienteEntity> data = new ArrayList<ClienteEntity>();

    private ArrayList<FormaDePagoEntity> formasDePagoData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(FormaDePagoClientesLogic.class.getPackage())
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
        em.createQuery("delete from FormaDePagoEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
   private void insertData() {
        for (int i = 0; i < 3; i++) {
            FormaDePagoEntity formasDePago = factory.manufacturePojo(FormaDePagoEntity.class);
            em.persist(formasDePago);
            formasDePagoData.add(formasDePago);
        }
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                formasDePagoData.get(i).setCliente(entity);
            }
        }
    }
   
 

    /**
     * Prueba para asociar un FormaDePagos existente a un Cliente.
     */
    @Test
    public void addClienteTest() {
        ClienteEntity entity = data.get(0);
        FormaDePagoEntity formaDePagoEntity = formasDePagoData.get(1);
        ClienteEntity response = formaDePagoClienteLogic.addCliente(entity.getId(), formaDePagoEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un Cliente.
     */
    @Test
    public void getClienteTest() {
        FormaDePagoEntity entity = formasDePagoData.get(0);
        ClienteEntity resultEntity = formaDePagoClienteLogic.getCliente(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getCliente().getId(), resultEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de FormaDePagos asociadas a una instancia
     * de Cliente.
     */
    @Test
    public void replaceClienteTest() {
        ClienteEntity entity = data.get(0);
        formaDePagoClienteLogic.replaceCliente(formasDePagoData.get(1).getId(), entity.getId());
        entity = formaDePagoClienteLogic.getCliente(formasDePagoData.get(1).getId());
        Assert.assertTrue(entity.getFormasDePago().contains(formasDePagoData.get(1)));
    }

    /**
     * Prueba para desasociar un FormaDePago existente de un Cliente existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void removeFormaDePagoTest() throws BusinessLogicException {
        formaDePagoClienteLogic.removeCliente(formasDePagoData.get(0).getId());
        Assert.assertNull(formaDePagoClienteLogic.getCliente(formasDePagoData.get(0).getId()));
    }

    /**
     * Prueba para desasociar un FormaDePago existente de un Cliente existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removeFormaDePagoSinClienteTest() throws BusinessLogicException {
        formaDePagoClienteLogic.removeCliente(formasDePagoData.get(1).getId());
    }
}
