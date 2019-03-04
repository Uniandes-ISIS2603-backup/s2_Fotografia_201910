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

    /**
     * Crea una nueva forma de pago
     * @param formaDePagoEntity la forma de pago a crear
     * @return la forma de pago creada
     */
    public FormaDePagoEntity create(FormaDePagoEntity formaDePagoEntity) {
        LOGGER.log(Level.INFO, "Creando una nueva forma de pago");

        em.persist(formaDePagoEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una nueva forma de pago");
        return formaDePagoEntity;
    }

    /**
     * Devuelve la forma de pago con el id ingresado
     * @param formaDePagoId el id de la forma de pago buscada
     * @return la forma de pago buscada
     */
    public FormaDePagoEntity get(Long formaDePagoId) {

        LOGGER.log(Level.INFO, "Consultando la forma de pago con id{0}", formaDePagoId);
        return em.find(FormaDePagoEntity.class, formaDePagoId);
    }
    
    /**
     * Busca una forma de pago por numero de tarjeta
     * @param numeroTarjeta el numero de tarjeta por el cual se buscaea
     * @return la tarjeta con el numero de tarjeta buscado
     */
    public FormaDePagoEntity getByNumeroTarjeta(Long numeroTarjeta)
    {
        LOGGER.log(Level.INFO, "Buscando la forma de pago por numero de tarjeta", numeroTarjeta);
        
        TypedQuery query = em.createQuery("Select f From FormaDePagoEntity f where f.numeroTarjeta = :numeroTarjeta", FormaDePagoEntity.class);
        
        query = query.setParameter("numeroTarjeta", numeroTarjeta);
        
        List<FormaDePagoEntity> sameNumeroTarjeta = query.getResultList();
        FormaDePagoEntity result;
        if (sameNumeroTarjeta == null) {
            result = null;
        } else if (sameNumeroTarjeta.isEmpty()) {
            result = null;
        } else {
            result = sameNumeroTarjeta.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar forma de pago por numero de tarjeta ", numeroTarjeta);
        return result;
    }

    /**
     * Devuelve la lista de todas las formas de pago
     * @return lista con todas las formas de pago
     */
    public List<FormaDePagoEntity> getAll() {
        LOGGER.log(Level.INFO, "Consultando todas las formas de pago");
        TypedQuery<FormaDePagoEntity> query = em.createQuery("select u from FormaDePagoEntity u", FormaDePagoEntity.class);
        return query.getResultList();

    }

    /**
     * Actualiza la informa de la forma de pago 
     * @param formaDePagoEntity informacion que actualizara
     * @return la forma de pago actualizada
     */
    public FormaDePagoEntity set(FormaDePagoEntity formaDePagoEntity) {
        LOGGER.log(Level.INFO, "Actualizando la forma de pago con id={0}", formaDePagoEntity.getId());

        return em.merge(formaDePagoEntity);
    }

    /**
     * Elimina la forma de pago con el id ingresado por parametro
     * @param formaDePagoId id de la forma de pago a eliminar
     */
    public void delete(Long formaDePagoId) {

        LOGGER.log(Level.INFO, "Borrando la forma de pago con id={0}", formaDePagoId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
        FormaDePagoEntity formaDePagoEntity = em.find(FormaDePagoEntity.class, formaDePagoId);

        em.remove(formaDePagoEntity);
    }
}
