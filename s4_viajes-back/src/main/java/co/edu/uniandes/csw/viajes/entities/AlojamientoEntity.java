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
 * @author Ymespana
 */
@Entity
public class AlojamientoEntity extends ServicioEntity implements Serializable{

   

    /**
     * Estrellas del alojamiento.
     */
    private Integer estrellas;

    /**
     * Tipo del alojamiento.
     */
    private String tipo;
 
    public AlojamientoEntity(){
        
    }

    
    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
}
