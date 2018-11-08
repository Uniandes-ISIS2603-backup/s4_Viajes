/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ActividadPersistence;
import co.edu.uniandes.csw.viajes.persistence.AlojamientoPersistence;

import co.edu.uniandes.csw.viajes.persistence.ReservaPersistence;
import co.edu.uniandes.csw.viajes.persistence.TransporteTerrestrePersistence;
import co.edu.uniandes.csw.viajes.persistence.VueloPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
public class ReservaLogic {
      private static final Logger LOGGER = Logger.getLogger(ReservaLogic.class.getName());

    @Inject
    private ReservaPersistence persistence;

    @Inject
    private ActividadPersistence actividadPersistence;
    
    @Inject
    private AlojamientoPersistence alojamientoPersistence;
     
    @Inject
    private TransporteTerrestrePersistence transporteTerrestrePersistence;
      
    @Inject
    private VueloPersistence vueloPersistence;

    /**
     * Guardar un nuevo pago
     *
     * @param reservaEntity La entidad de tipo ReservaEntity del nuevo pago a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException tiene un servicio invalido o ya existe en la
     * persistencia.
     */
    public ReservaEntity createReserva(ReservaEntity reservaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la reserva");
              
        if(reservaEntity==null)
            throw new BusinessLogicException("Error en el formato.");

        if (reservaEntity.getIdActividad()==-1l&&reservaEntity.getIdAlojamiento()==-1l&&
            reservaEntity.getIdTransporteTerrestre()==-1l&&reservaEntity.getIdVuelo()==-1l) 
            throw new BusinessLogicException("La reserva debe tener un servicio asociado");
        
        if(reservaEntity.getIdActividad()!=-1)
        {
            ActividadEntity actividadEntity = actividadPersistence.find(reservaEntity.getIdActividad());
            if (actividadEntity == null) {
                LOGGER.log(Level.SEVERE, "La actividad de la reserva con el id = {0} no existe", reservaEntity.getIdActividad());
                throw new BusinessLogicException("La actividad de la reserva que se desea realizar no existe");
            }
            try {
                reservaEntity.setActividad(actividadEntity);
            } catch (Exception ex) {
                throw new BusinessLogicException(ex.getMessage());
            }
        }
        if(reservaEntity.getIdAlojamiento()!=-1l)
        {
            AlojamientoEntity alojamientoEntity = alojamientoPersistence.find(reservaEntity.getIdAlojamiento());
            if (alojamientoEntity == null) {
                LOGGER.log(Level.SEVERE, "El alojamiento de la reserva con el id = {0} no existe", reservaEntity.getIdAlojamiento());
                throw new BusinessLogicException("El alojamiento de la reserva que se desea realizar no existe");
            }
            try {
                reservaEntity.setAlojamiento(alojamientoEntity);
            } catch (Exception ex) {
                throw new BusinessLogicException(ex.getMessage());
            }
        }
        if(reservaEntity.getIdTransporteTerrestre()!=-1l)
        {
            TransporteTerrestreEntity transporteTerrestreEntity = transporteTerrestrePersistence.find(reservaEntity.getIdTransporteTerrestre());
            if (transporteTerrestreEntity == null) {
                LOGGER.log(Level.SEVERE, "El transporteTerrestre de la reserva con el id = {0} no existe", reservaEntity.getIdTransporteTerrestre());
                throw new BusinessLogicException("El transporteTerrestre de la reserva que se desea realizar no existe");
            }
            try {
                reservaEntity.setTransporteTerrestre(transporteTerrestreEntity);
            } catch (Exception ex) {
                throw new BusinessLogicException(ex.getMessage());
            }
        }
        if(reservaEntity.getIdVuelo()!=-1l)
        {
            VueloEntity vueloEntity = vueloPersistence.find(reservaEntity.getIdVuelo());
            if (vueloEntity == null) {
                LOGGER.log(Level.SEVERE, "El vuelo de la reserva con el id = {0} no existe", reservaEntity.getIdVuelo());
                throw new BusinessLogicException("El vuelo de la reserva que se desea realizar no existe");
            }
            try {
                reservaEntity.setVuelo(vueloEntity);
            } catch (Exception ex) {
                throw new BusinessLogicException(ex.getMessage());
            }
        }
        
         if(reservaEntity.getCantidadPersonas()<=0)
           throw new BusinessLogicException("La cantidad de personas no puede ser negativa");
         
          
         if (reservaEntity.getFechaInicio()==null||reservaEntity.getFechaFin()==null) 
           throw new BusinessLogicException("Las fechas no pueden ser nulas");
         
         if (reservaEntity.getFechaFin().compareTo(reservaEntity.getFechaInicio()) < 0) 
           throw new BusinessLogicException("La fecha inicial no puede ser despues de la final");
            
        reservaEntity = persistence.create(reservaEntity);
        
        LOGGER.log(Level.INFO, "Termina proceso de creación de la reserva");
        return reservaEntity;
    }

    /**
     * Devuelve todos las reservas que hay en la base de datos.
     *
     * @return Lista de entidades de tipo reserva.
     */
    public List<ReservaEntity> getReservas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las resrvas");
        List<ReservaEntity> reservas = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos las resrvas");
        return reservas;
    }

    /**
     * Busca una reserva por ID
     *
     * @param reservaId El id de la reserva a buscar
     * @return La reserva encontrada, null si no lo encuentra.
     */
    public ReservaEntity getReserva(Long reservaId) throws BusinessLogicException, Exception {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la reserva con id = {0}", reservaId);
        ReservaEntity reservaEntity = persistence.find(reservaId);
        if (reservaEntity == null) {
            LOGGER.log(Level.SEVERE, "La reserva con el id = {0} no existe", reservaId);
            throw new BusinessLogicException("La reserva con el id ="+ reservaId+" no existe");
        }
        if(reservaEntity.getIdActividad()!=-1l)
        {
            ActividadEntity actividadEntity = actividadPersistence.find(reservaEntity.getIdActividad());
            reservaEntity.setActividad(actividadEntity);
        }
        if(reservaEntity.getIdAlojamiento()!=-1l)
        {
            AlojamientoEntity alojamientoEntity = alojamientoPersistence.find(reservaEntity.getIdAlojamiento());
            reservaEntity.setAlojamiento(alojamientoEntity);
        }
        if(reservaEntity.getIdTransporteTerrestre()!=-1l)
        {
            TransporteTerrestreEntity transporteTerrestreEntity = transporteTerrestrePersistence.find(reservaEntity.getIdTransporteTerrestre());
            reservaEntity.setTransporteTerrestre(transporteTerrestreEntity);
        }
        if(reservaEntity.getIdVuelo()!=-1l)
        {
            VueloEntity vueloEntity = vueloPersistence.find(reservaEntity.getIdVuelo());
            reservaEntity.setVuelo(vueloEntity);
        }
        else
            throw new BusinessLogicException("La reserva con el id ="+ reservaId+" no tiene ningun servicio asociado");
        LOGGER.log(Level.INFO, "Termina proceso de consultar la reserva con id = {0}", reservaId);
        return reservaEntity;
    }

    /**
     * Actualizar un libro por ID
     *
     * @param reservaId El ID del libro a actualizar
     * @param reservaEntity La entidad del libro con los cambios deseados
     * @return La entidad del libro luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public ReservaEntity updateReserva(Long reservaId, ReservaEntity reservaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el pago con id = {0}", reservaId);
        if(reservaEntity==null)
            throw new BusinessLogicException("Error en el formato.");
        
        if (persistence.find(reservaId) == null) {
            LOGGER.log(Level.SEVERE, "La reserva con el id = {0} no existe", reservaId);
            throw new BusinessLogicException("La reserva con el id ="+ reservaId+" no existe");
        }
        
        if (reservaEntity.getIdActividad()==-1l&&reservaEntity.getIdAlojamiento()==-1l&&
                reservaEntity.getIdTransporteTerrestre()==-1l&&reservaEntity.getIdVuelo()==-1l) {
            throw new BusinessLogicException("La reserva debe tener un servicio asociado");
        }
        if(reservaEntity.getIdActividad()!=-1)
        {
            ActividadEntity actividadEntity = actividadPersistence.find(reservaEntity.getIdActividad());
            if (actividadEntity == null) {
                LOGGER.log(Level.SEVERE, "La actividad de la reserva con el id = {0} no existe", reservaEntity.getIdActividad());
                throw new BusinessLogicException("La actividad de la reserva que se desea realizar no existe");
            }
            try {
                reservaEntity.setActividad(actividadEntity);
            } catch (Exception ex) {
                throw new BusinessLogicException(ex.getMessage());
            }
        }
        if(reservaEntity.getIdAlojamiento()!=-1l)
        {
            AlojamientoEntity alojamientoEntity = alojamientoPersistence.find(reservaEntity.getIdAlojamiento());
            if (alojamientoEntity == null) {
                LOGGER.log(Level.SEVERE, "El alojamiento de la reserva con el id = {0} no existe", reservaEntity.getIdAlojamiento());
                throw new BusinessLogicException("El alojamiento de la reserva que se desea realizar no existe");
            }
            try {
                reservaEntity.setAlojamiento(alojamientoEntity);
            } catch (Exception ex) {
                throw new BusinessLogicException(ex.getMessage());
            }
        }
        if(reservaEntity.getIdTransporteTerrestre()!=-1l)
        {
            TransporteTerrestreEntity transporteTerrestreEntity = transporteTerrestrePersistence.find(reservaEntity.getIdTransporteTerrestre());
            if (transporteTerrestreEntity == null) {
                LOGGER.log(Level.SEVERE, "El transporteTerrestre de la reserva con el id = {0} no existe", reservaEntity.getIdTransporteTerrestre());
                throw new BusinessLogicException("El transporteTerrestre de la reserva que se desea realizar no existe");
            }
            try {
                reservaEntity.setTransporteTerrestre(transporteTerrestreEntity);
            } catch (Exception ex) {
                throw new BusinessLogicException(ex.getMessage());
            }
        }
        if(reservaEntity.getIdVuelo()!=-1l)
        {
            VueloEntity vueloEntity = vueloPersistence.find(reservaEntity.getIdVuelo());
            if (vueloEntity == null) {
                LOGGER.log(Level.SEVERE, "El vuelo de la reserva con el id = {0} no existe", reservaEntity.getIdVuelo());
                throw new BusinessLogicException("El vuelo de la reserva que se desea realizar no existe");
            }
            try {
                reservaEntity.setVuelo(vueloEntity);
            } catch (Exception ex) {
                throw new BusinessLogicException(ex.getMessage());
            }
        }

        
         if(reservaEntity.getCantidadPersonas()<=0)
           throw new BusinessLogicException("La cantidad de personas no puede ser negativa");
         
          
         if (reservaEntity.getFechaInicio()==null||reservaEntity.getFechaFin()==null) 
           throw new BusinessLogicException("Las fechas no pueden ser nulas");
         
         if (reservaEntity.getFechaFin().compareTo(reservaEntity.getFechaInicio()) < 0) 
           throw new BusinessLogicException("La fecha inicial no puede ser despues de la final");
            
        ReservaEntity newEntity = persistence.update(reservaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el pago con id = {0}", reservaEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un pago por ID
     *
     * @param reservaId El ID de la reserva a eliminar
     * @throws BusinessLogicException si el pago...
     */
    public void deleteReserva(Long reservaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la reserva con id = {0}", reservaId);
        if(reservaId == null)
          throw new BusinessLogicException("Identificador de la reserva inexistente.");

        persistence.delete(reservaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la reserva con id = {0}", reservaId);
    }


}
