/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;
import co.edu.uniandes.csw.fotografia.adapters.DateAdapter;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 *
 * @author n.rincond
 *ConcursoDTO Objeto de transferencia de datos de Concursos. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {  
 *      "id" : number,
 *      "tema": string,
 *      "restricciones": [string, ... , string],
 *      "edadDeLaFoto": number,
 *      "maxFotos" : number,
 *      "fechaDelConcurso" : date,
 *      "premioCantidad" : number
 *      "ronda":{@link RondaDTO},
 *      "organizador":{@link OrganizadorDTO}
 *      
 *   }
 * </pre> por ejemplo : <br>   
 * <pre>
 * 
 *  {  
 *      "id" : 43,
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
 *      }
 *      
 *   }
 * </pre>
 * @author n.rincond
 */
public class ConcursoDTO implements Serializable {

    private Long id;
    private String tema;
    private List<String> restricciones;
    private Integer edadDeLaFoto;
    private Integer maxFotos;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaDelConcurso;
    private Integer premioCantidad;

    private OrganizadorDTO organizador;
    private RondaDTO ronda;

    /**
     * Constructor vacio
     */
    public ConcursoDTO() {

    }

    public ConcursoDTO(ConcursoEntity concursoEntity) {
        if (concursoEntity != null) {
            this.id = concursoEntity.getId();
            this.tema = concursoEntity.getTema();
            this.restricciones = concursoEntity.getRestricciones();
            this.edadDeLaFoto = concursoEntity.getEdadFoto();
            this.maxFotos = concursoEntity.getMaximasFotos();
            this.premioCantidad = concursoEntity.getCantidadPremio();
            this.fechaDelConcurso = concursoEntity.getFecha();
             if (concursoEntity.getOrganizador() != null) {
                this.organizador = new OrganizadorDTO(concursoEntity.getOrganizador());
            } else {
                concursoEntity.setOrganizador(null);
            }
             if(concursoEntity.getRonda() != null){
                 this.ronda = new RondaDTO(concursoEntity.getRonda());
             }
             else{
                 concursoEntity.setRonda(null);
             }
        }
    }

    public ConcursoEntity toEntity() {
        ConcursoEntity entity = new ConcursoEntity();
        entity.setId(this.getId());
        entity.setCantidadPremio(this.premioCantidad);
        entity.setFecha(this.fechaDelConcurso);
        entity.setMaximasFotos(this.maxFotos);
        entity.setEdadFoto(this.edadDeLaFoto);
        entity.setTema(this.tema);
        entity.setRestricciones(this.restricciones);
        if(organizador != null )entity.setOrganizador(organizador.toEntity());
        if(ronda != null) entity.setRonda(ronda.toEntity());
        return entity;
    }

    /**
     * Obtiene el atributo id
     *
     * @return atributo id
     */

    public Long getId() {
        return id;
    }

    /**
     * Establece un valor para el atributoid
     *
     * @param pId nuevo valor del atributo
     */
    public void setId(Long pId) {
        id = pId;
    }

    /**
     * Obtiene el atributo tema
     *
     * @return atributo tema
     */
    public String getTema() {
        return tema;
    }

    /**
     * Establece un valor para el atributo tema
     *
     * @param pTema nuevo valor para el atributo
     */
    public void setTema(String pTema) {
        tema = pTema;
    }

    /**
     * Obtiene el atributo restricciones
     *
     * @return atributo restricciones
     */
    public List<String> getRestricciones() {
        return restricciones;
    }

    /**
     * Establece un valor para el atributo restricciones
     *
     * @param pRestricciones nueva lista de restricciones
     */
    public void setRestricciones(ArrayList<String> pRestricciones) {
        restricciones = pRestricciones;
    }

    /**
     * Agrega una restriccion a la lista de restricciones
     *
     */
    public void addRestriccion(String pRestriccion) {
        restricciones.add(pRestriccion);
    }

    /**
     * Obtiene el atributo fechaConcurso
     *
     * @return atributo fechaConcurso
     */
    public Date getFechaConcurso() {
        return fechaDelConcurso;
    }

    /**
     * Establece un valor para el atributo fecha concurso
     *
     * @param pFecha nuevo valor para el atributo
     */
    public void setFechaConcurso(Date pFechaConcurso) {
        fechaDelConcurso = pFechaConcurso;
    }

    /**
     * Obtiene el atributo edad de la foto
     *
     * @return atributo edad de la foto
     */
    public int getEdadDeLaFoto() {
        return edadDeLaFoto;
    }

    /**
     * Establece un valor para el atributo edad de la fotografia
     *
     * @param pEdad nuevo valor para el atributo edadDeLaFoto
     */
    public void setEdadDeLaFoto(int pEdad) {
        edadDeLaFoto = pEdad;
    }

    /**
     * Obtiene el atributo maxFotos
     *
     * @return atributo maxFotos
     */
    public int getMaxFotos() {
        return maxFotos;
    }

    /**
     * Establece un valor para el atributo maxFotos
     *
     * @param max nuevo valor del atributo
     */

    public void setMaxFotos(int max) {
        maxFotos = max;
    }

    /**
     * Obtiene el atributo premio
     *
     * @return atributo premio
     */

    public int getPremioCantidad() {
        return premioCantidad;
    }

    /**
     * Establece un valor para el atributo premio
     *
     * @param pPremio nuevo valor del premio
     */
    public void setPremioCantidad(int pPremio) {
        premioCantidad = pPremio;
    }

    public OrganizadorDTO getOrganizador() {
        return organizador;
    }

    public void setOrganizador(OrganizadorDTO organizador) {
        this.organizador = organizador;
    }

    public RondaDTO getRonda() {
        return ronda;
    }

    public void setRonda(RondaDTO ronda) {
        this.ronda = ronda;
    }


}