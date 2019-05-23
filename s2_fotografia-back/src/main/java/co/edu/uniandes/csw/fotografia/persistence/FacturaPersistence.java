/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
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
public class FacturaPersistence 
{
     private static final Logger LOGGER = Logger.getLogger(FacturaPersistence.class.getName());

    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;

    /**
     * Crea una factura
     * @param facturaEntity la factura a crear
     * @return  la factura creada
     */
    public FacturaEntity create(FacturaEntity facturaEntity) {
        LOGGER.log(Level.INFO, "Creando una nueva factura");
        em.persist(facturaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una nueva factura");
        return facturaEntity;
    }

    /**
     * Devuelve la factura con el id ingresado por parametro
     * @param facturaId el id de la factura buscada
     * @return la factura buscada
     */
    public FacturaEntity get(Long facturaId) {

        LOGGER.log(Level.INFO, "Consultando la factura con id{0}", facturaId);
        return em.find(FacturaEntity.class, facturaId);
    }

    /**
     * Devuelve la lista de todas las facturas
     * @return la lista de las facturas
     */
    public List<FacturaEntity> getAll() {
        LOGGER.log(Level.INFO, "Consultando todas las facturas");
        TypedQuery<FacturaEntity> query = em.createQuery("select u from FacturaEntity u", FacturaEntity.class);
        return query.getResultList();

    }
    
     /**
     * Devuelve la factura con el numero que se ingresa como parametro
     * @param numero de la factura que se quiere consultar
     * @return la factura con el numero ingresado
     */
    public FacturaEntity getByNumero(Integer numero)
    {
        LOGGER.log(Level.INFO, "Buscando la factura por su numero", numero);
        
        TypedQuery query = em.createQuery("Select f From FacturaEntity f where f.numero = :numero", FacturaEntity.class);
        
        query = query.setParameter("numero", numero);
        
        List<FacturaEntity> sameNumero = query.getResultList();
        FacturaEntity result;
        if (sameNumero == null) {
            result = null;
        } else if (sameNumero.isEmpty()) {
            result = null;
        } else {
            result = sameNumero.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar factura por numero ", numero);
        return result;
    }

    /**
     * Actualiza la informacion de la factura ingresada
     * @param facturaEntity factura a actualizar
     * @return la factura actualizada
     */
    public FacturaEntity set(FacturaEntity facturaEntity) {
        LOGGER.log(Level.INFO, "Actualizando la factura con id={0}", facturaEntity.getId());

        return em.merge(facturaEntity);
    }

    /**
     * Elimina la factura con el id ingresado
     * @param facturaId id de la factura a eliminar
     */
    public void delete(Long facturaId) {

        LOGGER.log(Level.INFO, "Borrando la factura con id={0}", facturaId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
        FacturaEntity facturaEntity = em.find(FacturaEntity.class, facturaId);

        em.remove(facturaEntity);
    }
    
    public List<FacturaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los fotografos");
        // Se crea un query para buscar todos los fotografos en la base de datos.
        TypedQuery query = em.createQuery("select u from FacturaEntity u", FacturaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de fotografos.
        return query.getResultList();
    }
    
    public FacturaEntity find(Long facturaId) {
        LOGGER.log(Level.INFO, "Consultando el fotografo con id={0}", facturaId);
       
        return em.find(FacturaEntity.class, facturaId);
    }
}
