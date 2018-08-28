/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class UsuarioDTO implements Serializable{
    private int edad;
    private String documento;
    private String nombre;
    private String user_name;
    private Boolean hasLoggedIn;

   public UsuarioDTO(){}
   
   public UsuarioDTO(UsuarioEntity usuarioEntity)
   {
    
       if(usuarioEntity!=null)
       {
           this.documento = usuarioEntity.getDocumento();
           this.edad = usuarioEntity.getEdad();
           this.user_name = usuarioEntity.getUserName();
           this.hasLoggedIn = usuarioEntity.hasLoggedIn();
           this.nombre = usuarioEntity.getNombre();
          
       }
   }
   
   public String getNombre(){
       return nombre;
   }
   
   public int getEdad()
   {
       return edad;
   }
   
   public String getDocumento()
   {
       return documento;
   }
   
   public String getUserName()
   {
      return user_name;
   }

    public Boolean hasLoggedIn()
    {
        return hasLoggedIn;
    }

    public UsuarioDTO toEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
   
     
    
}
