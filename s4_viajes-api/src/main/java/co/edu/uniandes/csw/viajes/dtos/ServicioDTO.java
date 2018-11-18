/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ServicioEntity;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Diego Barrios
 */
public class ServicioDTO implements Serializable{
   
     /**
     * ID del servicio.
     */
    private Long id;
    
     /**
     * Lista de fechas en las que el servicio se encuentra disponible.
     */
    private List<Date> fechasDisponibles;
    
    /**
     * Lista de cupos disponibles, cada inice muestra la disponibilidad de la fecha del mismo indice el la lista fechasDisponibles.
     */
    private List<Integer> disponibilidadFecha;
    
    /**
     * Latitud donde se lleva a cabo el servicio.
     */
    private Double latitud;
    
     /**
     * Longitud donde se lleva a cabo el servicio.
     */
    private Double longitud;
   
    /**
     * Costo del servicio.
     */
    private Double costo;
    
     /**
     * Puntuacion del servicio.
     */
    private Double puntuacion;
   
      /**
     * Nombre del servicio.
     */
    private String nombre;
    
     /**
     * Cantidad de calificaciones del servicio.
     */
    private int cantidadCalificaciones;
   
     /**
     * Duracion del servicio.
     */
    private double duracion;
    
    
    /**
     * Constructor vacio.
     */
    public ServicioDTO()
    {
        
    }
    
    /**
     * Constructor por defecto.
     * @param servicioEntity Entidad del servicio. 
     */ 
    public ServicioDTO(ServicioEntity servicioEntity)
    {
        if(servicioEntity!=null)
        {
            fechasDisponibles=servicioEntity.getFechasDisponibles();
            disponibilidadFecha=servicioEntity.getDisponibilidadFecha();
            latitud=servicioEntity.getLatitud();
            longitud=servicioEntity.getLongitud();
            costo=servicioEntity.getCosto();
            puntuacion=servicioEntity.getPuntuacion();
            nombre=servicioEntity.getNombre();
            id=servicioEntity.getId();
            cantidadCalificaciones=servicioEntity.getCantidadCalificaciones();
            duracion=servicioEntity.getDuracion();
        }
    }
    
     /**
     * Método para transformar el DTO a una entidad.
     * @return La entidad del DTO asociado.
     */
      public ServicioEntity toEntity() 
    {
        ServicioEntity servicioEntity = new ServicioEntity();
        servicioEntity.setCosto(costo);
        servicioEntity.setDisponibilidadFecha(disponibilidadFecha);
        servicioEntity.setFechasDisponibles(fechasDisponibles);
        servicioEntity.setLatitud(latitud);
        servicioEntity.setLongitud(longitud);
        servicioEntity.setNombre(nombre);
        servicioEntity.setPuntuacion(puntuacion);
        servicioEntity.setCantidadCalificaciones(cantidadCalificaciones);
        servicioEntity.setDuracion(duracion);
        return servicioEntity;
    }

    public List<Date> getFechasDisponibles() {
        return fechasDisponibles;
    }

    public void setFechasDisponibles(List<Date> fechasDisponibles) {
        this.fechasDisponibles = fechasDisponibles;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
