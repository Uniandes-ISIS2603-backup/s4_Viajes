/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;


import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import java.io.Serializable;
import java.sql.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
    
    private Long id; 
    
    private ServicioDTO servicio=new ServicioDTO();
    
    private Long idServicio;
    


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
             id=reservaEntity.getId();
             costo=reservaEntity.getCosto();
             if(reservaEntity.getServicio()!=null)
                servicio=new ServicioDTO(reservaEntity.getServicio());
         }
     }
     
     public ReservaEntity toEntity() throws Exception{
         ReservaEntity reservaEntity=new ReservaEntity();
         reservaEntity.setCantidadPersonas(cantidadPersonas);
         reservaEntity.setPagada(pagada);
         reservaEntity.setFechaInicio(fechaInicio);
         reservaEntity.setFechaFin(fechaFin);
         reservaEntity.setIdServicio(idServicio);
         
        
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
        return id;
    }

    public void setIdReserva(Long idReserva) {
        this.id = idReserva;
    }

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public ServicioDTO getServicio() {
        return servicio;
    }

    public void setServicio(ServicioDTO servicio) {
        this.servicio = servicio;
    }

    
     
    @Override
    public String toString() {
         return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    } 
     
}
