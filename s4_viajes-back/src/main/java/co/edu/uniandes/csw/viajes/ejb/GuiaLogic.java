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
import java.util.List;
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
         if (validarDocumentoExistente(guiaEntity.getDocumento()))
        {
            throw new BusinessLogicException("Ya existe un guia con ese documento");
        }
        
        if (!validarDocumento(guiaEntity.getDocumento()))
        {
            throw new BusinessLogicException("El documento del guia es invalido");
        }
        
        if (!validarPuntuacion(guiaEntity.getPuntuacion()))
        {
            throw new BusinessLogicException("La puntuacion del guia es invalida");
        }
        if (persistence.findByDocumento(guiaEntity.getDocumento()) != null) {
            throw new BusinessLogicException("Ya existe una Guia con el documento \"" + guiaEntity.getDocumento() + "\"");
        }
        // Invoca la persistencia para crear el guia
        persistence.create(guiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del guia");
        return guiaEntity;
    }

    
    public GuiaEntity getGuia(Long guiaId) {
        
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la guia con id = {0}", guiaId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
       
        GuiaEntity guiaEntity = persistence.findByDocumento(guiaId);
        if (guiaEntity == null) {
            LOGGER.log(Level.SEVERE, "La guia con el id = {0} no existe", guiaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la guia con id = {0}", guiaId);
        return guiaEntity;
        
        
    }
    
    public GuiaEntity modificarGuia(Long id, GuiaEntity guiaEntity)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la guia con id = {0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        if (!validarDocumento(guiaEntity.getDocumento()))
        {
            throw new BusinessLogicException("El id es invalido");
        }
        
        if (!validarPuntuacion(guiaEntity.getPuntuacion()))
        {
            throw new BusinessLogicException("La puntuacion es invalida");
        }
 
        GuiaEntity newEntity = persistence.update(guiaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la guia con id = {0}", guiaEntity.getId());
        return new GuiaEntity();
    }

    
    /**
     * Borrar un editorial
     *
     * @param guiaId: id de la editorial a borrar
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    public void deleteGuia(Long guiaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la actividad con id = {0}", guiaId);
        
        GuiaEntity e = persistence.findByDocumento(guiaId);
        if(!validarGuiaExistente(e))
        {
            throw new BusinessLogicException("El guia a eliminar no existe");
        }
        
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delete(guiaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la actividad con id = {0}", guiaId);
    }
    
      public void deleteAll(){
        persistence.deleteAll();
    }

    
    
      /**
     * Devuelve todos los guias que hay en la base de datos.
     *
     * @return Lista de guias.
     */
    public List<GuiaEntity> getGuias() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los guias");
        List<GuiaEntity> guias = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos las actividades");
        return guias;
    }
    
    private boolean validarDocumento(Long doc)
    {
    return !(doc == null || doc <= 0L);
     }
    
    private boolean validarPuntuacion(int p)
    {return !(p > 10 || p <0);}
    
    private boolean validarGuiaExistente(GuiaEntity e)
    {return !(e == null);}
    
    private boolean validarDocumentoExistente(Long doc)
    {
        return !(persistence.findByDocumento(doc) == null);
    }
      
    

} 

