/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import java.io.Serializable;

/**
 *
 * @author ym.espana
 */
public class TransporteTerrestreDTO implements Serializable {
    //-----------------------------------------------------------------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------------------------------------------------------------

    private Double costo;

    private String destino;

    private Long latitudOrigen;

    private Long longitudOrigen;

    private Long latitudDestino;

    private Long longitudDestino;

    private Integer numeroDias;

    private Integer numeroHoras;

    private Integer puntuacion;

    private Long id;

    //-----------------------------------------------------------------------------------------------------------------------
    //Metodos
    //-----------------------------------------------------------------------------------------------------------------------
    /**
     * Constructor por defecto.
     *
     * @param transporteEntity Entidad del alojamiento.
     */
    public TransporteTerrestreDTO(TransporteTerrestreEntity transporteEntity) {
        if (transporteEntity != null) {
            this.id = transporteEntity.getId();
            this.costo = transporteEntity.getCosto();
            this.destino = transporteEntity.getDestino();
            this.latitudDestino = transporteEntity.getLatitudDestino();
            this.latitudOrigen = transporteEntity.getLatitudOrigen();
            this.longitudDestino = transporteEntity.getLongitudDestino();
            this.longitudOrigen = transporteEntity.getLongitudOrigen();
            this.numeroDias = transporteEntity.getNumeroDias();
            this.numeroHoras = transporteEntity.getNumeroHoras();
            this.puntuacion = transporteEntity.getPuntuacion();
        }
    }

    public TransporteTerrestreDTO() {

    }

    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del alojamiento asociado.
     */
    public TransporteTerrestreEntity toEntity() {
        TransporteTerrestreEntity transporteTerrestreEntity = new TransporteTerrestreEntity();

        transporteTerrestreEntity.setId(this.id);
        transporteTerrestreEntity.setCosto(this.costo);
        transporteTerrestreEntity.setDestino(this.destino);
        transporteTerrestreEntity.setLatitudDestino(this.latitudDestino);
        transporteTerrestreEntity.setLatitudOrigen(this.latitudOrigen);
        transporteTerrestreEntity.setLongitudDestino(this.longitudDestino);
        transporteTerrestreEntity.setLongitudOrigen(this.longitudOrigen);
        transporteTerrestreEntity.setNumeroDias(this.numeroDias);
        transporteTerrestreEntity.setNumeroHoras(this.numeroHoras);
        transporteTerrestreEntity.setPuntuacion(this.puntuacion);

        return transporteTerrestreEntity;
    }

    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Long getLatitudOrigen() {
        return latitudOrigen;
    }

    public void setLatitudOrigen(Long latitudOrigen) {
        this.latitudOrigen = latitudOrigen;
    }

    public Long getLongitudOrigen() {
        return longitudOrigen;
    }

    public void setLongitudOrigen(Long longitudOrigen) {
        this.longitudOrigen = longitudOrigen;
    }

    public Long getLatitudDestino() {
        return latitudDestino;
    }

    public void setLatitudDestino(Long latitudDestino) {
        this.latitudDestino = latitudDestino;
    }

    public Long getLongitudDestino() {
        return longitudDestino;
    }

    public void setLongitudDestino(Long longitudDestino) {
        this.longitudDestino = longitudDestino;
    }

    public Integer getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(Integer numeroDias) {
        this.numeroDias = numeroDias;
    }

    public Integer getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(Integer numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
