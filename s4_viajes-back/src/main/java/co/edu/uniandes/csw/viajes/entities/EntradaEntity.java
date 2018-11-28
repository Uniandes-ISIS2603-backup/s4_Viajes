/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import co.edu.uniandes.csw.viajes.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

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
    @PodamExclude
    @OneToOne
    private ComboEntity comboEntrada;
    private String titulo;
    private String textoContenido;
    private List<String> multimedia;
    private double puntuacion;
    private double calificacionComunidad;
    @Temporal(javax.persistence.TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;
    
    private Long idCombo;
    public UsuarioEntity getAutor()
    {
        return autor;
    }
    
    public List<ComentarioEntity> getComentarios()
    {
        return comentarios;
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
     
         public Date getFecha()
    {
         return fecha;
    }
         
    public ComboEntity getCombo()
    {
        return comboEntrada;
    }
     
     public void setAutor(UsuarioEntity pAutor)
    {
       autor = pAutor;  
    } 
        
     public void setComentarios(List<ComentarioEntity> pComentarios)
     {
         comentarios = pComentarios;
     }
     
     public void setCombo(ComboEntity pCombo)
    {
        comboEntrada = pCombo;
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
     
     public void setFecha(Date pFecha)
    {
        fecha = pFecha;
    } 
     
    public Long getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(Long idCombo) {
        this.idCombo = idCombo;
    }
    
}
