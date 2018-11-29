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
import java.util.ArrayList;
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
    
    private static final String PROVEEDOR = "El proveedor con id ";
    
    private static final String NO_EXISTE = " no existe";
    
    /**
     * Agregar un alojameinto al proveedor. 
     *
     * @param alojamientosId El id del alojamiento a guardar
     * @param proveedoresId El id del proveedor en la cual se va a guardar el alojamiento.
     * @return El alojamiento creado.
     * @throws BusinessLogicException
     */
    public ProveedorEntity addAlojamiento(Long alojamientosId, Long proveedorId) throws BusinessLogicException {
        
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una actividad al proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        AlojamientoEntity alojamientoEntity = alojamientoPersistence.find(alojamientosId);
        if(proveedorEntity==null)
            throw new BusinessLogicException(PROVEEDOR + proveedorId + NO_EXISTE);
        if(alojamientoEntity==null)
            throw new BusinessLogicException("EL alojamiento con id "+alojamientosId +" no existe");

         for(long idServicio : proveedorEntity.getIdsServicios())
            if(alojamientosId == idServicio)
                throw new BusinessLogicException("El combo ya tiene asignado un alojamiento con id " + alojamientosId +".");
           
        proveedorEntity.addIdServicio(alojamientosId);
        proveedorEntity.addServicioFirst(alojamientoEntity);

        proveedorPersistence.update(proveedorEntity);

        LOGGER.log(Level.INFO, "Termina proceso de agregarle una actividad al proveedor con id = {0}", proveedorId);
        return proveedorEntity;
    }
    
    /**
     * Retorna todos los alojamientos asociados a un proveedor.
     *
     * @param proveedoresId El ID del proveedor buscado
     * @return La lista de alojamientos del proveedor
     * @throws BusinessLogicException
     */
    public List<AlojamientoEntity> getAlojamientos(Long proveedorId) throws BusinessLogicException {

        
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los alojamientos asociados al proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        if(proveedorEntity==null)
            throw new BusinessLogicException(PROVEEDOR + proveedorId + NO_EXISTE);
        List<AlojamientoEntity> alojamientos=new ArrayList<>();
        for(long idServicio : proveedorEntity.getIdsServicios())   
        {
            AlojamientoEntity alojamiento = alojamientoPersistence.find(idServicio);
            if(alojamiento==null)
               {
                  //No era un alojamiento
               }
            else
                alojamientos.add(alojamiento);
        }
        
        return alojamientos;
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

        
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la actividad con id = {0} del proveedor con id = " + proveedorId, alojamientosId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        if(proveedorEntity==null)
            throw new BusinessLogicException( PROVEEDOR + proveedorId + NO_EXISTE);
        AlojamientoEntity alojamiento=null;
        for(long idServicio : proveedorEntity.getIdsServicios())   
            if(alojamientosId==idServicio){
                alojamiento = alojamientoPersistence.find(alojamientosId);
                break;
            }
        if(alojamiento==null)
            throw new BusinessLogicException(PROVEEDOR+proveedorId +" no tiene la actividad con id "+alojamientosId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el alojamiento con id = {0} del proveedor con id = " + proveedorId, alojamientosId); 
        return alojamiento;

    }
    
    
  
}
