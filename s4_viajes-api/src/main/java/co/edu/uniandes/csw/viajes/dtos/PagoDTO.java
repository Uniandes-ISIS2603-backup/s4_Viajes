/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.PagoEntity;

/**
 *
 * @author estudiante
 */
public class PagoDTO {
   
    private ComboDTO aPagar;
    
    /**
     * Constructor vacio.
     */ 
    public PagoDTO(){}

    /**
     * Constructor por defecto.
     * @param pagoEntity Entidad del pago. 
     */ 
    public PagoDTO(PagoEntity pagoEntity){
        if(pagoEntity!=null)
            aPagar=new ComboDTO(pagoEntity.getaPagar());
    }

    public ComboDTO getaPagar() {
        return aPagar;
    }

    public void setaPagar(ComboDTO aPagar) {
        this.aPagar = aPagar;
    }
    
    /**
     * Método para transformar el DTO a una entidad.
     * @return La entidad del DTO asociado.
     */
      public PagoEntity toEntity() 
    {
        PagoEntity pagoEntity = new PagoEntity(); 
        pagoEntity.setaPagar(aPagar.toEntity());
        return pagoEntity; 
    }
}
