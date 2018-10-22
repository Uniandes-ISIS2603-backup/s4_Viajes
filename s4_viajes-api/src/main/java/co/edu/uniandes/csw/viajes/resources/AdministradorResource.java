/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.AdministradorDTO;
import co.edu.uniandes.csw.viajes.ejb.AdministradorLogic;
import co.edu.uniandes.csw.viajes.entities.AdministradorEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
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
@Path("administradores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AdministradorResource {
    
 /**
 * Clase que implementa el recurso "administrador".
 *
 * 
 * @version 1.0
 */

   private static final Logger LOGGER = Logger.getLogger(ActividadResource.class.getName());
   @Inject
   
   AdministradorLogic administradorLogic; //variable que accede a la lógica de la aplicación.
   
    /**
     * Crea un nuevo administrador con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param admin El administrador a crear.
     * @return JSON {@link UsuarioDTO} - El administrador guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la editorial.
     */
   // @POST
    // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
   
   @POST
   public AdministradorDTO createAdministrador(AdministradorDTO admin) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: input: {0}", admin.toString());
       AdministradorDTO nuevoAdminDTO = new AdministradorDTO(administradorLogic.createAdministrador(admin.toEntity(), "Tr1pBvld3rUltr4S3cr3tP@ssw0rd"));
       if(admin.getpassword()==null)
       {
            throw new WebApplicationException("La contraseña no puede ser nula");
       }
     LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: output: {0}", nuevoAdminDTO.toString());
        return nuevoAdminDTO;
    }
   
   

    /**
     * Borra el administrador con el id asociado (número) recibido en la URL.
     *
     * @param adminId
     * @param pContrasena
     * @throws co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException
     */
    @DELETE
    @Path("{administradorId: \\d+}")
    public void deleteAdministrador(@PathParam("administradorId") Long adminId, String pContrasena) throws BusinessLogicException {
      LOGGER.log(Level.INFO, "UsuarioResource deleteUsuario: input: {0}", adminId);
        AdministradorEntity entity = administradorLogic.getAdministrador(adminId); 
        if(entity==null)
       {
           throw new WebApplicationException("El recurso /administradores/"+ adminId + "no existe.", 404);
           
       }
        
       administradorLogic.deleteAdministrador(adminId, pContrasena);
       
    }
    
    
    
    
}
