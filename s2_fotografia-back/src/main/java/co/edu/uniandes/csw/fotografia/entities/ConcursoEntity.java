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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;

/**
 *
 * @author Nicolas Rincon
 */
@Entity
public class ConcursoEntity extends BaseEntity implements Serializable{
    private String tema;
    
    
    private List<String> restricciones;
    
    @PodamIntValue(minValue = 0, maxValue = Integer.MAX_VALUE)
    private Integer edadDeLaFoto;
    
    @PodamIntValue(minValue = 1, maxValue = Integer.MAX_VALUE)
    private Integer maxFotos;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
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

    @PodamExclude
    @OneToOne
    private RondaEntity ronda;
    
    private Integer cantidadPremio;
    

    public ConcursoEntity(){
    /**
     * no hago nada xd 
     */
    }

    public OrganizadorEntity getOrganizador() {
        return organizador;
    }

    public void setOrganizador(OrganizadorEntity organizador) {
        this.organizador = organizador;
    }

    public RondaEntity getRonda() {
        return ronda;
    }

    public void setRonda(RondaEntity ronda) {
        this.ronda = ronda;
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

    public Integer getEdadDeLaFoto() {
        return edadDeLaFoto;
    }

    public void setEdadDeLaFoto(Integer edadFoto) {
        this.edadDeLaFoto = edadFoto;
    }

    public Integer getMaxFotos() {
        return maxFotos;
    }

    public void setMaxFotos(Integer maximasFotos) {
        this.maxFotos = maximasFotos;
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