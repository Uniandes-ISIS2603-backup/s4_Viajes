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
public class AdministradorEntity extends BaseEntity implements Serializable {
    
    private String contraseña;
    private String nombre;
    private String user_name;
    
    public String getNombre()
    {
        return nombre;
    }
    
    public String getUserName()
    {
        return user_name;
    }
    
    public void getUserName(String pUserName)
    {
        user_name= pUserName;
    
    }
      public String getContraseña()
      {
          return contraseña;
      }
      
       public void setNombre(String pNombre)
    {
        nombre= pNombre;
    
    }
       
            
       public void setContraseña(String pContraseña)
    {
        contraseña= pContraseña;
    
    }
      
      
    
}
