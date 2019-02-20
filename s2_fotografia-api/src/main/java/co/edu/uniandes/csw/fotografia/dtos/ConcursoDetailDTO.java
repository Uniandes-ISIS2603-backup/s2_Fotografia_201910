package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import java.io.Serializable;
import java.util.List;

public class ConcursoDetailDTO extends ConcursoDTO implements Serializable {
	
	private List<JuradoDTO> jurados;
	//private List<RondasDTO> rondas;
        private List<PhotoDTO> fotos;
        
        private List<FotografoDTO> fotografos;
        
	public ConcursoDetailDTO() {
            super();
	}
        
        public ConcursoDetailDTO(ConcursoEntity concursoEntity){
            super(concursoEntity);
            if(concursoEntity != null){
                //TODO
            }
        }
        
        @Override
        public ConcursoEntity toEntity(){
            //TODO
            return null;
        }
        
        public List<JuradoDTO> getJurados(){
            return jurados;
        }
        
        public void setJurados(List<JuradoDTO> pJurados){
            jurados = pJurados;
        }
        
        public List<PhotoDTO> getFotos(){
            return fotos;
        }
        
        public void setFotos(List<PhotoDTO> pFotos){
            fotos = pFotos;
        }
        
        public List<FotografoDTO> getFotografos(){
            return fotografos;
        }
        
        public void setFotogorafos(List<FotografoDTO> pFotografos){
            fotografos = pFotografos;
        }
}
