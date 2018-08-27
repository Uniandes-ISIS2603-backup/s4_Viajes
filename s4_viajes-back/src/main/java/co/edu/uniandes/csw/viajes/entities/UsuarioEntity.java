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
public class UsuarioEntity extends BaseEntity implements Serializable{
    
    private int edad;
    private String documento;
    private String nombre;
    private String user_name;
    private Boolean hasLoggedIn;
    
    public int getEdad()
    {
        return edad;
    }
    
    public String getDocumento()
    {
        return documento;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public String getUserName()
    {
        return user_name;
    }
    
     public Boolean hasLoggedIn()
    {
        return hasLoggedIn;
    } 
     
     public void setEdad(int pEdad)
    {
        edad = pEdad;
    }
    
    public void setDocumento(String pDocumento)
    {
        documento = pDocumento;
    }
    
    public void setUserName(String pUserName)
    {
        user_name= pUserName;
    }
    
     public void hasLoggedIn(Boolean pHasLoggedIn)
    {
        hasLoggedIn= pHasLoggedIn;
    } 
    
     public void setNombre(String pNombre)
    {
        nombre= pNombre;
    } 
    
}
