/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.AdministradorEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class AdministradorPersistence {
    
      private static final Logger LOGGER = Logger.getLogger(AdministradorPersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Crea un carrito de compras en la base de datos
     *
     * @param administradorEntity objeto carritoCompras que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public AdministradorEntity create(AdministradorEntity administradorEntity) {
        LOGGER.log(Level.INFO, "Creando un administrador nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la author en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(administradorEntity);
       LOGGER.log(Level.INFO, "Administrador creado");
        return administradorEntity;
    }

    /**
     * Devuelve todas los administradores de la base de datos.
     *
     * @return una lista con todos los administradores que encuentre en la base de
     * datos, "select u from UsuarioEntity u" es como un "select * from
     * AuthorEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<AdministradorEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los autores");
        // Se crea un query para buscar todos los administradores en la base de datos.
        TypedQuery query = em.createQuery("select u from AdministradorEntity u", AdministradorEntity.class);
         //Note que en el query se hace uso del método getResultList() que obtiene una lista de administradores.
        return query.getResultList();
    }

    /**
     * Busca si hay usuario con el documento que se envía de argumento
     *
     * @param documento: id correspondiente a la author buscada.
     * @return un usuario.
     */
    public AdministradorEntity find(String userName) {
        LOGGER.log(Level.INFO, "Consultando el administrador con id={0}", userName);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from UsuarioEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(AdministradorEntity.class, userName);
    }

    /**
     * Actualiza un usuario.
     *
     * @param administradorEntity: el administrador que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una author con los cambios aplicados.
     */
    public AdministradorEntity update(AdministradorEntity administradorEntity) {
        LOGGER.log(Level.INFO, "Actualizando el administrador con id={0}", administradorEntity.getUserName());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la author con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(administradorEntity);
    }

    /**
     * Borra un usuario de la base de datos recibiendo como argumento el id de
     * la author
     *
     * @param documento: documento correspondiente al usuario a borrar.
     */
    public void delete(String userName) {

        LOGGER.log(Level.INFO, "Borrando el administrador con id={0}", userName);
        // Se hace uso de mismo método que esta explicado en public UsuarioEntity find(Long id) para obtener el usuario a borrar.
       AdministradorEntity administradorEntity = em.find(AdministradorEntity.class, userName);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from UsuarioEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(administradorEntity);
    }
    
    
}
