/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.MedallaEntity;
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
     * @param medallasId El id medalla a guardar
     * @param usuariosId El id de el usuario en la cual se va a guardar el
     * medalla.
     * @return La medalla creado.
     */
    public MedallaEntity addMedalla(Long medallasId, Long usuariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una medalla a el usuario con id = {0}", usuariosId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        MedallaEntity medallaEntity = medallaPersistence.find(medallasId);
        List<MedallaEntity> medallas = usuarioEntity.getMedallas();
        for(MedallaEntity medalla : medallas)
        {
            if(medalla.getId() == medallasId)
            {
                throw new BusinessLogicException("El usuario ya tiene asignada la medalla con id " + medallasId +".");
            }
        }
        medallas.add(medallaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un medalla a el usuario con id = {0}", usuariosId);
        return medallaEntity;
    }

    /**
     * Retorna todas las medallas asociadas a un usuario
     *
     * @param usuariosId El ID de el usuario buscado
     * @return La lista de medallas de el usuario
     */
    public List<MedallaEntity> getMedallas(Long usuariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las medallas asociados a el usuario con id = {0}", usuariosId);
        return usuarioPersistence.find(usuariosId).getMedallas();
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
    public MedallaEntity getMedalla(Long usuariosId, Long medallasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar de la medalla con id = {0} de el usuario con id = " + usuariosId, medallasId);
        List<MedallaEntity> medallas = usuarioPersistence.find(usuariosId).getMedallas();
        MedallaEntity medallaEntity = medallaPersistence.find(medallasId);
        int index = medallas.indexOf(medallaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar de la medalla con id = {0} de el usuario con id = " + usuariosId, medallasId);
        if (index >= 0) {
            return medallas.get(index);
        }
        throw new BusinessLogicException("La medalla no está asociado a el usuario");
    }

    /**
     * Remplazar medallas de un usuario
     *
     * @param medallas Lista de medallas que serán los de el usuario.
     * @param usuariosId El id de el usuario que se quiere actualizar.
     * @return La lista de medallas actualizada.
     */
    public List<MedallaEntity> replaceMedallas(Long usuariosId, List<MedallaEntity> medallas) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el usuario con id = {0}", usuariosId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        usuarioEntity.setMedallas(medallas);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el usuario con id = {0}", usuariosId);
        return medallas;
    }
}
