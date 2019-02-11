/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.entities;

/**
 *
 * @author estudiante
 */
public class FotografoEntity {
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private int edad;
    private String correo;
    private int telefono;
    private String pais;
    private Long id;
    
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
    public int getEdad(){
        return edad;
    }
    public void setEdad(int ed){
        edad = ed;
    }
    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String pCorreo){
        correo = pCorreo;
    }
    public int getTelefono(){
        return telefono;
    }
    public void setTelefono(int pTelefono){
        telefono = pTelefono;
    }
    public String getPais(){
        return pais;
    }
    
    public void setPais(String pPais){
        pais = pPais;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long pId){
        id = pId;
    }
    
}
