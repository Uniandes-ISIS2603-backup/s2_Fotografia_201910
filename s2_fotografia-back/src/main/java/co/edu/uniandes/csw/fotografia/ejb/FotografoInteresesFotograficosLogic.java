/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.FotografoPersistence;
import co.edu.uniandes.csw.fotografia.persistence.InteresFotograficoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Fotografo y InteresFotografico.
 *
 * @author s.acostav
 */
@Stateless
public class FotografoInteresesFotograficosLogic {

    private static final Logger LOGGER = Logger.getLogger(FotografoInteresesFotograficosLogic.class.getName());

    @Inject
    private InteresFotograficoPersistence interesFotograficoPersistence;

    @Inject
    private FotografoPersistence fotografoPersistence;

    /**
     * Asocia un InteresFotografico existente a un Fotografo
     *
     * @param fotografosId Identificador de la instancia de Fotografo
     * @param interesesFotograficosId Identificador de la instancia de InteresFotografico
     * @return Instancia de InteresFotograficoEntity que fue asociada a Fotografo
     */
    public InteresFotograficoEntity addInteresFotografico(Long fotografosId, Long interesesFotograficosId) {
       LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al autor con id = {0}", fotografosId);
        FotografoEntity fotografoEntity = fotografoPersistence.find(fotografosId);
        InteresFotograficoEntity interesFotograficoEntity = interesFotograficoPersistence.find(interesesFotograficosId);
        interesFotograficoEntity.getFotografos().add(fotografoEntity);
       List<InteresFotograficoEntity> a = fotografoEntity.getIntereses();
       a.add(interesFotograficoEntity);
       fotografoEntity.setIntereses(a);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", fotografosId);
        return interesFotograficoEntity;
    }

    /**
     * Obtiene una colección de instancias de InteresFotograficoEntity asociadas a una
     * instancia de Fotografo
     *
     * @param fotografosId Identificador de la instancia de Fotografo
     * @return Colección de instancias de InteresFotograficoEntity asociadas a la instancia de
     * Fotografo
     */
    public List<InteresFotograficoEntity> getInteresesFotograficos(Long fotografosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", fotografosId);
        return fotografoPersistence.find(fotografosId).getIntereses();
    }

    /**
     * Obtiene una instancia de InteresFotograficoEntity asociada a una instancia de Fotografo
     *
     * @param fotografosId Identificador de la instancia de Fotografo
     * @param interesesFotograficosId Identificador de la instancia de InteresFotografico
     * @return La entidadd de Libro del autor
     * @throws BusinessLogicException Si el libro no está asociado al autor
     */
    public InteresFotograficoEntity getInteresFotografico(Long fotografosId, Long interesesFotograficosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} del autor con id = " + fotografosId, interesesFotograficosId);
        List<InteresFotograficoEntity> interesesFotograficos = fotografoPersistence.find(fotografosId).getIntereses();
        InteresFotograficoEntity interesFotograficoEntity = interesFotograficoPersistence.find(interesesFotograficosId);
        int index = interesesFotograficos.indexOf(interesFotograficoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} del autor con id = " + fotografosId, interesesFotograficosId);
        if (index >= 0) {
            return interesesFotograficos.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado al autor");
    }

    /**
     * Remplaza las instancias de InteresFotografico asociadas a una instancia de Fotografo
     *
     * @param fotografoId Identificador de la instancia de Fotografo
     * @param interesesFotograficos Colección de instancias de InteresFotograficoEntity a asociar a instancia
     * de Fotografo
     * @return Nueva colección de InteresFotograficoEntity asociada a la instancia de Fotografo
     */
    public List<InteresFotograficoEntity> replaceInteresesFotograficos(Long fotografoId, List<InteresFotograficoEntity> interesesFotograficos) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al fotografo con id = {0}", fotografoId);
        FotografoEntity fotografoEntity = fotografoPersistence.find(fotografoId);
        List<InteresFotograficoEntity> interesFotograficoList = interesFotograficoPersistence.findAll();
        for (InteresFotograficoEntity interesFotografico : interesFotograficoList) {
            if (interesesFotograficos.contains(interesFotografico)) {
                if (!interesFotografico.getFotografos().contains(fotografoEntity)) {
                    interesFotografico.getFotografos().add(fotografoEntity);
                }
            } else {
                interesFotografico.getFotografos().remove(fotografoEntity);
            }
        }
        fotografoEntity.setIntereses(interesesFotograficos);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los libros asocidos al fotografo con id = {0}", fotografoId);
        return fotografoEntity.getIntereses();
    }

    /**
     * Desasocia un InteresFotografico existente de un Fotografo existente
     *
     * @param fotografosId Identificador de la instancia de Fotografo
     * @param interesesFotograficosId Identificador de la instancia de InteresFotografico
     */
    public void removeInteresFotografico(Long fotografosId, Long interesesFotograficosId) {
       LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del fotografo con id = {0}", fotografosId);
        FotografoEntity fotografoEntity = fotografoPersistence.find(fotografosId);
        InteresFotograficoEntity interesFotograficoEntity = interesFotograficoPersistence.find(interesesFotograficosId);
        interesFotograficoEntity.getFotografos().remove(fotografoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del fotografo con id = {0}", fotografosId);
    }
}
