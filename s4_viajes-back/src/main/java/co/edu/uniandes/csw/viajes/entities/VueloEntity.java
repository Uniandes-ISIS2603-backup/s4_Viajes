/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *Clase que representa un vuelo en la persistencia y permite su serialización
 * 
 * @author jf.torresp
 */
@Entity
public class VueloEntity extends BaseEntity implements Serializable {
    //Atributos//
    
    private String numero;
    
    private double costo;
    
    private double puntaje;
    
    private Long latitudOrigen;
    
    private Long longitudOrigen;
    
    private Long latitudDestino;
    
    private Long longitudDestino;
    
    @PodamExclude
    @ManyToOne
    private ProveedorEntity proveedor;
    
    public VueloEntity(){
    
    }
    
    //Métodos//
    
    /**
     * Devuelve el numero del vuelo.
     *
     * @return el numero
     */
    public String getNumero(){
        return numero;
    }
    
    /**
     * Devuelve el costo del vuelo.
     *
     * @return el costo
     */
    public double getCosto(){
        return costo;
    }
    
    /**
     * Devuelve el puntaje del vuelo.
     *
     * @return el puntaje
     */
    public double getPuntaje(){
        return puntaje;
    }
    
    /**
     * Devuelve la coordenada de latitud de origen del vuelo.
     *
     * @return latitud de origen
     */
    public Long getLatO(){
        return latitudOrigen;
    }
    
    /**
     * Devuelve la coordenada de longitud de origen del vuelo.
     *
     * @return longitud de origen
     */
    public Long getLonO(){
        return longitudOrigen;
    }
    
    /**
     * Devuelve la coordenada de latitud de destino del vuelo.
     *
     * @return latitud de destino
     */
    public Long getLatD(){
        return latitudDestino;
    }
    
    /**
     * Devuelve la coordenada de longitud de destino del vuelo.
     *
     * @return longitud de destino
     */
    public Long getLonD(){
        return longitudDestino;
    }
    
    /**
     * Modifica el numero del vuelo
     *
     * @param numero el numero a actualizar
     */
    public void setNumero(String numero){
        this.numero = numero;
    }
    
    /**
     * Modifica el costo del vuelo
     *
     * @param costo el costo a actualizar
     */
    public void setCosto(double costo){
        this.costo = costo;
    }
    
    /**
     * Modifica el puntaje del vuelo.
     *
     * @param puntaje el puntaje a actualizar
     */
    public void setPuntaje(double puntaje){
        this.puntaje = puntaje;
    }
    
    /**
     * Modifica las coordenadas de origen del vuelo.
     *
     * @param latitudOrigen las coordenadas a actualizar
     */
    public void setLatO(Long latitudOrigen){
        this.latitudOrigen = latitudOrigen;
    }
    
        /**
     * Modifica las coordenadas de origen del vuelo.
     *
     * @param longitudOrigen las coordenadas a actualizar
     */
    public void setLonO(Long longitudOrigen){
        this.longitudOrigen = longitudOrigen;
    }
    
    /**
     * Modifica las coordenadas de destino del vuelo.
     *
     * @param latitudDestino las coordenadas a actualizar
     */
    public void setLatD(Long latitudDestino){
        this.latitudDestino = latitudDestino;
    }
    
        /**
     * Modifica las coordenadas de destino del vuelo.
     *
     * @param longitudDestino las coordenadas a actualizar
     */
    public void setLonD(Long longitudDestino){
        this.longitudDestino = longitudDestino;
    }
    
    /**
     * Devuelve el proveedor a la que pertenece el libro.
     *
     * @return Una entidad de proveedor.
     */
    public ProveedorEntity getProveedor() {
        return proveedor;
    }

    /**
     * Modifica el proveedor al que pertenece el vuelo.
     *
     * @param proveedorEntity El nuevo proveedor.
     */
    public void setProveedor(ProveedorEntity proveedorEntity) {
        this.proveedor = proveedorEntity;
    }
}
