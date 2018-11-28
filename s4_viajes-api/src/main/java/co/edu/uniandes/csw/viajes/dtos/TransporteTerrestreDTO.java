/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import java.io.Serializable;

/**
 *
 * @author ym.espana
 */
public class TransporteTerrestreDTO extends TransporteDTO implements Serializable {
   

    //-----------------------------------------------------------------------------------------------------------------------
    //Metodos
    //-----------------------------------------------------------------------------------------------------------------------
   
    /**
     * Constructor defecto.
     */
     public TransporteTerrestreDTO() {

    }
    /**
     * Constructor por defecto.
     *
     * @param transporteEntity Entidad del alojamiento.
     */
    public TransporteTerrestreDTO(TransporteTerrestreEntity transporteEntity) {
        super(transporteEntity);
    }

   

    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del alojamiento asociado.
     */
    public TransporteTerrestreEntity toEntity() {
       
        TransporteTerrestreEntity transporteTerrestreEntity=new TransporteTerrestreEntity();
        super.toEntity(transporteTerrestreEntity);
        return transporteTerrestreEntity;
    }

    

}
