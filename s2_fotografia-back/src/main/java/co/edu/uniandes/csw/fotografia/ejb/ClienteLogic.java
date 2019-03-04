/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.ClientePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Valentina Duarte
 */

@Stateless
public class ClienteLogic 
{
    
    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());
    
    @Inject
    private ClientePersistence cp;        
    
    
    /**
     *  Crea un nuevo cliente verificando las reglas de negocio
     * @param cliente el cliente que se quiere crear
     * @return el cliente que se creo
     * @throws BusinessLogicException si el cliente a crear no cumple las reglas de negocio
     */
    public ClienteEntity createCliente (ClienteEntity cliente) throws BusinessLogicException
    {
         LOGGER.log(Level.INFO, "Inicia la creación del cliente");
         
         //Verificar la regla de negocio que afirma que no deben haber dos usuarios con el mismo login
         
         if(cp.getByLogin(cliente.getLogin())!= null)
         {
             throw new BusinessLogicException ("Ya existe un cliente con el login \"" + cliente.getLogin() + "\"");
         }
         
        if(cliente.getCorreo().indexOf('@')<0 || cliente.getCorreo().indexOf('.')<0 || cliente.getCorreo()==null)
        {
            throw new BusinessLogicException ("El correo\"" + cliente.getCorreo() + " no es valido\"");
        }
         
        boolean correctFormat = false;
        char[] letras = cliente.getContrasena().toCharArray();
        for (int i =0; i<cliente.getContrasena().length()&& !correctFormat;i++)
        {
            if(Character.isDigit(letras[i]))
            {
               correctFormat = true; 
            }
        }
        
        if(correctFormat == false || cliente.getContrasena().length()<8 || cliente.getContrasena()==null)
        {
            throw new BusinessLogicException ("La contrasena\"" + cliente.getContrasena() + "no es valida\"");
        }
         //Llama a la persistencia para crear el cliente
       
         cp.create(cliente);
        LOGGER.log(Level.INFO, "Termina proceso de creación del cliente");
        return cliente;
    }
    
    /**
     * Se busca el cliente con el id ingresado por parametro 
     * @param clienteId el id del cliente que se quiere consultar
     * @return el cliente buscado
     */
    public ClienteEntity getCliente(Long clienteId)
    {
      LOGGER.log(Level.INFO, "Inicia proceso de consultar el cliente con id = {0}", clienteId);  
      
      //El inyect de dependecias es el que permite llamar al get que esta en la persistencia
      
      ClienteEntity cliente = cp.get(clienteId);
        if (cliente == null) 
        {
            LOGGER.log(Level.SEVERE, "El cliente con el id = {0} no existe", clienteId);
        }
        
        LOGGER.log(Level.INFO, "Termina proceso de consultar el cliente con id = {0}", clienteId);
        return cliente;
    }
    
    /**
     * Devuelve la lista de todos los clientes
     * @return lista de todos los clientes
     */
    public List<ClienteEntity> getClientes()
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar todos los clientes");
        // El inyect de dependenciaspermite llamar al getAll de la persistencia
        List<ClienteEntity> clientes = cp.getAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los clientes");
        return clientes;
    }
    
    /**
     * Actualiza el cliente con el id ingresado por parametro, por el cliente ingresado por parametro revisando
     * que se cumplan las reglas de negocio
     * @param clienteId el id del cliente que se quiere actualizar
     * @param cliente el cliente con los cambios que se quieres hacer
     * @return el cliente actualizado
     */
    public ClienteEntity setCliente(Long clienteId, ClienteEntity cliente) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el cliente con id = {0}", clienteId);
         // El inyect de dependenciaspermite llamar al set de la persistencia
        
         if(cp.getByLogin(cliente.getLogin())!= null)
         {
             throw new BusinessLogicException ("Ya existe un cliente con el login \"" + cliente.getLogin() + "\"");
         }
         
        if(cliente.getCorreo().indexOf('@')<0 || cliente.getCorreo().indexOf('.')<0 || cliente.getCorreo()==null)
        {
            throw new BusinessLogicException ("El correo\"" + cliente.getCorreo() + "no es valido\"");
        }
         
        boolean correctFormat = false;
        char[] letras = cliente.getContrasena().toCharArray();
        for (int i =0; i<cliente.getContrasena().length()&& !correctFormat;i++)
        {
            if(Character.isDigit(letras[i]))
            {
               correctFormat = true; 
            }
        }
        
        if(correctFormat == false || cliente.getContrasena().length()<8 || cliente.getContrasena()==null)
        {
            throw new BusinessLogicException ("La contrasena\"" + cliente.getContrasena() + " no es valida\"");
        }
         
         
         ClienteEntity nuevoCliente =cp.set(cliente);
         
         LOGGER.log(Level.INFO, "Termina proceso de actualizar el cliente con id = {0}", cliente.getId());
        return nuevoCliente;
         
    }
    
    /**
     * Elimina el cliente con el id ingresado como parametro
     * @param clienteId id del cliente que se quiere eliminar
     */
    public void deleteCliente(Long clienteId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el cliente con id = {0}", clienteId);
        // El inyect de dependenciaspermite llamar al delete de la persistencia
        
        cp.delete(clienteId);
        
        LOGGER.log(Level.INFO, "Termina proceso de borrar el cliente con id = {0}", clienteId);
    }
}
