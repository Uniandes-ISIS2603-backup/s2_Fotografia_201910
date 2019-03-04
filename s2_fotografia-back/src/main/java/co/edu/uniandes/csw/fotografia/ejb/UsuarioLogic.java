/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.UsuarioEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class UsuarioLogic {
      private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject
    private UsuarioPersistence persistence;
    
    public UsuarioEntity createUsuario(UsuarioEntity usuario) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de creación del usuario");
        if(!usuario.getLogin().matches("(.*)@(.*)")){
            throw new BusinessLogicException("El login debe ser un correo ");
        }
        if(usuario.getNombreDeUsuario() == null || usuario.getNombreDeUsuario().length() <= 0){
            throw new BusinessLogicException("El nombre de usurario no puede estar vacio");
        }
        UsuarioEntity newUsuarioEntity = persistence.create(usuario);
        LOGGER.log(Level.INFO, "Termina proceso de creación del usuario");
        return newUsuarioEntity;
    }
    
    public UsuarioEntity getUsuario(long pId){
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el usuario con id = {0}", pId);
        UsuarioEntity usuarioEntity = persistence.find(pId);
        if (usuarioEntity == null) {
            LOGGER.log(Level.SEVERE, "El usuario con el id = {0} no existe", pId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el usuario con id = {0}", pId);
        return usuarioEntity;
    }
    
    public List<UsuarioEntity> getUsuarios(){
       LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los usuarios");
        List<UsuarioEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los usuarios");
        return lista;
    }
    
     public UsuarioEntity updateUsuario(Long usuarioId, UsuarioEntity entity){
       LOGGER.log(Level.INFO, "Inicia proceso de actualizar el usuario con id = {0}", usuarioId);
        UsuarioEntity newUsuarioEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el usuario con id = {0}", usuarioId);
        return newUsuarioEntity;
     }
     
     public void deleteUsuario(Long usuarioId){
         
     }
    
}
