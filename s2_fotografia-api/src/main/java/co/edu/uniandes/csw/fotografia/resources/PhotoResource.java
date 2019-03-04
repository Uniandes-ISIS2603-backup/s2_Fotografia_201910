/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.PhotoDTO;
import co.edu.uniandes.csw.fotografia.dtos.PhotoDetailDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
    
    @POST
    public PhotoDTO createPhoto(PhotoDTO photo){
        return photo;
    }
    
    @GET
        @Path("{id: \\d+}")
        public PhotoDetailDTO getPhoto(@PathParam("id") int id){
            return null;
        }
        
    @DELETE
        @Path("{id: \\d+}")
    public PhotoDetailDTO deletePhoto(@PathParam("id") int id){
        return null;
    }
}
