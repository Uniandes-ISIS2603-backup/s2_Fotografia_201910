/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.ClientDTO;
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

public class ClientResource 
{
    private static final Logger LOGGER = Logger.getLogger(ClientResource.class.getName());
    
    @POST
    public ClientDTO createClient (ClientDTO client)
    {
        return client;
        
    }
    
    @GET
    @Path ("{usuario:\\d+}")
    public ClientDTO getClient (@PathParam ("usuario")String usuario)
    {
        return null;
    }
    
    @GET
    public List<ClientDTO> getClients ()
    {
        return null;
    }
    
    @PUT
    public ClientDTO updateClient(@PathParam ("usuario")String usuario, ClientDTO client )
    {
        return null;
    }    
    
    @DELETE
    @Path ("{usuario:\\d+}")
    public ClientDTO deleteClient(@PathParam ("usuario")String usuario)
    {
        return null;
    }   
}
