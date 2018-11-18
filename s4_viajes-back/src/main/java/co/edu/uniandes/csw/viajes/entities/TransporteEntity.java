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

/**
 *
 * @author Juan Diego Barrios
 */
@Entity
public class TransporteEntity extends ServicioEntity implements Serializable{
    
    protected Double latitudDestino;
    protected Double longitudDestino;
    protected List<Date> fechasLlegada=new ArrayList<>();;
    protected String origen;
    protected String destino;
    
    
    
    public TransporteEntity()
    {
        super();
    }
    

    public Double getLatitudDestino() {
        return latitudDestino;
    }

    public void setLatitudDestino(Double latitudDestino) {
        this.latitudDestino = latitudDestino;
    }

    public Double getLongitudDestino() {
        return longitudDestino;
    }

    public void setLongitudDestino(Double longitudDestino) {
        this.longitudDestino = longitudDestino;
    }

    public List<Date> getFechasLlegada() {
        return fechasLlegada;
    }

    public void setFechasLlegada(List<Date> fechasLlegada) {
        this.fechasLlegada = fechasLlegada;
    }
    
    public void addFechaDisponible(Date fechaSalida,Date fechaLlegada,int disponibilidad) {
        if(fechaSalida!=null&&fechaLlegada!=null&&disponibilidad>0){
            super.addFechaDisponible(fechaSalida,disponibilidad);
            fechasLlegada.add(fechaLlegada);
        }
    }
    
    @Override
    public int deleteFechaDisponible(Date fechaDisponible){
        int i=super.deleteFechaDisponible(fechaDisponible);
        if(i!=-1)
            fechasLlegada.remove(i);
        return i;

    }
 

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    
    
    
    
    
}
