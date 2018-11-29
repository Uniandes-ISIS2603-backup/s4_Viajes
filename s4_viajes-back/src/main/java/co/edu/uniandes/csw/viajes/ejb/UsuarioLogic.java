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
import co.edu.uniandes.csw.viajes.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
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
    
    @Inject
    private ComboPersistence comboPersistence;
    
    @Inject
    private ComboLogic comboLogic;
    
    @Inject
    private UsuarioPagosLogic usuarioPagosLogic;


    
    /**
     * Se encarga de crear un Uusuario  en la base de datos.
     *
     * @param usuarioEntity Objeto de usuarioEntity con los datos nuevos
     * @return Objeto de AuthorEntity con los datos nuevos y su ID.
     */
    public UsuarioEntity createUsuario(UsuarioEntity usuarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del usuario");
        if(usuarioEntity.getDocumento().length()<8)
        {
            throw new BusinessLogicException("El documento debe tener al menos 8 caracteres");
        }
        
            if(usuarioEntity.getDocumento().length()>12)
        {
            throw new BusinessLogicException("El documento debe tener máximo 12 caracteres");
        }
        
         if(persistence.findByUserName(usuarioEntity.getUserName())!=null)
        {
            throw new BusinessLogicException("El nombre de usuario ya ha sido tomado");
        }
        
        persistence.create(usuarioEntity);

        
        LOGGER.log(Level.INFO, "Termina proceso de creación del usuario");
        return usuarioEntity;
       
    }
    
    
     /**
     * Devuelve todos los usuarios que hay en la base de datos.
     *
     * @return Lista de entidades de tipo usuario.
     */
    public List<UsuarioEntity> getUsuarios() throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los usuarios");
        List<UsuarioEntity> usuarios = persistence.findAll();
        for(UsuarioEntity usuario:usuarios){
            for(long idCombo : usuario.getIdsCombos())
            {
               ComboEntity combo = comboPersistence.find(idCombo);
               if(combo==null)
                   {
//                           throw new BusinessLogicException("El combo reserva que envio no existe");
                    }
               else
                   usuario.addCombo(combo);
                          
            } 
            List<PagoEntity> pagos=new ArrayList<>();
            for(long idCombo : usuario.getIdsCombos())
                pagos.addAll(usuarioPagosLogic.getPagosCombo(idCombo));
            usuario.setPagos(pagos);

        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los libros");
        return usuarios;
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
           throw new BusinessLogicException("El usuario consultado no existe");
        }
       
        for(long idCombo : usuario.getIdsCombos())
            {
               ComboEntity combo = comboPersistence.find(idCombo);
               if(combo==null)
                   {
//                           throw new BusinessLogicException("El combo reserva que envio no existe");
                    }
               else
                   usuario.addCombo(combo);
                          
            } 
        List<PagoEntity> pagos=new ArrayList<>();
        for(long idCombo : usuario.getIdsCombos())
            pagos.addAll(usuarioPagosLogic.getPagosCombo(idCombo));
        usuario.setPagos(pagos);
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
        if (usuarioEntity.getDocumento()==null|usuarioEntity.getNombre()==null|usuarioEntity.getUserName()==null) {

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
        if (usuarioEntity==null) 
            throw new BusinessLogicException("No es posible borrar, ya que el usuario no existe");
        for(Long idCombo:usuarioEntity.getIdsCombos())
        {
           ComboEntity combo= comboPersistence.find(idCombo);
           if(combo!=null)
               comboLogic.sePuedeEliminarCombo(idCombo);
        }
        for(Long idCombo:usuarioEntity.getIdsCombos())
        {
           ComboEntity combo= comboPersistence.find(idCombo);
           if(combo!=null)
               comboLogic.deleteComboSinVerificar(idCombo);
           
        }
        persistence.delete(userID);
        
    }
  }
    
    
    
   


    


     
    


    

 
