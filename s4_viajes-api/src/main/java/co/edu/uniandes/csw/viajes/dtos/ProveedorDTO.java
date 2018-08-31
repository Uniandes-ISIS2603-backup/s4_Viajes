/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import java.io.Serializable;

/**
 *
 * @author Juan Felipe Torres
 */
public class ProveedorDTO implements Serializable {

    //Atributos//
    private String username;
    private String contrasena;
    private String nombre;
    private int puntuacion;

    //Métodos//
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
     * @param pUser nuevo usuario que modificará el actual.
     */
    public void setUser(String pUser) {
        username = pUser;
    }

    /**
     * Modifica (set) la contraseña de un proveedor por la ingresada por
     * parámetro.
     *
     * @param pPassword nueva contraseña que modificará la actual.
     */
    public void setPassword(String pPassword) {
        contrasena = pPassword;
    }

    /**
     * Modifica (set) el nombre de un proveedor por el ingresado por parámetro.
     *
     * @param pNombre nuevo nombre que modificará el actual.
     */
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    /**
     * Modifica (set) el puntaje de un proveedor por el ingresado por parámetro.
     *
     * @param pPuntaje nuevo puntaje que modificará el actual.
     */
    public void setPuntaje(int pPuntaje) {
        puntuacion = pPuntaje;
    }
}
