/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.entities;

/**
 *
 * @author s.acostav
 */
public class InteresFotograficoEntity {
    
    private long id;
    private String interes;
    
    public InteresFotograficoEntity(){
        
    }
    
    public Long getId(){
        return id;
    }
    
    public void setId(long pId){
        id = pId;
    }
    
    public String getInteres(){
        return interes;
    }
    
    public void setInteres(String pInteres){
        interes = pInteres;
    }
}
