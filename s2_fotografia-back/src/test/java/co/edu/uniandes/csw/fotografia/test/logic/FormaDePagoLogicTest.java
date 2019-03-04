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
public class FormaDePagoLogicTest {

    Calendar c = Calendar.getInstance();
    Date fecha = new Date(c.get(Calendar.YEAR)+1,8,20);
    
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

            //formaDePago.setNumeroTarjeta(Long.valueOf("1234333998763455")+i);
           // formaDePago.setNumeroVerificacion(543);
            //formaDePago.setFechaVencimiento(new Date(2022, 7, 18));
           // formaDePago.setTipoDeTarjeta(FormaDePagoLogic.TARJETACREDITO);
           // formaDePago.setTipoTarjetaDeCredito(FormaDePagoLogic.MASTERCARD);
            em.persist(formaDePago);
            data.add(formaDePago);
        }
    }

    /**
     * Prueba el metodo de crearFormaDePago de la clase FormaDePagoLogic
     *
     * @throws BusinessLogicException si no se puede crear la forma de pago
     */
    @Test
    public void createFormaDePagoTest() throws BusinessLogicException {
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);

        newEntity.setNumeroTarjeta(Long.valueOf("1234333221123455"));
        newEntity.setNumeroVerificacion(234);
        newEntity.setFechaVencimiento(fecha);
        newEntity.setTipoDeTarjeta(FormaDePagoLogic.TARJETACREDITO);
        newEntity.setTipoTarjetaDeCredito(FormaDePagoLogic.VISA);

        FormaDePagoEntity result = formaDePagoLogic.createFormaDePago(newEntity);
        Assert.assertNotNull(result);
        FormaDePagoEntity entity = em.find(FormaDePagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNumeroTarjeta(), entity.getNumeroTarjeta());
    }

    /**
     * Prueba crear dos formas de pago con el mismo numero
     *
     * @throws BusinessLogicException si no se puede crear la forma de pago
     */
    @Test(expected = BusinessLogicException.class)
    public void createFormaDePagoConMismoNumeroTest() throws BusinessLogicException {
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        newEntity.setNumeroTarjeta(data.get(0).getNumeroTarjeta());
        formaDePagoLogic.createFormaDePago(newEntity);
    }

    /**
     * Prueba crear una forma de pago con numero null
     *
     * @throws BusinessLogicException si no se puede crear la forma de pago
     */
    @Test(expected = BusinessLogicException.class)
    public void createFormaDePagoConNumeroNullTest() throws BusinessLogicException {
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        newEntity.setNumeroTarjeta(null);
        newEntity.setNumeroVerificacion(234);
        newEntity.setFechaVencimiento(fecha);
        newEntity.setTipoDeTarjeta(FormaDePagoLogic.TARJETACREDITO);
        newEntity.setTipoTarjetaDeCredito(FormaDePagoLogic.VISA);
        formaDePagoLogic.createFormaDePago(newEntity);

    }

    /**
     * Prueba crear una forma de pago con numero invalido
     *
     * @throws BusinessLogicException si no se puede crear la forma de pago
     */
    @Test(expected = BusinessLogicException.class)
    public void createFormaDePagoConNumeroInvalidoTest() throws BusinessLogicException {
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        newEntity.setNumeroTarjeta(Long.valueOf("23456"));
        newEntity.setNumeroVerificacion(234);
        newEntity.setFechaVencimiento(fecha);
        newEntity.setTipoDeTarjeta(FormaDePagoLogic.TARJETACREDITO);
        newEntity.setTipoTarjetaDeCredito(FormaDePagoLogic.VISA);
        formaDePagoLogic.createFormaDePago(newEntity);

        newEntity.setNumeroTarjeta(Long.valueOf("2345678908764321234"));
        formaDePagoLogic.createFormaDePago(newEntity);

    }

    /**
     * Prueba crear una forma de pago con numero de verificacion invalido
     *
     * @throws BusinessLogicException si no se puede crear la forma de pago
     */
    @Test(expected = BusinessLogicException.class)
    public void createFormaDePagoConNumeroVerificacionInvalidoTest() throws BusinessLogicException {
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        newEntity.setNumeroVerificacion(12);

        newEntity.setNumeroTarjeta(Long.valueOf("1234333221123455"));
        newEntity.setFechaVencimiento(fecha);
        newEntity.setTipoDeTarjeta(FormaDePagoLogic.TARJETACREDITO);
        newEntity.setTipoTarjetaDeCredito(FormaDePagoLogic.VISA);

        formaDePagoLogic.createFormaDePago(newEntity);

        newEntity.setNumeroVerificacion(13122);
        formaDePagoLogic.createFormaDePago(newEntity);

        newEntity.setNumeroVerificacion(-1234);
        formaDePagoLogic.createFormaDePago(newEntity);

    }

    /**
     * Prueba crear una forma de pago con fecha de vencimiento null
     *
     * @throws BusinessLogicException si no se puede crear la forma de pago
     */
    @Test(expected = BusinessLogicException.class)
    public void createFormaDePagoConFechaNullTest() throws BusinessLogicException {
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        newEntity.setFechaVencimiento(null);
        newEntity.setNumeroTarjeta(Long.valueOf("1234333221123455"));
        newEntity.setNumeroVerificacion(234);
        newEntity.setTipoDeTarjeta(FormaDePagoLogic.TARJETACREDITO);
        newEntity.setTipoTarjetaDeCredito(FormaDePagoLogic.VISA);
        formaDePagoLogic.createFormaDePago(newEntity);
    }

    /**
     * Prueba crear una forma de pago vencida
     *
     * @throws BusinessLogicException si no se puede crear la forma de pago
     */
    @Test(expected = BusinessLogicException.class)
    public void createFormaDePagoVencidaTest() throws BusinessLogicException {
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        newEntity.setFechaVencimiento(new Date(c.get(Calendar.YEAR) -1, 11, 12));

        newEntity.setNumeroTarjeta(Long.valueOf("1234333221123455"));
        newEntity.setNumeroVerificacion(234);
        newEntity.setTipoDeTarjeta(FormaDePagoLogic.TARJETACREDITO);
        newEntity.setTipoTarjetaDeCredito(FormaDePagoLogic.VISA);

        formaDePagoLogic.createFormaDePago(newEntity);

        newEntity.setFechaVencimiento(new Date(2019, 2, 22));
        formaDePagoLogic.createFormaDePago(newEntity);

    }

    /**
     * Prueba crear una forma de pago con tipo de tarjeta invalido
     *
     * @throws BusinessLogicException si no se puede crear la forma de pago
     */
    @Test(expected = BusinessLogicException.class)
    public void createFormaDePagoTipoTarjetaInvalidoTest() throws BusinessLogicException {
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        newEntity.setTipoDeTarjeta("PSE");
        newEntity.setNumeroTarjeta(Long.valueOf("1234333221123455"));
        newEntity.setNumeroVerificacion(234);
        newEntity.setFechaVencimiento(fecha);

        formaDePagoLogic.createFormaDePago(newEntity);
    }

    /**
     * Prueba crear una forma de pago con tipo de tarjeta de credito invalido
     *
     * @throws BusinessLogicException si no se puede crear la forma de pago
     */
    @Test(expected = BusinessLogicException.class)
    public void createFormaDePagoTipoTarjetaCreditoInvalidoTest() throws BusinessLogicException {
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        newEntity.setTipoDeTarjeta(FormaDePagoLogic.TARJETACREDITO);
        newEntity.setNumeroTarjeta(Long.valueOf("1234333221123455"));
        newEntity.setNumeroVerificacion(234);
        newEntity.setFechaVencimiento(fecha);
        newEntity.setTipoTarjetaDeCredito("American");
        formaDePagoLogic.createFormaDePago(newEntity);
    }

    /**
     * Prueba para consultar una forma de pago
     */
    @Test
    public void getFormaDePagoTest() {
        FormaDePagoEntity entity = data.get(0);
        FormaDePagoEntity resultEntity = formaDePagoLogic.getFormaDePago(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNumeroTarjeta(), resultEntity.getNumeroTarjeta());
    }

    /**
     * Prueba para consultar la lista de formas de pago.
     */
    @Test
    public void getFormasDePagoTest() {
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
    public void setFormasDePagoTest() throws BusinessLogicException {
        FormaDePagoEntity entity = data.get(0);
        FormaDePagoEntity pojoEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        
        pojoEntity.setId(entity.getId());
        pojoEntity.setNumeroTarjeta(Long.valueOf("1234333221123455"));
        pojoEntity.setNumeroVerificacion(234);
        pojoEntity.setFechaVencimiento(fecha);
        pojoEntity.setTipoDeTarjeta(FormaDePagoLogic.TARJETACREDITO);
        pojoEntity.setTipoTarjetaDeCredito(FormaDePagoLogic.VISA);
        
        formaDePagoLogic.setFormaDePago(pojoEntity.getId(), pojoEntity);
        FormaDePagoEntity resp = em.find(FormaDePagoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNumeroTarjeta(), resp.getNumeroTarjeta());
    }

    /**
     * Prueba para eliminar una forma de pago
     */
    @Test
    public void deleteClienteTest() {
        FormaDePagoEntity entity = data.get(0);
        formaDePagoLogic.deleteFormaDePago(entity.getId());
        FormaDePagoEntity deleted = em.find(FormaDePagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
