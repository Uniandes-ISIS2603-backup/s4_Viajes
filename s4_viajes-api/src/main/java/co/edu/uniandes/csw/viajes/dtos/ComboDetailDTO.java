/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class ComboDetailDTO extends ComboDTO implements Serializable{

    
    private List<ReservaDTO> reservas;
    private List<Long> idsReservas;

   
    
    /**
     * Crea un nuevo objeto ComboDetailDTO vacio
     *
     */
    public ComboDetailDTO()
    {
        super();
        reservas=new ArrayList<>();
    }

     /**
     * Crea un objeto ComboDetailDTO a partir de un objeto ComboEntity
     * incluyendo los atributos de ComboDTO.
     *
     * @param comboEntity Entidad ComboEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
     public ComboDetailDTO(ComboEntity comboEntity)
    {
        super(comboEntity);
        reservas=new ArrayList<>();
   
        if (comboEntity != null)
            for(ReservaEntity reserva:comboEntity.getReservas())
                reservas.add(new ReservaDTO(reserva));
     }
    
     /**
     * Convierte un objeto ComboDetailDTO a ComboEntity incluyendo los
     * atributos de ComboDTO.
     *
     * @return Nuevo objeto ComboEntity.
     *
     */
    @Override
    public ComboEntity toEntity() throws Exception{
        ComboEntity comboEntity = super.toEntity();
        if (idsReservas != null) {
           comboEntity.setIdsReservas(idsReservas);
        }
        
        return comboEntity;
    }

    
     /**
     * Obtiene la lista de reservas del combo
     *
     * @return reservas
     */
     public List<ReservaDTO> getReservas() {
        return reservas;
    }

      /**
     * Modifica la lista de reservas para el combo con los que llegan por parametro
     *
     * @param reservas las reservas
     */
    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    
    
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
