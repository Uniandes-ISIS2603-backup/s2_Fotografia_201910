/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.CalificacionDTO;
import co.edu.uniandes.csw.fotografia.ejb.CalificacionLogic;
import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @calificacion a.trujilloa1
 */
@Produces("application/json")
@Consumes("application/json")
public class CalificacionResource {
        private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());

    @Inject
    private CalificacionLogic calificacionLogic;

    /**
     * Crea una nueva calificacion con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param photoId
     * @param calificacion {@link CalificacionDTO} - La calificacion que se desea guardar.
     * @return JSON {@link CalificacionDTO} - La calificacion guardada con el atributo id
     * autogenerado.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @POST
    public CalificacionDTO createCalificacion(@PathParam("photosId")Long photoId, CalificacionDTO calificacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: input: {0}", calificacion);
        CalificacionDTO calificacionDTO = new CalificacionDTO(calificacionLogic.createCalificacion(photoId,calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: output: {0}", calificacionDTO);
        return calificacionDTO;
    }

    /**
     * Busca y devuelve todas las calificaciones que existen en la aplicacion.
     *
     * @param photoId
     * @return JSONArray {@link CalificacionDetailDTO} - Las calificaciones encontradas en la
     * aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<CalificacionDTO> getCalificaciones(@PathParam("photosId") Long photoId) {
        LOGGER.info("CalificacionResource getCalificaciones: input: void");
        List<CalificacionDTO> listaCalificaciones = listEntity2DTO(calificacionLogic.getCalificaciones(photoId));
        LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: output: {0}", listaCalificaciones);
        return listaCalificaciones;
    }

    /**
     * Busca la calificacion con el id asociado recibido en la URL y la devuelve.
     *
     * @param photoId
     * @param calificacionesId Identificador de la calificacion que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link CalificacionDetailDTO} - La calificacion buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @GET
    @Path("{calificacionesId: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("photosId") Long photoId, @PathParam("calificacionesId") Long calificacionesId) {
        LOGGER.log(Level.INFO, "CalificacionResource get: input: {0}", calificacionesId);
        CalificacionEntity calificacionEntity = calificacionLogic.getCalificacion(photoId, calificacionesId);
        if (calificacionEntity == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionesId + " no existe.", 404);
        }
        CalificacionDTO detailDTO = new CalificacionDTO(calificacionEntity);
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la calificacion con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param photoId
     * @param calificacionesId Identificador de la calificacion que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param calificacion {@link CalificacionDetailDTO} La calificacion que se desea guardar.
     * @return JSON {@link CalificacionDetailDTO} - La calificacion guardada.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion a
     * actualizar.
     */
    @PUT
    @Path("{calificacionesId: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("photosId") Long photoId, @PathParam("calificacionesId") Long calificacionesId, CalificacionDTO calificacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: input: calificacionesId: {0} , calificacion: {1}", new Object[]{calificacionesId, calificacion});
        if (calificacionesId.equals(calificacion.getId())) {
            throw new BusinessLogicException("Los ids del Review no coinciden.");
        }
        CalificacionEntity entity = calificacionLogic.getCalificacion(photoId, calificacionesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + photoId + "/reviews/" + calificacionesId + " no existe.", 404);

        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(calificacionLogic.updateCalificacion(photoId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: output: {0}", calificacionDTO);
        return calificacionDTO;
    }

    /**
     * Borra el calificacion con el id asociado recibido en la URL.
     *
     * @param photoId
     * @param calificacionesId Identificador de la calificacion que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra la calificacion a borrar.
     */
    @DELETE
    @Path("{calificacionesId: \\d+}")
    public void deleteCalificacion(@PathParam("photosId") Long photoId, @PathParam("calificacionesId") Long calificacionesId) throws BusinessLogicException {
        CalificacionEntity entity = calificacionLogic.getCalificacion(photoId, calificacionesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /books/" + photoId + "/reviews/" + calificacionesId + " no existe.", 404);
        }
        calificacionLogic.deleteCalificacion(photoId, calificacionesId);
    }

    /**
     * Convierte una lista de CalificacionEntity a una lista de CalificacionDetailDTO.
     *
     * @param entityList Lista de CalificacionEntity a convertir.
     * @return Lista de CalificacionDetailDTO convertida.
     */
    private List<CalificacionDTO> listEntity2DTO(List<CalificacionEntity> entityList) {
        List<CalificacionDTO> list = new ArrayList<>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDTO(entity));
        }
        return list;
    }
}
