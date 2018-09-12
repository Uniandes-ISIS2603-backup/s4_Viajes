/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Ymespana
 */
public class AlojamientoDetailDTO extends AlojamientoDTO implements Serializable {
    //-----------------------------------------------------------------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------------------------------------------------------------

    private ProveedorDTO proveedor;

    //-----------------------------------------------------------------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------------------------------------------------------------
    public AlojamientoDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param alojamientoEntity La entidad de la cual se construye el DTO
     */
    public AlojamientoDetailDTO(AlojamientoEntity alojamientoEntity) {
        super(alojamientoEntity);
        if (alojamientoEntity.getProveedor() != null) {
            this.proveedor = new ProveedorDTO(alojamientoEntity.getProveedor());
        }
    }

    /**
     * Convierte un objeto AlojamientoDetailDTO a AlojamientoEntity incluyendo
     * los atributos de AlojamientoDTO.
     *
     * @return Nueva objeto AlojamientoEntity.
     */
    @Override
    public AlojamientoEntity toEntity() {
        AlojamientoEntity entity = super.toEntity();
        if (getProveedor() != null) {
            entity.setProveedor(getProveedor().toEntity());
        }
        return entity;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
