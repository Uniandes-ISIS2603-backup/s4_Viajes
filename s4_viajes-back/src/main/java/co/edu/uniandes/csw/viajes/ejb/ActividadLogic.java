/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ActividadPersistence;
import co.edu.uniandes.csw.viajes.persistence.GuiaPersistence;
import co.edu.uniandes.csw.viajes.persistence.ReservaPersistence;
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
     
          @Inject
    private ReservaPersistence reservaPersistence; 
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
        
        ActividadEntity actividad=persistence.findByIdentificador(actividadId);
        if (actividad==null)
        {
            throw new BusinessLogicException("No existe la actividad a eliminar");
        }
        
        
        for(ReservaEntity reserva:reservaPersistence.findAll())
            if(actividadId.compareTo(reserva.getIdServicio())==0)
                throw new BusinessLogicException("No se puede eliminar el servicio pues ya tiene reservas asociasas");
        for(Long idGuia:actividad.getIdsGuias())
            if(guiaPersistence.find(idGuia)!=null)
                guiaPersistence.delete(idGuia);
        
        
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
                    }
               else
                   actividadEntity.addGuia(guia);
                          
            } 
        LOGGER.log(Level.INFO, "Termina proceso de consultar la actividad con id = {0}", actividadId);
        return actividadEntity;
          
    }
    
 
    
    public ActividadEntity modificarActividad(ActividadEntity actividadEntity, double calificacion)throws BusinessLogicException{
     
        double puntuacionActual=actividadEntity.getPuntuacion();
        int cantidad=actividadEntity.getCantidadCalificaciones();
        
        puntuacionActual=((puntuacionActual*(double)cantidad)+calificacion)/(cantidad+1);
        actividadEntity.setCantidadCalificaciones(cantidad+1);
        actividadEntity.setPuntuacion(puntuacionActual);
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
    
    private boolean validarId(Long id)
    {
        return !(id == null || id <= 0L);
    }
    
    
    
    

   
    
}
