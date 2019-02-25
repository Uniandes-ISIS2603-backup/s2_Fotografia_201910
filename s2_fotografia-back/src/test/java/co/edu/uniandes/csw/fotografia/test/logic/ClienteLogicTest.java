/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.ClienteLogic;
import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
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
public class ClienteLogicTest 
{
   private PodamFactory factory = new PodamFactoryImpl();
   
   @Inject
    private ClienteLogic clienteLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ClienteEntity> data = new ArrayList<>();

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
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            ClienteEntity cliente = factory.manufacturePojo(ClienteEntity.class);
            em.persist(cliente);
            data.add(cliente);
        }
    }
    
    
    /**
     * Prueba el metodo de crearCliente de la clase clienteLogic
     * @throws BusinessLogicException si no se puede crear el cliente
     */
     @Test
    public void createClienteTest() throws BusinessLogicException 
    {
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setCorreo("gabo@hotmail.com");
        newEntity.setContrasena("xxxxxxxx123");
        ClienteEntity result = clienteLogic.createCliente(newEntity);
        Assert.assertNotNull(result);
        ClienteEntity entity = em.find(ClienteEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getLogin(), entity.getLogin());
    }
    
     /**
     * Prueba crear dos clientes con el mismo login
     *
     * @throws BusinessLogicException si no se puede crear el cliente
     */
    @Test(expected = BusinessLogicException.class)
    public void createClienteConMismoLoginTest() throws BusinessLogicException 
    {
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setLogin(data.get(0).getLogin());
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba crear un cliente con correo no valido
     *
     * @throws BusinessLogicException si no se puede crear el cliente
     */
    @Test(expected = BusinessLogicException.class)
    public void createClienteCorreoInvalidoTest() throws BusinessLogicException 
    {
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setCorreo("xxxxxx");
        clienteLogic.createCliente(newEntity);
        
        newEntity.setCorreo("xxx@xxx");
        clienteLogic.createCliente(newEntity);
        
        newEntity.setCorreo("xxxx.xx");
        clienteLogic.createCliente(newEntity);
        
        newEntity.setCorreo(null);
        clienteLogic.createCliente(newEntity);
    }
    
    
    /**
     * Prueba crear un cliente con una contrasena no valida
     * @throws BusinessLogicException si no se puede crear el cliente
     */
    @Test(expected = BusinessLogicException.class)
    public void createClienteContrasenaInvalidaTest() throws BusinessLogicException 
    {
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setCorreo("xx@xxx.xx");
        newEntity.setContrasena("xx");
        clienteLogic.createCliente(newEntity);
        
        newEntity.setContrasena("xxxxxxxxx");
        clienteLogic.createCliente(newEntity);
        
        newEntity.setContrasena(null);
        clienteLogic.createCliente(newEntity);
    }
    
     /**
     * Prueba para consultar un cliente.
     */
    @Test
    public void getClienteTest() {
        ClienteEntity entity = data.get(0);
        ClienteEntity resultEntity = clienteLogic.getCliente(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getLogin(), resultEntity.getLogin());
    }
    
      /**
     * Prueba para consultar la lista de clientes.
     */
    @Test
    public void getClientesTest() {
        List<ClienteEntity> list = clienteLogic.getClientes();
        Assert.assertEquals(data.size(), list.size());
        for (ClienteEntity entity : list) {
            boolean found = false;
            for (ClienteEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para actualizar un cliente.
     */
    @Test
    public void setClienteTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setCorreo("xxx@xx.xx");
        pojoEntity.setContrasena("xxxxxxxx0909");
        clienteLogic.setCliente(pojoEntity.getId(), pojoEntity);
        ClienteEntity resp = em.find(ClienteEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getLogin(), resp.getLogin());
    }
    
    /**
     * Prueba para eliminar un cliente
     */
    @Test
    public void deleteClienteTest()  {
        ClienteEntity entity = data.get(0);
        clienteLogic.deleteCliente(entity.getId());
        ClienteEntity deleted = em.find(ClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
