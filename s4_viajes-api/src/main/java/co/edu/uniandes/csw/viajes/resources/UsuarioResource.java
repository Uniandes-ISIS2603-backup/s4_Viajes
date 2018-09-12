/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.UsuarioDTO;
import co.edu.uniandes.csw.viajes.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.viajes.ejb.UsuarioLogic;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.Z;
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

@Path ("usuarios")
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
   
    /**
     * Crea un nuevo usuario con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param usuario {@link UsuarioDTO} - El usuario a guardar
     * guardar.
     * @return JSON {@link UsuarioDTO} - El usuario guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la editorial.
     */
   
      
      @POST
   public UsuarioDTO createUsuario(UsuarioDTO usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource createUsuario: input: {0}", usuario.toString());
       UsuarioEntity usuarioEntity = usuario.toEntity();
       UsuarioDTO nuevoUsuarioDTO = new UsuarioDTO(usuarioEntity);
        return nuevoUsuarioDTO;
    }
   
  
   
   /**
     * Obtiene un usuario con su información de acuerdo a su documento.
     * información que fue previamente ingresada en formato JSON.
     *
     * @return un usuario y su información de acuerdo a su documento.
     */
    @GET
    @Path("{documento: [a-zA-Z][a-zA-Z]*}")
    public UsuarioDTO consultarUsuarios(@PathParam("documento") String documento){

        return new UsuarioDTO();
        
    }
   
     /**
     * Modifica la informacion de un usuario dado por la información ingresada en
     * formato JSON.
     *
     * @param nuevo (@link UsuarioDTO) - el usuario que desea modificar.
     */
    @PUT
    @Path("{documento: [a-zA-Z][a-zA-Z]*}")
    public UsuarioDTO modificarUsuario(@PathParam("documento")int documento, UsuarioDTO nuevo) throws WebApplicationException
    {
       return nuevo;
    }

    /**
     * Borra el usuario con el id asociado (número) recibido en la URL.
     *
     * @param usuarioNum Identificador dl usuario que se desea borrar. Este debe ser
     * una cadena de dígitos (int).
     */
    @DELETE
    @Path("{documento: [a-zA-Z][a-zA-Z]*}")
    public void deleteUsuario(@PathParam("documento") Long documento) {
    
    }
   
   
    }
   

