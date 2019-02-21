/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.ClienteDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
    
    /**
     * Crea el cliente que entra como parametro
     * @param cliente para ser creado
     * @return el cliente creado
     */
    @POST
    public ClienteDTO createCliente (ClienteDTO cliente)
    {
        return cliente;
        
    }
    
    /**
     * Busca el cliente con el id ingresado por parametro
     * @param login del cliente buscado
     * @return null
     */
    @GET
    @Path ("{login:\\d+}")
    public ClienteDTO getCliente (@PathParam ("login")String login)
    {
        return null;
    }
    
    /**
     * Devuelve la lista de clientes
     * @return null
     */
    @GET
    public List<ClienteDTO> getClientes ()
    {
        return null;
    }
    
    /**
     * Actualiza el cliente ingresado por parametro
     * @param login del cliente a actualizar
     * @param cliente el cliente por el cual se quiere actualizar
     * @return null
     */
    @PUT
    public ClienteDTO updateCliente(@PathParam ("login")String login, ClienteDTO cliente )
    {
        return null;
    }    
    
    /**
     * Elimina el cliente ingresado como parametro
     * @param login del usuario que se quiere eliminar 
     * @return null
     */
    @DELETE
    @Path ("{login:\\d+}")
    public ClienteDTO deleteCliente(@PathParam ("login")String login)
    {
        return null;
    }   
}
