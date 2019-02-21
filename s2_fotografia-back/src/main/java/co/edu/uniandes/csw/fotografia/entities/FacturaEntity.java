/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Valentina Duarte
 */

@Entity
public class FacturaEntity extends BaseEntity implements Serializable
{
   
    private Integer numero;
    private Double precio;
    
     @Temporal (TemporalType.DATE)
    private Date fechaCompra;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
    
    @PodamExclude
    @ManyToOne
    private PhotoEntity fotoComprada;
    
  //  private FormaDePagoEntity formaDePagoFactura;
    
    public FacturaEntity()
            {
    }

    /**
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the fechaCompra
     */
    public Date getFechaCompra() {
        return fechaCompra;
    }

    /**
     * @param fechaCompra the fechaCompra to set
     */
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
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
     * @return the fotoComprada
     */
    public PhotoEntity getFotoComprada() {
        return fotoComprada;
    }

    /**
     * @param fotoComprada the fotoComprada to set
     */
    public void setFotoComprada(PhotoEntity fotoComprada) {
        this.fotoComprada = fotoComprada;
    }


    
}
