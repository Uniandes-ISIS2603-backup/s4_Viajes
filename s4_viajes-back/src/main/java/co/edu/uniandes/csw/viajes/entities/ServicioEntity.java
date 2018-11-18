/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Diego Barrios
 */
@Entity
public class ServicioEntity extends BaseEntity implements Serializable {
    
    /**
     * Lista de fechas en las que el servicio se encuentra disponible.
     */
    protected List<Date> fechasDisponibles=new ArrayList<>();
    
    /**
     * Lista de cupos disponibles, cada inice muestra la disponibilidad de la fecha del mismo indice el la lista fechasDisponibles.
     */
    protected List<Integer> disponibilidadFecha=new ArrayList<>();
    
    /**
     * Latitud donde se lleva a cabo el servicio.
     */
    protected Double latitud;
    
     /**
     * Longitud donde se lleva a cabo el servicio.
     */
    protected Double longitud;
   
    /**
     * Costo del servicio.
     */
    protected Double costo;
    
     /**
     * Puntuacion del servicio.
     */
    protected Double puntuacion;
   
      /**
     * Nombre del servicio.
     */
    protected String nombre;
    
     /**
     * Cantidad de calificaciones del servicio.
     */
    protected int cantidadCalificaciones;
    
    /**
     * Duracion del servicio.
     */
    private double duracion;
    
    @PodamExclude
    @ManyToOne
    private ReservaEntity reserva;
    
    @PodamExclude
    @ManyToOne
    private ProveedorEntity proveedor;
    
    public ServicioEntity()
    {

    }

    public List<Date> getFechasDisponibles() {
        return fechasDisponibles;
    }

    public void setFechasDisponibles(List<Date> fechasDisponibles) {
        this.fechasDisponibles = fechasDisponibles;
    }
    
    public void addFechaDisponible(Date fechaDisponible,int disponibilidad) {
        if(fechaDisponible!=null&&disponibilidad>0){
            fechasDisponibles.add(fechaDisponible);
            disponibilidadFecha.add(disponibilidad);
        }
    }
    
    public int deleteFechaDisponible(Date fechaDisponible){
        int i=getPosFecha(fechaDisponible);
        if(i!=-1){
               fechasDisponibles.remove(i);
               disponibilidadFecha.remove(i);
            }
        return i;
    }
    
    public int getPosFecha(Date fechaDisponible){
        for(int i=0;i<fechasDisponibles.size();i++)
            if((fechasDisponibles.get(i)).compareTo(fechaDisponible)==0)  
               return i;
        return -1;
    }
    
    public int reservarCuposFecha(Date fechaDisponible,int cantCupos){
        int i=getPosFecha(fechaDisponible);
        if(i!=-1){
            int nDisponibilidad=disponibilidadFecha.get(i)-cantCupos;
            if(nDisponibilidad>=0)
                disponibilidadFecha.set(i, nDisponibilidad);
            else
                i=-1;
        }
        return i;
    }
    

    public List<Integer> getDisponibilidadFecha() {
        return disponibilidadFecha;
    }

    public void setDisponibilidadFecha(List<Integer> disponibilidadFecha) {
        this.disponibilidadFecha = disponibilidadFecha;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadCalificaciones() {
        return cantidadCalificaciones;
    }

    public void setCantidadCalificaciones(int cantidadCalificaciones) {
        this.cantidadCalificaciones = cantidadCalificaciones;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
    
    
    

    
}
