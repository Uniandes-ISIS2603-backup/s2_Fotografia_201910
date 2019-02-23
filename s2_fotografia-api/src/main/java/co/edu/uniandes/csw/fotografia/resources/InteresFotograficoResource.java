/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;
import co.edu.uniandes.csw.fotografia.dtos.InteresFotograficoDTO;
import co.edu.uniandes.csw.fotografia.dtos.InteresFotograficoDetailDTO;
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
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "InteresFotograficos".
 *
 * @InteresFotografico s.acostav
 * @version 1.0
 */
@Path("/InteresFotograficos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class InteresFotograficoResource {

    private static final Logger LOGGER = Logger.getLogger(InteresFotograficoResource.class.getName());

    @Inject
    private InteresFotograficoLogic InteresFotograficoLogic;

    /**
     * Crea un nuevo interesFotografico con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param InteresFotografico {@link InteresFotograficoDTO} - EL interesFotografico que se desea guardar.
     * @return JSON {@link InteresFotograficoDTO} - El interesFotografico guardado con el atributo id
     * autogenerado.
     */
    @POST
    public InteresFotograficoDTO createInteresFotografico(InteresFotograficoDTO InteresFotografico) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "InteresFotograficoResource createInteresFotografico: input: {0}", InteresFotografico);
        InteresFotograficoDTO InteresFotograficoDTO = new InteresFotograficoDTO(InteresFotograficoLogic.createInteresFotografico(InteresFotografico.toEntity()));
        LOGGER.log(Level.INFO, "InteresFotograficoResource createInteresFotografico: output: {0}", InteresFotograficoDTO);
        return InteresFotograficoDTO;
    }

    /**
     * Busca y devuelve todos los interesFotograficos que existen en la aplicacion.
     *
     * @return JSONArray {@link InteresFotograficoDetailDTO} - Los interesFotograficos encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<InteresFotograficoDetailDTO> getInteresesFotograficos() {
        LOGGER.info("InteresFotograficoResource getInteresFotograficos: input: void");
        List<InteresFotograficoDetailDTO> listaInteresFotograficos = listEntity2DTO(InteresFotograficoLogic.getInteresesFotograficos());
        LOGGER.log(Level.INFO, "InteresFotograficoResource getInteresFotograficos: output: {0}", listaInteresFotograficos);
        return listaInteresFotograficos;
    }

    /**
     * Busca el interesFotografico con el id asociado recibido en la URL y lo devuelve.
     *
     * @param InteresFotograficosId Identificador del interesFotografico que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link InteresFotograficoDetailDTO} - El interesFotografico buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el interesFotografico.
     */
    @GET
    @Path("{InteresFotograficosId: \\d+}")
    public InteresFotograficoDetailDTO getInteresFotografico(@PathParam("InteresFotograficosId") Long InteresFotograficosId) {
        LOGGER.log(Level.INFO, "InteresFotograficoResource getInteresFotografico: input: {0}", InteresFotograficosId);
        InteresFotograficoEntity InteresFotograficoEntity = InteresFotograficoLogic.getInteresFotografico(InteresFotograficosId);
        if (InteresFotograficoEntity == null) {
            throw new WebApplicationException("El recurso /InteresFotograficos/" + InteresFotograficosId + " no existe.", 404);
        }
        InteresFotograficoDetailDTO detailDTO = new InteresFotograficoDetailDTO(InteresFotograficoEntity);
        LOGGER.log(Level.INFO, "InteresFotograficoResource getInteresFotografico: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el interesFotografico con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param InteresFotograficosId Identificador del interesFotografico que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param InteresFotografico {@link InteresFotograficoDetailDTO} El interesFotografico que se desea guardar.
     * @return JSON {@link InteresFotograficoDetailDTO} - El interesFotografico guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el interesFotografico a
     * actualizar.
     */
    @PUT
    @Path("{InteresFotograficosId: \\d+}")
    public InteresFotograficoDetailDTO updateInteresFotografico(@PathParam("InteresFotograficosId") Long InteresFotograficosId, InteresFotograficoDetailDTO InteresFotografico) {
        LOGGER.log(Level.INFO, "InteresFotograficoResource updateInteresFotografico: input: InteresFotograficosId: {0} , InteresFotografico: {1}", new Object[]{InteresFotograficosId, InteresFotografico});
        InteresFotografico.setId(InteresFotograficosId);
        if (InteresFotograficoLogic.getInteresFotografico(InteresFotograficosId) == null) {
            throw new WebApplicationException("El recurso /InteresFotograficos/" + InteresFotograficosId + " no existe.", 404);
        }
        InteresFotograficoDetailDTO detailDTO = new InteresFotograficoDetailDTO(InteresFotograficoLogic.updateInteresFotografico(InteresFotograficosId, InteresFotografico.toEntity()));
        LOGGER.log(Level.INFO, "InteresFotograficoResource updateInteresFotografico: output: {0}", detailDTO);
        return detailDTO;
    }



    /**
     * Convierte una lista de InteresFotograficoEntity a una lista de InteresFotograficoDetailDTO.
     *
     * @param entityList Lista de InteresFotograficoEntity a convertir.
     * @return Lista de InteresFotograficoDetailDTO convertida.
     */
    private List<InteresFotograficoDetailDTO> listEntity2DTO(List<InteresFotograficoEntity> entityList) {
        List<InteresFotograficoDetailDTO> list = new ArrayList<>();
        for (InteresFotograficoEntity entity : entityList) {
            list.add(new InteresFotograficoDetailDTO(entity));
        }
        return list;
    }
}
