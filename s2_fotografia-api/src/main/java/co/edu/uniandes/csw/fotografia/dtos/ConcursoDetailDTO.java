package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConcursoDetailDTO extends ConcursoDTO implements Serializable {

    private List<JuradoDTO> jurados;

    private List<PhotoDTO> fotos;

    private List<FotografoDTO> fotografos;

    public ConcursoDetailDTO() {
        super();
    }

    public ConcursoDetailDTO(ConcursoEntity concursoEntity) {
        super(concursoEntity);
        if (concursoEntity != null) {
            jurados = new ArrayList<>();
            for (JuradoEntity entityJurados : concursoEntity.getJurados()) {
                jurados.add(new JuradoDTO(entityJurados));
            }
            fotos = new ArrayList<>();
           for(PhotoEntity entityFotos : concursoEntity.getFotosEnConcurso()){
               fotos.add(new PhotoDTO(entityFotos));
           }
           fotografos = new ArrayList<>();
           for(FotografoEntity entityFotografos : concursoEntity.getFotografos()){
               fotografos.add(new FotografoDTO(entityFotografos));
           }
        }
    }

    @Override
    public ConcursoEntity toEntity() {
        ConcursoEntity concursoEntity = super.toEntity();
        if(jurados != null){
            List<JuradoEntity> juradosEntity  = new ArrayList<>();
            for(JuradoDTO dtoJurado: jurados){
                juradosEntity.add(dtoJurado.toEntity());
            }
            concursoEntity.setJurados(juradosEntity);
        }
        if(fotos != null){
            List<PhotoEntity> juradosEntity  = new ArrayList<>();
            for(PhotoDTO dtoPhoto: fotos){
                juradosEntity.add(dtoPhoto.toEntity());
            }
            concursoEntity.setFotosEnConcurso(juradosEntity);
        }
        if(fotografos != null){
            List<FotografoEntity> juradosEntity  = new ArrayList<>();
            for(FotografoDTO dtoFotografo: fotografos){
                juradosEntity.add(dtoFotografo.toEntity());
            }
            concursoEntity.setFotografos(juradosEntity);
        }
        return concursoEntity;
    }

    public List<JuradoDTO> getJurados() {
        return jurados;
    }

    public void setJurados(List<JuradoDTO> pJurados) {
        jurados = pJurados;
    }

    public List<PhotoDTO> getFotos() {
        return fotos;
    }

    public void setFotos(List<PhotoDTO> pFotos) {
        fotos = pFotos;
    }

    public List<FotografoDTO> getFotografos() {
        return fotografos;
    }

    public void setFotogorafos(List<FotografoDTO> pFotografos) {
        fotografos = pFotografos;
    }


}
