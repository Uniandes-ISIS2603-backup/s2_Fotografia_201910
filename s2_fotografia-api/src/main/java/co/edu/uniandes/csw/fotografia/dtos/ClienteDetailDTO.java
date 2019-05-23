/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valentina Duarte
 */
public class ClienteDetailDTO extends ClienteDTO implements Serializable
{
    private List<FacturaDTO> facturas;
    
    private List<FormaDePagoDTO> formasDePago;
    
    private List <ConcursoDTO> concursosCliente;    
    
    private List <CalificacionDTO> calificacionesPorCliente;
    
    
      public ClienteDetailDTO()
     {
        super(); 
     }
      
      
      /**
     * Crea un objeto ClienteDetailDTO a partir de un objeto ClienteEntity
     * incluyendo los atributos de ClienteDTO.
     *
     * @param clienteEntity Entidad ClienteEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public ClienteDetailDTO(ClienteEntity clienteEntity) {
        super(clienteEntity);
        if (clienteEntity != null) {
            facturas = new ArrayList<>();
            for (FacturaEntity entityFacturas : clienteEntity.getFacturas()) {
                facturas.add(new FacturaDTO(entityFacturas));
            }
            formasDePago = new ArrayList();
            for (FormaDePagoEntity entityFormaDePago : clienteEntity.getFormasDePago()) {
                formasDePago.add(new FormaDePagoDTO(entityFormaDePago));
            }
            
            concursosCliente = new ArrayList<>();
            for(ConcursoEntity entityConcurso: clienteEntity.getConcursosCliente())
            {
                concursosCliente.add(new ConcursoDTO(entityConcurso));
            }
            calificacionesPorCliente = new ArrayList<>();
            for(CalificacionEntity entityCalificacion : clienteEntity.getCalificacionesPorCliente())
            {
                calificacionesPorCliente.add(new CalificacionDTO(entityCalificacion));
            }
            
        }
    }

    /**
     * Convierte un objeto ClienteDetailDTO a CLienteEntity incluyendo los
     * atributos de ClienteDTO.
     *
     * @return Nueva objeto ClienteEntity.
     *
     */
    @Override
    public ClienteEntity toEntity() {
        ClienteEntity clienteEntity = super.toEntity();
        if (facturas != null) {
            List<FacturaEntity> facturasEntity = new ArrayList<>();
            for (FacturaDTO dtoFactura : facturas) {
                facturasEntity.add(dtoFactura.toEntity());
            }
            clienteEntity.setFacturas(facturasEntity);
        }
        if (formasDePago != null) {
            List<FormaDePagoEntity> formasDePagoEntity = new ArrayList<>();
            for (FormaDePagoDTO dtoFormaDePago : formasDePago) {
                formasDePagoEntity.add(dtoFormaDePago.toEntity());
            }
            clienteEntity.setFormasDePago(formasDePagoEntity);
        }
        
        if(concursosCliente != null)
        {
            List<ConcursoEntity> concursosEntity = new ArrayList<>();
            for (ConcursoDTO dtoConcurso: concursosCliente)
            {
                concursosEntity.add(dtoConcurso.toEntity());
            }
            
            clienteEntity.setConcursosCliente(concursosEntity);
        }
        if(calificacionesPorCliente!=null)
        {
            List<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for(CalificacionDTO dtoCalificacion : calificacionesPorCliente)
            {
                calificacionesEntity.add(dtoCalificacion.toEntity());
            }
            clienteEntity.setCalificacionesPorCliente(calificacionesEntity);
        }
        
       
        
        return clienteEntity;
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

    /**
     * @return the formasDePago
     */
    public List<FormaDePagoDTO> getFormasDePago() {
        return formasDePago;
    }

    /**
     * @param formasDePago the formasDePago to set
     */
    public void setFormasDePago(List<FormaDePagoDTO> formasDePago) {
        this.formasDePago = formasDePago;
    }

    /**
     * @return the concursosCliente
     */
    public List <ConcursoDTO> getConcursosCliente() {
        return concursosCliente;
    }

    /**
     * @param concursosCliente the concursosCliente to set
     */
    public void setConcursosCliente(List <ConcursoDTO> concursosCliente) {
        this.concursosCliente = concursosCliente;
    }
    
     /**
     * @return the calificacionesPorCliente
     */
    public List <CalificacionDTO> getCalificacionesPorCliente() {
        return calificacionesPorCliente;
    }

    /**
     * @param calificacionesPorCliente the calificacionesPorCliente to set
     */
    public void setCalificacionesPorCliente(List <CalificacionDTO> calificacionesPorCliente) {
        this.calificacionesPorCliente = calificacionesPorCliente;
    }

}
