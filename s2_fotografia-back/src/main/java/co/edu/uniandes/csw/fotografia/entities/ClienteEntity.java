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
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.CascadeType;

/**
 *
 * @author Valentina Duarte
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable {

    private String nombre;
    private String login;
    private String correo;
    private String contrasena;
    private String imagen;

    @PodamExclude
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<FacturaEntity> facturas = new ArrayList<FacturaEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy="cliente",cascade = CascadeType.PERSIST, orphanRemoval = true, fetch =FetchType.LAZY)
     private List<FormaDePagoEntity> formasDePago= new ArrayList<>();
     
    @PodamExclude
    @OneToMany(mappedBy="cliente", fetch =FetchType.LAZY)
    private List<ConcursoEntity> concursosCliente = new ArrayList<ConcursoEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy="clienteCalificador", fetch =FetchType.LAZY)
     private List<CalificacionEntity> calificacionesPorCliente = new ArrayList<CalificacionEntity>();
    
    /**
     * Constructor vacio
     */
   public ClienteEntity() {

    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the usuario to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the facturas
     */
    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the formasDePago
     */
    public List<FormaDePagoEntity> getFormasDePago() {
        return formasDePago;
    }

    /**
     * @param formasDePago the formasDePago to set
     */
    public void setFormasDePago(List<FormaDePagoEntity> formasDePago) {
        this.formasDePago = formasDePago;
    }

    /**
     * @return the concursosCliente
     */
    public List<ConcursoEntity> getConcursosCliente() {
        return concursosCliente;
    }

    /**
     * @param concursosCliente the concursosCliente to set
     */
    public void setConcursosCliente(List<ConcursoEntity> concursosCliente) {
        this.concursosCliente = concursosCliente;
    }
    
    /**
     * @return the calificacionesPorCliente
     */
    public List<CalificacionEntity> getCalificacionesPorCliente() {
        return calificacionesPorCliente;
    }

    /**
     * @param calificacionesPorCliente the calificacionesPorCliente to set
     */
    public void setCalificacionesPorCliente(List<CalificacionEntity> calificacionesPorCliente) {
        this.calificacionesPorCliente = calificacionesPorCliente;
    }

}
