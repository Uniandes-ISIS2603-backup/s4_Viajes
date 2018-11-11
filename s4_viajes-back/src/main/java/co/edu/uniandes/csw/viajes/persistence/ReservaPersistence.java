/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
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
public class ReservaPersistence {
    private static final Logger LOGGER = Logger.getLogger(ReservaPersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param reservaEntity objeto reserva que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ReservaEntity create(ReservaEntity reservaEntity) {
        LOGGER.log(Level.INFO, "Creando una reserva nueva");
        em.persist(reservaEntity);
        LOGGER.log(Level.INFO, "Reserva creado");
        return reservaEntity;
    }

    /**
     * Devuelve todos los pagos de la base de datos.
     *
     * @return una lista con todos los libros que encuentre en la base de datos,
     * "select u from ReservaEntity u" es como un "select * from ReservaEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<ReservaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las reservas");
        TypedQuery query = em.createQuery("select u from ReservaEntity u", ReservaEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algun pago con el id que se envía de argumento
     *
     * @param reservaId: id correspondiente al pago buscado.
     * @return un pago.
     */
    public ReservaEntity find(Long reservaId) {
        LOGGER.log(Level.INFO, "Consultando la reserva con id={0}", reservaId);
        return em.find(ReservaEntity.class, reservaId);
    }

    /**
     * Actualiza un pago.
     *
     * @param reservaEntity: la reserva que viene con los nuevos cambios. 
     * @return una reserva con los cambios aplicados.
     */
    public ReservaEntity update(ReservaEntity reservaEntity) {
        LOGGER.log(Level.INFO, "Actualizando la reserva con id={0}", reservaEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la reserva con id={0}", reservaEntity.getId());

        return em.merge(reservaEntity);
    }

    /**
     *
     * Borra un pago de la base de datos recibiendo como argumento el id del
     * pago
     *
     * @param reservaId: id correspondiente al pago a borrar.
     */
    public void delete(Long reservaId) {
        LOGGER.log(Level.INFO, "Borrando la reserva con id={0}", reservaId);
        ReservaEntity reservaEntity = em.find(ReservaEntity.class, reservaId);
        em.remove(reservaEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la reserva con id={0}", reservaId);

    }
}
