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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.acostav
 */
@Entity
public class FotografoEntity extends BaseEntity implements Serializable{
    private String nombre;
    private String hobbies;
    private String descrip;
    private String apellido;
    private String fechaNacimiento;
    private Integer edad;
    private String correo;
    private Integer telefono;
    private String pais;
    private String password;
    private String login;
    private String foto;
    @PodamExclude
    @OneToMany(mappedBy = "fotografo")
    private List<PhotoEntity> fotos = new ArrayList<PhotoEntity>();
    @PodamExclude
    @OneToMany(mappedBy = "concursante")
    private List<PhotoEntity> fotosConcurso = new ArrayList<PhotoEntity>();
    @PodamExclude
    @ManyToMany(mappedBy="fotografos")
    private List<ConcursoEntity> concursos = new ArrayList<ConcursoEntity>();

    
    
    public void setFotos(List<PhotoEntity> p){
        fotos = p;
    }
    
    public List<PhotoEntity> getFotos(){
        return fotos;
    }
    
    public void setFotosConcurso(List<PhotoEntity> p){
        fotosConcurso = p;
    }
    
    public List<PhotoEntity> getFotosConcurso(){
        return fotosConcurso;
    }
     public void setConcursos(List<ConcursoEntity> p){
        concursos = p;
    }
     

    
    public List<ConcursoEntity> getConcursos
        (){
        return concursos;
    }
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
    public void setLogin(String pLogin){
        login = pLogin;
    }
    public String getLogin(){
        return login;
    }
    public void setPassword(String pPassword){
        password = pPassword;
    }
    public String getPassword(){
        return password;
    }
    
    public void setFoto(String pFoto){
        foto = pFoto;}
    public String getFoto(){
        
        return foto;
    }
    
     public String getHobbies(){
        return hobbies;
    }
    public void setHobbies(String pH){
        hobbies = pH;
    }
    
    public String getDescrp(){
        return descrip;
    }
    public void setDescrp(String p){
        descrip = p;
    }
    
}

