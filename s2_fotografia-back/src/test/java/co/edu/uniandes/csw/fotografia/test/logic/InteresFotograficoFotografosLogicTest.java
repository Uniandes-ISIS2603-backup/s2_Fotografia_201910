/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.InteresFotograficoFotografosLogic;
import co.edu.uniandes.csw.fotografia.ejb.FotografoLogic;
import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.InteresFotograficoPersistence;
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
 * Pruebas de logica de la relacion InteresFotografico - Fotografos
 *
 * @author s.acostav
 */
@RunWith(Arquillian.class)
public class InteresFotograficoFotografosLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private InteresFotograficoFotografosLogic interesFotograficoFotografoLogic;

    @Inject
    private FotografoLogic fotografoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private InteresFotograficoEntity interesFotografico = new InteresFotograficoEntity();
    private List<FotografoEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InteresFotograficoEntity.class.getPackage())
                .addPackage(FotografoEntity.class.getPackage())
                .addPackage(InteresFotograficoFotografosLogic.class.getPackage())
                .addPackage(InteresFotograficoPersistence.class.getPackage())
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
       

        interesFotografico = factory.manufacturePojo(InteresFotograficoEntity.class);
        interesFotografico.setId(1L);
        interesFotografico.setFotografos(new ArrayList<>());
        em.persist(interesFotografico);

        for (int i = 0; i < 3; i++) {
            FotografoEntity entity = factory.manufacturePojo(FotografoEntity.class);
            entity.setIntereses(new ArrayList<>());
            entity.getIntereses().add(interesFotografico);
            em.persist(entity);
            data.add(entity);
            interesFotografico.getFotografos().add(entity);
        }
    }

    /**
     * Prueba para asociar un autor a un libro.
     *
     *
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @Test
    public void addFotografoTest() throws BusinessLogicException {
        FotografoEntity newFotografo = factory.manufacturePojo(FotografoEntity.class);
        fotografoLogic.createFotografo(newFotografo);
        FotografoEntity fotografoEntity = interesFotograficoFotografoLogic.addFotografo(interesFotografico.getId(), newFotografo.getId());
        Assert.assertNotNull(fotografoEntity);

        Assert.assertEquals(fotografoEntity.getId(), newFotografo.getId());
        Assert.assertEquals(fotografoEntity.getNombre(), newFotografo.getNombre());
        Assert.assertEquals(fotografoEntity.getCorreo(), newFotografo.getCorreo());
        Assert.assertEquals(fotografoEntity.getLogin(), newFotografo.getLogin());
        Assert.assertEquals(fotografoEntity.getTelefono(), newFotografo.getTelefono());

        FotografoEntity lastFotografo = interesFotograficoFotografoLogic.getFotografo(interesFotografico.getId(), newFotografo.getId());

        Assert.assertEquals(lastFotografo.getId(), newFotografo.getId());
        Assert.assertEquals(lastFotografo.getNombre(), newFotografo.getNombre());
        Assert.assertEquals(lastFotografo.getTelefono(), newFotografo.getTelefono());
        Assert.assertEquals(lastFotografo.getLogin(), newFotografo.getLogin());
        Assert.assertEquals(lastFotografo.getCorreo(), newFotografo.getCorreo());
    }

    /**
     * Prueba para consultar la lista de Fotografos de un autor.
     */
    @Test
    public void getFotografosTest() {
        List<FotografoEntity> fotografoEntities = interesFotograficoFotografoLogic.getFotografos(interesFotografico.getId());

        Assert.assertEquals(data.size(), fotografoEntities.size());

        for (int i = 0; i < data.size(); i++) {
            Assert.assertTrue(fotografoEntities.contains(data.get(0)));
        }
    }

    /**
     * Prueba para cpnsultar un libro de un autor.
     *
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @Test
    public void getFotografoTest() throws BusinessLogicException {
        FotografoEntity fotografoEntity = data.get(0);
        FotografoEntity fotografo = interesFotograficoFotografoLogic.getFotografo(interesFotografico.getId(), fotografoEntity.getId());
        Assert.assertNotNull(fotografo);

        Assert.assertEquals(fotografoEntity.getId(), fotografo.getId());
        Assert.assertEquals(fotografoEntity.getNombre(), fotografo.getNombre());
        Assert.assertEquals(fotografoEntity.getCorreo(), fotografo.getCorreo());
        Assert.assertEquals(fotografoEntity.getTelefono(), fotografo.getTelefono());
        Assert.assertEquals(fotografoEntity.getLogin(), fotografo.getLogin());
    }

    /**
     * Prueba para actualizar los libros de un autor.
     *
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @Test
    public void replaceFotografosTest() throws BusinessLogicException {
        List<FotografoEntity> nuevaLista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FotografoEntity entity = factory.manufacturePojo(FotografoEntity.class);
            entity.setIntereses(new ArrayList<>());
            entity.getIntereses().add(interesFotografico);
            fotografoLogic.createFotografo(entity);
            nuevaLista.add(entity);
        }
        interesFotograficoFotografoLogic.replaceFotografos(interesFotografico.getId(), nuevaLista);
        List<FotografoEntity> fotografoEntities = interesFotograficoFotografoLogic.getFotografos(interesFotografico.getId());
        for (FotografoEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(fotografoEntities.contains(aNuevaLista));
        }
    }

    /**
     * Prueba desasociar un libro con un autor.
     *
     */
    @Test
    public void removeFotografoTest() {
        for (FotografoEntity fotografo : data) {
            interesFotograficoFotografoLogic.removeFotografo(interesFotografico.getId(), fotografo.getId());
        }
        Assert.assertTrue(interesFotograficoFotografoLogic.getFotografos(interesFotografico.getId()).isEmpty());
    }
}
