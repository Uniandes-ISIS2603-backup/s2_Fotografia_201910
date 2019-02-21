/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author a.trujilloa1
 */
@Entity
public class JuradoEntity extends BaseEntity implements Serializable{
    private String nombre;
    private String apellido;
    private String correo;
    private Long cedula;
    private String pais;
    private String ciudad;
    
    @PodamExclude
    @ManyToMany
    private List<PhotoEntity> fotosCalificadas = new ArrayList<PhotoEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "juradoCalificador", fetch = FetchType.LAZY)
    private List<CalificacionEntity> calificaciones = new ArrayList<CalificacionEntity>();
    
    @PodamExclude
    @ManyToOne
    private ConcursoEntity concursoJurado;
    
    //@PodamExclude
    //@OneToOne(mappedBy = "jurado", fetch=FetchType.LAZY)
    //private RondaEntity ronda;
    
    /**
* Constructor de la clase
*/
    public JuradoEntity(){}
    
     /**
     * Devuelve el nombre del jurado.
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del jurado.
     *
     * @param nombre el nombre asignado
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el atributo apellido.
     *
     * @return atributo apellido.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el valor del atributo apellido.
     *
     * @param apellido el apellido asignado
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Devuelve el correo del jurado
     *
     * @return el correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Modifica la correo del jurado
     *
     * @param correo el correo asignado
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Devuelve la cedula del jurado
     *
     * @return la cedula
     */
    public Long getCedula() {
        return cedula;
    }

    /**
     * Modifica la cedula del jurado
     *
     * @param cedula la cedula asignada
     */
    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }
    
    /**
     * Devuelve el pais del jurado
     *
     * @return el pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Modifica el pais del jurado
     *
     * @param pais el pais asignado
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
    
    /**
     * Devuelve la ciudad del jurado
     *
     * @return la ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Modifica la ciudad del jurado
     *
     * @param ciudad la ciudad asignada
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
