/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
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
   
    private List<Date> fechas;

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

    public List<Date> getFechas() {
        return fechas;
    }

    public void setFechas(List<Date> fechas) {
        this.fechas = fechas;
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
