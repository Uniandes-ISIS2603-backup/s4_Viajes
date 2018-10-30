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
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Esteban Cantor
 */

@Stateless
public class GuiaPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(GuiaPersistence.class.getName());

    @PersistenceContext(unitName = "TripBuilderTeamPU")
    protected EntityManager em;

    /**
     * Busca si hay algun guia con el id que se envía de argumento
     *
     * @param documento: documento correspondiente al guia buscado.
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
        TypedQuery q = em.createQuery("select u from GuiaEntity u",GuiaEntity.class);
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
     * Actualiza un guia.
     *
     * @param guiaEntity: el premio que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un premio con los cambios aplicados.
     */
    public GuiaEntity update(GuiaEntity guiaEntity) {
        LOGGER.log(Level.INFO, "Actualizando guia con id = {0}", guiaEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar el guiacon id = {0}", guiaEntity.getId());
        return em.merge(guiaEntity);
    }

    /**
     *
     * Borra un guia de la base de datos recibiendo como argumento el id del
     * guia
     *
     * @param prizeId: id correspondiente al guia a borrar.
     */
    public void delete(Long guiaId) {
        LOGGER.log(Level.INFO, "Borrando guia con id = {0}", guiaId);
        GuiaEntity entity = findByDocumento(guiaId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la actividad con id = {0}", guiaId);
    }
    
     public void deleteAll()
    {
      List<GuiaEntity> list = findAll();
      
      for(GuiaEntity e: list)
      {
          em.remove(e);
      }
    }
    
    
    public GuiaEntity findByDocumento(Long documento)
    {
           LOGGER.log(Level.INFO, "Consultando guias por documento ", documento);
        // Se crea un query para buscar guias con el documento que recibe el método como argumento. ":doc" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From GuiaEntity e where e.documento = :documento", GuiaEntity.class);
        // Se remplaza el placeholder ":doc" con el valor del argumento 
        query = query.setParameter("documento", documento);
        // Se invoca el query se obtiene la lista resultado
        List<GuiaEntity> sameDoc = query.getResultList();
        GuiaEntity result;
        if (sameDoc == null) {
            result = null;
        } else if (sameDoc.isEmpty()) {
            result = null;
        } else {
            result = sameDoc.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar guias por documento ", documento);
        return result;
    }
}
