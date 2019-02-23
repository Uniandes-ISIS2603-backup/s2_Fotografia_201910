/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
/**
 *
 * @author s.acostav
 */

@Stateless
public class FotografoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(FotografoPersistence.class.getName());
    
    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;
    
    public FotografoEntity create(FotografoEntity fotografoEntity){
        LOGGER.log(Level.INFO,"Creando un fotografo nuevo");
        em.persist(fotografoEntity);
        LOGGER.log(Level.INFO,"Saliendo de crear una editorial nueva");
        return fotografoEntity;
    }
    
    public List<FotografoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los fotografos");
        // Se crea un query para buscar todos los fotografos en la base de datos.
        TypedQuery query = em.createQuery("select u from FotografoEntity u", FotografoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de fotografos.
        return query.getResultList();
    }
    
    public FotografoEntity find(Long fotografoId) {
        LOGGER.log(Level.INFO, "Consultando el fotografo con id={0}", fotografoId);
       
        return em.find(FotografoEntity.class, fotografoId);
    }
    
    public FotografoEntity findByLogin(String login){
        LOGGER.log(Level.INFO,"Consultando el fotografo con login={0}", login);
        TypedQuery query = em.createQuery("Select e From FotografoEntity f where f.login = :login", FotografoEntity.class);
        query = query.setParameter("login",login);
         FotografoEntity result;
         List<FotografoEntity> sameLogin = query.getResultList();
        if (sameLogin == null) {
            result = null;
        } else if (sameLogin.isEmpty()) {
            result = null;
        } else {
            result = sameLogin.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar libros por isbn ", login);
        return result;
    }
    
     public FotografoEntity update(FotografoEntity fotografoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el fotografo con id={0}", fotografoEntity.getId());
       
        return em.merge(fotografoEntity);
    }
     
     public void delete(Long fId) {

        LOGGER.log(Level.INFO, "Borrando el fotografo con id={0}", fId);
        FotografoEntity fotografoEntity = em.find(FotografoEntity.class, fId);
        em.remove(fotografoEntity);
    }
     
     
}
