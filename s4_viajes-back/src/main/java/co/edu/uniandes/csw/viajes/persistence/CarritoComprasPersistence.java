/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.CarritoComprasEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author n.segura
 */

@Stateless
public class CarritoComprasPersistence {
    
    
      private static final Logger LOGGER = Logger.getLogger(CarritoComprasPersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Crea un usuario en la base de datos
     *
     * @param CarritoComprasEntity objeto usuario que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CarritoComprasEntity create(CarritoComprasEntity carritoComprasEntity) {
        LOGGER.log(Level.INFO, "Creando un carrito de compras nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la author en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(carritoComprasEntity);
       LOGGER.log(Level.INFO, "CarritoCompras creado");
        return carritoComprasEntity;
    }

    /**
     * Devuelve todas los carritos de compras de la base de datos.
     *
     * @return una lista con todos los carritos de compras que encuentre en la base de
     * datos, "select u from CarritoComprasEntity u" es como un "select * from
     * AuthorEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<CarritoComprasEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los carritos de compras");
        // Se crea un query para buscar todos los usuarios en la base de datos.
        TypedQuery query = em.createQuery("select u from CarritoComprasEntity u", CarritoComprasEntity.class);
         //Note que en el query se hace uso del método getResultList() que obtiene una lista de authores.
        return query.getResultList();
    }

    /**
     * Busca si hay usuario con el documento que se envía de argumento
     *
     * @param documento: id correspondiente a la author buscada.
     * @return un usuario.
     */
    public CarritoComprasEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando el carrito con id={0}", id);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from UsuarioEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(CarritoComprasEntity.class, id);
    }

    /**
     * Actualiza un Carrito de Compras.
     *
     * @param CarritoComprasEntity: El carrito que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una carrito con los cambios aplicados.
     */
    public CarritoComprasEntity update(CarritoComprasEntity carritoComprasEntity) {
        LOGGER.log(Level.INFO, "Actualizando el carrito con id={0}", carritoComprasEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la author con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(carritoComprasEntity);
    }

    /**
     * Borra un carrito de compras de la base de datos recibiendo como argumento el id de
     * la author
     *
     * @param documento: documento correspondiente al usuario a borrar.
     */
    public void delete(Long id) {

        LOGGER.log(Level.INFO, "Borrando el author con id={0}", id);
        // Se hace uso de mismo método que esta explicado en public UsuarioEntity find(Long id) para obtener el usuario a borrar.
       CarritoComprasEntity carritoEntity = em.find(CarritoComprasEntity.class, id);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from UsuarioEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(carritoEntity);
    }
    
}
    
    

