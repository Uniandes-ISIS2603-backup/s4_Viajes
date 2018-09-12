/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Ymespana
 */
public class TransporteDetailDTO extends TransporteTerrestreDTO implements Serializable {
    //-----------------------------------------------------------------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------------------------------------------------------------

    private ProveedorDTO proveedor;
    //-----------------------------------------------------------------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------------------------------------------------------------

    public TransporteDetailDTO() {
        super();
    }

    public TransporteDetailDTO(TransporteTerrestreEntity transporteEntity) {
        super(transporteEntity);
        if (transporteEntity.getProveedor() != null) {
            this.proveedor = new ProveedorDTO(transporteEntity.getProveedor());
        }
    }

    /**
     * Convierte un objeto TransporteDetailDTO a TransporteEntity incluyendo los
     * atributos de TransporteDTO.
     *
     * @return Nueva objeto TransporteEntity.
     */
    @Override
    public TransporteTerrestreEntity toEntity() {
        TransporteTerrestreEntity entity = super.toEntity();
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
