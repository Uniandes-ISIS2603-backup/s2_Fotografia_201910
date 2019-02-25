/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
/**
 *
 * @author n.rincond
 */
@Stateless
public class ConcursoPersistence {
    private static final Logger LOGGER = Logger.getLogger(ConcursoPersistence.class.getName());
    
    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;
    /**
     * Método para persistir la entidad en la base de datos.
     * @param concursoEntity objeto de concurso que se creara en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos
     */
    public ConcursoEntity create(ConcursoEntity concursoEntity){
        LOGGER.log(Level.INFO, "Creando un nuevo concurso");
        
        em.persist(concursoEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo concurso");
        return concursoEntity;
    }
    /**
     * Borra un concurso de la base de datos a partir de un id que recibe
     * @param concursoID id correspondiente al concurso a borrar.
     */
    public void delete(Long concursoID){
        LOGGER.log(Level.INFO, "Borrando concurso con id = {0}", concursoID);
        ConcursoEntity entity = em.find(ConcursoEntity.class, concursoID);
        em.remove(entity);
    }
    
    public ConcursoEntity find(Long concursoId){
                LOGGER.log(Level.INFO, "Consultando el cliente con id{0}", concursoId);
        return em.find(ConcursoEntity.class, concursoId);
    }
    
    public ConcursoEntity update(ConcursoEntity concursoEntity){
        LOGGER.log(Level.INFO, "Actualizando el concurso con id={0}", concursoEntity.getId());
        return em.merge(concursoEntity);
    }
    
    public List<ConcursoEntity> findAll(){
        LOGGER.log(Level.INFO,"Consultando todos los concursos");
        TypedQuery<ConcursoEntity> query = em.createQuery("Select u from ConcursoEntity u", ConcursoEntity.class);
        return query.getResultList();
    }
}
