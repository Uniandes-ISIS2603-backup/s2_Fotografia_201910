/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;
import co.edu.uniandes.csw.fotografia.dtos.FotografoDTO;
import co.edu.uniandes.csw.fotografia.dtos.FotografoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.FotografoLogic;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
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
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "Fotografos".
 *
 * @Fotografo s.acostav
 */
@Path("/Fotografos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class FotografoResource {

    private static final Logger LOGGER = Logger.getLogger(FotografoResource.class.getName());

    @Inject
    private FotografoLogic FotografoLogic;

    /**
     * Crea un nuevo fotografo con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param Fotografo {@link FotografoDTO} - EL fotografo que se desea guardar.
     * @return JSON {@link FotografoDTO} - El fotografo guardado con el atributo id
     * autogenerado.
     */
    @POST
    public FotografoDTO createFotografo(FotografoDTO Fotografo) {
        LOGGER.log(Level.INFO, "FotografoResource createFotografo: input: {0}", Fotografo);
        FotografoDTO FotografoDTO = new FotografoDTO(FotografoLogic.createFotografo(Fotografo.toEntity()));
        LOGGER.log(Level.INFO, "FotografoResource createFotografo: output: {0}", FotografoDTO);
        return FotografoDTO;
    }

    /**
     * Busca y devuelve todos los fotografos que existen en la aplicacion.
     *
     * @return JSONArray {@link FotografoDetailDTO} - Los fotografos encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<FotografoDetailDTO> getFotografos() {
        LOGGER.info("FotografoResource getFotografos: input: void");
        List<FotografoDetailDTO> listaFotografos = listEntity2DTO(FotografoLogic.getFotografos());
        LOGGER.log(Level.INFO, "FotografoResource getFotografos: output: {0}", listaFotografos);
        return listaFotografos;
    }

    /**
     * Busca el fotografo con el id asociado recibido en la URL y lo devuelve.
     *
     * @param FotografosId Identificador del fotografo que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link FotografoDetailDTO} - El fotografo buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el fotografo.
     */
    @GET
    @Path("{FotografosId: \\d+}")
    public FotografoDetailDTO getFotografo(@PathParam("FotografosId") Long FotografosId) {
        LOGGER.log(Level.INFO, "FotografoResource getFotografo: input: {0}", FotografosId);
        FotografoEntity FotografoEntity = FotografoLogic.getFotografo(FotografosId);
        if (FotografoEntity == null) {
            throw new WebApplicationException("El recurso /Fotografos/" + FotografosId + " no existe.", 404);
        }
        FotografoDetailDTO detailDTO = new FotografoDetailDTO(FotografoEntity);
        LOGGER.log(Level.INFO, "FotografoResource getFotografo: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el fotografo con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param FotografosId Identificador del fotografo que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param Fotografo {@link FotografoDetailDTO} El fotografo que se desea guardar.
     * @return JSON {@link FotografoDetailDTO} - El fotografo guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el fotografo a
     * actualizar.
     */
    @PUT
    @Path("{FotografosId: \\d+}")
    public FotografoDetailDTO updateFotografo(@PathParam("FotografosId") Long FotografosId, FotografoDetailDTO Fotografo) {
        LOGGER.log(Level.INFO, "FotografoResource updateFotografo: input: FotografosId: {0} , Fotografo: {1}", new Object[]{FotografosId, Fotografo});
        Fotografo.setId(FotografosId);
        if (FotografoLogic.getFotografo(FotografosId) == null) {
            throw new WebApplicationException("El recurso /Fotografos/" + FotografosId + " no existe.", 404);
        }
        FotografoDetailDTO detailDTO = new FotografoDetailDTO(FotografoLogic.updateFotografo(FotografosId, Fotografo.toEntity()));
        LOGGER.log(Level.INFO, "FotografoResource updateFotografo: output: {0}", detailDTO);
        return detailDTO;
    }



    /**
     * Convierte una lista de FotografoEntity a una lista de FotografoDetailDTO.
     *
     * @param entityList Lista de FotografoEntity a convertir.
     * @return Lista de FotografoDetailDTO convertida.
     */
    private List<FotografoDetailDTO> listEntity2DTO(List<FotografoEntity> entityList) {
        List<FotografoDetailDTO> list = new ArrayList<>();
        for (FotografoEntity entity : entityList) {
            list.add(new FotografoDetailDTO(entity));
        }
        return list;
    }
}
