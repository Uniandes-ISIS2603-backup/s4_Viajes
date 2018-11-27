/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.ServicioEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ProveedorPersistence;
import co.edu.uniandes.csw.viajes.persistence.TransporteTerrestrePersistence;
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
public class ProveedorTransportesTerrestresLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorTransportesTerrestresLogic.class.getName());
    
    @Inject
    private ProveedorPersistence proveedorPersistence; 
    
    @Inject
    private TransporteTerrestrePersistence transporteTerrestrePersistence; 
    
    /**
     * Agregar un transporte al proveedor. 
     *
     * @param transporteTerrestreId El id del transporte a guardar
     * @param proveedoresId El id del proveedor en la cual se va a guardar el transporte.
     * @return El transporte creado.
     * @throws BusinessLogicException
     */
    public ProveedorEntity addTransporteTerrestre(Long transporteTerrestreId, Long proveedorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una actividad al proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        TransporteTerrestreEntity transporteTerrestreEntity = transporteTerrestrePersistence.find(transporteTerrestreId);
        if(proveedorEntity==null)
            throw new BusinessLogicException("El proveedor con id "+proveedorId +" no existe");
        if(transporteTerrestreEntity==null)
            throw new BusinessLogicException("EL transporteTerrestre con id "+transporteTerrestreId +" no existe");

         for(long idServicio : proveedorEntity.getIdsServicios())
            if(transporteTerrestreId == idServicio)
                throw new BusinessLogicException("El combo ya tiene asignada una actividad con id " + transporteTerrestreId +".");
          
        proveedorEntity.addIdServicio(transporteTerrestreId);
        proveedorEntity.addServicioFirst(transporteTerrestreEntity);

        proveedorPersistence.update(proveedorEntity);

        LOGGER.log(Level.INFO, "Termina proceso de agregarle una actividad al proveedor con id = {0}", proveedorId);
        return proveedorEntity;
    }
    
    /**
     * Retorna todos los transportes asociados a un proveedor.
     *
     * @param proveedoresId El ID del proveedor buscado
     * @return La lista de transportes del proveedor
     * @throws BusinessLogicException
     */
    public List<TransporteTerrestreEntity> getTransportesTerrestres(Long proveedorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las actividades asociados al proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        if(proveedorEntity==null)
            throw new BusinessLogicException("El proveedor con id "+proveedorId +" no existe");
        List<TransporteTerrestreEntity> transportesTerrestres=new ArrayList<>();
        for(long idServicio : proveedorEntity.getIdsServicios())   
        {
            TransporteTerrestreEntity transporteTerrestre = transporteTerrestrePersistence.find(idServicio);
            if(transporteTerrestre==null)
               {
                  //No era un transpote
               }
            else
                transportesTerrestres.add(transporteTerrestre);
        }
        
        return transportesTerrestres;
    }
    
    /**
     * Retorna un transporte asociado a un proveedor
     *
     * @param proveedorId El id del proveedor a buscar.
     * @param transportId El id del transporte a buscar
     * @return El transporte encontrado dentro del proveedor.
     * @throws BusinessLogicException Si el transporte no se encuentra.
     */
    public TransporteTerrestreEntity getTransporteTerrestre(Long proveedorId, Long transportId) throws BusinessLogicException {
         LOGGER.log(Level.INFO, "Inicia proceso de consultar el transporteTerrestre con id = {0} del proveedor con id = " + proveedorId, transportId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        if(proveedorEntity==null)
            throw new BusinessLogicException("El proveedor con id "+proveedorId +" no existe");
        TransporteTerrestreEntity transporteTerrestre=null;
        for(long idServicio : proveedorEntity.getIdsServicios())   
            if(transportId==idServicio){
                transporteTerrestre = transporteTerrestrePersistence.find(transportId);
                break;
            }      
        if(transporteTerrestre==null)
            throw new BusinessLogicException("El proveedor con id "+proveedorId +" no tiene la actividad con id "+transportId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el transporteTerrestre con id = {0} del proveedor con id = " + proveedorId, transportId); 
        return transporteTerrestre;
    }
    
   
}
