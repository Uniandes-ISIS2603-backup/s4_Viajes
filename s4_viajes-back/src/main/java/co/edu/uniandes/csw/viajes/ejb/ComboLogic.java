/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ComboPersistence;
import co.edu.uniandes.csw.viajes.persistence.ReservaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
public class ComboLogic {
    public static final Logger LOGGER = Logger.getLogger(ComboLogic.class.getName());
    
    @Inject
    private ComboPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

     @Inject
    private ReservaPersistence reservaPersistence;
    
     /**
     * Guardar un nuevo combo. 
     * @param comboEntity La entidad de tipo ComboEntity del nuevo combo a persistir.
     * @return El combo luego de persistirla
     * @throws Exception En caso que la entidad sea nula.
     */
    public ComboEntity createCombo(ComboEntity comboEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del combo"); 
        if(comboEntity == null)
            throw new BusinessLogicException("Error en el formato.");
        // Verifica la regla de negocio que El combo debe tener un nombre
        if(comboEntity.getNombre().trim().equals(""))
            throw new BusinessLogicException("El combo debe tener un nombre\"" + comboEntity.getNombre() + "\"");
        // Verifica la regla de negocio que La puntuación del combo debe estar entre 0 y 5
        if(comboEntity.getPuntuacion()<0||comboEntity.getPuntuacion()>5)
            throw new BusinessLogicException("La puntuación del combo debe estar entre 0 y 5 \"" + comboEntity.getNombre() + "\"");
        double costo=0;
        for(long idReserva : comboEntity.getIdsReservas())
            {
               ReservaEntity reserva = reservaPersistence.find(idReserva);
               if(reserva==null)
                   {
//                           throw new BusinessLogicException("El combo reserva que envio no existe");
                    }
               else
               {
                   comboEntity.addReserva(reserva);
                   costo+=reserva.getCosto();
               }
            } 
        comboEntity.setCosto(costo);
        
        comboEntity =persistence.create(comboEntity);

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
        List<ComboEntity> combos = persistence.findAll();
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
        ComboEntity comboEntity = persistence.find(comboId);
        if (comboEntity == null) {
            LOGGER.log(Level.SEVERE, "El combo con el id = {0} no existe", comboId);
        }
        double costo=0;
         for(long idReserva : comboEntity.getIdsReservas())
            {
               ReservaEntity reserva = reservaPersistence.find(idReserva);
               if(reserva==null)
                   {
//                           throw new BusinessLogicException("El combo reserva que envio no existe");
                    }
               else
                {
                   comboEntity.addReserva(reserva);
                   costo+=reserva.getCosto();
                }            
            } 

        LOGGER.log(Level.INFO, "Termina proceso de consultar el combo con id = {0}", comboId);
        return comboEntity;  
    }
    
     /**
     * Actualizar un combo por ID
     * @param comboId El ID del combo a actualizar
     * @param comboEntity La entidad del aoljamiento con los cambios deseados
     * @return La entidad del alojamiento luego de actualizarla
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    public ComboEntity updateCombo(Long comboId, ComboEntity comboEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el combo con id = {0}", comboId);
        if(comboId == null)
            throw new BusinessLogicException("Identificador del combo inexistente.");
        if(comboEntity == null)
            throw new BusinessLogicException("Error en el formato.");
        // Verifica la regla de negocio que El combo debe tener un nombre
        if(comboEntity.getNombre().trim().equals(""))
            throw new BusinessLogicException("El combo debe tener un nombre\"" + comboEntity.getNombre() + "\"");
        // Verifica la regla de negocio que El costo del combo no pueden ser negativo
        if(comboEntity.getCosto()<0)
            throw new BusinessLogicException("El costo del combo no pueden ser negativo \"" + comboEntity.getNombre() + "\"");
        // Verifica la regla de negocio que Los dias de duración del combo no pueden ser 0 ni negativos
        if(comboEntity.getDias()<=0)
            throw new BusinessLogicException("Los dias de duración del combo no pueden ser 0 ni negativos \"" + comboEntity.getNombre() + "\"");
        // Verifica la regla de negocio que Las de duración del combo no pueden ser negativas
        if(comboEntity.getHoras()<0)
            throw new BusinessLogicException("Las de duración del combo no pueden ser negativas \"" + comboEntity.getNombre() + "\"");
        // Verifica la regla de negocio que La puntuación del combo debe estar entre 0 y 5
        if(comboEntity.getPuntuacion()<0||comboEntity.getPuntuacion()>5)
            throw new BusinessLogicException("La puntuación del combo debe estar entre 0 y 5 \"" + comboEntity.getNombre() + "\"");
        
        
        ComboEntity newEntity = persistence.update(comboEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el combo con id = {0}", comboEntity.getId());
        return newEntity;
    }
    
     /**
     * Eliminar un combo por ID
     * @param comboId El ID del combo a eliminar
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    public void deleteCombo(Long comboId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el combo con id = {0}", comboId);
        if(comboId == null)
          throw new BusinessLogicException("Identificador del combo inexistente.");

        persistence.delete(comboId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el combo con id = {0}", comboId);
   
    }
}
