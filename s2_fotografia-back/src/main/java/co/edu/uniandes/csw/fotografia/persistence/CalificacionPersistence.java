/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
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
    
     /* Crea una calificacion en la base de dato
     * @param calificacionEntity objeto author que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CalificacionEntity create(CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Creando una calificacion nueva");
        em.persist(calificacionEntity);
        LOGGER.log(Level.INFO, "Calificacion creado");
        return calificacionEntity;
    }

    /**
     * Devuelve todas las authores de la base de datos.
     *
     * @return una lista con todas las calificaciones que encuentre en la base de
     * datos, "select u from CalificacionEntity u" es como un "select * from
     * CalificacionEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<CalificacionEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las calificaciones");
        // Se crea un query para buscar todas las authores en la base de datos.
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de calificaciones.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna calificacion con el id que se envía de argumento
     *
     * @param calificacionId: id correspondiente a la calificacion buscada.
     * @return un calificacion.
     */
    public CalificacionEntity find(Long calificacionId) {
        LOGGER.log(Level.INFO, "Consultando el calificacion con id={0}", calificacionId);
        return em.find(CalificacionEntity.class, calificacionId);
    }

    /**
     * Actualiza una calificacion.
     *
     * @param calificacionEntity: la calificacion que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una author con los cambios aplicados.
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
}

