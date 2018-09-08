/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.EntradaDTO;
import co.edu.uniandes.csw.viajes.dtos.VueloDTO;
//import co.edu.uniandes.csw.viajes.ejb.EntradaLogic;
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
 * @author Luis Gómez Amado
 */
@Produces("application/json")
@Consumes("application/json")
@Path("entradas")
@RequestScoped
public class EntradaResource {
     /**
     * Clase que implementa el recurso "entrada".
     *
     *
     * @version 1.0
     */
    private static final Logger LOGGER = Logger.getLogger(EntradaResource.class.getName());

    //@Inject
    //EntradaLogic entradaLogic; //variable para acceder a la lógica de la aplicación. Es una inyección de independencias.
    
     /**
     * Crea una nuevo entrada con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param documento El documento del usuario al cual se le agrega la entrada
     * @param entrada {@link EntradaDTO} - La entrada que se desea guardar.
     * @return JSON {@link EntradaDTO} - La entrada guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el vuelo.
     */
    @POST
    public EntradaDTO crearEntrada(@PathParam("documento") String userName,EntradaDTO entrada) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EntradaResouce createEntrada: input: {0}", entrada.toString());

        return entrada;
    }
    
        /**
     * Busca y devuelve todas las reseñas que existen en un libro.
     *
     * @param documento El documento del usuario del cual se buscan las entradas
     * @return JSONArray {@link ReviewDTO} - Las reseñas encontradas en el
     * libro. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<EntradaDTO> getEntradas(@PathParam("documento") String userName) {
        return new ArrayList<EntradaDTO>();
    }
    
     /**
     * Obtiene una entrada con su información dada por su número, se retorna esta
     * información que fue previamente ingresada en formato JSON.
     *
     * @param documento El user name del usuario del que se quiere buscar la entrada.
     * @param entradaNum {@link EntradaDTO} - La entrada que se desea obtener.
     * @return una entrada y su información de acuerdo a su nùmero.
     */
    @GET
        @Path("{numero: \\d+}")
    public EntradaDTO consultarEntrada(@PathParam("documento") String documento, @PathParam("numero") int entradaNum) 
    {
        return new EntradaDTO();
    }
    
    /**
     * Modifica la informacion de una entrada dada por la información ingresada en
     * formato JSON.
     * @param documento El documento del usuario del cual se guarda la reseña
     * @param entradaNum El numero de la entrada que se va a actualizar
     * @param nueva (@link EntradaDTO) - el vuelo que desea modificar.
     */
    @PUT
    @Path("{numero: \\d+}")
    public EntradaDTO modificarEntrada(@PathParam("userName") String userName,@PathParam("numero")int entradaNum, EntradaDTO nueva) throws WebApplicationException
    {
       return nueva;
    }
    
        /**
     * Borra la reseña con el id asociado recibido en la URL.
     *
     * @param documento El documento del usuario del cual se va a eliminar la entrada.
     * @param entradaNum El numero de la reseña que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la entrada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @DELETE
    @Path("{numero: \\d+}")
    public void deleteEntrada(@PathParam("documento") String documento, @PathParam("numero") int entradaNum) {
        
    }
}
