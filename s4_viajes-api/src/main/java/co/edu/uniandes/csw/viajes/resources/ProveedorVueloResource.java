/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ProveedorDTO;
import co.edu.uniandes.csw.viajes.dtos.ProveedorDetailDTO;
import co.edu.uniandes.csw.viajes.dtos.VueloDTO;
import co.edu.uniandes.csw.viajes.ejb.ProveedorVueloLogic;
import co.edu.uniandes.csw.viajes.ejb.VueloLogic;
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
 * Clase que implementa el recurso "proveedores/{id}/vuelos".
 * 
 * @author jf.torresp
 */
@Path("proveedores/{proveedorId: \\d+}/vuelos")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped 
public class ProveedorVueloResource {
    
    private static final Logger LOGGER = Logger.getLogger(ProveedorVueloResource.class.getName());

    @Inject
    private ProveedorVueloLogic proveedorVueloLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private VueloLogic vueloLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Guarda un libro dentro de un proveedor con la informacion que recibe el
     * la URL. Se devuelve el vuelo que se guarda en el proveedor.
     *
     * @param proveedorId Identificador del proveedor que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param vueloId Identificador del vuelo que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link VueloDTO} - El vuelo guardado en el proveedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el vuelo.
     */
    @POST
    @Path("{vueloId: \\d+}")
    public ProveedorDetailDTO addVuelo(@PathParam("proveedorId") Long proveedorId, @PathParam("vueloId") Long vueloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorVuelosResource addVuelo: input: vueloID: {0} , vuelosId: {1}", new Object[]{proveedorId, vueloId});
        ProveedorDetailDTO proveedorDTO = new ProveedorDetailDTO(proveedorVueloLogic.addVuelo(vueloId, proveedorId));
        LOGGER.log(Level.INFO, "ProveedorVueloResource addVuelo: output: {0}", proveedorDTO.toString());
        return proveedorDTO;
    }
    
    @POST
    public ProveedorDetailDTO crearVuelo(@PathParam("proveedorId") Long proveedorId,VueloDTO vuelo) throws BusinessLogicException {
        ProveedorDetailDTO proveedorDTO = new ProveedorDetailDTO(proveedorVueloLogic.addVuelo((vueloLogic.createVuelo(vuelo.toEntity())).getId(), proveedorId));
        return proveedorDTO;
    }

    
    /**
     * Busca y devuelve todos los vuelos que existen en el proveedor.
     *
     * @param proveedorId Identificador del proveedor que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link VueloDTO} - Los vuelos encontrados en la
     * proveedor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<VueloDTO> getVuelos(@PathParam("proveedorId") Long proveedorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorVueloResource getVuelos: input: {0}", proveedorId);
        List<VueloDTO> listaDTOs = vuelosListEntity2DTO(proveedorVueloLogic.getVuelos(proveedorId));
        LOGGER.log(Level.INFO, "ProveedorVueloResource geVuelos: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }
    
    /**
     * Busca el vuelo con el id asociado dentro del proveedor con id asociado.
     *
     * @param proveedorId Identificador dl proveedor que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param vueloId Identificador del vuelo que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link VueloDTO} - El vuelo buscado
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el vuelo en 
     * el proveedor
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el vuelo.
     */
    @GET
    @Path("{vueloId: \\d+}")
    public VueloDTO getVuelo(@PathParam("proveedorId") Long proveedorId, @PathParam("vueloId") Long vueloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorVueloResource getVuelo: input: proveedorID: {0} , vuelosId: {1}", new Object[]{proveedorId, vueloId});
        VueloDTO vueloDTO = new VueloDTO(proveedorVueloLogic.getVuelo(proveedorId, vueloId));
        LOGGER.log(Level.INFO, "ProveedorVueloResource getVuelo: output: {0}", vueloDTO.toString());
        return vueloDTO;
    }

//    /**
//     * Remplaza las instancias de Vuelo asociadas a una instancia de Proveedor
//     *
//     * @param proveedorId Identificador del proveedor que se esta
//     * remplazando. Este debe ser una cadena de dígitos.
//     * @param vuelos JSONArray {@link VueloDTO} El arreglo de vuelos nuevo para el
//     * proveedor.
//     * @return JSON {@link VueloDTO} - El arreglo de vuelos guardado en 
//     * el proveedor.
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra el vuelo.
//     */
//    @PUT
//    @Path("{vueloId: \\d+}")
//    public List<VueloDTO> replaceVuelos(@PathParam("proveedorId") Long proveedorId, List<VueloDTO> vuelos) {
//        LOGGER.log(Level.INFO, "ProveedorVueloResource replaceVuelos: input: proveedorId: {0} , vuelos: {1}", new Object[]{proveedorId, vuelos.toString()});
//        for (VueloDTO vuelo : vuelos) {
//            if (vueloLogic.getVuelo(vuelo.getId()) == null) {
//                throw new WebApplicationException("El recurso /vuelos/" + vuelo.getId() + " no existe.", 404);
//            }
//        }
//        List<VueloDTO> listaDTOs = vuelosListEntity2DTO(proveedorVueloLogic.replaceVuelos(proveedorId, vuelosListDTO2Entity(vuelos)));
//        LOGGER.log(Level.INFO, "ProveedorVueloResource replaceVuelos: output: {0}", listaDTOs.toString());
//        return listaDTOs;
//    }

    /**
     * Convierte una lista de VueloEntity a una lista de VueloDTO.
     *
     * @param entityList Lista de VueloEntity a convertir.
     * @return Lista de VueloDTO convertida.
     */
    private List<VueloDTO> vuelosListEntity2DTO(List<VueloEntity> entityList) {
        List<VueloDTO> list = new ArrayList();
        for (VueloEntity entity : entityList) {
            list.add(new VueloDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de VueloDTO a una lista de VueloEntity.
     *
     * @param dtos Lista de VueloDTO a convertir.
     * @return Lista de VueloEntity convertida.
     */
    private List<VueloEntity> vuelosListDTO2Entity(List<VueloDTO> dtos) {
        List<VueloEntity> list = new ArrayList<>();
        for (VueloDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    
    
}
