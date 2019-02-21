/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author a.trujilloa1
 */
public class JuradoDTO implements Serializable {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private Long cedula;
    private String pais;
    private String ciudad;
    
    /**
     * Constructor vacio
     */
    public JuradoDTO(){}
    
    /**
     * Crea un objeto JuradoDTO a partir de un objeto JuradoEntity.
     *
     * @param juradoEntity Entidad JuradoEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public JuradoDTO(JuradoEntity juradoEntity) {
        if (juradoEntity != null) {
            this.id = juradoEntity.getId();
            this.nombre = juradoEntity.getNombre();
            this.apellido = juradoEntity.getApellido();
            this.correo = juradoEntity.getCorreo();
            this.cedula = juradoEntity.getCedula();
            this.pais = juradoEntity.getPais();
            this.ciudad = juradoEntity.getCiudad();             
        }
    }

    /**
     * Convierte un objeto JuradoDTO a JuradoEntity.
     *
     * @return Nueva objeto JuradoEntity.
     *
     */
    public JuradoEntity toEntity() {
        JuradoEntity juradoEntity = new JuradoEntity();
        juradoEntity.setId(this.getId());
        juradoEntity.setNombre(this.getNombre());
        juradoEntity.setApellido(this.getApellido());
        juradoEntity.setCorreo(this.getCorreo());
        juradoEntity.setCedula(this.cedula);
        juradoEntity.setPais(this.pais);
        juradoEntity.setCiudad(this.ciudad);
        return juradoEntity;
    }

    /**
     * Obtiene el atributo id.
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
     * Obtiene el atributo nombre.
     *
     * @return atributo nombre.
     *
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el valor del atributo nombre.
     *
     * @param nombre nuevo valor del atributo
     *
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el atributo apellido.
     *
     * @return atributo apellido.
     *
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el valor del atributo apellido.
     *
     * @param apellido nuevo valor del atributo
     *
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el atributo correo
     *
     * @return el correo
     */
    public String getCorreo() {
        return correo;
    }
    
    /**
     * Establece el valor del atributo correo.
     *
     * @param correo nuevo valor del atributo
     *
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Ontiene el atributo de cedula
     *
     * @return la cedula
     */
    public Long getCedula() {
        return cedula;
    }

    /**
     * Establece la cedula del autor
     *
     * @param cedula tla cedula asignada
     */
    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }
    
    /**
     * Ontiene el atributo de pais
     *
     * @return el pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Establece la pais del autor
     *
     * @param pais el pais asignado
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
    
    /**
     * Ontiene el atributo de ciudad
     *
     * @return la ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece la ciudad del autor
     *
     * @param ciudad la ciudad asignada
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
