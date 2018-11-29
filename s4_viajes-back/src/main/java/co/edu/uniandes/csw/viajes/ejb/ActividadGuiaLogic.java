/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.ejb;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.GuiaEntity;
import co.edu.uniandes.csw.viajes.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.viajes.persistence.ActividadPersistence;
import co.edu.uniandes.csw.viajes.persistence.GuiaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Actividad y Guia.
 *
 * @author estudiante
 */
@Stateless
public class ActividadGuiaLogic {
    
     private static final Logger LOGGER = Logger.getLogger(ActividadGuiaLogic.class.getName());

     private static final String NO_EXISTE = " no existe.";
    @Inject
    private ActividadPersistence actividadPersistence;

    @Inject
    private GuiaPersistence guiaPersistence;

    /**
     * Agregar un guia a la actvidad
     *
     * @param documentoId El id guia a guardar
     * @param actividadId El id de la actividad en la cual se va a guardar el
     * guia.
     * @return El guia creado.
     */
    public ActividadEntity addGuia(Long guiaId, Long actividadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un guia al proveedor con id = {0}", actividadId);
        ActividadEntity actividadEntity = actividadPersistence.find(actividadId);
        GuiaEntity guiaEntity = guiaPersistence.find(guiaId);
        if(actividadEntity==null)
            throw new BusinessLogicException("La actividad con id "+actividadId + NO_EXISTE);
        if(guiaEntity==null)
            throw new BusinessLogicException("EL guia con id "+guiaId + NO_EXISTE);

         for(long idGuia : actividadEntity.getIdsGuias())
            if(idGuia == guiaId)
                throw new BusinessLogicException("El combo ya tiene asignado un alojamiento con id " + guiaId +".");
           
        actividadEntity.addIdGuia(guiaId);
        actividadEntity.addGuiaFirst(guiaEntity);

        actividadPersistence.update(actividadEntity);

        LOGGER.log(Level.INFO, "Termina proceso de agregarle un guia al proveedor con id = {0}", actividadId);
        return actividadEntity;
    }

    /**
     * Retorna todos los guias asociados con una actividad
     *
     * @param actividadId El ID de la actividad buscada
     * @return La lista de libros de la editorial
     */
    public List<GuiaEntity> getGuias(Long actividadId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los guias asociados a la actividad con id = {0}", actividadId);
        ActividadEntity actividadEntity= actividadPersistence.findByIdentificador(actividadId);
          if(actividadEntity==null)
            throw new BusinessLogicException("El proveedor con id "+actividadId + NO_EXISTE);
        List<GuiaEntity> guias=new ArrayList<>();
        
        for(long idGuia : actividadEntity.getIdsGuias())   
        {
            GuiaEntity guia = guiaPersistence.find(idGuia);
            if(guia==null)
               {
                  //No era un alojamiento
               }
            else
                guias.add(guia);
        }
        
        return guias;
    }

    /**
     * Retorna un Guia asociado a una Actividad
     *
     * @param actividadId El id de la editorial a buscar.
     * @param guiaId El id del guia a buscar
     * @return El guia encontrado dentro de la actividad.
     * @throws BusinessLogicException Si el guia no se encuentra en la
     * actividad
     */
    public GuiaEntity getGuia(Long actividadId, Long guiaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el guia con id = {0} de la actividad con id = {1}" , new Object[]{guiaId, actividadId});
        List<GuiaEntity> guias = actividadPersistence.findByIdentificador(actividadId).getGuias();
        GuiaEntity guiaEntity = guiaPersistence.findByDocumento(guiaId);
        int index = guias.indexOf(guiaEntity);
        LOGGER.log(Level.INFO, "GuiaList exists: {0}. Guia List Size: {1} ", new Object[]{true, guias.size()});
        LOGGER.log(Level.INFO, "Guia exists: {0}", guiaEntity != null);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el guia con id = {0} de la actividad con id = {1}", new Object[]{guiaId, actividadId});
        if (index >= 0) {
            return guias.get(index);
        }
        throw new BusinessLogicException("El guia no está asociado a la actividad");
    }

    /**
     * Remplazar guias de una actividad
     * @param guias Lista de guias que serán los de la actividad.
     * @param actividadId El id de la actividad que se quiere actualizar.
     * @return La lista de guias actualizada.
     */
    public List<GuiaEntity> replaceGuias(Long actividadId, List<GuiaEntity> guias) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la actividad con id = {0}" ,actividadId);
        ActividadEntity actividadEntity = actividadPersistence.findByIdentificador(actividadId);
        List<GuiaEntity> guiaList = guiaPersistence.findAll();
        for (GuiaEntity guia : guiaList) {
            if (guias.contains(guia)) {
                guia.setActividad(actividadEntity);
            } else if (guia.getActividad() != null && guia.getActividad().equals(actividadEntity)) {
                guia.setActividad(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la actividad con id = {0}", actividadId);
        return guias;
    }
    
}
