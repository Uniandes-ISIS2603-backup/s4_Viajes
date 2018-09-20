/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.MedallaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Luis Gómez Amado
 */
@Stateless
public class MedallaPersistence {
     private static final Logger LOGGER = Logger.getLogger(MedallaPersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad (Medalla) en la base de datos.
     *
     * @param medallaEntity objeto medalla que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public MedallaEntity create(MedallaEntity medallaEntity) {
        LOGGER.log(Level.INFO, "Creando una nueva medalla");
        em.persist(medallaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una medalla nuevo");
        return medallaEntity;
    }

    /**
     * Devuelve todas las medallas de la base de datos.
     *
     * @return una lista con todas las medallas que encuentre en la base de datos,
     * "select u from MedallaEntity u" es como un "select * from MedallaEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<MedallaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las medallas");
        // Se crea un query para buscar todos las medallas en la base de datos.
        TypedQuery query = em.createQuery("select u from MedallaEntity u", MedallaEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay alguna medalla con el id que se envía de argumento
     *
     * @param medallaId: id correspondiente a la medalla buscada.
     * @return un vuelo.
     */
    public MedallaEntity find(Long medallaId) {
        LOGGER.log(Level.INFO, "Consultando medalla con id={0}", medallaId);
        return em.find(MedallaEntity.class, medallaId);
    }
    
    /**
     * Actualiza una medalla.
     *
     * @param medallaEntity: la medalla que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una medalla con los cambios aplicados.
     */
    public MedallaEntity update(MedallaEntity medallaEntity) {
        LOGGER.log(Level.INFO, "Actualizando medalla con id = {0}", medallaEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el vuelo con id = {0}", medallaEntity.getId());
        return em.merge(medallaEntity);
    }
    
        /**
     * Borra una medalla de la base de datos recibiendo como argumento el id
     * de la medalla
     *
     * @param medallaId: id correspondiente a la medalla a borrar.
     */
    public void delete(Long medallaId) {
        LOGGER.log(Level.INFO, "Borrando medalla con id = {0}", medallaId);
        MedallaEntity entity = em.find(MedallaEntity.class, medallaId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la medalla con id = {0}", medallaId);
    }
    
        /**
     * Busca si hay alguna medalla con el nombre que se envía de argumento
     *
     * @param nombre: Nombre de la medalla que se está buscando
     * @return null si no existe ningun proveedor con el nombre del argumento. Si
     * existe alguno devuelve el primero.
     */
    public MedallaEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando medallas por nombre ", nombre);
        // Se crea un query para buscar medallas con el nombre que recibe el método como argumento
        TypedQuery query = em.createQuery("Select e From MedallaEntity e where e.nombre = :nombre", MedallaEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<MedallaEntity> sameNombre = query.getResultList();
        MedallaEntity result;
        if (sameNombre == null) {
            result = null;
        } else if (sameNombre.isEmpty()) {
            result = null;
        } else {
            result = sameNombre.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar medalla por nombre ", nombre);
        return result;
    }
}
