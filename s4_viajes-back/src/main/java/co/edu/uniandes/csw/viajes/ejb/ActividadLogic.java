/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ActividadPersistence;
import co.edu.uniandes.csw.viajes.persistence.GuiaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */

@Stateless
public class ActividadLogic extends ServicioLogic{
     private static final Logger LOGGER = Logger.getLogger(ActividadLogic.class.getName());

    @Inject
    private ActividadPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

     @Inject
    private GuiaPersistence guiaPersistence; 
    /**
     * Crea una actividad en la persistencia.
     *
     * @param actividadEntity La entidad que representa la actividad a
     * persistir.
     * @return La entidad de la actividad luego de persistirla.
     * @throws BusinessLogicException Si la editorial a persistir ya existe.
     */
    public ActividadEntity createActividad(ActividadEntity actividadEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la actividad");
        // Verifica la regla de negocio que dice que no puede haber dos actividades con el mismo id
        super.createServicio(actividadEntity);
        // Invoca la persistencia para crear la editorial
        persistence.create(actividadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la actividad");
        return actividadEntity;
    }

    /**
     * Borrar una actividad
     *
     * @param actividadId: id de la actividad a borrar
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */

    public void deleteActividad(Long actividadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el libro con id = {0}", actividadId);
        
        if (!validarActividadExistente(actividadId))
        {
            throw new BusinessLogicException("No existe la actividad a eliminar");
        }
        
        persistence.delete(actividadId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la actividad con id = {0}", actividadId);
    }
    
    public void deleteAll(){
        persistence.deleteAll();
    }

    public ActividadEntity getActividad(Long actividadId) throws BusinessLogicException {
        
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la actividad con id = {0}", actividadId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        
        //PERSISTENCIA
        ActividadEntity actividadEntity = persistence.find(actividadId);
        if (actividadEntity == null) 
            throw new BusinessLogicException("No existe ninguna actividad con id "+actividadId);
        
         for(long idGuia : actividadEntity.getIdsGuias())
            {
               GuiaEntity guia = guiaPersistence.find(idGuia);
               if(guia==null)
                   {
//                           throw new BusinessLogicException("El combo reserva que envio no existe");
                    }
               else
                   actividadEntity.addGuia(guia);
                          
            } 
        LOGGER.log(Level.INFO, "Termina proceso de consultar la actividad con id = {0}", actividadId);
        return actividadEntity;
          
    }
    
 
    
    public ActividadEntity modificarActividad(Long id, ActividadEntity actividadEntity)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la actividad con id = {0}", id);
        if (!validarId(id))
        {
            throw new BusinessLogicException("El id a actualizar es inválido");
        }
        
//        if(!validarNombre(actividadEntity.getNombreActividad()))
//        {
//            throw new BusinessLogicException("El nombre a actualizar es invalido");
//        }
//        
//        if(!validarPuntuacion(actividadEntity.getPuntuacion()))
//        {
//            throw new BusinessLogicException("La puntuacion a actualizar es invalida");
//        }
        
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        //PERSISTENCIA
        ActividadEntity newEntity = persistence.update(actividadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la actividad con id = {0}", actividadEntity.getId());
        return newEntity;
    }
    
      /**
     * Devuelve todos las actividades que hay en la base de datos.
     *
     * @return Lista de actividades de tipo libro.
     */
    public List<ActividadEntity> getActividades() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las actividades");
        List<ActividadEntity> actividades = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos las actividades");
        return actividades;
    }
    
    private boolean validarActividadExistente(Long id)
    {
        return !(persistence.findByIdentificador(id) == null);
    }
    
    private boolean validarId(Long id)
    {
        return !(id == null || id <= 0L);
    }
    
    private boolean validarNombre(String nom)
    {return !(nom == null || nom.isEmpty());}
    
    private boolean validarPuntuacion(int p)
    {return !(p > 10 || p <0);}
    
    
    
    

   
    
}
