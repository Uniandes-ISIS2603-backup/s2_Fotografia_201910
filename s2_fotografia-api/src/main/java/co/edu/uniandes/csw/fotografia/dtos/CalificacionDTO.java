/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author a.trujilloa1
 */
public class CalificacionDTO implements Serializable {
    
    private Long id;
    private Double puntaje;
    private String comentario;  
    private String nombre;

    /**
     * Constructor vacio
     */
    public CalificacionDTO() {
    }

    /**
     * Crea un objeto CalificacionDTO a partir de un objeto CalificacionEntity.
     *
     * @param calificacionEntity Entidad CalificacionEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public CalificacionDTO(CalificacionEntity calificacionEntity) {
        if (calificacionEntity != null) {
            this.id = calificacionEntity.getId();
            this.puntaje = calificacionEntity.getPuntaje();
            this.comentario = calificacionEntity.getComentario();
            this.nombre = calificacionEntity.getNombre();
        }
    }

    /**
     * Convierte un objeto CalificacionDTO a CalificacionEntity.
     *
     * @return Nueva objeto CalificacionEntity.
     *
     */
    public CalificacionEntity toEntity() {
        CalificacionEntity calificacionEntity = new CalificacionEntity();
        calificacionEntity.setId(this.getId());
        calificacionEntity.setPuntaje(this.getPuntaje());
        calificacionEntity.setComentario(this.getComentario());
        calificacionEntity.setNombre(this.getNombre());
        return calificacionEntity;
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
     * Obtiene el atributo puntaje.
     *
     * @return atributo puntaje.
     *
     */
    public double getPuntaje() {
        return puntaje;
    }

    /**
     * Establece el valor del atributo puntaje.
     *
     * @param puntaje nuevo valor del atributo
     *
     */
    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * Obtiene el atributo comentario.
     *
     * @return atributo comentario.
     *
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Establece el valor del atributo comentario.
     *
     * @param comentario nuevo valor del atributo
     *
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
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
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
