/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class ActividadDetailDTO extends ActividadDTO implements Serializable {
    
    private List <GuiaDTO> guias=new ArrayList<>();
    
    private Long idGuia;

    
    public ActividadDetailDTO(){       
        super();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param actividadEntity La entidad de la actividad para transformar a DTO.
     */
    public ActividadDetailDTO(ActividadEntity actividadEntity) {
        super(actividadEntity);
        if (actividadEntity != null) 
            for (GuiaEntity entityGuia : actividadEntity.getGuias()) {
                guias.add(new GuiaDTO(entityGuia));
                
            
        }
    }
    
     /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la actividad para transformar a Entity
     */
    @Override
    public ActividadEntity toEntity() {
        ActividadEntity actividadEntity = super.toEntity();
        if(idGuia!=null&&idGuia!=0l)
             actividadEntity.addIdGuia(idGuia);
       
        return actividadEntity;
    }

    /**
     * Devuelve la lista de guias de la actividad.
     *
     * @return the guides
     */
    public List<GuiaDTO> getGuias() {
        return guias;
    }

    /**
     * Modifica la lista de guias de la actividad.
     *
     * @param guias the guides to set
     */
    public void setGuias(List<GuiaDTO> guias) {
        this.guias =  guias;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
