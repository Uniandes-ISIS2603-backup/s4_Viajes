/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author n.segura 
 */
@Entity
public class AdministradorEntity extends BaseEntity implements Serializable {
    
    private String nombre;
    private String userName;
    private String contraseña;

   
       
    public String getNombre()
    {
        return nombre;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String pUserName)
    {
        userName= pUserName;
    
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
