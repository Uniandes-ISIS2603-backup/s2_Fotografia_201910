/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.fotografia.dtos.ClienteDTO;
import co.edu.uniandes.csw.fotografia.ejb.CalificacionClienteLogic;
import co.edu.uniandes.csw.fotografia.ejb.CalificacionLogic;
import co.edu.uniandes.csw.fotografia.ejb.ClienteLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author a.trujilloa1
 */
@Path("calificaciones/{calificacionesId: \\d+}/cliente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalificacionClienteResource {
     private static final Logger LOGGER = Logger.getLogger(CalificacionClienteResource.class.getName());

    @Inject
    private CalificacionLogic calificacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CalificacionClienteLogic calificacionClienteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ClienteLogic clienteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de Cliente asociada a un Book.
     *
     * @param calificacionesId Identificador del libro que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param calificacion La calificacion que se será del libro.
     * @return JSON {@link CalificacionDetailDTO} - El arreglo de libros guardado en la
     * calificacion.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion o el
     * libro.
     */
    @PUT
    public CalificacionDetailDTO replaceCliente(@PathParam("calificacionesId") Long calificacionesId, ClienteDTO calificacion) {
        LOGGER.log(Level.INFO, "BookClienteResource replaceCliente: input: calificacionesId{0} , Cliente:{1}", new Object[]{calificacionesId, calificacion});
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /calificacion/" + calificacionesId + " no existe.", 404);
        }
        if (clienteLogic.getCliente(calificacion.getId()) == null) {
            throw new WebApplicationException("El recurso /calificacions/" + calificacion.getId() + " no existe.", 404);
        }
        CalificacionDetailDTO calificacionDetailDTO = new CalificacionDetailDTO(calificacionClienteLogic.replaceCliente(calificacionesId, calificacion.getId()));
        LOGGER.log(Level.INFO, "BookClienteResource replaceCliente: output: {0}", calificacionDetailDTO);
        return calificacionDetailDTO;
    }
    
}
