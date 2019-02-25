/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.adapters.DateAdapter;
import co.edu.uniandes.csw.fotografia.entities.OrganizadorEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * OrganizadorDTO Objeto de transferencia de datos de Organizador. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "nombre": string,
 *      "apellido": string,
 *      "edad": number,
 *      "correo": string,
 *      "telefono": number,
 *      "pais": string
 *   }
 * </pre> Por ejemplo un Organizador se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "nombre": "Jose",
 *      "apellido": "Ramirez",
 *      "edad": 29,
 *      "correo": "jRamirez@example.com",
 *      "telefono": 31293829,
 *      "pais": "Colombia"
 *   }
 *
 * </pre>
 *
 * @author Nicolas Melendez
 */
public class OrganizadorDTO implements Serializable {

    private Long id;
    private String nombre;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private String apellido;
    private Integer edad;
    private String correo;
    private Integer telefono;
    private String pais;
  
    /**
     * Constructor vacio
     */
    public OrganizadorDTO() {
    }
    /**
     * Crea un objeto OrganizadorDTO a partir de un objeto OrganizadorEntity.
     *
     * @param organizadorEntity Entidad AuthorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public OrganizadorDTO(OrganizadorEntity organizadorEntity) {
        if (organizadorEntity != null) {
            this.id = organizadorEntity.getId();
            this.nombre = organizadorEntity.getNombre();
            this.apellido = organizadorEntity.getApellido();
            this.edad = organizadorEntity.getEdad();
            this.correo = organizadorEntity.getCorreo();
            this.telefono = organizadorEntity.getTelefono();
            this.pais = organizadorEntity.getPais();
        }
    }

    public void nothing(){
        
    }
    /**
     * Convierte un objeto OrganizadorDTO a OrganizadorEntity.
     *
     * @return Nueva objeto OrganizadorEntity.
     *
     */
    public OrganizadorEntity toEntity() {
        OrganizadorEntity organizadorEntity = new OrganizadorEntity();
        organizadorEntity.setId(this.getId());
        organizadorEntity.setNombre(this.getNombre());
        organizadorEntity.setApellido(this.getApellido());
        organizadorEntity.setEdad(this.getEdad());
        organizadorEntity.setCorreo(this.getCorreo());
        organizadorEntity.setTelefono(this.getTelefono());
        organizadorEntity.setPais(this.getPais());
        return organizadorEntity;
    }

    /**
     * Obtiene el Organizador id.
     *
     * @return atributo id.
     *
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     *
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     *
     */
    public void setNombre(String name) {
        this.nombre = name;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getCorreo() {
        return correo;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
