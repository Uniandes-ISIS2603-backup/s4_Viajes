/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class PagoEntity extends BaseEntity implements Serializable  {
    
    private ComboEntity aPagar;
    private String pagoId;

    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

    public PagoEntity(){}
    
    public ComboEntity getaPagar() {
        return aPagar;
    }

    public void setaPagar(ComboEntity aPagar) {
        this.aPagar = aPagar;
    }
    
        public String getPagoId() {
        return pagoId;
    }

    public void setPagoId(String pagoId) {
        this.pagoId = pagoId;
    }
    
    
    
}
