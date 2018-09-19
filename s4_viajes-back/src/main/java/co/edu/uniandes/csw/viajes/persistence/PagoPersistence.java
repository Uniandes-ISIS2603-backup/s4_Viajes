/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.PagoEntity;
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
public class PagoPersistence {
    private static final Logger LOGGER = Logger.getLogger(PagoPersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param pagoEntity objeto libro que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PagoEntity create(PagoEntity pagoEntity) {
        LOGGER.log(Level.INFO, "Creando un pago nuevo");
        em.persist(pagoEntity);
        LOGGER.log(Level.INFO, "Pago creado");
        return pagoEntity;
    }

    /**
     * Devuelve todos loslibros de la base de datos.
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
     * Busca si hay algun lubro con el id que se envía de argumento
     *
     * @param pagoId: id correspondiente al libro buscado.
     * @return un libro.
     */
    public PagoEntity find(Long pagoId) {
        LOGGER.log(Level.INFO, "Consultando el pago con id={0}", pagoId);
        return em.find(PagoEntity.class, pagoId);
    }

    /**
     * Actualiza un libro.
     *
     * @param pagoEntity: el libro que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un libro con los cambios aplicados.
     */
    public PagoEntity update(PagoEntity pagoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el pago con id={0}", pagoEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el pago con id={0}", pagoEntity.getId());

        return em.merge(pagoEntity);
    }

    /**
     *
     * Borra un libro de la base de datos recibiendo como argumento el id del
     * libro
     *
     * @param pagoId: id correspondiente al libro a borrar.
     */
    public void delete(Long pagoId) {
        LOGGER.log(Level.INFO, "Borrando el pago con id={0}", pagoId);
        PagoEntity pagoEntity = em.find(PagoEntity.class, pagoId);
        em.remove(pagoEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el pago con id={0}", pagoId);

    }

    
}
