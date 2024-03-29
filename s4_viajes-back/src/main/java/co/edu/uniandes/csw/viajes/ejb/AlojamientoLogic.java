/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.AlojamientoPersistence;
import co.edu.uniandes.csw.viajes.persistence.ComboPersistence;
import co.edu.uniandes.csw.viajes.persistence.ProveedorPersistence;
import co.edu.uniandes.csw.viajes.persistence.ReservaPersistence;
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
public class AlojamientoLogic extends ServicioLogic{

    public static final Logger LOGGER = Logger.getLogger(AlojamientoLogic.class.getName());

    @Inject
    private AlojamientoPersistence persistence;

    @Inject
    private ProveedorPersistence proveedorPersistence;
    
    @Inject
    private ComboPersistence comboPersistence; 
    
     @Inject
    private ReservaPersistence reservaPersistence; 


    /**
     * Guardar un nuevo alojamiento
     *
     * @param alojamientoEntity La entidad de tipo alojamiento del nuevo
     * alojamiento a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el nombre es inválido o ya existe en la
     * persistencia.
     */
    public AlojamientoEntity createAlojamiento(AlojamientoEntity alojamientoEntity) throws BusinessLogicException{
     
        LOGGER.log(Level.INFO, "Inicial el proceso de creacion de alojamiento."); 
        super.createServicio(alojamientoEntity);
  
        if(alojamientoEntity.getEstrellas()<=0||alojamientoEntity.getEstrellas()>5)
            throw new BusinessLogicException("El alojamiento debe tener estrellas entro 0 y 5");
       
        if(alojamientoEntity.getTipo()==null||alojamientoEntity.getTipo().trim().equals(""))
            throw new BusinessLogicException("El alojamiento debe tener un tipo");
        
        persistence.create(alojamientoEntity);
        LOGGER.log(Level.INFO,"Termina la creacion del alojamiento." ); 
        return alojamientoEntity;  
    }

    private boolean validateNombre(String nombre) {
        Pattern pat = Pattern.compile("[a-zA-Z]{1,25}");
        Matcher mat = pat.matcher(nombre);
        return (mat.matches() && !nombre.isEmpty());
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
     * @return El alojamiento encontrado.
     */
    public AlojamientoEntity getAlojamiento(Long alojamientoId)  {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el alojamiento con id = {0}", alojamientoId);
        AlojamientoEntity alojamientoEntity = persistence.find(alojamientoId); 
        if (alojamientoEntity == null) {
            LOGGER.log(Level.SEVERE, "El alojamiento con el id = {0} no existe: (AlojamientoLogic) ", alojamientoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el alojamiento con id = {0}", alojamientoId);
        return alojamientoEntity; 
    }

    /**
     * Actualizar un alojamiento por ID
     *
     * @param alojamientoEntity La entidad del aoljamiento con los cambios
     * deseados
     * @param alojamientosId
     * @return La entidad del alojamiento luego de actualizarla
     * @throws BusinessLogicException Si el nombre de la actualización es
     * inválido
     */
    public AlojamientoEntity updateAlojamiento(Long alojamientosId, AlojamientoEntity alojamientoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el alojamiento con id = {0}", alojamientosId);
        
        if (!validateNombre(alojamientoEntity.getNombre()) || alojamientoEntity.getNombre() == null) {
            throw new BusinessLogicException("El nombre es inválido: (AlojamientoLogicUP)" + alojamientoEntity.getNombre()); 
        }
        if (persistence.findByNombre(alojamientoEntity.getNombre()) == null) {
            throw new BusinessLogicException("El nombre ingresado no existe: (AlojamientoLogicUP) " + alojamientoEntity.getNombre());
        }
        if (alojamientoEntity.getCosto() < 0) {
            throw new BusinessLogicException("El costo ingresado es invalido: (AlojamientoLogicUP) " + alojamientoEntity.getCosto());
        }
        if (alojamientoEntity.getEstrellas() < 0 || alojamientoEntity.getEstrellas() > 5) {
            throw new BusinessLogicException("Las estrellas ingresadas son invalidas: (AlojamientoLogicUP)" + alojamientoEntity.getEstrellas());
        }
//        if(alojamientoEntity.getNoches() < 0){
//            throw new BusinessLogicException("Las noches son invalidas: (AlojamientoLogicUP) " + alojamientoEntity.getNoches());
//        }
        AlojamientoEntity alojamiento = persistence.update(alojamientoEntity); 
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el alojamiento con id = {0}", alojamientoEntity.getId());
        return alojamiento;  

    }

    /**
     * Eliminar un alojamiento por ID
     *
     * @param alojamientoId El ID del alojamiento a eliminarw
     * @throws BusinessLogicException
     */
    public void deleteAlojamiento(Long alojamientoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el alojamiento con id = {0}", alojamientoId);
        AlojamientoEntity alojamiento = getAlojamiento(alojamientoId);
        if (alojamiento == null) {
            throw new BusinessLogicException("El alojamiento no se encuentra registrado, imposible eliminar: (AlojamientoLogicDEL)" + alojamientoId);
        }
        for(ReservaEntity reserva:reservaPersistence.findAll())
            if(alojamientoId.compareTo(reserva.getIdServicio())==0)
                throw new BusinessLogicException("No se puede eliminar el servicio pues ya tiene reservas asociasas");

    
        persistence.delete(alojamientoId);  
        LOGGER.log(Level.INFO, "Termina proceso de borrar el alojamiento con id = {0}", alojamientoId);
    }
}
