/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.RondaEntity;
import co.edu.uniandes.csw.fotografia.persistence.RondaPersistence;
import java.util.List;
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
    public RondaEntity createOrganizador(RondaEntity rondaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del ronda");
        RondaEntity newRondaEntity = persistence.create(rondaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del ronda");
        return newRondaEntity;
    }

    /**
     * Obtiene la lista de los registros de Ronda.
     *
     * @return Colección de objetos de RondaEntity.
     */
    public List<RondaEntity> getRondas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los rondas");
        List<RondaEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los rondas");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Ronda a partir de su ID.
     *
     * @param rondasId Identificador de la instancia a consultar
     * @return Instancia de RondaEntity con los datos del Ronda consultado.
     */
    public RondaEntity getRonda(Long rondassId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el ronda con id = {0}", rondassId);
        RondaEntity rondaEntity = persistence.find(rondassId);
        if (rondaEntity == null) {
            LOGGER.log(Level.SEVERE, "La ronda con el id = {0} no existe", rondassId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el ronda con id = {0}", rondassId);
        return rondaEntity;
    }

    /**
     * Actualiza la información de una instancia de Ronda.
     *
     * @param rondasId Identificador de la instancia a actualizar
     * @param rondaEntity Instancia de OrganizadorEntity con los nuevos datos.
     * @return Instancia de OrganizadorEntity con los datos actualizados.
     */
    public RondaEntity updateOrganizador(Long rondasId, RondaEntity rondaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el organizador con id = {0}", rondasId);
        RondaEntity newRondaEntity = persistence.update(rondaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el organizador con id = {0}", rondasId);
        return newRondaEntity;
    }

    /**
     * Elimina una instancia de Ronda de la base de datos.
     *
     * @param rondasId Identificador de la instancia a eliminar.
     */
    public void deleteRonda(Long rondasId){
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el ronda con id = {0}", rondasId);
   
        persistence.delete(rondasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el ronda con id = {0}", rondasId);
    }
}
