/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.ClientePersistence;
import co.edu.uniandes.csw.fotografia.persistence.ConcursoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Concurso y Cliente
 *
 * @cliente ISIS2603
 */
@Stateless
public class ConcursoClienteLogic {

    private static final Logger LOGGER = Logger.getLogger(ConcursoClienteLogic.class.getName());

    @Inject
    private ClientePersistence clientePersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private ConcursoPersistence concursoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Agregar un cliente a un concurso
     *
     * @param concursosId El id concurso a guardar
     * @param clientesId El id del cliente al cual se le va a guardar el concurso.
     * @return El concurso que fue agregado al cliente.
     */
    public ClienteEntity addCliente(Long clientesId, Long concursosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar el cliente con id = {0} al concurso con id = " + concursosId, clientesId);
        ClienteEntity clienteEntity = clientePersistence.get(clientesId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        concursoEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el cliente con id = {0} al concurso con id = " + concursosId, clientesId);
        return clientePersistence.get(clientesId);
    }

    /**
     *
     * Obtener un concurso por medio de su id y el de su cliente.
     *
     * @param concursosId id del concurso a ser buscado.
     * @return el cliente solicitada por medio de su id.
     */
    public ClienteEntity getCliente(Long concursosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el cliente del concurso con id = {0}", concursosId);
        ClienteEntity clienteEntity = concursoPersistence.find(concursosId).getCliente();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el cliente del concurso con id = {0}", concursosId);
        return clienteEntity;
    }

    /**
     * Remplazar cliente de un concurso
     *
     * @param concursosId el id del concurso que se quiere actualizar.
     * @param clientesId El id del nuebo cliente asociado al concurso.
     * @return el nuevo cliente asociado.
     */
    public ClienteEntity replaceCliente(Long concursosId, Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el cliente del concurso concurso con id = {0}", concursosId);
        ClienteEntity clienteEntity = clientePersistence.get(clientesId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        concursoEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el cliente con id = {0} al concurso con id = " + concursosId, clientesId);
        return clientePersistence.get(clientesId);
    }

    /**
     * Borrar el cliente de un concurso
     *
     * @param concursosId El concurso que se desea borrar del cliente.
     * @throws BusinessLogicException si el concurso no tiene cliente
     */
    public void removeCliente(Long concursosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el cliente del concurso con id = {0}", concursosId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        if (concursoEntity.getCliente() == null) {
            throw new BusinessLogicException("El concurso no tiene cliente");
        }
        ClienteEntity clienteEntity = clientePersistence.get(concursoEntity.getCliente().getId());
        concursoEntity.setCliente(null);
        clienteEntity.getConcursosCliente().remove(concursoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el cliente con id = {0} del concurso con id = " + concursosId, clienteEntity.getId());
    }
}
