/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.FacturaDTO;
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

@Path ("facturas")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped


public class FacturaResource
{
    
    private static final Logger LOGGER = Logger.getLogger(FacturaResource.class.getName());
    
    /**
     * Crea la factura que se ingresa por parametro
     * @param factura la factura a crear
     * @return la factura
     */
    @POST
    public FacturaDTO createFactura (FacturaDTO factura)
    {
        return factura;
        
    }
    
    /**
     * Devuelve la factura con el numero ingresado por parametro
     * @param numero de la factura que se quiere traer
     * @return null
     */
    @GET
    @Path ("{numero:\\d+}")
    public FacturaDTO getFactura (@PathParam ("numero")int numero)
    {
        return null;
    }
    
    /**
     * Devuelve la lista de todas las facturas
     * @return null
     */
       @GET
    public List<FacturaDTO> getFacturas ()
    {
        return null;
    }
    
    /**
     * Actualiza la factura
     * @param numero nunero de la factura a actualizar
     * @param factura la informacion de la factura por la cual se va a actualizar
     * @return null
     */
    @PUT
    public FacturaDTO updateFactura(@PathParam ("numero")int numero, FacturaDTO factura)
    {
        return null;
    }    
   
    /**
     * Elimina la factura con el numero ingresado por parametro
     * @param numero de la factura que se quiere eliminar
     * @return null
     */
    @DELETE
    @Path ("{numero:\\d+}")
    public FacturaDTO deleteFactura(@PathParam ("numero")int numero)
    {
        return null;
    }  
}
