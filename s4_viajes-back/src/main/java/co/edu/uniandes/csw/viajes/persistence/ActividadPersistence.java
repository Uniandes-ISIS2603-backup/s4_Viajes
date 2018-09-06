/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author estudiante
 */

@Stateless
public class ActividadPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ActividadPersistence.class.getName());

    /**
     *
     */
    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param actividadEntity objeto actividad que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ActividadEntity create(ActividadEntity actividadEntity) {
        LOGGER.log(Level.INFO, "Creando una actividad nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la actividad en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(actividadEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una actividad nueva");
        return actividadEntity;
    }
	
	/**
     * Devuelve todas las editoriales de la base de datos.
     *
     * @return una lista con todas las actividades que encuentre en la base de
     * datos, "select u from ActividadEntity u" es como un "select * from
     * EditorialEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<ActividadEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las actividades");
        // Se crea un query para buscar todas las actividades en la base de datos.
        TypedQuery query = em.createQuery("select u from ActividadEntity u", ActividadEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de actvidades.
        return query.getResultList();
    }
	
    /**
     * Busca si hay alguna actividad con el id que se envía de argumento
     *
     * @param actividadId: id correspondiente a la actividad buscada.
     * @return una actividad.
     */
    public ActividadEntity find(Long actividadId) {
        LOGGER.log(Level.INFO, "Consultando actividad con id={0}", actividadId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EditorialEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(ActividadEntity.class, actividadId);
    }

	 /**
     * Actualiza una actividad.
     *
     * @param actividadEntity: la actividad que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una actividad con los cambios aplicados.
     */
    public ActividadEntity update(ActividadEntity actividadEntity) {
        LOGGER.log(Level.INFO, "Actualizando editorial con id = {0}", actividadEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(actividadEntity);
    }
	
    /**
     *
     * Borra una actividad de la base de datos recibiendo como argumento el id
     * de la actividad
     *
     * @param actividadId: id correspondiente a la editorial a borrar.
     */
    public void delete(Long actividadId) {
        LOGGER.log(Level.INFO, "Borrando editorial con id = {0}", actividadId);
        // Se hace uso de mismo método que esta explicado en public EditorialEntity find(Long id) para obtener la editorial a borrar.
        ActividadEntity entity = em.find(ActividadEntity.class, actividadId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from ActividadEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la actividad con id = {0}", actividadId);
    }
	
    /**
     * Busca si hay alguna actividad con el nombre que se envía de argumento
     *
     * @param name: Nombre de la actividad que se está buscando
     * @return null si no existe ninguna actividad con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public ActividadEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando actividad por nombre ", name);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ActividadEntity e where e.name = :name", ActividadEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<ActividadEntity> sameName = query.getResultList();
        ActividadEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar actividad por nombre ", name);
        return result;
    }
    
}
