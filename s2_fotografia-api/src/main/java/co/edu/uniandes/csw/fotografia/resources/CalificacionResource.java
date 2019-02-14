/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.CalificacionDTO;
import co.edu.uniandes.csw.fotografia.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.CalificacionLogic;
import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @calificacion a.trujilloa1
 */
@Path("/calificaciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CalificacionResource {
        private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());

    @Inject
    private CalificacionLogic calificacionLogic;

    /**
     * Crea un nuevo autor con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param calificacion {@link CalificacionDTO} - EL autor que se desea guardar.
     * @return JSON {@link CalificacionDTO} - El autor guardado con el atributo id
     * autogenerado.
     */
    @POST
    public CalificacionDTO createAuthor(CalificacionDTO calificacion) {
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: input: {0}", calificacion);
        CalificacionDTO calificacionDTO = new CalificacionDTO(calificacionLogic.createCalificacion(calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: output: {0}", calificacionDTO);
        return calificacionDTO;
    }

    /**
     * Busca y devuelve todos los autores que existen en la aplicacion.
     *
     * @return JSONArray {@link CalificacionDetailDTO} - Los autores encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CalificacionDetailDTO> getAuthors() {
        LOGGER.info("CalificacionResource getCalificaciones: input: void");
        List<CalificacionDetailDTO> listaCalificaciones = listEntity2DTO(calificacionLogic.getCalificaciones());
        LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: output: {0}", listaCalificaciones);
        return listaCalificaciones;
    }

    /**
     * Busca el autor con el id asociado recibido en la URL y lo devuelve.
     *
     * @param calificacionesId Identificador del autor que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link CalificacionDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{calificacionesId: \\d+}")
    public CalificacionDetailDTO getCalificacion(@PathParam("calificacionesId") Long calificacionesId) {
        LOGGER.log(Level.INFO, "CalificacionResource get: input: {0}", calificacionesId);
        CalificacionEntity calificacionEntity = calificacionLogic.getCalificacion(calificacionesId);
        if (calificacionEntity == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + " no existe.", 404);
        }
        CalificacionDetailDTO detailDTO = new CalificacionDetailDTO(calificacionEntity);
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param calificacionesId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param calificacion {@link CalificacionDetailDTO} El autor que se desea guardar.
     * @return JSON {@link CalificacionDetailDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{calificacionesId: \\d+}")
    public CalificacionDetailDTO updateAuthor(@PathParam("calificacionesId") Long calificacionesId, CalificacionDetailDTO calificacion) {
        LOGGER.log(Level.INFO, "CalificacionResource updateAuthor: input: calificacionesId: {0} , calificacion: {1}", new Object[]{calificacionesId, calificacion});
        calificacion.setId(calificacionesId);
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + " no existe.", 404);
        }
        CalificacionDetailDTO detailDTO = new CalificacionDetailDTO(calificacionLogic.updateCalificacion(calificacionesId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource updateAuthor: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el autor con el id asociado recibido en la URL.
     *
     * @param calificacionesId Identificador del autor que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el autor tiene libros asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
    @DELETE
    @Path("{calificacionesId: \\d+}")
    public void deleteAuthor(@PathParam("calificacionesId") Long calificacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource deleteAuthor: input: {0}", calificacionesId);
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + " no existe.", 404);
        }
        calificacionLogic.deleteCalificacion(calificacionesId);
        LOGGER.info("CalificacionResource deleteAuthor: output: void");
    }

    /**
     * Convierte una lista de AuthorEntity a una lista de CalificacionDetailDTO.
     *
     * @param entityList Lista de AuthorEntity a convertir.
     * @return Lista de CalificacionDetailDTO convertida.
     */
    private List<CalificacionDetailDTO> listEntity2DTO(List<CalificacionEntity> entityList) {
        List<CalificacionDetailDTO> list = new ArrayList<>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDetailDTO(entity));
        }
        return list;
    }
}
