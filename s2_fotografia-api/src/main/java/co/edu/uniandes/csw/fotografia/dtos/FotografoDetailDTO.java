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
 *
 * @author s.acostav
 */
public class FotografoDetailDTO extends FotografoDTO implements Serializable {
    
    public ArrayList<PhotoDTO> fotos;
    public ArrayList<PhotoDTO> fotosConcurso;
    public ArrayList<InteresFotograficoDTO> intereses;
    public ArrayList<ConcursoDTO> concursos;
    
    
    public FotografoDetailDTO(){
       
    }
    public FotografoDetailDTO(FotografoEntity fotografo){
         super(fotografo);
         if(fotografo!=null){
             if(fotografo.getFotos()!=null){
                 fotos = new ArrayList();
                  for (PhotoEntity entity : fotografo.getFotos()) {
                    /**fotos.add(new PhotoDTO(entity   ));**/}
             }
              if(fotografo.getFotos()!=null){
                 fotosConcurso = new ArrayList();
                  for (PhotoEntity entity1 : fotografo.getFotosConcurso()) {
                    /**fotosConcurso.add(new PhotoDTO(entity1));**/}
             }
              if(fotografo.getConcursos()!=null){
                 concursos = new ArrayList();
                  for (ConcursoEntity entity2 : fotografo.getConcursos()) {
                    concursos.add(new ConcursoDTO(entity2));}
             }
              if(fotografo.getIntereses()!=null){
                 intereses = new ArrayList();
                  for (InteresFotograficoEntity entity3: fotografo.getIntereses()) {
                    intereses.add(new InteresFotograficoDTO(entity3));
             }
           }
         }
    }
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
          if (intereses != null) {
            List<InteresFotograficoEntity> interesesEntity = new ArrayList<>();
            for (InteresFotograficoDTO dto : intereses) {
               interesesEntity.add(dto.toEntity());
            }
            fotografoEntity.setIntereses(interesesEntity);
        }
        return fotografoEntity;
    }
      
       
    public ArrayList<PhotoDTO> getFotos(){
        return fotos;
    } 
    public ArrayList<PhotoDTO> getFotosConcurso(){
        return fotosConcurso;
    }
    
     public ArrayList<ConcursoDTO> getConcursos(){
        return concursos;
    }
      public ArrayList<InteresFotograficoDTO> getIntereses(){
        return intereses;}
      
      public void setFotos(ArrayList<PhotoDTO> p){
          fotos = p;
      }
      public void setFotosConcurso(ArrayList<PhotoDTO> p){
          fotosConcurso = p;
      }
     public void setConcursos(ArrayList<ConcursoDTO> p){
          concursos = p;
      }
     public void setIntereses(ArrayList<InteresFotograficoDTO> p){
          intereses = p;
      }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    public void setId(Long pId){
        
    }
    
}
