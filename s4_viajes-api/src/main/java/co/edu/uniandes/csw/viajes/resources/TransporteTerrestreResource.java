/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.TransporteTerrestreDTO;
import co.edu.uniandes.csw.viajes.ejb.ProveedorLogic;
import co.edu.uniandes.csw.viajes.ejb.TransporteTerrestreLogic;
import co.edu.uniandes.csw.viajes.ejb.TransporteTerrestreProveedorLogic;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
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
 * @author Ymespana
 */
@Path("transportes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TransporteTerrestreResource {

    private static final Logger LOGGER = Logger.getLogger(TransporteTerrestreResource.class.getName());

    @Inject
    private TransporteTerrestreLogic transporteTerrestreLogic; // Variable para acceder a la lógica de la aplicación.

    @Inject
    private ProveedorLogic proveedorLogic;

    @Inject
    private TransporteTerrestreProveedorLogic transporteProveedorLogic;

    /**
     * Crea un nuevo transporte con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param transporteT {@link transporteDTO} - EL transporte que se desea
     * guardar.
     * @return JSON {@link TransporteDTO} - El transporte guardado con el
     * atributo id.
     * @throws BusinessLogicException Si la -------- ingresada es invalida.
     */
    @POST
    public TransporteTerrestreDTO createTransporte(TransporteTerrestreDTO transporteT) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TransporteTerrestreResource createTransporte: input: {0}", transporteT.toString());
        TransporteTerrestreDTO nuevoTransporteTerrestreDTO
                = new TransporteTerrestreDTO(transporteTerrestreLogic.createTransporte(transporteT.toEntity()));
        LOGGER.log(Level.INFO, "TransporteTResource createTransporte: output: {0}", nuevoTransporteTerrestreDTO.toString());
        return nuevoTransporteTerrestreDTO;
    }

    /**
     * Busca y devuelve todos los transportes que existen en la aplicacion.
     * (DEBERIA RETORNAR DTOs)
     *
     * @return Todos los transportes.
     */
    @GET
    public List<TransporteTerrestreDTO> getTransportesTerrestres() {
        LOGGER.log(Level.INFO, "TrasnporteTerrestreResource getTransportes: input: void");
        List<TransporteTerrestreDTO> listaTransportes = listEntity2DetailDTO(transporteTerrestreLogic.getTransportes());
        LOGGER.log(Level.INFO, "TrasnporteResource getTrasnportes: output: {0}", listaTransportes.toString());
        return listaTransportes;
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos TransporteEntity a una lista
     * de objetos TransporteTerretreDTO (json)
     *
     * @param entityList corresponde a la lista de transportes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de transportes en forma DTO (json)
     */
    private List<TransporteTerrestreDTO> listEntity2DetailDTO(List<TransporteTerrestreEntity> entityList) {
        List<TransporteTerrestreDTO> list = new ArrayList<TransporteTerrestreDTO>();
        for (TransporteTerrestreEntity entity : entityList) {
            list.add(new TransporteTerrestreDTO(entity));
        }
        return list;
    }

    /**
     * Busca el transporte con el id asociado recibido en la URL y lo devuelve.
     *
     * @param transportesId Identificador del transporte que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link transporteTerrestreDTO}
     */
    @GET
    @Path("{transportesId: \\d+}")
    public TransporteTerrestreDTO getTransporte(@PathParam("trasnportesId") Long transportesId) {
        LOGGER.log(Level.INFO, "TransporteTerrestreResource getTransporte: input: {0}", transportesId);
        TransporteTerrestreEntity transporteTerrestreEntity = transporteTerrestreLogic.getTransporte(transportesId);
        if (transporteTerrestreEntity == null) {
            throw new WebApplicationException("El recurso /transportes/" + transportesId + " no existe.", 404);
        }
        TransporteTerrestreDTO TransporteDTO = new TransporteTerrestreDTO(transporteTerrestreEntity);
        LOGGER.log(Level.INFO, "TransporteTResource getTransporte: output: {0}", TransporteDTO.toString());
        return TransporteDTO;
    }

    /**
     * Actualiza el transporte con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param transportesId Identificador del transporte que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param transporte El transporte que se desea guardar.
     * @return JSON El transporte guardado.
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{transportesId: \\d+}")
    public TransporteTerrestreDTO updateTransporte(@PathParam("transportesId") Long transportesId, TransporteTerrestreDTO transporte) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TransporteTerrestreResource updateTransporteTerrestre: input: id: {0} , transporte: {1}",
                new Object[]{transportesId, transporte.toString()});
        transporte.setId(transportesId);
        TransporteTerrestreEntity transporteEntity = transporteTerrestreLogic.getTransporte(transportesId);
        if (transporteEntity == null) {
            throw new WebApplicationException("El recurso /transportes/" + transportesId + " no existe.", 404);
        }
        TransporteTerrestreDTO transporteDTO
                = new TransporteTerrestreDTO(transporteTerrestreLogic.updateTransporte(transportesId, transporte.toEntity()));
        LOGGER.log(Level.INFO, "TransporteTResource updateTrasnporte: output: {0}", transporte.toString());
        return transporteDTO;
    }

    /**
     * Borra el transporte con el id asociado recibido en la URL.
     *
     * @param transportesId Identificador del transporte que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException
     */
    @DELETE
    @Path("{transportesId: \\d+}")
    public void deleteTransporte( @PathParam("transportesId") Long transportesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TransporteTerrestreResource deleteTransporte: input: {0}", transportesId);
        TransporteTerrestreEntity entity = transporteTerrestreLogic.getTransporte(transportesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /transportes/" + transportesId + " no existe.", 404);
        }
        transporteProveedorLogic.removeProveedor(transportesId); 
        transporteTerrestreLogic.deleteTransporte(transportesId);
        LOGGER.info("TrasnporteResource deleteTransporte: output: void");
    }
}
