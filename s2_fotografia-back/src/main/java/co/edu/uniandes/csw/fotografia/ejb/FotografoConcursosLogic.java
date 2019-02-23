/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.FotografoPersistence;
import co.edu.uniandes.csw.fotografia.persistence.ConcursoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Fotografo y Concurso.
 *
 * @author s.acostav
 */
@Stateless
public class FotografoConcursosLogic {

    private static final Logger LOGGER = Logger.getLogger(FotografoConcursosLogic.class.getName());

    @Inject
    private ConcursoPersistence concursoPersistence;

    @Inject
    private FotografoPersistence fotografoPersistence;

    /**
     * Asocia un Concurso existente a un Fotografo
     *
     * @param fotografosId Identificador de la instancia de Fotografo
     * @param concursosId Identificador de la instancia de Concurso
     * @return Instancia de ConcursoEntity que fue asociada a Fotografo
     */
    public ConcursoEntity addConcurso(Long fotografosId, Long concursosId) {
        /**LOGGER.log(Level.INFO, "Inicia proceso de asociarle un libro al autor con id = {0}", fotografosId);
        FotografoEntity fotografoEntity = fotografoPersistence.find(fotografosId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        concursoEntity.getFotografos().add(fotografoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un libro al autor con id = {0}", fotografosId);
        return concursoPersistence.find(concursosId);**/
        return null;
    }

    /**
     * Obtiene una colección de instancias de ConcursoEntity asociadas a una
     * instancia de Fotografo
     *
     * @param fotografosId Identificador de la instancia de Fotografo
     * @return Colección de instancias de ConcursoEntity asociadas a la instancia de
     * Fotografo
     */
    public List<ConcursoEntity> getConcursos(Long fotografosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros del autor con id = {0}", fotografosId);
        return fotografoPersistence.find(fotografosId).getConcursos();
    }

    /**
     * Obtiene una instancia de ConcursoEntity asociada a una instancia de Fotografo
     *
     * @param fotografosId Identificador de la instancia de Fotografo
     * @param concursosId Identificador de la instancia de Concurso
     * @return La entidadd de Libro del autor
     * @throws BusinessLogicException Si el libro no está asociado al autor
     */
    public ConcursoEntity getConcurso(Long fotografosId, Long concursosId) throws BusinessLogicException {
        /**LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} del autor con id = " + fotografosId, concursosId);
        List<ConcursoEntity> concursos = fotografoPersistence.find(fotografosId).getConcursos();
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        int index = concursos.indexOf(concursoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} del autor con id = " + fotografosId, concursosId);
        if (index >= 0) {
            return concursos.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado al autor");**/
        
        return null;
    }

    /**
     * Remplaza las instancias de Concurso asociadas a una instancia de Fotografo
     *
     * @param fotografoId Identificador de la instancia de Fotografo
     * @param concursos Colección de instancias de ConcursoEntity a asociar a instancia
     * de Fotografo
     * @return Nueva colección de ConcursoEntity asociada a la instancia de Fotografo
     */
    public List<ConcursoEntity> replaceConcursos(Long fotografoId, List<ConcursoEntity> concursos) {
        /**LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los libros asocidos al fotografo con id = {0}", fotografoId);
        FotografoEntity fotografoEntity = fotografoPersistence.find(fotografoId);
        List<ConcursoEntity> concursoList = concursoPersistence.findAll();
        for (ConcursoEntity concurso : concursoList) {
            if (concursos.contains(concurso)) {
                if (!concurso.getFotografos().contains(fotografoEntity)) {
                    concurso.getFotografos().add(fotografoEntity);
                }
            } else {
                concurso.getFotografos().remove(fotografoEntity);
            }
        }
        fotografoEntity.setConcursos(concursos);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los libros asocidos al fotografo con id = {0}", fotografoId);
        return fotografoEntity.getConcursos();**/
        return null;
    }

    /**
     * Desasocia un Concurso existente de un Fotografo existente
     *
     * @param fotografosId Identificador de la instancia de Fotografo
     * @param concursosId Identificador de la instancia de Concurso
     */
    public void removeConcurso(Long fotografosId, Long concursosId) {
        /**LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del fotografo con id = {0}", fotografosId);
        FotografoEntity fotografoEntity = fotografoPersistence.find(fotografosId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        concursoEntity.getFotografos().remove(fotografoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del fotografo con id = {0}", fotografosId);**/
    }
}
