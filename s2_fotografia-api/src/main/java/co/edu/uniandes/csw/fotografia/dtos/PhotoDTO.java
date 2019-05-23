/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.adapters.DateAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author da.benavides
 */
public class PhotoDTO implements Serializable{
    
    /*
    * Atributo correspondiente al id de la foto.
    */
    private Long id;
    
    /*
    * Atributo correspondiente a la ruta de la foto.
    */
    private String rutaFoto;
    /*
    * Atributo correspondiente a el nombre de la foto.
    */
    private String nombre;
    
    /*
    * Atributo correspondiente a la fecha de la foto.
    */
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date date;
    
    /*
    * Atributo correspondiente a la descripcion de la foto.
    */
    private String description;
    
    /*
    * Atributo correspondiente a el precio de la foto.
    */
    private Double price;
    
     /*
    * Atributo correspondiente a si la foto es ganadora o no.
    */
    private Boolean winner;
    
    /*
    * Atributo correspondiente a si la ha sido publicada o no.
    */
    private Boolean published;

    //Create getters and setters
    //private Photgrapher participant;
    
    //Create getters and setters
    //private Photographer photographer;
    
    //Create getters and setters
    //private Tag tags
    
    /**
     * Constructor vacío
     */
    public PhotoDTO(){
        
    }
    /**
     * Constructor que recibe un entity y transforma a DTO
     */
    public PhotoDTO(PhotoEntity entidad){
        if(entidad != null){
            this.id = entidad.getId();
            this.date = entidad.getDate();
            this.description = entidad.getDescription();
            this.nombre = entidad.getNombre();
            this.price = entidad.getPrice();
            this.published = entidad.getPublished();
            this.winner = entidad.getWinner();
            this.rutaFoto= entidad.getRutaFoto();
        }
    }
    /**
     * Método que transforma el entity a DTO
     * @return entidad. Objeto DTO transformado a entity.
     */
    public PhotoEntity toEntity(){
        PhotoEntity entidad = new PhotoEntity();
        entidad.setId(this.getId());
        entidad.setDate(this.getDate());
        entidad.setDescription(this.getDescription());
        entidad.setNombre(this.getNombre());
        entidad.setPrice(this.getPrice());
        entidad.setPublished(this.getPublished());
        entidad.setWinner(this.getWinner());
        entidad.setRutaFoto(this.getRutaFoto());
        return entidad;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

   

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the winner
     */
    public Boolean getWinner() {
        return winner;
    }

    /**
     * @param winner the winner to set
     */
    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    /**
     * @return the published
     */
    public Boolean getPublished() {
        return published;
    }

    /**
     * @param published the published to set
     */
    public void setPublished(Boolean published) {
        this.published = published;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return the rutafoto
     */
    public String getRutaFoto() {
        return rutaFoto;
    }

    /**
     * @param rutaFoto the rutaFoto to set
     */
    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }
}
