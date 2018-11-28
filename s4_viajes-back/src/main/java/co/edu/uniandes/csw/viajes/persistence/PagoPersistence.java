/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
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
public class PagoPersistence {
    private static final Logger LOGGER = Logger.getLogger(PagoPersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param pagoEntity objeto pago que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PagoEntity create(PagoEntity pagoEntity) {
        LOGGER.log(Level.INFO, "Creando un pago nuevo");
        em.persist(pagoEntity);
        LOGGER.log(Level.INFO, "Pago creado");
        return pagoEntity;
    }

    /**
     * Devuelve todos los pagos de la base de datos.
     *
     * @return una lista con todos los libros que encuentre en la base de datos,
     * "select u from BookEntity u" es como un "select * from BookEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<PagoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los pagos");
        TypedQuery query = em.createQuery("select u from PagoEntity u", PagoEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algun pago con el id que se envía de argumento
     *
     * @param pagoId: id correspondiente al pago buscado.
     * @return un pago.
     */
    public PagoEntity find(Long pagoId) {
        LOGGER.log(Level.INFO, "Consultando el pago con id={0}", pagoId);
        return em.find(PagoEntity.class, pagoId);
    }

    public PagoEntity findByIdReserva(Long idReservaAPagar) {
        TypedQuery query = em.createQuery("Select e From PagoEntity e where e.idReservaAPagar = :idReservaAPagar", PagoEntity.class);
        query = query.setParameter("idReservaAPagar", idReservaAPagar);
        List<PagoEntity> sameNombre = query.getResultList();
        PagoEntity result;
        if (sameNombre == null) {
            result = null;
        } else if (sameNombre.isEmpty()) {
            result = null;
        } else {
            result = sameNombre.get(0);
        }
        return result;
    
    }
    /**
     * Actualiza un pago.
     *
     * @param pagoEntity: el pago que viene con los nuevos cambios. 
     * @return un pago con los cambios aplicados.
     */
    public PagoEntity update(PagoEntity pagoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el pago con id={0}", pagoEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el pago con id={0}", pagoEntity.getId());

        return em.merge(pagoEntity);
    }

    /**
     *
     * Borra un pago de la base de datos recibiendo como argumento el id del
     * pago
     *
     * @param pagoId: id correspondiente al pago a borrar.
     */
    public void delete(Long pagoId) {
        LOGGER.log(Level.INFO, "Borrando el pago con id={0}", pagoId);
        PagoEntity pagoEntity = em.find(PagoEntity.class, pagoId);
        em.remove(pagoEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el pago con id={0}", pagoId);

    }

    
}
