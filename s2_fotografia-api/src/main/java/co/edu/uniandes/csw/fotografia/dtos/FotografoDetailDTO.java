/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que extiende de {@link FotografoDTO} para manejar las relaciones entre
 * los Fotografo JSON y otros DTOs. Para conocer el contenido de la un
 * Fotografo vaya a la documentacion de {@link FotografoDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "interesesFotograficos": ArrayList,
 *      "id": number,
 *      "nombre": string,
 *      "apellido": String,
 *      "fechaNacimiento": date,
 *      "edad":number,
 *      "correo": String,
 *      "telefono": number,
 *      "pais":String,
 *      "telefono": number,
 *      "pais": String,
 *      "id": number
 *      "fotos": [{@link PhotoDTO}]
 *      "fotosConcurso": [{@link PhotoDTO}]
 *      "concursos": [{@link ConcursoDTO}]
 *      "intereses": [{@link InteresFotograficoDTO}]
 *   }
 * </pre> Por ejemplo un fotografo se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "interesesFotograficos": ["paisaje, retrato"],
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
 *      "fotos" : [
 *          {
 *              
 *          },
 *          {
 *          }
 *      ]
 *     "fotosConcurso" : [
 *          {
 *              
 *          },
 *          {
 *          }
 *      ]
 * 
 *     "concursos" : [
 *          {
 *              
 *          },
 *          {
 *          }
 *      ]
 * 
 *     "intereses" : [
 *          {
 *              "interes": "Paisajes",
 *              "id" : "4"
 *          },
 *          {
 *             "interes": "Retratos",
 *             "id" : "5"
 *          }
 *      ]
 *   }
 *
 * </pre>
 * @author s.acostav
 */
public class FotografoDetailDTO extends FotografoDTO implements Serializable {
    /**
     * Esta lista contiene las fotos que estan asociadad al fotografo 
     */
    private ArrayList<PhotoDTO> fotos;
    /**
     * Esta lista contiene las fotos del concurso que estan asociadas al fotografo
     */
    private ArrayList<PhotoDTO> fotosConcurso;
    /**
     * Esta lista contiene los intereses que estan asociados al fotografo
     */

    /**
     * Esta lista contiene los concursos que estan asociados al fotografo  
     */
    private ArrayList<ConcursoDTO> concursos;
    
    /**
     * Constructos vacio por defecto 
     */
    public FotografoDetailDTO(){
       
    }
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param fotografo La entidad del fotografo para transformar a DTO.
     */
    public FotografoDetailDTO(FotografoEntity fotografo){
         super(fotografo);
         if(fotografo!=null){
             if(fotografo.getFotos()!=null){
                 fotos = new ArrayList();
                  for (PhotoEntity entity : fotografo.getFotos()) {
                    fotos.add(new PhotoDTO(entity   ));}
             }
              if(fotografo.getFotos()!=null){
                 fotosConcurso = new ArrayList();
                  for (PhotoEntity entity1 : fotografo.getFotosConcurso()) {
                    fotosConcurso.add(new PhotoDTO(entity1));}
             }
              if(fotografo.getConcursos()!=null){
                 concursos = new ArrayList();
                  for (ConcursoEntity entity2 : fotografo.getConcursos()) {
                    concursos.add(new ConcursoDTO(entity2));}
             }
              
         }
    }
    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO del fotografo para transformar a Entity
     */
    @Override 
    public FotografoEntity toEntity() {
        FotografoEntity fotografoEntity = super.toEntity();
        if (fotos != null) {
            List<PhotoEntity> fotosEntity = new ArrayList<>();
            for (PhotoDTO dto : fotos) {
               /* fotosEntity.add(dto.toEntity());*/
            }
            fotografoEntity.setFotos(fotosEntity);
        }
        if (fotosConcurso != null) {
            List<PhotoEntity> fotosEntity = new ArrayList<>();
            for (PhotoDTO dto : fotosConcurso) {
               /* fotosEntity.add(dto.toEntity());*/
            }
            fotografoEntity.setFotosConcurso(fotosEntity);
        }
         if (concursos != null) {
            List<ConcursoEntity> concursosEntity = new ArrayList<>();
            for (ConcursoDTO dto : concursos) {
              concursosEntity.add(dto.toEntity());
            }
            fotografoEntity.setConcursos(concursosEntity);
        }
          
        return fotografoEntity;
    }
      
    /**
     * Metodo que retorna el atributo fotos
     * @return atributo fotos
     */
    public ArrayList<PhotoDTO> getFotos(){
        return fotos;
    } 
    
    /**
     * Metodo que retorna el atributo fotos concurso
     * @return atributo fotos concurso
     */
    public ArrayList<PhotoDTO> getFotosConcurso(){
        return fotosConcurso;
    }
    /**
     * Metodo que retorna el atributo concursos
     * @return atributo concursos
     */
     public ArrayList<ConcursoDTO> getConcursos(){
        return concursos;
    }
     /**
      * Metodo que retorna el atributo intereses
      * @return intereses 
      */
    
      /**
       * Metodo que edita el atributo fotos
       * @param p nuevo valor del atributo fotos
       */
      public void setFotos(ArrayList<PhotoDTO> p){
          fotos = p;
      }
      
      /**
       * Metodo que le da un nuevo valor al atributo fotosConcurso
       * @param p nuevo valor del atributo 
       */
      public void setFotosConcurso(ArrayList<PhotoDTO> p){
          fotosConcurso = p;
      }
      
      /**
       * Metodo que le da un nuevo valor al atributo concursos
       * @param p nuevo valor del atributo 
       */
     public void setConcursos(ArrayList<ConcursoDTO> p){
          concursos = p;
      }
     
     /**
      * Metodo que le da un nuevo valor al atributo intereses
      * @param p nuevo valor del atributo
      */
   
     
     
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
}
