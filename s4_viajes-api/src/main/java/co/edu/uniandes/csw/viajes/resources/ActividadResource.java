/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ActividadDTO;
import co.edu.uniandes.csw.viajes.ejb.ActividadLogic;
import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
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
@Path("actividades")
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
    ActividadLogic actividadLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva editorial con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param Actividad {@link ActividadDTO} - La editorial que se desea
     * guardar.
     * @return JSON {@link ActividadDTO} - La editorial guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la actividad.
     */
   @POST
   @Path("create")
    public ActividadDTO createActividad(ActividadDTO actividad) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: input: {0}", actividad.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ActividadEntity actividadEntity = actividad.toEntity();
        // Invoca la lógica para crear la actividad nueva
        ActividadEntity nuevoActividadEntity = actividadLogic.createActividad(actividadEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        ActividadDTO nuevoActividadDTO = new ActividadDTO(nuevoActividadEntity);
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevoActividadDTO.toString());
        return actividad;
    }
    
    
    @GET
    public ActividadDTO consultarActividad()
    {
        return new ActividadDTO();
    }

    /**
     * Borra la editorial con el id asociado recibido en la URL.
     *
     * @param editorialsId Identificador de la actividad que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{ActividadId: \\d+}")
    public void deleteActividad(@PathParam("actividadId") Long editorialsId) {
        LOGGER.log(Level.INFO, "ActividadResource deleteActividad: input: {0}", editorialsId);
        // Invoca la lógica para borrar la actividad
        //editorialLogic.deleteEditorial(editorialsId);
        LOGGER.info("EditorialResource deleteActividad: output: void");
    }
    
}
