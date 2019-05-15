/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author a.trujilloa1
 */
@Stateless
public class CalificacionPersistence {
    
    
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());

    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;
    
     /* Crea una calificacion en la base de datos
     * @param calificacionEntity objeto calificacion que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CalificacionEntity create(CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Creando una calificacion nueva");
        em.persist(calificacionEntity);
        LOGGER.log(Level.INFO, "Calificacion creado");
        return calificacionEntity;
    }

    /**
     * Buscar una reseña
     *
     * Busca si hay alguna reseña asociada a un libro y con un ID específico
     *
     * @param photoId El ID del libro con respecto al cual se busca
     * @param calificacionId El ID de la reseña buscada
     * @return La reseña encontrada o null. Nota: Si existe una o más reseñas
     * devuelve siempre la primera que encuentra
     */
    public CalificacionEntity find(Long photoId, Long calificacionId) {
        LOGGER.log(Level.INFO, "Consultando el calificacion con id = {0} del libro con id = " + photoId, calificacionId);
        TypedQuery<CalificacionEntity> q = em.createQuery("select p from PhotoEntity p where (p.photo.id = :photoid) and (p.id = :calificacionId)", CalificacionEntity.class);
        q.setParameter("photoid", photoId);
        q.setParameter("calificacionId", calificacionId);
        List<CalificacionEntity> results = q.getResultList();
        CalificacionEntity calificacion = null;
        if (results == null) {
            calificacion = null;
        } else if (results.isEmpty()) {
            calificacion = null;
        } else if (results.size() >= 1) {
            calificacion = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el calificacion con id = {0} del libro con id =" + photoId, calificacionId);
        return calificacion;
    }

    /**
     * Actualiza una calificacion.
     *
     * @param calificacionEntity: la calificacion que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una calificacion con los cambios aplicados.
     */
    public CalificacionEntity update(CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Actualizando la calificacion con id={0}", calificacionEntity.getId());
        return em.merge(calificacionEntity);
    }

    /**
     * Borra una calificacion de la base de datos recibiendo como argumento el id de
     * la calificacion
     *
     * @param calificacionId: id correspondiente a la calificacion a borrar.
     */
    public void delete(Long calificacionId) {

        LOGGER.log(Level.INFO, "Borrando el author con id={0}", calificacionId);
        CalificacionEntity calificacionEntity = em.find(CalificacionEntity.class, calificacionId);
        em.remove(calificacionEntity);
    }
    
   /**
    * Verifica que el puntaje de la calificacion sea un número entre 1 y 5
    * @param pPuntaje puntaje de la calificacion
    * @return un booleano indicando si esta bien o mal definido el puntaje
    */
    public boolean verificarPuntaje(double pPuntaje)
    {
        boolean bien = false;
        
        if(0 <= pPuntaje && pPuntaje<= 5)
        {
            bien = true;
        }
        
        return bien;
    }
    
    /**
     * Verifica que el comentario sea un String con un tamaño menor o igual a 500
     * @param pComentario comentario de la calificacion
     * @return boolean indicando si esta bien o mal definido el comentario
     */
    public boolean verificarComentario(String pComentario)
    {
        boolean bien = false;
        String comentario = pComentario.replace(" ", "");
        if( comentario.length()<= 500)
        {
            bien = true;
        }
        
        return bien;
    }
    
    /**
     * Busca si hay algun lubro con el id que se envía de argumento
     *
     * @param calificacionId: id correspondiente al libro buscado.
     * @return un libro.
     */
    public CalificacionEntity find(Long calificacionId) {
        LOGGER.log(Level.INFO, "Consultando el libro con id={0}", calificacionId);
        return em.find(CalificacionEntity.class, calificacionId);
    }
}

