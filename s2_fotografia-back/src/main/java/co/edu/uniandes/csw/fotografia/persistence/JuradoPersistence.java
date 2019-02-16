/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
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
public class JuradoPersistence {
    private static final Logger LOGGER = Logger.getLogger(JuradoPersistence.class.getName());

    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;
    
     /* Crea un jurado en la base de dato
     * @param juradoEntity objeto jurado que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public JuradoEntity create(JuradoEntity juradoEntity) {
        LOGGER.log(Level.INFO, "Creando un jurado nuevo");
        em.persist(juradoEntity);
        LOGGER.log(Level.INFO, "Jurado creado");
        return juradoEntity;
    }

    /**
     * Devuelve todas los jurados de la base de datos.
     *
     * @return una lista con todas las jurados que encuentre en la base de
     * datos, "select u from JuradoEntity u" es como un "select * from
     * JuradoEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<JuradoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando tods los jurados");
        // Se crea un query para buscar todas las jurados en la base de datos.
        TypedQuery query = em.createQuery("select u from JuradoEntity u", JuradoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de jurados.
        return query.getResultList();
    }

    /**
     * Busca si hay algun jurado con el id que se envía de argumento
     *
     * @param juradoId: id correspondiente a la jurado buscada.
     * @return un jurado.
     */
    public JuradoEntity find(Long juradoId) {
        LOGGER.log(Level.INFO, "Consultando el jurado con id={0}", juradoId);
        return em.find(JuradoEntity.class, juradoId);
    }

    /**
     * Actualiza un jurado.
     *
     * @param juradoEntity: el jurado que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un jurado con los cambios aplicados.
     */
    public JuradoEntity update(JuradoEntity juradoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el jurado con id={0}", juradoEntity.getId());
        return em.merge(juradoEntity);
    }

    /**
     * Borra un jurado de la base de datos recibiendo como argumento el id de
     * la jurado
     *
     * @param juradoId: id correspondiente al jurado a borrar.
     */
    public void delete(Long juradoId) {

        LOGGER.log(Level.INFO, "Borrando el jurado con id={0}", juradoId);
        JuradoEntity juradoEntity = em.find(JuradoEntity.class, juradoId);
        em.remove(juradoEntity);
    }
}
