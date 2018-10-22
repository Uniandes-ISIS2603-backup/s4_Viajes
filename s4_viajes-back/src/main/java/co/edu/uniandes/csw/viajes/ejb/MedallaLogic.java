/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.MedallaPersistence;
import co.edu.uniandes.csw.viajes.entities.MedallaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Luis Gòmez Amado
 */
@Stateless
public class MedallaLogic {
    private static final Logger LOGGER = Logger.getLogger(MedallaLogic.class.getName());

    @Inject
    private MedallaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una medalla en la persistencia.
     *
     * @param medallaEntity La entidad que representa la medalla a
     * persistir.
     * @return La entidad de la medalla luego de persistirla.
     * @throws BusinessLogicException Si la medalla a persistir ya existe.
     */
    public MedallaEntity createMedalla(MedallaEntity medallaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la medalla");
        
        if (persistence.findByName(medallaEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una medalla con el nombre \"" + medallaEntity.getNombre() + "\"");
        }
            
        validarReglasDeNegocio(medallaEntity);
        
        // Invoca la persistencia para crear la medalla
        persistence.create(medallaEntity);                              
        LOGGER.log(Level.INFO, "Termina proceso de creación de la medalla");
        return medallaEntity;
    }
    
    /**
     * Obtener todas los medallas existentes en la base de datos.
     *
     * @return una lista de medallas.
     */
    public List<MedallaEntity> getMedallas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los medallas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<MedallaEntity> medallas = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los medallas");
        return medallas;
    }

    /**
     * Obtener una medalla por medio de su id.
     *
     * @param medallaId: id de la medalla para ser buscada.
     * @return la medalla solicitada por medio de su id.
     */
    public MedallaEntity getMedalla(Long medallaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la medalla con id = {0}", medallaId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        MedallaEntity medallaEntity = persistence.find(medallaId);
        if (medallaEntity == null) {
            LOGGER.log(Level.SEVERE, "El medalla con el id = {0} no existe", medallaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la medalla con id = {0}", medallaId);
        return medallaEntity;
    }

    /**
     * Actualizar una medalla.
     *
     * @param medallaId: id de la medalla para buscarlo en la base de
     * datos.
     * @param medallaEntity: medalla con los cambios para ser actualizado,
     * por ejemplo el nombre.
     * @return la medalla con los cambios actualizados en la base de datos.
     */
    public MedallaEntity updateMedalla(Long medallaId, MedallaEntity medallaEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la medalla con id = {0} ", medallaId);
         //Verifica la regla de negocio que dice que no se puede actualizar el id de un vuelo con un vuelo que ya tenga ese id.
        if (persistence.find(medallaEntity.getId()) == null) 
        {
            throw new BusinessLogicException("No existe una medalla con el id \"" + medallaEntity.getId() + "\" por lo que no se puede modificar");
        }
        validarReglasDeNegocio(medallaEntity);
        
        MedallaEntity newEntity = persistence.update(medallaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la medalla con id = {0}", medallaEntity.getId());
        return newEntity;
    }

    /**
     * Borrar una medalla
     *
     * @param medallaId: id de la medalla a borrar
     * @throws BusinessLogicException Si la medalla tiene vuelos, transportes y actividades asociadas.
     */
    public void deleteMedalla(Long medallaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la medalla con id = {0}", medallaId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        MedallaEntity medalla = persistence.find(medallaId);
        if(medalla == null){
            throw new BusinessLogicException("La medalla que se quiere borrar no existe.");
        }
        persistence.delete(medallaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la medalla con id = {0}", medallaId);
    }
    
    /**
     * Metodo qeu valida las reglas de negocio en el create y el update
     * @param medallaEntity La medalla a la cual se le quieren validar las reglas de negocio.
     * @throws BusinessLogicException Si alguna de las reglas de negocio no se cumple.
     */
    public void validarReglasDeNegocio(MedallaEntity medallaEntity) throws BusinessLogicException{
        
        String input = medallaEntity.getNombre();
        
        if(input == null || input.equals("")){
            throw new BusinessLogicException("El nombre de la medalla no puede ser vacío o nulo.");
        }

        Pattern p1 = Pattern.compile("^[a-zA-Z0-9!-/:-@¿¡ ]{4,25}$");
        Matcher m1 = p1.matcher(input);

        if(!m1.find())
        {
            throw new BusinessLogicException("El nombre de la medalla debe tener mínimo 4 letras y mìnimo 25 letras y solo puede contener los siguientes caracteres especiales: ,.¡¿?!.");
        }
        
        input = medallaEntity.getDescripcion();
         
        if(input == null || input.equals("")){
            throw new BusinessLogicException("La descripción de la medalla no puede ser vacía o nula.");
        }
        Pattern p2 = Pattern.compile("^[A-Z¡¿0-9]{1}[a-zA-Z0-9!-/:-@¿¡\n ]{3,100}$");
        m1 = p2.matcher(input);
        
        if(!m1.find())
        {
            throw new BusinessLogicException("La descripciòn de la medalla debe tener mínimo 4 letras y maximo 100 letras.");
        }
        
        input = medallaEntity.getRutaImagen();
         
        if(input == null || input.equals("")){
            throw new BusinessLogicException("La ruta de la imagen no puede ser vacía o nula.");
        }
        Pattern p3 = Pattern.compile("[a-zA-Z0-9._]{8,30}$");
        m1 = p3.matcher(input);
        
        if(!m1.find())
        {
            throw new BusinessLogicException("La ruta de la imagen debe tener entre 8 y 30 caracteres.");
        }
    }
}
