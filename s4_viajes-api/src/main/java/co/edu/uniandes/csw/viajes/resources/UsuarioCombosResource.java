/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ComboDTO;
import co.edu.uniandes.csw.viajes.dtos.ComboDetailDTO;
import co.edu.uniandes.csw.viajes.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.viajes.ejb.ComboLogic;
import co.edu.uniandes.csw.viajes.ejb.MedallaLogic;
import co.edu.uniandes.csw.viajes.ejb.UsuarioCombosLogic;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.MedallaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Juan Diego Barrios
 */
@Path("usuarios/{usuarioId: \\d+}/combos")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped 
public class UsuarioCombosResource {
     private static final Logger LOGGER = Logger.getLogger(UsuarioCombosResource.class.getName());
     
     @Inject 
     private UsuarioCombosLogic usuarioCombosLogic;
     
     @Inject
     private ComboLogic comboLogic;
     
     @Inject
     private MedallaLogic medallaLogic;
     
     
      /**
     * Guarda una medalla dentro de un usuario con la informacion que recibe el
     * la URL. Se devuelve la medalla que se guarda en el usuario.
     *
     * @param usuarioId Identificador del usuario que se esta
     * actualizando. Este debe ser una cadena de caracteres.
     * @param idCombo Identificador de la medalla que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link MedallaDTO} - La medalla guardada en el usuario.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{idCombo: \\d+}")
    public UsuarioDetailDTO addCombo(@PathParam("usuarioId") Long usuarioId, @PathParam("idCombo") Long idCombo) throws BusinessLogicException {       
       
          MedallaEntity medalla=medallaLogic.findByName("PrimerCombo");
            if(medalla==null)
            {
               medalla=new MedallaEntity();
               medalla.setDescripcion("Medalla otorgada por crear su primer combo!!");
               medalla.setNombre("PrimerCombo");
               medalla.setRutaImagen("https://i.imgur.com/2lcAHkn.png");
               medallaLogic.createMedalla(medalla);
            }
        UsuarioDetailDTO usuarioDTO = new UsuarioDetailDTO(usuarioCombosLogic.addCombo(idCombo, usuarioId));
       
        return usuarioDTO;
    }
    
     @POST
    public UsuarioDetailDTO crearCombo(@PathParam("usuarioId") Long usuarioId,ComboDetailDTO combo) throws BusinessLogicException {
        
        
        MedallaEntity medalla=medallaLogic.findByName("PrimerCombo");
            if(medalla==null)
            {
               medalla=new MedallaEntity();
               medalla.setDescripcion("Medalla otorgada por crear su primer combo!!");
               medalla.setNombre("PrimerCombo");
               medalla.setRutaImagen("https://i.imgur.com/2lcAHkn.png");
               medallaLogic.createMedalla(medalla);
            }
        LOGGER.log(Level.INFO, "ComboResource createCombo: input: {0}", combo.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ComboEntity comboEntity;
        try {
            comboEntity = combo.toEntity();
        } catch (Exception ex) {
            throw new BusinessLogicException("ERROR: "+ex.getMessage());
        }
       
        UsuarioDetailDTO usuarioDTO = new UsuarioDetailDTO(usuarioCombosLogic.addCombo((comboLogic.createCombo(comboEntity)).getId(), usuarioId));
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
    public List<ComboDTO> getCombos(@PathParam("usuarioId") Long usuarioId) throws BusinessLogicException {
        List<ComboDTO> listaDTOs = combosListEntity2DTO(usuarioCombosLogic.getCombos(usuarioId));
        return listaDTOs;
    }
    
        /**
     * Busca y devuelve la medalla con el numero dado por parametro si la tiene un usuario.
     *
     * @param usuarioId Identificador del usuario que se esta
     * actualizando. Este debe ser una cadena de caracteres.
     * @param idCombo Identificador de la medalla que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSONArray {@link MedallaDetailDTO} - Los libros encontrados en la
     * usuario. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    @Path("{idCombo: \\d+}")
    public ComboDTO getCombo(@PathParam("usuarioId") Long usuarioId, @PathParam("idCombo") Long idCombo) throws BusinessLogicException {
        ComboDTO comboDTO = new ComboDTO(usuarioCombosLogic.getCombo(usuarioId, idCombo));
        return comboDTO;
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
    private List<ComboDTO> combosListEntity2DTO(List<ComboEntity> entityList) {
        List<ComboDTO> list = new ArrayList();
        for (ComboEntity entity : entityList) {
            list.add(new ComboDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte una lista de MedallaDetailDTO a una lista de MedallaEntity.
     *
     * @param dtos Lista de MedallaDetailDTO a convertir.
     * @return Lista de MedallaEntity convertida.
     */
    private List<ComboEntity> combosListDTO2Entity(List<ComboDTO> dtos) throws Exception {
        List<ComboEntity> list = new ArrayList<>();
        for (ComboDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
