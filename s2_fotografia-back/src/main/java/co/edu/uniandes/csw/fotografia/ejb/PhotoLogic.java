/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.PhotoPersistance;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author da.benavides
 */
@Stateless
public class PhotoLogic {
    
     private static final Logger LOGGER = Logger.getLogger(PhotoLogic.class.getName());

    @Inject
    private PhotoPersistance persistence;
    
    public PhotoEntity createPhoto(PhotoEntity photo) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la foto");
        Date today = new Date();      
        if(today.compareTo(photo.getDate()) < 0){
            throw new BusinessLogicException("La foto no puede ser creada con una fecha en el futuro");
        }
        if(photo.getName()==null){
            throw new BusinessLogicException("La foto tiene que tener un nombre.");
        }
        if(photo.getName().length() > 70){
            throw new BusinessLogicException("El nombre no puede tener más de 120 caracteres.");
        }
        
      //  if(photo.getFotografo.isInstanceOf(Fotografo.class))
      // {
      //      throw new BusinessLogicException("Solo un fotografo puede crear una foto.");
      //  }
        if(photo.getPrice() < 0 ){
            throw new BusinessLogicException("El precio tiene que ser positivo.");
        }
        if(photo.getDescription().length() > 300){
            throw new BusinessLogicException("La descripción no puede tener más de 300 caracteres.");
        }
        
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
     
     /**
      * Elimina la foto con el id ingresado por parametro
      * @param photoId el id de la factura a eliminar
      */
      public void deletePhoto(Long photoId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la factura con id = {0}", photoId);
        // El inyect de dependenciaspermite llamar al delete de la persistencia
        
        persistence.delete(photoId);
        
        LOGGER.log(Level.INFO, "Termina proceso de borrar la factura con id = {0}", photoId);
    }
     
    
}
