package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.PhotoDTO;
import co.edu.uniandes.csw.fotografia.dtos.PhotoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.FacturaLogic;
import co.edu.uniandes.csw.fotografia.ejb.FacturaPhotoLogic;
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
 * Clase que implementa el recurso "factura/{id}/photo".
 *
 * @author dany y valentina
 */
@Path("facturas/{facturaId: \\d+}/photo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PhotoFacturaResource {

    
    private static final Logger LOGGER = Logger.getLogger(PhotoFacturaResource.class.getName());

    @Inject
    private FacturaLogic facturaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private PhotoLogic fotoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private FacturaPhotoLogic facturaPhotoLogic;
    /**
     * Guarda una foto dentro de una factura con la informacion que recibe el la
     * URL.
     *
     * @param facturaId Identificador de la factura que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param fotoId Identificador de la foto que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AuthorDTO} - El autor guardado en el premio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la factura.
     */
    @POST
    @Path("{photosId: \\d+}")
    public PhotoDTO addPhoto(@PathParam("facturaId") Long facturaId, @PathParam("photosId") Long photosId) {
        LOGGER.log(Level.INFO, "FotografoPhotosResource addPhoto: input: fotografosID: {0} , photosId: {1}", new Object[]{facturaId, photosId});
        if (fotoLogic.getFoto(photosId) == null) {
            throw new WebApplicationException("El recurso /photos/" + photosId + " no existe.", 404);
        }
        PhotoDTO photoDTO = new PhotoDTO(facturaPhotoLogic.addPhoto(photosId, facturaId));
        LOGGER.log(Level.INFO, "FotografoPhotosResource addPhoto: output: {0}", photoDTO);
        return photoDTO;
    }

    /**
     * Busca la foto dentro de la facrura con id asociado.
     *
     * @param facturaId Identificador de el factura que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} -foto buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @GET
    public List<PhotoDetailDTO> getPhotos(@PathParam("FacturaId") Long facturaId) {
        LOGGER.log(Level.INFO, "FotografoPhotosResource getPhotos: input: {0}", facturaId);
        List<PhotoDetailDTO> listaDetailDTOs = photosListEntity2DTO(facturaPhotoLogic.getPhotos(facturaId));
        LOGGER.log(Level.INFO, "FotografoPhotosResource getPhotos: output: {0}", listaDetailDTOs);
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
    
    @GET
    @Path("{photosId: \\d+}")
    public PhotoDetailDTO getPhoto(@PathParam("facturaId") Long facturaId, @PathParam("photosId") Long photosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FotografoPhotosResource getPhoto: input: fotografosID: {0} , photosId: {1}", new Object[]{facturaId, photosId});
        if (fotoLogic.getFoto(photosId) == null) {
            throw new WebApplicationException("El recurso /fotografos/" + facturaId + "/photos/" + photosId + " no existe.", 404);
        }
        PhotoDetailDTO photoDetailDTO = new PhotoDetailDTO(facturaPhotoLogic.getPhoto(facturaId, photosId));
        LOGGER.log(Level.INFO, "FotografoPhotosResource getPhoto: output: {0}", photoDetailDTO);
        return photoDetailDTO;
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
    /**
     * Remplaza la instancia de fotos asociada a una instancia de factura
     *
     * @param facturasId Identificador de factra que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param authorsId Identificador de foto que se esta remplazando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link AuthorDetailDTO} - El autor actualizado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @PUT
    @Path("{fotosId: \\d+}")
    public PhotoDetailDTO replacePhoto(@PathParam("facturasId") Long facturasId, @PathParam("fotosId") Long fotosId) {
        LOGGER.log(Level.INFO, "PhotoFacturaResource replaceAuthor: input: prizesId: {0} , authorsId: {1}", new Object[]{facturasId, fotosId});
        if (fotoLogic.getFoto(fotosId) == null) {
            throw new WebApplicationException("El recurso /facturas/" + facturasId + " no existe.", 404);
        }
        PhotoDetailDTO photoDetailDTO = new PhotoDetailDTO(fotoLogic.getFoto(fotosId));
        LOGGER.log(Level.INFO, "PrizeAuthorResource replaceAuthor: output: {0}", photoDetailDTO);
        return photoDetailDTO;
    }

    /**
     * Elimina la conexión entre la foto y la factura recibido en la URL.
     *
     * @param facturaId El ID del premio al cual se le va a desasociar el autor
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @DELETE
    public void removePhoto(@PathParam("facturaId") Long facturaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PhotoFacturaResource removeAuthor: input: {0}", facturaId);
        fotoLogic.deletePhoto(facturaId);
        LOGGER.info("PhotoFacturaResource removeAuthor: output: void");
    }
}