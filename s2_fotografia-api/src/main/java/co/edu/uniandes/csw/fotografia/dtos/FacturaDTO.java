/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class FacturaDTO implements Serializable
{
    private Integer numero;
    
    private Double precio;
   // private PhotographerDTO fotografo;
    //private ClientDTO cliente;
    //private PhotoDTO fotoComprada;
    private Date fechaCompra;
    
    public FacturaDTO()
    {
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * @return the cliente
     */
   // public ClientDTO getCliente() {
     //   return cliente;
   // }

    /**
     * @param cliente the cliente to set
     */
   // public void setCliente(ClientDTO cliente) {
    //    this.cliente = cliente;
   // }

    /**
     * @return the id
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param id the id to set
     */
    public void setNumero(int numero) {
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
    
}
