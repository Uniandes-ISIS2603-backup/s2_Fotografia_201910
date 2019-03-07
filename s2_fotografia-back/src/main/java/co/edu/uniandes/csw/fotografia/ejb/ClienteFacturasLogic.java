/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.ClientePersistence;
import co.edu.uniandes.csw.fotografia.persistence.FacturaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Valentina Duarte
 */

@Stateless
public class ClienteFacturasLogic 
{
    private static final Logger LOGGER = Logger.getLogger(ClienteFacturasLogic.class.getName());

    @Inject
    private FacturaPersistence fp;

    @Inject
    private ClientePersistence cp;

    /**
     * Agregar una factura al cliente
     *
     * @param facturasId El id de la factura a guardar
     * @param clientesId El id del cliente en la cual se va a guardar la
     * factura.
     * @return La factura creada.
     */
    public FacturaEntity addFactura(Long facturasId, Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una factura al cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = cp.get(clientesId);
        FacturaEntity facturaEntity = fp.get(facturasId);
        facturaEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una factura al cliente con id = {0}", clientesId);
        return facturaEntity;
    }

    /**
     * Retorna todas las facturas asociadas a un cliente
     *
     * @param clientesId El ID del cliente buscado
     * @return La lista de facturas del cliente
     */
    public List<FacturaEntity> getFacturas(Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las facturas asociados al cliente con id = {0}", clientesId);
        return cp.get(clientesId).getFacturas();
    }

    /**
     * Retorna una factura asociada a un cliente
     *
     * @param clientesId El id del cliente a buscar.
     * @param facturasId El id de la factura a buscar
     * @return La factura encontrada dentro del cliente.
     * @throws BusinessLogicException Si la factura no se encuentra en el cliente
     */
    public FacturaEntity getFactura(Long clientesId, Long facturasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la factura con id = {0} del cliente con id = " + clientesId, facturasId);
        List<FacturaEntity> facturas = cp.get(clientesId).getFacturas();
        FacturaEntity facturaEntity = fp.get(facturasId);
        int index = facturas.indexOf(facturaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la factura con id = {0} del cliente con id = " + clientesId, facturasId);
        if (index >= 0) {
            return facturas.get(index);
        }
        throw new BusinessLogicException("La factura no está asociado al cliente");
    }

    /**
     * Remplazar facturas de un cliente
     *
     * @param facturas Lista de facturas que serán los del cliente.
     * @param clientesId El id del cliente que se quiere actualizar.
     * @return La lista de facturas actualizada.
     */
    public List<FacturaEntity> replaceFacturas(Long clientesId, List<FacturaEntity> facturas) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = cp.get(clientesId);
        List<FacturaEntity> facturaList = fp.getAll();
        for (FacturaEntity factura : facturaList) {
            if (facturas.contains(factura)) {
                factura.setCliente(clienteEntity);
            } else if (factura.getCliente() != null && factura.getCliente().equals(clienteEntity)) {
                factura.setCliente(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el cliente con id = {0}", clientesId);
        return facturas;
    }
}
