/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.MedallaEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Luis Gómez Amado
 */
public class MedallaDTO implements Serializable{
    private String nombre;
    private String rutaImagen;
    private String descripcion;
    private Long id;

   public MedallaDTO(){}
   
   public MedallaDTO(MedallaEntity medallaEntity)
   {
    
       if(medallaEntity!=null)
       {
           this.id = medallaEntity.getId();
           this.nombre = medallaEntity.getNombre();
           this.rutaImagen = medallaEntity.getRutaImagen();
           this.descripcion = medallaEntity.getDescripcion();
          
       }
   }
    
   public Long getId()
   {
       return id;
   }
   
    public String getNombre()
    {
        return nombre;
    }
    
    public String getRutaImagen()
    {
        return rutaImagen;
    }
    
    public String getDescripcion()
    {
        return descripcion;
    }
    
    public void setId(Long pId)
    {
        id = pId;
    }
    
    public void setNombre(String pNombre)
    {
        nombre = pNombre;
    }
    
    public void setRutaImagen(String pRutaImg)
    {
        rutaImagen = pRutaImg;
    }
    
    public void setDescripcion(String pDescripcion)
    {
        descripcion= pDescripcion;
    }

    public MedallaEntity toEntity() {
        MedallaEntity medallaEntity = new MedallaEntity();
        medallaEntity.setNombre(this.nombre);
        medallaEntity.setRutaImagen(this.rutaImagen);
        medallaEntity.setDescripcion(this.descripcion);
        return medallaEntity;
    }
      
    @Override
    public String toString() {
         return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }   
     
    
}

