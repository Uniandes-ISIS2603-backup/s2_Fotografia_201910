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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Nicolas Rincon
 */
@Entity
public class ConcursoEntity extends BaseEntity implements Serializable{
    private String tema;
    private List<String> restricciones;
    private Integer edadFoto;
    private Integer maximasFotos;
    
    @PodamExclude
    @ManyToMany
    private List<FotografoEntity> fotografos = new ArrayList<>();
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
    
    
    @PodamExclude
    @ManyToMany
    private List<PhotoEntity> fotosEnConcurso = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "concursoJurado")
    private List<JuradoEntity> jurados = new ArrayList<>();
    

    @PodamExclude
    @ManyToOne
    private OrganizadorEntity organizador;
 
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    private Integer cantidadPremio;
    /**
     * AQUI ESTARAN LAS RELACIONES CON LAS DEMAS CLASES
     */
    
    
    
    
    public ConcursoEntity(){
    /**
     * 
     */
    }

    public void setOrganizador(OrganizadorEntity organizador) {
        this.organizador = organizador;
    }
    
    public String getTema() {
        return tema;
    }

    public List<String> getRestricciones() {
        return restricciones;
    }

    public int getEdadFoto() {
        return edadFoto;
    }

    public int getMaximasFotos() {
        return maximasFotos;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getCantidadPremio() {
        return cantidadPremio;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setRestricciones(List<String> restricciones) {
        this.restricciones = restricciones;
    }

    public void setEdadFoto(int edadFoto) {
        this.edadFoto = edadFoto;
    }

    public void setMaximasFotos(int maximasFotos) {
        this.maximasFotos = maximasFotos;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCantidadPremio(int cantidadPremio) {
        this.cantidadPremio = cantidadPremio;
    }
    
    
}