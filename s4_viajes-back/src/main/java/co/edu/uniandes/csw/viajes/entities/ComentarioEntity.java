/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author estudiante
 */
@Entity
public class ComentarioEntity extends BaseEntity implements Serializable {
    private int numero;
    private String textoContenido;
    private List multimedia;
    private double calificacion;
    private Calendar fechaPub;
    
    
    public int getNumero()
    {
        return numero;
    }
    
    public String getTextoContenido()
    {
        return textoContenido;
    }
    
    public List getMultimedia()
    {
        return multimedia;
    }
    
     public double getCalificacion()
    {
        return calificacion;
    } 
    
    public Calendar getFechaPub()
    {
         return fechaPub;
    }
         
     public void setNumero(int pNumero)
    {
        numero = pNumero;
    }

    public void setTextoContenido(String pTextoContenido)
    {
        textoContenido = pTextoContenido;
    }
    
    public void setMultimedia(List pMultimedia)
    {
        multimedia= pMultimedia;
    }
    
     public void setCalificacion(double pCalificacion)
    {
        calificacion = pCalificacion;
    } 
     
     public void setFechaPub(Calendar pFecha)
    {
        fechaPub = pFecha;
    } 
}
