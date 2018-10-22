/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa un proveedor en la persistencia y permite su serialización
 * 
 * @author jf.torresp
 */
@Entity
public class ProveedorEntity extends BaseEntity implements Serializable{
    
    //Atributos//

    private String username;
    
    private String contrasena;
    
    private String nombre;
    
    private int puntuacion;
    
    @PodamExclude
    @OneToMany (mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VueloEntity> vuelos = new ArrayList<VueloEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TransporteTerrestreEntity> transportes = new ArrayList<TransporteTerrestreEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ActividadEntity> actividades = new ArrayList<ActividadEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AlojamientoEntity> alojamientos = new ArrayList<AlojamientoEntity>();
    
   //Métodos//
    
    /**
     * Obtiene el usuario de un proveedor.
     *
     * @return username del proveedor.
     */
    public String getUser() {
        return username;
    }

    /**
     * Obtiene la contraseña de un proveedor.
     *
     * @return password del proveedor.
     */
    public String getPassword() {
        return contrasena;
    }

    /**
     * Obtiene el nombre de un proveedor.
     *
     * @return nombre del proveedor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el puntaje de un proveedor.
     *
     * @return puntuacion del proveedor.
     */
    public int getPuntaje() {
        return puntuacion;
    }

    /**
     * Obtiene los vuelos de un proveedor.
     *
     * @return vuelos del proveedor.
     */
    public List<VueloEntity> getVuelos()
    {
        return vuelos;
    }

    /**
     * Obtiene los transportes terrestres de un proveedor.
     *
     * @return transportes terrestres del proveedor.
     */
    public List<TransporteTerrestreEntity> getTransportes()
    {
        return transportes;
    }
    
    /**
     * Obtiene las actividades del proveedor
     *
     * @return actividades del proveedor.
     */
    public List<ActividadEntity> getActividades()
    {
        return actividades;
    }
    
    /**
     * Obtiene los alojamientos del proveedor
     * @return alojamientos del proveedor.
     */
    public List<AlojamientoEntity> getAlojamientos()
    {
        return alojamientos;
    }

    /**
     * Modifica (set) el usuario de un proveedor por el ingresado por parámetro.
     *
     * @param username nuevo usuario que modificará el actual.
     */
    public void setUser(String username) {
        this.username = username;
    }

    /**
     * Modifica (set) la contraseña de un proveedor por la ingresada por
     * parámetro.
     *
     * @param contrasena nueva contraseña que modificará la actual.
     */
    public void setPassword(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Modifica (set) el nombre de un proveedor por el ingresado por parámetro.
     *
     * @param nombre nuevo nombre que modificará el actual.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Modifica (set) el puntaje de un proveedor por el ingresado por parámetro.
     *
     * @param puntuacion nuevo puntaje que modificará el actual.
     */
    public void setPuntaje(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    /**
     * Modifica los vuelos del proveedor.
     *
     * @param vuelos Los nuevos vuelos.
     */
    public void setVuelos(List<VueloEntity> vuelos) {
        this.vuelos = vuelos;
    }
    
    /**
     * Modifica los transportes del proveedor.
     *
     * @param transportes Los nuevos transportes.
     */
    public void setTransportes(List<TransporteTerrestreEntity> transportes) {
        this.transportes = transportes;
    }
    
    /**
     * Modifica las actividades del proveedor.
     *
     * @param actividades Las nuevas actividades.
     */
    public void setActividades(List<ActividadEntity> actividades) {
        this.actividades = actividades;
    }
            
    /**
     * Modifica los alojamientos del proveedor.
     *
     * @param alojamientos os nuevos alojamientos.
     */
    public void setAlojamientos(List<AlojamientoEntity> alojamientos) {
        this.alojamientos = alojamientos;
    }
}
