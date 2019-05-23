/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.ejb;

import co.edu.uniandes.csw.fotografia.entities.JuradoEntity;
import co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.fotografia.persistence.JuradoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author a.trujilloa1
 */
@Stateless
public class JuradoLogic {
     private static final Logger LOGGER = Logger.getLogger(JuradoLogic.class.getName());

    @Inject
    private JuradoPersistence persistence;

    /**
     * Se encarga de crear un Jurado en la base de datos.
     *
     * @param juradoEntity Objeto de JuradoEntity con los datos nuevos
     * @return Objeto de JuradoEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.fotografia.exceptions.BusinessLogicException
     */
    public JuradoEntity createJurado (JuradoEntity juradoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del jurado");
        if(!persistence.verificarNombre(juradoEntity.getNombre())&& !persistence.verificarApellido(juradoEntity.getApellido()))
        {
            throw new BusinessLogicException("El nombre y el apellido estan mal definidos" + juradoEntity.getNombre() + juradoEntity.getApellido());
        }
        else if( ! persistence.verificarCedula(juradoEntity.getCedula()))
        {
            throw new BusinessLogicException("La cedula no esta bien definida" + juradoEntity.getCedula());
        }
        else if ( ! persistence.verificarPais(juradoEntity.getPais()) && ! persistence.verificarCiudad(juradoEntity.getCiudad()))
        {
            throw new BusinessLogicException("El pais y la ciudad estan mal definidos" + juradoEntity.getPais() + juradoEntity.getCiudad());
        }
        JuradoEntity newJuradoEntity = persistence.create(juradoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del jurado");
        return newJuradoEntity;
    }

    /**
     * Obtiene la lista de los registros de Jurado.
     *
     * @return Colección de objetos de JuradoEntity.
     */
    public List<JuradoEntity> getJurados() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los jurados");
        List<JuradoEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las jurados");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Jurado a partir de su ID.
     *
     * @param juradoId Identificador de la instancia a consultar
     * @return Instancia de JuradoEntity con los datos del Jurado consultado.
     */
    public JuradoEntity getJurado(Long juradoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el jurado con id = {0}", juradoId);
        JuradoEntity juradoEntity = persistence.find(juradoId);
        if (juradoEntity == null) {
            LOGGER.log(Level.SEVERE, "El jurado con el id = {0} no existe", juradoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el jurado con id = {0}", juradoId);
        return juradoEntity;
    }

    /**
     * Actualiza la información de una instancia de Jurado.
     *
     * @param juradoId Identificador de la instancia a actualizar
     * @param juradoEntity Instancia de JuradoEntity con los nuevos datos.
     * @return Instancia de JuradoEntity con los datos actualizados.
     */
    public JuradoEntity updateJurado(Long juradoId, JuradoEntity juradoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el jurado con id = {0}", juradoId);
        JuradoEntity newJuradoEntity = persistence.update(juradoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el jurado con id = {0}", juradoId);
        return newJuradoEntity;
    }

    /**
     * Elimina una instancia de Jurado de la base de datos.
     *
     * @param juradoId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el jurado tiene libros asociados.
     */
    public void deleteJurado(Long juradoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el jurado con id = {0}", juradoId);
        persistence.delete(juradoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el jurado con id = {0}", juradoId);
    }

    public JuradoEntity getJuradoByLogin(String login) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el cliente con login= {0}", login);
        
        JuradoEntity fotografo = persistence.findByLogin(login);
        if(fotografo == null)
        {
            LOGGER.log(Level.SEVERE, "El cliente con el login = {0} no existe", login);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el cliente con login = {0}", login);
        return fotografo;
    }
}
