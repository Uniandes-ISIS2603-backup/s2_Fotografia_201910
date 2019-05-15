/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author da.benavides
 */
public class PhotoDetailDTO extends PhotoDTO implements Serializable{
    /**
     * Esta lista contiene las calificaciones a la foto
     */
    private List<CalificacionDTO> calificaciones;
    /**
     * Constructor vacío
     */
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
        if (photoEntity.getCalificaciones() != null) {
            calificaciones = new ArrayList<>();
            for(CalificacionEntity entityCalificacion : photoEntity.getCalificaciones())
            {
                calificaciones.add(new CalificacionDTO(entityCalificacion));
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
        if(calificaciones!=null)
        {
            List<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for(CalificacionDTO dtoCalificacion : getCalification())
            {
                calificacionesEntity.add(dtoCalificacion.toEntity());
            }
            photoEntity.setCalificaciones(calificacionesEntity);
        }
        return photoEntity;
    }
    /**
     * @return the calificaciones
     */
    public List<CalificacionDTO> getCalification() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalification(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }
}
