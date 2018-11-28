/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ActividadPersistence;
import co.edu.uniandes.csw.viajes.persistence.AlojamientoPersistence;
import co.edu.uniandes.csw.viajes.persistence.PagoPersistence;

import co.edu.uniandes.csw.viajes.persistence.ReservaPersistence;
import co.edu.uniandes.csw.viajes.persistence.TransporteTerrestrePersistence;
import co.edu.uniandes.csw.viajes.persistence.VueloPersistence;
import java.sql.Date;
import java.util.Calendar;
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
    private PagoPersistence pagoPersistence;

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

        if (reservaEntity.getIdServicio()==0l) 
            throw new BusinessLogicException("La reserva debe tener un servicio asociado");
        
        if (reservaEntity.getFechas()==null||reservaEntity.getFechas().isEmpty()) 
           throw new BusinessLogicException("La reserva debe tener al menos una fecha");
         
        Date hoy = new java.sql.Date(Calendar.getInstance().getTime().getTime());
     

        for(Date fecha: reservaEntity.getFechas())
            if (fecha.compareTo(hoy)<0) 
               throw new BusinessLogicException("No puede haber fechas pasadas en la reserva");
       
         if(reservaEntity.getCantidadPersonas()<=0)
           throw new BusinessLogicException("La cantidad de personas no puede ser cero ni negativa");
        
         reservarServicio(reservaEntity, reservaEntity.getCantidadPersonas(), reservaEntity.getFechas());

         reservaEntity.setCosto(reservaEntity.getServicio().getCosto()*reservaEntity.getCantidadPersonas()*reservaEntity.getFechas().size());
          reservaEntity.setPagada(false);
         persistence.create(reservaEntity);
        
        LOGGER.log(Level.INFO, "Termina proceso de creación de la reserva");
        return reservaEntity;
    }

    /**
     * Devuelve todos las reservas que hay en la base de datos.
     *
     * @return Lista de entidades de tipo reserva.
     */
    public List<ReservaEntity> getReservas() throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las resrvas");
        List<ReservaEntity> reservas = persistence.findAll();
        for(ReservaEntity reserva:reservas)
            escogerServicio(reserva);
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
        escogerServicio(reservaEntity);
     
        LOGGER.log(Level.INFO, "Termina proceso de consultar la reserva con id = {0}", reservaId);
        return reservaEntity;
    }

    public void escogerServicio(ReservaEntity reservaEntity) throws BusinessLogicException
    {
        if(reservaEntity.getIdServicio()!=0l)
        {
            VueloEntity vueloEntity = vueloPersistence.find(reservaEntity.getIdServicio());
            ActividadEntity actividadEntity=actividadPersistence.find(reservaEntity.getIdServicio());
            AlojamientoEntity alojamientoEntity=alojamientoPersistence.find(reservaEntity.getIdServicio());
            TransporteTerrestreEntity transporteTerrestreEntity=transporteTerrestrePersistence.find(reservaEntity.getIdServicio());
            if (vueloEntity == null&&actividadEntity == null&&alojamientoEntity == null&&transporteTerrestreEntity == null) {
                LOGGER.log(Level.SEVERE, "El servicio de la reserva con el id = {0} no existe", reservaEntity.getIdServicio());
                throw new BusinessLogicException("El servico de la reserva que se desea realizar no existe");
            }
            boolean yaHay=false;
            if(vueloEntity != null)
            {
                reservaEntity.setServicio(vueloEntity);
                reservaEntity.setTipo(ReservaEntity.VUELO);
                yaHay=true;
            }
            if(actividadEntity != null)
            {
                reservaEntity.setServicio(actividadEntity);
                if(yaHay)
                    throw new BusinessLogicException("Hay mas de un servicio con el mismo id asociado.");
                reservaEntity.setTipo(ReservaEntity.ACTIVIDAD);
                yaHay=true;
            }
            if(alojamientoEntity != null)
            {
                reservaEntity.setServicio(alojamientoEntity);
                if(yaHay)
                  throw new BusinessLogicException("Hay mas de un servicio con el mismo id asociado.");
                reservaEntity.setTipo(ReservaEntity.ALOJAMIENTO);
                yaHay=true;
            }
            if(transporteTerrestreEntity != null)
            {
                reservaEntity.setServicio(transporteTerrestreEntity);
                if(yaHay)
                    throw new BusinessLogicException("Hay mas de un servicio con el mismo id asociado.");
                reservaEntity.setTipo(ReservaEntity.TRANSPORTE_TERRESTRE);
                yaHay=true;
            }            
        }
        else
            throw new BusinessLogicException("La reserva, no tiene un servicio asociado.");            
    }
    
    
    
    public void reservarServicio(ReservaEntity reservaEntity,int cantidadPersonas, List<Date> fechasReserva) throws BusinessLogicException
    {
        if(reservaEntity.getIdServicio()!=0l)
        {
            VueloEntity vueloEntity = vueloPersistence.find(reservaEntity.getIdServicio());
            ActividadEntity actividadEntity=actividadPersistence.find(reservaEntity.getIdServicio());
            AlojamientoEntity alojamientoEntity=alojamientoPersistence.find(reservaEntity.getIdServicio());
            TransporteTerrestreEntity transporteTerrestreEntity=transporteTerrestrePersistence.find(reservaEntity.getIdServicio());
            if (vueloEntity == null&&actividadEntity == null&&alojamientoEntity == null&&transporteTerrestreEntity == null) {
                LOGGER.log(Level.SEVERE, "El servicio de la reserva con el id = {0} no existe", reservaEntity.getIdServicio());
                throw new BusinessLogicException("El servico de la reserva que se desea realizar no existe");
            }
            if(vueloEntity != null)
            {
                List<Date> fechas=vueloEntity.getFechasDisponibles();
                boolean fallo=false;
                for(Date fecha:fechasReserva){
                    boolean ya=false;
                    for(int i=0;i<fechas.size()&&!fallo&&!ya;i++)
                    {
                        Date date= fechas.get(i);
                        if(date.compareTo(fecha)==0)
                        {
                            int cantidad=vueloEntity.getDisponibilidadFecha().get(i)-cantidadPersonas;
                            if(cantidad<0)
                                throw new BusinessLogicException("No hay suficientes cupos disponibles para realizar esta reserva, para la fecha "+fecha.toString()+" solo hay "+vueloEntity.getDisponibilidadFecha().get(i)+" cupos.");
                            vueloEntity.getDisponibilidadFecha().set(i, cantidad);
                            vueloPersistence.update(vueloEntity);
                           ya=true;
                        }
                    }
                    if(!ya)
                        throw new BusinessLogicException("El vuelo no tiene disponible la fecha");

                }
                reservaEntity.setTipo(ReservaEntity.VUELO);
                reservaEntity.setServicio(vueloEntity);
            }
            else if(actividadEntity != null)
            {
                List<Date> fechas=actividadEntity.getFechasDisponibles();
                for(Date fecha:fechasReserva){
                    boolean ya=false;
                    for(int i=0;i<fechas.size()&&!ya;i++)
                    {
                        Date date= fechas.get(i);
                        if(date.compareTo(fecha)==0)
                        {
                            int cantidad=actividadEntity.getDisponibilidadFecha().get(i)-cantidadPersonas;
                            if(cantidad<0)
                                throw new BusinessLogicException("No hay suficientes cupos disponibles para realizar esta reserva, para la fecha "+fecha.toString()+" solo hay "+actividadEntity.getDisponibilidadFecha().get(i)+" cupos.");
                            actividadEntity.getDisponibilidadFecha().set(i, cantidad);
                            actividadPersistence.update(actividadEntity);
                           ya=true;
                        }
                    }
                    if(!ya)
                        throw new BusinessLogicException("La actividad no tiene disponible la fecha");

                }
                reservaEntity.setTipo(ReservaEntity.ACTIVIDAD);
                reservaEntity.setServicio(actividadEntity);
                
            }
            else if(alojamientoEntity != null)
            {
                List<Date> fechas=alojamientoEntity.getFechasDisponibles();
                for(Date fecha:fechasReserva){
                    boolean ya=false;
                    for(int i=0;i<fechas.size()&&!ya;i++)
                    {
                        Date date= fechas.get(i);
                        if(date.compareTo(fecha)==0)
                        {
                            int cantidad=alojamientoEntity.getDisponibilidadFecha().get(i)-cantidadPersonas;
                            if(cantidad<0)
                                throw new BusinessLogicException("No hay suficientes cupos disponibles para realizar esta reserva, para la fecha "+fecha.toString()+" solo hay "+alojamientoEntity.getDisponibilidadFecha().get(i)+" cupos.");
                            alojamientoEntity.getDisponibilidadFecha().set(i, cantidad);
                            alojamientoPersistence.update(alojamientoEntity);
                           ya=true;
                        }
                    }
                    if(!ya)
                        throw new BusinessLogicException("El alojamiento no tiene disponible la fecha");

                }
                reservaEntity.setTipo(ReservaEntity.ALOJAMIENTO);
                reservaEntity.setServicio(alojamientoEntity);
               
            }
            else if(transporteTerrestreEntity != null)
            {
                List<Date> fechas=transporteTerrestreEntity.getFechasDisponibles();
                for(Date fecha:fechasReserva){
                    boolean ya=false;
                    for(int i=0;i<fechas.size()&&!ya;i++)
                    {
                        Date date= fechas.get(i);
                        if(date.compareTo(fecha)==0)
                        {
                            int cantidad=transporteTerrestreEntity.getDisponibilidadFecha().get(i)-cantidadPersonas;
                            if(cantidad<0)
                                throw new BusinessLogicException("No hay suficientes cupos disponibles para realizar esta reserva, para la fecha "+fecha.toString()+" solo hay "+transporteTerrestreEntity.getDisponibilidadFecha().get(i)+" cupos.");
                            transporteTerrestreEntity.getDisponibilidadFecha().set(i, cantidad);
                            transporteTerrestrePersistence.update(transporteTerrestreEntity);
                           ya=true;
                        }
                    }
                    if(!ya)
                        throw new BusinessLogicException("El transporte terrestre no tiene disponible la fecha");

                }
                reservaEntity.setTipo(ReservaEntity.TRANSPORTE_TERRESTRE);
                reservaEntity.setServicio(transporteTerrestreEntity);
                
            }            
        }
        else
            throw new BusinessLogicException("La reserva, no tiene un servicio asociado.");            
    }
    
    
    public void eliminarReservaServicio(ReservaEntity reservaEntity,int cantidadPersonas, List<Date> fechasReserva) throws BusinessLogicException
    {
        if(reservaEntity.getIdServicio()!=0l)
        {
            VueloEntity vueloEntity = vueloPersistence.find(reservaEntity.getIdServicio());
            ActividadEntity actividadEntity=actividadPersistence.find(reservaEntity.getIdServicio());
            AlojamientoEntity alojamientoEntity=alojamientoPersistence.find(reservaEntity.getIdServicio());
            TransporteTerrestreEntity transporteTerrestreEntity=transporteTerrestrePersistence.find(reservaEntity.getIdServicio());
            if (vueloEntity == null&&actividadEntity == null&&alojamientoEntity == null&&transporteTerrestreEntity == null) {
                LOGGER.log(Level.SEVERE, "El servicio de la reserva con el id = {0} no existe", reservaEntity.getIdServicio());
                throw new BusinessLogicException("El servico de la reserva que se desea realizar no existe");
            }
            if(vueloEntity != null)
            {
                List<Date> fechas=vueloEntity.getFechasDisponibles();
                for(Date fecha:fechasReserva)
                {
                    boolean ya=false;
                    for(int i=0;i<fechas.size()&&!ya;i++)
                    {
                        Date date= fechas.get(i);
                        if(date.compareTo(fecha)==0)
                        {
                            int nCantidad=vueloEntity.getDisponibilidadFecha().get(i)+cantidadPersonas;
                            vueloEntity.getDisponibilidadFecha().set(i, nCantidad);
                            vueloPersistence.update(vueloEntity);
                            ya=true;
                        }
                    }
                }   
            }
            else if(actividadEntity != null)
            {
                List<Date> fechas=actividadEntity.getFechasDisponibles();
                for(Date fecha:fechasReserva)
                {
                    boolean ya=false;
                    for(int i=0;i<fechas.size()&&!ya;i++)
                    {
                        Date date= fechas.get(i);
                        if(date.compareTo(fecha)==0)
                        {
                            int nCantidad=actividadEntity.getDisponibilidadFecha().get(i)+cantidadPersonas;
                            actividadEntity.getDisponibilidadFecha().set(i, nCantidad);
                            vueloPersistence.update(vueloEntity);
                            ya=true;
                        }
                    }
                }
      
            }
            else if(alojamientoEntity != null)
            {
                List<Date> fechas=alojamientoEntity.getFechasDisponibles();
                for(Date fecha:fechasReserva)
                {
                    boolean ya=false;
                    for(int i=0;i<fechas.size()&&!ya;i++)
                    {
                        Date date= fechas.get(i);
                        if(date.compareTo(fecha)==0)
                        {
                            int nCantidad=alojamientoEntity.getDisponibilidadFecha().get(i)+cantidadPersonas;
                            alojamientoEntity.getDisponibilidadFecha().set(i, nCantidad);
                            vueloPersistence.update(vueloEntity);
                            ya=true;
                        }
                    }
              }
              
               
            }
            else if(transporteTerrestreEntity != null)
            {
                List<Date> fechas=transporteTerrestreEntity.getFechasDisponibles();
                for(Date fecha:fechasReserva)
                {
                    boolean ya=false;
                    for(int i=0;i<fechas.size()&&!ya;i++)
                    {
                        Date date= fechas.get(i);
                        if(date.compareTo(fecha)==0)
                        {
                            int nCantidad=transporteTerrestreEntity.getDisponibilidadFecha().get(i)+cantidadPersonas;
                            transporteTerrestreEntity.getDisponibilidadFecha().set(i, nCantidad);
                            vueloPersistence.update(vueloEntity);
                            ya=true;
                        }
                    }    
                }               
            }            
        }
        else
            throw new BusinessLogicException("La reserva, no tiene un servicio asociado.");            
    }
    /**
     * Actualizar un libro por ID
     *
     * @param reservaId El ID del libro a actualizar
     * @param pago
     * @param reservaEntity La entidad del libro con los cambios deseados
     * @return La entidad del libro luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public ReservaEntity pagarReserva(Long reservaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el pago con id = {0}", reservaId);
        

        ReservaEntity reserva=persistence.find(reservaId);
        if(reserva==null)
            throw new BusinessLogicException("La reserva no existe");
        if(reserva.isPagada())
            throw new BusinessLogicException("La reserva ya habia sido pagada");
       
        escogerServicio(reserva);
        
        reserva.setPagada(true);
   
        persistence.update(reserva);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el pago con id = {0}", reserva.getId());
        return reserva;
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
        ReservaEntity reserva=persistence.find(reservaId);
        if(reserva == null)
          throw new BusinessLogicException("La reserva no existe.");
       boolean esPasada=true;
        Date hoy = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        for(Date fecha:reserva.getFechas())
            if(hoy.compareTo(fecha)<0)
            {
                esPasada=false;
                break;
            }
            
        
        if(reserva.isPagada()&&!esPasada)
            throw new BusinessLogicException("No se puede eliminar, la reserva no se ha completado y esta pagada, comunicate la empresa para más informacion.");

        eliminarReservaServicio(reserva, reserva.getCantidadPersonas(), reserva.getFechas());
        persistence.delete(reservaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la reserva con id = {0}", reservaId);
    }


}
