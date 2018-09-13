/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.TransporteTerrestrePersistence;
import java.util.List;
//import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class TransporteTerrestreLogic {

    public static final Logger LOGGER = Logger.getLogger(TransporteTerrestreLogic.class.getName());

    @Inject
    private TransporteTerrestrePersistence persistence;

    /**
     * Guardar un nuevo alojamiento.
     *
     * @param transporteEntity La entidad de tipo alojamiento del nuevo
     * alojamiento a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException En caso que la entidad sea nula.
     */
    public TransporteTerrestreEntity createTransporte(TransporteTerrestreEntity transporteEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del transporte");
        if (transporteEntity.getId() == null || persistence.find(transporteEntity.getId()) == null) {
            throw new BusinessLogicException("El id del transporte es inválido" + transporteEntity.getId());
        }
        if (transporteEntity.getCosto() <= 0) {
            throw new BusinessLogicException("El costo del transporte es invalido." + transporteEntity.getCosto());
        }
        if (transporteEntity.getDestino().isEmpty() || transporteEntity.getDestino().equals("")) {
            throw new BusinessLogicException("El destino ingresado es invalido" + transporteEntity.getDestino());
        }
        if (transporteEntity.getNumeroDias() < 0 || transporteEntity.getNumeroHoras() < 0) {
            throw new BusinessLogicException("La duracion de viaje es invalida, numero dias: "
                    + "" + transporteEntity.getNumeroDias() + " numero horas: " + transporteEntity.getNumeroHoras());
        }

        persistence.create(transporteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del transporte");

        return transporteEntity;
    }

    /**
     * Devuelve todos los alojamientos que hay en la base de datos.
     *
     * @return Lista de entidades de tipo alojamiento.
     */
    public List<TransporteTerrestreEntity> getTransportes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los transportes.");
        List<TransporteTerrestreEntity> transportes = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los transportes");
        return transportes;
    }

    /**
     * Busca un alojamiento por ID
     *
     * @param transporteId El id del transpore a buscar
     * @return El alojamiento encontrado, null si no lo encuentra.
     */
    public TransporteTerrestreEntity getTransporte(Long transporteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el transporte con id = {0}", transporteId);
        if (transporteId == null) {
            LOGGER.log(Level.SEVERE, "El transporte con el id = {0} no existe", transporteId);
        }
        TransporteTerrestreEntity transporteEntity = persistence.find(transporteId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el transporte con id = {0}", transporteId);
        return transporteEntity;
    }

    /**
     * Actualizar un alojamiento por ID
     *
     * @param transporteId El ID del alojamiento a actualizar
     * @param transporteTerrestreEntity La entidad del aoljamiento con los
     * cambios deseados
     * @return La entidad del alojamiento luego de actualizarla
     * @throws BusinessLogicException
     */
    public TransporteTerrestreEntity updateTransporte(Long transporteId, TransporteTerrestreEntity transporteTerrestreEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el transporte con id = {0}", transporteId);
        if (transporteId == null) {
            throw new BusinessLogicException("El id es invalido" + transporteId);
        }
        if (transporteTerrestreEntity.getCosto() <= 0) {
            throw new BusinessLogicException("El costo del transporte es invalido." + transporteTerrestreEntity.getCosto());
        }
        if (transporteTerrestreEntity.getDestino().isEmpty() || transporteTerrestreEntity.getDestino().equals("")) {
            throw new BusinessLogicException("El destino ingresado es invalido" + transporteTerrestreEntity.getDestino());
        }
        persistence.update(transporteTerrestreEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el transporte con id = {0}", transporteTerrestreEntity.getId());
        return transporteTerrestreEntity;
    }

    /**
     * Eliminar un alojamiento por ID
     *
     * @param transporteId El ID del alojamiento a eliminar
     * @throws BusinessLogicException
     */
    public void deleteTransporte(Long transporteId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el transporte con id = {0}", transporteId);
        if (transporteId == null) {
            throw new BusinessLogicException("El id ingresado es invalido" + transporteId + ", imposible eliminar el transporte."); 
        }
        persistence.delete(transporteId);   
        LOGGER.log(Level.INFO, "Termina proceso de borrar el transporte con id = {0}", transporteId);
    }
}
