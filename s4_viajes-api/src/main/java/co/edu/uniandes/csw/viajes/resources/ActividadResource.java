/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("editorials")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ActividadResource {
    


/**
 * Clase que implementa el recurso "actividad".
 *
 * 
 * @version 1.0
 */



    private static final Logger LOGGER = Logger.getLogger(ActividadResource.class.getName());

    @Inject
    //ActividadLogic actividadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva editorial con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param editorial {@link EditorialDTO} - La editorial que se desea
     * guardar.
     * @return JSON {@link EditorialDTO} - La editorial guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la editorial.
     */
  /**  @POST
    public EditorialDTO createEditorial(EditorialDTO editorial) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: input: {0}", editorial.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        EditorialEntity editorialEntity = editorial.toEntity();
        // Invoca la lógica para crear la editorial nueva
        EditorialEntity nuevoEditorialEntity = editorialLogic.createEditorial(editorialEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        EditorialDTO nuevoEditorialDTO = new EditorialDTO(nuevoEditorialEntity);
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevoEditorialDTO.toString());
        return nuevoEditorialDTO;
    }
    
    @GET
    public EditorialDTO consultarEditorial()
    {
        return new EditorialDTO();
    }

    /**
     * Borra la editorial con el id asociado recibido en la URL.
     *
     * @param editorialsId Identificador de la editorial que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{editorialsId: \\d+}")
    public void deleteEditorial(@PathParam("editorialsId") Long editorialsId) {
        LOGGER.log(Level.INFO, "EditorialResource deleteEditorial: input: {0}", editorialsId);
        // Invoca la lógica para borrar la editorial
        //editorialLogic.deleteEditorial(editorialsId);
        LOGGER.info("EditorialResource deleteEditorial: output: void");
    }
    
}
