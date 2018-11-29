/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import java.io.Serializable;

/**
 * /**
 * VueloDTO Objeto de transferencia de datos de Vuelos. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {

 *      "numero": String,
 *      "costo": double,
 *      "puntaje": double,
 *      "latLongDestino" : { "latitud" : double, "longitud: double},
 *      "latLongOrigen" : { "latitud" : double, "longitud: double}     
 *   }
 * </pre> Por ejemplo un vuelo se representa asi:<br>
 *
 * <pre>
 *
 *  {
 *      "numero" : A26,     
 *      "costo" : 100000,      
 *      "puntuacion" : 4.5, 
 *      "latLongDestino" : { "latitud" : 4.6098906, "longitud" : -95.08167809},
 *      "latLongOrigen" : "4.6097100,-74.0817500"
 *  }
 *
 * </pre>
 *
 * @author Juan Felipe Torres
 */
public class VueloDTO extends TransporteDTO implements Serializable {

   
    
    /**
     * Constructor por defecto
     */
    public VueloDTO() {

    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param vueloEntity: Es la entidad que se va a convertir a DTO
     */
    public VueloDTO(VueloEntity vueloEntity) {
        super(vueloEntity);
    }
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    @Override
    public VueloEntity toEntity() {
        VueloEntity vuelo=new VueloEntity();
        super.toEntity(vuelo);
        return vuelo;
    }
  
}
