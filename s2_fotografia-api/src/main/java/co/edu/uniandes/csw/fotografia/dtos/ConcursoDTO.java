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
 *      "tema": "/tema",
		"id" : "/id"
 *      "nombre": string,
 *      "apellido": String,
 *      "fechaNacimiento": date,
 *      "edad":number,
 *      "correo": String,
 *      "telefono": number,
 *      "pais":String,
 *      "telefono": number,
 *      "pais": String,
 *      "id": number
 * 
 *   }
 * </pre> 
 * @author n.rincond
 */
public class ConcursoDTO implements Serializable  {
	private Long id;
	private String tema;
	private List<String> restricciones ;
	private int edadDeLaFoto;
	private int maxFotos;
	private Date fechaDelConcurso;
	private int premioCantidad;

	//private OrganizadorDTO organizador


	/**
	 * Constructor vacio
	 */
	public ConcursoDTO(){

	}
        
        public ConcursoDTO(ConcursoEntity concursoEntity){
            if(concursoEntity != null){
                this.id = concursoEntity.getId();
                this.tema = concursoEntity.getTema();
                this.restricciones = concursoEntity.getRestricciones();
                this.edadDeLaFoto = concursoEntity.getEdadFoto();
                this.maxFotos = concursoEntity.getMaximasFotos();
                this.premioCantidad = concursoEntity.getCantidadPremio();
                this.fechaDelConcurso = concursoEntity.getFecha();
            }
        }
        
        public ConcursoEntity toEntity(){
            ConcursoEntity entity = new ConcursoEntity();
            entity.setId(this.getId());
            entity.setCantidadPremio(this.premioCantidad);
            entity.setFecha(this.fechaDelConcurso);
            entity.setMaximasFotos(this.maxFotos);
            entity.setEdadFoto(this.edadDeLaFoto);
            entity.setTema(this.tema);
            entity.setRestricciones(this.restricciones);
            return entity;
        }
	/**
	 *Obtiene el atributo id 
	 * @return atributo id
	 */

	public Long getId(){
		return id;
	}
	/**
	 * Establece un valor para el atributoid
	 * @param pId nuevo valor del atributo 
	 */
	public void setId(Long pId){
		id = pId;
	}

	/**
	 * Obtiene el atributo tema
	 * @return atributo tema
	 */

	public String getTema(){
		return tema;
	}
	/**
	 * Establece un valor para el atributo tema
	 * @param pTema nuevo valor para el atributo 
	 */
	public void setTema(String pTema){
		tema = pTema;
	}
	/**
	 * Obtiene el atributo restricciones
	 * @return atributo restricciones
	 */
	public List<String> getRestricciones(){
		return restricciones;
	}

	/**
	 * Establece un valor para el atributo restricciones
	 * @param pRestricciones nueva lista de restricciones
	 */
	public void setRestricciones(ArrayList<String> pRestricciones){
		restricciones = pRestricciones;
	}
	/**
	 * Agrega una restriccion a la lista de restricciones
	 *
	 */
	public void addRestriccion(String pRestriccion)
	{
		restricciones.add(pRestriccion);
	}
	/**
	 * Obtiene el atributo fechaConcurso
	 * @return atributo fechaConcurso
	 */
	public Date getFechaConcurso(){
		return fechaDelConcurso;
	}

	/**
	 * Establece un valor para el atributo fecha concurso
	 * @param pFecha nuevo valor para el atributo 
	 */
	public void setFechaConcursoString(Date pFechaConcurso){
                fechaDelConcurso = pFechaConcurso;
	}


	/**
	 * Obtiene el atributo edad de la foto
	 * @return atributo edad de la foto
	 */
	public int getEdadFoto(){
		return edadDeLaFoto;
	}

	/**
	 * Establece un valor para el atributo edad de la fotografia
	 * @param pEdad nuevo valor para el atributo edadDeLaFoto
	 */
	public void setEdadFoto(int pEdad) {
		edadDeLaFoto = pEdad;
	}


	/**
	 *Obtiene el atributo maxFotos
	 * @return atributo maxFotos
	 */
	public int getMaxFotos(){
		return maxFotos;
	}
	/**
	 * Establece un valor para el atributo maxFotos
	 * @param max nuevo valor del atributo 
	 */

	public void setMaxFotos(int max){
		maxFotos = max;
	}
	/**
	 *Obtiene el atributo premio
	 * @return atributo premio
	 */

	public int getPremioCantidad(){
		return premioCantidad;
	}
	/**
	 * Establece un valor para el atributo premio
	 * @param pPremio nuevo valor del premio
	 */
	public void setPremioCantidad(int pPremio){
		premioCantidad = pPremio;
	}



}