/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.persistence.ConcursoPersistence;
import co.edu.uniandes.csw.fotografia.persistence.PhotoPersistance;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *Implementa la conexión con la persistencia entre la relación Concurso y Photo
 * @author NicolasRinconD
 */
@Stateless
public class ConcursoPhotoLogic {
    private static final Logger LOGGER = Logger.getLogger(ConcursoPhotoLogic.class.getName());

    @Inject
    private ConcursoPersistence concursoPersistence;

    @Inject
    private PhotoPersistance photoPersistence;

    /**
     * Asocia un Photo existente a un Concurso
     *
     * @param concursosId Identificador de la instancia de Concurso
     * @param photosId Identificador de la instancia de Photo
     * @return Instancia de PhotoEntity que fue asociada a Concurso
     */
    public PhotoEntity addPhoto(Long concursosId, Long photosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un autor al libro con id = {0}", concursosId);
        PhotoEntity photoEntity = photoPersistence.find(photosId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        concursoEntity.getFotosEnConcurso().add(photoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un autor al libro con id = {0}", concursosId);
        return photoPersistence.find(photosId);
    }

    /**
     * Obtiene una colección de instancias de PhotoEntity asociadas a una
     * instancia de Concurso
     *
     * @param concursosId Identificador de la instancia de Concurso
     * @return Colección de instancias de PhotoEntity asociadas a la instancia
     * de Concurso
     */
    public List<PhotoEntity> getPhotos(Long concursosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores del libro con id = {0}", concursosId);
        return concursoPersistence.find(concursosId).getFotosEnConcurso();
    }

    /**
     * Obtiene una instancia de PhotoEntity asociada a una instancia de Concurso
     *
     * @param concursosId Identificador de la instancia de Concurso
     * @param photosId Identificador de la instancia de Photo
     * @return La entidad del Autor asociada al libro
     */
    public PhotoEntity getPhoto(Long concursosId, Long photosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", concursosId);
        List<PhotoEntity> photos = concursoPersistence.find(concursosId).getFotosEnConcurso();
        PhotoEntity photoEntity = photoPersistence.find(photosId);
        int index = photos.indexOf(photoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un autor del libro con id = {0}", concursosId);
        if (index >= 0) {
            return photos.get(index);
        }
        return null;
    }

    /**
     * Remplaza las instancias de Photo asociadas a una instancia de Concurso
     *
     * @param concursosId Identificador de la instancia de Concurso
     * @param list Colección de instancias de PhotoEntity a asociar a instancia
     * de Concurso
     * @return Nueva colección de PhotoEntity asociada a la instancia de Concurso
     */
    public List<PhotoEntity> replacePhotos(Long concursosId, List<PhotoEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los autores del libro con id = {0}", concursosId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        concursoEntity.setFotosEnConcurso(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los autores del libro con id = {0}", concursosId);
        return concursoPersistence.find(concursosId).getFotosEnConcurso();
    }

    /**
     * Desasocia un Photo existente de un Concurso existente
     *
     * @param concursosId Identificador de la instancia de Concurso
     * @param photosId Identificador de la instancia de Photo
     */
    public void removePhoto(Long concursosId, Long photosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor del libro con id = {0}", concursosId);
        PhotoEntity photoEntity = photoPersistence.find(photosId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        concursoEntity.getFotosEnConcurso().remove(photoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un autor del libro con id = {0}", concursosId);
    }
}
