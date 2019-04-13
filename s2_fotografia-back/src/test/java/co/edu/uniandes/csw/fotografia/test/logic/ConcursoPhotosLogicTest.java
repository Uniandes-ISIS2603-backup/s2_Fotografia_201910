/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.ConcursoPhotoLogic;
import co.edu.uniandes.csw.fotografia.ejb.PhotoLogic;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
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
 * Pruebas de logica de la relacion Concurso - Photos
 *
 * @author n.rincond
 */
@RunWith(Arquillian.class)
public class ConcursoPhotosLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ConcursoPhotoLogic concursoPhotoLogic;

    @Inject
    private PhotoLogic photoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private ConcursoEntity concurso = new ConcursoEntity();
    private List<PhotoEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ConcursoEntity.class.getPackage())
                .addPackage(PhotoEntity.class.getPackage())
                .addPackage(ConcursoPhotoLogic.class.getPackage())
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
        em.createQuery("delete from PhotoEntity").executeUpdate();
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
            PhotoEntity entity = factory.manufacturePojo(PhotoEntity.class);
            entity.setConcursos(new ArrayList<>());
            entity.getConcursos().add(concurso);
            em.persist(entity);
            data.add(entity);
            concurso.getFotosEnConcurso().add(entity);
        }
    }

    /**
     * Prueba para asociar un concurso a un foto.
     *
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void addPhotoTest() throws BusinessLogicException {
        PhotoEntity newPhoto = factory.manufacturePojo(PhotoEntity.class);
        photoLogic.createPhoto(newPhoto);
        PhotoEntity photoEntity = concursoPhotoLogic.addPhoto(concurso.getId(), newPhoto.getId());
        Assert.assertNotNull(photoEntity);

        Assert.assertEquals(photoEntity.getId(), newPhoto.getId());
        Assert.assertEquals(photoEntity.getNombre(), newPhoto.getNombre());
        Assert.assertEquals(photoEntity.getDescription(), newPhoto.getDescription());
        Assert.assertEquals(photoEntity.getPrice(), newPhoto.getPrice());
        Assert.assertEquals(photoEntity.getRutaFoto(), newPhoto.getRutaFoto());

        PhotoEntity lastPhoto = concursoPhotoLogic.getPhoto(concurso.getId(), newPhoto.getId());

        Assert.assertEquals(lastPhoto.getId(), newPhoto.getId());
        Assert.assertEquals(lastPhoto.getNombre(), newPhoto.getNombre());
        Assert.assertEquals(lastPhoto.getDescription(), newPhoto.getDescription());
        Assert.assertEquals(lastPhoto.getPrice(), newPhoto.getPrice());
        Assert.assertEquals(lastPhoto.getRutaFoto(), newPhoto.getRutaFoto());
    }

    /**
     * Prueba para consultar la lista de fotos de un concurso.
     */
    @Test
    public void getPhotosTest() {
        List<PhotoEntity> photoEntities = concursoPhotoLogic.getPhotos(concurso.getId());

        Assert.assertEquals(data.size(), photoEntities.size());

        for (int i = 0; i < data.size(); i++) {
            Assert.assertTrue(photoEntities.contains(data.get(0)));
        }
    }

    /**
     * Prueba para cpnsultar una foto de un concurso.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getPhotoTest() throws BusinessLogicException {
        PhotoEntity photoEntity = data.get(0);
        PhotoEntity photo = concursoPhotoLogic.getPhoto(concurso.getId(), photoEntity.getId());
        Assert.assertNotNull(photo);

        Assert.assertEquals(photoEntity.getId(), photo.getId());
        Assert.assertEquals(photoEntity.getNombre(), photo.getNombre());
        Assert.assertEquals(photoEntity.getDescription(), photo.getDescription());
        Assert.assertEquals(photoEntity.getPrice(), photo.getPrice());
        Assert.assertEquals(photoEntity.getRutaFoto(), photo.getRutaFoto());
    }

    /**
     * Prueba para actualizar los fotos de un concurso.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void replacePhotoTest() throws BusinessLogicException {
        List<PhotoEntity> nuevaLista = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PhotoEntity entity = factory.manufacturePojo(PhotoEntity.class);
            entity.setConcursos(new ArrayList<>());
            entity.getConcursos().add(concurso);
            photoLogic.createPhoto(entity);
            nuevaLista.add(entity);
        }
        concursoPhotoLogic.replacePhotos(concurso.getId(), nuevaLista);
        List<PhotoEntity> photoEntities = concursoPhotoLogic.getPhotos(concurso.getId());
        for (PhotoEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(photoEntities.contains(aNuevaLista));
        }
    }

    /**
     * Prueba desasociar una foto con un concurso.
     *
     */
    @Test
    public void removePhotoTest() {
        for (PhotoEntity photo : data) {
            concursoPhotoLogic.removePhoto(concurso.getId(), photo.getId());
        }
        Assert.assertTrue(concursoPhotoLogic.getPhotos(concurso.getId()).isEmpty());
    }
}
