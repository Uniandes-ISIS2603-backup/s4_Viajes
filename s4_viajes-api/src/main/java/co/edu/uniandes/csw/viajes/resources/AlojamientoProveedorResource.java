/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.AlojamientoDTO;
import co.edu.uniandes.csw.viajes.dtos.ProveedorDTO;
import co.edu.uniandes.csw.viajes.ejb.AlojamientoLogic;
import co.edu.uniandes.csw.viajes.ejb.AlojamientoProveedorLogic;
import co.edu.uniandes.csw.viajes.ejb.ProveedorLogic;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Yeferson Espana
 */
@Path("alojamientos/{alojamientosId: \\d+}/proveedor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlojamientoProveedorResource {
    
    private static final Logger LOGGER = Logger.getLogger(AlojamientoProveedorResource.class.getName()); 
    
    @Inject
    private AlojamientoLogic alojamientoLogic;
    
    @Inject
    private AlojamientoProveedorLogic alojamientoProveedorLogic;
    
    @Inject
    private ProveedorLogic proveedorLogic; 
    
    
    /**
     * Remplaza la instancia de proveedor asociada a un alojamiento.
     *
     * @param alojamientosId Identificador del alojamiento que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param proveedor El proveedor del alojamiento.
     * @return JSON {@link AlojamietoDetailDTO} - El arreglo de alojamientos guardado en el proveedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el proveedor o el
     * alojamiento.
     * @throws BusinessLogicException
     */
    @PUT
    public AlojamientoDTO replaceProveedor(@PathParam("alojamientosId") Long alojamientosId, ProveedorDTO proveedor)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AlojamientoProveedorResource replaceProveedor: input: alojamientosId{0} , Proveedor:{1}", new Object[]{alojamientosId, proveedor.toString()});
        if (alojamientoLogic.getAlojamiento(alojamientosId) == null) {
            throw new WebApplicationException("El recurso /alojamientos/" + alojamientosId + " no existe.", 404);
        }
        if (proveedorLogic.getProveedor(proveedor.getId()) == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedor.getId() + " no existe.", 404);
        }
        AlojamientoDTO alojamientoDTO = new AlojamientoDTO(alojamientoProveedorLogic.replaceProveedor(alojamientosId, proveedor.getId()));
        LOGGER.log(Level.INFO, "AlojamientoProveedorResource replaceProveedor: output: {0}", alojamientoDTO.toString());
        return alojamientoDTO;
    }
    
}
