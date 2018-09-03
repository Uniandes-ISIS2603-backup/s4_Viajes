/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

//import co.edu.uniandes.csw.viajes.entities.CarritoComprasEntity;
import co.edu.uniandes.csw.viajes.entities.CarritoComprasEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class CarritoComprasDTO implements Serializable{
    private String nombre;
    private Double costo;

   public CarritoComprasDTO(){}
   
 public CarritoComprasDTO(CarritoComprasEntity carritoEntity) {
        if (carritoEntity != null) {
          
            this.costo = carritoEntity.getCosto();
            this.nombre = carritoEntity.getNombre();
            
        }
    }
   
   public String getNombre(){
       return nombre;
   }
   
 
   
   public Double getCosto()
   {
      return costo;
   }

  

    public CarritoComprasEntity toEntity() {
          CarritoComprasEntity carritoEntity = new CarritoComprasEntity();
        carritoEntity.setCosto(this.costo);
        carritoEntity.setNombre(this.nombre);
       return carritoEntity; 
    }
      
    public void setCosto(double pCosto) {
        costo = pCosto;
    }
    
    
     public void setNombre(String pNombre) {
        nombre = pNombre;
    }
    
    
    
    @Override
    public String toString() {
         return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }    
     
    
}
