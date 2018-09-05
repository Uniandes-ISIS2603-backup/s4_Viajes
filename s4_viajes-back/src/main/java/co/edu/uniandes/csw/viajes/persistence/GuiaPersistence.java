/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author estudiante
 */

@Stateless
public class GuiaPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(GuiaPersistence.class.getName());

    /**
     *
     */
    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Busca si hay algun guia con el id que se envía de argumento
     *
     * @param guia: guia correspondiente al guia buscado.
     * @return un guia.
     */
    public GuiaEntity find(Long documento) {
        LOGGER.log(Level.INFO, "Consultando guia con documento = {0}", documento);
        return em.find(GuiaEntity.class, documento);
        
    }

    /**
     * Devuelve todos los guias de la base de datos.
     *
     * @return una lista con todos los guias que encuentre en la base de
     * datos, "select u from GuiaEntity u" es como un "select * from
     * GuiaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<GuiaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los guias");
        Query q = em.createQuery("select u from GuiaEntity u");
        return q.getResultList();
    }

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param guiaEntity objeto premio que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public GuiaEntity create(GuiaEntity guiaEntity) {
        LOGGER.log(Level.INFO, "Creando un guia nuevo");
        em.persist(guiaEntity);
        LOGGER.log(Level.INFO, "Guia creado");
        return guiaEntity;
    }

    /**
     * Actualiza un premio.
     *
     * @param prizeEntity: el premio que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un premio con los cambios aplicados.
     */
    public GuiaEntity update(GuiaEntity guiaEntity) {
        LOGGER.log(Level.INFO, "Actualizando guia con id = {0}", guiaEntity.getId());
        return em.merge(guiaEntity);
    }

    /**
     *
     * Borra un premio de la base de datos recibiendo como argumento el id del
     * premio
     *
     * @param prizeId: id correspondiente al premio a borrar.
     */
    public void delete(Long guiaId) {
        LOGGER.log(Level.INFO, "Borrando guia con id = {0}", guiaId);
        GuiaEntity entity = em.find(GuiaEntity.class, guiaId);
        em.remove(entity);
    }
    
    public String findByDocumento(Long doc)
    {
        return "";
    }
}
