/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author s.acostav
 */
@Entity
public class FotografoEntity extends BaseEntity implements Serializable{
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private Integer edad;
    private String correo;
    private Integer telefono;
    private String pais;
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String pNombre){
        nombre = pNombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public void setApellido(String pApellido){
        apellido = pApellido;
    }
    public String getFechaNacimiento(){
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento(String fecha){
        fechaNacimiento = fecha;
    }
    public Integer getEdad(){
        return edad;
    }
    public void setEdad(Integer ed){
        edad = ed;
    }
    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String pCorreo){
        correo = pCorreo;
    }
    public Integer getTelefono(){
        return telefono;
    }
    public void setTelefono(Integer pTelefono){
        telefono = pTelefono;
    }
    public String getPais(){
        return pais;
    }
    
    public void setPais(String pPais){
        pais = pPais;
    }
    
}
