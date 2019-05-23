/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.FotografoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.acostav
 */

@Stateless
public class FotografoLogic {
    
     private static final Logger LOGGER = Logger.getLogger(FotografoLogic.class.getName());

    @Inject
    private FotografoPersistence persistence;
    
    public FotografoEntity createFotografo(FotografoEntity fotografo) throws BusinessLogicException{
       
       if (persistence.findByLogin(fotografo.getLogin())!=null) {
            throw new BusinessLogicException("El login ya existe");
        }
        LOGGER.log(Level.INFO, "Inicia proceso de creación del fotografo");
        FotografoEntity newFotografoEntity = persistence.create(fotografo);
        LOGGER.log(Level.INFO, "Termina proceso de creación del fotografo");
        return newFotografoEntity;
    }
    
    public FotografoEntity getFotografo(long pId){
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el fotografo con id = {0}", pId);
        FotografoEntity fotografoEntity = persistence.find(pId);
        if (fotografoEntity == null) {
            LOGGER.log(Level.SEVERE, "El fotografo con el id = {0} no existe", pId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el fotografo con id = {0}", pId);
        return fotografoEntity;
    }
    
     /**
     * Se busca el fotografo con el login ingresado por parametro
     * @param login el login del cliente que se quiere consultar
     * @return el fotografo buscado
     */
    public FotografoEntity getFotografoByLogin(String login)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el cliente con login= {0}", login);
        
        FotografoEntity fotografo = persistence.findByLogin(login);
        if(fotografo == null)
        {
            LOGGER.log(Level.SEVERE, "El cliente con el login = {0} no existe", login);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el cliente con login = {0}", login);
        return fotografo;
    }
    
    public List<FotografoEntity> getFotografos(){
       LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los fotografos");
        List<FotografoEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los fotografos");
        return lista;
    }
    
     public FotografoEntity updateFotografo(Long fotografoId, FotografoEntity entity){
       LOGGER.log(Level.INFO, "Inicia proceso de actualizar el fotografo con id = {0}", fotografoId);
        FotografoEntity newFotografoEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el fotografo con id = {0}", fotografoId);
        return newFotografoEntity;
     }
     
     public void deleteFotografo(Long fotografoId) throws BusinessLogicException{
         LOGGER.log(Level.INFO, "Inicia proceso de borrar el fotografo con id = {0}", fotografoId);
        List<PhotoEntity> fotos = getFotografo(fotografoId).getFotos();
        List<PhotoEntity> fotos1 = getFotografo(fotografoId).getFotosConcurso();
        if (fotos != null && !fotos.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el fotografo con id = " + fotografoId + " porque tiene fotos asociados");
        }
        if (fotos1 != null && !fotos1.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el fotografo con id = " + fotografoId + " porque tiene fotos asociados");
        }
        
        persistence.delete(fotografoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el fotografo con id = {0}", fotografoId);
     }

     
}
