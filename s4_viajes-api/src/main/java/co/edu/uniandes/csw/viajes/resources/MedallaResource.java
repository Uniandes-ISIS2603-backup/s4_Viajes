/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.EntradaDTO;
import co.edu.uniandes.csw.viajes.dtos.MedallaDTO;
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
 * @author Luis Gómez Amado
 */
public class MedallaResource {
         /**
     * Clase que implementa el recurso "medalla".
     *
     *
     * @version 1.0
     */
    private static final Logger LOGGER = Logger.getLogger(MedallaResource.class.getName());

    //@Inject
    //MedallaLogic medallaLogic; //variable para acceder a la lógica de la aplicación. Es una inyección de independencias.
    
     /**
     * Crea una nuevo entrada con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param medalla {@link MedallaDTO} - La medalla que se desea guardar.
     * @return JSON {@link MedallaDTO} - La medalla guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la medalla.
     */
    @POST
    public MedallaDTO crearMedalla(MedallaDTO medalla) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MedallaResouce createMedalla: input: {0}", medalla.toString());

        return medalla;
    }
    
        /**
     * Busca y devuelve todas las medallas que existen.
     *
     * @return JSONArray {@link MedallaDTO} - 
     * Retorna todas las medallas existentes. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<MedallaDTO> getMedallas() {
        return new ArrayList<MedallaDTO>();
    }
    
     /**
     * Obtiene una medalla con su información dada por su número, se retorna esta
     * información que fue previamente ingresada en formato JSON.
     *
     * @param medallaNum {@link MedallaDTO} - El número de la medalla que se desea obtener.
     * @return una entrada y su información de acuerdo a su nùmero.
     */
    @GET
        @Path("{numero: \\d+}")
    public MedallaDTO consultarMedalla(@PathParam("numero") int medallaNum) 
    {
        return new MedallaDTO();
    }
    
    /**
     * Modifica la informacion de una medalla dada por la información ingresada en
     * formato JSON.
     * @param medallaNum El numero de la entrada que se va a actualizar
     * @param nueva (@link EntradaDTO) - el vuelo que desea modificar.
     */
    @PUT
    @Path("{numero: \\d+}")
    public MedallaDTO modificarMedalla(@PathParam("numero")int medallaNum, MedallaDTO nueva) throws WebApplicationException
    {
       return nueva;
    }
    
        /**
     * Borra la medalla con el numero asociado recibido en la URL.
     *
     * @param medallaNum El numero de la reseña que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la entrada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reseña.
     */
    @DELETE
    @Path("{numero: \\d+}")
    public void deleteEntrada(@PathParam("numero") int medallaNum) {
        
    }
}
