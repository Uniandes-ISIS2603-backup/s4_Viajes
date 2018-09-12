/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.entities.ComboEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class ComboDetailDTO extends ComboDTO implements Serializable{

    
    private List<VueloDTO> vuelos;
    
    private List<TransporteTerrestreDTO> transportesTerrestres;

    private List<ActividadDTO> actividades;
        
    private List<AlojamientoDTO> alojamientos;
    
    /**
     * Crea un nuevo objeto ComboDetailDTO vacio
     *
     */
    public ComboDetailDTO()
    {
        super();
        vuelos=new ArrayList<VueloDTO>();
        transportesTerrestres=new ArrayList<TransporteTerrestreDTO>();
        actividades=new ArrayList<ActividadDTO>();
        alojamientos=new ArrayList<AlojamientoDTO>();

    }

     /**
     * Crea un objeto ComboDetailDTO a partir de un objeto ComboEntity
     * incluyendo los atributos de ComboDTO.
     *
     * @param comboEntity Entidad ComboEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
     public ComboDetailDTO(ComboEntity comboEntity)
    {
        super(comboEntity);
        vuelos=new ArrayList<VueloDTO>();
        transportesTerrestres=new ArrayList<TransporteTerrestreDTO>();
        actividades=new ArrayList<ActividadDTO>();
        alojamientos=new ArrayList<AlojamientoDTO>();
        if (comboEntity != null) {
            if(comboEntity.getVuelos()!=null)
                 for(VueloEntity vuelo:comboEntity.getVuelos())
                     vuelos.add(new VueloDTO(vuelo));
            if(comboEntity.getTransporteTerrestre()!=null)
                for(TransporteTerrestreEntity transporte:comboEntity.getTransporteTerrestre())
                    transportesTerrestres.add(new TransporteTerrestreDTO(transporte));
            if(comboEntity.getActividades()!=null)
                for(ActividadEntity actividad:comboEntity.getActividades())
                     actividades.add(new ActividadDTO(actividad));
             if(comboEntity.getAlojamientos()!=null)
                for(AlojamientoEntity alojamiento:comboEntity.getAlojamientos())
                     alojamientos.add(new AlojamientoDTO(alojamiento));         
        }
         
     }
     
     /**
     * Convierte un objeto ComboDetailDTO a ComboEntity incluyendo los
     * atributos de ComboDTO.
     *
     * @return Nuevo objeto ComboEntity.
     *
     */
    @Override
    public ComboEntity toEntity() {
        ComboEntity comboEntity = super.toEntity();
        if (vuelos != null) {
            List<VueloEntity> vuelosEntity = new ArrayList<VueloEntity>();
            for (VueloDTO vueloDTO : vuelos) {
                vuelosEntity.add(vueloDTO.toEntity());
            }
            comboEntity.setVuelos(vuelosEntity);
        }
        if (alojamientos != null) {
            List<AlojamientoEntity> alojamientosEntity = new ArrayList<AlojamientoEntity>();
            for (AlojamientoDTO dalojamientoDTO : alojamientos) {
                alojamientosEntity.add(dalojamientoDTO.toEntity());
            }
            comboEntity.setAlojamientos(alojamientosEntity);
        }
        if (transportesTerrestres != null) {
            List<TransporteTerrestreEntity> transportesTerrestresEntity = new ArrayList<TransporteTerrestreEntity>();
            for (TransporteTerrestreDTO transporteTerrestreDTO : transportesTerrestres) {
                transportesTerrestresEntity.add(transporteTerrestreDTO.toEntity());
            }
            comboEntity.setTransporteTerrestre(transportesTerrestresEntity);
        }
        if (actividades != null) {
            List<ActividadEntity> actividadesEntity = new ArrayList<ActividadEntity>();
            for (ActividadDTO actividadDTO : actividades) {
                actividadesEntity.add(actividadDTO.toEntity());
            }
            comboEntity.setActividades(actividadesEntity);
        }
        return comboEntity;
    }

    
     /**
     * Obtiene la lista de vuelos del combo
     *
     * @return vuelos
     */
     public List<VueloDTO> getVuelos() {
        return vuelos;
    }

      /**
     * Modifica la lista de vuelos para el combo con los que llegan por parametro
     *
     * @param vuelos los vuelos
     */
    public void setVuelos(List<VueloDTO> vuelos) {
        this.vuelos = vuelos;
    }

    /**
     * Obtiene la lista de transportes terrestres del combo
     *
     * @return transportesTerrestres
     */
    public List<TransporteTerrestreDTO> getTransportesTerrestres() {
        return transportesTerrestres;
    }

    /**
     * Modifica la lista de transportes Terrestres para el combo con los que llegan por parametro
     *
     * @param transportesTerrestres los transportes Terrestres
     */
    public void setTransportesTerrestres(List<TransporteTerrestreDTO> transportesTerrestres) {
        this.transportesTerrestres = transportesTerrestres;
    }

     /**
     * Obtiene la lista de actividades del combo
     *
     * @return actividades
     */
    public List<ActividadDTO> getActividades() {
        return actividades;
    }

    /**
     * Modifica la lista de actividades para el combo con los que llegan por parametro
     *
     * @param actividades las actividades
     */
    public void setActividades(List<ActividadDTO> actividades) {
        this.actividades = actividades;
    }

     /**
     * Obtiene la lista de alojamientos del combo
     *
     * @return alojamientos
     */
    public List<AlojamientoDTO> getAlojamientos() {
        return alojamientos;
    }

    /**
     * Modifica la lista de alojamientos para el combo con los que llegan por parametro
     *
     * @param alojamientos los alojamientos
     */
    public void setAlojamientos(List<AlojamientoDTO> alojamientos) {
        this.alojamientos = alojamientos;
    }
    
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
