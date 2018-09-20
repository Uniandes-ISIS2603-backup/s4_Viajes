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
    private Long pagoId;
    private boolean pagaConTarjeta;
    private String tarjeta;
    
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
            pagaConTarjeta=pagoEntity.isPagaConTarjeta();
            tarjeta=pagoEntity.getTarjeta();
        }
    }

    public ComboDTO getaPagar() {
        return aPagar;
    }

    public void setaPagar(ComboDTO aPagar) {
        this.aPagar = aPagar;
    }

    public Long getPagoId() {
        return pagoId;
    }

    public void setPagoId(Long pagoId) {
        this.pagoId = pagoId;
    }

    public boolean isPagaConTarjeta() {
        return pagaConTarjeta;
    }

    public void setPagaConTarjeta(boolean pagaConTarjeta) {
        this.pagaConTarjeta = pagaConTarjeta;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
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
//        pagoEntity.setPagoId(pagoId);
        pagoEntity.setPagaConTarjeta(pagaConTarjeta);
        pagoEntity.setTarjeta(tarjeta);
        return pagoEntity; 
    }
      
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
