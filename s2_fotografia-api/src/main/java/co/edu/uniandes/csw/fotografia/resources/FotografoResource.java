/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.FotografoDTO;
import co.edu.uniandes.csw.fotografia.dtos.FotografoDetailDTO;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.ejb.FotografoLogic;
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "Fotografos".
 *
 * @author s.acostav
 * @version 1.0
 */
@Path("Fotografos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FotografoResource {

    private static final Logger LOGGER = Logger.getLogger(FotografoResource.class.getName());

    @Inject
    private FotografoLogic FotografoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva Fotografo con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param Fotografo {@link FotografoDTO} - La Fotografo que se desea
     * guardar.
     * @return JSON {@link FotografoDTO} - La Fotografo guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la Fotografo.
     */
    @POST
    public FotografoDTO createFotografo(FotografoDTO Fotografo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FotografoResource createFotografo: input: {0}", Fotografo);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        FotografoEntity FotografoEntity = Fotografo.toEntity();
        // Invoca la lógica para crear la Fotografo nueva
        FotografoEntity nuevoFotografoEntity = FotografoLogic.createFotografo(FotografoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        FotografoDTO nuevoFotografoDTO = new FotografoDTO(nuevoFotografoEntity);
        LOGGER.log(Level.INFO, "FotografoResource createFotografo: output: {0}", nuevoFotografoDTO);
        return nuevoFotografoDTO;
    }

    /**
     * Busca y devuelve todas las Fotografoes que existen en la aplicacion.
     *
     * @return JSONArray {@link FotografoDetailDTO} - Las Fotografoes
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<FotografoDetailDTO> getFotografos() {
        LOGGER.info("FotografoResource getFotografos: input: void");
        List<FotografoDetailDTO> listaFotografoes = listEntity2DetailDTO(FotografoLogic.getFotografos());
        LOGGER.log(Level.INFO, "FotografoResource getFotografos: output: {0}", listaFotografoes);
        return listaFotografoes;
    }

    /**
     * Busca la Fotografo con el id asociado recibido en la URL y la devuelve.
     *
     * @param FotografosId Identificador de la Fotografo que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link FotografoDetailDTO} - La Fotografo buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Fotografo.
     */
    @GET
    @Path("{FotografosId: \\d+}")
    public FotografoDetailDTO getFotografo(@PathParam("FotografosId") Long FotografosId) throws WebApplicationException {
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
     * Actualiza la Fotografo con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param FotografosId Identificador de la Fotografo que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param Fotografo {@link FotografoDetailDTO} La Fotografo que se desea
     * guardar.
     * @return JSON {@link FotografoDetailDTO} - La Fotografo guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Fotografo a
     * actualizar.
     */
    @PUT
    @Path("{FotografosId: \\d+}")
    public FotografoDetailDTO updateFotografo(@PathParam("FotografosId") Long FotografosId, FotografoDetailDTO Fotografo) throws WebApplicationException {
        LOGGER.log(Level.INFO, "FotografoResource updateFotografo: input: id:{0} , Fotografo: {1}", new Object[]{FotografosId, Fotografo});
        Fotografo.setId(FotografosId);
        if (FotografoLogic.getFotografo(FotografosId) == null) {
            throw new WebApplicationException("El recurso /Fotografos/" + FotografosId + " no existe.", 404);
        }
        FotografoDetailDTO detailDTO = new FotografoDetailDTO(FotografoLogic.updateFotografo(FotografosId, Fotografo.toEntity()));
        LOGGER.log(Level.INFO, "FotografoResource updateFotografo: output: {0}", detailDTO);
        return detailDTO;

    }

    /**
     * Borra la Fotografo con el id asociado recibido en la URL.
     *
     * @param FotografosId Identificador de la Fotografo que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la Fotografo.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Fotografo.
     */
    @DELETE
    @Path("{FotografosId: \\d+}")
    public void deleteFotografo(@PathParam("FotografosId") Long FotografosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FotografoResource deleteFotografo: input: {0}", FotografosId);
        if (FotografoLogic.getFotografo(FotografosId) == null) {
            throw new WebApplicationException("El recurso /Fotografos/" + FotografosId + " no existe.", 404);
        }
        FotografoLogic.deleteFotografo(FotografosId);
        LOGGER.info("FotografoResource deleteFotografo: output: void");
    }

  

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos FotografoEntity a una lista de
     * objetos FotografoDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Fotografoes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de Fotografoes en forma DTO (json)
     */
    private List<FotografoDetailDTO> listEntity2DetailDTO(List<FotografoEntity> entityList) {
        List<FotografoDetailDTO> list = new ArrayList<>();
        for (FotografoEntity entity : entityList) {
            list.add(new FotografoDetailDTO(entity));
        }
        return list;
    }
}
