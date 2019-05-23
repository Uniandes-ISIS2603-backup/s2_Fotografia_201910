/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.adapters.DateAdapter;
import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Valentina Duarte
 */
public class FacturaDTO implements Serializable
{
    private Long id;
    private Integer numero;
    private Double precio;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaCompra;
    
   
    private ClienteDTO cliente;
    private FormaDePagoDTO formaDePago;    
    
    public FacturaDTO()
    {
    }
    
     /**
     * Crea un objeto FacturaDTO a partir de un objeto FacturaEntity.
     *
     * @param facturaEntity Entidad FacturaEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public FacturaDTO(FacturaEntity facturaEntity) {
        if (facturaEntity != null) 
        {
            this.numero = facturaEntity.getNumero();
            this.fechaCompra = facturaEntity.getFechaCompra();
            this.precio= facturaEntity.getPrecio();
            this.id = facturaEntity.getId();
            
            if (facturaEntity.getCliente() != null) {
                this.cliente = new ClienteDTO(facturaEntity.getCliente());
            } else {
                facturaEntity.setCliente(null);
            }
            
            if (facturaEntity.getFormaDePagoFactura()!= null) {
                this.formaDePago = new FormaDePagoDTO(facturaEntity.getFormaDePagoFactura());
            } else {
                facturaEntity.setFormaDePagoFactura(null);
            }
            
            
            
        }
    }

    /**
     * Convierte un objeto FacturaDTO a FacturaEntity.
     *
     * @return Nueva objeto FacturaEntity.
     *
     */
    public FacturaEntity toEntity() {
        FacturaEntity facturaEntity = new FacturaEntity();
        facturaEntity.setNumero(this.getNumero());
        facturaEntity.setPrecio(this.getPrecio());
        facturaEntity.setFechaCompra(this.getFechaCompra());
        facturaEntity.setId(this.getId());
       
        if (cliente != null) {
            facturaEntity.setCliente(cliente.toEntity());
        }
        
        if(formaDePago!= null)
        {
            facturaEntity.setFormaDePagoFactura(formaDePago.toEntity());
        }
        
        return facturaEntity;
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
    
  
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the id to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
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
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the formaDePago
     */
    public FormaDePagoDTO getFormaDePago() {
        return formaDePago;
    }

    /**
     * @param formaDePago the formaDePago to set
     */
    public void setFormaDePago(FormaDePagoDTO formaDePago) {
        this.formaDePago = formaDePago;
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
