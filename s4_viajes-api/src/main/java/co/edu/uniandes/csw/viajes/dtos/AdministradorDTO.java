/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.AdministradorEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author n.segura
 */
public class AdministradorDTO implements Serializable{
    private Long id;
    private String password;
    private String nombre;
    private String userName;
    
    
    //Métodos
    
    
    /**
     * Modifica el identificador de un administrador.
     *
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

     /**
     * Modifica la password de un administrador.
     *
     * @param password
     */
    public void setpassword(String password) {
        this.password = password;
    }
    
    
    
    /**
     * Modifica el nombre de un administrador.
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     /**
     * Modifica el UserName de un usuario.
     *
     * @param userName Nombre de usuario del administrador. 
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

   public AdministradorDTO(){
   
   }
   
   public AdministradorDTO(AdministradorEntity administradorEntity)
   {
    
       if(administradorEntity!=null)
       {
           this.userName = administradorEntity.getUserName();
           this.nombre = administradorEntity.getNombre();
           this.password = administradorEntity.getContraseña();
           this.id = administradorEntity.getId();
          
       }
   }
   
   public String getNombre(){
       return nombre;
   }
   
  
   public String getpassword()
   {
       return password;
   }
   
   public String getUserName()
   {
      return userName;
   }

  

    public AdministradorEntity toEntity() {
        
        AdministradorEntity administradorEntity = new AdministradorEntity();
        administradorEntity.setNombre(this.nombre);
        administradorEntity.setId(this.id);
        administradorEntity.setContraseña(this.password);
        administradorEntity.setUserName(this.userName);
        return administradorEntity;

    }
      
    @Override
    public String toString() {
         return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }    

     
    
}
