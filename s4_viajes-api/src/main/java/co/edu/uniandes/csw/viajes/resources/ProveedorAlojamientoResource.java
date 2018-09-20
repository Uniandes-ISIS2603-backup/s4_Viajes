/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.AlojamientoDTO;
import co.edu.uniandes.csw.viajes.ejb.AlojamientoLogic;
import co.edu.uniandes.csw.viajes.ejb.ProveedorAlojamientoLogic;
import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "proveedores/{id}/alojamientos".
 * 
 * @author jf.torresp
 */
@Path("proveedores/{proveedorId: \\d+}/alojamientos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProveedorAlojamientoResource {
       
    private static final Logger LOGGER = Logger.getLogger(ProveedorAlojamientoResource.class.getName());

    @Inject
    private ProveedorAlojamientoLogic proveedorAlojamientoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private AlojamientoLogic alojamientoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Guarda un Alojamiento dentro de un proveedor con la informacion que recibe el
     * la URL. Se devuelve el Alojamiento que se guarda en el proveedor.
     *
     * @param proveedorId Identificador del proveedor que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param alojamientoId Identificador del Alojamiento que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AlojamientoDTO} - El alojamiento guardado en el proveedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Alojamiento.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Alojamiento.
     */
    @POST
    @Path("{alojamientoId: \\d+}")
    public AlojamientoDTO addAlojamiento(@PathParam("proveedorId") Long proveedorId, @PathParam("alojamientoId") Long alojamientoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorAlojamientoResource addAlojamiento: input: proveedorID: {0} , alojamientoId: {1}", new Object[]{proveedorId, alojamientoId});
        if (alojamientoLogic.getAlojamiento(alojamientoId) == null) {
            throw new WebApplicationException("El recurso /alojamientos/" + alojamientoId + " no existe.", 404);
        }
        AlojamientoDTO AlojamientoDTO = new AlojamientoDTO(proveedorAlojamientoLogic.addAlojamiento(alojamientoId, proveedorId));
        LOGGER.log(Level.INFO, "ProveedorAlojamientoResource addAlojamiento: output: {0}", AlojamientoDTO.toString());
        return AlojamientoDTO;
    }
    
    /**
     * Busca y devuelve todos las alojamientos que existen en el proveedor.
     *
     * @param proveedorId Identificador del proveedor que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link AlojamientoDTO} - Los alojamientos encontrados en el
     * proveedor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<AlojamientoDTO> getAlojamientos(@PathParam("proveedorId") Long proveedorId) {
        LOGGER.log(Level.INFO, "ProveedorAlojamientoResource getAlojamientos: input: {0}", proveedorId);
        List<AlojamientoDTO> listaDTOs = alojamientosListEntity2DTO(proveedorAlojamientoLogic.getAlojamientos(proveedorId));
        LOGGER.log(Level.INFO, "ProveedorAlojamientoesResource getAlojamientos: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }
    
    /**
     * Busca la Alojamiento con el id asociado dentro del proveedor con id asociado.
     *
     * @param proveedorId Identificador del proveedor que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param alojamientoId Identificador del Alojamiento que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AlojamientoDTO} - El Alojamiento buscado
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Alojamiento en 
     * el proveedor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Alojamiento.
     */
    @GET
    @Path("{alojamientoId: \\d+}")
    public AlojamientoDTO getAlojamiento(@PathParam("proveedorId") Long proveedorId, @PathParam("alojamientoId") Long alojamientoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorAlojamientoResource getAlojamiento: input: proveedorID: {0} , AlojamientoId: {1}", new Object[]{proveedorId, alojamientoId});
        if (alojamientoLogic.getAlojamiento(alojamientoId) == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedorId + "/alojamientos/" + alojamientoId + " no existe.", 404);
        }
        AlojamientoDTO alojamientoDTO = new AlojamientoDTO(proveedorAlojamientoLogic.getAlojamiento(proveedorId, alojamientoId));
        LOGGER.log(Level.INFO, "ProveedorAlojamientoResource getAlojamiento: output: {0}", alojamientoDTO.toString());
        return alojamientoDTO;
    }

    /**
     * Remplaza las instancias de Alojamiento asociadas a una instancia de Proveedor
     *
     * @param proveedorId Identificador del proveedor que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param alojamientos JSONArray {@link AlojamientoDTO} El arreglo de alojamientos nuevo para el
     * proveedor.
     * @return JSON {@link VueloDTO} - El arreglo de alojamientos guardado en 
     * el proveedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Alojamiento.
     */
    @PUT
    @Path("{proveedorId: \\d+}")
    public List<AlojamientoDTO> replaceAlojamientos(@PathParam("proveedorId") Long proveedorId, List<AlojamientoDTO> alojamientos) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorAlojamientoResource replaceAlojamientos: input: proveedorId: {0} , alojamientos: {1}", new Object[]{proveedorId, alojamientos.toString()});
        for (AlojamientoDTO alojamiento : alojamientos) {
            if (alojamientoLogic.getAlojamiento(alojamiento.getId()) == null) {
                throw new WebApplicationException("El recurso /alojamientos/" + alojamiento.getId() + " no existe.", 404);
            }
        }
        List<AlojamientoDTO> listaDTOs = alojamientosListEntity2DTO(proveedorAlojamientoLogic.replaceAlojamientos(proveedorId, alojamientosListDTO2Entity(alojamientos)));
        LOGGER.log(Level.INFO, "ProveedorAlojamientoResource replaceAlojamientos: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }

    /**
     * Convierte una lista de AlojamientoEntity a una lista de AlojamientoDTO.
     *
     * @param entityList Lista de AlojamientoEntity a convertir.
     * @return Lista de AlojamientoDTO convertida.
     */
    private List<AlojamientoDTO> alojamientosListEntity2DTO(List<AlojamientoEntity> entityList) {
        List<AlojamientoDTO> list = new ArrayList();
        for (AlojamientoEntity entity : entityList) {
            list.add(new AlojamientoDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de AlojamientoDTO a una lista de AlojamientoEntity.
     *
     * @param dtos Lista de AlojamientoDTO a convertir.
     * @return Lista de AlojamientoEntity convertida.
     */
    private List<AlojamientoEntity> alojamientosListDTO2Entity(List<AlojamientoDTO> dtos) {
        List<AlojamientoEntity> list = new ArrayList<>();
        for (AlojamientoDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }    
}
