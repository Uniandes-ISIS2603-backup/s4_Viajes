/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ProveedorPersistence;
import co.edu.uniandes.csw.viajes.persistence.VueloPersistence;
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
    public VueloEntity addVuelo(Long vueloId, Long proveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un vuelo al proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        VueloEntity vueloEntity = vueloPersistence.find(vueloId);
        vueloEntity.setProveedor(proveedorEntity);
        proveedorEntity.getVuelos().add(vueloEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un vuelo al proveedor con id = {0}", proveedorId);
        return vueloEntity;
    }
    
    /**
     * Retorna todos los vuelos asociados a un proveedor
     *
     * @param proveedorId El ID del proveedor buscado
     * @return La lista de vuelos del proveedor
     */
    public List<VueloEntity> getVuelos(Long proveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los vuelos asociados al proveedor con id = {0}", proveedorId);
        return proveedorPersistence.find(proveedorId).getVuelos();
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
        List<VueloEntity> vuelos = proveedorPersistence.find(proveedorId).getVuelos();
        VueloEntity vueloEntity = vueloPersistence.find(vueloId);
        int index = vuelos.indexOf(vueloEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el vuelo con id = {0} del proveedor con id = " + proveedorId, vueloId);
        if (index >= 0) {
            return vuelos.get(index);
        }
        throw new BusinessLogicException("El vuelo no está asociado al proveedor");
    }

    /**
     * Remplazar vuelos de un proveedor
     *
     * @param vuelos Lista de vuelos que serán los del proveedor.
     * @param proveedorId El id del proveedor que se quiere actualizar.
     * @return La lista de vuelos actualizada.
     */
    public List<VueloEntity> replaceVuelos(Long proveedorId, List<VueloEntity> vuelos) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el proveedor con id = {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        List<VueloEntity> vueloList = vueloPersistence.findAll();
        for (VueloEntity vuelo : vueloList) {
            if (vuelos.contains(vuelo)) {
                vuelo.setProveedor(proveedorEntity);
            } else if (vuelo.getProveedor() != null && vuelo.getProveedor().equals(proveedorEntity)) {
                vuelo.setProveedor(null);
            }
        }
        proveedorEntity.setVuelos(vuelos);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el proveedor con id = {0}", proveedorId);
        return vuelos;
    }
    
}
