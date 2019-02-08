/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;
import co.edu.uniandes.csw.fotografia.adapters.DateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 *
 * @author s.acostav
 *FotografoDTO Objeto de transferencia de datos de Fotografos. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {  
 *      "interesesFotograficos": ArrayList,
 *      "id": number,
 *      "nombre": string,
 *      "apellido": String,
 *      "fechaNacimiento": date,
 *      "edad":int,
 *      "correo": String,
 *      "telefono": int,
 *      "pais":String,
 *      "telefono": int,
 *      "pais": String,
 *      "id": long
 * 
 *   }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "interesesFotograficos": ["paisaje, retrato"],
 *      "id": "123",
 *      "nombre": "Sara",
 *      "apellido": "Acosta",
 *      "fechaNacimiento": "27/12/99",
 *      "edad": "12",
 *      "correo": "s.acostav,
 *      "telefono": "319",
 *      "pais": "Colombia",
 *      "telefono": "365",
 *      "pais": "Colombia",
 *      "id": "246"
 *   }
 *
 * </pre>
 *
 * @author s.acostav
 */
public class FotografoDTO implements Serializable  {
    
    private ArrayList<String> interesesFotograficos;
    private String nombre;
    private String apellido;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaNacimiento;
    private int edad;
    private String correo;
    private int telefono;
    private String pais;
    private long id;
    
    
    /**
     * Constructor vacio
     */
    public FotografoDTO(){
        
    }
    
    /**
     * obtiene el atributo interesesFotograficos
     * @return atributo interesesFotograficos
     */
    
    public ArrayList<String> getInteresesFotograficos(){
        return interesesFotograficos;
    }
    
    /**
     * Establece un valor para el atributo interesesFotograficos
     * @param pIntereses nuevo valor para el atributo
     */
    public void setInteresesFotograficos(ArrayList<String> pIntereses){
        interesesFotograficos = pIntereses;
    }
    
    /**
     * Obtiene el atributo nombre
     * @return atributo nombre 
     */
    
    public String getNombre(){
        return nombre;
    }
    /**
     * Establece un valor para el atributo nombre
     * @param pNombre nuevo valor para el atributo 
     */
    public void setNombre(String pNombre){
        nombre = pNombre;
    }
    /**
     * Obtiene el atributo apellido 
     * @return atributo apellido
     */
    public String getApellido(){
        return apellido;
    }
    
    /**
     * Establece un valor para el atributo apellido
     * @param pApellido 
     */
    public void setApellido(String pApellido){
        apellido = pApellido;
    }
    /**
     * Obtiene el atributo fechaNacimiento
     * @return atributo fechaNacimiento
     */
    public Date getFechaNacimiento(){
        return fechaNacimiento;
    }
    
    /**
     * Establece un valor para el atributo fecha nacimiento
     * @param pFecha nuevo valor para el atributo 
     */
    public void setFechaNacimiento(Date pFecha){
        fechaNacimiento = pFecha;
    }
    /**
     * Obtiene el atributo edad
     * @return atributo edad
     */
    public int getEdad(){
        return edad;
    }
    
    /**
     * Establece un valor para el atributo edad
     * @param pEdad nuevo valor para el atributo
     */
    public void setEdad(int pEdad) {
        edad = pEdad;
    }
    
    /**
     * Obtiene el atributo correo
     * @return atributo correo
     */
    
    public String getCorreo(){
        return correo;
    }
    
    /**
     * Establece un valor para el atributo correo 
     * @param pCorreo nuevo valor del atributo correo
     */
    
    public void setCorreo(String pCorreo){
        correo = pCorreo;
    }
    /**
     *Obtiene el atributo telefono 
     * @return atributo telefono
     */
    public int getTelefono(){
        return telefono;
    }
    /**
     * Establece un valor para el atributo telefono
     * @param tel nuevo valor del atributo 
     */
    
    public void setTelefono(int tel){
        telefono = tel;
    }
        /**
     *Obtiene el atributo pais
     * @return atributo pais
     */
    
    public String getPais(){
        return pais;
    }
    /**
     * Establece un valor para el atributo pais
     * @param pPais nuevo valor del atributo 
     */
    public void setPais(String pPais){
        pais = pPais;
    }
        /**
     *Obtiene el atributo id 
     * @return atributo id
     */
    
    public long getId(){
        return id;
    }
    /**
     * Establece un valor para el atributoid
     * @param pId nuevo valor del atributo 
     */
    public void setId(long pId){
        id = pId;
    }
      @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    }