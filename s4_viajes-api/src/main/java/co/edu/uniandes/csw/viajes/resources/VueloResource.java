/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ProveedorDetailDTO;
import co.edu.uniandes.csw.viajes.dtos.VueloDTO;
import co.edu.uniandes.csw.viajes.ejb.VueloLogic;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
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
@Path("vuelos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class VueloResource {

    /**
     * Clase que impkementa el recurso "vuelo".
     *
     *
     * @version 1.0
     */
    private static final Logger LOGGER = Logger.getLogger(VueloResource.class.getName());

    @Inject
    VueloLogic vueloLogic; //variable para acceder a la lógica de la aplicación. Es una inyección de independencias.

    /**
     * Crea un nuevo vuelo con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param vuelo {@link VueloDTO} - El vuelo que se desea guardar.
     * @return JSON {@link VueloDTO} - El vuelo guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el vuelo.
     */
    @POST
    public VueloDTO crearVuelo(VueloDTO vuelo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VueloResource createVuelo: input: {0}", vuelo.toString());
        VueloDTO nuevoVueloDTO = new VueloDTO(vueloLogic.createVuelo(vuelo.toEntity()));
        LOGGER.log(Level.INFO, "VueloResource createVuelo: output: {0}", nuevoVueloDTO.toString());
        return nuevoVueloDTO;
    }

    /**
     * Obtiene un vuelo con su información dada por su úmero, se retorna esta
     * información que fue previamente ingresada en formato JSON.
     *
     * @param vueloId 
     * 
     * @return un vuelo y su información de acuerdo a su nombre.
     */
    @GET
    @Path("{vueloId: \\d+}")
    public VueloDTO consultarVuelo(@PathParam("vueloId") Long vueloId) throws Exception 
    {
       
        LOGGER.log(Level.INFO, "VueloResource getVuelo: input: {0}", vueloId);
        VueloEntity vueloEntity = vueloLogic.getVuelo(vueloId);
        if (vueloEntity == null) {
            throw new WebApplicationException("El recurso /books/" + vueloId + " no existe.",404);
        }
        VueloDTO vueloDTO = new VueloDTO(vueloEntity);

        LOGGER.log(Level.INFO, "VueloResource geVuelo: output: {0}", vueloDTO.toString());
        return vueloDTO;
    }
    
    /**
     * Modifica la informacion de un vuelo dado por la información ingresada en
     * formato JSON.
     *
     * @param vueloId (@link VueloDTO) - el vuelo que desea modificar.
     * 
     * @return VueloDTO
     */
    @PUT
    @Path("{vueloId: \\d+}")
    public VueloDTO modificarVuelo(@PathParam("vueloId")Long vueloId) throws WebApplicationException
    {
       LOGGER.log(Level.INFO, "EditorialResource getEditorial: input: {0}", vueloId);
        VueloEntity vueloEntity = vueloLogic.getVuelo(vueloId);
        if (vueloEntity == null) {
            throw new WebApplicationException("El recurso /vuelos/" + vueloId + " no existe.", 404);
        }
        VueloDTO vueloDTO = new VueloDTO(vueloEntity);
        LOGGER.log(Level.INFO, "EditorialResource getEditorial: output: {0}", vueloDTO.toString());
        return vueloDTO;
    }

    /**
     * Borra el vuelo con el id asociado (número) recibido en la URL.
     *
     * @param vueloId Identificador dl vuelo que se desea borrar. Este debe ser
     * una cadena de dígitos (int).
     * 
     * @throws BusinessLogicException
     */
    @DELETE
    @Path("{vueloId: \\d+}")
    public void deleteVuelo(@PathParam("vueloId") Long vueloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VueloResource deleteVuelo: input: {0}", vueloId);
        VueloEntity entity = vueloLogic.getVuelo(vueloId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /vuelos/" + vueloId + " no existe.", 404);
        }
        vueloLogic.deleteVuelo(vueloId);
        LOGGER.info("VueloResource deleteVuelo: output: void");
    }
    
        /**
     * Busca y devuelve todos los proveedoresque existen en la aplicacion.
     *
     * @return JSONArray {@link ProveedorDetailDTO} - Los proveedores
     * encontrados en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<VueloDTO> getVuelos(){
        LOGGER.info("VueloResource getProveedores: input: void");
        List<VueloDTO> listaVuelos = listEntity2DetailDTO(vueloLogic.getVuelos());
        LOGGER.log(Level.INFO, "ProveedorResource getProveedores: output: {0}", listaVuelos.toString());
        return listaVuelos;
    }
    
        /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos EditorialDetailDTO (json)
     * @param entityList corresponde a la lista de proveedores de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de proveedores en forma DTO (json)
     */
    private List<VueloDTO> listEntity2DetailDTO(List<VueloEntity> entityList) {
        List<VueloDTO> list = new ArrayList<>();
        for (VueloEntity entity : entityList) {
            list.add(new VueloDTO(entity));
        }
        return list;
    }

}
