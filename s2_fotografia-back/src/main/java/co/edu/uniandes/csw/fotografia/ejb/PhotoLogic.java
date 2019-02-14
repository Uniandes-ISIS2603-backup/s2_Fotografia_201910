/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.persistence.PhotoPersistance;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author da.benavides
 */
@Stateless
public class PhotoLogic {
    
     private static final Logger LOGGER = Logger.getLogger(PhotoLogic.class.getName());

    @Inject
    private PhotoPersistance persistence;
    
    public PhotoEntity createFotografo(PhotoEntity photo){
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la foto");
        PhotoEntity newPhotoEntity = persistence.create(photo);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la foto");
        return newPhotoEntity;
    }
    
    public PhotoEntity getFoto(long pId){
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la foto con id = {0}", pId);
        PhotoEntity photoEntity = persistence.find(pId);
        if (photoEntity == null) {
            LOGGER.log(Level.SEVERE, "La foto con el id = {0} no existe", pId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la foto con id = {0}", pId);
        return photoEntity;
    }
    
    public List<PhotoEntity> getFotos(){
       LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las fotos");
        List<PhotoEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las fotos");
        return lista;
    }
    
     public PhotoEntity updateFoto(Long fotoId, PhotoEntity entity){
       LOGGER.log(Level.INFO, "Inicia proceso de actualizar la foto con id = {0}", fotoId);
        PhotoEntity newPhotoEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la foto con id = {0}", fotoId);
        return newPhotoEntity;
     }
     
     public void deleteFoto(Long fotoId){
     }
    
    
}
