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
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa un proveedor en la persistencia y permite su serialización
 * 
 * @author jf.torresp
 */
@Entity
public class ProveedorEntity extends BaseEntity implements Serializable{
    
    //Atributos//

    private String username;
    
    private String contrasena;
    
    private String nombre;
    
    private int puntuacion;
    
    private List<Long> idsServicios=new ArrayList<>();
    
    private String imagen;

    
    @PodamExclude
    @OneToMany (mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ServicioEntity> servicios = new ArrayList<>();

    public String getImagen() {
        return imagen;    
    }

    //Métodos//
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene el usuario de un proveedor.
     *
     * @return username del proveedor.
     */
    public String getUser() {
        return username;
    }

    /**
     * Obtiene la contraseña de un proveedor.
     *
     * @return password del proveedor.
     */
    public String getPassword() {
        return contrasena;
    }

    /**
     * Obtiene el nombre de un proveedor.
     *
     * @return nombre del proveedor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el puntaje de un proveedor.
     *
     * @return puntuacion del proveedor.
     */
    public int getPuntaje() {
        return puntuacion;
    }


    /**
     * Modifica (set) el usuario de un proveedor por el ingresado por parámetro.
     *
     * @param username nuevo usuario que modificará el actual.
     */
    public void setUser(String username) {
        this.username = username;
    }

    /**
     * Modifica (set) la contraseña de un proveedor por la ingresada por
     * parámetro.
     *
     * @param contrasena nueva contraseña que modificará la actual.
     */
    public void setPassword(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Modifica (set) el nombre de un proveedor por el ingresado por parámetro.
     *
     * @param nombre nuevo nombre que modificará el actual.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Modifica (set) el puntaje de un proveedor por el ingresado por parámetro.
     *
     * @param puntuacion nuevo puntaje que modificará el actual.
     */
    public void setPuntaje(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public List<Long> getIdsServicios() {
        return idsServicios;
    }

    public void setIdsServicios(List<Long> idsServicios) {
        this.idsServicios = idsServicios;
    }
    
    public void addIdServicio(Long idServicio) {
         
        idsServicios.add(0,idServicio);
    }
    
     public void deleteIdServicio(Long idServicio) {
        boolean ya=false;
        for(int i=0;i<idsServicios.size()&&!ya;i++)
            if(idsServicios.get(i)==idServicio)
            {
                idsServicios.remove(i);
                ya=true;
            }
    }

    public List<ServicioEntity> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioEntity> servicios) {
        this.servicios = servicios;
    }
    
     public void addServicio(ServicioEntity servicio)
    {
        if(servicio!=null)
             servicios.add(servicio);
    }
     public void addServicioFirst(ServicioEntity servicio)
    {
        if(servicio!=null)
             servicios.add(0,servicio);
    }
   
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
   
}
