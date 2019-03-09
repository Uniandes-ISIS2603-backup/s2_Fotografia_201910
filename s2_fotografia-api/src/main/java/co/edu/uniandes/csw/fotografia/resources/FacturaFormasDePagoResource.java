/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.FormaDePagoDTO;
import co.edu.uniandes.csw.fotografia.ejb.FacturaFormasDePagoLogic;
import co.edu.uniandes.csw.fotografia.ejb.FormaDePagoLogic;
import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FacturaFormasDePagoResource 
{
    private static final Logger LOGGER = Logger.getLogger(FacturaFormasDePagoResource.class.getName());

    @Inject
    private FacturaFormasDePagoLogic facturaFormasDePagoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private FormaDePagoLogic formaDePagoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda una forma de pago dentro de una factura con la informacion que recibe el la
     * URL.
     *
     * @param facturasId Identificador de la factura que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param formasDePagoId Identificador del autor que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AuthorDTO} - La forma de pago guardado en la factura.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la forma de pago.
     */
    @POST
    @Path("{formasDePagoId: \\d+}")
    public FormaDePagoDTO addFormaDePago(@PathParam("facturasId") Long facturasId, @PathParam("formasDePagoId") Long formasDePagoId) {
        LOGGER.log(Level.INFO, "FacturaFormasDePagoResource addFormaDePago: input: facturasID: {0} , formasDePagoId: {1}", new Object[]{facturasId, formasDePagoId});
        if (formaDePagoLogic.getFormaDePago(formasDePagoId) == null) {
            throw new WebApplicationException("El recurso /formasDePago/" + formasDePagoId + " no existe.", 404);
        }
        FormaDePagoDTO formaDePagoDTO = new FormaDePagoDTO(facturaFormasDePagoLogic.addFormaDePago(formasDePagoId, facturasId));
        LOGGER.log(Level.INFO, "PrizeAuthorResource addAuthor: output: {0}", formaDePagoDTO);
        return formaDePagoDTO;
    }

    /**
     * Busca la forma de pago dentro de la factura con id asociado.
     *
     * @param facturasId Identificador de la factura que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - La forma de pago buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando la factura no tiene forma de pago.
     */
    @GET
    public FormaDePagoDTO getFormaDePago(@PathParam("facturasId") Long facturasId) {
        LOGGER.log(Level.INFO, "FacturaFormasDePagoResource getFormaDePago: input: {0}", facturasId);
        FormaDePagoEntity formaDePagoEntity = facturaFormasDePagoLogic.getFormaDePago(facturasId);
        if (formaDePagoEntity == null) {
            throw new WebApplicationException("El recurso /facturas/" + facturasId + "/formaDePago no existe.", 404);
        }
        FormaDePagoDTO formaDePagoDTO = new FormaDePagoDTO(formaDePagoEntity);
        LOGGER.log(Level.INFO, "FacturaFormasDePagoResource getFormaDePago: output: {0}", formaDePagoDTO);
        return formaDePagoDTO;
    }

    /**
     * Remplaza la instancia de FormaDePago asociada a una instancia de Factura
     *
     * @param facturasId Identificador la factura que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param formasDePagoId Identificador de la forma de pago que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - La fporma de pago actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la forma de pago.
     */
    @PUT
    @Path("{formasDePagoId: \\d+}")
    public FormaDePagoDTO replaceFormaDePago(@PathParam("facturasId") Long facturasId, @PathParam("formasDePagoId") Long formasDePagoId) {
        LOGGER.log(Level.INFO, "FacturaFormasDePagoResource replaceFormaDePago: input: facturasId: {0} , formasDePagoId: {1}", new Object[]{facturasId, formasDePagoId});
        if (formaDePagoLogic.getFormaDePago(formasDePagoId) == null) {
            throw new WebApplicationException("El recurso /formasDePago/" + formasDePagoId + " no existe.", 404);
        }
        FormaDePagoDTO formaDePagoDTO = new FormaDePagoDTO(facturaFormasDePagoLogic.replaceFormaDePago(facturasId, formasDePagoId));
        LOGGER.log(Level.INFO, "FacturaFormasDePagoResource replaceFormaDePago: output: {0}", formaDePagoDTO);
        return formaDePagoDTO;
    }

    /**
     * Elimina la conexión entre la forma de pago y la factura recibido en la URL.
     *
     * @param facturasId El ID del la factura al cual se le va a desasociar la forma de pago 
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @DELETE
    public void removeFormaDePago(@PathParam("facturasId") Long facturasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FacturaFormasDePagoResource removeFormaDePago: input: {0}", facturasId);
        facturaFormasDePagoLogic.removeFormaDePago(facturasId);
        LOGGER.info("FacturaFormasDePagoResource removeFormasDePago: output: void");
    }
}
