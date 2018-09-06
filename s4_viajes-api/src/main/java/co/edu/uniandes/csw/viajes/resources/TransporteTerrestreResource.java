/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.TransporteTerrestreDTO;
import co.edu.uniandes.csw.viajes.ejb.TransporteTerrestreLogic;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
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
 * @author Ymespana
 */
@Path("transportes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped 
public class TransporteTerrestreResource {
      private static final Logger LOGGER = Logger.getLogger(TransporteTerrestreResource.class.getName());

    @Inject
    private TransporteTerrestreLogic transporteTerrestreLogic; // Variable para acceder a la lógica de la aplicación.
    
    
    /**
     * Crea un nuevo alojamiento con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     * @param transporteT {@link AlojamientoDTO} - EL alojamiento que se desea guardar.
     * @return JSON {@link AlojamientoDTO} - El alojamiento guardado con el atributo id.
     * @throws Exception Si la -------- ingresada es invalida.
     */
    @POST
    public TransporteTerrestreDTO createTransporte(TransporteTerrestreDTO transporteT) throws Exception 
    {
        LOGGER.log(Level.INFO, "TransporteTResource createTransporte: input: {0}", transporteT.toString());
        TransporteTerrestreDTO nuevoTransporteTerrestreDTO = 
                new TransporteTerrestreDTO(transporteTerrestreLogic.createTransporte(transporteT.toEntity())); 
        LOGGER.log(Level.INFO, "TransporteTResource createTransporte: output: {0}", nuevoTransporteTerrestreDTO.toString());
        return nuevoTransporteTerrestreDTO; 
    }
    
    /**
     * Busca y devuelve todos los alojamientos que existen en la aplicacion. (DEBERIA RETORNAR DTOs)
     * @return Todos los alojamientos.
     */
    @GET
    public List<TransporteTerrestreEntity> getTransportesTerrestres()
    {
        LOGGER.info("TrasnporteTResource getTrasnportes: input: void");
        List<TransporteTerrestreEntity> listaTrasnportes = transporteTerrestreLogic.getTransportes(); 
        LOGGER.log(Level.INFO, "TrasnporteResource getTrasnportes: output: {0}", listaTrasnportes.toString());
        return listaTrasnportes;
    }
    
    /**
     * Busca el transporte con el id asociado recibido en la URL y lo devuelve.
     * @param transportesId Identificador del alojamiento que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link transporteTerrestreDTO}
     */
    @GET
    @Path("{transportesId: \\d+}")
    public TransporteTerrestreDTO getTransporte (@PathParam ("trasnportesId") Long transportesId) 
    {
        LOGGER.log(Level.INFO, "TrasnporteTResource getTrasnportes: input: {0}", transportesId);
        TransporteTerrestreEntity transporteTerrestreEntity = transporteTerrestreLogic.getTransporte(transportesId);
        if (transporteTerrestreEntity == null) {
            throw new WebApplicationException("El recurso /transportes/" + transportesId + " no existe.", 404);  
        } 
        TransporteTerrestreDTO TransporteDTO = new TransporteTerrestreDTO(transporteTerrestreEntity);
        LOGGER.log(Level.INFO, "TransporteTResource getTransporte: output: {0}", TransporteDTO.toString());
        return TransporteDTO;
    } 
    
    /**
     * Actualiza el alojamiento con el id recibido en la URL con la información que se recibe en el cuerpo de la petición.
     * @param transportesId Identificador del alojamientoque se desea actualizar. Este debe ser una cadena de dígitos.
     * @param transporte El alojamiento que se desea guardar.
     * @return JSON  El alojamiento guardado.
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{alojamientosId: \\d+}")
    public TransporteTerrestreDTO updateTransporte(@PathParam("transportesId")Long transportesId, TransporteTerrestreDTO transporte)  throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "TransporteTResource updateTrasnporteT: input: id: {0} , transporte: {1}", 
                new Object[]{transportesId, transporte.toString()});
        transporte.setId(transportesId);
        if (transporteTerrestreLogic.getTransporte(transportesId) == null) 
            throw new WebApplicationException("El recurso /transportes/" + transportesId + " no existe.", 404);
        TransporteTerrestreDTO transporteDTO = 
                new TransporteTerrestreDTO(transporteTerrestreLogic.updateTransporte(transportesId, transporte.toEntity()));
        LOGGER.log(Level.INFO, "TransporteTResource updateTrasnporte: output: {0}",transporte.toString()); 
        return transporteDTO; 
    }
    
    /**
     * Borra el alojamiento con el id asociado recibido en la URL.
     * @param transportesId Identificador del alojamiento que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{alojamientosId: \\d+}")
    public void deleteTransporte(@PathParam ("transportesId")Long transportesId)  
    {
        LOGGER.log(Level.INFO, "TransporteTResource deleteTransporte: input: {0}", transportesId);
        TransporteTerrestreEntity entity = transporteTerrestreLogic.getTransporte(transportesId);
        if (entity == null) 
            throw new WebApplicationException("El recurso /transportes/" + transportesId + " no existe.", 404);
        transporteTerrestreLogic.deleteTransporte(transportesId);
        LOGGER.info("TrasnporteResource deleteTransporte: output: void");
    }
}
