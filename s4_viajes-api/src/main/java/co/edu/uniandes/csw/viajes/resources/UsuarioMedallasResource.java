/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.MedallaDTO;
import co.edu.uniandes.csw.viajes.dtos.UsuarioDTO;
import co.edu.uniandes.csw.viajes.ejb.MedallaLogic;
import co.edu.uniandes.csw.viajes.ejb.UsuarioMedallasLogic;
import co.edu.uniandes.csw.viajes.entities.MedallaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
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
 *
 * @author Luis Gómez Amado
 */
@Path("usuarios/{usuarioId: \\d+}/medallas")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped 
public class UsuarioMedallasResource {
    
     private static final Logger LOGGER = Logger.getLogger(UsuarioMedallasResource.class.getName());
     
     @Inject 
     private UsuarioMedallasLogic usuarioMedallasLogic;
     
     @Inject
     private MedallaLogic medallaLogic;
     
      /**
     * Guarda una medalla dentro de un usuario con la informacion que recibe el
     * la URL. Se devuelve la medalla que se guarda en el usuario.
     *
     * @param usuarioId Identificador del usuario que se esta
     * actualizando. Este debe ser una cadena de caracteres.
     * @param idMedalla Identificador de la medalla que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link MedallaDTO} - La medalla guardada en el usuario.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{idMedalla: \\d+}")
    public UsuarioDTO addMedalla(@PathParam("usuarioId") Long usuarioId, @PathParam("idMedalla") Long idMedalla) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioMedallasResource addMedalla: input: usuarioId: {0} , idMedalla: {1}", new Object[]{usuarioId, idMedalla});
        
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioMedallasLogic.addMedalla(idMedalla, usuarioId));
        LOGGER.log(Level.INFO, "UsuarioMedallasResource addMedalla: output: {0}", usuarioDTO.toString());
        return usuarioDTO;
    }
     
    /**
     * Busca y devuelve todas las medallas que tiene un usuario.
     *
     * @param usuariosId Identificador del usuario que se esta buscando.
     * Este debe ser una cadena de caractéres.
     * @return JSONArray {@link MedallaDTO} - Los libros encontrados en la
     * usuario. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<MedallaDTO> getMedallas(@PathParam("usuarioId") Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioMedallasResource getMedallas: input: {0}", usuarioId);
        List<MedallaDTO> listaDTOs = medallasListEntity2DTO(usuarioMedallasLogic.getMedallas(usuarioId));
        LOGGER.log(Level.INFO, "UsuarioMedallasResource getMedallas: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }
    
        /**
     * Busca y devuelve la medalla con el numero dado por parametro si la tiene un usuario.
     *
     * @param usuarioId Identificador del usuario que se esta
     * actualizando. Este debe ser una cadena de caracteres.
     * @param idMedalla Identificador de la medalla que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSONArray {@link MedallaDetailDTO} - Los libros encontrados en la
     * usuario. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    @Path("{idMedalla: \\d+}")
    public MedallaDTO getMedalla(@PathParam("usuarioId") Long usuarioId, @PathParam("idMedalla") Long idMedalla) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioMedallasResource getMedalla: input: usuariosID: {0} , idMedalla: {1}", new Object[]{usuarioId, idMedalla});
        MedallaDTO medallaDTO = new MedallaDTO(usuarioMedallasLogic.getMedalla(usuarioId, idMedalla));
        LOGGER.log(Level.INFO, "UsuarioMedallasResource getMedalla: output: {0}", medallaDTO.toString());
        return medallaDTO;
    }
    
//        /**
//     * Remplaza las instancias de Medalla asociadas a una instancia de Usuario
//     *
//     * @param usuarioId Identificador del usuario en el que se esta
//     * remplazando. Este debe ser una cadena de dígitos.
//     * @param medallas JSONArray {@link MedallaDTO} El arreglo de medallas nuevo para la
//     * usuario.
//     * @return JSON {@link MedallaDTO} - El arreglo de medallas guardado en la
//     * usuario.
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra el libro.
//     */
//    @PUT
//    public List<MedallaDTO> replaceMedallas(@PathParam("usuarioId") Long usuarioId, List<MedallaDTO> medallas) {
//        LOGGER.log(Level.INFO, "UsuarioMedallasResource replaceMedallas: input: usuariosId: {0} , medallas: {1}", new Object[]{usuarioId, medallas.toString()});
//        for (MedallaDTO medalla : medallas) {
//            if (medallaLogic.getMedalla(medalla.getId()) == null) {
//                throw new WebApplicationException("El recurso /medallas/" + medalla.getId() + " no existe.", 404);
//            }
//        }
//        List<MedallaDTO> listaDetailDTOs = medallasListEntity2DTO(usuarioMedallasLogic.replaceMedallas(usuarioId, medallasListDTO2Entity(medallas)));
//        LOGGER.log(Level.INFO, "UsuarioMedallasResource replaceMedallas: output: {0}", listaDetailDTOs.toString());
//        return listaDetailDTOs;
//    }
    
    /**
     * Convierte una lista de MedallaEntity a una lista de MedallaDetailDTO.
     *
     * @param entityList Lista de MedallaEntity a convertir.
     * @return Lista de MedallaDTO convertida.
     */
    private List<MedallaDTO> medallasListEntity2DTO(List<MedallaEntity> entityList) {
        List<MedallaDTO> list = new ArrayList();
        for (MedallaEntity entity : entityList) {
            list.add(new MedallaDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte una lista de MedallaDetailDTO a una lista de MedallaEntity.
     *
     * @param dtos Lista de MedallaDetailDTO a convertir.
     * @return Lista de MedallaEntity convertida.
     */
    private List<MedallaEntity> medallasListDTO2Entity(List<MedallaDTO> dtos) {
        List<MedallaEntity> list = new ArrayList<>();
        for (MedallaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
