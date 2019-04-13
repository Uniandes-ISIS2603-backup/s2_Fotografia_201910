/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.RondaEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * OrganizadorDTO Objeto de transferencia de datos de Organizador. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "numRonda": number
 *   }
 * </pre> Por ejemplo un Organizador se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "numRonda": 2
 *   }
 *
 * </pre>
 *
 * @author Nicolas Melendez
 */
public class RondaDTO implements Serializable {

    private Long id;
    private Integer numRonda;
   

    /**
     * Constructor vacio
     */
    public RondaDTO() {
    }

    /**
     * Crea un objeto RondaDTO a partir de un objeto RondaEntity.
     *
     * @param rondaEntity Entidad AuthorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public RondaDTO(RondaEntity rondaEntity) {
        if (rondaEntity != null) {
            this.id = rondaEntity.getId();
            this.numRonda = rondaEntity.getNumeroRonda();
        }
    }

    /**
     * Convierte un objeto RondaDTO a RondaEntity.
     *
     * @return Nueva objeto RondaEntity.
     *
     */
    public RondaEntity toEntity() {
        RondaEntity rondaEntity = new RondaEntity();
        rondaEntity.setId(this.getId());
        rondaEntity.setNumeroRonda(this.getNumRonda());
        return rondaEntity;
    }

    /**
     * Obtiene el Organizador id.
     *
     * @return atributo id.
     *
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumRonda() {
        return numRonda;
    }

    public void setNumRonda(Integer numRonda) {
        this.numRonda = numRonda;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
