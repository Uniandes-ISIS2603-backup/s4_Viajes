/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estudiante
 */
public class AlojamientoLogic 
{
    public static final Logger LOGGER = Logger.getLogger(AlojamientoLogic.class.getName());
    
    
     /**
     * Guardar un nuevo alojamiento. 
     * @param alojamientoEntity La entidad de tipo alojamiento del nuevo alojamiento a persistir.
     * @return La entidad luego de persistirla
     * @throws Exception En caso que la entidad sea nula.
     */
    public AlojamientoEntity createAlojamiento(AlojamientoEntity alojamientoEntity) throws Exception
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del alojamiento"); 
        if(alojamientoEntity == null)
            throw new Exception("Error en el formato.");
        LOGGER.log(Level.INFO, "Termina proceso de creación del alojamiento");
        return alojamientoEntity; 
    }
    
    /**
     * Devuelve todos los alojamientos que hay en la base de datos.
     * @return Lista de entidades de tipo alojamiento.
     */
    public List<AlojamientoEntity> getAlojamientos() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los alojamientos.");
        List<AlojamientoEntity> alojamientos = new ArrayList<AlojamientoEntity>(); 
//        List<AlojamientoEntity> alojamientos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los alojamientos");
        return alojamientos;
    }
    
    /**
     * Busca un alojamiento por ID
     * @param alojamientoId El id del alojamiento a buscar
     * @return El alojamiento encontrado, null si no lo encuentra.
     */
    public AlojamientoEntity getAlojamiento(Long alojamientoId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el alojamiento con id = {0}", alojamientoId);
        AlojamientoEntity alojamientoEntity = new AlojamientoEntity();
//        AlojamientoEntity alojamientoEntity = persistence.find(alojamientoId);
        if (alojamientoId == null) {
            LOGGER.log(Level.SEVERE, "El alojamiento con el id = {0} no existe", alojamientoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el alojamiento con id = {0}", alojamientoId);
        return alojamientoEntity;  
    }
    
     /**
     * Actualizar un alojamiento por ID
     * @param alojamientoId El ID del alojamiento a actualizar
     * @param alojamientoEntity La entidad del aoljamiento con los cambios deseados
     * @return La entidad del alojamiento luego de actualizarla
     */
    public AlojamientoEntity updateAlojamiento(Long alojamientoId, AlojamientoEntity alojamientoEntity) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el alojamiento con id = {0}", alojamientoId);
        AlojamientoEntity newEntity = new AlojamientoEntity();
        if(alojamientoId == null)
            return null; 
//        AlojamientoEntity newEntity = persistence.update(alojamientoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el alojamiento con id = {0}", alojamientoEntity.getId());
        return newEntity;
    }
    
     /**
     * Eliminar un alojamiento por ID
     * @param alojamientoId El ID del alojamiento a eliminar
     */
    public void deleteAlojamiento(Long alojamientoId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el alojamiento con id = {0}", alojamientoId);
      if(alojamientoId == null)
          return;
//        persistence.delete(alojamientoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el libro con id = {0}", alojamientoId);
    }  
}
