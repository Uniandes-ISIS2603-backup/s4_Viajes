/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.PagoDTO;
import co.edu.uniandes.csw.viajes.dtos.ReservaDTO;
import co.edu.uniandes.csw.viajes.ejb.ReservaLogic;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.ArrayList;
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
 * @author jd.barriosc
 */
@Path("reservas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped 
public class ReservaResource {
     /**
     * Clase que implementa el recurso "pago".
     *
     * 
     * @version 1.0
     */

    private static final Logger LOGGER = Logger.getLogger(ReservaResource.class.getName());

    @Inject
    ReservaLogic reservaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

   /**
     * Crea un nuevo pago con la informacion que se recibe en el cuerpo de la
 petición y se regresa un objeto identico con un id auto-generado por la
 base de datos.
     *
     * @param reserva {@link ReservaDTO} - La reserva que se desea guardar.
     * @return JSON {@link ReservaDTO} - La reserva guardado con el atributo id
 autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
 Error de lógica que se genera cuando ya existe la reserva.
     */
    @POST
     public ReservaDTO crearReserva(ReservaDTO reserva) throws BusinessLogicException, Exception {
        
        LOGGER.log(Level.INFO, "ReservaResource createReserva: input: {0}", reserva.toString());
        ReservaEntity reservaEntity= reserva.toEntity();
        ReservaDTO nuevaReservaDTO = new ReservaDTO(reservaLogic.createReserva(reservaEntity));

        LOGGER.log(Level.INFO, "ReservaResource createReserva: output: {0}", nuevaReservaDTO.toString());
        return nuevaReservaDTO;
       
    }
    
     /**
     * Busca y devuelve todos las reservas que existen en la aplicacion.
     *
     * @return JSONArray {@link PagoDTO} - Las reservas
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ReservaDTO> getReservas() throws BusinessLogicException {
        LOGGER.info("ReservaResource getReservas: input: void");
        List<ReservaDTO> listaReservas = listEntity2DetailDTO(reservaLogic.getReservas());
        LOGGER.log(Level.INFO, "ReservaResource getReservas: output: {0}", listaReservas.toString());
        return listaReservas;
    }
    
   /**
     * Busca el pago con el id asociado recibido en la URL y la devuelve.
     *
     * @param reservaId Identificador del pago que se esta buscando.
     * Este debe ser una cadena de caracteres.
     * @return JSON {@link PagoDTO} - El pago buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago.
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    @GET
        @Path("{reservaId: \\d+}")
    public ReservaDTO consultarReserva(@PathParam("reservaId") Long reservaId) throws WebApplicationException, BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ReservaResource getReserva: input: {0}", reservaId);
        ReservaEntity reservaEntity;
        try {
            reservaEntity = reservaLogic.getReserva(reservaId);
        } catch (Exception ex) {
            throw new WebApplicationException("El recurso /reservas/" + reservaId + " tiene un error:"+ex.getMessage(), 404);
        }
        if (reservaEntity == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservaId + " no existe.", 404);
        }
        ReservaDTO reservaDTO=new ReservaDTO(reservaEntity);
        LOGGER.log(Level.INFO, "ReservaResource getReserva: output: {0}", reservaDTO.toString());
        
         return reservaDTO;
    }
  
    /**
     * Actualiza el pago con el id recibido en la URL con la informacion
 que se recibe en el cuerpo de la petición.
     *
     * @param reservaId Identificador del pago que se desea
 actualizar. Este debe ser una cadena de caracteres.
     * @param reserva {@link PagoDTO} El pago que se desea
 guardar.
     * @return JSON {@link PagoDTO} - EL pago guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
 Error de lógica que se genera cuando no se encuentra el pago a
 actualizar.
     */
    @PUT
    @Path("{reservaId: \\d+}")
    public ReservaDTO updateReserva(@PathParam("reservaId") Long reservaId, ReservaDTO reserva) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ReservaResource updateReserva: input: id:{0} , reserva: {1}", new Object[]{reservaId, reserva.toString()});
 
        ReservaEntity reservaEntity;
        try {
            reservaEntity = reserva.toEntity();
        } catch (Exception ex) {
            throw new WebApplicationException("El recurso /reservas/" + reservaId + " tiene un error:"+ex.getMessage(), 404);
        }
        reservaEntity.setId(reservaId);
        ReservaDTO reservaDTO = new ReservaDTO(reservaLogic.updateReserva(reservaId, reservaEntity));
        LOGGER.log(Level.INFO, "ReservaResource updatePago: output: {0}", reservaDTO.toString());
        return reservaDTO;

    }

      /**
     * Borra el pago con el id asociado recibido en la URL.
     *
     * @param reservaId Identificador del pago que se desea borrar.
     * Este debe ser una cadena de caracteres.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el pago.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago.
     */
    @DELETE
    @Path("{reservaId: \\d+}")
    public void deleteReserva(@PathParam("reservaId") Long reservaId) throws BusinessLogicException, Exception {
        LOGGER.log(Level.INFO, "ReservaResource deleteReserva: input: {0}", reservaId);
        if (reservaLogic.getReserva(reservaId) == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservaId + " no existe.", 404);
        }
        reservaLogic.deleteReserva(reservaId);
        LOGGER.info("ReservaResource deleteReserva: output: void");
    }
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos EditorialDetailDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de editoriales en forma DTO (json)
     */
    private List<ReservaDTO> listEntity2DetailDTO(List<ReservaEntity> entityList) {
        List<ReservaDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDTO(entity));
        }
        return list;
    }
    
    

}
