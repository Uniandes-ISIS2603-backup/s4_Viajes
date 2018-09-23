
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
import co.edu.uniandes.csw.viajes.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.viajes.mappers.WebApplicationExceptionMapper;
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

    UsuarioLogic usuarioLogic; //variable que accede a la lÃ³gica de la aplicaciÃ³n.
    /**
     * Crea un nuevo usuario con la informacion que se recibe en el cuerpo de la
     * peticiÃ³n y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param usuario {@link UsuarioDTO} - El usuario a guardar guardar.
     * @return JSON {@link UsuarioDTO} - El usuario guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lÃ³gica que se genera cuando ya existe la editorial.
     */
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource createUsuario: input: {0}", usuario.toString());
        UsuarioEntity usuarioEntity = usuario.toEntity();
        UsuarioEntity nuevoUsuarioEntity = usuarioLogic.createUsuario(usuarioEntity);
        UsuarioDTO nuevoUsuarioDTO = new UsuarioDTO(nuevoUsuarioEntity);
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevoUsuarioDTO.toString());

        return nuevoUsuarioDTO;
    }

    /**
     * Obtiene un usuario con su informaciÃ³n de acuerdo a su documento.
     * informaciÃ³n que fue previamente ingresada en formato JSON.
     *
     * @return un usuario y su informaciÃ³n de acuerdo a su documento.
     */
    @GET
    @Path("{usuarioId: \\d+}")
    public UsuarioDTO consultarUsuarios(@PathParam("usuarioId") Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: input {0}");
        UsuarioEntity usuarioEntity = usuarioLogic.getUsuario(usuarioId);
        if (usuarioEntity == null) {

            throw new WebApplicationException("El recurso /usuarios/" + usuarioId + " no existe.", 404);

        }

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioEntity);

        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: output {0}");

        return new UsuarioDTO();

    }

    /**
     * Modifica la informacion de un usuario dado por la informaciÃ³n ingresada
     * en formato JSON.
     *
     * @param nuevo (@link UsuarioDTO) - el usuario que desea modificar.
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
     * Borra el usuario con el id asociado (nÃºmero) recibido en la URL.
     *
     * @param usuariosId Identificador dl usuario que se desea borrar. Este debe
     * ser una cadena de dÃ­gitos (int).
     */
    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteUsuario(@PathParam("usuariosId") Long usuariosId) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "UsuarioResource deleteUsuario: input: {0}", usuariosId);
        UsuarioEntity entity = usuarioLogic.getUsuario(usuariosId);
        if(entity==null)
       {
           throw new WebApplicationException("El recurso /usuarios/"+ usuariosId + "no existe.", 404);
           
       }
        
       usuarioLogic.deleteUsuario(usuariosId);
       
    }
    
            /**
     * ConexiÃ³n con el servicio de entradas para un usuario. {@link EntradaResource}
     *
     * Este mÃ©todo conecta la ruta de /usuarios con las rutas de /entradas que
     * dependen del usuario, es una redirecciÃ³n al servicio que maneja el segmento
     * de la URL que se encarga de las entradas.
     *
     * @param usuarioId El id del usuario con respecto al cual se accede a la entrada.
     * @return El servicio de Entradas para ese usuario en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lÃ³gica que se genera cuando no se encuentra el usuario.
     */
    @Path("{usuarioId: \\d+}/entradas")
    public Class<EntradaResource> getEntradaResource(@PathParam("usuarioId") Long usuarioId) throws BusinessLogicException {
       if (usuarioLogic.getUsuario(usuarioId) == null) {
          throw new WebApplicationException("El recurso /usuarios/" + usuarioId + "/entradas no existe.", 404);
      }
        return EntradaResource.class;
    }

}

   

