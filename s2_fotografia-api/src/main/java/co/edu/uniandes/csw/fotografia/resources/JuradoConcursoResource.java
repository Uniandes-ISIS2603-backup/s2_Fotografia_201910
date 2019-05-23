/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.ConcursoDTO;
import co.edu.uniandes.csw.fotografia.dtos.ConcursoDetailDTO;
import co.edu.uniandes.csw.fotografia.dtos.ConcursoDetailDTO;
import co.edu.uniandes.csw.fotografia.dtos.ConcursoDetailDTO;
import co.edu.uniandes.csw.fotografia.dtos.JuradoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.ConcursoLogic;
import co.edu.uniandes.csw.fotografia.ejb.JuradoConsursosLogic;
import co.edu.uniandes.csw.fotografia.ejb.JuradoLogic;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author a.trujilloa1
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JuradoConcursoResource {
    
    private static final Logger LOGGER = Logger.getLogger(JuradoConcursoResource.class.getName());

    @Inject
    private JuradoConsursosLogic juradoConcursoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ConcursoLogic concursoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

     /**
     * Asocia un libro existente con un autor existente
     *
     * @param juradosId El ID del autor al cual se le va a asociar el libro
     * @param concursosId El ID del libro que se asocia
     * @return JSON {@link ConcursoDetailDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{concursosId: \\d+}")
    public ConcursoDetailDTO addConcurso(@PathParam("juradosId") Long juradosId, @PathParam("concursosId") Long concursosId) {
        LOGGER.log(Level.INFO, "FotografoConcursosResource addConcurso: input: juradosId {0} , concursosId {1}", new Object[]{juradosId, concursosId});
        if (concursoLogic.getConcurso(concursosId) == null) {
            throw new WebApplicationException("El recurso /concursos/" + concursosId + " no existe.", 404);
        }
        ConcursoDetailDTO detailDTO = new ConcursoDetailDTO(juradoConcursoLogic.addConcurso(concursosId, juradosId));
        LOGGER.log(Level.INFO, "FotografoConcursosResource addConcurso: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Busca y devuelve el libro con el ID recibido en la URL, relativo a un
     * autor.
     *
     * @param juradosId El ID del autor del cual se busca el libro
     * @param concursosId El ID del libro que se busca
     * @return {@link ConcursoDetailDTO} - El libro encontrado en el autor.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     * si el libro no está asociado al autor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @GET
    public ConcursoDTO getConcurso(@PathParam("juradosId") Long juradosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FotografoConcursosResource getConcurso: input: juradosId {0} , concursosId {1}", juradosId);
        ConcursoEntity concursoEntity = juradoConcursoLogic.getConcurso(juradosId);
        if (concursoEntity == null) {
            throw new WebApplicationException("El recurso /prizes/" + juradosId + "/author no existe.", 404);
        }
        ConcursoDetailDTO concursoDetailDTO = new ConcursoDetailDTO(concursoEntity);
        LOGGER.log(Level.INFO, "PrizeAuthorResource getAuthor: output: {0}", concursoDetailDTO);
        return concursoDetailDTO;
    }
    
    /**
     * Remplaza la instancia de Concurso asociada a un Book.
     *
     * @param juradosId Identificador del libro que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param concurso La concurso que se será del libro.
     * @return JSON {@link JuradoDetailDTO} - El arreglo de libros guardado en la
     * concurso.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la concurso o el
     * libro.
     */
    @PUT
    public JuradoDetailDTO replaceConcurso(@PathParam("juradosId") Long juradosId, ConcursoDTO concurso) {
        LOGGER.log(Level.INFO, "JuradoConcursoResource replaceConcurso: input: juradosId{0} , Concurso:{1}", new Object[]{juradosId, concurso});
        //if (concursoLogic.getJurado(juradosId) == null) {
          //  throw new WebApplicationException("El recurso /jurados/" + juradosId + " no existe.", 404);
        //}
        if (concursoLogic.getConcurso(concurso.getId()) == null) {
            throw new WebApplicationException("El recurso /concursos/" + concurso.getId() + " no existe.", 404);
        }
        JuradoDetailDTO juradoDetailDTO = new JuradoDetailDTO(juradoConcursoLogic.replaceConcurso(juradosId, concurso.getId()));
        LOGGER.log(Level.INFO, "JuradoConcursoResource replaceConcurso: output: {0}", juradoDetailDTO);
        return juradoDetailDTO;
    }
        
}
