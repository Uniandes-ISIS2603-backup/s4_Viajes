/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa un combo en la persistencia y permite su
 * serialización.
 * @author estudiante
 */
@Entity
public class ComboEntity  extends BaseEntity implements Serializable {
    //-----------------------------------------------------------------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------------------------------------------------------------
  
    private double costo;
    
    private String nombre;
    

    private int dias;
    
    private double horas;

    private int puntuacion;
    
    private List<Long> idsReservas=new ArrayList<>();

    
    public ComboEntity()
    {
    }

    @PodamExclude
    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReservaEntity> reservas=new ArrayList<ReservaEntity>();
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

    //-----------------------------------------------------------------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------------------------------------------------------------


    public double getCosto() {
        return costo;
    }
     
    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
   
    public List<ReservaEntity> getReservas() {
        
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }
 
    public void addReserva(ReservaEntity reserva)
    {
        if(reserva!=null)
             reservas.add(reserva);
    }
     public void addReservaFirst(ReservaEntity reserva)
    {
        if(reserva!=null)
             reservas.add(0,reserva);
    }
    public boolean isVacio()
    {
        if (idsReservas.isEmpty()) 
            return true;
        return false;     
    }

    public List<Long> getIdsReservas() {
        return idsReservas;
    }

    public void setIdsReservas(List<Long> idsReservas) {
        this.idsReservas = idsReservas;
    }
     public void addIdReserva(Long idReserva) {

        idsReservas.add(0,idReserva);
    }
     public void deleteIdReserva(Long idReserva) {
        boolean ya=false;
        for(int i=0;i<idsReservas.size()&&!ya;i++)
            if(idsReservas.get(i)==idReserva)
            {
                idsReservas.remove(i);
                ya=true;
            }
    }
}
