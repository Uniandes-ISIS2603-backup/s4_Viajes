/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Esteban Cantor
 */
public class ActividadDTO extends ServicioDTO implements Serializable{
    
  
    private ActividadEntity actividad;
    
    public ActividadDTO(){}
    
    public ActividadDTO(ActividadEntity actividadEntity) {
            super(actividadEntity);  
    }

    public ActividadEntity toEntity() {
        ActividadEntity actividadEntity =(ActividadEntity) super.toEntity();
        return actividadEntity;
    }


    
}
