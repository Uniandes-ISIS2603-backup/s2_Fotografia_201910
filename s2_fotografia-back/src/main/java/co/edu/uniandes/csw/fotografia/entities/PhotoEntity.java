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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author da.benavides
 */
@Entity
public class PhotoEntity extends BaseEntity implements Serializable{
    /*
    * Atributo correspondiente a el nombre de la foto.
    */
    private String nombre;
     /*
    * Atributo correspondiente a el nombre de la foto.
    */
    private String rutaFoto;
    /*
    * Atributo correspondiente a la fecha de la foto.
    */
    @Temporal(TemporalType.DATE)
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
    
    /**
     * Relacion con clase Factura de tipo OneToMany
     */
    @PodamExclude
    @OneToMany(mappedBy = "fotoComprada", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.LAZY)
    private List<FacturaEntity> facturasAsociadas = new ArrayList<FacturaEntity>();
     
    /**
     * Relacion con clase Fotografo de tipo ManyToOne
     */ 
   @PodamExclude
   @ManyToOne
   private FotografoEntity concursante;
     /**
     * Relacion con clase Fotografo de tipo ManyToOne
     */
   @PodamExclude
   @ManyToOne
    private FotografoEntity fotografo;
     /**
     * Relacion con clase Jurado de tipo ManyToMany mapeado por esta clase
     */
   @PodamExclude
   @ManyToMany(mappedBy = "fotosCalificadas")
   private List<JuradoEntity> jurados = new ArrayList<>();
    /**
     * Relacion con clase Calificacion de tipo OneToMany mapeado por esta clase
     */
   @PodamExclude
   @OneToMany(mappedBy = "fotoCalificada", cascade = CascadeType.PERSIST, orphanRemoval = true)
   private List<CalificacionEntity> calificaciones = new ArrayList<CalificacionEntity>();
     /**
     * Relacion con clase Concurso de tipo ManyToMany mapeado por esta clase
     */
   @PodamExclude
   @ManyToMany(mappedBy = "fotosEnConcurso")
   private List<ConcursoEntity> concursos = new ArrayList<>();

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

    /**
     * @return the concursante
     */
    public FotografoEntity getConcursante() {
        return concursante;
    }

    /**
     * @param concursante the concursante to set
     */
    public void setConcursante(FotografoEntity concursante) {
        this.concursante = concursante;
    }

    /**
     * @return the fotografo
     */
    public FotografoEntity getFotografo() {
        return fotografo;
    }

    /**
     * @param fotografo the fotografo to set
     */
    public void setFotografo(FotografoEntity fotografo) {
        this.fotografo = fotografo;
    }

    /**
     * @return the jurados
     */
    public List<JuradoEntity> getJurados() {
        return jurados;
    }

    /**
     * @param jurados the jurados to set
     */
    public void setJurados(List<JuradoEntity> jurados) {
        this.jurados = jurados;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the concursos
     */
    public List<ConcursoEntity> getConcursos() {
        return concursos;
    }

    /**
     * @param concursos the concursos to set
     */
    public void setConcursos(List<ConcursoEntity> concursos) {
        this.concursos = concursos;
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
     * @return the nombre
     */
    public String getRutaFoto() {
        return rutaFoto;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }
}
