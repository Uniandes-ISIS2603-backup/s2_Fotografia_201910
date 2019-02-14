/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.ReceiptEntity;
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
public class ReceiptPersistence {

    private static final Logger LOGGER = Logger.getLogger(ClientPersistence.class.getName());

    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;

    public ReceiptEntity create(ReceiptEntity receiptEntity) {
        LOGGER.log(Level.INFO, "Creando una factura nuevo");

        em.persist(receiptEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una factura nuevo");
        return receiptEntity;
    }

    public ReceiptEntity get(Long receiptId) {

        LOGGER.log(Level.INFO, "Consultando la factura con id{0}", receiptId);
        return em.find(ReceiptEntity.class, receiptId);
    }

    public List<ReceiptEntity> getAll() {
        LOGGER.log(Level.INFO, "Consultando todas las facturas");
        TypedQuery<ReceiptEntity> query = em.createQuery("select u from ReceiptEntity u", ReceiptEntity.class);
        return query.getResultList();

    }

    public ReceiptEntity set(ReceiptEntity receiptEntity) {
        LOGGER.log(Level.INFO, "Actualizando la factura con id={0}", receiptEntity.getId());

        return em.merge(receiptEntity);
    }

    public void delete(Long receiptId) {

        LOGGER.log(Level.INFO, "Borrando la factura con id={0}", receiptId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
        ReceiptEntity receiptEntity = em.find(ReceiptEntity.class, receiptId);

        em.remove(receiptEntity);
    }
}
