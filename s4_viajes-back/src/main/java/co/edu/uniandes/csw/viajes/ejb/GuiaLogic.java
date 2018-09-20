/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;


import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.GuiaPersistence;
import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */

@Stateless
public class GuiaLogic {
    
     private static final Logger LOGGER = Logger.getLogger(GuiaLogic.class.getName());


    @Inject
    private GuiaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una guia en la persistencia.
     *
     * @param guiaEntity La guia que representa la guia a
     * persistir.
     * @return La entiddad de la editorial luego de persistirla.
     * @throws BusinessLogicException Si la editorial a persistir ya existe.
     */
    public GuiaEntity createGuia(GuiaEntity guiaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del guia");
        // Verifica la regla de negocio que dice que no puede haber dos editoriales con el mismo nombre
        if (guiaEntity.getActividad() == null)
        {
            throw new BusinessLogicException("No se puede encontrar la actividad especificada");
        }
        
        if (!validarDocumento(guiaEntity.getDocumento()))
        {
            throw new BusinessLogicException("El documento del guia es invalido");
        }
        if (persistence.findByDocumento(guiaEntity.getDocumento()) != null) {
            throw new BusinessLogicException("Ya existe una Guia con el documento \"" + guiaEntity.getDocumento() + "\"");
        }
        // Invoca la persistencia para crear la editorial
        persistence.create(guiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la editorial");
        return guiaEntity;
    }

    
    public GuiaEntity getGuia(Long guiaId) {
        
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la guia con id = {0}", guiaId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
       
        GuiaEntity guiaEntity = persistence.find(guiaId);
        if (guiaEntity == null) {
            LOGGER.log(Level.SEVERE, "La editorial con el id = {0} no existe", guiaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la editorial con id = {0}", guiaId);
        return guiaEntity;
        
        
    }
    
    public GuiaEntity modificarGuia(Long id, GuiaEntity guiaEntity)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la guia con id = {0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        if (!validarDocumento(guiaEntity.getDocumento()))
        {
            throw new BusinessLogicException("El id es invalido");
        }
 
        GuiaEntity newEntity = persistence.update(guiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la guia con id = {0}", guiaEntity.getId());
        return new GuiaEntity();
    }

    
    /**
     * Borrar un editorial
     *
     * @param editorialsId: id de la editorial a borrar
     */
    public void deleteGuia(Long guiaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la actividad con id = {0}", guiaId);
        
        
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delete(guiaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la actividad con id = {0}", guiaId);
    }
    
    private boolean validarDocumento(Long doc)
    {
    return !(doc == null || doc <= 0L);
     }


} 

