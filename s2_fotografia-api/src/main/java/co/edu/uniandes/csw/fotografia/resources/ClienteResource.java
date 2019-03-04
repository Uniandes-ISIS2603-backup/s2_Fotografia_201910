/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.ClienteDTO;
import co.edu.uniandes.csw.fotografia.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.ClienteLogic;
import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
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
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Valentina Duarte
 */
@Path ("clientes")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped

public class ClienteResource 
{
    private static final Logger LOGGER = Logger.getLogger(ClienteResource.class.getName());
    
    @Inject
    private ClienteLogic clienteLogic;
    
    /**
     * Crea el cliente que entra como parametro
     * @param cliente para ser creado
     * @return el cliente creado
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @POST
    public ClienteDTO createCliente (ClienteDTO cliente) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ClienteResource createCliente: input: {0}", cliente);
        ClienteDTO clienteDTO = new ClienteDTO(clienteLogic.createCliente(cliente.toEntity()));
        LOGGER.log(Level.INFO, "ClienteResource createCliente: output: {0}", clienteDTO);
        return clienteDTO;
    }
    
    /**
     * Busca el cliente con el id ingresado por parametro
     * @param clientesId
     * @return null
     */
    @GET
    @Path ("{login:\\d+}")
    public ClienteDetailDTO getCliente (@PathParam ("clientesId")Long clientesId)
    {
         LOGGER.log(Level.INFO, "ClienteResource getCliente: input: {0}", clientesId);
        ClienteEntity clienteEntity = clienteLogic.getCliente(clientesId);
        if (clienteEntity == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        ClienteDetailDTO detailDTO = new ClienteDetailDTO(clienteEntity);
        LOGGER.log(Level.INFO, "ClienteResource getCliente: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Convierte una lista de ClienteEntity a una lista de ClienteDetailDTO.
     *
     * @param entityList Lista de ClienteEntity a convertir.
     * @return Lista de ClienteDetailDTO convertida.
     */
    private List<ClienteDetailDTO> listEntityADTO(List<ClienteEntity> listaEntity) {
        List<ClienteDetailDTO> list = new ArrayList<>();
        for (ClienteEntity entity : listaEntity) {
            list.add(new ClienteDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Devuelve la lista de clientes
     * @return null
     */
    @GET
    public List<ClienteDetailDTO> getClientes ()
    {
        LOGGER.info("ClienteResource getClientes: input: void");
        List<ClienteDetailDTO> listaClientes = listEntityADTO(clienteLogic.getClientes());
        LOGGER.log(Level.INFO, "ClienteResource getClientes: output: {0}", listaClientes);
        return listaClientes;
    }
    
    /**
     * Actualiza el cliente ingresado por parametro
     * @param clientesId del cliente a actualizar
     * @param cliente el cliente por el cual se quiere actualizar
     * @return null
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @PUT
    public ClienteDetailDTO updateCliente(@PathParam("clientesId") Long clientesId, ClienteDetailDTO cliente) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "ClienteResource updateCliente: input: clientesId: {0} , cliente: {1}", new Object[]{clientesId, cliente});
        cliente.setId(clientesId);
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        ClienteDetailDTO detailDTO = new ClienteDetailDTO(clienteLogic.setCliente(clientesId, cliente.toEntity()));
        LOGGER.log(Level.INFO, "ClienteResource updateCliente: output: {0}", detailDTO);
        return detailDTO;
    }    
    
    /**
     * Elimina el cliente ingresado como parametro
     * @param clientesId del usuario que se quiere eliminar 
     */
    @DELETE
    @Path ("{clientesId:\\d+}")
    public void deleteCliente(@PathParam ("clientesId")Long clientesId)
    {
         LOGGER.log(Level.INFO, "ClienteResource deleteCliente: input: {0}", clientesId);
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        clienteLogic.deleteCliente(clientesId);
        LOGGER.info("ClienteResource deleteCliente: output: void");
    }   
}
