/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;


import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author da.benavides
 */

public class FacturaDetailDTO extends FacturaDTO implements Serializable {

    // relación  cero o muchos reviews 
    private List<PhotoDTO> photos;

    public FacturaDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param FacturaEntity La entidad de la cual se construye el DTO
     */
    public FacturaDetailDTO(FacturaEntity facturaEntity) {
        super(facturaEntity);
        if (facturaEntity.getPhotos() != null) {
            photos = new ArrayList();
            for (PhotoEntity entityPhoto : facturaEntity.getPhotos()) {
                photos.add(new PhotoDTO(entityPhoto));
            }
        }
    }

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
    @Override
    public FacturaEntity toEntity() {
        FacturaEntity facturaEntity = super.toEntity();
        if (photos != null) {
            List<PhotoEntity> photosEntity = new ArrayList<>();
            for (PhotoDTO dtoPhoto : getPhotos()) {
                photosEntity.add(dtoPhoto.toEntity());
            }
            facturaEntity.setPhotos(photosEntity);
        return facturaEntity;
    }

    /**
     * Devuelve las reseñas asociadas a este libro
     *
     * @return Lista de DTOs de Reseñas
     */
    public List<PhotoDTO> getPhotos() {
        return photos;
    }

    /**
     * Modifica las reseñas de este libro.
     *
     * @param reviews Las nuevas reseñas
     */
    public void setPhotos(List<PhotoDTO> photos) {
        this.photos = photos;
    }
}
