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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class AlojamientoProveedorLogic {
    
    private static final Logger LOGGER = Logger.getLogger(AlojamientoProveedorLogic.class.getName()); 
    
    @Inject
    private AlojamientoPersistence alojamientoPersistence;
    
    @Inject
    private ProveedorPersistence proveedorPersistence; 
    
    /**
     * Remplazar el proveedor de un alojamiento.
     *
     * @param alojamientosId id del alojamiento que se quiere actualizar.
     * @param proveedoresId El id del proveedor que se será del alojamiento.
     * @return el nuevo alojamiento.
     * @throws BusinessLogicException
     */
    public AlojamientoEntity replaceProveedor(Long alojamientosId, Long proveedoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar alojamiento con id = {0}", alojamientosId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedoresId);
        AlojamientoEntity alojamientoEntity = alojamientoPersistence.find(alojamientosId);
        if(proveedorEntity == null){
            throw new BusinessLogicException("El proveedor por el cual se desea cambiar es invalido: (APLogic)" + proveedoresId);
        }
        else if(alojamientoEntity == null){
            throw new BusinessLogicException("El alojamiento al cual se desea cambiar el proveedor es invalido: (APLogic)" + alojamientosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar alojamiento con id = {0}", alojamientoEntity.getId());
        return alojamientoEntity; 
    }
    
//    /**
//     * Borrar un alojamiento de un proveedor. Este metodo se utiliza para borrar la
//     * relacion de un alojamiento.
//     *
//     * @param alojamientosId El alojamiento que se desea borrar del proveedor.
//     * @throws BusinessLogicException
//     */
//    public void removeProveedor(Long alojamientosId) throws BusinessLogicException{
//        LOGGER.log(Level.INFO, "Inicia proceso de borrar el proveedor del alojamiento con id = {0}", alojamientosId);
//        AlojamientoEntity alojamientoEntity = alojamientoPersistence.find(alojamientosId);
//        ProveedorEntity proveedorEntity = proveedorPersistence.find(alojamientoEntity.getProveedor().getId());
//        if(proveedorEntity == null){
//            throw new BusinessLogicException("El proveedor el cual se desea eliminar es invalido: (APLogic)");
//        }
//        else if(alojamientoEntity == null){
//            throw new BusinessLogicException("El alojamiento al cual se desea eliminar el proveedor es invalido: (APLogic)");
//        }
//        proveedorEntity.getAlojamientos().remove(alojamientoEntity);
//        LOGGER.log(Level.INFO, "Termina proceso de borrar el proveedor del alojamiento con id = {0}", alojamientosId);
//    }
}
