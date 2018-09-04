/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.CarritoComprasDTO;
import co.edu.uniandes.csw.viajes.ejb.CarritoComprasLogic;
import co.edu.uniandes.csw.viajes.entities.CarritoComprasEntity;
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
 * @author estudiante
 */
@Path("carritoscompras")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CarritoComprasResource {
    
    
    private static final Logger LOGGER = Logger.getLogger(ActividadResource.class.getName());

    @Inject
    CarritoComprasLogic carritoComprasLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea un nuevo Carrito de Compras con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param carritoCompras {@link CarritoComprasDTO} - El Carrito que se desea
     * guardar.
     * @return JSON {@link ActividadDTO} - El Carrito guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la actividad.
     */
   @POST
    public CarritoComprasDTO createCarritoCompras(CarritoComprasDTO carritoCompras) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: input: {0}", carritoCompras.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        return carritoCompras;
    }
    
    @GET
    @Path("carritoscompras: \\d+")
    public CarritoComprasDTO consultarCarritoCompras(@PathParam("carritoComprasID") String carritoComprasID)
    {
         LOGGER.log(Level.INFO, "CarritoComprasResource getCarritoCompras: input: {0}", carritoComprasID);
           return new CarritoComprasDTO();
    }
    
    @PUT
    @Path("numero: \\d+")
    public CarritoComprasDTO modificarCarrito(@PathParam("carritoComprasID") Long carritoComprasID, CarritoComprasDTO nuevo) throws WebApplicationException {

        return nuevo;}

    /**
     * Borra la actividad con el id asociado recibido en la URL.
     *
     * @param actividadId Identificador de la actividad que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("actividadId: \\d+")
    public void deleteCarritoCompras(@PathParam("carritoID") Long carritoID) {
        LOGGER.log(Level.INFO, "CarritoComprasResource deleteCarritoCompras: input: {0}", carritoID);
        // Invoca la lógica para borrar la actividad
        //editorialLogic.deleteEditorial(editorialsId);
        LOGGER.info("ActividadResource deleteActividad: output: void");
    }
    
}