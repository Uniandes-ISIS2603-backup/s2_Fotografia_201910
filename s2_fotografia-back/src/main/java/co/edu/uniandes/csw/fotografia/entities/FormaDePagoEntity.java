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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.CascadeType;
/**
 *
 * @author Valentina Duarte
 */
@Entity
public class FormaDePagoEntity extends BaseEntity implements Serializable {

    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ClienteEntity cliente;
    
    @PodamExclude
    @OneToMany(mappedBy="formaDePagoFactura", fetch =FetchType.LAZY)
    private List<FacturaEntity> facturas = new ArrayList<FacturaEntity>();
    
    
    private Long numeroTarjeta;
    
     @Temporal (TemporalType.DATE)
    private Date fechaVencimiento;
     
    private Integer numeroVerificacion;
    private String tipoDeTarjeta;
    private String tipoTarjetaDeCredito;
    private String nombre;

    /**
     *Constructor vacio
     */
    public FormaDePagoEntity()
    {
        
    }

   

    /**
     * @return the cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the facturas
     */
    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
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
}
