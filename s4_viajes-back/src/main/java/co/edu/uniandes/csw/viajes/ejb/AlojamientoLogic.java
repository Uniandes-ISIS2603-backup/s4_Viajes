/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.AlojamientoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class AlojamientoLogic {

    public static final Logger LOGGER = Logger.getLogger(AlojamientoLogic.class.getName());

    @Inject
    private AlojamientoPersistence persistence;

    /**
     * Guardar un nuevo alojamiento
     *
     * @param alojamientoEntity La entidad de tipo alojamiento del nuevo
     * alojamiento a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el nombre es inválido o ya existe en la
     * persistencia.
     */
    public AlojamientoEntity createAlojamiento(AlojamientoEntity alojamientoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del alojamiento");

        String nombreParam = alojamientoEntity.getNombre();

        //Valida que el nombre de la entidad parametro no genere excepcion
        if (persistence.find(alojamientoEntity.getId()) == null) {
            throw new BusinessLogicException("El alojamiento es inválido");
        }
        if (!validateNombre(nombreParam)) {
            throw new BusinessLogicException("El nombre ingresado es inválido:" + nombreParam);
        }
        if (persistence.findByNombre(nombreParam) != null) {
            throw new BusinessLogicException("El nombre ingresado ya existe:" + nombreParam);
        }
        
        //Crea el alojamiento en la persistencia
        persistence.create(alojamientoEntity);

        LOGGER.log(Level.INFO, "Termina proceso de creación del alojamiento");
        return alojamientoEntity;
    }

    private boolean validateNombre(String nombre) {
        Pattern pat = Pattern.compile("[a-zA-Z]{1,15}");
        Matcher mat = pat.matcher(nombre);
        return (mat.matches() && !(nombre == null || nombre.isEmpty()));
    }

    /**
     * Devuelve todos los alojamientos que hay en la base de datos.
     *
     * @return Lista de entidades de tipo alojamiento.
     */
    public List<AlojamientoEntity> getAlojamientos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los alojamientos.");
        List<AlojamientoEntity> alojamientos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los alojamientos.");
        return alojamientos;
    }

    /**
     * Busca un alojamiento por ID
     *
     * @param alojamientoId El id del alojamiento a buscar
     * @return El alojamiento encontrado, null si no lo encuentra.
     */
    public AlojamientoEntity getAlojamiento(Long alojamientoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el alojamiento con id = {0}", alojamientoId);
//        AlojamientoEntity alojamientoEntity = persistence.find(alojamientoId);
        if (alojamientoId == null) {
            LOGGER.log(Level.SEVERE, "El alojamiento con el id = {0} no existe", alojamientoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el alojamiento con id = {0}", alojamientoId);
//        return alojamientoEntity;
        return null;
    }

    /**
     * Actualizar un alojamiento por ID
     *
     * @param alojamientoId El ID del alojamiento a actualizar
     * @param alojamientoEntity La entidad del aoljamiento con los cambios
     * deseados
     * @return La entidad del alojamiento luego de actualizarla
     * @throws BusinessLogicException Si el nombre de la actualización es
     * inválido
     */
    public AlojamientoEntity updateAlojamiento(Long alojamientoId, AlojamientoEntity alojamientoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el alojamiento con id = {0}", alojamientoId);
        if (!validateNombre(alojamientoEntity.getNombre())) {
            throw new BusinessLogicException("El nombre es inválido");
        }
//        AlojamientoEntity newEntity = persistence.update(alojamientoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el alojamiento con id = {0}", alojamientoEntity.getId());
//        return newEntity; 
        return null;

    }

    /**
     * Eliminar un alojamiento por ID
     *
     * @param alojamientoId El ID del alojamiento a eliminar
     * @throws BusinessLogicException
     */
    public void deleteAlojamiento(Long alojamientoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el alojamiento con id = {0}", alojamientoId);
        if (alojamientoId == null) {
            throw new BusinessLogicException("No se puede borrar el alojamiento con id = " + alojamientoId + " porque el id es invalido");
        }
//        persistence.delete(alojamientoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el libro con id = {0}", alojamientoId);
    }
}
