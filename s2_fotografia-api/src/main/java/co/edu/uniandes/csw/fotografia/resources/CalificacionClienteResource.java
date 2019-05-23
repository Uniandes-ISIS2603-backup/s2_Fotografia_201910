/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.ClienteDTO;
import co.edu.uniandes.csw.fotografia.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.fotografia.dtos.FotografoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.CalificacionClienteLogic;
import co.edu.uniandes.csw.fotografia.ejb.CalificacionLogic;
import co.edu.uniandes.csw.fotografia.ejb.ClienteLogic;
import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
 * @author a.trujilloa1
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalificacionClienteResource {
     private static final Logger LOGGER = Logger.getLogger(CalificacionClienteResource.class.getName());

    @Inject
    private CalificacionClienteLogic calificacionClienteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ClienteLogic clienteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

     /**
     * Guarda un author dentro de un premio con la informacion que recibe el la
     * URL.
     *
     * @param calificacionesId Identificador de el premio que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param clientesId Identificador del autor que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AuthorDTO} - El autor guardado en el premio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{clientesId: \\d+}")
    public ClienteDTO addAuthor(@PathParam("photosId")Long photoId, @PathParam("calificacionesId") Long calificacionesId, @PathParam("clientesId") Long clientesId) {
        LOGGER.log(Level.INFO, "PrizeAuthorResource addAuthor: input: prizesID: {0} , clientesId: {1}", new Object[]{calificacionesId, clientesId});
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /authors/" + clientesId + " no existe.", 404);
        }
        ClienteDTO clienteDTO = new ClienteDTO(calificacionClienteLogic.addCliente(photoId, calificacionesId,clientesId));
        LOGGER.log(Level.INFO, "PrizeAuthorResource addAuthor: output: {0}", clienteDTO);
        return clienteDTO;
    }
    
     /**
     * Busca y devuelve el autor con el ID recibido en la URL, relativo a un
     * libro.
     *
     * @param calificacionesId El ID del autor que se busca
     * @param photosId El ID del libro del cual se busca el autor
     * @return {@link FotografoDetailDTO} - El autor encontrado en el libro.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{clientesId: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("photosId") Long photosId, @PathParam("calificacionesId") Long calificacionesId, Long clienteId) {
        LOGGER.log(Level.INFO, "InteresFotograficoFotografosResource getFotografo: input: photosId {0} , calificacionesId {1}", new Object[]{photosId, calificacionesId});
        ClienteEntity clienteEntity = calificacionClienteLogic.getCliente(photosId,calificacionesId);
        if (clienteEntity == null) {
            throw new WebApplicationException("El recurso /prizes/" + calificacionesId + "/author no existe.", 404);
        }
        ClienteDetailDTO detailDTO = new ClienteDetailDTO(calificacionClienteLogic.getCliente(photosId, calificacionesId));
        LOGGER.log(Level.INFO, "InteresFotograficoFotografosResource getFotografo: output: {0}", detailDTO);
        return detailDTO;
    }
    
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
    public ClienteDetailDTO replaceCliente(@PathParam("photosId")Long photoId,@PathParam("calificacionesId") Long calificacionesId, @PathParam("clientesId")Long clienteId) {
       if (clienteLogic.getCliente(clienteId) == null) {
            throw new WebApplicationException("El recurso /calificacion/" + calificacionesId + " no existe.", 404);
        }
        ClienteDetailDTO clienteDetailDTO = new ClienteDetailDTO(calificacionClienteLogic.replaceCliente(photoId,calificacionesId, clienteId));
        LOGGER.log(Level.INFO, "BookClienteResource replaceCliente: output: {0}", clienteDetailDTO);
        return clienteDetailDTO;
    }
    
}
