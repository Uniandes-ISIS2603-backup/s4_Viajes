/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ComentarioEntity;
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
public class ComentarioPersistence {
         private static final Logger LOGGER = Logger.getLogger(ComentarioPersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad (Comentario) en la base de datos.
     *
     * @param commentEntity objeto comentario que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ComentarioEntity create(ComentarioEntity commentEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo comentario");
        em.persist(commentEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un comentario nuevo");
        return commentEntity;
    }

    /**
     * Devuelve todos los comentarios de la base de datos.
     *
     * @return una lista con todos los comentarios que encuentre en la base de datos,
     * "select u from EntradaEntity u" es como un "select * from EntradaEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<ComentarioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los comentarios");
        // Se crea un query para buscar todos los comentarios en la base de datos.
        TypedQuery query = em.createQuery("select u from ComentarioEntity u", ComentarioEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algún comentario con el id que se envía de argumento
     *
     * @param commentId: id correspondiente al comentario buscada.
     * @return un comentario.
     */
    public ComentarioEntity find(Long commentId) {
        LOGGER.log(Level.INFO, "Consultando entrada con id={0}", commentId);

        return em.find(ComentarioEntity.class, commentId);
    }
    
    /**
     * Actualiza un comentario.
     *
     * @param commentEntity: el comentario que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un comentario con los cambios aplicados.
     */
    public ComentarioEntity update(ComentarioEntity commentEntity) {
        LOGGER.log(Level.INFO, "Actualizando comentario con id = {0}", commentEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar comentario con id = {0}", commentEntity.getId());
        return em.merge(commentEntity);
    }
    
        /**
     * Borra un comentario de la base de datos recibiendo como argumento el id
     * del comentario
     *
     * @param commentId: id correspondiente al comentario a borrar.
     */
    public void delete(Long commentId) {
        LOGGER.log(Level.INFO, "Borrando comentario con id = {0}", commentId);
        ComentarioEntity entity = em.find(ComentarioEntity.class, commentId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el comentario con id = {0}", commentId);
    }
}
