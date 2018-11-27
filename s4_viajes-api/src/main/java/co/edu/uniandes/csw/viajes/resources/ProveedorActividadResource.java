/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ActividadDTO;
import co.edu.uniandes.csw.viajes.dtos.ProveedorDTO;
import co.edu.uniandes.csw.viajes.dtos.VueloDTO;
import co.edu.uniandes.csw.viajes.ejb.ActividadLogic;
import co.edu.uniandes.csw.viajes.ejb.ProveedorActividadLogic;
import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.viajes.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
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
 *
 * Clase que implementa el recurso "proveedores/{id}/actividades".
 * 
 * @author jf.torresp
 */
@Path("proveedores/{proveedorId: \\d+}/actividades")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped 
public class ProveedorActividadResource {
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorActividadResource.class.getName());

    @Inject
    private ProveedorActividadLogic proveedorActividadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ActividadLogic actividadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Guarda uua actividad dentro de un proveedor con la informacion que recibe el
     * la URL. Se devuelve la actividad que se guarda en el proveedor.
     *
     * @param proveedorId Identificador del proveedor que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param actividadId Identificador de la actividad que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link VueloDTO} - El vuelo guardado en el proveedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la actividad.
     */
    @POST
    @Path("{actividadId: \\d+}")
    public ProveedorDTO addActividad(@PathParam("proveedorId") Long proveedorId, @PathParam("actividadId") Long actividadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorActividadResource addAlojamiento: input: proveedorID: {0} , actividadId: {1}", new Object[]{proveedorId, actividadId});
        ProveedorDTO proveedorDTO = new ProveedorDTO(proveedorActividadLogic.addActividad(actividadId, proveedorId));
        LOGGER.log(Level.INFO, "ProveedorActividadResource addActividad: output: {0}", proveedorDTO.toString());
        return proveedorDTO;
    }
    
    /**
     * Busca y devuelve todos las actividades que existen en el proveedor.
     *
     * @param proveedorId Identificador del proveedor que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link ActividadDTO} - Las actividades encontradas en el
     * proveedor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ActividadDTO> getActividades(@PathParam("proveedorId") Long proveedorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorActividadResource getActividades: input: {0}", proveedorId);
        List<ActividadDTO> listaDTOs = actividadesListEntity2DTO(proveedorActividadLogic.getActividades(proveedorId));
        LOGGER.log(Level.INFO, "ProveedorActividadesResource getActividades: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }
    
    /**
     * Busca la actividad con el id asociado dentro del proveedor con id asociado.
     *
     * @param proveedorId Identificador dl proveedor que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param actividadId Identificador de la actividad que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link ActividadDTO} - La actividad buscada
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la actividad en 
     * el proveedor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la actividad.
     */
    @GET
    @Path("{actividadId: \\d+}")
    public ActividadDTO getActividad(@PathParam("proveedorId") Long proveedorId, @PathParam("actividadId") Long actividadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorActividadResource getActividad: input: proveedorID: {0} , actividadId: {1}", new Object[]{proveedorId, actividadId});
        ActividadDTO actividadDTO = new ActividadDTO(proveedorActividadLogic.getActividad(proveedorId, actividadId));
        LOGGER.log(Level.INFO, "ProveedorActividadResource getActividad: output: {0}", actividadDTO.toString());
        return actividadDTO;
    }

//    /**
//     * Remplaza las instancias de Actividad asociadas a una instancia de Proveedor
//     *
//     * @param proveedorId Identificador del proveedor que se esta
//     * remplazando. Este debe ser una cadena de dígitos.
//     * @param actividades JSONArray {@link ActividadDTO} El arreglo de actividades nuevo para el
//     * proveedor.
//     * @return JSON {@link VueloDTO} - El arreglo de actividades guardado en 
//     * el proveedor.
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra la actividad.
//     */
//    @PUT
//    @Path("{proveedorId: \\d+}")
//    public List<ActividadDTO> replaceActividades(@PathParam("proveedorId") Long proveedorId, List<ActividadDTO> actividades) {
//        LOGGER.log(Level.INFO, "ProveedorActividadResource replaceActividades: input: proveedorId: {0} , actividades: {1}", new Object[]{proveedorId, actividades.toString()});
//        List<ActividadDTO> listaDTOs = actividadesListEntity2DTO(proveedorActividadLogic.replaceActividades(proveedorId, actividadesListDTO2Entity(actividades)));
//        LOGGER.log(Level.INFO, "ProveedorActividadResource replaceActividades: output: {0}", listaDTOs.toString());
//        return listaDTOs;
//    }

    /**
     * Convierte una lista de ActividadEntity a una lista de ActividadDTO.
     *
     * @param entityList Lista de ActividadEntity a convertir.
     * @return Lista de ActividadDTO convertida.
     */
    private List<ActividadDTO> actividadesListEntity2DTO(List<ActividadEntity> entityList) {
        List<ActividadDTO> list = new ArrayList();
        for (ActividadEntity entity : entityList) {
            list.add(new ActividadDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de ActividadDTO a una lista de ActividadEntity.
     *
     * @param dtos Lista de ActividadDTO a convertir.
     * @return Lista de ActividadEntity convertida.
     */
    private List<ActividadEntity> actividadesListDTO2Entity(List<ActividadDTO> dtos) {
        List<ActividadEntity> list = new ArrayList<>();
        for (ActividadDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }    
}
