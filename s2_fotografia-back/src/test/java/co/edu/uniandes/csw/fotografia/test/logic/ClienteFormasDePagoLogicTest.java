/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.ClienteFormasDePagoLogic;
import co.edu.uniandes.csw.fotografia.ejb.ClienteLogic;
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
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Valentina Duarte
 */
@RunWith(Arquillian.class)
public class ClienteFormasDePagoLogicTest 
{
    

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ClienteLogic clienteLogic;
    @Inject
    private ClienteFormasDePagoLogic clienteFormasDePagoLogic;

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
                .addPackage(ClienteLogic.class.getPackage())
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
     * Prueba para asociar un FormasDePago existente a un Cliente.
     */
    @Test
    public void addFormasDePagoTest() {
        ClienteEntity entity = data.get(0);
        FormaDePagoEntity formaDePagoEntity = formasDePagoData.get(1);
        FormaDePagoEntity response = clienteFormasDePagoLogic.addFormaDePago(formaDePagoEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(formaDePagoEntity.getId(), response.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de FormasDePago asociadas a una
     * instancia Cliente.
     */
    @Test
    public void getFormasDePagoTest() {
        List<FormaDePagoEntity> list = clienteFormasDePagoLogic.getFormasDePago(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }

    /**
     * Prueba para obtener una instancia de FormasDePago asociada a una instancia
     * Cliente.
     *
     * @throws co.edu.uniandes.csw.formasDePago.exceptions.BusinessLogicException
     */
    @Test
    public void getFormaDePagoTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        FormaDePagoEntity formaDePagoEntity = formasDePagoData.get(0);
        FormaDePagoEntity response = clienteFormasDePagoLogic.getFormaDePago(entity.getId(), formaDePagoEntity.getId());

        Assert.assertEquals(formaDePagoEntity.getId(), response.getId());
        Assert.assertEquals(formaDePagoEntity.getNumeroTarjeta(), response.getNumeroTarjeta());
        Assert.assertEquals(formaDePagoEntity.getNumeroVerificacion(), response.getNumeroVerificacion());
    }

    /**
     * Prueba para obtener una instancia de FormasDePago asociada a una instancia
     * Cliente que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.formasDePago.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getFormaDePagoNoAsociadoTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        FormaDePagoEntity formaDePagoEntity = formasDePagoData.get(1);
        clienteFormasDePagoLogic.getFormaDePago(entity.getId(), formaDePagoEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de FormasDePago asociadas a una instancia
     * de Cliente.
     */
    @Test
    public void replaceFormasDePagoTest() {
        ClienteEntity entity = data.get(0);
        List<FormaDePagoEntity> list = formasDePagoData.subList(1, 3);
        clienteFormasDePagoLogic.replaceFormasDePago(entity.getId(), list);

        entity = clienteLogic.getCliente(entity.getId());
        Assert.assertFalse(entity.getFormasDePago().contains(formasDePagoData.get(0)));
        Assert.assertTrue(entity.getFormasDePago().contains(formasDePagoData.get(1)));
        Assert.assertTrue(entity.getFormasDePago().contains(formasDePagoData.get(2)));
    }
}
