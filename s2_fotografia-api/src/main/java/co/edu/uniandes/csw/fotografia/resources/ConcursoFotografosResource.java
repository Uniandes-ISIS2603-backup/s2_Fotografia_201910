/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.FotografoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.ConcursoFotografoLogic;
import co.edu.uniandes.csw.fotografia.ejb.FotografoLogic;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
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
 *
 * @fotografo estudiante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConcursoFotografosResource {
     private static final Logger LOGGER = Logger.getLogger(ConcursoFotografosResource.class.getName());

    @Inject
    private ConcursoFotografoLogic concursoFotografoLogic;

    @Inject
    private FotografoLogic fotografoLogic;

    /**
     * Asocia un fotografo existente con un concurso existente
     *
     * @param fotografosId El ID del fotografo que se va a asociar
     * @param concursosId El ID del concurso al cual se le va a asociar el fotografo
     * @return JSON {@link FotografoDetailDTO} - El fotografo asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el fotografo.
     */
    @POST
    @Path("{fotografosId: \\d+}")
    public FotografoDetailDTO addFotografo(@PathParam("concursosId") Long concursosId, @PathParam("fotografosId") Long fotografosId) {
        LOGGER.log(Level.INFO, "ConcursoFotografosResource addFotografo: input: concursosId {0} , fotografosId {1}", new Object[]{concursosId, fotografosId});
        if (fotografoLogic.getFotografo(fotografosId) == null) {
            throw new WebApplicationException("El recurso /fotografos/" + fotografosId + " no existe.", 404);
        }
        FotografoDetailDTO detailDTO = new FotografoDetailDTO(concursoFotografoLogic.addFotografo(concursosId, fotografosId));
        LOGGER.log(Level.INFO, "ConcursoFotografosResource addFotografo: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los fotografos que existen en un concurso.
     *
     * @param concursosId El ID del concurso del cual se buscan los fotografos
     * @return JSONArray {@link FotografoDetailDTO} - Los fotografos encontrados en el
     * concurso. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<FotografoDetailDTO> getFotografos(@PathParam("concursosId") Long concursosId) {
        LOGGER.log(Level.INFO, "ConcursoFotografosResource getFotografos: input: {0}", concursosId);
        List<FotografoDetailDTO> lista = fotografosListEntity2DTO(concursoFotografoLogic.getFotografos(concursosId));
        LOGGER.log(Level.INFO, "ConcursoFotografosResource getFotografos: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el fotografo con el ID recibido en la URL, relativo a un
     * concurso.
     *
     * @param fotografosId El ID del fotografo que se busca
     * @param concursosId El ID del concurso del cual se busca el fotografo
     * @return {@link FotografoDetailDTO} - El fotografo encontrado en el concurso.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el fotografo.
     */
    @GET
    @Path("{fotografosId: \\d+}")
    public FotografoDetailDTO getFotografo(@PathParam("concursosId") Long concursosId, @PathParam("fotografosId") Long fotografosId) {
        LOGGER.log(Level.INFO, "ConcursoFotografosResource getFotografo: input: concursosId {0} , fotografosId {1}", new Object[]{concursosId, fotografosId});
        if (fotografoLogic.getFotografo(fotografosId) == null) {
            throw new WebApplicationException("El recurso /fotografos/" + fotografosId + " no existe.", 404);
        }
        FotografoDetailDTO detailDTO = new FotografoDetailDTO(concursoFotografoLogic.getFotografo(concursosId, fotografosId));
        LOGGER.log(Level.INFO, "ConcursoFotografosResource getFotografo: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de fotografos de un concurso con la lista que se recibe en
     * el cuerpo.
     *
     * @param concursosId El ID del concurso al cual se le va a asociar la lista de
     * fotografos
     * @param fotografos JSONArray {@link FotografoDetailDTO} - La lista de fotografos
     * que se desea guardar.
     * @return JSONArray {@link FotografoDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el fotografo.
     */
    @PUT
    public List<FotografoDetailDTO> replaceFotografos(@PathParam("concursosId") Long concursosId, List<FotografoDetailDTO> fotografos) {
        LOGGER.log(Level.INFO, "ConcursoFotografosResource replaceFotografos: input: concursosId {0} , fotografos {1}", new Object[]{concursosId, fotografos});
        for (FotografoDetailDTO fotografo : fotografos) {
            if (fotografoLogic.getFotografo(fotografo.getId()) == null) {
                throw new WebApplicationException("El recurso /fotografos/" + fotografo.getId() + " no existe.", 404);
            }
        }
        List<FotografoDetailDTO> lista = fotografosListEntity2DTO(concursoFotografoLogic.replaceFotografos(concursosId, fotografosListDTO2Entity(fotografos)));
        LOGGER.log(Level.INFO, "ConcursoFotografosResource replaceFotografos: output:{0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el fotografo y el concurso recibidos en la URL.
     *
     * @param concursosId El ID del concurso al cual se le va a desasociar el fotografo
     * @param fotografosId El ID del fotografo que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el fotografo.
     */
    @DELETE
    @Path("{fotografosId: \\d+}")
    public void removeFotografo(@PathParam("concursosId") Long concursosId, @PathParam("fotografosId") Long fotografosId) {
        LOGGER.log(Level.INFO, "ConcursoFotografosResource removeFotografo: input: concursosId {0} , fotografosId {1}", new Object[]{concursosId, fotografosId});
        if (fotografoLogic.getFotografo(fotografosId) == null) {
            throw new WebApplicationException("El recurso /fotografos/" + fotografosId + " no existe.", 404);
        }
        concursoFotografoLogic.removeFotografo(concursosId, fotografosId);
        LOGGER.info("ConcursoFotografosResource removeFotografo: output: void");
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
