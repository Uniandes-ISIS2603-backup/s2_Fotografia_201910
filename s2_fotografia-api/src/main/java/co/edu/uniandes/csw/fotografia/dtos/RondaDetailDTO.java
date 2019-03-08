/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.RondaEntity;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que extiende de {@link PrizeDTO} para manejar las relaciones entre los
 * PrizeDTO y otros DTOs. Para conocer el contenido de un Premio vaya a la
 * documentacion de {@link PrizeDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "numeroRonda": number
 *      "jurado": {@link JuradoDTO},
 *      "concurso": {@link ConcursoDTO}
 *   }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "numeroRonda": 2
 *      "jurado":
 *      {
 *          "id": 1,
 *          "nombre": "Jose",
 *          "apellido": "Ramirez",
 *          "correo": "jRamirez@example.com",
 *          "cedula": 31293829,
 *          "pais": "Colombia"
 *          "ciudad": "Bogota"
 *      },
 *      "concurso":
 *      {
 *         "id" : 43,
 *         "tema": "Arduo Dolor",
 *         "restricciones": ["Solo fotos digitales", "Maxima resolucion de 2000x2000" , "Solo fotos monocromas"],
 *         "edadDeLaFoto": 5,
 *         "maxFotos" : 80,
 *         "fechaDelConcurso" : 06/12/2019,
 *         "premioCantidad" : 120000
 *      }
 *  }
 *
 * </pre>
 *
 * @author Nicolas Melendez
 */
public class RondaDetailDTO extends RondaDTO implements Serializable {

    private JuradoDTO jurado;
    
    private ConcursoDTO concurso;

    /**
     * Constructor por defecto
     */
    public RondaDetailDTO() {
        super();
    }

    /**
     * Crea un objeto RondaDetailDTO a partir de un objeto RondaEntity
     * incluyendo los atributos de RondaDTO.
     *
     * @param rondaEntity Entidad RondaEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public RondaDetailDTO(RondaEntity rondaEntity) {
        super(rondaEntity);
        if (rondaEntity.getJurado() != null) {
            this.jurado = new JuradoDTO(rondaEntity.getJurado());
        }
        if (rondaEntity.getConcurso() != null) {
            this.concurso = new ConcursoDTO(rondaEntity.getConcurso());
        }
    }

    /**
     * Convierte un objeto RondaDetailDTO a RondaEntity incluyendo los atributos
     * de RondaDTO.
     *
     * @return Nueva objeto RondaEntity.
     *
     */
    @Override
    public RondaEntity toEntity() {
        RondaEntity rondaEntity = super.toEntity();
        if (jurado != null) {
            rondaEntity.setJurado(jurado.toEntity());
        }
        if (concurso != null) {
            rondaEntity.setConcurso(concurso.toEntity());
        }
        return rondaEntity;
    }

    public JuradoDTO getJurado() {
        return jurado;
    }

    public void setJurado(JuradoDTO jurado) {
        this.jurado = jurado;
    }

    public ConcursoDTO getConcurso() {
        return concurso;
    }

    public void setConcurso(ConcursoDTO concurso) {
        this.concurso = concurso;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
