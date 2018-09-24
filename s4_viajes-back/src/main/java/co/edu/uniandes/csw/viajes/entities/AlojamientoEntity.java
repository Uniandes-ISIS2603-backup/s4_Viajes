/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;
 
/**
 *
 * @author Ymespana
 */
@Entity
public class AlojamientoEntity extends BaseEntity implements Serializable {

    /**
     * Costo del alojamiento.
     */
    private Double costo;

    /**
     * Estrellas del alojamiento.
     */
    private Integer estrellas;

    /**
     * Nombre del alojamiento.
     */
    private String nombre;

    /**
     * Tipo del alojamiento.
     */
    private String tipo;

    /**
     * Noches del alojamiento.
     */
    private Integer noches;

    /**
     * Latitud del alojamiento.
     */
    private double latitud;

    /**
     * Longitud del alojamiento.
     */
    private double longitud;

    /**
     * Puntuacion del alojamiento.
     */
    private Integer puntuacion;

    /**
     * Ubicacion del alojamiento.
     */
    private String ubicacion;
    
    @PodamExclude
    @ManyToOne
    private ComboEntity combo;

    /**
     * Proveedor del alojamiento. 
     * Relacion muchos a uno. 
     */
    @PodamExclude
    @ManyToOne
    private ProveedorEntity proveedor;

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNoches() {
        return noches;
    }

    public void setNoches(Integer noches) {
        this.noches = noches;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ComboEntity getCombo() {
        return combo;
    }

    public void setCombo(ComboEntity combo) {
        this.combo = combo;
    }

    public ProveedorEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorEntity proveedor) {
        this.proveedor = proveedor;
    }

}
