/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.UsuarioDTO;
import co.edu.uniandes.csw.viajes.ejb.UsuarioLogic;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
   
   UsuarioLogic usuarioLogic;
   
    /**
     * Crea una nueva editorial con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param editorial {@link EditorialDTO} - La editorial que se desea
     * guardar.
     * @return JSON {@link EditorialDTO} - La editorial guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la editorial.
     */
   // @POST
//    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws BusinessLogicException {
  //      LOGGER.log(Level.INFO, "EditorialResource createEditorial: input: {0}", usuario.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
    //    UsuarioDTO usuarioEntity = usuario.toEntity();
        // Invoca la lógica para crear la editorial nueva
      //  UsuarioDTO nuevoUsuarioEntity = UsuarioLogic.createUsuario(usuarioEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        //UsuarioDTO nuevoEditorialDTO = new UsuarioDTO(nuevousuarioEntity);
       // LOGGER.log(Level.INFO, "UsuarioResource createUsuario: output: {0}", nuevoEditorialDTO.toString());
       // return nuevoUsuarioDTO;
    }
   

