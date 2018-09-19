/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ActividadDTO;
import co.edu.uniandes.csw.viajes.dtos.GuiaDTO;
import co.edu.uniandes.csw.viajes.ejb.ActividadLogic;
import co.edu.uniandes.csw.viajes.ejb.GuiaLogic;
import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
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
 * @author Juan Esteban Cantor
 */
@Path("guia")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class GuiaResource {
    
    
/**
 * Clase que implementa el recurso "actividad".
 *
 * 
 * @version 1.0
 */



    private static final Logger LOGGER = Logger.getLogger(GuiaResource.class.getName());

    @Inject
    GuiaLogic guiaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva editorial con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param guia {@link GuiaDTO} - La editorial que se desea
     * guardar.
     * @return JSON {@link ActividadDTO} - La editorial guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la actividad.
     */
   @POST
    public GuiaDTO createGuia(GuiaDTO guia) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "GuiaResource createGuia: input: {0}", guia.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        GuiaEntity guiaEntity = guia.toEntity();
        // Invoca la lógica para crear la actividad nueva
        GuiaEntity nuevoActividadEntity = guiaLogic.createGuia(guiaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo

        GuiaDTO nuevoGuiaDTO = new GuiaDTO(guiaEntity);
        LOGGER.log(Level.INFO, "GuiaResource guiaEditorial: output: {0}", nuevoGuiaDTO.toString());

        LOGGER.log(Level.INFO, "GuiaResource guiaEditorial: output: {0}", nuevoGuiaDTO.toString());
       
        return nuevoGuiaDTO;

    }
    

    @GET
    @Path("guiaId: \\d+")
    public GuiaDTO consultarGuia(@PathParam("actividadId") Long guiaId) throws WebApplicationException
    {
         LOGGER.log(Level.INFO, "GuiaResource getResource: input: {0}", guiaId);
        GuiaEntity guiaEntity = guiaLogic.getGuia(guiaId);
        if (guiaEntity == null) {
            throw new WebApplicationException("El recurso /guia/" + guiaId + " no existe.", 404);
        }
        GuiaDTO detailDTO = new GuiaDTO(guiaEntity);
        LOGGER.log(Level.INFO, "GuiaResource getGuia: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Borra la guia con el id asociado recibido en la URL.
     *
     * @param guiaId Identificador de la guia que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("guiaId: \\d+")
    public void deleteGuia(@PathParam("guiaId") Long guiaId) {
        LOGGER.log(Level.INFO, "GuiaResource deleteGuia: input: {0}", guiaId);
        // Invoca la lógica para borrar la actividad
        guiaLogic.deleteGuia(guiaId);
        LOGGER.info("GuiaResource deleteGuia: output: void");
    }
    
    @PUT
    @Path("guiaId: \\d+")
    public GuiaDTO modificarGuia(@PathParam("guiaId") Long guiaId, GuiaDTO guia) throws WebApplicationException, BusinessLogicException {
        LOGGER.log(Level.INFO, "GuiaResource updateGuia: input: id:{0} , guia: {1}", new Object[]{guiaId, guia.toString()});
        guia.setDocumento(guiaId);
        if (guiaLogic.getGuia(guiaId) == null) {
            throw new WebApplicationException("El recurso /guia/" + guiaId + " no existe.", 404);
        }
        GuiaDTO detailDTO = new GuiaDTO(guiaLogic.modificarGuia(guiaId, guia.toEntity()));
        LOGGER.log(Level.INFO, "GuiaResource updateGuia: output: {0}", detailDTO.toString());
        return detailDTO;}
    
}
