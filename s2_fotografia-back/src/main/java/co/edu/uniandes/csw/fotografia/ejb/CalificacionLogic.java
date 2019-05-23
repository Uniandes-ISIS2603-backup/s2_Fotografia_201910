/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.CalificacionPersistence;
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
public class CalificacionLogic {
     private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
     
    @Inject
    private CalificacionPersistence persistence;

    @Inject
    private PhotoPersistance photoPersistence;    
    
    /**
     * Se encarga de crear un Calificacion en la base de datos.
     *
     * @param photoId
     * @param calificacionEntity Objeto de CalificacionEntity con los datos nuevos
     * @return Objeto de CalificacionEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    public CalificacionEntity createCalificacion (Long photoId,CalificacionEntity calificacionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la calificacion");
        PhotoEntity photo = photoPersistence.find(photoId);
        calificacionEntity.setFotoCalificada(photo);
        if(!persistence.verificarPuntaje(calificacionEntity.getPuntaje()))
        {
            throw new BusinessLogicException("El puntaje no esta bien definido" + calificacionEntity.getPuntaje());
        }
        else if (!persistence.verificarComentario(calificacionEntity.getComentario()))
        {
            throw new BusinessLogicException("El comentario no esta bien definido" + calificacionEntity.getComentario());
        }
        persistence.create(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la calificacion");
        return calificacionEntity;
    }

    /**
     * Obtiene la lista de los registros de Calificacion.
     *
     * @param photoId
     * @return Colección de objetos de CalificacionEntity.
     */
    public List<CalificacionEntity> getCalificaciones(Long photoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las calificaciones");
        PhotoEntity photoEntity = photoPersistence.find(photoId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las calificaciones");
        return photoEntity.getCalificaciones();
    }

    /**
     * Obtiene los datos de una instancia de Calificacion a partir de su ID.
     *
     * @param photoId
     * @param calificacionId Identificador de la instancia a consultar
     * @return Instancia de CalificacionEntity con los datos del Calificacion consultado.
     */
    public CalificacionEntity getCalificacion(Long photoId,Long calificacionId) {
          LOGGER.log(Level.INFO, "Inicia proceso de consultar el review con id = {0} del libro con id = " + photoId, calificacionId);
        return persistence.find(photoId, calificacionId);
    }
    
    /**
     * Actualiza la información de una instancia de Calificacion.
     *
     * @param photoId
     * @param calificacionEntity Instancia de CalificacionEntity con los nuevos datos.
     * @return Instancia de CalificacionEntity con los datos actualizados.
     */
    public CalificacionEntity updateCalificacion(Long photoId, CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la calificacion con id = {0}", photoId);
        PhotoEntity photoEntity = photoPersistence.find(photoId);
        calificacionEntity.setFotoCalificada(photoEntity);
        persistence.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la calificacion con id = {0}", photoId);
        return calificacionEntity;
    }

    /**
     * Elimina una instancia de Calificacion de la base de datos.
     *
     * @param photoId
     * @param calificacionId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el calificacion tiene libros asociados.
     */
    public void deleteCalificacion(Long photoId,Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificacion con id = {0}", calificacionId);
        CalificacionEntity old = getCalificacion(photoId, calificacionId);
        if (old == null) {
            throw new BusinessLogicException("El review con id = " + calificacionId + " no esta asociado a el libro con id = " + photoId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la calificacion con id = {0}", calificacionId);
    }
}
