/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.entities.RondaEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.fotografia.persistence.RondaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author a.trujilloa1
 */
@Stateless
public class CalificacionRondaLogic {
    private static final Logger LOGGER = Logger.getLogger(CalificacionRondaLogic.class.getName());

    @Inject
    private RondaPersistence rondaPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private CalificacionPersistence callificacionPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Agregar un ronda a un calficacion
     *
     * @param calificacionesId El id calficacion a guardar
     * @param rondaId El id del ronda al cual se le va a guardar el calficacion.
     * @return El calficacion que fue agregado al ronda.
     */
    public RondaEntity addRonda(Long rondaId, Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar el ronda con id = {0} al calficacion con id = " + calificacionesId, rondaId);
        RondaEntity rondaEntity = rondaPersistence.find(rondaId);
        CalificacionEntity calificacionEntity = callificacionPersistence.find(calificacionesId);
        calificacionEntity.setRondaCalificada(rondaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el ronda con id = {0} al calficacion con id = " + calificacionesId, rondaId);
        return rondaPersistence.find(rondaId);
    }

    /**
     *
     * Obtener un calficacion por medio de su id y el de su ronda.
     *
     * @param calificacionesId id del calficacion a ser buscado.
     * @return el ronda solicitada por medio de su id.
     */
    public RondaEntity getRonda(Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el ronda del calficacion con id = {0}", calificacionesId);
        RondaEntity rondaEntity = callificacionPersistence.find(calificacionesId).getRondaCalificada();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el ronda del calficacion con id = {0}", calificacionesId);
        return rondaEntity;
    }

    /**
     * Remplazar ronda de un calficacion
     *
     * @param calificacionesId el id del calficacion que se quiere actualizar.
     * @param rondaId El id del nuebo ronda asociado al calficacion.
     * @return el nuevo ronda asociado.
     */
    public CalificacionEntity replaceRonda(Long calificacionesId, Long rondaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el ronda del calficacion calficacion con id = {0}", calificacionesId);
        RondaEntity rondaEntity = rondaPersistence.find(rondaId);
        CalificacionEntity calificacionEntity = callificacionPersistence.find(calificacionesId);
        calificacionEntity.setRondaCalificada(rondaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el ronda con id = {0} al calficacion con id = " + calificacionesId, rondaId);
        return calificacionEntity;
    }

    /**
     * Borrar el ronda de un calficacion
     *
     * @param calificacionesId El calficacion que se desea borrar del ronda.
     * @throws BusinessLogicException si el calficacion no tiene ronda
     */
    public void removeRonda(Long calificacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el ronda del calficacion con id = {0}", calificacionesId);
        CalificacionEntity calificacionEntity = callificacionPersistence.find(calificacionesId);
        if (calificacionEntity.getRondaCalificada()== null) {
            throw new BusinessLogicException("El calficacion no tiene ronda");
        }
        RondaEntity rondaEntity = rondaPersistence.find(calificacionEntity.getRondaCalificada().getId());
        calificacionEntity.setRondaCalificada(null);
        rondaEntity.getCalificaciones().remove(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el ronda con id = {0} del calficacion con id = " + calificacionesId, rondaEntity.getId());
    }
    
}
