/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ProveedorDTO;
import co.edu.uniandes.csw.viajes.dtos.ProveedorDetailDTO;
import co.edu.uniandes.csw.viajes.ejb.ProveedorLogic;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.mappers.BusinessLogicExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Juan Felipe Torres
 */
@Path("proveedores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProveedorResource {

    /**
     * Clase que impkementa el recurso "proveedor".
     *
     *
     * @version 1.0
     */
    private static final Logger LOGGER = Logger.getLogger(ProveedorResource.class.getName());

    @Inject

    ProveedorLogic proveedorLogic;

    /**
     * Crea un nuevo proveedor con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param proveedor {@link ProveedorDTO} - El proveedor que se desea
     * guardar.
     * @return JSON {@link ProveedorDTO} - El proveedor guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el proveedor.
     */
    @POST
    public ProveedorDTO crearProveedor(ProveedorDTO proveedor) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorResource createProveedor: input: {0}", proveedor.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ProveedorEntity proveedorEntity = proveedor.toEntity();
        // Invoca la lógica para crear la actividad nueva
        ProveedorEntity nuevoProveedorEntity = proveedorLogic.createProveedor(proveedorEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        ProveedorDTO nuevoProveedorDTO = new ProveedorDTO(nuevoProveedorEntity);
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevoProveedorDTO.toString());
        return nuevoProveedorDTO;
    }

    /**
     * Obtiene un proveedor con su información dada por su nombre, se retorna
     * esta información que fue previamente ingresada en formato JSON.
     * @param proveedorId id del proveedor
     * @return un proveedor y su información de acuerdo a su nombre.
     */
    @GET
        @Path("{proveedorId: \\d+}")
    public ProveedorDTO consultarProveedor(@PathParam("proveedorId") Long proveedorId) {
        LOGGER.log(Level.INFO, "ProveedorResource getProveedor: input: {0}", proveedorId);
        ProveedorEntity proveedorEntity = proveedorLogic.getProveedor(proveedorId);
        if (proveedorEntity == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedorId + " no existe.", 404);
        }
       ProveedorDetailDTO detailDTO = new ProveedorDetailDTO(proveedorEntity);
        LOGGER.log(Level.INFO, "ProveedorResource getProveedor: output: {0}", detailDTO.toString());
        return detailDTO;
    }
    
    /**
     * Busca y devuelve todos los proveedoresque existen en la aplicacion.
     *
     * @return JSONArray {@link ProveedorDetailDTO} - Los proveedores
     * encontrados en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ProveedorDetailDTO> getProveedores() {
        LOGGER.info("ProveedorResource getProveedores: input: void");
        List<ProveedorDetailDTO> listaProveedores = listEntity2DetailDTO(proveedorLogic.getProveedores());
        LOGGER.log(Level.INFO, "ProveedorResource getProveedores: output: {0}", listaProveedores.toString());
        return listaProveedores;
    }

    /**
     * Modifica la informacion de un proveedor dado por la información ingresada
     * en formato JSON.
     *
     * @param proveedor (@link ProveedorDetailDTO) - el proveedor que desea
     * modificar.
     * @param id 
     * @return JSON {@link ProveedorDetailDTO} - El proveedor guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * @throws BusinessLogicException
     * Error de lógica que se genera cuando no se encuentra la editorial a
     * actualizar. 
     */
    @PUT
    @Path("{proveedorId: \\d+}")
    public ProveedorDetailDTO modificarProveedor(@PathParam("proveedorId") Long id, ProveedorDetailDTO proveedor) throws WebApplicationException, BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ProveedorResource updateProveedor: input: id:{0} , proveedor: {1}", new Object[]{id, proveedor.toString()});
        proveedor.setId(id);
        if (proveedorLogic.getProveedor(id) == null) {
            throw new WebApplicationException("El recurso proveedores/" + id + " no existe.", 404);
        }
        ProveedorDetailDTO detailDTO = new ProveedorDetailDTO(proveedorLogic.updateProveedor(id, proveedor.toEntity()));
        LOGGER.log(Level.INFO, "ProveedorResource updateProveedor: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Borra el proveedor con el id asociado (número) recibido en la URL.
     *
     * @param proveedorId Identificador del proveedor que se desea borrar. Este debe ser
     * una cadena de letras.
     * 
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper}
     */
    @DELETE
    @Path("{proveedorId: \\d+}")
    public void deleteProveedor(@PathParam("proveedorId")Long proveedorId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ProveedorResource deleteProveedor: input: {0}", proveedorId);
        if (proveedorLogic.getProveedor(proveedorId) == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedorId + " no existe.", 404);
        }
        proveedorLogic.deleteProveedor(proveedorId);
        LOGGER.info("ProveedorResource deleteProveedor: output: void");
    }
    
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos EditorialDetailDTO (json)
     *
     * @param entityList corresponde a la lista de proveedores de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de proveedores en forma DTO (json)
     */
    private List<ProveedorDetailDTO> listEntity2DetailDTO(List<ProveedorEntity> entityList) {
        List<ProveedorDetailDTO> list = new ArrayList<>();
        for (ProveedorEntity entity : entityList) {
            list.add(new ProveedorDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Conexión con el servicio de alojamientos para un proveedor. {@link AlojamientoResource}
     *
     * Este método conecta la ruta de /proveedores con las rutas de /alojamientos que
     * dependen del proveedor, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de los alojamientos.
     *
     * @param proveedoresId El ID del proveedor con respecto al cual se accede al
     * servicio.
     * @return El servicio de alojameintos para ese proveedor en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el alojamiento.
     */
    @Path("{proveedoresId: \\d+}/alojamientos")
    public Class<ProveedorAlojamientosResource> getProveedorAlojamientoResource(@PathParam("proveedoresId") Long proveedoresId) {
        if (proveedorLogic.getProveedor(proveedoresId) == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedoresId + "/alojamientos no existe.", 404);
        }
        return ProveedorAlojamientosResource.class; 
    }
    
    @Path("{proveedoresId: \\d+}/transportes")
    public Class<ProveedorTransportesResource> getTransporteResource(@PathParam("proveedoresId") Long proveedoresId) {
        if (proveedorLogic.getProveedor(proveedoresId) == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedoresId + "/transportes no existe.", 404);
        }
        return ProveedorTransportesResource.class;
    }
}
