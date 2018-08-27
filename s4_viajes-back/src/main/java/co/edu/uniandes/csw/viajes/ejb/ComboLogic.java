/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estudiante
 */
public class ComboLogic {
    public static final Logger LOGGER = Logger.getLogger(ComboLogic.class.getName());
    
    
     /**
     * Guardar un nuevo alojamiento. 
     * @param comboEntity La entidad de tipo ComboEntity del nuevo combo a persistir.
     * @return La entidad luego de persistirla
     * @throws Exception En caso que la entidad sea nula.
     */
    public ComboEntity createAlojamiento(ComboEntity comboEntity) throws Exception
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del combo"); 
        if(comboEntity == null)
            throw new Exception("Error en el formato.");
        LOGGER.log(Level.INFO, "Termina proceso de creación del combo");
        return comboEntity; 
    }
    
    /**
     * Devuelve todos los alojamientos que hay en la base de datos.
     * @return Lista de entidades de tipo alojamiento.
     */
    public List<ComboEntity> getCombos() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los combos de un carrito de compras.");
        List<ComboEntity> combos = new ArrayList<ComboEntity>(); 
//        List<ComboEntity> combos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar los combos de un carrito de compras.");
        return combos;
    }
    
    /**
     * Busca un combo por ID
     * @param comboId El id del combo a buscar
     * @return El combo encontrado, null si no lo encuentra.
     */
    public ComboEntity getCombo(Long comboId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el combo con id = {0}", comboId);
        ComboEntity comboEntity = new ComboEntity();
//        ComboEntity comboEntity = persistence.find(comboId);
        if (comboId == null) {
            LOGGER.log(Level.SEVERE, "El combo con el id = {0} no existe", comboId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el combo con id = {0}", comboId);
        return comboEntity;  
    }
    
     /**
     * Actualizar un combo por ID
     * @param comboId El ID del combo a actualizar
     * @param comboEntity La entidad del aoljamiento con los cambios deseados
     * @return La entidad del alojamiento luego de actualizarla
     */
    public ComboEntity updateCombo(Long comboId, ComboEntity comboEntity) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el combo con id = {0}", comboId);
        ComboEntity newEntity = new ComboEntity();
        if(comboId == null)
            return null; 
//        ComboEntity newEntity = persistence.update(comboEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el combo con id = {0}", comboEntity.getId());
        return newEntity;
    }
    
     /**
     * Eliminar un combo por ID
     * @param comboId El ID del combo a eliminar
     */
    public void deleteCombo(Long comboId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el combo con id = {0}", comboId);
      if(comboId == null)
          return;
//        persistence.delete(comboId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el combo con id = {0}", comboId);
    
    }
}
