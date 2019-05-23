/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.PhotoPersistance;
import co.edu.uniandes.csw.fotografia.persistence.FotografoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Fotografo y Photo.
 *
 * @author s.acostav
 */
@Stateless
public class FotografoPhotosLogic {

    private static final Logger LOGGER = Logger.getLogger(FotografoPhotosLogic.class.getName());

    @Inject
    private PhotoPersistance photoPersistence;

    @Inject
    private FotografoPersistence fotografoPersistence;

    /**
     * Agregar un photo a la fotografo
     *
     * @param photosId El id libro a guardar
     * @param fotografosId El id de la fotografo en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public PhotoEntity addPhoto(Long photosId, Long fotografosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la fotografo con id = {0}", fotografosId);
        FotografoEntity fotografoEntity = fotografoPersistence.find(fotografosId);
        PhotoEntity photoEntity = photoPersistence.find(photosId);
        fotografoEntity.getFotos().add(photoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la fotografo con id = {0}", fotografosId);
        return photoPersistence.find(photosId);
    }

    /**
     * Retorna todos los photos asociados a una fotografo
     *
     * @param fotografosId El ID de la fotografo buscada
     * @return La lista de libros de la fotografo
     */
    public List<PhotoEntity> getPhotos(Long fotografosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los libros asociados a la fotografo con id = {0}", fotografosId);
        return fotografoPersistence.find(fotografosId).getFotos();
    }

    /**
     * Retorna un photo asociado a una fotografo
     *
     * @param fotografosId El id de la fotografo a buscar.
     * @param photosId El id del libro a buscar
     * @return El libro encontrado dentro de la fotografo.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * fotografo
     */
    public PhotoEntity getPhoto(Long fotografosId, Long photosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la fotografo con id = " + fotografosId, photosId);
        List<PhotoEntity> photos = fotografoPersistence.find(fotografosId).getFotos();
        PhotoEntity photoEntity = photoPersistence.find(photosId);
        int index = photos.indexOf(photoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la fotografo con id = " + fotografosId, photosId);
        if (index >= 0) {
            return photos.get(index);
        }
        return null;
    }

    /**
     * Remplazar photos de una fotografo
     *
     * @param photos Lista de libros que serán los de la fotografo.
     * @param fotografosId El id de la fotografo que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<PhotoEntity> replacePhotos(Long fotografosId, List<PhotoEntity> photos) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la fotografo con id = {0}", fotografosId);
        FotografoEntity fotografoEntity = fotografoPersistence.find(fotografosId);
        fotografoEntity.setFotos(photos);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la fotografo con id = {0}", fotografosId);
        return photos;
    }
}
