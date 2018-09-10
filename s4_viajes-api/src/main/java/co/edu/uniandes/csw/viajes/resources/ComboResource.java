/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ComboDTO;
import co.edu.uniandes.csw.viajes.dtos.VueloDTO;
import co.edu.uniandes.csw.viajes.ejb.ComboLogic;

import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
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
 * @author jd.barriosc
 */
@Path("combos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped 
public class ComboResource {
    
    
    
    /**
     * Clase que implementa el recurso "actividad".
     *
     * 
     * @version 1.0
     */



    private static final Logger LOGGER = Logger.getLogger(ComboResource.class.getName());

    @Inject
    ComboLogic comboLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

   /**
     * Crea un nuevo combo con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param combo {@link ComboDTO} - El combo que se desea guardar.
     * @return JSON {@link ComboDTO} - El combo guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el combo.
     */
    @POST
    public ComboDTO crearCombo(ComboDTO combo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: input: {0}", combo.toString());

        return combo;
    }
    
    /**
     * Obtiene un combo con su información dada por su nombre, se retorna esta
     * información que fue previamente ingresada en formato JSON.
     *
     * @return un combo y su información de acuerdo a su nombre.
     */
    @GET
        @Path("{comboId: [a-zA-Z][a-zA-Z]*}")
    public ComboDTO consultarCombo(@PathParam("comboId") String comboId) throws WebApplicationException
    {
        // LOGGER.log(Level.INFO, "EditorialResource getEditorial: input: {0}", nombreCombo);
        //ComboEntity comboEntity = ActividadLogicgic.getActividad(nombreCombo);
        //if (comboEntity == null) {
        //    throw new WebApplicationException("El recurso /editorials/" + nombreCombo + " no existe.", 404);
        //}
        ComboDTO comboDTO = new ComboDTO();
        comboDTO.setId(comboId);
       
        //LOGGER.log(Level.INFO, "ActividadResource getActividad: output: {0}", comboDTO.toString());
        return comboDTO;
    }
  
    /**
     * Modifica la informacion de un vuelo dado por la información ingresada en
     * formato JSON.
     *
     * @param nuevo (@link VueloDTO) - el vuelo que desea modificar.
     */
    @PUT
    @Path("comboId")
    public VueloDTO modificarCombo(@PathParam("comboId")int comboId, VueloDTO nuevo) throws WebApplicationException
    {
       return nuevo;
    }

        /**
     * Borra el vuelo con el id asociado (número) recibido en la URL.
     *
     * @param vueloNum Identificador dl vuelo que se desea borrar. Este debe ser
     * una cadena de dígitos (int).
     */
    @DELETE
    @Path("comboId")
    public void deleteCombo(@PathParam("comboId") Long comboId) {
        //LOGGER.log(Level.INFO, "VueloResource deleteVuelo: input: {0}", vueloNum);
        // Invoca la lógica para borrar lel vuelo
        //editorialLogic.deleteEditorial(editorialsId);
        //LOGGER.info("VueloResource deleteVuelo: output: void");
    }
    
    
}
