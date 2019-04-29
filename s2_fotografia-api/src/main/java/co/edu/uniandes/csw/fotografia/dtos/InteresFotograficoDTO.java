/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import java.io.Serializable;

/**
 *
 * @author s.acostav
 *InteresFotograficoDTO Objeto de transferencia de datos de InteresesFotograficos. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {  
 *      "interes": String,
 *      "id": number
 * 
 *   }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "interes": "Paisajes",
 *      "id" : "4"
 *   }
 *
 * </pre>
 *
 * @author s.acostav
 */
public class InteresFotograficoDTO implements Serializable{
    
    private Long id;
    private String interes;
    /**
     * Constructor vacio
     */
    public InteresFotograficoDTO(){
        
    }
    
    /**
     * Constructor 
     * @param fotografoEntity 
     */
    public InteresFotograficoDTO(InteresFotograficoEntity interesEntity) {
        if (interesEntity != null) {
            this.id = interesEntity.getId();
            this.interes = interesEntity.getInteres();
        }
    }
    
    /**
     * Metodo para transformar el DTO a una entidad
     * @return nueva entidad 
     */
    
    public InteresFotograficoEntity toEntity(){
        InteresFotograficoEntity interes = new InteresFotograficoEntity();
        interes.setId(id);
        interes.setInteres(this.interes);
    return interes;
}
    
    /**
     * Obtiene el atributo id
     * @return atributo id
     */
    public Long getId(){
        return id;
    }
    
    /**
     * Establece un valor para el atributo id
     * @param pId nuevo valor para el atributo 
     */
    
    public void setId(Long pId){
     id = pId;
    }
    
    /**
     * Obtiene el atributo interes
     * @return atributo interes
     */
    public String getInteres(){
        return interes;
    }
    /**
     * Establece un valor para el atributo interes
     * @param pInteres 
     */
    public void setInteres(String pInteres){
        interes = pInteres;
    }
    
    
}