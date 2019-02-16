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
public class ClientePersistence {

    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());

    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;

    public ClienteEntity create(ClienteEntity clientEntity) {
        LOGGER.log(Level.INFO, "Creando un cliente nuevo");

        em.persist(clientEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un cliente nuevo");
        return clientEntity;
    }

    public ClienteEntity get(Long clientId) {

        LOGGER.log(Level.INFO, "Consultando el cliente con id{0}", clientId);
        return em.find(ClienteEntity.class, clientId);
    }

    public List<ClienteEntity> getAll() {
        LOGGER.log(Level.INFO, "Consultando todos los clientes");
        TypedQuery<ClienteEntity> query = em.createQuery("select u from ClientEntity u", ClienteEntity.class);
        return query.getResultList();

    }

    public ClienteEntity set(ClienteEntity clientEntity) {
        LOGGER.log(Level.INFO, "Actualizando el cliente con id={0}", clientEntity.getId());

        return em.merge(clientEntity);
    }

    public void delete(Long clientId) {

        LOGGER.log(Level.INFO, "Borrando el cliente con id={0}", clientId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
        ClienteEntity clientEntity = em.find(ClienteEntity.class, clientId);

        em.remove(clientEntity);
    }

}
