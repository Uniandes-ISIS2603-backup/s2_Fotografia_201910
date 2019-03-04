/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.dtos.UsuarioDTO;
import co.edu.uniandes.csw.fotografia.dtos.UsuarioDTO;
import co.edu.uniandes.csw.fotografia.ejb.UsuarioLogic;
import co.edu.uniandes.csw.fotografia.entities.UsuarioEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import com.sun.istack.internal.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
 * @author Nicolas Rincon
 */
@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class UsuarioResource {
     private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(UsuarioResource.class.getName());

    @Inject
    private UsuarioLogic UsuarioLogic;

    /**
     * Crea un nuevo usuario con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param usuario
     * @param Usuario {@link UsuarioDTO} - EL fotografo que se desea guardar.
     * @return JSON {@link UsuarioDTO} - El fotografo guardado con el atributo id
     * autogenerado.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException si 
     * no concuerda con las reglas del negocio del usuario
     */
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource createUsuario: input: {0}", usuario);
        UsuarioEntity usuarioEntity = usuario.toEntity();
        UsuarioEntity nuevoUsuarioEntity = UsuarioLogic.createUsuario(usuarioEntity);
        UsuarioDTO nuevoUsuarioDTO = new UsuarioDTO(nuevoUsuarioEntity);
        LOGGER.log(Level.INFO, "UsuarioResource createUsuario: output: {0}", nuevoUsuarioDTO);
        return nuevoUsuarioDTO;
    }

    /**
     * Busca y devuelve todos los fotografos que existen en la aplicacion.
     *
     * @return JSONArray {@link UsuarioDTO} - Los fotografos encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<UsuarioDTO> getUsuarios() {
        LOGGER.info("UsuarioResource getUsuarios: input: void");
        List<UsuarioDTO> listaUsuarios = listEntity2DTO(UsuarioLogic.getUsuarios());
        LOGGER.log(Level.INFO, "UsuarioResource getUsuarios: output: {0}", listaUsuarios);
        return listaUsuarios;
    }

    /**
     * Busca el fotografo con el id asociado recibido en la URL y lo devuelve.
     *
     * @param UsuariosId Identificador del fotografo que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link UsuarioDTO} - El fotografo buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el fotografo.
     */
    @GET
    @Path("{usuariosId: \\d+}")
    public UsuarioDTO getUsuario(@PathParam("usuariosId") Long UsuariosId) {
        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: input: {0}", UsuariosId);
        UsuarioEntity UsuarioEntity = UsuarioLogic.getUsuario(UsuariosId);
        if (UsuarioEntity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + UsuariosId + " no existe.", 404);
        }
        UsuarioDTO detailDTO = new UsuarioDTO(UsuarioEntity);
        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el fotografo con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param UsuariosId Identificador del fotografo que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param Usuario {@link UsuarioDTO} El fotografo que se desea guardar.
     * @return JSON {@link UsuarioDTO} - El fotografo guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el fotografo a
     * actualizar.
     */
    @PUT
    @Path("{usuariosId: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("usuariosId") Long UsuariosId, UsuarioDTO Usuario) {
        LOGGER.log(Level.INFO, "UsuarioResource updateUsuario: input: id: {0} , usuario: {1}", new Object[]{UsuariosId, Usuario});
        Usuario.setId(UsuariosId);
        if (UsuarioLogic.getUsuario(UsuariosId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + UsuariosId + " no existe.", 404);
        }
        UsuarioDTO detailDTO = new UsuarioDTO(UsuarioLogic.updateUsuario(UsuariosId, Usuario.toEntity()));
        LOGGER.log(Level.INFO, "UsuarioResource updateUsuario: output: {0}", detailDTO);
        return detailDTO;
    }



    /**
     * Convierte una lista de UsuarioEntity a una lista de UsuarioDTO.
     *
     * @param entityList Lista de UsuarioEntity a convertir.
     * @return Lista de UsuarioDTO convertida.
     */
    private List<UsuarioDTO> listEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDTO(entity));
        }
        return list;
    }
}
