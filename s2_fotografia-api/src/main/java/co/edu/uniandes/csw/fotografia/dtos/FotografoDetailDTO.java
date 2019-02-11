/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.dtos;

import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;

/**
 *
 * @author estudiante
 */
public class FotografoDetailDTO {
    
    public FotografoDetailDTO(FotografoEntity fotografo){
        
    }
    
    public FotografoEntity toEntity(){
        return new FotografoEntity();
    }
    
    public void setId(Long pId){
        
    }
    
}
