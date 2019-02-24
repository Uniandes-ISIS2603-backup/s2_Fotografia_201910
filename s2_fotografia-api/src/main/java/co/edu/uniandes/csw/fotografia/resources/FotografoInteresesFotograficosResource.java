/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;
import co.edu.uniandes.csw.fotografia.dtos.InteresFotograficoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.FotografoInteresesFotograficosLogic;
import co.edu.uniandes.csw.fotografia.ejb.InteresFotograficoLogic;
import co.edu.uniandes.csw.fotografia.entities.InteresFotograficoEntity;
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
 * Clase que implementa el recurso "fotografos/{id}/interesesFotograficos".
 *
 * @author s.acostav
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FotografoInteresesFotograficosResource {

    private static final Logger LOGGER = Logger.getLogger(FotografoInteresesFotograficosResource.class.getName());

    @Inject
    private FotografoInteresesFotograficosLogic fotografoInteresFotograficoLogic;

    @Inject
    private InteresFotograficoLogic interesFotograficoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Asocia un libro existente con un autor existente
     *
     * @param fotografosId El ID del autor al cual se le va a asociar el libro
     * @param interesesFotograficosId El ID del libro que se asocia
     * @return JSON {@link InteresFotograficoDetailDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{interesesFotograficosId: \\d+}")
    public InteresFotograficoDetailDTO addInteresFotografico(@PathParam("fotografosId") Long fotografosId, @PathParam("interesesFotograficosId") Long interesesFotograficosId) {
        LOGGER.log(Level.INFO, "FotografoInteresesFotograficosResource addInteresFotografico: input: fotografosId {0} , interesesFotograficosId {1}", new Object[]{fotografosId, interesesFotograficosId});
        if (interesFotograficoLogic.getInteresFotografico(interesesFotograficosId) == null) {
            throw new WebApplicationException("El recurso /interesesFotograficos/" + interesesFotograficosId + " no existe.", 404);
        }
        InteresFotograficoDetailDTO detailDTO = new InteresFotograficoDetailDTO(fotografoInteresFotograficoLogic.addInteresFotografico(fotografosId, interesesFotograficosId));
        LOGGER.log(Level.INFO, "FotografoInteresesFotograficosResource addInteresFotografico: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en un autor.
     *
     * @param fotografosId El ID del autor del cual se buscan los libros
     * @return JSONArray {@link InteresFotograficoDetailDTO} - Los libros encontrados en el
     * autor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<InteresFotograficoDetailDTO> getInteresesFotograficos(@PathParam("fotografosId") Long fotografosId) {
        LOGGER.log(Level.INFO, "FotografoInteresesFotograficosResource getInteresesFotograficos: input: {0}", fotografosId);
        List<InteresFotograficoDetailDTO> lista = interesesFotograficosListEntity2DTO(fotografoInteresFotograficoLogic.getInteresesFotograficos(fotografosId));
        LOGGER.log(Level.INFO, "FotografoInteresesFotograficosResource getInteresesFotograficos: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el libro con el ID recibido en la URL, relativo a un
     * autor.
     *
     * @param fotografosId El ID del autor del cual se busca el libro
     * @param interesesFotograficosId El ID del libro que se busca
     * @return {@link InteresFotograficoDetailDTO} - El libro encontrado en el autor.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     * si el libro no está asociado al autor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @GET
    @Path("{interesesFotograficosId: \\d+}")
    public InteresFotograficoDetailDTO getInteresFotografico(@PathParam("fotografosId") Long fotografosId, @PathParam("interesesFotograficosId") Long interesesFotograficosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FotografoInteresesFotograficosResource getInteresFotografico: input: fotografosId {0} , interesesFotograficosId {1}", new Object[]{fotografosId, interesesFotograficosId});
        if (interesFotograficoLogic.getInteresFotografico(interesesFotograficosId) == null) {
            throw new WebApplicationException("El recurso /interesesFotograficos/" + interesesFotograficosId + " no existe.", 404);
        }
        InteresFotograficoDetailDTO detailDTO = new InteresFotograficoDetailDTO(fotografoInteresFotograficoLogic.getInteresFotografico(fotografosId, interesesFotograficosId));
        LOGGER.log(Level.INFO, "FotografoInteresesFotograficosResource getInteresFotografico: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de libros de un autor con la lista que se recibe en el
     * cuerpo
     *
     * @param fotografosId El ID del autor al cual se le va a asociar el libro
     * @param interesesFotograficos JSONArray {@link InteresFotograficoDetailDTO} - La lista de libros que se
     * desea guardar.
     * @return JSONArray {@link InteresFotograficoDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<InteresFotograficoDetailDTO> replaceInteresesFotograficos(@PathParam("fotografosId") Long fotografosId, List<InteresFotograficoDetailDTO> interesesFotograficos) {
        LOGGER.log(Level.INFO, "FotografoInteresesFotograficosResource replaceInteresesFotograficos: input: fotografosId {0} , interesesFotograficos {1}", new Object[]{fotografosId, interesesFotograficos});
        for (InteresFotograficoDetailDTO interesFotografico : interesesFotograficos) {
            if (interesFotograficoLogic.getInteresFotografico(interesFotografico.getId()) == null) {
                throw new WebApplicationException("El recurso /interesesFotograficos/" + interesFotografico.getId() + " no existe.", 404);
            }
        }
        List<InteresFotograficoDetailDTO> lista = interesesFotograficosListEntity2DTO(fotografoInteresFotograficoLogic.replaceInteresesFotograficos(fotografosId, interesesFotograficosListDTO2Entity(interesesFotograficos)));
        LOGGER.log(Level.INFO, "FotografoInteresesFotograficosResource replaceInteresesFotograficos: output: {0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el libro y e autor recibidos en la URL.
     *
     * @param fotografosId El ID del autor al cual se le va a desasociar el libro
     * @param interesesFotograficosId El ID del libro que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @DELETE
    @Path("{interesesFotograficosId: \\d+}")
    public void removeInteresFotografico(@PathParam("fotografosId") Long fotografosId, @PathParam("interesesFotograficosId") Long interesesFotograficosId) {
        LOGGER.log(Level.INFO, "FotografoInteresesFotograficosResource deleteInteresFotografico: input: fotografosId {0} , interesesFotograficosId {1}", new Object[]{fotografosId, interesesFotograficosId});
        if (interesFotograficoLogic.getInteresFotografico(interesesFotograficosId) == null) {
            throw new WebApplicationException("El recurso /interesesFotograficos/" + interesesFotograficosId + " no existe.", 404);
        }
        fotografoInteresFotograficoLogic.removeInteresFotografico(fotografosId, interesesFotograficosId);
        LOGGER.info("FotografoInteresesFotograficosResource deleteInteresFotografico: output: void");
    }

    /**
     * Convierte una lista de InteresFotograficoEntity a una lista de InteresFotograficoDetailDTO.
     *
     * @param entityList Lista de InteresFotograficoEntity a convertir.
     * @return Lista de InteresFotograficoDetailDTO convertida.
     */
    private List<InteresFotograficoDetailDTO> interesesFotograficosListEntity2DTO(List<InteresFotograficoEntity> entityList) {
        List<InteresFotograficoDetailDTO> list = new ArrayList<>();
        for (InteresFotograficoEntity entity : entityList) {
            list.add(new InteresFotograficoDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de InteresFotograficoDetailDTO a una lista de InteresFotograficoEntity.
     *
     * @param dtos Lista de InteresFotograficoDetailDTO a convertir.
     * @return Lista de InteresFotograficoEntity convertida.
     */
    private List<InteresFotograficoEntity> interesesFotograficosListDTO2Entity(List<InteresFotograficoDetailDTO> dtos) {
        List<InteresFotograficoEntity> list = new ArrayList<>();
        for (InteresFotograficoDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
