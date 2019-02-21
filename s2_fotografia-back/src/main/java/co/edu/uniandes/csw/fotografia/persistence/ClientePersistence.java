/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Valentina Duarte
 */

@Stateless
public class ClientePersistence 
{
     private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());
     
    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;

    /**
     * Crea el cliente
     * @param clienteEntity el cliente que se quiere crear
     * @return el cliente creado
     */
    public ClienteEntity create(ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo cliente");

        em.persist(clienteEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo cliente");
        return clienteEntity;
    }

    /**
     * Devuelve el cliente con el id que se pide
     * @param clienteId el id del cliente buscado  
     * @return el cliente buscado. Si no existe retorna null
     */
    public ClienteEntity get(Long clienteId) {

        LOGGER.log(Level.INFO, "Consultando el cliente con id{0}", clienteId);
        return em.find(ClienteEntity.class, clienteId);
    }

    /**
     * Devuelve la lista de todos los clientes
     * @return la lista de clientes
     */
    public List<ClienteEntity> getAll() {
        LOGGER.log(Level.INFO, "Consultando todos los clientes");
        TypedQuery<ClienteEntity> query = em.createQuery("select u from ClienteEntity u", ClienteEntity.class);
        return query.getResultList();
    }
    
    /**
     * Devuelve el cliente con el login que se ingresa como parametro
     * @param login del cliente que se quiere consultar
     * @return el cliente con el login ingresado
     */
    public ClienteEntity getByLogin(String login)
    {
        LOGGER.log(Level.INFO, "Buscando el cliente por su login", login);
        
        TypedQuery query = em.createQuery("Select c From ClienteEntity c where c.login = :login", ClienteEntity.class);
        
        query = query.setParameter("login", login);
        
        List<ClienteEntity> sameLogin = query.getResultList();
        ClienteEntity result;
        if (sameLogin == null) {
            result = null;
        } else if (sameLogin.isEmpty()) {
            result = null;
        } else {
            result = sameLogin.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar cliente por login ", login);
        return result;
    }

    /**
     * Actualiza el cliente que se ingresa por parametro
     * @param clienteEntity el cliente que se quiere actualizar
     * @return El cliente actualizado
     */
    public ClienteEntity set(ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Actualizando el cliente id={0}", clienteEntity.getId());

        return em.merge(clienteEntity);
    }

    /**
     * Elimina el cliente con el id que se ingresa como parametro
     * @param clienteId el id del cliente que se quiere eliminar
     */
    public void delete(Long clienteId) {

        LOGGER.log(Level.INFO, "Borrando el cliente con id={0}", clienteId);
        ClienteEntity clienteEntity = em.find(ClienteEntity.class, clienteId);

        em.remove(clienteEntity);
    }
}
