/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ComboPersistence;
import co.edu.uniandes.csw.viajes.persistence.PagoPersistence;
import co.edu.uniandes.csw.viajes.persistence.ReservaPersistence;
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

   @Inject
    private ComboPersistence comboPersistence;
   
   @Inject
    private ReservaPersistence reservaPersistence;

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
        for(Long idCombo:usuarioEntity.getIdsCombos())
            pagos.addAll(getPagosCombo(idCombo));
        
        return pagos;
    }

    public List<PagoEntity> getPagosCombo(Long idCombo)
    {
        ComboEntity combo=comboPersistence.find(idCombo);
        List<PagoEntity> pagos=new ArrayList<>();
        if(combo!=null)
        {
            for(Long idReserva:combo.getIdsReservas())
            {
                ReservaEntity reserva=reservaPersistence.find(idReserva);
                if(reserva!=null&&reserva.isPagada())
                {
                    PagoEntity pago=pagoPersistence.findByIdReserva(reserva.getId());
                    if(pago!=null){
                        pago.setaPagar(reserva);
                        pagos.add(0,pago);
                    }
                }
            }
        }
        return pagos;
    }
   
     public PagoEntity pagoPorIdUsuario(UsuarioEntity usuario, Long idPago)
    {
        PagoEntity pay=null;
        for(Long idCombo:usuario.getIdsCombos()){         
            ComboEntity combo=comboPersistence.find(idCombo);
            if(combo!=null)
                for(Long idReserva:combo.getIdsReservas())
                {
                    ReservaEntity reserva=reservaPersistence.find(idReserva);
                    if(reserva!=null&&reserva.isPagada())
                    {
                        PagoEntity pago=pagoPersistence.findByIdReserva(reserva.getId());
                        if(pago!=null){
                            if(pago.getId().compareTo(idPago)==0)
                            {
                                pago.setaPagar(reserva);
                                pay=pago;
                                break;
                            }
                        }
                    }
                }
            if(pay!=null)
                break;
        }
        return pay;
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
          if(true)
            throw new BusinessLogicException("Aun no esta hecho");
        return new PagoEntity();
    }

}
