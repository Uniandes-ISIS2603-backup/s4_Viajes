/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.MedallaDTO;
import co.edu.uniandes.csw.viajes.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 *
 * @author Luis Gómez Amado
 */
@Path("usuarios/{documento: [a-zA-Z][a-zA-Z]*}/medallas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioMedallasResource {
    
     private static final Logger LOGGER = Logger.getLogger(UsuarioMedallasResource.class.getName());
     
      /**
     * Guarda una medalla dentro de un usuario con la informacion que recibe el
     * la URL. Se devuelve la medalla que se guarda en el usuario.
     *
     * @param documento Identificador del usuario que se esta
     * actualizando. Este debe ser una cadena de caracteres.
     * @param numMedalla Identificador de la medalla que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link MedallaDTO} - La medalla guardada en el usuario.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{numMedalla: \\d+}")
    public MedallaDTO addMedalla(@PathParam("documento") String documento, @PathParam("numMedalla") int numMedalla) {
        LOGGER.log(Level.INFO, "EditorialBooksResource addBook: input: documento: {0} , numMedalla: {1}", new Object[]{documento, numMedalla});
        MedallaDTO medallaDTO = new MedallaDTO();
        return medallaDTO;
    }
     
    /**
     * Busca y devuelve todas las medallas que tiene un usuario.
     *
     * @param documento Identificador del usuario que se esta buscando.
     * Este debe ser una cadena de caractéres.
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en la
     * editorial. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<MedallaDTO> getMedallas(@PathParam("documento") String documento) {
        LOGGER.log(Level.INFO, "UsuarioMedallasResource getMedallas: input: {0}", documento);
        List<MedallaDTO> medallaDTOs = new ArrayList<MedallaDTO>();
        return medallaDTOs;
    }
    
        /**
     * Busca y devuelve la medalla con el numero dado por parametro si la tiene un usuario.
     *
     * @param documento Identificador del usuario que se esta
     * actualizando. Este debe ser una cadena de caracteres.
     * @param numMedalla Identificador de la medalla que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en la
     * editorial. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    @Path("{numero: \\d+}")
    public MedallaDTO getMedalla(@PathParam("documento") String documento, @PathParam("numero") int numMedalla) {
        LOGGER.log(Level.INFO, "UsuarioMedallasResource getMedalla: input: documento: {0} , numMedalla: {1}", new Object[]{documento, numMedalla});
        MedallaDTO medallaDTO = new MedallaDTO();
        return medallaDTO;
    }
    
        /**
     * Remplaza las instancias de Medalla asociadas a una instancia de Usuario
     *
     * @param documento Identificador del usuario en el que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param medallas JSONArray {@link BookDTO} El arreglo de medallas nuevo para la
     * editorial.
     * @return JSON {@link BookDTO} - El arreglo de medallas guardado en la
     * editorial.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @PUT
    public List<MedallaDTO> replaceMedallas(@PathParam("documento") String documento, List<MedallaDTO> medallas) {
        LOGGER.log(Level.INFO, "UsuarioMedallasResource replaceMedallas: input: documento: {0} , medallas: {1}", new Object[]{documento, medallas.toString()});
        List<MedallaDTO> listaMedallaDTOS = new ArrayList<MedallaDTO>();
        return listaMedallaDTOS;
    }
}
