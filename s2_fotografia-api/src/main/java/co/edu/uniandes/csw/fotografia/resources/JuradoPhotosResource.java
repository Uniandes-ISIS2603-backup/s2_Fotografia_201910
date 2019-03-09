/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.PhotoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.JuradoPhotosLogic;
import co.edu.uniandes.csw.fotografia.ejb.PhotoLogic;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author a.trujilloa1
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JuradoPhotosResource {
     private static final Logger LOGGER = Logger.getLogger(JuradoPhotosResource.class.getName());

    @Inject
    private JuradoPhotosLogic juradoPhotosLogic;

    @Inject
    private PhotoLogic photoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Asocia un photo existente con un jurado existente
     *
     * @param juradoId El ID del jurado al cual se le va a asociar el photo
     * @param photosId El ID del photo que se asocia
     * @return JSON {@link PhotoDetailDTO} - El photo asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el photo.
     */
    @POST
    @Path("{photosId: \\d+}")
    public PhotoDetailDTO addPhoto(@PathParam("juradoId") Long juradoId, @PathParam("photosId") Long photosId) {
        LOGGER.log(Level.INFO, "JuradoPhotosResource addPhoto: input: juradoId {0} , photosId {1}", new Object[]{juradoId, photosId});
        if (photoLogic.getFoto(photosId) == null) {
            throw new WebApplicationException("El recurso /fotos/" + photosId + " no existe.", 404);
        }
        //PhotoDetailDTO detailDTO = new PhotoDetailDTO(juradoPhotosLogic.addPhoto(juradoId, photosId));
        PhotoDetailDTO detailDTO =new PhotoDetailDTO();
        LOGGER.log(Level.INFO, "JuradoPhotosResource addPhoto: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los photos que existen en un jurado.
     *
     * @param juradoId El ID del jurado del cual se buscan los photos
     * @return JSONArray {@link PhotoDetailDTO} - Los photos encontrados en el
     * jurado. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PhotoDetailDTO> getFotos(@PathParam("juradoId") Long juradoId) {
        LOGGER.log(Level.INFO, "JuradoPhotosResource getFotos: input: {0}", juradoId);
        List<PhotoDetailDTO> lista = fotosListEntity2DTO(juradoPhotosLogic.getPhotos(juradoId));
        LOGGER.log(Level.INFO, "JuradoPhotosResource getFotos: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el photo con el ID recibido en la URL, relativo a un
     * jurado.
     *
     * @param juradoId El ID del jurado del cual se busca el photo
     * @param photosId El ID del photo que se busca
     * @return {@link PhotoDetailDTO} - El photo encontrado en el jurado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el photo.
     */
    @GET
    @Path("{photosId: \\d+}")
    public PhotoDetailDTO getFoto(@PathParam("juradoId") Long juradoId, @PathParam("photosId") Long photosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "JuradoPhotosResource getFoto: input: juradoId {0} , photosId {1}", new Object[]{juradoId, photosId});
        if (photoLogic.getFoto(photosId) == null) {
            throw new WebApplicationException("El recurso /fotos/" + photosId + " no existe.", 404);
        }
        //PhotoDetailDTO detailDTO = new PhotoDetailDTO(juradoPhotosLogic.getPhoto(juradoId, photosId));
        PhotoDetailDTO detailDTO = new PhotoDetailDTO();
        LOGGER.log(Level.INFO, "JuradoPhotosResource getFoto: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de photos de un jurado con la lista que se recibe en el
     * cuerpo
     *
     * @param juradoId El ID del jurado al cual se le va a asociar el photo
     * @param fotos JSONArray {@link PhotoDetailDTO} - La lista de photos que se
     * desea guardar.
     * @return JSONArray {@link PhotoDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el photo.
     */
    @PUT
    public List<PhotoDetailDTO> replacePhotos(@PathParam("juradoId") Long juradoId, List<PhotoDetailDTO> fotos) {
        LOGGER.log(Level.INFO, "JuradoPhotosResource replacePhotos: input: juradoId {0} , fotos {1}", new Object[]{juradoId, fotos});
        for (PhotoDetailDTO foto : fotos) {
            //if (photoLogic.getFoto(foto.getId()) == null) {
              //  throw new WebApplicationException("El recurso /fotos/" + foto.getId() + " no existe.", 404);
            //}
        }
        List<PhotoDetailDTO> lista = fotosListEntity2DTO(juradoPhotosLogic.replacePhotos(juradoId, fotosListDTO2Entity(fotos)));
        LOGGER.log(Level.INFO, "JuradoPhotosResource replacePhotos: output: {0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el photo y e jurado recibidos en la URL.
     *
     * @param juradoId El ID del jurado al cual se le va a desasociar el photo
     * @param photosId El ID del photo que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el photo.
     */
    @DELETE
    @Path("{photosId: \\d+}")
    public void removePhoto(@PathParam("juradoId") Long juradoId, @PathParam("photosId") Long photosId) {
        LOGGER.log(Level.INFO, "JuradoPhotosResource deletePhoto: input: juradoId {0} , photosId {1}", new Object[]{juradoId, photosId});
        if (photoLogic.getFoto(photosId) == null) {
            throw new WebApplicationException("El recurso /fotos/" + photosId + " no existe.", 404);
        }
        juradoPhotosLogic.removePhoto(juradoId, photosId);
        LOGGER.info("JuradoPhotosResource deletePhoto: output: void");
    }

    /**
     * Convierte una lista de PhotoEntity a una lista de PhotoDetailDTO.
     *
     * @param entityList Lista de PhotoEntity a convertir.
     * @return Lista de PhotoDetailDTO convertida.
     */
    private List<PhotoDetailDTO> fotosListEntity2DTO(List<PhotoEntity> entityList) {
        List<PhotoDetailDTO> list = new ArrayList<>();
        for (PhotoEntity entity : entityList) {
            //list.add(new PhotoDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de PhotoDetailDTO a una lista de PhotoEntity.
     *
     * @param dtos Lista de PhotoDetailDTO a convertir.
     * @return Lista de PhotoEntity convertida.
     */
    private List<PhotoEntity> fotosListDTO2Entity(List<PhotoDetailDTO> dtos) {
        List<PhotoEntity> list = new ArrayList<>();
        for (PhotoDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
