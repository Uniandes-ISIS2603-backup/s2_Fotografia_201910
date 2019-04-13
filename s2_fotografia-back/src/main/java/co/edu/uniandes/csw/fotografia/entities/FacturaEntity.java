/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Valentina Duarte y ahora Dany Benavides
 */

@Entity
public class FacturaEntity extends BaseEntity implements Serializable
{
   /*
    * Atributo correspondiente a el numero de la factura.
    */
    private Integer numero;
    /*
    * Atributo correspondiente a el precio de la foto.
    */
    private Double precio;
    /*
    * Atributo correspondiente a la fecha de la factura.
    */
    @Temporal (TemporalType.DATE)
    private Date fechaCompra;
    /*
    * Atributo correspondiente a la asociacion con cliente
    */
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
    
    /*
    * Atributo correspondiente la foto comprada
    */
    @PodamExclude
    @ManyToOne(cascade = CascadeType.ALL)
    private PhotoEntity fotoComprada;
    /*
    * Atributo correspondiente a la forma de pago usada.
    */
    @PodamExclude
    @ManyToOne
    private FormaDePagoEntity formaDePagoFactura;
    
 
    
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

    /**
     * @return the formaDePagoFactura
     */
    public FormaDePagoEntity getFormaDePagoFactura() {
        return formaDePagoFactura;
    }

    /**
     * @param formaDePagoFactura the formaDePagoFactura to set
     */
    public void setFormaDePagoFactura(FormaDePagoEntity formaDePagoFactura) {
        this.formaDePagoFactura = formaDePagoFactura;
    }


    
}
