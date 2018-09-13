/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ProveedorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 *Proveedor.
 * 
 * @author jf.torresp
 */
@Stateless
public class ProveedorLogic {
    private static final Logger LOGGER = Logger.getLogger(ProveedorLogic.class.getName());

    @Inject
    private ProveedorPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea un proveedor en la persistencia.
     *
     * @param proveedorEntity La entidad que representa el proveedor a
     * persistir.
     * @return La entidad del proveedor luego de persistirla.
     * @throws BusinessLogicException Si el proveedor a persistir ya existe.
     */
    public ProveedorEntity createProveedor(ProveedorEntity proveedorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del proveedor");
        // Verifica la regla de negocio que dice que no puede haber dos proveedores con el mismo nombre
        if(persistence.find(proveedorEntity.getId()) == null)
        {
            throw new BusinessLogicException("Ya existe un proveedor con el id \"" + proveedorEntity.getId() + "\"");
        }
        
        String input = proveedorEntity.getNombre();
         
        // Verifica la regla de negocio que dice que el nombre del proveedor debe ser compuesto por solo letras y debe tener mínimo 5 letras y máximo 30 letras.
        Pattern p1 = Pattern.compile("^[a-zA-Z]{5,30}");
        Matcher m1 = p1.matcher(input);
        
        if(m1.find() == false)
        {
            throw new BusinessLogicException("El nombre del proveedor debe ser compuesto por solo letras y debe tener mínimo 5 letras y máximo 30 letras.");
        }
        
        // Verifica la regla de negocio que dice que dos proveedores con el mismo username.
        if(proveedorEntity.getUser() != null)
        {
            throw new BusinessLogicException("El usuario del proveedor ya existe, debe ingresar uno diferente.");
        }
        
        if (persistence.findByName(proveedorEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe un proveedor con el nombre \"" + proveedorEntity.getNombre() + "\"");
        }
        
        String input2 = proveedorEntity.getPassword();
         
        // Verifica la regla de negocio que dice que la contraseña debe complur lo siguiente:
        // Mínimo 8 caracteres
        //Maximo 15 caracteres
        //Al menos una letra mayúscula
        //Al menos una letra minúcula
        //Al menos un dígito
        //No espacios en blanco
        //Al menos 1 caracter especial
        Pattern p2 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,15}");
        Matcher m2 = p2.matcher(input2);
        
        if(m2.find() == false)
        {
            throw new BusinessLogicException("La contraseña no cumple con las reglas establecidas.");
        }
        
        // Invoca la persistencia para crear el proveedor
        persistence.create(proveedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del proveedor");
        return proveedorEntity;
    }
    
    /**
     * Obtener todas los proveedores existentes en la base de datos.
     *
     * @return una lista de proveedores.
     */
    public List<ProveedorEntity> getProveedores() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los proveedores");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ProveedorEntity> proveedores = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los proveedores");
        return proveedores;
    }

    /**
     * Obtener un proveedor por medio de su id.
     *
     * @param proveedorId: id del proveedor para ser buscada.
     * @return el proveedor solicitada por medio de su id.
     */
    public ProveedorEntity getProveedor(Long proveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el proveedor con id = {0}", proveedorId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        ProveedorEntity proveedorEntity = persistence.find(proveedorId);
        if (proveedorEntity == null) {
            LOGGER.log(Level.SEVERE, "El proveedor con el id = {0} no existe", proveedorId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el proveedor con id = {0}", proveedorId);
        return proveedorEntity;
    }

    /**
     * Actualizar un proveedor.
     *
     * @param proveedorId: id del proveedor para buscarlo en la base de
     * datos.
     * @param proveedorEntity: proveedor con los cambios para ser actualizado,
     * por ejemplo el nombre.
     * @return el proveedor con los cambios actualizados en la base de datos.
     */
    public ProveedorEntity updateProveedor(Long proveedorId, ProveedorEntity proveedorEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el proveedor con id = {0}", proveedorId);
        // Verifica la regla de negocio que dice que no se puede actualizar el id de un vuelo con un vuelo que ya tenga ese id.
        if (persistence.find(proveedorEntity.getId()) != null) 
        {
            throw new BusinessLogicException("Ya existe un proveedor con el id que quiere cambiar \"" + proveedorEntity.getId() + "\"");
        } 
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        ProveedorEntity newEntity = persistence.update(proveedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el proveedor con id = {0}", proveedorEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un proveedor
     *
     * @param proveedorId: id del proveedor a borrar
     * @throws BusinessLogicException Si el proveedor a eliminar no existe.
     */
    public void deleteProveedor(Long proveedorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar elproveedor con id = {0}", proveedorId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        if (proveedorId == null) {
            throw new BusinessLogicException("No se puede borrar el proveedor con id = " + proveedorId + " porque no existe");
        }
        persistence.delete(proveedorId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el proveedor con id = {0}", proveedorId);
    }
}
