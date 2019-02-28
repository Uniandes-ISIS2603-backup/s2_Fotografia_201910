/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.RondaEntity;
import co.edu.uniandes.csw.fotografia.persistence.RondaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author nicolas melendez
 */

@Stateless
public class RondaLogic {
     private static final Logger LOGGER = Logger.getLogger(OrganizadorLogic.class.getName());

    @Inject
    private RondaPersistence persistence;

    /**
     * Se encarga de crear un Organizador en la base de datos.
     *
     * @param rondaEntity Objeto de RondaEntity con los datos nuevos
     * @return Objeto de RondaEntity con los datos nuevos y su ID.
     */
    public RondaEntity createRonda(RondaEntity rondaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del ronda");
        RondaEntity newRondaEntity = persistence.create(rondaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del ronda");
        return newRondaEntity;
    }

    /**
     * Obtiene los datos de una instancia de Ronda a partir de su ID.
     *
     * @param rondasId Identificador de la instancia a consultar
     * @return Instancia de RondaEntity con los datos del Ronda consultado.
     */
    public RondaEntity getRonda(Long rondasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el ronda con id = {0}", rondasId);
        RondaEntity rondaEntity = persistence.find(rondasId);
        if (rondaEntity == null) {
            LOGGER.log(Level.SEVERE, "La ronda con el id = {0} no existe", rondasId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el ronda con id = {0}", rondasId);
        return rondaEntity;
    }

}
