/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.OrganizadorEntity;
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
public class OrganizadorPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(OrganizadorPersistence.class.getName());
    
    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;
    
    public OrganizadorEntity create(OrganizadorEntity organizadorEntity){
        LOGGER.log(Level.INFO,"Creando un organizador nuevo");
        em.persist(organizadorEntity);
        LOGGER.log(Level.INFO,"Saliendo de crear un organizador nuevo");
        return organizadorEntity;
    }
    
    public List<OrganizadorEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los organizadores");
        // Se crea un query para buscar todos los fotografos en la base de datos.
        TypedQuery query = em.createQuery("select u from OrganizadorEntity u", OrganizadorEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de fotografos.
        return query.getResultList();
    }
    
    public OrganizadorEntity find(Long organizadorId) {
        LOGGER.log(Level.INFO, "Consultando el organizador con id={0}", organizadorId);
       
        return em.find(OrganizadorEntity.class, organizadorId);
    }
    
     public OrganizadorEntity update(OrganizadorEntity organizadorEntity) {
        LOGGER.log(Level.INFO, "Actualizando el organizador con id={0}", organizadorEntity.getId());
       
        return em.merge(organizadorEntity);
    }
     
     public void delete(Long oId) {

        LOGGER.log(Level.INFO, "Borrando el organizador con id={0}", oId);
        OrganizadorEntity organizadorEntity = em.find(OrganizadorEntity.class, oId);
        em.remove(organizadorEntity);
    }
     
     
}
