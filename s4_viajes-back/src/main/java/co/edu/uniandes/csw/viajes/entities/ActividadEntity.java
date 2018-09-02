/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jecantorm
 */

@Entity
public class ActividadEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @OneToMany(mappedBy = "actividad", fetch=FetchType.LAZY)
    private List<GuiaEntity> guias = new LinkedList<GuiaEntity>();
    
    @Id
    private Long id;
    
    private int costo;
    
    private boolean ofrece_guia;
    
    private int puntuacion;
    
    private int duracion;
    
    private double latitud;
    
    private double longitud;
    
    public Long getIdentificador()
    {
        return id;
    }
    
    public int getCosto()
    {
        return costo;
    }
    
    public boolean getOfreceGuia()
    {
        return ofrece_guia;
    }
    
    public int getPuntuacion()
    {
        return puntuacion;
    }
    
    public int getDuracion()
    {
        return duracion;
    }
    
    public double getLatitud()
    {
        return latitud;
    }
    
    public double getLongitud()
    {
        return longitud;
    }
    
    /**
     * Devuelve las guias de la editorial.
     *
     * @return Lista de entidades de Libro.
     */
    public List<GuiaEntity> getGuias() {
        return guias;
    }
    
    public void setIdentificador(Long identificador)
    {
        this.id = identificador;
    }

    /**
     * Modifica los libros de la editorial.
     *
     * @param books Los nuevos libros.
     */
    public void setGuias(List<GuiaEntity> books) {
        this.guias = books;
    }
    
    public void setCosto(int pCosto){
        costo = pCosto;
    }
    public void setOfrece_guia(boolean pOfguia){
        ofrece_guia = pOfguia;
    }
    public void setPuntuacion(int pPuntuacion)
    {
        puntuacion = pPuntuacion;
    }
    public void setDuracion(int pDuracion)
    {
        duracion = pDuracion;
    }
    public void setLatitud(double pLatitud)
            {
                latitud = pLatitud;
            }
    public void setLongitud(double pLongitud)
    {
        longitud = pLongitud;
    }
}
