/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.fotografia.persistence.ClientePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author a.trujilloa1
 */
@Stateless
public class CalificacionClienteLogic {
    private static final Logger LOGGER = Logger.getLogger(CalificacionClienteLogic.class.getName());

    @Inject
    private ClientePersistence clientePersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private CalificacionPersistence calificacionPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Agregar un cliente a un calficacion
     *
     * @param calificacionesId El id calficacion a guardar
     * @param clientesId El id del cliente al cual se le va a guardar el calficacion.
     * @return El calficacion que fue agregado al cliente.
     */
    public ClienteEntity addCliente(Long photoId, Long clientesId, Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar el cliente con id = {0} al calficacion con id = " + calificacionesId, clientesId);
        ClienteEntity clienteEntity = clientePersistence.get(clientesId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(photoId, calificacionesId);
        calificacionEntity.setClienteCalificador(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el cliente con id = {0} al calficacion con id = " + calificacionesId, clientesId);
        return clientePersistence.get(clientesId);
    }

    /**
     *
     * Obtener un calficacion por medio de su id y el de su cliente.
     *
     * @param calificacionesId id del calficacion a ser buscado.
     * @return el cliente solicitada por medio de su id.
     */
    public ClienteEntity getCliente(Long photoId,Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el cliente del calficacion con id = {0}", calificacionesId);
        ClienteEntity clienteEntity = calificacionPersistence.find(photoId,calificacionesId).getClienteCalificador();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el cliente del calficacion con id = {0}", calificacionesId);
        return clienteEntity;
    }

    /**
     * Remplazar cliente de un calficacion
     *
     * @param calificacionesId el id del calficacion que se quiere actualizar.
     * @param clientesId El id del nuebo cliente asociado al calficacion.
     * @return el nuevo cliente asociado.
     */
    public ClienteEntity replaceCliente(Long photoId, Long calificacionesId, Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el cliente del calficacion calficacion con id = {0}", calificacionesId);
        ClienteEntity clienteEntity = clientePersistence.get(clientesId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(photoId,calificacionesId);
        calificacionEntity.setClienteCalificador(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el cliente con id = {0} al calficacion con id = " + calificacionesId, clientesId);
        return clienteEntity;
    }

    /**
     * Borrar el cliente de un calficacion
     *
     * @param calificacionesId El calficacion que se desea borrar del cliente.
     * @throws BusinessLogicException si el calficacion no tiene cliente
     */
    public void removeCliente(Long photoId, Long calificacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el cliente del calficacion con id = {0}", calificacionesId);
        CalificacionEntity calificacionEntity = calificacionPersistence.find(photoId,calificacionesId);
        if (calificacionEntity.getClienteCalificador()== null) {
            throw new BusinessLogicException("El calficacion no tiene cliente");
        }
        ClienteEntity clienteEntity = clientePersistence.get(calificacionEntity.getClienteCalificador().getId());
        calificacionEntity.setClienteCalificador(null);
        //clienteEntity.getCalificaciones().remove(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el cliente con id = {0} del calficacion con id = " + calificacionesId, clienteEntity.getId());
    }
    
    /**
     * Obtiene una colección de instancias de FotografoEntity asociadas a una
     * instancia de InteresFotografico
     *
     * @param interesesFotograficosId Identificador de la instancia de InteresFotografico
     * @return Colección de instancias de FotografoEntity asociadas a la instancia
     * de InteresFotografico
     */
    public ClienteEntity getClientes(Long photoId, Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores del libro con id = {0}", calificacionesId);
        return calificacionPersistence.find(photoId, calificacionesId).getClienteCalificador();
    }
}
