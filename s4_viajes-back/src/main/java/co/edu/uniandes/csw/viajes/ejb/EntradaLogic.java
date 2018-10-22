/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.EntradaEntity;
import co.edu.uniandes.csw.viajes.entities.UsuarioEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ComboPersistence;
import co.edu.uniandes.csw.viajes.persistence.EntradaPersistence;
import co.edu.uniandes.csw.viajes.persistence.UsuarioPersistence;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Luis Gómez Amado
 */
@Stateless
public class EntradaLogic {
     private static final Logger LOGGER = Logger.getLogger(VueloLogic.class.getName());

    @Inject
    private EntradaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección dedependencias. 
    @Inject
    private UsuarioPersistence usuarioPersistence; 
    @Inject
    private ComboPersistence comboPersistence; 

    /**
     * Crea una entrada en la persistencia.
     *
     * @param userId El id del usuario que va a crear la entrada
     * @param entradaEntity La entidad que representa la entrada a
     * persistir.
     * @return La entidad de la entrada luego de persistirla.
     * @throws BusinessLogicException Si la entrada a persistir ya existe.
     */
    public EntradaEntity createEntrada(Long userId, EntradaEntity entradaEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la entrada");
        
        UsuarioEntity usuario = usuarioPersistence.find(userId);
        
        if(persistence.find(userId, entradaEntity.getId()) != null)
        {
            throw new BusinessLogicException("La entrada con id " + entradaEntity.getId() + " ya existe.");
        }
        
        verificarReglasDeNegocio(entradaEntity);
        
        ComboEntity combo = comboPersistence.find(entradaEntity.getCombo().getId());
        
        if(entradaEntity.getCalificacionComunidad() != 0){
            throw new BusinessLogicException("La calificación de la comunidad inicial es inválida: debe ser 0");
        }
        
        Date actual = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        
        if (entradaEntity.getFecha().getDate() != actual.getDate() || entradaEntity.getFecha().getMonth() != actual.getMonth() || entradaEntity.getFecha().getYear() != actual.getYear())
        {
            throw new BusinessLogicException("Fecha invàlida: La fecha ingresada "+ sdf.format(entradaEntity.getFecha())+ "es diferente de la actual " + sdf.format(actual)+".");
        }
        
        // Invoca la persistencia para crear el entrada
        entradaEntity.setAutor(usuario);
        entradaEntity.setCombo(combo);
        persistence.create(entradaEntity);
        usuario.getEntradas().add(entradaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la entrada");
        return entradaEntity;
    }
    
       /**
     * Obtener todas las entradas existentes en la base de datos.
     *
     * @param userId id del usuario del que se quieren conseguir las entradas
     * @return una lista de entradas.
     */
    public List<EntradaEntity> getEntradas(Long userId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las entradas");
        UsuarioEntity usuarioEntity = usuarioPersistence.find(userId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las entradas");
        return usuarioEntity.getEntradas();
    }
    
        /**
     * Obtener una entrada por medio de su id.
     *
     * @param userId id del usuario del que se quieren conseguir la entrada
     * @param entradaId: id de la entrada para ser buscado.
     * @return la entrada solicitada por medio de su id.
     */
    public EntradaEntity getEntrada(Long userId, Long entradaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la entrada con id = {0}", entradaId);
        EntradaEntity entradaEntity = persistence.find(userId, entradaId);
        
        if (entradaEntity == null) {
            LOGGER.log(Level.SEVERE, "La entrada con el id = {0} no existe", entradaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la entrada con id = {0}", entradaId);
        return entradaEntity;
    }
    
        /**
     * Actualizar una entrada.
     *
     * @param userId: id del usuario al cual se le quiere actualizar una entrada.
     * @param entradaEntity: entrada con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la entrada con los cambios actualizados en la base de datos.
     */
    public EntradaEntity updateEntrada(Long userId, EntradaEntity entradaEntity) throws BusinessLogicException 
    {

       
        UsuarioEntity user = usuarioPersistence.find(userId);

        if(entradaEntity != null && entradaEntity.getId() != null){
            LOGGER.log(Level.INFO, "Inicia proceso de actualizar la entrada con id = {0} del usuario con id = " +  entradaEntity.getId(), userId);
            entradaEntity.setAutor(user);
            Long entradaId = entradaEntity.getId();
            EntradaEntity entradaOriginal = persistence.find(userId, entradaId);
            if (entradaOriginal == null)
            {
                throw new BusinessLogicException("La entrada con id \"" + entradaId + "\" no existe pòr lo que no se puede actualizar.");
            }

            verificarReglasDeNegocio(entradaEntity);

            if(entradaEntity.getCalificacionComunidad() < 0 || entradaEntity.getCalificacionComunidad() >5 ){
                throw new BusinessLogicException("La calificación de la comunidad inicial es inválida: debe ser 0");
            }
            
            if (entradaEntity.getFecha().compareTo(entradaOriginal.getFecha()) != 0) {
                throw new BusinessLogicException("La fecha de publicacón no puede ser modificada.");
            }
            
        }

        EntradaEntity newEntity = persistence.update(entradaEntity);
        if(entradaEntity != null){
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el entrada con id = {0}", entradaEntity.getId());
        }

        return newEntity;
    }

    /**
     * Borrar una entrada
     *
     * @param userId: id del usuario del cual se quiere borrar la entrada.
     * @param entradaId: id del entrada a borrar
     * @throws BusinessLogicException Si la entrada a eliminar no existe.
     */
    public void deleteEntrada(Long userId, Long entradaId) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "Inicia proceso de borrar la entrada con id = {0} del usuario con id = " + userId, entradaId);
        EntradaEntity old = getEntrada(userId, entradaId);
        if (old == null) {
            throw new BusinessLogicException("La entrada con id = " + entradaId + " no esta asociada al usuario con id = " + userId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la entrada con id = {0} del usuario con id = " + userId, entradaId);
    }   
    
    /**
     * Método que verifica las reglas de negocio compartidas por el create y el update
     * @param entradaEntity La entrada a la que se le quieren verificar las reglas de negocio
     * @throws BusinessLogicException Si alguna regla de negocio no se cumple.
     */
    public void verificarReglasDeNegocio(EntradaEntity entradaEntity) throws BusinessLogicException{
        if (entradaEntity.getCombo() == null){
            throw new BusinessLogicException("El combo asignado a la entrada no puede ser nulo.");
        }
        if (entradaEntity.getTitulo() == null || entradaEntity.getTitulo().equals(""))
        {
            throw new BusinessLogicException("El titulo es inválido: no puede ser nulo ni vacío");
        }
        
        Pattern p1 = Pattern.compile("^[a-zA-Z0-9¡¿]{1}[a-zA-Z0-9!-/:-@¡¿ ]{4,40}$");
        Matcher m1 = p1.matcher(entradaEntity.getTitulo());
        
        if(!m1.find())
        {
            throw new BusinessLogicException("El título es inválido: debe tener entre 5 y 40 caracteres");
        }

        
        if (entradaEntity.getTextoContenido() == null || entradaEntity.getTextoContenido().equals("")) 
        {
            throw new BusinessLogicException("El texto del contenido es inválido: no puede ser nulo ni vacío");
        }
        
                
        Pattern p2 = Pattern.compile("^[A-Z¡¿0-9]{1}[a-zA-Z0-9!-/:-@¿¡ ]{49,255}$");
        m1 = p2.matcher(entradaEntity.getTextoContenido());
        
        if(!m1.find())
        {
            throw new BusinessLogicException("El texto de contenido es inválido: debe comenzar por mayúscula y tener entre 50 y 255 caracteres");
        }
        
        if (entradaEntity.getMultimedia().size() > 10)
        {
            throw new BusinessLogicException("La lista de archivos multimedia es inválida: No se pueden subir más de 10 archivos multimedia.");
        }
        
        if(entradaEntity.getPuntuacion() < 0 || entradaEntity.getPuntuacion() > 5){
            throw new BusinessLogicException("La puntuación es inválida: debe ser un valor entre 0 y 5.");
        }
        if (entradaEntity.getFecha() == null) {
            throw new BusinessLogicException("Fecha invàlida: La fecha no puede ser nula. Pudo haber un error en el formato.");
        }
    }
}
