/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ComboDTO;
import co.edu.uniandes.csw.viajes.dtos.ComboDetailDTO;
import co.edu.uniandes.csw.viajes.dtos.VueloDTO;
import co.edu.uniandes.csw.viajes.ejb.ComboLogic;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;

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
        
        LOGGER.log(Level.INFO, "ComboResource createCombo: input: {0}", combo.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ComboEntity comboEntity = combo.toEntity();
        // Invoca la lógica para crear la editorial nueva
        ComboEntity nuevoComboEntity = comboLogic.createCombo(comboEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        ComboDTO nuevoComboDTO = new ComboDTO(nuevoComboEntity);
        LOGGER.log(Level.INFO, "ComboResource createCombo: output: {0}", nuevoComboDTO.toString());
        return nuevoComboDTO;
       
//        return combo;
    }
    
     /**
     * Busca y devuelve todos los combos que existen en la aplicacion.
     *
     * @return JSONArray {@link EditorialDetailDTO} - Los combos
     * encontradas en la aplicación. Si no hay ninguni retorna una lista vacía.
     */
    @GET
    public List<ComboDetailDTO> getCombos() {
        LOGGER.info("ComboResource getCombos: input: void");
        List<ComboDetailDTO> listaCombos = listEntity2DetailDTO(comboLogic.getCombos());
        LOGGER.log(Level.INFO, "ComboResource getCombos: output: {0}", listaCombos.toString());
        return listaCombos;
    }
    
   /**
     * Busca el combo con el id asociado recibido en la URL y la devuelve.
     *
     * @param comboId Identificador del combo que se esta buscando.
     * Este debe ser una cadena de caracteres.
     * @return JSON {@link ComboDetailDTO} - El combo buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el combo.
     */
    @GET
        @Path("{comboId: \\d+}")
    public ComboDTO consultarCombo(@PathParam("comboId") Long comboId) throws WebApplicationException
    {
//        LOGGER.log(Level.INFO, "ComboResource getCombo: input: {0}", comboId);
//        ComboEntity comboEntity = comboLogic.getCombo(comboId);
//        if (comboEntity == null) {
//            throw new WebApplicationException("El recurso /combos/" + comboId + " no existe.", 404);
//        }
//        ComboDetailDTO comboDetailDTO=new ComboDetailDTO(comboEntity);
//        LOGGER.log(Level.INFO, "ComboResource getCombo: output: {0}", comboDetailDTO.toString());
        
        ComboDTO comboDTO = new ComboDTO();
        comboDTO.setComboIdLong(comboId);
        return comboDTO;
    }
  
    /**
     * Actualiza el combo con el id recibido en la URL con la informacion
 que se recibe en el cuerpo de la petición.
     *
     * @param comboId Identificador del combo que se desea
 actualizar. Este debe ser una cadena de caracteres.
     * @param combo {@link EditorialDetailDTO} El combo que se desea
 guardar.
     * @return JSON {@link ComboDetailDTO} - EL combo guardado.
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
 Error de lógica que se genera cuando no se encuentra el combo a
 actualizar.
     */
    @PUT
    @Path("{comboId: \\d+}")
    public ComboDetailDTO updateCombo(@PathParam("comboId") Long comboId, ComboDetailDTO combo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EditorialResource updateEditorial: input: id:{0} , editorial: {1}", new Object[]{comboId, combo.toString()});
        combo.setComboIdLong(comboId);
  
        if (comboLogic.getCombo(comboId) == null) {
            throw new WebApplicationException("El recurso /combos/" + comboId + " no existe.", 404);
        }
        ComboDetailDTO comboDetailDTO = new ComboDetailDTO(comboLogic.updateCombo(comboId, combo.toEntity()));
        LOGGER.log(Level.INFO, "ComboResource updateCombo: output: {0}", comboDetailDTO.toString());
        return comboDetailDTO;

    }

      /**
     * Borra la editorial con el id asociado recibido en la URL.
     *
     * @param comboId Identificador del combo que se desea borrar.
     * Este debe ser una cadena de caracteres.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el combo.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el combo.
     */
    @DELETE
    @Path("{comboId: \\d+}")
    public void deleteEditorial(@PathParam("comboId") Long comboId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ComboResource deleteCombo: input: {0}", comboId);
        if (comboLogic.getCombo(comboId) == null) {
            throw new WebApplicationException("El recurso /combos/" + comboId + " no existe.", 404);
        }
        comboLogic.deleteCombo(comboId);
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
    private List<ComboDetailDTO> listEntity2DetailDTO(List<ComboEntity> entityList) {
        List<ComboDetailDTO> list = new ArrayList<>();
        for (ComboEntity entity : entityList) {
            list.add(new ComboDetailDTO(entity));
        }
        return list;
    }
    
}
