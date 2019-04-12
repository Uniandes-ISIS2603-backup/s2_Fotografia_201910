/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;
import co.edu.uniandes.csw.fotografia.adapters.DateAdapter;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
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
 *      "edad":number,
 *      "correo": String,
 *      "telefono": number,
 *      "pais":String,
 *      "telefono": number,
 *      "pais": String,
 *      "id": number
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
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private int edad;
    private String correo;
    private int telefono;
    private String pais;
    private Long id;
    private String login;
    private String password;
    private String foto;
    
    
    /**
     * Constructor vacio
     */
    public FotografoDTO(){
        
    }
    /**
     * Constructor 
     * @param fotografoEntity 
     */
    public FotografoDTO(FotografoEntity fotografoEntity) {
        if (fotografoEntity != null) {
            this.id = fotografoEntity.getId();
            this.nombre = fotografoEntity.getNombre();
            this.apellido = fotografoEntity.getApellido();
            this.fechaNacimiento = fotografoEntity.getFechaNacimiento();
            this.edad = fotografoEntity.getEdad();
            this.correo = fotografoEntity.getCorreo();
             this.telefono = fotografoEntity.getTelefono();
            this.pais= fotografoEntity.getPais();
            this.login = fotografoEntity.getLogin();
            this.password = fotografoEntity.getPassword();
            this.foto = fotografoEntity.getFoto();
            
           
        }
    }
    
    /**
     * Metodo para transformar el DTO a una entidad
     * @return nueva entidad 
     */
    
    public FotografoEntity toEntity(){
        FotografoEntity fotografo = new FotografoEntity();
        fotografo.setNombre(nombre);
        fotografo.setApellido(apellido);
        fotografo.setId(id);
        fotografo.setFechaNacimiento(fechaNacimiento);
        fotografo.setEdad(edad);
        fotografo.setCorreo(correo);
        fotografo.setTelefono(telefono);
        fotografo.setPais(pais);
        fotografo.setLogin(login);
        fotografo.setPassword(password);
        fotografo.setFoto(foto);
       
        return fotografo;
    }
    /**
     * Obtiene el atributo password
     * @return atributo password
     */
    public String getPassword(){
        return password;
    }
    /**
     * Modifica el valor del atributo password
     * @param clave nuevo valor del atributo 
     */
    public void setPassword(String clave){
        password = clave;
    }
    /**
     * Obtiene el atributo login
     * @return atributo login
     */
    public String getLogin(){
        return login;
    }
    /**
     * Modifica el valor del atributo login
     * @param log nuevo valor del atributo 
     */
    public void setLogin(String log){
        login = log;
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
    public String  getFechaNacimiento(){
        return fechaNacimiento;
    }
    
    /**
     * Establece un valor para el atributo fecha nacimiento
     * @param pFecha nuevo valor para el atributo 
     */
    public void setFechaNacimiento(String pFecha){
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
    
    public String getFoto(){
        return foto;
    }
    public void setFoto(String pFoto){
        foto = pFoto;
    }
    
    public Long getId(){
        return id;
    }
  
    /**
     * Establece un valor para el atributoid
     * @param pId nuevo valor del atributo 
     */
    public void setId(Long pId){
        id = pId;
    }
      @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    }