/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ProveedorDTO;
import co.edu.uniandes.csw.viajes.dtos.TransporteTerrestreDTO;
import co.edu.uniandes.csw.viajes.ejb.ProveedorLogic;
import co.edu.uniandes.csw.viajes.ejb.TransporteTerrestreLogic;
import co.edu.uniandes.csw.viajes.ejb.TransporteTerrestreProveedorLogic;
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
 * @author estudiante
 */
@Path("transportes/{transportesId: \\d+}/proveedor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransporteProveedorResource {
    
    private static final Logger LOGGER = Logger.getLogger(TransporteProveedorResource.class.getName()); 
    
    @Inject
    private TransporteTerrestreLogic transporteLogic;
    
    @Inject
    private TransporteTerrestreProveedorLogic transporteProveedorLogic;
    
    @Inject
    private ProveedorLogic proveedorLogic; 
    
    /**
     * Remplaza la instancia de proveedor asociada a un transporte.
     *
     * @param transportesId Identificador del transporte que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param proveedor El proveedor del transporte.
     * @return JSON {@link TransporteDTO} - El arreglo de transportes guardado en el proveedor.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el proveedor o el
     * transporte.
     * @throws BusinessLogicException
     */
    @PUT
    public TransporteTerrestreDTO replaceProveedor(@PathParam("transportesId") Long transportesId, ProveedorDTO proveedor)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TransporteProveedorResource replaceProveedor: input: transportesId{0} , Proveedor:{1}", new Object[]{transportesId, proveedor.toString()});
        if (transporteLogic.getTransporte(transportesId) == null) {
            throw new WebApplicationException("El recurso /transportes/" + transportesId + " no existe.", 404);
        }
        if (proveedorLogic.getProveedor(proveedor.getId()) == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedor.getId() + " no existe.", 404);
        }
        TransporteTerrestreDTO transporteDTO = new TransporteTerrestreDTO(transporteProveedorLogic.replaceProveedor(transportesId, proveedor.getId()));
        LOGGER.log(Level.INFO, "TransporteProveedorResource replaceProveedor: output: {0}", transporteDTO.toString());
        return transporteDTO;
    }
}
