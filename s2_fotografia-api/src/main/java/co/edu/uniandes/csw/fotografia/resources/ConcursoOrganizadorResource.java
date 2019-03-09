/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.OrganizadorDTO;
import co.edu.uniandes.csw.fotografia.dtos.OrganizadorDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.OrganizadorLogic;
import co.edu.uniandes.csw.fotografia.ejb.ConcursoOrganizadorLogic;
import co.edu.uniandes.csw.fotografia.entities.OrganizadorEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.mappers.WebApplicationExceptionMapper;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "concurso/{id}/organizador".
 *
 * @author Nicolas Melendez
 * @version 1.0
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConcursoOrganizadorResource {

    private static final Logger LOGGER = Logger.getLogger(ConcursoOrganizadorResource.class.getName());

    @Inject
    private ConcursoOrganizadorLogic concursoOrganizadorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private OrganizadorLogic organizadorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un organizador dentro de un concurso con la informacion que recibe el la
     * URL.
     *
     * @param concursosId Identificador de el concurso que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param organizadorsId Identificador del organizador que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link OrganizadorDTO} - El organizador guardado en el concurso.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el organizador.
     */
    @POST
    @Path("{organizadorsId: \\d+}")
    public OrganizadorDTO addOrganizador(@PathParam("concursossId") Long concursosId, @PathParam("organizadorsId") Long organizadorsId) {
        LOGGER.log(Level.INFO, "ConcursoOrganizadorResource addOrganizador: input: concursosID: {0} , organizadorsId: {1}", new Object[]{concursosId, organizadorsId});
        if (organizadorLogic.getOrganizador(organizadorsId) == null) {
            throw new WebApplicationException("El recurso /organizadors/" + organizadorsId + " no existe.", 404);
        }
        OrganizadorDTO organizadorDTO = new OrganizadorDTO(concursoOrganizadorLogic.addOrganizador(organizadorsId, concursosId));
        LOGGER.log(Level.INFO, "ConcursoOrganizadorResource addOrganizador: output: {0}", organizadorDTO);
        return organizadorDTO;
    }

    /**
     * Busca el organizador dentro de el concurso con id asociado.
     *
     * @param concursosId Identificador de el concurso que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link OrganizadorDetailDTO} - El organizador buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el concurso no tiene organizador.
     */
    @GET
    public OrganizadorDetailDTO getOrganizador(@PathParam("organizadorsId") Long concursosId) {
        LOGGER.log(Level.INFO, "ConcursoOrganizadorResource getOrganizador: input: {0}", concursosId);
        OrganizadorEntity organizadorEntity = concursoOrganizadorLogic.getOrganizador(concursosId);
        if (organizadorEntity == null) {
            throw new WebApplicationException("El recurso /concursoss/" + concursosId + "/organizador no existe.", 404);
        }
        OrganizadorDetailDTO organizadorDetailDTO = new OrganizadorDetailDTO(organizadorEntity);
        LOGGER.log(Level.INFO, "ConcursoOrganizadorResource getOrganizador: output: {0}", organizadorDetailDTO);
        return organizadorDetailDTO;
    }

    /**
     * Remplaza la instancia de Organizador asociada a una instancia de Concurso
     *
     * @param concursosId Identificador de el concurso que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param organizadorsId Identificador de el organizador que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link OrganizadorDetailDTO} - El organizador actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el organizador.
     */
    @PUT
    @Path("{organizadorsId: \\d+}")
    public OrganizadorDetailDTO replaceOrganizador(@PathParam("concursosId") Long concursosId, @PathParam("organizadorsId") Long organizadorsId) {
        LOGGER.log(Level.INFO, "ConcursoOrganizadorResource replaceOrganizador: input: concursosId: {0} , organizadorsId: {1}", new Object[]{concursosId, organizadorsId});
        if (organizadorLogic.getOrganizador(organizadorsId) == null) {
            throw new WebApplicationException("El recurso /organizadors/" + organizadorsId + " no existe.", 404);
        }
        OrganizadorDetailDTO organizadorDetailDTO = new OrganizadorDetailDTO(concursoOrganizadorLogic.replaceOrganizador(concursosId, organizadorsId));
        LOGGER.log(Level.INFO, "ConcursoOrganizadorResource replaceOrganizador: output: {0}", organizadorDetailDTO);
        return organizadorDetailDTO;
    }

    /**
     * Elimina la conexión entre el organizador y el concurso recibido en la URL.
     *
     * @param concursosId El ID del premio al cual se le va a desasociar el autor
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando el concurso no tiene organizador.
     */
    @DELETE
    public void removeOrganizador(@PathParam("concursosId") Long concursosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ConcursoOrganizadorResource removeOrganizador: input: {0}", concursosId);
        concursoOrganizadorLogic.removeOrganizador(concursosId);
        LOGGER.info("ConcursoOrganizadorResource removeOrganizador: output: void");
    }
}
