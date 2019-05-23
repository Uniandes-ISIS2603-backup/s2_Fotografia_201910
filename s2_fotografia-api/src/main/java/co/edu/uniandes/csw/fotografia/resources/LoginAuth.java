/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.resources;

import co.edu.uniandes.csw.fotografia.adapters.Login;
import co.edu.uniandes.csw.fotografia.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.fotografia.dtos.FotografoDetailDTO;
import co.edu.uniandes.csw.fotografia.dtos.JuradoDetailDTO;
import co.edu.uniandes.csw.fotografia.dtos.OrganizadorDetailDTO;
import co.edu.uniandes.csw.fotografia.ejb.ClienteLogic;
import co.edu.uniandes.csw.fotografia.ejb.FotografoLogic;
import co.edu.uniandes.csw.fotografia.ejb.JuradoLogic;
import co.edu.uniandes.csw.fotografia.ejb.OrganizadorLogic;
import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.entities.FotografoEntity;
import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import co.edu.uniandes.csw.fotografia.entities.OrganizadorEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "Concursos".
 *
 * @Concurso Nicolas Rincon
 */
@Path("login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class LoginAuth {

    private static final Logger LOGGER = Logger.getLogger(LoginAuth.class.getName());

    @Inject
    private FotografoLogic fotografoLogic;
    
    @Inject
    private OrganizadorLogic organizadorLogic;
    
    @Inject
    private ClienteLogic clienteLogic;
    
    @Inject
    private JuradoLogic juradoLogic;

    /**
     * Revisa y autoriza el login de un Ftogorafo
     *
     * 
     * @param response  - El login y contraseña dadas.
     * @return JSON {@link ConcursoDTO} - El fotografo guardado con el atributo id
     * autogenerado.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException si 
     * no concuerda con las reglas del negocio del concurso
     */
    @POST
    @Path("fotografo")
    public FotografoDetailDTO autorizarFotografo(Login response) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Login : input: {0}", response.login);
        FotografoEntity nuevoFotografoEntity = fotografoLogic.getFotografoByLogin(response.login);
        FotografoDetailDTO nuevoFotografoDTO = new FotografoDetailDTO(nuevoFotografoEntity);
        LOGGER.log(Level.INFO, "ConcursoResource createConcurso: output: {0}", nuevoFotografoDTO);
        return nuevoFotografoDTO;
    }
    
    /**
     * Revisa y autoriza el login de un organizador
     *
     * 
     * @param response  - El login y contraseña dadas.
     * @return JSON {@link ConcursoDTO} - El fotografo guardado con el atributo id
     * autogenerado.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException si 
     * no concuerda con las reglas del negocio del concurso
     */
    @POST
    @Path("organizador")
    public OrganizadorDetailDTO autorizarOrganizador(Login response) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Login : input: {0}", response.login);
        OrganizadorEntity nuevoOrganizadorEntity = organizadorLogic.getOrganizadorByLogin(response.login);
        OrganizadorDetailDTO nuevoOrganizadorDTO = new OrganizadorDetailDTO(nuevoOrganizadorEntity);
        LOGGER.log(Level.INFO, "ConcursoResource createConcurso: output: {0}", nuevoOrganizadorDTO);
        return nuevoOrganizadorDTO;
    }
    
    /**
     * Revisa y autoriza el login de un cliente
     *
     * 
     * @param response  - El login y contraseña dadas.
     * @return JSON {@link ConcursoDTO} - El fotografo guardado con el atributo id
     * autogenerado.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException si 
     * no concuerda con las reglas del negocio del concurso
     */
    @POST
    @Path("cliente")
    public ClienteDetailDTO autorizarCliente(Login response) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Login : input: {0}", response.login);
        ClienteEntity nuevoClienteEntity = clienteLogic.getClienteByLogin(response.login);
        ClienteDetailDTO nuevoClienteDTO = new ClienteDetailDTO(nuevoClienteEntity);
        LOGGER.log(Level.INFO, "ConcursoResource createConcurso: output: {0}", nuevoClienteDTO);
        return nuevoClienteDTO;
    }
    
    /**
     * Revisa y autoriza el login de un Ftogorafo
     *
     * 
     * @param response  - El login y contraseña dadas.
     * @return JSON {@link ConcursoDTO} - El fotografo guardado con el atributo id
     * autogenerado.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException si 
     * no concuerda con las reglas del negocio del concurso
     */
    @POST
    @Path("jurado")
    public JuradoDetailDTO autorizarJurado(Login response) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Login : input: {0}", response.login);
        JuradoEntity nuevoJuradoEntity = juradoLogic.getJuradoByLogin(response.login);
        JuradoDetailDTO nuevoJuradoDTO = new JuradoDetailDTO(nuevoJuradoEntity);
        LOGGER.log(Level.INFO, "ConcursoResource createConcurso: output: {0}", nuevoJuradoDTO);
        return nuevoJuradoDTO;
    }
}
