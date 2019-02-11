/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author s.acostav
 */
@Entity
public class InteresFotograficoEntity extends BaseEntity implements Serializable{
    
    private String interes;
    
    public InteresFotograficoEntity(){
        
    }
    
  
    
    public String getInteres(){
        return interes;
    }
    
    public void setInteres(String pInteres){
        interes = pInteres;
    }
}
