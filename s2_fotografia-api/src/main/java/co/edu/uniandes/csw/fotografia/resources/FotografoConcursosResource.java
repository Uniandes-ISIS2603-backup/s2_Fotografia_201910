/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.ConcursoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.FotografoConcursosLogic;
import co.edu.uniandes.csw.fotografia.ejb.ConcursoLogic;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.mappers.WebApplicationExceptionMapper;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "fotografos/{id}/concursos".
 *
 * @author s.acostav
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FotografoConcursosResource {

    private static final Logger LOGGER = Logger.getLogger(FotografoConcursosResource.class.getName());

    @Inject
    private FotografoConcursosLogic fotografoConcursoLogic;

    @Inject
    private ConcursoLogic concursoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Asocia un libro existente con un autor existente
     *
     * @param fotografosId El ID del autor al cual se le va a asociar el libro
     * @param concursosId El ID del libro que se asocia
     * @return JSON {@link ConcursoDetailDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{concursosId: \\d+}")
    public ConcursoDetailDTO addConcurso(@PathParam("fotografosId") Long fotografosId, @PathParam("concursosId") Long concursosId) {
        LOGGER.log(Level.INFO, "FotografoConcursosResource addConcurso: input: fotografosId {0} , concursosId {1}", new Object[]{fotografosId, concursosId});
        if (concursoLogic.getConcurso(concursosId) == null) {
            throw new WebApplicationException("El recurso /concursos/" + concursosId + " no existe.", 404);
        }
        ConcursoDetailDTO detailDTO = new ConcursoDetailDTO(fotografoConcursoLogic.addConcurso(fotografosId, concursosId));
        LOGGER.log(Level.INFO, "FotografoConcursosResource addConcurso: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en un autor.
     *
     * @param fotografosId El ID del autor del cual se buscan los libros
     * @return JSONArray {@link ConcursoDetailDTO} - Los libros encontrados en el
     * autor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ConcursoDetailDTO> getConcursos(@PathParam("fotografosId") Long fotografosId) {
        LOGGER.log(Level.INFO, "FotografoConcursosResource getConcursos: input: {0}", fotografosId);
        List<ConcursoDetailDTO> lista = concursosListEntity2DTO(fotografoConcursoLogic.getConcursos(fotografosId));
        LOGGER.log(Level.INFO, "FotografoConcursosResource getConcursos: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el libro con el ID recibido en la URL, relativo a un
     * autor.
     *
     * @param fotografosId El ID del autor del cual se busca el libro
     * @param concursosId El ID del libro que se busca
     * @return {@link ConcursoDetailDTO} - El libro encontrado en el autor.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     * si el libro no está asociado al autor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @GET
    @Path("{concursosId: \\d+}")
    public ConcursoDetailDTO getConcurso(@PathParam("fotografosId") Long fotografosId, @PathParam("concursosId") Long concursosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FotografoConcursosResource getConcurso: input: fotografosId {0} , concursosId {1}", new Object[]{fotografosId, concursosId});
        if (concursoLogic.getConcurso(concursosId) == null) {
            throw new WebApplicationException("El recurso /concursos/" + concursosId + " no existe.", 404);
        }
        ConcursoDetailDTO detailDTO = new ConcursoDetailDTO(fotografoConcursoLogic.getConcurso(fotografosId, concursosId));
        LOGGER.log(Level.INFO, "FotografoConcursosResource getConcurso: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de libros de un autor con la lista que se recibe en el
     * cuerpo
     *
     * @param fotografosId El ID del autor al cual se le va a asociar el libro
     * @param concursos JSONArray {@link ConcursoDetailDTO} - La lista de libros que se
     * desea guardar.
     * @return JSONArray {@link ConcursoDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<ConcursoDetailDTO> replaceConcursos(@PathParam("fotografosId") Long fotografosId, List<ConcursoDetailDTO> concursos) {
        LOGGER.log(Level.INFO, "FotografoConcursosResource replaceConcursos: input: fotografosId {0} , concursos {1}", new Object[]{fotografosId, concursos});
        for (ConcursoDetailDTO concurso : concursos) {
            if (concursoLogic.getConcurso(concurso.getId()) == null) {
                throw new WebApplicationException("El recurso /concursos/" + concurso.getId() + " no existe.", 404);
            }
        }
        List<ConcursoDetailDTO> lista = concursosListEntity2DTO(fotografoConcursoLogic.replaceConcursos(fotografosId, concursosListDTO2Entity(concursos)));
        LOGGER.log(Level.INFO, "FotografoConcursosResource replaceConcursos: output: {0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el libro y e autor recibidos en la URL.
     *
     * @param fotografosId El ID del autor al cual se le va a desasociar el libro
     * @param concursosId El ID del libro que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @DELETE
    @Path("{concursosId: \\d+}")
    public void removeConcurso(@PathParam("fotografosId") Long fotografosId, @PathParam("concursosId") Long concursosId) {
        LOGGER.log(Level.INFO, "FotografoConcursosResource deleteConcurso: input: fotografosId {0} , concursosId {1}", new Object[]{fotografosId, concursosId});
        if (concursoLogic.getConcurso(concursosId) == null) {
            throw new WebApplicationException("El recurso /concursos/" + concursosId + " no existe.", 404);
        }
        fotografoConcursoLogic.removeConcurso(fotografosId, concursosId);
        LOGGER.info("FotografoConcursosResource deleteConcurso: output: void");
    }

    /**
     * Convierte una lista de ConcursoEntity a una lista de ConcursoDetailDTO.
     *
     * @param entityList Lista de ConcursoEntity a convertir.
     * @return Lista de ConcursoDetailDTO convertida.
     */
    private List<ConcursoDetailDTO> concursosListEntity2DTO(List<ConcursoEntity> entityList) {
        List<ConcursoDetailDTO> list = new ArrayList<>();
        for (ConcursoEntity entity : entityList) {
            list.add(new ConcursoDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de ConcursoDetailDTO a una lista de ConcursoEntity.
     *
     * @param dtos Lista de ConcursoDetailDTO a convertir.
     * @return Lista de ConcursoEntity convertida.
     */
    private List<ConcursoEntity> concursosListDTO2Entity(List<ConcursoDetailDTO> dtos) {
        List<ConcursoEntity> list = new ArrayList<>();
        for (ConcursoDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
