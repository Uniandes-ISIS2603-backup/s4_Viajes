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
 * @author jecantorm
 */

@Entity
public class ActividadEntity extends BaseEntity implements Serializable {
    
    private int costo;
    
    private boolean ofrece_guia;
    
    private int puntuacion;
    
    private int duracion;
    
    private double latitud;
    
    private double longitud;
    
    
    public int getCosto()
    {
        return costo;
    }
    
    public boolean getOfreceGuia()
    {
        return ofrece_guia;
    }
    
    public int getPuntuacion()
    {
        return puntuacion;
    }
    
    public int getDuracion()
    {
        return duracion;
    }
    
    public double getLatitud()
    {
        return latitud;
    }
    
    public double getLongitud()
    {
        return longitud;
    }
    
    public void setCosto(int pCosto){
        costo = pCosto;
    }
    public void setOfrece_guia(boolean pOfguia){
        ofrece_guia = pOfguia;
    }
    public void setPuntuacion(int pPuntuacion)
    {
        puntuacion = pPuntuacion;
    }
    public void setDuracion(int pDuracion)
    {
        duracion = pDuracion;
    }
    public void setLatitud(double pLatitud)
            {
                latitud = pLatitud;
            }
    public void setLongitud(double pLongitud)
    {
        longitud = pLongitud;
    }
}
