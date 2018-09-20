/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ActividadPersistence;
import co.edu.uniandes.csw.viajes.persistence.ProveedorPersistence;
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
    public ActividadEntity addActividad(Long actividadId, Long proveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una actividad al proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        ActividadEntity actividadEntity = actividadPersistence.find(actividadId);
        actividadEntity.setProveedor(proveedorEntity);
        proveedorEntity.getActividades().add(actividadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una actividad al proveedor con id = {0}", proveedorId);
        return actividadEntity;
    }
    
    /**
     * Retorna todas las actividades asociados a un proveedor
     *
     * @param proveedorId El ID del proveedor buscado
     * @return La lista de actividades del proveedor
     */
    public List<ActividadEntity> getActividades(Long proveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las actividades asociados al proveedor con id = {0}", proveedorId);
        return proveedorPersistence.find(proveedorId).getActividades();
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
        List<ActividadEntity> actividades = proveedorPersistence.find(proveedorId).getActividades();
        ActividadEntity ActividadEntity = actividadPersistence.find(actividadId);
        int index = actividades.indexOf(ActividadEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la actividad con id = {0} del proveedor con id = " + proveedorId, actividadId);
        if (index >= 0) {
            return actividades.get(index);
        }
        throw new BusinessLogicException("La actividad no está asociada al proveedor");
    }

    /**
     * Remplazar actividades de un proveedor
     *
     * @param actividades Lista de actividades que serán los del proveedor.
     * @param proveedorId El id del proveedor que se quiere actualizar.
     * @return La lista de actividades actualizada.
     */
    public List<ActividadEntity> replaceActividades(Long proveedorId, List<ActividadEntity> actividades) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        List<ActividadEntity> actividadList = actividadPersistence.findAll();
        for (ActividadEntity actividad : actividadList) {
            if (actividades.contains(actividad)) {
                actividad.setProveedor(proveedorEntity);
            } else if (actividad.getProveedor() != null && actividad.getProveedor().equals(proveedorEntity)) {
                actividad.setProveedor(null);
            }
        }
        proveedorEntity.setActividades(actividades);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el proveedor con id = {0}", proveedorId);
        return actividades;
    }
}
