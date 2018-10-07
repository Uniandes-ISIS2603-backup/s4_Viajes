/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class PagoEntity extends BaseEntity implements Serializable  {
   
    
    @PodamExclude
    @OneToOne(mappedBy = "pago", fetch=FetchType.LAZY)    
    private ComboEntity aPagar;
    private Long pagoId;
    private boolean pagaConTarjeta;
    private String tarjeta;
    private long idComboAPagar;

    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

    public PagoEntity(){
    }
    
    
    
    public ComboEntity getaPagar() {
        return aPagar;
    }

    public void setaPagar(ComboEntity aPagar) {
        if(aPagar!=null)
            idComboAPagar=aPagar.getComboIdLong();
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        this.aPagar = aPagar;
    }
    
    public Long getPagoId() {
        pagoId=getId();
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

    public long getIdComboAPagar() {
        return idComboAPagar;
    }

    public void setIdComboAPagar(long idComboAPagar) {
        this.idComboAPagar = idComboAPagar;
    }
    
   
    
    
    
}
