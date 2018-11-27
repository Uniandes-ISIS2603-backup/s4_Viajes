/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * Clase que maneja la persistencia para el Proveedor. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 * 
 * @author jf.torresp
 */
@Stateless
public class ProveedorPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorPersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad (Proveedor) en la base de datos.
     *
     * @param proveedorEntity objeto proveedor que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ProveedorEntity create(ProveedorEntity proveedorEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo proveedor");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la editorial en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(proveedorEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un proveedor nuevo");
        return proveedorEntity;
    }

    /**
     * Devuelve todos los proveedores de la base de datos.
     *
     * @return una lista con todos los proveedores que encuentre en la base de datos,
     * "select u from ProveedorEntity u" es como un "select * from ProveedorEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<ProveedorEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los proveedores");
        // Se crea un query para buscar todos los proveedores en la base de datos.
        TypedQuery query = em.createQuery("select u from ProveedorEntity u", ProveedorEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de vuelos.
        return query.getResultList();
    }

    /**
     * Busca si hay algun proveedor con el id que se envía de argumento
     *
     * @param proveedorId: id correspondiente l vuelo buscado.
     * @return un vuelo.
     */
    public ProveedorEntity find(Long proveedorId) {
        LOGGER.log(Level.INFO, "Consultando proveedor con id={0}", proveedorId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from ProveedorEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(ProveedorEntity.class, proveedorId);
    }
    
    /**
     * Actualiza un proveedor.
     *
     * @param proveedorEntity: el proveedor que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un proveedor con los cambios aplicados.
     */
    public ProveedorEntity update(ProveedorEntity proveedorEntity) {
        LOGGER.log(Level.INFO, "Actualizando proveedor con id = {0}", proveedorEntity.getUser());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        el vuelo con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar el proveedorcon id = {0}", proveedorEntity.getUser());
        return em.merge(proveedorEntity);
    }
    
        /**
     * Borra un proveedor de la base de datos recibiendo como argumento el id
     * del proveedor
     *
     * @param proveedorId: id correspondiente al proveedor a borrar.
     */
    public void delete(Long proveedorId) {
        LOGGER.log(Level.INFO, "Borrando vuelo con id = {0}", proveedorId);
        ProveedorEntity entity = em.find(ProveedorEntity.class, proveedorId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from VueloEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el proveedor con id = {0}", proveedorId);
    }
    
    /**
     * Busca si hay algun proveedor con elnombre que se envía de argumento
     *
     * @param nombre: Nombre del proveedor que se está buscando
     * @return null si no existe ningun proveedor con el nombre del argumento. Si
     * existe alguno devuelve el primero.
     */
    public ProveedorEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando proveedores por nombre ", nombre);
        // Se crea un query para buscar proveedores con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ProveedorEntity e where e.nombre = :nombre", ProveedorEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<ProveedorEntity> sameNombre = query.getResultList();
        ProveedorEntity result;
        if (sameNombre == null) {
            result = null;
        } else if (sameNombre.isEmpty()) {
            result = null;
        } else {
            result = sameNombre.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar proveedor por nombre ", nombre);
        return result;
    }
         
    public ProveedorEntity findByUserName(String username) {
        TypedQuery query = em.createQuery("Select e From ProveedorEntity e where e.username = :username", ProveedorEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("username", username);
        // Se invoca el query se obtiene la lista resultado
        List<ProveedorEntity> sameNombre = query.getResultList();
        ProveedorEntity result;
        if (sameNombre == null) {
            result = null;
        } else if (sameNombre.isEmpty()) {
            result = null;
        } else {
            result = sameNombre.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar proveedor por username ", username);
        return result;
    }
}
