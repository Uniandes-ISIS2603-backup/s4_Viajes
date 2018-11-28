/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;


import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Esteban Cantor
 */
public class GuiaDTO implements Serializable{
    private int edad;
    private Long documento;
    private String nombre;
    private int puntuacion;
    private double sueldo;
    private String idioma;
    private Long id;
    private int cantidadCalificaciones;

    public GuiaDTO(){}
    
    public GuiaDTO(GuiaEntity guiaEntity) {
        if (guiaEntity != null) {
            
            
            this.edad = guiaEntity.getEdad();
            this.documento = guiaEntity.getDocumento();
            this.nombre = guiaEntity.getNombre();
            this.puntuacion = guiaEntity.getPuntuacion();
            this.sueldo = guiaEntity.getSueldo();
            this.idioma = guiaEntity.getIdioma();
            this.id=guiaEntity.getId();
            this.cantidadCalificaciones=guiaEntity.getCantidadCalificaciones();
        }
    }
    
    public int getEdad(){return edad;}
    public Long getDocumento(){return documento;}
    public String getNombre(){return nombre;}
    public int getPuntuacion(){return puntuacion;}
    public double getSueldo(){return sueldo;}
    public String getIdioma(){return idioma;}
    
    public void setEdad(int edad){this.edad = edad;}
    public void setDocumento(Long doc){this.documento = doc;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setPuntuacion(int puntuacion){this.puntuacion = puntuacion;}
    public void setSueldo(double sueldo){this.sueldo = sueldo;}
    public void setIdioma(String idiomas){this.idioma = idiomas;}

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
    
    
    public GuiaEntity toEntity() {
        GuiaEntity guiaEntity = new GuiaEntity();
        guiaEntity.setEdad(this.edad);
        guiaEntity.setDocumento(this.documento);
        guiaEntity.setNombre(this.nombre);
        guiaEntity.setPuntuacion(this.puntuacion);
        guiaEntity.setSueldo(this.sueldo);
        guiaEntity.setIdioma(this.idioma);
        guiaEntity.setCantidadCalificaciones(cantidadCalificaciones);
        return guiaEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
