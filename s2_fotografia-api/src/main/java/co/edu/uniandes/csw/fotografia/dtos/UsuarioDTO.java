/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.UsuarioEntity;
import java.io.Serializable;

/**
 *
 * @author Nicolas Rincon
 */
public class UsuarioDTO implements Serializable{
    private Long id;
    private String login; //Correo?
    private String nombreDeUsuario;
    private String contrasena;
    
    public UsuarioDTO(){};
    
    public UsuarioDTO(UsuarioEntity usuarioEntity){
        if(usuarioEntity != null){
            this.id = usuarioEntity.getId();
            this.login = usuarioEntity.getLogin();
            this.nombreDeUsuario = usuarioEntity.getNombreDeUsuario();
            this.contrasena = usuarioEntity.getContrasena();
        }
    }
    
    public UsuarioEntity toEntity(){
        UsuarioEntity newEntity = new UsuarioEntity();
        newEntity.setId(this.id);
        newEntity.setLogin(this.login);
        newEntity.setNombreDeUsuario(this.nombreDeUsuario);
        newEntity.setContrasena(this.contrasena);
        return newEntity;
    }
    
    /**
     * Retorna el id del usuario
     * @return id
     */
    public Long getId() {
        return id;
    }
    /**
     * 
     * Establece un valor para el atributo id
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Retrorna el login del usuario
     * @return login El login del usuario
     */
    public String getLogin() {
        return login;
    }
    /**
     * Establece un valor para el atributo login
     * @param login El login del usuario
     */
    public void setLogin(String login) {
        this.login = login;
    }
    /**
     * Retorna el nombre del Usuario
     * @return nombreDeUsuario nombre del usuario
     */
    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }
    /**
     * Establece un valor para el atributo nombredeUsuario
     * @param nombreDeUsuario 
     */
    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }
    /**
     * Retorna la contraseña del usuario
     * @return contraseña del usuario
     */
    public String getContrasena() {
        return contrasena;
    }
    /**
     * Establece un valor para el atributo contraseña
     * @param contraseña Contraseña del usuario
     */
    public void setContrasena(String contraseña) {
        this.contrasena = contraseña;
    }
    
    
    
    
}
