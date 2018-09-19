/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
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
public class ComboPersistence {
        
    private static final Logger LOGGER = Logger.getLogger(ComboPersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param comboEntity objeto editorial que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ComboEntity create(ComboEntity comboEntity) {
        LOGGER.log(Level.INFO, "Creando un combo nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la editorial en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(comboEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear  un combo nuevo");
        return comboEntity;
    }
	
	/**
     * Devuelve todas los combos de la base de datos.
     *
     * @return una lista con todas los combos que encuentre en la base de
     * datos, "select u from ComboEntity u" es como un "select * from
     * ComboEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<ComboEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas los combos");
        // Se crea un query para buscar todas las editoriales en la base de datos.
        TypedQuery query = em.createQuery("select u from ComboEntity u", ComboEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de combos.
        return query.getResultList();
    }
	
    /**
     * Busca si hay algún combo con el id que se envía de argumento
     *
     * @param comboId: id correspondiente al combo buscado.
     * @return un combo.
     */
    public ComboEntity find(Long comboId) {
        LOGGER.log(Level.INFO, "Consultando combo con id={0}", comboId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EditorialEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(ComboEntity.class, comboId);
    }

	 /**
     * Actualiza un combo.
     *
     * @param comboEntity: el combo que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un combo con los cambios aplicados.
     */
    public ComboEntity update(ComboEntity comboEntity) {
        LOGGER.log(Level.INFO, "Actualizando combo con id = {0}", comboEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar el combo con id = {0}", comboEntity.getId());
        return em.merge(comboEntity);
    }
	
    /**
     *
     * Borra un combo de la base de datos recibiendo como argumento el id
     * del combo
     *
     * @param comboId: id correspondiente al combo a borrar.
     */
    public void delete(Long comboId) {
        LOGGER.log(Level.INFO, "Borrando combo con id = {0}", comboId);
        // Se hace uso de mismo método que esta explicado en public ComboEntity find(String id) para obtener la editorial a borrar.
        ComboEntity entity = em.find(ComboEntity.class, comboId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el combo con id = {0}", comboId);
    }
	
    /**
     * Busca si hay algun combo con el nombre que se envía de argumento
     *
     * @param nombre: Nombre del combo que se está buscando
     * @return null si no existe ningun combo con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public ComboEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando combo por nombre ", nombre);
        // Se crea un query para buscar editoriales con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ComboEntity e where e.nombre = :nombre", ComboEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<ComboEntity> sameName = query.getResultList();
        ComboEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar combo por nombre ", nombre);
        return result;
    }
    
}
