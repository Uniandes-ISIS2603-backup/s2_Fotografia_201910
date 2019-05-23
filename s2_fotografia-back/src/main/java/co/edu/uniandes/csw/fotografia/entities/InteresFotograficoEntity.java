/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.acostav
 */
@Entity
public class InteresFotograficoEntity extends BaseEntity implements Serializable{
    
    private String interes;
    private String foto;
    @PodamExclude
    @OneToMany(mappedBy = "interes")
    private List<PhotoEntity> fotosInteres= new ArrayList<PhotoEntity>();

    
    public InteresFotograficoEntity(){
        
    }
    
    public String getFoto(){
        return foto;
    }
    public void setFoto(String pFoto){
        foto = pFoto;
    }
    
  
   
    
    public String getInteres(){
        return interes;
    }
    
    public void setInteres(String pInteres){
        interes = pInteres;
    }

    /**
     * @return the fotosInteres
     */
    public List<PhotoEntity> getFotosInteres() {
        return fotosInteres;
    }

    /**
     * @param fotosInteres the fotosInteres to set
     */
    public void setFotosInteres(List<PhotoEntity> fotosInteres) {
        this.fotosInteres = fotosInteres;
    }
}
