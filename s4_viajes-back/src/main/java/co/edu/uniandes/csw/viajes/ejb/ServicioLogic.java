/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.ServicioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;


/**
 *
 * @author Juan Diego Barrios
 */
public class ServicioLogic {
    
   
    
     /**
     * Guardar un nuevo servicio. 
     * @param servicioEntity La entidad de tipo ServicioEntity del nuevo servicio a persistir.
     * @return El servicio luego de persistirlo
     * @throws Exception En caso que la entidad sea nula.
     */
    public void createServicio(ServicioEntity servicioEntity ) throws BusinessLogicException
    {
       
        // Verifica la regla de negocio que El servicio debe tener un nombre
        if(servicioEntity==null)
            throw new BusinessLogicException("El servicio que ha llegado tiene un formato incorrecto");
        
        // Verifica la regla de negocio que El servicio debe tener un nombre
        if(servicioEntity.getNombre()==null||servicioEntity.getNombre().trim().equals(""))
            throw new BusinessLogicException("El servicio debe tener un nombre");
       
        
//         // Verifica la regla de negocio que El servicio debe tener un nombre
//        if(servicioEntity.getImagen()==null||servicioEntity.getImagen().trim().equals(""))
//            throw new BusinessLogicException("El servicio debe tener una imagen");
       
         // Verifica la regla de negocio que El servicio debe tener un costo positivo
        if(servicioEntity.getCosto()<0)
            throw new BusinessLogicException("El servicio no puede tener un costo negativo");

        // Verifica la regla de negocio que El servicio debe tener latitud y longitud validas
        if(servicioEntity.getLatitud()==null||servicioEntity.getLongitud()==null||(servicioEntity.getLatitud()==0.0&&servicioEntity.getLongitud()==0.0))
            throw new BusinessLogicException("El servicio debe tener la localizacion donde se presta");
        
        // Verifica la regla de negocio que El servicio debe tener una duracion positiva
        if(servicioEntity.getDuracion()<0)
            throw new BusinessLogicException("El servicio debe tener una duracion positiva");
        
        if(servicioEntity.getDisponibilidadFecha().size()!=servicioEntity.getFechasDisponibles().size())
            throw new BusinessLogicException("El transporte debe tener una capacidad por cada fecha una fecha de llegada por cada fecha de salida");

        for(Integer disponibilidad:servicioEntity.getDisponibilidadFecha())
            if(disponibilidad<0)
                throw new BusinessLogicException("No puede haber disponibilidad negativa en ninguna fecha");

        // Un servicio nuevo no tiene calificaciones, asi que su puntuacion es -1 (Inexistente)
        servicioEntity.setPuntuacion(-1.0);
        servicioEntity.setCantidadCalificaciones(0);
       
    }
   
   
    
     /**
     * Actualizar un combo por ID
     * @param comboId El ID del combo a actualizar
     * @param comboEntity La entidad del aoljamiento con los cambios deseados
     * @return La entidad del alojamiento luego de actualizarla
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    public ComboEntity updateCombo(Long comboId, ComboEntity comboEntity) throws BusinessLogicException
    {
        if(comboId == null)
            throw new BusinessLogicException("Identificador del combo inexistente.");
        if(comboEntity == null)
            throw new BusinessLogicException("Error en el formato.");
        // Verifica la regla de negocio que El combo debe tener un nombre
        if(comboEntity.getNombre().trim().equals(""))
            throw new BusinessLogicException("El combo debe tener un nombre\"" + comboEntity.getNombre() + "\"");
        // Verifica la regla de negocio que El costo del combo no pueden ser negativo
        if(comboEntity.getCosto()<0)
            throw new BusinessLogicException("El costo del combo no pueden ser negativo \"" + comboEntity.getNombre() + "\"");
        // Verifica la regla de negocio que Los dias de duración del combo no pueden ser 0 ni negativos
        if(comboEntity.getDias()<=0)
            throw new BusinessLogicException("Los dias de duración del combo no pueden ser 0 ni negativos \"" + comboEntity.getNombre() + "\"");
        // Verifica la regla de negocio que Las de duración del combo no pueden ser negativas
        if(comboEntity.getHoras()<0)
            throw new BusinessLogicException("Las de duración del combo no pueden ser negativas \"" + comboEntity.getNombre() + "\"");
        // Verifica la regla de negocio que La puntuación del combo debe estar entre 0 y 5
        if(comboEntity.getPuntuacion()<0||comboEntity.getPuntuacion()>5)
            throw new BusinessLogicException("La puntuación del combo debe estar entre 0 y 5 \"" + comboEntity.getNombre() + "\"");
        
        
        return comboEntity;
    }
    
     /**
     * Eliminar un combo por ID
     * @param comboId El ID del combo a eliminar
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    public void deleteCombo(Long comboId) throws BusinessLogicException
    {
        if(comboId == null)
          throw new BusinessLogicException("Identificador del combo inexistente.");

      
    }
}
