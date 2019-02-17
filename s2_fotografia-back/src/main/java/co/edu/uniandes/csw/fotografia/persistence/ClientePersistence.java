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

    public ClienteEntity create(ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo cliente");

        em.persist(clienteEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo cliente");
        return clienteEntity;
    }

    public ClienteEntity get(Long clienteId) {

        LOGGER.log(Level.INFO, "Consultando el cliente con id{0}", clienteId);
        return em.find(ClienteEntity.class, clienteId);
    }

    public List<ClienteEntity> getAll() {
        LOGGER.log(Level.INFO, "Consultando todos los clientes");
        TypedQuery<ClienteEntity> query = em.createQuery("select u from ClienteEntity u", ClienteEntity.class);
        return query.getResultList();

    }

    public ClienteEntity set(ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Actualizando el cliente id={0}", clienteEntity.getId());

        return em.merge(clienteEntity);
    }

    public void delete(Long clienteId) {

        LOGGER.log(Level.INFO, "Borrando el cliente con id={0}", clienteId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
        ClienteEntity clienteEntity = em.find(ClienteEntity.class, clienteId);

        em.remove(clienteEntity);
    }
}
