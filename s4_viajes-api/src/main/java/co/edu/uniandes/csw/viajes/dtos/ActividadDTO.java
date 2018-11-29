/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;

import java.io.Serializable;

/**
 *
 * @author Juan Esteban Cantor
 */
public class ActividadDTO extends ServicioDTO implements Serializable{
    
  
    
    public ActividadDTO(){}
    
    public ActividadDTO(ActividadEntity actividadEntity) {
            super(actividadEntity);  
    }

    @Override
    public ActividadEntity toEntity() {
        ActividadEntity actividadEntity =new ActividadEntity();
        super.toEntity(actividadEntity);
        return actividadEntity;
    }


    
}
