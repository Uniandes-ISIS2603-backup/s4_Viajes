/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.AlojamientoPersistence;
import co.edu.uniandes.csw.viajes.persistence.ProveedorPersistence;
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

    @Inject
    private ProveedorPersistence proveedorPersistence;

    /**
     * Guardar un nuevo alojamiento
     *
     * @param alojamientoEntity La entidad de tipo alojamiento del nuevo
     * @param proveedoresId
     * alojamiento a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el nombre es inválido o ya existe en la
     * persistencia.
     */
    public AlojamientoEntity createAlojamiento(Long proveedoresId , AlojamientoEntity alojamientoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del alojamiento");
        ProveedorEntity proveedor = proveedorPersistence.find(proveedoresId);
        alojamientoEntity.setProveedor(proveedor);  
        if (alojamientoEntity.getProveedor() == null || proveedorPersistence.find(alojamientoEntity.getProveedor().getId()) == null) {
            throw new BusinessLogicException("El proveedor es inválido");
        }
        if (!validateNombre(alojamientoEntity.getNombre())) {
            throw new BusinessLogicException("El nombre ingresado es inválido:" + alojamientoEntity.getNombre());
        }
        if (persistence.findByNombre(alojamientoEntity.getNombre()) != null) {
            throw new BusinessLogicException("El nombre ingresado ya existe:" + alojamientoEntity.getNombre());
        }

        if (persistence.find(proveedor.getId(), alojamientoEntity.getId()) != null) {
            throw new BusinessLogicException("El alojamiento es inválido");
        }

        ProveedorEntity proveedorEntity = proveedorPersistence.find(alojamientoEntity.getProveedor().getId());
        proveedorEntity.getAlojamientos().add(alojamientoEntity);
        if (proveedorEntity == null) {
            throw new BusinessLogicException("El proveedor es inválido");
        }

        if (alojamientoEntity.getCosto() <= 0) {
            throw new BusinessLogicException("El costo ingresado es invalido:" + alojamientoEntity.getCosto());
        }
        if (alojamientoEntity.getEstrellas() <= 0) {
            throw new BusinessLogicException("Las estrellas ingresadas son invalidas:" + alojamientoEntity.getEstrellas());
        }

        //Crea el alojamiento en la persistencia
        LOGGER.log(Level.INFO, "Termina proceso de creación del alojamiento");
        return persistence.create(alojamientoEntity);
    }

    private boolean validateNombre(String nombre) {
        Pattern pat = Pattern.compile("[a-zA-Z]{1,25}");
        Matcher mat = pat.matcher(nombre);
        return (mat.matches() && !nombre.isEmpty());
    }

    /**
     * Devuelve todos los alojamientos que hay en la base de datos.
     *
     * @param proveedoresId
     * @return Lista de entidades de tipo alojamiento.
     */
    public List<AlojamientoEntity> getAlojamientos(Long proveedoresId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los alojamientos.");
       ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedoresId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los alojamientos.");
        return proveedorEntity.getAlojamientos();  
    }

    /**
     * Busca un alojamiento por ID
     *
     * @param alojamientoId El id del alojamiento a buscar
     * @param proveedoresId id del proveedor
     * @return El alojamiento encontrado.
     * @throws BusinessLogicException
     */
    public AlojamientoEntity getAlojamiento(Long proveedoresId , Long alojamientoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el alojamiento con id = {0}", alojamientoId);
        //Aun no esta definido si un ID es 0
        if (alojamientoId == null) {
            LOGGER.log(Level.SEVERE, "El alojamiento con el id = {0} no existe", alojamientoId);
            throw new BusinessLogicException("Error en el id buscado" + alojamientoId);
        }
        AlojamientoEntity alojamientoEntity = persistence.find(proveedoresId, alojamientoId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el alojamiento con id = {0}", alojamientoId);
        return alojamientoEntity;
    }

    /**
     * Actualizar un alojamiento por ID
     *
     * @param proveedoresId El ID del alojamiento a actualizar
     * @param alojamientoEntity La entidad del aoljamiento con los cambios
     * deseados
     * @return La entidad del alojamiento luego de actualizarla
     * @throws BusinessLogicException Si el nombre de la actualización es
     * inválido
     */
    public AlojamientoEntity updateAlojamiento(Long proveedoresId, AlojamientoEntity alojamientoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el alojamiento con id = {0}", proveedoresId);
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedoresId); 
        alojamientoEntity.setProveedor(proveedorEntity); 
        persistence.update(alojamientoEntity); 
       
        String nombreParam = alojamientoEntity.getNombre();
        if (!validateNombre(nombreParam)) {
            throw new BusinessLogicException("El nombre es inválido" + nombreParam);
        }
        if (alojamientoEntity.getCosto() < 0) {
            throw new BusinessLogicException("El costo del alojamiento es invalido." + alojamientoEntity.getCosto());
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el alojamiento con id = {0}", alojamientoEntity.getId());
        return alojamientoEntity; 

    }

    /**
     * Eliminar un alojamiento por ID
     *
     * @param alojamientoId El ID del alojamiento a eliminarw
     * @param proveedoresId
     * @throws BusinessLogicException
     */
    public void deleteAlojamiento(Long proveedoresId, Long alojamientoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el alojamiento con id = {0}", alojamientoId);
        AlojamientoEntity alojamiento = getAlojamiento(proveedoresId, alojamientoId);
        if (alojamientoId == null) {
            throw new BusinessLogicException("No se puede borrar el alojamiento con id: " + alojamientoId + ", porque es invalido.");
        }
        persistence.delete(alojamiento.getId());  
        LOGGER.log(Level.INFO, "Termina proceso de borrar el libro con id = {0}", alojamientoId);
    }
}
