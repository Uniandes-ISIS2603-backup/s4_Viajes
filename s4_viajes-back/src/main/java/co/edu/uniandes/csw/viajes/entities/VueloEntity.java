/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.List;

/**
 *Clase que representa un vuelo en la persistencia y permite su serialización
 * 
 * @author jf.torresp
 */
public class VueloEntity extends BaseEntity implements Serializable
{
    //Atributos//
    
    private int numero;
    
    private double costo;
    
    private double puntaje;
    
    private List latLongOrigen;
    
    private List latLongDestino;
    
    private ProveedorEntity proveedor;
    
    //Métodos//
    
    /**
     * Devuelve el numero del vuelo.
     *
     * @return el numero
     */
    public int getNumero(){
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
     * Devuelve las coordenadas (lat, long) de origen de vuelo.
     *
     * @return coordenadas de origen
     */
    public List getLatLongO(){
        return latLongOrigen;
    }
    
    /**
     * Devuelve las coordenadas (lst, long) de destino del vuelo.
     *
     * @return coordenadas de destino
     */
    public List getLatLongD(){
        return latLongDestino;
    }
    
    /**
     * Modifica el numero del vuelo
     *
     * @param numero el numero a actualizar
     */
    public void setNumero(int numero){
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
     * @param npuntaje el puntaje a actualizar
     */
    public void setPuntaje(double puntaje){
        this.puntaje = puntaje;
    }
    
    /**
     * Modifica las coordenadas de origen del vuelo.
     *
     * @param latLongOrigen las coordenadas a actualizar
     */
    public void setLatLongO(List latLongOrigen){
        this.latLongOrigen = latLongOrigen;
    }
    
    /**
     * Modifica las coordenadas de destino del vuelo.
     *
     * @param latLongDestino las coordenadas a actualizar
     */
    public void setLatLongD(List latLongDestino){
        this.latLongDestino = latLongDestino;
    }
    
    /**
     * Devuelve el proveedor a la que pertenece el libro.
     *
     * @return Una entidad de proveedor.
     */
    public ProveedorEntity getProveddor() {
        return proveedor;
    }

    /**
     * Modifica el proveedor al que pertenece el vuelo.
     *
     * @param proveedorEntity El nuevo proveedor.
     */
    public void seProveedor(ProveedorEntity proveedorEntity) {
        this.proveedor = proveedorEntity;
    }
}
