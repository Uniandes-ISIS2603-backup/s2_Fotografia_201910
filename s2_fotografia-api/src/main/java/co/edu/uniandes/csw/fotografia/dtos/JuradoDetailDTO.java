/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import co.edu.uniandes.csw.fotografia.entities.RondaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author a.trujilloa1
 */
public class JuradoDetailDTO extends JuradoDTO implements Serializable {

    private List<PhotoDTO> fotos;
    private List<CalificacionDTO> calificaciones;
    
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
        super(juradoEntity);
        if (juradoEntity != null) {
            fotos = new ArrayList<>();
            for (PhotoEntity photoEntity : juradoEntity.getFotos()) {
                fotos.add(new PhotoDTO(photoEntity));
            }
            calificaciones = new ArrayList();
            for ( CalificacionEntity entityCalificacion : juradoEntity.getCalificaciones()) {
                calificaciones.add(new CalificacionDTO(entityCalificacion));
            }
        }
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
