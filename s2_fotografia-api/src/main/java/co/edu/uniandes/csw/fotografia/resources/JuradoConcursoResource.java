/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.ConcursoDTO;
import co.edu.uniandes.csw.fotografia.dtos.JuradoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.ConcursoLogic;
import co.edu.uniandes.csw.fotografia.ejb.JuradoConsursosLogic;
import co.edu.uniandes.csw.fotografia.ejb.JuradoLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
@Path("jurados/{juradosId: \\d+}/concurso")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JuradoConcursoResource {
    
    private static final Logger LOGGER = Logger.getLogger(JuradoConcursoResource.class.getName());

    @Inject
    private JuradoLogic juradoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private JuradoConsursosLogic juradoConcursoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ConcursoLogic concursoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

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
