/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.FacturaDTO;
import co.edu.uniandes.csw.fotografia.ejb.ClienteFacturasLogic;
import co.edu.uniandes.csw.fotografia.ejb.FacturaLogic;
import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
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
public class ClienteFacturasResource 
{
    private static final Logger LOGGER = Logger.getLogger(ClienteFacturasResource.class.getName());
    
  
    @Inject
    private ClienteFacturasLogic clienteFacturasLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private FacturaLogic facturaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda una factura dentro de un cliente con la informacion que recibe
     * la URL. Se devuelve la factura que se guarda en el cliente.
     *
     * @param clientesId Identificador del cliente que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param facturasId Identificador de la factura que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link FacturaDTO} - La factura guardada en el cliente.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la factura .
     */
    @POST
    @Path("{facturasId: \\d+}")
    public FacturaDTO addFactura(@PathParam("clientesId") Long clientesId, @PathParam("facturasId") Long facturasId) {
        LOGGER.log(Level.INFO, "ClienteFacturasResource addFactura: input: clientesID: {0} , facturasId: {1}", new Object[]{clientesId, facturasId});
        if (facturaLogic.getFactura(facturasId) == null) {
            throw new WebApplicationException("El recurso /facturas/" + facturasId + " no existe.", 404);
        }
        FacturaDTO facturaDTO = new FacturaDTO(clienteFacturasLogic.addFactura(facturasId, clientesId));
        LOGGER.log(Level.INFO, "ClienteFacturasResource addFactura: output: {0}", facturaDTO);
        return facturaDTO;
    }

    /**
     * Busca y devuelve todas las facturas que existen en el cliente.
     *
     * @param clientesId Identificador del cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link FacturaDTO} - Las facturass encontradas en el cliente
     * Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<FacturaDTO> getFacturas(@PathParam("clientesId") Long clientesId) {
        LOGGER.log(Level.INFO, "ClienteFacturasResource getFacturas: input: {0}", clientesId);
        List<FacturaDTO> listaDTOs = facturasListEntityADTO(clienteFacturasLogic.getFacturas(clientesId));
        LOGGER.log(Level.INFO, "ClienteFacturasResource getFacturas: output: {0}", listaDTOs);
        return listaDTOs;
    }

    /**
     * Busca la factura con el id asociado dentro del cliente con id asociado.
     *
     * @param clientesId Identificador del cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param facturasId Identificador de la factura que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link FacturaDTO} - La factura buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la factura.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la factura en el cliente
     */
    @GET
    @Path("{facturasId: \\d+}")
    public FacturaDTO getFactura(@PathParam("clientesId") Long clientesId, @PathParam("facturasId") Long facturasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteFacturasResource getFactura: input: clientesID: {0} , facturasId: {1}", new Object[]{clientesId, facturasId});
        if (facturaLogic.getFactura(facturasId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + "/facturas/" + facturasId + " no existe.", 404);
        }
        FacturaDTO facturaDTO = new FacturaDTO(clienteFacturasLogic.getFactura(clientesId, facturasId));
        LOGGER.log(Level.INFO, "ClienteFacturasResource getFactura: output: {0}",facturaDTO);
        return facturaDTO;
    }

    /**
     * Remplaza las instancias de Factura asociadas a una instancia de Cliente
     *
     * @param clientesId Identificador del cliente que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param facturas JSONArray {@link FacturaDTO} El arreglo de facturas nuevo para el cliente. 
     * @return JSON {@link FacturaDTO} - El arreglo de facturas guardado en el cliente
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la factura.
     */
    @PUT
    public List<FacturaDTO> replaceFacturas(@PathParam("clientesId") Long clientesId, List<FacturaDTO> facturas) {
        LOGGER.log(Level.INFO, "ClienteFacturasResource replaceFacturas: input: clientesId: {0} , facturas: {1}", new Object[]{clientesId, facturas});
        for (FacturaDTO factura : facturas) {
            if (facturaLogic.getFactura(factura.getId()) == null) {
                throw new WebApplicationException("El recurso /facturas/" + factura.getId() + " no existe.", 404);
            }
        }
        List<FacturaDTO> listaDTOs = facturasListEntityADTO(clienteFacturasLogic.replaceFacturas(clientesId, facturasListDTOAEntity(facturas)));
        LOGGER.log(Level.INFO, "ClienteFacturasResource replaceFacturas: output: {0}", listaDTOs);
        return listaDTOs;
    }

    /**
     * Convierte una lista de FacturaEntity a una lista de FacturaDTO.
     *
     * @param entityList Lista de FacturaEntity a convertir.
     * @return Lista de FacturaDTO convertida.
     */
    private List<FacturaDTO> facturasListEntityADTO(List<FacturaEntity> entityList) {
        List<FacturaDTO> list = new ArrayList();
        for (FacturaEntity entity : entityList) {
            list.add(new FacturaDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de FacturaDTO a una lista de FacturaEntity.
     *
     * @param dtos Lista de FacturaDTO a convertir.
     * @return Lista de FacturaEntity convertida.
     */
    private List<FacturaEntity> facturasListDTOAEntity(List<FacturaDTO> dtos) {
        List<FacturaEntity> list = new ArrayList<>();
        for (FacturaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

}
