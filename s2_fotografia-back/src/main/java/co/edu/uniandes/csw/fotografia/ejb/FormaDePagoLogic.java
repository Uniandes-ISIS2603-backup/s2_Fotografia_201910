/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.FormaDePagoPersistence;
import java.util.Calendar;
import java.util.Date;
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
public class FormaDePagoLogic {

    public final static String TARJETADEBITO = "Tarjeta Debito";
    public final static String TARJETACREDITO = "Tarjeta Credito";
    public final static String VISA = "VISA";
    public final static String MASTERCARD = "MASTERCARD";
    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());

    @Inject
    private FormaDePagoPersistence fdpp;

    /**
     * Crea una nueva forma de pago verificando las reglas de negocio
     *
     * @param formaDePago de pago que se va a crear
     * @return la forma de pago creada
     * @throws BusinessLogicException si la forma de pago creada no cumple con
     * alguna de las reglas de negocio
     */
    public FormaDePagoEntity createFormaDePago(FormaDePagoEntity formaDePago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia la creación de la factura");

        //Verificar la regla de negocio que afirma que no deben haber dos tarjetas con el mismo numero
        if (fdpp.getByNumeroTarjeta(formaDePago.getNumeroTarjeta()) != null) {
            throw new BusinessLogicException("Ya existe una forma de pago con numero \"" + formaDePago.getNumeroTarjeta() + "\"");
        }

        //Verificar la regla de negocio que afirma que el numero de la tarjeta no puede ser null
        if (formaDePago.getNumeroTarjeta() == null) {
            throw new BusinessLogicException("El numero de tarjeta no puede ser null \"");
        }

        //Verificar la regla de negocio que afirma que el numero de la tarjeta debe tener entre 13 y 18 digitos, y que debe ser positivo
        String digitos = formaDePago.getNumeroTarjeta().toString().trim();
        if (formaDePago.getNumeroTarjeta() < 0 || digitos.length() < 13 || digitos.length() > 18) {
            throw new BusinessLogicException("El numero de la tarjeta no es valido");
        }

        //Verificar la regla de negocio que afirma que el numero de verificacion debe tener entre 3 o 4 digitos, y que debe ser posi
        if (formaDePago.getNumeroVerificacion() != null) {
            String numeroVerificacion = formaDePago.getNumeroVerificacion().toString().trim();
            if (formaDePago.getTipoDeTarjeta().equals(TARJETACREDITO)) {
                if (formaDePago.getNumeroVerificacion() < 0 || numeroVerificacion.length() < 3
                        || numeroVerificacion.length() > 4) {
                    throw new BusinessLogicException("El numero de verificación no es valido");
                }
            }

            // Verifica la regla de negocio que afirma que la fecha de vencimiento debe ser anterior a la actual
            Date fecha = formaDePago.getFechaVencimiento();
            if (fecha == null) {
                throw new BusinessLogicException("La fecha de vencimiento no puede estar vacía.");
            }

            Calendar c = Calendar.getInstance();

            if (fecha.getYear() < c.get(Calendar.YEAR)
                    || (fecha.getYear() == c.get(Calendar.YEAR) && fecha.getMonth() < c.get(Calendar.MONTH))) {
                throw new BusinessLogicException("La tarjeta ya está vencida.");
            }
        }

        //Verifica regla de negocio que afirma que ina tarjeta solo puede ser de credito o debito
        if (!(formaDePago.getTipoDeTarjeta().equalsIgnoreCase(TARJETACREDITO) || formaDePago.getTipoDeTarjeta().equalsIgnoreCase(TARJETADEBITO)) ) {
            
            throw new BusinessLogicException("El tipo de tarjeta no es valido");
        }

        //Verifica regla de negocio que afirma que una tarjeta credito solo puede ser VISA o MASTERCARD
        if (formaDePago.getTipoDeTarjeta().equalsIgnoreCase(TARJETACREDITO)) {
            if (!(formaDePago.getTipoTarjetaDeCredito().equalsIgnoreCase(MASTERCARD)||formaDePago.getTipoTarjetaDeCredito().equalsIgnoreCase(VISA))) 
            {
                throw new BusinessLogicException("El tipo de tarjeta de credito no es valido. Debe ser VISA o MASTERCARD");
            }
        }

        //Llama a la persistencia para crear la forma de pago
        fdpp.create(formaDePago);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la factura");
        return formaDePago;

    }

    /**
     * Se busca la forma de pago con el id ingresado por parametro
     *
     * @param formaDePagoId id de la forma de pago buscada
     * @return la forma de pago buscada
     */
    public FormaDePagoEntity getFormaDePago(Long formaDePagoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la forma de pago con id = {0}", formaDePagoId);

        //El inyect de dependecias es el que permite llamar al get que esta en la persistencia
        FormaDePagoEntity formaDePago = fdpp.get(formaDePagoId);
        if (formaDePago == null) {
            LOGGER.log(Level.SEVERE, "La forma de pago con el id = {0} no existe", formaDePagoId);
        }

        LOGGER.log(Level.INFO, "Termina proceso de consultar la forma de pago con id = {0}", formaDePagoId);
        return formaDePago;
    }

    /**
     * Devuelve la lista de todas las formas de pago
     *
     * @return la lista de todas las formas de pago
     */
    public List<FormaDePagoEntity> getFormasDePago() {
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar todas las formas de pago");
        // El inyect de dependenciaspermite llamar al getAll de la persistencia
        List<FormaDePagoEntity> formasDePago = fdpp.getAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las formas de pago");
        return formasDePago;
    }

    /**
     * Actualiza la forma de pago con el id ingresado por la forma de pago
     * ingresada
     *
     * @param formaDePagoId el id de la forma de pago a modificar
     * @param formaDePago la informacion por la cual se modificara
     * @return la forma de pago modificada
     * @throws BusinessLogicException si no se cumple alguna de las reglas de
     * negocio
     */
    public FormaDePagoEntity setFormaDePago(Long formaDePagoId, FormaDePagoEntity formaDePago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la forma de pago con id = {0}", formaDePagoId);
        // El inyect de dependenciaspermite llamar al set de la persistencia

        //Verificar la regla de negocio que afirma que no deben haber dos tarjetas con el mismo numero
        if (fdpp.getByNumeroTarjeta(formaDePago.getNumeroTarjeta()) != null) {
            throw new BusinessLogicException("Ya existe una forma de pago con numero \"" + formaDePago.getNumeroTarjeta() + "\"");
        }

        //Verificar la regla de negocio que afirma que el numero de la tarjeta no puede ser null
        if (formaDePago.getNumeroTarjeta() == null) {
            throw new BusinessLogicException("El numero de tarjeta no puede ser null \"");
        }

        //Verificar la regla de negocio que afirma que el numero de la tarjeta debe tener entre 13 y 18 digitos, y que debe ser positivo
        String digitos = formaDePago.getNumeroTarjeta().toString().trim();
        if (formaDePago.getNumeroTarjeta() < 0 || digitos.length() < 13 || digitos.length() > 18) {
            throw new BusinessLogicException("El numero de la tarjeta no es valido");
        }

        //Verificar la regla de negocio que afirma que el numero de verificacion debe tener entre 3 o 4 digitos, y que debe ser posi
        if (formaDePago.getNumeroVerificacion() != null) {
            String numeroVerificacion = formaDePago.getNumeroVerificacion().toString().trim();
            if (formaDePago.getTipoDeTarjeta().equals(TARJETACREDITO)) {
                if (formaDePago.getNumeroVerificacion() < 0 || numeroVerificacion.length() < 3
                        || numeroVerificacion.length() > 4) {
                    throw new BusinessLogicException("El numero de verificación no es valido");
                }
            }

            // Verifica la regla de negocio que afirma que la fecha de vencimiento debe ser anterior a la actual
            Date fecha = formaDePago.getFechaVencimiento();
            if (fecha == null) {
                throw new BusinessLogicException("La fecha de vencimiento no puede estar vacía.");
            }

            Calendar c = Calendar.getInstance();

            if (fecha.getYear() < c.get(Calendar.YEAR)
                    || (fecha.getYear() == c.get(Calendar.YEAR) && fecha.getMonth() < c.get(Calendar.MONTH))) {
                throw new BusinessLogicException("La tarjeta ya está vencida.");
            }
        }

       //Verifica regla de negocio que afirma que ina tarjeta solo puede ser de credito o debito
        if (!(formaDePago.getTipoDeTarjeta().equalsIgnoreCase(TARJETACREDITO) || formaDePago.getTipoDeTarjeta().equalsIgnoreCase(TARJETADEBITO)) ) {
            
            throw new BusinessLogicException("El tipo de tarjeta no es valido");
        }

        //Verifica regla de negocio que afirma que una tarjeta credito solo puede ser VISA o MASTERCARD
        if (formaDePago.getTipoDeTarjeta().equalsIgnoreCase(TARJETACREDITO)) {
            if (!(formaDePago.getTipoTarjetaDeCredito().equalsIgnoreCase(MASTERCARD)||formaDePago.getTipoTarjetaDeCredito().equalsIgnoreCase(VISA))) 
            {
                throw new BusinessLogicException("El tipo de tarjeta de credito no es valido. Debe ser VISA o MASTERCARD");
            }
        }

        FormaDePagoEntity nuevaFormaDePago = fdpp.set(formaDePago);

        LOGGER.log(Level.INFO, "Termina proceso de actualizar la forma de pago con id = {0}", formaDePago.getId());
        return nuevaFormaDePago;
    }

    /**
     * Elimina la forma de pago con el id ingresado por parametro
     *
     * @param formaDePagoId el id de la forma de pago a eliminar
     */
    public void deleteFormaDePago(Long formaDePagoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la forma de pago con id = {0}", formaDePagoId);
        // El inyect de dependenciaspermite llamar al delete de la persistencia

        fdpp.delete(formaDePagoId);

        LOGGER.log(Level.INFO, "Termina proceso de borrar la forma de pago con id = {0}", formaDePagoId);
    }
}
