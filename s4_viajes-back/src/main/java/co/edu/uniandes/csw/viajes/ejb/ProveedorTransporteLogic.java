/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.TransporteTerrestrePersistence;
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
public class ProveedorTransporteLogic {
      
    private static final Logger LOGGER = Logger.getLogger(ProveedorTransporteLogic.class.getName());

    @Inject
    private TransporteTerrestrePersistence TransporteTerrestrePersistence;

    @Inject
    private ProveedorPersistence proveedorPersistence;

    /**
     * Agregar una TransporteTerrestre al proveedor
     *
     * @param transporteTerrestreId El id TransporteTerrestre a guardar
     * @param proveedorId El id de la proveedor en la cual se va a guardar el
     * TransporteTerrestre.
     * @return El TransporteTerrestre agregado al proveedor.
     */
    public TransporteTerrestreEntity addTransporte(Long transporteTerrestreId, Long proveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un TransporteTerrestre al proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        TransporteTerrestreEntity TransporteTerrestreEntity = TransporteTerrestrePersistence.find(transporteTerrestreId);
        TransporteTerrestreEntity.setProveedor(proveedorEntity);
        proveedorEntity.getTransportes().add(TransporteTerrestreEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un TransporteTerrestre al proveedor con id = {0}", proveedorId);
        return TransporteTerrestreEntity;
    }
    
    /**
     * Retorna todas los TransporteTerrestrees asociados a un proveedor
     *
     * @param proveedorId El ID del proveedor buscado
     * @return La lista de TransporteTerrestrees del proveedor
     */
    public List<TransporteTerrestreEntity> getTransportes(Long proveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los TransporteTerrestres asociados al proveedor con id = {0}", proveedorId);
        return proveedorPersistence.find(proveedorId).getTransportes();
    }
    
    /**
     * Retorna un TransporteTerrestre asociado a una editorial
     *
     * @param proveedorId El id del proveedor a buscar.
     * @param TransporteTerrestreId El id de la TransporteTerrestre a buscar
     * @return El TransporteTerrestre encontrado dentro del proveedor.
     * @throws BusinessLogicException Si el TransporteTerrestre no se encuentra en el proveedor
     */
    public TransporteTerrestreEntity getTransporte(Long proveedorId, Long TransporteTerrestreId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la TransporteTerrestre con id = {0} del proveedor con id = " + proveedorId, TransporteTerrestreId);
        List<TransporteTerrestreEntity> TransporteTerrestres = proveedorPersistence.find(proveedorId).getTransportes();
        TransporteTerrestreEntity TransporteTerrestreEntity = TransporteTerrestrePersistence.find(TransporteTerrestreId);
        int index = TransporteTerrestres.indexOf(TransporteTerrestreEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el TransporteTerrestre con id = {0} del proveedor con id = " + proveedorId, TransporteTerrestreId);
        if (index >= 0) {
            return TransporteTerrestres.get(index);
        }
        throw new BusinessLogicException("El TransporteTerrestre no está asociado al proveedor");
    }

    /**
     * Remplazar TransporteTerrestres de un proveedor
     *
     * @param TransporteTerrestres Lista de TransporteTerrestres que serán los del proveedor.
     * @param proveedorId El id del proveedor que se quiere actualizar.
     * @return La lista de TransporteTerrestres actualizada.
     */
    public List<TransporteTerrestreEntity> replaceTransportes(Long proveedorId, List<TransporteTerrestreEntity> TransporteTerrestres) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        List<TransporteTerrestreEntity> TransporteTerrestreList = TransporteTerrestrePersistence.findAll();
        for (TransporteTerrestreEntity TransporteTerrestre : TransporteTerrestreList) {
            if (TransporteTerrestres.contains(TransporteTerrestre)) {
                TransporteTerrestre.setProveedor(proveedorEntity);
            } else if (TransporteTerrestre.getProveedor() != null && TransporteTerrestre.getProveedor().equals(proveedorEntity)) {
                TransporteTerrestre.setProveedor(null);
            }
        }
        proveedorEntity.setTransportes(TransporteTerrestres);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el proveedor con id = {0}", proveedorId);
        return TransporteTerrestres;
    }    
}
