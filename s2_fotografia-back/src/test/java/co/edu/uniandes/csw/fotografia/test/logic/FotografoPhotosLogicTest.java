/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.logic;

import co.edu.uniandes.csw.fotografia.ejb.FotografoPhotosLogic;
import co.edu.uniandes.csw.fotografia.ejb.FotografoLogic;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
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
 * Pruebas de logica de la relacion Fotografo - Photos
 *
 * @author s.acostav
 */
@RunWith(Arquillian.class)
public class FotografoPhotosLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FotografoLogic fotografoLogic;
    @Inject
    private FotografoPhotosLogic fotografoPhotosLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FotografoEntity> data = new ArrayList<FotografoEntity>();

    private List<PhotoEntity> photosData = new ArrayList();

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
        em.createQuery("delete from PhotoEntity").executeUpdate();
        em.createQuery("delete from FotografoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PhotoEntity photos = factory.manufacturePojo(PhotoEntity.class);
            em.persist(photos);
            photosData.add(photos);
        }
        for (int i = 0; i < 3; i++) {
            FotografoEntity entity = factory.manufacturePojo(FotografoEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
               /** photosData.get(i).setFotografos(data);**/
            }
        }
    }

    /**
     * Prueba para asociar un Photos existente a un Fotografo.
     */
    @Test
    public void addPhotosTest() {
        FotografoEntity entity = data.get(0);
        PhotoEntity photoEntity = photosData.get(1);
        PhotoEntity response = fotografoPhotosLogic.addPhoto(photoEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(photoEntity.getId(), response.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de Photos asociadas a una
     * instancia Fotografo.
     */
    @Test
    public void getPhotosTest() {
      /**  List<PhotoEntity> list = fotografoPhotosLogic.getPhotos(data.get(0).getId());

        Assert.assertEquals(1, list.size());
   **/ }

    /**
     * Prueba para obtener una instancia de Photos asociada a una instancia
     * Fotografo.
     *
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @Test
    public void getPhotoTest() throws BusinessLogicException {
       /** FotografoEntity entity = data.get(0);
        PhotoEntity photoEntity = photosData.get(0);
        PhotoEntity response = fotografoPhotosLogic.getPhoto(entity.getId(), photoEntity.getId());

        Assert.assertEquals(photoEntity.getId(), response.getId());
       Assert.assertEquals(photoEntity.getFotografos(), response.getFotografos());**/}

    /**
     * Prueba para obtener una instancia de Photos asociada a una instancia
     * Fotografo que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getPhotoNoAsociadoTest() throws BusinessLogicException {
        FotografoEntity entity = data.get(0);
        PhotoEntity photoEntity = photosData.get(1);
        fotografoPhotosLogic.getPhoto(entity.getId(), photoEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Photos asociadas a una instancia
     * de Fotografo.
     */
    @Test
     public  void replacePhotosTest() {
        /**FotografoEntity entity = data.get(0);
        List<PhotoEntity> list = photosData.subList(1, 3);
        fotografoPhotosLogic.replacePhotos(entity.getId(), list);

        entity = fotografoLogic.getFotografo(entity.getId());
        Assert.assertFalse(entity.getFotos().contains(photosData.get(0)));
        Assert.assertTrue(entity.getFotos().contains(photosData.get(1)));
        Assert.assertTrue(entity.getFotos().contains(photosData.get(2)));
  **/  }
     
     
}


