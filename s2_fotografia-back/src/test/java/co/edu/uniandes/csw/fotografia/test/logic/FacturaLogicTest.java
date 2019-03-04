/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.FacturaLogic;
import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.FacturaPersistence;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class FacturaLogicTest 
{
    
    Calendar c = Calendar.getInstance();
    
    Date fecha = new Date(c.get(Calendar.YEAR)-1,8,20);
    
    private PodamFactory factory = new PodamFactoryImpl();
   
   @Inject
    private FacturaLogic facturaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FacturaEntity> data = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
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
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            FacturaEntity factura = factory.manufacturePojo(FacturaEntity.class);
            em.persist(factura);
            data.add(factura);
        }
    }
    
     /**
     * Prueba el metodo de crearFactura de la clase facturaLogic
     * @throws BusinessLogicException si no se puede crear la factura
     */
     @Test
    public void createFacturaTest() throws BusinessLogicException 
    {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setNumero(1);
        newEntity.setPrecio(1.0);
        newEntity.setFechaCompra(fecha);
        FacturaEntity result = facturaLogic.createFactura(newEntity);
        Assert.assertNotNull(result);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
    }
    
    /**
     * Prueba crear dos facturas con el mismo numero
     *
     * @throws BusinessLogicException si no se puede crear el cliente
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaConMismoNumeroTest() throws BusinessLogicException 
    {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setNumero(data.get(0).getNumero());
        facturaLogic.createFactura(newEntity);
    }
    
    /**
     * Prueba crear una factura con un numero no valido
     *
     * @throws BusinessLogicException si no se puede crear la factura
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaNumeroInvalidoTest() throws BusinessLogicException 
    {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setNumero(-11);
    
        newEntity.setPrecio(1.0);
        newEntity.setFechaCompra(fecha);
        facturaLogic.createFactura(newEntity);
        
        newEntity.setNumero(null);
        facturaLogic.createFactura(newEntity);
    }
    
    /**
     * Prueba crear una factura con un precio no valido
     *
     * @throws BusinessLogicException si no se puede crear la factura
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaPrecioInvalidoTest() throws BusinessLogicException 
    {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setNumero(1);
        newEntity.setFechaCompra(fecha);
        newEntity.setPrecio(-11.00);
        facturaLogic.createFactura(newEntity);
        
        newEntity.setPrecio(null);
        facturaLogic.createFactura(newEntity);
    }
    
    /**
     * Prueba crear una factura con una fecha no valida
     *
     * @throws BusinessLogicException si no se puede crear la factura
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaFechaInvalidaTest() throws BusinessLogicException 
    {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setNumero(1);
        newEntity.setPrecio(1.0);
        newEntity.setFechaCompra(new Date(c.get(Calendar.YEAR) +1,2,11));
        facturaLogic.createFactura(newEntity);
        
        newEntity.setFechaCompra(new Date(c.get(Calendar.YEAR),c.get(Calendar.MONTH)+1,11));
        facturaLogic.createFactura(newEntity);
        
        newEntity.setFechaCompra(new Date(c.get(Calendar.YEAR),c.get(Calendar.MONTH)+1,c.get(Calendar.DATE)+1));
        facturaLogic.createFactura(newEntity);
    }
    
     /**
     * Prueba para consultar una factura
     */
    @Test
    public void getFacturaTest() {
        FacturaEntity entity = data.get(0);
        FacturaEntity resultEntity = facturaLogic.getFactura(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNumero(), resultEntity.getNumero());
    }
    
      /**
     * Prueba para consultar la lista de facturas.
     */
    @Test
    public void getFacturasTest() {
        List<FacturaEntity> list = facturaLogic.getFacturas();
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity entity : list) {
            boolean found = false;
            for (FacturaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para actualizar una factura.
     */
    @Test
    public void setFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setNumero(12);
        pojoEntity.setPrecio(11.9);
        pojoEntity.setFechaCompra(fecha);
        facturaLogic.setFactura(pojoEntity.getId(), pojoEntity);
        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNumero(), resp.getNumero());
    }
    
    /**
     * Prueba para eliminar una factura
     */
    @Test
    public void deleteClienteTest()  {
        FacturaEntity entity = data.get(0);
        facturaLogic.deleteFactura(entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
