/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Juan Felipe Torres
 */
public class VueloDTO implements Serializable {

    //Atributos//
    private int numero;
    private double costo;
    private double puntuacion;
    private List latLongOrigen;
    private List latLongDestino;

    //Constructores//
    public VueloDTO() {

    }

    //Métodos//
    /**
     * Obtiene el número de un vuelo.
     *
     * @return número del vuelo.
     */
    public int getNumero() {
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
    public List getLatLongOrigen() {
        return latLongOrigen;
    }

    /**
     * Obtiene las coordenadas (latitud, longitud) de destino del vuelo
     * configurados en una lista tipo double.
     *
     * @return coordenadas (lat, long) de destino del vuelo.
     */
    public List getLatLongDestino() {
        return latLongDestino;
    }

    /**
     * Modifica (set) el número de un vuelo ingresado por parámetro.
     *
     * @param pNumero nuevo número que modificará el actual.
     */
    public void setNumero(int pNumero) {
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
     * @param pListO nuevas coordenadas de origen que modificarán las actuales.
     */
    public void setLatLogOrigen(List pListO) {
        latLongOrigen = pListO;
    }

    /**
     * Modifica (set) las coordenadas (lat,long) dedestino ingresadas por
     * parámetro.
     *
     * @param pListD nuevas coordenadas de destino que modificarán las actuales.
     */
    public void setLatLongDestino(List pListD) {
        latLongDestino = pListD;
    }
}
