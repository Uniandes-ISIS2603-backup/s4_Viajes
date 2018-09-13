/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.UsuarioPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author n.segura 
 */
@Stateless
public class UsuarioLogic {
    
        private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject
    private UsuarioPersistence persistence;

    /**
     * Se encarga de crear un Uusuario  en la base de datos.
     *
     * @param usuarioEntity Objeto de usuarioEntity con los datos nuevos
     * @return Objeto de AuthorEntity con los datos nuevos y su ID.
     */
    public UsuarioEntity createUsuario(UsuarioEntity usuarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del usuario");
        UsuarioEntity newUsuarioEntity = persistence.create(usuarioEntity);
        if(newUsuarioEntity.getDocumento().length()<8)
        {
            throw new BusinessLogicException("El documento debe tener al menos 8 caracteres");
        }
        
            if(newUsuarioEntity.getDocumento().length()>12)
        {
            throw new BusinessLogicException("El documento debe tener máximo 12 caracteres");
        }
        
         if(persistence.findByUserName(usuarioEntity.getUserName())!=null)
        {
            throw new BusinessLogicException("El nombre de usuario ya ha sido tomado");
        }
         
           if(persistence.find(usuarioEntity.getId())!=null)
        {
            throw new BusinessLogicException("Este usuario ya se encuentra registrado");
        }
         
        LOGGER.log(Level.INFO, "Termina proceso de creación del usuario");
        return newUsuarioEntity;
       
    }
    
    public boolean validateContrasena(String pContrasena)
    {
        Pattern pat = Pattern.compile("[.]{8,})");
        Matcher mat = pat.matcher(pContrasena);
        return (mat.matches() && !pContrasena.isEmpty());

    }

    public boolean validateNombre(String pNombre)
    {
        Pattern pat = Pattern.compile("[a-zA-Z]{1,}[.]{*})");
        Matcher mat = pat.matcher(pNombre);
        return (mat.matches() && !pNombre.isEmpty());

    }    
    
    
    /**
     * Obtener un usuario por medio de su id.
     *
     * @param usuarioId: id del usuario para ser buscado.
     * @return el usuario solicitado por medio de su id.
     */
    public UsuarioEntity getUsuario(Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar usuario con id = {0}", usuarioId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        UsuarioEntity usuario = persistence.find(usuarioId);
        if (usuario == null) {
            LOGGER.log(Level.SEVERE, "El usuario con el id = {0} no existe", usuarioId);
           throw new BusinessLogicException("El usuario consultado no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el usuario con id = {0}", usuarioId);
        return usuario;
    }
    
    
    
    /**
     * Actualiza un usuario por medio de su id.
     *
     * @param usuarioId: id del usuario para ser actualizado.
     * @return el usuario solicitado por medio de su id.
     */
    public UsuarioEntity updateUsuario(UsuarioEntity usuarioEntity, Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la información de un usuario con id = {0}", usuarioId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        if (usuario.getDocumento()==null|usuario.getNombre()==null|usuario.getUserName()==null) {
           throw new BusinessLogicException("No es posible actualizar un usuario con información nula");
        }
                UsuarioEntity usuario = persistence.update(usuarioEntity);

        LOGGER.log(Level.INFO, "Termina proceso de actualizar el usuario con id = {0}", usuarioId);
        return usuario;
    }
    
    
    /**
     * Borrar un usuario
     *
     * @param usuarioId: id del usuario a borrar
     * @throws BusinessLogicException si el usuario quiere borrarse a si mismo.
     * asociado.
     */
    public void deleteUsuario(Long userID) throws BusinessLogicException {
     
        
        UsuarioEntity usuarioEntity = persistence.find(userID);
        if (usuarioEntity!=null) {
            throw new BusinessLogicException("No es posible borrar su cuenta, mande un ticket a los administradores");
        }
     
    }


    
}
    
