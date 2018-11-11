    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ActividadDTO;
import co.edu.uniandes.csw.viajes.dtos.ActividadDetailDTO;
import co.edu.uniandes.csw.viajes.ejb.ActividadLogic;
import co.edu.uniandes.csw.viajes.ejb.GuiaLogic;
import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
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
 * @author Juan Esteban Cantor
 */
@Path("actividad")
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
    
    @Inject
    GuiaLogic guiaLogic;
    /**
     * Crea una nueva editorial con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param actividad
     * @return JSON {@link ActividadDTO} - La editorial guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la actividad.
     */
   @POST
    public ActividadDTO createActividad(ActividadDTO actividad) throws BusinessLogicException {
        if(actividad == null) throw new BusinessLogicException("No se recibió ninguna actividad");
        LOGGER.log(Level.INFO, "ActividadResource createActividad: input: {0}", actividad.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ActividadEntity actividadEntity = actividad.toEntity();
        // Invoca la lógica para crear la actividad nueva
        ActividadEntity nuevoActividadEntity = actividadLogic.createActividad(actividadEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        ActividadDTO nuevoActividadDTO = new ActividadDTO(nuevoActividadEntity);
        LOGGER.log(Level.INFO, "ActividadResource createActividad: output: {0}", nuevoActividadDTO.toString());
        return nuevoActividadDTO;
    }
    
     /**
     * Busca y devuelve todos los libros que existen en la aplicacion.
     *
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ActividadDetailDTO> getActividades() {
        LOGGER.info("ActividadResource getActividades: input: void");
        List<ActividadDetailDTO> listaGuias = listEntity2DetailDTO(actividadLogic.getActividades());
        LOGGER.log(Level.INFO, "ActividadResource getActividades: output: {0}", listaGuias.toString());
        return listaGuias;
    }
    
    
    @GET
    @Path("{actividadId: \\d+}")
    public ActividadDetailDTO consultarActividad(@PathParam("actividadId") Long actividadId) throws WebApplicationException
    {
         LOGGER.log(Level.INFO, "ActividadResource consultarActividad: input: {0}", actividadId);
        ActividadEntity actividadEntity = actividadLogic.getActividadByIdentificador(actividadId);
        if (actividadEntity == null) {
            throw new WebApplicationException("El recurso /actividad/" + actividadId + " no existe.", 404);
        }
        ActividadDetailDTO detailDTO = new ActividadDetailDTO(actividadEntity);
        LOGGER.log(Level.INFO, "ActividadResource consultarActividad: output: {0}", detailDTO.toString());
        return detailDTO;
    }
    
    @PUT
    @Path("{actividadId: \\d+}")
    public ActividadDTO modificarActividad(@PathParam("actividadId") Long actividadId, ActividadDTO actividad) throws WebApplicationException, BusinessLogicException {
        LOGGER.log(Level.INFO, "ActividadResource modificarActividad: input: id:{0} , actividad: {1}", new Object[]{actividadId, actividad.toString()});
        actividad.setIdentificador(actividadId);
        if (actividadLogic.getActividad(actividadId) == null) {
            throw new WebApplicationException("El recurso /actividad/" + actividadId + " no existe.", 404);
        }
        ActividadDTO detailDTO = new ActividadDTO(actividadLogic.modificarActividad(actividadId, actividad.toEntity()));
        LOGGER.log(Level.INFO, "ActividadResource modificarActividad: output: {0}", detailDTO.toString());
        return detailDTO;}
    /**
     * Borra la actividad con el id asociado recibido en la URL.
     *
     * @param actividadId Identificador de la actividad que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    @DELETE
    @Path("{actividadId: \\d+}")
    public void deleteActividad(@PathParam("actividadId") Long actividadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ActividadResource deleteActividad: input: {0}", actividadId);
        // Invoca la lógica para borrar la actividad
        actividadLogic.deleteActividad(actividadId);
        LOGGER.info("ActividadResource deleteActividad: output: void");
    }
    
     /**
     * Borra todas las actividades.
     *
     
    @DELETE
    public void deleteAllActividades(){
        // Invoca la lógica para borrar las actividades
        actividadLogic.deleteAll();
 
    }*/
   
      /**
     * Conexión con el servicio de guias para una actividad.
     * {@link ActividadGuiaResource}
     *
     * Este método conecta la ruta de /actividad con las rutas de /guia que
     * dependen de la actividad, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de los guias de una actividad.
     *
     * @param actividadId El ID de la actividad con respecto a la cual se
     * accede al servicio.
     * @return El servicio de guias para esta editorial en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el guia.
     */
    @Path("{actividadId: \\d+}/guia")
    public Class<ActividadGuiaResource> getActividadGuiaResource(@PathParam("actividadId") Long actividadId) {
        if (actividadLogic.getActividad(actividadId) == null) {
            throw new WebApplicationException("El recurso /actividad/" + actividadId + " no existe.", 404);
        }
        return ActividadGuiaResource.class;
    }
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ActividadEntity a una lista de
     * objetos ActividadDetailDTO (json)
     *
     * @param entityList corresponde a la lista de actividades de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de actividades en forma DTO (json)
     */
    private List<ActividadDetailDTO> listEntity2DetailDTO(List<ActividadEntity> entityList) {
        List<ActividadDetailDTO> list = new ArrayList<>();
        entityList.forEach((entity) -> {
            list.add(new ActividadDetailDTO(entity));
        });
        return list;
    }
    
}
