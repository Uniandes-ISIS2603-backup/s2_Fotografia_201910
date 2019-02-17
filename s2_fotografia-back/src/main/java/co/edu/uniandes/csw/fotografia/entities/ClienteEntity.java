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

/**
 *
 * @author Valentina Duarte
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable {

    private String nombre;
    private String usuario;
    private String Correo;

    @PodamExclude
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<FacturaEntity> facturas = new ArrayList<FacturaEntity>();

    // @PodamExclude
    //@OneToMany(mappedBy="clienteCalificador", fetch =FetchType.LAZY)
    // private List<CalificacionEntity> calificacionesPorCliente = new ArrayList<CalificacionEntity>();
    
      
   // private List<FormaDePagoEntity> formasDePago= new ArrayList<>();
    
   // private List<ConcursoEntity> concursosCliente= new ArrayList<>();
    
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
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the Correo
     */
    public String getCorreo() {
        return Correo;
    }

    /**
     * @param Correo the Correo to set
     */
    public void setCorreo(String Correo) {
        this.Correo = Correo;
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

 

}
