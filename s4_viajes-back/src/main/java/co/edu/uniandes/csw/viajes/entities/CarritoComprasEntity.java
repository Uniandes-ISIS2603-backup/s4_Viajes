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
     
    
    private Double costo;
    private String nombre;


  
    @PodamExclude
    @OneToOne
    private UsuarioEntity usuario;
  
    
      /**
     * Devuelve el costo del carrito.
     *
     * @return El costo del carrito
     */
    public double getCosto()
    {
        return costo;
    }
    
      /**
     * Devuelve nombre del carrito.
     *
     * @return El nombre del carrito.
     */
     public String getNombre()
    {
        return nombre;
    }
    
      /**
     * Modifica el nombre del carrito.
     *
     * @param pNombre
     */
     public void setNombre(String pNombre)
    {
        this.nombre= pNombre;
    } 
    
     
    /**
     * Modifica el costo del carrito.
     *
     * @param pCosto
     */
     public void setCosto(Double pCosto)
    {
        this.costo= pCosto;
    } 
    
   
    
}
