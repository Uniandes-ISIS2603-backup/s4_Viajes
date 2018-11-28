/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ServicioEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;

/**
 *
 * @author Juan Diego Barrios
 */
public class TransporteLogic extends ServicioLogic {
 
    
    
    /**
     * Guardar un nuevo servicio. 
     * @param transporteEntity La entidad de tipo ServicioEntity del nuevo servicio a persistir.
     * @return El servicio luego de persistirlo
     * @throws Exception En caso que la entidad sea nula.
     */
    public void createTransporte(TransporteEntity transporteEntity)  throws BusinessLogicException
    {
        super.createServicio(transporteEntity);
       
   
        // Verifica la regla de negocio que El servicio debe tener un nombre
        if(transporteEntity.getOrigen().trim().equals(""))
            throw new BusinessLogicException("El transporte debe tener un origen");
       
         // Verifica la regla de negocio que El servicio debe tener un nombre
        if(transporteEntity.getDestino().trim().equals(""))
            throw new BusinessLogicException("El transporte debe tener un destino");

        // Verifica la regla de negocio que El servicio debe tener un nombre
        if(transporteEntity.getDestino().trim().equalsIgnoreCase(transporteEntity.getOrigen().trim()))
            throw new BusinessLogicException("El destino y el origen del destino no pueden ser el mismo");
        
        // Verifica la regla de negocio que El servicio debe tener latitud y longitud validas
        if(transporteEntity.getLatitudDestino()==null||transporteEntity.getLongitudDestino()==null||(transporteEntity.getLatitudDestino()==0.0&&transporteEntity.getLongitudDestino()==0.0))
            throw new BusinessLogicException("El transporte debe tener la localizacion hacia a donde se dirigue");
        
        if(transporteEntity.getFechasLlegada().size()!=transporteEntity.getFechasDisponibles().size())
            throw new BusinessLogicException("El transporte debe tener la una fecha de llegada por cada fecha de salida");

        for(int i=0;i<transporteEntity.getFechasLlegada().size();i++)
            if(transporteEntity.getFechasLlegada().get(i).compareTo(transporteEntity.getFechasDisponibles().get(i))<0)
                throw new BusinessLogicException("La fecha de llegada no pude ser antes que la de salida, como pasa con fecha salida: "+transporteEntity.getFechasDisponibles().get(i)+" fecha llegada: "+transporteEntity.getFechasLlegada().get(i));

//        return transporteEntity; 
    }    
}
