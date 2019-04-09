/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author a.trujilloa1
 */
public class CalificacionDetailDTO extends CalificacionDTO implements Serializable{
    
    private ClienteDTO cliente;
    private PhotoDTO foto;
    private JuradoDTO jurado;
    private RondaDTO ronda;
    
    public CalificacionDetailDTO() {
        super();
    }

    /**
     * Crea un objeto CalificacionDetailDTO a partir de un objeto CalificacionEntity
     * incluyendo los atributos de CalificacionDTO.
     *
     * @param calificacionEntity Entidad CalificacionEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public CalificacionDetailDTO(CalificacionEntity calificacionEntity) {
        super(calificacionEntity);
        if(calificacionEntity.getClienteCalificador() != null){
            cliente = new ClienteDTO(calificacionEntity.getClienteCalificador());
        }
        if(calificacionEntity.getFotoCalificada()!=null){
            foto = new PhotoDTO(calificacionEntity.getFotoCalificada());
        }
        if(calificacionEntity.getJuradoCalificador()!= null)
        {
            jurado = new JuradoDTO(calificacionEntity.getJuradoCalificador());
        }
        if(calificacionEntity.getRondaCalificada() != null)
        {
            ronda = new RondaDTO(calificacionEntity.getRondaCalificada());
        }
    }

    /**
     * Convierte un objeto CalificacionDetailDTO a CalificacionEntity incluyendo los
     * atributos de CalificacionDTO.
     *
     * @return Nueva objeto CalificacionEntity.
     *
     */
    @Override
    public CalificacionEntity toEntity() {
        CalificacionEntity calificacionEntity = super.toEntity();
        if(cliente != null)
        {
            calificacionEntity.setClienteCalificador(cliente.toEntity());
        }
        if(foto != null)
        {
            calificacionEntity.setFotoCalificada(foto.toEntity());
        }
        if(jurado != null)
        {
            calificacionEntity.setJuradoCalificador(jurado.toEntity());
        }
        if(ronda != null)
        {
            calificacionEntity.setRondaCalificada(ronda.toEntity());
        }
        return calificacionEntity;
    }

    /**
     * Devuelve el cliente
     * @return cliente
     */
    public ClienteDTO getCliente()
    {
        return cliente;
    }
    
    /**
     * Devuelve la foto
     * @return foto
     */
    public PhotoDTO getFoto()
    {
        return foto;
    }
    
    /**
     * Devuelve el jurado
     * @return jurado
     */
    public JuradoDTO getJurado()
    {
        return jurado;
    }
    
    /**
     * Devuelve la ronda
     * @return ronda
     */
    public RondaDTO getRonda()
    {
        return ronda;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
