/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
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
    
    private double latitudOrigen;
    
    private double longitudOrigen;
    
    private double latitudDestino;
    
    private double longitudDestino;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaSalida;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaLlegada;
    
    @PodamExclude
    @ManyToOne
    private ComboEntity combo;
    
    @PodamExclude
    @ManyToOne
    private ProveedorEntity proveedor;
       
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
    public double getLatO(){
        return latitudOrigen;
    }
    
    /**
     * Devuelve la coordenada de longitud de origen del vuelo.
     *
     * @return longitud de origen
     */
    public double getLonO(){
        return longitudOrigen;
    }
    
    /**
     * Devuelve la coordenada de latitud de destino del vuelo.
     *
     * @return latitud de destino
     */
    public double getLatD(){
        return latitudDestino;
    }
    
    /**
     * Devuelve la coordenada de longitud de destino del vuelo.
     *
     * @return longitud de destino
     */
    public double getLonD(){
        return longitudDestino;
    }
    
        /**
     * Devuelve la fecha de salida del vuelo.
     *
     * @return fecha de salida del vuelo
     */
    public Date getFechaSalida(){
        return fechaSalida;
    }
    
        /**
     * Devuelve la fecha de llegada del vuelo.
     *
     * @return fecha de llegada del vuelo.
     */
    public Date getFechaLlegada(){
        return fechaLlegada;
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
    public void setLatO(double latitudOrigen){
        this.latitudOrigen = latitudOrigen;
    }
    
        /**
     * Modifica las coordenadas de origen del vuelo.
     *
     * @param longitudOrigen las coordenadas a actualizar
     */
    public void setLonO(double longitudOrigen){
        this.longitudOrigen = longitudOrigen;
    }
    
    /**
     * Modifica las coordenadas de destino del vuelo.
     *
     * @param latitudDestino las coordenadas a actualizar
     */
    public void setLatD(double latitudDestino){
        this.latitudDestino = latitudDestino;
    }
    
    /**
     * Modifica las coordenadas de destino del vuelo.
     *
     * @param longitudDestino las coordenadas a actualizar
     */
    public void setLonD(double longitudDestino){
        this.longitudDestino = longitudDestino;
    }
    
    /**
     * Modifica la fecha de salida del vuelo.
     *
     * @param fechaSalida la fecha a actualizar
     */
    public void setFechaSalida(Date fechaSalida){
        this.fechaSalida = fechaSalida;
    }
    
    /**
     * Modifica la fecha de llegada del vuelo.
     *
     * @param fechaLlegada la fecha a actualizar
     */
    public void setFechaLlegada(Date fechaLlegada){
        this.fechaLlegada = fechaLlegada;
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

    public ComboEntity getCombo() {
        return combo;
    }

    public void setCombo(ComboEntity combo) {
        this.combo = combo;
    }    
    
}
