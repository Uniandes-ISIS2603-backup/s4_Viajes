/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.AlojamientoEntity;
import java.io.Serializable;

/**
 *
 * BookDTO Objeto de transferencia de datos de Editoriales. Los DTO contienen
 * las representaciones de los JSON que se transfieren entre el cliente y el
 * servidor. Al serializarse como JSON esta clase implementa el siguiente
 * modelo: <br>
 * <pre>
 *{
 * "costo": "99999",
 * "estrellas": "5",
 * "nombre": "nombreHotel",
 * "puntuacion": "10",
 * "tipo": "HOTEL",
 * "ubicacion": "enAlgunLugar",
 * "noches": "5",
 * "latitud": "6.26584484",
 * "longitud": "-45.154799914"
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

    private Long id;
    private Double costo;
    private Integer estrellas;
    private String nombre;
    private String tipo;
    private Integer noches;
    private Long latitud;
    private Long longitud;
    private Integer puntuacion;
    private String ubicacion;

    //-----------------------------------------------------------------------------------------------------------------------
    //Metodos
    //-----------------------------------------------------------------------------------------------------------------------
    /**
     * Constructor por defecto.
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

    public Long getLatitud() {
        return latitud;
    }

    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    public Long getLongitud() {
        return longitud;
    }

    public void setLongitud(Long longitud) {
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
}
