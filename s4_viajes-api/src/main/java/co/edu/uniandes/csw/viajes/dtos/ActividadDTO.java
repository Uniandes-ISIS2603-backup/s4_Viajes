/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import java.util.List;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Esteban Cantor
 */
public class ActividadDTO implements Serializable{
    
    private Long id;
    private int costo;
    private boolean ofrece_guia;
    private int puntuacion;
    private int duracion;
    private double latitud;
    private double longitud;
    
    public ActividadDTO(){}
    
    public ActividadDTO(ActividadEntity actividadEntity) {
        if (actividadEntity != null) {
            this.id = actividadEntity.getId();
            this.costo = actividadEntity.getCosto();
            this.ofrece_guia = actividadEntity.isOfreceGuia();
            this.puntuacion = actividadEntity.getPuntuacion();
            this.duracion = actividadEntity.getDuracion();
            this.latitud = actividadEntity.getLatitud();
            this.longitud = actividadEntity.getLongitud();
        }
    }
    
    public int getCosto(){return costo;}
    public Long getIdentificador(){return id;}
    public boolean getOfrece_guia(){return ofrece_guia;}
    public int getPuntuacion(){return puntuacion;}
    public int getDuracion(){return duracion;}
    public double getLatitud(){return latitud;}
    public double getLongitud(){return longitud;}
    
    public void setCosto(int costo){this.costo = costo;}
    public void setIdentificador (Long id){this.id = id;}
    public void setOfrece_guia(boolean og){this.ofrece_guia = og;}
    public void setPuntuacion(int puntuacion){this.puntuacion = puntuacion;}
    public void setDuracion(int duracion){this.duracion = duracion;}
    public void setLatitud(double lat){this.latitud = lat;}
    public void setLongitud(double lon){this.longitud = lon;}
    
    public ActividadEntity toEntity() {
        ActividadEntity actividadEntity = new ActividadEntity();
        actividadEntity.setId(this.id);
        actividadEntity.setCosto(this.costo);
        actividadEntity.setDuracion(this.duracion);
        actividadEntity.setLatitud(this.latitud);
        actividadEntity.setLongitud(this.longitud);
        actividadEntity.setPuntuacion(this.puntuacion);
        actividadEntity.setOfrece_guia(this.ofrece_guia);
        return actividadEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
