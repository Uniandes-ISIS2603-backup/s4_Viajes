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
 * @author Yeferson Espana
 */
@Stateless
public class ProveedorAlojamientosLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorAlojamientosLogic.class.getName());
    
    @Inject
    private ProveedorPersistence proveedorPersistence; 
    
    @Inject
    private AlojamientoPersistence alojamientoPersistence; 
    
    /**
     * Agregar un alojameinto al proveedor. 
     *
     * @param alojamientosId El id del alojamiento a guardar
     * @param proveedoresId El id del proveedor en la cual se va a guardar el alojamiento.
     * @return El alojamiento creado.
     * @throws BusinessLogicException
     */
    public AlojamientoEntity addAlojamiento(Long alojamientosId, Long proveedoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un alojamiento a un proveedor con id = {0}", proveedoresId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedoresId);
        AlojamientoEntity alojamientoEntity = alojamientoPersistence.find(alojamientosId);
        if(proveedorEntity == null){
            throw new BusinessLogicException("El proveedor no se encuentra registrado: (PALogic) " + proveedoresId);
        }
        if(alojamientoEntity == null){
             throw new BusinessLogicException("El alojamiento no se encuentra registrado: (PALogic) " + alojamientosId);
        }
        alojamientoEntity.setProveedor(proveedorEntity); 
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un alojamiento a un proveedor con id = {0}", proveedoresId);
        return alojamientoEntity; 
    }
    
    /**
     * Retorna todos los alojamientos asociados a un proveedor.
     *
     * @param proveedoresId El ID del proveedor buscado
     * @return La lista de alojamientos del proveedor
     * @throws BusinessLogicException
     */
    public List<AlojamientoEntity> getAlojamientos(Long proveedoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los alojamientos asociados a un proveedor con id = {0}", proveedoresId);
        ProveedorEntity proveedor = proveedorPersistence.find(proveedoresId);
        if(proveedor == null){
            throw new BusinessLogicException("El proveedor no se encuentra registrado, imposible hacer la consulta. (PALogic)"+ proveedoresId);
        }
        return proveedor.getAlojamientos(); 
    }
    
    /**
     * Retorna un alojamiento asociado a un proveedor
     *
     * @param proveedorId El id del proveedor a buscar.
     * @param alojamientosId El id del alojamiento a buscar
     * @return El alojamiento encontrado dentro del proveedor.
     * @throws BusinessLogicException Si el alojamiento no se encuentra.
     */
    public AlojamientoEntity getAlojamiento(Long proveedorId, Long alojamientosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el alojamiento con id = {0} del proveedor con id = " + proveedorId, alojamientosId);
        ProveedorEntity proveedor = proveedorPersistence.find(proveedorId);
        if(proveedor == null){
            throw new BusinessLogicException("El id del proveedor ingresado es invalido: (PALogic)" + proveedorId);
        }
        List<AlojamientoEntity> alojamientos = proveedor.getAlojamientos();
        AlojamientoEntity alojamientoEntity = alojamientoPersistence.find(alojamientosId);
        if(alojamientoEntity == null){
            throw new BusinessLogicException("El alojamiento no está asociado al proveedor. (PALogic)");
        }
        int index = alojamientos.indexOf(alojamientoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el alojamiento con id = {0} del proveedor con id = " + proveedorId, alojamientosId);
        
        return alojamientos.get(index);
    }
    
    /**
     * Remplazar alojamientos de un proveedor.
     *
     * @param alojamientos Lista de alojamientos que serán los del proveedor.
     * @param proveedorId El id del proveedor que se quiere actualizar.
     * @return La lista de alojamientos del proveedor actualizada.
     * @throws BusinessLogicException
     */
    public List<AlojamientoEntity> replaceAlojamientos(Long proveedorId, List<AlojamientoEntity> alojamientos) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        if(proveedorEntity == null){
            throw new BusinessLogicException("El id del proveedor ingresado es invalido: (PALogic)" + proveedorId);
        }
        List<AlojamientoEntity> alojamientoList = alojamientoPersistence.findAll();
        for (AlojamientoEntity alojamiento : alojamientoList) {
            if (alojamientos.contains(alojamiento)) {
                alojamiento.setProveedor(proveedorEntity);
            } else if (alojamiento.getProveedor() != null && alojamiento.getProveedor().equals(proveedorEntity)) {
                alojamiento.setProveedor(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar los alojamientos del proveedor con id = {0}", proveedorId);
        return alojamientos;
    }
}
