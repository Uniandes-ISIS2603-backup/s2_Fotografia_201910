/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.JuradoPersistence;
import co.edu.uniandes.csw.fotografia.persistence.PhotoPersistance;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author a.trujilloa1
 */
@Stateless
public class JuradoPhotosLogic {
     private static final Logger LOGGER = Logger.getLogger(JuradoPhotosLogic.class.getName());

    @Inject
    private PhotoPersistance photoPersistence;

    @Inject
    private JuradoPersistence juradoPersistence;

    /**
     * Asocia un Photo existente a un Jurado
     *
     * @param juradosId Identificador de la instancia de Jurado
     * @param photosId Identificador de la instancia de Photo
     * @return Instancia de PhotoEntity que fue asociada a Jurado
     */
    public PhotoEntity addPhoto(Long juradosId, Long photosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un photo al jurado con id = {0}", juradosId);
        JuradoEntity juradoEntity = juradoPersistence.find(juradosId);
        PhotoEntity photoEntity = photoPersistence.find(photosId);
        //photoEntity.getJurados().add(juradoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un photo al jurado con id = {0}", juradosId);
        return photoPersistence.find(photosId);
    }

    /**
     * Obtiene una colección de instancias de PhotoEntity asociadas a una
     * instancia de Jurado
     *
     * @param juradosId Identificador de la instancia de Jurado
     * @return Colección de instancias de PhotoEntity asociadas a la instancia de
     * Jurado
     */
    public List<PhotoEntity> getPhotos(Long juradosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los photos del jurado con id = {0}", juradosId);
        return juradoPersistence.find(juradosId).getFotos();
    }

    /**
     * Obtiene una instancia de PhotoEntity asociada a una instancia de Jurado
     *
     * @param juradosId Identificador de la instancia de Jurado
     * @param photosId Identificador de la instancia de Photo
     * @return La entidadd de Photo del jurado
     * @throws BusinessLogicException Si el photo no está asociado al jurado
     */
    public PhotoEntity getPhoto(Long juradosId, Long photosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el photo con id = {0} del jurado con id = " + juradosId, photosId);
        List<PhotoEntity> photos = juradoPersistence.find(juradosId).getFotos();
        PhotoEntity photoEntity = photoPersistence.find(photosId);
        int index = photos.indexOf(photoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el photo con id = {0} del jurado con id = " + juradosId, photosId);
        if (index >= 0) {
            return photos.get(index);
        }
        throw new BusinessLogicException("El photo no está asociado al jurado");
    }

    /**
     * Remplaza las instancias de Photo asociadas a una instancia de Jurado
     *
     * @param authorId Identificador de la instancia de Jurado
     * @param photos Colección de instancias de PhotoEntity a asociar a instancia
     * de Jurado
     * @return Nueva colección de PhotoEntity asociada a la instancia de Jurado
     */
    public List<PhotoEntity> replacePhotos(Long authorId, List<PhotoEntity> photos) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los photos asocidos al author con id = {0}", authorId);
        JuradoEntity juradoEntity = juradoPersistence.find(authorId);
        List<PhotoEntity> photoList = photoPersistence.findAll();
        for (PhotoEntity photo : photoList) {
            if (photos.contains(photo)) {
                //if (!photo.getJurados().contains(juradoEntity)) {
                    //photo.getJurados().add(juradoEntity);
                }
            } //else {
                //photo.getJurados().remove(juradoEntity);
            //}
        //}
        juradoEntity.setFotos(photos);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los photos asocidos al author con id = {0}", authorId);
        return juradoEntity.getFotos();
    }

    /**
     * Desasocia un Photo existente de un Jurado existente
     *
     * @param juradosId Identificador de la instancia de Jurado
     * @param photosId Identificador de la instancia de Photo
     */
    public void removePhoto(Long juradosId, Long photosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un photo del author con id = {0}", juradosId);
        JuradoEntity juradoEntity = juradoPersistence.find(juradosId);
        PhotoEntity photoEntity = photoPersistence.find(photosId);
        //photoEntity.getJurados().remove(juradoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un photo del author con id = {0}", juradosId);
    }
}
