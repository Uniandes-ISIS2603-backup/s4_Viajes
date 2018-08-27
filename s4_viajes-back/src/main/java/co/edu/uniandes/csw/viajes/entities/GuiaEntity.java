/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class GuiaEntity extends BaseEntity implements Serializable{
    
    private ActividadEntity actividad;
    
    private int edad;
    
    private String documento;
    
    private String nombre;
    
    private int puntuacion;
    
    private double sueldo;
    
    private List<String> idiomas;
    
    private ActividadEntity act;
    
    public int getEdad(){return edad;}
    public String getDocumento() {return documento;}
    public String getNombre() {return nombre;}
    public int getPuntuacion() {return puntuacion;}        
    public double getSueldo() {return sueldo;}
    public List getIdiomasList() {return idiomas;}
    public ActividadEntity getActividad(){return act;}
    
    public void setEdad(int pEdad) {edad = pEdad;}
    public void setPuntuacion(int pPuntuacion){puntuacion = pPuntuacion;}
    public void setNombre(String nom){nombre = nom;}
    public void setDocumento(String doc){documento = doc;}        
    public void setSueldo(double pSueldo){sueldo = pSueldo;}
    public void setIdiomas(List pIdiomas){idiomas = pIdiomas;}
    public void setIdiomasList(ActividadEntity actividad){act = actividad;}
    public void agregarIdioma(String pIdioma){idiomas.add(pIdioma);}
            
}
