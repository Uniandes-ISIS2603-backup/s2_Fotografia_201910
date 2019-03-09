/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.persistence.ConcursoPersistence;
import co.edu.uniandes.csw.fotografia.persistence.FotografoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *Implementa la conexión con la persistencia entre la relación Concurso y Fotografo
 * @author NicolasRinconD
 */
@Stateless
public class ConcursoFotografoLogic {
    private static final Logger LOGGER = Logger.getLogger(ConcursoFotografoLogic.class.getName());

    @Inject
    private ConcursoPersistence concursoPersistence;

    @Inject
    private FotografoPersistence fotografoPersistence;

    /**
     * Asocia un Fotografo existente a un Concurso
     *
     * @param concursosId Identificador de la instancia de Concurso
     * @param fotografosId Identificador de la instancia de Fotografo
     * @return Instancia de FotografoEntity que fue asociada a Concurso
     */
    public FotografoEntity addFotografo(Long concursosId, Long fotografosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un autor al libro con id = {0}", concursosId);
        FotografoEntity fotografoEntity = fotografoPersistence.find(fotografosId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        concursoEntity.getFotografos().add(fotografoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un autor al libro con id = {0}", concursosId);
        return fotografoPersistence.find(fotografosId);
    }

    /**
     * Obtiene una colección de instancias de FotografoEntity asociadas a una
     * instancia de Concurso
     *
     * @param concursosId Identificador de la instancia de Concurso
     * @return Colección de instancias de FotografoEntity asociadas a la instancia
     * de Concurso
     */
    public List<FotografoEntity> getFotografos(Long concursosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores del libro con id = {0}", concursosId);
        return concursoPersistence.find(concursosId).getFotografos();
    }

    /**
     * Obtiene una instancia de FotografoEntity asociada a una instancia de Concurso
     *
     * @param concursosId Identificador de la instancia de Concurso
     * @param fotografosId Identificador de la instancia de Fotografo
     * @return La entidad del Autor asociada al libro
     */
    public FotografoEntity getFotografo(Long concursosId, Long fotografosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un autor del libro con id = {0}", concursosId);
        List<FotografoEntity> fotografos = concursoPersistence.find(concursosId).getFotografos();
        FotografoEntity fotografoEntity = fotografoPersistence.find(fotografosId);
        int index = fotografos.indexOf(fotografoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un autor del libro con id = {0}", concursosId);
        if (index >= 0) {
            return fotografos.get(index);
        }
        return null;
    }

    /**
     * Remplaza las instancias de Fotografo asociadas a una instancia de Concurso
     *
     * @param concursosId Identificador de la instancia de Concurso
     * @param list Colección de instancias de FotografoEntity a asociar a instancia
     * de Concurso
     * @return Nueva colección de FotografoEntity asociada a la instancia de Concurso
     */
    public List<FotografoEntity> replaceFotografos(Long concursosId, List<FotografoEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los autores del libro con id = {0}", concursosId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        concursoEntity.setFotografos(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los autores del libro con id = {0}", concursosId);
        return concursoPersistence.find(concursosId).getFotografos();
    }

    /**
     * Desasocia un Fotografo existente de un Concurso existente
     *
     * @param concursosId Identificador de la instancia de Concurso
     * @param fotografosId Identificador de la instancia de Fotografo
     */
    public void removeFotografo(Long concursosId, Long fotografosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un autor del libro con id = {0}", concursosId);
        FotografoEntity fotografoEntity = fotografoPersistence.find(fotografosId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        concursoEntity.getFotografos().remove(fotografoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un autor del libro con id = {0}", concursosId);
    }
}
