/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.FormaDePagoDTO;
import co.edu.uniandes.csw.fotografia.ejb.ClienteFormasDePagoLogic;
import co.edu.uniandes.csw.fotografia.ejb.FormaDePagoLogic;
import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
public class ClienteFormasDePagoResource 
{
    private static final Logger LOGGER = Logger.getLogger(ClienteFormasDePagoResource.class.getName());
    
  
    @Inject
    private ClienteFormasDePagoLogic clienteFormasDePagoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private FormaDePagoLogic formaDePagoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda una formaDePago dentro de un cliente con la informacion que recibe
     * la URL. Se devuelve la formaDePago que se guarda en el cliente.
     *
     * @param clientesId Identificador del cliente que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param formasDePagoId Identificador de la formaDePago que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link FormaDePagoDTO} - La formaDePago guardada en el cliente.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la formaDePago .
     */
    @POST
    @Path("{formasDePagoId: \\d+}")
    public FormaDePagoDTO addFormaDePago(@PathParam("clientesId") Long clientesId, @PathParam("formasDePagoId") Long formasDePagoId) {
        LOGGER.log(Level.INFO, "ClienteFormasDePagoResource addFormaDePago: input: clientesID: {0} , formasDePagoId: {1}", new Object[]{clientesId, formasDePagoId});
        if (formaDePagoLogic.getFormaDePago(formasDePagoId) == null) {
            throw new WebApplicationException("El recurso /formasDePago/" + formasDePagoId + " no existe.", 404);
        }
        FormaDePagoDTO formaDePagoDTO = new FormaDePagoDTO(clienteFormasDePagoLogic.addFormaDePago(formasDePagoId, clientesId));
        LOGGER.log(Level.INFO, "ClienteFormasDePagoResource addFormaDePago: output: {0}", formaDePagoDTO);
        return formaDePagoDTO;
    }

    /**
     * Busca y devuelve todas las formasDePago que existen en el cliente.
     *
     * @param clientesId Identificador del cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link FormaDePagoDTO} - Las formasDePagos encontradas en el cliente
     * Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<FormaDePagoDTO> getFormasDePago(@PathParam("clientesId") Long clientesId) {
        LOGGER.log(Level.INFO, "ClienteFormasDePagoResource getFormasDePago: input: {0}", clientesId);
        List<FormaDePagoDTO> listaDTOs = formasDePagoListEntityADTO(clienteFormasDePagoLogic.getFormasDePago(clientesId));
        LOGGER.log(Level.INFO, "ClienteFormasDePagoResource getFormasDePago: output: {0}", listaDTOs);
        return listaDTOs;
    }

    /**
     * Busca la formaDePago con el id asociado dentro del cliente con id asociado.
     *
     * @param clientesId Identificador del cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param formasDePagoId Identificador de la formaDePago que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link FormaDePagoDTO} - La formaDePago buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la formaDePago.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la formaDePago en el cliente
     */
    @GET
    @Path("{formasDePagoId: \\d+}")
    public FormaDePagoDTO getFormaDePago(@PathParam("clientesId") Long clientesId, @PathParam("formasDePagoId") Long formasDePagoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteFormasDePagoResource getFormaDePago: input: clientesID: {0} , formasDePagoId: {1}", new Object[]{clientesId, formasDePagoId});
        if (formaDePagoLogic.getFormaDePago(formasDePagoId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + "/formasDePago/" + formasDePagoId + " no existe.", 404);
        }
        FormaDePagoDTO formaDePagoDTO = new FormaDePagoDTO(clienteFormasDePagoLogic.getFormaDePago(clientesId, formasDePagoId));
        LOGGER.log(Level.INFO, "ClienteFormasDePagoResource getFormaDePago: output: {0}",formaDePagoDTO);
        return formaDePagoDTO;
    }

    /**
     * Remplaza las instancias de FormaDePago asociadas a una instancia de Cliente
     *
     * @param clientesId Identificador del cliente que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param formasDePago JSONArray {@link FormaDePagoDTO} El arreglo de formasDePago nuevo para el cliente. 
     * @return JSON {@link FormaDePagoDTO} - El arreglo de formasDePago guardado en el cliente
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la formaDePago.
     */
    @PUT
    public List<FormaDePagoDTO> replaceFormasDePago(@PathParam("clientesId") Long clientesId, List<FormaDePagoDTO> formasDePago) {
        LOGGER.log(Level.INFO, "ClienteFormasDePagoResource replaceFormasDePago: input: clientesId: {0} , formasDePago: {1}", new Object[]{clientesId, formasDePago});
        for (FormaDePagoDTO formaDePago : formasDePago) {
            if (formaDePagoLogic.getFormaDePago(formaDePago.getId()) == null) {
                throw new WebApplicationException("El recurso /formasDePago/" + formaDePago.getId() + " no existe.", 404);
            }
        }
        List<FormaDePagoDTO> listaDTOs = formasDePagoListEntityADTO(clienteFormasDePagoLogic.replaceFormasDePago(clientesId, formasDePagoListDTOAEntity(formasDePago)));
        LOGGER.log(Level.INFO, "ClienteFormasDePagoResource replaceFormasDePago: output: {0}", listaDTOs);
        return listaDTOs;
    }

    /**
     * Convierte una lista de FormaDePagoEntity a una lista de FormaDePagoDTO.
     *
     * @param entityList Lista de FormaDePagoEntity a convertir.
     * @return Lista de FormaDePagoDTO convertida.
     */
    private List<FormaDePagoDTO> formasDePagoListEntityADTO(List<FormaDePagoEntity> entityList) {
        List<FormaDePagoDTO> list = new ArrayList();
        for (FormaDePagoEntity entity : entityList) {
            list.add(new FormaDePagoDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de FormaDePagoDTO a una lista de FormaDePagoEntity.
     *
     * @param dtos Lista de FormaDePagoDTO a convertir.
     * @return Lista de FormaDePagoEntity convertida.
     */
    private List<FormaDePagoEntity> formasDePagoListDTOAEntity(List<FormaDePagoDTO> dtos) {
        List<FormaDePagoEntity> list = new ArrayList<>();
        for (FormaDePagoDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

}
