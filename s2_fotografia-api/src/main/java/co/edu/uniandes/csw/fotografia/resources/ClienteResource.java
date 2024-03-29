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
@Path ("/clientes")
@Produces (MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
    @Path ("{clientesId:\\d+}")
    public ClienteDetailDTO getCliente (@PathParam ("clientesId")Long clientesId) throws WebApplicationException
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
     * Busca el cliente con el login ingresado por parametro
     * @param login el login por el cual se quiere buscar
     * @return el cliente buscado
     */
    @GET
    @Path ("{login:[a-z A-Z 0-9][a-z A-Z 0-9]*}")
    public ClienteDetailDTO getClienteByLogin (@PathParam ("login")String login) throws WebApplicationException
    {
         LOGGER.log(Level.INFO, "ClienteResource getCliente: input: {0}", login);
        ClienteEntity clienteEntity = clienteLogic.getClienteByLogin(login);
        if (clienteEntity == null) {
            throw new WebApplicationException("El recurso /clientes/" + login + " no existe.", 404);
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
    @Path("{clientesId: \\d+}")
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
    
     /**
     * Conexión con el servicio de facturas para un cliente.
     * {@link ClienteFacturasResource}
     *
     * Este método conecta la ruta de /clientes con las rutas de /facturas que
     * dependen del cliente, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de las facturas de un cliente.
     *
     * @param clientesId El ID del cliente con respecto a la cual se
     * accede al servicio.
     * @return El servicio de facturas para este cliente en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
   @Path("{clientesId: \\d+}/facturas")
    public Class<ClienteFacturasResource> getClienteFacturasResource(@PathParam("clientesId") Long clientesId) {
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        return ClienteFacturasResource.class;
    }
    
    
      
     /**
     * Conexión con el servicio de formas de pago para un cliente.
     * {@link ClienteFormasDePagoResource}
     *
     * Este método conecta la ruta de /clientes con las rutas de /formasDePago que
     * dependen del cliente, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de las facturas de un cliente.
     *
     * @param clientesId El ID del cliente con respecto a la cual se
     * accede al servicio.
     * @return El servicio de formasDePago para este cliente en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
   @Path("{clientesId: \\d+}/formasDePago")
    public Class<ClienteFormasDePagoResource> getClienteFormasDePagoResource(@PathParam("clientesId") Long clientesId) {
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        return ClienteFormasDePagoResource.class;
    }
    

}
