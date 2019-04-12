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
 * @author da.benavides
 */
@Stateless
public class PhotoPersistance {
    
private static final Logger LOGGER = Logger.getLogger(PhotoPersistance.class.getName());
    /**
     * EntityManager con el PersistenceUnit
     */
    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;
    /**
     * Crea la foto
     * @param fotoEntity la foto que se quiere crear
     * @return el mismo objeto
     */
    public PhotoEntity create(PhotoEntity fotoEntity){
        LOGGER.log(Level.INFO,"Creando un foto nuevo");
        em.persist(fotoEntity);
        LOGGER.log(Level.INFO,"Saliendo de crear una foto nueva");
        return fotoEntity;
    }
    /**
     * Busca con un query todos los objetos de la base de datos de PhotoEntity
     * @return Lista de PhotoEntity
     */
    public List<PhotoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las fotos");
        // Se crea un query para buscar todos los fotografos en la base de datos.
        TypedQuery query = em.createQuery("select u from PhotoEntity u", PhotoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de fotografos.
        return query.getResultList();
    }
    /**
     * Busca en la base de datos el objeto dado un id
     * @param fotografoId id del objeto que se quiere
     * @return Objeto buscado
     */
    public PhotoEntity find(Long fotografoId) {
        LOGGER.log(Level.INFO, "Consultando el fotografo con id={0}", fotografoId);
       
        return em.find(PhotoEntity.class, fotografoId);
    }
    /**
     * Actualiza La informacion de una foto con un id dado
     * @param fotografoEntity foto que se quiere actualizar
     * @return photo actualizada
     */
     public PhotoEntity update(PhotoEntity fotografoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el fotografo con id={0}", fotografoEntity.getId());
       
        return em.merge(fotografoEntity);
    }
     /**
      * Borra una foto en la base de datos con id dado
      * @param fId id de la foto que se quiere borrar
      */
     public void delete(Long fId) {

        LOGGER.log(Level.INFO, "Borrando el fotografo con id={0}", fId);
        PhotoEntity photoEntity = em.find(PhotoEntity.class, fId);
        em.remove(photoEntity);
    }
     
         
}
