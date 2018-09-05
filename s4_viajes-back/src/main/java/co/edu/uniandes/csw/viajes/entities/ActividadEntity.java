/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jecantorm
 */

@Entity
public class ActividadEntity extends BaseEntity implements Serializable {
    
   // private Long id;
    
    private int costo;
    
    private boolean ofrece_guia;
    
    private int puntuacion;
    
    private int duracion;
    
    private double latitud;
    
    private double longitud;

    //public Long getIdentificador()
    //{
      //  return id;
    //}
    
    @PodamExclude
    @ManyToOne
    private ProveedorEntity proveedor;

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
