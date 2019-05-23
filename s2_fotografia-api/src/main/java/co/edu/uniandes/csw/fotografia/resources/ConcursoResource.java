/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;
import co.edu.uniandes.csw.fotografia.dtos.ConcursoDTO;
import co.edu.uniandes.csw.fotografia.dtos.ConcursoDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.ConcursoLogic;
import co.edu.uniandes.csw.fotografia.entities.ConcursoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.mappers.WebApplicationExceptionMapper;
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
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "Concursos".
 *
 * @Concurso Nicolas Rincon
 */
@Path("concursos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ConcursoResource {

    private static final Logger LOGGER = Logger.getLogger(ConcursoResource.class.getName());

    @Inject
    private ConcursoLogic ConcursoLogic;

    /**
     * Crea un nuevo concurso con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param concurso
     * @param Concurso {@link ConcursoDTO} - EL fotografo que se desea guardar.
     * @return JSON {@link ConcursoDTO} - El fotografo guardado con el atributo id
     * autogenerado.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException si 
     * no concuerda con las reglas del negocio del concurso
     */
    @POST
    public ConcursoDTO createConcurso(ConcursoDTO concurso) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ConcursoResource createConcurso: input: {0}", concurso);
        ConcursoEntity concursoEntity = concurso.toEntity();
        ConcursoEntity nuevoConcursoEntity = ConcursoLogic.createConcurso(concursoEntity);
        ConcursoDTO nuevoConcursoDTO = new ConcursoDTO(nuevoConcursoEntity);
        LOGGER.log(Level.INFO, "ConcursoResource createConcurso: output: {0}", nuevoConcursoDTO);
        return nuevoConcursoDTO;
    }

    /**
     * Busca y devuelve todos los fotografos que existen en la aplicacion.
     *
     * @return JSONArray {@link ConcursoDetailDTO} - Los fotografos encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ConcursoDetailDTO> getConcursos() {
        LOGGER.info("ConcursoResource getConcursos: input: void");
        List<ConcursoDetailDTO> listaConcursos = listEntity2DTO(ConcursoLogic.getConcursos());
        LOGGER.log(Level.INFO, "ConcursoResource getConcursos: output: {0}", listaConcursos);
        return listaConcursos;
    }

    /**
     * Busca el fotografo con el id asociado recibido en la URL y lo devuelve.
     *
     * @param ConcursosId Identificador del fotografo que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link ConcursoDetailDTO} - El fotografo buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el fotografo.
     */
    @GET
    @Path("{concursosId: \\d+}")
    public ConcursoDetailDTO getConcurso(@PathParam("concursosId") Long ConcursosId) {
        LOGGER.log(Level.INFO, "ConcursoResource getConcurso: input: {0}", ConcursosId);
        ConcursoEntity ConcursoEntity = ConcursoLogic.getConcurso(ConcursosId);
        if (ConcursoEntity == null) {
            throw new WebApplicationException("El recurso /concursos/" + ConcursosId + " no existe.", 404);
        }
        ConcursoDetailDTO detailDTO = new ConcursoDetailDTO(ConcursoEntity);
        LOGGER.log(Level.INFO, "ConcursoResource getConcurso: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el fotografo con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param ConcursosId Identificador del fotografo que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param Concurso {@link ConcursoDetailDTO} El fotografo que se desea guardar.
     * @return JSON {@link ConcursoDetailDTO} - El fotografo guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el fotografo a
     * actualizar.
     */
    @PUT
    @Path("{concursosId: \\d+}")
    public ConcursoDetailDTO updateConcurso(@PathParam("concursosId") Long ConcursosId, ConcursoDetailDTO Concurso) {
        LOGGER.log(Level.INFO, "ConcursoResource updateConcurso: input: id: {0} , concurso: {1}", new Object[]{ConcursosId, Concurso});
        Concurso.setId(ConcursosId);
        if (ConcursoLogic.getConcurso(ConcursosId) == null) {
            throw new WebApplicationException("El recurso /concursos/" + ConcursosId + " no existe.", 404);
        }
        ConcursoDetailDTO detailDTO = new ConcursoDetailDTO(ConcursoLogic.updateConcurso(ConcursosId, Concurso.toEntity()));
        LOGGER.log(Level.INFO, "ConcursoResource updateConcurso: output: {0}", detailDTO);
        return detailDTO;
    }

   

    /**
     * Conexión con el servicio de concursos para una foto.
     * {@link ConcursoPhotosResource}
     *
     * Este método conecta la ruta de /concursos con las rutas de /photos que
     * dependen del concurso, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las reseñas.
     *
     * @param concursosId El ID del concurso con respecto al cual se accede al
     * servicio.
     * @return El servicio de concursos para esa foto en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el concurso.
     */
    @Path("{concursosId: \\d+}/photos")
    public Class<ConcursoPhotosResource> getConcursoPhotosResource(@PathParam("concursosId") Long concursosId) {
        if (ConcursoLogic.getConcurso(concursosId) == null) {
            throw new WebApplicationException("El recurso /concursos/" + concursosId + " no existe.", 404);
        }
        return ConcursoPhotosResource.class;
    }

    /**
     * Conexión con el servicio de concursos para un fotografo.
     * {@link ConcursoFotografosResource}
     *
     * Este método conecta la ruta de /concursos con las rutas de /fotografos que
     * dependen del concurso, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las reseñas.
     *
     * @param concursosId El ID del concurso con respecto al cual se accede al
     * servicio.
     * @return El servicio de concursos para ese fotografo en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el concurso.
     */
    @Path("{concursosId: \\d+}/fotografos")
    public Class<ConcursoFotografosResource> getConcursoFotografosResource(@PathParam("concursosId") Long concursosId) {
        if (ConcursoLogic.getConcurso(concursosId) == null) {
            throw new WebApplicationException("El recurso /concursos/" + concursosId + " no existe.", 404);
        }
        return ConcursoFotografosResource.class;
    }
    
    /**
     * Conexión con el servicio de concursos para un fotografo.
     * {@link ConcursoFotografosResource}
     *
     * Este método conecta la ruta de /concursos con las rutas de /fotografos que
     * dependen del concurso, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las reseñas.
     *
     * @param concursosId El ID del concurso con respecto al cual se accede al
     * servicio.
     * @return El servicio de concursos para ese fotografo en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el concurso.
     */
    @Path("{concursosId: \\d+}/jurados")
    public Class<JuradoConcursoResource> getJuradoConcursoResource(@PathParam("concursosId") Long concursosId) {
        if (ConcursoLogic.getConcurso(concursosId) == null) {
            throw new WebApplicationException("El recurso /concursos/" + concursosId + " no existe.", 404);
        }
        return JuradoConcursoResource.class;
    }


    /**
     * Convierte una lista de ConcursoEntity a una lista de ConcursoDetailDTO.
     *
     * @param entityList Lista de ConcursoEntity a convertir.
     * @return Lista de ConcursoDetailDTO convertida.
     */
    private List<ConcursoDetailDTO> listEntity2DTO(List<ConcursoEntity> entityList) {
        List<ConcursoDetailDTO> list = new ArrayList<>();
        for (ConcursoEntity entity : entityList) {
            list.add(new ConcursoDetailDTO(entity));
        }
        return list;
    }
}

