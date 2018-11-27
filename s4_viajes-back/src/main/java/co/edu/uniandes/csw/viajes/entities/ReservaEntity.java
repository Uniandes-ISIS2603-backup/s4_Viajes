/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class ReservaEntity extends BaseEntity implements Serializable {
    
    private int cantidadPersonas;
   
    private boolean pagada;
   
//    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
       
//    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;
    
    private double costo;
    
    
    @PodamExclude
    @OneToOne(mappedBy = "reserva", fetch=FetchType.LAZY)  
    private ServicioEntity servicio;
    
    private long idServicio;
    
   


    @PodamExclude
    @OneToOne
    private PagoEntity pago;
    
    @PodamExclude
    @ManyToOne
    private ComboEntity combo;
   
    //-----------------------------------------------------------------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------------------------------------------------------------
    
    public ReservaEntity(){
    }
   
    public ReservaEntity(ServicioEntity servicio,int cantidadPersonas,Date fechaInicio,Date fechaFin){
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        pagada=false;
        this.servicio=servicio;
        this.cantidadPersonas=cantidadPersonas;
        costo=cantidadPersonas*servicio.getCosto();
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
    
    public PagoEntity getPago() {
        return pago;
    }

    public void setPago(PagoEntity pago) {
        this.pago = pago;
    }

    public ServicioEntity getServicio() {
        return servicio;
    }

    public void setServicio(ServicioEntity servicio) {
        this.servicio = servicio;
//         if(servicio!=null)
//            costo=servicio.getCosto()*cantidadPersonas;
    }

    public long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(long idServicio) {
        this.idServicio = idServicio;
    }

   


    
}
