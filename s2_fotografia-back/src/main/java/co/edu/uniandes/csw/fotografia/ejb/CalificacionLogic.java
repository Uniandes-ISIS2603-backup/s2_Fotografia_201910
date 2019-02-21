/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.CalificacionPersistence;
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

    
    
    
    /**
     * Se encarga de crear un Calificacion en la base de datos.
     *
     * @param calificacionEntity Objeto de CalificacionEntity con los datos nuevos
     * @return Objeto de CalificacionEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    public CalificacionEntity createCalificacion (CalificacionEntity calificacionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la calificacion");
        
        if(!persistence.verificarPuntaje(calificacionEntity.getPuntaje()))
        {
            throw new BusinessLogicException("El puntaje no esta bien definido" + calificacionEntity.getPuntaje());
        }
        else if (!persistence.verificarComentario(calificacionEntity.getComentario()))
        {
            throw new BusinessLogicException("El comentario no esta bien definido" + calificacionEntity.getComentario());
        }
        CalificacionEntity newCalificacionEntity = persistence.create(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la calificacion");
        return newCalificacionEntity;
    }

    /**
     * Obtiene la lista de los registros de Calificacion.
     *
     * @return Colección de objetos de CalificacionEntity.
     */
    public List<CalificacionEntity> getCalificaciones() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las calificaciones");
        List<CalificacionEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las calificaciones");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Calificacion a partir de su ID.
     *
     * @param calificacionId Identificador de la instancia a consultar
     * @return Instancia de CalificacionEntity con los datos del Calificacion consultado.
     */
    public CalificacionEntity getCalificacion(Long calificacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la calificacion con id = {0}", calificacionId);
        CalificacionEntity calificacionEntity = persistence.find(calificacionId);
        if (calificacionEntity == null) {
            LOGGER.log(Level.SEVERE, "La califion con el id = {0} no existe", calificacionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la calificacion con id = {0}", calificacionId);
        return calificacionEntity;
    }

    /**
     * Actualiza la información de una instancia de Calificacion.
     *
     * @param calificacionId Identificador de la instancia a actualizar
     * @param calificacionEntity Instancia de CalificacionEntity con los nuevos datos.
     * @return Instancia de CalificacionEntity con los datos actualizados.
     */
    public CalificacionEntity updateCalificacion(Long calificacionId, CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la calificacion con id = {0}", calificacionId);
        CalificacionEntity newCalificacionEntity = persistence.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la calificacion con id = {0}", calificacionId);
        return newCalificacionEntity;
    }

    /**
     * Elimina una instancia de Calificacion de la base de datos.
     *
     * @param calificacionId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el calificacion tiene libros asociados.
     */
    public void deleteCalificacion(Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificacion con id = {0}", calificacionId);
        persistence.delete(calificacionId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la calificacion con id = {0}", calificacionId);
    }
}
