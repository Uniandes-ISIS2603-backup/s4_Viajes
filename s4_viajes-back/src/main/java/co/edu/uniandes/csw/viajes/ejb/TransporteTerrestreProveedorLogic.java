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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class TransporteTerrestreProveedorLogic {
     private static final Logger LOGGER = Logger.getLogger(TransporteTerrestreProveedorLogic.class.getName()); 
    
    @Inject
    private TransporteTerrestrePersistence transportePersistence;
    
    @Inject
    private ProveedorPersistence proveedorPersistence; 
    
    /**
     * Remplazar el proveedor de un transporte.
     *
     * @param transportesId id del transporte que se quiere actualizar.
     * @param proveedoresId El id del proveedor que se será del transporte.
     * @return el nuevo transporte.
     * @throws BusinessLogicException
     */
    public TransporteTerrestreEntity replaceProveedor(Long transportesId, Long proveedoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar transporte con id = {0}", transportesId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedoresId);
        TransporteTerrestreEntity transporteEntity = transportePersistence.find(transportesId);
        if(proveedorEntity == null){
            throw new BusinessLogicException("El proveedor por el cual se desea cambiar es invalido: (TPLogic)" + proveedoresId);
        }
        else if(transporteEntity == null){
            throw new BusinessLogicException("El transporte al cual se desea cambiar el proveedor es invalido: (TPLogic)" + transportesId);
        }
        transporteEntity.setProveedor(proveedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar transporte con id = {0}", transporteEntity.getId());
        return transporteEntity; 
    }
    
    /**
     * Borrar un transporte de un proveedor. Este metodo se utiliza para borrar la
     * relacion de un transporte.
     *
     * @param transportesId El transporte que se desea borrar del proveedor.
     * @throws BusinessLogicException
     */
    public void removeProveedor(Long transportesId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el proveedor del alojamiento con id = {0}", transportesId);
        TransporteTerrestreEntity transporteEntity = transportePersistence.find(transportesId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(transporteEntity.getProveedor().getId());
        if(proveedorEntity == null){
            throw new BusinessLogicException("El proveedor el cual se desea eliminar es invalido: (TPLogic)");
        }
        else if(transporteEntity == null){
            throw new BusinessLogicException("El transporte al cual se desea eliminar el proveedor es invalido: (TPLogic)");
        }
        transporteEntity.setProveedor(null);
        proveedorEntity.getTransportes().remove(transporteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el proveedor del transporte con id = {0}", transportesId);
    }
}
