/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.OrganizadorEntity;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.OrganizadorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Organizador.
 *
 * @author Nicolas Melendez
 */
@Stateless
public class OrganizadorLogic {

    private static final Logger LOGGER = Logger.getLogger(OrganizadorLogic.class.getName());

    @Inject
    private OrganizadorPersistence persistence;

    /**
     * Se encarga de crear un Organizador en la base de datos.
     *
     * @param organizadorEntity Objeto de OrganizadorEntity con los datos nuevos
     * @return Objeto de OrganizadorEntity con los datos nuevos y su ID.
     */
    public OrganizadorEntity createOrganizador(OrganizadorEntity authorEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del organizador");
        OrganizadorEntity newOrganizadorEntity = persistence.create(authorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del organizador");
        return newOrganizadorEntity;
    }

    /**
     * Obtiene la lista de los registros de Organizador.
     *
     * @return Colección de objetos de OrganizadorEntity.
     */
    public List<OrganizadorEntity> getOrganizadors() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los organizadores");
        List<OrganizadorEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los organizadores");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Organizador a partir de su ID.
     *
     * @param organizadorsId Identificador de la instancia a consultar
     * @return Instancia de OrganizadorEntity con los datos del Organizador consultado.
     */
    public OrganizadorEntity getOrganizador(Long organizadorsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el organizador con id = {0}", organizadorsId);
        OrganizadorEntity organizadorEntity = persistence.find(organizadorsId);
        if (organizadorEntity == null) {
            LOGGER.log(Level.SEVERE, "La editorial con el id = {0} no existe", organizadorsId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el organizador con id = {0}", organizadorsId);
        return organizadorEntity;
    }

    /**
     * Actualiza la información de una instancia de Organizador.
     *
     * @param organizadorsId Identificador de la instancia a actualizar
     * @param organizadorEntity Instancia de OrganizadorEntity con los nuevos datos.
     * @return Instancia de OrganizadorEntity con los datos actualizados.
     */
    public OrganizadorEntity updateOrganizador(Long organizadorsId, OrganizadorEntity organizadorEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el organizador con id = {0}", organizadorsId);
        OrganizadorEntity newOrganizadorEntity = persistence.update(organizadorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el organizador con id = {0}", organizadorsId);
        return newOrganizadorEntity;
    }

    /**
     * Elimina una instancia de Organizador de la base de datos.
     *
     * @param organizadorsId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el organizador tiene concursos asociados.
     */
    public void deleteOrganizador(Long organizadorsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el organizador con id = {0}", organizadorsId);
        List<ConcursoEntity> concursos = getOrganizador(organizadorsId).getConcursos();
        if (concursos != null && !concursos.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el autor con id = " + organizadorsId + " porque tiene concursos asociados");
        }
   
        persistence.delete(organizadorsId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el organizador con id = {0}", organizadorsId);
    }
}
