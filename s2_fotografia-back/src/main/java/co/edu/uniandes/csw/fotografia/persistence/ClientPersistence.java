/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.ClientEntity;
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
public class ClientPersistence {

    private static final Logger LOGGER = Logger.getLogger(ClientPersistence.class.getName());

    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;

    public ClientEntity create(ClientEntity clientEntity) {
        LOGGER.log(Level.INFO, "Creando un cliente nuevo");

        em.persist(clientEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un cliente nuevo");
        return clientEntity;
    }

    public ClientEntity get(Long clientId) {

        LOGGER.log(Level.INFO, "Consultando el cliente con id{0}", clientId);
        return em.find(ClientEntity.class, clientId);
    }

    public List<ClientEntity> getAll() {
        LOGGER.log(Level.INFO, "Consultando todos los clientes");
        TypedQuery<ClientEntity> query = em.createQuery("select u from ClientEntity u", ClientEntity.class);
        return query.getResultList();

    }

    public ClientEntity set(ClientEntity clientEntity) {
        LOGGER.log(Level.INFO, "Actualizando el cliente con id={0}", clientEntity.getId());

        return em.merge(clientEntity);
    }

    public void delete(Long clientId) {

        LOGGER.log(Level.INFO, "Borrando la factura con id={0}", clientId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
        ClientEntity clientEntity = em.find(ClientEntity.class, clientId);

        em.remove(clientEntity);
    }

}
