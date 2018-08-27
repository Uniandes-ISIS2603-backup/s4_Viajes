/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class TransporteTerrestreEntity extends BaseEntity implements Serializable{
    //-----------------------------------------------------------------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------------------------------------------------------------
  
    public static final Double COSTO_MILLA_LIMOSINA = 123.0; 
    
    public static final Double COSTO_MILLA_UBER = 15.0; 
    
    private Double costo;
   
    private String destino; 
   
    private Long latitudOrigen;

    private Long longitudOrigen;
    
    private Long latitudDestino;

    private Long longitudDestino;

    private Integer numeroDias; 
   
    private Integer numeroHoras; 

    private Integer puntuacion; 

    //-----------------------------------------------------------------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------------------------------------------------------------

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Long getLatitudOrigen() {
        return latitudOrigen;
    }

    public void setLatitudOrigen(Long latitudOrigen) {
        this.latitudOrigen = latitudOrigen;
    }

    public Long getLongitudOrigen() {
        return longitudOrigen;
    }

    public void setLongitudOrigen(Long longitudOrigen) {
        this.longitudOrigen = longitudOrigen;
    }

    public Long getLatitudDestino() {
        return latitudDestino;
    }

    public void setLatitudDestino(Long latitudDestino) {
        this.latitudDestino = latitudDestino;
    }

    public Long getLongitudDestino() {
        return longitudDestino;
    }

    public void setLongitudDestino(Long longitudDestino) {
        this.longitudDestino = longitudDestino;
    }

    public Integer getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(Integer numeroDias) {
        this.numeroDias = numeroDias;
    }

    public Integer getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(Integer numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }
}
