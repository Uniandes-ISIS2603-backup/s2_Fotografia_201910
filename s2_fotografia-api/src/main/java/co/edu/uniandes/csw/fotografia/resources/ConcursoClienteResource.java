/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;
import co.edu.uniandes.csw.fotografia.dtos.ClienteDTO;
import co.edu.uniandes.csw.fotografia.ejb.ClienteLogic;
import co.edu.uniandes.csw.fotografia.ejb.ConcursoClienteLogic;
import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
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
 * Clase que implementa el recurso "concurso/{id}/cliente".
 *
 * @cliente ISIS2603
 * @version 1.0
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConcursoClienteResource {

    private static final Logger LOGGER = Logger.getLogger(ConcursoClienteResource.class.getName());

    @Inject
    private ConcursoClienteLogic concursoClienteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ClienteLogic clienteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un cliente dentro de un concurso con la informacion que recibe el la
     * URL.
     *
     * @param concursosId Identificador de el concurso que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param clientesId Identificador del cliente que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link ClienteDTO} - El cliente guardado en el concurso.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @POST
    @Path("{clientesId: \\d+}")
    public ClienteDTO addCliente(@PathParam("concursosId") Long concursosId, @PathParam("clientesId") Long clientesId) {
        LOGGER.log(Level.INFO, "ConcursoClienteResource addCliente: input: concursosID: {0} , clientesId: {1}", new Object[]{concursosId, clientesId});
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        ClienteDTO clienteDTO = new ClienteDTO(concursoClienteLogic.addCliente(clientesId, concursosId));
        LOGGER.log(Level.INFO, "ConcursoClienteResource addCliente: output: {0}", clienteDTO);
        return clienteDTO;
    }

    /**
     * Busca el cliente dentro de el concurso con id asociado.
     *
     * @param concursosId Identificador de el concurso que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link ClienteDTO} - El cliente buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el concurso no tiene cliente.
     */
    @GET
    public ClienteDTO getCliente(@PathParam("concursosId") Long concursosId) {
        LOGGER.log(Level.INFO, "ConcursoClienteResource getCliente: input: {0}", concursosId);
        ClienteEntity clienteEntity = concursoClienteLogic.getCliente(concursosId);
        if (clienteEntity == null) {
            throw new WebApplicationException("El recurso /concursos/" + concursosId + "/cliente no existe.", 404);
        }
        ClienteDTO clienteDTO = new ClienteDTO(clienteEntity);
        LOGGER.log(Level.INFO, "ConcursoClienteResource getCliente: output: {0}", clienteDTO);
        return clienteDTO;
    }

    /**
     * Remplaza la instancia de Cliente asociada a una instancia de Concurso
     *
     * @param concursosId Identificador de el concurso que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param clientesId Identificador de el cliente que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link ClienteDTO} - El cliente actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @PUT
    @Path("{clientesId: \\d+}")
    public ClienteDTO replaceCliente(@PathParam("concursosId") Long concursosId, @PathParam("clientesId") Long clientesId) {
        LOGGER.log(Level.INFO, "ConcursoClienteResource replaceCliente: input: concursosId: {0} , clientesId: {1}", new Object[]{concursosId, clientesId});
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        ClienteDTO clienteDTO = new ClienteDTO(concursoClienteLogic.replaceCliente(concursosId, clientesId));
        LOGGER.log(Level.INFO, "ConcursoClienteResource replaceCliente: output: {0}", clienteDTO);
        return clienteDTO;
    }

    /**
     * Elimina la conexión entre el cliente y el concurso recibido en la URL.
     *
     * @param concursosId El ID del concurso al cual se le va a desasociar el cliente
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando el concurso no tiene cliente.
     */
    @DELETE
    public void removeCliente(@PathParam("concursosId") Long concursosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ConcursoClienteResource removeCliente: input: {0}", concursosId);
        concursoClienteLogic.removeCliente(concursosId);
        LOGGER.info("ConcursoClienteResource removeCliente: output: void");
    }
}
