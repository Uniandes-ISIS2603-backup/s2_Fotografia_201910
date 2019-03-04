package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase que extiende de {@link ConcursoDTO} para manejar las relaciones entre los
 * ConcursoDTO y otros DTOs. Para conocer el contenido de la un Concurso vaya a la
 * documentacion de {@link ConcursoDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *  {  
 *      "id" : number,
 *      "tema": string,
 *      "restricciones": [string, ... , string],
 *      "edadDeLaFoto": number,
 *      "maxFotos" : number,
 *      "fechaDelConcurso" : date,
 *      "premioCantidad" : number
 *      "ronda":{@link RondaDTO},
 *      "organizador":{@link OrganizadorDTO},
 *      "jurados":[{@link JuradoDTO}],
 *      "fotos":[{@link PhotoDTO}],
 *      "fotografos":[{@link FotografoDTO}]
 *      
 *   }
 * </pre> Por ejemplo un Concurso se representa asi:<br>
 *  "id" : 43,
 *      "tema": "Arduo Dolor",
 *      "restricciones": ["Solo fotos digitales", "Maxima resolucion de 2000x2000" , "Solo fotos monocromas"],
 *      "edadDeLaFoto": 5,
 *      "maxFotos" : 80,
 *      "fechaDelConcurso" : 06/12/2019,
 *      "premioCantidad" : 120000
 *      "ronda":
 *      {
 *        "id" : 1,
 *        "numeroRonda" : 2
 *      },
 *      "organizador":
 *      {
 *       "id": 1,
 *       "nombre": "Jose",
 *       "apellido": "Ramirez",
 *       "edad": 29,
 *       "correo": "jRamirez@example.com",
 *       "telefono": 31293829,
 *       "pais": "Colombia"
 *      },
 *      "jurados":[
 * 
 * 
 *      ],
 *      "fotos":[
 *      ],
 *      "fotografos":[
 *           {
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
 *         },
 *      {
 *      "interesesFotograficos": ["animales, urbano"],
 *      "id": "33",
 *      "nombre": "Los",
 *      "apellido": "Monos",
 *      "fechaNacimiento": "44/12/01",
 *      "edad": "16",
 *      "correo": "m.biono,
 *      "telefono": "37790748",
 *      "pais": "Colombia",
 *      "telefono": "37532118",
 *       }
 * 
 *      ]
 *          
 *      
 *<pre>
 * 
 **/
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
