/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.PhotoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.ConcursoPhotoLogic;
import co.edu.uniandes.csw.fotografia.ejb.PhotoLogic;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
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
 * @photo estudiante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConcursoPhotosResource {
     private static final Logger LOGGER = Logger.getLogger(ConcursoPhotosResource.class.getName());

    @Inject
    private ConcursoPhotoLogic concursoPhotoLogic;

    @Inject
    private PhotoLogic photoLogic;

    /**
     * Asocia un autor existente con un libro existente
     *
     * @param photosId El ID del autor que se va a asociar
     * @param concursosId El ID del libro al cual se le va a asociar el autor
     * @return JSON {@link PhotoDetailDTO} - El autor asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{photosId: \\d+}")
    public PhotoDetailDTO addPhoto(@PathParam("concursosId") Long concursosId, @PathParam("photosId") Long photosId) {
        LOGGER.log(Level.INFO, "ConcursoPhotosResource addPhoto: input: concursosId {0} , photosId {1}", new Object[]{concursosId, photosId});
        if (photoLogic.getFoto(photosId) == null) {
            throw new WebApplicationException("El recurso /photos/" + photosId + " no existe.", 404);
        }
        PhotoDetailDTO detailDTO = new PhotoDetailDTO(concursoPhotoLogic.addPhoto(concursosId, photosId));
        LOGGER.log(Level.INFO, "ConcursoPhotosResource addPhoto: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los autores que existen en un libro.
     *
     * @param concursosId El ID del libro del cual se buscan los autores
     * @return JSONArray {@link PhotoDetailDTO} - Los autores encontrados en el
     * libro. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PhotoDetailDTO> getFotos(@PathParam("concursosId") Long concursosId) {
        LOGGER.log(Level.INFO, "ConcursoPhotosResource getFotos: input: {0}", concursosId);
        List<PhotoDetailDTO> lista = photosListEntity2DTO(concursoPhotoLogic.getPhotos(concursosId));
        LOGGER.log(Level.INFO, "ConcursoPhotosResource getFotos: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el autor con el ID recibido en la URL, relativo a un
     * libro.
     *
     * @param photosId El ID del autor que se busca
     * @param concursosId El ID del libro del cual se busca el autor
     * @return {@link PhotoDetailDTO} - El autor encontrado en el libro.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{photosId: \\d+}")
   
    public PhotoDetailDTO getFoto(@PathParam("concursosId") Long concursosId, @PathParam("photosId") Long photosId) {
        LOGGER.log(Level.INFO, "ConcursoPhotosResource getFoto: input: concursosId {0} , photosId {1}", new Object[]{concursosId, photosId});
        if (photoLogic.getFoto(photosId) == null) {
            throw new WebApplicationException("El recurso /photos/" + photosId + " no existe.", 404);
        }
        PhotoDetailDTO detailDTO = new PhotoDetailDTO(concursoPhotoLogic.getPhoto(concursosId, photosId));
        LOGGER.log(Level.INFO, "ConcursoPhotosResource getFoto: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de autores de un libro con la lista que se recibe en
     * el cuerpo.
     *
     * @param concursosId El ID del libro al cual se le va a asociar la lista de
     * autores
     * @param photos JSONArray {@link PhotoDetailDTO} - La lista de autores
     * que se desea guardar.
     * @return JSONArray {@link PhotoDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @PUT
    public List<PhotoDetailDTO> replacePhotos(@PathParam("concursosId") Long concursosId, List<PhotoDetailDTO> photos) {
        LOGGER.log(Level.INFO, "ConcursoPhotosResource replacePhotos: input: concursosId {0} , photos {1}", new Object[]{concursosId, photos});
        for (PhotoDetailDTO photo : photos) {
            if (photoLogic.getFoto(photo.getId()) == null) {
                throw new WebApplicationException("El recurso /photos/" + photo.getId() + " no existe.", 404);
            }
        }
        List<PhotoDetailDTO> lista = photosListEntity2DTO(concursoPhotoLogic.replacePhotos(concursosId, photosListDTO2Entity(photos)));
        LOGGER.log(Level.INFO, "ConcursoPhotosResource replacePhotos: output:{0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el autor y el libro recibidos en la URL.
     *
     * @param concursosId El ID del libro al cual se le va a desasociar el autor
     * @param photosId El ID del autor que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @DELETE
    @Path("{photosId: \\d+}")
    public void removePhoto(@PathParam("concursosId") Long concursosId, @PathParam("photosId") Long photosId) {
        LOGGER.log(Level.INFO, "ConcursoPhotosResource removePhoto: input: concursosId {0} , photosId {1}", new Object[]{concursosId, photosId});
        if (photoLogic.getFoto(photosId) == null) {
            throw new WebApplicationException("El recurso /photos/" + photosId + " no existe.", 404);
        }
        concursoPhotoLogic.removePhoto(concursosId, photosId);
        LOGGER.info("ConcursoPhotosResource removePhoto: output: void");
    }

    /**
     * Convierte una lista de PhotoEntity a una lista de PhotoDetailDTO.
     *
     * @param entityList Lista de PhotoEntity a convertir.
     * @return Lista de PhotoDetailDTO convertida.
     */
    private List<PhotoDetailDTO> photosListEntity2DTO(List<PhotoEntity> entityList) {
        List<PhotoDetailDTO> list = new ArrayList<>();
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

