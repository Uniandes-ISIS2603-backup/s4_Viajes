/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Luis Gómez Amado
 */
@Entity
public class MedallaEntity extends BaseEntity implements Serializable{
    

    private String nombre;
    private String rutaImagen;
    private String descripcion;
    public MedallaEntity(){
        
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
    
}