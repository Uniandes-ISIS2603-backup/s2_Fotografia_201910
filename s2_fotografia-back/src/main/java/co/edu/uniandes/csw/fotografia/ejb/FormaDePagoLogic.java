/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.FormaDePagoPersistence;
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
public class FormaDePagoLogic 
{
    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());
    
    @Inject
    private FormaDePagoPersistence fdpp;
    
    /**
     * Crea una nueva forma de pago verificando las reglas de negocio
     * @param forma de pago  que se va a crear
     * @return la forma de pago creada
     * @throws BusinessLogicException si la forma de pago creada no cumple con alguna de las reglas de negocio
     */
    public FormaDePagoEntity createFactura(FormaDePagoEntity formaDePago) throws BusinessLogicException
    {
       LOGGER.log(Level.INFO, "Inicia la creación de la factura");
       
          
          //Llama a la persistencia para crear la forma de pago
       
        fdpp.create(formaDePago);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la factura");
        return formaDePago;
          
    }
   
    /**
     * Se busca la forma de pago con el id ingresado por parametro
     * @param formaDePagoId id de la forma de pago buscada
     * @return la forma de pago buscada
     */
    public FormaDePagoEntity getFormaDePago(Long formaDePagoId)
    {
      LOGGER.log(Level.INFO, "Inicia proceso de consultar la forma de pago con id = {0}", formaDePagoId);  
      
      //El inyect de dependecias es el que permite llamar al get que esta en la persistencia
      
      FormaDePagoEntity formaDePago = fdpp.get(formaDePagoId);
        if (formaDePago == null) 
        {
            LOGGER.log(Level.SEVERE, "La forma de pago con el id = {0} no existe", formaDePagoId);
        }
        
        LOGGER.log(Level.INFO, "Termina proceso de consultar la forma de pago con id = {0}", formaDePagoId);
        return formaDePago;
    }
    
    
    /**
     * Devuelve la lista de todas las formas de pago
     * @return la lista de todas las formas de pago 
     */
    public List<FormaDePagoEntity> getFormasDePago()
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar todas las formas de pago");
        // El inyect de dependenciaspermite llamar al getAll de la persistencia
        List<FormaDePagoEntity> formasDePago = fdpp.getAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las formas de pago");
        return formasDePago;
    }
    
    /**
     * Actualiza la forma de pago con el id ingresado por la forma de pago ingresada
     * @param formaDePagoId el id de la forma de pago a modificar
     * @param formaDePago la informacion por la cual se modificara
     * @return la forma de pago modificada
     * @throws BusinessLogicException si no se cumple alguna de las reglas de negocio
     */
     public FormaDePagoEntity setFormaDePago(Long formaDePagoId, FormaDePagoEntity formaDePago) throws BusinessLogicException
     {
         LOGGER.log(Level.INFO, "Inicia proceso de actualizar la forma de pago con id = {0}", formaDePagoId);
         // El inyect de dependenciaspermite llamar al set de la persistencia
         

       
         FormaDePagoEntity nuevaFormaDePago =fdpp.set(formaDePago);
         
         LOGGER.log(Level.INFO, "Termina proceso de actualizar la forma de pago con id = {0}", formaDePago.getId());
        return nuevaFormaDePago;
     }
     
     /**
      * Elimina la forma de pago con el id ingresado por parametro
      * @param formaDePagoId el id de la forma de pago a eliminar
      */
      public void deleteFormaDePago(Long formaDePagoId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la forma de pago con id = {0}", formaDePagoId);
        // El inyect de dependenciaspermite llamar al delete de la persistencia
        
        fdpp.delete(formaDePagoId);
        
        LOGGER.log(Level.INFO, "Termina proceso de borrar la forma de pago con id = {0}", formaDePagoId);
    }
}
