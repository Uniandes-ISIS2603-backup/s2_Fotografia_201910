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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Valentina Duarte
 */
@Stateless
public class FormaDePagoClientesLogic 
{
    private static final Logger LOGGER = Logger.getLogger(FormaDePagoClientesLogic.class.getName());

    @Inject
    private ClientePersistence clientePersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private FormaDePagoPersistence formaDePagoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Agregar un cliente a una forma de pago
     * @param formasDePagoId El id de la forma de pago a guardar
     * @param clientesId El id del cliente al cual se le va a guardar la forma de pago
     * @return El cliente que fue agragado a la forma de pago.
     */
    public ClienteEntity addCliente(Long clientesId, Long formasDePagoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar el cliente con id = {0} a la forma de pago con id = " + formasDePagoId, clientesId);
        ClienteEntity clienteEntity = clientePersistence.get(clientesId);
        FormaDePagoEntity formaDePagoEntity = formaDePagoPersistence.get(formasDePagoId);
        formaDePagoEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el cliente con id = {0} a la forma de pago con id = " + formasDePagoId, clientesId);
        return clientePersistence.get(clientesId);
    }

    /**
     *
     * Obtener una forma de pago por medio de su id y el de su cliente.
     *
     * @param formasDePagoId id de la forma de pago a ser buscada.
     * @return el cliente solicitado por medio de su id.
     */
    public ClienteEntity getCliente(Long formasDePagoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el cliente de la forma de pago con id = {0}", formasDePagoId);
        ClienteEntity clienteEntity = formaDePagoPersistence.get(formasDePagoId).getCliente();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el cliente de la forma de pago con id = {0}", formasDePagoId);
        return clienteEntity;
    }

    /**
     * Remplazar cliente de una forma de pago
     *
     * @param formasDePagoId el id de la forma de pago que se quiere actualizar.
     * @param clientesId El id del nuevo cliente asociado a la forma de pago.
     * @return el nuevo cliente asociado.
     */
    public ClienteEntity replaceCliente(Long formasDePagoId, Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el cliente de la forma de pago con id = {0}", formasDePagoId);
        ClienteEntity clienteEntity = clientePersistence.get(clientesId);
       FormaDePagoEntity formaDePagoEntity =formaDePagoPersistence.get(formasDePagoId);
        formaDePagoEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el cliente con id = {0} a la forma de pago con id = " + formasDePagoId, clientesId);
        return clientePersistence.get(clientesId);
    }

    /**
     * Borrar el cliente de una forma de pago
     *
     * @param formasDePagoId La forma de pago que se desea borrar del cliente.
     * @throws BusinessLogicException si la forma de pago no tiene cliente
     */
    public void removeCliente(Long formasDePagoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el cliente de la forma de pago con id = {0}", formasDePagoId);
        FormaDePagoEntity formaDePagoEntity = formaDePagoPersistence.get(formasDePagoId);
        if (formaDePagoEntity.getCliente() == null) {
            throw new BusinessLogicException("La forma de pago no tiene cliente");
        }
        ClienteEntity clienteEntity = clientePersistence.get(formaDePagoEntity.getCliente().getId());
        formaDePagoEntity.setCliente(null);
        clienteEntity.getFormasDePago().remove(formaDePagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el cliente con id = {0} de la forma de pago con id = " + formasDePagoId, clienteEntity.getId());
    }
}
