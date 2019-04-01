/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.entities.FacturaEntity;
import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.fotografia.persistence.FacturaPersistence;
import co.edu.uniandes.csw.fotografia.persistence.FormaDePagoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Valentina Duarte
 */
@Stateless
public class FacturaFormasDePagoLogic 
{
     private static final Logger LOGGER = Logger.getLogger(FacturaFormasDePagoLogic.class.getName());

    @Inject
    private FormaDePagoPersistence formaDePagoPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
 
    @Inject
    private FacturaPersistence facturaPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    
     /**
     * Agregar una forma de pago a una factura
     * @param facturasId El id de la factura a guardar
     * @param formasDePagoId El id de la forma de pago al cual se le va a guardar la factura
     * @return La forma de pago que fue agregado a la factura.
     */
    public FormaDePagoEntity addFormaDePago(Long formasDePagoId, Long facturasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar la forma de pago con id = {0} a la factura con id = " + facturasId, formasDePagoId);
        FormaDePagoEntity formaDePagoEntity = formaDePagoPersistence.get(formasDePagoId);
        FacturaEntity facturaEntity = facturaPersistence.get(facturasId);
        facturaEntity.setFormaDePagoFactura(formaDePagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar la forma de Pago con id = {0} a la factura con id = " + facturasId, formasDePagoId);
        return formaDePagoPersistence.get(formasDePagoId);
    }

    /**
     *
     * Obtener una factura por medio de su id y el de su formaDepago.
     *
     * @param facturasId id de la factura a ser buscada
     * @return la forma de pago solicitada por medio de su id.
     */
    public FormaDePagoEntity getFormaDePago(Long facturasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la formaDePago de la factura con id = {0}", facturasId);
        FormaDePagoEntity formaDePagoEntity = facturaPersistence.get(facturasId).getFormaDePagoFactura();
        LOGGER.log(Level.INFO, "Termina proceso de consultar la forma de pago dela factura con id = {0}", facturasId);
        return formaDePagoEntity;
    }

    /**
     * Remplazar forma de pago de una factura
     *
     * @param facturasId el id dela factura que se quiere actualizar.
     * @param formasDePagoId El id la nueva forma de pago asociado al premio.
     * @return el nuevo forma de pago asociado.
     */
    public FormaDePagoEntity replaceFormaDePago(Long facturasId, Long formasDePagoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar l FORMA DE PAGO de la factura premio con id = {0}", facturasId);
        FormaDePagoEntity formaDePagoEntity = formaDePagoPersistence.get(formasDePagoId);
        FacturaEntity facturaEntity = facturaPersistence.get(facturasId);
        facturaEntity.setFormaDePagoFactura(formaDePagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar la forma de pago con id = {0} a la factura con id = " + facturasId, formasDePagoId);
        return formaDePagoPersistence.get(formasDePagoId);
    }

    /**
     * Borrar la forma de pago de una factura
     *
     * @param facturasId La factura que se desea borrar de la forma de pago.
     * @throws BusinessLogicException si la factura no tiene forma de pago
     */
    
    public void removeFormaDePago(Long facturasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la forma de pago dela factura con id = {0}", facturasId);
        FacturaEntity facturaEntity = facturaPersistence.get(facturasId);
        if (facturaEntity.getFormaDePagoFactura() == null) {
            throw new BusinessLogicException("La factura no tiene forma de pago");
        }
        FormaDePagoEntity formaDePagoEntity = formaDePagoPersistence.get(facturaEntity.getFormaDePagoFactura().getId());
        facturaEntity.setFormaDePagoFactura(null);
        formaDePagoEntity.getFacturas().remove(facturaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la forma de pago con id = {0} de la factura con id = " + facturasId, formaDePagoEntity.getId());
    }
}
