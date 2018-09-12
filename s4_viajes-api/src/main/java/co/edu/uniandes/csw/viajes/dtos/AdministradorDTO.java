/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.AdministradorEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class AdministradorDTO implements Serializable{
    private String contraseña;
    private String nombre;
    private String userName;

   public AdministradorDTO(){}
   
   public AdministradorDTO(AdministradorEntity administradorEntity)
   {
    
       if(administradorEntity!=null)
       {
           this.contraseña = administradorEntity.getContraseña();
           this.userName = administradorEntity.getUserName();
           this.nombre = administradorEntity.getNombre();
          
       }
   }
   
   public String getNombre(){
       return nombre;
   }
   
  
   public String getContraseña()
   {
       return contraseña;
   }
   
   public String getUserName()
   {
      return userName;
   }

  

    public AdministradorEntity toEntity() {
        
          AdministradorEntity administradorEntity = new AdministradorEntity();
        administradorEntity.setNombre(this.nombre);
        administradorEntity.setContraseña(this.contraseña);
        administradorEntity.setUserName(this.userName);
 

       
        
        return administradorEntity;

    }
      
    @Override
    public String toString() {
         return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }    

     
    
}
