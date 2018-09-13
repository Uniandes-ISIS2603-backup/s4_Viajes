/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * Clase que maneja la persistencia paraVuelo. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author jf.torresp
 */
@Stateless
public class VueloPersistence {

    private static final Logger LOGGER = Logger.getLogger(VueloPersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad (Vuelo) en la base de datos.
     *
     * @param vueloEntity objeto vuelo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public VueloEntity create(VueloEntity vueloEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo vuelo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la editorial en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(vueloEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un vuelo nuevo");
        return vueloEntity;
    }

    /**
     * Devuelve todas los vuelos de la base de datos.
     *
     * @return una lista con todos los vuelos que encuentre en la base de datos,
     * "select u from VueloEntity u" es como un "select * from VueloEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<VueloEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los vuelos");
        // Se crea un query para buscar todos los vuelos en la base de datos.
        TypedQuery query = em.createQuery("select u from VueloEntity u", VueloEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de vuelos.
        return query.getResultList();
    }

    /**
     * Busca si hay algun vuelo con el id que se envía de argumento
     *
     * @param vueloId: id correspondiente l vuelo buscado.
     * @return un vuelo.
     */
    public VueloEntity find(Long vueloId) {
        LOGGER.log(Level.INFO, "Consultando vuelo con id={0}", vueloId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from VueloEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(VueloEntity.class, vueloId);
    }
    
    /**
     * Actualiza un vuelo.
     *
     * @param vueloEntity: lel vuelo que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return u vuelo con los cambios aplicados.
     */
    public VueloEntity update(VueloEntity vueloEntity) {
        LOGGER.log(Level.INFO, "Actualizando vuelo con id = {0}", vueloEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        el vuelo con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar el vuelo con id = {0}", vueloEntity.getId());
        return em.merge(vueloEntity);
    }
    
        /**
     * Borra un vuelo de la base de datos recibiendo como argumento el id
     * del vuelo
     *
     * @param vueloId: id correspondiente al vuelo a borrar.
     */
    public void delete(Long vueloId) {
        LOGGER.log(Level.INFO, "Borrando vuelo con id = {0}", vueloId);
        VueloEntity entity = em.find(VueloEntity.class, vueloId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el vuelo con id = {0}", vueloId);
    }
    
        /**
     * Busca si hay algun proveedor con elnombre que se envía de argumento
     *
     * @param nombre: Nombre del proveedor que se está buscando
     * @return null si no existe ningun proveedor con el nombre del argumento. Si
     * existe alguno devuelve el primero.
     */
    public VueloEntity findByNumber(String numero) {
        LOGGER.log(Level.INFO, "Consultando vuelos por numero ", numero);
        // Se crea un query para buscar vuelos con el umero que recibe el método como argumento. ":numero" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From VueloEntity e where e.numero = :numero", VueloEntity.class);
        // Se remplaza el placeholder ":numero" con el valor del argumento 
        query = query.setParameter("nombre", numero);
        // Se invoca el query se obtiene la lista resultado
        List<VueloEntity> sameNumero = query.getResultList();
        VueloEntity result;
        if (sameNumero == null) {
            result = null;
        } else if (sameNumero.isEmpty()) {
            result = null;
        } else {
            result = sameNumero.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar vuelo por numero ", numero);
        return result;
    }
    
}
