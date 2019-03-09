/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
import co.edu.uniandes.csw.fotografia.persistence.FotografoPersistence;
import co.edu.uniandes.csw.fotografia.persistence.InteresFotograficoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de InteresFotografico y Fotografo.
 *
 * @author s.acostav
 */
@Stateless
public class InteresFotograficoFotografosLogic {

    private static final Logger LOGGER = Logger.getLogger(InteresFotograficoFotografosLogic.class.getName());

    @Inject
    private InteresFotograficoPersistence interesFotograficoPersistence;

    @Inject
    private FotografoPersistence fotografoPersistence;

    /**
     * Asocia un Fotografo existente a un InteresFotografico
     *
     * @param interesesFotograficosId Identificador de la instancia de InteresFotografico
     * @param fotografosId Identificador de la instancia de Fotografo
     * @return Instancia de FotografoEntity que fue asociada a InteresFotografico
     */
    public FotografoEntity addFotografo(Long interesesFotograficosId, Long fotografosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un autor al libro con id = {0}", interesesFotograficosId);
         InteresFotograficoEntity interesEntity = interesFotograficoPersistence.find(interesesFotograficosId);
        FotografoEntity fEntity = fotografoPersistence.find(fotografosId);
        interesEntity.getFotografos().add(fEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un autor al libro con id = {0}", interesesFotograficosId);
        return fotografoPersistence.find(fotografosId);
    }

    /**
     * Obtiene una colección de instancias de FotografoEntity asociadas a una
     * instancia de InteresFotografico
     *
     * @param interesesFotograficosId Identificador de la instancia de InteresFotografico
     * @return Colección de instancias de FotografoEntity asociadas a la instancia
     * de InteresFotografico
     */
    public List<FotografoEntity> getFotografos(Long interesesFotograficosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores del libro con id = {0}", interesesFotograficosId);
        return interesFotograficoPersistence.find(interesesFotograficosId).getFotografos();
    }

    /**
     * Obtiene una instancia de FotografoEntity asociada a una instancia de InteresFotografico
     *
     * @param interesesFotograficosId Identificador de la instancia de InteresFotografico
     * @param fotografosId Identificador de la instancia de Fotografo
     * @return La entidad del Autor asociada al libro
     */
    public FotografoEntity getFotografo(Long interesesFotograficosId, Long fotografosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", interesesFotograficosId);
        List<FotografoEntity> fotografos = interesFotograficoPersistence.find(interesesFotograficosId).getFotografos();
        FotografoEntity fotografoEntity = fotografoPersistence.find(fotografosId);
        int index = fotografos.indexOf(fotografoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un autor del libro con id = {0}", interesesFotograficosId);
        if (index >= 0) {
            return fotografos.get(index);
        }
        return null;
    }

    /**
     * Remplaza las instancias de Fotografo asociadas a una instancia de InteresFotografico
     *
     * @param interesesFotograficosId Identificador de la instancia de InteresFotografico
     * @param list Colección de instancias de FotografoEntity a asociar a instancia
     * de InteresFotografico
     * @return Nueva colección de FotografoEntity asociada a la instancia de InteresFotografico
     */
    public List<FotografoEntity> replaceFotografos(Long interesesFotograficosId, List<FotografoEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los autores del libro con id = {0}", interesesFotograficosId);
        InteresFotograficoEntity interesFotograficoEntity = interesFotograficoPersistence.find(interesesFotograficosId);
        interesFotograficoEntity.setFotografos(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los autores del libro con id = {0}", interesesFotograficosId);
        return interesFotograficoPersistence.find(interesesFotograficosId).getFotografos();
    }

    /**
     * Desasocia un Fotografo existente de un InteresFotografico existente
     *
     * @param interesesFotograficosId Identificador de la instancia de InteresFotografico
     * @param fotografosId Identificador de la instancia de Fotografo
     */
    public void removeFotografo(Long interesesFotograficosId, Long fotografosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor del libro con id = {0}", interesesFotograficosId);
        FotografoEntity fotografoEntity = fotografoPersistence.find(fotografosId);
        InteresFotograficoEntity interesFotograficoEntity = interesFotograficoPersistence.find(interesesFotograficosId);
        interesFotograficoEntity.getFotografos().remove(fotografoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un autor del libro con id = {0}", interesesFotograficosId);
    }
}

