package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import java.io.Serializable;

public class ConcursoDetailDTO extends ConcursoDTO implements Serializable {
	
	
	/*private List<RondasDTO> rondas;
	*private List<JuradoDTO> jurados;
	*
	*
	*
	*/
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

}
