/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edu.uniandes.csw.fotografia.dtos.PhotoDTO;
import co.edu.uniandes.csw.fotografia.dtos.PhotoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.FotografoLogic;
import co.edu.uniandes.csw.fotografia.ejb.PhotoLogic;
import co.edu.uniandes.csw.fotografia.ejb.InteresFotograficoFotosLogic;
import co.edu.uniandes.csw.fotografia.ejb.InteresFotograficoLogic;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.fotografia.mappers.WebApplicationExceptionMapper;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
 * Clase que implementa el recurso "interesFotografico/{id}/photos".
 *
 * @author s.acostav
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InteresFotograficoFotosResource {
    private static final Logger LOGGER = Logger.getLogger(InteresFotograficoFotosResource.class.getName());

    @Inject
    private InteresFotograficoFotosLogic interesFotograficoPhotosLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private PhotoLogic photoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un libro dentro de una interesFotografico con la informacion que recibe el
     * la URL. Se devuelve el libro que se guarda en la interesFotografico.
     *
     * @param interesFotograficosId Identificador de la interesFotografico que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param photosId Identificador del libro que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link PhotoDTO} - El libro guardado en la interesFotografico.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{photosId: \\d+}")
    public PhotoDTO addPhoto(@PathParam("InteresFotograficosId") Long interesFotograficosId, @PathParam("photosId") Long photosId) {
        LOGGER.log(Level.INFO, "InteresFotograficoPhotosResource addPhoto: input: interesFotograficosID: {0} , photosId: {1}", new Object[]{interesFotograficosId, photosId});
        if (photoLogic.getFoto(photosId) == null) {
            throw new WebApplicationException("El recurso /photos/" + photosId + " no existe.", 404);
        }
       PhotoDTO photoDTO = new PhotoDTO(interesFotograficoPhotosLogic.addPhoto(photosId, interesFotograficosId));
        LOGGER.log(Level.INFO, "InteresFotograficoPhotosResource addPhoto: output: {0}", photoDTO);
        return photoDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en la interesFotografico.
     *
     * @param interesFotograficosId Identificador de la interesFotografico que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link PhotoDetailDTO} - Los libros encontrados en la
     * interesFotografico. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PhotoDetailDTO> getPhotos(@PathParam("InteresFotograficosId") Long interesFotograficosId) {
        LOGGER.log(Level.INFO, "InteresFotograficoPhotosResource getPhotos: input: {0}", interesFotograficosId);
        List<PhotoDetailDTO> listaDetailDTOs = photosListEntity2DTO(interesFotograficoPhotosLogic.getPhotos(interesFotograficosId));
        LOGGER.log(Level.INFO, "InteresFotograficoPhotosResource getPhotos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }

    /**
     * Busca el libro con el id asociado dentro de la interesFotografico con id asociado.
     *
     * @param interesFotograficosId Identificador de la interesFotografico que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param photosId Identificador del libro que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link PhotoDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro en la
     * interesFotografico.
     */
    @GET
    @Path("{photosId: \\d+}")
    public PhotoDetailDTO getPhoto(@PathParam("InteresFotograficosId") Long interesFotograficosId, @PathParam("photosId") Long photosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "InteresFotograficoPhotosResource getPhoto: input: interesFotograficosID: {0} , photosId: {1}", new Object[]{interesFotograficosId, photosId});
        if (photoLogic.getFoto(photosId) == null) {
            throw new WebApplicationException("El recurso /interesFotograficos/" + interesFotograficosId + "/photos/" + photosId + " no existe.", 404);
        }
        PhotoDetailDTO photoDetailDTO = new PhotoDetailDTO(interesFotograficoPhotosLogic.getPhoto(interesFotograficosId, photosId));
        LOGGER.log(Level.INFO, "InteresFotograficoPhotosResource getPhoto: output: {0}", photoDetailDTO);
        return photoDetailDTO;
    }

    /**
     * Remplaza las instancias de Photo asociadas a una instancia de InteresFotografico
     *
     * @param interesFotograficosId Identificador de la interesFotografico que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param photos JSONArray {@link PhotoDTO} El arreglo de libros nuevo para la
     * interesFotografico.
     * @return JSON {@link PhotoDTO} - El arreglo de libros guardado en la
     * interesFotografico.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<PhotoDetailDTO> replacePhotos(@PathParam("InteresFotograficosId") Long interesFotograficosId, List<PhotoDetailDTO> photos) {
        LOGGER.log(Level.INFO, "InteresFotograficoPhotosResource replacePhotos: input: interesFotograficosId: {0} , photos: {1}", new Object[]{interesFotograficosId, photos});
        for (PhotoDetailDTO photo : photos) {
            if (photoLogic.getFoto(photo.getId()) == null) {
                throw new WebApplicationException("El recurso /photos/" + photo.getId() + " no existe.", 404);
            }
        }
        List<PhotoDetailDTO> listaDetailDTOs = photosListEntity2DTO(interesFotograficoPhotosLogic.replacePhotos(interesFotograficosId, photosListDTO2Entity(photos)));
        LOGGER.log(Level.INFO, "InteresFotograficoPhotosResource replacePhotos: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }
    
    

    /**
     * Convierte una lista de PhotoEntity a una lista de PhotoDetailDTO.
     *
     * @param entityList Lista de PhotoEntity a convertir.
     * @return Lista de PhotoDTO convertida.
     */
    private List<PhotoDetailDTO> photosListEntity2DTO(List<PhotoEntity> entityList) {
        List<PhotoDetailDTO> list = new ArrayList();
        for (PhotoEntity entity : entityList) {
            list.add(new PhotoDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de PhotoDetailDTO a una lista de PhotoEntity.
     *
     * @param dtos Lista de PhotoDetailDTO a convertir.
     * @return Lista de PhotoEntity convertida.
     */
    private List<PhotoEntity> photosListDTO2Entity(List<PhotoDetailDTO> dtos) {
        List<PhotoEntity> list = new ArrayList<>();
        for (PhotoDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}

