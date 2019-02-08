/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

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
public class InteresFotograficoDTO {
    
    private long id;
    private String interes;
    /**
     * Constructor vacio
     */
    public InteresFotograficoDTO(){
        
    }
    
    /**
     * Obtiene el atributo id
     * @return atributo id
     */
    public long getId(){
        return id;
    }
    
    /**
     * Establece un valor para el atributo id
     * @param pId nuevo valor para el atributo 
     */
    
    public void setId(long pId){
        pId = id;
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