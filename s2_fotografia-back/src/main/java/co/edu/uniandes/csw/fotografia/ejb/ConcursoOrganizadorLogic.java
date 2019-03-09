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
import co.edu.uniandes.csw.fotografia.persistence.ConcursoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de concurso y organizador
 *
 * @author Nicolas Melendez
 */
@Stateless
public class ConcursoOrganizadorLogic {

    private static final Logger LOGGER = Logger.getLogger(ConcursoOrganizadorLogic.class.getName());

    @Inject
    private OrganizadorPersistence organizadorPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private ConcursoPersistence concursoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Agregar un organizador a un concurso
     *
     * @param concursosId El id concurso a guardar
     * @param organizadorsId El id del organizador al cual se le va a guardar el concurso.
     * @return El concurso que fue agregado al organizador.
     */
    public OrganizadorEntity addOrganizador(Long organizadorsId, Long concursosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar el organizador con id = {0} al concurso con id = " + concursosId, organizadorsId);
        OrganizadorEntity organizadorEntity = organizadorPersistence.find(organizadorsId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        concursoEntity.setOrganizador(organizadorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el organizador con id = {0} al concurso con id = " + concursosId, organizadorsId);
        return organizadorPersistence.find(organizadorsId);
    }

    /**
     *
     * Obtener un concurso por medio de su id y el de su organizador.
     *
     * @param concursosId id del concurso a ser buscado.
     * @return el organizador solicitada por medio de su id.
     */
    public OrganizadorEntity getOrganizador(Long concursosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el organizador del concurso con id = {0}", concursosId);
        OrganizadorEntity organizadorEntity = concursoPersistence.find(concursosId).getOrganizador();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el organizador del concurso con id = {0}", concursosId);
        return organizadorEntity;
    }

    /**
     * Remplazar organizador de un concurso
     *
     * @param concursosId el id del concurso que se quiere actualizar.
     * @param organizadorsId El id del nuebo organizador asociado al concurso.
     * @return el nuevo organizador asociado.
     */
    public OrganizadorEntity replaceOrganizador(Long concursosId, Long organizadorsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el organizador del  concurso con id = {0}", concursosId);
        OrganizadorEntity autorEntity = organizadorPersistence.find(organizadorsId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        concursoEntity.setOrganizador(autorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el organizador con id = {0} al concurso con id = " + concursosId, organizadorsId);
        return organizadorPersistence.find(organizadorsId);
    }

    /**
     * Borrar el organizador de un concurso
     *
     * @param concursosId El concurso que se desea borrar del organizador.
     * @throws BusinessLogicException si el concurso no tiene organizador
     */
    public void removeOrganizador(Long concursosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el organizador del concurso con id = {0}", concursosId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        if (concursoEntity.getOrganizador() == null) {
            throw new BusinessLogicException("El concurso no tiene organizador");
        }
        OrganizadorEntity organizadorEntity = organizadorPersistence.find(concursoEntity.getOrganizador().getId());
        concursoEntity.setOrganizador(null);
        organizadorEntity.getConcursos().remove(concursoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el organizador con id = {0} del concurso con id = " + concursosId, organizadorEntity.getId());
    }
}
