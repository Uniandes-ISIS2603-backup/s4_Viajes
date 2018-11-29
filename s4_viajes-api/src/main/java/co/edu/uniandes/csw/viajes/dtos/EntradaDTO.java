/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.EntradaEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Luis Gómez Amado
 */
public class EntradaDTO implements Serializable{
    private Long id;
    private String titulo;
    private String textoContenido;
    private List<String> multimedia;
    private double puntuacion;
    private double calificacionComunidad;
    private Date fecha;
    private Long idCombo;
    private ComboDTO combo=new ComboDTO();
  

   public EntradaDTO(){}
   
   public EntradaDTO(EntradaEntity entradaEntity)
   {
    
       if(entradaEntity!=null)
       {
           this.id = entradaEntity.getId();
           this.titulo = entradaEntity.getTitulo();
           this.textoContenido = entradaEntity.getTextoContenido();
           this.multimedia = entradaEntity.getMultimedia();
           this.puntuacion = entradaEntity.getPuntuacion();
           this.calificacionComunidad = entradaEntity.getCalificacionComunidad();
           this.fecha = entradaEntity.getFecha();
           if(entradaEntity.getCombo()!=null)
               combo=new ComboDTO(entradaEntity.getCombo());
       }
   }
    
   public Long getId()
   {
       return id;
   }
   
    public String getTitulo()
    {
        return titulo;
    }
    
    public String getTextoContenido()
    {
        return textoContenido;
    }
    
    public List<String> getMultimedia()
    {
        return multimedia;
    }
    
     public double getPuntuacion()
    {
        return puntuacion;
    } 
     
     public ComboDTO getCombo()
     {
         return combo;
     }
     
     public double getCalificacionComunidad()
    {
         return calificacionComunidad;
    }
     
         public Date getFecha()
    {
         return fecha;
    }
         
    public void setId(Long pId)
    {
        id = pId;
    }
    
    public void setTitulo(String pTitulo)
    {
        titulo = pTitulo;
    }
    
    public void setTextoContenido(String pTextoContenido)
    {
        textoContenido = pTextoContenido;
    }
    
    public void setMultimedia(List<String> pMultimedia)
    {
        multimedia= pMultimedia;
    }
    
     public void setPuntuacion(double pPuntuacion)
    {
        puntuacion = pPuntuacion;
    } 
    
     public void setCalificacionComunidad(double pCalifComunidad)
    {
        calificacionComunidad = pCalifComunidad;
    } 
     
     public void setFecha(Date pFecha)
    {
        fecha = pFecha;
    } 

    public Long getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(Long idCombo) {
        this.idCombo = idCombo;
    }
     

    public EntradaEntity toEntity() {
       EntradaEntity entradaEntity = new EntradaEntity();
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
