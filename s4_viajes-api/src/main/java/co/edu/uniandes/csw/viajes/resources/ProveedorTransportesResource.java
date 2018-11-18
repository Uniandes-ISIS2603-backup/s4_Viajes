/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ProveedorDTO;
import co.edu.uniandes.csw.viajes.dtos.TransporteTerrestreDTO;
import co.edu.uniandes.csw.viajes.ejb.ProveedorTransportesTerrestresLogic;
import co.edu.uniandes.csw.viajes.ejb.TransporteTerrestreLogic;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Yeferson Espana
 */
@Path("proveedores/{proveedorId: \\d+}/transportesTerrestres")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped 
public class ProveedorTransportesResource {
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorTransportesResource.class.getName());

    @Inject
    private ProveedorTransportesTerrestresLogic proveedorTransportesLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private TransporteTerrestreLogic transporteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un transporte dentro de un proveedor con la informacion que recibe el
     * la URL. Se devuelve el transporte que se guarda en el proveedor.
     *
     * @param proveedorId Identificador del proveedor que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param transporteTerrestreId Identificador del transporte que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link TransporteDTO} - El transporte guardado en el proveedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el transporte.
     * @throws BusinessLogicException
     */
    @POST
    @Path("{transporteTerrestreId: \\d+}")
    public ProveedorDTO addTransporte(@PathParam("proveedorId") Long proveedorId, @PathParam("transporteTerrestreId") Long transporteTerrestreId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorTransportesResource addTransporte: input: proveedoresID: {0} , transportesId: {1}", new Object[]{proveedorId, transporteTerrestreId});
        ProveedorDTO proveedorDTO = new ProveedorDTO(proveedorTransportesLogic.addTransporteTerrestre(transporteTerrestreId, proveedorId));
        LOGGER.log(Level.INFO, "ProveedorTransportesResource addTransporte: output: {0}", proveedorDTO.toString());
        return proveedorDTO;
    }
    
    /**
     * 
     * @param proveedorId
     * @return
     * @throws BusinessLogicException
     */
    @GET
    public List<TransporteTerrestreDTO> getTransportes(@PathParam("proveedorId") Long proveedorId)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorTransportesResource getTransportes: input: {0}", proveedorId);
        List<TransporteTerrestreDTO> listaDetailDTOs = transportesListEntity2DTO(proveedorTransportesLogic.getTransportesTerrestres(proveedorId));
        LOGGER.log(Level.INFO, "ProveedorTransportessResource getTransportes: output: {0}", listaDetailDTOs.toString());
        return listaDetailDTOs;
    }
    
    /**
     * 
     * @param entityList
     * @return 
     */
    private List<TransporteTerrestreDTO> transportesListEntity2DTO(List<TransporteTerrestreEntity> entityList) {
        List<TransporteTerrestreDTO> list = new ArrayList<>();
        for (TransporteTerrestreEntity entity : entityList) {
            list.add(new TransporteTerrestreDTO(entity));
        }
        return list;
    }
    
    /**
     * 
     * @param proveedorId
     * @param transporteTerrestreId
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{transporteTerrestreId: \\d+}")
    public TransporteTerrestreDTO getTransporte(@PathParam("proveedorId") Long proveedorId, @PathParam("transporteTerrestreId") Long transporteTerrestreId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorTransportesResource getTransporte: input: transportesId: {0} , alojamientosId: {1}", new Object[]{proveedorId, transporteTerrestreId});
        TransporteTerrestreDTO transporteDTO = new TransporteTerrestreDTO(proveedorTransportesLogic.getTransporteTerrestre(proveedorId, transporteTerrestreId));
        LOGGER.log(Level.INFO, "ProveedorTransportesResource getTransporte: output: {0}", transporteDTO.toString());
        return transporteDTO;
    }
    
//    /**
//     * 
//     * @param proveedoresId
//     * @param transportes
//     * @return
//     * @throws BusinessLogicException 
//     */
//    @PUT
//    public List<TransporteTerrestreDTO> replaceTransportes(@PathParam("proveedoresId") Long proveedoresId, List<TransporteTerrestreDTO> transportes) throws BusinessLogicException{
//        LOGGER.log(Level.INFO, "ProveedorTransportesResource replaceTransportes: input: proveedoresId: {0} , transportes: {1}", new Object[]{proveedoresId, transportes.toString()});
//        for (TransporteTerrestreDTO transporte : transportes) {
//            if (transporteLogic.getTransporteTerrestre(transporte.getId()) == null) {
//                throw new WebApplicationException("El recurso /transportes/" + transporte.getId() + " no existe.", 404);
//            }
//        }
//        List<TransporteTerrestreDTO> listaDetailDTOs = transportesListEntity2DTO(proveedorTransportesLogic.replaceTransportes(proveedoresId, transportesListDTO2Entity(transportes)));
//        LOGGER.log(Level.INFO, "ProveedorTransportesResource replaceTransportes: output: {0}", listaDetailDTOs.toString());
//        return listaDetailDTOs;
//    }
    
    /**
     * 
     * @param dtos
     * @return 
     */
    private List<TransporteTerrestreEntity> transportesListDTO2Entity(List<TransporteTerrestreDTO> dtos) {
        List<TransporteTerrestreEntity> list = new ArrayList<>();
        for (TransporteTerrestreDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
