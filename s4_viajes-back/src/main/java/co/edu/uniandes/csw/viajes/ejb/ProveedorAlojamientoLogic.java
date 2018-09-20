/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.AlojamientoPersistence;
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
public class ProveedorAlojamientoLogic {
 
       
    private static final Logger LOGGER = Logger.getLogger(ProveedorAlojamientoLogic.class.getName());

    @Inject
    private AlojamientoPersistence alojamientoPersistence;

    @Inject
    private ProveedorPersistence proveedorPersistence;

    /**
     * Agregar una Alojamiento al proveedor
     *
     * @param alojamientoId El id Alojamiento a guardar
     * @param proveedorId El id de la proveedor en la cual se va a guardar el
     * Alojamiento.
     * @return El Alojamiento agregado al proveedor.
     */
    public AlojamientoEntity addAlojamiento(Long alojamientoId, Long proveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un alojamiento al proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        AlojamientoEntity alojamientoEntity = alojamientoPersistence.find(alojamientoId);
        alojamientoEntity.setProveedor(proveedorEntity);
        proveedorEntity.getAlojamientos().add(alojamientoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un alojamiento al proveedor con id = {0}", proveedorId);
        return alojamientoEntity;
    }
    
    /**
     * Retorna todas los Alojamientoes asociados a un proveedor
     *
     * @param proveedorId El ID del proveedor buscado
     * @return La lista de Alojamientoes del proveedor
     */
    public List<AlojamientoEntity> getAlojamientos(Long proveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los Alojamientos asociados al proveedor con id = {0}", proveedorId);
        return proveedorPersistence.find(proveedorId).getAlojamientos();
    }
    
    /**
     * Retorna un Alojamiento asociado a una editorial
     *
     * @param proveedorId El id del proveedor a buscar.
     * @param alojamientoId El id de la Alojamiento a buscar
     * @return El Alojamiento encontrado dentro del proveedor.
     * @throws BusinessLogicException Si el Alojamiento no se encuentra en el proveedor
     */
    public AlojamientoEntity getAlojamiento(Long proveedorId, Long alojamientoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la Alojamiento con id = {0} del proveedor con id = " + proveedorId, alojamientoId);
        List<AlojamientoEntity> alojamientos = proveedorPersistence.find(proveedorId).getAlojamientos();
        AlojamientoEntity alojamientoEntity = alojamientoPersistence.find(alojamientoId);
        int index = alojamientos.indexOf(alojamientoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el Alojamiento con id = {0} del proveedor con id = " + proveedorId, alojamientoId);
        if (index >= 0) {
            return alojamientos.get(index);
        }
        throw new BusinessLogicException("El Alojamiento no está asociado al proveedor");
    }

    /**
     * Remplazar Alojamientos de un proveedor
     *
     * @param alojamientos Lista de Alojamientos que serán los del proveedor.
     * @param proveedorId El id del proveedor que se quiere actualizar.
     * @return La lista de Alojamientos actualizada.
     */
    public List<AlojamientoEntity> replaceAlojamientos(Long proveedorId, List<AlojamientoEntity> alojamientos) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        List<AlojamientoEntity> alojamientoList = alojamientoPersistence.findAll();
        for (AlojamientoEntity alojamiento : alojamientoList) {
            if (alojamientos.contains(alojamiento)) {
                alojamiento.setProveedor(proveedorEntity);
            } else if (alojamiento.getProveedor() != null && alojamiento.getProveedor().equals(proveedorEntity)) {
                alojamiento.setProveedor(null);
            }
        }
        proveedorEntity.setAlojamientos(alojamientos);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el proveedor con id = {0}", proveedorId);
        return alojamientos;
    }
}
