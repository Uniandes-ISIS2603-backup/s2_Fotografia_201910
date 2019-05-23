/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;



import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.persistence.InteresFotograficoPersistence;
import co.edu.uniandes.csw.fotografia.persistence.PhotoPersistance;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *Implementa la conexión con la persistencia entre la relación InteresFotografico y Photo
 * @author sacostav
 */
@Stateless
public class InteresFotograficoFotosLogic {
    private static final Logger LOGGER = Logger.getLogger(InteresFotograficoFotosLogic.class.getName());

    @Inject
    private InteresFotograficoPersistence interesFotograficoPersistence;

    @Inject
    private PhotoPersistance photoPersistence;

    /**
     * Asocia un Photo existente a un InteresFotografico
     *
     * @param interesFotograficosId Identificador de la instancia de InteresFotografico
     * @param photosId Identificador de la instancia de Photo
     * @return Instancia de PhotoEntity que fue asociada a InteresFotografico
     */
    public PhotoEntity addPhoto(Long interesFotograficosId, Long photosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un photo al interesFotografico con id = {0}", interesFotograficosId);
        PhotoEntity photoEntity = photoPersistence.find(photosId);
        InteresFotograficoEntity interesFotograficoEntity = interesFotograficoPersistence.find(interesFotograficosId);
        interesFotograficoEntity.getFotosInteres().add(photoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un photo al interesFotografico con id = {0}", interesFotograficosId);
        return photoPersistence.find(photosId);
    }

    /**
     * Obtiene una colección de instancias de PhotoEntity asociadas a una
     * instancia de InteresFotografico
     *
     * @param interesFotograficosId Identificador de la instancia de InteresFotografico
     * @return Colección de instancias de PhotoEntity asociadas a la instancia
     * de InteresFotografico
     */
    public List<PhotoEntity> getPhotos(Long interesFotograficosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los photos del interesFotografico con id = {0}", interesFotograficosId);
        return interesFotograficoPersistence.find(interesFotograficosId).getFotosInteres();
    }

    /**
     * Obtiene una instancia de PhotoEntity asociada a una instancia de InteresFotografico
     *
     * @param interesFotograficosId Identificador de la instancia de InteresFotografico
     * @param photosId Identificador de la instancia de Photo
     * @return La entidad del Autor asociada al interesFotografico
     */
    public PhotoEntity getPhoto(Long interesFotograficosId, Long photosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un photo del interesFotografico con id = {0}", interesFotograficosId);
        List<PhotoEntity> photos = interesFotograficoPersistence.find(interesFotograficosId).getFotosInteres();
        PhotoEntity photoEntity = photoPersistence.find(photosId);
        int index = photos.indexOf(photoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un photo del interesFotografico con id = {0}", interesFotograficosId);
        if (index >= 0) {
            return photos.get(index);
        }
        return null;
    }

    /**
     * Remplaza las instancias de Photo asociadas a una instancia de InteresFotografico
     *
     * @param interesFotograficosId Identificador de la instancia de InteresFotografico
     * @param list Colección de instancias de PhotoEntity a asociar a instancia
     * de InteresFotografico
     * @return Nueva colección de PhotoEntity asociada a la instancia de InteresFotografico
     */
    public List<PhotoEntity> replacePhotos(Long interesFotograficosId, List<PhotoEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los photos del interesFotografico con id = {0}", interesFotograficosId);
        InteresFotograficoEntity interesFotograficoEntity = interesFotograficoPersistence.find(interesFotograficosId);
        interesFotograficoEntity.setFotosInteres(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los photos del interesFotografico con id = {0}", interesFotograficosId);
        return interesFotograficoPersistence.find(interesFotograficosId).getFotosInteres();
    }

    /**
     * Desasocia un Photo existente de un InteresFotografico existente
     *
     * @param interesFotograficosId Identificador de la instancia de InteresFotografico
     * @param photosId Identificador de la instancia de Photo
     */
    public void removePhoto(Long interesFotograficosId, Long photosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un photo del interesFotografico con id = {0}", interesFotograficosId);
        PhotoEntity photoEntity = photoPersistence.find(photosId);
        InteresFotograficoEntity interesFotograficoEntity = interesFotograficoPersistence.find(interesFotograficosId);
        interesFotograficoEntity.getFotosInteres().remove(photoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un photo del interesFotografico con id = {0}", interesFotograficosId);
    }
}

