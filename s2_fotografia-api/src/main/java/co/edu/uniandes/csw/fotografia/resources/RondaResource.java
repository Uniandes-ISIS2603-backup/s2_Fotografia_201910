/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.RondaDTO;
import co.edu.uniandes.csw.fotografia.dtos.RondaDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.RondaLogic;
import co.edu.uniandes.csw.fotografia.entities.RondaEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.fotografia.mappers.WebApplicationExceptionMapper;
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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "rondas".
 *
 * @author Nicolas Melendez
 * @version 1.0
 */
@Path("rondas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class RondaResource {

    private static final Logger LOGGER = Logger.getLogger(RondaResource.class.getName());

    @Inject
    private RondaLogic rondaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea un nuevo ronda con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param ronda {@link RondaDTO} - EL Ronda que se desea guardar.
     * @return JSON {@link RondaDTO} - El Ronda guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el Ronda
     */
    @POST
    public RondaDTO createRonda(RondaDTO ronda) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RondaResource createRonda: input: {0}", ronda);
        RondaDTO nuevoRondaDTO = new RondaDTO(rondaLogic.createRonda(ronda.toEntity()));
        LOGGER.log(Level.INFO, "PrizeResource createPrize: output: {0}", nuevoRondaDTO);
        return nuevoRondaDTO;
    }

    /**
     * Busca y devuelve todos los rondas que existen en la aplicacion.
     *
     * @return JSONArray {@link RondaDetailDTO} - Los rondas encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<RondaDetailDTO> getRondas() {
        LOGGER.info("RondaResource getRondas: input: void");
        List<RondaDetailDTO> listaRondas = listEntity2DetailDTO(rondaLogic.getRondas());
        LOGGER.log(Level.INFO, "RondaResource getRondas: output: {0}", listaRondas);
        return listaRondas;
    }

    /**
     * Busca el ronda con el id asociado recibido en la URL y lo devuelve.
     *
     * @param rondasId Identificador del ronda que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link RondaDetailDTO} - El ronda buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el ronda.
     */
    @GET
    @Path("{rondasId: \\d+}")
    public RondaDetailDTO getRonda(@PathParam("rondasId") Long rondasId) {
        LOGGER.log(Level.INFO, "RondaResource getRonda: input: {0}", rondasId);
        RondaEntity rondaEntity = rondaLogic.getRonda(rondasId);
        if (rondaEntity == null) {
            throw new WebApplicationException("El recurso /rondas/" + rondasId + " no existe.", 404);
        }
        RondaDetailDTO rondaDetailDTO = new RondaDetailDTO(rondaEntity);
        LOGGER.log(Level.INFO, "RondaResource getRonda: output: {0}", rondaDetailDTO);
        return rondaDetailDTO;
    }

    /**
     * Actualiza el ronda con el id recibido en la URL con la información que
     * se recibe en el cuerpo de la petición.
     *
     * @param rondasId Identificador del ronda que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param ronda {@link RondaDTO} El ronda que se desea guardar.
     * @return JSON {@link RondaDetailDTO} - El ronda guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el ronda a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el ronda.
     */
    @PUT
    @Path("{rondasId: \\d+}")
    public RondaDetailDTO updateRonda(@PathParam("rondasId") Long rondasId, RondaDTO ronda) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RondaResource updateRonda: input: rondasId: {0} , ronda: {1}", new Object[]{rondasId, ronda});
        ronda.setId(rondasId);
        if (rondaLogic.getRonda(rondasId) == null) {
            throw new WebApplicationException("El recurso /prizes/" + rondasId + " no existe.", 404);
        }
        RondaDetailDTO detailDTO = new RondaDetailDTO(rondaLogic.updateRonda(rondasId, ronda.toEntity()));
        LOGGER.log(Level.INFO, "RondaResource updateRonda: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el ronda con el id asociado recibido en la URL.
     *
     * @param rondasId Identificador del ronda que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando el ronda tiene un concurso.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el premio.
     */
    @DELETE
    @Path("{rondasId: \\d+}")
    public void deleteRonda(@PathParam("rondasId") Long rondasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RondaResource deleteRonda: input: {0}", rondasId);
        if (rondaLogic.getRonda(rondasId) == null) {
            throw new WebApplicationException("El recurso /rondass/" + rondasId + " no existe.", 404);
        }
        rondaLogic.deleteRonda(rondasId);
        LOGGER.info("RondaResource deleteRonda: output: void");
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PrizeEntity a una lista de
     * objetos PrizeDetailDTO (json)
     *
     * @param entityList corresponde a la lista de premios de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de premios en forma DTO (json)
     */
    private List<RondaDetailDTO> listEntity2DetailDTO(List<RondaEntity> entityList) {
        List<RondaDetailDTO> list = new ArrayList<>();
        for (RondaEntity entity : entityList) {
            list.add(new RondaDetailDTO(entity));
        }
        return list;
    }
}