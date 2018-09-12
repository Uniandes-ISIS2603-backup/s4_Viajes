/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.VueloPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Vuelo.
 * 
 * @author jf.torresp
 */
@Stateless
public class VueloLogic {
 
    private static final Logger LOGGER = Logger.getLogger(VueloLogic.class.getName());

    @Inject
    private VueloPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea un vuelo en la persistencia.
     *
     * @param vueloEntity La entidad que representa el vuelo a
     * persistir.
     * @return La entiddad del vuelo luego de persistirla.
     * @throws BusinessLogicException Si el vuelo a persistir ya existe.
     */
    public VueloEntity createVuelo(VueloEntity vueloEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del vuelo");
        // Verifica la regla de negocio que dice que no puede haber dos vuelos con el mismo numero
        if (persistence.findByNumber(vueloEntity.getNumero()) != null) {
            throw new BusinessLogicException("Ya existe un Vuelo con el nombre \"" + vueloEntity.getNumero() + "\"");
        }
        
        //FALTAN LAS DEMAS REGLAS DE NEGOCIO
        
        // Invoca la persistencia para crear la editorial
        persistence.create(vueloEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del vuelo");
        return vueloEntity;
    }
    
       /**
     * Obtener todos los vuelos existentes en la base de datos.
     *
     * @return una lista de vuelos.
     */
    public List<VueloEntity> getVueloss() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los vuelos");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<VueloEntity> vuelos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las editoriales");
        return vuelos;
    }
    
        /**
     * Obtener un vuelo por medio de su id.
     *
     * @param vueloId: id del vuelo para ser buscado.
     * @return el vuelo solicitado por medio de su id.
     */
    public VueloEntity getVuelo(Long vueloId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el vuelo con id = {0}", vueloId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        VueloEntity vueloEntity = persistence.find(vueloId);
        
        if (vueloEntity == null) {
            LOGGER.log(Level.SEVERE, "El vuelo con el id = {0} no existe", vueloId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el vuelo con id = {0}", vueloId);
        return vueloEntity;
    }
    
        /**
     * Actualizar un vuelo.
     *
     * @param vueloId: id del vuelo para buscarlo en la base de
     * datos.
     * @param vueloEntity: vuelo con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return el vuelo con los cambios actualizados en la base de datos.
     */
    public VueloEntity updateVuelo(Long vueloId, VueloEntity vueloEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el vuelo con id = {0}", vueloId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        VueloEntity newEntity = persistence.update(vueloEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el vuelo con id = {0}", vueloEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un vuelo
     *
     * @param vueloId: id del vuelol a borrar
     * @throws BusinessLogicException Si el vuelo a eliminar tiene usuarios.
     */
    public void deleteVuelov(Long vueloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el vuelo con id = {0}", vueloId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        //List<ProveedorEntity> proveedores = getVuelo(vueloId).getProveedor();
        ////if (proveedores != null && !books.isEmpty()) {
        //    throw new BusinessLogicException("No se puede borrar la editorial con id = " + editorialsId + " porque tiene books asociados");
        }
        //persistence.delete(vueloId);
        //LOGGER.log(Level.INFO, "Termina proceso de borrar la editorial con id = {0}", editorialsId);
    //}

    
}
