/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
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
        if (juradoEntity.getFotos() != null) {
            fotos = new ArrayList<>();
            for (PhotoEntity photoEntity : juradoEntity.getFotos()) {
                fotos.add(new PhotoDTO(photoEntity));
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
        if( fotos != null){
            List<PhotoEntity> fotosEntity = new ArrayList<>();
            for( PhotoDTO dtoFoto : getFotos()){
                fotosEntity.add(dtoFoto.toEntity());
            }
            juradoEntity.setFotos(fotosEntity);
        }
        
        return juradoEntity;
    }

    /**
     * Devuelve las reseñas asociadas a este jurado
     *
     * @return Lista de DTOs de Fotos
     */
    public List<PhotoDTO> getFotos() {
        return fotos;
    }

    /**
     * Modifica las fotos del jurado.
     *
     * @param fotos Las nuevas reseñas
     */
    public void setFotos(List<PhotoDTO> fotos) {
        this.fotos = fotos;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
