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
public class AlojamientoDTO extends ServicioDTO implements Serializable {
    //-----------------------------------------------------------------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------------------------------------------------------------

    /**
     * Estrellas del alojamiento.
     */
    private Integer estrellas;

    /**
     * Tipo del alojamiento.
     */
    private String tipo;


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
        super(alojamientoEntity);
        if (alojamientoEntity != null) { 
            this.estrellas = alojamientoEntity.getEstrellas();
            this.tipo = alojamientoEntity.getTipo();
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
        AlojamientoEntity alojamientoEntity =(AlojamientoEntity)super.toEntity() ;
        alojamientoEntity.setEstrellas(this.estrellas);
        alojamientoEntity.setTipo(this.tipo);
      
        return alojamientoEntity;
    }
  
    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
