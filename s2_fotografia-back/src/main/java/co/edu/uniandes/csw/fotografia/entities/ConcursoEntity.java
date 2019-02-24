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
 * @author estudiante
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

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public List<String> getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(List<String> restricciones) {
        this.restricciones = restricciones;
    }

    public Integer getEdadFoto() {
        return edadFoto;
    }

    public void setEdadFoto(Integer edadFoto) {
        this.edadFoto = edadFoto;
    }

    public Integer getMaximasFotos() {
        return maximasFotos;
    }

    public void setMaximasFotos(Integer maximasFotos) {
        this.maximasFotos = maximasFotos;
    }

    public List<FotografoEntity> getFotografos() {
        return fotografos;
    }

    public void setFotografos(List<FotografoEntity> fotografos) {
        this.fotografos = fotografos;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<PhotoEntity> getFotosEnConcurso() {
        return fotosEnConcurso;
    }

    public void setFotosEnConcurso(List<PhotoEntity> fotosEnConcurso) {
        this.fotosEnConcurso = fotosEnConcurso;
    }

    public List<JuradoEntity> getJurados() {
        return jurados;
    }

    public void setJurados(List<JuradoEntity> jurados) {
        this.jurados = jurados;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidadPremio() {
        return cantidadPremio;
    }

    public void setCantidadPremio(Integer cantidadPremio) {
        this.cantidadPremio = cantidadPremio;
    }
    
    
}