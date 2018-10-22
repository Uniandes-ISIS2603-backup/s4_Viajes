/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
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
public class TransporteTerrestrePersistence {

    private static final Logger LOGGER = Logger.getLogger(TransporteTerrestrePersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param transporteEntity objeto alojamiento que se creará en la base de
     * datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public TransporteTerrestreEntity create(TransporteTerrestreEntity transporteEntity) {
        LOGGER.log(Level.INFO, "Creando un transporte nuevo");
        em.persist(transporteEntity);
        LOGGER.log(Level.INFO, "Transporte creado");
        return transporteEntity;
    }

    /**
     * Devuelve todos los transportes de la base de datos.
     *
     * @return una lista con todos los transportes que encuentre en la base de
     * datos, "select u from AlojamientoEntity u" es como un "select * from
     * TransporteTerrestreEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<TransporteTerrestreEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los transportes");
        TypedQuery q = em.createQuery("select u from TransporteTerrestreEntity u", TransporteTerrestreEntity.class);
        return q.getResultList();
    }

    /**
     * Busca si hay algun transporte con el id que se envía de argumento
     *
     * @param transportesId: id correspondiente al alojamiento buscado.
     * @return un transporte.
     */
    public TransporteTerrestreEntity find(Long transportesId) {
        LOGGER.log(Level.INFO, "Consultando el transporte con id = {0}", transportesId);
        return em.find(TransporteTerrestreEntity.class, transportesId);
    }

    /**
     * Actualiza un transporte.
     *
     * @param transporteTerrestreEntity: el alojamiento que viene con los nuevos
     * cambios. Por ejemplo el id pudo cambiar. En ese caso, se haria uso del
     * método update.
     * @return un alojamiento con los cambios aplicados.
     */
    public TransporteTerrestreEntity update(TransporteTerrestreEntity transporteTerrestreEntity) {
        LOGGER.log(Level.INFO, "Actualizando el transporte con id={0}", transporteTerrestreEntity.getId());
        return em.merge(transporteTerrestreEntity);
    }

    /**
     * Borra un transporte de la base de datos recibiendo como argumento el id
     * del transporte
     *
     * @param transportesId: id correspondiente al alojamiento a borrar.
     */
    public void delete(Long transportesId) {
        LOGGER.log(Level.INFO, "Borrando el transporte con id={0}", transportesId);
        TransporteTerrestreEntity transporteEntity = em.find(TransporteTerrestreEntity.class, transportesId);
        em.remove(transporteEntity);
    }
}
