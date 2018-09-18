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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    @Inject
    private ProveedorPersistence proveedorPersistence;
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
         if (vueloEntity.getProveedor() == null) {
            throw new BusinessLogicException("El proveedor es inválido");
        }
        ProveedorEntity proveedorEntity = proveedorPersistence.find(vueloEntity.getProveedor().getId());
        if (proveedorEntity == null) {
            throw new BusinessLogicException("El proveedor es inválida");
        }
        
        if (!validateNumero(vueloEntity.getNumero())) 
        {
            throw new BusinessLogicException("El Numero de vuelo es inválido");
        }

        // Verifica la regla de negocio que dice que no puede haber dos vuelos con el mismo numero
        
//        if (persistence.findByNumber(vueloEntity.getNumero()) != null) {
//            throw new BusinessLogicException("Ya existe un Vuelo con el nombre \"" + vueloEntity.getNumero() + "\"");
//        }
        
        String input = vueloEntity.getNumero();
        
        // Verifica la regla de negocio que dice que el número del vuelo debe empezar por tres letras mayúsculas que identifican la aerolínea.
        Pattern p1 = Pattern.compile("^[A-Z]{3}");
        Matcher m1 = p1.matcher(input);
        
        if(m1.find())
        {
            throw new BusinessLogicException("El numero del vuelo debería empezar por tres letras mayúsculas identificando la aerolínea");
        }
        
        // Verifica la regla de negocio que dice que el final del número de vuelo deben ser 4 números máximos que van después del identificador de la aerolínea.
        Pattern p2 = Pattern.compile("[\\d]$ {4}");
        Matcher m2 = p2.matcher(input);
        
        if(m2.find())
        {
            throw new BusinessLogicException("El numero del vuelo debe tener máximo 4 números después del identificador de la aerolínea");
        }
        
        // Verifica la regla de negocio que dice que las coordenadas (lat, long) de origen y destino no pueden ser nulas.
        if(vueloEntity.getLatO() == null && vueloEntity.getLonO() == null && vueloEntity.getLatD() == null && vueloEntity.getLonD() == null)
        {
            throw new BusinessLogicException("Las coordenadas (latitud, longitud) de origen y destino deben exsitir, no pueden ser vacías");
        }
 
        // Verifica la regla de negocio que dice que las coordenadas (lat, long) de origen y destino no pueden ser iguales.        
        if(vueloEntity.getLatO().equals(vueloEntity.getLatD()) && vueloEntity.getLonO().equals(vueloEntity.getLonD()) )
        {
           throw new BusinessLogicException("Las coordenadas (latitud, longitud) de origen y destino no pueden ser iguales");
        }
        
        // Verifica la regla de negocio que dice que las fechas de salida y de llegada deben ser válidas y exisitir (not null). 
        if(vueloEntity.getFechaSalida() == (null) && vueloEntity.getFechaLlegada() == (null))
        {
           throw new BusinessLogicException("Las fechas de salida y de llegada deben exisitir y ser vàlidas");            
        }
        
        // Invoca la persistencia para crear el vuelo
        persistence.create(vueloEntity);
        proveedorEntity.getVuelos().add(vueloEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del vuelo");
        return vueloEntity;
    }
    
       /**
     * Obtener todos los vuelos existentes en la base de datos.
     *
     * @return una lista de vuelos.
     */
    public List<VueloEntity> getVuelos() {
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
    public VueloEntity updateVuelo(Long vueloId, VueloEntity vueloEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el vuelo con id = {0}", vueloId);
        // Verifica la regla de negocio que dice que no se puede actualizar el id de un vuelo con un vuelo que ya tenga ese id.

        if (persistence.find(vueloEntity.getId()) != null) 
        {
            throw new BusinessLogicException("Ya existe un Vuelo con el id que quiere cambiar \"" + vueloEntity.getId() + "\"");
        } 
        
        if (!validateNumero(vueloEntity.getNumero())) {
            throw new BusinessLogicException("El Numero de vuelo es inválido");
        }

        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        VueloEntity newEntity = persistence.update(vueloEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el vuelo con id = {0}", vueloEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un vuelo
     *
     * @param vueloId: id del vuelol a borrar
     * @throws BusinessLogicException Si el vuelo a eliminar no existe.
     */
    public void deleteVuelo(Long vueloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el vuelo con id = {0}", vueloId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        if (vueloId == null) {
            throw new BusinessLogicException("No se puede borrar el vuelo con id = " + vueloId + " porque no existe");
        }
        persistence.delete(vueloId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el vuelo con id = {0}", vueloId);
    }

    /**
     * Verifica que el Numero no sea invalido.
     *
     * @param numero a verificar
     * @return true si el numero es valido.
     */
    private boolean validateNumero(String numero) {
        return !(numero == null || numero.isEmpty());
    }    
}
