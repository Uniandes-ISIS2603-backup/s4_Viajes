/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.AlojamientoDTO;
import co.edu.uniandes.csw.viajes.dtos.AlojamientoDetailDTO;
import co.edu.uniandes.csw.viajes.ejb.AlojamientoLogic;
import co.edu.uniandes.csw.viajes.ejb.ProveedorLogic;
import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
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
 * Clase que implementa el servicio "alojamientos".
 *
 * @author Ymespana
 */
@Path("alojamientos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AlojamientoResource {

    private static final Logger LOGGER = Logger.getLogger(AlojamientoResource.class.getName());

    @Inject
    private AlojamientoLogic alojamientoLogic; // Variable para acceder a la lógica de la aplicación.

    @Inject
    private ProveedorLogic proveedorLogic;

    /**
     * Crea un nuevo alojamiento con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * @param alojamiento {@link AlojamientoDTO} - EL alojamiento que se desea
     * guardar.
     * @return JSON {@link AlojamientoDTO} - El alojamiento guardado con el
     * atributo id.
     * @throws BusinessLogicException
     */
    @POST
    public AlojamientoDTO createAlojamiento(AlojamientoDTO alojamiento) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AlojamientoResource createAlojamiento: input: {0}", alojamiento.toString());
        AlojamientoDTO nuevoAlojamientoDTO = new AlojamientoDTO(alojamientoLogic.createAlojamiento(alojamiento.toEntity()));
        LOGGER.log(Level.INFO, "AlojamientoResource createAlojamiento: output: {0}", nuevoAlojamientoDTO.toString());
        return nuevoAlojamientoDTO;
    }

    /**
     * Busca y devuelve todos los alojamientos que existen en la aplicacion.
     *
     * @return Todos los alojamientos, si no hay ninguna retorna una lista vacia. 
     */
    @GET
    public List<AlojamientoDetailDTO> getAlojamientos() {
        LOGGER.info("AlojamientoResource getAlojamientos: input: void");
        List<AlojamientoDetailDTO> listaAlojamientos = listEntity2DetailDTO(alojamientoLogic.getAlojamientos()); 
        LOGGER.log(Level.INFO, "AlojamientoResource getAlojamientos: output: {0}", listaAlojamientos.toString());
        return listaAlojamientos; 
    }
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos AlojamientoEntity a una lista de
     * objetos AlojameintoDetailDTO (json)
     *
     * @param entityList corresponde a la lista de alojamientos de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de alojamientos en forma DTO (json)
     */ 
    private List<AlojamientoDTO> listEntity2DetailDTO(List<AlojamientoEntity> entityList) {
        List<AlojamientoDTO> list = new ArrayList<>();
        for (AlojamientoEntity entity : entityList) {
            list.add(new AlojamientoDTO(entity));
        }
        return list;
    }

    /**
     * Busca el alojamiento con el id asociado recibido en la URL y lo devuelve.
     *
     * @param alojamientosId Identificador del alojamiento que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link AlojamientoDTO}
     * @throws BusinessLogicException
     */
    @GET
    @Path("{alojamientosId: \\d+}") 
    public AlojamientoDTO getAlojamiento(@PathParam("alojamientosId") Long alojamientosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AlojamientoResource getAlojamiento: input: {0}", alojamientosId);
        AlojamientoEntity alojamientoEntity = alojamientoLogic.getAlojamiento(alojamientosId);
        if (alojamientoEntity == null) { 
            throw new WebApplicationException("El recurso /alojamientos/" + alojamientosId + " no existe.", 404);
        }
        AlojamientoDTO alojamientoDTO = new AlojamientoDTO(alojamientoEntity);
        LOGGER.log(Level.INFO, "AlojamientoResource getAlojamiento: output: {0}", alojamientoDTO.toString());
        return alojamientoDTO;
    }
 
    /**
     * Actualiza el alojamiento con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param alojamientosId Identificador del alojamientoque se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param alojamiento El alojamiento que se desea guardar.
     * @return JSON El alojamiento guardado.
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{alojamientosId: \\d+}")
    public AlojamientoDTO updateAlojamiento(@PathParam("alojamientosId") Long alojamientosId, AlojamientoDTO alojamiento) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AlojamientoResource updateAlojamiento: input: id: {0} , alojamiento: {1}",
                new Object[]{alojamientosId, alojamiento.toString()});
        alojamiento.setId(alojamientosId);
        if (alojamientoLogic.getAlojamiento(alojamientosId) == null) {
            throw new WebApplicationException("El recurso /alojamientos/" + alojamientosId + " no existe.", 404);
        }
        AlojamientoDetailDTO alojamientoDetailDTO = new AlojamientoDetailDTO(alojamientoLogic.updateAlojamiento(alojamientosId, alojamiento.toEntity()));
        LOGGER.log(Level.INFO, "AlojamientoResource updateAlojamiento: output: {0}", alojamientoDetailDTO.toString());
        return alojamientoDetailDTO;
    }

    /**
     * Borra el alojamiento con el id asociado recibido en la URL.
     *
     * @param alojamientosId Identificador del alojamiento que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException
     */
    @DELETE
    @Path("{alojamientosId: \\d+}")
    public void deleteBook(@PathParam("alojamientosId") Long alojamientosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AlojamientoResource deleteAlojamiento: input: {0}", alojamientosId);
        AlojamientoEntity alojamientoEntity = alojamientoLogic.getAlojamiento(alojamientosId);
        if (alojamientoEntity == null) {
            throw new WebApplicationException("El recurso /alojamientos/" + alojamientosId + " no existe.", 404);
        }
        alojamientoLogic.deleteAlojamiento(alojamientosId);
        LOGGER.info("AlojamientoResource deleteAlojamiento: output: void");
    }
}
