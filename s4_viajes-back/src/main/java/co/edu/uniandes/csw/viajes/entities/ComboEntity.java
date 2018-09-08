/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author estudiante
 */
@Entity
public class ComboEntity  extends BaseEntity implements Serializable {
    //-----------------------------------------------------------------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------------------------------------------------------------
  
    private double costo;
    
    private String nombre;
    
    private int dias;
    
    private double horas;

    private int puntuacion;
    
    //-----------------------------------------------------------------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------------------------------------------------------------
        
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
    
}
