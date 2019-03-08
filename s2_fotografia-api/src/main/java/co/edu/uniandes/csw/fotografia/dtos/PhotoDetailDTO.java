/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author da.benavides
 */
public class PhotoDetailDTO extends PhotoDTO {
    /**
     * Esta lista contiene las calificaciones a la foto
     */
    private List<CalificacionDTO> calification;

    public PhotoDetailDTO(){
        super();
    }    
    /**
     * Crea un objeto PhotoDetailDTO a partir de un objeto PhotoEntity
     * incluyendo los atributos de PhotoDTO.
     *
     * @param photoEntity Entidad PhotoEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public PhotoDetailDTO(PhotoEntity photoEntity) {
        super(photoEntity);
        if (photoEntity != null) {
            calification = new ArrayList<>();
            for(CalificacionEntity entityCalificacion : photoEntity.getCalificaciones())
            {
                calification.add(new CalificacionDTO(entityCalificacion));
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
    public PhotoEntity toEntity() {
        PhotoEntity photoEntity = super.toEntity();
        if(calification!=null)
        {
            List<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for(CalificacionDTO dtoCalificacion : calification)
            {
                calificacionesEntity.add(dtoCalificacion.toEntity());
            }
            photoEntity.setCalificaciones(calificacionesEntity);
        }
        return photoEntity;
    }
    /**
     * @return the calification
     */
    public List<CalificacionDTO> getCalification() {
        return calification;
    }

    /**
     * @param calification the calification to set
     */
    public void setCalification(List<CalificacionDTO> calification) {
        this.calification = calification;
    }
}
