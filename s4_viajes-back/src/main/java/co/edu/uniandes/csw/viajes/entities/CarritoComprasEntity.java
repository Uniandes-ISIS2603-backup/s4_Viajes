/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class CarritoComprasEntity extends BaseEntity implements Serializable{
    
    private double costo;
    private String nombre;

    private Long carritoId;

    
    @PodamExclude
    @OneToOne
    private UsuarioEntity usuario;
    
    public double getCosto()
    {
        return costo;
    }
    
    public Long getIdCarrito()
    {
        return carritoId;
    }
    
     public void setIdCarrito(Long pId)
    {
        this.carritoId= pId;
    }
   
     public String getNombre()
    {
        return nombre;
    }
    
    
     public void setNombre(String pNombre)
    {
        this.nombre= pNombre;
    } 
     
     
     public void setCosto(Double pCosto)
    {
        this.costo= pCosto;
    } 
    
   
    
}
