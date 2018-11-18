/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.MedallaEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ComboPersistence;
import co.edu.uniandes.csw.viajes.persistence.MedallaPersistence;
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
public class UsuarioCombosLogic {
    private static final Logger LOGGER = Logger.getLogger(UsuarioCombosLogic.class.getName());

    @Inject
    private ComboPersistence comboPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    /**
     * Agregar un medalla a el usuario
     *
     * @param comboId El id medalla a guardar
     * @param usuarioId El id de el usuario en la cual se va a guardar el
     * medalla.
     * @return La medalla creado.
     */
    public UsuarioEntity addCombo(Long comboId, Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un combo al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        ComboEntity comboEntity = comboPersistence.find(comboId);
        if(usuarioEntity==null)
            throw new BusinessLogicException("El ususario con id "+usuarioId +" no existe");
        if(comboEntity==null)
            throw new BusinessLogicException("El combo con id "+comboId +" no existe");
        for(long idCombo : usuarioEntity.getIdsCombos())
            if(comboId == idCombo)
                throw new BusinessLogicException("El usuario ya tiene asignado un combo con id " + comboId +".");
            else
            {
                ComboEntity combo = comboPersistence.find(idCombo);
               if(combo==null)
                   {
//                     throw new BusinessLogicException("La medalla con id " + medallaId +" no existe");
                   }
               else
                    usuarioEntity.addCombo(combo);
            } 
        usuarioEntity.addIdCombo(comboId);
        usuarioEntity.addComboFirst(comboEntity);

        usuarioPersistence.update(usuarioEntity);

        LOGGER.log(Level.INFO, "Termina proceso de agregarle un combo al usuario con id = {0}", usuarioId);
        return usuarioEntity;
    }

    /**
     * Retorna todas las medallas asociadas a un usuario
     *
     * @param usuarioId El ID de el usuario buscado
     * @return La lista de combos de el usuario
     */
    public List<ComboEntity> getCombos(Long usuarioId) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "Inicia proceso de consultar las medallas asociados al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        if(usuarioEntity==null)
            throw new BusinessLogicException("El usuario con id "+usuarioId +" no existe");
        List<ComboEntity> combos=new ArrayList<>();
        for(long idCombo : usuarioEntity.getIdsCombos())   
        {
            ComboEntity combo = comboPersistence.find(idCombo);
            if(combo==null)
               {
//                     throw new BusinessLogicException("El combo con id " + medallaId +" no existe");
               }
            else
                combos.add(combo);
        }
        
        return combos;
    }

    /**
     * Retorna una medalla asociada a un usuario
     *
     * @param usuariosId El id de el usuario a buscar.
     * @param medallasId El id de la medalla a buscar
     * @return La medalla encontrada dentro de el usuario.
     * @throws BusinessLogicException Si de la medalla no se encuentra en la
     * usuario
     */
    public ComboEntity getCombo(Long usuarioId, Long comboId) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "Inicia proceso de consultar el combo con id = {0} del usuario con id = " + usuarioId, comboId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        if(usuarioEntity==null)
            throw new BusinessLogicException("El usuario con id "+usuarioId +" no existe");
        ComboEntity combo=null;
        for(long idCombo : usuarioEntity.getIdsCombos())   
            if(comboId==idCombo){
                combo = comboPersistence.find(comboId);
                break;
            }      
        if(combo==null)
            throw new BusinessLogicException("El usuario con id "+usuarioId +" no tiene el combo con id "+comboId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el combo con id = {0} del usuario con id = " + usuarioId, comboId); 
        return combo;
    }

}