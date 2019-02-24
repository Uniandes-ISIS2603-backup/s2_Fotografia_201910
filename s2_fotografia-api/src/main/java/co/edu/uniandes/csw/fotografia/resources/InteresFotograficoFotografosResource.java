/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;
import co.edu.uniandes.csw.fotografia.dtos.FotografoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.FotografoLogic;
import co.edu.uniandes.csw.fotografia.ejb.InteresFotograficoFotografosLogic;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
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
 * Clase que implementa el recurso "interesesFotograficos/{id}/fotografos".
 *
 * @author s.acostav
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InteresFotograficoFotografosResource {

    private static final Logger LOGGER = Logger.getLogger(InteresFotograficoFotografosResource.class.getName());

    @Inject
    private InteresFotograficoFotografosLogic interesFotograficoFotografoLogic;

    @Inject
    private FotografoLogic fotografoLogic;

    /**
     * Asocia un autor existente con un libro existente
     *
     * @param fotografosId El ID del autor que se va a asociar
     * @param interesesFotograficosId El ID del libro al cual se le va a asociar el autor
     * @return JSON {@link FotografoDetailDTO} - El autor asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{fotografosId: \\d+}")
    public FotografoDetailDTO addFotografo(@PathParam("interesesFotograficosId") Long interesesFotograficosId, @PathParam("fotografosId") Long fotografosId) {
        LOGGER.log(Level.INFO, "InteresFotograficoFotografosResource addFotografo: input: interesesFotograficosId {0} , fotografosId {1}", new Object[]{interesesFotograficosId, fotografosId});
        if (fotografoLogic.getFotografo(fotografosId) == null) {
            throw new WebApplicationException("El recurso /fotografos/" + fotografosId + " no existe.", 404);
        }
        FotografoDetailDTO detailDTO = new FotografoDetailDTO(interesFotograficoFotografoLogic.addFotografo(interesesFotograficosId, fotografosId));
        LOGGER.log(Level.INFO, "InteresFotograficoFotografosResource addFotografo: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los autores que existen en un libro.
     *
     * @param interesesFotograficosId El ID del libro del cual se buscan los autores
     * @return JSONArray {@link FotografoDetailDTO} - Los autores encontrados en el
     * libro. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<FotografoDetailDTO> getFotografos(@PathParam("interesesFotograficosId") Long interesesFotograficosId) {
        LOGGER.log(Level.INFO, "InteresFotograficoFotografosResource getFotografos: input: {0}", interesesFotograficosId);
        List<FotografoDetailDTO> lista = fotografosListEntity2DTO(interesFotograficoFotografoLogic.getFotografos(interesesFotograficosId));
        LOGGER.log(Level.INFO, "InteresFotograficoFotografosResource getFotografos: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el autor con el ID recibido en la URL, relativo a un
     * libro.
     *
     * @param fotografosId El ID del autor que se busca
     * @param interesesFotograficosId El ID del libro del cual se busca el autor
     * @return {@link FotografoDetailDTO} - El autor encontrado en el libro.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{fotografosId: \\d+}")
    public FotografoDetailDTO getFotografo(@PathParam("interesesFotograficosId") Long interesesFotograficosId, @PathParam("fotografosId") Long fotografosId) {
        LOGGER.log(Level.INFO, "InteresFotograficoFotografosResource getFotografo: input: interesesFotograficosId {0} , fotografosId {1}", new Object[]{interesesFotograficosId, fotografosId});
        if (fotografoLogic.getFotografo(fotografosId) == null) {
            throw new WebApplicationException("El recurso /fotografos/" + fotografosId + " no existe.", 404);
        }
        FotografoDetailDTO detailDTO = new FotografoDetailDTO(interesFotograficoFotografoLogic.getFotografo(interesesFotograficosId, fotografosId));
        LOGGER.log(Level.INFO, "InteresFotograficoFotografosResource getFotografo: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de autores de un libro con la lista que se recibe en
     * el cuerpo.
     *
     * @param interesesFotograficosId El ID del libro al cual se le va a asociar la lista de
     * autores
     * @param fotografos JSONArray {@link FotografoDetailDTO} - La lista de autores
     * que se desea guardar.
     * @return JSONArray {@link FotografoDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @PUT
    public List<FotografoDetailDTO> replaceFotografos(@PathParam("interesesFotograficosId") Long interesesFotograficosId, List<FotografoDetailDTO> fotografos) {
        LOGGER.log(Level.INFO, "InteresFotograficoFotografosResource replaceFotografos: input: interesesFotograficosId {0} , fotografos {1}", new Object[]{interesesFotograficosId, fotografos});
        for (FotografoDetailDTO fotografo : fotografos) {
            if (fotografoLogic.getFotografo(fotografo.getId()) == null) {
                throw new WebApplicationException("El recurso /fotografos/" + fotografo.getId() + " no existe.", 404);
            }
        }
        List<FotografoDetailDTO> lista = fotografosListEntity2DTO(interesFotograficoFotografoLogic.replaceFotografos(interesesFotograficosId, fotografosListDTO2Entity(fotografos)));
        LOGGER.log(Level.INFO, "InteresFotograficoFotografosResource replaceFotografos: output:{0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el autor y el libro recibidos en la URL.
     *
     * @param interesesFotograficosId El ID del libro al cual se le va a desasociar el autor
     * @param fotografosId El ID del autor que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @DELETE
    @Path("{fotografosId: \\d+}")
    public void removeFotografo(@PathParam("interesesFotograficosId") Long interesesFotograficosId, @PathParam("fotografosId") Long fotografosId) {
        LOGGER.log(Level.INFO, "InteresFotograficoFotografosResource removeFotografo: input: interesesFotograficosId {0} , fotografosId {1}", new Object[]{interesesFotograficosId, fotografosId});
        if (fotografoLogic.getFotografo(fotografosId) == null) {
            throw new WebApplicationException("El recurso /fotografos/" + fotografosId + " no existe.", 404);
        }
        interesFotograficoFotografoLogic.removeFotografo(interesesFotograficosId, fotografosId);
        LOGGER.info("InteresFotograficoFotografosResource removeFotografo: output: void");
    }

    /**
     * Convierte una lista de FotografoEntity a una lista de FotografoDetailDTO.
     *
     * @param entityList Lista de FotografoEntity a convertir.
     * @return Lista de FotografoDetailDTO convertida.
     */
    private List<FotografoDetailDTO> fotografosListEntity2DTO(List<FotografoEntity> entityList) {
        List<FotografoDetailDTO> list = new ArrayList<>();
        for (FotografoEntity entity : entityList) {
            list.add(new FotografoDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de FotografoDetailDTO a una lista de FotografoEntity.
     *
     * @param dtos Lista de FotografoDetailDTO a convertir.
     * @return Lista de FotografoEntity convertida.
     */
    private List<FotografoEntity> fotografosListDTO2Entity(List<FotografoDetailDTO> dtos) {
        List<FotografoEntity> list = new ArrayList<>();
        for (FotografoDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}

