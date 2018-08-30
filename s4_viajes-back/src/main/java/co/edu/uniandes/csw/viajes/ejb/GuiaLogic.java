/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

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
    //private EditorialPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una editorial en la persistencia.
     *
     * @param editorialEntity La entidad que representa la editorial a
     * persistir.
     * @return La entiddad de la editorial luego de persistirla.
     * @throws BusinessLogicException Si la editorial a persistir ya existe.
     */
    public GuiaEntity createGuia(GuiaEntity guiaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la editorial");
        // Verifica la regla de negocio que dice que no puede haber dos editoriales con el mismo nombre
        /**if (persistence.findByName(editorialEntity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una Editorial con el nombre \"" + editorialEntity.getName() + "\"");
        }**/
        // Invoca la persistencia para crear la editorial
        //persistence.create(editorialEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la editorial");
        return guiaEntity;
    }

    /**
     * Borrar un editorial
     *
     * @param editorialsId: id de la editorial a borrar
     */
    public void deleteEditorial(Long editorialsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la editorial con id = {0}", editorialsId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        //persistence.delete(editorialsId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la editorial con id = {0}", editorialsId);
    }
    
}
