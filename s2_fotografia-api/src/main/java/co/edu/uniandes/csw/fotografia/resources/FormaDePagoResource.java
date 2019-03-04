/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.FormaDePagoDTO;
import co.edu.uniandes.csw.fotografia.ejb.FormaDePagoLogic;
import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
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

@Path ("formasDePago")
@Produces ("application/json")
@Consumes("application/json")
@RequestScoped
public class FormaDePagoResource 
{
    private static final Logger LOGGER = Logger.getLogger(FormaDePagoResource.class.getName());
    
    @Inject
    private FormaDePagoLogic formaDePagoLogic;
    /**
     * Crea una forma de pago
     * @param formaDePago la forma de pago a crear
     * @return la forma de pago
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @POST
    public FormaDePagoDTO createFormaDePago (FormaDePagoDTO formaDePago) throws BusinessLogicException
    {
         LOGGER.log(Level.INFO, "FormaDePagoResource createFormaDePago: input: {0}", formaDePago);
        FormaDePagoDTO nuevaFormaDePagoDTO = new FormaDePagoDTO(formaDePagoLogic.createFormaDePago(formaDePago.toEntity()));
        LOGGER.log(Level.INFO, "FacturaResource createFactura: output: {0}", nuevaFormaDePagoDTO);
        return nuevaFormaDePagoDTO;
    }
    
    /**
     * Devuelve la forma de pago con el nombre ingresado por parametro
     * @param formasDePagoId de la forma de que se esta buscando
     * @return forma de pago
     */
    @GET
    @Path ("{formasDePagoId:\\d+}")
    public FormaDePagoDTO getFormaDePago (@PathParam ("formasDePagoId")Long formasDePagoId)
    {
        LOGGER.log(Level.INFO, "FormaDePagoResource getFormaDePago: input: {0}", formasDePagoId);
        FormaDePagoEntity entity = formaDePagoLogic.getFormaDePago(formasDePagoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /formasDePago/" + formasDePagoId + " no existe.", 404);
        }
        FormaDePagoDTO formaDePagoDTO = new FormaDePagoDTO(entity);
        LOGGER.log(Level.INFO, "FormaDePagoResource getFormaDePago: output: {0}", formaDePagoDTO);
        return formaDePagoDTO;
    }
    
    
     /**
     * Convierte una lista de Entity a una lista de FormaDePagoDTO.
     *
     * @param entityList Lista de FormaDePagoEntity a convertir.
     * @return Lista de FormaDePagoDTO convertida.
     */
    private List<FormaDePagoDTO> listEntityADTO(List<FormaDePagoEntity> listaEntity) {
        List<FormaDePagoDTO> list = new ArrayList<>();
        for (FormaDePagoEntity entity : listaEntity) {
            list.add(new FormaDePagoDTO(entity));
        }
        return list;
    }
    
    /**
     * Devuelve la lista con todas las formas de pago
     * @return formas de pago
     */
    @GET
    public List<FormaDePagoDTO> getFormasDePago ()
    {
        LOGGER.info("FormaDePagoResource getFormasDePago: input: void");
        List<FormaDePagoDTO> listaFormasDePago = listEntityADTO(formaDePagoLogic.getFormasDePago());
        LOGGER.log(Level.INFO, "FormaDePagoResource getFormasDePago: output: {0}", listaFormasDePago);
        return listaFormasDePago;
    }
    
    /**
     * Actualiza la forma de pago con el nombre ingresado por parametro
     * @param formasDePagoId de la forma de pago a actualizar
     * @param formaDePago la informacion de la forma de pago que se ingresara
     * @return forma de pago actualizada
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @PUT
    public FormaDePagoDTO updateFormaDePago(@PathParam ("formasDePagoId")Long formasDePagoId, FormaDePagoDTO formaDePago ) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "FormaDePagoResource updateFormaDePago: input: formasDePagoId: {0} , formaDePago: {1}", new Object[]{formasDePagoId, formaDePago});
        formaDePago.setId(formasDePagoId);
        if (formaDePagoLogic.getFormaDePago(formasDePagoId) == null) {
            throw new WebApplicationException("El recurso /formasDePago/" + formasDePagoId + " no existe.", 404);
        }
        FormaDePagoDTO dto = new FormaDePagoDTO(formaDePagoLogic.setFormaDePago(formasDePagoId, formaDePago.toEntity()));
        LOGGER.log(Level.INFO, "FormaDePagoResource updateFormaDePago: output: {0}", dto);
        return dto;
    }    
    
    /**
     * Elimina la forma de pago con el nombre ingresado por parametro
     * @param formasDePagoId de la forma de pago a eliminar
     */
    @DELETE
    @Path ("{formasDePagoId:\\d+}")
    public void deleteFormaDePago(@PathParam ("formasDePagoId")Long formasDePagoId)
    {
        LOGGER.log(Level.INFO, "FacturaResource deleteFactura: input: {0}", formasDePagoId);
        if (formaDePagoLogic.getFormaDePago(formasDePagoId) == null) {
            throw new WebApplicationException("El recurso /formasDePago/" + formasDePagoId + " no existe.", 404);
        }
        formaDePagoLogic.deleteFormaDePago(formasDePagoId);
        LOGGER.info("FormaDePagoResource deleteFormaDePago: output: void");
    }   
}
