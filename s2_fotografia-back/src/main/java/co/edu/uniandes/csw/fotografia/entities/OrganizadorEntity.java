/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa un autor en la persistencia y permite su serialización
 *
 * @author Nicolas Melendez
 */
@Entity
public class OrganizadorEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @OneToMany(mappedBy = "organizador",fetch=FetchType.LAZY)
    private List<ConcursoEntity> concursos = new ArrayList<>();
    
    private String nombre;
    private String apellido;
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
    
    /**
     * Obtiene la colección de concursos.
     *
     * @return colección concursos.
     */
    public List<ConcursoEntity> getConcursos() {
        return concursos;
    }

    /**
     * Establece el valor de la colección de concursos.
     *
     * @param pConcursos nuevo valor de la colección.
     */
    public void setConcursos(List<ConcursoEntity> pConcursos) {
        this.concursos = pConcursos;
    }
    public void vacio(){
        
    }
    
}
