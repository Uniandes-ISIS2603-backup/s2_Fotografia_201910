/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.FacturaDTO;
import co.edu.uniandes.csw.fotografia.ejb.FacturaLogic;
import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
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
    
    @Inject
    private FacturaLogic facturaLogic;
    
    /**
     * Crea la factura que se ingresa por parametro
     * @param factura la factura a crear
     * @return la factura
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @POST
    public FacturaDTO createFactura (FacturaDTO factura) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "FacturaResource createFactura: input: {0}", factura);
        FacturaDTO nuevaFacturaDTO = new FacturaDTO(facturaLogic.createFactura(factura.toEntity()));
        LOGGER.log(Level.INFO, "FacturaResource createFactura: output: {0}", nuevaFacturaDTO);
        return nuevaFacturaDTO;
        
    }
   
    
    /**
     * Devuelve la factura con el numero ingresado por parametro
     * @param facturasId de la factura que se quiere traer
     * @return factura
     */
    @GET
    @Path ("{facturasId:\\d+}")
    public FacturaDTO getFactura (@PathParam ("facturasId")Long facturasId)
    {
        LOGGER.log(Level.INFO, "FacturaResource getFactura: input: {0}", facturasId);
        FacturaEntity entity = facturaLogic.getFactura(facturasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /facturas/" + facturasId + " no existe.", 404);
        }
        FacturaDTO facturaDTO = new FacturaDTO(entity);
        LOGGER.log(Level.INFO, "FacturaResource getFactura: output: {0}", facturaDTO);
        return facturaDTO;
    }
    
       /**
     * Convierte una lista de FacturaEntity a una lista de FacturaDTO.
     *
     * @param entityList Lista de FacturaEntity a convertir.
     * @return Lista de FacturaDTO convertida.
     */
    private List<FacturaDTO> listEntityADTO(List<FacturaEntity> listaEntity) {
        List<FacturaDTO> list = new ArrayList<>();
        for (FacturaEntity entity : listaEntity) {
            list.add(new FacturaDTO(entity));
        }
        return list;
    }
    
    /**
     * Devuelve la lista de todas las facturas
     * @return lista de facturas
     */
       @GET
    public List<FacturaDTO> getFacturas ()
    {
        LOGGER.info("ClienteResource getClientes: input: void");
        List<FacturaDTO> listaFacturas = listEntityADTO(facturaLogic.getFacturas());
        LOGGER.log(Level.INFO, "FacturaResource getFacturas: output: {0}", listaFacturas);
        return listaFacturas;
    }
    
    /**
     * Actualiza la factura
     * @param facturasId id de la factura a actualizar
     * @param factura la informacion de la factura por la cual se va a actualizar
     * @return forma de pago actualizada
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @PUT
    public FacturaDTO updateFactura(@PathParam ("facturasId")Long facturasId, FacturaDTO factura) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "FacturaResource updateFactura: input: facturasId: {0} , factura: {1}", new Object[]{facturasId, factura});
        factura.setId(facturasId);
        if (facturaLogic.getFactura(facturasId) == null) {
            throw new WebApplicationException("El recurso /facturas/" + facturasId + " no existe.", 404);
        }
        FacturaDTO dto = new FacturaDTO(facturaLogic.setFactura(facturasId, factura.toEntity()));
        LOGGER.log(Level.INFO, "FacturaResource updateFactura: output: {0}", dto);
        return dto;
    }    
   
    /**
     * Elimina la factura con el numero ingresado por parametro
     * @param facturasId de la factura que se quiere eliminar
     */
    @DELETE
    @Path ("{facturasId:\\d+}")
    public void deleteFactura(@PathParam ("facturasId")Long facturasId)
    {
       LOGGER.log(Level.INFO, "FacturaResource deleteFactura: input: {0}", facturasId);
        if (facturaLogic.getFactura(facturasId) == null) {
            throw new WebApplicationException("El recurso /facturas/" + facturasId + " no existe.", 404);
        }
        facturaLogic.deleteFactura(facturasId);
        LOGGER.info("FacturaResource deleteFactura: output: void");
    }  
}
