/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.JuradoDTO;
import co.edu.uniandes.csw.fotografia.dtos.JuradoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.JuradoLogic;
import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("/jurados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class JuradoResource {
     private static final Logger LOGGER = Logger.getLogger(JuradoResource.class.getName());

    @Inject
    private JuradoLogic juradoLogic;

    /**
     * Crea un nuevo jurado con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param jurado {@link JuradoDTO} - EL jurado que se desea guardar.
     * @return JSON {@link JuradoDTO} - El jurado guardado con el atributo id
     * autogenerado.
     */
    @POST
    public JuradoDTO createJurado(JuradoDTO jurado) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "JuradoResourse createJurado: input: {0}", jurado);
        JuradoDTO juradoDTO = new JuradoDTO(juradoLogic.createJurado(jurado.toEntity()));
        LOGGER.log(Level.INFO, "JuradoResourse createJurado: output: {0}", juradoDTO);
        return juradoDTO;
    }

    /**
     * Busca y devuelve todos los jurados que existen en la aplicacion.
     *
     * @return JSONArray {@link JuradoDetailDTO} - Los jurados encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<JuradoDetailDTO> getJurados() {
        LOGGER.info("JuradoResourse getJurados: input: void");
        List<JuradoDetailDTO> listaJurados = listEntity2DTO(juradoLogic.getJurados());
        LOGGER.log(Level.INFO, "JuradoResourse getJurados: output: {0}", listaJurados);
        return listaJurados;
    }

    /**
     * Busca el jurado con el id asociado recibido en la URL y lo devuelve.
     *
     * @param juradosId Identificador del jurado que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link JuradoDetailDTO} - El jurado buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el jurado.
     */
    @GET
    @Path("{juradosId: \\d+}")
    public JuradoDetailDTO getJurado(@PathParam("juradosId") Long juradosId) {
        LOGGER.log(Level.INFO, "JuradoResourse get: input: {0}", juradosId);
        JuradoEntity juradoEntity = juradoLogic.getJurado(juradosId);
        if (juradoEntity == null) {
            throw new WebApplicationException("El recurso /jurados/" + juradosId + " no existe.", 404);
        }
        JuradoDetailDTO detailDTO = new JuradoDetailDTO(juradoEntity);
        LOGGER.log(Level.INFO, "JuradoResourse getJurado: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el jurado con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param juradosId Identificador del jurado que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param jurado {@link JuradoDetailDTO} El jurado que se desea guardar.
     * @return JSON {@link JuradoDetailDTO} - El jurado guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el jurado a
     * actualizar.
     */
    @PUT
    @Path("{juradosId: \\d+}")
    public JuradoDetailDTO updateJurado(@PathParam("juradosId") Long juradosId, JuradoDetailDTO jurado) {
        LOGGER.log(Level.INFO, "JuradoResourse updateJurado: input: juradosId: {0} , jurado: {1}", new Object[]{juradosId, jurado});
        jurado.setId(juradosId);
        if (juradoLogic.getJurado(juradosId) == null) {
            throw new WebApplicationException("El recurso /jurados/" + juradosId + " no existe.", 404);
        }
        JuradoDetailDTO detailDTO = new JuradoDetailDTO(juradoLogic.updateJurado(juradosId, jurado.toEntity()));
        LOGGER.log(Level.INFO, "JuradoResourse updateJurado: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el jurado con el id asociado recibido en la URL.
     *
     * @param juradosId Identificador del jurado que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el jurado a borrar.
     */
    @DELETE
    @Path("{juradosId: \\d+}")
    public void deleteJurado(@PathParam("juradosId") Long juradosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "JuradoResourse deleteJurado: input: {0}", juradosId);
        if (juradoLogic.getJurado(juradosId) == null) {
            throw new WebApplicationException("El recurso /jurados/" + juradosId + " no existe.", 404);
        }
        juradoLogic.deleteJurado(juradosId);
        LOGGER.info("JuradoResourse deleteJurado: output: void");
    }
    
     @Path("{juradosId: \\d+}/concursos")
    public Class<JuradoConcursoResource> getJuradoConcursoResource(@PathParam("juradosId") Long juradosId) {
        return JuradoConcursoResource.class;
    }

    /**
     * Convierte una lista de JuradoEntity a una lista de JuradoDetailDTO.
     *
     * @param entityList Lista de JuradoEntity a convertir.
     * @return Lista de JuradoDetailDTO convertida.
     */
    private List<JuradoDetailDTO> listEntity2DTO(List<JuradoEntity> entityList) {
        List<JuradoDetailDTO> list = new ArrayList<>();
        for (JuradoEntity entity : entityList) {
            list.add(new JuradoDetailDTO(entity));
        }
        return list;
    }
}
