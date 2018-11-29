/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.TransporteEntity;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class TransporteDTO extends ServicioDTO implements Serializable{
   
    
    /**
     * Latitiud del destino del transporte.
     */
    private Double latitudDestino;
    
    /**
     * Longitud del destino del transporte.
     */
    private Double longitudDestino;
     
    /**
     * Fecha de llegada al destino del transporte.
     */
    private List<Date> fechasLlegada;
    
    /**
     * Origen del transporte.
     */
    private String origen;
    
    /**
     * Destino del transporte.
     */
    private String destino;
    
     //-----------------------------------------------------------------------------------------------------------------------
    //Metodos
    //-----------------------------------------------------------------------------------------------------------------------
    /**
     * Constructor defecto.
     */
    public TransporteDTO(){
        
    }
    
    /**
     * Constructor a partir de la entidad.
     *
     * @param transporteEntity Entidad del transporte.
     */
    public TransporteDTO(TransporteEntity transporteEntity) {
        super(transporteEntity);
        if (transporteEntity != null) { 
            this.latitudDestino = transporteEntity.getLatitudDestino();
            this.longitudDestino = transporteEntity.getLongitudDestino();
            this.fechasLlegada = transporteEntity.getFechasLlegada();
            this.origen = transporteEntity.getOrigen();
            this.destino = transporteEntity.getDestino();
        }
    }
    
       /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del alojamiento asociado.
     */
    @Override
    public TransporteEntity toEntity() {
        TransporteEntity transporteEntity =new TransporteEntity();
        super.toEntity(transporteEntity) ;
        transporteEntity.setDestino(destino);
        transporteEntity.setFechasLlegada(fechasLlegada);
        transporteEntity.setLatitudDestino(latitudDestino);
        transporteEntity.setLongitudDestino(longitudDestino);
        transporteEntity.setOrigen(origen);
                
        return transporteEntity;
    }
    
     public void toEntity(TransporteEntity transporteEntity) {
        super.toEntity(transporteEntity) ;
        transporteEntity.setDestino(destino);
        transporteEntity.setFechasLlegada(fechasLlegada);
        transporteEntity.setLatitudDestino(latitudDestino);
        transporteEntity.setLongitudDestino(longitudDestino);
        transporteEntity.setOrigen(origen);              
    }
    
    
    public Double getLatitudDestino() {
        return latitudDestino;
    }

    public void setLatitudDestino(Double latitudDestino) {
        this.latitudDestino = latitudDestino;
    }

    public Double getLongitudDestino() {
        return longitudDestino;
    }

    public void setLongitudDestino(Double longitudDestino) {
        this.longitudDestino = longitudDestino;
    }

    public List<Date> getFechasLlegada() {
        return fechasLlegada;
    }

    public void setFechasLlegada(List<Date> fechasLlegada) {
        this.fechasLlegada = fechasLlegada;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    
    
    
    
}
