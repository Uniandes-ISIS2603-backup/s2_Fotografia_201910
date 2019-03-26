/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.ClienteFacturasLogic;
import co.edu.uniandes.csw.fotografia.ejb.ClienteLogic;
import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
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
public class ClienteFacturasLogicTest 
{
    

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ClienteLogic clienteLogic;
    @Inject
    private ClienteFacturasLogic clienteFacturasLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private ArrayList<ClienteEntity> data = new ArrayList<ClienteEntity>();

    private ArrayList<FacturaEntity> facturasData = new ArrayList();

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
        em.createQuery("delete from FacturaEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            FacturaEntity facturas = factory.manufacturePojo(FacturaEntity.class);
            em.persist(facturas);
            facturasData.add(facturas);
        }
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                facturasData.get(i).setCliente(entity);
            }
        }
    }

    /**
     * Prueba para asociar un Facturas existente a un Cliente.
     */
    @Test
    public void addFacturasTest() {
        ClienteEntity entity = data.get(0);
        FacturaEntity facturaEntity = facturasData.get(1);
        FacturaEntity response = clienteFacturasLogic.addFactura(facturaEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(facturaEntity.getId(), response.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de Facturas asociadas a una
     * instancia Cliente.
     */
    @Test
    public void getFacturasTest() {
        List<FacturaEntity> list = clienteFacturasLogic.getFacturas(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }

    /**
     * Prueba para obtener una instancia de Facturas asociada a una instancia
     * Cliente.
     *
     * @throws co.edu.uniandes.csw.facturastore.exceptions.BusinessLogicException
     */
    @Test
    public void getFacturaTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        FacturaEntity facturaEntity = facturasData.get(0);
        FacturaEntity response = clienteFacturasLogic.getFactura(entity.getId(), facturaEntity.getId());

        Assert.assertEquals(facturaEntity.getId(), response.getId());
        Assert.assertEquals(facturaEntity.getNumero(), response.getNumero());
        Assert.assertEquals(facturaEntity.getPrecio(), response.getPrecio());
    }

    /**
     * Prueba para obtener una instancia de Facturas asociada a una instancia
     * Cliente que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.facturastore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getFacturaNoAsociadoTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        FacturaEntity facturaEntity = facturasData.get(1);
        clienteFacturasLogic.getFactura(entity.getId(), facturaEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Facturas asociadas a una instancia
     * de Cliente.
     */
    @Test
    public void replaceFacturasTest() {
        ClienteEntity entity = data.get(0);
        List<FacturaEntity> list = facturasData.subList(1, 3);
        clienteFacturasLogic.replaceFacturas(entity.getId(), list);

        entity = clienteLogic.getCliente(entity.getId());
        Assert.assertFalse(entity.getFacturas().contains(facturasData.get(0)));
        Assert.assertTrue(entity.getFacturas().contains(facturasData.get(1)));
        Assert.assertTrue(entity.getFacturas().contains(facturasData.get(2)));
    }
}
