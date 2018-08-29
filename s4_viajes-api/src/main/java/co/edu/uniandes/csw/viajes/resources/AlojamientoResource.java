/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.AlojamientoDTO;
import co.edu.uniandes.csw.viajes.ejb.AlojamientoLogic;
import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ymespana
 */
//@Path("alojamientos")
//@Produces("application/json")
//@Consumes("application/json")
public class AlojamientoResource 
{ 
    private static final Logger LOGGER = Logger.getLogger(AlojamientoResource.class.getName());

//    @Inject
    private AlojamientoLogic alojamientoLogic; // Variable para acceder a la lógica de la aplicación.
    
    
    /**
     * Crea un nuevo alojamiento con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     * @param alojamiento {@link AlojamientoDTO} - EL alojamiento que se desea guardar.
     * @return JSON {@link AlojamientoDTO} - El alojamiento guardado con el atributo id.
     * @throws Exception Si la -------- ingresada es invalida.
     */
    public AlojamientoDTO createAlojamiento(AlojamientoDTO alojamiento) throws Exception 
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
//    @GET
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
//    @GET
//    @Path("{booksId: \\d+}")
//    @PathParam
    public AlojamientoDTO getAlojamiento (Long alojamientosId) throws Exception 
    {
        LOGGER.log(Level.INFO, "AlojamientoResource getALojamiento: input: {0}", alojamientosId);
        AlojamientoEntity alojamientoEntity = alojamientoLogic.getAlojamiento(alojamientosId);
        if (alojamientoEntity == null) {
            throw new Exception("El recurso /books/" + alojamientosId + " no existe.");  
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
     * @throws Exception id invalido
     */
//    @PUT
//    @Path("{alojamientosId: \\d+}")
//    @Path("alojamientosId")Param("alojamientosId"
    public AlojamientoDTO updateAlojamiento(Long alojamientosId, AlojamientoDTO alojamiento) throws Exception 
    {
        LOGGER.log(Level.INFO, "ALojamientoResource updateAlojamiento: input: id: {0} , alojamiento: {1}", 
                new Object[]{alojamientosId, alojamiento.toString()});
        alojamiento.setId(alojamientosId);
        if (alojamientoLogic.getAlojamiento(alojamientosId) == null) 
            throw new Exception("El recurso /alojamientos/" + alojamientosId + " no existe.");
        AlojamientoDTO alojamientoDTO = new AlojamientoDTO(alojamientoLogic.updateAlojamiento(alojamientosId, alojamiento.toEntity()));
        LOGGER.log(Level.INFO, "AlojamientoResource updateAlojamiento: output: {0}", alojamientoDTO.toString());
        return alojamientoDTO;
    }
    
    /**
     * Borra el alojamiento con el id asociado recibido en la URL.
     * @param alojamientosId Identificador del alojamiento que se desea borrar. Este debe ser una cadena de dígitos.
     * @throws Exception id invalido
     */
//    @DELETE
//    @Path("{booksId: \\d+}")
//    @PathParam("booksId")
    public void deleteBook(Long alojamientosId) throws Exception 
    {
        LOGGER.log(Level.INFO, "AlojamientoResource deleteAlojamiento: input: {0}", alojamientosId);
        AlojamientoEntity entity = alojamientoLogic.getAlojamiento(alojamientosId);
        if (entity == null) 
            throw new Exception("El recurso /books/" + alojamientosId + " no existe.");
        alojamientoLogic.deleteAlojamiento(alojamientosId);
        LOGGER.info("AlojamientoResource deleteAlojamiento: output: void");
    } 
}
