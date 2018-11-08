/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ComboDTO;
import co.edu.uniandes.csw.viajes.dtos.PagoDTO;
import co.edu.uniandes.csw.viajes.ejb.ComboLogic;
import co.edu.uniandes.csw.viajes.ejb.PagoLogic;
import co.edu.uniandes.csw.viajes.entities.PagoEntity;
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
@Path("pagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped 
public class PagoResource {
    
    
    /**
     * Clase que implementa el recurso "pago".
     *
     * 
     * @version 1.0
     */

    private static final Logger LOGGER = Logger.getLogger(PagoResource.class.getName());

    @Inject
    PagoLogic pagoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

     @Inject
    ComboLogic comboLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
   /**
     * Crea un nuevo pago con la informacion que se recibe en el cuerpo de la
 petición y se regresa un objeto identico con un id auto-generado por la
 base de datos.
     *
     * @param pago {@link ComboDTO} - El pago que se desea guardar.
     * @return JSON {@link ComboDTO} - El pago guardado con el atributo id
 autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
 Error de lógica que se genera cuando ya existe el pago.
     */
    @POST
     public PagoDTO crearPago(PagoDTO pago) throws BusinessLogicException {
        
        
//        throw new BusinessLogicException("id: "+pago.getaPagar().getComboIdLong()+", nombre:"+pago.getaPagar().getNombre());
        LOGGER.log(Level.INFO, "PagoResource createPago: input: {0}", pago.toString());
        PagoEntity pagoEntity;
        try {
            pagoEntity = pago.toEntity();
        } catch (Exception ex) {
            throw new WebApplicationException("Error: "+ex.getMessage(), 404);
        }
        PagoDTO nuevoPagoDTO = new PagoDTO(pagoLogic.createPago(pagoEntity));

        LOGGER.log(Level.INFO, "PagoResource createPago: output: {0}", nuevoPagoDTO.toString());
        return nuevoPagoDTO;
       
    }
    
     /**
     * Busca y devuelve todos los pagos que existen en la aplicacion.
     *
     * @return JSONArray {@link PagoDTO} - Los pagos
     * encontradas en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PagoDTO> getPagos() {
        LOGGER.info("PagoResource getPagos: input: void");
        List<PagoDTO> listaPagos = listEntity2DetailDTO(pagoLogic.getPagos());
        LOGGER.log(Level.INFO, "PagoResource getPagos: output: {0}", listaPagos.toString());
        return listaPagos;
    }
    
   /**
     * Busca el pago con el id asociado recibido en la URL y la devuelve.
     *
     * @param pagoId Identificador del pago que se esta buscando.
     * Este debe ser una cadena de caracteres.
     * @return JSON {@link PagoDTO} - El pago buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago.
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    @GET
        @Path("{pagoId: \\d+}")
    public PagoDTO consultarPago(@PathParam("pagoId") Long pagoId) throws WebApplicationException, BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ComboResource getCombo: input: {0}", pagoId);
        PagoEntity pagoEntity = pagoLogic.getPago(pagoId);
        if (pagoEntity == null) {
            throw new WebApplicationException("El recurso /pagos/" + pagoId + " no existe.", 404);
        }
        PagoDTO pagoDTO=new PagoDTO(pagoEntity);
        LOGGER.log(Level.INFO, "ComboResource getCombo: output: {0}", pagoDTO.toString());
        
        
//        pagoDTO.setaPagar(combo);
        return pagoDTO;
    }
  
    /**
     * Actualiza el pago con el id recibido en la URL con la informacion
 que se recibe en el cuerpo de la petición.
     *
     * @param pagoId Identificador del pago que se desea
 actualizar. Este debe ser una cadena de caracteres.
     * @param pago {@link PagoDTO} El pago que se desea
 guardar.
     * @return JSON {@link PagoDTO} - EL pago guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
 Error de lógica que se genera cuando no se encuentra el pago a
 actualizar.
     */
    @PUT
    @Path("{pagoId: \\d+}")
    public PagoDTO updatePago(@PathParam("pagoId") Long pagoId, PagoDTO pago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PagoResource updatePago: input: id:{0} , pago: {1}", new Object[]{pagoId, pago.toString()});
  
        if (pagoLogic.getPago(pagoId) == null) {
            throw new WebApplicationException("El recurso /pagos/" + pagoId + " no existe.", 404);
        }
        PagoEntity pagoEntity;
        try {
            pagoEntity = pago.toEntity();
        } catch (Exception ex) {
            throw new WebApplicationException("Error: "+ex.getMessage(), 404);
        }
        pagoEntity.setId(pagoId);
        PagoDTO pagoDTO = new PagoDTO(pagoLogic.updatePago(pagoId, pagoEntity));
        LOGGER.log(Level.INFO, "PagoResource updatePago: output: {0}", pagoDTO.toString());
        return pagoDTO;

    }

      /**
     * Borra el pago con el id asociado recibido en la URL.
     *
     * @param pagoId Identificador del pago que se desea borrar.
     * Este debe ser una cadena de caracteres.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el pago.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el pago.
     */
    @DELETE
    @Path("{pagoId: \\d+}")
    public void deletePago(@PathParam("pagoId") Long pagoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PagoResource deletePago: input: {0}", pagoId);
        if (pagoLogic.getPago(pagoId) == null) {
            throw new WebApplicationException("El recurso /pagos/" + pagoId + " no existe.", 404);
        }
        pagoLogic.deletePago(pagoId);
        LOGGER.info("ComboResource deleteCombo: output: void");
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
    private List<PagoDTO> listEntity2DetailDTO(List<PagoEntity> entityList) {
        List<PagoDTO> list = new ArrayList<>();
        for (PagoEntity entity : entityList) {
            list.add(new PagoDTO(entity));
        }
        return list;
    }
    
    

  
}
