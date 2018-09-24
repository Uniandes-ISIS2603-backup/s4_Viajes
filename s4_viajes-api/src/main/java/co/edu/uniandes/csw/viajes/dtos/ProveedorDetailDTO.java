/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ActividadEntity;
import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que implementa el ProveedorDetailDTO
 * 
 * @author jf.torresp
 */
public class ProveedorDetailDTO extends ProveedorDTO implements Serializable{
    
    private List<VueloDTO> vuelos;
    
    private List<TransporteTerrestreDTO> transportesTerrestres;

    private List<ActividadDTO> actividades;
    
    private List<AlojamientoDTO> alojamientos;
    
    /**
     * Crea un nuevo objeto ProveedorDetailDTO vacio
     *
     */
    public ProveedorDetailDTO()
    {
        super();
        vuelos=new ArrayList<VueloDTO>();
        transportesTerrestres=new ArrayList<TransporteTerrestreDTO>();
        actividades=new ArrayList<ActividadDTO>();
        alojamientos= new ArrayList<AlojamientoDTO>();
    }
    
        /**
     * Crea un objeto ProveedorDetailDTO a partir de un objeto ProveedorEntity
     * incluyendo los atributos de ProveedorDTO.
     *
     * @param proveedorEntity Entidad ProveedorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
     public ProveedorDetailDTO(ProveedorEntity proveedorEntity)
    {
        super(proveedorEntity);
        vuelos=new ArrayList<VueloDTO>();
        transportesTerrestres=new ArrayList<TransporteTerrestreDTO>();
        actividades=new ArrayList<ActividadDTO>();
        if (proveedorEntity != null) {
            if(proveedorEntity.getVuelos()!=null)
                 for(VueloEntity vuelo:proveedorEntity.getVuelos())
                     vuelos.add(new VueloDTO(vuelo));
            if(proveedorEntity.getTransportes()!=null)
                for(TransporteTerrestreEntity transporte:proveedorEntity.getTransportes())
                    transportesTerrestres.add(new TransporteTerrestreDTO(transporte));
            if(proveedorEntity.getActividades()!=null)
                for(ActividadEntity actividad:proveedorEntity.getActividades())
                     actividades.add(new ActividadDTO(actividad));
            if(proveedorEntity.getAlojamientos()!=null)
                for(AlojamientoEntity alojamiento:proveedorEntity.getAlojamientos())
                     alojamientos.add(new AlojamientoDTO(alojamiento));
        }       
     }
     
    /**
     * Convierte un objeto ProveedorDetailDTO a ProveedorEntity incluyendo los
     * atributos de ProveedorDTO.
     *
     * @return Nuevo objeto ProveedorEntity.
     *
     */
    @Override
    public ProveedorEntity toEntity() {
        ProveedorEntity proveedorEntity = super.toEntity();
        if (vuelos != null) {
            List<VueloEntity> vuelosEntity = new ArrayList<VueloEntity>();
            for (VueloDTO vueloDTO : vuelos) {
                vuelosEntity.add(vueloDTO.toEntity());
            }
            proveedorEntity.setVuelos(vuelosEntity);
        }
        if (transportesTerrestres != null) {
            List<TransporteTerrestreEntity> transportesTerrestresEntity = new ArrayList<TransporteTerrestreEntity>();
            for (TransporteTerrestreDTO transporteTerrestreDTO : transportesTerrestres) {
                transportesTerrestresEntity.add(transporteTerrestreDTO.toEntity());
            }
            proveedorEntity.setTransportes(transportesTerrestresEntity);
        }
        if (actividades != null) {
            List<ActividadEntity> actividadesEntity = new ArrayList<ActividadEntity>();
            for (ActividadDTO actividadDTO : actividades) {
                actividadesEntity.add(actividadDTO.toEntity());
            }
            proveedorEntity.setActividades(actividadesEntity);
        }
        if (alojamientos != null) {
            List<AlojamientoEntity> alojamientosEntity = new ArrayList<AlojamientoEntity>();
            for (AlojamientoDTO alojamientoDTO : alojamientos) {
                alojamientosEntity.add(alojamientoDTO.toEntity());
            }
            proveedorEntity.setAlojamientos(alojamientosEntity);
        }
        return proveedorEntity;
    }
    
    /**
     * Obtiene la lista de vuelos del proveedor
     *
     * @return vuelos
     */
     public List<VueloDTO> getVuelos() {
        return vuelos;
    }

    /**
     * Modifica la lista de vuelos para el proveedor con los que llegan por parametro
     *
     * @param vuelos los vuelos
     */
    public void setVuelos(List<VueloDTO> vuelos) {
        this.vuelos = vuelos;
    }

    /**
     * Obtiene la lista de transportes terrestres del proveedor
     *
     * @return transportesTerrestres
     */
    public List<TransporteTerrestreDTO> getTransportesTerrestres() {
        return transportesTerrestres;
    }

    /**
     * Modifica la lista de transportes Terrestres para el proveedor con los que llegan por parametro
     *
     * @param transportesTerrestres los transportes Terrestres
     */
    public void setTransportesTerrestres(List<TransporteTerrestreDTO> transportesTerrestres) {
        this.transportesTerrestres = transportesTerrestres;
    }

     /**
     * Obtiene la lista de actividades del proveedor
     *
     * @return actividades
     */
    public List<ActividadDTO> getActividades() {
        return actividades;
    }

    /**
     * Modifica la lista de actividades para el proveedor con los que llegan por parametro
     *
     * @param actividades las actividades
     */
    public void setActividades(List<ActividadDTO> actividades) {
        this.actividades = actividades;
    }
    
         /**
     * Obtiene la lista de alojamientos del proveedor
     *
     * @return alojamientos
     */
    public List<AlojamientoDTO> getAlojamientos() {
        return alojamientos;
    }
    
    /**
     * Modifica la lista de alojamiento para el proveedor con los que llegan por parametro
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
