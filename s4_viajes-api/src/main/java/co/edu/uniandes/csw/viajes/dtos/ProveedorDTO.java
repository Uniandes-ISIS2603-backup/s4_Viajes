/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import java.io.Serializable;

/**
 *
 * @author Juan Felipe Torres
 */
public class ProveedorDTO implements Serializable
{
    private String username;
    private String contrasena;
    private String nombre;
    private int puntuacion;
    
    public String getUser(){
        return username;
    }
    
    public String getPassword()
    {
        return contrasena;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public int getPuntaje()
    {
        return puntuacion;
    }
    
    public void setUser(String pUser)
    {
        username = pUser;
    }
    
    public void setPassword(String pPassword)
    {
        contrasena = pPassword;
    }
    
    public void setNombre(String pNombre)
    {
        nombre = pNombre;
    }
    
    public void setPuntaje(int pPuntaje)
    {
        puntuacion = pPuntaje;
    }
}
