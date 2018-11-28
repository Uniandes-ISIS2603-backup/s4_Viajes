/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ComboPersistence;
import co.edu.uniandes.csw.viajes.persistence.PagoPersistence;
import co.edu.uniandes.csw.viajes.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author Juan Diego Barrios
 */
@Stateless
public class UsuarioPagosLogic {
     private static final Logger LOGGER = Logger.getLogger(UsuarioPagosLogic.class.getName());

    @Inject
    private PagoPersistence pagoPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    /**
     * Agregar un medalla a el usuario
     *
     * @param pagoId El id medalla a guardar
     * @param usuarioId El id de el usuario en la cual se va a guardar el
     * medalla.
     * @return La medalla creado.
     */
    public UsuarioEntity addPago(Long pagoId, Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un combo al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        PagoEntity pagoEntity = pagoPersistence.find(pagoId);
        if(usuarioEntity==null)
            throw new BusinessLogicException("El ususario con id "+usuarioId +" no existe");
        if(pagoEntity==null)
            throw new BusinessLogicException("El pago con id "+pagoId +" no existe");
        for(long idPago : usuarioEntity.getIdsPagos())
            if(pagoId == idPago)
                throw new BusinessLogicException("El usuario ya tiene asignado un pago con id " + pagoId +".");
            else
            {
                PagoEntity pago = pagoPersistence.find(idPago);
               if(pago==null)
                   {
//                     throw new BusinessLogicException("El pago con id " + medallaId +" no existe");
                   }
               else
                    usuarioEntity.addPago(pago);
            } 
        usuarioEntity.addIdPago(pagoId);
        usuarioEntity.addPagoFirst(pagoEntity);

        usuarioPersistence.update(usuarioEntity);

        LOGGER.log(Level.INFO, "Termina proceso de agregarle un pago al usuario con id = {0}", usuarioId);
        return usuarioEntity;
    }

    /**
     * Retorna todos los pagos asociadas a un usuario
     *
     * @param usuarioId El ID de el usuario buscado
     * @return La lista de pagos de el usuario
     */
    public List<PagoEntity> getPagos(Long usuarioId) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "Inicia proceso de consultar los pagos asociados al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        if(usuarioEntity==null)
            throw new BusinessLogicException("El usuario con id "+usuarioId +" no existe");
        List<PagoEntity> pagos=new ArrayList<>();
        for(long idPago : usuarioEntity.getIdsPagos())   
        {
            PagoEntity pago = pagoPersistence.find(idPago);
            if(pago==null)
               {
//                     throw new BusinessLogicException("El combo con id " + medallaId +" no existe");
               }
            else
                pagos.add(pago);
        }
        
        return pagos;
    }

    /**
     * Retorna una medalla asociada a un usuario
     *
     * @param usuariosId El id de el usuario a buscar.
     * @param pagoId El id del pago a buscar
     * @return La medalla encontrada dentro de el usuario.
     * @throws BusinessLogicException Si de la medalla no se encuentra en la
     * usuario
     */
    public PagoEntity getPago(Long usuarioId, Long pagoId) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "Inicia proceso de consultar el combo con id = {0} del usuario con id = " + usuarioId, pagoId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        if(usuarioEntity==null)
            throw new BusinessLogicException("El usuario con id "+usuarioId +" no existe");
        PagoEntity pago=null;
        for(long idPago : usuarioEntity.getIdsPagos())   
            if(pagoId==idPago){
                pago = pagoPersistence.find(pagoId);
                break;
            }      
        if(pago==null)
            throw new BusinessLogicException("El usuario con id "+usuarioId +" no tiene el pago con id "+pagoId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el pago con id = {0} del usuario con id = " + usuarioId, pagoId); 
        return pago;
    }

}
