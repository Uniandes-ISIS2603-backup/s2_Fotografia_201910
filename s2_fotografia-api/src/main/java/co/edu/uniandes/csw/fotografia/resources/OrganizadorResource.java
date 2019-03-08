/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.OrganizadorDTO;
import co.edu.uniandes.csw.fotografia.dtos.OrganizadorDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.OrganizadorLogic;
import co.edu.uniandes.csw.fotografia.entities.OrganizadorEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.mappers.WebApplicationExceptionMapper;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "organizadors".
 *
 * @author Nicolas Melendez
 * @version 1.0
 */
@Path("/organizadors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class OrganizadorResource {

    private static final Logger LOGGER = Logger.getLogger(OrganizadorResource.class.getName());

    @Inject
    private OrganizadorLogic organizadorLogic;

    /**
     * Crea un nuevo autor con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param organizador {@link OrganizadorDTO} - EL organizador que se desea guardar.
     * @return JSON {@link OrganizadorDTO} - El organizador guardado con el atributo id
     * autogenerado.
     */
    @POST
    public OrganizadorDTO createOrganizador(OrganizadorDTO organizador) {
        LOGGER.log(Level.INFO, "OrganizadorResource createOrganizador: input: {0}", organizador);
        OrganizadorDTO organizadorDTO = new OrganizadorDTO(organizadorLogic.createOrganizador(organizador.toEntity()));
        LOGGER.log(Level.INFO, "OrganizadorResource createOrganizador: output: {0}", organizadorDTO);
        return organizadorDTO;
    }

    /**
     * Busca y devuelve todos los organizadores que existen en la aplicacion.
     *
     * @return JSONArray {@link OrganizadorDetailDTO} - Los organizadores encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<OrganizadorDetailDTO> getOrganizadors() {
        LOGGER.info("OrganizadorResource getOrganizadors: input: void");
        List<OrganizadorDetailDTO> listaOrganizadors = listEntity2DTO(organizadorLogic.getOrganizadors());
        LOGGER.log(Level.INFO, "OrganizadorResource getOrganizadors: output: {0}", listaOrganizadors);
        return listaOrganizadors;
    }

    /**
     * Busca el organizador con el id asociado recibido en la URL y lo devuelve.
     *
     * @param organizadorsId Identificador del autor que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{organizadorsId: \\d+}")
    public OrganizadorDetailDTO getOrganizador(@PathParam("organizadorsId") Long organizadorsId) {
        LOGGER.log(Level.INFO, "OrganizadorResource getOrganizador: input: {0}", organizadorsId);
        OrganizadorEntity organizadorEntity = organizadorLogic.getOrganizador(organizadorsId);
        if (organizadorEntity == null) {
            throw new WebApplicationException("El recurso /organizadors/" + organizadorsId + " no existe.", 404);
        }
        OrganizadorDetailDTO detailDTO = new OrganizadorDetailDTO(organizadorEntity);
        LOGGER.log(Level.INFO, "OrganizadorResource getOrganizador: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el organizador con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param organizadorsId Identificador del organizador que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param organizador {@link OrganizadorDetailDTO} El organizador que se desea guardar.
     * @return JSON {@link OrganizadorDetailDTO} - El organizador guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el organizador a
     * actualizar.
     */
    @PUT
    @Path("{organizadorsId: \\d+}")
    public OrganizadorDetailDTO updateOrganizador(@PathParam("organizadorsId") Long organizadorsId, OrganizadorDetailDTO organizador) {
        LOGGER.log(Level.INFO, "OrganizadorResource updateOrganizador: input: organizadorsId: {0} , organizador: {1}", new Object[]{organizadorsId, organizador});
        organizador.setId(organizadorsId);
        if (organizadorLogic.getOrganizador(organizadorsId) == null) {
            throw new WebApplicationException("El recurso /organizadors/" + organizadorsId + " no existe.", 404);
        }
        OrganizadorDetailDTO detailDTO = new OrganizadorDetailDTO(organizadorLogic.updateOrganizador(organizadorsId, organizador.toEntity()));
        LOGGER.log(Level.INFO, "OrganizadorResource updateOrganizador: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el organizador con el id asociado recibido en la URL.
     *
     * @param organizadorsId Identificador del organizador que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     * si el organizador tiene concursos asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el organizador a borrar.
     */
    @DELETE
    @Path("{organizadorsId: \\d+}")
    public void deleteOrganizador(@PathParam("organizadorsId") Long organizadorsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "OrganizadorResource deleteOrganizador: input: {0}", organizadorsId);
        if (organizadorLogic.getOrganizador(organizadorsId) == null) {
            throw new WebApplicationException("El recurso /organizadors/" + organizadorsId + " no existe.", 404);
        }
        organizadorLogic.deleteOrganizador(organizadorsId);
        LOGGER.info("OrganizadorResource deleteOrganizador: output: void");
    }

    /**
     * Convierte una lista de OrganizadorEntity a una lista de OrganizadorDetailDTO.
     *
     * @param entityList Lista de OrganizadorEntity a convertir.
     * @return Lista de OrganizadorDetailDTO convertida.
     */
    private List<OrganizadorDetailDTO> listEntity2DTO(List<OrganizadorEntity> entityList) {
        List<OrganizadorDetailDTO> list = new ArrayList<>();
        for (OrganizadorEntity entity : entityList) {
            list.add(new OrganizadorDetailDTO(entity));
        }
        return list;
    }
}