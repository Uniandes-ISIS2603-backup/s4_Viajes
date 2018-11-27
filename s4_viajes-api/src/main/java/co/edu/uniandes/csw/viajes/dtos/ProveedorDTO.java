/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.viajes.dtos;

import co.edu.uniandes.csw.viajes.entities.ProveedorEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * ProveedorDTO Objeto de transferencia de datos de Proveedores. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "nombre": String,
 *      "user": String,
 *      "password": String,
 *      "puntaje": Double
 *   }
 * </pre> Por ejemplo un proveedor se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "nombre": "Avianca",
 *      "user": "avianca123",
 *      "password": "Avianca2018",
 *      "puntaje": 4.2
 *   }
 *
 * </pre>
 * 
 * @author Juan Felipe Torres
 */
public class ProveedorDTO implements Serializable {

    //Atributos//
    private Long proveedorId;
    
    private String username;
    
    private String contrasena;
    
    private String nombre;
    
    private int puntuacion;
    
    private String imagen;

    //Constructores//
    
    /**
     * Constructor por defecto
     */
    public ProveedorDTO(){
        
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param proveedorEntity: Es la entidad que se va a convertir a DTO
     */
    public ProveedorDTO(ProveedorEntity proveedorEntity) {
        if (proveedorEntity != null) {
            this.proveedorId = proveedorEntity.getId();
            this.nombre = proveedorEntity.getNombre();
            this.username = proveedorEntity.getUser();
            this.contrasena = proveedorEntity.getPassword();
            this.puntuacion = proveedorEntity.getPuntaje();
            this.imagen=proveedorEntity.getImagen();
        }
    }


    //Métodos//

    /**
     * Obtiene el id de un proveedor.
     *
     * @return id del proveedor.
     */
    public Long getId() {
        return proveedorId;
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
     * Modifica (set) el id de un proveedor por el ingresado por parámetro.
     *
     * @param pId nuevo Id que modificará el actual.
     */
    public void setId(Long pId) {
        proveedorId = pId;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public ProveedorEntity toEntity() {
        ProveedorEntity proveedorEntity = new ProveedorEntity();
        proveedorEntity.setNombre(this.nombre);
        proveedorEntity.setUser(this.username);
        proveedorEntity.setPassword(this.contrasena);
        proveedorEntity.setPuntaje(this.puntuacion);
        proveedorEntity.setImagen(this.imagen);
        return proveedorEntity;
    }

    @Override
    public String toString() {
         return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }   
}
