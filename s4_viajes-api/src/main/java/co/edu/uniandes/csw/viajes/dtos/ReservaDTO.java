/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
public class ReservaDTO implements Serializable{
    private int cantidadPersonas;
   
    private boolean pagada;
   
    private Date fechaInicio;
   
    private Date fechaFin;
    
    private double costo;
    
    private Long idReserva; 
    
    private VueloDTO vuelo;
    
    private AlojamientoDTO alojamiento;

    private ActividadDTO actividad;
    
    private TransporteTerrestreDTO transporteTerrestre;
    
    private long idVuelo=-1l;
      
    private long idAlojamiento=-1l;

    private long idActividad=-1l;

    private long idTransporteTerrestre=-1l;

    /**
     * Constructor por vacio.
     */
    public ReservaDTO(){}
    
    /**
     * Constructor por defecto.
     * @param reservaEntity Entidad de la reserva. 
     */ 
     public ReservaDTO(ReservaEntity reservaEntity)
     {
         if(reservaEntity!=null)
         {
             cantidadPersonas=reservaEntity.getCantidadPersonas();
             pagada=reservaEntity.isPagada();
             fechaInicio=reservaEntity.getFechaInicio();
             fechaFin=reservaEntity.getFechaFin();
             idReserva=reservaEntity.getId();
             costo=reservaEntity.getCosto();
             idActividad=reservaEntity.getIdActividad();
             idAlojamiento=reservaEntity.getIdAlojamiento();
             idTransporteTerrestre=reservaEntity.getIdTransporteTerrestre();
             idVuelo=reservaEntity.getIdVuelo();
             if(reservaEntity.getVuelo()!=null)
                vuelo=new VueloDTO(reservaEntity.getVuelo());
             else if(reservaEntity.getAlojamiento()!=null)
                alojamiento=new AlojamientoDTO(reservaEntity.getAlojamiento());
             else if(reservaEntity.getTransporteTerrestre()!=null)
                transporteTerrestre=new TransporteTerrestreDTO(reservaEntity.getTransporteTerrestre());
             else if(reservaEntity.getActividad()!=null)
                actividad=new ActividadDTO(reservaEntity.getActividad());
         }
     }
     
     public ReservaEntity toEntity() throws Exception{
         ReservaEntity reservaEntity=new ReservaEntity();
         reservaEntity.setCantidadPersonas(cantidadPersonas);
         reservaEntity.setPagada(pagada);
         reservaEntity.setFechaInicio(fechaInicio);
         reservaEntity.setFechaFin(fechaFin);
         reservaEntity.setIdActividad(idActividad);
         reservaEntity.setIdAlojamiento(idAlojamiento);
         reservaEntity.setIdTransporteTerrestre(idTransporteTerrestre);
         reservaEntity.setIdVuelo(idVuelo);
         if(vuelo!=null)
             reservaEntity.setVuelo(vuelo.toEntity());
         else if(alojamiento!=null)
             reservaEntity.setAlojamiento(alojamiento.toEntity());
         else if(transporteTerrestre!=null)
             reservaEntity.setTransporteTerrestre(transporteTerrestre.toEntity());
         else if(actividad!=null)
             reservaEntity.setActividad(actividad.toEntity());
        
         return reservaEntity;
     }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public VueloDTO getVuelo() {
        return vuelo;
    }

    public void setVuelo(VueloDTO vuelo) {
        this.vuelo = vuelo;
    }

    public AlojamientoDTO getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(AlojamientoDTO alojamiento) {
        this.alojamiento = alojamiento;
    }

    public ActividadDTO getActividad() {
        return actividad;
    }

    public void setActividad(ActividadDTO actividad) {
        this.actividad = actividad;
    }

    public TransporteTerrestreDTO getTransporteTerrestre() {
        return transporteTerrestre;
    }

    public void setTransporteTerrestre(TransporteTerrestreDTO transporteTerrestre) {
        this.transporteTerrestre = transporteTerrestre;
    }

    public long getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(long idVuelo) {
        this.idVuelo = idVuelo;
    }

    public long getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(long idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(long idActividad) {
        this.idActividad = idActividad;
    }

    public long getIdTransporteTerrestre() {
        return idTransporteTerrestre;
    }

    public void setIdTransporteTerrestre(long idTransporteTerrestre) {
        this.idTransporteTerrestre = idTransporteTerrestre;
    }
     
    
    
    
     
     
     
     
    @Override
    public String toString() {
         return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    } 
     
}
