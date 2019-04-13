/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.ConcursoFotografoLogic;
import co.edu.uniandes.csw.fotografia.ejb.FotografoLogic;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.persistence.ConcursoPersistence;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.ConcursoPersistence;
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
 * Pruebas de logica de la relacion Concurso - Fotografos
 *
 * @author n.rincond
 */
@RunWith(Arquillian.class)
public class ConcursoFotografosLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ConcursoFotografoLogic concursoFotografoLogic;

    @Inject
    private FotografoLogic fotografoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private ConcursoEntity concurso = new ConcursoEntity();
    private List<FotografoEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConcursoEntity.class.getPackage())
                .addPackage(FotografoEntity.class.getPackage())
                .addPackage(ConcursoFotografoLogic.class.getPackage())
                .addPackage(ConcursoPersistence.class.getPackage())
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
        em.createQuery("delete from ConcursoEntity").executeUpdate();
        em.createQuery("delete from FotografoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        concurso = factory.manufacturePojo(ConcursoEntity.class);
        concurso.setId(1L);
        concurso.setFotosEnConcurso(new ArrayList<>());
        em.persist(concurso);

        for (int i = 0; i < 3; i++) {
            FotografoEntity entity = factory.manufacturePojo(FotografoEntity.class);
            entity.setConcursos(new ArrayList<>());
            entity.getConcursos().add(concurso);
            em.persist(entity);
            data.add(entity);
            concurso.getFotografos().add(entity);
        }
    }

    /**
     * Prueba para asociar un concurso a un foto.
     *
     *
     * @throws BusinessLogicException
     */
    @Test
    public void addFotografoTest() throws BusinessLogicException {
        FotografoEntity newFotografo = factory.manufacturePojo(FotografoEntity.class);
        fotografoLogic.createFotografo(newFotografo);
        FotografoEntity fotografoEntity = concursoFotografoLogic.addFotografo(concurso.getId(), newFotografo.getId());
        Assert.assertNotNull(fotografoEntity);

        Assert.assertEquals(fotografoEntity.getId(), newFotografo.getId());
        Assert.assertEquals(fotografoEntity.getNombre(), newFotografo.getNombre());
        Assert.assertEquals(fotografoEntity.getApellido(), newFotografo.getApellido());
        Assert.assertEquals(fotografoEntity.getCorreo(), newFotografo.getCorreo());
        Assert.assertEquals(fotografoEntity.getEdad(), newFotografo.getEdad());

        FotografoEntity lastFotografo = concursoFotografoLogic.getFotografo(concurso.getId(), newFotografo.getId());

        Assert.assertEquals(lastFotografo.getId(), newFotografo.getId());
        Assert.assertEquals(lastFotografo.getNombre(), newFotografo.getNombre());
        Assert.assertEquals(lastFotografo.getApellido(), newFotografo.getApellido());
        Assert.assertEquals(lastFotografo.getCorreo(), newFotografo.getCorreo());
        Assert.assertEquals(lastFotografo.getEdad(), newFotografo.getEdad());
    }

    /**
     * Prueba para consultar la lista de Books de un concurso.
     */
    @Test
    public void getFotografosTest() {
        List<FotografoEntity> fotografoEntities = concursoFotografoLogic.getFotografos(concurso.getId());

        Assert.assertEquals(data.size(), fotografoEntities.size());

        for (int i = 0; i < data.size(); i++) {
            Assert.assertTrue(fotografoEntities.contains(data.get(0)));
        }
    }

    /**
     * Prueba para cpnsultar una fotografo de un concurso.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getFotografoTest() throws BusinessLogicException {
        FotografoEntity fotografoEntity = data.get(0);
        FotografoEntity fotografo = concursoFotografoLogic.getFotografo(concurso.getId(), fotografoEntity.getId());
        Assert.assertNotNull(fotografo);

        Assert.assertEquals(fotografoEntity.getId(), fotografo.getId());
        Assert.assertEquals(fotografoEntity.getNombre(), fotografo.getNombre());
        Assert.assertEquals(fotografoEntity.getApellido(), fotografo.getApellido());
        Assert.assertEquals(fotografoEntity.getCorreo(), fotografo.getCorreo());
        Assert.assertEquals(fotografoEntity.getEdad(), fotografo.getEdad());
    }

    /**
     * Prueba para actualizar los fotografos  de un concurso.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void replaceFotografoTest() throws BusinessLogicException {
        List<FotografoEntity> nuevaLista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FotografoEntity entity = factory.manufacturePojo(FotografoEntity.class);
            entity.setConcursos(new ArrayList<>());
            entity.getConcursos().add(concurso);
            fotografoLogic.createFotografo(entity);
            nuevaLista.add(entity);
        }
        concursoFotografoLogic.replaceFotografos(concurso.getId(), nuevaLista);
        List<FotografoEntity> fotografoEntities = concursoFotografoLogic.getFotografos(concurso.getId());
        for (FotografoEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(fotografoEntities.contains(aNuevaLista));
        }
    }

    /**
     * Prueba desasociar una fotografo con un concurso.
     *
     */
    @Test
    public void removeFotografoTest() {
        for (FotografoEntity fotografo : data) {
            concursoFotografoLogic.removeFotografo(concurso.getId(), fotografo.getId());
        }
        Assert.assertTrue(concursoFotografoLogic.getFotografos(concurso.getId()).isEmpty());
    }
}
