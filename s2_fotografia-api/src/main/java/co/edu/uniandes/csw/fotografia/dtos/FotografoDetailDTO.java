/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import java.io.Serializable;
import java.util.ArrayList;

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
        super();
    }
    public FotografoDetailDTO(FotografoEntity fotografo){
        
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
    
    public FotografoEntity toEntity(){
        return new FotografoEntity();
    }
    
    public void setId(Long pId){
        
    }
    
}
