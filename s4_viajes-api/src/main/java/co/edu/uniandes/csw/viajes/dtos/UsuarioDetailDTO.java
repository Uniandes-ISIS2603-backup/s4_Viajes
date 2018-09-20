/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable {
 
    //Relación con cero o muchos pagos
    private List<PagoDTO> pagos;

    public UsuarioDetailDTO()
    {
        super();
    }
    
     /**
     * Crea un objeto UsuarioDetailDTO a partir de un objeto UsuarioEntity
     * incluyendo los atributos de UsuarioDTO.
     *
     * @param usuarioEntity Entidad UsuarioEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public UsuarioDetailDTO(UsuarioEntity usuarioEntity) {
        super(usuarioEntity);
        if (usuarioEntity != null) {
            pagos = new ArrayList<>();
           }  
        
        for(PagoEntity entityPagos: usuarioEntity.getPagos())
        {
            pagos.add(new PagoDTO(entityPagos));
            
        }
        
        }
    
    
    /**
     * Convierte un objeto UsuarioDetailDTO a UsuarioEntity incluyendo los
     * atributos de UsuarioDTO.
     *
     * @return Nuevo objeto UsuarioEntity.
     *
     */
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity usuarioEntity = super.toEntity();
        if (pagos != null) {
            List<PagoEntity> pagosEntity = new ArrayList<>();
            for (PagoDTO dtoPago : pagos) {
                pagosEntity.add(dtoPago.toEntity());
            }
            usuarioEntity.setPagos(pagosEntity);
        }
     
        return usuarioEntity;
    }
    
    /**
     * Obtiene la lista de pagos del usuario
     *
     * @return los pagos
     */

    public List<PagoDTO> getPagos() {
        return pagos;
    }
    
    /**
     * Establece la lista de pagos del usuario
     * @param pagos
     */

    public void setPagos(List<PagoDTO> pagos) {
        this.pagos = pagos;
    }

    
    
       @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    }
    
