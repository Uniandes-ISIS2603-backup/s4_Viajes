/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class PagoDTO {
   
    private ComboDTO aPagar;
    private String pagoId;
    
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
        {
            if(pagoEntity.getaPagar()!=null)
                aPagar=new ComboDTO(pagoEntity.getaPagar());
            else
                aPagar=null;
            pagoId=pagoEntity.getPagoId();
        }
    }

    public ComboDTO getaPagar() {
        return aPagar;
    }

    public void setaPagar(ComboDTO aPagar) {
        this.aPagar = aPagar;
    }

    public String getPagoId() {
        return pagoId;
    }

    public void setPagoId(String pagoId) {
        this.pagoId = pagoId;
    }
    
    
    /**
     * Método para transformar el DTO a una entidad.
     * @return La entidad del DTO asociado.
     */
      public PagoEntity toEntity() 
    {
        PagoEntity pagoEntity = new PagoEntity();
        if(aPagar!=null)pagoEntity.setaPagar(aPagar.toEntity());
        else pagoEntity.setaPagar(null);
        pagoEntity.setPagoId(pagoId);
        return pagoEntity; 
    }
      
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
