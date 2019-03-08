/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.entities;

import co.edu.uniandes.csw.fotografia.podam.CorreoStrategy;
import java.io.Serializable;
import javax.persistence.Entity;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Nicolas Rincon
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable {
    @PodamStrategyValue(CorreoStrategy.class)
    private String login;
    private String nombreDeUsuario;
    private String contrasena;

    /**
     * Constructor vacio
     */
    public UsuarioEntity(){};
    
    
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
     * @param contrasena Contraseña del usuario
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
    
    
    
    
    
}
