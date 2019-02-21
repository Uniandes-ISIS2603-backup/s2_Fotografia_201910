/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.ClienteEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.ClientePersistence;
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
     *  
     * @param cliente
     * @return
     * @throws BusinessLogicException 
     */
    ClienteEntity createCliente (ClienteEntity cliente) throws BusinessLogicException
    {
         LOGGER.log(Level.INFO, "Inicia la creación del cliente");
         
         //Verificar la regla de negocio que afirma que no deben haber dos usuarios con el mismo login
         
         if(cp.getByLogin(cliente.getLogin())!= null)
         {
             throw new BusinessLogicException ("Ya existe un cliente con el login \"" + cliente.getLogin() + "\"");
         }
         
        if(cliente.getCorreo().indexOf('@')<0 && cliente.getCorreo().indexOf('.')<0 && cliente.getCorreo()==null)
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
        
        if(correctFormat == false && cliente.getContrasena().length()<8 && cliente.getContrasena()==null)
        {
            throw new BusinessLogicException ("La contrasena\"" + cliente.getContrasena() + "no es valida\"");
        }
         //Llama a la persistencia para crear el cliente
       
         cp.create(cliente);
        LOGGER.log(Level.INFO, "Termina proceso de creación del cliente");
        return cliente;
       
    }
    
    
    
}
