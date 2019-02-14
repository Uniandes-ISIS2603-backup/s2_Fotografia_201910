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
 * @author estudiante
 */
public class ClientDetailDTO extends ClientDTO implements Serializable
{
    private List<ReceiptDTO> facturas;
    
      public ClientDetailDTO()
     {
         
     }
    /**
     * @return the facturas
     */
    public List<ReceiptDTO> getFacturas() {
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(List<ReceiptDTO> facturas) {
        this.facturas = facturas;
    }
    
    //private List<CalificationDTO> calificacionesPorCliente;
    
    //private List<CompetitionDTO> concursosCliente;
}
