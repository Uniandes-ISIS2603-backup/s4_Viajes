/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Diego Barrios
 */
public class ComboDTO implements Serializable{ 

    private double costo;
    private String nombre;
    private Long id;
    private int dias;
    private double horas;
    private int puntuacion;

    /**
     * Constructor por vacio.
     */
    public ComboDTO(){}

    /**
     * Constructor por defecto.
     * @param comboEntity Entidad del combo. 
     */ 
     public ComboDTO(ComboEntity comboEntity)
     {
        if (comboEntity != null) {
            costo=comboEntity.getCosto();
            nombre=comboEntity.getNombre();
            dias=comboEntity.getDias();
            horas=comboEntity.getHoras();
            puntuacion=comboEntity.getPuntuacion();
            id=comboEntity.getId();         
        }
         
     }
   
     /**
     * Método para transformar el DTO a una entidad.
     * @return La entidad del DTO asociado.
     */
      public ComboEntity toEntity()
    {
        ComboEntity comboEntity = new ComboEntity(); 
       
        comboEntity.setCosto(costo);
        comboEntity.setNombre(nombre);
        comboEntity.setPuntuacion(puntuacion);
        comboEntity.setDias(dias);
        comboEntity.setHoras(horas);
        
        return comboEntity; 
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

    public Long getId() {
        return id;
    }

    public void setId(Long comboId) {
        this.id = comboId;
    }
    @Override
    public String toString() {
         return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }    
    
   
}
