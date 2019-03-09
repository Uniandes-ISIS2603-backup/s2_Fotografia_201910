/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.fotografia.dtos.JuradoDTO;
import co.edu.uniandes.csw.fotografia.ejb.CalificacionJuradosLogic;
import co.edu.uniandes.csw.fotografia.ejb.CalificacionLogic;
import co.edu.uniandes.csw.fotografia.ejb.JuradoLogic;
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
@Path("calificaciones/{calificacionesId: \\d+}/jurado")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalificacionJuradosResource {
    private static final Logger LOGGER = Logger.getLogger(CalificacionJuradosResource.class.getName());

    @Inject
    private CalificacionLogic calificacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CalificacionJuradosLogic calificacionJuradoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private JuradoLogic juradoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de Jurado asociada a un Book.
     *
     * @param calificacionesId Identificador del libro que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param jurado La jurado que se será del libro.
     * @return JSON {@link CalificacionDetailDTO} - El arreglo de libros guardado en la
     * jurado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la jurado o el
     * libro.
     */
    @PUT
    public CalificacionDetailDTO replaceJurado(@PathParam("calificacionesId") Long calificacionesId, JuradoDTO jurado) {
        LOGGER.log(Level.INFO, "BookJuradoResource replaceJurado: input: calificacionesId{0} , Jurado:{1}", new Object[]{calificacionesId, jurado});
        if (calificacionLogic.getCalificacion(calificacionesId) == null) {
            throw new WebApplicationException("El recurso /books/" + calificacionesId + " no existe.", 404);
        }
        if (juradoLogic.getJurado(jurado.getId()) == null) {
            throw new WebApplicationException("El recurso /jurados/" + jurado.getId() + " no existe.", 404);
        }
        CalificacionDetailDTO calificacionDetailDTO = new CalificacionDetailDTO(calificacionJuradoLogic.replaceJurado(calificacionesId, jurado.getId()));
        LOGGER.log(Level.INFO, "BookJuradoResource replaceJurado: output: {0}", calificacionDetailDTO);
        return calificacionDetailDTO;
    }
    
}
