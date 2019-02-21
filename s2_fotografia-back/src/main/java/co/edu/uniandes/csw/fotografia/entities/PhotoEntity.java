/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author da.benavides
 */
@Entity
public class PhotoEntity extends BaseEntity implements Serializable{
    private String name;
    
    private Date date;
    
    private String[] tags;
    
    private String description;
    
    private Double price;
    
   // private ArrayList<String> comments;
    
    private Boolean winner;
    
    private Boolean published;

     @PodamExclude
    @OneToMany(mappedBy = "fotoComprada", fetch = FetchType.LAZY)
    private List<FacturaEntity> facturasAsociadas = new ArrayList<FacturaEntity>();
     
     
   @PodamExclude
   @ManyToOne
   private FotografoEntity concursante;
     
   @PodamExclude
   @ManyToOne
    private FotografoEntity fotografo;
     
   @PodamExclude
   @ManyToMany(mappedBy = "fotosCalificadas")
   private List<JuradoEntity> jurados = new ArrayList<>();
    
   @PodamExclude
   @OneToMany(mappedBy = "fotoCalificada", fetch = FetchType.LAZY)
   private List<CalificacionEntity> calificaciones = new ArrayList<CalificacionEntity>();
     
   @PodamExclude
   @ManyToMany(mappedBy = "fotosEnConcurso")
   private List<ConcursoEntity> concursos = new ArrayList<>();
 
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
     * @return the tags
     */
    public String[] getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String[] tags) {
        this.tags = tags;
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
     * @return the comments
     */
   // public ArrayList<String> getComments() {
   //     return comments;
   // }

    /**
     * @pasram comments the comments to set
     */
   // public void setComments(ArrayList<String> comments) {
   //     this.comments = comments;
   // }

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
     * @return the facturasAsociadas
     */
    public List<FacturaEntity> getFacturasAsociadas() {
        return facturasAsociadas;
    }

    /**
     * @param facturasAsociadas the facturasAsociadas to set
     */
    public void setFacturasAsociadas(List<FacturaEntity> facturasAsociadas) {
        this.facturasAsociadas = facturasAsociadas;
    }

}