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
 * @author Ymespana
 */
public class TransporteDetailDTO extends TransporteTerrestreDTO implements Serializable
{
   //-----------------------------------------------------------------------------------------------------------------------
   // Atributos
   //-----------------------------------------------------------------------------------------------------------------------

   
   //-----------------------------------------------------------------------------------------------------------------------
   // Metodos
   //-----------------------------------------------------------------------------------------------------------------------
   
   public TransporteDetailDTO(){
       
   }
   
   public TransporteDetailDTO(TransporteTerrestreEntity transporteEntity)
   {
       super(transporteEntity);
      //INCOMPLETO 
   }
   
   /**
     * Convierte un objeto TransporteDetailDTO a TransporteEntity incluyendo los atributos de TransporteDTO.
     * @return Nueva objeto TransporteEntity.
     */
    @Override
    public TransporteTerrestreEntity toEntity() {
       //INCOMPLETO
       return new TransporteTerrestreEntity();
    }
}
