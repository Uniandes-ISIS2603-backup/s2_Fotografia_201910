/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author a.trujilloa1
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable{
    
    private Double puntaje;
    private String comentario;
    private String nombre;
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PhotoEntity fotoCalificada;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity clienteCalificador;
   
 /**
     * Devuelve el puntaje de la calificación.
     *
     * @return puntaje
     */
    public double getPuntaje() {
        return puntaje;
    }

    /**
     * Modifica el puntaje de la calificación.
     *
     * @param puntaje el puntaje asignado
     */
    public void setPuntaje(double puntaje) {
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
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    /**
     * Devuelve la foto calificada
     * @return foto calificada
     */
    public PhotoEntity getFotoCalificada()
    {
        return fotoCalificada;
    }
    
    /**
     * Modifica la foto calificada
     * @param foto la nueva foto
     */
    public void setFotoCalificada( PhotoEntity foto)
    {
        this.fotoCalificada = foto;
    }
    
    /**
     * Devuelve el cliente  calificador
     * @return Un cliente
     */
    public ClienteEntity getClienteCalificador()
    {
        return clienteCalificador;
    }
    
    /**
     * Modifica el cliente calificador
     * @param cliente
     */
    public void setClienteCalificador(ClienteEntity cliente)
    {
        this.clienteCalificador = cliente;
    }
}
