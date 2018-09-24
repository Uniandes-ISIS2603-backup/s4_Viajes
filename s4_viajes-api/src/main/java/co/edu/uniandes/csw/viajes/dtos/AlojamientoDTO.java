/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * BookDTO Objeto de transferencia de datos de Editoriales. Los DTO contienen
 * las representaciones de los JSON que se transfieren entre el cliente y el
 * servidor. Al serializarse como JSON esta clase implementa el siguiente
 * modelo: <br>
 * <pre>
 *{
 * "costo": "double",
 * "estrellas": "integer",
 * "nombre": "string",
 * "puntuacion": "integer",
 * "tipo": "string",
 * "ubicacion": "string",
 * "noches": "integer",
 * "latitud": "double",
 * "longitud": "double"
 * }
 * </pre>
 *
 * @author ym.espana
 *
 */
public class AlojamientoDTO implements Serializable {
    //-----------------------------------------------------------------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------------------------------------------------------------

    /**
     * ID del alojamiento.
     */
    private Long id;

    /**
     * Costo del alojamiento.
     */
    private Double costo;

    /**
     * Estrellas del alojamiento.
     */
    private Integer estrellas;

    /**
     * Nombre del alojamiento.
     */
    private String nombre;

    /**
     * Tipo del alojamiento.
     */
    private String tipo;

    /**
     * Noches del alojamiento.
     */
    private Integer noches;

    /**
     * Latitud del alojamiento.
     */
    private double latitud;

    /**
     * Longitud del alojamiento.
     */
    private double longitud;

    /**
     * Puntuacion del alojamiento.
     */
    private Integer puntuacion;

    /**
     * Ubicacion del alojamiento.
     */
    private String ubicacion;

    /**
     * Proveedor del alojamiento.
     */
    private ProveedorDTO proveedor;

    //-----------------------------------------------------------------------------------------------------------------------
    //Metodos
    //-----------------------------------------------------------------------------------------------------------------------
    /**
     * Constructor defecto.
     */
    public AlojamientoDTO() {
    }

    /**
     * Constructor a partir de la entidad.
     *
     * @param alojamientoEntity Entidad del alojamiento.
     */
    public AlojamientoDTO(AlojamientoEntity alojamientoEntity) {
        if (alojamientoEntity != null) {
            this.id = alojamientoEntity.getId();
            this.costo = alojamientoEntity.getCosto();
            this.estrellas = alojamientoEntity.getEstrellas();
            this.latitud = alojamientoEntity.getLatitud();
            this.longitud = alojamientoEntity.getLongitud();
            this.noches = alojamientoEntity.getNoches();
            this.nombre = alojamientoEntity.getNombre();
            this.puntuacion = alojamientoEntity.getPuntuacion();
            this.tipo = alojamientoEntity.getTipo();
            this.ubicacion = alojamientoEntity.getUbicacion();
            if (alojamientoEntity.getProveedor() != null) {
                this.proveedor = new ProveedorDTO(alojamientoEntity.getProveedor());
            } else {
                this.proveedor = null;
            }
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del alojamiento asociado.
     */
    public AlojamientoEntity toEntity() {
        AlojamientoEntity alojamientoEntity = new AlojamientoEntity();

        alojamientoEntity.setId(this.id);
        alojamientoEntity.setCosto(this.costo);
        alojamientoEntity.setEstrellas(this.estrellas);
        alojamientoEntity.setLatitud(this.latitud);
        alojamientoEntity.setLongitud(this.longitud);
        alojamientoEntity.setNoches(this.noches);
        alojamientoEntity.setNombre(this.nombre);
        alojamientoEntity.setPuntuacion(this.puntuacion);
        alojamientoEntity.setTipo(this.tipo);
        alojamientoEntity.setUbicacion(this.ubicacion);
        if (this.proveedor != null) {
            alojamientoEntity.setProveedor(this.proveedor.toEntity());
        }

        return alojamientoEntity;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNoches() {
        return noches;
    }

    public void setNoches(Integer noches) {
        this.noches = noches;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    public void setProveedor(ProveedorDTO proveedor) {   
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
