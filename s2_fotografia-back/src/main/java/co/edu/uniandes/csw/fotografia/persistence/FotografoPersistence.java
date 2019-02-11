/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.persistence;

import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author s.acostav
 */

@Stateless
public class FotografoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(FotografoPersistence.class.getName());
    
    @PersistenceContext(unitName = "fotografiaPU")
    protected EntityManager em;
    
    public FotografoEntity create(FotografoEntity fotografoEntity){
        LOGGER.log(Level.INFO,"Creando un fotografo nuevo");
        em.persist(fotografoEntity);
        LOGGER.log(Level.INFO,"Saliendo de crear una editorial nueva");
        return fotografoEntity;
    }
    
    
}
