/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author a.trujilloa1
 */
public class JuradoDetailDTO extends JuradoDTO implements Serializable {
     
    public JuradoDetailDTO() {
        super();
    }

    /**
     * Crea un objeto JuradoDetailDTO a partir de un objeto JuradoEntity
     * incluyendo los atributos de JuradoDTO.
     *
     * @param juradoEntity Entidad JuradoEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public JuradoDetailDTO(JuradoEntity juradoEntity) {
        
    }

    /**
     * Convierte un objeto JuradoDetailDTO a JuradoEntity incluyendo los
     * atributos de JuradoDTO.
     *
     * @return Nueva objeto JuradoEntity.
     *
     */
    @Override
    public JuradoEntity toEntity() {
        JuradoEntity juradoEntity = super.toEntity();
        return juradoEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
