/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.FotografoInteresesFotograficosLogic;
import co.edu.uniandes.csw.fotografia.ejb.FotografoLogic;
import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.FotografoPersistence;
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
 * Pruebas de logica de la relacion Fotografo - InteresesFotograficos
 *
 * @author s.acostav
 */
@RunWith(Arquillian.class)
public class FotografoInteresesFotograficosLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FotografoLogic fotografoLogic;
    @Inject
    private FotografoInteresesFotograficosLogic fotografoInteresesFotograficosLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FotografoEntity> data = new ArrayList<FotografoEntity>();

    private List<InteresFotograficoEntity> interesesFotograficosData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FotografoEntity.class.getPackage())
                .addPackage(FotografoLogic.class.getPackage())
                .addPackage(FotografoPersistence.class.getPackage())
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
        em.createQuery("delete from InteresFotograficoEntity").executeUpdate();
        em.createQuery("delete from FotografoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            InteresFotograficoEntity interesesFotograficos = factory.manufacturePojo(InteresFotograficoEntity.class);
            em.persist(interesesFotograficos);
            interesesFotograficosData.add(interesesFotograficos);
        }
        for (int i = 0; i < 3; i++) {
            FotografoEntity entity = factory.manufacturePojo(FotografoEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                interesesFotograficosData.get(i).setFotografos(data);
            }
        }
    }

    /**
     * Prueba para asociar un InteresesFotograficos existente a un Fotografo.
     */
    @Test
    public void addInteresesFotograficosTest() {
        FotografoEntity entity = data.get(0);
        InteresFotograficoEntity interesFotograficoEntity = interesesFotograficosData.get(1);
        InteresFotograficoEntity response = fotografoInteresesFotograficosLogic.addInteresFotografico(interesFotograficoEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(interesFotograficoEntity.getInteres(), response.getInteres());
    }

    /**
     * Prueba para obtener una colección de instancias de InteresesFotograficos asociadas a una
     * instancia Fotografo.
     */
    @Test
    public void getInteresesFotograficosTest() {
        List<InteresFotograficoEntity> list = fotografoInteresesFotograficosLogic.getInteresesFotograficos(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }

    /**
     * Prueba para obtener una instancia de InteresesFotograficos asociada a una instancia
     * Fotografo.
     *
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @Test
    public void getInteresFotograficoTest() throws BusinessLogicException {
        FotografoEntity entity = data.get(0);
        InteresFotograficoEntity interesFotograficoEntity = interesesFotograficosData.get(0);
        InteresFotograficoEntity response = fotografoInteresesFotograficosLogic.getInteresFotografico(entity.getId(), interesFotograficoEntity.getId());

        Assert.assertEquals(interesFotograficoEntity.getId(), response.getId());
        Assert.assertEquals(interesFotograficoEntity.getInteres(), response.getInteres());}

    /**
     * Prueba para obtener una instancia de InteresesFotograficos asociada a una instancia
     * Fotografo que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getInteresFotograficoNoAsociadoTest() throws BusinessLogicException {
        FotografoEntity entity = data.get(0);
        InteresFotograficoEntity interesFotograficoEntity = interesesFotograficosData.get(1);
        fotografoInteresesFotograficosLogic.getInteresFotografico(entity.getId(), interesFotograficoEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de InteresesFotograficos asociadas a una instancia
     * de Fotografo.
     */
    @Test
     public  void replaceInteresesFotograficosTest() {
        FotografoEntity entity = data.get(0);
        List<InteresFotograficoEntity> list = interesesFotograficosData.subList(1, 3);
        fotografoInteresesFotograficosLogic.replaceInteresesFotograficos(entity.getId(), list);

        entity = fotografoLogic.getFotografo(entity.getId());
        Assert.assertFalse(entity.getIntereses().contains(interesesFotograficosData.get(0)));
        Assert.assertTrue(entity.getIntereses().contains(interesesFotograficosData.get(1)));
        Assert.assertTrue(entity.getIntereses().contains(interesesFotograficosData.get(2)));
    }
}

