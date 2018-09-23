/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.AlojamientoDTO;
import co.edu.uniandes.csw.viajes.ejb.AlojamientoLogic;
import co.edu.uniandes.csw.viajes.ejb.ProveedorAlojamientosLogic;
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
 *
 * @author Yeferson Espana
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProveedorAlojamientosResource {
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorAlojamientosResource.class.getName());

    @Inject
    private ProveedorAlojamientosLogic proveedorAlojamientosLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private AlojamientoLogic alojamientoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un alojamiento dentro de un proveedor con la informacion que recibe el
     * la URL. Se devuelve el alojamiento que se guarda en el proveedor.
     *
     * @param proveedoresId Identificador del proveedor que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param alojamientosId Identificador del alojamiento que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AlojamientoDTO} - El alojamiento guardado en el proveedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el alojamiento.
     * @throws BusinessLogicException
     */
    @POST
    @Path("{alojamientosId: \\d+}")
    public AlojamientoDTO addAlojamiento(@PathParam("proveedoresId") Long proveedoresId, @PathParam("alojamientosId") Long alojamientosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorAlojamientosResource addAlojamiento: input: proveedoresID: {0} , alojamientosId: {1}", new Object[]{proveedoresId, alojamientosId});
        if (alojamientoLogic.getAlojamiento(alojamientosId) == null) {
            throw new WebApplicationException("El recurso /alojamientos/" + alojamientosId + " no existe.", 404);
        }
        AlojamientoDTO alojamientoDTO = new AlojamientoDTO(proveedorAlojamientosLogic.addAlojamiento(alojamientosId, proveedoresId));
        LOGGER.log(Level.INFO, "ProveedorAlojamientosResource addAlojamiento: output: {0}", alojamientoDTO.toString());
        return alojamientoDTO;
    }
    
    /**
     * 
     * @param proveedoresId
     * @return
     * @throws BusinessLogicException
     */
    @GET
    public List<AlojamientoDTO> getAlojamientos(@PathParam("proveedoresId") Long proveedoresId)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorAlojamientosResource getAlojamientos: input: {0}", proveedoresId);
        List<AlojamientoDTO> listaDetailDTOs = alojamientosListEntity2DTO(proveedorAlojamientosLogic.getAlojamientos(proveedoresId));
        LOGGER.log(Level.INFO, "ProveedorAlojamientosResource getAlojamientos: output: {0}", listaDetailDTOs.toString());
        return listaDetailDTOs;
    }
    
    /**
     * 
     * @param entityList
     * @return 
     */
    private List<AlojamientoDTO> alojamientosListEntity2DTO(List<AlojamientoEntity> entityList) {
        List<AlojamientoDTO> list = new ArrayList();
        for (AlojamientoEntity entity : entityList) {
            list.add(new AlojamientoDTO(entity));
        }
        return list;
    }
    
    /**
     * 
     * @param proveedoresId
     * @param alojamientosId
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{alojamientosId: \\d+}")
    public AlojamientoDTO getAlojamiento(@PathParam("proveedoresId") Long proveedoresId, @PathParam("alojamientosId") Long alojamientosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialBooksResource getBook: input: editorialsID: {0} , booksId: {1}", new Object[]{proveedoresId, alojamientosId});
        if (alojamientoLogic.getAlojamiento(alojamientosId) == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedoresId + "/alojamientos/" + alojamientosId + " no existe.", 404);
        } 
        AlojamientoDTO alojamientoDTO = new AlojamientoDTO(proveedorAlojamientosLogic.getAlojamiento(proveedoresId, alojamientosId));
        LOGGER.log(Level.INFO, "ProveedorAlojamientosResource getAlojamiento: output: {0}", alojamientoDTO.toString());
        return alojamientoDTO;
    }
    
    /**
     * 
     * @param proveedoresId
     * @param alojamientos
     * @return
     * @throws BusinessLogicException 
     */
    @PUT
    public List<AlojamientoDTO> replaceAlojamientos(@PathParam("proveedoresId") Long proveedoresId, List<AlojamientoDTO> alojamientos) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "ProveedorAlojamientosResource replaceAlojamientos: input: proveedoresId: {0} , alojamientos: {1}", new Object[]{proveedoresId, alojamientos.toString()});
        for (AlojamientoDTO alojamiento : alojamientos) {
            if (alojamientoLogic.getAlojamiento(alojamiento.getId()) == null) {
                throw new WebApplicationException("El recurso /alojamientos/" + alojamiento.getId() + " no existe.", 404);
            }
        }
        List<AlojamientoDTO> listaDetailDTOs = alojamientosListEntity2DTO(proveedorAlojamientosLogic.replaceAlojamientos(proveedoresId, alojamientosListDTO2Entity(alojamientos)));
        LOGGER.log(Level.INFO, "EditorialBooksResource replaceBooks: output: {0}", listaDetailDTOs.toString());
        return listaDetailDTOs;
    }
    
    /**
     * 
     * @param dtos
     * @return 
     */
    private List<AlojamientoEntity> alojamientosListDTO2Entity(List<AlojamientoDTO> dtos) {
        List<AlojamientoEntity> list = new ArrayList<>();
        for (AlojamientoDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
}
