/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.TypedQuery;
/**
 *
 * @author s.acostav
 */

@Stateless
public class InteresFotograficoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(InteresFotograficoPersistence.class.getName());
    
    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;
    
    public InteresFotograficoEntity create(InteresFotograficoEntity interesFotograficoEntity){
        LOGGER.log(Level.INFO,"Creando un interesFotografico nuevo");
        em.persist(interesFotograficoEntity);
        LOGGER.log(Level.INFO,"Saliendo de crear una editorial nueva");
        return interesFotograficoEntity;
    }
    
    public List<InteresFotograficoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los interesFotograficos");
        // Se crea un query para buscar todos los interesFotograficos en la base de datos.
        TypedQuery query = em.createQuery("select u from InteresFotograficoEntity u", InteresFotograficoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de interesFotograficos.
        return query.getResultList();
    }
    
    public InteresFotograficoEntity findByInteres(String interes){
        LOGGER.log(Level.INFO,"Consultando el fotografo con login={0}", interes);
        TypedQuery query = em.createQuery("Select i From  InteresFotograficoEntity i where i.interes = :interes", InteresFotograficoEntity.class);
        query = query.setParameter("interes",interes);
         InteresFotograficoEntity result;
         List<InteresFotograficoEntity> same = query.getResultList();
        if (same == null) {
            result = null;
        } else if (same.isEmpty()) {
            result = null;
        } else {
            result = same.get(0);
        }
        LOGGER.log(Level.INFO, "Consultar el interes ", interes);
        return result;
    }
    
    public InteresFotograficoEntity find(Long interesFotograficoId) {
        LOGGER.log(Level.INFO, "Consultando el interesFotografico con id={0}", interesFotograficoId);
       
        return em.find(InteresFotograficoEntity.class, interesFotograficoId);
    }
    
     public InteresFotograficoEntity update(InteresFotograficoEntity interesFotograficoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el interesFotografico con id={0}", interesFotograficoEntity.getId());
       
        return em.merge(interesFotograficoEntity);
    }
     
     public void delete(Long fId) {

        LOGGER.log(Level.INFO, "Borrando el interesFotografico con id={0}", fId);
        InteresFotograficoEntity interesFotograficoEntity = em.find(InteresFotograficoEntity.class, fId);
        em.remove(interesFotograficoEntity);
    }
     
     
}
