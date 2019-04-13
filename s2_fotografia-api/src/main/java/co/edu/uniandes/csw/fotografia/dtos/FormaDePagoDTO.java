/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Valentina Duarte
 */
public class FormaDePagoDTO implements Serializable
{
    public final static String TARJETADEBITO = "Tarjeta Debito";
    public final static String TARJETACREDITO = "Tarjeta Credito";
    public final static String VISA = "VISA";
    public final static String MASTERCARD = "MASTERCARD";
    
    private Long id;
    private Long numeroTarjeta;
    private Date fechaVencimiento;
    private Integer numeroVerificacion;
    private String tipoDeTarjeta;
    private String tipoTarjetaDeCredito;
   
    
    /**
     * Constructor vacio
     */
    
    public FormaDePagoDTO()
    {
        
    }
    
     /**
     * Crea un objeto FormaDePagoDTO a partir de un objeto FormaDePagoEntity.
     *
     * @param formaDePagoEntity Entidad FormaDePagoEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public FormaDePagoDTO(FormaDePagoEntity formaDePagoEntity) {
        if (formaDePagoEntity != null) 
        {
           
            this.numeroTarjeta = formaDePagoEntity.getNumeroTarjeta();
            this.numeroVerificacion = formaDePagoEntity.getNumeroVerificacion();
            this.fechaVencimiento = formaDePagoEntity.getFechaVencimiento();
            this.tipoDeTarjeta = formaDePagoEntity.getTipoDeTarjeta();
            this.tipoTarjetaDeCredito = formaDePagoEntity.getTipoTarjetaDeCredito();
            this.id = formaDePagoEntity.getId();
        }
    }

    /**
     * Convierte un objeto FormaDePagoDTO a FormaDePagoEntity.
     *
     * @return Nueva objeto FormaDePagoEntity.
     *
     */
    public FormaDePagoEntity toEntity() {
        FormaDePagoEntity formaDePagoEntity = new FormaDePagoEntity();
        formaDePagoEntity.setNumeroTarjeta(this.getNumeroTarjeta());
        formaDePagoEntity.setFechaVencimiento(this.getFechaVencimiento());
        formaDePagoEntity.setNumeroVerificacion(this.getNumeroVerificacion());
        formaDePagoEntity.setTipoDeTarjeta(this.getTipoDeTarjeta());
        formaDePagoEntity.setTipoTarjetaDeCredito(this.getTipoTarjetaDeCredito());
       formaDePagoEntity.setId(this.getId());
        
        return formaDePagoEntity;
    } 

    /**
     * @return the numeroTarjeta
     */
    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * @param numeroTarjeta the numeroTarjeta to set
     */
    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the numeroVerificacion
     */
    public Integer getNumeroVerificacion() {
        return numeroVerificacion;
    }

    /**
     * @param numeroVerificacion the numeroVerificacion to set
     */
    public void setNumeroVerificacion(Integer numeroVerificacion) {
        this.numeroVerificacion = numeroVerificacion;
    }

    /**
     * @return the tipoDeTarjeta
     */
    public String getTipoDeTarjeta() {
        return tipoDeTarjeta;
    }

    /**
     * @param tipoDeTarjeta the tipoDeTarjeta to set
     */
    public void setTipoDeTarjeta(String tipoDeTarjeta) {
        this.tipoDeTarjeta = tipoDeTarjeta;
    }

    /**
     * @return the tipoTarjetaDeCredito
     */
    public String getTipoTarjetaDeCredito() {
        return tipoTarjetaDeCredito;
    }

    /**
     * @param tipoTarjetaDeCredito the tipoTarjetaDeCredito to set
     */
    public void setTipoTarjetaDeCredito(String tipoTarjetaDeCredito) {
        this.tipoTarjetaDeCredito = tipoTarjetaDeCredito;
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

   
}
