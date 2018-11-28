/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.ServicioEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ProveedorPersistence;
import co.edu.uniandes.csw.viajes.persistence.VueloPersistence;
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
public class ProveedorVueloLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorVueloLogic.class.getName());

    @Inject
    private VueloPersistence vueloPersistence;

    @Inject
    private ProveedorPersistence proveedorPersistence;

    /**
     * Agregar un vuelo al proveedor
     *
     * @param vueloId El id vuelo a guardar
     * @param proveedorId El id de la proveedor en la cual se va a guardar el
     * vuelo.
     * @return El vuelo agregado al proveedor.
     */
    public ProveedorEntity addVuelo(Long vueloId, Long proveedorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una actividad al proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        VueloEntity vueloEntity = vueloPersistence.find(vueloId);
        if(proveedorEntity==null)
            throw new BusinessLogicException("El proveedor con id "+proveedorId +" no existe");
        if(vueloEntity==null)
            throw new BusinessLogicException("EL vuelo con id "+vueloId +" no existe");
        
         for(long idServicio : proveedorEntity.getIdsServicios())
            if(vueloId == idServicio)
                throw new BusinessLogicException("El combo ya tiene asignada un vuelo con id " + vueloId +".");
        proveedorEntity.addIdServicio(vueloId);
        proveedorEntity.addServicioFirst(vueloEntity);

        proveedorPersistence.update(proveedorEntity);

        LOGGER.log(Level.INFO, "Termina proceso de agregarle una actividad al proveedor con id = {0}", proveedorId);
        return proveedorEntity;
    }
    
    /**
     * Retorna todos los vuelos asociados a un proveedor
     *
     * @param proveedorId El ID del proveedor buscado
     * @return La lista de vuelos del proveedor
     */
    public List<VueloEntity> getVuelos(Long proveedorId) throws BusinessLogicException {
      LOGGER.log(Level.INFO, "Inicia proceso de consultar los vuelos asociados al proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        if(proveedorEntity==null)
            throw new BusinessLogicException("El proveedor con id "+proveedorId +" no existe");
        List<VueloEntity> vuelos=new ArrayList<>();
        for(long idServicio : proveedorEntity.getIdsServicios())   
        {
            VueloEntity vuelo = vueloPersistence.find(idServicio);
            if(vuelo==null)
               {
                  //No era una actividad
               }
            else
                vuelos.add(vuelo);
        }
        
        return vuelos;
    }
    
        /**
     * Retorna un vuelo asociado a una editorial
     *
     * @param proveedorId El id de la editorial a buscar.
     * @param vueloId El id del libro a buscar
     * @return El libro encontrado dentro de la editorial.
     * @throws BusinessLogicException Si el vuelo no se encuentra en el proveedor
     */
    public VueloEntity getVuelo(Long proveedorId, Long vueloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el vuelo con id = {0} del proveedor con id = " + proveedorId, vueloId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        if(proveedorEntity==null)
            throw new BusinessLogicException("El proveedor con id "+proveedorId +" no existe");
        VueloEntity vuelo=null;
        for(long idServicio : proveedorEntity.getIdsServicios())   
            if(vueloId==idServicio){
                vuelo = vueloPersistence.find(vueloId);
                break;
            }      
        if(vuelo==null)
            throw new BusinessLogicException("El proveedor con id "+proveedorId +" no tiene el vuelo con id "+vueloId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la actividad con id = {0} del proveedor con id = " + proveedorId, vueloId); 
        return vuelo;
    }

    
}
