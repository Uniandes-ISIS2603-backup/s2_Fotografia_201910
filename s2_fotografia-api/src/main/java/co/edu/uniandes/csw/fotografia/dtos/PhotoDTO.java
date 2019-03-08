/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.podam.DateStrategy;
import java.io.Serializable;
import java.util.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;
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
    * Atributo correspondiente a el nombre de la foto.
    */
    private String name;
    
    /*
    * Atributo correspondiente a la fecha de la foto.
    */
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
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
    public PhotoDTO(PhotoEntity entidad){
        if(entidad != null){
            this.date = entidad.getDate();
            this.description = entidad.getDescription();
            this.name = entidad.getName();
            this.price = entidad.getPrice();
            this.published = entidad.getPublished();
            this.winner = entidad.getWinner();
        }
    }
    
    public PhotoEntity toEntity(){
        PhotoEntity entidad = new PhotoEntity();
        entidad.setDate(this.getDate());
        entidad.setDescription(this.getDescription());
        entidad.setName(this.getName());
        entidad.setPrice(this.getPrice());
        entidad.setPublished(this.getPublished());
        entidad.setWinner(this.getWinner());
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
}
