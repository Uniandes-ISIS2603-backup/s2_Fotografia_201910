/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
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
public class FormaDePagoPersistence 
{
        private static final Logger LOGGER = Logger.getLogger(FormaDePagoPersistence.class.getName());

    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;

    public FormaDePagoEntity create(FormaDePagoEntity formaDePagoEntity) {
        LOGGER.log(Level.INFO, "Creando una nueva forma de pago");

        em.persist(formaDePagoEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una nueva forma de pago");
        return formaDePagoEntity;
    }

    public FormaDePagoEntity get(Long formaDePagoId) {

        LOGGER.log(Level.INFO, "Consultando la forma de pago con id{0}", formaDePagoId);
        return em.find(FormaDePagoEntity.class, formaDePagoId);
    }

    public List<FormaDePagoEntity> getAll() {
        LOGGER.log(Level.INFO, "Consultando todas las formas de pago");
        TypedQuery<FormaDePagoEntity> query = em.createQuery("select u from FormaDePagoEntity u", FormaDePagoEntity.class);
        return query.getResultList();

    }

    public FormaDePagoEntity set(FormaDePagoEntity formaDePagoEntity) {
        LOGGER.log(Level.INFO, "Actualizando la forma de pago con id={0}", formaDePagoEntity.getId());

        return em.merge(formaDePagoEntity);
    }

    public void delete(Long formaDePagoId) {

        LOGGER.log(Level.INFO, "Borrando la forma de pago con id={0}", formaDePagoId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
        FormaDePagoEntity formaDePagoEntity = em.find(FormaDePagoEntity.class, formaDePagoId);

        em.remove(formaDePagoEntity);
    }
}
