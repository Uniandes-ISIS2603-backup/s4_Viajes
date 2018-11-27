/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.ServicioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ActividadPersistence;
import co.edu.uniandes.csw.viajes.persistence.ProveedorPersistence;
import java.util.ArrayList;
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
public class ProveedorActividadLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorActividadLogic.class.getName());

    @Inject
    private ActividadPersistence actividadPersistence;

    @Inject
    private ProveedorPersistence proveedorPersistence;

    /**
     * Agregar una actividad al proveedor
     *
     * @param actividadId El id actividad a guardar
     * @param proveedorId El id de la proveedor en la cual se va a guardar el
     * actividad.
     * @return El actividad agregado al proveedor.
     */
    public ProveedorEntity addActividad(Long actividadId, Long proveedorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una actividad al proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        ActividadEntity actividadEntity = actividadPersistence.find(actividadId);
        if(proveedorEntity==null)
            throw new BusinessLogicException("El proveedor con id "+proveedorId +" no existe");
        if(actividadEntity==null)
            throw new BusinessLogicException("La actividad con id "+actividadId +" no existe");
         for(long idServicio : proveedorEntity.getIdsServicios())
            if(actividadId == idServicio)
                throw new BusinessLogicException("El combo ya tiene asignada una actividad con id " + actividadId +".");
        proveedorEntity.addIdServicio(actividadId);
        proveedorEntity.addServicioFirst(actividadEntity);

        proveedorPersistence.update(proveedorEntity);

        LOGGER.log(Level.INFO, "Termina proceso de agregarle una actividad al proveedor con id = {0}", proveedorId);
        return proveedorEntity;
    }
    
    /**
     * Retorna todas las actividades asociados a un proveedor
     *
     * @param proveedorId El ID del proveedor buscado
     * @return La lista de actividades del proveedor
     */
    public List<ActividadEntity> getActividades(Long proveedorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las actividades asociados al proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        if(proveedorEntity==null)
            throw new BusinessLogicException("El proveedor con id "+proveedorId +" no existe");
        List<ActividadEntity> actividades=new ArrayList<>();
        for(long idServicio : proveedorEntity.getIdsServicios())   
        {
            ActividadEntity actividad = actividadPersistence.find(idServicio);
            if(actividad==null)
               {
                  //No era una actividad
               }
            else
                actividades.add(actividad);
        }
        
        return actividades;
    }
    
    /**
     * Retorna una actividad asociado a una editorial
     *
     * @param proveedorId El id del proveedor a buscar.
     * @param actividadId El id de la actividad a buscar
     * @return La actividad encontrado dentro del proveedor.
     * @throws BusinessLogicException Si la actividad no se encuentra en el proveedor
     */
    public ActividadEntity getActividad(Long proveedorId, Long actividadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la actividad con id = {0} del proveedor con id = " + proveedorId, actividadId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        if(proveedorEntity==null)
            throw new BusinessLogicException("El proveedor con id "+proveedorId +" no existe");
        ActividadEntity actividad=null;
        for(long idServicio : proveedorEntity.getIdsServicios())   
            if(actividadId==idServicio){
                actividad = actividadPersistence.find(actividadId);
                break;
            }      
        if(actividad==null)
            throw new BusinessLogicException("El proveedor con id "+proveedorId +" no tiene la actividad con id "+actividadId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la actividad con id = {0} del proveedor con id = " + proveedorId, actividadId); 
        return actividad;

    }


}
