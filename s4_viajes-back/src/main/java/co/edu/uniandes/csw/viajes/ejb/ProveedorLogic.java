/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ActividadPersistence;
import co.edu.uniandes.csw.viajes.persistence.AlojamientoPersistence;
import co.edu.uniandes.csw.viajes.persistence.ProveedorPersistence;
import co.edu.uniandes.csw.viajes.persistence.TransporteTerrestrePersistence;
import co.edu.uniandes.csw.viajes.persistence.VueloPersistence;
import java.util.List;
import static java.util.Objects.isNull;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 *Proveedor.
 * 
 * @author jf.torresp
 */
@Stateless
public class ProveedorLogic {
    private static final Logger LOGGER = Logger.getLogger(ProveedorLogic.class.getName());

    @Inject
    private ProveedorPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

     @Inject
    private ActividadPersistence actividadPersistence;
    
    @Inject
    private AlojamientoPersistence alojamientoPersistence;
     
    @Inject
    private TransporteTerrestrePersistence transporteTerrestrePersistence;
      
    @Inject
    private VueloPersistence vueloPersistence;
    /**
     * Crea un proveedor en la persistencia.
     *
     * @param proveedorEntity La entidad que representa el proveedor a
     * persistir.
     * @return La entidad del proveedor luego de persistirla.
     * @throws BusinessLogicException Si el proveedor a persistir ya existe.
     */
    public ProveedorEntity createProveedor(ProveedorEntity proveedorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del proveedor");
        // Verifica la regla de negocio que dice que no puede haber dos proveedores con el mismo nombre
         if(proveedorEntity.getNombre()==null||proveedorEntity.getNombre().trim().equals(""))
            throw new BusinessLogicException("El proveedor debe tener un nombre");
             
        if (persistence.findByName(proveedorEntity.getNombre()) != null) 
            throw new BusinessLogicException("Ya existe un proveedor con el nombre \"" + proveedorEntity.getNombre() + "\"");
         
        if(proveedorEntity.getUsername()==null||proveedorEntity.getUsername().trim().equals(""))
            throw new BusinessLogicException("El proveedor debe tener un username");     
        if (persistence.findByUserName(proveedorEntity.getUsername()) != null) 
            throw new BusinessLogicException("Ya existe un proveedor con el username \"" + proveedorEntity.getUsername() + "\"");
       
        if(proveedorEntity.getImagen()==null||proveedorEntity.getImagen().trim().equals(""))
            throw new BusinessLogicException("El servicio debe tener una imagen");
        if(proveedorEntity.getPassword()==null||proveedorEntity.getPassword().trim().equals(""))
            throw new BusinessLogicException("El servicio debe tener una contraseña");
        
        proveedorEntity.setPuntuacion(-1);
        proveedorEntity.setCantidadCalificaciones(0);
        String input = proveedorEntity.getNombre();
         
        // Verifica la regla de negocio que dice que el nombre del proveedor debe ser compuesto por solo letras y debe tener mínimo 5 letras y máximo 30 letras.
        Pattern p1 = Pattern.compile("^[a-zA-Z]{5,30}");
        Matcher m1 = p1.matcher(input);
        
        if(m1.find() == false)
        {
            throw new BusinessLogicException("El nombre del proveedor debe ser compuesto por solo letras y debe tener mínimo 5 letras y máximo 30 letras.");
        }
         
        // Verifica la regla de negocio que dice que la contraseña debe complur lo siguiente:
        // Mínimo 4 caracteres
        //Maximo 8 caracteres
        //Al menos una letra mayúscula
        //Al menos una letra minúcula
        //Al menos un dígito
        Pattern p2 = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$");
        String input2 = proveedorEntity.getPassword();
        Matcher m2 = p2.matcher(input2);
        
        if(m2.find())
        {
            throw new BusinessLogicException("La contraseña no cumple con las reglas establecidas.");
        }
        
        // Invoca la persistencia para crear el proveedor
        persistence.create(proveedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del proveedor");
        return proveedorEntity;
    }
    
    /**
     * Obtener todas los proveedores existentes en la base de datos.
     *
     * @return una lista de proveedores.
     */
    public List<ProveedorEntity> getProveedores() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los proveedores");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ProveedorEntity> proveedores = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los proveedores");
        return proveedores;
    }

    /**
     * Obtener un proveedor por medio de su id.
     *
     * @param proveedorId: id del proveedor para ser buscada.
     * @return el proveedor solicitada por medio de su id.
     */
    public ProveedorEntity getProveedor(Long proveedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el proveedor con id = {0}", proveedorId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        ProveedorEntity proveedorEntity = persistence.find(proveedorId);
        if (proveedorEntity == null) {
            LOGGER.log(Level.SEVERE, "El proveedor con el id = {0} no existe", proveedorId);
        }
        escogerServicios(proveedorEntity);
       
        LOGGER.log(Level.INFO, "Termina proceso de consultar el proveedor con id = {0}", proveedorId);
        return proveedorEntity;
    }

    public void escogerServicios(ProveedorEntity proveedorEntity) 
    {
        
        
        for(Long id:proveedorEntity.getIdsServicios())
            if(id!=0l)
            {
                VueloEntity vueloEntity = vueloPersistence.find(id);
                ActividadEntity actividadEntity=actividadPersistence.find(id);
                AlojamientoEntity alojamientoEntity=alojamientoPersistence.find(id);
                TransporteTerrestreEntity transporteTerrestreEntity=transporteTerrestrePersistence.find(id);
                if (vueloEntity == null&&actividadEntity == null&&alojamientoEntity == null&&transporteTerrestreEntity == null) {
                    //hay un servicio faltante
                }
                else if(vueloEntity != null)
                    proveedorEntity.addServicio(vueloEntity);                 
                else if(actividadEntity != null)
                    proveedorEntity.addServicio(actividadEntity);                 
                else if(alojamientoEntity != null)
                   proveedorEntity.addServicio(alojamientoEntity);
                else if(transporteTerrestreEntity != null)
                   proveedorEntity.addServicio(transporteTerrestreEntity);
                    
            }
                   
    }
    /**
     * Actualizar un proveedor.
     *
     * @param proveedorId: id del proveedor para buscarlo en la base de
     * datos.
     * @param proveedorEntity: proveedor con los cambios para ser actualizado,
     * por ejemplo el nombre.
     * @return el proveedor con los cambios actualizados en la base de datos.
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    public ProveedorEntity updateProveedor(Long proveedorId, ProveedorEntity proveedorEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el proveedor con id = {0}", proveedorId);
         //Verifica la regla de negocio que dice que no se puede actualizar el id de un vuelo con un vuelo que ya tenga ese id.
        if (persistence.find(proveedorEntity.getId()) == null) 
        {
            throw new BusinessLogicException("Ya existe un proveedor con el id que quiere cambiar \"" + proveedorEntity.getId() + "\"");
        }
        
        //List<VueloEntity> vuelos = getProveedor(proveedorId).getVuelos();
        //if (vuelos != null && !vuelos.isEmpty()) {
        //    throw new BusinessLogicException("No se puede borrar el proveedor con id = " + proveedorId + " porque tiene vuelos asociados");
       // }
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        ProveedorEntity newEntity = persistence.update(proveedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el proveedor con id = {0}", proveedorEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un proveedor
     *
     * @param proveedorId: id del proveedor a borrar
     * @throws BusinessLogicException Si el proveedor tiene vuelos, transportes y actividades asociadas.
     */
    public void deleteProveedor(Long proveedorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar elproveedor con id = {0}", proveedorId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        ProveedorEntity proveedor=getProveedor(proveedorId);
        if(proveedor==null)
            throw new BusinessLogicException("No existe el proveedor con id \"" + proveedorId + "\"");
        if(proveedor.getIdsServicios().size()>0)
            throw new BusinessLogicException("No se puee eliminar el proveedor con id \"" + proveedorId + "\" ya que tiene servicios asociados");

        persistence.delete(proveedorId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el proveedor con id = {0}", proveedorId);
    }
   
}
