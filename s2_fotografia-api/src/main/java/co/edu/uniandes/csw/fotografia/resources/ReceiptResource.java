/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.ReceiptDTO;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Valentina Duarte
 */

@Path ("receipts")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped


public class ReceiptResource
{
    
    private static final Logger LOGGER = Logger.getLogger(ReceiptResource.class.getName());
    
    @POST
    public ReceiptDTO createReceipt (ReceiptDTO receipt)
    {
        return receipt;
        
    }
    
    @GET
    @Path ("{numero:\\d+}")
    public ReceiptDTO getReceipt (@PathParam ("numero")int numero)
    {
        return null;
    }
    
       @GET
    public List<ReceiptDTO> getReceipts ()
    {
        return null;
    }
    
    @PUT
    public ReceiptDTO updateReceipt(@PathParam ("numero")int numero, ReceiptDTO receipt)
    {
        return null;
    }    
    
    @DELETE
    @Path ("{numero:\\d+}")
    public ReceiptDTO deleteReceipt(@PathParam ("numero")int numero)
    {
        return null;
    }  
}
