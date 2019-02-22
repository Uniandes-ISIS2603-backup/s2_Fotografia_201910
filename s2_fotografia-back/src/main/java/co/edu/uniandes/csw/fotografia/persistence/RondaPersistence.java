/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.RondaEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author nicolas melendez
 */

@Stateless
public class RondaPersistence {
    private static final Logger LOGGER = Logger.getLogger(RondaPersistence.class.getName());
    
    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;
    
    public RondaEntity create(RondaEntity rondaEntity){
        LOGGER.log(Level.INFO,"Creando una ronda nueva");
        em.persist(rondaEntity);
        LOGGER.log(Level.INFO,"Saliendo de crear una ronda nueva");
        return rondaEntity;
    }
    
    public List<RondaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las rondas");
        // Se crea un query para buscar todos las rondas en la base de datos.
        TypedQuery query = em.createQuery("select u from RondaEntity u", RondaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de rondas.
        return query.getResultList();
    }
    
    public RondaEntity find(Long rondaId) {
        LOGGER.log(Level.INFO, "Consultando la ronda con id={0}", rondaId);
       
        return em.find(RondaEntity.class, rondaId);
    }
    
     public RondaEntity update(RondaEntity rondaEntity) {
        LOGGER.log(Level.INFO, "Actualizando el ronda con id={0}", rondaEntity.getId());
       
        return em.merge(rondaEntity);
    }
     
     public void delete(Long rId) {

        LOGGER.log(Level.INFO, "Borrando la ronda con id={0}", rId);
        RondaEntity rondaEntity = em.find(RondaEntity.class, rId);
        em.remove(rondaEntity);
    }
     
}
