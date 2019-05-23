/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.CalificacionEntity;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.ConcursoPersistence;
import co.edu.uniandes.csw.fotografia.persistence.JuradoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author a.trujilloa1
 */
@Stateless
public class JuradoConsursosLogic {
    private static final Logger LOGGER = Logger.getLogger(JuradoConsursosLogic.class.getName());

    @Inject
    private JuradoPersistence juradoPersistence;

    @Inject
    private ConcursoPersistence concursoPersistence;

    /**
     * Agregar un cliente a un calficacion
     *
     * @param juradosId El id calficacion a guardar
     * @param clientesId El id del cliente al cual se le va a guardar el calficacion.
     * @return El calficacion que fue agregado al cliente.
     */
    public ConcursoEntity addConcurso( Long concursosId, Long juradosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar el cliente con id = {0} al calficacion con id = " + juradosId, concursosId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        JuradoEntity juradoEntity = juradoPersistence.find( juradosId);
        juradoEntity.setConcursoJurado(concursoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el cliente con id = {0} al calficacion con id = " + juradosId, concursosId);
        return concursoEntity;
    }
    
    /**
     *
     * Obtener un calficacion por medio de su id y el de su cliente.
     *
     * @param juradosId id del calficacion a ser buscado.
     * @return el cliente solicitada por medio de su id.
     */
    public ConcursoEntity getConcurso( Long juradosId) {
         ConcursoEntity concurso = juradoPersistence.find(juradosId).getConcursoJurado();
        return concurso;
    }
    
    /**
     * Remplazar la concurso de un jurado.
     *
     * @param juradosId id del jurado que se quiere actualizar.
     * @param concursosId El id de la concurso que se será del jurado.
     * @return el nuevo jurado.
     */
    public JuradoEntity replaceConcurso(Long juradosId, Long concursosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar jurado con id = {0}", juradosId);
        ConcursoEntity concursoEntity = concursoPersistence.find(concursosId);
        JuradoEntity juradoEntity = juradoPersistence.find(juradosId);
        juradoEntity.setConcursoJurado(concursoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar jurado con id = {0}", juradoEntity.getId());
        return juradoEntity;
    }

    /**
     * Borrar un jurado de una concurso. Este metodo se utiliza para borrar la
     * relacion de un jurado.
     *
     * @param juradosId El jurado que se desea borrar de la concurso.
     */
    public void removeConcurso(Long juradosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Concurso del jurado con id = {0}", juradosId);
        JuradoEntity juradoEntity = juradoPersistence.find(juradosId);
        ConcursoEntity concursoEntity = concursoPersistence.find(juradoEntity.getConcursoJurado().getId());
        juradoEntity.setConcursoJurado(null);
        //concursoEntity.getBooks().remove(juradoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Concurso del jurado con id = {0}", juradoEntity.getId());
    }
    
}
