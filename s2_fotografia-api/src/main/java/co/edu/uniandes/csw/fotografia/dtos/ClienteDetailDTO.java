/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Valentina Duarte
 */
public class ClienteDetailDTO extends ClienteDTO implements Serializable
{
    private List<FacturaDTO> facturas;
    
      public ClienteDetailDTO()
     {
         
     }
    /**
     * @return the facturas
     */
    public List<FacturaDTO> getFacturas() {
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(List<FacturaDTO> facturas) {
        this.facturas = facturas;
    }
    
   // private List<CalificacionDTO> calificacionesPorCliente;
    
    private List<ConcursoDTO> concursosCliente;
}
