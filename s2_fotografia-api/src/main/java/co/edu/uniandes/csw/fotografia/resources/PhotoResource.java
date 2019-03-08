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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */

@Path("photos")
@Produces("applications/json")
@Consumes("applications/json")
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
    
    @GET
        @Path("{id: \\d+}")
        public PhotoDetailDTO getPhoto(@PathParam("id") int id){
            return null;
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
}
