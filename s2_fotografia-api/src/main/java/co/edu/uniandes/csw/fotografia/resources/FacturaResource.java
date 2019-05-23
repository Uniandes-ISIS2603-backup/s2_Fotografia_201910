/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.FacturaDTO;
import co.edu.uniandes.csw.fotografia.dtos.FacturaDetailDTO;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Valentina Duarte
 */

@Path ("/facturas")
@Produces (MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
    public FacturaDetailDTO createFactura(FacturaDetailDTO factura) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "FacturaResource createFactura: input: {0}", factura);
        FacturaDetailDTO nuevaFacturaDTO = new FacturaDetailDTO(facturaLogic.createFactura(factura.toEntity()));
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
    public FacturaDetailDTO getFactura (@PathParam ("facturasId")Long facturasId)
    {
        LOGGER.log(Level.INFO, "FacturaResource getFactura: input: {0}", facturasId);
        FacturaEntity entity = facturaLogic.getFactura(facturasId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /facturas/" + facturasId + " no existe.", 404);
        }
        FacturaDetailDTO facturaDetailDTO = new FacturaDetailDTO(entity);
        LOGGER.log(Level.INFO, "FacturaResource getFactura: output: {0}", facturaDetailDTO);
        return facturaDetailDTO;
    }
    
       /**
     * Convierte una lista de FacturaEntity a una lista de FacturaDTO.
     *
     * @param entityList Lista de FacturaEntity a convertir.
     * @return Lista de FacturaDTO convertida.
     */
    private List<FacturaDetailDTO> listEntity2DTO(List<FacturaEntity> entityList) {
        List<FacturaDetailDTO> list = new ArrayList<>();
        for (FacturaEntity entity : entityList) {
            list.add(new FacturaDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Devuelve la lista de todas las facturas
     * @return lista de facturas
     */
    @GET
    public List<FacturaDetailDTO> getFacturas ()
    {
        LOGGER.info("ClienteResource getClientes: input: void");
        List<FacturaDetailDTO> listaFacturas = listEntity2DTO(facturaLogic.getFacturas());
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
    @Path("{facturasId: \\d+}")
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
    
    /**
     * Conexión con el servicio de formas de pago para una factura
     * {@link FacturaFormasDePagoResource}
     *
     * Este método conecta la ruta de /facturas con las rutas de /formasDePago que
     * dependen de la factura, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga la forma de pago de la factura.
     *
     * @param facturasId El ID de la factura con respecto a la cual se accede al
     * servicio.
     * @return El servicio de forma de pago para esta factura en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se la facturaq.
     */
    @Path("{facturasId: \\d+}/formasDePago")
    public Class<FacturaFormasDePagoResource> getFacturaFormasDePagoResource(@PathParam("facturasId") Long facturasId) {
        if (facturaLogic.getFactura(facturasId) == null) {
            throw new WebApplicationException("El recurso /facturas/" + facturasId + " no existe.", 404);
        }
        return FacturaFormasDePagoResource.class;
    }

    @Path("{facturasId: \\d+}/photos")
    public Class<PhotoFacturaResource> getFotografoPhotosResource(@PathParam("facturasId") Long facturasId) {
    
        if (facturaLogic.getFactura(facturasId) == null) {
            throw new WebApplicationException("El recurso /Fotografos/" + facturasId + " no existe.", 404);
        }
        return PhotoFacturaResource.class;
    }
}
