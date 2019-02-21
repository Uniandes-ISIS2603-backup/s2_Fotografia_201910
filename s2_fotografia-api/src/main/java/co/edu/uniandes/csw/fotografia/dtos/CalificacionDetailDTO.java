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
        return calificacionEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
