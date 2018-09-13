/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author estudiante
 */
@Entity
public class ComboEntity  extends BaseEntity implements Serializable {
    //-----------------------------------------------------------------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------------------------------------------------------------
  
    private double costo;
    
    private String nombre;
    
    private String comboId;

    private int dias;
    
    private double horas;

    private int puntuacion;
    
    private List<VueloEntity> vuelos;
    
    private List<AlojamientoEntity> alojamientos;

    private List<ActividadEntity> actividades;
    
    private List<TransporteTerrestreEntity> transportesTerrestres;

    

    //-----------------------------------------------------------------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------------------------------------------------------------
    
    
    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {    
        this.comboId = comboId;
    }


    public double getCosto() {
        return costo;
    }
     
    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    public List<VueloEntity> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<VueloEntity> vuelos) {
        this.vuelos = vuelos;
    }

    public List<AlojamientoEntity> getAlojamientos() {
        return alojamientos;
    }

    public void setAlojamientos(List<AlojamientoEntity> alojamientos) {
        this.alojamientos = alojamientos;
    }

    public List<ActividadEntity> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadEntity> actividades) {
        this.actividades = actividades;
    }

     public List<TransporteTerrestreEntity> getTransportesTerrestres() {
        return transportesTerrestres;
    }
    
    public void setTransportesTerrestres(List<TransporteTerrestreEntity> transportesTerrestres) {
        this.transportesTerrestres = transportesTerrestres;
    }
}
