/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ActividadDetailDTO;
import co.edu.uniandes.csw.viajes.dtos.GuiaDTO;
import co.edu.uniandes.csw.viajes.ejb.ActividadGuiaLogic;
import co.edu.uniandes.csw.viajes.ejb.GuiaLogic;
import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "actividad/{id}/guias".
 *
 * @author estudiante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ActividadGuiaResource {
    
    private static final Logger LOGGER = Logger.getLogger(ActividadGuiaResource.class.getName());

    @Inject
    private ActividadGuiaLogic actividadGuiaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private GuiaLogic guiaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un guia dentro de una actividad con la informacion que recibe el
     * la URL. Se devuelve el guia que se guarda en la actividad.
     *
     * @param actividadId Identificador de la actividad que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param guiaId Documento del guia que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link GuiaDTO} - El guia guardado en la actividad.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el guia.
     */
    @POST
    @Path("{guiaId: \\d+}")
    public GuiaDTO agregarGuia(@PathParam("actividadId") Long actividadId, @PathParam("guiaId") Long guiaId) {
        LOGGER.log(Level.INFO, "ActividadGuiaResource agregarGuia: input: actividadID: {0} , guiaId: {1}", new Object[]{actividadId, guiaId});
        if (guiaLogic.getGuia(guiaId) == null) {
            throw new WebApplicationException("El recurso /guia/" + guiaId + " no existe.", 404);
        }
        GuiaDTO guiaDTO = new GuiaDTO(actividadGuiaLogic.addGuia(guiaId, actividadId));
        LOGGER.log(Level.INFO, "ActividadGuiaResource addGuia: output: {0}", guiaDTO.toString());
        return guiaDTO;
    }

    /**
     * Busca y devuelve todos los guias que existen en la actividad.
     *
     * @param actividadId Identificador de la actividad que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link ActividadDetailDTO} - Los guia encontrados en la
     * guia. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<GuiaDTO> getGuias(@PathParam("actividadId") Long actividadId) {
        LOGGER.log(Level.INFO, "ActividadGuiaResource getGuias: input: {0}", actividadId);
        List<GuiaDTO> listaDTOs = guiasListEntity2DTO(actividadGuiaLogic.getGuias(actividadId));
        LOGGER.log(Level.INFO, "ActividadGuiaResource getGuias: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }

    /**
     * Busca el guia con el id asociado dentro de la actividad con id asociado.
     *
     * @param actividadId Identificador de la actividad que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param guiaId Identificador del guia que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link GuiaDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el guia.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el guia en la
     * actividad.
     */
    @GET
    @Path("{guiaId: \\d+}")
    public GuiaDTO getGuia(@PathParam("actividadId") Long actividadId, @PathParam("guiaId") Long guiaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ActividadGuiaResource getGuia: input: actividadID: {0} , guiaId: {1}", new Object[]{actividadId, guiaId});
        if (guiaLogic.getGuia(guiaId) == null) {
            throw new WebApplicationException("El recurso /actividad/" + actividadId + "/guia/" + guiaId + " no existe.", 404);
        }
        GuiaDTO GuiaDTO = new GuiaDTO(actividadGuiaLogic.getGuia(actividadId, guiaId));
        LOGGER.log(Level.INFO, "ActividadGuiaResource getGuia: output: {0}", GuiaDTO.toString());
        return GuiaDTO;
    }

    /**
     * Remplaza las instancias de Guia asociadas a una instancia de Actividad
     *
     * @param actividadId Identificador de la actividad que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param books JSONArray {@link GuiaDTO} El arreglo de guias nuevo para la
     * actividad.
     * @return JSON {@link GuiaDTO} - El arreglo de guias guardado en la
     * actividad.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el guia.
     */
    @PUT
    public List<GuiaDTO> replaceGuia(@PathParam("actividadId") Long actividadId, List<GuiaDTO> guias) {
        LOGGER.log(Level.INFO, "ActividadGuiaResource replaceGuias: input: actvidadId: {0} , guias: {1}", new Object[]{actividadId, guias.toString()});
        for (GuiaDTO guia : guias) {
            if (guiaLogic.getGuia(guia.getDocumento()) == null) {
                throw new WebApplicationException("El recurso /guia/" + guia.getDocumento() + " no existe.", 404);
            }
        }
        List<GuiaDTO> listaDTOs = guiasListEntity2DTO(actividadGuiaLogic.replaceGuias(actividadId, guiaListDTO2Entity(guias)));
        LOGGER.log(Level.INFO, "ActividadGuiaResource replaceGuia: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }

    /**
     * Convierte una lista de GuiaEntity a una lista de GuiaDTO.
     *
     * @param entityList Lista de GuiaEntity a convertir.
     * @return Lista de GuiaDTO convertida.
     */
    private List<GuiaDTO> guiasListEntity2DTO(List<GuiaEntity> entityList) {
        List<GuiaDTO> list = new ArrayList();
        for (GuiaEntity entity : entityList) {
            list.add(new GuiaDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de GuiaDTO a una lista de GuiaEntity.
     *
     * @param dtos Lista de GuiaDTO a convertir.
     * @return Lista de GuiaEntity convertida.
     */
    private List<GuiaEntity> guiaListDTO2Entity(List<GuiaDTO> dtos) {
        List<GuiaEntity> list = new ArrayList<>();
        for (GuiaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
}
