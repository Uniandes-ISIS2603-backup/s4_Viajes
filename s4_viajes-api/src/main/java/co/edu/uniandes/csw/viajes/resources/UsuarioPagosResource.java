/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.resources;

import co.edu.uniandes.csw.viajes.dtos.MedallaDTO;
import co.edu.uniandes.csw.viajes.dtos.PagoDTO;
import co.edu.uniandes.csw.viajes.dtos.UsuarioDTO;
import co.edu.uniandes.csw.viajes.ejb.MedallaLogic;
import co.edu.uniandes.csw.viajes.ejb.PagoLogic;
import co.edu.uniandes.csw.viajes.ejb.UsuarioMedallasLogic;
import co.edu.uniandes.csw.viajes.ejb.UsuarioPagosLogic;
import co.edu.uniandes.csw.viajes.entities.MedallaEntity;
import co.edu.uniandes.csw.viajes.entities.PagoEntity;
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

/**
 *
 * @author Juan Diego Barrios
 */
@Path("usuarios/{usuarioId: \\d+}/pagos")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped 
public class UsuarioPagosResource {
     
     private static final Logger LOGGER = Logger.getLogger(UsuarioPagosResource.class.getName());
     
     @Inject 
     private UsuarioPagosLogic usuarioPagosLogic;
     
     @Inject
     private PagoLogic pagoLogic;
     
     
     
    /**
     * Busca y devuelve todas las medallas que tiene un usuario.
     *
     * @param usuariosId Identificador del usuario que se esta buscando.
     * Este debe ser una cadena de caractéres.
     * @return JSONArray {@link MedallaDTO} - Los libros encontrados en la
     * usuario. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PagoDTO> getPagos(@PathParam("usuarioId") Long usuarioId) throws BusinessLogicException {
        List<PagoDTO> listaDTOs = pagosListEntity2DTO(usuarioPagosLogic.getPagos(usuarioId));
        return listaDTOs;
    }
    
        /**
     * Busca y devuelve la medalla con el numero dado por parametro si la tiene un usuario.
     *
     * @param usuarioId Identificador del usuario que se esta
     * actualizando. Este debe ser una cadena de caracteres.
     * @param idPago Identificador de la medalla que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSONArray {@link MedallaDetailDTO} - Los libros encontrados en la
     * usuario. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    @Path("{idPago: \\d+}")
    public PagoDTO getMedalla(@PathParam("usuarioId") Long usuarioId, @PathParam("idPago") Long idPago) throws BusinessLogicException {
        PagoDTO pagoDTO = new PagoDTO(usuarioPagosLogic.getPago(usuarioId, idPago));
        return pagoDTO;
    }
    

    /**
     * Convierte una lista de MedallaEntity a una lista de MedallaDetailDTO.
     *
     * @param entityList Lista de MedallaEntity a convertir.
     * @return Lista de MedallaDTO convertida.
     */
    private List<PagoDTO> pagosListEntity2DTO(List<PagoEntity> entityList) {
        List<PagoDTO> list = new ArrayList();
        for (PagoEntity entity : entityList) {
            list.add(new PagoDTO(entity));
        }
        return list;
    }
    
    /**
     * Convierte una lista de MedallaDetailDTO a una lista de MedallaEntity.
     *
     * @param dtos Lista de MedallaDetailDTO a convertir.
     * @return Lista de MedallaEntity convertida.
     */
    private List<PagoEntity> pagosListDTO2Entity(List<PagoDTO> dtos) throws Exception {
        List<PagoEntity> list = new ArrayList<>();
        for (PagoDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
