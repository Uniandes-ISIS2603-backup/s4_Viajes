/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ComboPersistence;
import co.edu.uniandes.csw.viajes.persistence.ProveedorPersistence;
import co.edu.uniandes.csw.viajes.persistence.ReservaPersistence;
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
public class TransporteTerrestreLogic extends TransporteLogic{

    public static final Logger LOGGER = Logger.getLogger(TransporteTerrestreLogic.class.getName());

    @Inject
    private TransporteTerrestrePersistence persistence;
    
    @Inject
    private ProveedorPersistence proveedorPersistence;
    
    @Inject
    private ComboPersistence comboPesistence; 
    
     @Inject
    private ReservaPersistence reservaPersistence; 

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
        super.createTransporte(transporteEntity);

        
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
    public TransporteTerrestreEntity getTransporte(Long transporteId) throws BusinessLogicException  {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el transporte con id = {0}", transporteId);
        TransporteTerrestreEntity transporteEntity = persistence.find(transporteId);
        if (transporteEntity == null) {
        throw new BusinessLogicException("EL transporte no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el transporte con id = {0}", transporteId);
        return transporteEntity;
    }

    /**
     * Actualizar un alojamiento por ID
     *
     * @param transporteId El ID del alojamiento a actualizar
     * @param transporteEntity La entidad del aoljamiento con los
     * cambios deseados
     * @return La entidad del alojamiento luego de actualizarla
     * @throws BusinessLogicException
     */
    public TransporteTerrestreEntity updateTransporte(Long transporteId, TransporteTerrestreEntity transporteEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el transporte con id = {0}", transporteId);
        if (transporteEntity.getCosto() < 0) {
            throw new BusinessLogicException("El costo del transporte es invalido: (TransporteLogicUP) " );
        }
        if (transporteEntity.getDestino() == null) {
            throw new BusinessLogicException("El destino ingresado es invalido: (TransporteLogicUP)" );
        }
//        if (transporteEntity.getNumeroDias() < 0 && transporteEntity.getNumeroMinutos() < 0 && transporteEntity.getNumeroHoras() < 0 ) {
//            throw new BusinessLogicException("La duracion de viaje es invalida (TransporteLogicUP)"); 
//        }
        TransporteTerrestreEntity transporte = persistence.update(transporteEntity); 
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el transporte con id = {0}", transporteEntity.getId());
        return transporte; 
    }

    /**
     * Eliminar un alojamiento por ID
     *
     * @param transporteId El ID del alojamiento a eliminar
     * @throws BusinessLogicException
     */
    public void deleteTransporte(Long transporteId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el transporte con id = {0}", transporteId);
       
        TransporteTerrestreEntity transporte = getTransporte(transporteId); 
        if (transporteId == null) {
            throw new BusinessLogicException("El transporte no se encuentra registrado, imposible eliminar: (TransporteLogicDEL)" + transporteId); 
        }

        for(ReservaEntity reserva:reservaPersistence.findAll())
            if(transporteId.compareTo(reserva.getIdServicio())==0)
                throw new BusinessLogicException("No se puede eliminar el servicio pues ya tiene reservas asociasas");

        
        persistence.delete(transporte.getId());    
        LOGGER.log(Level.INFO, "Termina proceso de borrar el transporte con id = {0}", transporteId);
    }
}
