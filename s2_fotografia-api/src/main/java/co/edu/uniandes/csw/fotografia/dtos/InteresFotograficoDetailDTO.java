/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link InteresFotograficoDTO} para manejar las relaciones entre
 * los interesesFotograficos JSON y otros DTOs. Para conocer el contenido de la un
 * InteresFotografico vaya a la documentacion de {@link InteresFotograficoDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "interes": String,
 *      "id": number
 *      "fotografo": [{@link FotografoDTO}]
 *   }
 * </pre> Por ejemplo un fotografo se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "interes": "Paisajes",
 *      "id" : "4"
 *      "fotografo" : [
 *          {
 *       "id": "123",
 *      "nombre": "Sara",
 *      "apellido": "Acosta",
 *      "fechaNacimiento": "27/12/99",
 *      "edad": "12",
 *      "correo": "s.acostav,
 *      "telefono": "319",
 *      "pais": "Colombia",
 *      "telefono": "365",
 *      "pais": "Colombia",
 *      "id": "246"
 *              
 *          },
 *          {
 *      "id": "123",
 *      "nombre": "Sara",
 *      "apellido": "Acosta",
 *      "fechaNacimiento": "27/12/99",
 *      "edad": "12",
 *      "correo": "s.acostav,
 *      "telefono": "319",
 *      "pais": "Colombia",
 *      "telefono": "365",
 *      "pais": "Colombia",
 *      "id": "246"
 *          }
 *      ]
 *      
 *   }
 *
 * </pre>
 * @author s.acostav
 */
public class InteresFotograficoDetailDTO extends InteresFotograficoDTO implements Serializable {
    /**
     * Esta lista contiene los fotgrafos asociados al interes
     */
    private List<PhotoDTO> fotosInteres;
    /**
     * Constructor vacio por defecto
     */
    public InteresFotograficoDetailDTO(){
        super();
    }
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity  La entidad del fotografo para transformar a DTO.
     */
    public InteresFotograficoDetailDTO(InteresFotograficoEntity entity){
        super(entity);
        if(entity!=null){
            if(entity.getFotosInteres()!=null){
                 fotosInteres = new ArrayList();
                  for (PhotoEntity entity1: entity.getFotosInteres()) {
                    fotosInteres.add(new PhotoDTO(entity1));
                  }
            }
        }
    }
     /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO del interes para transformar a Entity
     */

    public InteresFotograficoEntity toEntity() {
       InteresFotograficoEntity interesEntity = super.toEntity();
        if (getFotosInteres() != null) {
            List<PhotoEntity> fotosEntity = new ArrayList<>();
            for (PhotoDTO dto : getFotosInteres()) {
               fotosEntity.add(dto.toEntity());
            }
            interesEntity.setFotosInteres(fotosEntity);
        }
      return interesEntity;
    }

    /**
     * @return the fotosInteres
     */
    public List<PhotoDTO> getFotosInteres() {
        return fotosInteres;
    }

    /**
     * @param fotosInteres the fotosInteres to set
     */
    public void setFotosInteres(List<PhotoDTO> fotosInteres) {
        this.fotosInteres = fotosInteres;
    }

}