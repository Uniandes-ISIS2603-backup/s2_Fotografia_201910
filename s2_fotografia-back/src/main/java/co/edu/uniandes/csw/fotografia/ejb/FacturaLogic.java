/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.FacturaPersistence;
import java.util.Calendar;
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
public class FacturaLogic 
{
    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());
    
    @Inject
    private FacturaPersistence fp;
    
    /**
     * Crea una nueva factura verificando las reglas de negocio
     * @param factura la factura que se va a crear
     * @return la factura creada
     * @throws BusinessLogicException si la factura creada no cumple con alguna de las reglas de negocio
     */
    public FacturaEntity createFactura(FacturaEntity factura) throws BusinessLogicException
    {
       LOGGER.log(Level.INFO, "Inicia la creación de la factura");
       
       //Verificar la regla de negocio que afirma que no deben haber dos facturas con el mismo numero
       
          if(fp.getByNumero(factura.getNumero())!= null)
         {
             throw new BusinessLogicException ("Ya existe una factura con el numero \"" + factura.getNumero() + "\"");
         }
          
          //Verifica la regla de negocio que prohibe que el numero de la factura sea negativo
          if(factura.getNumero()<0 || factura.getNumero()==null)
          {
               throw new BusinessLogicException ("El numero de la factura no puede ser negativo");
          }
          
          //Verifica la regla de negocio que prohibe que el precio en la factura sea negativo
          if(factura.getPrecio()<0)
          {
              throw new BusinessLogicException ("El precio en la factura no puede ser negativo");
          }
          //Verifica la regla de negocio que prohibe que la fecha sea mayor a la actual
          
          Calendar c = Calendar.getInstance();
          if(factura.getFechaCompra().getYear() > c.get(Calendar.YEAR) ||
             (factura.getFechaCompra().getYear() == c.get(Calendar.YEAR)&& factura.getFechaCompra().getMonth() > c.get(Calendar.MONTH)) ||
             (factura.getFechaCompra().getYear() == c.get(Calendar.YEAR)&& factura.getFechaCompra().getMonth() == c.get(Calendar.MONTH)&& factura.getFechaCompra().getDate()> c.get(Calendar.DATE)))
          {
               throw new BusinessLogicException ("La fecha ingresada es mayor a la actual");
          }
          
          
          //Llama a la persistencia para crear la factura
       
        fp.create(factura);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la factura");
        return factura;
          
    }
   
    /**
     * Se busca la factura con el id ingresado por parametro
     * @param facturaId id de la factura buscada
     * @return la factura buscada
     */
    public FacturaEntity getFactura(Long facturaId)
    {
      LOGGER.log(Level.INFO, "Inicia proceso de consultar la factura con id = {0}", facturaId);  
      
      //El inyect de dependecias es el que permite llamar al get que esta en la persistencia
      
      FacturaEntity factura = fp.get(facturaId);
        if (factura == null) 
        {
            LOGGER.log(Level.SEVERE, "La factura con el id = {0} no existe", facturaId);
        }
        
        LOGGER.log(Level.INFO, "Termina proceso de consultar la factura con id = {0}", facturaId);
        return factura;
    }
    
    
    /**
     * Devuelve la lista de todas las facturas
     * @return la lista de todas las facturas 
     */
    public List<FacturaEntity> getFacturas()
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar todas las facturas");
        // El inyect de dependenciaspermite llamar al getAll de la persistencia
        List<FacturaEntity> facturas = fp.getAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las facturas");
        return facturas;
    }
    
    /**
     * Actualiza la factura con el ide ingresado por la factura ingresada
     * @param facturaId el id de la factura a modificar
     * @param factura la informacion por la cual se modificara
     * @return la factura modificada
     * @throws BusinessLogicException si no se cumple alguna de las reglas de negocio
     */
     public FacturaEntity setFactura(Long facturaId, FacturaEntity factura) throws BusinessLogicException
     {
         LOGGER.log(Level.INFO, "Inicia proceso de actualizar la factura con id = {0}", facturaId);
         // El inyect de dependenciaspermite llamar al set de la persistencia
         
         //Verificar la regla de negocio que afirma que no deben haber dos usuarios con el mismo login
       
          if(fp.getByNumero(factura.getNumero())!= null)
         {
             throw new BusinessLogicException ("Ya existe una factura con el numero \"" + factura.getNumero() + "\"");
         }
          
          //Verifica la regla de negocio que prohibe que el numero de la factura sea negativo
          if(factura.getNumero()<0)
          {
               throw new BusinessLogicException ("El numero de la factura no puede ser negativo");
          }
          
          //Verifica la regla de negocio que prohibe que el precio en la factura sea negativo
          if(factura.getPrecio()<0)
          {
              throw new BusinessLogicException ("El precio en la factura no puede ser negativo");
          }
         
         FacturaEntity nuevaFactura =fp.set(factura);
         
         LOGGER.log(Level.INFO, "Termina proceso de actualizar la factura con id = {0}", factura.getId());
        return nuevaFactura;
     }
     
     /**
      * Elimina la factura con el id ingresado por parametro
      * @param facturaId el id de la factura a eliminar
      */
      public void deleteFactura(Long facturaId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la factura con id = {0}", facturaId);
        // El inyect de dependenciaspermite llamar al delete de la persistencia
        
        fp.delete(facturaId);
        
        LOGGER.log(Level.INFO, "Termina proceso de borrar la factura con id = {0}", facturaId);
    }
}
