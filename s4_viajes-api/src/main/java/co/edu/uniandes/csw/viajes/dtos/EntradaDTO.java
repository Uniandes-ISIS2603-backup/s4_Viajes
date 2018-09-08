/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.EntradaEntity;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Luis Gómez Amado
 */
public class EntradaDTO implements Serializable{
    private int numero;
    private String titulo;
    private String textoContenido;
    private List multimedia;
    private double puntuacion;
    private double calificacionComunidad;
    private Calendar fecha;

   public EntradaDTO(){}
   
   public EntradaDTO(EntradaEntity entradaEntity)
   {
    
       if(entradaEntity!=null)
       {
           this.numero = entradaEntity.getNumero();
           this.titulo = entradaEntity.getTitulo();
           this.textoContenido = entradaEntity.getTextoContenido();
           this.multimedia = entradaEntity.getMultimedia();
           this.puntuacion = entradaEntity.getPuntuacion();
           this.calificacionComunidad = entradaEntity.getCalificacionComunidad();
           this.fecha = entradaEntity.getFecha();
          
       }
   }
   
    public int getNumero()
    {
        return numero;
    }
    
    public String getTitulo()
    {
        return titulo;
    }
    
    public String getTextoContenido()
    {
        return textoContenido;
    }
    
    public List getMultimedia()
    {
        return multimedia;
    }
    
     public double getPuntuacion()
    {
        return puntuacion;
    } 
     
     public double getCalificacionComunidad()
    {
         return calificacionComunidad;
    }
     
         public Calendar getFecha()
    {
         return fecha;
    }

    public EntradaEntity toEntity() {
       EntradaEntity entradaEntity = new EntradaEntity();
        entradaEntity.setNumero(this.numero);
        entradaEntity.setTitulo(this.titulo);
        entradaEntity.setTextoContenido(this.textoContenido);
        entradaEntity.setMultimedia(this.multimedia);
        entradaEntity.setPuntuacion(this.puntuacion);
        entradaEntity.setCalificacionComunidad(this.calificacionComunidad);
        entradaEntity.setFecha(this.fecha);
        return entradaEntity;
    }
      
    @Override
    public String toString() {
         return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }   
     
    
}
