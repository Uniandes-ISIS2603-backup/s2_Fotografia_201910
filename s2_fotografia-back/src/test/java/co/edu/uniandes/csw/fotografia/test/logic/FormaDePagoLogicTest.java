/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;


import co.edu.uniandes.csw.fotografia.ejb.FormaDePagoLogic;
import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.FormaDePagoPersistence;

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
public class FormaDePagoLogicTest 
{
     private PodamFactory factory = new PodamFactoryImpl();
   
   @Inject
    private FormaDePagoLogic formaDePagoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FormaDePagoEntity> data = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FormaDePagoEntity.class.getPackage())
                .addPackage(FormaDePagoLogic.class.getPackage())
                .addPackage(FormaDePagoPersistence.class.getPackage())
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
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            FormaDePagoEntity formaDePago = factory.manufacturePojo(FormaDePagoEntity.class);
            em.persist(formaDePago);
            data.add(formaDePago);
        }
    }
    
     /**
     * Prueba el metodo de crearFormaDePago de la clase FormaDePagoLogic
     * @throws BusinessLogicException si no se puede crear la forma de pago
     */
     @Test
    public void createFormaDePagoTest() throws BusinessLogicException 
    {
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
 
        FormaDePagoEntity result = formaDePagoLogic.createFactura(newEntity);
        Assert.assertNotNull(result);
        FormaDePagoEntity entity = em.find(FormaDePagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    
     /**
     * Prueba para consultar una forma de pago
     */
    @Test
    public void getFacturaTest() {
        FormaDePagoEntity entity = data.get(0);
        FormaDePagoEntity resultEntity = formaDePagoLogic.getFormaDePago(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }
    
      /**
     * Prueba para consultar la lista de formas de pago.
     */
    @Test
    public void getFacturasTest() {
        List<FormaDePagoEntity> list = formaDePagoLogic.getFormasDePago();
        Assert.assertEquals(data.size(), list.size());
        for (FormaDePagoEntity entity : list) {
            boolean found = false;
            for (FormaDePagoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para actualizar una forma de pago.
     */
    @Test
    public void setFacturaTest() throws BusinessLogicException {
        FormaDePagoEntity entity = data.get(0);
        FormaDePagoEntity pojoEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        pojoEntity.setId(entity.getId());
       
        formaDePagoLogic.setFormaDePago(pojoEntity.getId(), pojoEntity);
        FormaDePagoEntity resp = em.find(FormaDePagoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }
    
    /**
     * Prueba para eliminar una forma de pago
     */
    @Test
    public void deleteClienteTest()  {
        FormaDePagoEntity entity = data.get(0);
        formaDePagoLogic.deleteFormaDePago(entity.getId());
        FormaDePagoEntity deleted = em.find(FormaDePagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
