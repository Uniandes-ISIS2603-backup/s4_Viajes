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
 * "latitud": "long",
 * "longitud": "long"
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
    private Long latitud;

    /**
     * Longitud del alojamiento.
     */
    private Long longitud;

    /**
     * Puntuacion del alojamiento.
     */
    private Integer puntuacion;

    /**
     * Ubicacion del alojamiento.
     */
    private String ubicacion;


    //-----------------------------------------------------------------------------------------------------------------------
    //Metodos
    //-----------------------------------------------------------------------------------------------------------------------
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
        }
    }

    /**
     * Constructor por defecto.
     */
    public AlojamientoDTO() {
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

        return alojamientoEntity;
    }

    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    /**
     * Devuelve el ID del alojamiento.
     *
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Coloca un ID al alojamiento.
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el costo del alojamiento.
     *
     * @return costo
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * Coloca el costo del alojamiento.
     *
     * @param costo
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    /**
     * Devuelve las estrellas del alojamiento.
     *
     * @return estrellas
     */
    public Integer getEstrellas() {
        return estrellas;
    }

    /**
     * Coloca las estrellas del alojamiento.
     *
     * @param estrellas
     */
    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    /**
     * Devuelve el nombre del alojamiento.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Coloca el nombre del alojamiento.
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el tipo del alojamiento.
     *
     * @return tipo.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Coloca el tipo de alojamiento.
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve las noches del alojamiento.
     *
     * @return noches
     */
    public Integer getNoches() {
        return noches;
    }

    /**
     * Coloca las noches del alojamiento.
     *
     * @param noches
     */
    public void setNoches(Integer noches) {
        this.noches = noches;
    }

    /**
     * Devuelve la latitud del alojamiento.
     *
     * @return latitud.
     */
    public Long getLatitud() {
        return latitud;
    }

    /**
     * Coloca la latitud del alojamiento.
     *
     * @param latitud
     */
    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    /**
     * Devuelve la longitud del alojamiento.
     *
     * @return longitud
     */
    public Long getLongitud() {
        return longitud;
    }

    /**
     * Coloca la longitud del alojamiento.
     *
     * @param longitud
     */
    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }

    /**
     * Devuelve la puntuacion del alojamiento.
     *
     * @return puntuacion.
     */
    public Integer getPuntuacion() {
        return puntuacion;
    }

    /**
     * Coloca la puntuacion del alojamiento.
     *
     * @param puntuacion
     */
    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Devuelve la ubicacion del alojamiento.
     *
     * @return ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Coloca la ubicacion del alojamiento.
     *
     * @param ubicacion
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
