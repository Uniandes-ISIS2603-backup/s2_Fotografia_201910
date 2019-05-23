package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.ejb.InteresFotograficoFotosLogic;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.persistence.InteresFotograficoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author s.acostav
 */

@Stateless
public class InteresFotograficoLogic {
    
     private static final Logger LOGGER = Logger.getLogger(InteresFotograficoLogic.class.getName());

    @Inject
    private InteresFotograficoPersistence persistence;
    
    public InteresFotograficoEntity createInteresFotografico(InteresFotograficoEntity interesFotograficos) throws BusinessLogicException{
       if (interesFotograficos.getInteres()==null) {
            throw new BusinessLogicException("El interesFotografico es invalido");
        }
       if (persistence.findByInteres(interesFotograficos.getInteres())!=null) {
            throw new BusinessLogicException("El tipo ya existe");
        }
        LOGGER.log(Level.INFO, "Inicia proceso de creación del interesFotograficos");
        InteresFotograficoEntity newInteresFotograficosEntity = persistence.create(interesFotograficos);
        LOGGER.log(Level.INFO, "Termina proceso de creación del interesFotograficos");
        return newInteresFotograficosEntity;
    }
    
    public InteresFotograficoEntity getInteresFotografico(long pId){
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el interesFotograficos con id = {0}", pId);
        InteresFotograficoEntity interesFotograficosEntity = persistence.find(pId);
        if (interesFotograficosEntity == null) {
            LOGGER.log(Level.SEVERE, "El interesFotograficos con el id = {0} no existe", pId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el interesFotograficos con id = {0}", pId);
        return interesFotograficosEntity;
    }
    
    public List<InteresFotograficoEntity> getInteresesFotograficos(){
       LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los interesesFotograficos");
        List<InteresFotograficoEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los interesesFotograficos");
        return lista;
    }
    
     public InteresFotograficoEntity updateInteresFotografico(Long interesFotograficosId, InteresFotograficoEntity entity){
       LOGGER.log(Level.INFO, "Inicia proceso de actualizar el interesFotograficos con id = {0}", interesFotograficosId);
        InteresFotograficoEntity newInteresFotograficosEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el interesFotograficos con id = {0}", interesFotograficosId);
        return newInteresFotograficosEntity;
     }

     
   public void deleteInteresFotografico(Long interesFotograficosId) throws BusinessLogicException{
        List<PhotoEntity> fotos1 = getInteresFotografico(interesFotograficosId).getFotosInteres();
        if (fotos1 != null && !fotos1.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar el interes con id = " + interesFotograficosId + " porque tiene fotos asociados");
        }

     
}
