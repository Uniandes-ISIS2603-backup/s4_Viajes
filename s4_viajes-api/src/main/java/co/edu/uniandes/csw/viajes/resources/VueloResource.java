/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.VueloDTO;
import co.edu.uniandes.csw.viajes.dtos.VueloDetailDTO;
import co.edu.uniandes.csw.viajes.ejb.VueloLogic;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.mappers.BusinessLogicExceptionMapper;
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
     * @param Vuelo {@link VueloDTO} - El vuelo que se desea guardar.
     * @return JSON {@link VueloDTO} - El vuelo guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el vuelo.
     */
    @POST
    public VueloDTO crearVuelo(VueloDTO vuelo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: input: {0}", vuelo.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        VueloEntity vueloEntity = vuelo.toEntity();
        // Invoca la lógica para crear la actividad nueva
        VueloEntity nuevoVueloEntity = vueloLogic.createVuelo(vueloEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        VueloDTO nuevoVueloDTO = new VueloDTO(nuevoVueloEntity);
        //LOGGER.log(Level.INFO, "VueloResource createVuelo: output: {0}", nuevoVueloDTO.toString());
        return nuevoVueloDTO;
    }

    /**
     * Obtiene un vuelo con su información dada por su úmero, se retorna esta
     * información que fue previamente ingresada en formato JSON.
     *
     * @return un vuelo y su información de acuerdo a su nombre.
     */
    @GET
    @Path("{vueloId: [a-zA-Z][a-zA-Z]*}")
    public VueloDTO consultarVuelo(@PathParam("vueloId") Long vueloId) 
    {
        LOGGER.log(Level.INFO, "VueloResource getVuelo: input(0)", vueloId);
        VueloEntity vueloEntity = vueloLogic.getVuelo(vueloId);
        if(vueloEntity == null)
        {
            throw new WebApplicationException("El recurso /vuelos/" + vueloId + "no existe.", 404);
        }
        VueloDTO detailDTO = new VueloDTO(vueloEntity);
        LOGGER.log(Level.INFO, "VueloResource getVuelo: output(0)", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Modifica la informacion de un vuelo dado por la información ingresada en
     * formato JSON.
     *
     * @param nuevo (@link VueloDTO) - el vuelo que desea modificar.
     */
    //@PUT
    //@Path("{numero: [a-zA-Z][a-zA-Z]*}")
    //public VueloDTO modificarVuelo(@PathParam("numero")Long vueloId) throws WebApplicationException
    //{
      // LOGGER.log(Level.INFO, "EditorialResource getEditorial: input: {0}", vueloId);
        //VueloEntity vueloEntity = VueloLogic.getVuelo(vueloId);
        //if (vueloEntity == null) {
        //    throw new WebApplicationException("El recurso /vuelos/" + numero + " no existe.", 404);
        //}
        //VueloDetailDTO detailDTO = new VueloDetailDTO(vueloEntity);
        //LOGGER.log(Level.INFO, "EditorialResource getEditorial: output: {0}", detailDTO.toString());
        //return detailDTO;
    //}

    /**
     * Borra el vuelo con el id asociado (número) recibido en la URL.
     *
     * @param vueloNum Identificador dl vuelo que se desea borrar. Este debe ser
     * una cadena de dígitos (int).
     */
    @DELETE
    @Path("{vueloNum: [a-zA-Z][a-zA-Z]*}")
    public void deleteVuelo(@PathParam("vueloNum") String vueloNum) {
        //LOGGER.log(Level.INFO, "VueloResource deleteVuelo: input: {0}", vueloNum);
        // Invoca la lógica para borrar lel vuelo
        //editorialLogic.deleteEditorial(editorialsId);
        //LOGGER.info("VueloResource deleteVuelo: output: void");
    }
}
