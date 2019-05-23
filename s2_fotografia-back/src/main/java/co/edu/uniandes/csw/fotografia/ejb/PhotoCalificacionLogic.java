/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
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
 * @author estudiante
 */
@Stateless
public class PhotoCalificacionLogic {
    
    private static final Logger LOGGER = Logger.getLogger(PhotoCalificacionLogic.class.getName());

    @Inject
    private PhotoPersistance photoPersistence;

    @Inject
    private CalificacionPersistence calificacionPersistence;

    /**
     * Agregar un photo a la calificacion
     *
     * @param photosId El id libro a guardar
     * @param calificacionId
     * @return El libro creado.
     */
    public CalificacionEntity addCalificacion(Long photosId, Long calificacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la calificacion con id = {0}", photosId);
        PhotoEntity photoEntity = photoPersistence.find(photosId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(photosId, calificacionId);
        /**photoEntity.setCalificacion(calificacionEntity);**/
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la calificacion con id = {0}", photosId);
        return calificacionEntity;
    }

    /**
     * Retorna todos los photos asociados a una calificacion
     *
     * @param photosId El ID de la calificacion buscada
     * @return La lista de libros de la calificacion
     */
    public List<CalificacionEntity> getCalificaciones(Long photosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los libros asociados a la calificacion con id = {0}", photosId);
        return photoPersistence.find(photosId).getCalificaciones();
    }

    /**
     * Retorna un photo asociado a una calificacion
     *
     * @param photosId El id de la calificacion a buscar.
     * @param photosId El id del libro a buscar
     * @return El libro encontrado dentro de la calificacion.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * calificacion
     */
    public CalificacionEntity getCalificacion(Long photosId, Long calificacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la calificacion con id = " + photosId, photosId);
        List<CalificacionEntity> calificaciones = photoPersistence.find(photosId).getCalificaciones();
        CalificacionEntity calificacionEntity = calificacionPersistence.find(photosId, calificacionesId);
        int index = calificaciones.indexOf(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la calificacion con id = " + photosId, photosId);
        if (index >= 0) {
            return calificaciones.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la calificacion");
    }

    /**
     * Remplazar photos de una calificacion
     *
     * @param photos Lista de libros que serán los de la calificacion.
     * @param photosId El id de la calificacion que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<CalificacionEntity> replaceCalificacions(Long photosId, List<CalificacionEntity> calificaciones) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la calificacion con id = {0}", photosId);
        PhotoEntity photoEntity = photoPersistence.find(photosId);
        List<CalificacionEntity> calificacionList = calificacionPersistence.findAll();
        for (CalificacionEntity calificacion : calificacionList) {
            if (calificaciones.contains(calificacion)) {
                /**photo.setCalificacion(calificacionEntity);
            } else if (photo.getCalificacion() != null && photo.getCalificacion().equals(calificacionEntity)) {
                photo.setCalificacion(null);
            }*/
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la calificacion con id = {0}", photosId);
        return calificaciones;
    }
    
}
