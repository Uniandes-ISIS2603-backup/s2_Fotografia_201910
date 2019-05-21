/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.OrganizadorEntity;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que extiende de {@link OrganizadorDTO} para manejar las relaciones entre los
 * OrganizadorDTO y otros DTOs. Para conocer el contenido de un Organizador vaya a la
 * documentacion de {@link OrganizadorDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "foto": string,
 *      "nombre": string,
 *      "apellido": string,
 *      "edad": number,
 *      "correo": string,
 *      "telefono": number,
 *      "pais": string
 *      "concursos": [{@link ConcursoDTO}]
 *   }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "foto": "asfs",
 *      "nombre": "Jose",
 *      "apellido": "Ramirez",
 *      "edad": 29,
 *      "correo": "jRamirez@example.com",
 *      "telefono": 31293829,
 *      "pais": "Colombia"
 *      "concursos" : [
 *          {
 *             "id" : 43,
 *             "tema": "Arduo Dolor",
 *             "restricciones": ["Solo fotos digitales", "Maxima resolucion de 2000x2000" , "Solo fotos monocromas"],
 *             "edadDeLaFoto": 5,
 *             "maxFotos" : 80,
 *             "fechaDelConcurso" : 06/12/2019,
 *             "premioCantidad" : 120000
 *          },
 *          {
 *             "id" : 22,
 *             "tema": "Paisaje",
 *             "restricciones": ["Solo fotos digitales", "Maxima resolucion de 2000x2000" , "Solo fotos monocromas"],
 *             "edadDeLaFoto": 2,
 *             "maxFotos" : 100,
 *             "fechaDelConcurso" : 02/02/2019,
 *             "premioCantidad" : 200000
 *          }
 *      ] 
 *   }
 *
 * </pre>
 *
 * @author Nicolas Melendez
 */
public class OrganizadorDetailDTO extends OrganizadorDTO implements Serializable {

    // relación  cero o muchos concursos
    private List<ConcursoDTO> concursos;

    public OrganizadorDetailDTO() {
        super();
    }

    /**
     * Crea un objeto OrganizadorDetailDTO a partir de un objeto OrganizadorEntity
     * incluyendo los atributos de OrganizadorDTO.
     *
     * @param organizadorEntity Entidad OrganizadorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public OrganizadorDetailDTO(OrganizadorEntity organizadorEntity) {
        super(organizadorEntity);
        if (organizadorEntity != null) {
            concursos = new ArrayList<>();
            for (ConcursoEntity entityConcursos : organizadorEntity.getConcursos()) {
                concursos.add(new ConcursoDTO(entityConcursos));
            }
        }
    }

    /**
     * Convierte un objeto OrganizadorDetailDTO aOrganizadorEntity incluyendo los
     * atributos de OrganizadorDTO.
     *
     * @return Nueva objeto OrganizadorEntity.
     *
     */
    @Override
    public OrganizadorEntity toEntity() {
        OrganizadorEntity organizadorEntity = super.toEntity();
        if (concursos != null) {
            List<ConcursoEntity> concursosEntity = new ArrayList<>();
            for (ConcursoDTO dtoBook : concursos) {
                concursosEntity.add(dtoBook.toEntity());
            }
            organizadorEntity.setConcursos(concursosEntity);
        }
    
        return organizadorEntity;
    }

    /**
     * Obtiene la lista de cocursos del organizador
     *
     * @return the concursos
     */
    public List<ConcursoDTO> getConcursos() {
        return concursos;
    }

    /**
     * Modifica la lista de concursos para el organizador
     *
     * @param concursos the concursos to set
     */
    public void setConcursos(List<ConcursoDTO> concursos) {
        this.concursos = concursos;
    }

   
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}