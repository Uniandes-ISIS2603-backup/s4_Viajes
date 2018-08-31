/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

//import co.edu.uniandes.csw.viajes.entities.CarritoComprasEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class CarritoComprasDTO implements Serializable{
    private String nombre;
    private Double costo;

   public CarritoComprasDTO(){}
   
   //public CarritoComprasDTO(CarritoComprasEntity carritoComprasEntity)
   {
    
       //if(carritoComprasEntity!=null)
       {
         //  this.nombre = carritoComprasEntity.getNombre();
           //this.nombre = carritoComprasEntity.getNombre();
          
       }
   }
   
   public String getNombre(){
       return nombre;
   }
   
 
   
   public Double getCosto()
   {
      return costo;
   }

  

    public CarritoComprasDTO toEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
   
     
    
}
