/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.entities;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author nicolas melendez
 */
@Entity
public class RondaEntity extends BaseEntity implements Serializable{
    @PodamExclude
    @OneToOne
    private JuradoEntity jurado;
    
    @PodamExclude
    @OneToOne(mappedBy = "ronda", fetch=FetchType.LAZY)
    private ConcursoEntity concurso;
    
    @PodamExclude
    @OneToMany (mappedBy = "rondaCalificada", fetch = FetchType.LAZY)
    private List<CalificacionEntity> calificaciones = new ArrayList<CalificacionEntity>();
    
    
    private Integer numeroRonda;

    public RondaEntity(){
        
    }
    public JuradoEntity getJurado() {
        return jurado;
    }

    public ConcursoEntity getConcurso() {
        return concurso;
    }

    public Integer getNumeroRonda() {
        return numeroRonda;
    }

    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public void setJurado(JuradoEntity jurado) {
        this.jurado = jurado;
    }

    public void setConcurso(ConcursoEntity concurso) {
        this.concurso = concurso;
    }

    public void setNumeroRonda(Integer numeroRonda) {
        this.numeroRonda = numeroRonda;
    }
  
}
