/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ComentarioDTO;
import co.edu.uniandes.csw.viajes.dtos.EntradaDTO;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.viajes.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */
public class ComentarioResource {
        /**
     * Clase que implementa el recurso "entrada".
     *
     *
     * @version 1.0
     */
    private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class.getName());

    //@Inject
    //EntradaLogic entradaLogic; //variable para acceder a la lógica de la aplicación. Es una inyección de independencias.
    
     /**
     * Crea un nuevo comentario con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param numero El numero de la entrada a la cual se le agrega el comentario
     * @param comment {@link ComentarioDTO} - El comentario que se desea guardar.
     * @return JSON {@link ComentarioDTO} - El comentario guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el vuelo.
     */
    @POST
    public ComentarioDTO crearComentario(@PathParam("numero") int numEntrada,ComentarioDTO comment) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EntradaResouce createEntrada: input: {0}", comment.toString());

        return comment;
    }
    
        /**
     * Busca y devuelve todos los comentarios que existen de una entrada.
     *
     * @param numEntrada El número de la entrada de la cual se buscan los comentarios
     * @return JSONArray {@link ReviewDTO} - Los comentarios encontradas en el
     * libro. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ComentarioDTO> getComentarios(@PathParam("numero") int numEntrada) {
        return new ArrayList<ComentarioDTO>();
    }
    
     /**
     * Obtiene un comentario con su información dado por su número, se retorna esta
     * información que fue previamente ingresada en formato JSON.
     *
     * @param numEntrada El número de la entrada de la que se quiere buscar el comentario.
     * @param numComment {@link EntradaDTO} - El comentario que se desea obtener.
     * @return un comentario y su información de acuerdo a su nùmero.
     */
    @GET
        @Path("{numeroCom: \\d+}")
    public EntradaDTO consultarComentario(@PathParam("numero") int numEntrada, @PathParam("numeroCom") int numComment) 
    {
        return new EntradaDTO();
    }
    
    /**
     * Modifica la informacion de un comentario dada por la información ingresada en
     * formato JSON.
     * @param numEntrada El número de la entrada de la cual se guarda el comentario
     * @param numComment El numero de la entrada que se va a actualizar
     * @param nuevo (@link EntradaDTO) - la entrada que desea modificar.
     * @return El nuevo comentario modificado.
     */
    @PUT
    @Path("{numeroCom: \\d+}")
    public ComentarioDTO modificarComentario(@PathParam("numero") int numEntrada,@PathParam("numeroCom")int numComment, ComentarioDTO nuevo) throws WebApplicationException
    {
       return nuevo;
    }
    
        /**
     * Borra el comentario con el id asociado recibido en la URL.
     *
     * @param numEntrada El número de la entrada de la cual se va a eliminar el comentario.
     * @param numComment El numero del comentario que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el comentario.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la entrada.
     */
    @DELETE
    @Path("{numeroCom: \\d+}")
    public void deleteComentario(@PathParam("numero") int numEntrada, @PathParam("numeroCom") int numComment) {
        
    }
}
