 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.ConcursoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicolas Rincon
 */

@Stateless
public class ConcursoLogic {
    
     private static final Logger LOGGER = Logger.getLogger(ConcursoLogic.class.getName());

    @Inject
    private ConcursoPersistence persistence;
    
    public ConcursoEntity createConcurso(ConcursoEntity concurso) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de creación del concurso");
        if(concurso.getEdadFoto() < 0){
            throw new BusinessLogicException("La edad de la foto es inválida");
        }
        if(concurso.getTema() == null || concurso.getTema().length() == 0){
            throw new BusinessLogicException("Tema invalido");
        }
        if(concurso.getMaximasFotos() <= 0){
            throw new BusinessLogicException("El numero maximo de fotos debe ser al menos uno");
        }
        if(concurso.getEdadFoto() <= 0){
            throw new BusinessLogicException("La edad de la foto debe ser un numero positivo");
        }
        ConcursoEntity newConcursoEntity = persistence.create(concurso);
        LOGGER.log(Level.INFO, "Termina proceso de creación del concurso");
        return newConcursoEntity;
    }
    
    public ConcursoEntity getConcurso(long pId){
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el concurso con id = {0}", pId);
        ConcursoEntity concursoEntity = persistence.find(pId);
        if (concursoEntity == null) {
            LOGGER.log(Level.SEVERE, "El concurso con el id = {0} no existe", pId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el concurso con id = {0}", pId);
        return concursoEntity;
    }
    
    public List<ConcursoEntity> getConcursos(){
       LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los concursos");
        List<ConcursoEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los concursos");
        return lista;
    }
    
     public ConcursoEntity updateConcurso(Long concursoId, ConcursoEntity entity){
       LOGGER.log(Level.INFO, "Inicia proceso de actualizar el concurso con id = {0}", concursoId);
        ConcursoEntity newConcursoEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el concurso con id = {0}", concursoId);
        return newConcursoEntity;
     }
     
     public void deleteConcurso(Long concursoId){
         //ESTE METODO NO SE DEBERIA USAR YA QUE TODOS LOS CONCURSOS QUEDAN GUARDADOS
     }
     
}
