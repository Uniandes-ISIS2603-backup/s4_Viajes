/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.EntradaEntity;
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
public class EntradaPersistence {
     private static final Logger LOGGER = Logger.getLogger(EntradaPersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad (Entrada) en la base de datos.
     *
     * @param entradaEntity objeto entrada que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public EntradaEntity create(EntradaEntity entradaEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo vuelo");
        em.persist(entradaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una entrada nueva");
        return entradaEntity;
    }

    /**
     * Devuelve todas las entradas de la base de datos.
     *
     * @return una lista con todas las entradas que encuentre en la base de datos,
     * "select u from EntradaEntity u" es como un "select * from EntradaEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<EntradaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las entradas");
        // Se crea un query para buscar todas las entradas en la base de datos.
        TypedQuery query = em.createQuery("select u from EntradaEntity u", EntradaEntity.class);
        return query.getResultList();
    }

    /**
     * Buscar una entrada
     *
     * Busca si hay alguna entrada asociada a un usuario y con un ID específico
     *
     * @param userId El ID del libro con respecto al cual se busca
     * @param entradaId El ID de la reseña buscada
     * @return La entrada encontrada o null. Nota: Si existe una o más entradas
     * devuelve siempre la primera que encuentra
     */
    public EntradaEntity find(Long userId, Long entradaId) {
        LOGGER.log(Level.INFO, "Consultando la entrada con id = {0} del usuario con id = " + entradaId, userId);
        TypedQuery<EntradaEntity> q = em.createQuery("select p from EntradaEntity p where (p.autor.id = :userid) and (p.id = :entradaId)", EntradaEntity.class);
        q.setParameter("userid", userId);
        q.setParameter("entradaId", entradaId);
        List<EntradaEntity> results = q.getResultList();
        EntradaEntity entrada = null;
        if (results == null) {
            entrada = null;
        } else if (results.isEmpty()) {
            entrada = null;
        } else if (results.size() >= 1) {
            entrada = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar la entrada con id = {0} del usuario con id =" + entradaId, userId);
        return entrada;
    }
    
    /**
     * Busca si hay algun pago con el id que se envía de argumento
     *
     * @param entradaId: id correspondiente al pago buscado.
     * @return un pago.
     */
    public EntradaEntity find(Long entradaId) {
        LOGGER.log(Level.INFO, "Consultando la entrada con id={0}", entradaId);
        return em.find(EntradaEntity.class, entradaId);
    }
    /**
     * Actualiza una entrada.
     *
     * @param entradaEntity: la entrada que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una entrada con los cambios aplicados.
     */
    public EntradaEntity update(EntradaEntity entradaEntity) {
        LOGGER.log(Level.INFO, "Actualizando entrada con id = {0}", entradaEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar entrada con id = {0}", entradaEntity.getId());
        return em.merge(entradaEntity);
    }
    
        /**
     * Borra una entrada de la base de datos recibiendo como argumento el id
     * del vuelo
     *
     * @param entradaId: id correspondiente a la entrada a borrar.
     */
    public void delete(Long entradaId) {
        LOGGER.log(Level.INFO, "Borrando entrada con id = {0}", entradaId);
        EntradaEntity entity = em.find(EntradaEntity.class, entradaId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la entrada con id = {0}", entradaId);
    }
    

}
