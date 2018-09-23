/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.TransporteTerrestreEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    private double latitudOrigen;

    private double longitudOrigen;

    private double latitudDestino;

    private double longitudDestino;

    private Integer numeroDias;

    private Integer numeroHoras;

    private Integer numeroMinutos;

    private Integer puntuacion;

    private Long id;

    private ProveedorDTO proveedor;

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
            this.numeroMinutos = transporteEntity.getNumeroMinutos();
            this.puntuacion = transporteEntity.getPuntuacion();
            if (transporteEntity.getProveedor() != null) {
                this.proveedor = new ProveedorDTO(transporteEntity.getProveedor());
            } else {
                this.proveedor = null;
            }
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
        transporteTerrestreEntity.setNumeroMinutos(this.numeroMinutos);
        transporteTerrestreEntity.setPuntuacion(this.puntuacion);
        if (this.proveedor != null) {
            transporteTerrestreEntity.setProveedor(this.proveedor.toEntity());
        }

        return transporteTerrestreEntity;
    }

    public Integer getNumeroMinutos() {
        return numeroMinutos;
    }

    public void setNumeroMinutos(Integer numeroMinutos) {
        this.numeroMinutos = numeroMinutos;
    }

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

    public double getLatitudOrigen() {
        return latitudOrigen;
    }

    public void setLatitudOrigen(double latitudOrigen) {
        this.latitudOrigen = latitudOrigen;
    }

    public double getLongitudOrigen() {
        return longitudOrigen;
    }

    public void setLongitudOrigen(double longitudOrigen) {
        this.longitudOrigen = longitudOrigen;
    }

    public double getLatitudDestino() {
        return latitudDestino;
    }

    public void setLatitudDestino(double latitudDestino) {
        this.latitudDestino = latitudDestino;
    }

    public double getLongitudDestino() {
        return longitudDestino;
    }

    public void setLongitudDestino(double longitudDestino) {
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

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
