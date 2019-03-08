/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.RondaEntity;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.ConcursoPersistence;
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
    
    @Inject
    private ConcursoPersistence organizationPersistence;

    /**
     * Se encarga de crear un Organizador en la base de datos.
     *
     * @param rondaEntity Objeto de RondaEntity con los datos nuevos
     * @throws BusinessLogicException si la organizacion no existe o ya tiene
     * @return Objeto de RondaEntity con los datos nuevos y su ID.
     */
    public RondaEntity createRonda(RondaEntity rondaEntity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de ronda");
        if (rondaEntity.getConcurso() == null) {
            throw new BusinessLogicException("El Concurso es inválido");
        }
        ConcursoEntity concursoEntity = organizationPersistence.find(rondaEntity.getConcurso().getId());
        if (concursoEntity == null) {
            throw new BusinessLogicException("El concurso es inválido");
        }
        if (concursoEntity.getRonda() != null) {
            throw new BusinessLogicException("El concurso ya tiene ronda");
        }
        rondaEntity.setConcurso(concursoEntity);
        concursoEntity.setRonda(rondaEntity);
        rondaEntity = persistence.create(rondaEntity);
        LOGGER.info("Termina proceso de creación de ronda");
        return rondaEntity;
    }
    
    /**
     * Devuelve todos las rondas que hay en la base de datos.
     *
     * @return Lista de entidades de tipo ronda.
     */
    public List<RondaEntity> getRondas() {
        LOGGER.info("Inicia proceso de consultar todos las rondas");
        List<RondaEntity> prizes = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los rondas");
        return prizes;
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
    
    /**
     * Actualizar un ronda por ID
     *
     * @param rondasId El ID del ronda a actualizar
     * @param rondaEntity La entidad del ronda con los cambios deseados
     * @return La entidad del premio luego de actualizarla
     */
    public RondaEntity updateRonda(Long rondasId, RondaEntity rondaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar ronda con id = {0}", rondasId);
        RondaEntity newEntity = persistence.update(rondaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar ronda con id = {0}", rondaEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar una ronda por ID
     *
     * @param rondasId El ID del ronda a eliminar
     * @throws BusinessLogicException si el ronda tiene un concurso asociado.
     */
    public void deleteRonda(Long rondasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar ronda con id = {0}", rondasId);
        if (persistence.find(rondasId).getConcurso() != null) {
            throw new BusinessLogicException("No se puede borrar el concurso con id = " + rondasId + " porque tiene un concurso asociado");
        }
        persistence.delete(rondasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar ronda con id = {0}", rondasId);
    }

}
