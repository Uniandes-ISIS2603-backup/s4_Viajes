/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.VueloEntity;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
public class VueloDTO implements Serializable {

    //Atributos//
    private String numero;
    private double costo;
    private double puntuacion;
    private Long latitudOrigen;
    private Long longitudOrigen;
    private Long latitudDestino;
    private Long longitudDestino;

    //Constructores//
    
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
        if (vueloEntity != null) {
            this.numero = vueloEntity.getNumero();
            this.costo = vueloEntity.getCosto();
            this.puntuacion = vueloEntity.getPuntaje();
            this.latitudOrigen = vueloEntity.getLatO();
            this.longitudOrigen = vueloEntity.getLonO();
            this.latitudDestino = vueloEntity.getLatD();
            this.longitudDestino = vueloEntity.getLonD();
        }
    }

    //Métodos//
    /**
     * Obtiene el número de un vuelo.
     *
     * @return número del vuelo.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Obtiene el costo de un vuelo.
     *
     * @return costo del vuelo.
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Obtiene el puntaje de un vuelo.
     *
     * @return puntuación del vuelo.
     */
    public double getPuntuacion() {
        return puntuacion;
    }

    /**
     * Obtiene las coordenadas (latitud, longitud) de origen del vuelo
     * configurados en una lista tipo double.
     *
     * @return coordenadas (lat, long) de origen del vuelo.
     */
    public Long getLatitudOrigen() {
        return latitudOrigen;
    }
    
    public Long getLongitudOrigen(){
        return longitudOrigen;
    }

    /**
     * Obtiene las coordenadas (latitud, longitud) de destino del vuelo
     * configurados en una lista tipo double.
     *
     * @return coordenadas (lat, long) de destino del vuelo.
     */
    public Long getLatitudDestino() {
        return latitudDestino;
    }
    
    public Long getLongitudDestino(){
        return longitudDestino;
    }

    /**
     * Modifica (set) el número de un vuelo ingresado por parámetro.
     *
     * @param pNumero nuevo número que modificará el actual.
     */
    public void setNumero(String pNumero) {
        numero = pNumero;
    }

    /**
     * Modifica (set) el costo de un vuelo ingresado por parámetro.
     *
     * @param pCosto nuevo costo que modificará el actual.
     */
    public void setCosto(double pCosto) {
        costo = pCosto;
    }

    /**
     * Modifica (set) la puntuación de un vuelo ingresada por parámetro.
     *
     * @param pPuntaje nueva puntuación que modificará la actual.
     */
    public void setPuntuacion(double pPuntaje) {
        puntuacion = pPuntaje;
    }

    /**
     * Modifica (set) las coordenadas (lat,long) de origen ingresadas por
     * parámetro.
     *
     * @param pLatO nuevas coordenadas de origen que modificarán las actuales.
     */
    public void setLatitudOrigen(Long pLatO) {
        latitudOrigen = pLatO;
    }
    
    public void setLongitudOrigen(Long pLonO){
        longitudOrigen = pLonO;
    }

    /**
     * Modifica (set) las coordenadas (lat,long) dedestino ingresadas por
     * parámetro.
     *
     * @param pLatD nuevas coordenadas de destino que modificarán las actuales.
     */
    public void setLatitudDestino(Long pLatD) {
        latitudDestino = pLatD;
    }
    
    public void setLongitudDestino(Long pLonD){
        longitudDestino = pLonD;
    }
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public VueloEntity toEntity() {
        VueloEntity vueloEntity = new VueloEntity();
        vueloEntity.setNumero(this.numero);
        vueloEntity.setCosto(this.costo);
        vueloEntity.setPuntaje(this.puntuacion);
        vueloEntity.setLatO(this.latitudOrigen);
        vueloEntity.setLonO(this.longitudOrigen);
        vueloEntity.setLatD(this.latitudDestino);
        vueloEntity.setLonD(this.longitudDestino);
        return vueloEntity;
    }

    @Override
    public String toString() {
         return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }    
}
