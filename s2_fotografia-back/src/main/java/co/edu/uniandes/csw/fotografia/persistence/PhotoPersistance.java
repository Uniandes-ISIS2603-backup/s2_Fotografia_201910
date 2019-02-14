/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import javax.ejb.Stateless;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.TypedQuery;
/**
 *
 * @author estudiante
 */
@Stateless
public class PhotoPersistance {
    
private static final Logger LOGGER = Logger.getLogger(PhotoPersistance.class.getName());
    
    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;
    
    public PhotoEntity create(PhotoEntity fotografoEntity){
        LOGGER.log(Level.INFO,"Creando un fotografo nuevo");
        em.persist(fotografoEntity);
        LOGGER.log(Level.INFO,"Saliendo de crear una editorial nueva");
        return fotografoEntity;
    }
    
    public List<PhotoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los fotografos");
        // Se crea un query para buscar todos los fotografos en la base de datos.
        TypedQuery query = em.createQuery("select u from FotografoEntity u", PhotoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de fotografos.
        return query.getResultList();
    }
    
    public PhotoEntity find(Long fotografoId) {
        LOGGER.log(Level.INFO, "Consultando el fotografo con id={0}", fotografoId);
       
        return em.find(PhotoEntity.class, fotografoId);
    }
    
     public PhotoEntity update(PhotoEntity fotografoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el fotografo con id={0}", fotografoEntity.getId());
       
        return em.merge(fotografoEntity);
    }
     
     public void delete(Long fId) {

        LOGGER.log(Level.INFO, "Borrando el fotografo con id={0}", fId);
        PhotoEntity fotografoEntity = em.find(PhotoEntity.class, fId);
        em.remove(fotografoEntity);
    }
     
         
}
