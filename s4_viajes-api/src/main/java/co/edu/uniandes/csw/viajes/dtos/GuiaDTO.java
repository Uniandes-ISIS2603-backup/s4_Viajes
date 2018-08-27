/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class GuiaDTO implements Serializable{
    private int edad;
    private String documento;
    private String nombre;
    private int puntuacion;
    private double sueldo;
    private List<String> idiomas;
    private GuiaEntity guia;
    
    public GuiaDTO(){}
    
    public GuiaDTO(GuiaEntity guiaEntity) {
        if (guiaEntity != null) {
            this.edad = guiaEntity.getEdad();
            this.documento = guiaEntity.getDocumento();
            this.nombre = guiaEntity.getNombre();
            this.puntuacion = guiaEntity.getPuntuacion();
            this.sueldo = guiaEntity.getSueldo();
            this.idiomas = guiaEntity.getIdiomasList();
        }
    }
    
    public int getEdad(){return edad;}
    public String getDocumento(){return documento;}
    public String getNombre(){return nombre;}
    public int getPuntuacion(){return puntuacion;}
    public double getSueldo(){return sueldo;}
    public List getIdiomas(){return idiomas;}
    
    public void setEdad(int edad){this.edad = edad;}
    public void setDocumento(String doc){this.documento = doc;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setPuntuacion(int puntuacion){this.puntuacion = puntuacion;}
    public void setSueldo(double sueldo){this.sueldo = sueldo;}
    public void setIdiomas(List idiomas){this.idiomas = idiomas;}
    
    public GuiaEntity toEntity() {
        GuiaEntity guiaEntity = new GuiaEntity();
        guiaEntity.setEdad(this.edad);
        guiaEntity.setDocumento(this.documento);
        guiaEntity.setNombre(this.nombre);
        guiaEntity.setPuntuacion(this.puntuacion);
        guiaEntity.setSueldo(this.sueldo);
        guiaEntity.setIdiomas(this.idiomas);
        return guiaEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
