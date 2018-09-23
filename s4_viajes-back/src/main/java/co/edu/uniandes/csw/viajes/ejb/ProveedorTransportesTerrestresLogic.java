/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ProveedorPersistence;
import co.edu.uniandes.csw.viajes.persistence.TransporteTerrestrePersistence;
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
     * @param transportesId El id del transporte a guardar
     * @param proveedoresId El id del proveedor en la cual se va a guardar el transporte.
     * @return El transporte creado.
     * @throws BusinessLogicException
     */
    public TransporteTerrestreEntity addTransporte(Long transportesId, Long proveedoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un transporte a un proveedor con id = {0}", proveedoresId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedoresId);
        TransporteTerrestreEntity transporteEntity = transporteTerrestrePersistence.find(transportesId);
        if(proveedorEntity == null){
            throw new BusinessLogicException("El proveedor no se encuentra registrado: (PTLogic) " + proveedoresId);
        }
        if(transporteEntity == null){
             throw new BusinessLogicException("El transporte no se encuentra registrado: (PTLogic) " + transportesId);
        }
        transporteEntity.setProveedor(proveedorEntity); 
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un transporte a un proveedor con id = {0}", proveedoresId);
        return transporteEntity;
    }
    
    /**
     * Retorna todos los transportes asociados a un proveedor.
     *
     * @param proveedoresId El ID del proveedor buscado
     * @return La lista de transportes del proveedor
     * @throws BusinessLogicException
     */
    public List<TransporteTerrestreEntity> getTransportes(Long proveedoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los transportes asociados a un proveedor con id = {0}", proveedoresId);
        ProveedorEntity proveedor = proveedorPersistence.find(proveedoresId);
        if(proveedor == null){
            throw new BusinessLogicException("El proveedor no se encuentra registrado, imposible hacer la consulta. (PTLogic)"+ proveedoresId);
        }
        return proveedor.getTransportes(); 
    }
    
    /**
     * Retorna un transporte asociado a un proveedor
     *
     * @param proveedorId El id del proveedor a buscar.
     * @param transportesId El id del transporte a buscar
     * @return El transporte encontrado dentro del proveedor.
     * @throws BusinessLogicException Si el transporte no se encuentra.
     */
    public TransporteTerrestreEntity getTransporte(Long proveedorId, Long transportesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el transporte con id = {0} del proveedor con id = " + proveedorId, transportesId);
        ProveedorEntity proveedor = proveedorPersistence.find(proveedorId);
        if(proveedor == null){
            throw new BusinessLogicException("El id del proveedor ingresado es invalido: (PTLogic)" + proveedorId);
        }
        List<TransporteTerrestreEntity> transportes = proveedor.getTransportes();
        TransporteTerrestreEntity transporteEntity = transporteTerrestrePersistence.find(transportesId);
        if(transporteEntity == null){
            throw new BusinessLogicException("El transporte no está asociado al proveedor. (PTLogic)");
        }
        int index = transportes.indexOf(transporteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el transporte con id = {0} del proveedor con id = " + proveedorId, transportesId);
        
        return transportes.get(index);
    }
    
    /**
     * Remplazar transportes de un proveedor.
     *
     * @param pTransportes Lista de alojamientos que serán los del proveedor.
     * @param proveedorId El id del proveedor que se quiere actualizar.
     * @return La lista de alojamientos del proveedor actualizada.
     * @throws BusinessLogicException
     */
    public List<TransporteTerrestreEntity> replaceTransportes(Long proveedorId, List<TransporteTerrestreEntity> pTransportes) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar transportes del proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        if(proveedorEntity == null){
            throw new BusinessLogicException("El id del proveedor ingresado es invalido: (PTLogic)" + proveedorId);
        }
        List<TransporteTerrestreEntity> transporteList = transporteTerrestrePersistence.findAll();
        for (TransporteTerrestreEntity transporte : transporteList) {
            if (pTransportes.contains(transporte)) {
                transporte.setProveedor(proveedorEntity);
            } else if (transporte.getProveedor() != null && transporte.getProveedor().equals(proveedorEntity)) {
                transporte.setProveedor(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar los transportes del proveedor con id = {0}", proveedorId);
        return pTransportes;
    }
}
