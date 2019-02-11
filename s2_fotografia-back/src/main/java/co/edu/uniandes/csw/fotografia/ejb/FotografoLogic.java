/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import java.util.List;

/**
 *
 * @author s.acostav
 */
public class FotografoLogic {
    
    public FotografoEntity createFotografo(FotografoEntity fotografo){
        return new FotografoEntity();
    }
    
    public FotografoEntity getFotografo(long pId){
        return new FotografoEntity();
    }
    
    public List<FotografoEntity> getFotografos(){
        return null;
    }
    
     public FotografoEntity updateFotografo(Long fotografoId, FotografoEntity entity){
         return new FotografoEntity();
     }
     
     public void deleteFotografo(Long fotografoId){
         
     }
     
}
