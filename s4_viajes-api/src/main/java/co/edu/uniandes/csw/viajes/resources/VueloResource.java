/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.VueloDTO;
import co.edu.uniandes.csw.viajes.ejb.VueloLogic;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.mappers.BusinessLogicExceptionMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @authorJuan Felipe Torres
 */
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
     * Crea un nuevo vuelo con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param Vuelo {@link VueloDTO} - El vuelo que se desea
     * guardar.
     * @return JSON {@link VueloDTO} - La editorial guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la actividad.
     */
    @POST
    public VueloDTO crearVuelo(VueloDTO vuelo) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: input: {0}", vuelo.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        VueloEntity vueloEntity = vuelo.toEntity();
        // Invoca la lógica para crear la actividad nueva
        VueloEntity nuevoVueloEntity = vueloLogic.createVuelo(vueloEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        VueloDTO nuevoVueloDTO = new VueloDTO(nuevoVueloEntity);
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevoVueloDTO.toString());
        return nuevoVueloDTO;
    } 
    
     @GET
    public VueloDTO consultarVuelo()
    {
        return new VueloDTO();
    }
    
    /**
     * Borra la editorial con el id asociado recibido en la URL.
     *
     * @param editorialsId Identificador de la actividad que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{VueloNum: \\d+}")
    public void deleteVuelo(@PathParam("vueloNum") Long vueloNum) {
        LOGGER.log(Level.INFO, "VueloResource deleteVuelo: input: {0}", vueloNum);
        // Invoca la lógica para borrar lel vuelo
        //editorialLogic.deleteEditorial(editorialsId);
        LOGGER.info("VueloResource deleteVuelo: output: void");
    }
}
