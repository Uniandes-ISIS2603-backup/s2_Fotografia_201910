/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nicolas Rincon
 */
@Stateless
public class UsuarioPersistence {
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());
    
    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;
    /**
     * Método para persistir la entidad en la base de datos.
     * @param usuarioEntity objeto de usuario que se creara en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos
     */
    public UsuarioEntity create(UsuarioEntity usuarioEntity){
        LOGGER.log(Level.INFO, "Creando un nuevo usuario");
        
        em.persist(usuarioEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo usuario");
        return usuarioEntity;
    }
    /**
     * Borra un usuario de la base de datos a partir de un id que recibe
     * @param usuarioID id correspondiente al usuario a borrar.
     */
    public void delete(Long usuarioID){
        LOGGER.log(Level.INFO, "Borrando usuario con id = {0}", usuarioID);
        UsuarioEntity entity = em.find(UsuarioEntity.class, usuarioID);
        em.remove(entity);
    }
    
    public UsuarioEntity find(Long usuarioId){
                LOGGER.log(Level.INFO, "Consultando el cliente con id{0}", usuarioId);
        return em.find(UsuarioEntity.class, usuarioId);
    }
    
    public UsuarioEntity update(UsuarioEntity usuarioEntity){
        LOGGER.log(Level.INFO, "Actualizando el usuario con id={0}", usuarioEntity.getId());
        return em.merge(usuarioEntity);
    }
    
    public List<UsuarioEntity> findAll(){
        LOGGER.log(Level.INFO,"Consultando todos los usuarios");
        TypedQuery<UsuarioEntity> query = em.createQuery("Select u from UsuarioEntity u", UsuarioEntity.class);
        return query.getResultList();
    }
}
