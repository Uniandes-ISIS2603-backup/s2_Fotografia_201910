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
 * @author a.trujilloa1
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable{
    
    private Long puntaje;
    private String comentario;

 /**
     * Devuelve el puntaje de la calificación.
     *
     * @return puntaje
     */
    public Long getPuntaje() {
        return puntaje;
    }

    /**
     * Modifica el puntaje de la calificación.
     *
     * @param puntaje el puntaje asignado
     */
    public void setPuntaje(Long puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * Obtiene el atributo comentario.
     *
     * @return atributo comentario.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Establece el valor del atributo comentario.
     *
     * @param comentario nuevo valor del atributo
     */
    public void setBirthDate(String comentario) {
        this.comentario = comentario;
    }

}
