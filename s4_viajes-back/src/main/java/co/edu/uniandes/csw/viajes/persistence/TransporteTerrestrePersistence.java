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
import javax.persistence.Query;
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
        Query q = em.createQuery("select u from TransporteTerrestreEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay algun transporte con el id que se envía de argumento
     *
     * @param transportesId: id correspondiente al alojamiento buscado.
     * @return un alojamiento.
     */
    public TransporteTerrestreEntity find(Long proveedoresId, Long transportesId) {
        LOGGER.log(Level.INFO, "Consultando el transporte con id={0} del proveedor con id = " + proveedoresId, transportesId);
        TypedQuery<TransporteTerrestreEntity> q = em.createQuery("select p from TransporteTerrestreEntity where{p.proveedor.id = :proveedoresid} and {p.id = : transportesId}", TransporteTerrestreEntity.class);
        q.setParameter("proveedoresid", proveedoresId);
        q.setParameter("alojamientosId", transportesId);  
        List<TransporteTerrestreEntity> transportes = q.getResultList();
        TransporteTerrestreEntity transporte = null; 
        if(transportes == null){
            transporte = null;
        } else if(transportes.isEmpty()){
            transporte = null;
        } else if(transportes.size() >= 1){
            transporte = transportes.get(0); 
        }
        return transporte;
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
