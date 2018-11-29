/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.MedallaEntity;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
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
 * @author Luis Gomez Amado
 */
@Stateless
public class UsuarioMedallasLogic {
    private static final Logger LOGGER = Logger.getLogger(UsuarioMedallasLogic.class.getName());

    @Inject
    private MedallaPersistence medallaPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    /**
     * Agregar un medalla a el usuario
     *
     * @param medallaId El id medalla a guardar
     * @param usuarioId El id de el usuario en la cual se va a guardar el
     * medalla.
     * @return La medalla creado.
     */
    public UsuarioEntity addMedalla(Long medallaId, Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una medalla al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        MedallaEntity medallaEntity = medallaPersistence.find(medallaId);
        if(usuarioEntity==null)
            throw new BusinessLogicException("El ususario con id "+usuarioId +" no existe");
        if(medallaEntity==null)
            throw new BusinessLogicException("La medalla con id "+medallaId +" no existe");
        for(long idMedalla : usuarioEntity.getIdsMedallas())
            if(medallaId == idMedalla)
                throw new BusinessLogicException("El usuario ya tiene asignada una medalla con id " + medallaId +".");
          
        usuarioEntity.addIdMedalla(medallaId);
        usuarioEntity.addMedallaFirst(medallaEntity);

        usuarioPersistence.update(usuarioEntity);

        LOGGER.log(Level.INFO, "Termina proceso de agregarle una medalla al usuario con id = {0}", usuarioId);
        return usuarioEntity;
    }

    /**
     * Retorna todas las medallas asociadas a un usuario
     *
     * @param usuarioId El ID de el usuario buscado
     * @return La lista de medallas de el usuario
     */
    public List<MedallaEntity> getMedallas(Long usuarioId) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "Inicia proceso de consultar las medallas asociados al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        if(usuarioEntity==null)
            throw new BusinessLogicException("El usuario con id "+usuarioId +" no existe");
        List<MedallaEntity> medallas=new ArrayList<>();
        for(long idMedalla : usuarioEntity.getIdsMedallas())   
        {
            MedallaEntity medalla = medallaPersistence.find(idMedalla);
            if(medalla==null)
               {
//                     throw new BusinessLogicException("La medalla con id " + medallaId +" no existe");
               }
            else
                medallas.add(medalla);
        }
        
        return medallas;
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
    public MedallaEntity getMedalla(Long usuarioId, Long medallaId) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "Inicia proceso de consultar la medalla con id = {0} del usuario con id = " + usuarioId, medallaId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        if(usuarioEntity==null)
            throw new BusinessLogicException("El usuario con id "+usuarioId +" no existe");
        MedallaEntity medalla=null;
        for(long idMedalla : usuarioEntity.getIdsMedallas())   
            if(medallaId==idMedalla){
                medalla = medallaPersistence.find(medallaId);
                break;
            }      
        if(medalla==null)
            throw new BusinessLogicException("El usuario con id "+usuarioId +" no tiene la medalla con id "+medallaId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la medalla con id = {0} del usuario con id = " + usuarioId, medallaId); 
        return medalla;
    }

    public boolean tieneMedalla(Long usuarioId, Long medallaId) {
       UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        if(usuarioEntity==null)
            return false;
        MedallaEntity medalla=null;
        for(long idMedalla : usuarioEntity.getIdsMedallas())   
            if(medallaId==idMedalla){
                medalla = medallaPersistence.find(medallaId);
                break;
            }      
        if(medalla==null)
            return false;
        return true;
    }
}
