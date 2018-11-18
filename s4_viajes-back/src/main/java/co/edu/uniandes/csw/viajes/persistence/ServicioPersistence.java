/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.persistence;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.ServicioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Diego Barrios
 */
@Stateless
public interface ServicioPersistence {
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param servicioEntity objeto servicio que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ServicioEntity create(ServicioEntity servicioEntity);
	
	/**
     * Devuelve todas las editoriales de la base de datos.
     *
     * @return una lista con todas los servicios que encuentre en la base de
     * datos, "select u from ServicioEntity u" es como un "select * from
     * EditorialEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<ServicioEntity> findAll();
    /**
     * Busca si hay alguna actividad con el id que se envía de argumento
     *
     * @param servicioId: id correspondiente al servicio buscado.
     * @return un servicio.
     */
    public ServicioEntity find(Long servicioId);

	 /**
     * Actualiza una actividad.
     *
     * @param servicioEntity: el servicio que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una actividad con los cambios aplicados.
     */
    public ServicioEntity update(ServicioEntity servicioEntity);
    /**
     *
     * Borra una actividad de la base de datos recibiendo como argumento el id
     * de la actividad
     *
     * @param servicioId: id correspondiente al servicio a borrar.
     */
    public void delete(Long servicioId);
    
    public void deleteAll();
	
    /**
     * Busca si hay algun Servicio con el nombre que se envía de argumento
     *
     * @param nombreServicio: Nombre del servicio que se está buscando
     * @return null si no existe ningun servicio con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public ServicioEntity findByName(String nombreServicio);
  
    
    public ServicioEntity findByIdentificador(Long identificador);
    
}
