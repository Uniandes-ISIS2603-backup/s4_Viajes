/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.MedallaEntity;
import co.edu.uniandes.csw.viajes.entities.ReservaEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ComboPersistence;
import co.edu.uniandes.csw.viajes.persistence.MedallaPersistence;
import co.edu.uniandes.csw.viajes.persistence.ReservaPersistence;
import co.edu.uniandes.csw.viajes.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author jd.barriosc
 */
public class ComboReservasLogic {
      private static final Logger LOGGER = Logger.getLogger(ComboReservasLogic.class.getName());

    @Inject
    private ComboPersistence comboPersistence;

    @Inject
    private ReservaPersistence reservaPersistence;

    /**
     * Agregar un medalla a el usuario
     *
     * @param comboId El id combo a guardar
     * @param reservaId El id de el usuario en la cual se va a guardar el
     * medalla.
     * @return La medalla creado.
     */
    public ComboEntity addReserva(Long comboId, Long reservaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una medalla a el usuario con id = {0}", reservaId);
        ReservaEntity reservaEntity = reservaPersistence.find(reservaId);
        ComboEntity comboEntity = comboPersistence.find(comboId);
        if(reservaEntity==null || comboEntity==null )
            throw new BusinessLogicException("El combo o la reserva que envio no existe");
        for(long idReserva : comboEntity.getIdsReservas())
            if(reservaId == idReserva)
                throw new BusinessLogicException("El combo ya tiene asignada la reserva con id " + reservaId +".");
            else
            {
               ReservaEntity reserva = reservaPersistence.find(idReserva);
               if(reserva==null)
                   {
//                           throw new BusinessLogicException("El combo reserva que envio no existe");
                    }
               else
                   comboEntity.addReserva(reserva);
            } 
        comboEntity.addIdReserva(reservaId);
        comboEntity.addReservaFirst(reservaEntity);

        comboPersistence.update(comboEntity);

        LOGGER.log(Level.INFO, "Termina proceso de agregarle una reserva a el combo con id = {0}", comboId);
        return comboEntity;
    }

    
    public boolean existeCombo(long comboId)
    {
         ComboEntity comboEntity = comboPersistence.find(comboId);
        if(comboEntity==null )
            return false;
        return true;
    }
    /**
     * Retorna todas las medallas asociadas a un usuario
     *
     * @param comboId El ID de el usuario buscado
     * @return La lista de medallas de el usuario
     */
    public List<ReservaEntity> getReservasCombo(Long comboId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las reservas asociados a el combo con id = {0}", comboId);
        ComboEntity comboEntity = comboPersistence.find(comboId);
        if(comboEntity==null )
            throw new BusinessLogicException("El combo que busca no existe");
        for(long idReserva : comboEntity.getIdsReservas())
        {
            ReservaEntity reserva = reservaPersistence.find(idReserva);
            if(reserva==null)
            {
//               throw new BusinessLogicException("El combo reserva que envio no existe");
            }
            else
                comboEntity.addReserva(reserva);
        } 
        
        return comboEntity.getReservas();
    }

    
}
