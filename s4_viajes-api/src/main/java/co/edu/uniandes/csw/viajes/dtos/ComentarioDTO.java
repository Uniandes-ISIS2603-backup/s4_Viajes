/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ComentarioEntity;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class ComentarioDTO {
     private int numero;
    private String textoContenido;
    private List multimedia;
    private double calificacion;
    private Calendar fechaPub;
    
    public ComentarioDTO(){};
    
    public ComentarioDTO(ComentarioEntity comentarioEntity)
    {
        if(comentarioEntity!=null)
       {
           this.numero = comentarioEntity.getNumero();
           this.textoContenido = comentarioEntity.getTextoContenido();
           this.multimedia = comentarioEntity.getMultimedia();
           this.calificacion = comentarioEntity.getCalificacion();
           this.fechaPub = comentarioEntity.getFechaPub();
          
       }
    };
    
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
