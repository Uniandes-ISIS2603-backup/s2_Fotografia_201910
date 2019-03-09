/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.PhotoDTO;
import co.edu.uniandes.csw.fotografia.dtos.PhotoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.PhotoLogic;
import co.edu.uniandes.csw.fotografia.entities.PhotoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */

@Path("photos")
@Produces (MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PhotoResource {
    
    private static final Logger LOGGER = Logger.getLogger(PhotoResource.class.getName());
    
    @Inject
    private PhotoLogic photoLogic;
    
    @POST
    public PhotoDTO createPhoto(PhotoDTO photo) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "PhotoResource createPhoto: input: {0}", photo);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        PhotoEntity photoEntity = photo.toEntity();
        // Invoca la lógica para crear la editorial nueva
        PhotoEntity nuevoPhotoEntity = photoLogic.createPhoto(photoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        PhotoDTO nuevoPhotoDTO = new PhotoDTO(nuevoPhotoEntity);
        LOGGER.log(Level.INFO, "EditorialPhoto createPhoto: output: {0}", nuevoPhotoDTO);
        return nuevoPhotoDTO;
    }
    /**
     * Busca la foto con el id asociado recibido en la URL y la devuelve.
     *
     * @param id Identificador de la editorial que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link PhotoDetailDTO} - La editorial buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la foto.
     */
    @GET
    @Path("{id: \\d+}")
    public PhotoDetailDTO getPhoto(@PathParam("id") Long id) throws WebApplicationException {
        LOGGER.log(Level.INFO, "PhotoResource getFoto: input: {0}", id);
        PhotoEntity photoEntity = photoLogic.getFoto(id);
        if (photoEntity == null) {
            throw new WebApplicationException("El recurso /photos/" + id + " no existe.", 404);
        }
        PhotoDetailDTO detailDTO = new PhotoDetailDTO(photoEntity);
        LOGGER.log(Level.INFO, "PhotoResource getFoto: output: {0}", detailDTO);
        return detailDTO;
    }
    /**
     * Busca y devuelve todas las fotos que existen en la aplicacion.
     *
     * @return JSONArray {@link PhotoDetailDTO} - Las fotos
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<PhotoDetailDTO> getPhotos() {
        LOGGER.info("PhotoResource getFotos: input: void");
        List<PhotoDetailDTO> listaFotos = listEntity2DetailDTO(photoLogic.getFotos());
        LOGGER.log(Level.INFO, "EditorialResource getEditorials: output: {0}", listaFotos);
        return listaFotos;
    }
    @DELETE
    @Path("{id: \\d+}")
    public void deletePhoto(@PathParam("id") Long id){
        LOGGER.log(Level.INFO, "PhotoResource deletePhoto: input: {0}", id);
        if (photoLogic.getFoto(id) == null) {
            throw new WebApplicationException("El recurso /photos/" + id + " no existe.", 404);
        }
        photoLogic.deletePhoto(id);
        LOGGER.info("PhotoResource deletePhoto: output: void");
    }
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PhotoEntity a una lista de
     * objetos PhotoDetailDTO (json)
     *
     * @param photoList corresponde a la lista de fotos de tipo PhotoEntity
     * que vamos a convertir a DTO.
     * @return la lista de fotos en forma DTO (json)
     */
    private List<PhotoDetailDTO> listEntity2DetailDTO(List<PhotoEntity> photoList) {
        List<PhotoDetailDTO> list = new ArrayList<PhotoDetailDTO>();
        for (PhotoEntity entity : photoList) {
            list.add(new PhotoDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Actualiza la foto ingresado por parametro
     * @param fotosId del cliente a actualizar
     * @param foto el cliente por el cual se quiere actualizar
     * @return null
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    @PUT
    @Path("{fotosId: \\d+}")
    public PhotoDetailDTO updatePhoto(@PathParam("fotosId") Long fotosId, PhotoDetailDTO foto) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "FotoResource updatePhoto: input: fotosId: {0} , foto: {1}", new Object[]{fotosId, foto});
        foto.setId(fotosId);
        if (photoLogic.getFoto(fotosId) == null) {
            throw new WebApplicationException("El recurso /photos/" + fotosId + " no existe.", 404);
        }
        PhotoDetailDTO detailDTO = new PhotoDetailDTO(photoLogic.updateFoto(fotosId, foto.toEntity()));
        LOGGER.log(Level.INFO, "PhotoResource updateCliente: output: {0}", detailDTO);
        return detailDTO;
    }   
}
