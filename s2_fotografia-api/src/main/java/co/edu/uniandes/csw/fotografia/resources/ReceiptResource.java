/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.ReceiptDTO;
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
 * @author estudiante
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
    public ReceiptDTO getReceipt (@PathParam ("id")int id)
    {
        return null;
    }
    
    @PUT
    public ReceiptDTO updateReceipt(@PathParam ("id")int id)
    {
        return null;
    }    
    
    @DELETE
    public ReceiptDTO deleteReceipt(@PathParam ("id")int id)
    {
        return null;
    }  
}
