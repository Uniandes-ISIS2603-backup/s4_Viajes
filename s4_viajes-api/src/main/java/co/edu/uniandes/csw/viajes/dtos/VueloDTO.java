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
public class VueloDTO implements Serializable
{
    
    //Atributos//
    
    private int numero;
    private double costo;
    private double puntuacion;
    private List latLongOrigen;
    private List latLongDestino;
    
    //Constructores//
    
    public VueloDTO(){
        
    }
    
    //Métodos//
    
    public int getNumero(){
        return numero;
    }
    
    public double getCosto(){
        return costo;
    }
    
    public double getPuntuacion()
    {
        return puntuacion;
    }
    
    public List getLatLongOrigen()
    {
        return latLongOrigen;
    }
    
    public List getLatLongDestino()
    {
        return latLongDestino;
    }
    
    public void setNumero(int pNumero)
    {
        numero = pNumero;
    }
    
    public void setCosto(double pCosto)
    {
        costo = pCosto;
    }
    
    public void setPuntuacion(double pPuntaje)
    {
        puntuacion = pPuntaje;
    }
    
    public void setLatLogOrigen(List pListO)
    {
        latLongOrigen = pListO;
    }
    
    public void setLatLongDestino(List pListD)
    {
        latLongDestino = pListD;
    }   
}
