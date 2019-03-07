/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import java.io.Serializable;


/**
 *
 * @author Valentina Duarte
 */
public class ClienteDTO implements Serializable
{
    private Long id;
    
    private String login;
    
    private String nombre;
    
    private String correo;
    
    private String contrasena;
    
     public ClienteDTO()
     {
         
     }
     
      /**
     * Crea un objeto ClienteDTO a partir de un objeto ClienteEntity.
     *
     * @param clienteEntity Entidad ClienteEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public ClienteDTO(ClienteEntity clienteEntity) {
        if (clienteEntity != null) 
        {
            this.id = clienteEntity.getId();
            this.login = clienteEntity.getLogin();
            this.correo = clienteEntity.getCorreo();
            this.nombre = clienteEntity.getNombre();
            this.contrasena= clienteEntity.getContrasena();
        }
    }

    /**
     * Convierte un objeto ClienteDTO a ClienteEntity.
     *
     * @return Nueva objeto ClienteEntity.
     *
     */
    public ClienteEntity toEntity() {
        
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(this.getId());
        clienteEntity.setNombre(this.getNombre());
        clienteEntity.setLogin(this.getLogin());
        clienteEntity.setCorreo(this.getCorreo());
        clienteEntity.setContrasena(this.getContrasena());
       
        return clienteEntity;
    } 

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the usuario
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the usuario to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
          
}
