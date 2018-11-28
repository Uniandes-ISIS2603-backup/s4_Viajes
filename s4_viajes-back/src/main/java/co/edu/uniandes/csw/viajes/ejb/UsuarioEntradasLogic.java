/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.EntradaEntity;
import co.edu.uniandes.csw.viajes.entities.PagoEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.EntradaPersistence;
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
public class UsuarioEntradasLogic {
    private static final Logger LOGGER = Logger.getLogger(UsuarioEntradasLogic.class.getName());

    @Inject
    private EntradaPersistence entradaPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    /**
     * Agregar un medalla a el usuario
     *
     * @param entradaId El id medalla a guardar
     * @param usuarioId El id de el usuario en la cual se va a guardar el
     * medalla.
     * @return La medalla creado.
     */
    public UsuarioEntity addEntrada(Long entradaId, Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un combo al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        EntradaEntity  entradaEntity = entradaPersistence.find(entradaId);
        if(usuarioEntity==null)
            throw new BusinessLogicException("El ususario con id "+usuarioId +" no existe");
        if(entradaEntity==null)
            throw new BusinessLogicException("La entrada con id "+entradaId +" no existe");
        for(long idEntrada : usuarioEntity.getIdsEntradas())
            if(entradaId == idEntrada)
                throw new BusinessLogicException("El usuario ya tiene asignado una entrada con id " + entradaId +".");
            else
            {
                EntradaEntity entrada = entradaPersistence.find(idEntrada);
               if(entrada==null)
                   {
//                     throw new BusinessLogicException("El pago con id " + medallaId +" no existe");
                   }
               else
                    usuarioEntity.addEntrada(entrada);
            } 
        usuarioEntity.addIdEntrada(entradaId);
        usuarioEntity.addEntradaFirst(entradaEntity);

        usuarioPersistence.update(usuarioEntity);

        LOGGER.log(Level.INFO, "Termina proceso de agregarle una entrada al usuario con id = {0}", usuarioId);
        return usuarioEntity;
    }

    /**
     * Retorna todos los pagos asociadas a un usuario
     *
     * @param usuarioId El ID de el usuario buscado
     * @return La lista de pagos de el usuario
     */
    public List<EntradaEntity> getEntradas(Long usuarioId) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "Inicia proceso de consultar las entradas asociados al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        if(usuarioEntity==null)
            throw new BusinessLogicException("El usuario con id "+usuarioId +" no existe");
        List<EntradaEntity> entradas=new ArrayList<>();
        for(long idEntrada : usuarioEntity.getIdsEntradas())   
        {
            EntradaEntity entrada = entradaPersistence.find(idEntrada);
            if(entrada==null)
               {
//                     throw new BusinessLogicException("El combo con id " + medallaId +" no existe");
               }
            else
                entradas.add(entrada);
        }
        
        return entradas;
    }

    /**
     * Retorna una medalla asociada a un usuario
     *
     * @param usuariosId El id de el usuario a buscar.
     * @param entradaId El id del pago a buscar
     * @return La medalla encontrada dentro de el usuario.
     * @throws BusinessLogicException Si de la medalla no se encuentra en la
     * usuario
     */
    public EntradaEntity getEntrada(Long usuarioId, Long entradaId) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "Inicia proceso de consultar la entrada con id = {0} del usuario con id = " + usuarioId, entradaId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        if(usuarioEntity==null)
            throw new BusinessLogicException("El usuario con id "+usuarioId +" no existe");
        EntradaEntity entrada=null;
        for(long idEntrada : usuarioEntity.getIdsEntradas())   
            if(entradaId==idEntrada){
                entrada = entradaPersistence.find(entradaId);
                break;
            }      
        if(entrada==null)
            throw new BusinessLogicException("El usuario con id "+usuarioId +" no tiene la entrada con id "+entradaId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la entrada con id = {0} del usuario con id = " + usuarioId, entradaId); 
        return entrada;
    }

}
