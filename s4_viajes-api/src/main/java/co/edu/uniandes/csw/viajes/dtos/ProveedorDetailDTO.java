/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.ServicioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que implementa el ProveedorDetailDTO
 * 
 * @author jf.torresp
 */
public class ProveedorDetailDTO extends ProveedorDTO implements Serializable{
    
    
    private List<ServicioDTO> servicios=new ArrayList<>();

    private Long idServicio;
    
    /**
     * Crea un nuevo objeto ProveedorDetailDTO vacio
     *
     */
    public ProveedorDetailDTO()
    {
        super();
    }
    
        /**
     * Crea un objeto ProveedorDetailDTO a partir de un objeto ProveedorEntity
     * incluyendo los atributos de ProveedorDTO.
     *
     * @param proveedorEntity Entidad ProveedorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
     public ProveedorDetailDTO(ProveedorEntity proveedorEntity)
    {
        super(proveedorEntity);
        
        if (proveedorEntity != null) 
            for(ServicioEntity servicio:proveedorEntity.getServicios())
                servicios.add(new ServicioDTO(servicio));
                   
     }
     
    /**
     * Convierte un objeto ProveedorDetailDTO a ProveedorEntity incluyendo los
     * atributos de ProveedorDTO.
     *
     * @return Nuevo objeto ProveedorEntity.
     *
     */
    @Override
    public ProveedorEntity toEntity() {
        ProveedorEntity proveedorEntity = super.toEntity();
        if(idServicio!=null&&idServicio!=0l)
             proveedorEntity.addIdServicio(idServicio);
        return proveedorEntity;
    }

    public List<ServicioDTO> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioDTO> servicios) {
        this.servicios = servicios;
    }
    
    
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }    
}
