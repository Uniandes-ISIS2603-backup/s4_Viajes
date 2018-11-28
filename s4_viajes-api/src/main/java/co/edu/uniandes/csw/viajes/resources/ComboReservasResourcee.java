/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ActividadDTO;
import co.edu.uniandes.csw.viajes.dtos.ComboDetailDTO;
import co.edu.uniandes.csw.viajes.dtos.ReservaDTO;
import co.edu.uniandes.csw.viajes.ejb.ComboReservasLogic;
import co.edu.uniandes.csw.viajes.ejb.ReservaLogic;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
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

/**
 *
 * @author jd.barriosc
 */
@Path("combos/{comboId: \\d+}/reservas")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped 
public class ComboReservasResourcee {
     private static final Logger LOGGER = Logger.getLogger(ComboReservasResourcee.class.getName());

    @Inject
    private ComboReservasLogic comboReservasLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    ReservaLogic reservaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda uua actividad dentro de un proveedor con la informacion que recibe el
     * la URL. Se devuelve la actividad que se guarda en el proveedor.
     *
     * @param comboId Identificador del proveedor que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param reservaId Identificador de la actividad que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link VueloDTO} - El vuelo guardado en el proveedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la actividad.
     */
    @POST
    @Path("{reservaId: \\d+}")
    public ComboDetailDTO addReserva(@PathParam("comboId") Long comboId, @PathParam("reservaId") Long reservaId)  throws WebApplicationException, Exception  {
        LOGGER.log(Level.INFO, "ComboReservas addReserva: input: comboId: {0} , reservaId: {1}", new Object[]{comboId, reservaId});
        ComboDetailDTO comboDTO;
         try {
             comboDTO = new ComboDetailDTO(comboReservasLogic.addReserva(comboId,reservaId));
         } catch (BusinessLogicException ex) {
            throw new WebApplicationException(ex.getMessage(), 404);
         }
        return comboDTO;
    }
    
     @POST
     public ComboDetailDTO crearReserva(@PathParam("comboId") Long comboId,ReservaDTO reserva) throws BusinessLogicException, Exception {
        
        ComboDetailDTO comboDTO = new ComboDetailDTO(comboReservasLogic.addReserva(comboId,(reservaLogic.createReserva(reserva.toEntity())).getId()));
        return comboDTO;        
       
    }
    
    /**
     * Busca y devuelve todos las actividades que existen en el proveedor.
     *
     * @param comboId Identificador del proveedor que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link ActividadDTO} - Las actividades encontradas en el
     * proveedor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ReservaDTO> getReservas(@PathParam("comboId") Long comboId) throws WebApplicationException  {
        LOGGER.log(Level.INFO, "ProveedorActividadResource getActividades: input: {0}", comboId);
        List<ReservaDTO> listaDTOs;
         try {
             listaDTOs = actividadesListEntity2DTO(comboReservasLogic.getReservasCombo(comboId));
         } catch (BusinessLogicException ex) {
            throw new WebApplicationException(ex.getMessage(), 404);
         }
        LOGGER.log(Level.INFO, "ProveedorActividadesResource getActividades: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }
    
//    /**
//     * Busca la actividad con el id asociado dentro del proveedor con id asociado.
//     *
//     * @param proveedorId Identificador dl proveedor que se esta buscando.
//     * Este debe ser una cadena de dígitos.
//     * @param actividadId Identificador de la actividad que se esta buscando. Este debe
//     * ser una cadena de dígitos.
//     * @return JSON {@link ActividadDTO} - La actividad buscada
//     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra la actividad en 
//     * el proveedor
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra la actividad.
//     */
//    @GET
//    @Path("{actividadId: \\d+}")
//    public ActividadDTO getActividad(@PathParam("proveedorId") Long proveedorId, @PathParam("actividadId") Long actividadId) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "ProveedorActividadResource getActividad: input: proveedorID: {0} , actividadId: {1}", new Object[]{proveedorId, actividadId});
//        if (actividadLogic.getActividad(actividadId) == null) {
//            throw new WebApplicationException("El recurso /proveedores/" + proveedorId + "/actividades/" + actividadId + " no existe.", 404);
//        }
//        ActividadDTO actividadDTO = new ActividadDTO(comboReservasLogic.getActividad(proveedorId, actividadId));
//        LOGGER.log(Level.INFO, "ProveedorActividadResource getActividad: output: {0}", actividadDTO.toString());
//        return actividadDTO;
//    }
//
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
//        for (ActividadDTO actividad : actividades) {
//            if (actividadLogic.getActividad(actividad.getId()) == null) {
//                throw new WebApplicationException("El recurso /actividades/" + actividad.getId() + " no existe.", 404);
//            }
//        }
//        List<ActividadDTO> listaDTOs = actividadesListEntity2DTO(comboReservasLogic.replaceActividades(proveedorId, actividadesListDTO2Entity(actividades)));
//        LOGGER.log(Level.INFO, "ProveedorActividadResource replaceActividades: output: {0}", listaDTOs.toString());
//        return listaDTOs;
//    }

    /**
     * Convierte una lista de ActividadEntity a una lista de ActividadDTO.
     *
     * @param entityList Lista de ActividadEntity a convertir.
     * @return Lista de ActividadDTO convertida.
     */
    private List<ReservaDTO> actividadesListEntity2DTO(List<ReservaEntity> entityList) {
        List<ReservaDTO> list = new ArrayList();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de ActividadDTO a una lista de ActividadEntity.
     *
     * @param dtos Lista de ActividadDTO a convertir.
     * @return Lista de ActividadEntity convertida.
     */
    private List<ReservaEntity> actividadesListDTO2Entity(List<ReservaDTO> dtos) throws Exception {
        List<ReservaEntity> list = new ArrayList<>();
        for (ReservaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    } 
}
