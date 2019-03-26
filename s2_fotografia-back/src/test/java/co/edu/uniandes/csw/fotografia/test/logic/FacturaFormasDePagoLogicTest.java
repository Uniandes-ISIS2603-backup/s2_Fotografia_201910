/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.FacturaFormasDePagoLogic;
import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.FormaDePagoPersistence;
import java.util.ArrayList;
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
public class FacturaFormasDePagoLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FacturaFormasDePagoLogic facturaFormaDePagoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private ArrayList<FormaDePagoEntity> data = new ArrayList<FormaDePagoEntity>();

    private ArrayList<FacturaEntity> facturasData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FormaDePagoEntity.class.getPackage())
                .addPackage(FacturaFormasDePagoLogic.class.getPackage())
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
        em.createQuery("delete from FacturaEntity").executeUpdate();
        em.createQuery("delete from FormaDePagoEntity").executeUpdate();
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
            FormaDePagoEntity entity = factory.manufacturePojo(FormaDePagoEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                facturasData.get(i).setFormaDePagoFactura(entity);
            }
        }
    }

    /**
     * Prueba para asociar un Facturas existente a un FormaDePago.
     */
    @Test
    public void addFormaDePagoTest() {
        FormaDePagoEntity entity = data.get(0);
        FacturaEntity facturaEntity = facturasData.get(1);
        FormaDePagoEntity response = facturaFormaDePagoLogic.addFormaDePago(entity.getId(), facturaEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(entity.getId(), response.getId());
    }

    /**
     * Prueba para consultar un FormaDePago.
     */
    @Test
    public void getFormaDePagoTest() {
        FacturaEntity entity = facturasData.get(0);
        FormaDePagoEntity resultEntity = facturaFormaDePagoLogic.getFormaDePago(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getFormaDePagoFactura().getId(), resultEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Facturas asociadas a una instancia
     * de FormaDePago.
     */
    //@Test
   // public void replaceFormaDePagoTest() {
       // FormaDePagoEntity entity = data.get(0);
      //  facturaFormaDePagoLogic.replaceFormaDePago(facturasData.get(1).getId(), entity.getId());
      //  entity = facturaFormaDePagoLogic.getFormaDePago(facturasData.get(1).getId());
      //  Assert.assertTrue(entity.getFacturas().contains(facturasData.get(1)));
  //  }

    /**
     * Prueba para desasociar un Factura existente de un FormaDePago existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
   // @Test
    //public void removeFacturaTest() throws BusinessLogicException {
     //   facturaFormaDePagoLogic.removeFormaDePago(facturasData.get(0).getId());
     //   Assert.assertNull(facturaFormaDePagoLogic.getFormaDePago(facturasData.get(0).getId()));
   // }

    /**
     * Prueba para desasociar un Factura existente de un FormaDePago existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removeFacturaSinFormaDePagoTest() throws BusinessLogicException {
        facturaFormaDePagoLogic.removeFormaDePago(facturasData.get(1).getId());
    }
}
