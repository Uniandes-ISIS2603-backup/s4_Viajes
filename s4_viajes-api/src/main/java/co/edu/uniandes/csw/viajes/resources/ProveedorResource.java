/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.ProveedorDTO;
import co.edu.uniandes.csw.viajes.dtos.VueloDTO;
import co.edu.uniandes.csw.viajes.ejb.ProveedorLogic;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.mappers.BusinessLogicExceptionMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author Juan Felipe Torres
 */
public class ProveedorResource {

    /**
     * Clase que impkementa el recurso "proveedor".
     *
     *
     * @version 1.0
     */
    private static final Logger LOGGER = Logger.getLogger(ProveedorResource.class.getName());

    @Inject

    ProveedorLogic proveedorLogic;

    /**
     * Crea un nuevo proveedor con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param Proveedor {@link ProveedorDTO} - El proveedor que se desea
     * guardar.
     * @return JSON {@link ProveedorDTO} - El proveedor guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el proveedor.
     */
    @POST
    public ProveedorDTO crearProveedor(ProveedorDTO proveedor) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ProveedorResource createProveedor: input: {0}", proveedor.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        //VueloEntity vueloEntity = vuelo.toEntity();
        // Invoca la lógica para crear la actividad nueva
        //VueloEntity nuevoVueloEntity = vueloLogic.createVuelo(vueloEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        //VueloDTO nuevoVueloDTO = new VueloDTO(nuevoVueloEntity);
        //LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevoVueloDTO.toString());
        return proveedor;
    }

    /**
     * Obtiene un proveedor con su información dada por su nombre, se retorna
     * esta información que fue previamente ingresada en formato JSON.
     *
     * @return un proveedor y su información de acuerdo a su nombre.
     */
    @GET
    public ProveedorDTO consultarProveedor() {
        return new ProveedorDTO();
    }

    /**
     * Modifica la informacion de un proveedor dado por la información ingresada
     * en formato JSON.
     *
     * @param nuevoProveedor (@link ProveedorDTO) - el proveedor que desea
     * modificar.
     */
    @PUT
    public ProveedorDTO modificarProveedor() {
        return new ProveedorDTO();
    }

    @POST
    public String mandarSolicitud() {
        LOGGER.log(Level.INFO, "Se solicita al administrador permisos");
        return "mensaje";
    }

    /**
     * Borra el vuelo con el id asociado (número) recibido en la URL.
     *
     * @param ProveedorNom Identificador del proveedor que se desea borrar. Este debe ser
     * una cadena de letras.
     */
    @DELETE
    @Path("{ProvedorNom: [a-zA-Z][a-zA-Z]*}}")
    public void deleteProveedor(@PathParam("ProveedorNom") Long proveedorNom) {
        //LOGGER.log(Level.INFO, "ProveedorResource deleteProveedor: input: {0}", ProveedorNom);
        // Invoca la lógica para borrar el proveedor
        //editorialLogic.deleteProveedor(editorialsId);
        //LOGGER.info("ProveedorResource deleteProveedor: output: void");
    }
}
