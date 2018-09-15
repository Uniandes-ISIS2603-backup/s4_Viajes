/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Luis Gómez Amado
 */
@Entity
public class EntradaEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity autor;
    @PodamExclude
    @OneToMany(cascade = CascadeType.ALL)
    private List<ComentarioEntity> comentarios;
    private int numero;
    private String titulo;
    private String textoContenido;
    private List<String> multimedia;
    private double puntuacion;
    private double calificacionComunidad;
    private Calendar fecha;
    
    public UsuarioEntity getAutor()
    {
        return autor;
    }
    
    public List<ComentarioEntity> getComentarios()
    {
        return comentarios;
    }
    
    public int getNumero()
    {
        return numero;
    }
    
    public String getTitulo()
    {
        return titulo;
    }
    
    public String getTextoContenido()
    {
        return textoContenido;
    }
    
    public List<String> getMultimedia()
    {
        return multimedia;
    }
    
     public double getPuntuacion()
    {
        return puntuacion;
    } 
     
     public double getCalificacionComunidad()
    {
         return calificacionComunidad;
    }
     
         public Calendar getFecha()
    {
         return fecha;
    }
     
     public void setAutor(UsuarioEntity pAutor)
    {
       autor = pAutor;  
    } 
        
     public void setComentarios(List<ComentarioEntity> pComentarios)
     {
         comentarios = pComentarios;
     }
         
     public void setNumero(int pNumero)
    {
        numero = pNumero;
    }
    
    public void setTitulo(String pTitulo)
    {
        titulo = pTitulo;
    }
    
    public void setTextoContenido(String pTextoContenido)
    {
        textoContenido = pTextoContenido;
    }
    
    public void setMultimedia(List<String> pMultimedia)
    {
        multimedia= pMultimedia;
    }
    
     public void setPuntuacion(double pPuntuacion)
    {
        puntuacion = pPuntuacion;
    } 
    
     public void setCalificacionComunidad(double pCalifComunidad)
    {
        calificacionComunidad = pCalifComunidad;
    } 
     
     public void setFecha(Calendar pFecha)
    {
        fecha = pFecha;
    } 
    
}
