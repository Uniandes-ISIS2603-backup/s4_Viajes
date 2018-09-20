/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
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
public class AlojamientoPersistence {

    private static final Logger LOGGER = Logger.getLogger(AlojamientoPersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param alojamientoEntity objeto alojamiento que se creará en la base de
     * datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public AlojamientoEntity create(AlojamientoEntity alojamientoEntity) {
        LOGGER.log(Level.INFO, "Creando un alojamiento nuevo");
        em.persist(alojamientoEntity);
        LOGGER.log(Level.INFO, "Alojamiento creado");
        return alojamientoEntity;
    }

        /**
     * Busca si hay algun alojamiento con el id que se envía de argumento
     *
     * @param alojamientosId: id correspondiente al alojamiento buscado.
     * @param proveedoresId: id del proveedor.
     * @return un alojamiento.
     */
    public AlojamientoEntity find(Long proveedoresId,Long alojamientosId) {
        LOGGER.log(Level.INFO, "Consultando el alojamiento con id={0} del proveedor con id = " + proveedoresId, alojamientosId);
        TypedQuery<AlojamientoEntity> q = em.createQuery("select p from AlojamientoEntity where{p.proveedor.id = :proveedoresid} and {p.id = : alojamientosId}", AlojamientoEntity.class);
        q.setParameter("proveedoresid", proveedoresId);
        q.setParameter("alojamientosId", alojamientosId); 
        List<AlojamientoEntity> alojamientos = q.getResultList();
        AlojamientoEntity alojamiento = null; 
        if(alojamientos == null){
            alojamiento = null;
        } else if(alojamientos.isEmpty()){
            alojamiento = null;
        } else if(alojamientos.size() >= 1){
            alojamiento = alojamientos.get(0); 
        }
        return alojamiento;
    }

    /**
     * Actualiza un alojamiento.
     *
     * @param alojamientoEntity: el alojamiento que viene con los nuevos
     * cambios. Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso
     * del método update.
     * @return un alojamiento con los cambios aplicados.
     */
    public AlojamientoEntity update(AlojamientoEntity alojamientoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el alojamiento con id={0}", alojamientoEntity.getId());
        return em.merge(alojamientoEntity);
    }

    /**
     * Borra un alojamiento de la base de datos recibiendo como argumento el id
     * del alojamiento
     *
     * @param alojamientosId: id correspondiente al alojamiento a borrar.
     */
    public void delete(Long alojamientosId) {
        LOGGER.log(Level.INFO, "Borrando el alojamiento con id={0}", alojamientosId);
        AlojamientoEntity alojamientoEntity = em.find(AlojamientoEntity.class, alojamientosId);
        em.remove(alojamientoEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar El alojamiento con id = {0}", alojamientosId);
    }

    /**
     * Busca si hay algun alojamiento con el nombre que se envía de argumento
     *
     * @param nombreAlojamiento: nombre de alojamiento que se está buscando
     * @return null si no existe ningun alojameinto con el nombre del argumento.
     * Si existe alguno devuelve el primero.
     */
    public AlojamientoEntity findByNombre(String nombreAlojamiento) {
        LOGGER.log(Level.INFO, "Consultando alojamientos por nombre ", nombreAlojamiento);
        // Se crea un query para buscar alojamientos con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From AlojamientoEntity e where e.nombre = :nombre", AlojamientoEntity.class);
        // Se remplaza el placeholder ":isbn" con el valor del argumento 
        query = query.setParameter("nombre", nombreAlojamiento);
        // Se invoca el query se obtiene la lista resultado
        List<AlojamientoEntity> sameNombre = query.getResultList();
        AlojamientoEntity result;
        if (sameNombre == null || sameNombre.isEmpty()) {
            result = null;
        } else {
            result = sameNombre.get(0);
        }

        LOGGER.log(Level.INFO, "Saliendo de consultar alojamientos por nombre.", nombreAlojamiento);
        return result;
    }
}
