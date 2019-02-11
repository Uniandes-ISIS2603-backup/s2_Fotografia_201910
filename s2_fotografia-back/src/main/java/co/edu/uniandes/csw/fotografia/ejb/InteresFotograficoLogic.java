/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import java.util.List;

/**
 *
 * @author s.acostav
 */
public class InteresFotograficoLogic {
    
    public InteresFotograficoEntity createInteresFotografico(InteresFotograficoEntity interesFotografico){
        return new InteresFotograficoEntity();
    }
    
    public InteresFotograficoEntity getInteresFotografico(long pId){
        return new InteresFotograficoEntity();
    }
    
    public List<InteresFotograficoEntity> getInteresFotograficos(){
        return null;
    }
    
     public InteresFotograficoEntity updateInteresFotografico(Long interesFotograficoId, InteresFotograficoEntity entity){
         return new InteresFotograficoEntity();
     }
     
     public void deleteInteresFotografico(Long interesFotograficoId){
         
     }
}
