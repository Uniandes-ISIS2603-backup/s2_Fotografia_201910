/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.ClientePersistence;
import co.edu.uniandes.csw.fotografia.persistence.FormaDePagoPersistence;
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
public class ClienteFormasDePagoLogic 
{
    private static final Logger LOGGER = Logger.getLogger(ClienteFormasDePagoLogic.class.getName());

    @Inject
    private FormaDePagoPersistence fp;

    @Inject
    private ClientePersistence cp;

    /**
     * Agregar una formaDePago al cliente
     *
     * @param formasDePagoId El id de la formaDePago a guardar
     * @param clientesId El id del cliente en la cual se va a guardar la
     * formaDePago.
     * @return La formaDePago creada.
     */
    public FormaDePagoEntity addFormaDePago(Long formasDePagoId, Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una formaDePago al cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = cp.get(clientesId);
        FormaDePagoEntity formaDePagoEntity = fp.get(formasDePagoId);
        formaDePagoEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una formaDePago al cliente con id = {0}", clientesId);
        return formaDePagoEntity;
    }

    /**
     * Retorna todas las formasDePago asociadas a un cliente
     *
     * @param clientesId El ID del cliente buscado
     * @return La lista de formasDePago del cliente
     */
    public List<FormaDePagoEntity> getFormasDePago(Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las formasDePago asociados al cliente con id = {0}", clientesId);
        return cp.get(clientesId).getFormasDePago();
    }

    /**
     * Retorna una formaDePago asociada a un cliente
     *
     * @param clientesId El id del cliente a buscar.
     * @param formasDePagoId El id de la formaDePago a buscar
     * @return La formaDePago encontrada dentro del cliente.
     * @throws BusinessLogicException Si la formaDePago no se encuentra en el cliente
     */
    public FormaDePagoEntity getFormaDePago(Long clientesId, Long formasDePagoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la formaDePago con id = {0} del cliente con id = " + clientesId, formasDePagoId);
        List<FormaDePagoEntity> formasDePago = cp.get(clientesId).getFormasDePago();
        FormaDePagoEntity formaDePagoEntity = fp.get(formasDePagoId);
        int index = formasDePago.indexOf(formaDePagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la formaDePago con id = {0} del cliente con id = " + clientesId, formasDePagoId);
        if (index >= 0) {
            return formasDePago.get(index);
        }
        throw new BusinessLogicException("La formaDePago no está asociado al cliente");
    }

    /**
     * Remplazar formasDePago de un cliente
     *
     * @param formasDePago Lista de formasDePago que serán los del cliente.
     * @param clientesId El id del cliente que se quiere actualizar.
     * @return La lista de formasDePago actualizada.
     */
    public List<FormaDePagoEntity> replaceFormasDePago(Long clientesId, List<FormaDePagoEntity> formasDePago) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = cp.get(clientesId);
        List<FormaDePagoEntity> formaDePagoList = fp.getAll();
        for (FormaDePagoEntity formaDePago : formaDePagoList) {
            if (formasDePago.contains(formaDePago)) {
                formaDePago.setCliente(clienteEntity);
            } else if (formaDePago.getCliente() != null && formaDePago.getCliente().equals(clienteEntity)) {
                formaDePago.setCliente(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el cliente con id = {0}", clientesId);
        return formasDePago;
    }
}
