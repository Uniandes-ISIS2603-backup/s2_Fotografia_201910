/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.fotografia.persistence.JuradoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author a.trujilloa1
 */
@Stateless
public class CalificacionJuradosLogic {
    private static final Logger LOGGER = Logger.getLogger(CalificacionJuradosLogic.class.getName());

    @Inject
    private JuradoPersistence juradoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private CalificacionPersistence callificacionPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Agregar un jurado a un calficacion
     *
     * @param calificacionesId El id calficacion a guardar
     * @param juradosId El id del jurado al cual se le va a guardar el calficacion.
     * @return El calficacion que fue agregado al jurado.
     */
    public JuradoEntity addJurado(Long juradosId, Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar el jurado con id = {0} al calficacion con id = " + calificacionesId, juradosId);
        JuradoEntity juradoEntity = juradoPersistence.find(juradosId);
        CalificacionEntity calificacionEntity = callificacionPersistence.find(calificacionesId);
        calificacionEntity.setJuradoCalificador(juradoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el jurado con id = {0} al calficacion con id = " + calificacionesId, juradosId);
        return juradoPersistence.find(juradosId);
    }

    /**
     *
     * Obtener un calficacion por medio de su id y el de su jurado.
     *
     * @param calificacionesId id del calficacion a ser buscado.
     * @return el jurado solicitada por medio de su id.
     */
    public JuradoEntity getJurado(Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el jurado del calficacion con id = {0}", calificacionesId);
        JuradoEntity juradoEntity = callificacionPersistence.find(calificacionesId).getJuradoCalificador();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el jurado del calficacion con id = {0}", calificacionesId);
        return juradoEntity;
    }

    /**
     * Remplazar jurado de un calficacion
     *
     * @param calificacionesId el id del calficacion que se quiere actualizar.
     * @param juradosId El id del nuebo jurado asociado al calficacion.
     * @return el nuevo jurado asociado.
     */
    public CalificacionEntity replaceJurado(Long calificacionesId, Long juradosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el jurado del calficacion calficacion con id = {0}", calificacionesId);
        JuradoEntity juradoEntity = juradoPersistence.find(juradosId);
        CalificacionEntity calificacionEntity = callificacionPersistence.find(calificacionesId);
        calificacionEntity.setJuradoCalificador(juradoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el jurado con id = {0} al calficacion con id = " + calificacionesId, juradosId);
        return calificacionEntity;
    }

    /**
     * Borrar el jurado de un calficacion
     *
     * @param calificacionesId El calficacion que se desea borrar del jurado.
     * @throws BusinessLogicException si el calficacion no tiene jurado
     */
    public void removeJurado(Long calificacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el jurado del calficacion con id = {0}", calificacionesId);
        CalificacionEntity calificacionEntity = callificacionPersistence.find(calificacionesId);
        if (calificacionEntity.getJuradoCalificador()== null) {
            throw new BusinessLogicException("El calficacion no tiene jurado");
        }
        JuradoEntity juradoEntity = juradoPersistence.find(calificacionEntity.getJuradoCalificador().getId());
        calificacionEntity.setJuradoCalificador(null);
        juradoEntity.getCalificaciones().remove(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el jurado con id = {0} del calficacion con id = " + calificacionesId, juradoEntity.getId());
    }
}
