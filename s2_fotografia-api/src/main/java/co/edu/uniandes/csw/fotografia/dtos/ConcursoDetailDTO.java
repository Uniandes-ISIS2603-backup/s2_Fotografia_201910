package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import java.io.Serializable;
import java.util.List;

public class ConcursoDetailDTO extends ConcursoDTO implements Serializable {
	
	private List<JuradoDTO> jurados;
	/*private List<RondasDTO> rondas;
	
	*
	*
	*
	*/
        private List<PhotoDTO> fotos;
        
        private List<FotografoDTO> fotografos;
        
	public ConcursoDetailDTO() {
            super();
	}
        
        public ConcursoDetailDTO(ConcursoEntity concursoEntity){
            super(concursoEntity);
            //TODO
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

}
