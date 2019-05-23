/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.ClienteDTO;
import co.edu.uniandes.csw.fotografia.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.ClienteLogic;
import co.edu.uniandes.csw.fotografia.ejb.FormaDePagoClientesLogic;
import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Valentina Duarte
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FormaDePagoClientesResource 
{
    private static final Logger LOGGER = Logger.getLogger(FormaDePagoClientesResource.class.getName());

    @Inject
    private FormaDePagoClientesLogic formaDePagoClientesLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ClienteLogic clienteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un cliente dentro de una forma de pago con la informacion que recibe el la URL.
     *
     * @param formasDePagoId Identificador de la forma de pago que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param clientesId Identificador del cliente que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link ClienteDTO} - El clinete guardado en la forma de pago.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @POST
    @Path("{clientesId: \\d+}")
    public ClienteDTO addCliente(@PathParam("formasDePagoId") Long formasDePagoId, @PathParam("clientesId") Long clientesId) {
        LOGGER.log(Level.INFO, "FormaDePagoClientesResource addCliente: input: formasDePagoId: {0} , clientesId: {1}", new Object[]{formasDePagoId, clientesId});
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        ClienteDTO clienteDTO = new ClienteDTO(formaDePagoClientesLogic.addCliente(clientesId, formasDePagoId));
        LOGGER.log(Level.INFO, "FormaDePagoClientesResource addCliente: output: {0}", clienteDTO);
        return clienteDTO;
    }

    /**
     * Busca el cliente dentro de la forma de pago con id asociado.
     *
     * @param formasDePagoId Identificador de la forma de pago que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link ClienteDetailDTO} - El cliente buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando la forma de pago no tiene cliente.
     */
    @GET
    public ClienteDetailDTO getCliente(@PathParam("formasDePagoId") Long formasDePagoId) {
        LOGGER.log(Level.INFO, "FormaDePagoClientesResource getCliente: input: {0}", formasDePagoId);
        ClienteEntity clienteEntity = formaDePagoClientesLogic.getCliente(formasDePagoId);
        if (clienteEntity == null) {
            throw new WebApplicationException("El recurso /formasDePago/" + formasDePagoId + "/cliente no existe.", 404);
        }
        ClienteDetailDTO clienteDetailDTO = new ClienteDetailDTO(clienteEntity);
        LOGGER.log(Level.INFO, "FormaDePagoClientesResource getCliente: output: {0}", clienteDetailDTO);
        return clienteDetailDTO;
    }

    /**
     * Remplaza la instancia de Cliente asociada a una instancia de FormaDePago
     *
     * @param formasDePagoId Identificador de la froma de pago que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param clientesId Identificador de el cliente que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - El cliente actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @PUT
    @Path("{clientesId: \\d+}")
    public ClienteDetailDTO replaceCliente(@PathParam("formasDePagoId") Long formasDePagoId, @PathParam("clientesId") Long clientesId) {
        LOGGER.log(Level.INFO, "FormaDePagoClientesResource replaceCliente: input: formasDePagoId: {0} , clientesId: {1}", new Object[]{formasDePagoId, clientesId});
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        ClienteDetailDTO clienteDetailDTO = new ClienteDetailDTO(formaDePagoClientesLogic.replaceCliente(formasDePagoId, clientesId));
        LOGGER.log(Level.INFO, "FormaDePAgoClientesResource replaceCliente: output: {0}", clienteDetailDTO);
        return clienteDetailDTO;
    }

    /**
     * Elimina la conexión entre el cliente y la forma de pago recibido en la URL.
     *
     * @param formasDePagoId El ID de la forma de pago a la cual se le va a desasociar el cliente
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando la froma de pago no tiene cliente.
     */
    @DELETE
    public void removCliente(@PathParam("formasDePagoId") Long formasDePagoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FormaDePagoClientesResource removeCliente: input: {0}", formasDePagoId);
        formaDePagoClientesLogic.removeCliente(formasDePagoId);
        LOGGER.info("FormaDePagoClientesResource removeCliente: output: void");
    }
    
 
}
