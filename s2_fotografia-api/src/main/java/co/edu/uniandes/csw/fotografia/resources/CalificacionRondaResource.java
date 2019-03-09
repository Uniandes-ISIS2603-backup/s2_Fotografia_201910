/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.fotografia.dtos.RondaDTO;
import co.edu.uniandes.csw.fotografia.ejb.CalificacionLogic;
import co.edu.uniandes.csw.fotografia.ejb.CalificacionRondaLogic;
import co.edu.uniandes.csw.fotografia.ejb.RondaLogic;
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
@Path("rondaes/{rondaesId: \\d+}/ronda")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalificacionRondaResource {
    private static final Logger LOGGER = Logger.getLogger(CalificacionRondaResource.class.getName());

    @Inject
    private CalificacionLogic calificacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CalificacionRondaLogic rondaRondaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private RondaLogic rondaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de Ronda asociada a un Book.
     *
     * @param rondaesId Identificador del libro que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param ronda La ronda que se será del libro.
     * @return JSON {@link CalificacionDetailDTO} - El arreglo de libros guardado en la
     * ronda.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la ronda o el
     * libro.
     */
    @PUT
    public CalificacionDetailDTO replaceRonda(@PathParam("rondaesId") Long rondaesId, RondaDTO ronda) {
        LOGGER.log(Level.INFO, "BookRondaResource replaceRonda: input: rondaesId{0} , Ronda:{1}", new Object[]{rondaesId, ronda});
        if (calificacionLogic.getCalificacion(rondaesId) == null) {
            throw new WebApplicationException("El recurso /ronda/" + rondaesId + " no existe.", 404);
        }
        if (rondaLogic.getRonda(ronda.getId()) == null) {
            throw new WebApplicationException("El recurso /rondas/" + ronda.getId() + " no existe.", 404);
        }
        CalificacionDetailDTO rondaDetailDTO = new CalificacionDetailDTO(rondaRondaLogic.replaceRonda(rondaesId, ronda.getId()));
        LOGGER.log(Level.INFO, "BookRondaResource replaceRonda: output: {0}", rondaDetailDTO);
        return rondaDetailDTO;
    }
}
