/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.AlojamientoDTO;
import co.edu.uniandes.csw.viajes.ejb.AlojamientoLogic;
import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.List;
//import java.util.List;
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

@Path("alojamientos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped 
public class AlojamientoResource 
{ 
    private static final Logger LOGGER = Logger.getLogger(AlojamientoResource.class.getName());

    @Inject
    private AlojamientoLogic alojamientoLogic; // Variable para acceder a la lógica de la aplicación.
    
    
    /**
     * Crea un nuevo alojamiento con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     * @param alojamiento {@link AlojamientoDTO} - EL alojamiento que se desea guardar.
     * @return JSON {@link AlojamientoDTO} - El alojamiento guardado con el atributo id.
     * @throws Exception Si la -------- ingresada es invalida.
     */
    @POST
    public AlojamientoDTO createAlojamiento(AlojamientoDTO alojamiento) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "AlojamientoResource createAlojamiento: input: {0}", alojamiento.toString());
        AlojamientoDTO nuevoAlojamientoDTO = new AlojamientoDTO(alojamientoLogic.createAlojamiento(alojamiento.toEntity())); 
        LOGGER.log(Level.INFO, "AlojamientoResource createAlojamiento: output: {0}", nuevoAlojamientoDTO.toString());
        return nuevoAlojamientoDTO;
    } 
    
    /**
     * Busca y devuelve todos los alojamientos que existen en la aplicacion. (DEBERIA RETORNAR DTOs)
     * @return Todos los alojamientos.
     */
    @GET
    public List<AlojamientoEntity> getAlojamientos()
    {
        LOGGER.info("AlojamientoResource getAlojamientos: input: void");
        List<AlojamientoEntity> listaAlojamientos = alojamientoLogic.getAlojamientos();
        LOGGER.log(Level.INFO, "AlojamientoResource getAlojamientos: output: {0}", listaAlojamientos.toString());
        return listaAlojamientos;
    } 
     
    /**
     * Busca el alojamiento con el id asociado recibido en la URL y lo devuelve.
     * @param alojamientosId Identificador del alojamiento que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link AlojamientoDTO}
     * @throws Exception id invalido 
     */
    @GET
    @Path("{alojamientosId: \\d+}")
    public AlojamientoDTO getAlojamiento (@PathParam ("alojamientosId")Long alojamientosId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "AlojamientoResource getALojamiento: input: {0}", alojamientosId);
        AlojamientoEntity alojamientoEntity = alojamientoLogic.getAlojamiento(alojamientosId);
        if (alojamientoEntity == null) {
            throw new BusinessLogicException("El recurso /books/" + alojamientosId + " no existe.");  
        } 
        AlojamientoDTO AlojamientoDTO = new AlojamientoDTO(alojamientoEntity);
        LOGGER.log(Level.INFO, "BookResource getBook: output: {0}", AlojamientoDTO.toString());
        return AlojamientoDTO;
    } 
    
    /**
     * Actualiza el alojamiento con el id recibido en la URL con la información que se recibe en el cuerpo de la petición.
     * @param alojamientosId Identificador del alojamientoque se desea actualizar. Este debe ser una cadena de dígitos.
     * @param alojamiento El alojamiento que se desea guardar.
     * @return JSON  El alojamiento guardado.
     */
    @PUT
    @Path("{alojamientosId: \\d+}")
    public AlojamientoDTO updateAlojamiento(@PathParam ("alojamientosId" )Long alojamientosId, AlojamientoDTO alojamiento) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ALojamientoResource updateAlojamiento: input: id: {0} , alojamiento: {1}", 
                new Object[]{alojamientosId, alojamiento.toString()});
        alojamiento.setId(alojamientosId);
        if (alojamientoLogic.getAlojamiento(alojamientosId) == null) 
            throw new WebApplicationException("El recurso /alojamientos/" + alojamientosId + " no existe.", 404);
        AlojamientoDTO alojamientoDTO = new AlojamientoDTO(alojamientoLogic.updateAlojamiento(alojamientosId, alojamiento.toEntity()));
        LOGGER.log(Level.INFO, "AlojamientoResource updateAlojamiento: output: {0}", alojamientoDTO.toString());
        return alojamientoDTO;  
    }
    
    /**
     * Borra el alojamiento con el id asociado recibido en la URL.
     * @param alojamientosId Identificador del alojamiento que se desea borrar. Este debe ser una cadena de dígitos.
     */ 
    @DELETE
    @Path("{alojamientosId: \\d+}")
    public void deleteBook(@PathParam ("alojamientosId")Long alojamientosId)  
    {
        LOGGER.log(Level.INFO, "AlojamientoResource deleteAlojamiento: input: {0}", alojamientosId);
        if (alojamientoLogic.getAlojamiento(alojamientosId) == null) 
            throw new WebApplicationException("El recurso /alojamientos/" + alojamientosId + " no existe.", 404); 
        alojamientoLogic.deleteAlojamiento(alojamientosId); 
        LOGGER.info("AlojamientoResource deleteAlojamiento: output: void");
    } 
}