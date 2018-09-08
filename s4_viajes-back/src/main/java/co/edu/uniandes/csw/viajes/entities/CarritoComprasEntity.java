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
public class CarritoComprasEntity extends BaseEntity implements Serializable{
    
    private double costo;
    private String nombre;
    
    public double getCosto()
    {
        return costo;
    } 
    
    public String getNombre()
    {
        return nombre;
    }
    
    
     public void setNombre(String pNombre)
    {
        this.nombre= pNombre;
    } 
     
     
    // public void setCosto(Double pCosto)
    //{
      //  this.costo= pCosto;
    //} 
    
}
