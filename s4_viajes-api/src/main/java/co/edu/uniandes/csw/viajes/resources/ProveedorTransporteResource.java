/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.TransporteTerrestreDTO;
import co.edu.uniandes.csw.viajes.dtos.VueloDTO;
import co.edu.uniandes.csw.viajes.ejb.ProveedorTransporteLogic;
import co.edu.uniandes.csw.viajes.ejb.TransporteTerrestreLogic;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.viajes.mappers.WebApplicationExceptionMapper;
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
 * Clase que implementa el recurso "proveedores/{id}/transportes".
 * 
 * @author jf.torresp
 */
@Path("proveedores/{proveedorId: \\d+}/transportes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProveedorTransporteResource {
 
           
    private static final Logger LOGGER = Logger.getLogger(ProveedorTransporteResource.class.getName());

    @Inject
    private ProveedorTransporteLogic proveedorTransporteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private TransporteTerrestreLogic transporteTerrestreLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Guarda un Transporte dentro de un proveedor con la informacion que recibe el
     * la URL. Se devuelve el Transporte que se guarda en el proveedor.
     *
     * @param proveedorId Identificador del proveedor que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param TransporteId Identificador del Transporte que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link TransporteDTO} - El Transporte guardado en el proveedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Transporte.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Transporte.
     */
    @POST
    @Path("{transporteId: \\d+}")
    public TransporteTerrestreDTO addTransporte(@PathParam("proveedorId") Long proveedorId, @PathParam("transporteId") Long TransporteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorTransporteResource addTransporte: input: proveedorID: {0} , transporteId: {1}", new Object[]{proveedorId, TransporteId});
        if (transporteTerrestreLogic.getTransporte(TransporteId) == null) {
            throw new WebApplicationException("El recurso /transportes/" + TransporteId + " no existe.", 404);
        }
        TransporteTerrestreDTO transporteDTO = new TransporteTerrestreDTO(proveedorTransporteLogic.addTransporte(TransporteId, proveedorId));
        LOGGER.log(Level.INFO, "ProveedorTransporteResource addTransporte: output: {0}", transporteDTO.toString());
        return transporteDTO;
    }
    
    /**
     * Busca y devuelve todos las Transportes que existen en el proveedor.
     *
     * @param proveedorId Identificador del proveedor que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link TransporteDTO} - Los Transportes encontrados en el
     * proveedor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<TransporteTerrestreDTO> getTransportes(@PathParam("proveedorId") Long proveedorId) {
        LOGGER.log(Level.INFO, "ProveedorTransporteResource getTransportes: input: {0}", proveedorId);
        List<TransporteTerrestreDTO> listaDTOs = transportesListEntity2DTO(proveedorTransporteLogic.getTransportes(proveedorId));
        LOGGER.log(Level.INFO, "ProveedorTransporteesResource getTransportes: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }
    
    /**
     * Busca la Transporte con el id asociado dentro del proveedor con id asociado.
     *
     * @param proveedorId Identificador del proveedor que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param transporteId Identificador del Transporte que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link TransporteDTO} - El Transporte buscado
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Transporte en 
     * el proveedor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Transporte.
     */
    @GET
    @Path("{TransporteId: \\d+}")
    public TransporteTerrestreDTO getTransporte(@PathParam("proveedorId") Long proveedorId, @PathParam("transporteId") Long transporteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorTransporteResource getTransporte: input: proveedorID: {0} , TransporteId: {1}", new Object[]{proveedorId, transporteId});
        if (transporteTerrestreLogic.getTransporte(transporteId) == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedorId + "/transportes/" + transporteId + " no existe.", 404);
        }
        TransporteTerrestreDTO transporteTerrestreDTO = new TransporteTerrestreDTO(proveedorTransporteLogic.getTransporte(proveedorId, transporteId));
        LOGGER.log(Level.INFO, "ProveedorTransporteResource getTransporte: output: {0}", transporteTerrestreDTO.toString());
        return transporteTerrestreDTO;
    }

    /**
     * Remplaza las instancias de Transporte asociadas a una instancia de Proveedor
     *
     * @param proveedorId Identificador del proveedor que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param transportes JSONArray {@link TransporteDTO} El arreglo de Transportes nuevo para el
     * proveedor.
     * @return JSON {@link VueloDTO} - El arreglo de Transportes guardado en 
     * el proveedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Transporte.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Transporte.
     */
    @PUT
    @Path("{proveedorId: \\d+}")
    public List<TransporteTerrestreDTO> replaceTransportes(@PathParam("proveedorId") Long proveedorId, List<TransporteTerrestreDTO> transportes) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorTransporteResource replaceTransportes: input: proveedorId: {0} , transportes: {1}", new Object[]{proveedorId, transportes.toString()});
        for (TransporteTerrestreDTO transporte : transportes) {
            if (transporteTerrestreLogic.getTransporte(transporte.getId()) == null) {
                throw new WebApplicationException("El recurso /Transportes/" + transporte.getId() + " no existe.", 404);
            }
        }
        List<TransporteTerrestreDTO> listaDTOs = transportesListEntity2DTO(proveedorTransporteLogic.replaceTransportes(proveedorId, transportesListDTO2Entity(transportes)));
        LOGGER.log(Level.INFO, "ProveedorTransporteResource replaceTransportes: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }

    /**
     * Convierte una lista de TransporteTerrestreEntity a una lista de TransporteTerrestreDTO.
     *
     * @param entityList Lista de TransporteEntity a convertir.
     * @return Lista de TransporteDTO convertida.
     */
    private List<TransporteTerrestreDTO> transportesListEntity2DTO(List<TransporteTerrestreEntity> entityList) {
        List<TransporteTerrestreDTO> list = new ArrayList();
        for (TransporteTerrestreEntity entity : entityList) {
            list.add(new TransporteTerrestreDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de TransporteTerrestreDTO a una lista de TransporteTerrestreEntity.
     *
     * @param dtos Lista de TransporteDTO a convertir.
     * @return Lista de TransporteEntity convertida.
     */
    private List<TransporteTerrestreEntity> transportesListDTO2Entity(List<TransporteTerrestreDTO> dtos) {
        List<TransporteTerrestreEntity> list = new ArrayList<>();
        for (TransporteTerrestreDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }    
}
