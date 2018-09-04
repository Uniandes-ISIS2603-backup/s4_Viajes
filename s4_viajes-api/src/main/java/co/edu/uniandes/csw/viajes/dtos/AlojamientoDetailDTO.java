/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import java.io.Serializable;

/**
 *
 * @author Ymespana
 */
public class AlojamientoDetailDTO extends AlojamientoDTO implements Serializable {
    //-----------------------------------------------------------------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------------------------------------------------------------
    public AlojamientoDetailDTO() {
        super();
    }

    public AlojamientoDetailDTO(AlojamientoEntity alojamientoEntity) {
        super(alojamientoEntity);
        //INCOMPLETO 
    }

    /**
     * Convierte un objeto AlojamientoDetailDTO a AlojamientoEntity incluyendo
     * los atributos de AlojamientoDTO.
     * @return Nueva objeto AlojamientoEntity.
     */
    @Override
    public AlojamientoEntity toEntity() {
        //INCOMPLETO
        return new AlojamientoEntity();
    }
}
