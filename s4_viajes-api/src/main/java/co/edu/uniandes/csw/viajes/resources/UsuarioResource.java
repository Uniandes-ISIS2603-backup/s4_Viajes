
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ComboDTO;
import co.edu.uniandes.csw.viajes.dtos.UsuarioDTO;
import co.edu.uniandes.csw.viajes.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.viajes.ejb.ComboLogic;
import co.edu.uniandes.csw.viajes.ejb.UsuarioCombosLogic;
import co.edu.uniandes.csw.viajes.ejb.UsuarioLogic;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
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
 * @author estudiante
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class UsuarioResource {

    /**
     * Clase que implementa el recurso "usuario".
     *
     *
     * @version 1.0
     */
    private static final Logger LOGGER = Logger.getLogger(ActividadResource.class.getName());
    @Inject
    UsuarioLogic usuarioLogic; //variable que accede a la lógica de la aplicación.
       
    @Inject
    ComboLogic comboLogic;
       
    @Inject
    UsuarioCombosLogic usuarioCombosLogic;
    /**
     * Crea un nuevo usuario con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param usuario {@link UsuarioDTO} - El usuario a guardar guardar.
     * @return JSON {@link UsuarioDTO} - El usuario guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la editorial.
     */
    @POST
    public UsuarioDetailDTO createUsuario(UsuarioDTO usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource createUsuario: input: {0}", usuario.toString());
        UsuarioDetailDTO nuevoUsuarioDTO = new UsuarioDetailDTO(usuarioLogic.createUsuario(usuario.toEntity()));
        LOGGER.log(Level.INFO, "UsuarioResource createUsuario: output: {0}", nuevoUsuarioDTO.toString());
        ComboEntity combo=new ComboEntity();
        combo.setCosto(0);
        combo.setDias(0);
        combo.setHoras(0);
        combo.setNombre("Tus Reservas");
        combo.setPuntuacion(-1);
        ComboDTO comboDTO=new ComboDTO(comboLogic.createCombo(combo));
        
        nuevoUsuarioDTO = new UsuarioDetailDTO(usuarioCombosLogic.addCombo(comboDTO.getId(), nuevoUsuarioDTO.getId()));
        return nuevoUsuarioDTO;
    }

    /**
     * Obtiene un usuario con su información de acuerdo a su documento.
     * información que fue previamente ingresada en formato JSON.
     *
     * @param usuarioId
     * @return un usuario y su información de acuerdo a su documento.
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    @GET
    @Path("{usuarioId: \\d+}")
    public UsuarioDetailDTO consultarUsuario(@PathParam("usuarioId") Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: input {0}");

        UsuarioDetailDTO usuarioDTO = new UsuarioDetailDTO(usuarioLogic.getUsuario(usuarioId));

        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: output {0}");

        return usuarioDTO;

    }
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos UsuarioEntity a una lista de
     * objetos UsuarioDetailDTO (json)
     *
     * @param entityList corresponde a la lista de usuarios de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de usuarios en forma DTO (json)
     */
    private List<UsuarioDetailDTO> listEntity2DetailDTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
    
    
     /**
     * Busca y devuelve todos los usuarios que existen en la aplicacion.
     *
     * @return JSONArray {@link BookDetailDTO} - Los usuarios encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios() {
        LOGGER.info("UsuarioResource getUsuarios: input: void");
        List<UsuarioDetailDTO> listaUsuarios = listEntity2DetailDTO(usuarioLogic.getUsuarios());
        LOGGER.log(Level.INFO, "UsuarioResource getUsuarios: output: {0}", listaUsuarios.toString());
        return listaUsuarios;
    }

    /**
     * Modifica la informacion de un usuario dado por la información ingresada
     * en formato JSON.
     *
     * @param usuarioId - Id del usuario a modificar.
     * @param nuevo (@link UsuarioDTO) - La nueva información del usuario. 
     * @return El usuario modificado. 
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
@PUT
 @Path("{usuariosId: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("usuariosId") Long usuarioId,  UsuarioDTO nuevo) throws WebApplicationException, BusinessLogicException {
      LOGGER.log(Level.INFO, "UsuarioResource updateUsuario: input: usuariosId: {0}, nuevo{1}", new Object[]{usuarioId, nuevo.toString()});
      nuevo.setId(usuarioId);
      if(usuarioLogic.getUsuario(usuarioId)==null){
          
        throw new WebApplicationException("El recurso /usuarios/" + usuarioId + " no existe.", 404);
      }
      
       UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(usuarioLogic.updateUsuario(nuevo.toEntity(), usuarioId));
        LOGGER.log(Level.INFO, "UsuarioResource updateUsuario: output: {0}", detailDTO.toString());
        return detailDTO;
      
    }


    /**
     * Borra el usuario con el id asociado (número) recibido en la URL.
     *
     * @param usuariosId Identificador dl usuario que se desea borrar. Este debe
     * ser una cadena de dígitos (int).
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteUsuario(@PathParam("usuariosId") Long usuariosId) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "UsuarioResource deleteUsuario: input: {0}", usuariosId);
       if(usuariosId==null)
           throw new BusinessLogicException("El id es invalido");
        
       usuarioLogic.deleteUsuario(usuariosId);
       
    }
    
    /**
     * Retorna el recurso entrada que le corresponde al usuario ingresado por parametro
     * @param usuariosId El id del usuario consultado
     * @return Un EntradaResource
     */
    @Path("{usuariosId: \\d+}/entradas")
    public Class<EntradaResource> getEntradaResource(@PathParam("usuariosId") Long usuariosId) {
        try {
            usuarioLogic.getUsuario(usuariosId); 
        }
        catch(BusinessLogicException e){
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + "/entradas no existe.", 404);
        }
        return EntradaResource.class;
    }
    
        /**
     * Retorna el recurso medalla que le corresponde al usuario ingresado por parametro
     * @param usuariosId El id del usuario consultado
     * @return Un UsuarioMedallasResource
     */
    @Path("{usuariosId: \\d+}/medallas")
    public Class<UsuarioMedallasResource> getMedallaResource(@PathParam("usuariosId") Long usuariosId) {
        try {
            usuarioLogic.getUsuario(usuariosId); 
        }
        catch(BusinessLogicException e){
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + "/medallas no existe.", 404);
        }
        return UsuarioMedallasResource.class;
    }

}

