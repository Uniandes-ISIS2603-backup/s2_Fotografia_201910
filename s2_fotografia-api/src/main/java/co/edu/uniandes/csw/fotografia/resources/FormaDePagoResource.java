/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.FormaDePagoDTO;
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

@Path ("formasDePago")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped
public class FormaDePagoResource 
{
    private static final Logger LOGGER = Logger.getLogger(FormaDePagoResource.class.getName());
    
    /**
     * Crea una forma de pago
     * @param formaDePago la forma de pago a crear
     * @return la forma de pago
     */
    @POST
    public FormaDePagoDTO createFormaDePago (FormaDePagoDTO formaDePago)
    {
        return formaDePago;
        
    }
    
    /**
     * Devuelve la forma de pago con el nombre ingresado por parametro
     * @param nombre de la forma de que se esta buscando
     * @return null
     */
    @GET
    @Path ("{nombre:\\d+}")
    public FormaDePagoDTO getFormaDePago (@PathParam ("nombre")String nombre)
    {
        return null;
    }
    
    /**
     * Devuelve la lista con todas las formas de pago
     * @return null
     */
    @GET
    public List<FormaDePagoDTO> getFormasDePago ()
    {
        return null;
    }
    
    /**
     * Actualiza la forma de pago con el nombre ingresado por parametro
     * @param nombre de la forma de pago a actualizar
     * @param formaDePago la informacion de la forma de pago que se ingresara
     * @return null
     */
    @PUT
    public FormaDePagoDTO updateFormaDePago(@PathParam ("nombre")String nombre, FormaDePagoDTO formaDePago )
    {
        return null;
    }    
    
    /**
     * Elimina la forma de pago con el nombre ingresado por parametro
     * @param nombre de la forma de pago a eliminar
     * @return null
     */
    @DELETE
    @Path ("{nombre:\\d+}")
    public FormaDePagoDTO deleteFormaDePago(@PathParam ("nombre")String nombre)
    {
        return null;
    }   
}
