/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.EntradaDTO;
import co.edu.uniandes.csw.viajes.ejb.EntradaLogic;
import co.edu.uniandes.csw.viajes.entities.EntradaEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
//import co.edu.uniandes.csw.viajes.ejb.EntradaLogic;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.viajes.mappers.WebApplicationExceptionMapper;
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
 * @author Luis Gómez Amado
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EntradaResource {
     /**
     * Clase que implementa el recurso "entrada".
     *
     *
     * @version 1.0
     */
    private static final Logger LOGGER = Logger.getLogger(EntradaResource.class.getName());
    
    @Inject 
    private EntradaLogic entradaLogic;

    //@Inject
    //EntradaLogic entradaLogic; //variable para acceder a la lógica de la aplicación. Es una inyección de independencias.
    
     /**
     * Crea una nuevo entrada con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param userId El id del usuario al cual se le agrega la entrada
     * @param entrada {@link EntradaDTO} - La entrada que se desea guardar.
     * @return JSON {@link EntradaDTO} - La entrada guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la entrada.
     */
    @POST
    public EntradaDTO crearEntrada(@PathParam("usuarioId") Long userId,EntradaDTO entrada) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "EntradaResouce createEntrada: input: {0}", entrada.toString());
        
        EntradaEntity entradaEntity = entrada.toEntity();
        EntradaDTO nuevaEntradaDTO = new EntradaDTO(entradaLogic.createEntrada(userId, entradaEntity));
        return nuevaEntradaDTO;
    }
    
        /**
     * Busca y devuelve todas las entradas que existen de un usuario.
     *
     * @param documento El documento del usuario del cual se buscan las entradas
     * @return JSONArray {@link EntradaDTO} - Las entradas encontradas en el
     * usuario. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<EntradaDTO> getEntradas(@PathParam("usuarioId") Long userId)
    {
        LOGGER.log(Level.INFO, "EntradaResource getEntradas: input: {0}", userId);
        List<EntradaDTO> listaDTOs = listEntity2DTO(entradaLogic.getEntradas(userId));
        LOGGER.log(Level.INFO, "EditorialBooksResource getBooks: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }
    
     /**
     * Obtiene una entrada con su información dada por su número, se retorna esta
     * información que fue previamente ingresada en formato JSON.
     *
     * @param documento El user name del usuario del que se quiere buscar la entrada.
     * @param entradaId {@link EntradaDTO} - el id de la entrada que se desea obtener.
     * @return una entrada y su información de acuerdo a su nùmero.
     */
    @GET
        @Path("{id: \\d+}")
    public EntradaDTO consultarEntrada(@PathParam("usuarioId") Long userId, @PathParam("id") Long entradaId) 
    {
        LOGGER.log(Level.INFO, "EntradaResource getEntrada: input: {0}", entradaId);
        EntradaEntity entity = entradaLogic.getEntrada(userId, entradaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + userId + "/entradas/" + entradaId + " no existe.", 404);
        }
        EntradaDTO entradaDTO = new EntradaDTO(entity);
        LOGGER.log(Level.INFO, "EntradaResource getEntrada: output: {0}", entradaDTO.toString());
        return entradaDTO;
    }
    
    /**
     * Modifica la informacion de una entrada dada por la información ingresada en
     * formato JSON.
     * @param userId El id del usuario del cual se guarda la reseña
     * @param entradaId El numero de la entrada que se va a actualizar
     * @param nueva (@link EntradaDTO) - la entrada que desea modificar.
     */
    @PUT
    @Path("{id: \\d+}")
    public EntradaDTO modificarEntrada(@PathParam("usuarioId") Long userId,@PathParam("id")Long entradaId, EntradaDTO nueva) throws WebApplicationException, BusinessLogicException
    {
        LOGGER.log(Level.INFO, "EntradaResource updateEntrada: input: userId: {0} , entradaId: {1} , entrada:{2}", new Object[]{userId, entradaId, nueva.toString()});
        if (!entradaId.equals(nueva.getId())) {
            throw new BusinessLogicException("Los ids del Entrada no coinciden.");
        }
        EntradaEntity entity = entradaLogic.getEntrada(userId, entradaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + userId + "/entradas/" + entradaId + " no existe.", 404);

        }
        EntradaDTO entradaDTO = new EntradaDTO(entradaLogic.updateEntrada(userId, nueva.toEntity()));
        LOGGER.log(Level.INFO, "EntradaResource updateEntrada: output:{0}", entradaDTO.toString());
        return entradaDTO;
    }
    
        /**
     * Borra la reseña con el id asociado recibido en la URL.
     *
     * @param documento El documento del usuario del cual se va a eliminar la entrada.
     * @param entradaId El id de la entrada que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la entrada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la entrada.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEntrada(@PathParam("usuarioId") Long userId, @PathParam("id") Long entradaId) throws BusinessLogicException {
        EntradaEntity entity = entradaLogic.getEntrada(userId, entradaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + userId + "/entradas/" + entradaId + " no existe.", 404);
        }
        entradaLogic.deleteEntrada(userId, entradaId);
    }
    
            /**
     * Conexión con el servicio de comentarios para un entrada. {@link ComentarioResource}
     *
     * Este método conecta la ruta de /entradas con las rutas de /comentarios que
     * dependen del usuario, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las entradas.
     *
     * @param id El id de la entrada con respecto a la cual se accede al comentario.
     * @return El servicio de Comentarios para ese usuario en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la entrada.
     */
    @Path("{id: \\d+}/comentarios")
    public Class<ComentarioResource> getComentarioResource(@PathParam("id") Long entradaId) {
        return ComentarioResource.class;
    }

    private List<EntradaDTO> listEntity2DTO(List<EntradaEntity> entradas) {
        List<EntradaDTO> list = new ArrayList<EntradaDTO>();
        for (EntradaEntity entity : entradas) {
            list.add(new EntradaDTO(entity));
        }
        return list;
    }
}
