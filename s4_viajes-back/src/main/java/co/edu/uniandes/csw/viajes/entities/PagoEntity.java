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
    private ReservaEntity aPagar;
    private String tarjeta;
    private long idReservaAPagar;

    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

    public PagoEntity(){
    }
    
    
    
    public ReservaEntity getaPagar() {
        return aPagar;
    }

    public void setaPagar(ReservaEntity aPagar) {
        if(aPagar!=null)
            idReservaAPagar=aPagar.getId();
        this.aPagar = aPagar;
    }


    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public long getIdReservaAPagar() {
        return idReservaAPagar;
    }

    public void setIdReservaAPagar(long idComboAPagar) {
        this.idReservaAPagar = idComboAPagar;
    }
    
   
    
    
    
}
